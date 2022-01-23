package com.example.bettingstrategies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bettingstrategies.data.GeneralRepositoryImpl
import com.example.bettingstrategies.domain.usecases.*
import com.example.bettingstrategies.presentation.fragments.FavouriteFragment


class ViewModelFactory: ViewModelProvider.Factory{

    private val generalRepository by lazy {
        GeneralRepositoryImpl()
    }

    private val getStrategies by lazy {
        GetStrategies(generalRepository = generalRepository)
    }

    private val getStrategiesFavourite by lazy {
        GetStrategiesFavourite(generalRepository = generalRepository)
    }

    private val getStrategy by lazy {
        GetStrategy(generalRepository = generalRepository)
    }

    private val addToFavourite by lazy {
        AddToFavourite(generalRepository = generalRepository)
    }

    private val deleteFromFavourites by lazy {
        DeleteFromFavourite(generalRepository = generalRepository)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)){
            return MainFragmentViewModel(
                getStrategies = getStrategies,
                addToFavourite = addToFavourite,
                getStrategiesFavourite = getStrategiesFavourite) as T
        }

        if (modelClass.isAssignableFrom(DetailedStrategyFragmentViewModel::class.java)){
            return DetailedStrategyFragmentViewModel(
                getStrategies = getStrategies,
                getStrategy = getStrategy,
                addToFavourite = addToFavourite,
                getStrategiesFavourite = getStrategiesFavourite) as T
        }

        if (modelClass.isAssignableFrom(FavouriteFragmentViewModel::class.java)){
            return FavouriteFragmentViewModel(
                getStrategies = getStrategies,
                deleteFromFavourite = deleteFromFavourites,
                getStrategiesFavourite = getStrategiesFavourite) as T
        }

        if (modelClass.isAssignableFrom(DetailedFavouriteStrategyFragmentViewModel::class.java)){
            return DetailedFavouriteStrategyFragmentViewModel(
                getStrategies = getStrategies,
                getStrategy = getStrategy,
                addToFavourite = addToFavourite) as T
        }
        throw IllegalAccessException("ViewModel class is not found")
    }
}

