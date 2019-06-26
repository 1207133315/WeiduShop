package com.bw.weidushop.adapter;

import android.support.annotation.Nullable;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.UserWalletBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WalletAdaprter extends BaseQuickAdapter<UserWalletBean.DetailListBean,BaseViewHolder> {

    public WalletAdaprter(int layoutResId, @Nullable List<UserWalletBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserWalletBean.DetailListBean item) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(item.getConsumerTime());
        String parse = format.format(date);
        helper.setText(R.id.wallet_jine,"￥"+item.getAmount()+".00");
        helper.setText(R.id.wallet_time,parse);
    }
}
