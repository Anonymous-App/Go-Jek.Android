package lib.gojek.base.networks;

import android.content.Context;
import lib.gojek.base.helper.BasePreferences;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

public class GojekNetworkManager
  extends BaseNetworkManager<GojekRest>
{
  private static final String INVALID_GRANT_RESPONSE = "invalid_grant";
  private Context context;
  GeneralNetworkHandler generalNetworkHandler;
  
  public GojekNetworkManager(Context paramContext, GeneralNetworkHandler paramGeneralNetworkHandler)
  {
    super(paramContext);
    this.generalNetworkHandler = paramGeneralNetworkHandler;
    this.context = paramContext;
  }
  
  /* Error */
  private boolean isInvalidGrant(Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 54
    //   4: ifeq +69 -> 73
    //   7: aload_1
    //   8: checkcast 54	retrofit/RetrofitError
    //   11: astore_2
    //   12: new 56	com/google/gson/Gson
    //   15: dup
    //   16: invokespecial 59	com/google/gson/Gson:<init>	()V
    //   19: astore_1
    //   20: aload_2
    //   21: invokevirtual 63	retrofit/RetrofitError:getResponse	()Lretrofit/client/Response;
    //   24: invokestatic 69	lib/gojek/base/util/BaseUtils:getJsonStringFromResponse	(Lretrofit/client/Response;)Ljava/lang/String;
    //   27: astore_2
    //   28: aload_1
    //   29: instanceof 56
    //   32: ifne +24 -> 56
    //   35: aload_1
    //   36: aload_2
    //   37: ldc 71
    //   39: invokevirtual 75	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   42: astore_1
    //   43: aload_1
    //   44: checkcast 71	lib/gojek/base/networks/model/ErrorResponse
    //   47: invokevirtual 79	lib/gojek/base/networks/model/ErrorResponse:getError	()Ljava/lang/String;
    //   50: ldc 9
    //   52: invokevirtual 85	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   55: ireturn
    //   56: aload_1
    //   57: checkcast 56	com/google/gson/Gson
    //   60: aload_2
    //   61: ldc 71
    //   63: invokestatic 90	com/newrelic/agent/android/instrumentation/GsonInstrumentation:fromJson	(Lcom/google/gson/Gson;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
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
    //   0	79	0	this	GojekNetworkManager
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
  
  protected String getEndPointUrl()
  {
    return "https://api.gojek.co.id/gojek";
  }
  
  protected Class getRestClass()
  {
    return GojekRest.class;
  }
  
  public <T> Observable<T> handleRefreshToken(Observable<T> paramObservable)
  {
    return paramObservable.onErrorResumeNext(GojekNetworkManager..Lambda.1.lambdaFactory$(this, paramObservable));
  }
  
  protected boolean isUseSSL()
  {
    return true;
  }
  
  public Observable<Boolean> refreshToken()
  {
    return ((GojekRest)getNetworkService()).refreshToken(BasePreferences.getRefreshToken()).map(GojekNetworkManager..Lambda.4.lambdaFactory$()).onErrorResumeNext(GojekNetworkManager..Lambda.5.lambdaFactory$(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/GojekNetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */