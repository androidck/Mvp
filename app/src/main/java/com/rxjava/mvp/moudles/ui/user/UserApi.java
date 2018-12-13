package com.rxjava.mvp.moudles.ui.user;

import com.rxjava.mvp.common.base.BaseEntry;
import com.rxjava.mvp.common.constant.Constant;
import com.rxjava.mvp.moudles.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {

    /**
     * 根据用户id 获取用户信息
     * @param userId
     * @return
     */
    @POST(Constant.USER_INFO)
    Observable<BaseEntry<UserInfo>> getUserInfo(@Header("X_UserId") String userId);
}
