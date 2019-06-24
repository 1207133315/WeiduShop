package com.bw.weidushop.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.Orderlist;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

public class Orderlist2Adapter extends BaseMultiItemQuickAdapter<Orderlist.OrderListBean.DetailListBean,BaseViewHolder> {


    private int i;
    public Orderlist2Adapter(List<Orderlist.OrderListBean.DetailListBean> data, int i) {
        super(data);
        this.i=i;
        addItemType(1,R.layout.orderlist2);
        addItemType(2,R.layout.orderlist3);
        addItemType(3,R.layout.orderlistdaipingjian);
        addItemType(9,R.layout.orderlist3);
    }

    @Override
    protected void convert(BaseViewHolder helper, Orderlist.OrderListBean.DetailListBean item) {
        switch (i){

            case 1:
                helper.setText(R.id.order_name,item.getCommodityName());
                helper.setText(R.id.order_price,"￥"+item.getCommodityPrice()+"0");
                String commodityPic = item.getCommodityPic();
                String[] split = commodityPic.split(",");
                Glide.with(mContext).load(split[0]).into((ImageView) helper.getView(R.id.order_img));
                break;

            case 3:
                helper.setText(R.id.order_name,item.getCommodityName());
                helper.setText(R.id.order_price,"￥"+item.getCommodityPrice()+"0");
                helper.addOnClickListener(R.id.qupingjia);
                String commodityPic1 = item.getCommodityPic();
                String[] split1 = commodityPic1.split(",");
                Glide.with(mContext).load(split1[0]).into((ImageView) helper.getView(R.id.order_img));
                break;
            case 9:
            case 2:
                helper.setText(R.id.order_name,item.getCommodityName());
                helper.setText(R.id.order_price,"￥"+item.getCommodityPrice()+"0");
                String commodityPic3 = item.getCommodityPic();
                String[] split3 = commodityPic3.split(",");
                Glide.with(mContext).load(split3[0]).into((ImageView) helper.getView(R.id.order_img));
                break;
        }
    }
}
