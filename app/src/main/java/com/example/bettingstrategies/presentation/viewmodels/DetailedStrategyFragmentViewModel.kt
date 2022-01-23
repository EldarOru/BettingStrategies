package com.example.bettingstrategies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.example.bettingstrategies.domain.usecases.AddToFavourite
import com.example.bettingstrategies.domain.usecases.GetStrategies
import com.example.bettingstrategies.domain.usecases.GetStrategiesFavourite
import com.example.bettingstrategies.domain.usecases.GetStrategy

class DetailedStrategyFragmentViewModel(
    private val getStrategies: GetStrategies,
    private val getStrategy: GetStrategy,
    private val addToFavourite: AddToFavourite,
    private val getStrategiesFavourite: GetStrategiesFavourite
): ViewModel() {

    private var strategiesLiveData: LiveData<ArrayList<BettingStrategy>> = getStrategies.getStrategies()

    private var strategiesLiveDataFavourite: LiveData<ArrayList<BettingStrategy>>? = getStrategiesFavourite.getStrategiesRoom()

    fun getStrategies(): LiveData<ArrayList<BettingStrategy>> {
        return strategiesLiveData
    }

    var strategy = MutableLiveData<BettingStrategy>()

    fun addToFavourite(bettingStrategy: BettingStrategy){
        if (strategiesLiveDataFavourite?.value!!.contains(bettingStrategy)) {
            errorLiveData.value = Unit
        } else {
            addToFavourite.addToFavourite(bettingStrategy)
            successLiveData.value = Unit
        }
    }
    fun getStrategy(id: Int){
        strategy.value = getStrategy.getStrategy(id)
    }

    var errorLiveData = MutableLiveData<Unit>()
    var successLiveData = MutableLiveData<Unit>()

    fun getError(): MutableLiveData<Unit>{
        return errorLiveData
    }

    fun getSuccess(): MutableLiveData<Unit>{
        return successLiveData
    }
}