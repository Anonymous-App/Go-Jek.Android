package com.gojek.gobox.networking;

import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.model.BookingInfoResponse;
import com.gojek.gobox.model.BookingRequestBody;
import com.gojek.gobox.model.CargoTypeResponse;
import com.gojek.gobox.model.ConfigResponse;
import com.gojek.gobox.model.ConfirmOrderCompleteBody;
import com.gojek.gobox.model.ConfirmationRemainingTimeResponse;
import com.gojek.gobox.model.DriverReviewRequestBody;
import com.gojek.gobox.model.EstimationRequestBody;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.OrderHistoryResponse;
import com.gojek.gobox.model.OrderResponse;
import com.gojek.gobox.model.RegisterGcmRequestBody;
import com.gojek.gobox.model.SendInvoiceRequestBody;
import com.gojek.gobox.model.TokenResponse;
import com.gojek.gobox.model.VehicleTypeResponse;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public abstract interface NetworkManager
{
  @DELETE("/v1/orders/{order_id}")
  public abstract Observable<Response> cancelBooking(@Path("order_id") String paramString);
  
  @GET("/v1/orders/{order_id}/status")
  public abstract Observable<BookingDriverStatusResponse> getBookingDriverStatusResponseObservable(@Path("order_id") String paramString);
  
  @GET("/v1/orders/{order_id}")
  public abstract Observable<BookingInfoResponse> getBookingInfoOrderObservable(@Path("order_id") String paramString);
  
  @GET("/v1/boxes")
  public abstract Observable<CargoTypeResponse> getCargoTypeResponseObservable();
  
  @GET("/v1/config")
  public abstract Observable<ConfigResponse> getConfigResponseObservable();
  
  @PUT("/v1/orders/{order_id}/status")
  public abstract Observable<Response> getConfirmOrderObservable(@Path("order_id") String paramString, @Body ConfirmOrderCompleteBody paramConfirmOrderCompleteBody);
  
  @GET("/v1/orders/{order_id}/confirmtimer")
  public abstract Observable<ConfirmationRemainingTimeResponse> getConfirmationRemainingTimeResponseObservable(@Path("order_id") String paramString);
  
  @POST("/v1/orders/{order_id}/rating")
  public abstract Observable<Response> getDriverReviewObservable(@Path("order_id") String paramString, @Body DriverReviewRequestBody paramDriverReviewRequestBody);
  
  @POST("/v1/boxes/{box_id}/estimation")
  public abstract Observable<EstimationResponse> getEstimationResponseObservable(@Path("box_id") long paramLong, @Body EstimationRequestBody paramEstimationRequestBody);
  
  @GET("/v1/users/{user_id}/history")
  public abstract Observable<OrderHistoryResponse> getOrderHistoryObservable(@Path("user_id") String paramString);
  
  @POST("/v1/boxes/{box_id}/book")
  public abstract Observable<OrderResponse> getOrderResponseObservable(@Path("box_id") long paramLong, @Body BookingRequestBody paramBookingRequestBody);
  
  @POST("/v2/consumer/gcm")
  public abstract Observable<Response> getRegisterGCMObservable(@Body RegisterGcmRequestBody paramRegisterGcmRequestBody);
  
  @PUT("/v1/orders/{order_id}/reorder")
  public abstract Observable<Response> getReorderReponse(@Path("order_id") String paramString);
  
  @PUT("/v2/consumer/token")
  public abstract Observable<Response> getSaveAccessTokenObservable(@Body TokenResponse paramTokenResponse);
  
  @PUT("/v1/orders/{order_id}/invoice")
  public abstract Observable<Response> getSendInvoiceObservable(@Path("order_id") String paramString, @Body SendInvoiceRequestBody paramSendInvoiceRequestBody);
  
  @GET("/v1/boxes/{box_id}/vehicles/available")
  public abstract Observable<VehicleTypeResponse> getVehicleResponseObservable(@Path("box_id") long paramLong, @Query("lat") double paramDouble1, @Query("long") double paramDouble2);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/NetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */