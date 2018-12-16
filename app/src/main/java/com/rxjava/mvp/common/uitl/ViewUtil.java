package com.rxjava.mvp.common.uitl;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.view.ViewGroup;

/**
 * 布局移除工具类
 */
public class ViewUtil {

    /**
     * 从父 view 中移除自己
     * @param child
     */
    public static void removeSelfFromParent(View child){
        if (child != null){
            ViewGroup parent = (ViewGroup)child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                parent.removeView(child);
            }
            parent.removeView(child);
        }
    }

    //设置圆角圆角北京
    private Drawable setDrawable(String colorStr) {
        RoundRectShape rr = new RoundRectShape(new float[]{15, 15, 15, 15, 15, 15, 15, 15},
                null, null); //60px = 20dp
        ShapeDrawable drawable = new ShapeDrawable(rr);
        drawable.getPaint().setColor(Color.parseColor(colorStr)); //指定填充颜色
        drawable.getPaint().setStyle(Paint.Style.FILL); // 指定填充模式
        return drawable;
    }

}
