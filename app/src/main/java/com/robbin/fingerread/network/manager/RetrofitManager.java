package com.robbin.fingerread.network.manager;

import android.util.Log;

import com.robbin.fingerread.FingerReadApplication;
import com.robbin.fingerread.bean.MovieBean;
import com.robbin.fingerread.bean.MovieBox;
import com.robbin.fingerread.bean.MovieCelebrity;
import com.robbin.fingerread.bean.MovieCommonsDP;
import com.robbin.fingerread.bean.MovieCommonsZY;
import com.robbin.fingerread.bean.MovieDetail;
import com.robbin.fingerread.bean.MovieMajor;
import com.robbin.fingerread.bean.WechatArticalBean;
import com.robbin.fingerread.bean.WechatArticalCategoryBean;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.bean.NewsList;
import com.robbin.fingerread.bean.ReadingBean;
import com.robbin.fingerread.bean.ScienceBean;
import com.robbin.fingerread.constant.BaseUrl;
import com.robbin.fingerread.network.service.MaoYanService;
import com.robbin.fingerread.network.service.MovieService;
import com.robbin.fingerread.network.service.ReadService;
import com.robbin.fingerread.network.service.ScienceService;
import com.robbin.fingerread.network.service.WechatService;
import com.robbin.fingerread.network.service.ZhiHuRBService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
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
    private WechatService mWechatService;
    private MovieService mMovieService;
    private MaoYanService mMaoYanService;
    public static RetrofitManager builderZhiHu(){
        return new RetrofitManager(BaseUrl.BASE_ZHIHU_URL);
    }
    public static RetrofitManager builderScience(){
        return new RetrofitManager(BaseUrl.BASE_SCIENCE_URL);
    }
    public static RetrofitManager builderRead(){
        return new RetrofitManager(BaseUrl.BASE_READ_URL);
    }
    public static RetrofitManager builderWechat(){
        return new RetrofitManager(BaseUrl.BASE_WECHAT_URL);
    }
    public static RetrofitManager builderMovie(){
        return new RetrofitManager(BaseUrl.BASE_MOVIE_URL);
    }
    public static RetrofitManager builderMaoYan(){
        return new RetrofitManager(BaseUrl.BASE_MAOYAN_URL);
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
        else if(baseUrl.equals(BaseUrl.BASE_WECHAT_URL)){
            mWechatService=retrofit.create(WechatService.class);
        }
        else if(baseUrl.equals(BaseUrl.BASE_MOVIE_URL)){
            mMovieService=retrofit.create(MovieService.class);
        }
        else if(baseUrl.equals(BaseUrl.BASE_MAOYAN_URL)){
            mMaoYanService=retrofit.create(MaoYanService.class);
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
    public Observable<ScienceBean> getScience(String key){return mScienceService.getScience("by_channel",key);}
    public Observable<ReadingBean> getBook(String tag){return mReadService.getBook(tag);}
    public Observable<WechatArticalCategoryBean> getArticalCategory(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String d = format.format(date);
        return mWechatService.getArticalCategory("19588",d,"d650ea2058644774a544a18430e8cedd");
    }
    public Observable<WechatArticalBean> getArticalList(int page,String typeid){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String d = format.format(date);
        return mWechatService.getArticalList("","0",String.valueOf(page),"19588",d,typeid,"d650ea2058644774a544a18430e8cedd");
    }
    public Observable<MovieBean> getMovies(String type, String offset,String limit){return mMovieService.getMovies(type,offset,limit);}
    public Observable<MovieDetail> getMovieDetail(String id){return mMovieService.getMovieDetail(id);}
    public Observable<MovieCelebrity> getMovieCelebrity(String id){return mMaoYanService.getMovieCelebrity(id);}
    public Observable<MovieMajor> getMovieMajor(String id){return mMaoYanService.getMovieMajor(id);}
    public Observable<MovieBox> getMovieBox(String id){return mMaoYanService.getMovieBox(id);}
    public Observable<MovieCommonsZY> getMoviCommosZY(String id,int offset,int limit){
        return mMaoYanService.getMovieCommonsZY(id,offset,limit,"AmovieBmovieCD-1","7401","xiaomi",
                "android","7.4.0","860308027377288",1,255,"MI%202",
                "48286829CF2E3DEDD5C94A9DC49FEDE89665DD1BAC0B223DD1BE5EDE86C61441","%2FMovieMainActivity");
    }
    public Observable<MovieCommonsDP> getMoviCommosDP(String id, int offset, int limit){
        return mMaoYanService.getMovieCommonsDP(id,"",offset,limit,0,"AmovieBmovieCD-1","7401","xiaomi",
                "android","7.4.0","860308027377288",1,255,"MI%202",
                "48286829CF2E3DEDD5C94A9DC49FEDE89665DD1BAC0B223DD1BE5EDE86C61441","%2FMovieMainActivity");
    }

}
