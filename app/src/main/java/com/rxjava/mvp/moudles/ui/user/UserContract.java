package com.rxjava.mvp.moudles.ui.user;

import com.rxjava.mvp.common.base.BasePresenter;
import com.rxjava.mvp.common.base.BaseView;
import com.rxjava.mvp.moudles.bean.UserInfo;

public interface UserContract {

    interface View extends BaseView<presenter>{
        void onSuccess(UserInfo userInfo);

        void onFail(String message);

        void onError(String message);
    }

    interface presenter extends BasePresenter{
        void getUserInfo(String userId);
    }
}
