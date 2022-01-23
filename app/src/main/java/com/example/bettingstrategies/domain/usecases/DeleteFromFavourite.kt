package com.example.bettingstrategies.domain.usecases

import com.example.bettingstrategies.data.GeneralRepositoryImpl
import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.models.BettingStrategy

class DeleteFromFavourite(private val generalRepository: GeneralRepository) {
    fun deleteFromFavourite(bettingStrategy: BettingStrategy){
        generalRepository.deleteStrategyFromFavourite(bettingStrategy)
    }
}