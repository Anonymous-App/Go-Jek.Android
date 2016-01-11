package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Payments;
import com.google.android.gms.wallet.Wallet.b;

public class ox
  implements Payments
{
  public void changeMaskedWallet(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final int paramInt)
  {
    paramGoogleApiClient.a(new Wallet.b()
    {
      protected void a(oy paramAnonymousoy)
      {
        paramAnonymousoy.d(paramString1, paramString2, paramInt);
        b(Status.Jv);
      }
    });
  }
  
  public void checkForPreAuthorization(GoogleApiClient paramGoogleApiClient, final int paramInt)
  {
    paramGoogleApiClient.a(new Wallet.b()
    {
      protected void a(oy paramAnonymousoy)
      {
        paramAnonymousoy.fI(paramInt);
        b(Status.Jv);
      }
    });
  }
  
  public void loadFullWallet(GoogleApiClient paramGoogleApiClient, final FullWalletRequest paramFullWalletRequest, final int paramInt)
  {
    paramGoogleApiClient.a(new Wallet.b()
    {
      protected void a(oy paramAnonymousoy)
      {
        paramAnonymousoy.a(paramFullWalletRequest, paramInt);
        b(Status.Jv);
      }
    });
  }
  
  public void loadMaskedWallet(GoogleApiClient paramGoogleApiClient, final MaskedWalletRequest paramMaskedWalletRequest, final int paramInt)
  {
    paramGoogleApiClient.a(new Wallet.b()
    {
      protected void a(oy paramAnonymousoy)
      {
        paramAnonymousoy.a(paramMaskedWalletRequest, paramInt);
        b(Status.Jv);
      }
    });
  }
  
  public void notifyTransactionStatus(GoogleApiClient paramGoogleApiClient, final NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    paramGoogleApiClient.a(new Wallet.b()
    {
      protected void a(oy paramAnonymousoy)
      {
        paramAnonymousoy.a(paramNotifyTransactionStatusRequest);
        b(Status.Jv);
      }
    });
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */