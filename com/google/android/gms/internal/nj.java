package com.google.android.gms.internal;

import com.google.android.gms.common.internal.o;
import java.util.ArrayList;

public class nj
{
  private final ArrayList<a> akK = new ArrayList();
  private int akL;
  
  public nj()
  {
    this(100);
  }
  
  public nj(int paramInt)
  {
    this.akL = paramInt;
  }
  
  private void mX()
  {
    while (getSize() > getCapacity()) {
      this.akK.remove(0);
    }
  }
  
  public void a(nm paramnm, ni paramni)
  {
    this.akK.add(new a(paramnm, paramni, null));
    mX();
  }
  
  public void clear()
  {
    this.akK.clear();
  }
  
  public int getCapacity()
  {
    return this.akL;
  }
  
  public int getSize()
  {
    return this.akK.size();
  }
  
  public boolean isEmpty()
  {
    return this.akK.isEmpty();
  }
  
  public ArrayList<a> mW()
  {
    return this.akK;
  }
  
  public static class a
  {
    public final nm akM;
    public final ni akN;
    public final pr.c akO;
    
    private a(nm paramnm, ni paramni)
    {
      this.akM = ((nm)o.i(paramnm));
      this.akN = ((ni)o.i(paramni));
      this.akO = null;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/nj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */