package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bw.weidushop.adapter
 *
 * @author 李宁康
 * @date 2019 2019/06/20 11:02
 */
public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.ViewHolder> {
    private Context context;
    private List<ShopCarBean> list=new ArrayList<>();

    public ShopCarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.gwc_itme,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.checkBox.setText(list.get(i).categoryName);
        viewHolder.adapter.addList(list.get(i).shoppingCartList);
        viewHolder.recyclerView.setAdapter(viewHolder.adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<ShopCarBean> result) {
        if (result!=null){
            list.addAll(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check)
        CheckBox checkBox;
        @BindView(R.id.child_recycler)
        RecyclerView recyclerView;
        ShopCarChildAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            adapter = new ShopCarChildAdapter(context);
        }
    }
}
