package com.bw.weidushop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.QuanZiBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.DianZanPresenter;
import com.bw.weidushop.presenter.UnDianZanPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.baway.rxretrofitmvpdemo.adapter
 *
 * @author 李宁康
 * @date 2019 2019/05/23 16:34
 */
public class QuanZiAdapter extends RecyclerView.Adapter<QuanZiAdapter.ViewHolder> {
    private Context context;
    private List<QuanZiBean> list=new ArrayList<>();

    public QuanZiAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.qz_itme_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final QuanZiBean quanZiBean = list.get(i);
        Glide.with(context).load(quanZiBean.headPic)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(viewHolder.title);
        viewHolder.data.setText(quanZiBean.createTime+"");
        viewHolder.name.setText(quanZiBean.nickName);
        final String[] images = quanZiBean.image.split(",");
        viewHolder.childAdapter.clear();
        viewHolder.childAdapter.addList(Arrays.asList(images));
        if (images.length == 1) {//只有一张图片
            viewHolder.gridLayoutManager.setSpanCount(1);
        } else if (images.length == 2 || images.length == 4) {
            viewHolder.gridLayoutManager.setSpanCount(2);
        } else {
            viewHolder.gridLayoutManager.setSpanCount(3);
    }
        viewHolder.dianzanNum.setText(quanZiBean.greatNum+"");
        viewHolder.recyclerView.setLayoutManager(viewHolder.gridLayoutManager);
        viewHolder.childAdapter.notifyDataSetChanged();
        viewHolder.dianzan.setTag(quanZiBean);
        viewHolder.message.setText(quanZiBean.content);
        viewHolder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuanZiBean bean = (QuanZiBean) view.getTag();
                boolean checked = ((CheckBox) view).isChecked();
                bean.check=checked;
                if (checked==true){
                    dianZan(bean);
                    bean.greatNum++;
                }else {
                    unDianZan(bean);
                    bean.greatNum--;
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.dianzanNum.setText(quanZiBean.greatNum+"");
        viewHolder.dianzan.setChecked(quanZiBean.check);
        viewHolder.dianzanNum.setTag(quanZiBean);


    }

    //取消点赞
    public void unDianZan(QuanZiBean bean){
        User user = GetDao.getuser();
         new UnDianZanPresenter(new RequestDataInterface() {
             @Override
             public void success(Object obj, Object... args) {
                 final Result result = (Result) obj;
                // Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
             }

             @Override
             public void fail(String msg) {
                // Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
             }
         }).requestData(user.getUserId(),user.getSessionId(),bean.id+"");
    }

    //请求点赞
    public void dianZan(QuanZiBean bean){
         User user = GetDao.getuser();
        new DianZanPresenter(new RequestDataInterface() {
            @Override
            public void success(Object obj, Object... args) {
                final Result result = (Result) obj;
                //Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String msg) {
                //Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
            }
        }).requestData(user.getUserId(),user.getSessionId(),bean.id+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void addList(List<QuanZiBean> result) {
        if (result!=null){
            list.addAll(result);
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        ImageView title;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.data)
        TextView data;
        @BindView(R.id.child_qz_itme)
        RecyclerView recyclerView;
        @BindView(R.id.dianzan)
        CheckBox dianzan;
        @BindView(R.id.dianzanNum)
        TextView dianzanNum;

         QuanziChildAdapter childAdapter;
         GridLayoutManager gridLayoutManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            gridLayoutManager = new GridLayoutManager(context, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            childAdapter = new QuanziChildAdapter(itemView.getContext());
            recyclerView.setAdapter(childAdapter);
        }
    }

    private DianZan dianZan;

    public void setDianZan(DianZan dianZan) {
        this.dianZan = dianZan;
    }

    public interface DianZan{
        void dianzan(View view, int i, boolean checked);
    }
}
