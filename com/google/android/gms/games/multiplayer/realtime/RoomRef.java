package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

public final class RoomRef
  extends d
  implements Room
{
  private final int aaK;
  
  RoomRef(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.aaK = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return RoomEntity.a(this, paramObject);
  }
  
  public Room freeze()
  {
    return new RoomEntity(this);
  }
  
  public Bundle getAutoMatchCriteria()
  {
    if (!getBoolean("has_automatch_criteria")) {
      return null;
    }
    return RoomConfig.createAutoMatchCriteria(getInteger("automatch_min_players"), getInteger("automatch_max_players"), getLong("automatch_bit_mask"));
  }
  
  public int getAutoMatchWaitEstimateSeconds()
  {
    return getInteger("automatch_wait_estimate_sec");
  }
  
  public long getCreationTimestamp()
  {
    return getLong("creation_timestamp");
  }
  
  public String getCreatorId()
  {
    return getString("creator_external");
  }
  
  public String getDescription()
  {
    return getString("description");
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    a("description", paramCharArrayBuffer);
  }
  
  public Participant getParticipant(String paramString)
  {
    return RoomEntity.c(this, paramString);
  }
  
  public String getParticipantId(String paramString)
  {
    return RoomEntity.b(this, paramString);
  }
  
  public ArrayList<String> getParticipantIds()
  {
    return RoomEntity.c(this);
  }
  
  public int getParticipantStatus(String paramString)
  {
    return RoomEntity.a(this, paramString);
  }
  
  public ArrayList<Participant> getParticipants()
  {
    ArrayList localArrayList = new ArrayList(this.aaK);
    int i = 0;
    while (i < this.aaK)
    {
      localArrayList.add(new ParticipantRef(this.II, this.JX + i));
      i += 1;
    }
    return localArrayList;
  }
  
  public String getRoomId()
  {
    return getString("external_match_id");
  }
  
  public int getStatus()
  {
    return getInteger("status");
  }
  
  public int getVariant()
  {
    return getInteger("variant");
  }
  
  public int hashCode()
  {
    return RoomEntity.a(this);
  }
  
  public String toString()
  {
    return RoomEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((RoomEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/realtime/RoomRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */