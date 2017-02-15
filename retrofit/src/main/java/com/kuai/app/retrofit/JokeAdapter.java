package com.kuai.app.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kuai.app.retrofit.bean.JokeResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tstau on 2017/2/14.
 */
public class JokeAdapter extends RecyclerView.Adapter{

    private List<JokeResult.ResultBean.Joke> mJokes;

    private Context mContext;

    public JokeAdapter(Context context){
        mContext = context;
        mJokes = new ArrayList<>();
    }

    public void refresh(List<JokeResult.ResultBean.Joke> jokes){
        this.mJokes = jokes;
        notifyDataSetChanged();
    }

    private static final int TYPE_IMG = 0;

    private static final int TYPE_TXT = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_IMG:
                return new ImgHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.joke_item_img,parent,false));
            case TYPE_TXT:
                return new TxtHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.joke_item_txt,parent,false));
        }
        throw new RuntimeException("error viewType!");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case TYPE_IMG:
                ImgHolder imgHolder = (ImgHolder) holder;
                imgHolder.content.setText(mJokes.get(position).getContent());
                Glide.with(mContext).load(mJokes.get(position).getUrl())
                        .centerCrop()
                        .crossFade()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(imgHolder.img);
                break;
            case TYPE_TXT:
                TxtHolder txtHolder = (TxtHolder) holder;
                txtHolder.content.setText(mJokes.get(position).getContent());
                txtHolder.time.setText(mJokes.get(position).getUpdateTime());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TextUtils.isEmpty(mJokes.get(position).getUrl()) ? TYPE_TXT : TYPE_IMG;
    }


    @Override
    public int getItemCount() {
        return mJokes.size();
    }

    private static class TxtHolder extends RecyclerView.ViewHolder{

        TextView time;

        TextView content;

        public TxtHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.tv_item_txt_time);
            content = (TextView) itemView.findViewById(R.id.tv_item_txt_content);
        }
    }

    private static class ImgHolder extends RecyclerView.ViewHolder{

        ImageView img;

        TextView content;

        public ImgHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_item_img);
            content = (TextView) itemView.findViewById(R.id.tv_item_img);
        }
    }
}
