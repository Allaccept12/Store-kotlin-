package com.example.store.account.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable
class Email(
    email: String
) {

    @Column(name = "email", nullable = false, unique = true)
    private val email: String = email

    companion object {

        fun of(email: String): Email {
            return Email(email)
        }
    }
}
