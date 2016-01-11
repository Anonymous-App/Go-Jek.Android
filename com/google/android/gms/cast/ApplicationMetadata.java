package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ik;
import java.util.ArrayList;
import java.util.List;

public final class ApplicationMetadata
  implements SafeParcelable
{
  public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new a();
  private final int BR;
  List<WebImage> EA;
  List<String> EB;
  String EC;
  Uri ED;
  String Ez;
  String mName;
  
  private ApplicationMetadata()
  {
    this.BR = 1;
    this.EA = new ArrayList();
    this.EB = new ArrayList();
  }
  
  ApplicationMetadata(int paramInt, String paramString1, String paramString2, List<WebImage> paramList, List<String> paramList1, String paramString3, Uri paramUri)
  {
    this.BR = paramInt;
    this.Ez = paramString1;
    this.mName = paramString2;
    this.EA = paramList;
    this.EB = paramList1;
    this.EC = paramString3;
    this.ED = paramUri;
  }
  
  public boolean areNamespacesSupported(List<String> paramList)
  {
    return (this.EB != null) && (this.EB.containsAll(paramList));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof ApplicationMetadata)) {
        return false;
      }
      paramObject = (ApplicationMetadata)paramObject;
    } while ((ik.a(this.Ez, ((ApplicationMetadata)paramObject).Ez)) && (ik.a(this.EA, ((ApplicationMetadata)paramObject).EA)) && (ik.a(this.mName, ((ApplicationMetadata)paramObject).mName)) && (ik.a(this.EB, ((ApplicationMetadata)paramObject).EB)) && (ik.a(this.EC, ((ApplicationMetadata)paramObject).EC)) && (ik.a(this.ED, ((ApplicationMetadata)paramObject).ED)));
    return false;
  }
  
  public Uri fu()
  {
    return this.ED;
  }
  
  public String getApplicationId()
  {
    return this.Ez;
  }
  
  public List<WebImage> getImages()
  {
    return this.EA;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getSenderAppIdentifier()
  {
    return this.EC;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.BR), this.Ez, this.mName, this.EA, this.EB, this.EC, this.ED });
  }
  
  public boolean isNamespaceSupported(String paramString)
  {
    return (this.EB != null) && (this.EB.contains(paramString));
  }
  
  public String toString()
  {
    return this.mName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/ApplicationMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */