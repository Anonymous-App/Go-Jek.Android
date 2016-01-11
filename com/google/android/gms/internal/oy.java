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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;

public class oy
  extends e<ot>
{
  private final String Dd;
  private final int atL;
  private final int mTheme;
  private final Activity nr;
  
  public oy(Activity paramActivity, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, int paramInt1, String paramString, int paramInt2)
  {
    super(paramActivity, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, new String[0]);
    this.nr = paramActivity;
    this.atL = paramInt1;
    this.Dd = paramString;
    this.mTheme = paramInt2;
  }
  
  public static Bundle a(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", paramInt1);
    localBundle.putString("androidPackageName", paramString1);
    if (!TextUtils.isEmpty(paramString2)) {
      localBundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(paramString2, "com.google"));
    }
    localBundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", paramInt2);
    return localBundle;
  }
  
  private Bundle pO()
  {
    return a(this.atL, this.nr.getPackageName(), this.Dd, this.mTheme);
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    paraml.k(parame, 6171000, getContext().getPackageName());
  }
  
  public void a(FullWalletRequest paramFullWalletRequest, int paramInt)
  {
    b localb = new b(paramInt);
    Bundle localBundle = pO();
    try
    {
      ((ot)gS()).a(paramFullWalletRequest, localBundle, localb);
      return;
    }
    catch (RemoteException paramFullWalletRequest)
    {
      Log.e("WalletClientImpl", "RemoteException getting full wallet", paramFullWalletRequest);
      localb.a(8, null, Bundle.EMPTY);
    }
  }
  
  public void a(MaskedWalletRequest paramMaskedWalletRequest, int paramInt)
  {
    Bundle localBundle = pO();
    b localb = new b(paramInt);
    try
    {
      ((ot)gS()).a(paramMaskedWalletRequest, localBundle, localb);
      return;
    }
    catch (RemoteException paramMaskedWalletRequest)
    {
      Log.e("WalletClientImpl", "RemoteException getting masked wallet", paramMaskedWalletRequest);
      localb.a(8, null, Bundle.EMPTY);
    }
  }
  
  public void a(NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    Bundle localBundle = pO();
    try
    {
      ((ot)gS()).a(paramNotifyTransactionStatusRequest, localBundle);
      return;
    }
    catch (RemoteException paramNotifyTransactionStatusRequest) {}
  }
  
  protected ot bP(IBinder paramIBinder)
  {
    return ot.a.bL(paramIBinder);
  }
  
  public void d(String paramString1, String paramString2, int paramInt)
  {
    Bundle localBundle = pO();
    b localb = new b(paramInt);
    try
    {
      ((ot)gS()).a(paramString1, paramString2, localBundle, localb);
      return;
    }
    catch (RemoteException paramString1)
    {
      Log.e("WalletClientImpl", "RemoteException changing masked wallet", paramString1);
      localb.a(8, null, Bundle.EMPTY);
    }
  }
  
  public void fI(int paramInt)
  {
    Bundle localBundle = pO();
    b localb = new b(paramInt);
    try
    {
      ((ot)gS()).a(localBundle, localb);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("WalletClientImpl", "RemoteException during checkForPreAuthorization", localRemoteException);
      localb.a(8, false, Bundle.EMPTY);
    }
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.wallet.internal.IOwService";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.wallet.service.BIND";
  }
  
  private static class a
    extends ow.a
  {
    public void a(int paramInt, FullWallet paramFullWallet, Bundle paramBundle) {}
    
    public void a(int paramInt, MaskedWallet paramMaskedWallet, Bundle paramBundle) {}
    
    public void a(int paramInt, boolean paramBoolean, Bundle paramBundle) {}
    
    public void a(Status paramStatus, op paramop, Bundle paramBundle) {}
    
    public void i(int paramInt, Bundle paramBundle) {}
  }
  
  final class b
    extends oy.a
  {
    private final int Lu;
    
    public b(int paramInt)
    {
      super();
      this.Lu = paramInt;
    }
    
    public void a(int paramInt, FullWallet paramFullWallet, Bundle paramBundle)
    {
      Object localObject = null;
      if (paramBundle != null) {
        localObject = (PendingIntent)paramBundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
      }
      paramBundle = new ConnectionResult(paramInt, (PendingIntent)localObject);
      if (paramBundle.hasResolution()) {
        try
        {
          paramBundle.startResolutionForResult(oy.b(oy.this), this.Lu);
          return;
        }
        catch (IntentSender.SendIntentException paramFullWallet)
        {
          Log.w("WalletClientImpl", "Exception starting pending intent", paramFullWallet);
          return;
        }
      }
      localObject = new Intent();
      int i;
      if (paramBundle.isSuccess())
      {
        i = -1;
        ((Intent)localObject).putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", paramFullWallet);
        paramFullWallet = oy.b(oy.this).createPendingResult(this.Lu, (Intent)localObject, 1073741824);
        if (paramFullWallet == null) {
          Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
        }
      }
      else
      {
        if (paramInt == 408) {}
        for (i = 0;; i = 1)
        {
          ((Intent)localObject).putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", paramInt);
          break;
        }
      }
      try
      {
        paramFullWallet.send(i);
        return;
      }
      catch (PendingIntent.CanceledException paramFullWallet)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", paramFullWallet);
      }
    }
    
    public void a(int paramInt, MaskedWallet paramMaskedWallet, Bundle paramBundle)
    {
      Object localObject = null;
      if (paramBundle != null) {
        localObject = (PendingIntent)paramBundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
      }
      paramBundle = new ConnectionResult(paramInt, (PendingIntent)localObject);
      if (paramBundle.hasResolution()) {
        try
        {
          paramBundle.startResolutionForResult(oy.b(oy.this), this.Lu);
          return;
        }
        catch (IntentSender.SendIntentException paramMaskedWallet)
        {
          Log.w("WalletClientImpl", "Exception starting pending intent", paramMaskedWallet);
          return;
        }
      }
      localObject = new Intent();
      int i;
      if (paramBundle.isSuccess())
      {
        i = -1;
        ((Intent)localObject).putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", paramMaskedWallet);
        paramMaskedWallet = oy.b(oy.this).createPendingResult(this.Lu, (Intent)localObject, 1073741824);
        if (paramMaskedWallet == null) {
          Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
        }
      }
      else
      {
        if (paramInt == 408) {}
        for (i = 0;; i = 1)
        {
          ((Intent)localObject).putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", paramInt);
          break;
        }
      }
      try
      {
        paramMaskedWallet.send(i);
        return;
      }
      catch (PendingIntent.CanceledException paramMaskedWallet)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", paramMaskedWallet);
      }
    }
    
    public void a(int paramInt, boolean paramBoolean, Bundle paramBundle)
    {
      paramBundle = new Intent();
      paramBundle.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", paramBoolean);
      paramBundle = oy.b(oy.this).createPendingResult(this.Lu, paramBundle, 1073741824);
      if (paramBundle == null)
      {
        Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
        return;
      }
      try
      {
        paramBundle.send(-1);
        return;
      }
      catch (PendingIntent.CanceledException paramBundle)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", paramBundle);
      }
    }
    
    public void i(int paramInt, Bundle paramBundle)
    {
      o.b(paramBundle, "Bundle should not be null");
      paramBundle = new ConnectionResult(paramInt, (PendingIntent)paramBundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
      if (paramBundle.hasResolution()) {
        try
        {
          paramBundle.startResolutionForResult(oy.b(oy.this), this.Lu);
          return;
        }
        catch (IntentSender.SendIntentException paramBundle)
        {
          Log.w("WalletClientImpl", "Exception starting pending intent", paramBundle);
          return;
        }
      }
      Log.e("WalletClientImpl", "Create Wallet Objects confirmation UI will not be shown connection result: " + paramBundle);
      paramBundle = new Intent();
      paramBundle.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
      paramBundle = oy.b(oy.this).createPendingResult(this.Lu, paramBundle, 1073741824);
      if (paramBundle == null)
      {
        Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
        return;
      }
      try
      {
        paramBundle.send(1);
        return;
      }
      catch (PendingIntent.CanceledException paramBundle)
      {
        Log.w("WalletClientImpl", "Exception setting pending result", paramBundle);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/oy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */