package com.imdevil.feature.tencent.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.imdevil.shot.core.model.data.VISITOR
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.common.collect
import com.imdevil.shot.feature.common.demo.DemoListFragment
import com.imdevil.shot.feature.common.demo.DemoViewPager2Adapter
import com.imdevil.shot.feature.tencent.R
import com.imdevil.shot.feature.tencent.databinding.FragmentHomepageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageFragment : BaseFragment<FragmentHomepageBinding>() {

    private val viewModel: HomepageViewModel by viewModels()

    private val fragmentCreators = listOf<() -> Fragment>(
        {
            DemoListFragment.newInstance("创建")
        },
        {
            DemoListFragment.newInstance("收藏")
        },
        {
            DemoListFragment.newInstance("本地")
        },
    )

    private val tabTexts = listOf(
        "创建",
        "收藏",
        "本地",
    )

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentHomepageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter =
            DemoViewPager2Adapter(childFragmentManager, lifecycle, fragmentCreators)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabTexts[position]
        }.attach()

        binding.userProfile.signIn.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://app.imdevil.shot/feature_settings_activity/add_cookies".toUri())
                .build()

            findNavController().navigate(request)
        }

        viewModel.userInfo.collect(viewLifecycleOwner) {
            val signIn = it != VISITOR
            binding.userProfile.userInfoContent.visibility =
                if (signIn) View.VISIBLE else View.GONE
            binding.userProfile.signInContent.visibility =
                if (signIn) View.GONE else View.VISIBLE

            if (signIn) {
                binding.userProfile.username.text = it.name
                binding.userProfile.follows.text = HtmlCompat.fromHtml(
                    getString(R.string.follows, it.follows.toString()),
                    FROM_HTML_MODE_LEGACY
                )
                binding.userProfile.fans.text = HtmlCompat.fromHtml(
                    getString(R.string.fans, it.fans.toString()),
                    FROM_HTML_MODE_LEGACY
                )
                binding.userProfile.friends.text = HtmlCompat.fromHtml(
                    getString(R.string.friends, it.friends.toString()),
                    FROM_HTML_MODE_LEGACY
                )
            }
        }
    }

    companion object {
        private const val TAG = "HomepageFragment"
    }
}