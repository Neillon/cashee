package com.neillon.cashee.authentication.repository

import com.google.firebase.auth.AuthCredential
import com.neillon.cashee.authentication.domain.User

interface AuthRepository : Repository<User> {
    suspend fun loginWithGoogle(credential: AuthCredential): User
}