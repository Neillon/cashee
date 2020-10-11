package com.neillon.cashee.authentication.usecase.register

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.AuthenticationRepository
import com.neillon.cashee.authentication.usecase.UseCase

class RegisterUseCase(
    private val authenticationRepository: AuthenticationRepository
) : UseCase<RegisterUseCase.Params, User> {

    data class Params(val email: String, val name: String, val password: String)

    override suspend fun execute(param: Params): User =
        authenticationRepository.register(param.email, param.name, param.password)
}