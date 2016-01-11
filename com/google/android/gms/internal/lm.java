package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.identity.intents.UserAddressRequest;

public class lm
  extends e<lo>
{
  private final String Dd;
  private a adM;
  private final int mTheme;
  private Activity nr;
  
  public lm(Activity paramActivity, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, int paramInt)
  {
    super(paramActivity, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.Dd = paramString;
    this.nr = paramActivity;
    this.mTheme = paramInt;
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.d(parame, 6171000, getContext().getPackageName());
  }
  
  public void a(UserAddressRequest paramUserAddressRequest, int paramInt)
  {
    lT();
    this.adM = new a(paramInt, this.nr);
    try
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", getContext().getPackageName());
      if (!TextUtils.isEmpty(this.Dd)) {
        localBundle.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(this.Dd, "com.google"));
      }
      localBundle.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
      lS().a(this.adM, paramUserAddressRequest, localBundle);
      return;
    }
    catch (RemoteException paramUserAddressRequest)
    {
      Log.e("AddressClientImpl", "Exception requesting user address", paramUserAddressRequest);
      paramUserAddressRequest = new Bundle();
      paramUserAddressRequest.putInt("com.google.android.gms.identity.intents.EXTRA_ERROR_CODE", 555);
      this.adM.g(1, paramUserAddressRequest);
    }
  }
  
  protected lo aF(IBinder paramIBinder)
  {
    return lo.a.aH(paramIBinder);
  }
  
  public void disconnect()
  {
    super.disconnect();
    if (this.adM != null)
    {
      a.a(this.adM, null);
      this.adM = null;
    }
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.identity.intents.internal.IAddressService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.identity.service.BIND";
  }
  
  protected lo lS()
  {
    return (lo)super.gS();
  }
  
  protected void lT()
  {
    super.dJ();
  }
  
  public static final class a
    extends ln.a
  {
    private final int Lu;
    private Activity nr;
    
    public a(int paramInt, Activity paramActivity)
    {
      this.Lu = paramInt;
      this.nr = paramActivity;
    }
    
    private void setActivity(Activity paramActivity)
    {
      this.nr = paramActivity;
    }
    
    public void g(int paramInt, Bundle paramBundle)
    {
      Object localObject;
      if (paramInt == 1)
      {
        localObject = new Intent();
        ((Intent)localObject).putExtras(paramBundle);
        paramBundle = this.nr.createPendingResult(this.Lu, (Intent)localObject, 1073741824);
        if (paramBundle != null) {}
      }
      for (;;)
      {
        return;
        try
        {
          paramBundle.send(1);
          return;
        }
        catch (PendingIntent.CanceledException paramBundle)
        {
          Log.w("AddressClientImpl", "Exception settng pending result", paramBundle);
          return;
        }
        localObject = null;
        if (paramBundle != null) {
          localObject = (PendingIntent)paramBundle.getParcelable("com.google.android.gms.identity.intents.EXTRA_PENDING_INTENT");
        }
        paramBundle = new ConnectionResult(paramInt, (PendingIntent)localObject);
        if (paramBundle.hasResolution()) {
          try
          {
            paramBundle.startResolutionForResult(this.nr, this.Lu);
            return;
          }
          catch (IntentSender.SendIntentException paramBundle)
          {
            Log.w("AddressClientImpl", "Exception starting pending intent", paramBundle);
            return;
          }
        }
        try
        {
          paramBundle = this.nr.createPendingResult(this.Lu, new Intent(), 1073741824);
          if (paramBundle != null)
          {
            paramBundle.send(1);
            return;
          }
        }
        catch (PendingIntent.CanceledException paramBundle)
        {
          Log.w("AddressClientImpl", "Exception setting pending result", paramBundle);
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/lm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */