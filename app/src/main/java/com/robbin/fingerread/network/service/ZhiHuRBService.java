package com.robbin.fingerread.network.service;

import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.bean.NewsList;
import com.robbin.fingerread.network.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by OneAPM on 2016/8/25.
 */
public interface ZhiHuRBService {
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("stories/latest")
    public Observable<NewsList> getLatestNews();
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("stories/before/{date}")
    Observable<NewsList> getBeforeNews(@Path("date") String date);
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("story/{id}")
    Observable<NewsDetail> getNewsDetail(@Path("id") int id);
}
