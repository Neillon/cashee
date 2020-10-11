package com.neillon.cashee.authentication.ui.register.email

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
import com.neillon.authentication.databinding.FragmentRegisterEmailBinding
import kotlinx.coroutines.launch

class RegisterEmailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegisterEmailBinding

    private val viewModel by activityViewModels<RegisterEmailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterEmailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupView()
    }

    private fun setupView() {
        binding.editTextRegisterEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                viewModel.updateEmail(email)

                lifecycleScope.launch {
                    if (viewModel.validEmail(email)) {
                        if (!binding.editTextRegisterEmail.error.isNullOrBlank())
                            binding.editTextRegisterEmail.error = null
                        binding.nextButtonRegisterEmail.enable()
                    } else {
                        if (binding.editTextRegisterEmail.error.isNullOrBlank())
                            binding.editTextRegisterEmail.error = getString(R.string.invalid_email)
                        binding.nextButtonRegisterEmail.disable()
                    }
                }

            }
        })

        binding.nextButtonRegisterEmail.disable()
        binding.nextButtonRegisterEmail.setOnClickListener { navigateToRegisterName() }
    }

    private fun navigateToRegisterName() {
        val action =
            RegisterEmailFragmentDirections.actionRegisterFragmentToRegisterNameFragment(
                viewModel.email.value.toString()
            )
        navController.navigate(action)
    }

    private fun setupNavigation() {
        navController = findNavController()
    }
}