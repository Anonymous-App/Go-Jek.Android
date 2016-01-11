package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomPropertyKey
  implements SafeParcelable
{
  public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new c();
  private static final Pattern PF = Pattern.compile("[\\w.!@$%^&*()/-]+");
  final int BR;
  final String JO;
  final int mVisibility;
  
  CustomPropertyKey(int paramInt1, String paramString, int paramInt2)
  {
    o.b(paramString, "key");
    o.b(PF.matcher(paramString).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
    boolean bool1 = bool2;
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        break label69;
      }
    }
    label69:
    for (bool1 = bool2;; bool1 = false)
    {
      o.b(bool1, "visibility must be either PUBLIC or PRIVATE");
      this.BR = paramInt1;
      this.JO = paramString;
      this.mVisibility = paramInt2;
      return;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null) {}
    do
    {
      return false;
      if (paramObject == this) {
        return true;
      }
    } while (!(paramObject instanceof CustomPropertyKey));
    paramObject = (CustomPropertyKey)paramObject;
    if ((((CustomPropertyKey)paramObject).getKey().equals(this.JO)) && (((CustomPropertyKey)paramObject).getVisibility() == this.mVisibility)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public String getKey()
  {
    return this.JO;
  }
  
  public int getVisibility()
  {
    return this.mVisibility;
  }
  
  public int hashCode()
  {
    return (this.JO + this.mVisibility).hashCode();
  }
  
  public String toString()
  {
    return "CustomPropertyKey(" + this.JO + "," + this.mVisibility + ")";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/metadata/CustomPropertyKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */