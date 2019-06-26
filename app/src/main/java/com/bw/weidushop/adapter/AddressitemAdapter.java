package com.bw.weidushop.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CheckBox;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.AddressBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class AddressitemAdapter extends BaseQuickAdapter<AddressBean,BaseViewHolder> {

    public AddressitemAdapter(int layoutResId, @Nullable List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {
        helper.setText(R.id.addressusername,item.realName);
        helper.setText(R.id.addressphone,item.phone);
        helper.setText(R.id.address,item.address);
        CheckBox view = helper.getView(R.id.checkbox);
        helper.addOnClickListener(R.id.checkbox);
        Log.d(TAG, "item.getWhetherDefault():" + item.whetherDefault);
        if (item.whetherDefault==1){
            view.setChecked(true);
        }else {
            view.setChecked(false);
        }
    }
}
