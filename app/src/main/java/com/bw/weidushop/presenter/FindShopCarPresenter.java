package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.bw.weidushop.presenter
 *
 * @author 李宁康
 * @date 2019 2019/06/20 11:21
 */
public class FindShopCarPresenter extends BasePresenter {
    public FindShopCarPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findShopCar((int)args[0],(String)args[1]);
    }
}
