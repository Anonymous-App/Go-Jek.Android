package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.d.a;
import com.google.android.gms.dynamic.e;

@ez
public final class dm
  implements SafeParcelable
{
  public static final dl CREATOR = new dl();
  public final gt lD;
  public final int orientation;
  public final dj rK;
  public final t rL;
  public final dn rM;
  public final gv rN;
  public final bw rO;
  public final String rP;
  public final boolean rQ;
  public final String rR;
  public final dq rS;
  public final int rT;
  public final bz rU;
  public final String rV;
  public final x rW;
  public final String rq;
  public final int versionCode;
  
  dm(int paramInt1, dj paramdj, IBinder paramIBinder1, IBinder paramIBinder2, IBinder paramIBinder3, IBinder paramIBinder4, String paramString1, boolean paramBoolean, String paramString2, IBinder paramIBinder5, int paramInt2, int paramInt3, String paramString3, gt paramgt, IBinder paramIBinder6, String paramString4, x paramx)
  {
    this.versionCode = paramInt1;
    this.rK = paramdj;
    this.rL = ((t)e.f(d.a.am(paramIBinder1)));
    this.rM = ((dn)e.f(d.a.am(paramIBinder2)));
    this.rN = ((gv)e.f(d.a.am(paramIBinder3)));
    this.rO = ((bw)e.f(d.a.am(paramIBinder4)));
    this.rP = paramString1;
    this.rQ = paramBoolean;
    this.rR = paramString2;
    this.rS = ((dq)e.f(d.a.am(paramIBinder5)));
    this.orientation = paramInt2;
    this.rT = paramInt3;
    this.rq = paramString3;
    this.lD = paramgt;
    this.rU = ((bz)e.f(d.a.am(paramIBinder6)));
    this.rV = paramString4;
    this.rW = paramx;
  }
  
  public dm(dj paramdj, t paramt, dn paramdn, dq paramdq, gt paramgt)
  {
    this.versionCode = 4;
    this.rK = paramdj;
    this.rL = paramt;
    this.rM = paramdn;
    this.rN = null;
    this.rO = null;
    this.rP = null;
    this.rQ = false;
    this.rR = null;
    this.rS = paramdq;
    this.orientation = -1;
    this.rT = 4;
    this.rq = null;
    this.lD = paramgt;
    this.rU = null;
    this.rV = null;
    this.rW = null;
  }
  
  public dm(t paramt, dn paramdn, bw parambw, dq paramdq, gv paramgv, boolean paramBoolean, int paramInt, String paramString, gt paramgt, bz parambz)
  {
    this.versionCode = 4;
    this.rK = null;
    this.rL = paramt;
    this.rM = paramdn;
    this.rN = paramgv;
    this.rO = parambw;
    this.rP = null;
    this.rQ = paramBoolean;
    this.rR = null;
    this.rS = paramdq;
    this.orientation = paramInt;
    this.rT = 3;
    this.rq = paramString;
    this.lD = paramgt;
    this.rU = parambz;
    this.rV = null;
    this.rW = null;
  }
  
  public dm(t paramt, dn paramdn, bw parambw, dq paramdq, gv paramgv, boolean paramBoolean, int paramInt, String paramString1, String paramString2, gt paramgt, bz parambz)
  {
    this.versionCode = 4;
    this.rK = null;
    this.rL = paramt;
    this.rM = paramdn;
    this.rN = paramgv;
    this.rO = parambw;
    this.rP = paramString2;
    this.rQ = paramBoolean;
    this.rR = paramString1;
    this.rS = paramdq;
    this.orientation = paramInt;
    this.rT = 3;
    this.rq = null;
    this.lD = paramgt;
    this.rU = parambz;
    this.rV = null;
    this.rW = null;
  }
  
  public dm(t paramt, dn paramdn, dq paramdq, gv paramgv, int paramInt, gt paramgt, String paramString, x paramx)
  {
    this.versionCode = 4;
    this.rK = null;
    this.rL = paramt;
    this.rM = paramdn;
    this.rN = paramgv;
    this.rO = null;
    this.rP = null;
    this.rQ = false;
    this.rR = null;
    this.rS = paramdq;
    this.orientation = paramInt;
    this.rT = 1;
    this.rq = null;
    this.lD = paramgt;
    this.rU = null;
    this.rV = paramString;
    this.rW = paramx;
  }
  
  public dm(t paramt, dn paramdn, dq paramdq, gv paramgv, boolean paramBoolean, int paramInt, gt paramgt)
  {
    this.versionCode = 4;
    this.rK = null;
    this.rL = paramt;
    this.rM = paramdn;
    this.rN = paramgv;
    this.rO = null;
    this.rP = null;
    this.rQ = paramBoolean;
    this.rR = null;
    this.rS = paramdq;
    this.orientation = paramInt;
    this.rT = 2;
    this.rq = null;
    this.lD = paramgt;
    this.rU = null;
    this.rV = null;
    this.rW = null;
  }
  
  public static void a(Intent paramIntent, dm paramdm)
  {
    Bundle localBundle = new Bundle(1);
    localBundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", paramdm);
    paramIntent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", localBundle);
  }
  
  public static dm b(Intent paramIntent)
  {
    try
    {
      paramIntent = paramIntent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
      paramIntent.setClassLoader(dm.class.getClassLoader());
      paramIntent = (dm)paramIntent.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
      return paramIntent;
    }
    catch (Exception paramIntent) {}
    return null;
  }
  
  IBinder cb()
  {
    return e.k(this.rL).asBinder();
  }
  
  IBinder cc()
  {
    return e.k(this.rM).asBinder();
  }
  
  IBinder cd()
  {
    return e.k(this.rN).asBinder();
  }
  
  IBinder ce()
  {
    return e.k(this.rO).asBinder();
  }
  
  IBinder cf()
  {
    return e.k(this.rU).asBinder();
  }
  
  IBinder cg()
  {
    return e.k(this.rS).asBinder();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    dl.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */