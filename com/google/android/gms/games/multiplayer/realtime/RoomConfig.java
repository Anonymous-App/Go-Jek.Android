package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.common.internal.o;
import java.util.ArrayList;
import java.util.Arrays;

public final class RoomConfig
{
  private final String WO;
  private final int acd;
  private final RoomUpdateListener aco;
  private final RoomStatusUpdateListener acp;
  private final RealTimeMessageReceivedListener acq;
  private final String[] acr;
  private final Bundle acs;
  private final boolean act;
  
  private RoomConfig(Builder paramBuilder)
  {
    this.aco = paramBuilder.aco;
    this.acp = paramBuilder.acp;
    this.acq = paramBuilder.acq;
    this.WO = paramBuilder.acu;
    this.acd = paramBuilder.acd;
    this.acs = paramBuilder.acs;
    this.act = paramBuilder.act;
    int i = paramBuilder.acv.size();
    this.acr = ((String[])paramBuilder.acv.toArray(new String[i]));
    if (this.acq == null) {
      o.a(this.act, "Must either enable sockets OR specify a message listener");
    }
  }
  
  public static Builder builder(RoomUpdateListener paramRoomUpdateListener)
  {
    return new Builder(paramRoomUpdateListener, null);
  }
  
  public static Bundle createAutoMatchCriteria(int paramInt1, int paramInt2, long paramLong)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("min_automatch_players", paramInt1);
    localBundle.putInt("max_automatch_players", paramInt2);
    localBundle.putLong("exclusive_bit_mask", paramLong);
    return localBundle;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.acs;
  }
  
  public String getInvitationId()
  {
    return this.WO;
  }
  
  public String[] getInvitedPlayerIds()
  {
    return this.acr;
  }
  
  public RealTimeMessageReceivedListener getMessageReceivedListener()
  {
    return this.acq;
  }
  
  public RoomStatusUpdateListener getRoomStatusUpdateListener()
  {
    return this.acp;
  }
  
  public RoomUpdateListener getRoomUpdateListener()
  {
    return this.aco;
  }
  
  public int getVariant()
  {
    return this.acd;
  }
  
  public boolean isSocketEnabled()
  {
    return this.act;
  }
  
  public static final class Builder
  {
    int acd = -1;
    final RoomUpdateListener aco;
    RoomStatusUpdateListener acp;
    RealTimeMessageReceivedListener acq;
    Bundle acs;
    boolean act = false;
    String acu = null;
    ArrayList<String> acv = new ArrayList();
    
    private Builder(RoomUpdateListener paramRoomUpdateListener)
    {
      this.aco = ((RoomUpdateListener)o.b(paramRoomUpdateListener, "Must provide a RoomUpdateListener"));
    }
    
    public Builder addPlayersToInvite(ArrayList<String> paramArrayList)
    {
      o.i(paramArrayList);
      this.acv.addAll(paramArrayList);
      return this;
    }
    
    public Builder addPlayersToInvite(String... paramVarArgs)
    {
      o.i(paramVarArgs);
      this.acv.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public RoomConfig build()
    {
      return new RoomConfig(this, null);
    }
    
    public Builder setAutoMatchCriteria(Bundle paramBundle)
    {
      this.acs = paramBundle;
      return this;
    }
    
    public Builder setInvitationIdToAccept(String paramString)
    {
      o.i(paramString);
      this.acu = paramString;
      return this;
    }
    
    public Builder setMessageReceivedListener(RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
    {
      this.acq = paramRealTimeMessageReceivedListener;
      return this;
    }
    
    public Builder setRoomStatusUpdateListener(RoomStatusUpdateListener paramRoomStatusUpdateListener)
    {
      this.acp = paramRoomStatusUpdateListener;
      return this;
    }
    
    public Builder setSocketCommunicationEnabled(boolean paramBoolean)
    {
      this.act = paramBoolean;
      return this;
    }
    
    public Builder setVariant(int paramInt)
    {
      if ((paramInt == -1) || (paramInt > 0)) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
        this.acd = paramInt;
        return this;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/realtime/RoomConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */