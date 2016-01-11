package com.crashlytics.android.answers;

final class SessionEventMetadata
{
  public final String advertisingId;
  public final String androidId;
  public final String appBundleId;
  public final String appVersionCode;
  public final String appVersionName;
  public final String betaDeviceToken;
  public final String buildId;
  public final String deviceModel;
  public final String executionId;
  public final String installationId;
  public final Boolean limitAdTrackingEnabled;
  public final String osVersion;
  private String stringRepresentation;
  
  public SessionEventMetadata(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Boolean paramBoolean, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11)
  {
    this.appBundleId = paramString1;
    this.executionId = paramString2;
    this.installationId = paramString3;
    this.androidId = paramString4;
    this.advertisingId = paramString5;
    this.limitAdTrackingEnabled = paramBoolean;
    this.betaDeviceToken = paramString6;
    this.buildId = paramString7;
    this.osVersion = paramString8;
    this.deviceModel = paramString9;
    this.appVersionCode = paramString10;
    this.appVersionName = paramString11;
  }
  
  public String toString()
  {
    if (this.stringRepresentation == null) {
      this.stringRepresentation = ("appBundleId=" + this.appBundleId + ", executionId=" + this.executionId + ", installationId=" + this.installationId + ", androidId=" + this.androidId + ", advertisingId=" + this.advertisingId + ", limitAdTrackingEnabled=" + this.limitAdTrackingEnabled + ", betaDeviceToken=" + this.betaDeviceToken + ", buildId=" + this.buildId + ", osVersion=" + this.osVersion + ", deviceModel=" + this.deviceModel + ", appVersionCode=" + this.appVersionCode + ", appVersionName=" + this.appVersionName);
    }
    return this.stringRepresentation;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionEventMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */