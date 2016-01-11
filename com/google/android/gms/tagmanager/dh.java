package com.google.android.gms.tagmanager;

class dh
  extends Number
  implements Comparable<dh>
{
  private double arR;
  private long arS;
  private boolean arT;
  
  private dh(double paramDouble)
  {
    this.arR = paramDouble;
    this.arT = false;
  }
  
  private dh(long paramLong)
  {
    this.arS = paramLong;
    this.arT = true;
  }
  
  public static dh a(Double paramDouble)
  {
    return new dh(paramDouble.doubleValue());
  }
  
  public static dh cW(String paramString)
    throws NumberFormatException
  {
    try
    {
      dh localdh1 = new dh(Long.parseLong(paramString));
      return localdh1;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        dh localdh2 = new dh(Double.parseDouble(paramString));
        return localdh2;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        throw new NumberFormatException(paramString + " is not a valid TypedNumber");
      }
    }
  }
  
  public static dh z(long paramLong)
  {
    return new dh(paramLong);
  }
  
  public int a(dh paramdh)
  {
    if ((pA()) && (paramdh.pA())) {
      return new Long(this.arS).compareTo(Long.valueOf(paramdh.arS));
    }
    return Double.compare(doubleValue(), paramdh.doubleValue());
  }
  
  public byte byteValue()
  {
    return (byte)(int)longValue();
  }
  
  public double doubleValue()
  {
    if (pA()) {
      return this.arS;
    }
    return this.arR;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof dh)) && (a((dh)paramObject) == 0);
  }
  
  public float floatValue()
  {
    return (float)doubleValue();
  }
  
  public int hashCode()
  {
    return new Long(longValue()).hashCode();
  }
  
  public int intValue()
  {
    return pC();
  }
  
  public long longValue()
  {
    return pB();
  }
  
  public boolean pA()
  {
    return this.arT;
  }
  
  public long pB()
  {
    if (pA()) {
      return this.arS;
    }
    return this.arR;
  }
  
  public int pC()
  {
    return (int)longValue();
  }
  
  public short pD()
  {
    return (short)(int)longValue();
  }
  
  public boolean pz()
  {
    return !pA();
  }
  
  public short shortValue()
  {
    return pD();
  }
  
  public String toString()
  {
    if (pA()) {
      return Long.toString(this.arS);
    }
    return Double.toString(this.arR);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/dh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */