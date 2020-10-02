package com.neillon.cashee.authentication.util

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class FirebaseConfiguration(var firebaseContext: Context) {

    var googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(Constants.FirebaseAuthentication.REQUEST_ID_TOKEN)
            .build()
        googleSignInClient = GoogleSignIn.getClient(firebaseContext, gso)
    }

    inner class Authentication {
        val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    }
}