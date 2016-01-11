package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class EventRef
  extends d
  implements Event
{
  EventRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return EventEntity.a(this, paramObject);
  }
  
  public Event freeze()
  {
    return new EventEntity(this);
  }
  
  public String getDescription()
  {
    return getString("description");
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    a("description", paramCharArrayBuffer);
  }
  
  public String getEventId()
  {
    return getString("external_event_id");
  }
  
  public String getFormattedValue()
  {
    return getString("formatted_value");
  }
  
  public void getFormattedValue(CharArrayBuffer paramCharArrayBuffer)
  {
    a("formatted_value", paramCharArrayBuffer);
  }
  
  public Uri getIconImageUri()
  {
    return aR("icon_image_uri");
  }
  
  public String getIconImageUrl()
  {
    return getString("icon_image_url");
  }
  
  public String getName()
  {
    return getString("name");
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    a("name", paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return new PlayerRef(this.II, this.JX);
  }
  
  public long getValue()
  {
    return getLong("value");
  }
  
  public int hashCode()
  {
    return EventEntity.a(this);
  }
  
  public boolean isVisible()
  {
    return getBoolean("visibility");
  }
  
  public String toString()
  {
    return EventEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((EventEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/event/EventRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */