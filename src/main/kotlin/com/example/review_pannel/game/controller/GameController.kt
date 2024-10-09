package com.example.review_pannel.game.controller

import com.example.review_pannel.game.dto.CreateGameScoreDto
import com.example.review_pannel.game.service.GameService
import com.example.review_pannel.game.dto.GameSummaryDto
import com.example.review_pannel.game.entity.GameScore
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController(
    private val gameService: GameService
) {

    @GetMapping("/summary")
    fun getGameSummary(): GameSummaryDto {
        return gameService.getGameSummary()
    }

    @PostMapping
    fun addGame(@RequestBody gameScore: CreateGameScoreDto): GameScore {
        return gameService.addGame(gameScore)
    }
}