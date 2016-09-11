package com.robbin.fingerread.network.manager;

import android.util.Log;

import com.robbin.fingerread.FingerReadApplication;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.bean.NewsList;
import com.robbin.fingerread.bean.ReadingBean;
import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.constant.BaseUrl;
import com.robbin.fingerread.network.service.ReadService;
import com.robbin.fingerread.network.service.ScienceService;
import com.robbin.fingerread.network.service.ZhiHuRBService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by OneAPM on 2016/8/25.
 */
public class RetrofitManager {

    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;
    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";
    private static OkHttpClient mOkHttpClient;
    private    ZhiHuRBService mZhihuService;
    private   ScienceService mScienceService;
    private ReadService mReadService;

    public static RetrofitManager builderZhiHu(){
        return new RetrofitManager(BaseUrl.BASE_ZHIHU_URL);
    }
    public static RetrofitManager builderScience(){
        return new RetrofitManager(BaseUrl.BASE_SCIENCE_URL);
    }
    public static RetrofitManager builderRead(){
        return new RetrofitManager(BaseUrl.BASE_READ_URL);
    }
    private RetrofitManager(String baseUrl) {
        initOkHttpClient();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).
                client(mOkHttpClient).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).
                build();
        if(baseUrl.equals(BaseUrl.BASE_ZHIHU_URL)){
            mZhihuService=retrofit.create(ZhiHuRBService.class);
        }
        else if(baseUrl.equals(BaseUrl.BASE_SCIENCE_URL)){
            mScienceService=retrofit.create(ScienceService.class);

        }
        else if(baseUrl.equals(BaseUrl.BASE_READ_URL)){
            mReadService=retrofit.create(ReadService.class);

        }
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(mOkHttpClient==null){
            synchronized (RetrofitManager.class){
                if(mOkHttpClient==null){
                    Cache cache=new Cache(new File(FingerReadApplication.AppContext.getCacheDir(),"HttpCache"),1024*1024*100);
                    mOkHttpClient=new OkHttpClient.Builder()
                            .cache(cache).addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public Observable<NewsList> getLatestNews(){
        return mZhihuService.getLatestNews();
    }
    public Observable<NewsDetail> getNewsDetail(int id){return  mZhihuService.getNewsDetail(id);}
    public Observable<NewsList> getBeforeNews(String date){return mZhihuService.getBeforeNews(date);}
    public Observable<ScienceBean> getScience(String key){Log.e("rjb", "getScience: "+key);return mScienceService.getScience("by_channel",key);}
    public Observable<ReadingBean> getBook(String tag){return mReadService.getBook(tag);}

}
