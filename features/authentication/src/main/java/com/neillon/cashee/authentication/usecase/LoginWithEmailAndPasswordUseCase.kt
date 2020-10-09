package com.neillon.cashee.authentication.usecase

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.AuthRepository
import com.neillon.cashee.authentication.util.FirebaseConfiguration

class LoginWithEmailAndPasswordUseCase(
    private val repository: AuthRepository
) : UseCase<LoginWithEmailAndPasswordUseCase.Params, User> {
    data class Params(val email: String, val password: String)

    override suspend fun execute(param: Params): User {
        return repository.loginWithEmailAndPassword(param.email, param.password)
    }

}