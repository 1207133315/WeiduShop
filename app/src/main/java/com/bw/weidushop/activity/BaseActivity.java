package com.bw.weidushop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * com.baway.rxretrofitmvpdemo.activity
 *
 * @author 李宁康
 * @date 2019 2019/05/14 17:06
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract void initView();




    protected abstract int getView();


}
