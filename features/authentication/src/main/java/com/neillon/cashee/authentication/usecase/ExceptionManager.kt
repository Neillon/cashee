package com.neillon.cashee.authentication.usecase

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.neillon.cashee.common.exceptions.InvalidCredentialsException
import com.neillon.cashee.common.exceptions.NotRegisteredException
import java.lang.Exception

object ExceptionManager {

    suspend fun <T> doAndTreatExceptions(block: suspend () -> T): T {
        try {
            return block()
        } catch (e: Exception) {
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> throw InvalidCredentialsException()
                is FirebaseAuthInvalidUserException -> throw NotRegisteredException()
                else -> throw e
            }
        }
    }
}