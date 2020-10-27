package com.neillon.cashee.authentication.usecase.register

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.auth.AuthenticationRepository
import com.neillon.cashee.authentication.repository.user.UserFirebaseRepository
import com.neillon.cashee.authentication.usecase.ExceptionManager
import com.neillon.cashee.authentication.usecase.UseCase

class RegisterUseCase(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserFirebaseRepository
) : UseCase<RegisterUseCase.Params, User> {

    data class Params(val email: String, val name: String, val password: String)

    override suspend fun execute(param: Params): User =
        ExceptionManager.doAndTreatExceptions<User> {
            val loggedUser = authenticationRepository.register(param.email, param.name, param.password)
            userRepository.createIfNotExists(loggedUser)
            loggedUser
        }

}