package com.google.android.gms.internal;

import android.os.Bundle;

@ez
public final class bn
{
  public static iv<String> oX;
  public static iv<String> oY;
  public static iv<Boolean> oZ;
  public static iv<Boolean> pa;
  public static iv<Boolean> pb;
  public static iv<String> pc;
  public static iv<Boolean> pd = c("gads:enable_content_fetching", false);
  public static iv<Integer> pe = a("gads:content_length_weight", 1);
  public static iv<Integer> pf = a("gads:content_age_weight", 1);
  public static iv<Integer> pg = a("gads:min_content_len", 11);
  public static iv<Integer> ph = a("gads:fingerprint_number", 10);
  public static iv<Integer> pi = a("gads:sleep_sec", 10);
  private static final Bundle pj = new Bundle();
  private static boolean pk = false;
  
  static
  {
    oX = a("gads:sdk_core_location", "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/sdk-core-v40.html");
    oY = a("gads:sdk_core_experiment_id", (String)null);
    oZ = c("gads:sdk_crash_report_enabled", false);
    pa = c("gads:sdk_crash_report_full_stacktrace", false);
    pb = c("gads:block_autoclicks", false);
    pc = a("gads:block_autoclicks_experiment_id", (String)null);
    pk = true;
  }
  
  private static iv<Integer> a(String paramString, int paramInt)
  {
    pj.putInt(paramString, paramInt);
    return iv.a(paramString, Integer.valueOf(paramInt));
  }
  
  private static iv<String> a(String paramString1, String paramString2)
  {
    pj.putString(paramString1, paramString2);
    return iv.m(paramString1, paramString2);
  }
  
  public static Bundle bs()
  {
    return pj;
  }
  
  private static iv<Boolean> c(String paramString, boolean paramBoolean)
  {
    pj.putBoolean(paramString, paramBoolean);
    return iv.g(paramString, paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/bn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */