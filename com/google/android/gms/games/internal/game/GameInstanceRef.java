package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.games.internal.constants.PlatformType;

public final class GameInstanceRef
  extends d
  implements GameInstance
{
  GameInstanceRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public String getApplicationId()
  {
    return getString("external_game_id");
  }
  
  public String getDisplayName()
  {
    return getString("instance_display_name");
  }
  
  public String getPackageName()
  {
    return getString("package_name");
  }
  
  public int iR()
  {
    return getInteger("platform_type");
  }
  
  public boolean le()
  {
    return getInteger("real_time_support") > 0;
  }
  
  public boolean lf()
  {
    return getInteger("turn_based_support") > 0;
  }
  
  public boolean lg()
  {
    return getInteger("piracy_check") > 0;
  }
  
  public boolean lh()
  {
    return getInteger("installed") > 0;
  }
  
  public String toString()
  {
    return n.h(this).a("ApplicationId", getApplicationId()).a("DisplayName", getDisplayName()).a("SupportsRealTime", Boolean.valueOf(le())).a("SupportsTurnBased", Boolean.valueOf(lf())).a("PlatformType", PlatformType.dH(iR())).a("PackageName", getPackageName()).a("PiracyCheckEnabled", Boolean.valueOf(lg())).a("Installed", Boolean.valueOf(lh())).toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/game/GameInstanceRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */