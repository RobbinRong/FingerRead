package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.robbin.fingerread.R;
import com.robbin.fingerread.ui.fragment.DailyDetailFragment;
import com.robbin.fingerread.ui.fragment.MovieCommentDPFragment;
import com.robbin.fingerread.ui.fragment.MovieCommentZYFragment;

public class MovieCommentDPActivity extends BaseActivity {

    public static final  String KEY_MOVIE_COMMENTDP="moviecommentdp";
    public static final  String TAG="moviecommentzy";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_comment_dp;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState)  {
        int  id = getIntent().getIntExtra(KEY_MOVIE_COMMENTDP,0);
        showNewsDetailFragment(id);
}
    private void showNewsDetailFragment(int id) {
        Fragment fragment= MovieCommentDPFragment.newInstance(id);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_container, fragment, MovieCommentDPFragment.TAG);
        transaction.commit();
    }

    public static void start(Context context, int movieId) {
        Intent intent=new Intent(context,MovieCommentDPActivity.class);
        intent.putExtra(KEY_MOVIE_COMMENTDP,movieId);
        context.startActivity(intent);
    }
}
