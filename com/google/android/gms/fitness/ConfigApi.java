package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

public abstract interface ConfigApi
{
  public abstract PendingResult<DataTypeResult> createCustomDataType(GoogleApiClient paramGoogleApiClient, DataTypeCreateRequest paramDataTypeCreateRequest);
  
  public abstract PendingResult<Status> disableFit(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<DataTypeResult> readDataType(GoogleApiClient paramGoogleApiClient, String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/ConfigApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */