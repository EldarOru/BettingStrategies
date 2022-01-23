package com.example.bettingstrategies.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.bettingstrategies.domain.DAO
import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.StrategyDatabase
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.google.firebase.database.*

class GeneralRepositoryImpl: GeneralRepository {

    private var strategiesLiveData: MutableLiveData<ArrayList<BettingStrategy>>? = null
    private var strategiesLiveDataFavourite: MutableLiveData<ArrayList<BettingStrategy>>? = null
    private var database: DatabaseReference? = null

    init {
        strategiesLiveData = MutableLiveData()
        strategiesLiveDataFavourite = MutableLiveData()
        database = FirebaseDatabase.getInstance("https://bettingstrategies-144dd-default-rtdb.firebaseio.com/").reference
        getStrategiesFromFavourite()
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

    override fun getStrategiesFromFavourite() {
        database?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val arrayStrategies = arrayListOf<BettingStrategy>()
                for (snapshotOne in snapshot.child("favourites").children){
                    arrayStrategies.add(
                        BettingStrategy(id = snapshotOne.child("id").value.toString().toInt(),
                            name = snapshotOne.child("name").value.toString(),
                            picUrl = snapshotOne.child("picUrl").value.toString(),
                            description = snapshotOne.child("description").value.toString())
                    )
                }
                strategiesLiveDataFavourite?.value = arrayStrategies
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun getStrategies(): LiveData<ArrayList<BettingStrategy>> {
        return strategiesLiveData!!
    }

    override fun getStrategiesFavourite(): LiveData<ArrayList<BettingStrategy>> {
        return strategiesLiveDataFavourite!!
    }

    override fun addStrategyToFavourite(bettingStrategy: BettingStrategy) {
        database?.child("favourites")?.child(bettingStrategy.id.toString())?.setValue(bettingStrategy)
    }

    override fun deleteStrategyFromFavourite(bettingStrategy: BettingStrategy) {
        database?.child("favourites")?.child(bettingStrategy.id.toString())?.removeValue()
    }

    override fun getStrategy(id: Int): BettingStrategy {
        return strategiesLiveData?.value?.find {
            it.id == id
        } ?: throw Exception("Element with id $id is not existed")
    }
}