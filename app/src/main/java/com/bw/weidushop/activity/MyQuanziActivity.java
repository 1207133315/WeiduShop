package com.bw.weidushop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.QuanZiAdapter;
import com.bw.weidushop.bean.QuanZiBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.MyQuanZiPresenter;
import com.bw.weidushop.presenter.QuanZiPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyQuanziActivity extends BaseActivity implements RequestDataInterface {
    MyQuanZiPresenter presenter;
    private XRecyclerView recycler;
    private QuanZiAdapter adapter;
    private String sessionId;
    private Result<List<QuanZiBean>> result;
    private List<QuanZiBean> list=new ArrayList<>();
    private User user;
    @Override
    protected int getView() {
        return R.layout.activity_my_quanzi;
    }

    @Override
    protected void initView() {
        presenter = new MyQuanZiPresenter(this);
        adapter = new QuanZiAdapter(this);
        user = GetDao.getuser();
        sessionId= user.getSessionId();
            init();
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(adapter);
        recycler.refresh();
    }

    private void init() {
        recycler =findViewById(R.id.recycler);
        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.requestData(true,user.getUserId(),sessionId,"5");
            }

            @Override
            public void onLoadMore() {
                presenter.requestData(false,user.getUserId(),sessionId,"5");
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




        adapter.addList(result.getResult());



        adapter.notifyDataSetChanged();

    }

    @Override
    public void fail(String msg) {

    }
}
