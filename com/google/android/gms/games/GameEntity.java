package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;

public final class GameEntity
  extends GamesDowngradeableSafeParcel
  implements Game
{
  public static final Parcelable.Creator<GameEntity> CREATOR = new GameEntityCreatorCompat();
  private final int BR;
  private final String Ez;
  private final String NH;
  private final String Tr;
  private final String Ve;
  private final String Vf;
  private final String Vg;
  private final Uri Vh;
  private final Uri Vi;
  private final Uri Vj;
  private final boolean Vk;
  private final boolean Vl;
  private final String Vm;
  private final int Vn;
  private final int Vo;
  private final int Vp;
  private final boolean Vq;
  private final boolean Vr;
  private final String Vs;
  private final String Vt;
  private final String Vu;
  private final boolean Vv;
  private final boolean Vw;
  private final boolean Vx;
  private final String Vy;
  
  GameEntity(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri1, Uri paramUri2, Uri paramUri3, boolean paramBoolean1, boolean paramBoolean2, String paramString7, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean3, boolean paramBoolean4, String paramString8, String paramString9, String paramString10, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, String paramString11)
  {
    this.BR = paramInt1;
    this.Ez = paramString1;
    this.NH = paramString2;
    this.Ve = paramString3;
    this.Vf = paramString4;
    this.Tr = paramString5;
    this.Vg = paramString6;
    this.Vh = paramUri1;
    this.Vs = paramString8;
    this.Vi = paramUri2;
    this.Vt = paramString9;
    this.Vj = paramUri3;
    this.Vu = paramString10;
    this.Vk = paramBoolean1;
    this.Vl = paramBoolean2;
    this.Vm = paramString7;
    this.Vn = paramInt2;
    this.Vo = paramInt3;
    this.Vp = paramInt4;
    this.Vq = paramBoolean3;
    this.Vr = paramBoolean4;
    this.Vv = paramBoolean5;
    this.Vw = paramBoolean6;
    this.Vx = paramBoolean7;
    this.Vy = paramString11;
  }
  
  public GameEntity(Game paramGame)
  {
    this.BR = 5;
    this.Ez = paramGame.getApplicationId();
    this.Ve = paramGame.getPrimaryCategory();
    this.Vf = paramGame.getSecondaryCategory();
    this.Tr = paramGame.getDescription();
    this.Vg = paramGame.getDeveloperName();
    this.NH = paramGame.getDisplayName();
    this.Vh = paramGame.getIconImageUri();
    this.Vs = paramGame.getIconImageUrl();
    this.Vi = paramGame.getHiResImageUri();
    this.Vt = paramGame.getHiResImageUrl();
    this.Vj = paramGame.getFeaturedImageUri();
    this.Vu = paramGame.getFeaturedImageUrl();
    this.Vk = paramGame.jO();
    this.Vl = paramGame.jQ();
    this.Vm = paramGame.jR();
    this.Vn = paramGame.jS();
    this.Vo = paramGame.getAchievementTotalCount();
    this.Vp = paramGame.getLeaderboardCount();
    this.Vq = paramGame.isRealTimeMultiplayerEnabled();
    this.Vr = paramGame.isTurnBasedMultiplayerEnabled();
    this.Vv = paramGame.isMuted();
    this.Vw = paramGame.jP();
    this.Vx = paramGame.areSnapshotsEnabled();
    this.Vy = paramGame.getThemeColor();
  }
  
  static int a(Game paramGame)
  {
    return n.hashCode(new Object[] { paramGame.getApplicationId(), paramGame.getDisplayName(), paramGame.getPrimaryCategory(), paramGame.getSecondaryCategory(), paramGame.getDescription(), paramGame.getDeveloperName(), paramGame.getIconImageUri(), paramGame.getHiResImageUri(), paramGame.getFeaturedImageUri(), Boolean.valueOf(paramGame.jO()), Boolean.valueOf(paramGame.jQ()), paramGame.jR(), Integer.valueOf(paramGame.jS()), Integer.valueOf(paramGame.getAchievementTotalCount()), Integer.valueOf(paramGame.getLeaderboardCount()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(paramGame.isMuted()), Boolean.valueOf(paramGame.jP()), Boolean.valueOf(paramGame.areSnapshotsEnabled()), paramGame.getThemeColor() });
  }
  
  static boolean a(Game paramGame, Object paramObject)
  {
    boolean bool2 = true;
    if (!(paramObject instanceof Game)) {
      bool1 = false;
    }
    do
    {
      return bool1;
      bool1 = bool2;
    } while (paramGame == paramObject);
    paramObject = (Game)paramObject;
    boolean bool3;
    if ((n.equal(((Game)paramObject).getApplicationId(), paramGame.getApplicationId())) && (n.equal(((Game)paramObject).getDisplayName(), paramGame.getDisplayName())) && (n.equal(((Game)paramObject).getPrimaryCategory(), paramGame.getPrimaryCategory())) && (n.equal(((Game)paramObject).getSecondaryCategory(), paramGame.getSecondaryCategory())) && (n.equal(((Game)paramObject).getDescription(), paramGame.getDescription())) && (n.equal(((Game)paramObject).getDeveloperName(), paramGame.getDeveloperName())) && (n.equal(((Game)paramObject).getIconImageUri(), paramGame.getIconImageUri())) && (n.equal(((Game)paramObject).getHiResImageUri(), paramGame.getHiResImageUri())) && (n.equal(((Game)paramObject).getFeaturedImageUri(), paramGame.getFeaturedImageUri())) && (n.equal(Boolean.valueOf(((Game)paramObject).jO()), Boolean.valueOf(paramGame.jO()))) && (n.equal(Boolean.valueOf(((Game)paramObject).jQ()), Boolean.valueOf(paramGame.jQ()))) && (n.equal(((Game)paramObject).jR(), paramGame.jR())) && (n.equal(Integer.valueOf(((Game)paramObject).jS()), Integer.valueOf(paramGame.jS()))) && (n.equal(Integer.valueOf(((Game)paramObject).getAchievementTotalCount()), Integer.valueOf(paramGame.getAchievementTotalCount()))) && (n.equal(Integer.valueOf(((Game)paramObject).getLeaderboardCount()), Integer.valueOf(paramGame.getLeaderboardCount()))) && (n.equal(Boolean.valueOf(((Game)paramObject).isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()))))
    {
      bool3 = ((Game)paramObject).isTurnBasedMultiplayerEnabled();
      if ((!paramGame.isTurnBasedMultiplayerEnabled()) || (!n.equal(Boolean.valueOf(((Game)paramObject).isMuted()), Boolean.valueOf(paramGame.isMuted()))) || (!n.equal(Boolean.valueOf(((Game)paramObject).jP()), Boolean.valueOf(paramGame.jP())))) {
        break label477;
      }
    }
    label477:
    for (boolean bool1 = true;; bool1 = false)
    {
      if ((n.equal(Boolean.valueOf(bool3), Boolean.valueOf(bool1))) && (n.equal(Boolean.valueOf(((Game)paramObject).areSnapshotsEnabled()), Boolean.valueOf(paramGame.areSnapshotsEnabled()))))
      {
        bool1 = bool2;
        if (n.equal(((Game)paramObject).getThemeColor(), paramGame.getThemeColor())) {
          break;
        }
      }
      return false;
    }
  }
  
  static String b(Game paramGame)
  {
    return n.h(paramGame).a("ApplicationId", paramGame.getApplicationId()).a("DisplayName", paramGame.getDisplayName()).a("PrimaryCategory", paramGame.getPrimaryCategory()).a("SecondaryCategory", paramGame.getSecondaryCategory()).a("Description", paramGame.getDescription()).a("DeveloperName", paramGame.getDeveloperName()).a("IconImageUri", paramGame.getIconImageUri()).a("IconImageUrl", paramGame.getIconImageUrl()).a("HiResImageUri", paramGame.getHiResImageUri()).a("HiResImageUrl", paramGame.getHiResImageUrl()).a("FeaturedImageUri", paramGame.getFeaturedImageUri()).a("FeaturedImageUrl", paramGame.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(paramGame.jO())).a("InstanceInstalled", Boolean.valueOf(paramGame.jQ())).a("InstancePackageName", paramGame.jR()).a("AchievementTotalCount", Integer.valueOf(paramGame.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(paramGame.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled())).a("AreSnapshotsEnabled", Boolean.valueOf(paramGame.areSnapshotsEnabled())).a("ThemeColor", paramGame.getThemeColor()).toString();
  }
  
  public boolean areSnapshotsEnabled()
  {
    return this.Vx;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Game freeze()
  {
    return this;
  }
  
  public int getAchievementTotalCount()
  {
    return this.Vo;
  }
  
  public String getApplicationId()
  {
    return this.Ez;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public String getDeveloperName()
  {
    return this.Vg;
  }
  
  public void getDeveloperName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Vg, paramCharArrayBuffer);
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.NH, paramCharArrayBuffer);
  }
  
  public Uri getFeaturedImageUri()
  {
    return this.Vj;
  }
  
  public String getFeaturedImageUrl()
  {
    return this.Vu;
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
  
  public int getLeaderboardCount()
  {
    return this.Vp;
  }
  
  public String getPrimaryCategory()
  {
    return this.Ve;
  }
  
  public String getSecondaryCategory()
  {
    return this.Vf;
  }
  
  public String getThemeColor()
  {
    return this.Vy;
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
  
  public boolean isMuted()
  {
    return this.Vv;
  }
  
  public boolean isRealTimeMultiplayerEnabled()
  {
    return this.Vq;
  }
  
  public boolean isTurnBasedMultiplayerEnabled()
  {
    return this.Vr;
  }
  
  public boolean jO()
  {
    return this.Vk;
  }
  
  public boolean jP()
  {
    return this.Vw;
  }
  
  public boolean jQ()
  {
    return this.Vl;
  }
  
  public String jR()
  {
    return this.Vm;
  }
  
  public int jS()
  {
    return this.Vn;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    Object localObject2 = null;
    if (!gQ())
    {
      GameEntityCreator.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.Ez);
    paramParcel.writeString(this.NH);
    paramParcel.writeString(this.Ve);
    paramParcel.writeString(this.Vf);
    paramParcel.writeString(this.Tr);
    paramParcel.writeString(this.Vg);
    Object localObject1;
    if (this.Vh == null)
    {
      localObject1 = null;
      paramParcel.writeString((String)localObject1);
      if (this.Vi != null) {
        break label189;
      }
      localObject1 = null;
      label93:
      paramParcel.writeString((String)localObject1);
      if (this.Vj != null) {
        break label201;
      }
      localObject1 = localObject2;
      label110:
      paramParcel.writeString((String)localObject1);
      if (!this.Vk) {
        break label213;
      }
      paramInt = 1;
      label125:
      paramParcel.writeInt(paramInt);
      if (!this.Vl) {
        break label218;
      }
    }
    label189:
    label201:
    label213:
    label218:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      paramParcel.writeString(this.Vm);
      paramParcel.writeInt(this.Vn);
      paramParcel.writeInt(this.Vo);
      paramParcel.writeInt(this.Vp);
      return;
      localObject1 = this.Vh.toString();
      break;
      localObject1 = this.Vi.toString();
      break label93;
      localObject1 = this.Vj.toString();
      break label110;
      paramInt = 0;
      break label125;
    }
  }
  
  static final class GameEntityCreatorCompat
    extends GameEntityCreator
  {
    public GameEntity cd(Parcel paramParcel)
    {
      if ((GameEntity.b(GameEntity.jT())) || (GameEntity.bw(GameEntity.class.getCanonicalName()))) {
        return super.cd(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      String str5 = paramParcel.readString();
      String str6 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2;
      label90:
      Object localObject3;
      label104:
      boolean bool1;
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = paramParcel.readString();
        if (localObject2 != null) {
          break label186;
        }
        localObject2 = null;
        localObject3 = paramParcel.readString();
        if (localObject3 != null) {
          break label196;
        }
        localObject3 = null;
        if (paramParcel.readInt() <= 0) {
          break label206;
        }
        bool1 = true;
        label113:
        if (paramParcel.readInt() <= 0) {
          break label211;
        }
      }
      label186:
      label196:
      label206:
      label211:
      for (boolean bool2 = true;; bool2 = false)
      {
        return new GameEntity(5, str1, str2, str3, str4, str5, str6, (Uri)localObject1, (Uri)localObject2, (Uri)localObject3, bool1, bool2, paramParcel.readString(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), false, false, null, null, null, false, false, false, null);
        localObject1 = Uri.parse((String)localObject1);
        break;
        localObject2 = Uri.parse((String)localObject2);
        break label90;
        localObject3 = Uri.parse((String)localObject3);
        break label104;
        bool1 = false;
        break label113;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/GameEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */