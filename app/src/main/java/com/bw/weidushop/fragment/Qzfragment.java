package com.bw.weidushop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bw.weidushop.R;
import com.bw.weidushop.adapter.QuanZiAdapter;
import com.bw.weidushop.bean.QuanZiBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.DianZanPresenter;
import com.bw.weidushop.presenter.QuanZiPresenter;
import com.bw.weidushop.presenter.UnDianZanPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.baway.rxretrofitmvpdemo.fragment
 *
 * @author 李宁康
 * @date 2019 2019/05/16 09:40
 */
public class Qzfragment extends Fragment implements RequestDataInterface {

    private XRecyclerView recycler;
    private QuanZiPresenter presenter;
    private QuanZiAdapter adapter;
    private String sessionId;
    private Result<List<QuanZiBean>> result;
    private List<QuanZiBean> list=new ArrayList<>();
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = View.inflate(getActivity(), R.layout.qz_fragment_layout, null);
        presenter = new QuanZiPresenter(this);
        adapter = new QuanZiAdapter(getActivity());
        user = GetDao.getuser();
        sessionId= user.getSessionId();
        initView(view);
       // dianzan();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapter);
        recycler.refresh();
        return view;
    }

    private void initView(View view) {
        recycler = view.findViewById(R.id.recycler);
        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.requestData(true,"974",sessionId,"5");
            }

            @Override
            public void onLoadMore() {
                presenter.requestData(false,"974",sessionId,"5");
            }
        });
    }

    @Override
    public void success(Object obj, Object... args) {
        result = (Result<List<QuanZiBean>>) obj;
        recycler.refreshComplete();
        recycler.loadMoreComplete();
        if (presenter.getPage()==1){
            adapter.clear();
        }
        dianzan();




        adapter.addList(result.getResult());



        adapter.notifyDataSetChanged();

    }

    @Override
    public void fail(String msg) {

    }
    //圈子点赞功能
    public void dianzan(){
        adapter.setDianZan(new QuanZiAdapter.DianZan() {


            private DianZanPresenter dianZanPresenter;

            @Override
            public void dianzan(View view, final int i,boolean checked) {
                final QuanZiBean quanZiBean = (QuanZiBean) view.getTag();
                if (checked==false) {
                   quanZiBean.check=false;
                    new UnDianZanPresenter(new RequestDataInterface() {
                        @Override
                        public void success(Object obj, Object... args) {
                            final Result result1 = (Result) obj;

                            Toast.makeText(getContext(), "" + result1.getMessage(), Toast.LENGTH_SHORT).show();
                            quanZiBean.greatNum--;
                            quanZiBean.check=false;
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void fail(String msg) {

                        }
                    }).requestData(user.getUserId(), sessionId, quanZiBean.id + "");

                }else {


                    dianZanPresenter = new DianZanPresenter(new RequestDataInterface() {


                        @Override
                        public void success(Object obj, Object... args) {
                            final Result result1 = (Result) obj;

                            Toast.makeText(getContext(), "" + result1.getMessage(), Toast.LENGTH_SHORT).show();
                            quanZiBean.greatNum++;
                            quanZiBean.check=true;
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void fail(String msg) {

                        }
                    });

                        dianZanPresenter.requestData(user.getUserId(), sessionId, quanZiBean.id + "");


                }
            }
        });
    }

}

