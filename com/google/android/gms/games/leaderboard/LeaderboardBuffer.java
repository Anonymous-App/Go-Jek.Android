package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class LeaderboardBuffer
  extends g<Leaderboard>
{
  public LeaderboardBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_leaderboard_id";
  }
  
  protected Leaderboard i(int paramInt1, int paramInt2)
  {
    return new LeaderboardRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/LeaderboardBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */