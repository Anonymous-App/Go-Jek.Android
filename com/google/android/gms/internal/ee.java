package com.google.android.gms.internal;

import android.content.Intent;

@ez
public class ee
{
  private final String oA;
  
  public ee(String paramString)
  {
    this.oA = paramString;
  }
  
  public boolean a(String paramString, int paramInt, Intent paramIntent)
  {
    if ((paramString == null) || (paramIntent == null)) {}
    String str;
    do
    {
      return false;
      str = ed.e(paramIntent);
      paramIntent = ed.f(paramIntent);
    } while ((str == null) || (paramIntent == null));
    if (!paramString.equals(ed.D(str)))
    {
      gs.W("Developer payload not match.");
      return false;
    }
    if ((this.oA != null) && (!ef.b(this.oA, str, paramIntent)))
    {
      gs.W("Fail to verify signature.");
      return false;
    }
    return true;
  }
  
  public String ct()
  {
    return gj.jdMethod_do();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */