package com.bw.weidushop.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * com.baway.rxretrofitmvpdemo.bean
 *
 * @author 李宁康
 * @date 2019 2019/05/15 19:28
 */
@Entity
public class User {
  @Id
    private long userId;
    private int sex;
    private String sessionId;
    private String nickName;
    private String string;
    private String headPic;
    public int isLogin;
    public String password;


    @Generated(hash = 1188912805)
    public User(long userId, int sex, String sessionId, String nickName,
            String string, String headPic, int isLogin, String password) {
        this.userId = userId;
        this.sex = sex;
        this.sessionId = sessionId;
        this.nickName = nickName;
        this.string = string;
        this.headPic = headPic;
        this.isLogin = isLogin;
        this.password = password;
    }

    @Generated(hash = 586692638)
    public User() {
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public long getUserId() {

        return userId;
    }

    public int getSex() {
        return sex;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getString() {
        return string;
    }

    public String getHeadPic() {
        return headPic;
    }

    public int getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
