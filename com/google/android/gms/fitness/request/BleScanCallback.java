package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;

public abstract class BleScanCallback
{
  public abstract void onDeviceFound(BleDevice paramBleDevice);
  
  public abstract void onScanStopped();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/BleScanCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */