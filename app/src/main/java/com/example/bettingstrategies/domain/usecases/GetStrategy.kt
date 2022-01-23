package com.example.bettingstrategies.domain.usecases

import com.example.bettingstrategies.domain.GeneralRepository
import com.example.bettingstrategies.domain.models.BettingStrategy

class GetStrategy(private val generalRepository: GeneralRepository) {
    fun getStrategy(id: Int): BettingStrategy{
        return generalRepository.getStrategy(id)
    }
}