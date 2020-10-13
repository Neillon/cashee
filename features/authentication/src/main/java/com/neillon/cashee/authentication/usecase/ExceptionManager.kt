package com.neillon.cashee.authentication.usecase

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.neillon.cashee.common.exceptions.InvalidCredentialsException
import java.lang.Exception

object ExceptionManager {

    suspend fun <T> doAndTreatExceptions(block: suspend () -> T): T {
        try {
            return block()
        } catch (e: Exception) {
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> throw InvalidCredentialsException()
                else -> throw e
            }
        }
    }
}