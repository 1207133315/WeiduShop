package com.bw.weidushop.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.WalletAdaprter;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.bean.UserWalletBean;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.dao.UserDao;
import com.bw.weidushop.presenter.WalletPresenter;

import java.util.List;

import butterknife.BindView;

public class WodbActivity extends BaseActivity {
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.userwalletrecy)
    RecyclerView userwalletrecy;

    @Override
    protected void initView() {
        User user = GetDao.getuser();
        Log.d("WodbActivity0", "user.getUserId():" + user.getUserId()+"/"+user.getSessionId());
        new WalletPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                Result<UserWalletBean> bean= (Result<UserWalletBean>) obj;
                UserWalletBean result = bean.getResult();
                balance.setText(result.getBalance()+".00");
                WalletAdaprter walletAdaprter = new WalletAdaprter(R.layout.userwalletitem, result.getDetailList());
                userwalletrecy.setLayoutManager(new LinearLayoutManager(WodbActivity.this,LinearLayoutManager.VERTICAL,false));
                userwalletrecy.setAdapter(walletAdaprter);
            }

            @Override
            public void fail(String msg) {

            }
        }).requestData((int)user.getUserId(),user.getSessionId(),1,10);
    }

    @Override
    protected int getView() {
        return R.layout.activity_wodb;
    }

}
