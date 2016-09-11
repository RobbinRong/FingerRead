package com.robbin.fingerread.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robbin.fingerread.R;
import com.robbin.fingerread.bean.BookBean;
import com.robbin.fingerread.bean.ReadingBean;
import com.robbin.fingerread.ui.activity.ReadDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by OneAPM on 2016/9/6.
 */
public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ReadViewHolder> {

    private ReadingBean readingBean;
    private Context context;
    private long lastPos = -1;
    private boolean isAnim = true;
    public ReadAdapter(ReadingBean readingBean, Context context) {
        this.readingBean = readingBean;
        this.context=context;
    }

    @Override
    public ReadAdapter.ReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_item, parent, false);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadAdapter.ReadViewHolder holder, int position) {
        final BookBean bookBean = readingBean.getBooks()[position];
        holder.bookTitle.setText(bookBean.getTitle());
        Glide.with(context).load(bookBean.getImage()).placeholder(R.drawable.ic_placeholder).into(holder.bookImg);
        holder.bookInfo.setText(bookBean.getInfo());
        holder.mCvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadDetailActivity.start(context,bookBean);
            }
        });
        if (isAnim) {
            startAnimator(holder.mCvItem, position);
        }

    }
    private void startAnimator(CardView mCvItem, int position) {
        if (position > lastPos) {
            mCvItem.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.item_bottom_in));
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return readingBean.getBooks()==null?0:readingBean.getBooks().length;
    }

    public void change(ReadingBean readingBean) {
        this.readingBean=readingBean;
        this.notifyDataSetChanged();
    }

    class ReadViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.cv_item)
        CardView mCvItem;

        @Bind(R.id.bookImg)
        ImageView bookImg;

        @Bind(R.id.bookTitle)
        TextView bookTitle;
        @Bind(R.id.bookInfo)
        TextView bookInfo;

        public ReadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
