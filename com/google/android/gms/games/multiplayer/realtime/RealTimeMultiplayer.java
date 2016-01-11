package com.google.android.gms.games.multiplayer.realtime;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

public abstract interface RealTimeMultiplayer
{
  public static final int REAL_TIME_MESSAGE_FAILED = -1;
  
  public abstract void create(GoogleApiClient paramGoogleApiClient, RoomConfig paramRoomConfig);
  
  public abstract void declineInvitation(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract void dismissInvitation(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2);
  
  public abstract Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract RealTimeSocket getSocketForParticipant(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);
  
  public abstract Intent getWaitingRoomIntent(GoogleApiClient paramGoogleApiClient, Room paramRoom, int paramInt);
  
  public abstract void join(GoogleApiClient paramGoogleApiClient, RoomConfig paramRoomConfig);
  
  public abstract void leave(GoogleApiClient paramGoogleApiClient, RoomUpdateListener paramRoomUpdateListener, String paramString);
  
  public abstract int sendReliableMessage(GoogleApiClient paramGoogleApiClient, ReliableMessageSentCallback paramReliableMessageSentCallback, byte[] paramArrayOfByte, String paramString1, String paramString2);
  
  public abstract int sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, byte[] paramArrayOfByte, String paramString1, String paramString2);
  
  public abstract int sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, byte[] paramArrayOfByte, String paramString, List<String> paramList);
  
  public abstract int sendUnreliableMessageToOthers(GoogleApiClient paramGoogleApiClient, byte[] paramArrayOfByte, String paramString);
  
  public static abstract interface ReliableMessageSentCallback
  {
    public abstract void onRealTimeMessageSent(int paramInt1, int paramInt2, String paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/realtime/RealTimeMultiplayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */