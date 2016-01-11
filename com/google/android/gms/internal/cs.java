package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Map;

@ez
public final class cs
  extends ct.a
{
  private Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> qC;
  
  private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> cu z(String paramString)
    throws RemoteException
  {
    try
    {
      Object localObject = Class.forName(paramString, false, cs.class.getClassLoader());
      if (com.google.ads.mediation.MediationAdapter.class.isAssignableFrom((Class)localObject))
      {
        localObject = (com.google.ads.mediation.MediationAdapter)((Class)localObject).newInstance();
        return new cz((com.google.ads.mediation.MediationAdapter)localObject, (com.google.ads.mediation.NetworkExtras)this.qC.get(((com.google.ads.mediation.MediationAdapter)localObject).getAdditionalParametersType()));
      }
      if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom((Class)localObject)) {
        return new cx((com.google.android.gms.ads.mediation.MediationAdapter)((Class)localObject).newInstance());
      }
      gs.W("Could not instantiate mediation adapter: " + paramString + " (not a valid adapter).");
      throw new RemoteException();
    }
    catch (Throwable localThrowable)
    {
      gs.W("Could not instantiate mediation adapter: " + paramString + ". " + localThrowable.getMessage());
      throw new RemoteException();
    }
  }
  
  public void d(Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> paramMap)
  {
    this.qC = paramMap;
  }
  
  public cu x(String paramString)
    throws RemoteException
  {
    return z(paramString);
  }
  
  public boolean y(String paramString)
    throws RemoteException
  {
    try
    {
      boolean bool = CustomEvent.class.isAssignableFrom(Class.forName(paramString, false, cs.class.getClassLoader()));
      return bool;
    }
    catch (Throwable localThrowable)
    {
      gs.W("Could not load custom event implementation class: " + paramString + ", assuming old implementation.");
    }
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */