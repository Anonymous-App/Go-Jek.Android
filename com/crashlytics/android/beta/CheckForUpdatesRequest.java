package com.crashlytics.android.beta;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;

class CheckForUpdatesRequest
  extends AbstractSpiCall
{
  static final String BETA_SOURCE = "3";
  static final String BUILD_VERSION = "build_version";
  static final String DISPLAY_VERSION = "display_version";
  static final String INSTANCE = "instance";
  static final String SOURCE = "source";
  private final CheckForUpdatesResponseTransform responseTransform;
  
  public CheckForUpdatesRequest(Kit paramKit, String paramString1, String paramString2, HttpRequestFactory paramHttpRequestFactory, CheckForUpdatesResponseTransform paramCheckForUpdatesResponseTransform)
  {
    super(paramKit, paramString1, paramString2, paramHttpRequestFactory, HttpMethod.GET);
    this.responseTransform = paramCheckForUpdatesResponseTransform;
  }
  
  private HttpRequest applyHeadersTo(HttpRequest paramHttpRequest, String paramString1, String paramString2)
  {
    return paramHttpRequest.header("Accept", "application/json").header("User-Agent", "Crashlytics Android SDK/" + this.kit.getVersion()).header("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa").header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", paramString1).header("X-CRASHLYTICS-D", paramString2);
  }
  
  private Map<String, String> getQueryParamsFor(BuildProperties paramBuildProperties)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("build_version", paramBuildProperties.versionCode);
    localHashMap.put("display_version", paramBuildProperties.versionName);
    localHashMap.put("instance", paramBuildProperties.buildId);
    localHashMap.put("source", "3");
    return localHashMap;
  }
  
  public CheckForUpdatesResponse invoke(String paramString1, String paramString2, BuildProperties paramBuildProperties)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    localObject2 = localObject3;
    localObject1 = localObject4;
    try
    {
      Map localMap = getQueryParamsFor(paramBuildProperties);
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramBuildProperties = getHttpRequest(localMap);
      localObject2 = paramBuildProperties;
      localObject1 = paramBuildProperties;
      paramString1 = applyHeadersTo(paramBuildProperties, paramString1, paramString2);
      localObject2 = paramString1;
      localObject1 = paramString1;
      Fabric.getLogger().d("Beta", "Checking for updates from " + getUrl());
      localObject2 = paramString1;
      localObject1 = paramString1;
      Fabric.getLogger().d("Beta", "Checking for updates query params are: " + localMap);
      localObject2 = paramString1;
      localObject1 = paramString1;
      if (paramString1.ok())
      {
        localObject2 = paramString1;
        localObject1 = paramString1;
        Fabric.getLogger().d("Beta", "Checking for updates was successful");
        localObject2 = paramString1;
        localObject1 = paramString1;
        paramString2 = JSONObjectInstrumentation.init(paramString1.body());
        localObject2 = paramString1;
        localObject1 = paramString1;
        paramString2 = this.responseTransform.fromJson(paramString2);
        if (paramString1 != null)
        {
          paramString1 = paramString1.header("X-REQUEST-ID");
          Fabric.getLogger().d("Fabric", "Checking for updates request ID: " + paramString1);
        }
        return paramString2;
      }
      localObject2 = paramString1;
      localObject1 = paramString1;
      Fabric.getLogger().e("Beta", "Checking for updates failed. Response code: " + paramString1.code());
      if (paramString1 != null)
      {
        paramString1 = paramString1.header("X-REQUEST-ID");
        Fabric.getLogger().d("Fabric", "Checking for updates request ID: " + paramString1);
      }
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        localObject1 = localObject2;
        Fabric.getLogger().e("Beta", "Error while checking for updates from " + getUrl(), paramString1);
        if (localObject2 != null)
        {
          paramString1 = ((HttpRequest)localObject2).header("X-REQUEST-ID");
          Fabric.getLogger().d("Fabric", "Checking for updates request ID: " + paramString1);
        }
      }
    }
    finally
    {
      if (localObject1 == null) {
        break label432;
      }
      paramString2 = ((HttpRequest)localObject1).header("X-REQUEST-ID");
      Fabric.getLogger().d("Fabric", "Checking for updates request ID: " + paramString2);
    }
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/CheckForUpdatesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */