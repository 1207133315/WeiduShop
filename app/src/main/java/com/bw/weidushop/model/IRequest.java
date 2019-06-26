package com.bw.weidushop.model;


import com.bw.weidushop.bean.AddressBean;
import com.bw.weidushop.bean.BannerBean;
import com.bw.weidushop.bean.BrowseListBean;
import com.bw.weidushop.bean.DetailBean;
import com.bw.weidushop.bean.Goods;
import com.bw.weidushop.bean.QuanZiBean;
import com.bw.weidushop.bean.Result;
import com.bw.weidushop.bean.SearchBean;
import com.bw.weidushop.bean.ShopCarBean;
import com.bw.weidushop.bean.SyBean;
import com.bw.weidushop.bean.User;
import com.bw.weidushop.bean.UserWalletBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * com.baway.rxretrofitmvpdemo.model
 *
 * @author 李宁康
 * @date 2019 2019/05/14 16:27
 */
public interface IRequest {
    //搜索
    @GET("small/v1/findCommodityByKeyword")
    Observable<Result<List<Goods>>> show(@Query("keyword") String keyword, @Query("page") String page, @Query("count") String count);

    //注册
    @FormUrlEncoded
    @POST("small/user/v1/register")
    Observable<Result> resgiter(@Field("phone") String phone, @Field("pwd") String pwd);

    //登录
    @FormUrlEncoded
    @POST("small/user/v1/login")
    Observable<Result<User>> login(@Field("phone") String phone, @Field("pwd") String pwd);


    //首页列表
    @GET("small/commodity/v1/commodityList")
    Observable<Result<SyBean>> showMain();

    //轮播图
    @GET("small/commodity/v1/bannerShow")
    Observable<Result<List<BannerBean>>> banner();

    //查询购物车
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<Result<List<ShopCarBean>>> findShopCar(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //根据id查详情
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<Result<DetailBean>> detail(
            @Header("userId") int userId,
            @Header("sessionId") String sessionId,
            @Query("commodityId") int commodityId);

    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchBean>>> searchBykeyword(@Query("keyword") String keyword, @Query("page") String page, @Query("count") String count);

    //同步购物车
    @FormUrlEncoded
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<Result> addShopCar(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("data") String data);


    //圈子列表
    @GET("small/circle/v1/findCircleList")
    Observable<Result<List<QuanZiBean>>> showQuanzi(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("page") String page, @Query("count") String count);

    //圈子点赞
    @FormUrlEncoded
    @POST("small/circle/verify/v1/addCircleGreat")
    Observable<Result> dianzan(@Header("userId") long userId, @Header("sessionId") String sessionId, @Field("circleId") String circleId);

    //取消点赞

    @DELETE("small/circle/verify/v1/cancelCircleGreat")
    Observable<Result> Undianzan(@Header("userId") long userId, @Header("sessionId") String sessionId, @Query("circleId") String circleId);


    //我的圈子
    @GET("small/circle/verify/v1/findMyCircleById")
    Observable<Result<List<QuanZiBean>>> myQuanzi(@Header("userId") long userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("page") int page,
                                                  @Query("count") String count);




    //添加订单
    @FormUrlEncoded
    @POST("small/order/verify/v1/createOrder")
    Observable<Result> createOrder(@Header("userId") int userId,
                                   @Header("sessionId") String sessionId,
                                   @Field("orderInfo") String orderInfo,
                                   @Field("totalPrice") double totalPrice,
                                   @Field("addressId") int addressId);

    //发布圈子
    @POST("small/circle/verify/v1/releaseCircle")
    Observable<Result> releaseCircle(@Header("userId") long userId,
                                     @Header("sessionId") String sessionId,
                                     @Body MultipartBody body);

    //发表评论
    @POST("small/commodity/verify/v1/addCommodityComment")
    Observable<Result> addComment(@Header("userId") long userId,
                                  @Header("sessionId") String sessionId,
                                  @Body MultipartBody body);

    Observable<Result<List<AddressBean>>> addressList(@Header("userId") String userId, @Header("sessionId") String sessionId);

    @GET("small/user/verify/v1/findUserWallet")
    Observable<Result<UserWalletBean>> findUserWallet(@Header("userId") int userId,
                                        @Header("sessionId") String sessionid,
                                        @Query("page") int page,
                                        @Query("count") int count);
    @FormUrlEncoded
    @POST("small/user/verify/v1/setDefaultReceiveAddress")
    Observable<Result> setDefaultReceiveAddress(@Header("userId") int userid,
                                                @Header("sessionId") String sessionid,
                                                @Field("id") int id);

                                                @Field("id")int id);

    //添加收货地址
    @FormUrlEncoded
    @POST("small/user/verify/v1/addReceiveAddress")
    Observable<Result> addAddress(@Header("userId") int userid,
                                  @Header("sessionId") String sessionid,
                                  @Field("realName") String realName,
                                  @Field("phone") String phone,
                                  @Field("address") String address,
                                  @Field("zipCode") String zipCode);

    @GET("small/commodity/verify/v1/browseList")
    Observable<Result<List<BrowseListBean>>> browseList(@Header("userId") int userId,
                                                  @Header("sessionId") String sessionid,
                                                  @Query("page") int page,
                                                  @Query("count") int count);
    @FormUrlEncoded
    @PUT("small/user/verify/v1/modifyUserNick")
    Observable<Result> modifyUserNick(@Header("userId") long userid,
                                      @Header("sessionId") String sessionid,
                                      @Field("nickName")String nickName);

    @FormUrlEncoded
    @PUT("small/user/verify/v1/modifyUserPwd")
    Observable<Result> modifyUserPwd(@Header("userId") long userid,
                                     @Header("sessionId") String sessionid,
                                     @Field("oldPwd")String oldPwd,
                                     @Field("newPwd")String newPwd
    );
}
