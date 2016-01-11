package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class LeaderboardScoreEntity
  implements LeaderboardScore
{
  private final String abA;
  private final String abB;
  private final long abC;
  private final long abD;
  private final String abE;
  private final Uri abF;
  private final Uri abG;
  private final PlayerEntity abH;
  private final String abI;
  private final String abJ;
  private final String abK;
  private final long abz;
  
  public LeaderboardScoreEntity(LeaderboardScore paramLeaderboardScore)
  {
    this.abz = paramLeaderboardScore.getRank();
    this.abA = ((String)o.i(paramLeaderboardScore.getDisplayRank()));
    this.abB = ((String)o.i(paramLeaderboardScore.getDisplayScore()));
    this.abC = paramLeaderboardScore.getRawScore();
    this.abD = paramLeaderboardScore.getTimestampMillis();
    this.abE = paramLeaderboardScore.getScoreHolderDisplayName();
    this.abF = paramLeaderboardScore.getScoreHolderIconImageUri();
    this.abG = paramLeaderboardScore.getScoreHolderHiResImageUri();
    Object localObject = paramLeaderboardScore.getScoreHolder();
    if (localObject == null) {}
    for (localObject = null;; localObject = (PlayerEntity)((Player)localObject).freeze())
    {
      this.abH = ((PlayerEntity)localObject);
      this.abI = paramLeaderboardScore.getScoreTag();
      this.abJ = paramLeaderboardScore.getScoreHolderIconImageUrl();
      this.abK = paramLeaderboardScore.getScoreHolderHiResImageUrl();
      return;
    }
  }
  
  static int a(LeaderboardScore paramLeaderboardScore)
  {
    return n.hashCode(new Object[] { Long.valueOf(paramLeaderboardScore.getRank()), paramLeaderboardScore.getDisplayRank(), Long.valueOf(paramLeaderboardScore.getRawScore()), paramLeaderboardScore.getDisplayScore(), Long.valueOf(paramLeaderboardScore.getTimestampMillis()), paramLeaderboardScore.getScoreHolderDisplayName(), paramLeaderboardScore.getScoreHolderIconImageUri(), paramLeaderboardScore.getScoreHolderHiResImageUri(), paramLeaderboardScore.getScoreHolder() });
  }
  
  static boolean a(LeaderboardScore paramLeaderboardScore, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof LeaderboardScore)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboardScore == paramObject);
      paramObject = (LeaderboardScore)paramObject;
      if ((!n.equal(Long.valueOf(((LeaderboardScore)paramObject).getRank()), Long.valueOf(paramLeaderboardScore.getRank()))) || (!n.equal(((LeaderboardScore)paramObject).getDisplayRank(), paramLeaderboardScore.getDisplayRank())) || (!n.equal(Long.valueOf(((LeaderboardScore)paramObject).getRawScore()), Long.valueOf(paramLeaderboardScore.getRawScore()))) || (!n.equal(((LeaderboardScore)paramObject).getDisplayScore(), paramLeaderboardScore.getDisplayScore())) || (!n.equal(Long.valueOf(((LeaderboardScore)paramObject).getTimestampMillis()), Long.valueOf(paramLeaderboardScore.getTimestampMillis()))) || (!n.equal(((LeaderboardScore)paramObject).getScoreHolderDisplayName(), paramLeaderboardScore.getScoreHolderDisplayName())) || (!n.equal(((LeaderboardScore)paramObject).getScoreHolderIconImageUri(), paramLeaderboardScore.getScoreHolderIconImageUri())) || (!n.equal(((LeaderboardScore)paramObject).getScoreHolderHiResImageUri(), paramLeaderboardScore.getScoreHolderHiResImageUri())) || (!n.equal(((LeaderboardScore)paramObject).getScoreHolder(), paramLeaderboardScore.getScoreHolder()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((LeaderboardScore)paramObject).getScoreTag(), paramLeaderboardScore.getScoreTag()));
    return false;
  }
  
  static String b(LeaderboardScore paramLeaderboardScore)
  {
    n.a locala = n.h(paramLeaderboardScore).a("Rank", Long.valueOf(paramLeaderboardScore.getRank())).a("DisplayRank", paramLeaderboardScore.getDisplayRank()).a("Score", Long.valueOf(paramLeaderboardScore.getRawScore())).a("DisplayScore", paramLeaderboardScore.getDisplayScore()).a("Timestamp", Long.valueOf(paramLeaderboardScore.getTimestampMillis())).a("DisplayName", paramLeaderboardScore.getScoreHolderDisplayName()).a("IconImageUri", paramLeaderboardScore.getScoreHolderIconImageUri()).a("IconImageUrl", paramLeaderboardScore.getScoreHolderIconImageUrl()).a("HiResImageUri", paramLeaderboardScore.getScoreHolderHiResImageUri()).a("HiResImageUrl", paramLeaderboardScore.getScoreHolderHiResImageUrl());
    if (paramLeaderboardScore.getScoreHolder() == null) {}
    for (Object localObject = null;; localObject = paramLeaderboardScore.getScoreHolder()) {
      return locala.a("Player", localObject).a("ScoreTag", paramLeaderboardScore.getScoreTag()).toString();
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public String getDisplayRank()
  {
    return this.abA;
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.abA, paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return this.abB;
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.abB, paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return this.abz;
  }
  
  public long getRawScore()
  {
    return this.abC;
  }
  
  public Player getScoreHolder()
  {
    return this.abH;
  }
  
  public String getScoreHolderDisplayName()
  {
    if (this.abH == null) {
      return this.abE;
    }
    return this.abH.getDisplayName();
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.abH == null)
    {
      jv.b(this.abE, paramCharArrayBuffer);
      return;
    }
    this.abH.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (this.abH == null) {
      return this.abG;
    }
    return this.abH.getHiResImageUri();
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (this.abH == null) {
      return this.abK;
    }
    return this.abH.getHiResImageUrl();
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (this.abH == null) {
      return this.abF;
    }
    return this.abH.getIconImageUri();
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (this.abH == null) {
      return this.abJ;
    }
    return this.abH.getIconImageUrl();
  }
  
  public String getScoreTag()
  {
    return this.abI;
  }
  
  public long getTimestampMillis()
  {
    return this.abD;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public LeaderboardScore lC()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/LeaderboardScoreEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */