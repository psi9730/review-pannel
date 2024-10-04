package com.example.review_pannel.service

import com.example.review_pannel.dto.GameSummaryDto
import com.example.review_pannel.entity.GameScore
import com.example.review_pannel.repository.GameRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameRepository: GameRepository
) {
    fun getAllGames(): List<GameScore> {
        return gameRepository.getAllGameScore()
    }

    fun getGameSummary(): GameSummaryDto {
        val allGameScores = getAllGames().map{it.score}

        val average = if (allGameScores.isEmpty()) {
            0.0
        } else {
            allGameScores.average()
        }

        val highestScore = allGameScores.maxOrNull()
        return GameSummaryDto(average, highestScore ?: 0)
    }

    fun addGame(gameScore: GameScore) {
        if (gameScore.score < 0) {
            throw IllegalArgumentException("스코어는 0과 100 사이어야합니다.")
        }
        if (gameScore.score > 100) {
            throw IllegalArgumentException("스코어는 0과 100 사이어야합니다.")
        }
        return gameRepository.saveGameScore(gameScore)
    }
}