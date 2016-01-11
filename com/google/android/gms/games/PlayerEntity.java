package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.jv;

public final class PlayerEntity
  extends GamesDowngradeableSafeParcel
  implements Player
{
  public static final Parcelable.Creator<PlayerEntity> CREATOR = new PlayerEntityCreatorCompat();
  private final int BR;
  private final String NH;
  private final String Nw;
  private final String VK;
  private final long VL;
  private final int VM;
  private final long VN;
  private final MostRecentGameInfoEntity VO;
  private final PlayerLevelInfo VP;
  private final boolean VQ;
  private final Uri Vh;
  private final Uri Vi;
  private final String Vs;
  private final String Vt;
  
  PlayerEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, long paramLong1, int paramInt2, long paramLong2, String paramString3, String paramString4, String paramString5, MostRecentGameInfoEntity paramMostRecentGameInfoEntity, PlayerLevelInfo paramPlayerLevelInfo, boolean paramBoolean)
  {
    this.BR = paramInt1;
    this.VK = paramString1;
    this.NH = paramString2;
    this.Vh = paramUri1;
    this.Vs = paramString3;
    this.Vi = paramUri2;
    this.Vt = paramString4;
    this.VL = paramLong1;
    this.VM = paramInt2;
    this.VN = paramLong2;
    this.Nw = paramString5;
    this.VQ = paramBoolean;
    this.VO = paramMostRecentGameInfoEntity;
    this.VP = paramPlayerLevelInfo;
  }
  
  public PlayerEntity(Player paramPlayer)
  {
    this.BR = 11;
    this.VK = paramPlayer.getPlayerId();
    this.NH = paramPlayer.getDisplayName();
    this.Vh = paramPlayer.getIconImageUri();
    this.Vs = paramPlayer.getIconImageUrl();
    this.Vi = paramPlayer.getHiResImageUri();
    this.Vt = paramPlayer.getHiResImageUrl();
    this.VL = paramPlayer.getRetrievedTimestamp();
    this.VM = paramPlayer.jU();
    this.VN = paramPlayer.getLastPlayedWithTimestamp();
    this.Nw = paramPlayer.getTitle();
    this.VQ = paramPlayer.isProfileVisible();
    Object localObject = paramPlayer.jV();
    if (localObject == null)
    {
      localObject = null;
      this.VO = ((MostRecentGameInfoEntity)localObject);
      this.VP = paramPlayer.getLevelInfo();
      a.f(this.VK);
      a.f(this.NH);
      if (this.VL <= 0L) {
        break label190;
      }
    }
    label190:
    for (boolean bool = true;; bool = false)
    {
      a.I(bool);
      return;
      localObject = new MostRecentGameInfoEntity((MostRecentGameInfo)localObject);
      break;
    }
  }
  
  static boolean a(Player paramPlayer, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Player)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramPlayer == paramObject);
      paramObject = (Player)paramObject;
      if ((!n.equal(((Player)paramObject).getPlayerId(), paramPlayer.getPlayerId())) || (!n.equal(((Player)paramObject).getDisplayName(), paramPlayer.getDisplayName())) || (!n.equal(((Player)paramObject).getIconImageUri(), paramPlayer.getIconImageUri())) || (!n.equal(((Player)paramObject).getHiResImageUri(), paramPlayer.getHiResImageUri())) || (!n.equal(Long.valueOf(((Player)paramObject).getRetrievedTimestamp()), Long.valueOf(paramPlayer.getRetrievedTimestamp()))) || (!n.equal(((Player)paramObject).getTitle(), paramPlayer.getTitle()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((Player)paramObject).getLevelInfo(), paramPlayer.getLevelInfo()));
    return false;
  }
  
  static int b(Player paramPlayer)
  {
    return n.hashCode(new Object[] { paramPlayer.getPlayerId(), paramPlayer.getDisplayName(), paramPlayer.getIconImageUri(), paramPlayer.getHiResImageUri(), Long.valueOf(paramPlayer.getRetrievedTimestamp()), paramPlayer.getTitle(), paramPlayer.getLevelInfo() });
  }
  
  static String c(Player paramPlayer)
  {
    return n.h(paramPlayer).a("PlayerId", paramPlayer.getPlayerId()).a("DisplayName", paramPlayer.getDisplayName()).a("IconImageUri", paramPlayer.getIconImageUri()).a("IconImageUrl", paramPlayer.getIconImageUrl()).a("HiResImageUri", paramPlayer.getHiResImageUri()).a("HiResImageUrl", paramPlayer.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(paramPlayer.getRetrievedTimestamp())).a("Title", paramPlayer.getTitle()).a("LevelInfo", paramPlayer.getLevelInfo()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Player freeze()
  {
    return this;
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.NH, paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    return this.Vi;
  }
  
  public String getHiResImageUrl()
  {
    return this.Vt;
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getIconImageUrl()
  {
    return this.Vs;
  }
  
  public long getLastPlayedWithTimestamp()
  {
    return this.VN;
  }
  
  public PlayerLevelInfo getLevelInfo()
  {
    return this.VP;
  }
  
  public String getPlayerId()
  {
    return this.VK;
  }
  
  public long getRetrievedTimestamp()
  {
    return this.VL;
  }
  
  public String getTitle()
  {
    return this.Nw;
  }
  
  public void getTitle(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Nw, paramCharArrayBuffer);
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public boolean hasHiResImage()
  {
    return getHiResImageUri() != null;
  }
  
  public boolean hasIconImage()
  {
    return getIconImageUri() != null;
  }
  
  public int hashCode()
  {
    return b(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isProfileVisible()
  {
    return this.VQ;
  }
  
  public int jU()
  {
    return this.VM;
  }
  
  public MostRecentGameInfo jV()
  {
    return this.VO;
  }
  
  public String toString()
  {
    return c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Object localObject2 = null;
    if (!gQ())
    {
      PlayerEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.VK);
    paramParcel.writeString(this.NH);
    if (this.Vh == null)
    {
      localObject1 = null;
      paramParcel.writeString((String)localObject1);
      if (this.Vi != null) {
        break label82;
      }
    }
    label82:
    for (Object localObject1 = localObject2;; localObject1 = this.Vi.toString())
    {
      paramParcel.writeString((String)localObject1);
      paramParcel.writeLong(this.VL);
      return;
      localObject1 = this.Vh.toString();
      break;
    }
  }
  
  static final class PlayerEntityCreatorCompat
    extends PlayerEntityCreator
  {
    public PlayerEntity ce(Parcel paramParcel)
    {
      if ((PlayerEntity.b(PlayerEntity.jT())) || (PlayerEntity.bw(PlayerEntity.class.getCanonicalName()))) {
        return super.ce(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2 = paramParcel.readString();
      if (localObject1 == null)
      {
        localObject1 = null;
        if (localObject2 != null) {
          break label98;
        }
      }
      label98:
      for (localObject2 = null;; localObject2 = Uri.parse((String)localObject2))
      {
        return new PlayerEntity(11, str1, str2, (Uri)localObject1, (Uri)localObject2, paramParcel.readLong(), -1, -1L, null, null, null, null, null, true);
        localObject1 = Uri.parse((String)localObject1);
        break;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/PlayerEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */