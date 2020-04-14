package com.example.chenren1.hello.utils;

import android.util.DisplayMetrics;

import com.example.chenren1.hello.DemoApplication;

public class DensityUtil {

    public static float scale = DemoApplication.getAppContext()
            .getResources().getDisplayMetrics().density;
    public static final int densityDpi = DemoApplication.getAppContext()
            .getResources().getDisplayMetrics().densityDpi;

    public static final DisplayMetrics DISPLAY_METRICS = DemoApplication.getAppContext()
            .getResources().getDisplayMetrics();

    public static int dip2px(float dpValue) {
        return (int) (dpValue * scale + 0.5F);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5F);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * scale + 0.5F);
    }

    public static int dpToPixcel(int dimenId) {
        return (int) DemoApplication.getAppContext()
                .getResources().getDimension(dimenId);
    }

    public static float getScale() {
        return scale;
    }
}
