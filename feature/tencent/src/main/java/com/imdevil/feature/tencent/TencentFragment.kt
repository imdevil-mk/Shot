package com.imdevil.feature.tencent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.common.demo.DemoListFragment
import com.imdevil.shot.feature.common.demo.DemoViewPager2Adapter
import com.imdevil.shot.feature.tencent.databinding.FragmentTencentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TencentFragment : BaseFragment<FragmentTencentBinding>() {

    private val viewModel: TencentViewModel by viewModels()

    private val fragmentCreators = listOf<() -> Fragment>(
        {
            DemoListFragment.newInstance("Demo")
        },
        {
            DemoListFragment.newInstance("Demo")
        },
        {
            DemoListFragment.newInstance("Demo")
        },
        {
            DemoListFragment.newInstance("Demo")
        },
    )

    private val tabTexts = listOf(
        "首页", "广场", "我的", "关注"
    )

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTencentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.addCookies.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://app.imdevil.shot/feature_settings_activity/add_cookies".toUri())
                .build()

            findNavController().navigate(request)
        }*/

        lifecycleScope.launch {
            //viewModel.getUserInfo("516959708")
            //viewModel.getPlaylistBriefByUser("516959708")
            //viewModel.getPlaylist("516959708", "7809078062")
            //viewModel.getSongDetail("002OKIox28ad9a")
        }

        binding.pager.adapter =
            DemoViewPager2Adapter(childFragmentManager, lifecycle, fragmentCreators)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabTexts[position]
        }.attach()
    }

    companion object {
        private const val TAG = "TencentFragment"
    }
}