package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.internal.constants.TimeSpan;
import java.util.HashMap;

public final class ScoreSubmissionData
{
  private static final String[] abs = { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
  private int HF;
  private String VK;
  private HashMap<Integer, Result> abY;
  private String abu;
  
  public ScoreSubmissionData(DataHolder paramDataHolder)
  {
    this.HF = paramDataHolder.getStatusCode();
    this.abY = new HashMap();
    int j = paramDataHolder.getCount();
    if (j == 3) {}
    for (boolean bool = true;; bool = false)
    {
      o.K(bool);
      int i = 0;
      while (i < j)
      {
        int k = paramDataHolder.ar(i);
        if (i == 0)
        {
          this.abu = paramDataHolder.c("leaderboardId", i, k);
          this.VK = paramDataHolder.c("playerId", i, k);
        }
        if (paramDataHolder.d("hasResult", i, k)) {
          a(new Result(paramDataHolder.a("rawScore", i, k), paramDataHolder.c("formattedScore", i, k), paramDataHolder.c("scoreTag", i, k), paramDataHolder.d("newBest", i, k)), paramDataHolder.b("timeSpan", i, k));
        }
        i += 1;
      }
    }
  }
  
  private void a(Result paramResult, int paramInt)
  {
    this.abY.put(Integer.valueOf(paramInt), paramResult);
  }
  
  public String getLeaderboardId()
  {
    return this.abu;
  }
  
  public String getPlayerId()
  {
    return this.VK;
  }
  
  public Result getScoreResult(int paramInt)
  {
    return (Result)this.abY.get(Integer.valueOf(paramInt));
  }
  
  public String toString()
  {
    n.a locala = n.h(this).a("PlayerId", this.VK).a("StatusCode", Integer.valueOf(this.HF));
    int i = 0;
    if (i < 3)
    {
      Object localObject = (Result)this.abY.get(Integer.valueOf(i));
      locala.a("TimesSpan", TimeSpan.dH(i));
      if (localObject == null) {}
      for (localObject = "null";; localObject = ((Result)localObject).toString())
      {
        locala.a("Result", localObject);
        i += 1;
        break;
      }
    }
    return locala.toString();
  }
  
  public static final class Result
  {
    public final String formattedScore;
    public final boolean newBest;
    public final long rawScore;
    public final String scoreTag;
    
    public Result(long paramLong, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.rawScore = paramLong;
      this.formattedScore = paramString1;
      this.scoreTag = paramString2;
      this.newBest = paramBoolean;
    }
    
    public String toString()
    {
      return n.h(this).a("RawScore", Long.valueOf(this.rawScore)).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", Boolean.valueOf(this.newBest)).toString();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/ScoreSubmissionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */