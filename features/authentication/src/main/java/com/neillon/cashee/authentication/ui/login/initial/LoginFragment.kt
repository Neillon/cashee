package com.neillon.cashee.authentication.ui.login.initial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.neillon.authentication.databinding.FragmentLoginBinding
import com.neillon.cashee.authentication.di.AuthenticationModule
import com.neillon.network.firebase.FirebaseConfiguration
import com.neillon.cashee.utils.Constants
import com.neillon.network.di.NetworkModule
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    private val authenticationViewModel by viewModel<LoginViewModel>()
    private val firebaseConfiguration: com.neillon.network.firebase.FirebaseConfiguration by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    private fun injectDependencies() {
        koinApplication {
            unloadKoinModules(listOf(AuthenticationModule.dependencies, NetworkModule.dependencies))
            loadKoinModules(listOf(AuthenticationModule.dependencies, NetworkModule.dependencies))
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
        observeData()
    }

    private fun observeData() {
        authenticationViewModel.error.observe(viewLifecycleOwner, Observer {
            val hasError = !it.isNullOrBlank()

            if (hasError)
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
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
        authenticationViewModel.loginWithGoogle(completedTask)
    }

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