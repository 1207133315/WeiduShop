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
import com.bw.weidushop.bean.ShopCarChildBean;

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ShopCarBean shopCarBean = list.get(i);
        viewHolder.checkBox.setText(list.get(i).categoryName);
        viewHolder.adapter.clear();
        viewHolder.adapter.addList(shopCarBean.shoppingCartList);
        viewHolder.adapter.notifyDataSetChanged();
        viewHolder.recyclerView.setAdapter(viewHolder.adapter);
        viewHolder.checkBox.setChecked(shopCarBean.isChecked);
        viewHolder.checkBox.setTag(shopCarBean);
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShopCarChildBean> shoppingCartList = list.get(i).shoppingCartList;
                ShopCarBean viewTag = (ShopCarBean) view.getTag();
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()){
                    viewTag.isChecked=true;
                    for (int j = 0; j <shoppingCartList.size(); j++) {
                        shoppingCartList.get(j).isChecked=checkBox.isChecked();
                    }
                   viewHolder.adapter. notifyDataSetChanged();
                }else {
                    viewTag.isChecked=false;
                    for (int j = 0; j <shoppingCartList.size(); j++) {
                        shoppingCartList.get(j).isChecked=checkBox.isChecked();
                    }
                    viewHolder.adapter. notifyDataSetChanged();
                }
                priceAll();



            }
        });

        //判断二级条目中是否全部选中,如果全选中 则让一级的Checkbox选中,否则相反
        viewHolder.adapter.setDataCall(new ShopCarChildAdapter.DataCall() {
            @Override
            public void dataCall(int a) {
                if (a>=list.get(i).shoppingCartList.size()) {
                    list.get(i).isChecked=true;
                }else {
                    list.get(i).isChecked=false;
                }

                priceAll();
            }
        });


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

    //全选反选
    public void checkedAll(boolean isChecked){

        for (int i = 0; i <list.size() ; i++) {
            list.get(i).isChecked=isChecked;
            final ShopCarBean shopCarBean = list.get(i);
            for (int j = 0; j < list.get(i).shoppingCartList.size(); j++) {
              shopCarBean.shoppingCartList.get(j).isChecked =isChecked;

            }
        }
       notifyDataSetChanged();
    }

    //计算价格
    public void priceAll(){
        int a=0;
        int b=0;
        double num = 0.0;
        for (int i = 0; i <list.size() ; i++) {
            final ShopCarBean shopCarBean = list.get(i);
            for (int j = 0; j < list.get(i).shoppingCartList.size(); j++) {
                b++;
                 ShopCarChildBean shopCarChildBean = shopCarBean.shoppingCartList.get(j);
                 if (shopCarChildBean.isChecked) {
                     num += shopCarChildBean.price * shopCarChildBean.count;
                     a++;
                 }

            }

        }
        if (a>=b){
            dataCall.dataCall(true);
        }else {
            dataCall.dataCall(false);
        }
       call.mCall(num,a);
        //a=0;
        notifyDataSetChanged();
    }


    public void clear() {
        list.clear();
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

    private Call call;

    public void setCall(Call call) {
        this.call = call;
    }

    public interface Call{
        void mCall(double a,int b);
    }

    private DataCall dataCall;

    public void setDataCall(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    public interface DataCall{
        void dataCall(boolean a);
    }
}
