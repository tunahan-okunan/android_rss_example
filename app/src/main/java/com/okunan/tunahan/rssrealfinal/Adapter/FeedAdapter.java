package com.okunan.tunahan.rssrealfinal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.okunan.tunahan.rssrealfinal.DetailsActivity;
import com.okunan.tunahan.rssrealfinal.Interface.ItemClickListener;
import com.okunan.tunahan.rssrealfinal.Model.RSSObject;
import com.okunan.tunahan.rssrealfinal.R;
import com.squareup.picasso.Picasso;

/**
 * Created by TUNAHAN on 05.01.2018.
 */


class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView mTitleTextView;
    public TextView mPubDateTextView;
    public TextView mContentTextView;
    public ImageView mImageView;


    private ItemClickListener mItemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);
        mTitleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        mPubDateTextView = (TextView) itemView.findViewById(R.id.pub_date_text_view);
        mContentTextView = (TextView) itemView.findViewById(R.id.content_text_view);
        mImageView = (ImageView) itemView.findViewById(R.id.image_view);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        mItemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private RSSObject mRssObject;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FeedAdapter(RSSObject rssObject, Context context) {
        this.mRssObject = rssObject;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.mTitleTextView.setText(mRssObject.getItems().get(position).getTitle());
        holder.mPubDateTextView.setText(mRssObject.getItems().get(position).getPubDate());
        holder.mContentTextView.setText(mRssObject.getItems().get(position).getContent());
       loadImageFromUrl(holder.mImageView,mRssObject.getFeed().getImage().toString());
        RSSObject.myItems.add(mRssObject.getItems().get(position));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
                    sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sharingIntent.setData(Uri.parse(mRssObject.getItems().get(position).getLink()));
                    mContext.startActivity(sharingIntent);
                }
                else {
                    Intent intent= DetailsActivity.newIntent(mContext,mRssObject.getItems().get(position).getGuid());
                    mContext.startActivity(intent);
                }
            }
        });
    }
    private void loadImageFromUrl(ImageView imageView,String url) {
        Picasso.with(mContext).load(url).placeholder(R.mipmap.ic_wait_image)
                .error(R.mipmap.ic_wait_image)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
    @Override
    public int getItemCount() {
        return mRssObject.items.size();
    }
}
