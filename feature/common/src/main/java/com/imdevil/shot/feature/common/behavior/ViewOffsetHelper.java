package com.imdevil.shot.feature.common.behavior;

import android.view.View;

import androidx.core.view.ViewCompat;

/**
 * Utility helper for moving a {@link View} around using {@link View#offsetLeftAndRight(int)} and
 * {@link View#offsetTopAndBottom(int)}.
 *
 * <p>Also the setting of absolute offsets (similar to translationX/Y), rather than additive
 * offsets.
 */
class ViewOffsetHelper {

    private final View view;

    private int layoutTop;
    private int layoutLeft;
    private int offsetTop;
    private int offsetLeft;
    private boolean verticalOffsetEnabled = true;
    private boolean horizontalOffsetEnabled = true;

    public ViewOffsetHelper(View view) {
        this.view = view;
    }

    void onViewLayout() {
        // Grab the original top and left
        layoutTop = view.getTop();
        layoutLeft = view.getLeft();
    }

    void applyOffsets() {
        ViewCompat.offsetTopAndBottom(view, offsetTop - (view.getTop() - layoutTop));
        ViewCompat.offsetLeftAndRight(view, offsetLeft - (view.getLeft() - layoutLeft));
    }

    /**
     * @param offset the offset in px.
     * @return true if the offset has changed
     */
    public boolean setTopAndBottomOffset(int offset) {
        if (verticalOffsetEnabled && offsetTop != offset) {
            offsetTop = offset;
            applyOffsets();
            return true;
        }
        return false;
    }

    /**
     * @param offset the offset in px.
     * @return true if the offset has changed
     */
    public boolean setLeftAndRightOffset(int offset) {
        if (horizontalOffsetEnabled && offsetLeft != offset) {
            offsetLeft = offset;
            applyOffsets();
            return true;
        }
        return false;
    }

    public int getTopAndBottomOffset() {
        return offsetTop;
    }

    public int getLeftAndRightOffset() {
        return offsetLeft;
    }

    public int getLayoutTop() {
        return layoutTop;
    }

    public int getLayoutLeft() {
        return layoutLeft;
    }

    public void setVerticalOffsetEnabled(boolean verticalOffsetEnabled) {
        this.verticalOffsetEnabled = verticalOffsetEnabled;
    }

    public boolean isVerticalOffsetEnabled() {
        return verticalOffsetEnabled;
    }

    public void setHorizontalOffsetEnabled(boolean horizontalOffsetEnabled) {
        this.horizontalOffsetEnabled = horizontalOffsetEnabled;
    }

    public boolean isHorizontalOffsetEnabled() {
        return horizontalOffsetEnabled;
    }
}
