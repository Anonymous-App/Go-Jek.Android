package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.internal.o;
import com.google.android.gms.games.Player;
import java.util.ArrayList;

public final class ParticipantUtils
{
  public static boolean bV(String paramString)
  {
    o.b(paramString, "Participant ID must not be null");
    return paramString.startsWith("p_");
  }
  
  public static String getParticipantId(ArrayList<Participant> paramArrayList, String paramString)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)paramArrayList.get(i);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer != null) && (localPlayer.getPlayerId().equals(paramString))) {
        return localParticipant.getParticipantId();
      }
      i += 1;
    }
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/ParticipantUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */