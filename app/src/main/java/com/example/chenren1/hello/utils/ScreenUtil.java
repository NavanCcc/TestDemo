package com.example.chenren1.hello.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtil {
    private static int sDensityDpi = 0;
    /**
     * 真实屏幕密度
     */
    private static float sDensity = 0.0f;
    private static float sScreenHeight = 0;
    private static float sScreenWidth = 0;
    /**
     * 单倍屏屏幕宽度
     */
    private static float sScreenWidthDpi = 0;
    private static String sScreenSize = "";
    private static boolean hasInitScreenParam;

    private static void getInfoFromDisplayMetrics(Context context) {
        if (hasInitScreenParam) {
            return;
        }
        hasInitScreenParam = true;
        DisplayMetrics display = context.getResources()
                .getDisplayMetrics();
        sDensityDpi = display.densityDpi;
        sDensity = display.density;
        sScreenHeight = display.heightPixels;
        sScreenWidth = display.widthPixels;
        sScreenWidthDpi = sScreenWidth / sDensity;
    }

    public static float getScreenPortraitWidth(Context context) {
        getInfoFromDisplayMetrics(context);
        if (sScreenHeight > sScreenWidth) {
            return sScreenWidth;
        } else {
            return sScreenHeight;
        }
    }

    public static float getScreenPortraitHeight(Context context) {
        getInfoFromDisplayMetrics(context);
        if (sScreenHeight > sScreenWidth) {
            return sScreenHeight;
        } else {
            return sScreenWidth;
        }
    }

    public static float getScreenWidth(Context context) {
        getInfoFromDisplayMetrics(context);
        return sScreenWidth;
    }

    public static float getScreenHeight(Context context) {
        getInfoFromDisplayMetrics(context);
        return sScreenHeight;
    }

    public static float getScreenWidthDpi(Context context) {
        getInfoFromDisplayMetrics(context);
        return sScreenWidthDpi;
    }
}
