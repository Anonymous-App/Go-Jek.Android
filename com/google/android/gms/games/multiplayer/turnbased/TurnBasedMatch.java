package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.Participatable;
import java.util.ArrayList;

public abstract interface TurnBasedMatch
  extends Parcelable, Freezable<TurnBasedMatch>, Participatable
{
  public static final int MATCH_STATUS_ACTIVE = 1;
  public static final int MATCH_STATUS_AUTO_MATCHING = 0;
  public static final int MATCH_STATUS_CANCELED = 4;
  public static final int MATCH_STATUS_COMPLETE = 2;
  public static final int MATCH_STATUS_EXPIRED = 3;
  public static final int[] MATCH_TURN_STATUS_ALL = { 0, 1, 2, 3 };
  public static final int MATCH_TURN_STATUS_COMPLETE = 3;
  public static final int MATCH_TURN_STATUS_INVITED = 0;
  public static final int MATCH_TURN_STATUS_MY_TURN = 1;
  public static final int MATCH_TURN_STATUS_THEIR_TURN = 2;
  public static final int MATCH_VARIANT_DEFAULT = -1;
  
  public abstract boolean canRematch();
  
  public abstract Bundle getAutoMatchCriteria();
  
  public abstract int getAvailableAutoMatchSlots();
  
  public abstract long getCreationTimestamp();
  
  public abstract String getCreatorId();
  
  public abstract byte[] getData();
  
  public abstract String getDescription();
  
  public abstract void getDescription(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract Participant getDescriptionParticipant();
  
  public abstract String getDescriptionParticipantId();
  
  public abstract Game getGame();
  
  public abstract long getLastUpdatedTimestamp();
  
  public abstract String getLastUpdaterId();
  
  public abstract String getMatchId();
  
  public abstract int getMatchNumber();
  
  public abstract Participant getParticipant(String paramString);
  
  public abstract String getParticipantId(String paramString);
  
  public abstract ArrayList<String> getParticipantIds();
  
  public abstract int getParticipantStatus(String paramString);
  
  public abstract String getPendingParticipantId();
  
  public abstract byte[] getPreviousMatchData();
  
  public abstract String getRematchId();
  
  public abstract int getStatus();
  
  public abstract int getTurnStatus();
  
  public abstract int getVariant();
  
  public abstract int getVersion();
  
  public abstract boolean isLocallyModified();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */