package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.robbin.fingerread.R;
import com.robbin.fingerread.adapter.PagerAdapter;
import com.robbin.fingerread.bean.BookBean;
import com.robbin.fingerread.constant.ReadingApi;
import com.robbin.fingerread.ui.activity.ReadDetailActivity;

import butterknife.Bind;

/**
 * Created by OneAPM on 2016/9/6.
 */
public class ReadDetailFragment extends BaseFragment {

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.sliding_tabs)
    TabLayout tabLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    public static  BookBean bookeBean;
    private PagerAdapter adapter;
    private AppCompatActivity mActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read_detail;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        bookeBean = getArguments().getParcelable(ReadDetailActivity.KEY_READ);
        mActivity = (AppCompatActivity) getActivity();
        mActivity.setSupportActionBar(toolbar);
        for(String title: ReadingApi.bookTab_Titles){
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setTitle(bookeBean.getTitle());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
        adapter = new PagerAdapter(mActivity.getSupportFragmentManager(),ReadingApi.bookTab_Titles) {
            @Override
            public Fragment getItem(int position) {
                ReadingTabFragment fragment = new ReadingTabFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.id_pos),position);
                fragment.setArguments(bundle);
                return fragment;
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    public static Fragment newInstance(BookBean bookBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ReadDetailActivity.KEY_READ, bookBean);
        Fragment fragment = new ReadDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
