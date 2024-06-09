package com.imdevil.feature.tencent.feature.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.tencent.databinding.FragmentRecommendBinding

class RecommendFragment : BaseFragment<FragmentRecommendBinding>() {

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecommendBinding.inflate(inflater, container, false)
}