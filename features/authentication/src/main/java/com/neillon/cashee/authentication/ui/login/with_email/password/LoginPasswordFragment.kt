package com.neillon.cashee.authentication.ui.login.with_email.password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.neillon.authentication.databinding.FragmentLoginPasswordBinding
import org.koin.android.viewmodel.ext.android.viewModel

private const val ARG_EMAIL = "email"

class LoginPasswordFragment : Fragment() {

    private lateinit var binding: FragmentLoginPasswordBinding
    private lateinit var navController: NavController

    private val arguments: LoginPasswordFragmentArgs by navArgs()
    private val email by lazy { arguments.email }
    private val viewModel: LoginPasswordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupView()
        observeData()
    }

    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            val hasError = !it.isNullOrBlank()

            if (hasError)
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        viewModel.handleLoginSuccess.observe(viewLifecycleOwner, Observer {
            if (it)
                navigateToMain()
        })
    }

    private fun setupView() {
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                if (password.isNotEmpty() && password.isNotBlank())
                    binding.nextButtonPassword.enable()
                else
                    binding.nextButtonPassword.disable()
            }
        })
        binding.nextButtonPassword.disable()
        binding.nextButtonPassword.setOnClickListener {
            val password = binding.editTextPassword.text.toString()
            val user = viewModel.loginWithEmail(email, password)
        }
    }

    private fun navigateToMain() {
        val action =
            LoginPasswordFragmentDirections.actionLoginPasswordFragmentToMainActivity()
        navController.navigate(action)
        activity?.finish()
    }

    private fun setupNavigation() {
        navController = findNavController()
    }
}


