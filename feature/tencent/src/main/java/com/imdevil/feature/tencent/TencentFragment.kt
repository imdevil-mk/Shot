package com.imdevil.feature.tencent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.imdevil.core.common.extensions.print
import com.imdevil.core.common.log.Dog
import com.imdevil.feature.tencent.feature.recommend.RecommendFragment
import com.imdevil.shot.core.network.common.model.onSuccess
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
            RecommendFragment()
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
        "首页",
        "广场",
        "我的",
        "关注",
    )

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
            // viewModel.getUserInfo("516959708")
            // viewModel.getPlaylistBriefByUser("516959708")
            // viewModel.getPlaylist("516959708", "7809078062")
            // viewModel.getSongDetail("002OKIox28ad9a")

            viewModel.getRecommendPlaylist().onSuccess {
                Dog.d(TAG, "getRecommendPlaylist: size = ${it.size}\n${it.print()}")
            }
            viewModel.getPlaylistBriefByUser("516959708").onSuccess {
                Dog.d(TAG, "getPlaylistBriefByUser: size = ${it.size}\n${it.print()}")
            }
            viewModel.getNewSongs().onSuccess {
                Dog.d(TAG, "getNewSongs: size = ${it.size}\n${it.print()}")
            }
            viewModel.getHotKeys().onSuccess {
                Dog.d(TAG, "getHotKeys: size = ${it.size}\n${it.print()}")
            }
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
