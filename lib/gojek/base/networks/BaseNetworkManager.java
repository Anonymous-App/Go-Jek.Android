package lib.gojek.base.networks;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newrelic.agent.android.instrumentation.retrofit.RetrofitInstrumentation;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.networks.utils.SSLUtils;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public abstract class BaseNetworkManager<T>
  extends AbstractBaseNetwork
{
  private static final String AUTHORIZATION = "Authorization";
  private static final String TAG = BaseNetworkManager.class.getSimpleName();
  private Context context;
  private T networkService;
  
  public BaseNetworkManager(Context paramContext)
  {
    this.context = paramContext;
    initializeNetworkInterface();
  }
  
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
    localHashMap.put("Authorization", BasePreferences.getBearerAccessToken());
    return localHashMap;
  }
  
  private RequestInterceptor getRequestInterceptorWithHeader()
  {
    return BaseNetworkManager..Lambda.2.lambdaFactory$(this);
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  protected abstract String getEndPointUrl();
  
  public T getNetworkService()
  {
    return (T)this.networkService;
  }
  
  protected abstract Class<T> getRestClass();
  
  protected GsonBuilder gsonHandler(GsonBuilder paramGsonBuilder)
  {
    return paramGsonBuilder;
  }
  
  protected void initializeNetworkInterface()
  {
    Object localObject = createCustomClient(isUseSSL());
    ((OkHttpClient)localObject).setCache(new Cache(new File(this.context.getCacheDir(), UUID.randomUUID().toString()), 4194304L));
    Gson localGson = gsonHandler(new GsonBuilder().setPrettyPrinting()).setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").create();
    RestAdapter.Builder localBuilder = new RestAdapter.Builder().setEndpoint(getEndPointUrl());
    localObject = new OkClient((OkHttpClient)localObject);
    if (!(localBuilder instanceof RestAdapter.Builder)) {}
    for (localBuilder = localBuilder.setClient((Client)localObject);; localBuilder = RetrofitInstrumentation.setClient((RestAdapter.Builder)localBuilder, (Client)localObject))
    {
      this.networkService = localBuilder.setConverter(new GsonConverter(localGson)).setRequestInterceptor(getRequestInterceptorWithHeader()).setLogLevel(RestAdapter.LogLevel.FULL).setLog(BaseNetworkManager..Lambda.1.lambdaFactory$()).build().create(getRestClass());
      return;
    }
  }
  
  protected abstract boolean isUseSSL();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/BaseNetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */