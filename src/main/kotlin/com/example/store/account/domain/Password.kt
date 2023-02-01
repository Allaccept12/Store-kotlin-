package com.example.store.account.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Embeddable
class Password(value: String) {

    @Column(name = "password")
    private var value: String? = null

    init {
        this.value = encodePassword(value)
    }

    fun isMatched(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, value)
    }

    private fun encodePassword(password: String): String {
        return BCryptPasswordEncoder().encode(password)
    }

    companion object {
        fun of(password: String): Password {
            return Password(password)
        }
    }
}
