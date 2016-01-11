package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.request.Requests.LoadRequestSummariesResult;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.SendRequestResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class RequestsImpl
  implements Requests
{
  public PendingResult<Requests.UpdateRequestsResult> acceptRequest(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return acceptRequests(paramGoogleApiClient, localArrayList);
  }
  
  public PendingResult<Requests.UpdateRequestsResult> acceptRequests(GoogleApiClient paramGoogleApiClient, final List<String> paramList)
  {
    if (paramList == null) {}
    for (paramList = null;; paramList = (String[])paramList.toArray(new String[paramList.size()])) {
      paramGoogleApiClient.b(new UpdateRequestsImpl(paramList)
      {
        protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
        {
          paramAnonymousGamesClientImpl.b(this, paramList);
        }
      });
    }
  }
  
  public PendingResult<Requests.UpdateRequestsResult> dismissRequest(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return dismissRequests(paramGoogleApiClient, localArrayList);
  }
  
  public PendingResult<Requests.UpdateRequestsResult> dismissRequests(GoogleApiClient paramGoogleApiClient, final List<String> paramList)
  {
    if (paramList == null) {}
    for (paramList = null;; paramList = (String[])paramList.toArray(new String[paramList.size()])) {
      paramGoogleApiClient.b(new UpdateRequestsImpl(paramList)
      {
        protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
        {
          paramAnonymousGamesClientImpl.c(this, paramList);
        }
      });
    }
  }
  
  public ArrayList<GameRequest> getGameRequestsFromBundle(Bundle paramBundle)
  {
    if ((paramBundle == null) || (!paramBundle.containsKey("requests"))) {
      return new ArrayList();
    }
    paramBundle = (ArrayList)paramBundle.get("requests");
    ArrayList localArrayList = new ArrayList();
    int j = paramBundle.size();
    int i = 0;
    while (i < j)
    {
      localArrayList.add((GameRequest)paramBundle.get(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public ArrayList<GameRequest> getGameRequestsFromInboxResponse(Intent paramIntent)
  {
    if (paramIntent == null) {
      return new ArrayList();
    }
    return getGameRequestsFromBundle(paramIntent.getExtras());
  }
  
  public Intent getInboxIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).kr();
  }
  
  public int getMaxLifetimeDays(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).kt();
  }
  
  public int getMaxPayloadSize(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).ks();
  }
  
  public Intent getSendIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, byte[] paramArrayOfByte, int paramInt2, Bitmap paramBitmap, String paramString)
  {
    return Games.c(paramGoogleApiClient).a(paramInt1, paramArrayOfByte, paramInt2, paramBitmap, paramString);
  }
  
  public PendingResult<Requests.LoadRequestsResult> loadRequests(GoogleApiClient paramGoogleApiClient, final int paramInt1, final int paramInt2, final int paramInt3)
  {
    paramGoogleApiClient.a(new LoadRequestsImpl(paramInt1)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramInt1, paramInt2, paramInt3);
      }
    });
  }
  
  public void registerRequestListener(GoogleApiClient paramGoogleApiClient, OnRequestReceivedListener paramOnRequestReceivedListener)
  {
    Games.c(paramGoogleApiClient).a(paramOnRequestReceivedListener);
  }
  
  public void unregisterRequestListener(GoogleApiClient paramGoogleApiClient)
  {
    Games.c(paramGoogleApiClient).kl();
  }
  
  private static abstract class LoadRequestSummariesImpl
    extends Games.BaseGamesApiMethodImpl<Requests.LoadRequestSummariesResult>
  {
    public Requests.LoadRequestSummariesResult ak(final Status paramStatus)
    {
      new Requests.LoadRequestSummariesResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class LoadRequestsImpl
    extends Games.BaseGamesApiMethodImpl<Requests.LoadRequestsResult>
  {
    public Requests.LoadRequestsResult al(final Status paramStatus)
    {
      new Requests.LoadRequestsResult()
      {
        public GameRequestBuffer getRequests(int paramAnonymousInt)
        {
          return new GameRequestBuffer(DataHolder.as(paramStatus.getStatusCode()));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class SendRequestImpl
    extends Games.BaseGamesApiMethodImpl<Requests.SendRequestResult>
  {
    public Requests.SendRequestResult am(final Status paramStatus)
    {
      new Requests.SendRequestResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class UpdateRequestsImpl
    extends Games.BaseGamesApiMethodImpl<Requests.UpdateRequestsResult>
  {
    public Requests.UpdateRequestsResult an(final Status paramStatus)
    {
      new Requests.UpdateRequestsResult()
      {
        public Set<String> getRequestIds()
        {
          return null;
        }
        
        public int getRequestOutcome(String paramAnonymousString)
        {
          throw new IllegalArgumentException("Unknown request ID " + paramAnonymousString);
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/RequestsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */