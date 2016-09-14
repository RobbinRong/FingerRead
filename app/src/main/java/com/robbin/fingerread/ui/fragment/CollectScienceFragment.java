package com.robbin.fingerread.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.robbin.fingerread.adapter.ScienceAdapter;
import com.robbin.fingerread.bean.ArticleBean;

import java.util.List;

/**
 * Created by OneAPM on 2016/9/13.
 */
public class CollectScienceFragment extends  BaseCollectItemFragment {
    @Override
    public RecyclerView.Adapter initAdapter() {
        List<ArticleBean> allScience = dao.getAllScience();
        return  new ScienceAdapter(allScience,getActivity());
    }
}
