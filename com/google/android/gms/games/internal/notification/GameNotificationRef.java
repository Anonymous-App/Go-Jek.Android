package com.google.android.gms.games.internal.notification;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;

public final class GameNotificationRef
  extends d
  implements GameNotification
{
  GameNotificationRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public long getId()
  {
    return getLong("_id");
  }
  
  public String getText()
  {
    return getString("text");
  }
  
  public String getTitle()
  {
    return getString("title");
  }
  
  public int getType()
  {
    return getInteger("type");
  }
  
  public String lk()
  {
    return getString("notification_id");
  }
  
  public String ll()
  {
    return getString("ticker");
  }
  
  public String lm()
  {
    return getString("coalesced_text");
  }
  
  public boolean ln()
  {
    return getInteger("acknowledged") > 0;
  }
  
  public boolean lo()
  {
    return getInteger("alert_level") == 0;
  }
  
  public String toString()
  {
    return n.h(this).a("Id", Long.valueOf(getId())).a("NotificationId", lk()).a("Type", Integer.valueOf(getType())).a("Title", getTitle()).a("Ticker", ll()).a("Text", getText()).a("CoalescedText", lm()).a("isAcknowledged", Boolean.valueOf(ln())).a("isSilent", Boolean.valueOf(lo())).toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/notification/GameNotificationRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */