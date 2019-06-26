package com.bw.weidushop.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.OrderlistAdapter;
import com.bw.weidushop.bean.Orderlist;
import com.bw.weidushop.bean.User;

import com.bw.weidushop.dao.GetDao;
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
                Request request = new Request.Builder().get().url("http://mobile.bwstudent.com/small/order/verify/v1/findOrderListByStatus?status=" + i + "&page=1&count=8")
                        .addHeader("userId", user.getUserId() + "")
                        .addHeader("sessionId", user.getSessionId())
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final Orderlist orderlist = gson.fromJson(string, Orderlist.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                OrderlistAdapter adapter = new OrderlistAdapter(orderlist.getOrderList());
                                recyler_dingdan.setAdapter(adapter);
                            }
                        });

                    }
                });
            }
        }.start();
    }
}
