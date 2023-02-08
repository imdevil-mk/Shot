package com.imdevil.shot.netease.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.imdevil.shot.common.base.LogFragment
import com.imdevil.shot.databinding.FragmentMainViewPagerBinding
import com.imdevil.shot.databinding.FragmentNeteaseBinding
import com.imdevil.shot.netease.ui.login.NeteaseLoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NeteaseFragment : LogFragment() {

    private lateinit var binding: FragmentNeteaseBinding
    private val neteaseViewModel: NeteaseViewModel by viewModels()
    private val loginViewModel: NeteaseLoginViewModel by activityViewModels()

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


    }
}