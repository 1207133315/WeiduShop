package com.bw.weidushop.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.Orderlist;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.AddCommentPresenter;
import com.bw.weidushop.presenter.ReleaseCirclePresenter;
import com.bw.weidushop.util.GlideLoader;
import com.bw.weidushop.util.PermissionsUtils;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageSelector;
import com.jaiky.imagespickers.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PingActivity extends AppCompatActivity implements View.OnClickListener, RequestDataInterface {

    private ImageView order_img;
    private TextView order_name;
    private TextView order_price;
    private EditText pinglun_text;
    private ImageView pinglun_pai;
    private CheckBox pinglun_radio;
    private Button pinglun_btn;
    private RelativeLayout linear;
    int i=0;
    private ReleaseCirclePresenter presenter;
    private User user;
    private Orderlist.OrderListBean.DetailListBean bean;
    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //创建监听权限的接口对象
    PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            //Toast.makeText(MessiageActivity.this, "权限通过，可以做其他事情!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void forbitPermissons() {
//            finish();
            // Toast.makeText(MessiageActivity.this, "权限不通过!", Toast.LENGTH_SHORT).show();
        }
    };
    private ProgressDialog progressDialog;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);
        initView();
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        bean = (Orderlist.OrderListBean.DetailListBean) intent.getSerializableExtra("bean");
        String commodityPic = bean.getCommodityPic();
        String commodityPic1 = bean.getCommodityPic();
        String[] split1 = commodityPic1.split(",");
        Glide.with(this).load(split1[0]).into(order_img);
        order_name.setText(bean.getCommodityName());
        order_price.setText("￥" + bean.getCommodityPrice() + "0");
    }

    private void initView() {
        user = GetDao.getuser();
        order_img = (ImageView) findViewById(R.id.order_img);
        order_name = (TextView) findViewById(R.id.order_name);
        order_price = (TextView) findViewById(R.id.order_price);
        pinglun_text = (EditText) findViewById(R.id.pinglun_text);
        pinglun_pai = (ImageView) findViewById(R.id.pinglun_pai);
        pinglun_pai.setOnClickListener(this);
        pinglun_radio = (CheckBox) findViewById(R.id.pinglun_radio);
        pinglun_btn = (Button) findViewById(R.id.pinglun_btn);
        pinglun_btn.setOnClickListener(this);
        linear = findViewById(R.id.ll);
        linear.setOnClickListener(this);
        presenter = new ReleaseCirclePresenter(this);
        PermissionsUtils.showSystemSetting = false;//是否支持显示系统设置权限设置窗口跳转
        //这里的this不是上下文，是Activity对象！
        PermissionsUtils.getInstance().chekPermissions(this, permissions, permissionsResult);
    }
    private CharSequence [] itme={"相机","相册","取消"};
    private ImageConfig imageConfig;
    private ArrayList<String> paths=new ArrayList<>();
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pinglun_pai:
                new AlertDialog.Builder(PingActivity.this)
                        .setItems(itme, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i==0){
                                    imageConfig = new ImageConfig.Builder(
                                            new GlideLoader())
                                            .steepToolBarColor(getResources().getColor(R.color.titleBlue))
                                            .titleBgColor(getResources().getColor(R.color.titleBlue))
                                            .titleSubmitTextColor(getResources().getColor(R.color.white))
                                            .titleTextColor(getResources().getColor(R.color.white))
                                            // 开启多选   （默认为多选）
                                            .mutiSelect()
                                            // 多选时的最大数量   （默认 9 张）
                                            .mutiSelectMaxSize(9)
                                            // 设置图片显示容器，参数：（1、显示容器，2、每行显示数量（建议不要超过8个），是否可删除）
                                            .setContainer(linear, 4, true)
                                            .setContainer(linear)
                                            // 已选择的图片路径
                                            .pathList(paths)
                                            // 拍照后存放的图片路径（默认 /temp/picture）
                                            .filePath("/temp")
                                            // 开启拍照功能 （默认关闭）
                                            .showCamera()
                                            .requestCode(200)
                                            .build();
                                    ImageSelector.open(PingActivity.this, imageConfig);
                                } else if (i==1){

                                    imageConfig = new ImageConfig.Builder(
                                            new GlideLoader())
                                            .steepToolBarColor(getResources().getColor(R.color.titleBlue))
                                            .titleBgColor(getResources().getColor(R.color.titleBlue))
                                            .titleSubmitTextColor(getResources().getColor(R.color.white))
                                            .titleTextColor(getResources().getColor(R.color.white))
                                            // 开启多选   （默认为多选）
                                            .mutiSelect()
                                            // 多选时的最大数量   （默认 9 张）
                                            .mutiSelectMaxSize(9)
                                            // 设置图片显示容器，参数：（1、显示容器，2、每行显示数量（建议不要超过8个），是否可删除）
                                            .setContainer(linear, 4, true)
                                            // 已选择的图片路径
                                            .pathList(paths)
                                            // 拍照后存放的图片路径（默认 /temp/picture）
                                            .filePath("/temp")
                                            // 开启拍照功能 （默认关闭）
//                                    .showCamera()
                                            .requestCode(200)
                                            .build();
                                    ImageSelector.open(PingActivity.this, imageConfig);
                                }else {

                                }
                            }
                        })
                        .show();
                break;
            case R.id.pinglun_btn:
                if (pinglun_radio.isChecked()){
                    presenter.requestData(user.getUserId(),user.getSessionId(),bean.getCommodityId(),pinglun_text.getText().toString(),files);
                    ping();
                    progressDialog = new ProgressDialog(PingActivity.this);
                    progressDialog.setTitle("提示");
                    progressDialog.setMessage("发布中...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }else {
                    ping();
                    progressDialog = new ProgressDialog(PingActivity.this);
                    progressDialog.setTitle("提示");
                    progressDialog.setMessage("发布中...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                }

                break;
        }
    }
    //发表评论
    public void ping(){
            new AddCommentPresenter(new RequestDataInterface() {
                @Override
                public void success(Object obj, Object... args) {
                    final Result result = (Result) obj;
                    Toast.makeText(PingActivity.this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }

                @Override
                public void fail(String msg) {
                    Toast.makeText(PingActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).requestData(user.getUserId(),user.getSessionId(),bean.getCommodityId(),orderId+"",pinglun_text.getText().toString(),files);
    }

    private ArrayList<File> files=new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 200:

                    List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
                    paths.clear();
                    paths.addAll(pathList);

                    for (int i = 0; i < paths.size(); i++) {
                        File file = new File(paths.get(i));
                        files.add(file);
                    }

                    break;
            }
        } else {
            Toast.makeText(PingActivity.this, "data为空 ！", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    @Override
    public void success(Object obj, Object... args) {
        final Result result = (Result) obj;
        Toast.makeText(this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        finish();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }
}
