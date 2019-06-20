package com.bw.weidushop.bean;

/**
 * com.baway.retrofitdemo
 *
 * @author 李宁康
 * @date 2019 2019/05/13 16:42
 */
public class Goods {
    private int commodityId;
    private int price;
    private int saleNum;
    private String commodityName;
    private String masterPic;
    public boolean goodsCheck;

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public int getCommodityId() {

        return commodityId;
    }

    public int getPrice() {
        return price;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }
}
