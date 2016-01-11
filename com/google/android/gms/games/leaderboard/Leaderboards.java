package com.google.android.gms.games.leaderboard;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public abstract interface Leaderboards
{
  public abstract Intent getAllLeaderboardsIntent(GoogleApiClient paramGoogleApiClient);
  
  public abstract Intent getLeaderboardIntent(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2);
  
  public abstract PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean);
  
  public abstract PendingResult<LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient paramGoogleApiClient, boolean paramBoolean);
  
  public abstract PendingResult<LoadScoresResult> loadMoreScores(GoogleApiClient paramGoogleApiClient, LeaderboardScoreBuffer paramLeaderboardScoreBuffer, int paramInt1, int paramInt2);
  
  public abstract PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract PendingResult<LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
  
  public abstract PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract PendingResult<LoadScoresResult> loadTopScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);
  
  public abstract void submitScore(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong);
  
  public abstract void submitScore(GoogleApiClient paramGoogleApiClient, String paramString1, long paramLong, String paramString2);
  
  public abstract PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong);
  
  public abstract PendingResult<SubmitScoreResult> submitScoreImmediate(GoogleApiClient paramGoogleApiClient, String paramString1, long paramLong, String paramString2);
  
  public static abstract interface LeaderboardMetadataResult
    extends Releasable, Result
  {
    public abstract LeaderboardBuffer getLeaderboards();
  }
  
  public static abstract interface LoadPlayerScoreResult
    extends Result
  {
    public abstract LeaderboardScore getScore();
  }
  
  public static abstract interface LoadScoresResult
    extends Releasable, Result
  {
    public abstract Leaderboard getLeaderboard();
    
    public abstract LeaderboardScoreBuffer getScores();
  }
  
  public static abstract interface SubmitScoreResult
    extends Releasable, Result
  {
    public abstract ScoreSubmissionData getScoreData();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/leaderboard/Leaderboards.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */