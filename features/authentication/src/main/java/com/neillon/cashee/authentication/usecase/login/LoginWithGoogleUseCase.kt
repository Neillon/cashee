package com.neillon.cashee.authentication.usecase.login

import com.google.firebase.auth.AuthCredential
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.auth.AuthRepository
import com.neillon.cashee.authentication.repository.user.UserFirebaseRepository
import com.neillon.cashee.authentication.usecase.ExceptionManager
import com.neillon.cashee.authentication.usecase.UseCase

class LoginWithGoogleUseCase(
    private val repository: AuthRepository,
    private val userRepository: UserFirebaseRepository
) : UseCase<LoginWithGoogleUseCase.Params, User> {
    data class Params(var credential: AuthCredential)

    override suspend fun execute(param: Params): User =
        ExceptionManager.doAndTreatExceptions<User> {
            val loggedUser = repository.loginWithGoogle(param.credential)
            userRepository.createIfNotExists(loggedUser)

            loggedUser
        }

    companion object {
        private const val TAG = "LoginWithGoogleUseCase"
    }
}