package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.bw.weidushop.presenter
 *
 * @author 李宁康
 * @date 2019 2019/06/22 09:48
 */
public class AddCarPresenter extends BasePresenter {
    public AddCarPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.addShopCar((int)args[0],(String)args[1],(String)args[2]);
    }
}
