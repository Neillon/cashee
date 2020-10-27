package com.neillon.cashee.authentication.repository.user

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.Repository

interface UserFirebaseRepository: Repository<User> {
    suspend fun create(user: User)
    suspend fun createIfNotExists(user: User)
    suspend fun getByEmail(email: String): User?
}
