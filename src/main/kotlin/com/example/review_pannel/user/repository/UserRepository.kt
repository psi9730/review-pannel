package com.example.review_pannel.user.repository

import com.example.review_pannel.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?

    @Query("SELECT u FROM User u JOIN FETCH u.scores WHERE u.name = :name")
    fun findUserWithScores(@Param("name") name: String): User


    @Query("SELECT u FROM User u JOIN FETCH u.scores")
    fun findAllUser(): List<User>
}