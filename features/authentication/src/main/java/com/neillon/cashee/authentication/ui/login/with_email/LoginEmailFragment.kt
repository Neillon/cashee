package com.neillon.cashee.authentication.ui.login.with_email

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.neillon.authentication.R
import com.neillon.authentication.databinding.FragmentLoginEmailBinding
import kotlinx.coroutines.launch

class LoginEmailFragment : Fragment() {

    private lateinit var binding: FragmentLoginEmailBinding
    private lateinit var navController: NavController
    private val viewModel: LoginEmailViewModel by activityViewModels<LoginEmailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginEmailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
    }

    private fun setupView() {
        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                viewModel.updateEmail(email)

                lifecycleScope.launch {
                    if (viewModel.validEmail()) {
                        binding.editTextEmail.error = null
                        binding.nextButtonEmail.enable()
                    } else {
                        binding.editTextEmail.error = getString(R.string.invalid_email)
                        binding.nextButtonEmail.disable()
                    }
                }

            }
        })

        binding.nextButtonEmail.disable()
        binding.nextButtonEmail.setOnClickListener { navigateToLoginWithPassword() }
    }

    private fun navigateToLoginWithPassword() {
        val action =
            LoginEmailFragmentDirections.actionLoginWithEmailFragmentToLoginPasswordFragment(
                viewModel.email.value.toString()
            )
        navController.navigate(action)
    }

    companion object {
        const val TAG = "LoginWithEmailFragment"
    }
}