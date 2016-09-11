package com.robbin.fingerread.network.service;

import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by OneAPM on 2016/9/6.
 */
public interface ScienceService {
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("minisite/article.json")
    public Observable<ScienceBean> getScience(@Query("retrieve_type") String type,@Query("channel_key") String key);
}
