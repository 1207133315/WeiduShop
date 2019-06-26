package com.bw.weidushop.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.BrowseListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyfootAdapter extends BaseQuickAdapter<BrowseListBean,BaseViewHolder> {

    public MyfootAdapter(int layoutResId, @Nullable List<BrowseListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrowseListBean item) {
        helper.setText(R.id.brow_name,item.getCommodityName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date(item.getBrowseTime());
        String format1 = format.format(date);
        helper.setText(R.id.brow_date,format1);
        helper.setText(R.id.brow_price,"￥"+item.getPrice());
        helper.setText(R.id.brow_liulan,"已浏览"+item.getBrowseNum()+"次");
        Glide.with(mContext).load(item.getMasterPic()).into((ImageView) helper.getView(R.id.brow_img));
    }
}