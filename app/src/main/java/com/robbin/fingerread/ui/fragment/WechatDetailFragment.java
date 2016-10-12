package com.robbin.fingerread.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.robbin.fingerread.FingerReadApplication;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.WechatArticalBean;
import com.robbin.fingerread.constant.BaseUrl;
import com.robbin.fingerread.dao.CollectDao;
import com.robbin.fingerread.ui.activity.WechatDetailActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/10/12.
 */
public class WechatDetailFragment extends  BaseFragment{

    private WechatArticalBean.Content content;
    @Bind(R.id.web_wechat)
    WebView webWechat;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private CollectDao dao;
    protected boolean isCollected;
    @Bind(R.id.mainContent)
    LinearLayout mainContent;
    public static Fragment newInstance(WechatArticalBean.Content content) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(WechatDetailActivity.KEY_WECHAT, content);
        Fragment fragment = new WechatDetailFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        content= (WechatArticalBean.Content) getArguments().getSerializable(WechatDetailActivity.KEY_WECHAT);
        setHasOptionsMenu(true);
        init();
        loadData();
    }

    private void loadData() {
        webWechat.setWebViewClient(new WebViewClient());
        webWechat.getSettings().setJavaScriptEnabled(true);
        webWechat.loadUrl(content.url);
    }

    private void init() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);

        ActionBar supportActionBar = activity.getSupportActionBar();
        if(supportActionBar!=null){

            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(content.title);
        }
        dao=new CollectDao(FingerReadApplication.AppContext);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail,menu);
        updateCollectionMenu(menu.findItem(R.id.menu_collect));
    }
    protected void updateCollectionMenu(MenuItem item){
        if(isCollected){
            item.setIcon(R.drawable.ic_star_black);
        }else {
            item.setIcon(R.drawable.ic_star_white);
        }
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
            case R.id.menu_collect:
                if(isCollected){
                    removeFromCollection();
                    isCollected=false;
                    updateCollectionMenu(item);
                    Snackbar.make(mainContent, R.string.notify_remove_from_collection,Snackbar.LENGTH_SHORT).show();
                }
                else {
                    addToCollection();
                    isCollected = true;
                    updateCollectionMenu(item);
                    Snackbar.make(mainContent, R.string.notify_add_to_collection,Snackbar.LENGTH_SHORT).show();

                }
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addToCollection() {
        dao.insertWechat(content.url);
    }

    private void removeFromCollection() {
        dao.deleteWechat(content.url);
    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        if(content!=null){
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "["+content.title+"]:"+ BaseUrl.BASE_ZHIHU_URL+"story/"+content.id+" ("+getString(R.string.text_share_from)+getString(R.string.app_name)+")");
        }
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.hint_share_to)));
    }


}
