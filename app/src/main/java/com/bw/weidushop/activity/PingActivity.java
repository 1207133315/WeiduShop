package com.bw.weidushop.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.Orderlist;

import java.io.File;


public class PingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView order_img;
    private TextView order_name;
    private TextView order_price;
    private EditText pinglun_text;
    private ImageView pinglun_pai;
    private RadioButton pinglun_radio;
    private Button pinglun_btn;
    private LinearLayout linear;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);
        initView();
        Intent intent = getIntent();
        Orderlist.OrderListBean.DetailListBean bean = (Orderlist.OrderListBean.DetailListBean) intent.getSerializableExtra("bean");
        String commodityPic = bean.getCommodityPic();
        String commodityPic1 = bean.getCommodityPic();
        String[] split1 = commodityPic1.split(",");
        Glide.with(this).load(split1[0]).into(order_img);
        order_name.setText(bean.getCommodityName());
        order_price.setText("￥" + bean.getCommodityPrice() + "0");
    }

    private void initView() {
        order_img = (ImageView) findViewById(R.id.order_img);
        order_name = (TextView) findViewById(R.id.order_name);
        order_price = (TextView) findViewById(R.id.order_price);
        pinglun_text = (EditText) findViewById(R.id.pinglun_text);
        pinglun_pai = (ImageView) findViewById(R.id.pinglun_pai);
        pinglun_pai.setOnClickListener(this);
        pinglun_radio = (RadioButton) findViewById(R.id.pinglun_radio);
        pinglun_btn = (Button) findViewById(R.id.pinglun_btn);
        pinglun_btn.setOnClickListener(this);
        linear = (LinearLayout) findViewById(R.id.linear);
        linear.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pinglun_pai:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/head"+i+".jpg")));
                startActivityForResult(intent, 100);
                break;
            case R.id.pinglun_btn:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 100 && resultCode == RESULT_OK){
            ImageView imageView = new ImageView(PingActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);
            params.setMargins(0,0,35,0);
            imageView.setLayoutParams(params);
            linear.addView(imageView);
//            Glide.with(PingActivity.this)
//                    .load(Environment.getExternalStorageDirectory()+"/head"+i+".jpg")
//                    .asBitmap()
//                    .transform(new CenterCrop(PingActivity.this), new RoundedCornersTransformation(PingActivity.this, 10, 0, RoundedCornersTransformation.CornerType.ALL))
//                    .into(imageView);
            i++;
        }
    }
}
