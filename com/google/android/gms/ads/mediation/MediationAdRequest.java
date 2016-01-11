package com.google.android.gms.ads.mediation;

import android.location.Location;
import java.util.Date;
import java.util.Set;

public abstract interface MediationAdRequest
{
  public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
  public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
  public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
  
  public abstract Date getBirthday();
  
  public abstract int getGender();
  
  public abstract Set<String> getKeywords();
  
  public abstract Location getLocation();
  
  public abstract boolean isTesting();
  
  public abstract int taggedForChildDirectedTreatment();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/mediation/MediationAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */