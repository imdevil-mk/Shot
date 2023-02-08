package com.imdevil.shot.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class LogFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Dog.d(javaClass.simpleName, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dog.d(javaClass.simpleName, "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Dog.d(javaClass.simpleName, "onCreateView: ")
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Dog.d(javaClass.simpleName, "onViewCreated: ")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Dog.d(javaClass.simpleName, "onViewStateRestored: ")
    }

    override fun onStart() {
        super.onStart()
        Dog.d(javaClass.simpleName, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Dog.d(javaClass.simpleName, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Dog.d(javaClass.simpleName, "onPause: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Dog.d(javaClass.simpleName, "onSaveInstanceState: ")
    }

    override fun onStop() {
        super.onStop()
        Dog.d(javaClass.simpleName, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Dog.d(javaClass.simpleName, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Dog.d(javaClass.simpleName, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Dog.d(javaClass.simpleName, "onDetach: ")
    }
}
