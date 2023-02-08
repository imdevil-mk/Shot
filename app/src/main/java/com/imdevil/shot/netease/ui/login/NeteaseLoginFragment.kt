package com.imdevil.shot.netease.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.imdevil.shot.common.base.LogFragment
import com.imdevil.shot.data.Resource
import com.imdevil.shot.databinding.FragmentNeteaseLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NeteaseLoginFragment : LogFragment() {

    companion object {
        private const val TAG = "LoginFragment"
    }

    private val loginViewModel: NeteaseLoginViewModel by activityViewModels()
    private lateinit var binding: FragmentNeteaseLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentNeteaseLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginStatus.collect { status ->
                    when (status) {
                        Resource.Loading -> {}
                        is Resource.Error -> {
                            Toast.makeText(context, status.msg, Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Success -> {
                            findNavController().navigate(NeteaseLoginFragmentDirections.actionLoginFragmentToMainViewPagerFragment())
                        }
                    }
                }
            }
        }

        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}