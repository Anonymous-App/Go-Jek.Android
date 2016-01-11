package com.google.android.gms.analytics;

import java.util.SortedSet;
import java.util.TreeSet;

class t
{
  private static final t ze = new t();
  private SortedSet<a> zb = new TreeSet();
  private StringBuilder zc = new StringBuilder();
  private boolean zd = false;
  
  public static t ep()
  {
    return ze;
  }
  
  public void B(boolean paramBoolean)
  {
    try
    {
      this.zd = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(a parama)
  {
    try
    {
      if (!this.zd)
      {
        this.zb.add(parama);
        this.zc.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(parama.ordinal()));
      }
      return;
    }
    finally
    {
      parama = finally;
      throw parama;
    }
  }
  
  public String eq()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      int j = 6;
      int i = 0;
      while (this.zb.size() > 0)
      {
        a locala = (a)this.zb.first();
        this.zb.remove(locala);
        int k = locala.ordinal();
        while (k >= j)
        {
          ((StringBuilder)localObject1).append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
          j += 6;
          i = 0;
        }
        i += (1 << locala.ordinal() % 6);
      }
      if ((i > 0) || (((StringBuilder)localObject1).length() == 0)) {
        ((StringBuilder)localObject1).append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
      }
      this.zb.clear();
      localObject1 = ((StringBuilder)localObject1).toString();
      return (String)localObject1;
    }
    finally {}
  }
  
  public String er()
  {
    try
    {
      if (this.zc.length() > 0) {
        this.zc.insert(0, ".");
      }
      String str = this.zc.toString();
      this.zc = new StringBuilder();
      return str;
    }
    finally {}
  }
  
  public static enum a
  {
    static
    {
      zA = new a("BLANK_21", 21);
      zB = new a("BLANK_22", 22);
      zC = new a("BLANK_23", 23);
      zD = new a("BLANK_24", 24);
      zE = new a("BLANK_25", 25);
      zF = new a("BLANK_26", 26);
      zG = new a("BLANK_27", 27);
      zH = new a("BLANK_28", 28);
      zI = new a("BLANK_29", 29);
      zJ = new a("SET_EXCEPTION_PARSER", 30);
      zK = new a("GET_EXCEPTION_PARSER", 31);
      zL = new a("CONSTRUCT_TRANSACTION", 32);
      zM = new a("CONSTRUCT_EXCEPTION", 33);
      zN = new a("CONSTRUCT_RAW_EXCEPTION", 34);
      zO = new a("CONSTRUCT_TIMING", 35);
      zP = new a("CONSTRUCT_SOCIAL", 36);
      zQ = new a("BLANK_37", 37);
      zR = new a("BLANK_38", 38);
      zS = new a("GET_TRACKER", 39);
      zT = new a("GET_DEFAULT_TRACKER", 40);
      zU = new a("SET_DEFAULT_TRACKER", 41);
      zV = new a("SET_APP_OPT_OUT", 42);
      zW = new a("GET_APP_OPT_OUT", 43);
      zX = new a("DISPATCH", 44);
      zY = new a("SET_DISPATCH_PERIOD", 45);
      zZ = new a("BLANK_46", 46);
    }
    
    private a() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */