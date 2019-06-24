package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.bw.weidushop.presenter
 *
 * @author 李宁康
 * @date 2019 2019/06/24 16:24
 */
public class AddressPresenter extends BasePresenter {
    public AddressPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.addressList((long)args[0],(String)args[1]);
    }
}
