package com.google.android.gms.common.api;

import com.google.android.gms.common.data.DataHolder;

public abstract class a
  implements Releasable, Result
{
  protected final Status CM;
  protected final DataHolder II;
  
  protected a(DataHolder paramDataHolder)
  {
    this.CM = new Status(paramDataHolder.getStatusCode());
    this.II = paramDataHolder;
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  public void release()
  {
    if (this.II != null) {
      this.II.close();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/api/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */