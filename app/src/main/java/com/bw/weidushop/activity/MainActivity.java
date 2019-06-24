package com.bw.weidushop.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.bw.weidushop.R;
import com.bw.weidushop.adapter.MainAdapter;
import com.bw.weidushop.bean.AddCarData;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.ShopCarBean;
import com.bw.weidushop.bean.ShopCarChildBean;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.AddCarDataDao;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.fragment.Gwcfragment;
import com.bw.weidushop.fragment.Qzfragment;
import com.bw.weidushop.fragment.Syfragment;
import com.bw.weidushop.fragment.Wdfragment;
import com.bw.weidushop.fragment.Zdfragment;
import com.bw.weidushop.presenter.FindShopCarPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private RadioGroup rg;
    private RadioButton sy;
    private RadioButton qz;
    private RadioButton gwc;
    private RadioButton zd;
    private RadioButton wd;
    private List<Fragment> list=new ArrayList<>();
    private MainAdapter mainAdapter;
    private String sessionId;
    private User user;
    List<AddCarData> addlist=new ArrayList<>();
    @Override
    protected void initView() {
        user = GetDao.getuser();
         Intent intent = getIntent();
        sessionId = intent.getStringExtra("sessionId");
         int index = intent.getIntExtra("index", 0);

        viewPager = findViewById(R.id.vp);



        rg = findViewById(R.id.rg);
        sy = findViewById(R.id.sy);
        qz = findViewById(R.id.qz);
        gwc = findViewById(R.id.gwc);
        zd = findViewById(R.id.zd);
        wd = findViewById(R.id.wd);

        list.add(new Syfragment());
        list.add(new Qzfragment());
        list.add(new Gwcfragment());
        list.add(new Zdfragment());
        list.add(new Wdfragment());
        viewPager.setOffscreenPageLimit(list.size());
        mainAdapter = new MainAdapter(getSupportFragmentManager(), MainActivity.this);
        mainAdapter.addList(list);
        viewPager.setAdapter(mainAdapter);
        lianDong();
        findShopCar();
        if (index!=0&&index==3) {
            viewPager.setCurrentItem(3);
        }
    }


    //查询购物车
    public void findShopCar(){
        new FindShopCarPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                Result<List<ShopCarBean>> listResult = (Result<List<ShopCarBean>>) obj;
                AddCarDataDao shopCarDao = GetDao.getShopCarDao();
                for (int i = 0; i <listResult.getResult().size() ; i++) {
                    ShopCarBean shopCarBean = listResult.getResult().get(i);
                    for (int j = 0; j < shopCarBean.shoppingCartList.size(); j++) {
                         ShopCarChildBean shopCarChildBean = shopCarBean.shoppingCartList.get(j);
                         AddCarData addCarData = new AddCarData((int) shopCarChildBean.commodityId, shopCarChildBean.count);
                        addlist.add(addCarData);

                    }
                }

                for (int i = 0; i <addlist.size() ; i++) {
                    shopCarDao.insertOrReplaceInTx(addlist.get(i));
                }

            }

            @Override
            public void fail(String msg) {
            }
        }).requestData((int)user.getUserId(),user.getSessionId());
    }


    private void lianDong() {
        sy.setOnClickListener(this);
        qz.setOnClickListener(this);
        gwc.setOnClickListener(this);
        zd.setOnClickListener(this);
        wd.setOnClickListener(this);
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                final RadioButton radioButton = (RadioButton) rg.getChildAt(i);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    protected int getView() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sy:
                viewPager.setCurrentItem(0);
                break;
            case R.id.qz:
                viewPager.setCurrentItem(1);
                break;
            case R.id.gwc:
                viewPager.setCurrentItem(2);
                break;
            case R.id.zd:
                viewPager.setCurrentItem(3);
                break;
            case R.id.wd:
                viewPager.setCurrentItem(4);
                break;
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public  void goOrder(){
        viewPager.setCurrentItem(3);
    }
}
