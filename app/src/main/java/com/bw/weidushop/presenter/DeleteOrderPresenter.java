package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

public class DeleteOrderPresenter extends BasePresenter {

    public DeleteOrderPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.deleteOrder((int)args[0],(String)args[1],(String)args[2]);
    }
}
