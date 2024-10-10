package com.example.review_pannel.game.repository

import com.example.review_pannel.game.entity.GameScore
import com.example.review_pannel.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GameRepository: JpaRepository<GameScore, Long> {
    @Query("SELECT g FROM GameScore g WHERE g.user = :user")
    fun findByUser(user: User): List<GameScore>
}