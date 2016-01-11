package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

public abstract interface RecordingApi
{
  public abstract PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient paramGoogleApiClient, DataType paramDataType);
  
  public abstract PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, DataSource paramDataSource);
  
  public abstract PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, DataType paramDataType);
  
  public abstract PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, DataSource paramDataSource);
  
  public abstract PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, DataType paramDataType);
  
  public abstract PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, Subscription paramSubscription);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/RecordingApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */