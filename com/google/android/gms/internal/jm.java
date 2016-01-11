package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class jm
  implements SafeParcelable
{
  public static final jn CREATOR = new jn();
  private final int BR;
  private final HashMap<String, HashMap<String, ji.a<?, ?>>> MI;
  private final ArrayList<a> MJ;
  private final String MK;
  
  jm(int paramInt, ArrayList<a> paramArrayList, String paramString)
  {
    this.BR = paramInt;
    this.MJ = null;
    this.MI = c(paramArrayList);
    this.MK = ((String)o.i(paramString));
    hs();
  }
  
  public jm(Class<? extends ji> paramClass)
  {
    this.BR = 1;
    this.MJ = null;
    this.MI = new HashMap();
    this.MK = paramClass.getCanonicalName();
  }
  
  private static HashMap<String, HashMap<String, ji.a<?, ?>>> c(ArrayList<a> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      a locala = (a)paramArrayList.get(i);
      localHashMap.put(locala.className, locala.hw());
      i += 1;
    }
    return localHashMap;
  }
  
  public void a(Class<? extends ji> paramClass, HashMap<String, ji.a<?, ?>> paramHashMap)
  {
    this.MI.put(paramClass.getCanonicalName(), paramHashMap);
  }
  
  public boolean b(Class<? extends ji> paramClass)
  {
    return this.MI.containsKey(paramClass.getCanonicalName());
  }
  
  public HashMap<String, ji.a<?, ?>> be(String paramString)
  {
    return (HashMap)this.MI.get(paramString);
  }
  
  public int describeContents()
  {
    jn localjn = CREATOR;
    return 0;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public void hs()
  {
    Iterator localIterator1 = this.MI.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localObject = (HashMap)this.MI.get(localObject);
      Iterator localIterator2 = ((HashMap)localObject).keySet().iterator();
      while (localIterator2.hasNext()) {
        ((ji.a)((HashMap)localObject).get((String)localIterator2.next())).a(this);
      }
    }
  }
  
  public void ht()
  {
    Iterator localIterator1 = this.MI.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str1 = (String)localIterator1.next();
      HashMap localHashMap1 = (HashMap)this.MI.get(str1);
      HashMap localHashMap2 = new HashMap();
      Iterator localIterator2 = localHashMap1.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str2 = (String)localIterator2.next();
        localHashMap2.put(str2, ((ji.a)localHashMap1.get(str2)).hi());
      }
      this.MI.put(str1, localHashMap2);
    }
  }
  
  ArrayList<a> hu()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.MI.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add(new a(str, (HashMap)this.MI.get(str)));
    }
    return localArrayList;
  }
  
  public String hv()
  {
    return this.MK;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = this.MI.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (String)localIterator1.next();
      localStringBuilder.append((String)localObject).append(":\n");
      localObject = (HashMap)this.MI.get(localObject);
      Iterator localIterator2 = ((HashMap)localObject).keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localStringBuilder.append("  ").append(str).append(": ");
        localStringBuilder.append(((HashMap)localObject).get(str));
      }
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    jn localjn = CREATOR;
    jn.a(this, paramParcel, paramInt);
  }
  
  public static class a
    implements SafeParcelable
  {
    public static final jo CREATOR = new jo();
    final ArrayList<jm.b> ML;
    final String className;
    final int versionCode;
    
    a(int paramInt, String paramString, ArrayList<jm.b> paramArrayList)
    {
      this.versionCode = paramInt;
      this.className = paramString;
      this.ML = paramArrayList;
    }
    
    a(String paramString, HashMap<String, ji.a<?, ?>> paramHashMap)
    {
      this.versionCode = 1;
      this.className = paramString;
      this.ML = a(paramHashMap);
    }
    
    private static ArrayList<jm.b> a(HashMap<String, ji.a<?, ?>> paramHashMap)
    {
      if (paramHashMap == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localArrayList.add(new jm.b(str, (ji.a)paramHashMap.get(str)));
      }
      return localArrayList;
    }
    
    public int describeContents()
    {
      jo localjo = CREATOR;
      return 0;
    }
    
    HashMap<String, ji.a<?, ?>> hw()
    {
      HashMap localHashMap = new HashMap();
      int j = this.ML.size();
      int i = 0;
      while (i < j)
      {
        jm.b localb = (jm.b)this.ML.get(i);
        localHashMap.put(localb.fv, localb.MM);
        i += 1;
      }
      return localHashMap;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      jo localjo = CREATOR;
      jo.a(this, paramParcel, paramInt);
    }
  }
  
  public static class b
    implements SafeParcelable
  {
    public static final jl CREATOR = new jl();
    final ji.a<?, ?> MM;
    final String fv;
    final int versionCode;
    
    b(int paramInt, String paramString, ji.a<?, ?> parama)
    {
      this.versionCode = paramInt;
      this.fv = paramString;
      this.MM = parama;
    }
    
    b(String paramString, ji.a<?, ?> parama)
    {
      this.versionCode = 1;
      this.fv = paramString;
      this.MM = parama;
    }
    
    public int describeContents()
    {
      jl localjl = CREATOR;
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      jl localjl = CREATOR;
      jl.a(this, paramParcel, paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */