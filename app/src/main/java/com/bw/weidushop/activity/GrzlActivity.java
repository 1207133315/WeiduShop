package com.bw.weidushop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.GetDao;
import com.bw.weidushop.presenter.ModifyUserNickPresenter;
import com.bw.weidushop.presenter.ModifyUserPwdPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;


public class GrzlActivity extends BaseActivity {


    @BindView(R.id.wd)
    TextView wd;
    @BindView(R.id.head)
    SimpleDraweeView head;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.diqu)
    EditText diqu;
    private User getuser;

    @Override
    protected int getView() {
        return R.layout.activity_grzl;
    }

    @Override
    protected void initView() {
        getuser = GetDao.getuser();
        final User user = getuser;
        String headPic = user.getHeadPic();
        head.setImageURI(headPic);
        final String nickName = user.getNickName();
        mobile.setText(nickName);
        final String password = user.getPassword();
        diqu.setText(password);
        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){

                }else {
                    new ModifyUserNickPresenter(new RequestDataInterface() {
                        @Override
                        public void success(Object obj, Object... args) {

                        }

                        @Override
                        public void fail(String msg) {

                        }
                    }).requestData((long)user.getUserId(),user.getSessionId(),nickName);
                }
            }
        });
        diqu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==false){
                    new ModifyUserPwdPresenter(new RequestDataInterface() {
                        @Override
                        public void success(Object obj, Object... args) {
                            Result result= (Result) obj;
                            Toast.makeText(GrzlActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(String msg) {

                        }
                    }).requestData((long)getuser.getUserId(),getuser.getSessionId(),getuser.password,password);
                }
            }
        });
    }

}
