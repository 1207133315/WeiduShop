package com.bw.weidushop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.weidushop.R;
import com.bw.weidushop.adapter.SearchAdapter;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.SearchBean;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.presenter.SearchPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, RequestDataInterface {

    private ImageView mOther;
    /**
     * 输入您要搜素的商品
     */
    private EditText mText;
    private ImageView mSearch;
    private XRecyclerView mRecycler;
    private SearchPresenter presenter;
    private String s;
    private SearchAdapter adapter;
    boolean aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

        presenter = new SearchPresenter(this);
        mRecycler.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new SearchAdapter(this);

        Intent intent = getIntent();
        String goods = intent.getStringExtra("goods");
        if (goods != null) {
            mText.setText(goods.trim());
            s=goods.trim();
            presenter.requestData(aa, s,"10");
        }
        mRecycler.setAdapter(adapter);
        mRecycler.refresh();
    }

    private void initView() {
        mOther = (ImageView) findViewById(R.id.other);
        mText = (EditText) findViewById(R.id.text);
        mSearch = (ImageView) findViewById(R.id.search1);
        mRecycler = (XRecyclerView) findViewById(R.id.recycler);
        mSearch.setOnClickListener(this);
        s = mText.getText().toString();
        mRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                aa=true;
                presenter.requestData(aa, s,"10");
            }

            @Override
            public void onLoadMore() {
                aa=false;
                presenter.requestData(aa, s,"10");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search1:
                s = mText.getText().toString();
                aa=true;
               // if (!s.isEmpty())
                presenter.requestData(aa, s,"10");

                break;
        }
    }

    @Override
    public void success(Object obj, Object... args) {

        final Result<List<SearchBean>> beanResult = (Result<List<SearchBean>>) obj;
        mRecycler.loadMoreComplete();
        mRecycler.refreshComplete();
        if (beanResult.getMessage().equals("网络异常"))
            return;

        if (presenter.getPage()==1){
            adapter.clearList();
        }

        adapter.addList(beanResult.getResult());

        adapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String msg) {

    }
}
