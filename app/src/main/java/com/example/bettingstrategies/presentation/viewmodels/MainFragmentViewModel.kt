package com.example.bettingstrategies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.example.bettingstrategies.domain.usecases.AddToFavourite
import com.example.bettingstrategies.domain.usecases.GetStrategies
import com.example.bettingstrategies.domain.usecases.GetStrategiesRoom

class MainFragmentViewModel(
    private val getStrategies: GetStrategies,
    private val addToFavourite: AddToFavourite,
    private val getStrategiesRoom: GetStrategiesRoom
    ): () {

    private var strategiesLiveData: LiveData<ArrayList<BettingStrategy>> = getStrategies.getStrategies()
    private var strategiesLiveDataRoom: LiveData<ArrayList<BettingStrategy>> = getStrategiesRoom.getStrategiesRoom()

    var errorLiveData = MutableLiveData<Unit>()
    var successLiveData = MutableLiveData<Unit>()

    fun getStrategies(): LiveData<ArrayList<BettingStrategy>>{
        return strategiesLiveData
    }

    fun addToFavourite(bettingStrategy: BettingStrategy) {
        if (strategiesLiveDataRoom.value!!.contains(bettingStrategy)) {
            errorLiveData.value = Unit
        } else {
            addToFavourite.addToFavourite(bettingStrategy)
            successLiveData.value = Unit
        }
    }

    fun getError(): MutableLiveData<Unit>{
        return errorLiveData
    }

    fun getSuccess(): MutableLiveData<Unit>{
        return successLiveData
    }
}