package com.neillon.cashee.common.exceptions

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
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