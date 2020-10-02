package com.neillon.cashee.authentication.usecase

import com.google.firebase.auth.AuthCredential
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.AuthRepository

class LoginWithGoogleUseCase(
    private val repository: AuthRepository
) : UseCase<LoginWithGoogleUseCase.Params, User> {
    data class Params(var credential: AuthCredential)

    override suspend fun execute(param: Params): User = repository.loginWithGoogle(param.credential)

    companion object {
        private const val TAG = "LoginWithGoogleUseCase"
    }
}