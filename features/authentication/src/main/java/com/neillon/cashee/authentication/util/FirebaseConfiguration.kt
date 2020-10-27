package com.neillon.cashee.authentication.util

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseConfiguration(var firebaseContext: Context) {

    var googleSignInClient: GoogleSignInClient
    val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(Constants.FirebaseAuthentication.REQUEST_ID_TOKEN)
            .build()
        googleSignInClient = GoogleSignIn.getClient(firebaseContext, gso)
    }

    object Collections {
        val users = "users"
    }
}