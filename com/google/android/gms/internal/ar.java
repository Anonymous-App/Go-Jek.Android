package com.google.android.gms.internal;

import java.security.MessageDigest;

public class ar
  extends ao
{
  private MessageDigest nP;
  
  byte[] a(String[] paramArrayOfString)
  {
    byte[] arrayOfByte = new byte[paramArrayOfString.length];
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      arrayOfByte[i] = ((byte)(aq.o(paramArrayOfString[i]) & 0xFF));
      i += 1;
    }
    return arrayOfByte;
  }
  
  public byte[] l(String arg1)
  {
    byte[] arrayOfByte1 = a(???.split(" "));
    this.nP = ba();
    for (;;)
    {
      synchronized (this.mw)
      {
        if (this.nP == null) {
          return new byte[0];
        }
        this.nP.reset();
        this.nP.update(arrayOfByte1);
        arrayOfByte1 = this.nP.digest();
        i = 4;
        if (arrayOfByte1.length > 4)
        {
          byte[] arrayOfByte2 = new byte[i];
          System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte2.length);
          return arrayOfByte2;
        }
      }
      int i = localObject.length;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */