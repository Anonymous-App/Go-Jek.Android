package lib.gojek.base.networks;

import lib.gojek.base.networks.model.Customer;
import lib.gojek.base.networks.model.Token;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public abstract interface GojekRest
{
  @GET("/v2/customer/{customer_id}")
  public abstract Observable<Customer> fetchCustomerData(@Path("customer_id") String paramString);
  
  @POST("/oauth/token?client_id=consumer-trusted-client&grant_type=refresh_token")
  public abstract Observable<Token> refreshToken(@Query("refresh_token") String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/GojekRest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */