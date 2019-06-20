package com.bw.weidushop.dao;

import android.app.Application;

import com.bw.weidushop.application.MyApplication;
import com.bw.weidushop.bean.User;

/**
 * com.bw.weidushop.dao
 *
 * @author 李宁康
 * @date 2019 2019/06/19 21:21
 */
public class GetUser {
    public static User getuser(){
        UserDao userDao = DaoMaster.newDevSession(MyApplication.getInstance(), UserDao.TABLENAME).getUserDao();
         User user = userDao.queryBuilder().where(UserDao.Properties.IsLogin.eq(1)).unique();
         return user;
    }
}
