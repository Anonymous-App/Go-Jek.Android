package com.fasterxml.jackson.core.io;

public final class NumberOutput
{
  private static int BILLION;
  static final char[] FULL_3;
  static final byte[] FULL_TRIPLETS_B;
  static final char[] LEAD_3;
  private static long MAX_INT_AS_LONG;
  private static int MILLION = 1000000;
  private static long MIN_INT_AS_LONG;
  private static final char NC = '\000';
  static final String SMALLEST_LONG;
  private static long TEN_BILLION_L;
  private static long THOUSAND_L;
  static final String[] sSmallIntStrs = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
  static final String[] sSmallIntStrs2 = { "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10" };
  
  static
  {
    BILLION = 1000000000;
    TEN_BILLION_L = 10000000000L;
    THOUSAND_L = 1000L;
    MIN_INT_AS_LONG = -2147483648L;
    MAX_INT_AS_LONG = 2147483647L;
    SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    LEAD_3 = new char['ྠ'];
    FULL_3 = new char['ྠ'];
    int i3 = 0;
    int i1 = 0;
    while (i1 < 10)
    {
      int k = (char)(i1 + 48);
      int i;
      int i2;
      if (i1 == 0)
      {
        i = 0;
        i2 = 0;
      }
      for (;;)
      {
        if (i2 >= 10) {
          break label219;
        }
        int m = (char)(i2 + 48);
        if ((i1 == 0) && (i2 == 0)) {}
        for (int j = 0;; j = m)
        {
          int i4 = 0;
          while (i4 < 10)
          {
            int n = (char)(i4 + 48);
            LEAD_3[i3] = i;
            LEAD_3[(i3 + 1)] = j;
            LEAD_3[(i3 + 2)] = n;
            FULL_3[i3] = k;
            FULL_3[(i3 + 1)] = m;
            FULL_3[(i3 + 2)] = n;
            i3 += 4;
            i4 += 1;
          }
          i = k;
          break;
        }
        i2 += 1;
      }
      label219:
      i1 += 1;
    }
    FULL_TRIPLETS_B = new byte['ྠ'];
    i1 = 0;
    while (i1 < 4000)
    {
      FULL_TRIPLETS_B[i1] = ((byte)FULL_3[i1]);
      i1 += 1;
    }
  }
  
  private static int calcLongStrLength(long paramLong)
  {
    int i = 10;
    for (long l = TEN_BILLION_L;; l = (l << 3) + (l << 1))
    {
      if ((paramLong < l) || (i == 19)) {
        return i;
      }
      i += 1;
    }
  }
  
  private static int full3(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int j = paramInt1 << 2;
    paramInt1 = paramInt2 + 1;
    byte[] arrayOfByte = FULL_TRIPLETS_B;
    int i = j + 1;
    paramArrayOfByte[paramInt2] = arrayOfByte[j];
    paramInt2 = paramInt1 + 1;
    paramArrayOfByte[paramInt1] = FULL_TRIPLETS_B[i];
    paramArrayOfByte[paramInt2] = FULL_TRIPLETS_B[(i + 1)];
    return paramInt2 + 1;
  }
  
  private static int full3(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    int j = paramInt1 << 2;
    paramInt1 = paramInt2 + 1;
    char[] arrayOfChar = FULL_3;
    int i = j + 1;
    paramArrayOfChar[paramInt2] = arrayOfChar[j];
    paramInt2 = paramInt1 + 1;
    paramArrayOfChar[paramInt1] = FULL_3[i];
    paramArrayOfChar[paramInt2] = FULL_3[(i + 1)];
    return paramInt2 + 1;
  }
  
  private static int leading3(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramInt1 <<= 2;
    char[] arrayOfChar = LEAD_3;
    int i = paramInt1 + 1;
    int j = arrayOfChar[paramInt1];
    paramInt1 = paramInt2;
    if (j != 0)
    {
      paramArrayOfByte[paramInt2] = ((byte)j);
      paramInt1 = paramInt2 + 1;
    }
    j = LEAD_3[i];
    paramInt2 = paramInt1;
    if (j != 0)
    {
      paramArrayOfByte[paramInt1] = ((byte)j);
      paramInt2 = paramInt1 + 1;
    }
    paramArrayOfByte[paramInt2] = ((byte)LEAD_3[(i + 1)]);
    return paramInt2 + 1;
  }
  
  private static int leading3(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    paramInt1 <<= 2;
    char[] arrayOfChar = LEAD_3;
    int j = paramInt1 + 1;
    int i = arrayOfChar[paramInt1];
    paramInt1 = paramInt2;
    if (i != 0)
    {
      paramArrayOfChar[paramInt2] = i;
      paramInt1 = paramInt2 + 1;
    }
    i = LEAD_3[j];
    paramInt2 = paramInt1;
    if (i != 0)
    {
      paramArrayOfChar[paramInt1] = i;
      paramInt2 = paramInt1 + 1;
    }
    paramArrayOfChar[paramInt2] = LEAD_3[(j + 1)];
    return paramInt2 + 1;
  }
  
  public static int outputInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < 0)
    {
      if (paramInt1 == Integer.MIN_VALUE) {
        return outputLong(paramInt1, paramArrayOfByte, paramInt2);
      }
      paramArrayOfByte[paramInt2] = 45;
      j = -paramInt1;
      i = paramInt2 + 1;
    }
    if (j < MILLION)
    {
      if (j < 1000) {
        if (j < 10)
        {
          paramArrayOfByte[i] = ((byte)(j + 48));
          paramInt1 = i + 1;
        }
      }
      for (;;)
      {
        return paramInt1;
        paramInt1 = leading3(j, paramArrayOfByte, i);
        continue;
        paramInt1 = j / 1000;
        paramInt1 = full3(j - paramInt1 * 1000, paramArrayOfByte, leading3(paramInt1, paramArrayOfByte, i));
      }
    }
    int k;
    if (j >= BILLION)
    {
      k = 1;
      paramInt1 = j;
      paramInt2 = i;
      if (k != 0)
      {
        paramInt1 = j - BILLION;
        if (paramInt1 < BILLION) {
          break label221;
        }
        paramInt1 -= BILLION;
        paramArrayOfByte[i] = 50;
        paramInt2 = i + 1;
      }
      label164:
      i = paramInt1 / 1000;
      j = i / 1000;
      if (k == 0) {
        break label233;
      }
    }
    label221:
    label233:
    for (paramInt2 = full3(j, paramArrayOfByte, paramInt2);; paramInt2 = leading3(j, paramArrayOfByte, paramInt2))
    {
      return full3(paramInt1 - i * 1000, paramArrayOfByte, full3(i - j * 1000, paramArrayOfByte, paramInt2));
      k = 0;
      break;
      paramArrayOfByte[i] = 49;
      paramInt2 = i + 1;
      break label164;
    }
  }
  
  public static int outputInt(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < 0)
    {
      if (paramInt1 == Integer.MIN_VALUE) {
        return outputLong(paramInt1, paramArrayOfChar, paramInt2);
      }
      paramArrayOfChar[paramInt2] = '-';
      j = -paramInt1;
      i = paramInt2 + 1;
    }
    if (j < MILLION)
    {
      if (j < 1000) {
        if (j < 10)
        {
          paramArrayOfChar[i] = ((char)(j + 48));
          paramInt1 = i + 1;
        }
      }
      for (;;)
      {
        return paramInt1;
        paramInt1 = leading3(j, paramArrayOfChar, i);
        continue;
        paramInt1 = j / 1000;
        paramInt1 = full3(j - paramInt1 * 1000, paramArrayOfChar, leading3(paramInt1, paramArrayOfChar, i));
      }
    }
    int k;
    if (j >= BILLION)
    {
      k = 1;
      paramInt1 = j;
      paramInt2 = i;
      if (k != 0)
      {
        paramInt1 = j - BILLION;
        if (paramInt1 < BILLION) {
          break label221;
        }
        paramInt1 -= BILLION;
        paramArrayOfChar[i] = '2';
        paramInt2 = i + 1;
      }
      label164:
      i = paramInt1 / 1000;
      j = i / 1000;
      if (k == 0) {
        break label233;
      }
    }
    label221:
    label233:
    for (paramInt2 = full3(j, paramArrayOfChar, paramInt2);; paramInt2 = leading3(j, paramArrayOfChar, paramInt2))
    {
      return full3(paramInt1 - i * 1000, paramArrayOfChar, full3(i - j * 1000, paramArrayOfChar, paramInt2));
      k = 0;
      break;
      paramArrayOfChar[i] = '1';
      paramInt2 = i + 1;
      break label164;
    }
  }
  
  public static int outputLong(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    int i;
    long l;
    if (paramLong < 0L)
    {
      if (paramLong > MIN_INT_AS_LONG) {
        return outputInt((int)paramLong, paramArrayOfByte, paramInt);
      }
      if (paramLong == Long.MIN_VALUE)
      {
        j = SMALLEST_LONG.length();
        i = 0;
        while (i < j)
        {
          paramArrayOfByte[paramInt] = ((byte)SMALLEST_LONG.charAt(i));
          i += 1;
          paramInt += 1;
        }
        return paramInt;
      }
      paramArrayOfByte[paramInt] = 45;
      l = -paramLong;
      i = paramInt + 1;
    }
    int k;
    do
    {
      k = i + calcLongStrLength(l);
      paramInt = k;
      while (l > MAX_INT_AS_LONG)
      {
        paramInt -= 3;
        paramLong = l / THOUSAND_L;
        full3((int)(l - THOUSAND_L * paramLong), paramArrayOfByte, paramInt);
        l = paramLong;
      }
      l = paramLong;
      i = paramInt;
    } while (paramLong > MAX_INT_AS_LONG);
    return outputInt((int)paramLong, paramArrayOfByte, paramInt);
    int m = (int)l;
    int j = paramInt;
    for (paramInt = m; paramInt >= 1000; paramInt = m)
    {
      j -= 3;
      m = paramInt / 1000;
      full3(paramInt - m * 1000, paramArrayOfByte, j);
    }
    leading3(paramInt, paramArrayOfByte, i);
    return k;
  }
  
  public static int outputLong(long paramLong, char[] paramArrayOfChar, int paramInt)
  {
    int i;
    long l;
    if (paramLong < 0L)
    {
      if (paramLong > MIN_INT_AS_LONG) {
        return outputInt((int)paramLong, paramArrayOfChar, paramInt);
      }
      if (paramLong == Long.MIN_VALUE)
      {
        i = SMALLEST_LONG.length();
        SMALLEST_LONG.getChars(0, i, paramArrayOfChar, paramInt);
        return paramInt + i;
      }
      paramArrayOfChar[paramInt] = '-';
      l = -paramLong;
      i = paramInt + 1;
    }
    int k;
    do
    {
      k = i + calcLongStrLength(l);
      paramInt = k;
      while (l > MAX_INT_AS_LONG)
      {
        paramInt -= 3;
        paramLong = l / THOUSAND_L;
        full3((int)(l - THOUSAND_L * paramLong), paramArrayOfChar, paramInt);
        l = paramLong;
      }
      l = paramLong;
      i = paramInt;
    } while (paramLong > MAX_INT_AS_LONG);
    return outputInt((int)paramLong, paramArrayOfChar, paramInt);
    int m = (int)l;
    int j = paramInt;
    for (paramInt = m; paramInt >= 1000; paramInt = m)
    {
      j -= 3;
      m = paramInt / 1000;
      full3(paramInt - m * 1000, paramArrayOfChar, j);
    }
    leading3(paramInt, paramArrayOfChar, i);
    return k;
  }
  
  public static String toString(double paramDouble)
  {
    return Double.toString(paramDouble);
  }
  
  public static String toString(float paramFloat)
  {
    return Float.toString(paramFloat);
  }
  
  public static String toString(int paramInt)
  {
    if (paramInt < sSmallIntStrs.length)
    {
      if (paramInt >= 0) {
        return sSmallIntStrs[paramInt];
      }
      int i = -paramInt - 1;
      if (i < sSmallIntStrs2.length) {
        return sSmallIntStrs2[i];
      }
    }
    return Integer.toString(paramInt);
  }
  
  public static String toString(long paramLong)
  {
    if ((paramLong <= 2147483647L) && (paramLong >= -2147483648L)) {
      return toString((int)paramLong);
    }
    return Long.toString(paramLong);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/io/NumberOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */