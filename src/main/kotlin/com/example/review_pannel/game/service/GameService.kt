package com.example.review_pannel.game.service

import com.example.review_pannel.game.dto.CreateGameScoreDto
import com.example.review_pannel.game.dto.GameSummaryDto
import com.example.review_pannel.game.entity.GameScore
import com.example.review_pannel.game.repository.GameRepository
import com.example.review_pannel.user.service.UserService
import com.example.review_pannel.user.util.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameRepository: GameRepository,
    private val userService: UserService
) {
    fun getAllGames(): List<GameScore> {
        return gameRepository.findAll()
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

    fun addGame(dto: CreateGameScoreDto): GameScore {
        GameScore.validateAddScore(dto)

        val user = userService.findUserById(dto.userId)
            ?: throw UserNotFoundException("User with ID ${dto.userId} not found.")

        return gameRepository.save(
            GameScore(
                score = dto.score,
                user = user,
            )
        )
    }
}