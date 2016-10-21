package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieCommonsZY;
import com.robbin.fingerread.bean.News;
import com.robbin.fingerread.network.manager.RetrofitManager;
import com.robbin.fingerread.ui.fragment.DailyDetailFragment;
import com.robbin.fingerread.ui.fragment.MovieCommentZYFragment;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieCommentZYActivity extends BaseActivity {

    public static final  String KEY_MOVIE_COMMENTZY="moviecommentzy";
    public static final  String TAG="moviecommentzy";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_comment_zy;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        String  id = getIntent().getStringExtra(KEY_MOVIE_COMMENTZY);
        showNewsDetailFragment(id);
    }
    private void showNewsDetailFragment(String id) {
        Fragment fragment= MovieCommentZYFragment.newInstance(id);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, MovieCommentZYFragment.TAG);
        transaction.commit();
    }

    public  static  void start(Context context,String id){
        Intent intent=new Intent(context,MovieCommentZYActivity.class);
        intent.putExtra(KEY_MOVIE_COMMENTZY,id);
        context.startActivity(intent);
    }
}
