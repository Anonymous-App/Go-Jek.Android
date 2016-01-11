package com.google.android.gms.analytics;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

abstract class j<T extends i>
{
  Context mContext;
  a<T> xV;
  
  public j(Context paramContext, a<T> parama)
  {
    this.mContext = paramContext;
    this.xV = parama;
  }
  
  private T a(XmlResourceParser paramXmlResourceParser)
  {
    for (;;)
    {
      try
      {
        paramXmlResourceParser.next();
        i = paramXmlResourceParser.getEventType();
        if (i == 1) {
          continue;
        }
        if (paramXmlResourceParser.getEventType() == 2)
        {
          str1 = paramXmlResourceParser.getName().toLowerCase();
          if (!str1.equals("screenname")) {
            continue;
          }
          str1 = paramXmlResourceParser.getAttributeValue(null, "name");
          str2 = paramXmlResourceParser.nextText().trim();
          if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2))) {
            this.xV.f(str1, str2);
          }
        }
      }
      catch (XmlPullParserException paramXmlResourceParser)
      {
        z.T("Error parsing tracker configuration file: " + paramXmlResourceParser);
        return this.xV.dW();
        if (!str1.equals("bool")) {
          continue;
        }
        String str2 = paramXmlResourceParser.getAttributeValue(null, "name");
        str1 = paramXmlResourceParser.nextText().trim();
        if (TextUtils.isEmpty(str2)) {
          continue;
        }
        bool = TextUtils.isEmpty(str1);
        if (bool) {
          continue;
        }
        try
        {
          bool = Boolean.parseBoolean(str1);
          this.xV.d(str2, bool);
        }
        catch (NumberFormatException localNumberFormatException1)
        {
          z.T("Error parsing bool configuration value: " + str1);
        }
        continue;
      }
      catch (IOException paramXmlResourceParser)
      {
        int i;
        z.T("Error parsing tracker configuration file: " + paramXmlResourceParser);
        continue;
        if (!str1.equals("integer")) {
          continue;
        }
        String str3 = paramXmlResourceParser.getAttributeValue(null, "name");
        String str1 = paramXmlResourceParser.nextText().trim();
        if (TextUtils.isEmpty(str3)) {
          continue;
        }
        boolean bool = TextUtils.isEmpty(str1);
        if (bool) {
          continue;
        }
        try
        {
          i = Integer.parseInt(str1);
          this.xV.c(str3, i);
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          z.T("Error parsing int configuration value: " + str1);
        }
        continue;
      }
      i = paramXmlResourceParser.next();
      continue;
      if (!str1.equals("string")) {
        continue;
      }
      str1 = paramXmlResourceParser.getAttributeValue(null, "name");
      str2 = paramXmlResourceParser.nextText().trim();
      if ((!TextUtils.isEmpty(str1)) && (str2 != null)) {
        this.xV.g(str1, str2);
      }
    }
  }
  
  public T w(int paramInt)
  {
    try
    {
      i locali = a(this.mContext.getResources().getXml(paramInt));
      return locali;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      z.W("inflate() called with unknown resourceId: " + localNotFoundException);
    }
    return null;
  }
  
  public static abstract interface a<U extends i>
  {
    public abstract void c(String paramString, int paramInt);
    
    public abstract void d(String paramString, boolean paramBoolean);
    
    public abstract U dW();
    
    public abstract void f(String paramString1, String paramString2);
    
    public abstract void g(String paramString1, String paramString2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */