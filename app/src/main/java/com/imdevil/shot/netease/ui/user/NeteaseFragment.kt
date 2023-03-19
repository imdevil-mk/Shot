package com.imdevil.shot.netease.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.imdevil.shot.common.base.Dog
import com.imdevil.shot.common.base.LogFragment
import com.imdevil.shot.common.extensions.load
import com.imdevil.shot.common.extensions.safeCollect
import com.imdevil.shot.common.extensions.visibleOrGone
import com.imdevil.shot.common.ui.mainviewpager.MainViewPagerFragmentDirections
import com.imdevil.shot.data.model.isEmpty
import com.imdevil.shot.databinding.FragmentNeteaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NeteaseFragment : LogFragment() {

    companion object {
        private const val TAG = "NeteaseFragment"
    }

    private lateinit var binding: FragmentNeteaseBinding
    private val neteaseViewModel: NeteaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentNeteaseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safeCollect {
            neteaseViewModel.userProfile.collect { user ->
                Dog.d(TAG, "onViewCreated: $user")
                binding.login.visibleOrGone(user.isEmpty())
                binding.userProfile.root.visibleOrGone(!user.isEmpty())
                with(user) {
                    binding.userProfile.username.text = name
                    binding.userProfile.icon.load(avatar)
                }
            }
        }

        binding.login.setOnClickListener {
            findNavController().navigate(MainViewPagerFragmentDirections.actionMainViewPagerFragmentToLoginFragment())
        }
    }
}