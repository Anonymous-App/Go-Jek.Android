package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class oq
  implements Parcelable.Creator<op>
{
  static void a(op paramop, Parcel paramParcel, int paramInt)
  {
    paramInt = b.D(paramParcel);
    b.c(paramParcel, 1, paramop.getVersionCode());
    b.a(paramParcel, 2, paramop.atO, false);
    b.a(paramParcel, 3, paramop.atP, false);
    b.H(paramParcel, paramInt);
  }
  
  public op dC(Parcel paramParcel)
  {
    String[] arrayOfString = null;
    int j = a.C(paramParcel);
    int i = 0;
    byte[][] arrayOfByte = (byte[][])null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        arrayOfString = a.A(paramParcel, k);
        break;
      case 3: 
        arrayOfByte = a.s(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new op(i, arrayOfString, arrayOfByte);
  }
  
  public op[] fD(int paramInt)
  {
    return new op[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/oq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */