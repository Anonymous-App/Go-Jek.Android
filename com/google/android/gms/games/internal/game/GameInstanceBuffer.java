package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class GameInstanceBuffer
  extends DataBuffer<GameInstance>
{
  public GameInstanceBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  public GameInstance dM(int paramInt)
  {
    return new GameInstanceRef(this.II, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/GameInstanceBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */