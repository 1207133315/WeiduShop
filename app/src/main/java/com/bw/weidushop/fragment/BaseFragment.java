package com.bw.weidushop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }



    //友盟统计
    /*@Override
    public void onResume() {
        super.onResume();
        if (!MTStringUtils.isEmpty(getPageName()))
            MobclickAgent.onPageStart(getPageName()); // 统计页面
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!MTStringUtils.isEmpty(getPageName()))
            MobclickAgent.onPageEnd(getPageName());// 统计页面
    }*/



    /**
     * 设置layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     */
    protected abstract void initView();
}
