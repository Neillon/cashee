package com.neillon.authentication.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.neillon.authentication.databinding.FragmentLoginBinding
import com.neillon.authentication.util.makeSimpleSnackBarWithMessage
import com.neillon.cashee.ui.AuthenticationActivity
import com.neillon.cashee.utils.Constants

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken("618600222236-u0qkd9kl9nlpocfnrij88le8nke95qk8.apps.googleusercontent.com") // 2I_jyg_dpHtGN-ZB1_X8yZNQ
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignInWithGoogle.setOnClickListener { signInWithGoogle() }

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constants.Code.REQUEST_CODE_SIGN_IN_WITH_GOOGLE -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, Constants.Code.REQUEST_CODE_SIGN_IN_WITH_GOOGLE)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            if (completedTask.isSuccessful) {
                val googleAccount = completedTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(googleAccount!!.idToken!!)
            } else {
                binding.root makeSimpleSnackBarWithMessage "Não foi possível realizar o login"
                Log.e(
                    TAG,
                    "handleSignInResult: Error when trying to log in -> ${completedTask.exception}"
                )
            }

        } catch (e: ApiException) {
            Log.w(AuthenticationActivity.TAG, "handleSignInResult: failed code -> " + e.statusCode);
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "firebaseAuthWithGoogle: Successfully logged")
                    val user = firebaseAuth.currentUser

                    val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
                    findNavController().navigate(action)
                    requireActivity().finish()

                } else {
                    Log.w(
                        TAG,
                        "firebaseAuthWithGoogle: Error logging in on Firebase -> ${task.exception}"
                    )
                    binding.root makeSimpleSnackBarWithMessage "Erro tentanto fazer login no firebase"
                }
            }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}