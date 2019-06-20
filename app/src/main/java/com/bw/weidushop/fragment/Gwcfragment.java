package com.bw.weidushop.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.ShopCarAdapter;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.ShopCarBean;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetUser;
import com.bw.weidushop.presenter.FindShopCarPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * com.baway.rxretrofitmvpdemo.fragment
 *
 * @author 李宁康
 * @date 2019 2019/05/16 09:40
 */
public class Gwcfragment extends BaseFragment {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.quanxuan)
    CheckBox mQuanxuan;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.go)
    Button mGo;
    private View view;
    private Unbinder unbinder;
    private User user;
    private ShopCarAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.gwc_fragment_layout;
    }

    @Override
    protected void initView() {
        user = GetUser.getuser();
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new ShopCarAdapter(getContext());
         mRecycler.setAdapter(adapter);
        findShopCar();

    }

    //查询购物车
    public void findShopCar(){
        new FindShopCarPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                 Result<List<ShopCarBean>> listResult = (Result<List<ShopCarBean>>) obj;
                adapter.addList(listResult.getResult());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String msg) {
                Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
            }
        }).requestData((int)user.getUserId(),user.getSessionId());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.recycler, R.id.quanxuan, R.id.price, R.id.go})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.recycler:
                break;
            case R.id.quanxuan:
                break;
            case R.id.price:
                break;
            case R.id.go:
                break;
        }
    }
}

