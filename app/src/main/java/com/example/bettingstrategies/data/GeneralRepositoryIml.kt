package com.example.bettingstrategies.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.StrategyDatabase
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.google.firebase.database.*

class GeneralRepositoryImpl(context: Context): GeneralRepository {

    private val strategyDao = StrategyDatabase.getDatabase(context).dao()
    private var strategiesLiveData: MutableLiveData<ArrayList<BettingStrategy>>? = null
    private var strategiesLiveDataRoom: MutableLiveData<ArrayList<BettingStrategy>>? = null
    private var database: DatabaseReference? = null

    init {
        strategiesLiveData = MutableLiveData()
        database = FirebaseDatabase.getInstance("https://bettingstrategies-144dd-default-rtdb.firebaseio.com/").reference
        getStrategiesFromRoom()
        getStrategiesFromFirebase()
    }

    override fun getStrategiesFromFirebase() {
        database?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val arrayStrategies = arrayListOf<BettingStrategy>()
                for (snapshotOne in snapshot.child("bettingstrategies").children){
                    arrayStrategies.add(
                        BettingStrategy(id = snapshotOne.child("id").value.toString().toInt(),
                            name = snapshotOne.child("name").value.toString(),
                            picUrl = snapshotOne.child("picUrl").value.toString(),
                            description = snapshotOne.child("description").value.toString())
                    )
                }
                strategiesLiveData?.value = arrayStrategies
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun getStrategiesFromRoom() {
        strategiesLiveDataRoom = strategyDao.readAllData()
    }

    override fun getStrategies(): LiveData<ArrayList<BettingStrategy>> {
        return strategiesLiveData!!
    }

    override fun getStrategiesRoom(): LiveData<ArrayList<BettingStrategy>> {
        return strategiesLiveDataRoom!!
    }

    override fun addStrategyToFavourite(bettingStrategy: BettingStrategy) {
        strategyDao.addStrategy(bettingStrategy)
        getStrategiesFromFirebase()
        Log.d("ORU", strategiesLiveDataRoom!!.value.toString())
    }

    override fun deleteStrategyFromFavourite(bettingStrategy: BettingStrategy) {
        strategyDao.deleteStrategy(bettingStrategy)
        getStrategiesFromFirebase()
    }

    override fun getStrategy(id: Int): BettingStrategy {
        return strategiesLiveData?.value?.find {
            it.id == id
        } ?: throw Exception("Element with id $id is not existed")
    }
}