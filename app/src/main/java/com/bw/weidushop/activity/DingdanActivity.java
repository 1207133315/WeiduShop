package com.bw.weidushop.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.AddDDAdapter;
import com.bw.weidushop.adapter.PopAddressAdapter;
import com.bw.weidushop.bean.AddressBean;
import com.bw.weidushop.bean.Dingdan;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.ShopCarChildBean;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.AddCarDataDao;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.AddressPresenter;
import com.bw.weidushop.presenter.DingDanPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingdanActivity extends BaseActivity {


    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.no)
    LinearLayout no;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.yes)
    LinearLayout yes;
    @BindView(R.id.dingdan)
    TextView dingdan;
    private List<ShopCarChildBean> lists;
    private User user;
    private AddressBean addressBean;
    private Result<List<AddressBean>> beanResult;
    @Override
    protected int getView() {
        return R.layout.activity_dingdan;
    }

    @Override
    protected void initView() {
        user = GetDao.getuser();
        Intent intent = getIntent();
        lists = (List<ShopCarChildBean>) intent.getSerializableExtra("list");
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AddDDAdapter adapter = new AddDDAdapter(this);
        adapter.clear();
        adapter.addList(lists);
        recycler.setAdapter(adapter);
        setPrice();
        showAddress();
    }

    //计算价格
    public void setPrice() {
        double num = 0.0;
        int a = 0;
        for (int i = 0; i < lists.size(); i++) {
            num += lists.get(i).price * lists.get(i).count;
            a += lists.get(i).count;
        }
        count.setText(a + "");
        price.setText(num + "");
    }
    private List<Dingdan> list=new ArrayList<>();
    //生成订单
    public void addDingdan(){
        double num=0.0;
        for (int i = 0; i < lists.size(); i++) {
             Dingdan dingdan = new Dingdan(lists.get(i).commodityId, lists.get(i).count);
             list.add(dingdan);
             num+=lists.get(i).price*lists.get(i).count;
        }
         String s = new Gson().toJson(list);
        new DingDanPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                 AddCarDataDao shopCarDao = GetDao.getShopCarDao();

                final Result result = (Result) obj;
                Toast.makeText(DingdanActivity.this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
              /*  for (int i = 0; i <lists.size() ; i++) {
                     ShopCarChildBean shopCarChildBean = lists.get(i);
                    shopCarDao.deleteByKey(shopCarChildBean.commodityId);
                }*/

                lists.clear();


                 Intent intent = new Intent(DingdanActivity.this, MainActivity.class);
                intent.putExtra("index",3);
                startActivity(intent);
            }

            @Override
            public void fail(String msg) {
                Toast.makeText(DingdanActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
            }
        }).requestData((int)user.getUserId(),user.getSessionId(),s,num,addressBean.id);
    }

    //查询收货地址列表
    public void showAddress() {

        new AddressPresenter(new RequestDataInterface() {



            @Override
            public void success(Object obj, Object... args) {
                beanResult = (Result<List<AddressBean>>) obj;
                if (beanResult.getResult()!=null) {
                    yes.setVisibility(View.VISIBLE);
                    no.setVisibility(View.GONE);
                    for (int i = 0; i < beanResult.getResult().size(); i++) {

                        if (beanResult.getResult().get(i).whetherDefault == 1) {
                            addressBean = beanResult.getResult().get(i);
                            name.setText(addressBean.realName);
                            phone.setText(addressBean.phone);
                            address.setText(addressBean.address);
                        }
                    }
                }else {
                    yes.setVisibility(View.GONE);
                    no.setVisibility(View.VISIBLE);

                }
            }



            @Override
            public void fail(String msg) {
                Toast.makeText(DingdanActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
            }
        }).requestData(user.getUserId()+"", user.getSessionId());
    }
    //显示pop
    public void showPop(){
        View view=View.inflate(DingdanActivity.this,R.layout.address_itme,null);
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(DingdanActivity.this,LinearLayoutManager.VERTICAL,false));
        PopAddressAdapter popAddressAdapter = new PopAddressAdapter(DingdanActivity.this);
        popAddressAdapter.addList(beanResult.getResult());


        recyclerView.setAdapter(popAddressAdapter);
        final PopupWindow popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setHeight(900);
        popupWindow.showAsDropDown(yes);
        popAddressAdapter.setDataCall(new PopAddressAdapter.DataCall() {
            @Override
            public void xuanze(AddressBean addressBean) {
                name.setText(addressBean.realName);
                phone.setText(addressBean.phone);
                address.setText(addressBean.address);
                popupWindow.dismiss();
            }
        });
    }
    @OnClick({R.id.add, R.id.recycler, R.id.count, R.id.price, R.id.no, R.id.name, R.id.phone, R.id.address, R.id.yes,R.id.dingdan,R.id.other})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.dingdan:
                if (lists!=null){
                    addDingdan();
                }
                break;
            case R.id.add:

                break;
            case R.id.recycler:
                break;
            case R.id.count:
                break;
            case R.id.price:
                break;
            case R.id.no:
                break;
            case R.id.name:
                break;
            case R.id.phone:
                break;
            case R.id.address:
                break;
            case R.id.yes:
                break;
            case R.id.other:
               showPop();
                break;
        }
    }



}
