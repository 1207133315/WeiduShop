package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/23 15:52
 */
public class QuanZiPresenter extends BasePresenter {
    private int page=1;
    public QuanZiPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        if ((boolean)args[0]){
            page=1;
        }else {
            page++;
        }
        return iRequest.showQuanzi((String)args[1],(String)args[2],String.valueOf(page),(String)args[3]);
    }

    public int getPage() {
        return page;
    }
}
