package com.example.store.account.domain

import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity


@Entity
class Account(
    @EmbeddedId
    val id: AccountId,

    @Embedded
    val email: Email,

    @Embedded
    val password: Password
) {

    fun createAccount(accountId: AccountId, email: Email, password: Password): Account? {
        return Account(accountId, email, password)
    }

    fun checkPassword(password: String): Boolean {
        return this.password.isMatched(password)
    }
}