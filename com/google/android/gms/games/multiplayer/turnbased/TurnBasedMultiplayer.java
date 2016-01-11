package com.google.android.gms.games.multiplayer.turnbased;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import java.util.List;

public abstract interface TurnBasedMultiplayer
{
  public abstract PendingResult<InitiateMatchResult> acceptInvitation(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<CancelMatchResult> cancelMatch(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<InitiateMatchResult> createMatch(GoogleApiClient paramGoogleApiClient, TurnBasedMatchConfig paramTurnBasedMatchConfig);
  
  public abstract void declineInvitation(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract void dismissInvitation(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract void dismissMatch(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<UpdateMatchResult> finishMatch(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<UpdateMatchResult> finishMatch(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte, List<ParticipantResult> paramList);
  
  public abstract PendingResult<UpdateMatchResult> finishMatch(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte, ParticipantResult... paramVarArgs);
  
  public abstract Intent getInboxIntent(GoogleApiClient paramGoogleApiClient);
  
  public abstract int getMaxMatchDataSize(GoogleApiClient paramGoogleApiClient);
  
  public abstract Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2);
  
  public abstract Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, boolean paramBoolean);
  
  public abstract PendingResult<LeaveMatchResult> leaveMatch(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<LeaveMatchResult> leaveMatchDuringTurn(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);
  
  public abstract PendingResult<LoadMatchResult> loadMatch(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<LoadMatchesResult> loadMatchesByStatus(GoogleApiClient paramGoogleApiClient, int paramInt, int[] paramArrayOfInt);
  
  public abstract PendingResult<LoadMatchesResult> loadMatchesByStatus(GoogleApiClient paramGoogleApiClient, int[] paramArrayOfInt);
  
  public abstract void registerMatchUpdateListener(GoogleApiClient paramGoogleApiClient, OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener);
  
  public abstract PendingResult<InitiateMatchResult> rematch(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<UpdateMatchResult> takeTurn(GoogleApiClient paramGoogleApiClient, String paramString1, byte[] paramArrayOfByte, String paramString2);
  
  public abstract PendingResult<UpdateMatchResult> takeTurn(GoogleApiClient paramGoogleApiClient, String paramString1, byte[] paramArrayOfByte, String paramString2, List<ParticipantResult> paramList);
  
  public abstract PendingResult<UpdateMatchResult> takeTurn(GoogleApiClient paramGoogleApiClient, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult... paramVarArgs);
  
  public abstract void unregisterMatchUpdateListener(GoogleApiClient paramGoogleApiClient);
  
  public static abstract interface CancelMatchResult
    extends Result
  {
    public abstract String getMatchId();
  }
  
  public static abstract interface InitiateMatchResult
    extends Result
  {
    public abstract TurnBasedMatch getMatch();
  }
  
  public static abstract interface LeaveMatchResult
    extends Result
  {
    public abstract TurnBasedMatch getMatch();
  }
  
  public static abstract interface LoadMatchResult
    extends Result
  {
    public abstract TurnBasedMatch getMatch();
  }
  
  public static abstract interface LoadMatchesResult
    extends Releasable, Result
  {
    public abstract LoadMatchesResponse getMatches();
  }
  
  public static abstract interface UpdateMatchResult
    extends Result
  {
    public abstract TurnBasedMatch getMatch();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/turnbased/TurnBasedMultiplayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */