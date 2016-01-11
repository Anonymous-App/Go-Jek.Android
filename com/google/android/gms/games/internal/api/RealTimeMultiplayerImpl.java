package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import java.util.List;

public final class RealTimeMultiplayerImpl
  implements RealTimeMultiplayer
{
  public void create(GoogleApiClient paramGoogleApiClient, RoomConfig paramRoomConfig)
  {
    Games.c(paramGoogleApiClient).a(paramRoomConfig);
  }
  
  public void declineInvitation(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    Games.c(paramGoogleApiClient).p(paramString, 0);
  }
  
  public void dismissInvitation(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    Games.c(paramGoogleApiClient).o(paramString, 0);
  }
  
  public Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2)
  {
    return Games.c(paramGoogleApiClient).b(paramInt1, paramInt2, true);
  }
  
  public Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return Games.c(paramGoogleApiClient).b(paramInt1, paramInt2, paramBoolean);
  }
  
  public RealTimeSocket getSocketForParticipant(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2)
  {
    return Games.c(paramGoogleApiClient).t(paramString1, paramString2);
  }
  
  public Intent getWaitingRoomIntent(GoogleApiClient paramGoogleApiClient, Room paramRoom, int paramInt)
  {
    return Games.c(paramGoogleApiClient).a(paramRoom, paramInt);
  }
  
  public void join(GoogleApiClient paramGoogleApiClient, RoomConfig paramRoomConfig)
  {
    Games.c(paramGoogleApiClient).b(paramRoomConfig);
  }
  
  public void leave(GoogleApiClient paramGoogleApiClient, RoomUpdateListener paramRoomUpdateListener, String paramString)
  {
    Games.c(paramGoogleApiClient).a(paramRoomUpdateListener, paramString);
  }
  
  public int sendReliableMessage(GoogleApiClient paramGoogleApiClient, RealTimeMultiplayer.ReliableMessageSentCallback paramReliableMessageSentCallback, byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    return Games.c(paramGoogleApiClient).a(paramReliableMessageSentCallback, paramArrayOfByte, paramString1, paramString2);
  }
  
  public int sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    return Games.c(paramGoogleApiClient).a(paramArrayOfByte, paramString1, new String[] { paramString2 });
  }
  
  public int sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, byte[] paramArrayOfByte, String paramString, List<String> paramList)
  {
    paramList = (String[])paramList.toArray(new String[paramList.size()]);
    return Games.c(paramGoogleApiClient).a(paramArrayOfByte, paramString, paramList);
  }
  
  public int sendUnreliableMessageToOthers(GoogleApiClient paramGoogleApiClient, byte[] paramArrayOfByte, String paramString)
  {
    return Games.c(paramGoogleApiClient).d(paramArrayOfByte, paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/RealTimeMultiplayerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */