package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

public class AddReceiveAddressPresenter extends BasePresenter {
    public AddReceiveAddressPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.addAddress((int)args[0],(String)args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5]);
    }
}
