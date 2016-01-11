package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class jf
  implements SafeParcelable, ji.b<String, Integer>
{
  public static final jg CREATOR = new jg();
  private final int BR;
  private final HashMap<String, Integer> Mt;
  private final HashMap<Integer, String> Mu;
  private final ArrayList<a> Mv;
  
  public jf()
  {
    this.BR = 1;
    this.Mt = new HashMap();
    this.Mu = new HashMap();
    this.Mv = null;
  }
  
  jf(int paramInt, ArrayList<a> paramArrayList)
  {
    this.BR = paramInt;
    this.Mt = new HashMap();
    this.Mu = new HashMap();
    this.Mv = null;
    b(paramArrayList);
  }
  
  private void b(ArrayList<a> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      a locala = (a)paramArrayList.next();
      h(locala.Mw, locala.Mx);
    }
  }
  
  public String a(Integer paramInteger)
  {
    String str = (String)this.Mu.get(paramInteger);
    paramInteger = str;
    if (str == null)
    {
      paramInteger = str;
      if (this.Mt.containsKey("gms_unknown")) {
        paramInteger = "gms_unknown";
      }
    }
    return paramInteger;
  }
  
  public int describeContents()
  {
    jg localjg = CREATOR;
    return 0;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public jf h(String paramString, int paramInt)
  {
    this.Mt.put(paramString, Integer.valueOf(paramInt));
    this.Mu.put(Integer.valueOf(paramInt), paramString);
    return this;
  }
  
  ArrayList<a> hc()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.Mt.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new a(str, ((Integer)this.Mt.get(str)).intValue()));
    }
    return localArrayList;
  }
  
  public int hd()
  {
    return 7;
  }
  
  public int he()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    jg localjg = CREATOR;
    jg.a(this, paramParcel, paramInt);
  }
  
  public static final class a
    implements SafeParcelable
  {
    public static final jh CREATOR = new jh();
    final String Mw;
    final int Mx;
    final int versionCode;
    
    a(int paramInt1, String paramString, int paramInt2)
    {
      this.versionCode = paramInt1;
      this.Mw = paramString;
      this.Mx = paramInt2;
    }
    
    a(String paramString, int paramInt)
    {
      this.versionCode = 1;
      this.Mw = paramString;
      this.Mx = paramInt;
    }
    
    public int describeContents()
    {
      jh localjh = CREATOR;
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      jh localjh = CREATOR;
      jh.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */