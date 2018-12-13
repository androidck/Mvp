package com.rxjava.mvp.moudles.ui.main;

import com.rxjava.mvp.common.base.BaseEntry;
import com.rxjava.mvp.moudles.bean.NewsList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求接口
 */
public interface MainApi {

    @GET("wxnew/?key=45b9e6a580d942b96e034bb207093193")
    Observable<BaseEntry<List<NewsList>>> getNewsList(@Query("num") int num);

}
