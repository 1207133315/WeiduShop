package com.bw.weidushop.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;


import com.bw.weidushop.R;
import com.bw.weidushop.activity.PingActivity;
import com.bw.weidushop.bean.Orderlist;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderlistAdapter extends BaseMultiItemQuickAdapter<Orderlist.OrderListBean,BaseViewHolder> {


    private String format1;


    public OrderlistAdapter(List<Orderlist.OrderListBean> data) {
        super(data);
        addItemType(1,R.layout.orderlist);
        addItemType(2,R.layout.orderlistdaishouhuo);
        addItemType(3,R.layout.orderlistdaipingjia2);
        addItemType(9,R.layout.orderlistdaipingjia2);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Orderlist.OrderListBean item) {
        String orderId1 = item.getOrderId();
        String substring1 = orderId1.substring(0, 8);
        substring1=substring1.substring(0,4)+"-"+substring1.substring(4,6)+"-"+substring1.substring(6,8);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(substring1);
            format1 = format.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (Orderlist.OrderListBean.DetailListBean detailListBean : item.getDetailList()) {
            detailListBean.setItemType(item.getOrderStatus());
        }
        switch (helper.getItemViewType()){

            case 1:
                helper.setText(R.id.ddh_content,"WD"+item.getOrderId().substring(0,15));
                helper.setText(R.id.order_date,format1);
                helper.setText(R.id.order_content,Html.fromHtml("共<font color='#FF0000'>"+item.getDetailList().size()+"</font>件商品，需付款<font color='#FF0000'>"+item.getPayAmount()+"0</font>元"));
                helper.addOnClickListener(R.id.qzf);
                helper.addOnClickListener(R.id.qxdd);
                Orderlist2Adapter adapter = new Orderlist2Adapter(item.getDetailList(),1);
                RecyclerView view = helper.getView(R.id.recyler_orderlist2);
                view.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
                view.setAdapter(adapter);
                break;
            case 2:
                helper.setText(R.id.ddh_content,"WD"+item.getOrderId().substring(0,15));
                helper.setText(R.id.order_date,format1);
                Orderlist2Adapter adapter1 = new Orderlist2Adapter(item.getDetailList(),2);
                RecyclerView view1 = helper.getView(R.id.recyler_orderlist2);
                view1.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
                view1.setAdapter(adapter1);
                helper.setText(R.id.order_paijiangongsi1,item.getExpressCompName());
                helper.setText(R.id.order_kuaididanhao1,item.getOrderId().substring(9));
                helper.addOnClickListener(R.id.qrsh);
                break;
            case 3:
                helper.setText(R.id.ddh_content,"WD"+item.getOrderId().substring(0,15));
                helper.setText(R.id.order_date,format1);
                Orderlist2Adapter adapter2 = new Orderlist2Adapter(item.getDetailList(),3);
                RecyclerView view2 = helper.getView(R.id.recyler_orderlist2);
                adapter2.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent=new Intent(mContext,PingActivity.class);
                        Orderlist.OrderListBean.DetailListBean bean = item.getDetailList().get(position);
                        intent.putExtra("orderId",item.getOrderId());
                        intent.putExtra("bean",bean);
                        mContext.startActivity(intent);
                    }
                });
                view2.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
                view2.setAdapter(adapter2);
                break;
            case 9:
                helper.setText(R.id.ddh_content,"WD"+item.getOrderId().substring(0,15));
                helper.setText(R.id.order_date,format1);
                Orderlist2Adapter adapter9 = new Orderlist2Adapter(item.getDetailList(),9);
                RecyclerView view9 = helper.getView(R.id.recyler_orderlist2);
                view9.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
                view9.setAdapter(adapter9);
                break;
        }
    }
}
