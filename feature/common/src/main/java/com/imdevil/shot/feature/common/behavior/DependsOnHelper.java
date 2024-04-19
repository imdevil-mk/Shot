package com.imdevil.shot.feature.common.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.imdevil.shot.feature.common.R;

import java.util.List;

public class DependsOnHelper {
    public static final int INVALID_ID = -0x1001;

    private int mLayoutDepends = INVALID_ID;
    private int mMeasureDepends = INVALID_ID;
    private int mScrollDepends = INVALID_ID;

    public DependsOnHelper(Context context, AttributeSet attrs) {
        final TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.CustomViewAttributes);
        mLayoutDepends = a.getResourceId(R.styleable.CustomViewAttributes_layout_depends, INVALID_ID);
        mMeasureDepends = a.getResourceId(R.styleable.CustomViewAttributes_measure_depends, INVALID_ID);
        mScrollDepends = a.getResourceId(R.styleable.CustomViewAttributes_scroll_depends, INVALID_ID);
        a.recycle();
    }

    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child,
                                   @NonNull View dependency) {
        return dependency.getId() == mLayoutDepends
                || dependency.getId() == mMeasureDepends
                || dependency.getId() == mScrollDepends;
    }

    public boolean matchScrollDependency(@NonNull CoordinatorLayout parent, @NonNull View child,
                                         @NonNull View dependency) {
        return dependency.getId() == mScrollDepends;
    }

    @Nullable
    public View findLayoutDependency(List<View> views) {
        return findViewById(views, mLayoutDepends);
    }

    @Nullable
    public View findMeasureDependency(List<View> views) {
        return findViewById(views, mMeasureDepends);
    }

    @Nullable
    private View findViewById(@NonNull List<View> views, int id) {
        for (int i = 0; i < views.size(); i++) {
            View view = views.get(i);
            if (view.getId() == id) {
                return view;
            }
        }
        return null;
    }
}
