package com.imdevil.shot.feature.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.imdevil.core.common.log.Dog

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = onCreateViewBinding(inflater, container, savedInstanceState).also { _binding = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun logHierarchy() {
        val list = mutableListOf<String>()
        var target: Fragment? = this
        while (target != null) {
            list.add(target::class.simpleName!!)
            target = target.parentFragment
        }
        list.reverse()

        var indent = ""
        val hierarchy = StringBuilder()
        list.forEachIndexed { index, s ->
            hierarchy.append(indent).append(s).append("\n")
            indent += "  "
        }

        Dog.i("FragmentHierarchy", hierarchy.toString())
    }
}
