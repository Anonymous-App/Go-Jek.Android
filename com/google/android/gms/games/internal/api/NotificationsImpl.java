package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.Notifications.ContactSettingLoadResult;
import com.google.android.gms.games.Notifications.GameMuteStatusChangeResult;
import com.google.android.gms.games.Notifications.GameMuteStatusLoadResult;
import com.google.android.gms.games.Notifications.InboxCountResult;
import com.google.android.gms.games.internal.GamesClientImpl;

public final class NotificationsImpl
  implements Notifications
{
  public void clear(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    Games.c(paramGoogleApiClient).dC(paramInt);
  }
  
  public void clearAll(GoogleApiClient paramGoogleApiClient)
  {
    clear(paramGoogleApiClient, 31);
  }
  
  private static abstract class ContactSettingLoadImpl
    extends Games.BaseGamesApiMethodImpl<Notifications.ContactSettingLoadResult>
  {
    public Notifications.ContactSettingLoadResult aa(final Status paramStatus)
    {
      new Notifications.ContactSettingLoadResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class ContactSettingUpdateImpl
    extends Games.BaseGamesApiMethodImpl<Status>
  {
    public Status d(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  private static abstract class InboxCountImpl
    extends Games.BaseGamesApiMethodImpl<Notifications.InboxCountResult>
  {
    public Notifications.InboxCountResult ab(final Status paramStatus)
    {
      new Notifications.InboxCountResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/NotificationsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */