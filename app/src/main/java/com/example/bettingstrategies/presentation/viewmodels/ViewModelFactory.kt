package com.example.bettingstrategies.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bettingstrategies.data.GeneralRepositoryImpl
import com.example.bettingstrategies.domain.usecases.AddToFavourite
import com.example.bettingstrategies.domain.usecases.GetStrategies
import com.example.bettingstrategies.domain.usecases.GetStrategiesRoom
import com.example.bettingstrategies.domain.usecases.GetStrategy
import com.example.bettingstrategies.presentation.App
import com.google.firebase.database.core.Context

class ViewModelFactory: ViewModelProvider.Factory{
    val app = App()

    private val generalRepository by lazy {
        GeneralRepositoryImpl(app.getAppContext()!!)
    }

    private val getStrategies by lazy {
        GetStrategies(generalRepository = generalRepository)
    }

    private val getStrategiesRoom by lazy {
        GetStrategiesRoom(generalRepository = generalRepository)
    }

    private val getStrategy by lazy {
        GetStrategy(generalRepository = generalRepository)
    }

    private val addToFavourite by lazy {
        AddToFavourite(generalRepository = generalRepository)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)){
            return MainFragmentViewModel(
                getStrategies = getStrategies,
                addToFavourite = addToFavourite,
                getStrategiesRoom = getStrategiesRoom) as T
        }

        if (modelClass.isAssignableFrom(DetailedStrategyFragmentViewModel::class.java)){
            return DetailedStrategyFragmentViewModel(
                getStrategies = getStrategies,
                getStrategy = getStrategy,
                addToFavourite = addToFavourite) as T
        }
        throw IllegalAccessException("ViewModel class is not found")
    }
}