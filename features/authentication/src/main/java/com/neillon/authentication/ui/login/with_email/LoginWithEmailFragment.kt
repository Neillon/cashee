package com.neillon.authentication.ui.login.with_email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neillon.authentication.R
import com.neillon.authentication.databinding.FragmentLoginWithEmailBinding

class LoginWithEmailFragment : Fragment() {

    private lateinit var binding: FragmentLoginWithEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginWithEmailBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        const val TAG = "LoginWithEmailFragment"
    }
}