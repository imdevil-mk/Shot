package com.imdevil.feature.tencent.feature.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.imdevil.core.ui.recyclerview.IRecyclerData
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.tencent.databinding.FragmentRecommendBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendFragment : BaseFragment<FragmentRecommendBinding>() {
    private val viewModel: RecommendViewModel by viewModels<RecommendViewModel>()

    private val listAdapter: RecommendListAdapter = RecommendListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listAdapter.registerItemBuilder(ChildListItemBuilder())
        listAdapter.registerItemBuilder(SongItemBuilder())
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecommendBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recommendMainList.layoutManager = LinearLayoutManager(context)
        binding.recommendMainList.adapter = listAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.recommendFeed.collect {
                when (it) {
                    is RecommendFeedUiState.Loading -> {

                    }

                    is RecommendFeedUiState.Success -> {
                        onRecommendFeedReady(it.feeds)
                    }
                }
            }
        }
    }

    private fun onRecommendFeedReady(feed: List<IRecyclerData>) {
        listAdapter.submitList(feed)
    }

    companion object {
        private const val TAG = "RecommendFragment"
    }
}