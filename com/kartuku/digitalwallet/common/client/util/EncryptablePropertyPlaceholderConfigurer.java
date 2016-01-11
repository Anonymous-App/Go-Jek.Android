package com.kartuku.digitalwallet.common.client.util;

import com.kartuku.helper.JasyptHelper;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptablePropertyPlaceholderConfigurer
  extends PropertyPlaceholderConfigurer
{
  protected String convertPropertyValue(String paramString)
  {
    if (!PropertyValueEncryptionUtils.isEncryptedValue(paramString)) {
      return paramString;
    }
    return PropertyValueEncryptionUtils.decrypt(paramString, JasyptHelper.getStringEncryptor());
  }
  
  protected String resolveSystemProperty(String paramString)
  {
    return convertPropertyValue(super.resolveSystemProperty(paramString));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/client/util/EncryptablePropertyPlaceholderConfigurer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */