package com.gojek.gotix.network;

import com.gojek.gotix.Event;
import com.gojek.gotix.Order;
import com.gojek.gotix.network.model.Booking;
import com.gojek.gotix.network.model.CallCenter;
import com.gojek.gotix.network.model.DeliveryBooking;
import com.gojek.gotix.network.model.EventDistance;
import com.gojek.gotix.network.model.PendingReview;
import com.gojek.gotix.network.model.PurchaseStatus;
import com.gojek.gotix.network.model.PurchasedOrderData;
import com.gojek.gotix.network.model.RegistrationGCM;
import com.gojek.gotix.network.model.ReleaseOrder;
import com.gojek.gotix.network.model.Review;
import com.gojek.gotix.network.model.TicketPurchased;
import com.gojek.gotix.network.model.Transaction;
import java.util.List;
import lib.gojek.base.networks.model.RegisteredToken;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public abstract interface GotixRest
{
  @POST("/v1/orders/{order_id}/purchase_failed_acknowledge")
  public abstract Observable<PurchaseStatus> ackPurchaseFailed(@Path("order_id") int paramInt);
  
  @GET("/v1/events/{event_id}/calculate")
  public abstract Observable<EventDistance> calculateEventDistanceFromLocation(@Path("event_id") int paramInt, @Query("latitude") Double paramDouble1, @Query("longitude") Double paramDouble2);
  
  @PUT("/v1/orders/{order_id}/cancel_booking")
  public abstract Observable<Void> cancelBooking(@Path("order_id") int paramInt);
  
  @POST("/v1/orders/{order_id}/booking")
  public abstract Observable<Booking> createBooking(@Path("order_id") int paramInt, @Body DeliveryBooking paramDeliveryBooking);
  
  @POST("/v1/orders")
  public abstract Observable<Transaction> createOrderForEvent(@Body TicketPurchased paramTicketPurchased);
  
  @POST("/v1/orders/{order_id}/purchase")
  public abstract Observable<PurchaseStatus> createPurchaseOrder(@Path("order_id") int paramInt, @Body PurchasedOrderData paramPurchasedOrderData);
  
  @GET("/v1/callcenter")
  public abstract Observable<CallCenter> fetchCallCenter();
  
  @GET("/v1/events/{status}")
  public abstract Observable<List<Event>> fetchEvent(@Path("status") String paramString, @Query("page_no") int paramInt1, @Query("page_size") int paramInt2);
  
  @GET("/v1/events/{event_id}")
  public abstract Observable<Event> fetchEventDetail(@Path("event_id") int paramInt);
  
  @GET("/v1/users/{user_id}/orders")
  public abstract Observable<List<Order>> fetchListAllPurchasedOrder(@Path("user_id") int paramInt1, @Query("page_no") int paramInt2, @Query("page_size") int paramInt3);
  
  @GET("/v1/users/{user_id}/orders/{order_id}")
  public abstract Observable<Order> fetchOrderDetailUser(@Path("user_id") int paramInt1, @Path("order_id") int paramInt2);
  
  @GET("/v1/orders/{order_id}/booking")
  public abstract Observable<Booking> findDriver(@Path("order_id") int paramInt);
  
  @GET("/v1/users/{user_id}/reviews")
  public abstract Observable<List<PendingReview>> getPendingReview(@Path("user_id") int paramInt);
  
  @GET("/v1/users/{user_id}/orders/{order_id}/review")
  public abstract Observable<PendingReview> getPendingReviewByOrder(@Path("user_id") int paramInt1, @Path("order_id") int paramInt2);
  
  @GET("/v1/orders/{order_id}/purchase_status")
  public abstract Observable<PurchaseStatus> getPurchaseStatus(@Path("order_id") int paramInt);
  
  @PUT("/v1/users/{user_id}/token")
  public abstract Observable<Response> registerAccessToken(@Body RegisteredToken paramRegisteredToken, @Path("user_id") String paramString);
  
  @PUT("/v1/users/{user_id}/gcm")
  public abstract Observable<Void> registerUserGCMToken(@Path("user_id") int paramInt, @Body RegistrationGCM paramRegistrationGCM);
  
  @PUT("/v1/orders/{order_id}/release")
  public abstract Observable<Void> releaseUnpaidOrder(@Path("order_id") int paramInt, @Body ReleaseOrder paramReleaseOrder);
  
  @GET("/v1/events")
  public abstract Observable<List<Event>> searchEvent(@Query("search") String paramString, @Query("page_no") int paramInt1, @Query("page_size") int paramInt2);
  
  @POST("/v1/orders/{order_id}/review")
  public abstract Observable<Response> submitReview(@Path("order_id") int paramInt, @Body Review paramReview);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/GotixRest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */