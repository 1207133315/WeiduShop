package com.bw.weidushop.adapter;

import android.support.annotation.Nullable;

import com.bw.weidushop.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class PopwindowAdapter01 extends BaseQuickAdapter<String,BaseViewHolder> {


    public PopwindowAdapter01(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        helper.setText(R.id.poptext,item);
        helper.addOnClickListener(R.id.poptext);
    }
}
