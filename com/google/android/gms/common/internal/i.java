package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.internal.nf;

public final class i
{
  private final String Mf;
  
  public i(String paramString)
  {
    this.Mf = ((String)o.i(paramString));
  }
  
  public void a(Context paramContext, String paramString1, String paramString2, Throwable paramThrowable)
  {
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while ((i < arrayOfStackTraceElement.length) && (i < 2))
    {
      localStringBuilder.append(arrayOfStackTraceElement[i].toString());
      localStringBuilder.append("\n");
      i += 1;
    }
    paramContext = new nf(paramContext, 10);
    paramContext.a("GMS_WTF", null, new String[] { "GMS_WTF", localStringBuilder.toString() });
    paramContext.send();
    if (aC(7))
    {
      Log.e(paramString1, paramString2, paramThrowable);
      Log.wtf(paramString1, paramString2, paramThrowable);
    }
  }
  
  public void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (aC(4)) {
      Log.i(paramString1, paramString2, paramThrowable);
    }
  }
  
  public boolean aC(int paramInt)
  {
    return Log.isLoggable(this.Mf, paramInt);
  }
  
  public void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (aC(5)) {
      Log.w(paramString1, paramString2, paramThrowable);
    }
  }
  
  public void c(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (aC(6)) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
  }
  
  public void n(String paramString1, String paramString2)
  {
    if (aC(3)) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public void o(String paramString1, String paramString2)
  {
    if (aC(2)) {
      Log.v(paramString1, paramString2);
    }
  }
  
  public void p(String paramString1, String paramString2)
  {
    if (aC(5)) {
      Log.w(paramString1, paramString2);
    }
  }
  
  public void q(String paramString1, String paramString2)
  {
    if (aC(6)) {
      Log.e(paramString1, paramString2);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/internal/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */