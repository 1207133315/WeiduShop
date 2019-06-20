package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * com.baway.rxretrofitmvpdemo.adapter
 *
 * @author 李宁康
 * @date 2019 2019/05/16 19:30
 */
public class MainAdapter extends FragmentStatePagerAdapter {
    Context context;
    List<Fragment> list=new ArrayList<>();
    public MainAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;

    }

    @Override
    public Fragment getItem(int i) {

        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addList(List<Fragment> list){
        this.list.addAll(list);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
