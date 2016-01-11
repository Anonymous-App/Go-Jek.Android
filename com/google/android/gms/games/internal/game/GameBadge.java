package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public abstract interface GameBadge
  extends Parcelable, Freezable<GameBadge>
{
  public abstract String getDescription();
  
  public abstract Uri getIconImageUri();
  
  public abstract String getTitle();
  
  public abstract int getType();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/GameBadge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */