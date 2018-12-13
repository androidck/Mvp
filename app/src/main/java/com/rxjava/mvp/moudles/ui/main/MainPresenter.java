package com.rxjava.mvp.moudles.ui.main;

import android.content.Context;

import com.rxjava.mvp.common.base.BaseEntry;
import com.rxjava.mvp.common.base.BaseObserver;
import com.rxjava.mvp.common.uitl.MainUtil;
import com.rxjava.mvp.common.uitl.RetrofitUtil;
import com.rxjava.mvp.moudles.bean.NewsList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.presenter {

    private Context context;
    private MainContract.View view;

    public MainPresenter(Context context,MainContract.View view){
        this.context=context;
        this.view=view;
    }


    @Override
    public void getNewsList(int num) {
        RetrofitUtil
                .getInstance()
                .initRetrofit()
                .getNewsList(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<NewsList>>(context,MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<List<NewsList>> t) throws Exception {
                        view.setContent(t.getNewslist());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (isNetWorkError){
                            view.Error("网络连接失败，请检查网络");
                        }
                    }
                });
    }
}
