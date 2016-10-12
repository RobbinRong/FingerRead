package com.robbin.fingerread.network.service;

import com.robbin.fingerread.bean.WechatArticalBean;
import com.robbin.fingerread.bean.WechatArticalCategoryBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/12.
 */
public interface WechatService {
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("582-1")
    public Observable<WechatArticalCategoryBean> getArticalCategory(@Query("showapi_appid") String appid, @Query("showapi_timestamp") String time, @Query("showapi_sign") String sign);
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("582-2")
    public Observable<WechatArticalBean> getArticalList(@Query("key") String key, @Query("needContent") String needContent,
                                                        @Query("page") String page, @Query("showapi_appid") String appid,
                                                        @Query("showapi_timestamp") String time, @Query("typeId") String typeid,
                                                        @Query("showapi_sign") String sign);

}
