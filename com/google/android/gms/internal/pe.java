package com.google.android.gms.internal;

public class pe
{
  private final byte[] aww = new byte['Ā'];
  private int awx;
  private int awy;
  
  public pe(byte[] paramArrayOfByte)
  {
    int j = 0;
    while (j < 256)
    {
      this.aww[j] = ((byte)j);
      j += 1;
    }
    int k = 0;
    j = 0;
    while (j < 256)
    {
      k = k + this.aww[j] + paramArrayOfByte[(j % paramArrayOfByte.length)] & 0xFF;
      int i = this.aww[j];
      this.aww[j] = this.aww[k];
      this.aww[k] = i;
      j += 1;
    }
    this.awx = 0;
    this.awy = 0;
  }
  
  public void o(byte[] paramArrayOfByte)
  {
    int m = this.awx;
    int k = this.awy;
    int j = 0;
    while (j < paramArrayOfByte.length)
    {
      m = m + 1 & 0xFF;
      k = k + this.aww[m] & 0xFF;
      int i = this.aww[m];
      this.aww[m] = this.aww[k];
      this.aww[k] = i;
      paramArrayOfByte[j] = ((byte)(paramArrayOfByte[j] ^ this.aww[(this.aww[m] + this.aww[k] & 0xFF)]));
      j += 1;
    }
    this.awx = m;
    this.awy = k;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/pe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */