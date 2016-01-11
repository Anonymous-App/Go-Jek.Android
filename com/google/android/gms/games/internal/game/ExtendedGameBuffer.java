package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class ExtendedGameBuffer
  extends g<ExtendedGame>
{
  public ExtendedGameBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected String gD()
  {
    return "external_game_id";
  }
  
  protected ExtendedGame h(int paramInt1, int paramInt2)
  {
    return new ExtendedGameRef(this.II, paramInt1, paramInt2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/ExtendedGameBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */