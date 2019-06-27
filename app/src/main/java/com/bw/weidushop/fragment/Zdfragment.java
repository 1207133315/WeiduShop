package com.bw.weidushop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.activity.PayActivity;
import com.bw.weidushop.adapter.OrderlistAdapter;
import com.bw.weidushop.bean.Orderlist;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.ConfirmReceipt;
import com.bw.weidushop.presenter.DeleteOrderPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Zdfragment extends BaseFragment {

    @BindView(R.id.alldingdan)
    RadioButton alldingdan;
    @BindView(R.id.daifukuan)
    RadioButton daifukuan;
    @BindView(R.id.daishouhuo)
    RadioButton daishouhuo;
    @BindView(R.id.daipingjia)
    RadioButton daipingjia;
    @BindView(R.id.yiwancheng)
    RadioButton yiwancheng;
    @BindView(R.id.recyler_dingdan)
    RecyclerView recyler_dingdan;
    Unbinder unbinder;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.zd_fragment_layout;
    }

    @Override
    protected void initView() {
        user = GetDao.getuser();
        recyler_dingdan.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        requestData(0);
    }


    @OnClick({R.id.alldingdan, R.id.daifukuan, R.id.daishouhuo, R.id.daipingjia, R.id.yiwancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alldingdan:
                requestData(0);
                break;
            case R.id.daifukuan:
                requestData(1);
                break;
            case R.id.daishouhuo:
                requestData(2);
                break;
            case R.id.daipingjia:
                requestData(3);
                break;
            case R.id.yiwancheng:
                requestData(9);
                break;
        }
    }

    public void requestData(final int i) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient client = new OkHttpClient.Builder().build();
                Log.d("Zdfragment", "user.getUserId():" + user.getUserId()+user.getSessionId());
                final Request request = new Request.Builder().get().url("http://mobile.bwstudent.com/small/order/verify/v1/findOrderListByStatus?status=" + i + "&page=1&count=8")
                        .addHeader("userId", user.getUserId() + "")
                        .addHeader("sessionId", user.getSessionId())
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(final Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final Orderlist orderlist = gson.fromJson(string, Orderlist.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                OrderlistAdapter adapter = new OrderlistAdapter(orderlist.getOrderList());
                                recyler_dingdan.setAdapter(adapter);
                                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                        switch (view.getId()){
                                            case R.id.qzf:
                                                double v = orderlist.getOrderList().get(position).getPayAmount();
                                                Intent intent=new Intent(getContext(),PayActivity.class);
                                                intent.putExtra("price",v);
                                                intent.putExtra("orderId",orderlist.getOrderList().get(position).getOrderId());
                                                startActivity(intent);
                                                break;
                                            case R.id.qxdd:
                                                new DeleteOrderPresenter(new RequestDataInterface() {
                                                    @Override
                                                    public void success(Object obj, Object... args) {
                                                        orderlist.getOrderList().remove(position);
                                                        adapter.notifyDataSetChanged();
                                                    }

                                                    @Override
                                                    public void fail(String msg) {

                                                    }
                                                }).requestData((int)GetDao.getuser().getUserId(),GetDao.getuser().getSessionId(),orderlist.getOrderList().get(position).getOrderId());
                                                break;
                                            case R.id.qrsh:
                                                    new ConfirmReceipt(new RequestDataInterface() {
                                                        @Override
                                                        public void success(Object obj, Object... args) {
                                                            Result result = (Result) obj;
                                                            Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void fail(String msg) {

                                                        }
                                                    }).requestData((int)GetDao.getuser().getUserId(),GetDao.getuser().getSessionId(),orderlist.getOrderList().get(position).getOrderId());
                                                break;
                                        }

                                    }
                                });
                            }
                        });

                    }
                });
            }
        }.start();
    }
}
