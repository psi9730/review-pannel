package com.example.review_pannel.user.entity

import com.example.review_pannel.game.entity.GameScore
import com.example.review_pannel.user.dto.CreateUserDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val phoneNumber: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    val scores: List<GameScore> = emptyList()
) {
    companion object {
        fun fromDto(dto: CreateUserDto): User {
            return User(
                name = dto.name,
                email = dto.email,
                phoneNumber = dto.phoneNumber
            )
        }
    }
}