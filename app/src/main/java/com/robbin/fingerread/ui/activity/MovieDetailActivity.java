package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieDetail;
import com.robbin.fingerread.network.manager.RetrofitManager;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends BaseActivity {
    public static  final  String KEY_MOVIE_DETAIL="moviedetailactivity";
    public static  final  String TAG="moviedetailactivity";
    private String movieId;

    @Bind(R.id.iv_img)
    ImageView ivImg;
    @Bind(R.id.tv_nm)
    TextView tvNm;
    @Bind(R.id.tv_sc)
    TextView tvSc;
    @Bind(R.id.tv_snum)
    TextView tvSnum;
    @Bind(R.id.tv_wish)
    TextView tvWish;
    @Bind(R.id.tv_cat)
    TextView tvCat;
    @Bind(R.id.tv_src)
    TextView tvSrc;
    @Bind(R.id.tv_dur)
    TextView tvDur;
    @Bind(R.id.tv_rt)
    TextView tvRt;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_dra)
    TextView tvDra;
    @Bind(R.id.lv_commons)
    ListView lvCommons;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        movieId = getIntent().getStringExtra(KEY_MOVIE_DETAIL);

        getDataFromHttp();

    }

    private void getDataFromHttp() {
        RetrofitManager.builderMovie().getMovieDetail(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieDetail>() {
                    @Override
                    public void call(MovieDetail detail) {
                        loadData(detail);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "call: "+throwable.getMessage());
                    }
                });
    }

    private void loadData(MovieDetail detail) {
        MovieDetail.MovieDetailModel detailModel=detail.data.MovieDetailModel;
        Glide.with(this).load(detailModel.img).placeholder(R.drawable.ic_gaoyuanyuan).into(ivImg);
        tvNm.setText(detailModel.nm);
        tvCat.setText(detailModel.cat);
        tvDra.setText(detailModel.dra);
        tvDur.setText(" /"+detailModel.dur+"分钟");
        tvRt.setText(detailModel.rt);
        tvSc.setText(String.valueOf(detailModel.sc));
        if(detailModel.snum>=10000){
            tvSnum.setText("("+detailModel.snum/10000+"万人评分)");
        }
        else {
            tvSnum.setText("("+detailModel.snum+"人评分)");

        }
        tvSrc.setText(detailModel.src);
        tvWish.setText(detailModel.wish+"");

    }

    public static  void start(Context context, String id){
        Intent intent=new Intent(context,MovieDetailActivity.class);
        intent.putExtra(KEY_MOVIE_DETAIL,id);
        context.startActivity(intent);

    }
}
