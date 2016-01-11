package com.google.android.gms.games.internal.game;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import java.util.ArrayList;

public abstract interface ExtendedGame
  extends Parcelable, Freezable<ExtendedGame>
{
  public abstract Game getGame();
  
  public abstract ArrayList<GameBadge> kR();
  
  public abstract int kS();
  
  public abstract boolean kT();
  
  public abstract int kU();
  
  public abstract long kV();
  
  public abstract long kW();
  
  public abstract String kX();
  
  public abstract long kY();
  
  public abstract String kZ();
  
  public abstract SnapshotMetadata la();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/ExtendedGame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */