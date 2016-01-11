package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef
  extends d
  implements LeaderboardScore
{
  private final PlayerRef abL;
  
  LeaderboardScoreRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.abL = new PlayerRef(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return LeaderboardScoreEntity.a(this, paramObject);
  }
  
  public String getDisplayRank()
  {
    return getString("display_rank");
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    a("display_rank", paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return getString("display_score");
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    a("display_score", paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return getLong("rank");
  }
  
  public long getRawScore()
  {
    return getLong("raw_score");
  }
  
  public Player getScoreHolder()
  {
    if (aS("external_player_id")) {
      return null;
    }
    return this.abL;
  }
  
  public String getScoreHolderDisplayName()
  {
    if (aS("external_player_id")) {
      return getString("default_display_name");
    }
    return this.abL.getDisplayName();
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (aS("external_player_id"))
    {
      a("default_display_name", paramCharArrayBuffer);
      return;
    }
    this.abL.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (aS("external_player_id")) {
      return null;
    }
    return this.abL.getHiResImageUri();
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (aS("external_player_id")) {
      return null;
    }
    return this.abL.getHiResImageUrl();
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (aS("external_player_id")) {
      return aR("default_display_image_uri");
    }
    return this.abL.getIconImageUri();
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (aS("external_player_id")) {
      return getString("default_display_image_url");
    }
    return this.abL.getIconImageUrl();
  }
  
  public String getScoreTag()
  {
    return getString("score_tag");
  }
  
  public long getTimestampMillis()
  {
    return getLong("achieved_timestamp");
  }
  
  public int hashCode()
  {
    return LeaderboardScoreEntity.a(this);
  }
  
  public LeaderboardScore lC()
  {
    return new LeaderboardScoreEntity(this);
  }
  
  public String toString()
  {
    return LeaderboardScoreEntity.b(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/LeaderboardScoreRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */