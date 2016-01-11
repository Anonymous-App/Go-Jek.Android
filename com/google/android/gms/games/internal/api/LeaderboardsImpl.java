package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

public final class LeaderboardsImpl
  implements Leaderboards
{
  public Intent getAllLeaderboardsIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).ke();
  }
  
  public Intent getLeaderboardIntent(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return Games.c(paramGoogleApiClient).bx(paramString);
  }
  
  public PendingResult<Leaderboards.LoadPlayerScoreResult> loadCurrentPlayerLeaderboardScore(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt1, final int paramInt2)
  {
    paramGoogleApiClient.a(new LoadPlayerScoreImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, null, paramString, paramInt1, paramInt2);
      }
    });
  }
  
  public PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient paramGoogleApiClient, final String paramString, final boolean paramBoolean)
  {
    paramGoogleApiClient.a(new LoadMetadataImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString, paramBoolean);
      }
    });
  }
  
  public PendingResult<Leaderboards.LeaderboardMetadataResult> loadLeaderboardMetadata(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean)
  {
    paramGoogleApiClient.a(new LoadMetadataImpl(paramBoolean)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(this, paramBoolean);
      }
    });
  }
  
  public PendingResult<Leaderboards.LoadScoresResult> loadMoreScores(GoogleApiClient paramGoogleApiClient, final LeaderboardScoreBuffer paramLeaderboardScoreBuffer, final int paramInt1, final int paramInt2)
  {
    paramGoogleApiClient.a(new LoadScoresImpl(paramLeaderboardScoreBuffer)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramLeaderboardScoreBuffer, paramInt1, paramInt2);
      }
    });
  }
  
  public PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return loadPlayerCenteredScores(paramGoogleApiClient, paramString, paramInt1, paramInt2, paramInt3, false);
  }
  
  public PendingResult<Leaderboards.LoadScoresResult> loadPlayerCenteredScores(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt1, final int paramInt2, final int paramInt3, final boolean paramBoolean)
  {
    paramGoogleApiClient.a(new LoadScoresImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(this, paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
      }
    });
  }
  
  public PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient paramGoogleApiClient, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return loadTopScores(paramGoogleApiClient, paramString, paramInt1, paramInt2, paramInt3, false);
  }
  
  public PendingResult<Leaderboards.LoadScoresResult> loadTopScores(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt1, final int paramInt2, final int paramInt3, final boolean paramBoolean)
  {
    paramGoogleApiClient.a(new LoadScoresImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
      }
    });
  }
  
  public void submitScore(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong)
  {
    submitScore(paramGoogleApiClient, paramString, paramLong, null);
  }
  
  public void submitScore(GoogleApiClient paramGoogleApiClient, String paramString1, long paramLong, String paramString2)
  {
    Games.c(paramGoogleApiClient).a(null, paramString1, paramLong, paramString2);
  }
  
  public PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong)
  {
    return submitScoreImmediate(paramGoogleApiClient, paramString, paramLong, null);
  }
  
  public PendingResult<Leaderboards.SubmitScoreResult> submitScoreImmediate(GoogleApiClient paramGoogleApiClient, final String paramString1, final long paramLong, String paramString2)
  {
    paramGoogleApiClient.b(new SubmitScoreImpl()
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString1, paramLong, this.YR);
      }
    });
  }
  
  private static abstract class LoadMetadataImpl
    extends Games.BaseGamesApiMethodImpl<Leaderboards.LeaderboardMetadataResult>
  {
    public Leaderboards.LeaderboardMetadataResult U(final Status paramStatus)
    {
      new Leaderboards.LeaderboardMetadataResult()
      {
        public LeaderboardBuffer getLeaderboards()
        {
          return new LeaderboardBuffer(DataHolder.as(14));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class LoadPlayerScoreImpl
    extends Games.BaseGamesApiMethodImpl<Leaderboards.LoadPlayerScoreResult>
  {
    public Leaderboards.LoadPlayerScoreResult V(final Status paramStatus)
    {
      new Leaderboards.LoadPlayerScoreResult()
      {
        public LeaderboardScore getScore()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class LoadScoresImpl
    extends Games.BaseGamesApiMethodImpl<Leaderboards.LoadScoresResult>
  {
    public Leaderboards.LoadScoresResult W(final Status paramStatus)
    {
      new Leaderboards.LoadScoresResult()
      {
        public Leaderboard getLeaderboard()
        {
          return null;
        }
        
        public LeaderboardScoreBuffer getScores()
        {
          return new LeaderboardScoreBuffer(DataHolder.as(14));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  protected static abstract class SubmitScoreImpl
    extends Games.BaseGamesApiMethodImpl<Leaderboards.SubmitScoreResult>
  {
    public Leaderboards.SubmitScoreResult X(final Status paramStatus)
    {
      new Leaderboards.SubmitScoreResult()
      {
        public ScoreSubmissionData getScoreData()
        {
          return new ScoreSubmissionData(DataHolder.as(14));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/LeaderboardsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */