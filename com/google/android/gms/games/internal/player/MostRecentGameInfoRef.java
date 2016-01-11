package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class MostRecentGameInfoRef
  extends d
  implements MostRecentGameInfo
{
  private final PlayerColumnNames VY;
  
  public MostRecentGameInfoRef(DataHolder paramDataHolder, int paramInt, PlayerColumnNames paramPlayerColumnNames)
  {
    super(paramDataHolder, paramInt);
    this.VY = paramPlayerColumnNames;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return MostRecentGameInfoEntity.a(this, paramObject);
  }
  
  public int hashCode()
  {
    return MostRecentGameInfoEntity.a(this);
  }
  
  public String lp()
  {
    return getString(this.VY.abl);
  }
  
  public String lq()
  {
    return getString(this.VY.abm);
  }
  
  public long lr()
  {
    return getLong(this.VY.abn);
  }
  
  public Uri ls()
  {
    return aR(this.VY.abo);
  }
  
  public Uri lt()
  {
    return aR(this.VY.abp);
  }
  
  public Uri lu()
  {
    return aR(this.VY.abq);
  }
  
  public MostRecentGameInfo lv()
  {
    return new MostRecentGameInfoEntity(this);
  }
  
  public String toString()
  {
    return MostRecentGameInfoEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((MostRecentGameInfoEntity)lv()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/player/MostRecentGameInfoRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */