package com.robbin.fingerread.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.robbin.fingerread.adapter.CollectDailyAdapter;
import com.robbin.fingerread.bean.NewsDetail;

import java.util.List;

/**
 * Created by OneAPM on 2016/9/13.
 */
public class CollectDailyFragment extends  BaseCollectItemFragment {
    @Override
    public RecyclerView.Adapter initAdapter() {
        List<NewsDetail> allDaily = dao.getAllDaily();
        return  new CollectDailyAdapter(getActivity(),allDaily);

    }
}
