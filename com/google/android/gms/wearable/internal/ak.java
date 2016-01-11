package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.Node;

public class ak
  implements SafeParcelable, Node
{
  public static final Parcelable.Creator<ak> CREATOR = new al();
  private final String BL;
  final int BR;
  private final String NH;
  
  ak(int paramInt, String paramString1, String paramString2)
  {
    this.BR = paramInt;
    this.BL = paramString1;
    this.NH = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ak)) {}
    do
    {
      return false;
      paramObject = (ak)paramObject;
    } while ((!((ak)paramObject).BL.equals(this.BL)) || (!((ak)paramObject).NH.equals(this.NH)));
    return true;
  }
  
  public String getDisplayName()
  {
    return this.NH;
  }
  
  public String getId()
  {
    return this.BL;
  }
  
  public int hashCode()
  {
    return (this.BL.hashCode() + 629) * 37 + this.NH.hashCode();
  }
  
  public String toString()
  {
    return "NodeParcelable{" + this.BL + "," + this.NH + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    al.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */