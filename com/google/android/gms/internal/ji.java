package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class ji
{
  private void a(StringBuilder paramStringBuilder, a parama, Object paramObject)
  {
    if (parama.hd() == 11)
    {
      paramStringBuilder.append(((ji)parama.hn().cast(paramObject)).toString());
      return;
    }
    if (parama.hd() == 7)
    {
      paramStringBuilder.append("\"");
      paramStringBuilder.append(jz.bf((String)paramObject));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  private void a(StringBuilder paramStringBuilder, a parama, ArrayList<Object> paramArrayList)
  {
    paramStringBuilder.append("[");
    int i = 0;
    int j = paramArrayList.size();
    while (i < j)
    {
      if (i > 0) {
        paramStringBuilder.append(",");
      }
      Object localObject = paramArrayList.get(i);
      if (localObject != null) {
        a(paramStringBuilder, parama, localObject);
      }
      i += 1;
    }
    paramStringBuilder.append("]");
  }
  
  protected <O, I> I a(a<I, O> parama, Object paramObject)
  {
    Object localObject = paramObject;
    if (a.c(parama) != null) {
      localObject = parama.convertBack(paramObject);
    }
    return (I)localObject;
  }
  
  protected boolean a(a parama)
  {
    if (parama.he() == 11)
    {
      if (parama.hk()) {
        return bd(parama.hl());
      }
      return bc(parama.hl());
    }
    return bb(parama.hl());
  }
  
  protected Object b(a parama)
  {
    String str = parama.hl();
    if (parama.hn() != null)
    {
      boolean bool;
      if (ba(parama.hl()) == null)
      {
        bool = true;
        o.a(bool, "Concrete field shouldn't be value object: %s", new Object[] { parama.hl() });
        if (!parama.hk()) {
          break label71;
        }
      }
      label71:
      for (parama = hh();; parama = hg())
      {
        if (parama == null) {
          break label79;
        }
        return parama.get(str);
        bool = false;
        break;
      }
      try
      {
        label79:
        parama = "get" + Character.toUpperCase(str.charAt(0)) + str.substring(1);
        parama = getClass().getMethod(parama, new Class[0]).invoke(this, new Object[0]);
        return parama;
      }
      catch (Exception parama)
      {
        throw new RuntimeException(parama);
      }
    }
    return ba(parama.hl());
  }
  
  protected abstract Object ba(String paramString);
  
  protected abstract boolean bb(String paramString);
  
  protected boolean bc(String paramString)
  {
    throw new UnsupportedOperationException("Concrete types not supported");
  }
  
  protected boolean bd(String paramString)
  {
    throw new UnsupportedOperationException("Concrete type arrays not supported");
  }
  
  public abstract HashMap<String, a<?, ?>> hf();
  
  public HashMap<String, Object> hg()
  {
    return null;
  }
  
  public HashMap<String, Object> hh()
  {
    return null;
  }
  
  public String toString()
  {
    HashMap localHashMap = hf();
    StringBuilder localStringBuilder = new StringBuilder(100);
    Iterator localIterator = localHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      a locala = (a)localHashMap.get(str);
      if (a(locala))
      {
        Object localObject = a(locala, b(locala));
        if (localStringBuilder.length() == 0) {
          localStringBuilder.append("{");
        }
        for (;;)
        {
          localStringBuilder.append("\"").append(str).append("\":");
          if (localObject != null) {
            break label135;
          }
          localStringBuilder.append("null");
          break;
          localStringBuilder.append(",");
        }
        label135:
        switch (locala.he())
        {
        default: 
          if (locala.hj()) {
            a(localStringBuilder, locala, (ArrayList)localObject);
          }
          break;
        case 8: 
          localStringBuilder.append("\"").append(js.d((byte[])localObject)).append("\"");
          break;
        case 9: 
          localStringBuilder.append("\"").append(js.e((byte[])localObject)).append("\"");
          break;
        case 10: 
          ka.a(localStringBuilder, (HashMap)localObject);
          continue;
          a(localStringBuilder, locala, localObject);
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      localStringBuilder.append("}");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("{}");
    }
  }
  
  public static class a<I, O>
    implements SafeParcelable
  {
    public static final jk CREATOR = new jk();
    private final int BR;
    protected final int MA;
    protected final boolean MB;
    protected final String MC;
    protected final int MD;
    protected final Class<? extends ji> ME;
    protected final String MF;
    private jm MG;
    private ji.b<I, O> MH;
    protected final int My;
    protected final boolean Mz;
    
    a(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, jd paramjd)
    {
      this.BR = paramInt1;
      this.My = paramInt2;
      this.Mz = paramBoolean1;
      this.MA = paramInt3;
      this.MB = paramBoolean2;
      this.MC = paramString1;
      this.MD = paramInt4;
      if (paramString2 == null) {
        this.ME = null;
      }
      for (this.MF = null; paramjd == null; this.MF = paramString2)
      {
        this.MH = null;
        return;
        this.ME = jp.class;
      }
      this.MH = paramjd.hb();
    }
    
    protected a(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends ji> paramClass, ji.b<I, O> paramb)
    {
      this.BR = 1;
      this.My = paramInt1;
      this.Mz = paramBoolean1;
      this.MA = paramInt2;
      this.MB = paramBoolean2;
      this.MC = paramString;
      this.MD = paramInt3;
      this.ME = paramClass;
      if (paramClass == null) {}
      for (this.MF = null;; this.MF = paramClass.getCanonicalName())
      {
        this.MH = paramb;
        return;
      }
    }
    
    public static a a(String paramString, int paramInt, ji.b<?, ?> paramb, boolean paramBoolean)
    {
      return new a(paramb.hd(), paramBoolean, paramb.he(), false, paramString, paramInt, null, paramb);
    }
    
    public static <T extends ji> a<T, T> a(String paramString, int paramInt, Class<T> paramClass)
    {
      return new a(11, false, 11, false, paramString, paramInt, paramClass, null);
    }
    
    public static <T extends ji> a<ArrayList<T>, ArrayList<T>> b(String paramString, int paramInt, Class<T> paramClass)
    {
      return new a(11, true, 11, true, paramString, paramInt, paramClass, null);
    }
    
    public static a<Integer, Integer> i(String paramString, int paramInt)
    {
      return new a(0, false, 0, false, paramString, paramInt, null, null);
    }
    
    public static a<Double, Double> j(String paramString, int paramInt)
    {
      return new a(4, false, 4, false, paramString, paramInt, null, null);
    }
    
    public static a<Boolean, Boolean> k(String paramString, int paramInt)
    {
      return new a(6, false, 6, false, paramString, paramInt, null, null);
    }
    
    public static a<String, String> l(String paramString, int paramInt)
    {
      return new a(7, false, 7, false, paramString, paramInt, null, null);
    }
    
    public static a<ArrayList<String>, ArrayList<String>> m(String paramString, int paramInt)
    {
      return new a(7, true, 7, true, paramString, paramInt, null, null);
    }
    
    public void a(jm paramjm)
    {
      this.MG = paramjm;
    }
    
    public I convertBack(O paramO)
    {
      return (I)this.MH.convertBack(paramO);
    }
    
    public int describeContents()
    {
      jk localjk = CREATOR;
      return 0;
    }
    
    public int getVersionCode()
    {
      return this.BR;
    }
    
    public int hd()
    {
      return this.My;
    }
    
    public int he()
    {
      return this.MA;
    }
    
    public a<I, O> hi()
    {
      return new a(this.BR, this.My, this.Mz, this.MA, this.MB, this.MC, this.MD, this.MF, hq());
    }
    
    public boolean hj()
    {
      return this.Mz;
    }
    
    public boolean hk()
    {
      return this.MB;
    }
    
    public String hl()
    {
      return this.MC;
    }
    
    public int hm()
    {
      return this.MD;
    }
    
    public Class<? extends ji> hn()
    {
      return this.ME;
    }
    
    String ho()
    {
      if (this.MF == null) {
        return null;
      }
      return this.MF;
    }
    
    public boolean hp()
    {
      return this.MH != null;
    }
    
    jd hq()
    {
      if (this.MH == null) {
        return null;
      }
      return jd.a(this.MH);
    }
    
    public HashMap<String, a<?, ?>> hr()
    {
      o.i(this.MF);
      o.i(this.MG);
      return this.MG.be(this.MF);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Field\n");
      localStringBuilder1.append("            versionCode=").append(this.BR).append('\n');
      localStringBuilder1.append("                 typeIn=").append(this.My).append('\n');
      localStringBuilder1.append("            typeInArray=").append(this.Mz).append('\n');
      localStringBuilder1.append("                typeOut=").append(this.MA).append('\n');
      localStringBuilder1.append("           typeOutArray=").append(this.MB).append('\n');
      localStringBuilder1.append("        outputFieldName=").append(this.MC).append('\n');
      localStringBuilder1.append("      safeParcelFieldId=").append(this.MD).append('\n');
      localStringBuilder1.append("       concreteTypeName=").append(ho()).append('\n');
      if (hn() != null) {
        localStringBuilder1.append("     concreteType.class=").append(hn().getCanonicalName()).append('\n');
      }
      StringBuilder localStringBuilder2 = localStringBuilder1.append("          converterName=");
      if (this.MH == null) {}
      for (String str = "null";; str = this.MH.getClass().getCanonicalName())
      {
        localStringBuilder2.append(str).append('\n');
        return localStringBuilder1.toString();
      }
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      jk localjk = CREATOR;
      jk.a(this, paramParcel, paramInt);
    }
  }
  
  public static abstract interface b<I, O>
  {
    public abstract I convertBack(O paramO);
    
    public abstract int hd();
    
    public abstract int he();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ji.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */