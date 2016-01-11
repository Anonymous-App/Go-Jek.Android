package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataBuffer;

public final class GameBadgeBuffer
  extends DataBuffer<GameBadge>
{
  public GameBadge dK(int paramInt)
  {
    return new GameBadgeRef(this.II, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/GameBadgeBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */