package com.robbin.fingerread.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.ui.activity.DailyDetailActivity;
import com.robbin.fingerread.ui.activity.ScienceDetailActivity;
import com.robbin.fingerread.utils.DisplayUtil;

import java.io.IOException;

import butterknife.Bind;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/11.
 */
public class ScienceDetailFragment extends  BaseFragment {
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.content_view)
    protected WebView contentView;
    @Bind(R.id.topImage)
    protected ImageView topImage;
    @Bind(R.id.scrollView)
    protected NestedScrollView scrollView;
    @Bind(R.id.main_content)
    protected FrameLayout mainContent;
    @Bind(R.id.progressBar)
    protected ProgressBar progressBar;
    @Bind(R.id.progressBarTopPic)
    protected ProgressBar progressBarTopPic;
    @Bind(R.id.networkBtn)
    protected ImageButton networkBtn;
    private ArticleBean articleBean;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_science_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        articleBean= (ArticleBean) getArguments().getSerializable(ScienceDetailActivity.KEY_SCIENCE);
        setHasOptionsMenu(true);
        init();
        loadData();
    }
    private void init(){
        final AppCompatActivity activity= (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar supportActionBar = activity.getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        supportActionBar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        supportActionBar.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.top_gradient));
        contentView.getSettings().setJavaScriptEnabled(true);
        contentView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                displayNetworkError();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                displayNetworkError();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                contentView.loadUrl(url);
                return false;
            }
        });
        contentView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        contentView.getSettings().setDomStorageEnabled(true);
        contentView.getSettings().setDatabaseEnabled(true);
        contentView.getSettings().setBlockNetworkImage(false);//不阻止网络图片，后期应该先判断是否wifi状态

        networkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkBtn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                loadData();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                return  true;
            case R.id.menu_action_share:
                share();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "["+articleBean.getTitle()+"]:"+articleBean.getUrl()+" ( "+getString(R.string.text_share_from)+getString(R.string.app_name)+")");
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.hint_share_to)));
    }
    public void hideLoading() {
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
            progressBarTopPic.setVisibility(View.VISIBLE);
        }
    }
    protected void setMainContentBg(final String url) {
        if (url.isEmpty()) {
            setDefaultColor();
            return;
        }
        Glide.with(getActivity()).load(url).placeholder(R.drawable.ic_placeholder).into(topImage);
        progressBarTopPic.setVisibility(View.GONE);



      /*  Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        HttpUtil.enqueue(request, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        setDefaultColor();
                    }
                });
            }
            @Override
            public void onResponse(Response response) throws IOException {
                final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(bitmap == null){
                            setDefaultColor();
                            return;
                        }
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                            topImage.setBackground(new BitmapDrawable(getResources(), bitmap));
                        } else{
                            topImage.setImageURI(Uri.parse(url));
                        }
                        mainContent.setBackgroundColor(ImageUtil.getImageColor(bitmap));
                        progressBarTopPic.setVisibility(View.GONE);
                    }
                });
            }
        });*/
    }
    protected void setDefaultColor(){
        mainContent.setBackgroundColor(Color.rgb(67,76,66));
        progressBarTopPic.setVisibility(View.GONE);
    }
    public void displayNetworkError() {
        if(networkBtn != null){
            networkBtn.setVisibility(View.VISIBLE);
        }
    }

    public void loadData(){
        scrollView.setVisibility(View.VISIBLE);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                topImage.setTranslationY(Math.max(-scrollY / 2, -DisplayUtil.dip2px(getActivity().getBaseContext(), 170)));
            }
        });
        contentView.loadUrl(articleBean.getUrl());
        setMainContentBg(articleBean.getImage_info().getUrl());
        hideLoading();
    }
    public static Fragment newInstance(ArticleBean articleBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ScienceDetailActivity.KEY_SCIENCE, articleBean);
        Fragment fragment = new ScienceDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
