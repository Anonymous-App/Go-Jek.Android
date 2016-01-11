package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

public abstract class DataBuffer<T>
  implements Releasable, Iterable<T>
{
  protected final DataHolder II;
  
  protected DataBuffer(DataHolder paramDataHolder)
  {
    this.II = paramDataHolder;
    if (this.II != null) {
      this.II.e(this);
    }
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (this.II == null) {
      return 0;
    }
    return this.II.getCount();
  }
  
  public Bundle gy()
  {
    return this.II.gy();
  }
  
  @Deprecated
  public boolean isClosed()
  {
    if (this.II == null) {
      return true;
    }
    return this.II.isClosed();
  }
  
  public Iterator<T> iterator()
  {
    return new c(this);
  }
  
  public void release()
  {
    if (this.II != null) {
      this.II.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new h(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/data/DataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */