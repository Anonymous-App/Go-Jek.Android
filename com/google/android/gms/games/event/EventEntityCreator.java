package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.games.PlayerEntity;

public class EventEntityCreator
  implements Parcelable.Creator<EventEntity>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(EventEntity paramEventEntity, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramEventEntity.getEventId(), false);
    b.c(paramParcel, 1000, paramEventEntity.getVersionCode());
    b.a(paramParcel, 2, paramEventEntity.getName(), false);
    b.a(paramParcel, 3, paramEventEntity.getDescription(), false);
    b.a(paramParcel, 4, paramEventEntity.getIconImageUri(), paramInt, false);
    b.a(paramParcel, 5, paramEventEntity.getIconImageUrl(), false);
    b.a(paramParcel, 6, paramEventEntity.getPlayer(), paramInt, false);
    b.a(paramParcel, 7, paramEventEntity.getValue());
    b.a(paramParcel, 8, paramEventEntity.getFormattedValue(), false);
    b.a(paramParcel, 9, paramEventEntity.isVisible());
    b.H(paramParcel, i);
  }
  
  public EventEntity createFromParcel(Parcel paramParcel)
  {
    boolean bool = false;
    String str1 = null;
    int j = a.C(paramParcel);
    long l = 0L;
    PlayerEntity localPlayerEntity = null;
    String str2 = null;
    Uri localUri = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        str5 = a.o(paramParcel, k);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        str4 = a.o(paramParcel, k);
        break;
      case 3: 
        str3 = a.o(paramParcel, k);
        break;
      case 4: 
        localUri = (Uri)a.a(paramParcel, k, Uri.CREATOR);
        break;
      case 5: 
        str2 = a.o(paramParcel, k);
        break;
      case 6: 
        localPlayerEntity = (PlayerEntity)a.a(paramParcel, k, PlayerEntity.CREATOR);
        break;
      case 7: 
        l = a.i(paramParcel, k);
        break;
      case 8: 
        str1 = a.o(paramParcel, k);
        break;
      case 9: 
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new EventEntity(i, str5, str4, str3, localUri, str2, localPlayerEntity, l, str1, bool);
  }
  
  public EventEntity[] newArray(int paramInt)
  {
    return new EventEntity[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/event/EventEntityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */