package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;

public abstract interface BleApi
{
  public abstract PendingResult<Status> claimBleDevice(GoogleApiClient paramGoogleApiClient, BleDevice paramBleDevice);
  
  public abstract PendingResult<Status> claimBleDevice(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<Status> startBleScan(GoogleApiClient paramGoogleApiClient, StartBleScanRequest paramStartBleScanRequest);
  
  public abstract PendingResult<Status> stopBleScan(GoogleApiClient paramGoogleApiClient, BleScanCallback paramBleScanCallback);
  
  public abstract PendingResult<Status> unclaimBleDevice(GoogleApiClient paramGoogleApiClient, BleDevice paramBleDevice);
  
  public abstract PendingResult<Status> unclaimBleDevice(GoogleApiClient paramGoogleApiClient, String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/BleApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */