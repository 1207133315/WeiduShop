package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.baway.rxretrofitmvpdemo.adapter
 *
 * @author 李宁康
 * @date 2019 2019/05/22 20:57
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchBean> list=new ArrayList<>();
    Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(viewGroup.getContext(),R.layout.pzsh_itme_layout1,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).masterPic).into(viewHolder.img);
        viewHolder.name.setText(list.get(i).commodityName);
        viewHolder.price.setText(list.get(i).price+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearList() {
        list.clear();
    }

    public void addList(List<SearchBean> result) {
        if (!result.isEmpty()) {
            list.addAll(result);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
}
