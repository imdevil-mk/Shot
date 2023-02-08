package com.imdevil.shot.common.ui.mainviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.imdevil.shot.R
import com.imdevil.shot.common.base.LogFragment
import com.imdevil.shot.databinding.FragmentMainViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewPagerFragment : LogFragment() {

    private lateinit var binding: FragmentMainViewPagerBinding
    private lateinit var adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainViewPagerAdapter(this)
        binding.pager.adapter = adapter

        binding.bottomNav.setOnItemSelectedListener {
            val index = when (it.itemId) {
                R.id.main_netease -> NETEASE_FRAGMENT_INDEX
                R.id.main_qq -> QQ_FRAGMENT_INDEX
                R.id.main_local -> LOCAL_FRAGMENT_INDEX
                else -> throw IndexOutOfBoundsException()
            }
            binding.pager.setCurrentItem(index, false)
            true
        }

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
}