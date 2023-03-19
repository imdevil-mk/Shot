package com.imdevil.shot.netease.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.imdevil.shot.common.base.LogFragment
import com.imdevil.shot.common.extensions.goneIf
import com.imdevil.shot.common.extensions.safeCollect
import com.imdevil.shot.data.model.LoginStatus
import com.imdevil.shot.databinding.FragmentNeteaseLoginBinding
import dagger.hilt.android.AndroidEntryPoint

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

        binding.password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }
            false
        }

        binding.login.setOnClickListener {
            loginViewModel.login(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        safeCollect {
            loginViewModel.loginStatus.collect { status ->
                binding.loading.goneIf(status != LoginStatus.Logging)
                when (status) {
                    is LoginStatus.LoggedIn -> {
                        findNavController().navigate(NeteaseLoginFragmentDirections.actionLoginFragmentToMainViewPagerFragment())
                    }
                    LoginStatus.Logging -> {
                        binding.loading.visibility = View.VISIBLE
                    }
                    else -> {
                        // no-op
                    }
                }
            }
        }

        safeCollect {
            loginViewModel.error.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}