package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.MessageApi.SendMessageResult;

public final class ag
  implements MessageApi
{
  private PendingResult<Status> a(GoogleApiClient paramGoogleApiClient, final MessageApi.MessageListener paramMessageListener, final IntentFilter[] paramArrayOfIntentFilter)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.a(this, paramMessageListener, paramArrayOfIntentFilter);
      }
      
      public Status d(Status paramAnonymousStatus)
      {
        return new Status(13);
      }
    });
  }
  
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, MessageApi.MessageListener paramMessageListener)
  {
    return a(paramGoogleApiClient, paramMessageListener, null);
  }
  
  public PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, final MessageApi.MessageListener paramMessageListener)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.a(this, paramMessageListener);
      }
      
      public Status d(Status paramAnonymousStatus)
      {
        return new Status(13);
      }
    });
  }
  
  public PendingResult<MessageApi.SendMessageResult> sendMessage(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final byte[] paramArrayOfByte)
  {
    paramGoogleApiClient.a(new d()
    {
      protected void a(aw paramAnonymousaw)
        throws RemoteException
      {
        paramAnonymousaw.a(this, paramString1, paramString2, paramArrayOfByte);
      }
      
      protected MessageApi.SendMessageResult aJ(Status paramAnonymousStatus)
      {
        return new ag.a(paramAnonymousStatus, -1);
      }
    });
  }
  
  public static class a
    implements MessageApi.SendMessageResult
  {
    private final Status CM;
    private final int uQ;
    
    public a(Status paramStatus, int paramInt)
    {
      this.CM = paramStatus;
      this.uQ = paramInt;
    }
    
    public int getRequestId()
    {
      return this.uQ;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */