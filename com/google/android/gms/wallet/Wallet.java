package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.BaseImplementation.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.om;
import com.google.android.gms.internal.ox;
import com.google.android.gms.internal.oy;
import com.google.android.gms.internal.pa;
import com.google.android.gms.internal.pb;
import com.google.android.gms.wallet.wobs.r;
import java.util.Locale;

public final class Wallet
{
  public static final Api<WalletOptions> API = new Api(CV, CU, new Scope[0]);
  private static final Api.c<oy> CU = new Api.c();
  private static final Api.b<oy, WalletOptions> CV = new Api.b()
  {
    public oy a(Context paramAnonymousContext, Looper paramAnonymousLooper, ClientSettings paramAnonymousClientSettings, Wallet.WalletOptions paramAnonymousWalletOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      o.b(paramAnonymousContext instanceof Activity, "An Activity must be used for Wallet APIs");
      paramAnonymousContext = (Activity)paramAnonymousContext;
      if (paramAnonymousWalletOptions != null) {}
      for (;;)
      {
        return new oy(paramAnonymousContext, paramAnonymousLooper, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymousWalletOptions.environment, paramAnonymousClientSettings.getAccountName(), paramAnonymousWalletOptions.theme);
        paramAnonymousWalletOptions = new Wallet.WalletOptions(null);
      }
    }
    
    public int getPriority()
    {
      return Integer.MAX_VALUE;
    }
  };
  public static final Payments Payments = new ox();
  public static final r atJ = new pb();
  public static final om atK = new pa();
  
  @Deprecated
  public static void changeMaskedWallet(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2, int paramInt)
  {
    Payments.changeMaskedWallet(paramGoogleApiClient, paramString1, paramString2, paramInt);
  }
  
  @Deprecated
  public static void checkForPreAuthorization(GoogleApiClient paramGoogleApiClient, int paramInt)
  {
    Payments.checkForPreAuthorization(paramGoogleApiClient, paramInt);
  }
  
  @Deprecated
  public static void loadFullWallet(GoogleApiClient paramGoogleApiClient, FullWalletRequest paramFullWalletRequest, int paramInt)
  {
    Payments.loadFullWallet(paramGoogleApiClient, paramFullWalletRequest, paramInt);
  }
  
  @Deprecated
  public static void loadMaskedWallet(GoogleApiClient paramGoogleApiClient, MaskedWalletRequest paramMaskedWalletRequest, int paramInt)
  {
    Payments.loadMaskedWallet(paramGoogleApiClient, paramMaskedWalletRequest, paramInt);
  }
  
  @Deprecated
  public static void notifyTransactionStatus(GoogleApiClient paramGoogleApiClient, NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    Payments.notifyTransactionStatus(paramGoogleApiClient, paramNotifyTransactionStatusRequest);
  }
  
  public static final class WalletOptions
    implements Api.ApiOptions.HasOptions
  {
    public final int environment;
    public final int theme;
    
    private WalletOptions()
    {
      this(new Builder());
    }
    
    private WalletOptions(Builder paramBuilder)
    {
      this.environment = Builder.a(paramBuilder);
      this.theme = Builder.b(paramBuilder);
    }
    
    public static final class Builder
    {
      private int atL = 0;
      private int mTheme = 0;
      
      public Wallet.WalletOptions build()
      {
        return new Wallet.WalletOptions(this, null);
      }
      
      public Builder setEnvironment(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 2) || (paramInt == 1))
        {
          this.atL = paramInt;
          return this;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", new Object[] { Integer.valueOf(paramInt) }));
      }
      
      public Builder setTheme(int paramInt)
      {
        if ((paramInt == 0) || (paramInt == 1))
        {
          this.mTheme = paramInt;
          return this;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", new Object[] { Integer.valueOf(paramInt) }));
      }
    }
  }
  
  public static abstract class a<R extends Result>
    extends BaseImplementation.a<R, oy>
  {
    public a()
    {
      super();
    }
  }
  
  public static abstract class b
    extends Wallet.a<Status>
  {
    protected Status d(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/Wallet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */