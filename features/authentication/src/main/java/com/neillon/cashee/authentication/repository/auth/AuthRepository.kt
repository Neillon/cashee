package com.neillon.cashee.authentication.repository.auth

import com.google.firebase.auth.AuthCredential
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.Repository

interface AuthRepository : Repository<User> {
    suspend fun loginWithGoogle(credential: AuthCredential): User
    suspend fun loginWithEmailAndPassword(email: String, password: String): User

    suspend fun register(email: String, name: String, password: String): User
}