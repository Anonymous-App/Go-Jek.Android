package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class u
  implements Parcelable.Creator<Value>
{
  static void a(Value paramValue, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramValue.getFormat());
    b.c(paramParcel, 1000, paramValue.getVersionCode());
    b.a(paramParcel, 2, paramValue.isSet());
    b.a(paramParcel, 3, paramValue.ja());
    b.H(paramParcel, paramInt);
  }
  
  public Value bx(Parcel paramParcel)
  {
    boolean bool = false;
    int k = a.C(paramParcel);
    float f = 0.0F;
    int j = 0;
    int i = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = a.B(paramParcel);
      switch (a.aD(m))
      {
      default: 
        a.b(paramParcel, m);
        break;
      case 1: 
        j = a.g(paramParcel, m);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        bool = a.c(paramParcel, m);
        break;
      case 3: 
        f = a.l(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new Value(i, j, bool, f);
  }
  
  public Value[] cO(int paramInt)
  {
    return new Value[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */