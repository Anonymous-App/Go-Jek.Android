package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import java.util.ArrayList;

public final class LeaderboardRef
  extends d
  implements Leaderboard
{
  private final int aaK;
  private final Game abx;
  
  LeaderboardRef(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.aaK = paramInt2;
    this.abx = new GameRef(paramDataHolder, paramInt1);
  }
  
  public boolean equals(Object paramObject)
  {
    return LeaderboardEntity.a(this, paramObject);
  }
  
  public String getDisplayName()
  {
    return getString("name");
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    a("name", paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.abx;
  }
  
  public Uri getIconImageUri()
  {
    return aR("board_icon_image_uri");
  }
  
  public String getIconImageUrl()
  {
    return getString("board_icon_image_url");
  }
  
  public String getLeaderboardId()
  {
    return getString("external_leaderboard_id");
  }
  
  public int getScoreOrder()
  {
    return getInteger("score_order");
  }
  
  public ArrayList<LeaderboardVariant> getVariants()
  {
    ArrayList localArrayList = new ArrayList(this.aaK);
    int i = 0;
    while (i < this.aaK)
    {
      localArrayList.add(new LeaderboardVariantRef(this.II, this.JX + i));
      i += 1;
    }
    return localArrayList;
  }
  
  public int hashCode()
  {
    return LeaderboardEntity.a(this);
  }
  
  public Leaderboard lz()
  {
    return new LeaderboardEntity(this);
  }
  
  public String toString()
  {
    return LeaderboardEntity.b(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/LeaderboardRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */