package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PlayerLevel
  implements SafeParcelable
{
  public static final PlayerLevelCreator CREATOR = new PlayerLevelCreator();
  private final int BR;
  private final int VR;
  private final long VS;
  private final long VT;
  
  PlayerLevel(int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    if (paramLong1 >= 0L)
    {
      bool1 = true;
      o.a(bool1, "Min XP must be positive!");
      if (paramLong2 <= paramLong1) {
        break label69;
      }
    }
    label69:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      o.a(bool1, "Max XP must be more than min XP!");
      this.BR = paramInt1;
      this.VR = paramInt2;
      this.VS = paramLong1;
      this.VT = paramLong2;
      return;
      bool1 = false;
      break;
    }
  }
  
  public PlayerLevel(int paramInt, long paramLong1, long paramLong2)
  {
    this(1, paramInt, paramLong1, paramLong2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof PlayerLevel)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (this == paramObject);
      paramObject = (PlayerLevel)paramObject;
      if ((!n.equal(Integer.valueOf(((PlayerLevel)paramObject).getLevelNumber()), Integer.valueOf(getLevelNumber()))) || (!n.equal(Long.valueOf(((PlayerLevel)paramObject).getMinXp()), Long.valueOf(getMinXp())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(Long.valueOf(((PlayerLevel)paramObject).getMaxXp()), Long.valueOf(getMaxXp())));
    return false;
  }
  
  public int getLevelNumber()
  {
    return this.VR;
  }
  
  public long getMaxXp()
  {
    return this.VT;
  }
  
  public long getMinXp()
  {
    return this.VS;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.VR), Long.valueOf(this.VS), Long.valueOf(this.VT) });
  }
  
  public String toString()
  {
    return n.h(this).a("LevelNumber", Integer.valueOf(getLevelNumber())).a("MinXp", Long.valueOf(getMinXp())).a("MaxXp", Long.valueOf(getMaxXp())).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    PlayerLevelCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/PlayerLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */