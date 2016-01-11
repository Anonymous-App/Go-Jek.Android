package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataRef;
import java.util.ArrayList;

public class ExtendedGameRef
  extends d
  implements ExtendedGame
{
  private final SnapshotMetadataRef aaJ;
  private final int aaK;
  private final GameRef aax;
  
  ExtendedGameRef(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.aax = new GameRef(paramDataHolder, paramInt1);
    this.aaK = paramInt2;
    if ((aQ("external_snapshot_id")) && (!aS("external_snapshot_id")))
    {
      this.aaJ = new SnapshotMetadataRef(paramDataHolder, paramInt1);
      return;
    }
    this.aaJ = null;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return ExtendedGameEntity.a(this, paramObject);
  }
  
  public Game getGame()
  {
    return this.aax;
  }
  
  public int hashCode()
  {
    return ExtendedGameEntity.a(this);
  }
  
  public ArrayList<GameBadge> kR()
  {
    int i = 0;
    if (this.II.c("badge_title", this.JX, this.II.ar(this.JX)) == null) {
      return new ArrayList(0);
    }
    ArrayList localArrayList = new ArrayList(this.aaK);
    while (i < this.aaK)
    {
      localArrayList.add(new GameBadgeRef(this.II, this.JX + i));
      i += 1;
    }
    return localArrayList;
  }
  
  public int kS()
  {
    return getInteger("availability");
  }
  
  public boolean kT()
  {
    return getBoolean("owned");
  }
  
  public int kU()
  {
    return getInteger("achievement_unlocked_count");
  }
  
  public long kV()
  {
    return getLong("last_played_server_time");
  }
  
  public long kW()
  {
    return getLong("price_micros");
  }
  
  public String kX()
  {
    return getString("formatted_price");
  }
  
  public long kY()
  {
    return getLong("full_price_micros");
  }
  
  public String kZ()
  {
    return getString("formatted_full_price");
  }
  
  public SnapshotMetadata la()
  {
    return this.aaJ;
  }
  
  public ExtendedGame lc()
  {
    return new ExtendedGameEntity(this);
  }
  
  public String toString()
  {
    return ExtendedGameEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((ExtendedGameEntity)lc()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/ExtendedGameRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */