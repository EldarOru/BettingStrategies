package com.example.bettingstrategies.domain

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bettingstrategies.domain.models.BettingStrategy

@Database (entities = [BettingStrategy::class], version = 1, exportSchema = false)
abstract class StrategyDatabase: RoomDatabase() {

    abstract fun dao(): DAO

    /*
    companion object{
        @Volatile
        private var INSTANCE: StrategyDatabase? = null

        fun getDatabase(context: Context):StrategyDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StrategyDatabase::class.java,
                    "strategy_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

     */
}