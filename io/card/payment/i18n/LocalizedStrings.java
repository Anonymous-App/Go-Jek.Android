package io.card.payment.i18n;

import android.content.Intent;
import io.card.payment.i18n.locales.LocalizedStringsList;

public final class LocalizedStrings
{
  private static final I18nManager<StringKey> i18nManager = new I18nManager(StringKey.class, LocalizedStringsList.ALL_LOCALES);
  
  public static String getString(StringKey paramStringKey)
  {
    return i18nManager.getString(paramStringKey);
  }
  
  public static String getString(StringKey paramStringKey, String paramString)
  {
    return i18nManager.getString(paramStringKey, i18nManager.getLocaleFromSpecifier(paramString));
  }
  
  public static void setLanguage(Intent paramIntent)
  {
    i18nManager.setLanguage(paramIntent.getStringExtra("io.card.payment.languageOrLocale"));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/i18n/LocalizedStrings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */