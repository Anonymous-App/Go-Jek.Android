package com.google.android.gms.internal;

import java.io.IOException;

class p
  implements n
{
  private pg kY;
  private byte[] kZ;
  private final int la;
  
  public p(int paramInt)
  {
    this.la = paramInt;
    reset();
  }
  
  public byte[] A()
    throws IOException
  {
    int i = this.kY.qx();
    if (i < 0) {
      throw new IOException();
    }
    if (i == 0) {
      return this.kZ;
    }
    byte[] arrayOfByte = new byte[this.kZ.length - i];
    System.arraycopy(this.kZ, 0, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public void b(int paramInt, long paramLong)
    throws IOException
  {
    this.kY.b(paramInt, paramLong);
  }
  
  public void b(int paramInt, String paramString)
    throws IOException
  {
    this.kY.b(paramInt, paramString);
  }
  
  public void reset()
  {
    this.kZ = new byte[this.la];
    this.kY = pg.q(this.kZ);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */