package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.internal.o;
import java.util.ArrayList;

public final class TurnBasedMatchConfig
{
  private final int acD;
  private final int acd;
  private final String[] acr;
  private final Bundle acs;
  
  private TurnBasedMatchConfig(Builder paramBuilder)
  {
    this.acd = paramBuilder.acd;
    this.acD = paramBuilder.acD;
    this.acs = paramBuilder.acs;
    int i = paramBuilder.acv.size();
    this.acr = ((String[])paramBuilder.acv.toArray(new String[i]));
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  public static Bundle createAutoMatchCriteria(int paramInt1, int paramInt2, long paramLong)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("min_automatch_players", paramInt1);
    localBundle.putInt("max_automatch_players", paramInt2);
    localBundle.putLong("exclusive_bit_mask", paramLong);
    return localBundle;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.acs;
  }
  
  public String[] getInvitedPlayerIds()
  {
    return this.acr;
  }
  
  public int getVariant()
  {
    return this.acd;
  }
  
  public int lH()
  {
    return this.acD;
  }
  
  public static final class Builder
  {
    int acD = 2;
    int acd = -1;
    Bundle acs = null;
    ArrayList<String> acv = new ArrayList();
    
    public Builder addInvitedPlayer(String paramString)
    {
      o.i(paramString);
      this.acv.add(paramString);
      return this;
    }
    
    public Builder addInvitedPlayers(ArrayList<String> paramArrayList)
    {
      o.i(paramArrayList);
      this.acv.addAll(paramArrayList);
      return this;
    }
    
    public TurnBasedMatchConfig build()
    {
      return new TurnBasedMatchConfig(this, null);
    }
    
    public Builder setAutoMatchCriteria(Bundle paramBundle)
    {
      this.acs = paramBundle;
      return this;
    }
    
    public Builder setVariant(int paramInt)
    {
      if ((paramInt == -1) || (paramInt > 0)) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
        this.acd = paramInt;
        return this;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */