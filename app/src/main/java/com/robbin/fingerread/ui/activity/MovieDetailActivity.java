package com.robbin.fingerread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.MovieCelebrity;
import com.robbin.fingerread.bean.MovieDetail;
import com.robbin.fingerread.bean.MovieMajor;
import com.robbin.fingerread.network.manager.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MovieDetailActivity extends BaseActivity implements View.OnClickListener{
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
    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.fl_img)
    FrameLayout flImg;
    @Bind(R.id.ll_celebrity)
    LinearLayout llCelebrity;
    @Bind(R.id.imb_show_all)
    ImageView imbShowAll;
    @Bind(R.id.im_show_major_all)
    ImageView imShowMajorAll;
    @Bind(R.id.iv_major)
    ImageView imMajor;
    @Bind(R.id.tv_major_name)
    TextView tvMajorName;
    @Bind(R.id.tv_major_content)
    TextView tvMajorContent;
    @Bind(R.id.tv_major_approve)
    TextView tvApprove;
    private MovieDetail movieDetail;

    private int maxLines;
    private int maxLinesMajor;
    private boolean isShowAll=false;
    private boolean isShowAllMajor=false;
    private static final int MAX = 3;//初始maxLine大小
    private static final int MAXMAJOR = 2;//初始maxLine大小

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        movieId = getIntent().getStringExtra(KEY_MOVIE_DETAIL);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getDataFromHttp();
        imShowMajorAll.setOnClickListener(this);
        imbShowAll.setOnClickListener(this);
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
        RetrofitManager.builderMaoYan().getMovieCelebrity(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieCelebrity>() {
                    @Override
                    public void call(MovieCelebrity celebrity) {
                        loadCelebrity(celebrity);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "call: "+throwable.getMessage());
                    }
                });
        RetrofitManager.builderMaoYan().getMovieMajor(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieMajor>() {
                    @Override
                    public void call(MovieMajor major) {
                        loadMajor(major);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "call: "+throwable.getMessage());
                    }
                });
    }

    private void loadMajor(MovieMajor major) {
        MovieMajor.Major m = major.data.major.get(0);
        if(null!=m){
            Glide.with(this).load(m.avatarurl).placeholder(R.drawable.ic_placeholder).into(imMajor);
            tvMajorName.setText(m.nickName);
            tvMajorContent.setText(m.content);
            maxLinesMajor=tvMajorContent.getLineCount();
            tvMajorContent.setMaxLines(MAXMAJOR);
            tvApprove.setText(m.approve);
        }
    }

    private void loadCelebrity(MovieCelebrity celebrity) {
            MovieCelebrity mc=new MovieCelebrity();
            List<MovieCelebrity.Data> datas= new ArrayList<MovieCelebrity.Data>();
            for (int i=0;i<celebrity.data.size();i++){
            for(int j=0;j<celebrity.data.get(i).size();j++){
                datas.add(celebrity.data.get(i).get(j));
            }
        }
        for(int i=0;i<datas.size();i++){
            if(!datas.get(i).still.isEmpty()){
                View view= LayoutInflater.from(this).inflate(R.layout.celebrity_item,null,false);
                ImageView ivCelebrity= (ImageView) view.findViewById(R.id.iv_celebrity);
                TextView tvCelebrityName= (TextView) view.findViewById(R.id.tv_celebrity_name);
                TextView tvCelebrityJueSe= (TextView) view.findViewById(R.id.tv_celebrity_juese);
                MovieCelebrity.Data d=datas.get(i);
                Glide.with(this).load(d.still).placeholder(R.drawable.ic_placeholder).into(ivCelebrity);
                tvCelebrityName.setText(d.cnm);
                tvCelebrityJueSe.setText("饰 "+d.roles);
                llCelebrity.addView(view);
            }

        }


    }
    private void loadData(MovieDetail detail) {
        ActionBar actionBar=getSupportActionBar();

        if(actionBar!=null){
            actionBar.setTitle(detail.data.MovieDetailModel.nm);
        }
        final MovieDetail.MovieDetailModel detailModel=detail.data.MovieDetailModel;
        Glide.with(this).load(detailModel.img).placeholder(R.drawable.ic_gaoyuanyuan).into(ivImg);
        tvNm.setText(detailModel.nm);
        tvCat.setText(detailModel.cat);
        String dra="";
        if(detailModel.dra.startsWith("<p>")){
            dra=detailModel.dra.replace("<p>","");
        }
        if(detailModel.dra.endsWith("</p>")){
            dra=dra.replace("</p>","");
        }
        tvDra.setText(dra);
        maxLines=tvDra.getLineCount();
        tvDra.setMaxLines(MAX);
        Log.e(TAG, "loadData: linecount"+tvDra.getLineCount()+"line height"+tvDra.getLineHeight());
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
        if(!detailModel.vd.isEmpty()){
            flImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieVDDetailActivity.start(MovieDetailActivity.this,detailModel.vd);
                }
            });
        }
        else {
            ivPlay.setVisibility(View.INVISIBLE);

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:return false;
        }
    }

    public static  void start(Context context, String id){
        Intent intent=new Intent(context,MovieDetailActivity.class);
        intent.putExtra(KEY_MOVIE_DETAIL,id);
        context.startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imb_show_all:
                toggle();
                break;
            case R.id.im_show_major_all:
                toggleMajor();
                break;
            default:
                break;
        }

    }

    private void toggleMajor() {
        if(!isShowAllMajor){
            isShowAllMajor=!isShowAllMajor;
            imShowMajorAll.setImageResource(R.drawable.ic_find_previous_holo_light);
            tvMajorContent.setMaxLines(maxLinesMajor);
            tvMajorContent.postInvalidate();
        }
        else {
            isShowAllMajor=!isShowAllMajor;
            imShowMajorAll.setImageResource(R.drawable.ic_find_next_holo_light);
            tvMajorContent.setMaxLines(MAXMAJOR);
            tvMajorContent.postInvalidate();

        }
    }

    public void toggle(){
        if(!isShowAll){
            isShowAll=!isShowAll;
            imbShowAll.setImageResource(R.drawable.ic_find_previous_holo_light);
            tvDra.setMaxLines(maxLines);
            tvDra.postInvalidate();
        }
        else {
            isShowAll=!isShowAll;
            imbShowAll.setImageResource(R.drawable.ic_find_next_holo_light);
            tvDra.setMaxLines(MAX);
            tvDra.postInvalidate();

        }
    }
}
