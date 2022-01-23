package com.example.bettingstrategies.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bettingstrategies.domain.models.BettingStrategy

@Dao
interface DAO {

    @Insert
    fun addStrategy(bettingStrategy: BettingStrategy)

    @Query("SELECT * FROM strategy_table")
    fun readAllData(): MutableLiveData<ArrayList<BettingStrategy>>

    @Delete
    fun deleteStrategy(bettingStrategy: BettingStrategy)
}