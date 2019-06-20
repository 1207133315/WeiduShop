package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.ShopCarBean;
import com.bw.weidushop.bean.ShopCarChildBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * com.baway.liningkang20190520.adapter
 *
 * @author 李宁康
 * @date 2019 2019/05/20 09:59
 */
public class ShopCarChildAdapter extends RecyclerView.Adapter<ShopCarChildAdapter.ViewHolder> {
    private List<ShopCarChildBean> list=new ArrayList<>();
    private Context context;

    public ShopCarChildAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gwc_child_itme,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.checkBox.setChecked(list.get(i).isChecked);
            viewHolder.name.setText(list.get(i).commodityName+"                                                    ");
            viewHolder.price.setText("单价:"+list.get(i).price);
            viewHolder.count.setText(list.get(i).count+"");
            Glide.with(context).load(list.get(i).pic).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.img);
            viewHolder.checkBox.setTag(list.get(i));
            viewHolder.jia.setTag(list.get(i));
            viewHolder.jian.setTag(list.get(i));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<ShopCarChildBean> list){
        if (list!=null)
        this.list.addAll(list);
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkbox)
        CheckBox checkBox;
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

    private  Call call;

    public void setCall(Call call) {
        this.call = call;
    }

    public interface Call{
        void mCall(double sum, int a);
    }
}
