package io.fabric.sdk.android.services.settings;

public class AnalyticsSettingsData
{
  public static final int DEFAULT_SAMPLING_RATE = 1;
  public final String analyticsURL;
  public final int flushIntervalSeconds;
  public final int maxByteSizePerFile;
  public final int maxFileCountPerSend;
  public final int maxPendingSendFileCount;
  public final int samplingRate;
  public final boolean trackCustomEvents;
  public final boolean trackPredefinedEvents;
  
  @Deprecated
  public AnalyticsSettingsData(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    this(paramString, paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, true, 1);
  }
  
  @Deprecated
  public AnalyticsSettingsData(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5)
  {
    this(paramString, paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, true, paramInt5);
  }
  
  public AnalyticsSettingsData(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, int paramInt5)
  {
    this.analyticsURL = paramString;
    this.flushIntervalSeconds = paramInt1;
    this.maxByteSizePerFile = paramInt2;
    this.maxFileCountPerSend = paramInt3;
    this.maxPendingSendFileCount = paramInt4;
    this.trackCustomEvents = paramBoolean1;
    this.trackPredefinedEvents = paramBoolean2;
    this.samplingRate = paramInt5;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/fabric/sdk/android/services/settings/AnalyticsSettingsData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */