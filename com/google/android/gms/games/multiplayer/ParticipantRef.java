package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef
  extends d
  implements Participant
{
  private final PlayerRef aci;
  
  public ParticipantRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.aci = new PlayerRef(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return ParticipantEntity.a(this, paramObject);
  }
  
  public Participant freeze()
  {
    return new ParticipantEntity(this);
  }
  
  public int getCapabilities()
  {
    return getInteger("capabilities");
  }
  
  public String getDisplayName()
  {
    if (aS("external_player_id")) {
      return getString("default_display_name");
    }
    return this.aci.getDisplayName();
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (aS("external_player_id"))
    {
      a("default_display_name", paramCharArrayBuffer);
      return;
    }
    this.aci.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    if (aS("external_player_id")) {
      return aR("default_display_hi_res_image_uri");
    }
    return this.aci.getHiResImageUri();
  }
  
  public String getHiResImageUrl()
  {
    if (aS("external_player_id")) {
      return getString("default_display_hi_res_image_url");
    }
    return this.aci.getHiResImageUrl();
  }
  
  public Uri getIconImageUri()
  {
    if (aS("external_player_id")) {
      return aR("default_display_image_uri");
    }
    return this.aci.getIconImageUri();
  }
  
  public String getIconImageUrl()
  {
    if (aS("external_player_id")) {
      return getString("default_display_image_url");
    }
    return this.aci.getIconImageUrl();
  }
  
  public String getParticipantId()
  {
    return getString("external_participant_id");
  }
  
  public Player getPlayer()
  {
    if (aS("external_player_id")) {
      return null;
    }
    return this.aci;
  }
  
  public ParticipantResult getResult()
  {
    if (aS("result_type")) {
      return null;
    }
    int i = getInteger("result_type");
    int j = getInteger("placing");
    return new ParticipantResult(getParticipantId(), i, j);
  }
  
  public int getStatus()
  {
    return getInteger("player_status");
  }
  
  public int hashCode()
  {
    return ParticipantEntity.a(this);
  }
  
  public boolean isConnectedToRoom()
  {
    return getInteger("connected") > 0;
  }
  
  public String jX()
  {
    return getString("client_address");
  }
  
  public String toString()
  {
    return ParticipantEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((ParticipantEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/ParticipantRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */