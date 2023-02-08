package com.imdevil.shot.common.ui.mainviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.imdevil.shot.local.ui.user.LocalFragment
import com.imdevil.shot.netease.ui.user.NeteaseFragment
import com.imdevil.shot.qq.QqFragment

const val NETEASE_FRAGMENT_INDEX = 0;
const val QQ_FRAGMENT_INDEX = 1;
const val LOCAL_FRAGMENT_INDEX = 2;

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        NETEASE_FRAGMENT_INDEX to { NeteaseFragment() },
        QQ_FRAGMENT_INDEX to { QqFragment() },
        LOCAL_FRAGMENT_INDEX to { LocalFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}