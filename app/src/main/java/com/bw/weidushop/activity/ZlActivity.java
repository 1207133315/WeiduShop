package com.bw.weidushop.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.dao.DaoMaster;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.dao.UserDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZlActivity extends AppCompatActivity {

    @BindView(R.id.my_head)
    ImageView mMyHead;
    @BindView(R.id.my_name)
    TextView mMyName;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zl);
        user = GetDao.getuser();
        initView();
    }

    private void initView() {
        Glide.with(this).load(user.getHeadPic()).into(mMyHead);
        mMyName.setText(user.getNickName());
    }

    @OnClick({R.id.my_head, R.id.my_name})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_head:
                break;
            case R.id.my_name:
                break;
        }
    }
}
