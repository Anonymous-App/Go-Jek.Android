package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;

@ez
public class ak
{
  private final Object mw = new Object();
  private final int nf;
  private final int ng;
  private final int nh;
  private final ap ni;
  private ArrayList<String> nj = new ArrayList();
  private int nk = 0;
  private int nl = 0;
  private int nm = 0;
  private int nn;
  private String no = "";
  
  public ak(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.nf = paramInt1;
    this.ng = paramInt2;
    this.nh = paramInt3;
    this.ni = new ap(paramInt4);
  }
  
  private String a(ArrayList<String> paramArrayList, int paramInt)
  {
    if (paramArrayList.isEmpty()) {
      paramArrayList = "";
    }
    Object localObject;
    do
    {
      return paramArrayList;
      localObject = new StringBuffer();
      paramArrayList = paramArrayList.iterator();
      do
      {
        if (!paramArrayList.hasNext()) {
          break;
        }
        ((StringBuffer)localObject).append((String)paramArrayList.next());
        ((StringBuffer)localObject).append(' ');
      } while (((StringBuffer)localObject).length() <= paramInt);
      ((StringBuffer)localObject).deleteCharAt(((StringBuffer)localObject).length() - 1);
      localObject = ((StringBuffer)localObject).toString();
      paramArrayList = (ArrayList<String>)localObject;
    } while (((String)localObject).length() < paramInt);
    return ((String)localObject).substring(0, paramInt);
  }
  
  private void j(String paramString)
  {
    if ((paramString == null) || (paramString.length() < this.nh)) {
      return;
    }
    synchronized (this.mw)
    {
      this.nj.add(paramString);
      this.nk += paramString.length();
      return;
    }
  }
  
  int a(int paramInt1, int paramInt2)
  {
    return this.nf * paramInt1 + this.ng * paramInt2;
  }
  
  public boolean aN()
  {
    for (;;)
    {
      synchronized (this.mw)
      {
        if (this.nm == 0)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public String aO()
  {
    return this.no;
  }
  
  public void aP()
  {
    synchronized (this.mw)
    {
      this.nn -= 100;
      return;
    }
  }
  
  public void aQ()
  {
    synchronized (this.mw)
    {
      this.nm -= 1;
      return;
    }
  }
  
  public void aR()
  {
    synchronized (this.mw)
    {
      this.nm += 1;
      return;
    }
  }
  
  public void aS()
  {
    synchronized (this.mw)
    {
      int i = a(this.nk, this.nl);
      if (i > this.nn)
      {
        this.nn = i;
        this.no = this.ni.a(this.nj);
      }
      return;
    }
  }
  
  int aT()
  {
    return this.nk;
  }
  
  public void c(int paramInt)
  {
    this.nl = paramInt;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ak)) {}
    do
    {
      return false;
      if (paramObject == this) {
        return true;
      }
      paramObject = (ak)paramObject;
    } while ((((ak)paramObject).aO() == null) || (!((ak)paramObject).aO().equals(aO())));
    return true;
  }
  
  public int getScore()
  {
    return this.nn;
  }
  
  public void h(String arg1)
  {
    j(???);
    synchronized (this.mw)
    {
      if (this.nm < 0) {
        gs.S("ActivityContent: negative number of WebViews.");
      }
      aS();
      return;
    }
  }
  
  public int hashCode()
  {
    return aO().hashCode();
  }
  
  public void i(String paramString)
  {
    j(paramString);
  }
  
  public String toString()
  {
    return "ActivityContent fetchId: " + this.nl + " score:" + this.nn + " total_length:" + this.nk + "\n text: " + a(this.nj, 200) + "\n signture: " + this.no;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */