package com.neillon.cashee.authentication.repository.auth

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.common.network.NetworkManager
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthenticationRepository(
    private val firebaseAuth: FirebaseAuth,
    private val networkManager: NetworkManager
) : AuthRepository {

    @SuppressLint("LongLogTag")
    override suspend fun loginWithGoogle(credential: AuthCredential): User =
        networkManager.fetchFromInternet<User> {
            suspendCoroutine { continuation ->
                firebaseAuth.signInWithCredential(credential)
                    .addOnSuccessListener {
                        val user = User(it.user?.email!!, it.user?.displayName)
                        continuation.resume(user)
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "loginWithGoogle -> ${e.localizedMessage}")
                        continuation.resumeWithException(e)
                    }
            }
        }

    @SuppressLint("LongLogTag")
    override suspend fun loginWithEmailAndPassword(email: String, password: String): User =
        networkManager.fetchFromInternet<User> {
            suspendCoroutine { continuation ->
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val user = User(it.user?.email!!, it.user?.displayName)
                        continuation.resume(user)
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "loginWithEmailAndPassword -> ${e.localizedMessage}")
                        continuation.resumeWithException(e)
                    }
            }
        }


    @SuppressLint("LongLogTag")
    override suspend fun register(email: String, name: String, password: String): User =
        networkManager.fetchFromInternet<User> {
            suspendCoroutine { continuation ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val user = User(it.user?.email!!, it.user?.displayName)
                        continuation.resume(user)
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "register -> ${e.localizedMessage}")
                        continuation.resumeWithException(e)
                    }
            }
        }

    companion object {
        private const val TAG = "AuthenticationRepository"
    }
}