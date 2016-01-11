package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;

public final class TurnBasedMatchTurnStatus
{
  public static String dH(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      GamesLog.q("MatchTurnStatus", "Unknown match turn status: " + paramInt);
      return "TURN_STATUS_UNKNOWN";
    case 0: 
      return "TURN_STATUS_INVITED";
    case 1: 
      return "TURN_STATUS_MY_TURN";
    case 2: 
      return "TURN_STATUS_THEIR_TURN";
    }
    return "TURN_STATUS_COMPLETE";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/constants/TurnBasedMatchTurnStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */