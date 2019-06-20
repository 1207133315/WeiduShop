package com.bw.weidushop.bean;

import java.util.List;

/**
 * com.baway.rxretrofitmvpdemo.bean
 *
 * @author 李宁康
 * @date 2019 2019/05/21 16:29
 */
public class SyBean {
    public Rxxp rxxp;
    public Pzsh pzsh;
    public Mlss mlss;
    public class Rxxp{
        public String name;
        public int id;
        public List<Itme> commodityList;
    }
    public class Pzsh{
        public String name;
        public int id;
        public List<Itme> commodityList;
    }
    public class Mlss{
        public String name;
        public int id;
        public List<Itme> commodityList;
    }

    public class Itme{
        public int commodityId;
        public int price;
        public int saleNum;
        public String commodityName;
        public String masterPic;
    }
}
