package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bw.weidushop.adapter
 *
 * @author 李宁康
 * @date 2019 2019/06/25 10:25
 */
public class PopAddressAdapter extends RecyclerView.Adapter<PopAddressAdapter.ViewHolder> {
    private List<AddressBean> list=new ArrayList<>();
    private Context context;

    public PopAddressAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.address_pop_itme,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         final AddressBean addressBean = list.get(i);
         viewHolder.name.setText(addressBean.realName);
         viewHolder.phone.setText(addressBean.phone);
         viewHolder.address.setText(addressBean.address);
         viewHolder.xuanze.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dataCall.xuanze(addressBean);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<AddressBean> result) {
        if (result!=null){
            list.addAll(result);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.xuanze)
        TextView xuanze;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private DataCall dataCall;

    public void setDataCall(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    public interface DataCall{
        void xuanze(AddressBean addressBean);
    }
}
