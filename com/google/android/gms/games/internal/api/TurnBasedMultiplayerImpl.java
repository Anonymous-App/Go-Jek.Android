package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;
import java.util.List;

public final class TurnBasedMultiplayerImpl
  implements TurnBasedMultiplayer
{
  public PendingResult<TurnBasedMultiplayer.InitiateMatchResult> acceptInvitation(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new InitiateMatchImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.e(this, paramString);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.CancelMatchResult> cancelMatch(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new CancelMatchImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.g(this, paramString);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.InitiateMatchResult> createMatch(GoogleApiClient paramGoogleApiClient, final TurnBasedMatchConfig paramTurnBasedMatchConfig)
  {
    paramGoogleApiClient.b(new InitiateMatchImpl(paramTurnBasedMatchConfig)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramTurnBasedMatchConfig);
      }
    });
  }
  
  public void declineInvitation(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    Games.c(paramGoogleApiClient).p(paramString, 1);
  }
  
  public void dismissInvitation(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    Games.c(paramGoogleApiClient).o(paramString, 1);
  }
  
  public void dismissMatch(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    Games.c(paramGoogleApiClient).by(paramString);
  }
  
  public PendingResult<TurnBasedMultiplayer.UpdateMatchResult> finishMatch(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return finishMatch(paramGoogleApiClient, paramString, null, (ParticipantResult[])null);
  }
  
  public PendingResult<TurnBasedMultiplayer.UpdateMatchResult> finishMatch(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte, List<ParticipantResult> paramList)
  {
    if (paramList == null) {}
    for (paramList = null;; paramList = (ParticipantResult[])paramList.toArray(new ParticipantResult[paramList.size()])) {
      return finishMatch(paramGoogleApiClient, paramString, paramArrayOfByte, paramList);
    }
  }
  
  public PendingResult<TurnBasedMultiplayer.UpdateMatchResult> finishMatch(GoogleApiClient paramGoogleApiClient, final String paramString, final byte[] paramArrayOfByte, final ParticipantResult... paramVarArgs)
  {
    paramGoogleApiClient.b(new UpdateMatchImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString, paramArrayOfByte, paramVarArgs);
      }
    });
  }
  
  public Intent getInboxIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).kg();
  }
  
  public int getMaxMatchDataSize(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).kq();
  }
  
  public Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2)
  {
    return Games.c(paramGoogleApiClient).a(paramInt1, paramInt2, true);
  }
  
  public Intent getSelectOpponentsIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return Games.c(paramGoogleApiClient).a(paramInt1, paramInt2, paramBoolean);
  }
  
  public PendingResult<TurnBasedMultiplayer.LeaveMatchResult> leaveMatch(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new LeaveMatchImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.f(this, paramString);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.LeaveMatchResult> leaveMatchDuringTurn(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
  {
    paramGoogleApiClient.b(new LeaveMatchImpl(paramString1)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString1, paramString2);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.LoadMatchResult> loadMatch(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.a(new LoadMatchImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.h(this, paramString);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.LoadMatchesResult> loadMatchesByStatus(GoogleApiClient paramGoogleApiClient, final int paramInt, final int[] paramArrayOfInt)
  {
    paramGoogleApiClient.a(new LoadMatchesImpl(paramInt)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramInt, paramArrayOfInt);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.LoadMatchesResult> loadMatchesByStatus(GoogleApiClient paramGoogleApiClient, int[] paramArrayOfInt)
  {
    return loadMatchesByStatus(paramGoogleApiClient, 0, paramArrayOfInt);
  }
  
  public void registerMatchUpdateListener(GoogleApiClient paramGoogleApiClient, OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
  {
    Games.c(paramGoogleApiClient).a(paramOnTurnBasedMatchUpdateReceivedListener);
  }
  
  public PendingResult<TurnBasedMultiplayer.InitiateMatchResult> rematch(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new InitiateMatchImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.d(this, paramString);
      }
    });
  }
  
  public PendingResult<TurnBasedMultiplayer.UpdateMatchResult> takeTurn(GoogleApiClient paramGoogleApiClient, String paramString1, byte[] paramArrayOfByte, String paramString2)
  {
    return takeTurn(paramGoogleApiClient, paramString1, paramArrayOfByte, paramString2, (ParticipantResult[])null);
  }
  
  public PendingResult<TurnBasedMultiplayer.UpdateMatchResult> takeTurn(GoogleApiClient paramGoogleApiClient, String paramString1, byte[] paramArrayOfByte, String paramString2, List<ParticipantResult> paramList)
  {
    if (paramList == null) {}
    for (paramList = null;; paramList = (ParticipantResult[])paramList.toArray(new ParticipantResult[paramList.size()])) {
      return takeTurn(paramGoogleApiClient, paramString1, paramArrayOfByte, paramString2, paramList);
    }
  }
  
  public PendingResult<TurnBasedMultiplayer.UpdateMatchResult> takeTurn(GoogleApiClient paramGoogleApiClient, final String paramString1, final byte[] paramArrayOfByte, final String paramString2, final ParticipantResult... paramVarArgs)
  {
    paramGoogleApiClient.b(new UpdateMatchImpl(paramString1)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString1, paramArrayOfByte, paramString2, paramVarArgs);
      }
    });
  }
  
  public void unregisterMatchUpdateListener(GoogleApiClient paramGoogleApiClient)
  {
    Games.c(paramGoogleApiClient).kj();
  }
  
  private static abstract class CancelMatchImpl
    extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.CancelMatchResult>
  {
    private final String BL;
    
    public CancelMatchImpl(String paramString)
    {
      this.BL = paramString;
    }
    
    public TurnBasedMultiplayer.CancelMatchResult as(final Status paramStatus)
    {
      new TurnBasedMultiplayer.CancelMatchResult()
      {
        public String getMatchId()
        {
          return TurnBasedMultiplayerImpl.CancelMatchImpl.a(TurnBasedMultiplayerImpl.CancelMatchImpl.this);
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class InitiateMatchImpl
    extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.InitiateMatchResult>
  {
    public TurnBasedMultiplayer.InitiateMatchResult at(final Status paramStatus)
    {
      new TurnBasedMultiplayer.InitiateMatchResult()
      {
        public TurnBasedMatch getMatch()
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
  
  private static abstract class LeaveMatchImpl
    extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.LeaveMatchResult>
  {
    public TurnBasedMultiplayer.LeaveMatchResult au(final Status paramStatus)
    {
      new TurnBasedMultiplayer.LeaveMatchResult()
      {
        public TurnBasedMatch getMatch()
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
  
  private static abstract class LoadMatchImpl
    extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.LoadMatchResult>
  {
    public TurnBasedMultiplayer.LoadMatchResult av(final Status paramStatus)
    {
      new TurnBasedMultiplayer.LoadMatchResult()
      {
        public TurnBasedMatch getMatch()
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
  
  private static abstract class LoadMatchesImpl
    extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.LoadMatchesResult>
  {
    public TurnBasedMultiplayer.LoadMatchesResult aw(final Status paramStatus)
    {
      new TurnBasedMultiplayer.LoadMatchesResult()
      {
        public LoadMatchesResponse getMatches()
        {
          return new LoadMatchesResponse(new Bundle());
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class UpdateMatchImpl
    extends Games.BaseGamesApiMethodImpl<TurnBasedMultiplayer.UpdateMatchResult>
  {
    public TurnBasedMultiplayer.UpdateMatchResult ax(final Status paramStatus)
    {
      new TurnBasedMultiplayer.UpdateMatchResult()
      {
        public TurnBasedMatch getMatch()
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
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/TurnBasedMultiplayerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */