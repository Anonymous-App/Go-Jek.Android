package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class a<T>
  implements MetadataField<T>
{
  private final String PB;
  private final Set<String> PC;
  private final Set<String> PD;
  private final int PE;
  
  protected a(String paramString, int paramInt)
  {
    this.PB = ((String)o.b(paramString, "fieldName"));
    this.PC = Collections.singleton(paramString);
    this.PD = Collections.emptySet();
    this.PE = paramInt;
  }
  
  protected a(String paramString, Collection<String> paramCollection1, Collection<String> paramCollection2, int paramInt)
  {
    this.PB = ((String)o.b(paramString, "fieldName"));
    this.PC = Collections.unmodifiableSet(new HashSet(paramCollection1));
    this.PD = Collections.unmodifiableSet(new HashSet(paramCollection2));
    this.PE = paramInt;
  }
  
  public final T a(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    if (b(paramDataHolder, paramInt1, paramInt2)) {
      return (T)c(paramDataHolder, paramInt1, paramInt2);
    }
    return null;
  }
  
  protected abstract void a(Bundle paramBundle, T paramT);
  
  public final void a(DataHolder paramDataHolder, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2)
  {
    o.b(paramDataHolder, "dataHolder");
    o.b(paramMetadataBundle, "bundle");
    paramMetadataBundle.b(this, a(paramDataHolder, paramInt1, paramInt2));
  }
  
  public final void a(T paramT, Bundle paramBundle)
  {
    o.b(paramBundle, "bundle");
    if (paramT == null)
    {
      paramBundle.putString(getName(), null);
      return;
    }
    a(paramBundle, paramT);
  }
  
  protected boolean b(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.PC.iterator();
    while (localIterator.hasNext()) {
      if (paramDataHolder.h((String)localIterator.next(), paramInt1, paramInt2)) {
        return false;
      }
    }
    return true;
  }
  
  protected abstract T c(DataHolder paramDataHolder, int paramInt1, int paramInt2);
  
  public final T f(Bundle paramBundle)
  {
    o.b(paramBundle, "bundle");
    if (paramBundle.get(getName()) != null) {
      return (T)g(paramBundle);
    }
    return null;
  }
  
  protected abstract T g(Bundle paramBundle);
  
  public final String getName()
  {
    return this.PB;
  }
  
  public String toString()
  {
    return this.PB;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/metadata/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */