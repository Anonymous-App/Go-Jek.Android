package com.kartuku.digitalwallet.common;

public abstract interface LoggerMessage
{
  public static final String APPS_AUTH_FAIL = "Apps authenticated failed";
  public static final String APPS_AUTH_SUCCESS = "Apps authenticated successfully";
  public static final String IAM_START_FAIL = "\n\nCan NOT connect to Authentication Service, pls check user & password apps\n\n";
  public static final String IPG_OTT_FAIL = "IPG OTT request failed";
  public static final String IPG_OTT_SUCCESS = "IPG OTT request success";
  public static final String IPG_PURCHASE_FAIL = "IPG Purchase request failed";
  public static final String IPG_PURCHASE_SUCCESS = "IPG Purchase request success";
  public static final String IPG_TOKEN_STORE_SUCCESS = "IPG Token Store request success";
  public static final String PREPAID_START_FAIL = "\n\nCan NOT connect to Prepaid Service, pls check user & password apps\n\n";
  public static final String REST_FAIL = "\n\nRequest failed to url : ";
  public static final String START_FAIL = "\n\nCan NOT connect : ";
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/LoggerMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */