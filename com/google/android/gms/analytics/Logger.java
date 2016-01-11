package com.google.android.gms.analytics;

public abstract interface Logger
{
  public abstract void error(Exception paramException);
  
  public abstract void error(String paramString);
  
  public abstract int getLogLevel();
  
  public abstract void info(String paramString);
  
  public abstract void setLogLevel(int paramInt);
  
  public abstract void verbose(String paramString);
  
  public abstract void warn(String paramString);
  
  public static class LogLevel
  {
    public static final int ERROR = 3;
    public static final int INFO = 1;
    public static final int VERBOSE = 0;
    public static final int WARNING = 2;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */