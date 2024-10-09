package com.example.review_pannel.game.repository

import com.example.review_pannel.game.entity.GameScore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository: JpaRepository<GameScore, Long> {}