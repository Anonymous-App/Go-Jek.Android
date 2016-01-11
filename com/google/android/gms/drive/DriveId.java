package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.ah;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.internal.pm;
import com.google.android.gms.internal.pn;

public class DriveId
  implements SafeParcelable
{
  public static final Parcelable.Creator<DriveId> CREATOR = new c();
  final int BR;
  final String Ni;
  final long Nj;
  final long Nk;
  private volatile String Nl = null;
  
  DriveId(int paramInt, String paramString, long paramLong1, long paramLong2)
  {
    this.BR = paramInt;
    this.Ni = paramString;
    if (!"".equals(paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      o.K(bool1);
      if (paramString == null)
      {
        bool1 = bool2;
        if (paramLong1 == -1L) {}
      }
      else
      {
        bool1 = true;
      }
      o.K(bool1);
      this.Nj = paramLong1;
      this.Nk = paramLong2;
      return;
    }
  }
  
  public DriveId(String paramString, long paramLong1, long paramLong2)
  {
    this(1, paramString, paramLong1, paramLong2);
  }
  
  public static DriveId bg(String paramString)
  {
    o.i(paramString);
    return new DriveId(paramString, -1L, -1L);
  }
  
  public static DriveId decodeFromString(String paramString)
  {
    o.b(paramString.startsWith("DriveId:"), "Invalid DriveId: " + paramString);
    return f(Base64.decode(paramString.substring("DriveId:".length()), 10));
  }
  
  static DriveId f(byte[] paramArrayOfByte)
  {
    for (;;)
    {
      ah localah;
      try
      {
        localah = ah.g(paramArrayOfByte);
        if ("".equals(localah.Pl))
        {
          paramArrayOfByte = null;
          return new DriveId(localah.versionCode, paramArrayOfByte, localah.Pm, localah.Pn);
        }
      }
      catch (pm paramArrayOfByte)
      {
        throw new IllegalArgumentException();
      }
      paramArrayOfByte = localah.Pl;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public final String encodeToString()
  {
    if (this.Nl == null)
    {
      String str = Base64.encodeToString(hN(), 10);
      this.Nl = ("DriveId:" + str);
    }
    return this.Nl;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof DriveId)) {}
    do
    {
      return false;
      paramObject = (DriveId)paramObject;
      if (((DriveId)paramObject).Nk != this.Nk)
      {
        v.p("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
        return false;
      }
      if ((((DriveId)paramObject).Nj == -1L) && (this.Nj == -1L)) {
        return ((DriveId)paramObject).Ni.equals(this.Ni);
      }
    } while (((DriveId)paramObject).Nj != this.Nj);
    return true;
  }
  
  public String getResourceId()
  {
    return this.Ni;
  }
  
  final byte[] hN()
  {
    ah localah = new ah();
    localah.versionCode = this.BR;
    if (this.Ni == null) {}
    for (String str = "";; str = this.Ni)
    {
      localah.Pl = str;
      localah.Pm = this.Nj;
      localah.Pn = this.Nk;
      return pn.f(localah);
    }
  }
  
  public int hashCode()
  {
    if (this.Nj == -1L) {
      return this.Ni.hashCode();
    }
    return (String.valueOf(this.Nk) + String.valueOf(this.Nj)).hashCode();
  }
  
  public String toString()
  {
    return encodeToString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/DriveId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */