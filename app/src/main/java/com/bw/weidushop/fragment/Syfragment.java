package com.bw.weidushop.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.activity.SearchActivity;
import com.bw.weidushop.adapter.RecyclerViewAdpater;
import com.bw.weidushop.bean.BannerBean;
import com.bw.weidushop.bean.ImageBean;
import com.bw.weidushop.bean.PopBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.SyBean;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.BannerPresenter;
import com.bw.weidushop.presenter.ShowSyPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * com.baway.rxretrofitmvpdemo.fragment
 *
 * @author 李宁康
 * @date 2019 2019/05/16 09:40
 */
public class Syfragment extends Fragment implements RequestDataInterface {

    private RecyclerView rxxplist;
    private RecyclerView mlsslist;
    private RecyclerView pzshlist;
    private ShowSyPresenter presenter;
    private List<ImageBean> imgs=new ArrayList<>();
    private XBanner banner;
    private ImageView other;

    private ImageView search;
    private TextView text;
    private String sessionId;
    private List<String> list1;
    private List<PopBean> list2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = View.inflate(getContext(), R.layout.sy_fragment_layout, null);

        initView(view);
        showBanner();
        return view;
    }

    private void initView(View view) {
        final User user = GetDao.getuser();
        sessionId=user.getSessionId();

        search = view.findViewById(R.id.search);
        other = view.findViewById(R.id.other);
        text = view.findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        rxxplist = view.findViewById(R.id.rxxplist);
        mlsslist = view.findViewById(R.id.mlsslist);
        pzshlist = view.findViewById(R.id.pzshlist);
        banner = view.findViewById(R.id.banner);
        presenter = new ShowSyPresenter(this);
        presenter.requestData();
        rxxplist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mlsslist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        pzshlist.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    //banner
    public void showBanner(){
        new BannerPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                final Result<List<BannerBean>> listResult = (Result<List<BannerBean>>) obj;
                if (imgs.size()<1){
                    for (int i = 0; i <listResult.getResult().size() ; i++) {
                        final ImageBean imageBean = new ImageBean();
                        imageBean.imgUrl=listResult.getResult().get(i).imageUrl;
                        imgs.add(imageBean);
                    }

                }
                banner.setBannerData(imgs);
                banner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity()).load(((ImageBean)model).getXBannerUrl()).into((ImageView)view);
                    }
                });

            }

            @Override
            public void fail(String msg) {

            }
        }).requestData();
    }


    @Override
    public void success(Object obj, Object... args) {
        Result<SyBean> goodsBean = (Result<SyBean>) obj;
        final SyBean result = goodsBean.getResult();
        RecyclerViewAdpater rxxpAdpater = new RecyclerViewAdpater(getContext(),result.rxxp.commodityList, result.rxxp.name);
        RecyclerViewAdpater mlssAdpater = new RecyclerViewAdpater(getContext(),result.mlss.commodityList, result.mlss.name);
        final RecyclerViewAdpater pzshAdpater = new RecyclerViewAdpater(getContext(),result.pzsh.commodityList, result.pzsh.name);
        rxxplist.setAdapter(rxxpAdpater);
        mlsslist.setAdapter(mlssAdpater);
        pzshlist.setAdapter(pzshAdpater);


    }

    @Override
    public void fail(String msg) {

    }

    private RecyclerViewAdpater.OnClick onClick;

    public void setOnClick(RecyclerViewAdpater.OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick{
        void onClickLisener(View view, int i);
    }
}

