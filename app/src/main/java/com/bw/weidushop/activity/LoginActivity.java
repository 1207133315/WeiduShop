package com.bw.weidushop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.weidushop.R;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.dao.DaoMaster;
import com.bw.weidushop.dao.UserDao;
import com.bw.weidushop.presenter.LoginPresenter;
import com.bw.weidushop.util.MD5Utils;
import com.bw.weidushop.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * com.baway.rxretrofitmvpdemo
 *
 * @author 李宁康
 * @date 2019 2019/05/15 16:21
 */
public class LoginActivity extends BaseActivity implements RequestDataInterface {
    @BindView(R.id.phoneh)
    EditText phoneh;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye_true)
    CheckBox eye_true;
    @BindView(R.id.isChecked)
    CheckBox isChecked;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter presenter;
    private String phone;
    private String md5pwd;
    private SharedPreferences sharedPreferences;
    private String sp_phone;
    private String sp_pwd;
    private UserDao userDao;
    private String s;


  /*  @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        List<User> users=null;
         users= userDao.loadAll();
       if (!users.isEmpty()){
           for (int i = 0; i <users.size() ; i++) {
               if (users.get(i).isLogin==1){
                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   intent.putExtra("sessionId",users.get(i).getSessionId());
                   startActivity(intent);
                   finish();

                   break;

               }
           }
           return;
       }
        super.onCreate(savedInstanceState);
    }*/

    @Override
    protected void initView() {
        userDao = DaoMaster.newDevSession(this, UserDao.TABLENAME).getUserDao();
        presenter = new LoginPresenter(this);
        //获取sp对象
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        //取值
        boolean flag = sharedPreferences.getBoolean("flag", false);
        //设置复选框选中状态
        isChecked.setChecked(flag);
        if (flag) {
            //获取保存在sp中的用户名和密码
            sp_phone = sharedPreferences.getString("phone", "");
            sp_pwd = sharedPreferences.getString("pwd", "");

            phoneh.setText(sp_phone);
            pwd.setText(sp_pwd);

        }
    }
    @Override
    protected int getView() {
        return R.layout.login_ui;
    }



    @OnClick({R.id.phoneh, R.id.pwd, R.id.eye_true, R.id.isChecked, R.id.register, R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.phoneh:
                break;
            case R.id.pwd:
                break;
            case R.id.eye_true:
                break;
            case R.id.isChecked:
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.login:

                phone = phoneh.getText().toString();
                s = pwd.getText().toString();
                md5pwd = MD5Utils.md5(s);
                Log.d("lnk", "onClick: "+md5pwd);
                if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(md5pwd)){
                    Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!StringUtils.isMobileNO(phone)){
                    Toast.makeText(this, "手机号格式错误,请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.requestData(phone, s);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                String phone = phoneh.getText().toString().trim();
                String psw = pwd.getText().toString().trim();
                if (isChecked.isChecked()){
                    edit.putString("phone",phone);
                    edit.putString("pwd",psw);
                    edit.putBoolean("flag",true);
                }else{
                    edit.clear();
                }
                edit.commit();

                break;
        }
    }

    @Override
    public void success(Object obj, Object... args) {

            Result<User> userResult = (Result<User>) obj;
                User user = userResult.getResult();
                user.password=s;
                Toast.makeText(this, "" + userResult.getMessage(), Toast.LENGTH_SHORT).show();
                user.isLogin=1;
                userDao.insertOrReplace(user);

                 Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

    }


    /*//同步购物车
    public void syncShoppingCart(User user){
        //Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        final AddShopCar1BeanDao car1BeanDao = DaoMaster.newDevSession(LoginActivity.this, AddShopCar1BeanDao.TABLENAME).getAddShopCar1BeanDao();
        final List<AddShopCar1Bean> car1BeanList = car1BeanDao.loadAll();
        final String s = new Gson().toJson(car1BeanList);
        if (car1BeanList.size()>=1){

            new AddShopCarPresenter(new RequestDataInterface.PresenterInterface() {
                @Override
                public void success(Object obj, Object... args) {
                    Result result = (Result) obj;
                    Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void fail(String msg) {

                    Toast.makeText(LoginActivity.this,msg, Toast.LENGTH_SHORT).show();
                }
            }).requestData(String.valueOf(user.getUserId()),user.getSessionId(),s);
        }

    }*/

    @Override
    public void fail(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
