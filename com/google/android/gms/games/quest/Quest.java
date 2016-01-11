package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import java.util.List;

public abstract interface Quest
  extends Parcelable, Freezable<Quest>
{
  public static final int[] QUEST_STATE_ALL = { 1, 2, 3, 4, 6, 5 };
  public static final String[] QUEST_STATE_NON_TERMINAL = { Integer.toString(1), Integer.toString(2), Integer.toString(3) };
  public static final int STATE_ACCEPTED = 3;
  public static final int STATE_COMPLETED = 4;
  public static final int STATE_EXPIRED = 5;
  public static final int STATE_FAILED = 6;
  public static final int STATE_OPEN = 2;
  public static final int STATE_UPCOMING = 1;
  public static final long UNSET_QUEST_TIMESTAMP = -1L;
  
  public abstract long getAcceptedTimestamp();
  
  public abstract Uri getBannerImageUri();
  
  @Deprecated
  public abstract String getBannerImageUrl();
  
  public abstract Milestone getCurrentMilestone();
  
  public abstract String getDescription();
  
  public abstract void getDescription(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract long getEndTimestamp();
  
  public abstract Game getGame();
  
  public abstract Uri getIconImageUri();
  
  @Deprecated
  public abstract String getIconImageUrl();
  
  public abstract long getLastUpdatedTimestamp();
  
  public abstract String getName();
  
  public abstract void getName(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract String getQuestId();
  
  public abstract long getStartTimestamp();
  
  public abstract int getState();
  
  public abstract int getType();
  
  public abstract boolean isEndingSoon();
  
  public abstract List<Milestone> lJ();
  
  public abstract long lK();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/quest/Quest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */