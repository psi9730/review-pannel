package com.example.review_pannel.repository

import com.example.review_pannel.entity.GameScore
import org.springframework.stereotype.Repository

@Repository
class GameRepositoryImpl: GameRepository {
    private val gameScores: MutableList<GameScore> = mutableListOf()

    override fun saveGameScore(gameScore: GameScore) {
        gameScores.add(gameScore)
        return
    }

    override fun getAllGameScore(): List<GameScore> {
        return gameScores
    }
}