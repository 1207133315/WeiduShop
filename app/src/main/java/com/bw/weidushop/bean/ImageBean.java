package com.bw.weidushop.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * com.baway.rxretrofitmvpdemo.bean
 *
 * @author 李宁康
 * @date 2019 2019/05/21 20:56
 */
public class ImageBean extends SimpleBannerInfo {
    public String imgUrl;
    @Override
    public Object getXBannerUrl() {
        return imgUrl;
    }
}
