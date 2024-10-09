package com.example.review_pannel.user.controller

import com.example.review_pannel.user.dto.CreateUserDto
import com.example.review_pannel.user.entity.User
import com.example.review_pannel.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{name}")
    fun getUserByName(@PathVariable("name") name: String): User? {
        return userService.findUserByName(name)
    }

    @PostMapping
    fun createUser(@RequestBody dto: CreateUserDto): User {
        return userService.saveUser(dto)
    }
}