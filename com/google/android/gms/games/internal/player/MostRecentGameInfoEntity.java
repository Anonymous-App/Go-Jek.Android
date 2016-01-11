package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MostRecentGameInfoEntity
  implements SafeParcelable, MostRecentGameInfo
{
  public static final MostRecentGameInfoEntityCreator CREATOR = new MostRecentGameInfoEntityCreator();
  private final int BR;
  private final String aaM;
  private final String aaN;
  private final long aaO;
  private final Uri aaP;
  private final Uri aaQ;
  private final Uri aaR;
  
  MostRecentGameInfoEntity(int paramInt, String paramString1, String paramString2, long paramLong, Uri paramUri1, Uri paramUri2, Uri paramUri3)
  {
    this.BR = paramInt;
    this.aaM = paramString1;
    this.aaN = paramString2;
    this.aaO = paramLong;
    this.aaP = paramUri1;
    this.aaQ = paramUri2;
    this.aaR = paramUri3;
  }
  
  public MostRecentGameInfoEntity(MostRecentGameInfo paramMostRecentGameInfo)
  {
    this.BR = 2;
    this.aaM = paramMostRecentGameInfo.lp();
    this.aaN = paramMostRecentGameInfo.lq();
    this.aaO = paramMostRecentGameInfo.lr();
    this.aaP = paramMostRecentGameInfo.ls();
    this.aaQ = paramMostRecentGameInfo.lt();
    this.aaR = paramMostRecentGameInfo.lu();
  }
  
  static int a(MostRecentGameInfo paramMostRecentGameInfo)
  {
    return n.hashCode(new Object[] { paramMostRecentGameInfo.lp(), paramMostRecentGameInfo.lq(), Long.valueOf(paramMostRecentGameInfo.lr()), paramMostRecentGameInfo.ls(), paramMostRecentGameInfo.lt(), paramMostRecentGameInfo.lu() });
  }
  
  static boolean a(MostRecentGameInfo paramMostRecentGameInfo, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof MostRecentGameInfo)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramMostRecentGameInfo == paramObject);
      paramObject = (MostRecentGameInfo)paramObject;
      if ((!n.equal(((MostRecentGameInfo)paramObject).lp(), paramMostRecentGameInfo.lp())) || (!n.equal(((MostRecentGameInfo)paramObject).lq(), paramMostRecentGameInfo.lq())) || (!n.equal(Long.valueOf(((MostRecentGameInfo)paramObject).lr()), Long.valueOf(paramMostRecentGameInfo.lr()))) || (!n.equal(((MostRecentGameInfo)paramObject).ls(), paramMostRecentGameInfo.ls())) || (!n.equal(((MostRecentGameInfo)paramObject).lt(), paramMostRecentGameInfo.lt()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((MostRecentGameInfo)paramObject).lu(), paramMostRecentGameInfo.lu()));
    return false;
  }
  
  static String b(MostRecentGameInfo paramMostRecentGameInfo)
  {
    return n.h(paramMostRecentGameInfo).a("GameId", paramMostRecentGameInfo.lp()).a("GameName", paramMostRecentGameInfo.lq()).a("ActivityTimestampMillis", Long.valueOf(paramMostRecentGameInfo.lr())).a("GameIconUri", paramMostRecentGameInfo.ls()).a("GameHiResUri", paramMostRecentGameInfo.lt()).a("GameFeaturedUri", paramMostRecentGameInfo.lu()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String lp()
  {
    return this.aaM;
  }
  
  public String lq()
  {
    return this.aaN;
  }
  
  public long lr()
  {
    return this.aaO;
  }
  
  public Uri ls()
  {
    return this.aaP;
  }
  
  public Uri lt()
  {
    return this.aaQ;
  }
  
  public Uri lu()
  {
    return this.aaR;
  }
  
  public MostRecentGameInfo lv()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    MostRecentGameInfoEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/player/MostRecentGameInfoEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */