package com.google.android.gms.drive.metadata.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.kd;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class MetadataBundle
  implements SafeParcelable
{
  public static final Parcelable.Creator<MetadataBundle> CREATOR = new h();
  final int BR;
  final Bundle PL;
  
  MetadataBundle(int paramInt, Bundle paramBundle)
  {
    this.BR = paramInt;
    this.PL = ((Bundle)o.i(paramBundle));
    this.PL.setClassLoader(getClass().getClassLoader());
    paramBundle = new ArrayList();
    Object localObject = this.PL.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (e.bj(str) == null)
      {
        paramBundle.add(str);
        v.p("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
      }
    }
    paramBundle = paramBundle.iterator();
    while (paramBundle.hasNext())
    {
      localObject = (String)paramBundle.next();
      this.PL.remove((String)localObject);
    }
  }
  
  private MetadataBundle(Bundle paramBundle)
  {
    this(1, paramBundle);
  }
  
  public static <T> MetadataBundle a(MetadataField<T> paramMetadataField, T paramT)
  {
    MetadataBundle localMetadataBundle = io();
    localMetadataBundle.b(paramMetadataField, paramT);
    return localMetadataBundle;
  }
  
  public static MetadataBundle a(MetadataBundle paramMetadataBundle)
  {
    return new MetadataBundle(new Bundle(paramMetadataBundle.PL));
  }
  
  public static MetadataBundle io()
  {
    return new MetadataBundle(new Bundle());
  }
  
  public <T> T a(MetadataField<T> paramMetadataField)
  {
    return (T)paramMetadataField.f(this.PL);
  }
  
  public <T> void b(MetadataField<T> paramMetadataField, T paramT)
  {
    if (e.bj(paramMetadataField.getName()) == null) {
      throw new IllegalArgumentException("Unregistered field: " + paramMetadataField.getName());
    }
    paramMetadataField.a(paramT, this.PL);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof MetadataBundle)) {
      return false;
    }
    paramObject = (MetadataBundle)paramObject;
    Object localObject = this.PL.keySet();
    if (!((Set)localObject).equals(((MetadataBundle)paramObject).PL.keySet())) {
      return false;
    }
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!n.equal(this.PL.get(str), ((MetadataBundle)paramObject).PL.get(str))) {
        return false;
      }
    }
    return true;
  }
  
  public int hashCode()
  {
    Iterator localIterator = this.PL.keySet().iterator();
    String str;
    for (int i = 1; localIterator.hasNext(); i = this.PL.get(str).hashCode() + i * 31) {
      str = (String)localIterator.next();
    }
    return i;
  }
  
  public Set<MetadataField<?>> ip()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.PL.keySet().iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(e.bj((String)localIterator.next()));
    }
    return localHashSet;
  }
  
  public void setContext(Context paramContext)
  {
    a locala = (a)a(kd.Ql);
    if (locala != null) {
      locala.a(paramContext.getCacheDir());
    }
  }
  
  public String toString()
  {
    return "MetadataBundle [values=" + this.PL + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/metadata/internal/MetadataBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */