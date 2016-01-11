package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ParcelableClientSettingsCreator
  implements Parcelable.Creator<ClientSettings.ParcelableClientSettings>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(ClientSettings.ParcelableClientSettings paramParcelableClientSettings, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramParcelableClientSettings.getAccountName(), false);
    b.c(paramParcel, 1000, paramParcelableClientSettings.getVersionCode());
    b.b(paramParcel, 2, paramParcelableClientSettings.getScopes(), false);
    b.c(paramParcel, 3, paramParcelableClientSettings.getGravityForPopups());
    b.a(paramParcel, 4, paramParcelableClientSettings.getRealClientPackageName(), false);
    b.H(paramParcel, paramInt);
  }
  
  public ClientSettings.ParcelableClientSettings createFromParcel(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int k = a.C(paramParcel);
    ArrayList localArrayList = null;
    String str2 = null;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        str2 = a.o(paramParcel, m);
        break;
      case 1000: 
        j = a.g(paramParcel, m);
        break;
      case 2: 
        localArrayList = a.C(paramParcel, m);
        break;
      case 3: 
        i = a.g(paramParcel, m);
        break;
      case 4: 
        str1 = a.o(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new ClientSettings.ParcelableClientSettings(j, str2, localArrayList, i, str1);
  }
  
  public ClientSettings.ParcelableClientSettings[] newArray(int paramInt)
  {
    return new ClientSettings.ParcelableClientSettings[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/internal/ParcelableClientSettingsCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */