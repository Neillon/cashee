package com.neillon.cashee.authentication.usecase.login

import com.google.firebase.auth.AuthCredential
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.repository.AuthRepository
import com.neillon.cashee.authentication.usecase.UseCase
import com.neillon.cashee.common.exceptions.NoInternetConnectionException

class LoginWithGoogleUseCase(
    private val repository: AuthRepository
) : UseCase<LoginWithGoogleUseCase.Params, User> {
    data class Params(var credential: AuthCredential)

    override suspend fun execute(param: Params): User {
        try {
            return repository.loginWithGoogle(param.credential)
        } catch (noInternetException: NoInternetConnectionException) {
            throw noInternetException
        } catch (e: Exception) {
            throw e
        }
    }

    companion object {
        private const val TAG = "LoginWithGoogleUseCase"
    }
}