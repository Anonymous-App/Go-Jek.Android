package com.google.android.gms.analytics;

import android.content.Context;
import java.util.ArrayList;

public class ExceptionReporter
  implements Thread.UncaughtExceptionHandler
{
  private final Context mContext;
  private final Thread.UncaughtExceptionHandler xX;
  private final Tracker xY;
  private ExceptionParser xZ;
  
  public ExceptionReporter(Tracker paramTracker, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler, Context paramContext)
  {
    if (paramTracker == null) {
      throw new NullPointerException("tracker cannot be null");
    }
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    this.xX = paramUncaughtExceptionHandler;
    this.xY = paramTracker;
    this.xZ = new StandardExceptionParser(paramContext, new ArrayList());
    this.mContext = paramContext.getApplicationContext();
    paramContext = new StringBuilder().append("ExceptionReporter created, original handler is ");
    if (paramUncaughtExceptionHandler == null) {}
    for (paramTracker = "null";; paramTracker = paramUncaughtExceptionHandler.getClass().getName())
    {
      z.V(paramTracker);
      return;
    }
  }
  
  Thread.UncaughtExceptionHandler dY()
  {
    return this.xX;
  }
  
  public ExceptionParser getExceptionParser()
  {
    return this.xZ;
  }
  
  public void setExceptionParser(ExceptionParser paramExceptionParser)
  {
    this.xZ = paramExceptionParser;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    String str = "UncaughtException";
    if (this.xZ != null) {
      if (paramThread == null) {
        break label112;
      }
    }
    label112:
    for (str = paramThread.getName();; str = null)
    {
      str = this.xZ.getDescription(str, paramThrowable);
      z.V("Tracking Exception: " + str);
      this.xY.send(new HitBuilders.ExceptionBuilder().setDescription(str).setFatal(true).build());
      GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
      if (this.xX != null)
      {
        z.V("Passing exception to original handler.");
        this.xX.uncaughtException(paramThread, paramThrowable);
      }
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/ExceptionReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */