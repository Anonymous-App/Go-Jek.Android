package com.google.android.gms.internal;

import java.io.IOException;

public abstract interface ll
{
  public static final class a
    extends ph<a>
  {
    public a[] adE;
    
    public a()
    {
      lP();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if ((this.adE != null) && (this.adE.length > 0))
      {
        int i = 0;
        while (i < this.adE.length)
        {
          a locala = this.adE[i];
          if (locala != null) {
            parampg.a(1, locala);
          }
          i += 1;
        }
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int i = super.c();
      int k = i;
      if (this.adE != null)
      {
        k = i;
        if (this.adE.length > 0)
        {
          int j = 0;
          for (;;)
          {
            k = i;
            if (j >= this.adE.length) {
              break;
            }
            a locala = this.adE[j];
            k = i;
            if (locala != null) {
              k = i + pg.c(1, locala);
            }
            j += 1;
            i = k;
          }
        }
      }
      return k;
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
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof a));
        paramObject = (a)paramObject;
        bool1 = bool2;
      } while (!pl.equals(this.adE, ((a)paramObject).adE));
      return a((ph)paramObject);
    }
    
    public int hashCode()
    {
      return (pl.hashCode(this.adE) + 527) * 31 + qz();
    }
    
    public a lP()
    {
      this.adE = a.lQ();
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public a n(pf parampf)
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
        case 10: 
          int j = pq.b(parampf, 10);
          if (this.adE == null) {}
          a[] arrayOfa;
          for (i = 0;; i = this.adE.length)
          {
            arrayOfa = new a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.adE, 0, arrayOfa, 0, i);
              j = i;
            }
            while (j < arrayOfa.length - 1)
            {
              arrayOfa[j] = new a();
              parampf.a(arrayOfa[j]);
              parampf.qi();
              j += 1;
            }
          }
          arrayOfa[j] = new a();
          parampf.a(arrayOfa[j]);
          this.adE = arrayOfa;
        }
      }
    }
    
    public static final class a
      extends ph<a>
    {
      private static volatile a[] adF;
      public String adG;
      public String adH;
      public int viewId;
      
      public a()
      {
        lR();
      }
      
      public static a[] lQ()
      {
        if (adF == null) {}
        synchronized (pl.awT)
        {
          if (adF == null) {
            adF = new a[0];
          }
          return adF;
        }
      }
      
      public void a(pg parampg)
        throws IOException
      {
        if (!this.adG.equals("")) {
          parampg.b(1, this.adG);
        }
        if (!this.adH.equals("")) {
          parampg.b(2, this.adH);
        }
        if (this.viewId != 0) {
          parampg.s(3, this.viewId);
        }
        super.a(parampg);
      }
      
      protected int c()
      {
        int j = super.c();
        int i = j;
        if (!this.adG.equals("")) {
          i = j + pg.j(1, this.adG);
        }
        j = i;
        if (!this.adH.equals("")) {
          j = i + pg.j(2, this.adH);
        }
        i = j;
        if (this.viewId != 0) {
          i = j + pg.u(3, this.viewId);
        }
        return i;
      }
      
      public boolean equals(Object paramObject)
      {
        boolean bool2 = false;
        boolean bool1;
        if (paramObject == this) {
          bool1 = true;
        }
        label41:
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof a));
            paramObject = (a)paramObject;
            if (this.adG != null) {
              break;
            }
            bool1 = bool2;
          } while (((a)paramObject).adG != null);
          if (this.adH != null) {
            break label92;
          }
          bool1 = bool2;
        } while (((a)paramObject).adH != null);
        label92:
        while (this.adH.equals(((a)paramObject).adH))
        {
          bool1 = bool2;
          if (this.viewId != ((a)paramObject).viewId) {
            break;
          }
          return a((ph)paramObject);
          if (this.adG.equals(((a)paramObject).adG)) {
            break label41;
          }
          return false;
        }
        return false;
      }
      
      public int hashCode()
      {
        int j = 0;
        int i;
        if (this.adG == null)
        {
          i = 0;
          if (this.adH != null) {
            break label56;
          }
        }
        for (;;)
        {
          return (((i + 527) * 31 + j) * 31 + this.viewId) * 31 + qz();
          i = this.adG.hashCode();
          break;
          label56:
          j = this.adH.hashCode();
        }
      }
      
      public a lR()
      {
        this.adG = "";
        this.adH = "";
        this.viewId = 0;
        this.awJ = null;
        this.awU = -1;
        return this;
      }
      
      public a o(pf parampf)
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
          case 10: 
            this.adG = parampf.readString();
            break;
          case 18: 
            this.adH = parampf.readString();
            break;
          case 24: 
            this.viewId = parampf.ql();
          }
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */