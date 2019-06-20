package com.bw.weidushop.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.bw.weidushop.bean.SyBean;

import java.util.List;

/**
 * com.baway.liningkang20190422.viewholder
 *
 * @author 李宁康
 * @date 2019 2019/04/22 09:53
 */
public abstract class AbstractViewHolder extends RecyclerView.ViewHolder {


    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindView(List<SyBean.Itme> list, Context context, int i);
}
