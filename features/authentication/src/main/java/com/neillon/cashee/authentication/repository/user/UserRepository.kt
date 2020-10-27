package com.neillon.cashee.authentication.repository.user

import com.google.firebase.firestore.FirebaseFirestore
import com.neillon.cashee.authentication.domain.User
import com.neillon.cashee.authentication.util.FirebaseConfiguration
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepository(
    private val firestore: FirebaseFirestore
): UserFirebaseRepository {

    override suspend fun create(user: User) = suspendCoroutine<Unit> { continuation ->
        firestore.collection(FirebaseConfiguration.Collections.users)
            .document()
            .set(user)
            .addOnSuccessListener { continuation.resume(Unit) }
            .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
    }

    override suspend fun createIfNotExists(user: User) {
        val hasFirebaseUser = getByEmail(user.email) != null
        if (!hasFirebaseUser)
            create(user)
    }

    override suspend fun getByEmail(email: String): User? = suspendCoroutine { continuation ->
        firestore.collection(FirebaseConfiguration.Collections.users)
            .whereEqualTo("email", "email")
            .limit(1L)
            .get()
            .addOnSuccessListener { users -> continuation.resume(users.firstOrNull()?.toObject(User::class.java)) }
            .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
    }
}