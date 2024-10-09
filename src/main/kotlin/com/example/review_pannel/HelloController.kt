package com.example.review_pannel

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @Value("\${spring.application.ping}")
    lateinit var pingMessage: String

    @GetMapping("/ping")
    fun ping(): String {
        return pingMessage
    }
}
