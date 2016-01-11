package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class ce
{
  private static ce aqd;
  private volatile String aoc;
  private volatile a aqe;
  private volatile String aqf;
  private volatile String aqg;
  
  ce()
  {
    clear();
  }
  
  private String cI(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }
  
  private String j(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }
  
  static ce oJ()
  {
    try
    {
      if (aqd == null) {
        aqd = new ce();
      }
      ce localce = aqd;
      return localce;
    }
    finally {}
  }
  
  void clear()
  {
    this.aqe = a.aqh;
    this.aqf = null;
    this.aoc = null;
    this.aqg = null;
  }
  
  String getContainerId()
  {
    return this.aoc;
  }
  
  boolean i(Uri paramUri)
  {
    boolean bool = true;
    String str;
    try
    {
      str = URLDecoder.decode(paramUri.toString(), "UTF-8");
      if (!str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
        break label153;
      }
      bh.V("Container preview url: " + str);
      if (!str.matches(".*?&gtm_debug=x$")) {
        break label138;
      }
      this.aqe = a.aqj;
    }
    catch (UnsupportedEncodingException paramUri)
    {
      for (;;)
      {
        bool = false;
        continue;
        this.aqe = a.aqi;
      }
    }
    finally {}
    this.aqg = j(paramUri);
    if ((this.aqe == a.aqi) || (this.aqe == a.aqj)) {
      this.aqf = ("/r?" + this.aqg);
    }
    this.aoc = cI(this.aqg);
    for (;;)
    {
      return bool;
      label138:
      label153:
      if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (cI(paramUri.getQuery()).equals(this.aoc))
        {
          bh.V("Exit preview mode for container: " + this.aoc);
          this.aqe = a.aqh;
          this.aqf = null;
        }
      }
      else
      {
        bh.W("Invalid preview uri: " + str);
        bool = false;
        continue;
      }
      bool = false;
    }
  }
  
  a oK()
  {
    return this.aqe;
  }
  
  String oL()
  {
    return this.aqf;
  }
  
  static enum a
  {
    private a() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */