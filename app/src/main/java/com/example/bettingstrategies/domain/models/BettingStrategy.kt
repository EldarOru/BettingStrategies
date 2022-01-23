package com.example.bettingstrategies.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "strategy_table")
data class BettingStrategy(
    @PrimaryKey(autoGenerate = false)
    val id: Int = UNDEFINED_ID,
    val name: String = "",
    val picUrl: String = "",
    val description: String = ""
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}