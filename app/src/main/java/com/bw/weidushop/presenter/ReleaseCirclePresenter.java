package com.bw.weidushop.presenter;

import com.bw.weidushop.core.RequestDataInterface;
import com.bw.weidushop.model.IRequest;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * com.bw.weidushop.presenter
 *
 * @author 李宁康
 * @date 2019 2019/06/25 21:01
 */
public class ReleaseCirclePresenter extends BasePresenter {
    public ReleaseCirclePresenter(RequestDataInterface presenterInterface) {
        super(presenterInterface);
    }

    @Override
    public Observable getModel(IRequest iRequest, Object... args) {
         int id = (int) args[2];
         String context = (String) args[3];
         ArrayList<File> files = (ArrayList<File>) args[4];
         MultipartBody.Builder builder = new MultipartBody.Builder();
         builder.setType(MultipartBody.FORM);
         builder.addFormDataPart("commodityId",id+"");
         builder.addFormDataPart("content",context);
        for (int i = 0; i < files.size(); i++) {
            builder.addFormDataPart("image",files.get(i).getName(),RequestBody.create(MediaType.parse("multipart/octet-stream"),files.get(i)));
        }

        return iRequest.releaseCircle((long)args[0],(String)args[1],builder.build());
    }
}
