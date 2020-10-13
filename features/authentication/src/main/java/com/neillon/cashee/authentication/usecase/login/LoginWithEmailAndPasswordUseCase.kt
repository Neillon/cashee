package com.neillon.cashee.authentication.usecase.login

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.AuthRepository
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.common.exceptions.NoInternetConnectionException

class LoginWithEmailAndPasswordUseCase(
    private val repository: AuthRepository
) : UseCase<LoginWithEmailAndPasswordUseCase.Params, User> {
    data class Params(val email: String, val password: String)

    override suspend fun execute(param: Params): User {
        try {
            return repository.loginWithEmailAndPassword(param.email, param.password)
        } catch (e: Exception) {
            throw e
        }
    }

}