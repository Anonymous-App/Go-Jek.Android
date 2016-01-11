package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;

public class nf
  implements ng.a
{
  private final ng akD;
  private boolean akE;
  
  public nf(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, null);
  }
  
  public nf(Context paramContext, int paramInt, String paramString)
  {
    this(paramContext, paramInt, paramString, null, true);
  }
  
  public nf(Context paramContext, int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.akD = new ng(paramContext, paramInt, paramString1, paramString2, this, paramBoolean);
    this.akE = true;
  }
  
  private void mT()
  {
    if (!this.akE) {
      throw new IllegalStateException("Cannot reuse one-time logger after sending.");
    }
  }
  
  public void a(String paramString, byte[] paramArrayOfByte, String... paramVarArgs)
  {
    mT();
    this.akD.b(paramString, paramArrayOfByte, paramVarArgs);
  }
  
  public void b(PendingIntent paramPendingIntent)
  {
    Log.w("OneTimePlayLogger", "logger connection failed: " + paramPendingIntent);
  }
  
  public void mU()
  {
    this.akD.stop();
  }
  
  public void mV()
  {
    Log.w("OneTimePlayLogger", "logger connection failed");
  }
  
  public void send()
  {
    mT();
    this.akD.start();
    this.akE = false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/nf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */