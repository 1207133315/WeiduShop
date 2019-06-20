package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import io.reactivex.Observable;

/**
 * com.baway.rxretrofitmvpdemo.presenter
 *
 * @author 李宁康
 * @date 2019 2019/05/22 20:46
 */
public class SearchPresenter extends BasePresenter {
    public SearchPresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }
    private int page=1;
    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
        if ((boolean)args[0]){
            page=1;
        }else {
            page++;
        }
        return iRequest.searchBykeyword((String)args[1],String.valueOf(page),(String)args[2]);
    }

    public int getPage() {
        return page;
    }
}
