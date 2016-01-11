package com.flurry.android;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.os.Build.VERSION;
import com.flurry.sdk.ep;
import com.flurry.sdk.fn;
import com.flurry.sdk.fo;
import com.flurry.sdk.fp;
import com.flurry.sdk.gb;
import com.flurry.sdk.gw;
import com.flurry.sdk.ha;
import com.flurry.sdk.hj;
import com.flurry.sdk.ho;
import java.util.Date;
import java.util.Map;

public final class FlurryAgent
{
  private static final String a = FlurryAgent.class.getSimpleName();
  
  public static void addOrigin(String paramString1, String paramString2)
  {
    addOrigin(paramString1, paramString2, null);
  }
  
  public static void addOrigin(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if ((paramString1 == null) || (paramString1.length() == 0)) {
      throw new IllegalArgumentException("originName not specified");
    }
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      throw new IllegalArgumentException("originVersion not specified");
    }
    try
    {
      fp.a().a(paramString1, paramString2, paramMap);
      return;
    }
    catch (Throwable paramString1)
    {
      gb.a(a, "", paramString1);
    }
  }
  
  public static void clearLocation()
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    ha.a().a("ExplicitLocation", null);
  }
  
  public static void endTimedEvent(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to endTimedEvent was null.");
      return;
    }
    try
    {
      ep.a().b(paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      gb.a(a, "Failed to signify the end of event: " + paramString, localThrowable);
    }
  }
  
  public static void endTimedEvent(String paramString, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to endTimedEvent was null.");
      return;
    }
    if (paramMap == null)
    {
      gb.b(a, "String eventId passed to endTimedEvent was null.");
      return;
    }
    try
    {
      ep.a().b(paramString, paramMap);
      return;
    }
    catch (Throwable paramMap)
    {
      gb.a(a, "Failed to signify the end of event: " + paramString, paramMap);
    }
  }
  
  public static int getAgentVersion()
  {
    return fo.a();
  }
  
  public static String getReleaseVersion()
  {
    return fo.f();
  }
  
  public static void init(Context paramContext, String paramString)
  {
    for (;;)
    {
      try
      {
        if (Build.VERSION.SDK_INT < 10)
        {
          gb.b(a, "Device SDK Version older than 10");
          return;
        }
        if (paramContext == null) {
          throw new NullPointerException("Null context");
        }
      }
      finally {}
      if ((paramString == null) || (paramString.length() == 0)) {
        throw new IllegalArgumentException("apiKey not specified");
      }
      try
      {
        ho.a();
        fn.a(paramContext, paramString);
      }
      catch (Throwable paramContext)
      {
        gb.a(a, "", paramContext);
      }
    }
  }
  
  public static boolean isSessionActive()
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return false;
    }
    try
    {
      boolean bool = gw.a().e();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      gb.a(a, "", localThrowable);
    }
    return false;
  }
  
  public static FlurryEventRecordStatus logEvent(String paramString)
  {
    FlurryEventRecordStatus localFlurryEventRecordStatus1 = FlurryEventRecordStatus.kFlurryEventFailed;
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return localFlurryEventRecordStatus1;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to logEvent was null.");
      return localFlurryEventRecordStatus1;
    }
    try
    {
      FlurryEventRecordStatus localFlurryEventRecordStatus2 = ep.a().a(paramString);
      return localFlurryEventRecordStatus2;
    }
    catch (Throwable localThrowable)
    {
      gb.a(a, "Failed to log event: " + paramString, localThrowable);
    }
    return localFlurryEventRecordStatus1;
  }
  
  public static FlurryEventRecordStatus logEvent(String paramString, Map<String, String> paramMap)
  {
    FlurryEventRecordStatus localFlurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return localFlurryEventRecordStatus;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to logEvent was null.");
      return localFlurryEventRecordStatus;
    }
    if (paramMap == null)
    {
      gb.b(a, "String parameters passed to logEvent was null.");
      return localFlurryEventRecordStatus;
    }
    try
    {
      paramMap = ep.a().a(paramString, paramMap);
      return paramMap;
    }
    catch (Throwable paramMap)
    {
      gb.a(a, "Failed to log event: " + paramString, paramMap);
    }
    return localFlurryEventRecordStatus;
  }
  
  public static FlurryEventRecordStatus logEvent(String paramString, Map<String, String> paramMap, boolean paramBoolean)
  {
    FlurryEventRecordStatus localFlurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return localFlurryEventRecordStatus;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to logEvent was null.");
      return localFlurryEventRecordStatus;
    }
    if (paramMap == null)
    {
      gb.b(a, "String parameters passed to logEvent was null.");
      return localFlurryEventRecordStatus;
    }
    try
    {
      paramMap = ep.a().a(paramString, paramMap, paramBoolean);
      return paramMap;
    }
    catch (Throwable paramMap)
    {
      gb.a(a, "Failed to log event: " + paramString, paramMap);
    }
    return localFlurryEventRecordStatus;
  }
  
  public static FlurryEventRecordStatus logEvent(String paramString, boolean paramBoolean)
  {
    FlurryEventRecordStatus localFlurryEventRecordStatus1 = FlurryEventRecordStatus.kFlurryEventFailed;
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return localFlurryEventRecordStatus1;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to logEvent was null.");
      return localFlurryEventRecordStatus1;
    }
    try
    {
      FlurryEventRecordStatus localFlurryEventRecordStatus2 = ep.a().a(paramString, paramBoolean);
      return localFlurryEventRecordStatus2;
    }
    catch (Throwable localThrowable)
    {
      gb.a(a, "Failed to log event: " + paramString, localThrowable);
    }
    return localFlurryEventRecordStatus1;
  }
  
  public static void onEndSession(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramContext == null) {
      throw new NullPointerException("Null context");
    }
    if ((paramContext instanceof Application)) {
      throw new IllegalArgumentException("Cannot end a session with an Application context");
    }
    if (fn.a() == null) {
      throw new IllegalStateException("Flurry SDK must be initialized before ending a session");
    }
    try
    {
      gw.a().c(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      gb.a(a, "", paramContext);
    }
  }
  
  @Deprecated
  public static void onError(String paramString1, String paramString2, String paramString3)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString1 == null)
    {
      gb.b(a, "String errorId passed to onError was null.");
      return;
    }
    if (paramString2 == null)
    {
      gb.b(a, "String message passed to onError was null.");
      return;
    }
    if (paramString3 == null)
    {
      gb.b(a, "String errorClass passed to onError was null.");
      return;
    }
    try
    {
      ep.a().a(paramString1, paramString2, paramString3);
      return;
    }
    catch (Throwable paramString1)
    {
      gb.a(a, "", paramString1);
    }
  }
  
  public static void onError(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString1 == null)
    {
      gb.b(a, "String errorId passed to onError was null.");
      return;
    }
    if (paramString2 == null)
    {
      gb.b(a, "String message passed to onError was null.");
      return;
    }
    if (paramThrowable == null)
    {
      gb.b(a, "Throwable passed to onError was null.");
      return;
    }
    try
    {
      ep.a().a(paramString1, paramString2, paramThrowable);
      return;
    }
    catch (Throwable paramString1)
    {
      gb.a(a, "", paramString1);
    }
  }
  
  @Deprecated
  public static void onEvent(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to onEvent was null.");
      return;
    }
    try
    {
      ep.a().c(paramString);
      return;
    }
    catch (Throwable paramString)
    {
      gb.a(a, "", paramString);
    }
  }
  
  @Deprecated
  public static void onEvent(String paramString, Map<String, String> paramMap)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      gb.b(a, "String eventId passed to onEvent was null.");
      return;
    }
    if (paramMap == null)
    {
      gb.b(a, "Parameters Map passed to onEvent was null.");
      return;
    }
    try
    {
      ep.a().c(paramString, paramMap);
      return;
    }
    catch (Throwable paramString)
    {
      gb.a(a, "", paramString);
    }
  }
  
  public static void onPageView()
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    try
    {
      ep.a().e();
      return;
    }
    catch (Throwable localThrowable)
    {
      gb.a(a, "", localThrowable);
    }
  }
  
  public static void onStartSession(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramContext == null) {
      throw new NullPointerException("Null context");
    }
    if ((paramContext instanceof Application)) {
      throw new NullPointerException("Cannot start a session with an Application context");
    }
    if (fn.a() == null) {
      throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
    }
    try
    {
      gw.a().b(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      gb.a(a, "", paramContext);
    }
  }
  
  @Deprecated
  public static void onStartSession(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramContext == null) {
      throw new NullPointerException("Null context");
    }
    if ((paramContext instanceof Application)) {
      throw new NullPointerException("Cannot start a session with an Application context");
    }
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new IllegalArgumentException("Api key not specified");
    }
    if (fn.a() == null) {
      throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
    }
    try
    {
      gw.a().b(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      gb.a(a, "", paramContext);
    }
  }
  
  public static void setAge(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 10) {
      gb.b(a, "Device SDK Version older than 10");
    }
    while ((paramInt <= 0) || (paramInt >= 110)) {
      return;
    }
    long l = new Date(new Date(System.currentTimeMillis() - paramInt * 31449600000L).getYear(), 1, 1).getTime();
    ha.a().a("Age", Long.valueOf(l));
  }
  
  public static void setCaptureUncaughtExceptions(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    ha.a().a("CaptureUncaughtExceptions", Boolean.valueOf(paramBoolean));
  }
  
  public static void setContinueSessionMillis(long paramLong)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramLong < 5000L)
    {
      gb.b(a, "Invalid time set for session resumption: " + paramLong);
      return;
    }
    ha.a().a("ContinueSessionMillis", Long.valueOf(paramLong));
  }
  
  public static void setGender(byte paramByte)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    switch (paramByte)
    {
    default: 
      ha.a().a("Gender", Byte.valueOf((byte)-1));
      return;
    }
    ha.a().a("Gender", Byte.valueOf(paramByte));
  }
  
  public static void setLocation(float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    Location localLocation = new Location("Explicit");
    localLocation.setLatitude(paramFloat1);
    localLocation.setLongitude(paramFloat2);
    ha.a().a("ExplicitLocation", localLocation);
  }
  
  public static void setLocationCriteria(Criteria paramCriteria)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    ha.a().a("LocationCriteria", paramCriteria);
  }
  
  public static void setLogEnabled(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramBoolean)
    {
      gb.b();
      return;
    }
    gb.a();
  }
  
  public static void setLogEvents(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    ha.a().a("LogEvents", Boolean.valueOf(paramBoolean));
  }
  
  public static void setLogLevel(int paramInt)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    gb.a(paramInt);
  }
  
  public static void setReportLocation(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    ha.a().a("ReportLocation", Boolean.valueOf(paramBoolean));
  }
  
  public static void setUserId(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      gb.b(a, "String userId passed to setUserId was null.");
      return;
    }
    ha.a().a("UserId", hj.b(paramString));
  }
  
  public static void setVersionName(String paramString)
  {
    if (Build.VERSION.SDK_INT < 10)
    {
      gb.b(a, "Device SDK Version older than 10");
      return;
    }
    if (paramString == null)
    {
      gb.b(a, "String versionName passed to setVersionName was null.");
      return;
    }
    ha.a().a("VersionName", paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/flurry/android/FlurryAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */