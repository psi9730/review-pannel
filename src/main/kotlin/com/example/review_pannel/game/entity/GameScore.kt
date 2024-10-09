package com.example.review_pannel.game.entity

import com.example.review_pannel.game.dto.CreateGameScoreDto
import com.example.review_pannel.user.entity.User
import jakarta.persistence.*

@Entity
@Table(name = "games")
data class GameScore(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var score: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
) {
    companion object {
        fun validateAddScore(dto: CreateGameScoreDto) {
            if (dto.score < 0) {
                throw IllegalArgumentException("스코어는 0과 100 사이어야합니다.")
            }
            if (dto.score > 100) {
                throw IllegalArgumentException("스코어는 0과 100 사이어야합니다.")
            }
        }
    }
}
