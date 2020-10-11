package com.neillon.cashee.authentication.ui.register.name

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.neillon.authentication.databinding.FragmentRegisterNameBinding

private const val ARG_EMAIL = "email"

class RegisterNameFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegisterNameBinding

    private val arguments: RegisterNameFragmentArgs by navArgs()
    private val email by lazy { arguments.email }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterNameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupView()
    }

    private fun setupView() {

        binding.editTextRegisterName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                val name = s.toString()

                if (name.isNotEmpty())
                    binding.nextButtonRegisterName.enable()
                else
                    binding.nextButtonRegisterName.disable()
            }

        })

        binding.nextButtonRegisterName.disable()
        binding.nextButtonRegisterName.setOnClickListener {
            val name = binding.editTextRegisterName.text.toString()
            navigateToRegisterPassword(email, name)
        }
    }

    private fun navigateToRegisterPassword(email: String, name: String) {
        val action = RegisterNameFragmentDirections.actionRegisterNameFragmentToRegisterPasswordFragment(email, name)
        navController.navigate(action)
    }

    private fun setupNavigation() {
        navController = findNavController()
    }

    companion object {
        const val TAG = "RegisterNameFragment"
    }
}