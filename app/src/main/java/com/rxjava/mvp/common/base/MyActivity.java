package com.rxjava.mvp.common.base;

import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.ToastUtils;
import com.rxjava.mvp.R;
import com.rxjava.mvp.common.receiver.NetBroadcastReceiver;
import com.rxjava.mvp.common.uitl.NetUtil;
import com.rxjava.mvp.common.uitl.ViewUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目中的Activity
 */
public abstract class MyActivity extends UIActivity
        implements OnTitleBarListener,NetBroadcastReceiver.NetChangeListener {

    private Unbinder mButterKnife;//View注解

    public static NetBroadcastReceiver.NetChangeListener listener;



    /**
     * 网络类型
     */
    private int netType;

    private NetBroadcastReceiver netBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = this;
        //Android 7.0以上需要动态注册
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //实例化IntentFilter对象
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            netBroadcastReceiver = new NetBroadcastReceiver();
            //注册广播接收
            registerReceiver(netBroadcastReceiver, filter);
        }
        checkNet();
    }

    /**
     * 初始化时判断有没有网络
     */
    public boolean checkNet() {
        this.netType = NetUtil.getNetWorkState(MyActivity.this);
        if (!isNetConnect()) {
            //网络异常，请检查网络
            //showNetDialog();
            toast("网络异常，请检查网络");
        }
        return isNetConnect();
    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netType == 1) {
            return true;
        } else if (netType == 0) {
            return true;
        } else if (netType == -1) {
            return false;
        }
        return false;
    }

    @Override
    public void init() {

        //初始化标题栏的监听
        if (getTitleBarId() > 0) {
            if (findViewById(getTitleBarId()) instanceof TitleBar) {
                ((TitleBar) findViewById(getTitleBarId())).setOnTitleBarListener(this);
            }
        }

        mButterKnife = ButterKnife.bind(this);

        initOrientation();

        super.init();
    }


    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        //如果没有指定屏幕方向，则默认为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onChangeListener(int netMobile) {
        // TODO Auto-generated method stub
        this.netType = netMobile;
        if (!isNetConnect()) {
            toast("网络异常，请检查网络");
        } else {
        }
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(int titleId) {
        setTitle(getText(titleId));
    }

    /**
     * 设置标题栏的标题
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        TitleBar titleBar = getTitleBar();
        if (titleBar != null) {
            titleBar.setTitle(title);
        }
    }

    protected TitleBar getTitleBar() {
        if (getTitleBarId() > 0 && findViewById(getTitleBarId()) instanceof TitleBar) {
            return findViewById(getTitleBarId());
        }
        return null;
    }

    @Override
    public boolean statusBarDarkFont() {
        //返回true表示黑色字体
        return true;
    }

    /**
     * {@link OnTitleBarListener}
     */

    // 标题栏左边的View被点击了
    @Override
    public void onLeftClick(View v) {
        onBackPressed();
    }

    // 标题栏中间的View被点击了
    @Override
    public void onTitleClick(View v) {}

    // 标题栏右边的View被点击了
    @Override
    public void onRightClick(View v) {}

    @Override
    protected void onResume() {
        super.onResume();
        // 友盟统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 友盟统计
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mButterKnife != null) mButterKnife.unbind();
    }

    /**
     * 显示一个吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }



    /**
     * 网络状态
     */


}
