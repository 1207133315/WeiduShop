package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.bw.weidushop.presenter
 *
 * @author 李宁康
 * @date 2019 2019/06/24 18:27
 */
public class DingDanPresenter extends BasePresenter {
    public DingDanPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.createOrder((int)args[0],(String)args[1],(String)args[2],(double)args[3],(int)args[4]);
    }
}
