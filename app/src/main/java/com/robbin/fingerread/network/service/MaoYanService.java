package com.robbin.fingerread.network.service;

import com.robbin.fingerread.bean.MovieCelebrity;
import com.robbin.fingerread.bean.MovieDetail;
import com.robbin.fingerread.bean.MovieMajor;
import com.robbin.fingerread.network.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/20.
 */
public interface MaoYanService {

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("mmdb/v7/movie/{id}/celebrities.json?utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7401&utm_source=xiaomi&utm_medium=android&utm_term=7.4.0&utm_content=860308027377288&ci=1&net=255&dModel=MI%202&uuid=48286829CF2E3DEDD5C94A9DC49FEDE89665DD1BAC0B223DD1BE5EDE86C61441&refer=%2FMovieMainActivity")
    public Observable<MovieCelebrity> getMovieCelebrity(@Path("id")String id);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("mmdb/comments/major/v2/info/{id}.json?utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7401&utm_source=xiaomi&utm_medium=android&utm_term=7.4.0&utm_content=860308027377288&ci=1&net=255&dModel=MI%202&uuid=48286829CF2E3DEDD5C94A9DC49FEDE89665DD1BAC0B223DD1BE5EDE86C61441&refer=%2FMovieMainActivity")
    public Observable<MovieMajor> getMovieMajor(@Path("id")String id);

}
