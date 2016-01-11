package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@ez
public final class fi
  implements SafeParcelable
{
  public static final fj CREATOR = new fj();
  public final ApplicationInfo applicationInfo;
  public final String lA;
  public final gt lD;
  public final ay lH;
  public final List<String> lS;
  public final String tA;
  public final String tB;
  public final Bundle tC;
  public final int tD;
  public final Bundle tE;
  public final boolean tF;
  public final Bundle tw;
  public final av tx;
  public final PackageInfo ty;
  public final String tz;
  public final int versionCode;
  
  fi(int paramInt1, Bundle paramBundle1, av paramav, ay paramay, String paramString1, ApplicationInfo paramApplicationInfo, PackageInfo paramPackageInfo, String paramString2, String paramString3, String paramString4, gt paramgt, Bundle paramBundle2, int paramInt2, List<String> paramList, Bundle paramBundle3, boolean paramBoolean)
  {
    this.versionCode = paramInt1;
    this.tw = paramBundle1;
    this.tx = paramav;
    this.lH = paramay;
    this.lA = paramString1;
    this.applicationInfo = paramApplicationInfo;
    this.ty = paramPackageInfo;
    this.tz = paramString2;
    this.tA = paramString3;
    this.tB = paramString4;
    this.lD = paramgt;
    this.tC = paramBundle2;
    this.tD = paramInt2;
    this.lS = paramList;
    this.tE = paramBundle3;
    this.tF = paramBoolean;
  }
  
  public fi(Bundle paramBundle1, av paramav, ay paramay, String paramString1, ApplicationInfo paramApplicationInfo, PackageInfo paramPackageInfo, String paramString2, String paramString3, String paramString4, gt paramgt, Bundle paramBundle2, int paramInt, List<String> paramList, Bundle paramBundle3, boolean paramBoolean)
  {
    this(4, paramBundle1, paramav, paramay, paramString1, paramApplicationInfo, paramPackageInfo, paramString2, paramString3, paramString4, paramgt, paramBundle2, paramInt, paramList, paramBundle3, paramBoolean);
  }
  
  public fi(a parama, String paramString)
  {
    this(parama.tw, parama.tx, parama.lH, parama.lA, parama.applicationInfo, parama.ty, paramString, parama.tA, parama.tB, parama.lD, parama.tC, parama.tD, parama.lS, parama.tE, parama.tF);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    fj.a(this, paramParcel, paramInt);
  }
  
  @ez
  public static final class a
  {
    public final ApplicationInfo applicationInfo;
    public final String lA;
    public final gt lD;
    public final ay lH;
    public final List<String> lS;
    public final String tA;
    public final String tB;
    public final Bundle tC;
    public final int tD;
    public final Bundle tE;
    public final boolean tF;
    public final Bundle tw;
    public final av tx;
    public final PackageInfo ty;
    
    public a(Bundle paramBundle1, av paramav, ay paramay, String paramString1, ApplicationInfo paramApplicationInfo, PackageInfo paramPackageInfo, String paramString2, String paramString3, gt paramgt, Bundle paramBundle2, List<String> paramList, Bundle paramBundle3, boolean paramBoolean)
    {
      this.tw = paramBundle1;
      this.tx = paramav;
      this.lH = paramay;
      this.lA = paramString1;
      this.applicationInfo = paramApplicationInfo;
      this.ty = paramPackageInfo;
      this.tA = paramString2;
      this.tB = paramString3;
      this.lD = paramgt;
      this.tC = paramBundle2;
      this.tF = paramBoolean;
      if ((paramList != null) && (paramList.size() > 0)) {
        this.tD = 2;
      }
      for (this.lS = paramList;; this.lS = null)
      {
        this.tE = paramBundle3;
        return;
        this.tD = 0;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */