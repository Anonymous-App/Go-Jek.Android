package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class dm
{
  private static bz<d.a> a(bz<d.a> parambz)
  {
    try
    {
      bz localbz = new bz(di.u(de(di.j((d.a)parambz.getObject()))), parambz.oG());
      return localbz;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      bh.b("Escape URI: unsupported encoding", localUnsupportedEncodingException);
    }
    return parambz;
  }
  
  private static bz<d.a> a(bz<d.a> parambz, int paramInt)
  {
    if (!q((d.a)parambz.getObject()))
    {
      bh.T("Escaping can only be applied to strings.");
      return parambz;
    }
    switch (paramInt)
    {
    default: 
      bh.T("Unsupported Value Escaping: " + paramInt);
      return parambz;
    }
    return a(parambz);
  }
  
  static bz<d.a> a(bz<d.a> parambz, int... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      parambz = a(parambz, paramVarArgs[i]);
      i += 1;
    }
    return parambz;
  }
  
  static String de(String paramString)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(paramString, "UTF-8").replaceAll("\\+", "%20");
  }
  
  private static boolean q(d.a parama)
  {
    return di.o(parama) instanceof String;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/dm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */