package com.google.android.gms.internal;

import android.location.Location;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

@ez
public final class cw
  implements MediationAdRequest
{
  private final Date d;
  private final Set<String> f;
  private final boolean g;
  private final Location h;
  private final int om;
  private final int qD;
  
  public cw(Date paramDate, int paramInt1, Set<String> paramSet, Location paramLocation, boolean paramBoolean, int paramInt2)
  {
    this.d = paramDate;
    this.om = paramInt1;
    this.f = paramSet;
    this.h = paramLocation;
    this.g = paramBoolean;
    this.qD = paramInt2;
  }
  
  public Date getBirthday()
  {
    return this.d;
  }
  
  public int getGender()
  {
    return this.om;
  }
  
  public Set<String> getKeywords()
  {
    return this.f;
  }
  
  public Location getLocation()
  {
    return this.h;
  }
  
  public boolean isTesting()
  {
    return this.g;
  }
  
  public int taggedForChildDirectedTreatment()
  {
    return this.qD;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */