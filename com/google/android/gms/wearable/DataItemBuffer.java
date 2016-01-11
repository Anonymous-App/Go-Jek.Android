package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;
import com.google.android.gms.wearable.internal.o;

public class DataItemBuffer
  extends g<DataItem>
  implements Result
{
  private final Status CM;
  
  public DataItemBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    this.CM = new Status(paramDataHolder.getStatusCode());
  }
  
  protected String gD()
  {
    return "path";
  }
  
  public Status getStatus()
  {
    return this.CM;
  }
  
  protected DataItem q(int paramInt1, int paramInt2)
  {
    return new o(this.II, paramInt1, paramInt2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/DataItemBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */