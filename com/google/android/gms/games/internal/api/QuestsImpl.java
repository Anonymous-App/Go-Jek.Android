package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;

public final class QuestsImpl
  implements Quests
{
  public PendingResult<Quests.AcceptQuestResult> accept(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new AcceptImpl(paramString)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.i(this, paramString);
      }
    });
  }
  
  public PendingResult<Quests.ClaimMilestoneResult> claim(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
  {
    paramGoogleApiClient.b(new ClaimImpl(paramString1)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(this, paramString1, paramString2);
      }
    });
  }
  
  public Intent getQuestIntent(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return Games.c(paramGoogleApiClient).bC(paramString);
  }
  
  public Intent getQuestsIntent(GoogleApiClient paramGoogleApiClient, int[] paramArrayOfInt)
  {
    return Games.c(paramGoogleApiClient).b(paramArrayOfInt);
  }
  
  public PendingResult<Quests.LoadQuestsResult> load(GoogleApiClient paramGoogleApiClient, final int[] paramArrayOfInt, final int paramInt, final boolean paramBoolean)
  {
    paramGoogleApiClient.a(new LoadsImpl(paramArrayOfInt)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.a(this, paramArrayOfInt, paramInt, paramBoolean);
      }
    });
  }
  
  public PendingResult<Quests.LoadQuestsResult> loadByIds(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean, final String... paramVarArgs)
  {
    paramGoogleApiClient.a(new LoadsImpl(paramBoolean)
    {
      protected void a(GamesClientImpl paramAnonymousGamesClientImpl)
      {
        paramAnonymousGamesClientImpl.b(this, paramBoolean, paramVarArgs);
      }
    });
  }
  
  public void registerQuestUpdateListener(GoogleApiClient paramGoogleApiClient, QuestUpdateListener paramQuestUpdateListener)
  {
    Games.c(paramGoogleApiClient).a(paramQuestUpdateListener);
  }
  
  public void showStateChangedPopup(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    Games.c(paramGoogleApiClient).bD(paramString);
  }
  
  public void unregisterQuestUpdateListener(GoogleApiClient paramGoogleApiClient)
  {
    Games.c(paramGoogleApiClient).kk();
  }
  
  private static abstract class AcceptImpl
    extends Games.BaseGamesApiMethodImpl<Quests.AcceptQuestResult>
  {
    public Quests.AcceptQuestResult ah(final Status paramStatus)
    {
      new Quests.AcceptQuestResult()
      {
        public Quest getQuest()
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
  
  private static abstract class ClaimImpl
    extends Games.BaseGamesApiMethodImpl<Quests.ClaimMilestoneResult>
  {
    public Quests.ClaimMilestoneResult ai(final Status paramStatus)
    {
      new Quests.ClaimMilestoneResult()
      {
        public Milestone getMilestone()
        {
          return null;
        }
        
        public Quest getQuest()
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
  
  private static abstract class LoadsImpl
    extends Games.BaseGamesApiMethodImpl<Quests.LoadQuestsResult>
  {
    public Quests.LoadQuestsResult aj(final Status paramStatus)
    {
      new Quests.LoadQuestsResult()
      {
        public QuestBuffer getQuests()
        {
          return new QuestBuffer(DataHolder.as(paramStatus.getStatusCode()));
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/QuestsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */