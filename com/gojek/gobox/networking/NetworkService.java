package com.gojek.gobox.networking;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.gojek.gobox.model.BookingDriverStatusResponse;
import com.gojek.gobox.model.BookingInfoResponse;
import com.gojek.gobox.model.BookingRequestBody;
import com.gojek.gobox.model.CargoTypeResponse;
import com.gojek.gobox.model.ConfirmOrderCompleteBody;
import com.gojek.gobox.model.ConfirmationRemainingTimeResponse;
import com.gojek.gobox.model.CorporatePINResponse;
import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.DriverReviewRequestBody;
import com.gojek.gobox.model.EstimationRequestBody;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.OrderHistory;
import com.gojek.gobox.model.OrderResponse;
import com.gojek.gobox.model.RegisterGcmRequestBody;
import com.gojek.gobox.model.SendInvoiceRequestBody;
import com.gojek.gobox.model.TokenResponse;
import com.gojek.gobox.model.VehicleTypeResponse;
import com.gojek.gobox.util.GoBoxDataInitializationListener;
import com.gojek.gobox.util.GoBoxPreferences;
import com.gojek.gobox.util.SSLUtils;
import com.newrelic.agent.android.instrumentation.retrofit.RetrofitInstrumentation;
import com.squareup.okhttp.OkHttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkService
  extends Service
{
  private static final String AUTHORIZATION = "Authorization";
  private static final String INVALID_GRANT_RESPONSE = "invalid_grant";
  private static final int TIMEOUT = 30;
  private static final String USER_UUID = "user-uuid";
  private final IBinder mBinder = new LocalBinder();
  private GoBoxPreferences mGoBoxPreferences;
  private GoJekNetworkManager mGoJekNetworkManager;
  private NetworkManager mNetworkManager;
  
  private OkHttpClient createCustomClient(boolean paramBoolean)
  {
    localOkHttpClient = new OkHttpClient();
    if (paramBoolean) {
      try
      {
        SSLContext localSSLContext = SSLContext.getInstance("SSL");
        try
        {
          localSSLContext.init(null, new TrustManager[] { SSLUtils.trustManager }, null);
          localOkHttpClient.setSslSocketFactory(localSSLContext.getSocketFactory());
          return localOkHttpClient;
        }
        catch (KeyManagementException localKeyManagementException)
        {
          for (;;)
          {
            localKeyManagementException.printStackTrace();
          }
        }
        return localOkHttpClient;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
  }
  
  private Map<String, String> createDefaultHeader()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Authorization", this.mGoBoxPreferences.getBearerAccessToken());
    localHashMap.put("user-uuid", this.mGoBoxPreferences.getUserUUID());
    return localHashMap;
  }
  
  private RequestInterceptor getRequestInterceptorWithHeader()
  {
    return new NetworkService.3(this);
  }
  
  /* Error */
  private boolean isInvalidGrant(Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 193
    //   4: ifeq +69 -> 73
    //   7: aload_1
    //   8: checkcast 193	retrofit/RetrofitError
    //   11: astore_2
    //   12: new 195	com/google/gson/Gson
    //   15: dup
    //   16: invokespecial 196	com/google/gson/Gson:<init>	()V
    //   19: astore_1
    //   20: aload_2
    //   21: invokevirtual 200	retrofit/RetrofitError:getResponse	()Lretrofit/client/Response;
    //   24: invokestatic 206	com/gojek/gobox/util/Utils:getJsonStringFromResponse	(Lretrofit/client/Response;)Ljava/lang/String;
    //   27: astore_2
    //   28: aload_1
    //   29: instanceof 195
    //   32: ifne +24 -> 56
    //   35: aload_1
    //   36: aload_2
    //   37: ldc -48
    //   39: invokevirtual 212	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   42: astore_1
    //   43: aload_1
    //   44: checkcast 208	com/gojek/gobox/model/ErrorResponse
    //   47: invokevirtual 215	com/gojek/gobox/model/ErrorResponse:getError	()Ljava/lang/String;
    //   50: ldc 14
    //   52: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   55: ireturn
    //   56: aload_1
    //   57: checkcast 195	com/google/gson/Gson
    //   60: aload_2
    //   61: ldc -48
    //   63: invokestatic 226	com/newrelic/agent/android/instrumentation/GsonInstrumentation:fromJson	(Lcom/google/gson/Gson;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   66: astore_1
    //   67: goto -24 -> 43
    //   70: astore_1
    //   71: iconst_0
    //   72: ireturn
    //   73: iconst_0
    //   74: ireturn
    //   75: astore_1
    //   76: goto -5 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	NetworkService
    //   0	79	1	paramThrowable	Throwable
    //   11	50	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	43	70	java/lang/NullPointerException
    //   43	56	70	java/lang/NullPointerException
    //   56	67	70	java/lang/NullPointerException
    //   0	43	75	com/google/gson/JsonSyntaxException
    //   43	56	75	com/google/gson/JsonSyntaxException
    //   56	67	75	com/google/gson/JsonSyntaxException
  }
  
  private boolean isTokenExpired(Throwable paramThrowable)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if ((paramThrowable instanceof RetrofitError))
      {
        int i = ((RetrofitError)paramThrowable).getResponse().getStatus();
        bool1 = bool2;
        if (i == 401) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (NullPointerException paramThrowable) {}
    return false;
  }
  
  private Observable<Boolean> refreshToken()
  {
    return this.mGoJekNetworkManager.refreshToken(this.mGoBoxPreferences.getRefreshToken()).flatMap(NetworkService..Lambda.2.lambdaFactory$(this)).flatMap(NetworkService..Lambda.3.lambdaFactory$(this)).onErrorResumeNext(NetworkService..Lambda.4.lambdaFactory$(this));
  }
  
  public Observable<BookingDriverStatusResponse> getBookingDriverStatusResponseObservable(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getBookingDriverStatusResponseObservable(paramString)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<BookingInfoResponse> getBookingInfoResponseObservable(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getBookingInfoOrderObservable(paramString)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<Boolean> getCancelBookingObservable(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.cancelBooking(paramString).map(NetworkService..Lambda.6.lambdaFactory$())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<CargoTypeResponse> getCargoTypeResponseObservable()
  {
    return handleRefreshToken(this.mNetworkManager.getCargoTypeResponseObservable()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<Boolean> getConfiguration()
  {
    return handleRefreshToken(this.mNetworkManager.getConfigResponseObservable()).map(NetworkService..Lambda.5.lambdaFactory$(this)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<Boolean> getConfirmOrderCompleteObservable(String paramString, ConfirmOrderCompleteBody paramConfirmOrderCompleteBody)
  {
    return handleRefreshToken(this.mNetworkManager.getConfirmOrderObservable(paramString, paramConfirmOrderCompleteBody).map(NetworkService..Lambda.10.lambdaFactory$())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<ConfirmationRemainingTimeResponse> getConfirmationRemainingTimeResponseObservable(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getConfirmationRemainingTimeResponseObservable(paramString)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<CorporatePINResponse> getCorporateResponseObservable(String paramString)
  {
    return handleRefreshToken(this.mGoJekNetworkManager.getCorporatePinResponseObservable(paramString)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<CustomerResponse> getCustomerResponseObservable()
  {
    return handleRefreshToken(this.mGoJekNetworkManager.getCustomerResponseObservable(this.mGoBoxPreferences.getUserUUID())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<Boolean> getDriverReviewObsevable(String paramString, DriverReviewRequestBody paramDriverReviewRequestBody)
  {
    return handleRefreshToken(this.mNetworkManager.getDriverReviewObservable(paramString, paramDriverReviewRequestBody).map(NetworkService..Lambda.9.lambdaFactory$())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<EstimationResponse> getEstimationResponseObservable(long paramLong, EstimationRequestBody paramEstimationRequestBody)
  {
    return handleRefreshToken(this.mNetworkManager.getEstimationResponseObservable(paramLong, paramEstimationRequestBody)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<OrderHistory[]> getOrderHistoryObservable()
  {
    return handleRefreshToken(this.mNetworkManager.getOrderHistoryObservable(this.mGoBoxPreferences.getUserUUID()).map(NetworkService..Lambda.13.lambdaFactory$())).onErrorResumeNext(NetworkService..Lambda.14.lambdaFactory$(this)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<OrderResponse> getOrderResponseObservable(long paramLong, BookingRequestBody paramBookingRequestBody)
  {
    return handleRefreshToken(this.mNetworkManager.getOrderResponseObservable(paramLong, paramBookingRequestBody)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<Boolean> getReorderResponse(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getReorderReponse(paramString).map(NetworkService..Lambda.7.lambdaFactory$()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()));
  }
  
  public Observable<Response> getSaveAccessTokenObservable(TokenResponse paramTokenResponse)
  {
    return this.mNetworkManager.getSaveAccessTokenObservable(paramTokenResponse).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<CargoTypeResponse> getSavedCargoTypeResponseObservable()
  {
    return Observable.create(new NetworkService.1(this)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<Boolean> getSendInvoiceObservable(String paramString, SendInvoiceRequestBody paramSendInvoiceRequestBody)
  {
    return handleRefreshToken(this.mNetworkManager.getSendInvoiceObservable(paramString, paramSendInvoiceRequestBody).map(NetworkService..Lambda.11.lambdaFactory$())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public Observable<VehicleTypeResponse> getVehicleResponseObservable(long paramLong, double paramDouble1, double paramDouble2)
  {
    return handleRefreshToken(this.mNetworkManager.getVehicleResponseObservable(paramLong, paramDouble1, paramDouble2)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public <T> Observable<T> handleRefreshToken(Observable<T> paramObservable)
  {
    return paramObservable.onErrorResumeNext(NetworkService..Lambda.1.lambdaFactory$(this, paramObservable));
  }
  
  public void initializeGoBoxData(GoBoxDataInitializationListener paramGoBoxDataInitializationListener)
  {
    Observable.zip(getConfiguration(), getCargoTypeResponseObservable(), NetworkService..Lambda.12.lambdaFactory$(this)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new NetworkService.2(this, paramGoBoxDataInitializationListener));
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    this.mGoBoxPreferences = new GoBoxPreferences(this);
    paramIntent = new RestAdapter.Builder().setEndpoint("https://gobox-api.gojek.co.id/").setRequestInterceptor(getRequestInterceptorWithHeader());
    Object localObject = new OkClient(createCustomClient(false));
    OkClient localOkClient;
    if (!(paramIntent instanceof RestAdapter.Builder))
    {
      paramIntent = paramIntent.setClient((Client)localObject);
      localObject = new RestAdapter.Builder().setEndpoint("https://api.gojek.co.id/gojek").setRequestInterceptor(getRequestInterceptorWithHeader());
      localOkClient = new OkClient(createCustomClient(true));
      if ((localObject instanceof RestAdapter.Builder)) {
        break label157;
      }
    }
    label157:
    for (localObject = ((RestAdapter.Builder)localObject).setClient(localOkClient);; localObject = RetrofitInstrumentation.setClient((RestAdapter.Builder)localObject, localOkClient))
    {
      this.mNetworkManager = ((NetworkManager)paramIntent.build().create(NetworkManager.class));
      this.mGoJekNetworkManager = ((GoJekNetworkManager)((RestAdapter.Builder)localObject).build().create(GoJekNetworkManager.class));
      return this.mBinder;
      paramIntent = RetrofitInstrumentation.setClient((RestAdapter.Builder)paramIntent, (Client)localObject);
      break;
    }
  }
  
  public Observable<Boolean> registerGoBoxGcm(String paramString)
  {
    return handleRefreshToken(this.mNetworkManager.getRegisterGCMObservable(new RegisterGcmRequestBody(paramString)).map(NetworkService..Lambda.8.lambdaFactory$())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
  }
  
  public class LocalBinder
    extends Binder
  {
    public LocalBinder() {}
    
    public NetworkService getService()
    {
      return NetworkService.this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/networking/NetworkService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */