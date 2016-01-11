package com.google.android.gms.tagmanager;

class cw
  implements cg
{
  private final long AN;
  private final int AO;
  private double AP;
  private final Object AR = new Object();
  private long arp;
  
  public cw()
  {
    this(60, 2000L);
  }
  
  public cw(int paramInt, long paramLong)
  {
    this.AO = paramInt;
    this.AP = this.AO;
    this.AN = paramLong;
  }
  
  public boolean eJ()
  {
    synchronized (this.AR)
    {
      long l = System.currentTimeMillis();
      if (this.AP < this.AO)
      {
        double d = (l - this.arp) / this.AN;
        if (d > 0.0D) {
          this.AP = Math.min(this.AO, d + this.AP);
        }
      }
      this.arp = l;
      if (this.AP >= 1.0D)
      {
        this.AP -= 1.0D;
        return true;
      }
      bh.W("No more tokens available.");
      return false;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/cw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */