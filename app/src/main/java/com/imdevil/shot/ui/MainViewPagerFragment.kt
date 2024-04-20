package com.imdevil.shot.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.imdevil.shot.R
import com.imdevil.shot.adapter.LOCAL_FRAGMENT_INDEX
import com.imdevil.shot.adapter.MainViewPagerAdapter
import com.imdevil.shot.adapter.NETEASE_FRAGMENT_INDEX
import com.imdevil.shot.adapter.TENCENT_FRAGMENT_INDEX
import com.imdevil.shot.databinding.FragmentMainViewPagerBinding
import com.imdevil.shot.feature.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewPagerFragment : BaseFragment<FragmentMainViewPagerBinding>() {

    private lateinit var adapter: MainViewPagerAdapter

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentMainViewPagerBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        installViewPager()
        installBottomNavigation()
    }

    private fun installViewPager() {
        adapter = MainViewPagerAdapter(childFragmentManager, lifecycle)
        binding.pager.adapter = adapter
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })
        binding.pager.currentItem = TENCENT_FRAGMENT_INDEX
    }

    private fun installBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            val index = when (it.itemId) {
                R.id.main_netease -> NETEASE_FRAGMENT_INDEX
                R.id.main_qq -> TENCENT_FRAGMENT_INDEX
                R.id.main_local -> LOCAL_FRAGMENT_INDEX
                else -> throw IndexOutOfBoundsException()
            }
            binding.pager.setCurrentItem(index, false)
            true
        }
    }
}
