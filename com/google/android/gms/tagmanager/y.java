package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class y
  implements aq
{
  private static y apb;
  private static final Object xz = new Object();
  private cg aos;
  private String apc;
  private String apd;
  private ar ape;
  
  private y(Context paramContext)
  {
    this(as.Z(paramContext), new cw());
  }
  
  y(ar paramar, cg paramcg)
  {
    this.ape = paramar;
    this.aos = paramcg;
  }
  
  public static aq X(Context paramContext)
  {
    synchronized (xz)
    {
      if (apb == null) {
        apb = new y(paramContext);
      }
      paramContext = apb;
      return paramContext;
    }
  }
  
  public boolean cz(String paramString)
  {
    if (!this.aos.eJ())
    {
      bh.W("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
      return false;
    }
    String str = paramString;
    if (this.apc != null)
    {
      str = paramString;
      if (this.apd == null) {}
    }
    try
    {
      str = this.apc + "?" + this.apd + "=" + URLEncoder.encode(paramString, "UTF-8");
      bh.V("Sending wrapped url hit: " + str);
      this.ape.cC(str);
      return true;
    }
    catch (UnsupportedEncodingException paramString)
    {
      bh.d("Error wrapping URL for testing.", paramString);
    }
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */