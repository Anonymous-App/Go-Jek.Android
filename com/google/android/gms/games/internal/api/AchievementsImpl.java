package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class AchievementsImpl
  implements Achievements
{
  public Intent getAchievementsIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).kf();
  }
  
  public void increment(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(null, paramString, paramInt);
      }
    });
  }
  
  public PendingResult<Achievements.UpdateAchievementResult> incrementImmediate(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramString, paramInt);
      }
    });
  }
  
  public PendingResult<Achievements.LoadAchievementsResult> load(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean)
  {
    paramGoogleApiClient.a(new LoadImpl(paramBoolean)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.c(this, paramBoolean);
      }
    });
  }
  
  public void reveal(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(null, paramString);
      }
    });
  }
  
  public PendingResult<Achievements.UpdateAchievementResult> revealImmediate(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(this, paramString);
      }
    });
  }
  
  public void setSteps(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(null, paramString, paramInt);
      }
    });
  }
  
  public PendingResult<Achievements.UpdateAchievementResult> setStepsImmediate(GoogleApiClient paramGoogleApiClient, final String paramString, final int paramInt)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(this, paramString, paramInt);
      }
    });
  }
  
  public void unlock(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.c(null, paramString);
      }
    });
  }
  
  public PendingResult<Achievements.UpdateAchievementResult> unlockImmediate(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new UpdateImpl(paramString)
    {
      public void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.c(this, paramString);
      }
    });
  }
  
  private static abstract class LoadImpl
    extends Games.BaseGamesApiMethodImpl<Achievements.LoadAchievementsResult>
  {
    public Achievements.LoadAchievementsResult J(final Status paramStatus)
    {
      new Achievements.LoadAchievementsResult()
      {
        public AchievementBuffer getAchievements()
        {
          return new AchievementBuffer(DataHolder.as(14));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class UpdateImpl
    extends Games.BaseGamesApiMethodImpl<Achievements.UpdateAchievementResult>
  {
    private final String BL;
    
    public UpdateImpl(String paramString)
    {
      this.BL = paramString;
    }
    
    public Achievements.UpdateAchievementResult K(final Status paramStatus)
    {
      new Achievements.UpdateAchievementResult()
      {
        public String getAchievementId()
        {
          return AchievementsImpl.UpdateImpl.a(AchievementsImpl.UpdateImpl.this);
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/AchievementsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */