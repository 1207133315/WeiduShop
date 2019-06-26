package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

public class AddressPresenter extends BasePresenter {
    public AddressPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.addressList((String)args[0],(String)args[1]);
        return iRequest.addressList((long)args[0],(String)args[1]);
    }
}
