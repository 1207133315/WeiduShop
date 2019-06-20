package com.bw.weidushop.presenter;


import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/23 20:05
 */
public class UnDianZanPresenter extends BasePresenter {
    public UnDianZanPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.Undianzan((String)args[0],(String)args[1],(String)args[2]);
    }
}
