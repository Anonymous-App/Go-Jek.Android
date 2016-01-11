package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

public final class LeaderboardEntity
  implements Leaderboard
{
  private final String NH;
  private final Uri Vh;
  private final String Vs;
  private final String abu;
  private final int abv;
  private final ArrayList<LeaderboardVariantEntity> abw;
  private final Game abx;
  
  public LeaderboardEntity(Leaderboard paramLeaderboard)
  {
    this.abu = paramLeaderboard.getLeaderboardId();
    this.NH = paramLeaderboard.getDisplayName();
    this.Vh = paramLeaderboard.getIconImageUri();
    this.Vs = paramLeaderboard.getIconImageUrl();
    this.abv = paramLeaderboard.getScoreOrder();
    Object localObject = paramLeaderboard.getGame();
    if (localObject == null) {}
    for (localObject = null;; localObject = new GameEntity((Game)localObject))
    {
      this.abx = ((Game)localObject);
      paramLeaderboard = paramLeaderboard.getVariants();
      int j = paramLeaderboard.size();
      this.abw = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        this.abw.add((LeaderboardVariantEntity)((LeaderboardVariant)paramLeaderboard.get(i)).freeze());
        i += 1;
      }
    }
  }
  
  static int a(Leaderboard paramLeaderboard)
  {
    return n.hashCode(new Object[] { paramLeaderboard.getLeaderboardId(), paramLeaderboard.getDisplayName(), paramLeaderboard.getIconImageUri(), Integer.valueOf(paramLeaderboard.getScoreOrder()), paramLeaderboard.getVariants() });
  }
  
  static boolean a(Leaderboard paramLeaderboard, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Leaderboard)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboard == paramObject);
      paramObject = (Leaderboard)paramObject;
      if ((!n.equal(((Leaderboard)paramObject).getLeaderboardId(), paramLeaderboard.getLeaderboardId())) || (!n.equal(((Leaderboard)paramObject).getDisplayName(), paramLeaderboard.getDisplayName())) || (!n.equal(((Leaderboard)paramObject).getIconImageUri(), paramLeaderboard.getIconImageUri())) || (!n.equal(Integer.valueOf(((Leaderboard)paramObject).getScoreOrder()), Integer.valueOf(paramLeaderboard.getScoreOrder())))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(((Leaderboard)paramObject).getVariants(), paramLeaderboard.getVariants()));
    return false;
  }
  
  static String b(Leaderboard paramLeaderboard)
  {
    return n.h(paramLeaderboard).a("LeaderboardId", paramLeaderboard.getLeaderboardId()).a("DisplayName", paramLeaderboard.getDisplayName()).a("IconImageUri", paramLeaderboard.getIconImageUri()).a("IconImageUrl", paramLeaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(paramLeaderboard.getScoreOrder())).a("Variants", paramLeaderboard.getVariants()).toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.NH, paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.abx;
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getIconImageUrl()
  {
    return this.Vs;
  }
  
  public String getLeaderboardId()
  {
    return this.abu;
  }
  
  public int getScoreOrder()
  {
    return this.abv;
  }
  
  public ArrayList<LeaderboardVariant> getVariants()
  {
    return new ArrayList(this.abw);
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public Leaderboard lz()
  {
    return this;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/LeaderboardEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */