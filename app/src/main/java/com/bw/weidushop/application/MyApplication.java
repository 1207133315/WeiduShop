package com.bw.weidushop.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * com.bw.weidushop.application
 *
 * @author 李宁康
 * @date 2019 2019/06/19 21:24
 */
public class MyApplication extends Application {
    //建立一个静态私有变量用于存储上下文实例
    private static MyApplication instance;

    //建立一个静态方法，用于返回所需要的上下文实例
    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //将应用程序本身的上下文实例赋值给instance变量
        this.instance = MyApplication.this;
        Fresco.initialize(this);
    }


}
