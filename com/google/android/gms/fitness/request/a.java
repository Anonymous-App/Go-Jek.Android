package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.data.BleDevice;
import java.util.HashMap;
import java.util.Map;

public class a
  extends l.a
{
  private final BleScanCallback Uf;
  
  private a(BleScanCallback paramBleScanCallback)
  {
    this.Uf = ((BleScanCallback)o.i(paramBleScanCallback));
  }
  
  public void onDeviceFound(BleDevice paramBleDevice)
    throws RemoteException
  {
    this.Uf.onDeviceFound(paramBleDevice);
  }
  
  public void onScanStopped()
    throws RemoteException
  {
    this.Uf.onScanStopped();
  }
  
  public static class a
  {
    private static final a Ug = new a();
    private final Map<BleScanCallback, a> Uh = new HashMap();
    
    public static a je()
    {
      return Ug;
    }
    
    public a a(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.Uh)
      {
        a locala2 = (a)this.Uh.get(paramBleScanCallback);
        a locala1 = locala2;
        if (locala2 == null)
        {
          locala1 = new a(paramBleScanCallback, null);
          this.Uh.put(paramBleScanCallback, locala1);
        }
        return locala1;
      }
    }
    
    public a b(BleScanCallback paramBleScanCallback)
    {
      synchronized (this.Uh)
      {
        a locala = (a)this.Uh.get(paramBleScanCallback);
        if (locala != null) {
          return locala;
        }
        paramBleScanCallback = new a(paramBleScanCallback, null);
        return paramBleScanCallback;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/request/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */