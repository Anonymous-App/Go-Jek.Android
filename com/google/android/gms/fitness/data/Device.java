package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kw;

public final class Device
  implements SafeParcelable
{
  public static final Parcelable.Creator<Device> CREATOR = new i();
  public static final int TYPE_CHEST_STRAP = 4;
  public static final int TYPE_PHONE = 1;
  public static final int TYPE_SCALE = 5;
  public static final int TYPE_TABLET = 2;
  public static final int TYPE_UNKNOWN = 0;
  public static final int TYPE_WATCH = 3;
  private final int BR;
  private final int FD;
  private final String SZ;
  private final String Sx;
  private final String Ta;
  private final String Tb;
  private final int Tc;
  
  Device(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3)
  {
    this.BR = paramInt1;
    this.SZ = ((String)o.i(paramString1));
    this.Ta = ((String)o.i(paramString2));
    this.Sx = "";
    this.Tb = ((String)o.i(paramString4));
    this.FD = paramInt2;
    this.Tc = paramInt3;
  }
  
  public Device(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this(paramString1, paramString2, "", paramString3, paramInt, 0);
  }
  
  public Device(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    this(paramString1, paramString2, paramString4, paramInt);
  }
  
  public Device(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    this(1, paramString1, paramString2, "", paramString4, paramInt1, paramInt2);
  }
  
  private static String M(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  private static int N(Context paramContext)
  {
    int j = 0;
    int i = j;
    switch (P(paramContext))
    {
    default: 
      if (R(paramContext)) {
        i = 1;
      }
      break;
    case 8: 
    case 9: 
    case 10: 
      do
      {
        return i;
        i = j;
      } while (!O(paramContext));
      return 3;
    }
    return 2;
  }
  
  public static boolean O(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().uiMode & 0xF) == 6;
  }
  
  private static int P(Context paramContext)
  {
    return Q(paramContext) % 1000 / 100 + 5;
  }
  
  private static int Q(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.w("Fitness", "Could not find package info for Google Play Services");
    }
    return -1;
  }
  
  private static boolean R(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType() != 0;
  }
  
  private boolean a(Device paramDevice)
  {
    return (n.equal(this.SZ, paramDevice.SZ)) && (n.equal(this.Ta, paramDevice.Ta)) && (n.equal(this.Sx, paramDevice.Sx)) && (n.equal(this.Tb, paramDevice.Tb)) && (this.FD == paramDevice.FD) && (this.Tc == paramDevice.Tc);
  }
  
  public static Device getLocalDevice(Context paramContext)
  {
    int i = N(paramContext);
    paramContext = M(paramContext);
    return new Device(Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, paramContext, i, 2);
  }
  
  private boolean iS()
  {
    return iR() == 1;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Device)) && (a((Device)paramObject)));
  }
  
  public String getManufacturer()
  {
    return this.SZ;
  }
  
  public String getModel()
  {
    return this.Ta;
  }
  
  String getStreamIdentifier()
  {
    return String.format("%s:%s:%s", new Object[] { this.SZ, this.Ta, this.Tb });
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public String getUid()
  {
    return this.Tb;
  }
  
  public String getVersion()
  {
    return this.Sx;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.SZ, this.Ta, this.Sx, this.Tb, Integer.valueOf(this.FD) });
  }
  
  public int iR()
  {
    return this.Tc;
  }
  
  Device iT()
  {
    return new Device(kw.bt(this.SZ), kw.bt(this.Ta), kw.bt(this.Sx), this.Tb, this.FD);
  }
  
  public String iU()
  {
    if ((kw.jc()) || (iS())) {
      return this.Tb;
    }
    return kw.bt(this.Tb);
  }
  
  public String toString()
  {
    return String.format("Device{%s:%s:%s:%s}", new Object[] { getStreamIdentifier(), this.Sx, Integer.valueOf(this.FD), Integer.valueOf(this.Tc) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    i.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/Device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */