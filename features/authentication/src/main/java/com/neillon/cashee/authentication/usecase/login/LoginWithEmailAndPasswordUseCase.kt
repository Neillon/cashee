package com.neillon.cashee.authentication.usecase.login

import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.auth.AuthRepository
import com.neillon.cashee.authentication.repository.user.UserFirebaseRepository
import com.neillon.cashee.common.exceptions.ExceptionManager
import com.neillon.cashee.authentication.usecase.UseCase

class LoginWithEmailAndPasswordUseCase(
    private val repository: AuthRepository,
    private val userRepository: UserFirebaseRepository
) : UseCase<LoginWithEmailAndPasswordUseCase.Params, User> {
    data class Params(val email: String, val password: String)

    override suspend fun execute(param: Params): User =
        ExceptionManager.doAndTreatExceptions<User> {
            val loggedUser = repository.loginWithEmailAndPassword(param.email, param.password)
            userRepository.createIfNotExists(loggedUser)

            loggedUser
        }
}
