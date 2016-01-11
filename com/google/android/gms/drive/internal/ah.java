package com.google.android.gms.drive.internal;

import com.google.android.gms.internal.pf;
import com.google.android.gms.internal.pg;
import com.google.android.gms.internal.ph;
import com.google.android.gms.internal.pm;
import com.google.android.gms.internal.pn;
import java.io.IOException;

public final class ah
  extends ph<ah>
{
  public String Pl;
  public long Pm;
  public long Pn;
  public int versionCode;
  
  public ah()
  {
    ic();
  }
  
  public static ah g(byte[] paramArrayOfByte)
    throws pm
  {
    return (ah)pn.a(new ah(), paramArrayOfByte);
  }
  
  public void a(pg parampg)
    throws IOException
  {
    parampg.s(1, this.versionCode);
    parampg.b(2, this.Pl);
    parampg.c(3, this.Pm);
    parampg.c(4, this.Pn);
    super.a(parampg);
  }
  
  protected int c()
  {
    return super.c() + pg.u(1, this.versionCode) + pg.j(2, this.Pl) + pg.e(3, this.Pm) + pg.e(4, this.Pn);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof ah));
        paramObject = (ah)paramObject;
        bool1 = bool2;
      } while (this.versionCode != ((ah)paramObject).versionCode);
      if (this.Pl != null) {
        break;
      }
      bool1 = bool2;
    } while (((ah)paramObject).Pl != null);
    while (this.Pl.equals(((ah)paramObject).Pl))
    {
      bool1 = bool2;
      if (this.Pm != ((ah)paramObject).Pm) {
        break;
      }
      bool1 = bool2;
      if (this.Pn != ((ah)paramObject).Pn) {
        break;
      }
      return a((ph)paramObject);
    }
    return false;
  }
  
  public int hashCode()
  {
    int j = this.versionCode;
    if (this.Pl == null) {}
    for (int i = 0;; i = this.Pl.hashCode()) {
      return (((i + (j + 527) * 31) * 31 + (int)(this.Pm ^ this.Pm >>> 32)) * 31 + (int)(this.Pn ^ this.Pn >>> 32)) * 31 + qz();
    }
  }
  
  public ah ic()
  {
    this.versionCode = 1;
    this.Pl = "";
    this.Pm = -1L;
    this.Pn = -1L;
    this.awJ = null;
    this.awU = -1;
    return this;
  }
  
  public ah m(pf parampf)
    throws IOException
  {
    for (;;)
    {
      int i = parampf.qi();
      switch (i)
      {
      default: 
        if (a(parampf, i)) {}
        break;
      case 0: 
        return this;
      case 8: 
        this.versionCode = parampf.ql();
        break;
      case 18: 
        this.Pl = parampf.readString();
        break;
      case 24: 
        this.Pm = parampf.qo();
        break;
      case 32: 
        this.Pn = parampf.qo();
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */