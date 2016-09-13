package com.robbin.fingerread.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.ui.activity.DailyDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OneAPM on 2016/9/13.
 */
public class CollectDailyAdapter  extends  RecyclerView.Adapter<CollectDailyAdapter.DailViewHolder>  {

    private Context context;
    private List<NewsDetail> list;
    public CollectDailyAdapter(Context context, List<NewsDetail> list) {
        this.context=context;
        this.list=list;

    }

    @Override
    public DailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item,parent,false);
        return new DailViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final DailViewHolder holder, int position) {
        final NewsDetail detail = list.get(position);
        holder.mTvTitle.setText(detail.getTitle());
        Glide.with(context).load(detail.getImage()).placeholder(R.drawable.ic_placeholder).into(holder.mIvNews);
        holder.mCvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyDetailActivity.start(context,detail);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class DailViewHolder extends  RecyclerView.ViewHolder{
        @Bind(R.id.cv_item)
        CardView mCvItem;

        @Bind(R.id.iv_news)
        ImageView mIvNews;

        @Bind(R.id.tv_title)
        TextView mTvTitle;
        public DailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        }
}
