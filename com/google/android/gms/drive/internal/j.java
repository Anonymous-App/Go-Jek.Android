package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class j
  implements Parcelable.Creator<CreateFileRequest>
{
  static void a(CreateFileRequest paramCreateFileRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.c(paramParcel, 1, paramCreateFileRequest.BR);
    b.a(paramParcel, 2, paramCreateFileRequest.On, paramInt, false);
    b.a(paramParcel, 3, paramCreateFileRequest.Ol, paramInt, false);
    b.a(paramParcel, 4, paramCreateFileRequest.Of, paramInt, false);
    b.a(paramParcel, 5, paramCreateFileRequest.Om, false);
    b.a(paramParcel, 6, paramCreateFileRequest.Oo);
    b.a(paramParcel, 7, paramCreateFileRequest.Nn, false);
    b.c(paramParcel, 8, paramCreateFileRequest.Op);
    b.c(paramParcel, 9, paramCreateFileRequest.Oq);
    b.H(paramParcel, i);
  }
  
  public CreateFileRequest ac(Parcel paramParcel)
  {
    int i = 0;
    String str = null;
    int m = a.C(paramParcel);
    int j = 0;
    boolean bool = false;
    Integer localInteger = null;
    Contents localContents = null;
    MetadataBundle localMetadataBundle = null;
    DriveId localDriveId = null;
    int k = 0;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.B(paramParcel);
      switch (a.aD(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        localDriveId = (DriveId)a.a(paramParcel, n, DriveId.CREATOR);
        break;
      case 3: 
        localMetadataBundle = (MetadataBundle)a.a(paramParcel, n, MetadataBundle.CREATOR);
        break;
      case 4: 
        localContents = (Contents)a.a(paramParcel, n, Contents.CREATOR);
        break;
      case 5: 
        localInteger = a.h(paramParcel, n);
        break;
      case 6: 
        bool = a.c(paramParcel, n);
        break;
      case 7: 
        str = a.o(paramParcel, n);
        break;
      case 8: 
        j = a.g(paramParcel, n);
        break;
      case 9: 
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new CreateFileRequest(k, localDriveId, localMetadataBundle, localContents, localInteger, bool, str, j, i);
  }
  
  public CreateFileRequest[] bm(int paramInt)
  {
    return new CreateFileRequest[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */