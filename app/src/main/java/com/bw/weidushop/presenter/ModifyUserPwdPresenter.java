package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

public class ModifyUserPwdPresenter extends BasePresenter {
    public ModifyUserPwdPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.modifyUserPwd((long)args[0],(String)args[1],(String)args[2],(String)args[3]);
    }
}
