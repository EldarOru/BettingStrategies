package com.example.bettingstrategies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.example.bettingstrategies.domain.usecases.AddToFavourite
import com.example.bettingstrategies.domain.usecases.GetStrategies
import com.example.bettingstrategies.domain.usecases.GetStrategy

class DetailedStrategyFragmentViewModel(
    private val getStrategies: GetStrategies,
    private val getStrategy: GetStrategy,
    private val addToFavourite: AddToFavourite
): ViewModel() {

    private var strategiesLiveData: LiveData<ArrayList<BettingStrategy>> = getStrategies.getStrategies()

    fun getStrategies(): LiveData<ArrayList<BettingStrategy>> {
        return strategiesLiveData
    }

    var strategy = MutableLiveData<BettingStrategy>()

    fun getStrategy(id: Int){
        strategy.value = getStrategy.getStrategy(id)
    }


}