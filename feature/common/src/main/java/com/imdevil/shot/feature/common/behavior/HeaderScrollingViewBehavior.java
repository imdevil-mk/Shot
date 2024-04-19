package com.imdevil.shot.feature.common.behavior;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

/**
 * The {@link Behavior} for a scrolling view that is positioned vertically below another view
 */
@SuppressLint("RestrictedApi")
public abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    private final String TAG = this.getClass().getSimpleName();
    final Rect tempRect1 = new Rect();
    final Rect tempRect2 = new Rect();

    private int verticalLayoutGap = 0;
    private int overlayTop;

    public HeaderScrollingViewBehavior() {
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onMeasureChild(
            @NonNull CoordinatorLayout parent,
            @NonNull View child,
            int parentWidthMeasureSpec,
            int widthUsed,
            int parentHeightMeasureSpec,
            int heightUsed) {
        final int childLpHeight = child.getLayoutParams().height;
        if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                || childLpHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            // If the menu's height is set to match_parent/wrap_content then measure it
            // with the maximum visible height

            final List<View> dependencies = parent.getDependencies(child);
            final View measureHeader = dependsOnHelper.findMeasureDependency(dependencies);
            final View layoutHeader = dependsOnHelper.findLayoutDependency(dependencies);

            if (measureHeader != null) {
                int specSize = View.MeasureSpec.getSize(parentHeightMeasureSpec);
                int headerHeight = calculateHeaderHeightRecursively(parent, layoutHeader, 0);
                int availableHeight = specSize - headerHeight;

                if (specSize > 0) {
                    if (ViewCompat.getFitsSystemWindows(measureHeader)) {
                        final WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
                        if (parentInsets != null) {
                            availableHeight += parentInsets.getSystemWindowInsetTop()
                                    + parentInsets.getSystemWindowInsetBottom();
                        }
                    }
                } else {
                    // If the measure spec doesn't specify a size, use the current height
                    availableHeight = parent.getHeight();
                }
                int height = availableHeight + getScrollRange(measureHeader);

                final int heightMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(
                                height,
                                childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                                        ? View.MeasureSpec.EXACTLY
                                        : View.MeasureSpec.AT_MOST);

                // Now measure the scrolling view with the correct height
                parent.onMeasureChild(
                        child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);

                return true;
            }
        }
        return false;
    }

    private int calculateHeaderHeightRecursively(@NonNull CoordinatorLayout parent, @Nullable View header, int height) {
        if (header == null) return 0;

        int headerHeight = header.getMeasuredHeight();
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) header.getLayoutParams();
        Behavior behavior = lp.getBehavior();
        if (behavior instanceof ViewOffsetBehavior) {
            ViewOffsetBehavior vb = (ViewOffsetBehavior) behavior;
            List<View> dependencies = parent.getDependencies(header);
            View layoutHeader = vb.dependsOnHelper.findLayoutDependency(dependencies);
            return calculateHeaderHeightRecursively(parent, layoutHeader, height + headerHeight);
        } else {
            return height + headerHeight;
        }
    }

    @Override
    protected void layoutChild(
            @NonNull final CoordinatorLayout parent,
            @NonNull final View child,
            final int layoutDirection) {
        final List<View> dependencies = parent.getDependencies(child);
        final View header = dependsOnHelper.findLayoutDependency(dependencies);
        if (header != null) {
            final CoordinatorLayout.LayoutParams lp =
                    (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            final Rect available = tempRect1;
            available.set(
                    parent.getPaddingLeft() + lp.leftMargin,
                    header.getBottom() + lp.topMargin,
                    parent.getWidth() - parent.getPaddingRight() - lp.rightMargin,
                    parent.getHeight() + header.getBottom() - parent.getPaddingBottom() - lp.bottomMargin);

            final WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
            if (parentInsets != null
                    && ViewCompat.getFitsSystemWindows(parent)
                    && !ViewCompat.getFitsSystemWindows(child)) {
                // If we're set to handle insets but this child isn't, then it has been measured as
                // if there are no insets. We need to lay it out to match horizontally.
                // Top and bottom and already handled in the logic above
                available.left += parentInsets.getSystemWindowInsetLeft();
                available.right -= parentInsets.getSystemWindowInsetRight();
            }

            final Rect out = tempRect2;
            GravityCompat.apply(
                    resolveGravity(lp.gravity),
                    child.getMeasuredWidth(),
                    child.getMeasuredHeight(),
                    available,
                    out,
                    layoutDirection);

            final int overlap = getOverlapPixelsForOffset(header);

            child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap);
            verticalLayoutGap = out.top - header.getBottom();
        } else {
            // If we don't have a dependency, let super handle it
            super.layoutChild(parent, child, layoutDirection);
            verticalLayoutGap = 0;
        }
    }

    protected boolean shouldHeaderOverlapScrollingChild() {
        return false;
    }

    float getOverlapRatioForOffset(final View header) {
        return 1f;
    }

    final int getOverlapPixelsForOffset(final View header) {
        return overlayTop == 0
                ? 0
                : MathUtils.clamp((int) (getOverlapRatioForOffset(header) * overlayTop), 0, overlayTop);
    }

    private static int resolveGravity(int gravity) {
        return gravity == Gravity.NO_GRAVITY ? GravityCompat.START | Gravity.TOP : gravity;
    }

    int getScrollRange(@NonNull View v) {
        return v.getMeasuredHeight();
    }

    /**
     * The gap between the top of the scrolling view and the bottom of the header layout in pixels.
     */
    final int getVerticalLayoutGap() {
        return verticalLayoutGap;
    }

    /**
     * Set the distance that this view should overlap any {@link
     * com.google.android.material.appbar.AppBarLayout}.
     *
     * @param overlayTop the distance in px
     */
    public final void setOverlayTop(int overlayTop) {
        this.overlayTop = overlayTop;
    }

    /**
     * Returns the distance that this view should overlap any {@link
     * com.google.android.material.appbar.AppBarLayout}.
     */
    public final int getOverlayTop() {
        return overlayTop;
    }
}
