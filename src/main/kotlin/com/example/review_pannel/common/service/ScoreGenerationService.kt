package com.example.review_pannel.common.service

import com.example.review_pannel.game.entity.GameScore
import com.example.review_pannel.user.entity.User
import com.example.review_pannel.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.PreparedStatement
import kotlin.random.Random

@Service
class ScoreGenerationService(
    private val jdbcTemplate: JdbcTemplate,
    private val userRepository: UserRepository,
) {

    @Transactional
    fun generateScoresForUsers() {
        // Step 1: Create 5 users if they don't already exist
        val users = createUsers()

        // Step 2: Generate random scores for each user
        users.forEach { user ->
            val scores = (1..10_000_00).map {
                GameScore(
                    score = Random.nextInt(0, 100), // Random score between 0 and 99
                    user = user
                )
            }
            batchInsertScores(scores)
        }
    }

    fun batchInsertScores(gameScores: List<GameScore>) {
        val sql = "INSERT INTO games (score, user_id) VALUES (?, ?)"

        jdbcTemplate.batchUpdate(sql, object : BatchPreparedStatementSetter {
            override fun setValues(ps: PreparedStatement, i: Int) {
                ps.setInt(1, gameScores[i].score)
                ps.setLong(2, gameScores[i].user.id)
            }

            override fun getBatchSize(): Int = gameScores.size
        })
    }

    private fun createUsers(): List<User> {
        // Create users if they don't exist
        val userList = listOf(
            User(name = "User 1", email = "user1@example.com", phoneNumber = "1234567890"),
            User(name = "User 2", email = "user2@example.com", phoneNumber = "1234567891"),
            User(name = "User 3", email = "user3@example.com", phoneNumber = "1234567892"),
            User(name = "User 4", email = "user4@example.com", phoneNumber = "1234567893"),
            User(name = "User 5", email = "user5@example.com", phoneNumber = "1234567894")
        )

        return userList.map { user ->
            userRepository.findById(user.id).orElseGet {
                userRepository.save(user)
            }
        }
    }
}