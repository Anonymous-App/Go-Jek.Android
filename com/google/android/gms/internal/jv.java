package com.google.android.gms.internal;

import android.database.CharArrayBuffer;

public final class jv
{
  public static void b(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    if ((paramCharArrayBuffer.data == null) || (paramCharArrayBuffer.data.length < paramString.length())) {
      paramCharArrayBuffer.data = paramString.toCharArray();
    }
    for (;;)
    {
      paramCharArrayBuffer.sizeCopied = paramString.length();
      return;
      paramString.getChars(0, paramString.length(), paramCharArrayBuffer.data, 0);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/jv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */