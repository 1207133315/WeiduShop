package com.bw.weidushop.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.AddressitemAdapter;
import com.bw.weidushop.bean.AddressBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.AddressPresenter;
import com.bw.weidushop.presenter.SetDefaultReceiveAddressPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShdzActivity extends BaseActivity {

    @BindView(R.id.shouhuotitle)
    TextView shouhuotitle;
    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.shdirecy)
    RecyclerView shdirecy;
    @BindView(R.id.newadd)
    Button newadd;

    @Override
    protected void initView() {
        final User user = GetDao.getuser();
        new AddressPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                Result<List<AddressBean>> result= (Result<List<AddressBean>>) obj;
                final List<AddressBean> list = result.getResult();
                LinearLayoutManager manager = new LinearLayoutManager(ShdzActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                shdirecy.setLayoutManager(manager);
                AddressitemAdapter adapter = new AddressitemAdapter(R.layout.shouhuoaddresslist,list);
                shdirecy.setAdapter(adapter);
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        for (AddressBean bean : list) {
                            bean.whetherDefault=2;
                        }
                        list.get(position).whetherDefault=1;
                        adapter.notifyDataSetChanged();
                        new SetDefaultReceiveAddressPresenter(new RequestDataInterface() {
                            @Override
                            public void success(Object obj, Object... args) {
                                Result er= (Result) obj;
                                Toast.makeText(ShdzActivity.this,er.getMessage() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void fail(String msg) {

                            }
                        }).requestData((int)user.getUserId(),user.getSessionId(),list.get(position).id);
                    }
                });

            }

            @Override
            public void fail(String msg) {

            }
        }).requestData((Long)user.getUserId()+"", user.getSessionId());
    }

    @Override
    protected int getView() {
        return R.layout.activity_shdz;
    }

    @OnClick(R.id.newadd)
    public void onViewClicked() {
        Intent intent = new Intent(this, NewaddShouhuoActivity.class);
        startActivity(intent);
        finish();
    }
}
