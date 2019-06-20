package com.bw.weidushop.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.presenter.RegisterPresenter;
import com.bw.weidushop.util.MD5Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * com.baway.rxretrofitmvpdemo
 *
 * @author 李宁康
 * @date 2019 2019/05/15 16:22
 */
public class RegisterActivity extends BaseActivity implements RequestDataInterface {
    @BindView(R.id.phoneh)
    EditText phoneh;
    @BindView(R.id.yanzheng)
    EditText yanzheng;
    @BindView(R.id.yanzhengma)
    TextView yanzhengma;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye_true)
    CheckBox eye_true;

    @BindView(R.id.goLogin)
    TextView goLogin;
    @BindView(R.id.regist)
    Button regist;
    private RegisterPresenter presenter;
    private String phone;
    private String md5pwd;

    @Override
    protected void initView() {

        presenter = new RegisterPresenter(this);
    }

    @Override
    protected int getView() {
        return R.layout.zhuce_ui;
    }



    @OnClick({ R.id.goLogin, R.id.regist})
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;

            case R.id.goLogin:

                finish();
                break;
            case R.id.regist:

                phone = phoneh.getText().toString();
                md5pwd = MD5Utils.md5(pwd.getText().toString());
                presenter.requestData(phone, md5pwd);
                break;
        }
    }

    @Override
    public void success(Object obj, Object... args) {
         Result result = (Result) obj;
        Toast.makeText(this, "" + result.getMessage(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
