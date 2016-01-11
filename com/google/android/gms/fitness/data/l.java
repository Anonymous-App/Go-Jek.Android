package com.google.android.gms.fitness.data;

import android.os.RemoteException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.fitness.request.OnDataPointListener;
import java.util.HashMap;
import java.util.Map;

public class l
  extends k.a
{
  private final OnDataPointListener Ti;
  
  private l(OnDataPointListener paramOnDataPointListener)
  {
    this.Ti = ((OnDataPointListener)o.i(paramOnDataPointListener));
  }
  
  public void c(DataPoint paramDataPoint)
    throws RemoteException
  {
    this.Ti.onDataPoint(paramDataPoint);
  }
  
  public static class a
  {
    private static final a Tj = new a();
    private final Map<OnDataPointListener, l> Tk = new HashMap();
    
    public static a iV()
    {
      return Tj;
    }
    
    public l a(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.Tk)
      {
        l locall2 = (l)this.Tk.get(paramOnDataPointListener);
        l locall1 = locall2;
        if (locall2 == null)
        {
          locall1 = new l(paramOnDataPointListener, null);
          this.Tk.put(paramOnDataPointListener, locall1);
        }
        return locall1;
      }
    }
    
    public l b(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.Tk)
      {
        paramOnDataPointListener = (l)this.Tk.get(paramOnDataPointListener);
        return paramOnDataPointListener;
      }
    }
    
    public l c(OnDataPointListener paramOnDataPointListener)
    {
      synchronized (this.Tk)
      {
        l locall = (l)this.Tk.remove(paramOnDataPointListener);
        if (locall != null) {
          return locall;
        }
        paramOnDataPointListener = new l(paramOnDataPointListener, null);
        return paramOnDataPointListener;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */