package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.internal.ik;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

public final class CastMediaControlIntent
{
  public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
  @Deprecated
  public static final String CATEGORY_CAST = "com.google.android.gms.cast.CATEGORY_CAST";
  public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
  public static final int ERROR_CODE_REQUEST_FAILED = 1;
  public static final int ERROR_CODE_SESSION_START_FAILED = 2;
  public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;
  public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
  public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";
  public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
  public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
  public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
  public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
  public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";
  
  private static String a(String paramString1, String paramString2, Collection<String> paramCollection)
    throws IllegalArgumentException
  {
    paramString1 = new StringBuffer(paramString1);
    Object localObject;
    if (paramString2 != null)
    {
      localObject = paramString2.toUpperCase();
      if (!((String)localObject).matches("[A-F0-9]+")) {
        throw new IllegalArgumentException("Invalid application ID: " + paramString2);
      }
      paramString1.append("/").append((String)localObject);
    }
    if (paramCollection != null)
    {
      if (paramCollection.isEmpty()) {
        throw new IllegalArgumentException("Must specify at least one namespace");
      }
      localObject = paramCollection.iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        if ((TextUtils.isEmpty(str)) || (str.trim().equals(""))) {
          throw new IllegalArgumentException("Namespaces must not be null or empty");
        }
      }
      if (paramString2 == null) {
        paramString1.append("/");
      }
      paramString1.append("/").append(TextUtils.join(",", paramCollection));
    }
    return paramString1.toString();
  }
  
  public static String categoryForCast(String paramString)
    throws IllegalArgumentException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("applicationId cannot be null");
    }
    return a("com.google.android.gms.cast.CATEGORY_CAST", paramString, null);
  }
  
  public static String categoryForCast(String paramString, Collection<String> paramCollection)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("applicationId cannot be null");
    }
    if (paramCollection == null) {
      throw new IllegalArgumentException("namespaces cannot be null");
    }
    return a("com.google.android.gms.cast.CATEGORY_CAST", paramString, paramCollection);
  }
  
  public static String categoryForCast(Collection<String> paramCollection)
    throws IllegalArgumentException
  {
    if (paramCollection == null) {
      throw new IllegalArgumentException("namespaces cannot be null");
    }
    return a("com.google.android.gms.cast.CATEGORY_CAST", null, paramCollection);
  }
  
  public static String categoryForRemotePlayback()
  {
    return a("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", null, null);
  }
  
  public static String categoryForRemotePlayback(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("applicationId cannot be null or empty");
    }
    return a("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK", paramString, null);
  }
  
  public static String languageTagForLocale(Locale paramLocale)
  {
    return ik.b(paramLocale);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/CastMediaControlIntent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */