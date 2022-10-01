package com.united.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.united.notes.databinding.FragmentRegisterBinding
import com.united.notes.models.UserRequest
import com.united.notes.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.btnLogin.setOnClickListener {
            authViewModel.loginUser(UserRequest("test11@gmail.com", "123", "test11001"))
        }
        binding.btnSignUp.setOnClickListener {
            authViewModel.registerUser(UserRequest("test11@gmail.com", "123", "test11001"))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    //token
                    findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                }
                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}