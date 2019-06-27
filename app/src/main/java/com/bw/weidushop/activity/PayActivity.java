package com.bw.weidushop.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.fragment.Syfragment;
import com.bw.weidushop.presenter.DeleteOrderPresenter;
import com.bw.weidushop.presenter.PayPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.GET;

public class PayActivity extends BaseActivity {


    @BindView(R.id.ttt)
    TextView ttt;
    @BindView(R.id.button1)
    RadioButton button1;
    @BindView(R.id.button2)
    RadioButton button2;
    @BindView(R.id.button3)
    RadioButton button3;
    @BindView(R.id.zhifu)
    Button zhifu;
    private double price;
    private String orderId;

    @Override
    protected int getView() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        price = intent.getDoubleExtra("price", 0);
        orderId = intent.getStringExtra("orderId");
        zhifu.setText("余额支付" + price + "元");
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.zhifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhifu:
                if (button1.isChecked()) {
                    new PayPresenter(new RequestDataInterface() {
                        @Override
                        public void success(Object obj, Object... args) {
                            new DeleteOrderPresenter(new RequestDataInterface() {
                                @Override
                                public void success(Object obj, Object... args) {
                                }

                                @Override
                                public void fail(String msg) {

                                }
                            }).requestData((int)GetDao.getuser().getUserId(),GetDao.getuser().getSessionId(),orderId);
                            View inflate = View.inflate(PayActivity.this, R.layout.zhifu, null);
                            PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.showAsDropDown(View.inflate(PayActivity.this,R.layout.activity_pay,null));
                            Button ckdd = inflate.findViewById(R.id.ckdd);
                            Button fhzy = inflate.findViewById(R.id.fhzy);
                            fhzy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            ckdd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                                    intent.putExtra("index",3);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void fail(String msg) {
                            View inflate = View.inflate(PayActivity.this, R.layout.zhifuf, null);
                            Button fhzy = inflate.findViewById(R.id.fhzy);

                            final PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.showAsDropDown(View.inflate(PayActivity.this,R.layout.activity_pay,null));
                            fhzy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    }).requestData((int) GetDao.getuser().getUserId(), GetDao.getuser().getSessionId(), orderId, 1);
                }
                if (button2.isChecked()) {
                    new PayPresenter(new RequestDataInterface() {
                        @Override
                        public void success(Object obj, Object... args) {
                            new DeleteOrderPresenter(new RequestDataInterface() {
                                @Override
                                public void success(Object obj, Object... args) {
                                }

                                @Override
                                public void fail(String msg) {

                                }
                            }).requestData((int)GetDao.getuser().getUserId(),GetDao.getuser().getSessionId(),orderId);
                            View inflate = View.inflate(PayActivity.this, R.layout.zhifu, null);
                            PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.showAsDropDown(View.inflate(PayActivity.this,R.layout.activity_pay,null));
                            Button ckdd = inflate.findViewById(R.id.ckdd);
                            Button fhzy = inflate.findViewById(R.id.fhzy);
                            fhzy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            ckdd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                                    intent.putExtra("index",3);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void fail(String msg) {
                            View inflate = View.inflate(PayActivity.this, R.layout.zhifuf, null);
                            Button fhzy = inflate.findViewById(R.id.fhzy);

                            final PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.showAsDropDown(View.inflate(PayActivity.this,R.layout.activity_pay,null));
                            fhzy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    }).requestData((int) GetDao.getuser().getUserId(), GetDao.getuser().getSessionId(), orderId, 2);
                }
                if (button3.isChecked()) {
                    new PayPresenter(new RequestDataInterface() {
                        @Override
                        public void success(Object obj, Object... args) {
                            new DeleteOrderPresenter(new RequestDataInterface() {
                                @Override
                                public void success(Object obj, Object... args) {
                                }

                                @Override
                                public void fail(String msg) {

                                }
                            }).requestData((int)GetDao.getuser().getUserId(),GetDao.getuser().getSessionId(),orderId);
                            View inflate = View.inflate(PayActivity.this, R.layout.zhifu, null);
                            PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.showAsDropDown(View.inflate(PayActivity.this,R.layout.activity_pay,null));
                            Button ckdd = inflate.findViewById(R.id.ckdd);
                            Button fhzy = inflate.findViewById(R.id.fhzy);
                            fhzy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            ckdd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(PayActivity.this,MainActivity.class);
                                    intent.putExtra("index",3);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void fail(String msg) {
                            View inflate = View.inflate(PayActivity.this, R.layout.zhifuf, null);
                            Button fhzy = inflate.findViewById(R.id.fhzy);

                            final PopupWindow popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(true);
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            popupWindow.showAsDropDown(View.inflate(PayActivity.this,R.layout.activity_pay,null));
                            fhzy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    }).requestData((int) GetDao.getuser().getUserId(), GetDao.getuser().getSessionId(), orderId, 3);
                }
                break;
        }
    }


}
