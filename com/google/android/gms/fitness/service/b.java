package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.k;
import java.util.Iterator;
import java.util.List;

class b
  implements SensorEventDispatcher
{
  private final k UA;
  
  b(k paramk)
  {
    this.UA = ((k)o.i(paramk));
  }
  
  public void publish(DataPoint paramDataPoint)
    throws RemoteException
  {
    this.UA.c(paramDataPoint);
  }
  
  public void publish(List<DataPoint> paramList)
    throws RemoteException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      publish((DataPoint)paramList.next());
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/service/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */