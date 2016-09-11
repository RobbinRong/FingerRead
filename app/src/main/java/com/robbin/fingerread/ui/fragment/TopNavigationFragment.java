package com.robbin.fingerread.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.robbin.fingerread.R;

import butterknife.Bind;

/**
 * Created by OneAPM on 2016/9/2.
 */
public abstract  class TopNavigationFragment extends BaseFragment {

    @Bind(R.id.vp_navi_content)
    ViewPager vpContent;
    private SmartTabLayout smartTabLayout;
    protected PagerAdapter pagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        smartTabLayout= (SmartTabLayout) getActivity().findViewById(R.id.tab_layout);
        smartTabLayout.setVisibility(View.VISIBLE);
        pagerAdapter=initPageAdapter();
        vpContent.setAdapter(pagerAdapter);
        smartTabLayout.setViewPager(vpContent);

    }

    public  abstract PagerAdapter initPageAdapter() ;


}
