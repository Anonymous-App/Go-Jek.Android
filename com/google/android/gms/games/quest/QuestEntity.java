package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.List;

public final class QuestEntity
  implements SafeParcelable, Quest
{
  public static final QuestEntityCreator CREATOR = new QuestEntityCreator();
  private final int BR;
  private final int FD;
  private final String Tr;
  private final long Wk;
  private final GameEntity aay;
  private final String acR;
  private final long acS;
  private final Uri acT;
  private final String acU;
  private final long acV;
  private final Uri acW;
  private final String acX;
  private final long acY;
  private final long acZ;
  private final ArrayList<MilestoneEntity> ada;
  private final String mName;
  private final int mState;
  
  QuestEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, long paramLong1, Uri paramUri1, String paramString2, String paramString3, long paramLong2, long paramLong3, Uri paramUri2, String paramString4, String paramString5, long paramLong4, long paramLong5, int paramInt2, int paramInt3, ArrayList<MilestoneEntity> paramArrayList)
  {
    this.BR = paramInt1;
    this.aay = paramGameEntity;
    this.acR = paramString1;
    this.acS = paramLong1;
    this.acT = paramUri1;
    this.acU = paramString2;
    this.Tr = paramString3;
    this.acV = paramLong2;
    this.Wk = paramLong3;
    this.acW = paramUri2;
    this.acX = paramString4;
    this.mName = paramString5;
    this.acY = paramLong4;
    this.acZ = paramLong5;
    this.mState = paramInt2;
    this.FD = paramInt3;
    this.ada = paramArrayList;
  }
  
  public QuestEntity(Quest paramQuest)
  {
    this.BR = 2;
    this.aay = new GameEntity(paramQuest.getGame());
    this.acR = paramQuest.getQuestId();
    this.acS = paramQuest.getAcceptedTimestamp();
    this.Tr = paramQuest.getDescription();
    this.acT = paramQuest.getBannerImageUri();
    this.acU = paramQuest.getBannerImageUrl();
    this.acV = paramQuest.getEndTimestamp();
    this.acW = paramQuest.getIconImageUri();
    this.acX = paramQuest.getIconImageUrl();
    this.Wk = paramQuest.getLastUpdatedTimestamp();
    this.mName = paramQuest.getName();
    this.acY = paramQuest.lK();
    this.acZ = paramQuest.getStartTimestamp();
    this.mState = paramQuest.getState();
    this.FD = paramQuest.getType();
    paramQuest = paramQuest.lJ();
    int j = paramQuest.size();
    this.ada = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      this.ada.add((MilestoneEntity)((Milestone)paramQuest.get(i)).freeze());
      i += 1;
    }
  }
  
  static int a(Quest paramQuest)
  {
    return n.hashCode(new Object[] { paramQuest.getGame(), paramQuest.getQuestId(), Long.valueOf(paramQuest.getAcceptedTimestamp()), paramQuest.getBannerImageUri(), paramQuest.getDescription(), Long.valueOf(paramQuest.getEndTimestamp()), paramQuest.getIconImageUri(), Long.valueOf(paramQuest.getLastUpdatedTimestamp()), paramQuest.lJ(), paramQuest.getName(), Long.valueOf(paramQuest.lK()), Long.valueOf(paramQuest.getStartTimestamp()), Integer.valueOf(paramQuest.getState()) });
  }
  
  static boolean a(Quest paramQuest, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Quest)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramQuest == paramObject);
      paramObject = (Quest)paramObject;
      if ((!n.equal(((Quest)paramObject).getGame(), paramQuest.getGame())) || (!n.equal(((Quest)paramObject).getQuestId(), paramQuest.getQuestId())) || (!n.equal(Long.valueOf(((Quest)paramObject).getAcceptedTimestamp()), Long.valueOf(paramQuest.getAcceptedTimestamp()))) || (!n.equal(((Quest)paramObject).getBannerImageUri(), paramQuest.getBannerImageUri())) || (!n.equal(((Quest)paramObject).getDescription(), paramQuest.getDescription())) || (!n.equal(Long.valueOf(((Quest)paramObject).getEndTimestamp()), Long.valueOf(paramQuest.getEndTimestamp()))) || (!n.equal(((Quest)paramObject).getIconImageUri(), paramQuest.getIconImageUri())) || (!n.equal(Long.valueOf(((Quest)paramObject).getLastUpdatedTimestamp()), Long.valueOf(paramQuest.getLastUpdatedTimestamp()))) || (!n.equal(((Quest)paramObject).lJ(), paramQuest.lJ())) || (!n.equal(((Quest)paramObject).getName(), paramQuest.getName())) || (!n.equal(Long.valueOf(((Quest)paramObject).lK()), Long.valueOf(paramQuest.lK()))) || (!n.equal(Long.valueOf(((Quest)paramObject).getStartTimestamp()), Long.valueOf(paramQuest.getStartTimestamp())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(Integer.valueOf(((Quest)paramObject).getState()), Integer.valueOf(paramQuest.getState())));
    return false;
  }
  
  static String b(Quest paramQuest)
  {
    return n.h(paramQuest).a("Game", paramQuest.getGame()).a("QuestId", paramQuest.getQuestId()).a("AcceptedTimestamp", Long.valueOf(paramQuest.getAcceptedTimestamp())).a("BannerImageUri", paramQuest.getBannerImageUri()).a("BannerImageUrl", paramQuest.getBannerImageUrl()).a("Description", paramQuest.getDescription()).a("EndTimestamp", Long.valueOf(paramQuest.getEndTimestamp())).a("IconImageUri", paramQuest.getIconImageUri()).a("IconImageUrl", paramQuest.getIconImageUrl()).a("LastUpdatedTimestamp", Long.valueOf(paramQuest.getLastUpdatedTimestamp())).a("Milestones", paramQuest.lJ()).a("Name", paramQuest.getName()).a("NotifyTimestamp", Long.valueOf(paramQuest.lK())).a("StartTimestamp", Long.valueOf(paramQuest.getStartTimestamp())).a("State", Integer.valueOf(paramQuest.getState())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Quest freeze()
  {
    return this;
  }
  
  public long getAcceptedTimestamp()
  {
    return this.acS;
  }
  
  public Uri getBannerImageUri()
  {
    return this.acT;
  }
  
  public String getBannerImageUrl()
  {
    return this.acU;
  }
  
  public Milestone getCurrentMilestone()
  {
    return (Milestone)lJ().get(0);
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public long getEndTimestamp()
  {
    return this.acV;
  }
  
  public Game getGame()
  {
    return this.aay;
  }
  
  public Uri getIconImageUri()
  {
    return this.acW;
  }
  
  public String getIconImageUrl()
  {
    return this.acX;
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
  
  public String getQuestId()
  {
    return this.acR;
  }
  
  public long getStartTimestamp()
  {
    return this.acZ;
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public int getType()
  {
    return this.FD;
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
  
  public boolean isEndingSoon()
  {
    return this.acY <= System.currentTimeMillis() + 1800000L;
  }
  
  public List<Milestone> lJ()
  {
    return new ArrayList(this.ada);
  }
  
  public long lK()
  {
    return this.acY;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    QuestEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/quest/QuestEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */