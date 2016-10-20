package com.robbin.fingerread.network.service;

import com.robbin.fingerread.bean.MovieBean;
import com.robbin.fingerread.bean.MovieDetail;
import com.robbin.fingerread.bean.ReadingBean;
import com.robbin.fingerread.network.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by OneAPM on 2016/9/6.
 */
public interface MovieService {
    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("list.json")
    public Observable<MovieBean> getMovies(@Query("type") String type, @Query("offset") String offset, @Query("limit") String limit);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("{id}.json")
    public Observable<MovieDetail> getMovieDetail(@Path("id")String id);


}
