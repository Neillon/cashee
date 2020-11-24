package com.neillon.network.firebase

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.neillon.cashee.common.constants.FirebaseConstants

class FirebaseConfiguration(var firebaseContext: Context) {

    var googleSignInClient: GoogleSignInClient
    val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(FirebaseConstants.FirebaseAuthentication.REQUEST_ID_TOKEN)
            .build()
        googleSignInClient = GoogleSignIn.getClient(firebaseContext, gso)
    }

    object Collections {
        const val users = "users"
    }
}