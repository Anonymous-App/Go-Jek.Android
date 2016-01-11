package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Asset
  implements SafeParcelable
{
  public static final Parcelable.Creator<Asset> CREATOR = new a();
  final int BR;
  private byte[] acH;
  private String auQ;
  public ParcelFileDescriptor auR;
  public Uri uri;
  
  Asset(int paramInt, byte[] paramArrayOfByte, String paramString, ParcelFileDescriptor paramParcelFileDescriptor, Uri paramUri)
  {
    this.BR = paramInt;
    this.acH = paramArrayOfByte;
    this.auQ = paramString;
    this.auR = paramParcelFileDescriptor;
    this.uri = paramUri;
  }
  
  public static Asset createFromBytes(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("Asset data cannot be null");
    }
    return new Asset(1, paramArrayOfByte, null, null, null);
  }
  
  public static Asset createFromFd(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor == null) {
      throw new IllegalArgumentException("Asset fd cannot be null");
    }
    return new Asset(1, null, null, paramParcelFileDescriptor, null);
  }
  
  public static Asset createFromRef(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Asset digest cannot be null");
    }
    return new Asset(1, null, paramString, null, null);
  }
  
  public static Asset createFromUri(Uri paramUri)
  {
    if (paramUri == null) {
      throw new IllegalArgumentException("Asset uri cannot be null");
    }
    return new Asset(1, null, null, null, paramUri);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof Asset)) {
        return false;
      }
      paramObject = (Asset)paramObject;
    } while ((n.equal(this.acH, ((Asset)paramObject).acH)) && (n.equal(this.auQ, ((Asset)paramObject).auQ)) && (n.equal(this.auR, ((Asset)paramObject).auR)) && (n.equal(this.uri, ((Asset)paramObject).uri)));
    return false;
  }
  
  public byte[] getData()
  {
    return this.acH;
  }
  
  public String getDigest()
  {
    return this.auQ;
  }
  
  public ParcelFileDescriptor getFd()
  {
    return this.auR;
  }
  
  public Uri getUri()
  {
    return this.uri;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.acH, this.auQ, this.auR, this.uri });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Asset[@");
    localStringBuilder.append(Integer.toHexString(hashCode()));
    if (this.auQ == null) {
      localStringBuilder.append(", nodigest");
    }
    for (;;)
    {
      if (this.acH != null)
      {
        localStringBuilder.append(", size=");
        localStringBuilder.append(this.acH.length);
      }
      if (this.auR != null)
      {
        localStringBuilder.append(", fd=");
        localStringBuilder.append(this.auR);
      }
      if (this.uri != null)
      {
        localStringBuilder.append(", uri=");
        localStringBuilder.append(this.uri);
      }
      localStringBuilder.append("]");
      return localStringBuilder.toString();
      localStringBuilder.append(", ");
      localStringBuilder.append(this.auQ);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt | 0x1);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/Asset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */