package com.bw.weidushop.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * com.bw.weidushop.bean
 *
 * @author 李宁康
 * @date 2019 2019/06/21 20:39
 */
@Entity
public class AddCarData {
    @Id
    public long commodityId;
    public int count;

    public AddCarData(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Generated(hash = 129192429)
    public AddCarData(long commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Generated(hash = 1322147097)
    public AddCarData() {
    }

    public long getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
