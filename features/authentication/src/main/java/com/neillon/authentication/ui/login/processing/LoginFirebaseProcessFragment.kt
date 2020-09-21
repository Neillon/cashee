package com.neillon.authentication.ui.login.processing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neillon.authentication.databinding.FragmentLoginProcessingBinding

class LoginFirebaseProcessFragment : Fragment() {

    private lateinit var binding: FragmentLoginProcessingBinding

    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            email = it.getString(ARG_EMAIL)
            password = it.getString(ARG_PASSWORD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginProcessingBinding.inflate(layoutInflater)
        return binding.root
    }

    fun encryptPassword(passwd: String) { }

    companion object {
        const val TAG = "LoginProcessingFragment"
        private const val ARG_EMAIL = "email"
        private const val ARG_PASSWORD = "password"
    }
}