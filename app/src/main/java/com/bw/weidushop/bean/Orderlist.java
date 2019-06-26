package com.bw.weidushop.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class Orderlist {

    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":1,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/5.jpg","commodityPrice":278,"orderDetailId":5616}],"expressCompName":"京东快递","expressSn":"1001","orderId":"20190522205223816606","orderStatus":1,"payAmount":278,"payMethod":1,"userId":606}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }


    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Orderlist{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", orderList=" + orderList +
                '}';
    }

    public static class OrderListBean implements MultiItemEntity,Serializable{
        /**
         * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","commodityPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/5.jpg","commodityPrice":278,"orderDetailId":5616}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 20190522205223816606
         * orderStatus : 1
         * payAmount : 278
         * payMethod : 1
         * userId : 606
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private double payAmount;
        private int payMethod;
        private int userId;
        private int itemType;
        private List<DetailListBean> detailList;
        public OrderListBean(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return orderStatus;
        }
        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public double getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(double payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList(){
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        @Override
        public String toString() {
            return "OrderListBean{" +
                    "expressCompName='" + expressCompName + '\'' +
                    ", expressSn='" + expressSn + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", orderStatus=" + orderStatus +
                    ", payAmount=" + payAmount +
                    ", payMethod=" + payMethod +
                    ", userId=" + userId +
                    ", detailList=" + detailList +
                    '}';
        }



        public static class DetailListBean implements MultiItemEntity, Serializable {
            /**
             * commentStatus : 1
             * commodityCount : 1
             * commodityId : 29
             * commodityName : 秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲
             * commodityPic : http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/5.jpg
             * commodityPrice : 278
             * orderDetailId : 5616
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private double commodityPrice;
            private int orderDetailId;
            private int itemType;

            public DetailListBean(int itemType) {
                this.itemType = itemType;
            }

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public double getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(double commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }

            @Override
            public String toString() {
                return "DetailListBean{" +
                        "commentStatus=" + commentStatus +
                        ", commodityCount=" + commodityCount +
                        ", commodityId=" + commodityId +
                        ", commodityName='" + commodityName + '\'' +
                        ", commodityPic='" + commodityPic + '\'' +
                        ", commodityPrice=" + commodityPrice +
                        ", orderDetailId=" + orderDetailId +
                        '}';
            }

            @Override
            public int getItemType() {
                return itemType;
            }
            public void setItemType(int i) {
               itemType=i;
            }
        }
    }
}
