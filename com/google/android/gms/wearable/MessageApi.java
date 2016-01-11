package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract interface MessageApi
{
  public static final int UNKNOWN_REQUEST_ID = -1;
  
  public abstract PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, MessageListener paramMessageListener);
  
  public abstract PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, MessageListener paramMessageListener);
  
  public abstract PendingResult<SendMessageResult> sendMessage(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2, byte[] paramArrayOfByte);
  
  public static abstract interface MessageListener
  {
    public abstract void onMessageReceived(MessageEvent paramMessageEvent);
  }
  
  public static abstract interface SendMessageResult
    extends Result
  {
    public abstract int getRequestId();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/MessageApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */