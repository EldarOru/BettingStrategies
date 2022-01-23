package com.example.bettingstrategies.domain.usecases

import androidx.lifecycle.LiveData
import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.models.BettingStrategy

class GetStrategiesFavourite(private val generalRepository: GeneralRepository) {
    fun getStrategiesRoom(): LiveData<ArrayList<BettingStrategy>>{
        return generalRepository.getStrategiesFavourite()
    }
}