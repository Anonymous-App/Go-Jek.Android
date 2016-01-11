package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class j
  implements Parcelable.Creator<Field>
{
  static void a(Field paramField, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.a(paramParcel, 1, paramField.getName(), false);
    b.c(paramParcel, 1000, paramField.getVersionCode());
    b.c(paramParcel, 2, paramField.getFormat());
    b.H(paramParcel, paramInt);
  }
  
  public Field bq(Parcel paramParcel)
  {
    int j = 0;
    int k = a.C(paramParcel);
    String str = null;
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
        str = a.o(paramParcel, m);
        break;
      case 1000: 
        i = a.g(paramParcel, m);
        break;
      case 2: 
        j = a.g(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new a.a("Overread allowed size end=" + k, paramParcel);
    }
    return new Field(i, str, j);
  }
  
  public Field[] cG(int paramInt)
  {
    return new Field[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */