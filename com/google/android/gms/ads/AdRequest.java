package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.bg;
import com.google.android.gms.internal.bg.a;
import java.util.Date;
import java.util.Set;

public final class AdRequest
{
  public static final String DEVICE_ID_EMULATOR = bg.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  public static final int GENDER_FEMALE = 2;
  public static final int GENDER_MALE = 1;
  public static final int GENDER_UNKNOWN = 0;
  public static final int MAX_CONTENT_URL_LENGTH = 512;
  private final bg ld;
  
  private AdRequest(Builder paramBuilder)
  {
    this.ld = new bg(Builder.a(paramBuilder));
  }
  
  bg V()
  {
    return this.ld;
  }
  
  public Date getBirthday()
  {
    return this.ld.getBirthday();
  }
  
  public String getContentUrl()
  {
    return this.ld.getContentUrl();
  }
  
  public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> paramClass)
  {
    return this.ld.getCustomEventExtrasBundle(paramClass);
  }
  
  public int getGender()
  {
    return this.ld.getGender();
  }
  
  public Set<String> getKeywords()
  {
    return this.ld.getKeywords();
  }
  
  public Location getLocation()
  {
    return this.ld.getLocation();
  }
  
  @Deprecated
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return this.ld.getNetworkExtras(paramClass);
  }
  
  public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> paramClass)
  {
    return this.ld.getNetworkExtrasBundle(paramClass);
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.ld.isTestDevice(paramContext);
  }
  
  public static final class Builder
  {
    private final bg.a le = new bg.a();
    
    public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
    {
      this.le.b(paramClass, paramBundle);
      return this;
    }
    
    public Builder addKeyword(String paramString)
    {
      this.le.r(paramString);
      return this;
    }
    
    public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
    {
      this.le.a(paramNetworkExtras);
      return this;
    }
    
    public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
    {
      this.le.a(paramClass, paramBundle);
      return this;
    }
    
    public Builder addTestDevice(String paramString)
    {
      this.le.s(paramString);
      return this;
    }
    
    public AdRequest build()
    {
      return new AdRequest(this, null);
    }
    
    public Builder setBirthday(Date paramDate)
    {
      this.le.a(paramDate);
      return this;
    }
    
    public Builder setContentUrl(String paramString)
    {
      o.b(paramString, "Content URL must be non-null.");
      o.b(paramString, "Content URL must be non-empty.");
      if (paramString.length() <= 512) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[] { Integer.valueOf(512), Integer.valueOf(paramString.length()) });
        this.le.t(paramString);
        return this;
      }
    }
    
    public Builder setGender(int paramInt)
    {
      this.le.g(paramInt);
      return this;
    }
    
    public Builder setLocation(Location paramLocation)
    {
      this.le.a(paramLocation);
      return this;
    }
    
    public Builder tagForChildDirectedTreatment(boolean paramBoolean)
    {
      this.le.h(paramBoolean);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/ads/AdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */