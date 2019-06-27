package com.bw.weidushop.adapter;

import android.support.annotation.Nullable;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.PopBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

public class PopwindowAdapter02 extends BaseQuickAdapter<PopBean,BaseViewHolder> {

    public PopwindowAdapter02(int layoutResId, @Nullable List<PopBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PopBean item) {
        helper.setText(R.id.poptext,item.getText());
        helper.setImageResource(R.id.popimg,item.getImg());
        helper.addOnClickListener(R.id.popimg);
    }
}
