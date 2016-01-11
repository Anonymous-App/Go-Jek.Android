package com.google.android.gms.games.request;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import java.util.List;

public abstract interface GameRequest
  extends Parcelable, Freezable<GameRequest>
{
  public static final int RECIPIENT_STATUS_ACCEPTED = 1;
  public static final int RECIPIENT_STATUS_PENDING = 0;
  public static final int STATUS_ACCEPTED = 1;
  public static final int STATUS_PENDING = 0;
  public static final int TYPE_ALL = 65535;
  public static final int TYPE_GIFT = 1;
  public static final int TYPE_WISH = 2;
  
  public abstract long getCreationTimestamp();
  
  public abstract byte[] getData();
  
  public abstract long getExpirationTimestamp();
  
  public abstract Game getGame();
  
  public abstract int getRecipientStatus(String paramString);
  
  public abstract List<Player> getRecipients();
  
  public abstract String getRequestId();
  
  public abstract Player getSender();
  
  public abstract int getStatus();
  
  public abstract int getType();
  
  public abstract boolean isConsumed(String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/request/GameRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */