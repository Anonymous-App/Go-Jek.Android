package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class AchievementEntity
  implements SafeParcelable, Achievement
{
  public static final AchievementEntityCreator CREATOR = new AchievementEntityCreator();
  private final int BR;
  private final int FD;
  private final String Tr;
  private final String Wa;
  private final Uri Wb;
  private final String Wc;
  private final Uri Wd;
  private final String We;
  private final int Wf;
  private final String Wg;
  private final PlayerEntity Wh;
  private final int Wi;
  private final String Wj;
  private final long Wk;
  private final long Wl;
  private final String mName;
  private final int mState;
  
  AchievementEntity(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, Uri paramUri1, String paramString4, Uri paramUri2, String paramString5, int paramInt3, String paramString6, PlayerEntity paramPlayerEntity, int paramInt4, int paramInt5, String paramString7, long paramLong1, long paramLong2)
  {
    this.BR = paramInt1;
    this.Wa = paramString1;
    this.FD = paramInt2;
    this.mName = paramString2;
    this.Tr = paramString3;
    this.Wb = paramUri1;
    this.Wc = paramString4;
    this.Wd = paramUri2;
    this.We = paramString5;
    this.Wf = paramInt3;
    this.Wg = paramString6;
    this.Wh = paramPlayerEntity;
    this.mState = paramInt4;
    this.Wi = paramInt5;
    this.Wj = paramString7;
    this.Wk = paramLong1;
    this.Wl = paramLong2;
  }
  
  public AchievementEntity(Achievement paramAchievement)
  {
    this.BR = 1;
    this.Wa = paramAchievement.getAchievementId();
    this.FD = paramAchievement.getType();
    this.mName = paramAchievement.getName();
    this.Tr = paramAchievement.getDescription();
    this.Wb = paramAchievement.getUnlockedImageUri();
    this.Wc = paramAchievement.getUnlockedImageUrl();
    this.Wd = paramAchievement.getRevealedImageUri();
    this.We = paramAchievement.getRevealedImageUrl();
    this.Wh = ((PlayerEntity)paramAchievement.getPlayer().freeze());
    this.mState = paramAchievement.getState();
    this.Wk = paramAchievement.getLastUpdatedTimestamp();
    this.Wl = paramAchievement.getXpValue();
    if (paramAchievement.getType() == 1)
    {
      this.Wf = paramAchievement.getTotalSteps();
      this.Wg = paramAchievement.getFormattedTotalSteps();
      this.Wi = paramAchievement.getCurrentSteps();
    }
    for (this.Wj = paramAchievement.getFormattedCurrentSteps();; this.Wj = null)
    {
      a.f(this.Wa);
      a.f(this.Tr);
      return;
      this.Wf = 0;
      this.Wg = null;
      this.Wi = 0;
    }
  }
  
  static int a(Achievement paramAchievement)
  {
    int j;
    int i;
    if (paramAchievement.getType() == 1)
    {
      j = paramAchievement.getCurrentSteps();
      i = paramAchievement.getTotalSteps();
    }
    for (;;)
    {
      return n.hashCode(new Object[] { paramAchievement.getAchievementId(), paramAchievement.getName(), Integer.valueOf(paramAchievement.getType()), paramAchievement.getDescription(), Long.valueOf(paramAchievement.getXpValue()), Integer.valueOf(paramAchievement.getState()), Long.valueOf(paramAchievement.getLastUpdatedTimestamp()), paramAchievement.getPlayer(), Integer.valueOf(j), Integer.valueOf(i) });
      i = 0;
      j = 0;
    }
  }
  
  static boolean a(Achievement paramAchievement, Object paramObject)
  {
    boolean bool3 = true;
    boolean bool2;
    if (!(paramObject instanceof Achievement)) {
      bool2 = false;
    }
    do
    {
      return bool2;
      bool2 = bool3;
    } while (paramAchievement == paramObject);
    paramObject = (Achievement)paramObject;
    boolean bool1;
    if (paramAchievement.getType() == 1)
    {
      bool2 = n.equal(Integer.valueOf(((Achievement)paramObject).getCurrentSteps()), Integer.valueOf(paramAchievement.getCurrentSteps()));
      bool1 = n.equal(Integer.valueOf(((Achievement)paramObject).getTotalSteps()), Integer.valueOf(paramAchievement.getTotalSteps()));
    }
    for (;;)
    {
      if ((n.equal(((Achievement)paramObject).getAchievementId(), paramAchievement.getAchievementId())) && (n.equal(((Achievement)paramObject).getName(), paramAchievement.getName())) && (n.equal(Integer.valueOf(((Achievement)paramObject).getType()), Integer.valueOf(paramAchievement.getType()))) && (n.equal(((Achievement)paramObject).getDescription(), paramAchievement.getDescription())) && (n.equal(Long.valueOf(((Achievement)paramObject).getXpValue()), Long.valueOf(paramAchievement.getXpValue()))) && (n.equal(Integer.valueOf(((Achievement)paramObject).getState()), Integer.valueOf(paramAchievement.getState()))) && (n.equal(Long.valueOf(((Achievement)paramObject).getLastUpdatedTimestamp()), Long.valueOf(paramAchievement.getLastUpdatedTimestamp()))) && (n.equal(((Achievement)paramObject).getPlayer(), paramAchievement.getPlayer())) && (bool2))
      {
        bool2 = bool3;
        if (bool1) {
          break;
        }
      }
      return false;
      bool1 = true;
      bool2 = true;
    }
  }
  
  static String b(Achievement paramAchievement)
  {
    n.a locala = n.h(paramAchievement).a("Id", paramAchievement.getAchievementId()).a("Type", Integer.valueOf(paramAchievement.getType())).a("Name", paramAchievement.getName()).a("Description", paramAchievement.getDescription()).a("Player", paramAchievement.getPlayer()).a("State", Integer.valueOf(paramAchievement.getState()));
    if (paramAchievement.getType() == 1)
    {
      locala.a("CurrentSteps", Integer.valueOf(paramAchievement.getCurrentSteps()));
      locala.a("TotalSteps", Integer.valueOf(paramAchievement.getTotalSteps()));
    }
    return locala.toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Achievement freeze()
  {
    return this;
  }
  
  public String getAchievementId()
  {
    return this.Wa;
  }
  
  public int getCurrentSteps()
  {
    return this.Wi;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public String getFormattedCurrentSteps()
  {
    return this.Wj;
  }
  
  public void getFormattedCurrentSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Wj, paramCharArrayBuffer);
  }
  
  public String getFormattedTotalSteps()
  {
    return this.Wg;
  }
  
  public void getFormattedTotalSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Wg, paramCharArrayBuffer);
  }
  
  public long getLastUpdatedTimestamp()
  {
    return this.Wk;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.mName, paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return this.Wh;
  }
  
  public Uri getRevealedImageUri()
  {
    return this.Wd;
  }
  
  public String getRevealedImageUrl()
  {
    return this.We;
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public int getTotalSteps()
  {
    return this.Wf;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public Uri getUnlockedImageUri()
  {
    return this.Wb;
  }
  
  public String getUnlockedImageUrl()
  {
    return this.Wc;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public long getXpValue()
  {
    return this.Wl;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    AchievementEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/achievement/AchievementEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */