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

    fun calGameSummary(gameScores: List<GameScore>): GameSummaryDto {
        val scores = gameScores.map{it.score}

        val average = if (scores.isEmpty()) {
            0.0
        } else {
            scores.average()
        }

        val highestScore = scores.maxOrNull()
        return GameSummaryDto(average, highestScore ?: 0)
    }

    fun getGameSummary(): GameSummaryDto {
        return calGameSummary(getAllGames())
    }

    fun getUserGameSummary(userId: Long): GameSummaryDto {
        val user = userService.findUserById(userId)
            ?: throw UserNotFoundException("User with ID $userId not found.")

        return calGameSummary(gameRepository.findByUser(user))
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