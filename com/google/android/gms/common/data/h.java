package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class h<T>
  extends c<T>
{
  private T Kr;
  
  public h(DataBuffer<T> paramDataBuffer)
  {
    super(paramDataBuffer);
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.JW);
    }
    this.JW += 1;
    if (this.JW == 0)
    {
      this.Kr = this.JV.get(0);
      if (!(this.Kr instanceof d)) {
        throw new IllegalStateException("DataBuffer reference of type " + this.Kr.getClass() + " is not movable");
      }
    }
    else
    {
      ((d)this.Kr).ap(this.JW);
    }
    return (T)this.Kr;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/data/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */