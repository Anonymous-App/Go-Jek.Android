package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
  implements Parcelable.Creator<Asset>
{
  static void a(Asset paramAsset, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramAsset.BR);
    b.a(paramParcel, 2, paramAsset.getData(), false);
    b.a(paramParcel, 3, paramAsset.getDigest(), false);
    b.a(paramParcel, 4, paramAsset.auR, paramInt, false);
    b.a(paramParcel, 5, paramAsset.uri, paramInt, false);
    b.H(paramParcel, i);
  }
  
  public Asset dP(Parcel paramParcel)
  {
    Uri localUri = null;
    int j = com.google.android.gms.common.internal.safeparcel.a.C(paramParcel);
    int i = 0;
    ParcelFileDescriptor localParcelFileDescriptor = null;
    String str = null;
    byte[] arrayOfByte = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.a.B(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.aD(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, k);
        break;
      case 1: 
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, k);
        break;
      case 2: 
        arrayOfByte = com.google.android.gms.common.internal.safeparcel.a.r(paramParcel, k);
        break;
      case 3: 
        str = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel, k);
        break;
      case 4: 
        localParcelFileDescriptor = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, ParcelFileDescriptor.CREATOR);
        break;
      case 5: 
        localUri = (Uri)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, Uri.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new Asset(i, arrayOfByte, str, localParcelFileDescriptor, localUri);
  }
  
  public Asset[] fS(int paramInt)
  {
    return new Asset[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */