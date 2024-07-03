package com.imdevil.shot.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.imdevil.feature.tencent.feature.home.HomepageFragment
import com.imdevil.feature.tencent.feature.onboard.OnboardFragment

const val MAIN_NAV_ONBOARD = 0
const val MAIN_NAV_HOMEPAGE = 1

class MainViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MAIN_NAV_ONBOARD to { OnboardFragment() },
        MAIN_NAV_HOMEPAGE to { HomepageFragment() },
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
