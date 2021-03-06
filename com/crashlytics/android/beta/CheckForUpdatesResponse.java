package com.crashlytics.android.beta;

class CheckForUpdatesResponse
{
  public final String buildVersion;
  public final String displayVersion;
  public final String instanceId;
  public final String packageName;
  public final String url;
  public final String versionString;
  
  public CheckForUpdatesResponse(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.url = paramString1;
    this.versionString = paramString2;
    this.displayVersion = paramString3;
    this.buildVersion = paramString4;
    this.packageName = paramString5;
    this.instanceId = paramString6;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/CheckForUpdatesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */