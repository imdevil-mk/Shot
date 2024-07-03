package com.imdevil.feature.tencent.ui.behavior;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.imdevil.shot.feature.common.behavior.HeaderBehavior;

import java.util.List;

@SuppressLint("RestrictedApi")
class HomePageUserProfileBehavior extends HeaderBehavior<View> {
    private static final String TAG = "HomePageUserProfileBehavior";
    final Rect tempRect1 = new Rect();
    final Rect tempRect2 = new Rect();

    private int verticalLayoutGap = 0;
    private int overlayTop;

    public HomePageUserProfileBehavior() {
    }

    public HomePageUserProfileBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(
            @NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependsOnHelper.layoutDependsOn(parent, child, dependency);
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
            final View header = dependsOnHelper.findMeasureDependency(dependencies);

            if (header != null) {
                int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
                if (availableHeight > 0) {
                    if (ViewCompat.getFitsSystemWindows(header)) {
                        final WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
                        if (parentInsets != null) {
                            availableHeight +=
                                    parentInsets.getSystemWindowInsetTop()
                                            + parentInsets.getSystemWindowInsetBottom();
                        }
                    }
                } else {
                    // If the measure spec doesn't specify a size, use the current height
                    availableHeight = parent.getHeight();
                }

                int height = availableHeight + getScrollRange(header);
                int headerHeight = header.getMeasuredHeight();
                if (shouldHeaderOverlapScrollingChild()) {
                    child.setTranslationY(-headerHeight);
                } else {
                    height -= headerHeight;
                }
                final int heightMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(
                                height,
                                childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT
                                        ? View.MeasureSpec.EXACTLY
                                        : View.MeasureSpec.AT_MOST);

                /*/
                Dog.INSTANCE.d(TAG, "onMeasureChild:"
                        + " parentWidthMeasureSpec = " + parentWidthMeasureSpec
                        + " widthUsed = " + widthUsed
                        + " heightMeasureSpec = " + heightMeasureSpec
                        + " heightUsed = " + heightUsed
                );
                //*/
                // Now measure the scrolling view with the correct height
                parent.onMeasureChild(
                        child, parentWidthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);

                return true;
            }
        }
        return false;
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
                    parent.getHeight()
                            + header.getBottom()
                            - parent.getPaddingBottom()
                            - lp.bottomMargin);
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

    private static int resolveGravity(int gravity) {
        return gravity == Gravity.NO_GRAVITY ? GravityCompat.START | Gravity.TOP : gravity;
    }

    protected boolean shouldHeaderOverlapScrollingChild() {
        return false;
    }

    final int getOverlapPixelsForOffset(final View header) {
        return overlayTop == 0
                ? 0
                : MathUtils.clamp(
                (int) (getOverlapRatioForOffset(header) * overlayTop), 0, overlayTop);
    }

    float getOverlapRatioForOffset(final View header) {
        return 1f;
    }

    int getScrollRange(@NonNull View v) {
        return v.getMeasuredHeight();
    }

    @Override
    public boolean onStartNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull View child,
            @NonNull View directTargetChild,
            @NonNull View target,
            int axes,
            int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0
                && coordinatorLayout.getHeight() - directTargetChild.getMeasuredHeight()
                <= child.getHeight();
    }

    @Override
    public void onNestedPreScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull View child,
            @NonNull View target,
            int dx,
            int dy,
            @NonNull int[] consumed,
            int type) {
        if (dy == 0) return;
        View anchor =
                dependsOnHelper.findLayoutDependency(coordinatorLayout.getDependencies(child));
        if (anchor == null) return;
        int anchorBottom = anchor.getBottom();
        int anchorHeight = anchor.getHeight();

        int currentBottom = child.getBottom();
        int consumedY = dy;
        int newBottom = currentBottom - consumedY;
        if (dy < 0) {
            // We`re scrolling down && the scroll_view can`t scroll now
            if (!target.canScrollVertically(-1)) {
                if (newBottom > anchorHeight + child.getHeight()) {
                    consumedY = currentBottom - anchorHeight - child.getHeight();
                }
            } else {
                consumedY = 0;
            }
        } else {
            // We`re scrolling up
            if (newBottom < anchorBottom) {
                consumedY = currentBottom - anchorBottom;
            }
        }
        /*/
        Dog.INSTANCE.d(TAG, "onNestedPreScroll: dy = " + dy
                + " childHeight = " + child.getHeight()
                + " childBottom = " + child.getBottom()
                + " consumedY = " + consumedY
                + " anchorHeight = " + anchorHeight
                + " anchorBottom = " + anchorBottom
        );
        //*/
        consumed[1] = consumedY;
        if (consumedY != 0) {
            scroll(coordinatorLayout, child, consumedY, getMaxDragOffset(child), 0);
        }
    }

    @Override
    public void onNestedScroll(
            @NonNull CoordinatorLayout coordinatorLayout,
            @NonNull View child,
            @NonNull View target,
            int dxConsumed,
            int dyConsumed,
            int dxUnconsumed,
            int dyUnconsumed,
            int type,
            @NonNull int[] consumed) {
        if (dyUnconsumed == 0) return;
        View anchor =
                dependsOnHelper.findLayoutDependency(coordinatorLayout.getDependencies(child));
        if (anchor == null) return;
        int anchorBottom = anchor.getBottom();
        int anchorHeight = anchor.getHeight();
        int currentBottom = child.getBottom();
        int consumedY = dyUnconsumed;
        int newBottom = currentBottom - consumedY;
        if (dyUnconsumed < 0) {
            // We`re scrolling down && the scroll_view can`t scroll now
            if (!target.canScrollVertically(-1)) {
                if (newBottom > anchorHeight + child.getHeight()) {
                    consumedY = currentBottom - anchorHeight - child.getHeight();
                }
            } else {
                consumedY = 0;
            }
        } else {
            // We`re scrolling up
            if (newBottom < anchorBottom) {
                consumedY = currentBottom - anchorBottom;
            }
        }
        /*/
        Dog.INSTANCE.d(TAG, "onNestedScroll: "
                + " dyConsumed = " + dyConsumed
                + " dyUnconsumed = " + dyUnconsumed
                + " childBottom = " + child.getBottom()
                + " consumedY = " + consumedY
        );
        //*/
        consumed[1] = consumedY;
        if (consumedY != 0) {
            scroll(coordinatorLayout, child, consumedY, getMaxDragOffset(child), 0);
        }
    }

    @Override
    public boolean canDragView(View view) {
        return true;
    }
}
