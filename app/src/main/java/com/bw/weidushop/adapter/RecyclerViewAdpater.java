package com.bw.weidushop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.bw.weidushop.R;
import com.bw.weidushop.adapter.viewholder.AbstractViewHolder;
import com.bw.weidushop.adapter.viewholder.MlssViewHolder;
import com.bw.weidushop.adapter.viewholder.PzshViewHolder;
import com.bw.weidushop.adapter.viewholder.RxxpViewHolder;
import com.bw.weidushop.bean.SyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * com.baway.liningkang20190422.dapater
 *
 * @author 李宁康
 * @date 2019 2019/04/22 09:50
 */
public class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SyBean.Itme> list=new ArrayList<>();
    private String name;

    public RecyclerViewAdpater(Context context, List<SyBean.Itme> list, String name) {
        this.context = context;
        this.list=list;
        this.name = name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                return new RxxpViewHolder(View.inflate(context, R.layout.rxxp_itme_layout,null));
            case 1:
                return new MlssViewHolder(View.inflate(context,R.layout.mlss_itme_layout,null));
            case 2:
                return new PzshViewHolder(View.inflate(context,R.layout.pzsh_itme_layout1,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final AbstractViewHolder viewHolder = (AbstractViewHolder) holder;
        viewHolder.onBindView(list,context,position);
       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(context, DetailActivity.class);
                //intent.putExtra("id",list.get(position).commodityId);
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (name.equals("热销新品")){
            return 0;
        }else if (name.equals("魔力时尚")){
            return 1;
        }else {
            return 2;
        }

    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick{
        void onClickLisener(View view, int i);
    }
}
