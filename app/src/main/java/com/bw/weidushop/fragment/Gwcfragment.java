package com.bw.weidushop.fragment;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.activity.DingdanActivity;
import com.bw.weidushop.adapter.ShopCarAdapter;
import com.bw.weidushop.adapter.ShopCarChildAdapter;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.ShopCarBean;
import com.bw.weidushop.bean.ShopCarChildBean;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.FindShopCarPresenter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private ShopCarChildAdapter shopCarChildAdapter;
    private List<ShopCarChildBean> list=new ArrayList<>();
    private Result<List<ShopCarBean>> listResult;
    @Override
    protected int getLayoutId() {
        return R.layout.gwc_fragment_layout;
    }

    @Override
    protected void initView() {
        user = GetDao.getuser();
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new ShopCarAdapter(getContext());
        mRecycler.setAdapter(adapter);
        findShopCar();
        setPrice();

        adapter.setDataCall(new ShopCarAdapter.DataCall() {
            @Override
            public void dataCall(boolean a) {
                mQuanxuan.setChecked(a);
            }
        });
    }



    //设置总价
    public void setPrice(){
        adapter.setCall(new ShopCarAdapter.Call() {
            @Override
            public void mCall(double num,int a) {

                mPrice.setText("总计"+num+"元");
                mGo.setText("去结算("+a+")");
            }
        });
    }

    //查询购物车
    public void findShopCar(){
        new FindShopCarPresenter(new RequestDataInterface() {



            @Override
            public void success(Object obj, Object... args) {
                listResult = (Result<List<ShopCarBean>>) obj;

                adapter.addList(listResult.getResult());
                adapter.notifyDataSetChanged();


            }

            @Override
            public void fail(String msg) {
                Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
            }
        }).requestData((int)user.getUserId(),user.getSessionId());
    }


    @OnClick({R.id.recycler, R.id.quanxuan, R.id.price, R.id.go})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.recycler:
                break;
            case R.id.quanxuan:
            adapter.checkedAll(mQuanxuan.isChecked());
            adapter.priceAll();
                break;
            case R.id.price:
                break;
            case R.id.go:
                list.clear();
                for (int i = 0; i <listResult.getResult().size() ; i++) {
                     ShopCarBean shopCarBean = listResult.getResult().get(i);
                    for (int j = 0; j < shopCarBean.shoppingCartList.size(); j++) {
                         ShopCarChildBean shopCarChildBean = shopCarBean.shoppingCartList.get(j);
                         if (shopCarChildBean.isChecked){
                             list.add(shopCarChildBean);
                         }
                    }
                }
                final Intent intent = new Intent(getContext(), DingdanActivity.class);

                intent.putExtra("list", (Serializable) list);
                startActivity(intent);
                break;
        }
    }
}

