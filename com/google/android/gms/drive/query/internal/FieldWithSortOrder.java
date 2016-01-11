package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class FieldWithSortOrder
  implements SafeParcelable
{
  public static final c CREATOR = new c();
  final int BR;
  final String PB;
  final boolean QN;
  
  FieldWithSortOrder(int paramInt, String paramString, boolean paramBoolean)
  {
    this.BR = paramInt;
    this.PB = paramString;
    this.QN = paramBoolean;
  }
  
  public FieldWithSortOrder(String paramString, boolean paramBoolean)
  {
    this(1, paramString, paramBoolean);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    Locale localLocale = Locale.US;
    String str2 = this.PB;
    if (this.QN) {}
    for (String str1 = "ASC";; str1 = "DESC") {
      return String.format(localLocale, "FieldWithSortOrder[%s %s]", new Object[] { str2, str1 });
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/internal/FieldWithSortOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */