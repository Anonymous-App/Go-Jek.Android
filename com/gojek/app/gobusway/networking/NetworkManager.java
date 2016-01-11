package com.gojek.app.gobusway.networking;

import com.gojek.app.gobusway.model.BusWayResponse;
import com.gojek.app.gobusway.model.HalteResponse;
import com.gojek.app.gobusway.model.HalteScheduleResponse;
import com.gojek.app.gobusway.model.TokenResponse;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public abstract interface NetworkManager
{
  @GET("/v1/busway/bus")
  public abstract Observable<BusWayResponse> getBusWayObservable(@Query("lat") double paramDouble1, @Query("long") double paramDouble2);
  
  public abstract Observable<String> getGcmRegistrationObservable();
  
  @GET("/v1/busway/halte")
  public abstract Observable<HalteResponse> getHalteObservable(@Header("Authorization") String paramString1, @Query("name") String paramString2);
  
  @GET("/v1/busway/halte/{halte_id}")
  public abstract Observable<HalteScheduleResponse> getHalteScheduleObservable(@Header("Authorization") String paramString1, @Path("halte_id") String paramString2);
  
  @POST("/v1/access_token")
  public abstract Observable<TokenResponse> refreshToken(@Header("refresh_token") String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/networking/NetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */