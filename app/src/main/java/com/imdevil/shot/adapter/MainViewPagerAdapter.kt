package com.imdevil.shot.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.imdevil.feature.tencent.TencentFragment
import com.imdevil.shot.local.LocalFragment
import com.imdevil.shot.netease.NeteaseFragment

const val NETEASE_FRAGMENT_INDEX = 0
const val TENCENT_FRAGMENT_INDEX = 1
const val LOCAL_FRAGMENT_INDEX = 2

class MainViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TENCENT_FRAGMENT_INDEX to { TencentFragment() },
        NETEASE_FRAGMENT_INDEX to { NeteaseFragment() },
        LOCAL_FRAGMENT_INDEX to { LocalFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}