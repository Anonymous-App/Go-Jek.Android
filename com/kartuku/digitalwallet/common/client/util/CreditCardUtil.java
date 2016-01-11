package com.kartuku.digitalwallet.common.client.util;

import org.apache.commons.lang3.StringUtils;

public class CreditCardUtil
{
  private static final Integer ˊ = Integer.valueOf(6);
  
  public String mask(String paramString)
  {
    return StringUtils.overlay(paramString, "****", ˊ.intValue(), ˊ.intValue() + 4);
  }
  
  public String maskingOtherVar(String paramString)
  {
    new StringUtils();
    return StringUtils.overlay(paramString, "****", ˊ.intValue(), ˊ.intValue() + 4);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/client/util/CreditCardUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */