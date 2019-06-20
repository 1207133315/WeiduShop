package com.bw.weidushop.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.SyBean;

import java.util.List;

/**
 * com.baway.liningkang20190422.viewholder
 *
 * @author 李宁康
 * @date 2019 2019/04/22 09:55
 */
public class MlssViewHolder extends AbstractViewHolder {
    ImageView img;
    TextView name;
    TextView price;
    public MlssViewHolder(View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.img);
        name=itemView.findViewById(R.id.name);
        price=itemView.findViewById(R.id.price);
    }

    @Override
    public void onBindView(List<SyBean.Itme> list, Context context, int i) {
        Glide.with(context).load(list.get(i).masterPic).into(img);
        name.setText(list.get(i).commodityName+"                                                       ");
        price.setText("$:"+list.get(i).price+".00");
    }
}
