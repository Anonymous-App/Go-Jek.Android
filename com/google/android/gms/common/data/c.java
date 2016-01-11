package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.o;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class c<T>
  implements Iterator<T>
{
  protected final DataBuffer<T> JV;
  protected int JW;
  
  public c(DataBuffer<T> paramDataBuffer)
  {
    this.JV = ((DataBuffer)o.i(paramDataBuffer));
    this.JW = -1;
  }
  
  public boolean hasNext()
  {
    return this.JW < this.JV.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.JW);
    }
    DataBuffer localDataBuffer = this.JV;
    int i = this.JW + 1;
    this.JW = i;
    return (T)localDataBuffer.get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/data/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */