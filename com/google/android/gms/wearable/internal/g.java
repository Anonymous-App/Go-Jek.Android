package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public class g
  implements DataEvent
{
  private int FD;
  private DataItem avs;
  
  public g(DataEvent paramDataEvent)
  {
    this.FD = paramDataEvent.getType();
    this.avs = ((DataItem)paramDataEvent.getDataItem().freeze());
  }
  
  public DataItem getDataItem()
  {
    return this.avs;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public DataEvent pW()
  {
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */