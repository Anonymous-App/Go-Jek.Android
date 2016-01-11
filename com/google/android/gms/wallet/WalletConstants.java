package com.google.android.gms.wallet;

import android.accounts.Account;

public final class WalletConstants
{
  public static final String ACTION_ENABLE_WALLET_OPTIMIZATION = "com.google.android.gms.wallet.ENABLE_WALLET_OPTIMIZATION";
  public static final int ENVIRONMENT_PRODUCTION = 1;
  public static final int ENVIRONMENT_SANDBOX = 0;
  public static final int ENVIRONMENT_STRICT_SANDBOX = 2;
  public static final int ERROR_CODE_AUTHENTICATION_FAILURE = 411;
  public static final int ERROR_CODE_BUYER_ACCOUNT_ERROR = 409;
  public static final int ERROR_CODE_INVALID_PARAMETERS = 404;
  public static final int ERROR_CODE_INVALID_TRANSACTION = 410;
  public static final int ERROR_CODE_MERCHANT_ACCOUNT_ERROR = 405;
  public static final int ERROR_CODE_SERVICE_UNAVAILABLE = 402;
  public static final int ERROR_CODE_SPENDING_LIMIT_EXCEEDED = 406;
  public static final int ERROR_CODE_UNKNOWN = 413;
  public static final int ERROR_CODE_UNSUPPORTED_API_VERSION = 412;
  public static final String EXTRA_ERROR_CODE = "com.google.android.gms.wallet.EXTRA_ERROR_CODE";
  public static final String EXTRA_FULL_WALLET = "com.google.android.gms.wallet.EXTRA_FULL_WALLET";
  public static final String EXTRA_IS_USER_PREAUTHORIZED = "com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED";
  public static final String EXTRA_MASKED_WALLET = "com.google.android.gms.wallet.EXTRA_MASKED_WALLET";
  public static final String EXTRA_MASKED_WALLET_REQUEST = "com.google.android.gms.wallet.EXTRA_MASKED_WALLET_REQUEST";
  public static final int RESULT_ERROR = 1;
  public static final int THEME_DARK = 0;
  @Deprecated
  public static final int THEME_HOLO_DARK = 0;
  @Deprecated
  public static final int THEME_HOLO_LIGHT = 1;
  public static final int THEME_LIGHT = 1;
  public static final Account atM = new Account("ACCOUNT_NO_WALLET", "com.google");
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wallet/WalletConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */