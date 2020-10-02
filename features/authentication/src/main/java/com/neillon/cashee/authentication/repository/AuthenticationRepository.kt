package com.neillon.cashee.authentication.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.neillon.cashee.authentication.domain.User
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthenticationRepository(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    @SuppressLint("LongLogTag")
    override suspend fun loginWithGoogle(credential: AuthCredential): User =
        suspendCoroutine { continuation ->
            val loginTask = firebaseAuth.signInWithCredential(credential)

            loginTask
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = firebaseAuth.currentUser!!
                        val user = User(firebaseUser.email!!, "")

                        continuation.resume(user)
                    } else {
                        Log.w(TAG, "firebaseAuthWithGoogle: $ERROR_LOGGING_IN -> ${task.exception}")
                        continuation.resumeWithException(task.exception!!)
                    }
                }
                .addOnFailureListener { e -> continuation.resumeWithException(e) }
        }

    companion object {
        private const val TAG = "AuthenticationRepository"
        private const val ERROR_LOGGING_IN = "Error logging in on Firebase"
    }
}