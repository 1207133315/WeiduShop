package com.bw.weidushop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bw.weidushop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * com.baway.rxretrofitmvpdemo.adapter
 *
 * @author 李宁康
 * @date 2019 2019/05/29 19:25
 */
public class QuanziChildAdapter extends RecyclerView.Adapter<QuanziChildAdapter.ViewHolder> {
    List<Object> imgs=new ArrayList<>();
    Context context;

    public QuanziChildAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(viewGroup.getContext(),R.layout.qz_chile_itme,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (imgs.get(i) instanceof String) {
            String imageUrl = (String) imgs.get(i);
            if (imageUrl.contains("http:")) {//加载http
               Glide.with(context).load(imageUrl).into(viewHolder.img);
            } else {//加载sd卡

                Glide.with(context).load("file://" + imageUrl).into(viewHolder.img);

            }
        } else {//加载资源文件
            int id = (int)imgs.get(i);
            Uri uri = Uri.parse("res://com.dingtao.rrmmp/" + id);

            Glide.with(context).load(uri).into(viewHolder.img);
        }
    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }

    public void clear() {
        imgs.clear();
    }

    public void addList(List<String> strings) {
        if (strings!=null){
            imgs.addAll(strings);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
        }
    }
}
