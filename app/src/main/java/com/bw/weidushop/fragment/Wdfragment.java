package com.bw.weidushop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.weidushop.R;
import com.bw.weidushop.activity.GrzlActivity;
import com.bw.weidushop.activity.MyfootActivity;
import com.bw.weidushop.activity.MyQuanziActivity;
import com.bw.weidushop.activity.ShdzActivity;
import com.bw.weidushop.activity.WodbActivity;
import com.bw.weidushop.activity.ZlActivity;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.dao.GetDao;
import com.facebook.drawee.view.SimpleDraweeView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Wdfragment extends BaseFragment {


    @BindView(R.id.mine_back)
    SimpleDraweeView mineBack;
    @BindView(R.id.mine_nickname)
    TextView mineNickname;
    @BindView(R.id.grzl)
    TextView grzl;
    @BindView(R.id.wdqz)
    TextView wdqz;
    @BindView(R.id.wdzj)
    TextView wdzj;
    @BindView(R.id.wdqb)
    TextView wdqb;
    @BindView(R.id.wdshdz)
    TextView wdshdz;
    @BindView(R.id.mine_touxiang)
    SimpleDraweeView mineTouxiang;


    @Override
    protected int getLayoutId() {
        return R.layout.wd_fragment_layout;
    }

    @Override
    protected void initView() {
        User user = GetDao.getuser();
        String headPic = user.getHeadPic();
        mineBack.setImageURI(headPic);
        mineTouxiang.setImageURI(headPic);
    }



  /*  @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }*/

    @OnClick({R.id.grzl, R.id.wdzj, R.id.wdqb, R.id.wdshdz,R.id.wdqz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.grzl:
                Intent intent1=new Intent(getContext(),GrzlActivity.class);
                startActivity(intent1);
                break;
            case R.id.wdzj:
                Intent intent3=new Intent(getContext(),MyfootActivity.class);
                startActivity(intent3);

                break;
            case R.id.wdqb:
                Intent intent2=new Intent(getContext(),WodbActivity.class);
                startActivity(intent2);
                break;
            case R.id.wdshdz:
                Intent intent=new Intent(getContext(),ShdzActivity.class);
                startActivity(intent);

                break;

            case R.id.wdqz:
                Intent intent4=new Intent(getContext(),MyQuanziActivity.class);
                startActivity(intent4);
                break;
        }
    }
}

