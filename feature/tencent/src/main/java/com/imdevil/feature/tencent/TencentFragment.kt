package com.imdevil.feature.tencent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.tencent.databinding.FragmentTencentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TencentFragment : BaseFragment<FragmentTencentBinding>() {

    private val viewModel: TencentViewModel by viewModels()

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTencentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCookies.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://app.imdevil.shot/feature_settings_activity/add_cookies".toUri())
                .build()

            findNavController().navigate(request)
        }

        lifecycleScope.launch{
            //viewModel.getUserInfo("516959708")
            //viewModel.getPlaylistBriefByUser("516959708")
            //viewModel.getPlaylist("516959708", "7809078062")
            //viewModel.getSongDetail("002OKIox28ad9a")
        }
    }

    companion object {
        private const val TAG = "TencentFragment"
    }
}