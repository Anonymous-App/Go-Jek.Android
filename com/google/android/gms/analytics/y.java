package com.google.android.gms.analytics;

class y
  implements ac
{
  private final long AN;
  private final int AO;
  private double AP;
  private long AQ;
  private final Object AR = new Object();
  private final String AS;
  
  public y(int paramInt, long paramLong, String paramString)
  {
    this.AO = paramInt;
    this.AP = this.AO;
    this.AN = paramLong;
    this.AS = paramString;
  }
  
  public y(String paramString)
  {
    this(60, 2000L, paramString);
  }
  
  public boolean eJ()
  {
    synchronized (this.AR)
    {
      long l = System.currentTimeMillis();
      if (this.AP < this.AO)
      {
        double d = (l - this.AQ) / this.AN;
        if (d > 0.0D) {
          this.AP = Math.min(this.AO, d + this.AP);
        }
      }
      this.AQ = l;
      if (this.AP >= 1.0D)
      {
        this.AP -= 1.0D;
        return true;
      }
      z.W("Excessive " + this.AS + " detected; call ignored.");
      return false;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */