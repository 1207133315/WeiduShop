package com.bw.weidushop.bean;

import java.util.List;

public class UserWalletBean {

    /**
     * balance : 99999999
     * detailList : [{"amount":2,"createTime":1542476199000}]
     */

    private double balance;
    private List<DetailListBean> detailList;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {

        private int amount;
        private long consumerTime;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getConsumerTime() {
            return consumerTime;
        }

        public void setConsumerTime(long consumerTime) {
            this.consumerTime = consumerTime;
        }

        public DetailListBean(int amount, long consumerTime) {
            this.amount = amount;
            this.consumerTime = consumerTime;
        }
    }
}
