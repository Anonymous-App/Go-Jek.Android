package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;

public class ApiKey
{
  static final String CRASHLYTICS_API_KEY = "com.crashlytics.ApiKey";
  static final String FABRIC_API_KEY = "io.fabric.ApiKey";
  
  @Deprecated
  public static String getApiKey(Context paramContext)
  {
    Fabric.getLogger().w("Fabric", "getApiKey(context) is deprecated, please upgrade kit(s) to the latest version.");
    return new ApiKey().getValue(paramContext);
  }
  
  @Deprecated
  public static String getApiKey(Context paramContext, boolean paramBoolean)
  {
    Fabric.getLogger().w("Fabric", "getApiKey(context, debug) is deprecated, please upgrade kit(s) to the latest version.");
    return new ApiKey().getValue(paramContext);
  }
  
  protected String buildApiKeyInstructions()
  {
    return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
  }
  
  protected String getApiKeyFromManifest(Context paramContext)
  {
    String str2 = null;
    Object localObject1 = null;
    String str1 = str2;
    try
    {
      Object localObject2 = paramContext.getPackageName();
      str1 = str2;
      localObject2 = paramContext.getPackageManager().getApplicationInfo((String)localObject2, 128).metaData;
      paramContext = (Context)localObject1;
      if (localObject2 != null)
      {
        str1 = str2;
        str2 = ((Bundle)localObject2).getString("io.fabric.ApiKey");
        paramContext = str2;
        if (str2 == null)
        {
          str1 = str2;
          Fabric.getLogger().d("Fabric", "Falling back to Crashlytics key lookup from Manifest");
          str1 = str2;
          paramContext = ((Bundle)localObject2).getString("com.crashlytics.ApiKey");
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Fabric.getLogger().d("Fabric", "Caught non-fatal exception while retrieving apiKey: " + paramContext);
    }
    return str1;
  }
  
  protected String getApiKeyFromStrings(Context paramContext)
  {
    String str = null;
    int j = CommonUtils.getResourcesIdentifier(paramContext, "io.fabric.ApiKey", "string");
    int i = j;
    if (j == 0)
    {
      Fabric.getLogger().d("Fabric", "Falling back to Crashlytics key lookup from Strings");
      i = CommonUtils.getResourcesIdentifier(paramContext, "com.crashlytics.ApiKey", "string");
    }
    if (i != 0) {
      str = paramContext.getResources().getString(i);
    }
    return str;
  }
  
  public String getValue(Context paramContext)
  {
    String str2 = getApiKeyFromManifest(paramContext);
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = getApiKeyFromStrings(paramContext);
    }
    if (TextUtils.isEmpty(str1)) {
      logErrorOrThrowException(paramContext);
    }
    return str1;
  }
  
  protected void logErrorOrThrowException(Context paramContext)
  {
    if ((Fabric.isDebuggable()) || (CommonUtils.isAppDebuggable(paramContext))) {
      throw new IllegalArgumentException(buildApiKeyInstructions());
    }
    Fabric.getLogger().e("Fabric", buildApiKeyInstructions());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/fabric/sdk/android/services/common/ApiKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */