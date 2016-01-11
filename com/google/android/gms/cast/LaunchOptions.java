package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ik;
import java.util.Locale;

public class LaunchOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<LaunchOptions> CREATOR = new c();
  private final int BR;
  private boolean Fb;
  private String Fc;
  
  public LaunchOptions()
  {
    this(1, false, ik.b(Locale.getDefault()));
  }
  
  LaunchOptions(int paramInt, boolean paramBoolean, String paramString)
  {
    this.BR = paramInt;
    this.Fb = paramBoolean;
    this.Fc = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof LaunchOptions)) {
        return false;
      }
      paramObject = (LaunchOptions)paramObject;
    } while ((this.Fb == ((LaunchOptions)paramObject).Fb) && (ik.a(this.Fc, ((LaunchOptions)paramObject).Fc)));
    return false;
  }
  
  public String getLanguage()
  {
    return this.Fc;
  }
  
  public boolean getRelaunchIfRunning()
  {
    return this.Fb;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Boolean.valueOf(this.Fb), this.Fc });
  }
  
  public void setLanguage(String paramString)
  {
    this.Fc = paramString;
  }
  
  public void setRelaunchIfRunning(boolean paramBoolean)
  {
    this.Fb = paramBoolean;
  }
  
  public String toString()
  {
    return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[] { Boolean.valueOf(this.Fb), this.Fc });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private LaunchOptions Fd = new LaunchOptions();
    
    public LaunchOptions build()
    {
      return this.Fd;
    }
    
    public Builder setLocale(Locale paramLocale)
    {
      this.Fd.setLanguage(ik.b(paramLocale));
      return this;
    }
    
    public Builder setRelaunchIfRunning(boolean paramBoolean)
    {
      this.Fd.setRelaunchIfRunning(paramBoolean);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/LaunchOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */