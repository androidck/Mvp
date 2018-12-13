package com.rxjava.mvp.moudles.ui.user;

import android.content.Context;

import com.rxjava.mvp.common.base.BaseEntry;
import com.rxjava.mvp.common.base.BaseObserver;
import com.rxjava.mvp.common.uitl.MainUtil;
import com.rxjava.mvp.common.uitl.RetrofitUtil;
import com.rxjava.mvp.moudles.bean.NewsList;
import com.rxjava.mvp.moudles.bean.UserInfo;
import com.rxjava.mvp.moudles.ui.main.MainContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter implements UserContract.presenter {

    private Context context;
    private UserContract.View view;

    public UserPresenter(Context context,UserContract.View view){
        this.context=context;
        this.view=view;
    }

    @Override
    public void getUserInfo(String userId) {
        RetrofitUtil
                .getInstance()
                .initUserInfoRetrofit()
                .getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserInfo>(context,MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<UserInfo> t) throws Exception {
                        if (t.getCode()==200){
                            view.onSuccess(t.getData());
                        }else {
                            view.onFail(t.getMsg());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (isNetWorkError){
                            view.onError("网络连接失败，请检查网络");
                        }
                    }
                });
    }
}
