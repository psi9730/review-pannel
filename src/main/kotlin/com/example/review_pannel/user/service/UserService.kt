package com.example.review_pannel.user.service

import com.example.review_pannel.user.dto.CreateUserDto
import com.example.review_pannel.user.entity.User
import com.example.review_pannel.user.repository.UserRepository
import com.example.review_pannel.user.util.UserAlreadyExistsException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun saveUser(user: CreateUserDto): User {
        try {
            return userRepository.save(User.fromDto(user))
        } catch (ex: DataIntegrityViolationException) {
            // Handle the unique constraint violation here
            throw UserAlreadyExistsException("User with this name already exists: ${user.name}")
        }
    }

    fun findUserByName(name: String): User? {
        return userRepository.findByName(name)
    }

    fun findUserById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }
}