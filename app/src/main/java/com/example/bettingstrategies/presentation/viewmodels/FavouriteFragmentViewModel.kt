package com.example.bettingstrategies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bettingstrategies.domain.models.BettingStrategy
import com.example.bettingstrategies.domain.usecases.AddToFavourite
import com.example.bettingstrategies.domain.usecases.DeleteFromFavourite
import com.example.bettingstrategies.domain.usecases.GetStrategies
import com.example.bettingstrategies.domain.usecases.GetStrategiesFavourite

class FavouriteFragmentViewModel(
    private val getStrategies: GetStrategies,
    private val deleteFromFavourite: DeleteFromFavourite,
    private val getStrategiesFavourite: GetStrategiesFavourite
): ViewModel() {

    private var strategiesLiveData: LiveData<ArrayList<BettingStrategy>> = getStrategies.getStrategies()
    private var strategiesLiveDataFavourite: LiveData<ArrayList<BettingStrategy>> = getStrategiesFavourite.getStrategiesRoom()

    var successLiveData = MutableLiveData<Unit>()

    fun getFavouriteStrategies(): LiveData<ArrayList<BettingStrategy>>{
        return strategiesLiveDataFavourite
    }

    fun deleteFromFavourites(bettingStrategy: BettingStrategy){
        deleteFromFavourite.deleteFromFavourite(bettingStrategy)
    }

    fun getSuccess(): MutableLiveData<Unit>{
        return successLiveData
    }
}