package com.imdevil.feature.tencent.feature.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.tencent.databinding.FragmentOnboardBinding

class OnboardFragment : BaseFragment<FragmentOnboardBinding>() {
    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentOnboardBinding.inflate(inflater, container, false)
}