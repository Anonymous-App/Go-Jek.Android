package com.gojek.gobox.networking;

import com.gojek.gobox.model.CorporatePINResponse;
import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.TokenResponse;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public abstract interface GoJekNetworkManager
{
  @GET("/corporate/findByPin")
  public abstract Observable<CorporatePINResponse> getCorporatePinResponseObservable(@Query("pin") String paramString);
  
  @GET("/v2/customer/{customer_id}")
  public abstract Observable<CustomerResponse> getCustomerResponseObservable(@Path("customer_id") String paramString);
  
  @POST("/oauth/token?client_id=consumer-trusted-client&grant_type=refresh_token")
  public abstract Observable<TokenResponse> refreshToken(@Query("refresh_token") String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/GoJekNetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */