package com.example.bettingstrategies.domain

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.bettingstrategies.domain.models.BettingStrategy


interface GeneralRepository {

    fun getStrategiesFromFirebase()

    fun getStrategiesFromRoom()

    fun getStrategiesRoom(): LiveData<ArrayList<BettingStrategy>>

    fun getStrategies(): LiveData<ArrayList<BettingStrategy>>

    fun addStrategyToFavourite(bettingStrategy: BettingStrategy)

    fun deleteStrategyFromFavourite(bettingStrategy: BettingStrategy)

    fun getStrategy(id: Int): BettingStrategy
}