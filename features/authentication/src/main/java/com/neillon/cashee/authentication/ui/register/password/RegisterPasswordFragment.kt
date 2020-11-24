package com.neillon.cashee.authentication.ui.register.password

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
import com.neillon.authentication.databinding.FragmentRegisterPasswordBinding
import com.neillon.cashee.common.utils.makeSimpleSnackBarWithMessage
import org.koin.android.viewmodel.ext.android.viewModel

private const val ARG_EMAIL = "email"
private const val ARG_NAME = "name"

class RegisterPasswordFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegisterPasswordBinding

    private val arguments: RegisterPasswordFragmentArgs by navArgs()
    private val email by lazy { arguments.email }
    private val name by lazy { arguments.name }

    private val viewModel by viewModel<RegisterPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterPasswordBinding.inflate(layoutInflater)
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
            if (hasError) {
                Toast.makeText(context, it!!, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupView() {
        binding.editTextRegisterPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                if (password.isNotEmpty() && password.isNotBlank())
                    binding.nextButtonRegisterPassword.enable()
                else
                    binding.nextButtonRegisterPassword.disable()
            }
        })
        binding.nextButtonRegisterPassword.disable()
        binding.nextButtonRegisterPassword.setOnClickListener {
            try {
                val password = binding.editTextRegisterPassword.text.toString()
                val user = viewModel.register(email, name, password)
                navigateToMain()

            } catch (e: Exception) {
                binding.root makeSimpleSnackBarWithMessage "Não foi possível realizar o login"
            }
        }
    }

    private fun navigateToMain() {
        val action =
            RegisterPasswordFragmentDirections.actionRegisterPasswordFragmentToMainActivity()
        navController.navigate(action)
        activity?.finish()
    }

    private fun setupNavigation() {
        navController = findNavController()
    }

    companion object {
        const val TAG = "RegisterPasswordFragment"
    }
}