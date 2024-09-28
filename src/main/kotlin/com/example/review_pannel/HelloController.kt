package com.example.review_pannel

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/ping")
    fun ping() = "pong"
}
