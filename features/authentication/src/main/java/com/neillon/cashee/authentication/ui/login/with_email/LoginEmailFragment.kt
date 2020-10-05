package com.neillon.cashee.authentication.ui.login.with_email

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neillon.authentication.databinding.FragmentLoginEmailBinding

class LoginEmailFragment : Fragment() {

    private lateinit var binding: FragmentLoginEmailBinding

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

    companion object {
        const val TAG = "LoginWithEmailFragment"
    }
}