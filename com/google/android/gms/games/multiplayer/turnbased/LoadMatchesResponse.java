package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
  private final TurnBasedMatchBuffer acA;
  private final TurnBasedMatchBuffer acB;
  private final TurnBasedMatchBuffer acC;
  private final InvitationBuffer acz;
  
  public LoadMatchesResponse(Bundle paramBundle)
  {
    DataHolder localDataHolder = a(paramBundle, 0);
    if (localDataHolder != null)
    {
      this.acz = new InvitationBuffer(localDataHolder);
      localDataHolder = a(paramBundle, 1);
      if (localDataHolder == null) {
        break label101;
      }
      this.acA = new TurnBasedMatchBuffer(localDataHolder);
      label48:
      localDataHolder = a(paramBundle, 2);
      if (localDataHolder == null) {
        break label109;
      }
    }
    label101:
    label109:
    for (this.acB = new TurnBasedMatchBuffer(localDataHolder);; this.acB = null)
    {
      paramBundle = a(paramBundle, 3);
      if (paramBundle == null) {
        break label117;
      }
      this.acC = new TurnBasedMatchBuffer(paramBundle);
      return;
      this.acz = null;
      break;
      this.acA = null;
      break label48;
    }
    label117:
    this.acC = null;
  }
  
  private static DataHolder a(Bundle paramBundle, int paramInt)
  {
    String str = TurnBasedMatchTurnStatus.dH(paramInt);
    if (!paramBundle.containsKey(str)) {
      return null;
    }
    return (DataHolder)paramBundle.getParcelable(str);
  }
  
  public void close()
  {
    if (this.acz != null) {
      this.acz.close();
    }
    if (this.acA != null) {
      this.acA.close();
    }
    if (this.acB != null) {
      this.acB.close();
    }
    if (this.acC != null) {
      this.acC.close();
    }
  }
  
  public TurnBasedMatchBuffer getCompletedMatches()
  {
    return this.acC;
  }
  
  public InvitationBuffer getInvitations()
  {
    return this.acz;
  }
  
  public TurnBasedMatchBuffer getMyTurnMatches()
  {
    return this.acA;
  }
  
  public TurnBasedMatchBuffer getTheirTurnMatches()
  {
    return this.acB;
  }
  
  public boolean hasData()
  {
    if ((this.acz != null) && (this.acz.getCount() > 0)) {}
    while (((this.acA != null) && (this.acA.getCount() > 0)) || ((this.acB != null) && (this.acB.getCount() > 0)) || ((this.acC != null) && (this.acC.getCount() > 0))) {
      return true;
    }
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/turnbased/LoadMatchesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */