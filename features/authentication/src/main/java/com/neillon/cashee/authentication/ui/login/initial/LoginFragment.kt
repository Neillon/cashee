package com.neillon.cashee.authentication.ui.login.initial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider
import com.neillon.authentication.databinding.FragmentLoginBinding
import com.neillon.cashee.authentication.di.AuthenticationModule
import com.neillon.cashee.authentication.util.FirebaseConfiguration
import com.neillon.cashee.authentication.util.makeSimpleSnackBarWithMessage
import com.neillon.cashee.ui.AuthenticationActivity
import com.neillon.cashee.utils.Constants
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    private val authenticationViewModel by viewModel<LoginViewModel>()
    private val firebaseConfiguration: FirebaseConfiguration by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        koinApplication {
            unloadKoinModules(AuthenticationModule.dependencies)
            loadKoinModules(AuthenticationModule.dependencies)
        }
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
        navController = findNavController()

        setupViews()
        observeViewModel()
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

    private fun setupViews() {
        binding.buttonSignInWithGoogle.setOnClickListener { signInWithGoogle() }
        binding.buttonRegister.setOnClickListener { navigateToRegister() }
        binding.buttonSignInWithEmail.setOnClickListener { navigateToLoginEmail() }
    }

    private fun observeViewModel() {
        authenticationViewModel.handleLoginResult.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
                navController.navigate(action)
            }
        })
    }

    private fun signInWithGoogle() {
        val signInIntent = firebaseConfiguration.googleSignInClient.signInIntent
        startActivityForResult(signInIntent, Constants.Code.REQUEST_CODE_SIGN_IN_WITH_GOOGLE)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            if (completedTask.isSuccessful) {
                val googleAccount = completedTask.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(googleAccount!!.idToken!!, null)
                authenticationViewModel.loginWithGoogle(credential)
            } else {
                binding.root makeSimpleSnackBarWithMessage "Não foi possível realizar o login"
                Log.e(TAG, "handleSignInResult: ${completedTask.exception}")
            }

        } catch (e: ApiException) {
            Log.w(AuthenticationActivity.TAG, "handleSignInResult: failed code -> " + e.statusCode)
        }
    }

    /**
     * Navigate to register fragment
     */
    private fun navigateToRegister() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        navController.navigate(action)
    }

    private fun navigateToLoginEmail() {
        val action = LoginFragmentDirections.actionLoginFragmentToLoginWithEmailFragment()
        navController.navigate(action)
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}