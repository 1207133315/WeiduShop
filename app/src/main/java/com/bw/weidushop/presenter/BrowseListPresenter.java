package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

public class BrowseListPresenter extends BasePresenter {
    public BrowseListPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.browseList((int)args[0],(String)args[1],(int)args[2],(int)args[3]);
    }
}
