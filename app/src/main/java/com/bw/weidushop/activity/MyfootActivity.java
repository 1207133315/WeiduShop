package com.bw.weidushop.activity;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.MyfootAdapter;
import com.bw.weidushop.bean.BrowseListBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.BrowseListPresenter;

import java.util.List;

import butterknife.BindView;

public class MyfootActivity extends BaseActivity {

    @BindView(R.id.recyler_myfoot)
    RecyclerView recylerMyfoot;

    @Override
    protected int getView() {
        return R.layout.activity_myfoot;
    }

    @Override
    protected void initView() {
        User getuser = GetDao.getuser();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recylerMyfoot.setLayoutManager(manager);
        new BrowseListPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                Result<List<BrowseListBean>> result= (Result<List<BrowseListBean>>) obj;
                List<BrowseListBean> list = result.getResult();
                MyfootAdapter myfootAdapter = new MyfootAdapter(R.layout.brow, list);
                recylerMyfoot.setAdapter(myfootAdapter);
            }

            @Override
            public void fail(String msg) {

            }
        }).requestData((int)getuser.getUserId(),getuser.getSessionId(),1,10);
    }

}
