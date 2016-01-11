package com.google.android.gms.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public abstract interface GamesMetadata
{
  public abstract Game getCurrentGame(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<LoadGamesResult> loadGame(GoogleApiClient paramGoogleApiClient);
  
  public static abstract interface LoadExtendedGamesResult
    extends Releasable, Result
  {}
  
  public static abstract interface LoadGameInstancesResult
    extends Releasable, Result
  {}
  
  public static abstract interface LoadGameSearchSuggestionsResult
    extends Releasable, Result
  {}
  
  public static abstract interface LoadGamesResult
    extends Releasable, Result
  {
    public abstract GameBuffer getGames();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/GamesMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */