package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieCommonsZY;
import com.robbin.fingerread.network.manager.RetrofitManager;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieCommentZYActivity extends BaseActivity {

    public static final  String KEY_MOVIE_COMMENTZY="moviecommentzy";
    public static final  String TAG="moviecommentzy";
    @Bind(R.id.lv_commentZY)
    ListView lvCommentZY;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_comment_zy;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        id = getIntent().getStringExtra(KEY_MOVIE_COMMENTZY);
        getDataFromHttp();
    }

    private void getDataFromHttp() {
        RetrofitManager.builderMaoYan().getMoviCommosZY(id,0,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieCommonsZY>() {
                    @Override
                    public void call(MovieCommonsZY commonZY) {
                        //loadCommonZY(commonZY);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "callzy: "+throwable.getMessage());
                    }
                });
    }

    public  static  void start(Context context,String id){
        Intent intent=new Intent(context,MovieCommentZYActivity.class);
        intent.putExtra(KEY_MOVIE_COMMENTZY,id);
        context.startActivity(intent);
    }
}
