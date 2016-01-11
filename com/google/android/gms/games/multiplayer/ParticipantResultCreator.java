package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ParticipantResultCreator
  implements Parcelable.Creator<ParticipantResult>
{
  public static final int CONTENT_DESCRIPTION = 0;
  
  static void a(ParticipantResult paramParticipantResult, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramParticipantResult.getParticipantId(), false);
    b.c(paramParcel, 1000, paramParticipantResult.getVersionCode());
    b.c(paramParcel, 2, paramParticipantResult.getResult());
    b.c(paramParcel, 3, paramParticipantResult.getPlacing());
    b.H(paramParcel, paramInt);
  }
  
  public ParticipantResult createFromParcel(Parcel paramParcel)
  {
    int k = 0;
    int m = a.C(paramParcel);
    String str = null;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        str = a.o(paramParcel, n);
        break;
      case 1000: 
        i = a.g(paramParcel, n);
        break;
      case 2: 
        j = a.g(paramParcel, n);
        break;
      case 3: 
        k = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new ParticipantResult(i, str, j, k);
  }
  
  public ParticipantResult[] newArray(int paramInt)
  {
    return new ParticipantResult[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/multiplayer/ParticipantResultCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */