package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.n.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;

public final class EventEntity
  implements SafeParcelable, Event
{
  public static final EventEntityCreator CREATOR = new EventEntityCreator();
  private final int BR;
  private final String Tr;
  private final Uri Vh;
  private final String Vs;
  private final PlayerEntity Wh;
  private final String Wm;
  private final long Wn;
  private final String Wo;
  private final boolean Wp;
  private final String mName;
  
  EventEntity(int paramInt, String paramString1, String paramString2, String paramString3, Uri paramUri, String paramString4, Player paramPlayer, long paramLong, String paramString5, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.Wm = paramString1;
    this.mName = paramString2;
    this.Tr = paramString3;
    this.Vh = paramUri;
    this.Vs = paramString4;
    this.Wh = new PlayerEntity(paramPlayer);
    this.Wn = paramLong;
    this.Wo = paramString5;
    this.Wp = paramBoolean;
  }
  
  public EventEntity(Event paramEvent)
  {
    this.BR = 1;
    this.Wm = paramEvent.getEventId();
    this.mName = paramEvent.getName();
    this.Tr = paramEvent.getDescription();
    this.Vh = paramEvent.getIconImageUri();
    this.Vs = paramEvent.getIconImageUrl();
    this.Wh = ((PlayerEntity)paramEvent.getPlayer().freeze());
    this.Wn = paramEvent.getValue();
    this.Wo = paramEvent.getFormattedValue();
    this.Wp = paramEvent.isVisible();
  }
  
  static int a(Event paramEvent)
  {
    return n.hashCode(new Object[] { paramEvent.getEventId(), paramEvent.getName(), paramEvent.getDescription(), paramEvent.getIconImageUri(), paramEvent.getIconImageUrl(), paramEvent.getPlayer(), Long.valueOf(paramEvent.getValue()), paramEvent.getFormattedValue(), Boolean.valueOf(paramEvent.isVisible()) });
  }
  
  static boolean a(Event paramEvent, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Event)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramEvent == paramObject);
      paramObject = (Event)paramObject;
      if ((!n.equal(((Event)paramObject).getEventId(), paramEvent.getEventId())) || (!n.equal(((Event)paramObject).getName(), paramEvent.getName())) || (!n.equal(((Event)paramObject).getDescription(), paramEvent.getDescription())) || (!n.equal(((Event)paramObject).getIconImageUri(), paramEvent.getIconImageUri())) || (!n.equal(((Event)paramObject).getIconImageUrl(), paramEvent.getIconImageUrl())) || (!n.equal(((Event)paramObject).getPlayer(), paramEvent.getPlayer())) || (!n.equal(Long.valueOf(((Event)paramObject).getValue()), Long.valueOf(paramEvent.getValue()))) || (!n.equal(((Event)paramObject).getFormattedValue(), paramEvent.getFormattedValue()))) {
        break;
      }
      bool1 = bool2;
    } while (n.equal(Boolean.valueOf(((Event)paramObject).isVisible()), Boolean.valueOf(paramEvent.isVisible())));
    return false;
  }
  
  static String b(Event paramEvent)
  {
    return n.h(paramEvent).a("Id", paramEvent.getEventId()).a("Name", paramEvent.getName()).a("Description", paramEvent.getDescription()).a("IconImageUri", paramEvent.getIconImageUri()).a("IconImageUrl", paramEvent.getIconImageUrl()).a("Player", paramEvent.getPlayer()).a("Value", Long.valueOf(paramEvent.getValue())).a("FormattedValue", paramEvent.getFormattedValue()).a("isVisible", Boolean.valueOf(paramEvent.isVisible())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Event freeze()
  {
    return this;
  }
  
  public String getDescription()
  {
    return this.Tr;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Tr, paramCharArrayBuffer);
  }
  
  public String getEventId()
  {
    return this.Wm;
  }
  
  public String getFormattedValue()
  {
    return this.Wo;
  }
  
  public void getFormattedValue(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.Wo, paramCharArrayBuffer);
  }
  
  public Uri getIconImageUri()
  {
    return this.Vh;
  }
  
  public String getIconImageUrl()
  {
    return this.Vs;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    jv.b(this.mName, paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return this.Wh;
  }
  
  public long getValue()
  {
    return this.Wn;
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isVisible()
  {
    return this.Wp;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    EventEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/event/EventEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */