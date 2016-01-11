package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import java.util.Map;
import java.util.UUID;

class SessionMetadataCollector
{
  private final Context context;
  private final IdManager idManager;
  private final String versionCode;
  private final String versionName;
  
  public SessionMetadataCollector(Context paramContext, IdManager paramIdManager, String paramString1, String paramString2)
  {
    this.context = paramContext;
    this.idManager = paramIdManager;
    this.versionCode = paramString1;
    this.versionName = paramString2;
  }
  
  public SessionEventMetadata getMetadata()
  {
    Object localObject = this.idManager.getDeviceIdentifiers();
    String str1 = this.context.getPackageName();
    String str2 = this.idManager.getAppInstallIdentifier();
    String str3 = (String)((Map)localObject).get(IdManager.DeviceIdentifierType.ANDROID_ID);
    String str4 = (String)((Map)localObject).get(IdManager.DeviceIdentifierType.ANDROID_ADVERTISING_ID);
    Boolean localBoolean = this.idManager.isLimitAdTrackingEnabled();
    localObject = (String)((Map)localObject).get(IdManager.DeviceIdentifierType.FONT_TOKEN);
    String str5 = CommonUtils.resolveBuildId(this.context);
    String str6 = this.idManager.getOsVersionString();
    String str7 = this.idManager.getModelName();
    return new SessionEventMetadata(str1, UUID.randomUUID().toString(), str2, str3, str4, localBoolean, (String)localObject, str5, str6, str7, this.versionCode, this.versionName);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionMetadataCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */