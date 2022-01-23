package com.example.bettingstrategies.domain.usecases

import androidx.lifecycle.LiveData
import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.models.BettingStrategy

class GetStrategies(private val generalRepository: GeneralRepository) {

    fun getStrategies(): LiveData<ArrayList<BettingStrategy>>{
        return generalRepository.getStrategies()
    }

}