package com.bw.weidushop.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;

import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.AddReceiveAddressPresenter;
import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewaddShouhuoActivity extends BaseActivity {
    @BindView(R.id.tx)
    TextView tx;
    @BindView(R.id.sjr)
    EditText sjr;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.yzbm)
    EditText yzbm;
    @BindView(R.id.baocun)
    Button baocun;
    private String s;
    private User user;

    private void submit() {
        // validate
        String sjrString = sjr.getText().toString().trim();
        String mobileString = mobile.getText().toString().trim();
        String addressString = address.getText().toString().trim();
        String yzbmString = yzbm.getText().toString().trim();
        if (TextUtils.isEmpty(yzbmString) || TextUtils.isEmpty(sjrString) || TextUtils.isEmpty(mobileString) || TextUtils.isEmpty(addressString)) {
            Toast.makeText(this, "输入的内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            new AddReceiveAddressPresenter(new RequestDataInterface() {
                @Override
                public void success(Object obj, Object... args) {
                    Result result = (Result) obj;
                    Toast.makeText(NewaddShouhuoActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    if (result.getStatus().equals("0000")) {
                        Intent intent = new Intent(NewaddShouhuoActivity.this, ShdzActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void fail(String msg) {

                }
            }).requestData((int) user.getUserId(), user.getSessionId(), sjrString, mobileString, addressString, yzbmString);
        }

        // TODO validate success, do something


    }

    @Override
    protected void initView() {
        user = GetDao.getuser();
    }

    @Override
    protected int getView() {
        return R.layout.activity_newadd_shouhuo;
    }



    @OnClick({R.id.diqu, R.id.baocun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.diqu:
                CityPicker build = new CityPicker.Builder(this)
                        .textSize(20)
                        .visibleItemsCount(3)
                        .build();
                build.show();
                build.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {

                    @Override
                    public void onSelected(String... citySelected) {
                        s = "";
                        for (int i = 0; i < citySelected.length; i++) {
                            if (i == citySelected.length - 1) {
                                yzbm.setText(citySelected[i] + " ");
                            } else {
                                s = s + citySelected[i];
                            }
                        }
                        diqu.setText(s);
                    }
                });
                break;
            case R.id.baocun:
                submit();
                break;
        }
    }
}
