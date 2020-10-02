package com.neillon.cashee.authentication.usecase

import com.neillon.cashee.authentication.domain.User
import kotlinx.coroutines.withContext

class LoginWithEmailAndPasswordUseCase : UseCase<LoginWithEmailAndPasswordUseCase.Params, User> {
    data class Params(val email: String, val password: String)

    override suspend fun execute(param: Params): User {
        return User("neilloncesar@gmail.com", "senha123")
    }

}