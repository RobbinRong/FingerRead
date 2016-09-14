package com.robbin.fingerread.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.constant.Settings;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OneAPM on 2016/9/13.
 */
public class CollectScienceAdapter extends  RecyclerView.Adapter<CollectScienceAdapter.DailViewHolder>  {

    private Context context;
    private List<ArticleBean> list;
    public CollectScienceAdapter(Context context, List<ArticleBean> list) {
        this.context=context;
        this.list=list;

    }

    @Override
    public DailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.science_item,parent,false);
        return new DailViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final DailViewHolder holder, int position) {
        final ArticleBean articleBean = list.get(position);
        if(Settings.isNightMode){
            holder.mCvItem.setCardBackgroundColor(Color.rgb(100,100,100));
        }
        holder.mTvTitle.setText(articleBean.getTitle());
        holder.mComment.setText(articleBean.getReplies_count()+"");
        Glide.with(context).load(articleBean.getImage_info().getUrl()).placeholder(R.drawable.ic_placeholder).into(holder.mIvNews);
        holder.mCvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DailyDetailActivity.start(context,articleBean);
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
        @Bind(R.id.comment)
        TextView mComment;


        public DailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        }
}
