package com.rxjava.mvp.moudles.ui.user;

import android.widget.Button;
import android.widget.TextView;

import com.rxjava.mvp.R;
import com.rxjava.mvp.common.base.MyActivity;
import com.rxjava.mvp.moudles.bean.UserInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户用户信息
 */
public class UserActivity extends MyActivity implements UserContract.View {

    @BindView(R.id.btn_login_commit)
    Button btnLoginCommit;
    @BindView(R.id.tv_date)
    TextView tvDate;
    private UserPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        presenter = new UserPresenter(this, this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        tvDate.setText(userInfo.toString());
    }

    @Override
    public void onFail(String message) {
        toast(message);
    }

    @Override
    public void onError(String message) {
        toast(message);
    }



    @OnClick(R.id.btn_login_commit)
    public void onViewClicked() {
        presenter.getUserInfo("b28fc6714528434d83f06e8e56e978b0");
    }
}
