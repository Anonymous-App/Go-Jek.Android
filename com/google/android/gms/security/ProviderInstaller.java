package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.o;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.lang.reflect.Method;

public class ProviderInstaller
{
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  private static Method anK = null;
  private static final Object uf = new Object();
  
  private static void V(Context paramContext)
    throws ClassNotFoundException, NoSuchMethodException
  {
    anK = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] { Context.class });
  }
  
  public static void installIfNeeded(Context arg0)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    o.b(???, "Context must not be null");
    GooglePlayServicesUtil.D(???);
    Context localContext = GooglePlayServicesUtil.getRemoteContext(???);
    if (localContext == null)
    {
      Log.e("ProviderInstaller", "Failed to get remote context");
      throw new GooglePlayServicesNotAvailableException(8);
    }
    synchronized (uf)
    {
      try
      {
        if (anK == null) {
          V(localContext);
        }
        anK.invoke(null, new Object[] { localContext });
        return;
      }
      catch (Exception localException)
      {
        Log.e("ProviderInstaller", "Failed to install provider: " + localException.getMessage());
        throw new GooglePlayServicesNotAvailableException(8);
      }
    }
  }
  
  public static void installIfNeededAsync(Context paramContext, final ProviderInstallListener paramProviderInstallListener)
  {
    o.b(paramContext, "Context must not be null");
    o.b(paramProviderInstallListener, "Listener must not be null");
    o.aT("Must be called on the UI thread");
    paramContext = new AsyncTask()
    {
      public Trace _nr_trace;
      
      public void _nr_setTrace(Trace paramAnonymousTrace)
      {
        try
        {
          this._nr_trace = paramAnonymousTrace;
          return;
        }
        catch (Exception paramAnonymousTrace) {}
      }
      
      protected Integer b(Void... paramAnonymousVarArgs)
      {
        try
        {
          ProviderInstaller.installIfNeeded(this.mV);
          return Integer.valueOf(0);
        }
        catch (GooglePlayServicesRepairableException paramAnonymousVarArgs)
        {
          return Integer.valueOf(paramAnonymousVarArgs.getConnectionStatusCode());
        }
        catch (GooglePlayServicesNotAvailableException paramAnonymousVarArgs) {}
        return Integer.valueOf(paramAnonymousVarArgs.errorCode);
      }
      
      protected void d(Integer paramAnonymousInteger)
      {
        if (paramAnonymousInteger.intValue() == 0)
        {
          paramProviderInstallListener.onProviderInstalled();
          return;
        }
        Intent localIntent = GooglePlayServicesUtil.ai(paramAnonymousInteger.intValue());
        paramProviderInstallListener.onProviderInstallFailed(paramAnonymousInteger.intValue(), localIntent);
      }
    };
    paramProviderInstallListener = new Void[0];
    if (!(paramContext instanceof AsyncTask))
    {
      paramContext.execute(paramProviderInstallListener);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)paramContext, paramProviderInstallListener);
  }
  
  public static abstract interface ProviderInstallListener
  {
    public abstract void onProviderInstallFailed(int paramInt, Intent paramIntent);
    
    public abstract void onProviderInstalled();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/security/ProviderInstaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */