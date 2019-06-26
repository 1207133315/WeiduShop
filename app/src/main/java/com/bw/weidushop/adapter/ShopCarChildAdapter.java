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


/**
 * com.baway.liningkang20190520.adapter
 *
 * @author 李宁康
 * @date 2019 2019/05/20 09:59
 */
public class ShopCarChildAdapter extends RecyclerView.Adapter<ShopCarChildAdapter.ViewHolder> {
    private List<ShopCarChildBean> list=new ArrayList<>();
    private Context context;
    public int a=0;
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
        a=0;
             ShopCarChildBean shopCarChildBean = list.get(i);
            viewHolder.name.setText(shopCarChildBean.commodityName);
            viewHolder.price.setText("单价:"+shopCarChildBean.price);
            viewHolder.count.setText(shopCarChildBean.count+"");
            Glide.with(context).load(shopCarChildBean.pic).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.img);
            viewHolder.checkBox.setChecked(shopCarChildBean.isChecked);
            viewHolder.checkBox.setTag(shopCarChildBean);

            viewHolder.jia.setTag(shopCarChildBean);
            viewHolder.jian.setTag(shopCarChildBean);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCarChildBean viewTag = (ShopCarChildBean) view.getTag();
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()){
                    viewTag.isChecked=true;
                }else {
                    viewTag.isChecked=false;
                }
                isCheck();
            }

        });


        viewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCarChildBean viewTag = (ShopCarChildBean) view.getTag();
                TextView textView = (TextView) view;
                viewTag.count++;
                notifyDataSetChanged();
            }
        });
        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCarChildBean viewTag = (ShopCarChildBean) view.getTag();
                TextView textView = (TextView) view;
                if (viewTag.count<=1){
                    Toast.makeText(context, "不能再减了!", Toast.LENGTH_SHORT).show();
                }else {
                    viewTag.count--;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addList(List<ShopCarChildBean> list){
        if (list!=null)
        this.list.addAll(list);
    }

    public void clear() {
        list.clear();
    }

    public void isCheck(){
        int a=0;
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).isChecked){
                a++;
            }
        }

        dataCall.dataCall(a);
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


    public DataCall dataCall;

    public void setDataCall(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    public interface DataCall{
        void dataCall(int a);
    }
}
