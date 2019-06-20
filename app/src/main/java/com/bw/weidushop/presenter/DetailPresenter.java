package com.bw.weidushop.presenter;


import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/22 15:04
 */
public class DetailPresenter extends BasePresenter {
    public DetailPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.detail((String) args[0]);
    }
}
