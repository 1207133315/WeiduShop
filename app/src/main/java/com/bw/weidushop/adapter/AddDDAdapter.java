package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.ShopCarChildBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bw.weidushop.adapter
 *
 * @author 李宁康
 * @date 2019 2019/06/24 15:03
 */
public class AddDDAdapter extends RecyclerView.Adapter<AddDDAdapter.ViewHolder> {
    private List<ShopCarChildBean> list=new ArrayList<>();
    private Context context;

    public AddDDAdapter( Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.add_dd_itme,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ShopCarChildBean shopCarChildBean = list.get(i);
        viewHolder.name.setText(shopCarChildBean.commodityName);
        viewHolder.price.setText("单价:"+shopCarChildBean.price);
        viewHolder.count.setText(shopCarChildBean.count+"");
        Glide.with(context).load(shopCarChildBean.pic).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.img);
        viewHolder.jia.setTag(shopCarChildBean);
        viewHolder.jian.setTag(shopCarChildBean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void addList(List<ShopCarChildBean> lists) {
        if (lists!=null){
            list.addAll(lists);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.jian)
        TextView jian;
        @BindView(R.id.jia)
        TextView jia;
        @BindView(R.id.count)
        TextView count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
