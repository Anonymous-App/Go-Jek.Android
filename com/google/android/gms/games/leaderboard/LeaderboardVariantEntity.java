package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;

public final class LeaderboardVariantEntity
  implements LeaderboardVariant
{
  private final int abM;
  private final int abN;
  private final boolean abO;
  private final long abP;
  private final String abQ;
  private final long abR;
  private final String abS;
  private final String abT;
  private final long abU;
  private final String abV;
  private final String abW;
  private final String abX;
  
  public LeaderboardVariantEntity(LeaderboardVariant paramLeaderboardVariant)
  {
    this.abM = paramLeaderboardVariant.getTimeSpan();
    this.abN = paramLeaderboardVariant.getCollection();
    this.abO = paramLeaderboardVariant.hasPlayerInfo();
    this.abP = paramLeaderboardVariant.getRawPlayerScore();
    this.abQ = paramLeaderboardVariant.getDisplayPlayerScore();
    this.abR = paramLeaderboardVariant.getPlayerRank();
    this.abS = paramLeaderboardVariant.getDisplayPlayerRank();
    this.abT = paramLeaderboardVariant.getPlayerScoreTag();
    this.abU = paramLeaderboardVariant.getNumScores();
    this.abV = paramLeaderboardVariant.lD();
    this.abW = paramLeaderboardVariant.lE();
    this.abX = paramLeaderboardVariant.lF();
  }
  
  static int a(LeaderboardVariant paramLeaderboardVariant)
  {
    return n.hashCode(new Object[] { Integer.valueOf(paramLeaderboardVariant.getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getCollection()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()), paramLeaderboardVariant.getDisplayPlayerScore(), Long.valueOf(paramLeaderboardVariant.getPlayerRank()), paramLeaderboardVariant.getDisplayPlayerRank(), Long.valueOf(paramLeaderboardVariant.getNumScores()), paramLeaderboardVariant.lD(), paramLeaderboardVariant.lF(), paramLeaderboardVariant.lE() });
  }
  
  static boolean a(LeaderboardVariant paramLeaderboardVariant, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof LeaderboardVariant)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboardVariant == paramObject);
      paramObject = (LeaderboardVariant)paramObject;
      if ((!n.equal(Integer.valueOf(((LeaderboardVariant)paramObject).getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getTimeSpan()))) || (!n.equal(Integer.valueOf(((LeaderboardVariant)paramObject).getCollection()), Integer.valueOf(paramLeaderboardVariant.getCollection()))) || (!n.equal(Boolean.valueOf(((LeaderboardVariant)paramObject).hasPlayerInfo()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()))) || (!n.equal(Long.valueOf(((LeaderboardVariant)paramObject).getRawPlayerScore()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()))) || (!n.equal(((LeaderboardVariant)paramObject).getDisplayPlayerScore(), paramLeaderboardVariant.getDisplayPlayerScore())) || (!n.equal(Long.valueOf(((LeaderboardVariant)paramObject).getPlayerRank()), Long.valueOf(paramLeaderboardVariant.getPlayerRank()))) || (!n.equal(((LeaderboardVariant)paramObject).getDisplayPlayerRank(), paramLeaderboardVariant.getDisplayPlayerRank())) || (!n.equal(Long.valueOf(((LeaderboardVariant)paramObject).getNumScores()), Long.valueOf(paramLeaderboardVariant.getNumScores()))) || (!n.equal(((LeaderboardVariant)paramObject).lD(), paramLeaderboardVariant.lD())) || (!n.equal(((LeaderboardVariant)paramObject).lF(), paramLeaderboardVariant.lF()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((LeaderboardVariant)paramObject).lE(), paramLeaderboardVariant.lE()));
    return false;
  }
  
  static String b(LeaderboardVariant paramLeaderboardVariant)
  {
    n.a locala = n.h(paramLeaderboardVariant).a("TimeSpan", TimeSpan.dH(paramLeaderboardVariant.getTimeSpan())).a("Collection", LeaderboardCollection.dH(paramLeaderboardVariant.getCollection()));
    if (paramLeaderboardVariant.hasPlayerInfo())
    {
      localObject = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
      locala = locala.a("RawPlayerScore", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label191;
      }
      localObject = paramLeaderboardVariant.getDisplayPlayerScore();
      label76:
      locala = locala.a("DisplayPlayerScore", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label197;
      }
      localObject = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
      label103:
      locala = locala.a("PlayerRank", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label203;
      }
    }
    label191:
    label197:
    label203:
    for (Object localObject = paramLeaderboardVariant.getDisplayPlayerRank();; localObject = "none")
    {
      return locala.a("DisplayPlayerRank", localObject).a("NumScores", Long.valueOf(paramLeaderboardVariant.getNumScores())).a("TopPageNextToken", paramLeaderboardVariant.lD()).a("WindowPageNextToken", paramLeaderboardVariant.lF()).a("WindowPagePrevToken", paramLeaderboardVariant.lE()).toString();
      localObject = "none";
      break;
      localObject = "none";
      break label76;
      localObject = "none";
      break label103;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public int getCollection()
  {
    return this.abN;
  }
  
  public String getDisplayPlayerRank()
  {
    return this.abS;
  }
  
  public String getDisplayPlayerScore()
  {
    return this.abQ;
  }
  
  public long getNumScores()
  {
    return this.abU;
  }
  
  public long getPlayerRank()
  {
    return this.abR;
  }
  
  public String getPlayerScoreTag()
  {
    return this.abT;
  }
  
  public long getRawPlayerScore()
  {
    return this.abP;
  }
  
  public int getTimeSpan()
  {
    return this.abM;
  }
  
  public boolean hasPlayerInfo()
  {
    return this.abO;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String lD()
  {
    return this.abV;
  }
  
  public String lE()
  {
    return this.abW;
  }
  
  public String lF()
  {
    return this.abX;
  }
  
  public LeaderboardVariant lG()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/LeaderboardVariantEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */