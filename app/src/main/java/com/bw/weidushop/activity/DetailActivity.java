package com.bw.weidushop.activity;


import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.weidushop.R;
import com.bw.weidushop.bean.AddCarData;
import com.bw.weidushop.bean.DetailBean;
import com.bw.weidushop.bean.ImageBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.AddCarDataDao;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.AddCarPresenter;
import com.bw.weidushop.presenter.DetailPresenter;
import com.bw.weidushop.view.AddCart;
import com.bw.weidushop.view.Buy;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements RequestDataInterface {


    @BindView(R.id.shou_back)
    ImageView mShouBack;
    @BindView(R.id.btn_sp)
    RadioButton mBtnSp;
    @BindView(R.id.btn_xq)
    RadioButton mBtnXq;
    @BindView(R.id.btn_pj)
    RadioButton mBtnPj;
    @BindView(R.id.banner)
    XBanner mBanner;
    @BindView(R.id.details_price)
    TextView mDetailsPrice;
    @BindView(R.id.details_Salesvolume)
    TextView mDetailsSalesvolume;
    @BindView(R.id.details_describe)
    TextView mDetailsDescribe;
    @BindView(R.id.webview)
    WebView mWebview;

    @BindView(R.id.buy)
    Buy mBuy;
    @BindView(R.id.cart)
    AddCart mCart;
    private int id;
    private boolean aa;
    private List<ImageBean> list = new ArrayList<>();
    private String js;
    private User user;
    private AddCarDataDao shopCarDao;

    @Override
    protected int getView() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {
        user = GetDao.getuser();
        shopCarDao = GetDao.getShopCarDao();
        id = getIntent().getIntExtra("id", 1);
        DetailPresenter presenter = new DetailPresenter(this);
        presenter.requestData((int)user.getUserId(),user.getSessionId(),id);


    }

    private void setWebView(Result<DetailBean> beanResult) {
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setJavaScriptEnabled(true);
        // 找到img标签
// 逐个改变
// 宽度改为100%
        js = "<script type=text/javascript>" +
                  "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                  "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                  "imgs[i].style.width = '100%';" +  // 宽度改为100%
                  "imgs[i].style.height = 'auto';" +
                  "}" +
                  "</script>";
        mWebview.loadDataWithBaseURL(null, beanResult.getResult().details + js, "text/html", "utf-8", null);

    }

    private void setData(Result<DetailBean> beanResult) {
        mDetailsPrice.setText("￥:"+beanResult.getResult().price);
        mDetailsDescribe.setText(beanResult.getResult().categoryName);
        mDetailsSalesvolume.setText("已售"+beanResult.getResult().commentNum+"件");

    }

    //展示banner
    public void showBanner(Result<DetailBean> beanResult) {
        String picture = beanResult.getResult().picture;
        final String[] split = picture.split(",");
        if (list.size() < 1) {
            for (int i = 0; i < split.length; i++) {
                ImageBean imageBean = new ImageBean();
                imageBean.imgUrl = split[i];
                list.add(imageBean);
            }
        }
        mBanner.setBannerData(list);
        mBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(DetailActivity.this)
                        .load(((ImageBean)model).getXBannerUrl())
                        .into((ImageView) view);
            }
        });
    }






    @OnClick({R.id.shou_back, R.id.btn_sp, R.id.btn_xq, R.id.btn_pj, R.id.banner, R.id.details_price, R.id.details_Salesvolume, R.id.details_describe, R.id.webview,  R.id.buy, R.id.cart})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.shou_back:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            case R.id.btn_sp:
                break;
            case R.id.btn_xq:
                break;
            case R.id.btn_pj:
                break;
            case R.id.banner:
                break;
            case R.id.details_price:
                break;
            case R.id.details_Salesvolume:
                break;
            case R.id.details_describe:
                break;
            case R.id.webview:
                break;

            case R.id.buy:
                break;
            case R.id.cart:
                addCar();

                break;
        }
    }
    //加入购物车
    private void addCar() {
        isExits();
         List<AddCarData> addCarData = shopCarDao.loadAll();
         String s = new Gson().toJson(addCarData);
         new AddCarPresenter(new RequestDataInterface() {
             @Override
             public void success(Object obj, Object... args) {
                  Result result = (Result) obj;
                 Toast.makeText(DetailActivity.this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
             }

             @Override
             public void fail(String msg) {
                 Toast.makeText(DetailActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
             }
         }).requestData((int)user.getUserId(),user.getSessionId(),s);

    }

    //判断加入时是否存在
    public void  isExits(){
        List<AddCarData> carDataList = shopCarDao.loadAll();
        for (int i = 0; i < carDataList.size(); i++) {
            if (carDataList.get(i).commodityId == id) {
                //如果存在的话就取出来这个对象,然后把它的数量加1,再添加到数据库中把原来的替换掉
                //把aa这个变量置为true
                 AddCarData addCarData = carDataList.get(i);
                addCarData.count++;
                shopCarDao.insertOrReplaceInTx(addCarData);
                aa = true;
            }
        }
        //判断如果aa==false的话就代表要添加购物车的这条数据购物车中没有,就创建一个对象,count=1,再把这个对象添加进去
        if (aa == false) {
            final AddCarData addCarData = new AddCarData(id, 1);
            shopCarDao.insertOrReplaceInTx(addCarData);
            aa=false;
        }


    }
    @Override
    public void success(Object obj, Object... args) {
      Result<DetailBean>  beanResult=(Result<DetailBean>)obj;
        //展示轮播图
        showBanner(beanResult);
        //设置数据
        setData(beanResult);
        //加载webView
        setWebView(beanResult);
    }

    @Override
    public void fail(String msg) {

    }
}
