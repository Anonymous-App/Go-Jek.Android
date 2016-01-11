package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.internal.q;

public final class ExecutionOptions
{
  public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
  public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
  public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
  private final String Nn;
  private final boolean No;
  private final int Np;
  
  private ExecutionOptions(String paramString, boolean paramBoolean, int paramInt)
  {
    this.Nn = paramString;
    this.No = paramBoolean;
    this.Np = paramInt;
  }
  
  public static void a(GoogleApiClient paramGoogleApiClient, ExecutionOptions paramExecutionOptions)
  {
    paramGoogleApiClient = (q)paramGoogleApiClient.a(Drive.CU);
    if ((paramExecutionOptions.hP()) && (!paramGoogleApiClient.ib())) {
      throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
    }
  }
  
  public static boolean aV(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean aW(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean bh(String paramString)
  {
    return (paramString != null) && (!paramString.isEmpty()) && (paramString.length() <= 65536);
  }
  
  public String hO()
  {
    return this.Nn;
  }
  
  public boolean hP()
  {
    return this.No;
  }
  
  public int hQ()
  {
    return this.Np;
  }
  
  public static final class Builder
  {
    private String Nn;
    private boolean No;
    private int Np = 0;
    
    public ExecutionOptions build()
    {
      if ((this.Np == 1) && (!this.No)) {
        throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
      }
      return new ExecutionOptions(this.Nn, this.No, this.Np, null);
    }
    
    public Builder setConflictStrategy(int paramInt)
    {
      if (!ExecutionOptions.aW(paramInt)) {
        throw new IllegalArgumentException("Unrecognized value for conflict strategy: " + paramInt);
      }
      this.Np = paramInt;
      return this;
    }
    
    public Builder setNotifyOnCompletion(boolean paramBoolean)
    {
      this.No = paramBoolean;
      return this;
    }
    
    public Builder setTrackingTag(String paramString)
    {
      if (!ExecutionOptions.bh(paramString)) {
        throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", new Object[] { Integer.valueOf(65536) }));
      }
      this.Nn = paramString;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/ExecutionOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */