package com.example.review_pannel.repository

import com.example.review_pannel.entity.GameScore

interface GameRepository {
    fun saveGameScore(gameScore: GameScore)
    fun getAllGameScore(): List<GameScore>
}