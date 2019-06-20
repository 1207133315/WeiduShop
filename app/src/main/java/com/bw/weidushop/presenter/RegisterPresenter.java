package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/15 17:16
 */
public class RegisterPresenter extends BasePresenter {
    public RegisterPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.resgiter((String)args[0],(String)args[1]);
    }
}
