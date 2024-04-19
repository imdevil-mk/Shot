package com.imdevil.feature.tencent.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.imdevil.shot.feature.common.behavior.HeaderScrollingViewBehavior

class HomePageContentBehavior(
    private val context: Context,
    private val attrs: AttributeSet,
) : HeaderScrollingViewBehavior(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependsOnHelper.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependsOnHelper.matchScrollDependency(parent, child, dependency)) {
            offsetChildAsNeeded(child, dependency)
        }
        return false
    }

    private fun offsetChildAsNeeded(child: View, dependency: View) {
        ViewCompat.offsetTopAndBottom(child, dependency.bottom - child.top)
    }

    companion object {
        private const val TAG = "HomePageContentBehavior"
    }
}