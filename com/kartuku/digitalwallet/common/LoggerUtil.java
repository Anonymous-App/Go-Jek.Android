package com.kartuku.digitalwallet.common;

import com.kartuku.digitalwallet.common.client.util.CreditCardUtil;
import org.springframework.stereotype.Service;

@Service
public class LoggerUtil
{
  public static String mask(String paramString)
  {
    return new CreditCardUtil().mask(paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/LoggerUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */