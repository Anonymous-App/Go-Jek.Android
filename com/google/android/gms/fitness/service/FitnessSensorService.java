package com.google.android.gms.fitness.service;

import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.kc;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.kt;
import com.google.android.gms.internal.lg;
import com.google.android.gms.internal.li;
import com.google.android.gms.internal.lk.a;
import java.util.List;

public abstract class FitnessSensorService
  extends Service
{
  public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
  private a Va;
  
  public final IBinder onBind(Intent paramIntent)
  {
    if ("com.google.android.gms.fitness.service.FitnessSensorService".equals(paramIntent.getAction()))
    {
      if (Log.isLoggable("FitnessSensorService", 3)) {
        Log.d("FitnessSensorService", "Intent " + paramIntent + " received by " + getClass().getName());
      }
      return this.Va.asBinder();
    }
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.Va = new a(this, null);
  }
  
  public abstract List<DataSource> onFindDataSources(List<DataType> paramList);
  
  public abstract boolean onRegister(FitnessSensorServiceRequest paramFitnessSensorServiceRequest);
  
  public abstract boolean onUnregister(DataSource paramDataSource);
  
  private static class a
    extends lk.a
  {
    private final FitnessSensorService Vb;
    
    private a(FitnessSensorService paramFitnessSensorService)
    {
      this.Vb = paramFitnessSensorService;
    }
    
    private void jM()
      throws SecurityException
    {
      int i = Binder.getCallingUid();
      if (kc.hI())
      {
        ((AppOpsManager)this.Vb.getSystemService("appops")).checkPackage(i, "com.google.android.gms");
        return;
      }
      String[] arrayOfString = this.Vb.getPackageManager().getPackagesForUid(i);
      if (arrayOfString != null)
      {
        int j = arrayOfString.length;
        i = 0;
        for (;;)
        {
          if (i >= j) {
            break label73;
          }
          if (arrayOfString[i].equals("com.google.android.gms")) {
            break;
          }
          i += 1;
        }
      }
      label73:
      throw new SecurityException("Unauthorized caller");
    }
    
    public void a(FitnessSensorServiceRequest paramFitnessSensorServiceRequest, kt paramkt)
      throws RemoteException
    {
      jM();
      if (this.Vb.onRegister(paramFitnessSensorServiceRequest))
      {
        paramkt.k(Status.Jv);
        return;
      }
      paramkt.k(new Status(13));
    }
    
    public void a(lg paramlg, kn paramkn)
      throws RemoteException
    {
      jM();
      paramkn.a(new DataSourcesResult(this.Vb.onFindDataSources(paramlg.getDataTypes()), Status.Jv));
    }
    
    public void a(li paramli, kt paramkt)
      throws RemoteException
    {
      jM();
      if (this.Vb.onUnregister(paramli.getDataSource()))
      {
        paramkt.k(Status.Jv);
        return;
      }
      paramkt.k(new Status(13));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/service/FitnessSensorService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */