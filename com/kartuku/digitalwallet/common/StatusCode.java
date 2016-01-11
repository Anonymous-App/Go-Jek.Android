package com.kartuku.digitalwallet.common;

public enum StatusCode
{
  private final String ˊ;
  private final String ˋ;
  
  static
  {
    FAILED = new StatusCode("FAILED", 1, "400", "Failed");
    FAILED_TOP_UP = new StatusCode("FAILED_TOP_UP", 2, "001", "Failed to Top Up Card");
    FAILED_TOP_UP_CONFIRM = new StatusCode("FAILED_TOP_UP_CONFIRM", 3, "002", "Failed to Confirm Top Up Card");
    FAILED_GET_CARD_DETAIL = new StatusCode("FAILED_GET_CARD_DETAIL", 4, "003", "Failed to Get Card Detail");
    FAILED_GET_CARD_TRANSACTION_HISTORY = new StatusCode("FAILED_GET_CARD_TRANSACTION_HISTORY", 5, "04", "Failed to Get Card Transaction History");
    FAILED_GET_CARD_BALANCE = new StatusCode("FAILED_GET_CARD_BALANCE", 6, "005", "Failed to Get Card Balance");
    FAILED_TOP_UP_CANCEL = new StatusCode("FAILED_TOP_UP_CANCEL", 7, "006", "Failed to Cancel Topup");
    FAILED_BI_TRANSFER = new StatusCode("FAILED_BI_TRANSFER", 8, "007", "Failed to Transfer");
    FAILED_AUTH_LOGIN = new StatusCode("FAILED_AUTH_LOGIN", 9, "101", "Failed to Login");
    FAILED_USER_SESSION_AVAILABLE = new StatusCode("FAILED_USER_SESSION_AVAILABLE", 10, "102", "User Session Unavailable");
    FAILED_LOGIN_OTHER_REST = new StatusCode("FAILED_LOGIN_OTHER_REST", 11, "103", "Failed to Login Other Rest");
    FAILED_RELOGIN_SERVER = new StatusCode("FAILED_RELOGIN_SERVER", 12, "104", "Failed to Relogin to Server");
    FAILED_AUTH_LOGIN_APPS = new StatusCode("FAILED_AUTH_LOGIN_APPS", 13, "105", "Failed to Login Apps");
    FAILED_AUTH_LOGOUT_USER = new StatusCode("FAILED_AUTH_LOGOUT_USER", 14, "106", "Failed to Logout");
    FAILED_GET_CARD_LIST = new StatusCode("FAILED_GET_CARD_LIST", 15, "201", "Failed to Get Card List");
    FAILED_LINK_CARD = new StatusCode("FAILED_LINK_CARD", 16, "202", "Failed to Link Card");
    FAILED_BLOCK_CARD = new StatusCode("FAILED_BLOCK_CARD", 17, "203", "Failed to Block Card");
    FAILED_IPG_OTT_PURCHASE = new StatusCode("FAILED_IPG_OTT_PURCHASE", 18, "301", "Failed to OTT and Purchase");
    FAILED_IPG_OTT = new StatusCode("FAILED_IPG_OTT", 19, "302", "Failed to Create OTT");
    FAILED_IPG_TOKEN_STORE = new StatusCode("FAILED_IPG_TOKEN_STORE", 20, "303", "Failed to Store Card Token");
    FAILED_IPG_TOKEN_LIST = new StatusCode("FAILED_IPG_TOKEN_LIST", 21, "304", "Failed to List Card Token");
    FAILED_IPG_TOKEN_REMOVE = new StatusCode("FAILED_IPG_TOKEN_REMOVE", 22, "305", "Failed to Remove Card Token");
    FAILED_IPG_AUTHORIZE = new StatusCode("FAILED_IPG_AUTHORIZE", 23, "306", "Failed to Process Authorize Payment");
    FAILED_IPG_CAPTURE = new StatusCode("FAILED_IPG_CAPTURE", 24, "307", "Failed to Process Capture Payment");
    FAILED_IPG_VOID_CAPTURE = new StatusCode("FAILED_IPG_VOID_CAPTURE", 25, "308", "Failed to Void Capture");
    FAILED_IPG_PURCHASE = new StatusCode("FAILED_IPG_PURCHASE", 26, "309", "Failed to Process Payment");
    FAILED_IPG_VOID_PURCHASE = new StatusCode("FAILED_IPG_VOID_PURCHASE", 27, "310", "Failed to Void Payment");
    FAILED_IPG_REFUND = new StatusCode("FAILED_IPG_REFUND", 28, "311", "Failed to Refund");
    FAILED_IPG_QUERY = new StatusCode("FAILED_IPG_QUERY", 29, "312", "Failed to Query Transaction");
    FAILED_IPG_ISSUE_REWARD = new StatusCode("FAILED_IPG_ISSUE_REWARD", 30, "312", "Failed to Issue Reward");
    FAILED_IPG_CANCEL_REWARD = new StatusCode("FAILED_IPG_CANCEL_REWARD", 31, "312", "Failed to Cancel Reward");
    FAILED_IPG_AUTH_CAPTURE = new StatusCode("FAILED_IPG_AUTH_CAPTURE", 32, "313", "Failed to Authorize and Capture");
    FAILED_TOKENIZE = new StatusCode("FAILED_TOKENIZE", 33, "401", "Failed to Tokenize Pan");
    FAILED_RETRIEVE = new StatusCode("FAILED_RETRIEVE", 34, "402", "Failed to Retrieve Pan");
    FAILED_DELETE_TOKEN = new StatusCode("FAILED_DELETE_TOKEN", 35, "403", "Failed to Delete Token");
    FAILED_BILLPAY_PRODUCT_LIST = new StatusCode("FAILED_BILLPAY_PRODUCT_LIST", 36, "461", "Failed to get Bill-Payment Product LIST");
    FAILED_BILLPAY_PRICE_LIST = new StatusCode("FAILED_BILLPAY_PRICE_LIST", 37, "462", "Failed to get List of Bill-Payment Product PRICE");
    FAILED_BILLPAY_PRODUCT_PURCHASE = new StatusCode("FAILED_BILLPAY_PRODUCT_PURCHASE", 38, "463", "Failed to get Bill-Payment Product PURCHASE");
    FAILED_BILLPAY_PRODUCT_ADVICE = new StatusCode("FAILED_BILLPAY_PRODUCT_ADVICE", 39, "464", "Failed to get List of Bill-Payment Product ADVICE");
    FAILED_CREDIT_PAYMENT = new StatusCode("FAILED_CREDIT_PAYMENT", 40, "501", "Failed to Process Credit Payment");
    FAILED_CREDITCARD_PAYMENT = new StatusCode("FAILED_CREDITCARD_PAYMENT", 41, "502", "Failed to Process Creditcard Payment");
    FAILED_USE_COUPON = new StatusCode("FAILED_USE_COUPON", 42, "503", "Failed to Process Coupon");
    FAILED_POINT_PAYMENT = new StatusCode("FAILED_POINT_PAYMENT", 43, "504", "Failed to Process Point Payment");
    FAILED_ADD_USER = new StatusCode("FAILED_ADD_USER", 44, "601", "Failed to Add User");
    FAILED_EDIT_USER = new StatusCode("FAILED_EDIT_USER", 45, "602", "Failed to Edit User");
    FAILED_DELETE_USER = new StatusCode("FAILED_DELETE_USER", 46, "603", "Failed to Delete User");
    FAILED_FIND_USER = new StatusCode("FAILED_FIND_USER", 47, "604", "Failed to Find User");
    FAILED_LIST_USER = new StatusCode("FAILED_LIST_USER", 48, "605", "Failed to Retrieve List of User");
    FAILED_AUTO_LOGIN = new StatusCode("FAILED_AUTO_LOGIN", 49, "606", "Failed to Auto Login");
    FAILED_CHANGE_PASSWORD = new StatusCode("FAILED_CHANGE_PASSWORD", 50, "607", "Failed to Change Password");
    FAILED_FORGOT_PASSWORD = new StatusCode("FAILED_FORGOT_PASSWORD", 51, "608", "Failed to Process Forgot Password");
    FAILED_SEND_EMAIL = new StatusCode("FAILED_SEND_EMAIL", 52, "609", "Failed to Send Email");
    FAILED_LOYALTY_INQUIRY = new StatusCode("FAILED_LOYALTY_INQUIRY", 53, "701", "Failed to Get Loyalty Inquiry");
    FAILED_LOYALTY_REDEEM = new StatusCode("FAILED_LOYALTY_REDEEM", 54, "702", "Failed to Redeem Loyalty");
    FAILED_LOYALTY_CANCEL = new StatusCode("FAILED_LOYALTY_CANCEL", 55, "703", "Failed to Cancel Loyalty");
    FAILED_LOYALTY_REVERSE = new StatusCode("FAILED_LOYALTY_REVERSE", 56, "704", "Failed to Reverse Loyalty");
    FAILED_ADD_DEVICE_IMPRINT = new StatusCode("FAILED_ADD_DEVICE_IMPRINT", 57, "801", "Failed to Add User Device Imprints");
    FAILED_EDIT_DEVICE_IMPRINT = new StatusCode("FAILED_EDIT_DEVICE_IMPRINT", 58, "802", "Failed to Edit User Device Imprints");
    FAILED_DELETE_DEVICE_IMPRINT = new StatusCode("FAILED_DELETE_DEVICE_IMPRINT", 59, "803", "Failed to Delete User Device Imprints");
    FAILED_CHECK_DEVICE_IMPRINT = new StatusCode("FAILED_CHECK_DEVICE_IMPRINT", 60, "804", "Failed to Cehck User Device Imprints");
    FAILED_PAYMENT = new StatusCode("FAILED_PAYMENT", 61, "900", "Payment Failed");
    FAILED_CONFIRM_PAYMENT = new StatusCode("FAILED_CONFIRM_PAYMENT", 62, "901", "Confirm Payment Failed");
    FAILED_VOID_PAYMENT = new StatusCode("FAILED_VOID_PAYMENT", 63, "902", "Void Payment Failed");
  }
  
  private StatusCode(String paramString1, String paramString2)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
  }
  
  public final String getCode()
  {
    return this.ˊ;
  }
  
  public final String getMessage()
  {
    return this.ˋ;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/StatusCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */