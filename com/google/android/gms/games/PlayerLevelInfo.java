package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PlayerLevelInfo
  implements SafeParcelable
{
  public static final PlayerLevelInfoCreator CREATOR = new PlayerLevelInfoCreator();
  private final int BR;
  private final long VU;
  private final long VV;
  private final PlayerLevel VW;
  private final PlayerLevel VX;
  
  PlayerLevelInfo(int paramInt, long paramLong1, long paramLong2, PlayerLevel paramPlayerLevel1, PlayerLevel paramPlayerLevel2)
  {
    if (paramLong1 != -1L) {}
    for (boolean bool = true;; bool = false)
    {
      o.I(bool);
      o.i(paramPlayerLevel1);
      o.i(paramPlayerLevel2);
      this.BR = paramInt;
      this.VU = paramLong1;
      this.VV = paramLong2;
      this.VW = paramPlayerLevel1;
      this.VX = paramPlayerLevel2;
      return;
    }
  }
  
  public PlayerLevelInfo(long paramLong1, long paramLong2, PlayerLevel paramPlayerLevel1, PlayerLevel paramPlayerLevel2)
  {
    this(1, paramLong1, paramLong2, paramPlayerLevel1, paramPlayerLevel2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof PlayerLevelInfo)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (PlayerLevelInfo)paramObject;
      if ((!n.equal(Long.valueOf(this.VU), Long.valueOf(((PlayerLevelInfo)paramObject).VU))) || (!n.equal(Long.valueOf(this.VV), Long.valueOf(((PlayerLevelInfo)paramObject).VV))) || (!n.equal(this.VW, ((PlayerLevelInfo)paramObject).VW))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(this.VX, ((PlayerLevelInfo)paramObject).VX));
    return false;
  }
  
  public PlayerLevel getCurrentLevel()
  {
    return this.VW;
  }
  
  public long getCurrentXpTotal()
  {
    return this.VU;
  }
  
  public long getLastLevelUpTimestamp()
  {
    return this.VV;
  }
  
  public PlayerLevel getNextLevel()
  {
    return this.VX;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Long.valueOf(this.VU), Long.valueOf(this.VV), this.VW, this.VX });
  }
  
  public boolean isMaxLevel()
  {
    return this.VW.equals(this.VX);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    PlayerLevelInfoCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/PlayerLevelInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */