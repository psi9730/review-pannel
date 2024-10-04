package com.example.review_pannel.controller

import com.example.review_pannel.service.GameService
import com.example.review_pannel.dto.GameSummaryDto
import com.example.review_pannel.entity.GameScore
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
    fun addGame(@RequestBody gameScore: GameScore) {
        return gameService.addGame(gameScore)
    }
}