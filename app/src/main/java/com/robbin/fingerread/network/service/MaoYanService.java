package com.robbin.fingerread.network.service;

import com.robbin.fingerread.bean.MovieBox;
import com.robbin.fingerread.bean.MovieCelebrity;
import com.robbin.fingerread.bean.MovieCommonsDP;
import com.robbin.fingerread.bean.MovieCommonsZY;
import com.robbin.fingerread.bean.MovieDetail;
import com.robbin.fingerread.bean.MovieMajor;
import com.robbin.fingerread.network.manager.RetrofitManager;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("mmdb/movie/{id}/feature/v1/mbox.json?utm_campaign=AmovieBmovieCD-1&movieBundleVersion=7401&utm_source=xiaomi&utm_medium=android&utm_term=7.4.0&utm_content=860308027377288&ci=1&net=255&dModel=MI%202&uuid=48286829CF2E3DEDD5C94A9DC49FEDE89665DD1BAC0B223DD1BE5EDE86C61441&refer=%2FMovieMainActivity")
    public Observable<MovieBox> getMovieBox(@Path("id")String id);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("mmdb/comments/pro/movie/{id}.json")
    public Observable<MovieCommonsZY> getMovieCommonsZY(@Path("id")String id,
             @Query("offset") int offset,@Query("limit") int  limit,@Query("utm_campaign")String utm_campaign
    ,@Query("movieBundleVersion")String movieBundleVersion,@Query("utm_source") String utm_source,@Query("utm_medium") String utm_medium
    ,@Query("utm_term") String utm_term,@Query("utm_content")String utm_content,@Query("ci") int ci,@Query("net") int net,@Query("dModel")String dModel
    ,@Query("uuid")String uuid,@Query("refer")String refer);


    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("mmdb/comments/movie/v2/{id}.json")
    public Observable<MovieCommonsDP> getMovieCommonsDP(@Path("id")String id,@Query("token")String token,
                                                        @Query("offset") int offset, @Query("limit") int  limit,@Query("tag")int tag, @Query("utm_campaign")String utm_campaign
            , @Query("movieBundleVersion")String movieBundleVersion, @Query("utm_source") String utm_source, @Query("utm_medium") String utm_medium
            , @Query("utm_term") String utm_term, @Query("utm_content")String utm_content, @Query("ci") int ci, @Query("net") int net, @Query("dModel")String dModel
            , @Query("uuid")String uuid, @Query("refer")String refer);

}
