package com.rxjava.mvp.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
  *    desc   : 项目中Fragment懒加载基类
 */
public abstract class MyLazyFragment extends UILazyFragment {

    private Unbinder mButterKnife;// View注解

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mButterKnife = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mButterKnife.unbind();
    }

    /**
     * 显示一个吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }
}
