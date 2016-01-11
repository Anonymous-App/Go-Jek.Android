package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;

public class b
  implements Parcelable.Creator<WebImage>
{
  static void a(WebImage paramWebImage, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.D(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramWebImage.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramWebImage.getUrl(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 3, paramWebImage.getWidth());
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 4, paramWebImage.getHeight());
    com.google.android.gms.common.internal.safeparcel.b.H(paramParcel, i);
  }
  
  public WebImage A(Parcel paramParcel)
  {
    int i = 0;
    int m = a.C(paramParcel);
    Uri localUri = null;
    int j = 0;
    int k = 0;
    if (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
      }
      for (;;)
      {
        break;
        k = a.g(paramParcel, n);
        continue;
        localUri = (Uri)a.a(paramParcel, n, Uri.CREATOR);
        continue;
        j = a.g(paramParcel, n);
        continue;
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new WebImage(k, localUri, j, i);
  }
  
  public WebImage[] ax(int paramInt)
  {
    return new WebImage[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/images/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */