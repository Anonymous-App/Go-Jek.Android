package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public abstract interface MostRecentGameInfo
  extends Parcelable, Freezable<MostRecentGameInfo>
{
  public abstract String lp();
  
  public abstract String lq();
  
  public abstract long lr();
  
  public abstract Uri ls();
  
  public abstract Uri lt();
  
  public abstract Uri lu();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/player/MostRecentGameInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */