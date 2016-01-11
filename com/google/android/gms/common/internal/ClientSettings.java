package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.view.View;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ClientSettings
{
  private final View IL;
  private final ParcelableClientSettings Ls;
  
  public ClientSettings(String paramString1, Collection<String> paramCollection, int paramInt, View paramView, String paramString2)
  {
    this.Ls = new ParcelableClientSettings(paramString1, paramCollection, paramInt, paramString2);
    this.IL = paramView;
  }
  
  public String getAccountName()
  {
    return this.Ls.getAccountName();
  }
  
  public String getAccountNameOrDefault()
  {
    return this.Ls.getAccountNameOrDefault();
  }
  
  public int getGravityForPopups()
  {
    return this.Ls.getGravityForPopups();
  }
  
  public ParcelableClientSettings getParcelableClientSettings()
  {
    return this.Ls;
  }
  
  public String getRealClientPackageName()
  {
    return this.Ls.getRealClientPackageName();
  }
  
  public List<String> getScopes()
  {
    return this.Ls.getScopes();
  }
  
  public String[] getScopesArray()
  {
    return (String[])this.Ls.getScopes().toArray(new String[0]);
  }
  
  public View getViewForPopups()
  {
    return this.IL;
  }
  
  public static final class ParcelableClientSettings
    implements SafeParcelable
  {
    public static final ParcelableClientSettingsCreator CREATOR = new ParcelableClientSettingsCreator();
    private final int BR;
    private final String Dd;
    private final int IK;
    private final String IM;
    private final List<String> Jk = new ArrayList();
    
    ParcelableClientSettings(int paramInt1, String paramString1, List<String> paramList, int paramInt2, String paramString2)
    {
      this.BR = paramInt1;
      this.Dd = paramString1;
      this.Jk.addAll(paramList);
      this.IK = paramInt2;
      this.IM = paramString2;
    }
    
    public ParcelableClientSettings(String paramString1, Collection<String> paramCollection, int paramInt, String paramString2)
    {
      this(3, paramString1, new ArrayList(paramCollection), paramInt, paramString2);
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String getAccountName()
    {
      return this.Dd;
    }
    
    public String getAccountNameOrDefault()
    {
      if (this.Dd != null) {
        return this.Dd;
      }
      return "<<default account>>";
    }
    
    public int getGravityForPopups()
    {
      return this.IK;
    }
    
    public String getRealClientPackageName()
    {
      return this.IM;
    }
    
    public List<String> getScopes()
    {
      return new ArrayList(this.Jk);
    }
    
    public int getVersionCode()
    {
      return this.BR;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      ParcelableClientSettingsCreator.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/internal/ClientSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */