package com.flurry.android;

public enum FlurryEventRecordStatus
{
  static
  {
    kFlurryEventParamsCountExceeded = new FlurryEventRecordStatus("kFlurryEventParamsCountExceeded", 3);
    kFlurryEventLogCountExceeded = new FlurryEventRecordStatus("kFlurryEventLogCountExceeded", 4);
    kFlurryEventLoggingDelayed = new FlurryEventRecordStatus("kFlurryEventLoggingDelayed", 5);
  }
  
  private FlurryEventRecordStatus() {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/flurry/android/FlurryEventRecordStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */