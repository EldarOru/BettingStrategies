package com.example.bettingstrategies.domain.usecases

import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.models.BettingStrategy

class AddToFavourite(private val generalRepository: GeneralRepository) {
    fun addToFavourite(bettingStrategy: BettingStrategy){
        generalRepository.addStrategyToFavourite(bettingStrategy)
    }
}