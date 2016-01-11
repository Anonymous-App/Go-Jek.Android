package com.google.android.gms.games.multiplayer;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public abstract interface Invitations
{
  public abstract Intent getInvitationInboxIntent(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient paramGoogleApiClient, int paramInt);
  
  public abstract void registerInvitationListener(GoogleApiClient paramGoogleApiClient, OnInvitationReceivedListener paramOnInvitationReceivedListener);
  
  public abstract void unregisterInvitationListener(GoogleApiClient paramGoogleApiClient);
  
  public static abstract interface LoadInvitationsResult
    extends Releasable, Result
  {
    public abstract InvitationBuffer getInvitations();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/Invitations.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */