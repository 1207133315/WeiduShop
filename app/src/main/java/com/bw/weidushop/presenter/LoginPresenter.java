package com.bw.weidushop.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/15 17:16
 */
public class LoginPresenter extends BasePresenter {
    public LoginPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.login((String) args[0], (String) args[1]);
    }

    public void savePwd(Context context, String phone, String pwd) {
        //获取sp对象
        final SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = config.edit();
        //取值
        boolean flag = config.getBoolean("flag", false);



    }


}
