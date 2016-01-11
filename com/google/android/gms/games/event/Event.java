package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public abstract interface Event
  extends Parcelable, Freezable<Event>
{
  public abstract String getDescription();
  
  public abstract void getDescription(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract String getEventId();
  
  public abstract String getFormattedValue();
  
  public abstract void getFormattedValue(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract Uri getIconImageUri();
  
  @Deprecated
  public abstract String getIconImageUrl();
  
  public abstract String getName();
  
  public abstract void getName(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract Player getPlayer();
  
  public abstract long getValue();
  
  public abstract boolean isVisible();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/event/Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */