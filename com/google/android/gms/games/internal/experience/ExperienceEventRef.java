package com.google.android.gms.games.internal.experience;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.GameRef;

public final class ExperienceEventRef
  extends d
  implements ExperienceEvent
{
  private final GameRef aax;
  
  public ExperienceEventRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    if (aS("external_game_id"))
    {
      this.aax = null;
      return;
    }
    this.aax = new GameRef(this.II, this.JX);
  }
  
  public String getIconImageUrl()
  {
    return getString("icon_url");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/experience/ExperienceEventRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */