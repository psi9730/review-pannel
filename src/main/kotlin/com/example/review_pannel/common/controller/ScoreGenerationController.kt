package com.example.review_pannel.common.controller

import com.example.review_pannel.common.service.ScoreGenerationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class ScoreController(private val scoreGenerationService: ScoreGenerationService) {

    @PostMapping("/score/generate")
    fun generateScores(): ResponseEntity<String> {
        scoreGenerationService.generateScoresForUsers()
        return ResponseEntity("Scores generated for 5 users", HttpStatus.CREATED)
    }
}