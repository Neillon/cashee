package com.neillon.cashee.authentication.ui.login.with_email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.neillon.authentication.databinding.FragmentLoginPasswordBinding

private const val ARG_EMAIL = "email"

class LoginPasswordFragment : Fragment() {

    private lateinit var binding: FragmentLoginPasswordBinding

    private val arguments: LoginPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


