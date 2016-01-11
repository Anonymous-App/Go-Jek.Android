package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.o;

public abstract class d
{
  protected final DataHolder II;
  protected int JX;
  private int JY;
  
  public d(DataHolder paramDataHolder, int paramInt)
  {
    this.II = ((DataHolder)o.i(paramDataHolder));
    ap(paramInt);
  }
  
  protected void a(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    this.II.a(paramString, this.JX, this.JY, paramCharArrayBuffer);
  }
  
  public boolean aQ(String paramString)
  {
    return this.II.aQ(paramString);
  }
  
  protected Uri aR(String paramString)
  {
    return this.II.g(paramString, this.JX, this.JY);
  }
  
  protected boolean aS(String paramString)
  {
    return this.II.h(paramString, this.JX, this.JY);
  }
  
  protected void ap(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.II.getCount())) {}
    for (boolean bool = true;; bool = false)
    {
      o.I(bool);
      this.JX = paramInt;
      this.JY = this.II.ar(this.JX);
      return;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof d))
    {
      paramObject = (d)paramObject;
      bool1 = bool2;
      if (n.equal(Integer.valueOf(((d)paramObject).JX), Integer.valueOf(this.JX)))
      {
        bool1 = bool2;
        if (n.equal(Integer.valueOf(((d)paramObject).JY), Integer.valueOf(this.JY)))
        {
          bool1 = bool2;
          if (((d)paramObject).II == this.II) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  protected boolean getBoolean(String paramString)
  {
    return this.II.d(paramString, this.JX, this.JY);
  }
  
  protected byte[] getByteArray(String paramString)
  {
    return this.II.f(paramString, this.JX, this.JY);
  }
  
  protected float getFloat(String paramString)
  {
    return this.II.e(paramString, this.JX, this.JY);
  }
  
  protected int getInteger(String paramString)
  {
    return this.II.b(paramString, this.JX, this.JY);
  }
  
  protected long getLong(String paramString)
  {
    return this.II.a(paramString, this.JX, this.JY);
  }
  
  protected String getString(String paramString)
  {
    return this.II.c(paramString, this.JX, this.JY);
  }
  
  protected int gz()
  {
    return this.JX;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Integer.valueOf(this.JX), Integer.valueOf(this.JY), this.II });
  }
  
  public boolean isDataValid()
  {
    return !this.II.isClosed();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/data/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */