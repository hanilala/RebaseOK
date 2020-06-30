package com.hani.colopi.view;

import android.content.Context;

public class DimensionUtil {
    public static int dip2px(Context ctx, int dpValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
