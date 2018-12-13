package com.rxjava.mvp.moudles.ui.main;

import com.rxjava.mvp.common.base.BasePresenter;
import com.rxjava.mvp.common.base.BaseView;
import com.rxjava.mvp.moudles.bean.Elements;
import com.rxjava.mvp.moudles.bean.NewsList;

import java.util.List;

/**
 *
 */
public interface MainContract {

    interface View extends BaseView<presenter>{
        //设置内容
        void setContent(List<NewsList> elements);

        void Error(String msg);
    }

    interface presenter extends BasePresenter{
        // 网络请求方法
       void getNewsList(int num);
    }
}
