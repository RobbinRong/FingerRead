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
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.MovieCommonZYAdapter;
import com.robbin.fingerread.bean.MovieBox;
import com.robbin.fingerread.bean.MovieCelebrity;
import com.robbin.fingerread.bean.MovieCommonsZY;
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
    //@Bind(R.id.lv_commons_zhuanye)
    //ListView lvCommonsZY;
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
    @Bind(R.id.tv_box)
    TextView tvBox;
    @Bind(R.id.ll_box)
    LinearLayout llBox;
    @Bind(R.id.tv_box_last_day_rank)
    TextView tvRank;
    @Bind(R.id.tv_box_frist_week)
    TextView tvFirstWeek;
    @Bind(R.id.tv_sum_box)
    TextView tvSumBox;
    @Bind(R.id.zy_1)
    View viewZY1;
    @Bind(R.id.rl_commentZY)
    RelativeLayout rlCommentZY;
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
        rlCommentZY.setOnClickListener(this);
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
                        Log.e(TAG, "calldata: "+throwable.getMessage());
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
                        Log.e(TAG, "callcelebrity: "+throwable.getMessage());
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
                        Log.e(TAG, "callmajor: "+throwable.getMessage());
                    }
                });
        RetrofitManager.builderMaoYan().getMovieBox(movieId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieBox>() {
                    @Override
                    public void call(MovieBox box) {
                        loadBox(box);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "callbox: "+throwable.getMessage());
                    }
                });
        RetrofitManager.builderMaoYan().getMoviCommosZY(movieId,0,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieCommonsZY>() {
                    @Override
                    public void call(MovieCommonsZY commonZY) {
                        loadCommonZY(commonZY);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "callzy: "+throwable.getMessage());
                    }
                });
    }

    private void loadCommonZY(MovieCommonsZY commonZY) {
        List<MovieCommonsZY.Data> data = commonZY.data;
        l(viewZY1,data.get(0));
        // lvCommonsZY.setAdapter(new MovieCommonZYAdapter(commonZY,this));
        //setListViewHeightBasedOnChildren(lvCommonsZY);

    }
    private  void l(View view,MovieCommonsZY.Data data){
        Log.e(TAG, "l: "+data);
       com.makeramen.roundedimageview.RoundedImageView userLogo= (RoundedImageView) view.findViewById(R.id.iv_zy);
       TextView tvName= (TextView) view.findViewById(R.id.tv_name);
       TextView tvAuthinfo = (TextView) view.findViewById(R.id.tv_authinfo);
       TextView tvScore=(TextView) view.findViewById(R.id.tv_score);
       TextView tvContent= (TextView) view.findViewById(R.id.tv_content);
       TextView tvDate=(TextView) view.findViewById(R.id.tv_date);
       TextView tvAprove= (TextView) view.findViewById(R.id.tv_common_approve);
       Glide.with(this).load(data.avatarurl).placeholder(R.drawable.ic_placeholder).into(userLogo);
       tvName.setText(data.nickName);
       tvAuthinfo.setText(data.authInfo);
       tvScore.setText(data.score*2+"");
       tvContent.setText(data.content);
       tvDate.setText(data.startTime);
       tvAprove.setText(data.approve);

    }
/*    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); //获得Adapter
        if (listAdapter == null) {  //判断是否为空
            return;
        }
        int totalHeight = 0;  //定义总高度
        //根据listAdapter.getCount()获取当前拥有多少个item项，然后进行遍历对每一个item获取高度再相加最终获得总的高度。
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //获取到list的布局属性
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //listview最终高度为item的高度+分隔线的高度，这是重新设置listview的属性
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//将重新设置的params再应用到listview中
        listView.setLayoutParams(params);
    }*/
    private void loadBox(MovieBox box) {
        MovieBox.Mbox mbox = box.mbox;
        tvRank.setText(mbox.lastDayRank+"");
        tvFirstWeek.setText(mbox.firstWeekBox+"");
        tvSumBox.setText(mbox.sumBox+"");
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
            case R.id.rl_commentZY:

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
