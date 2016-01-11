package com.fasterxml.jackson.databind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  private static final String GMT_ID = "GMT";
  private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");
  private static final TimeZone TIMEZONE_Z = TIMEZONE_GMT;
  
  private static boolean checkOffset(String paramString, int paramInt, char paramChar)
  {
    return (paramInt < paramString.length()) && (paramString.charAt(paramInt) == paramChar);
  }
  
  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int k = "yyyy-MM-ddThh:mm:ss".length();
    int i;
    int j;
    label51:
    char c;
    if (paramBoolean)
    {
      i = ".sss".length();
      if (paramTimeZone.getRawOffset() != 0) {
        break label320;
      }
      j = "Z".length();
      paramDate = new StringBuilder(k + i + j);
      padInt(paramDate, localGregorianCalendar.get(1), "yyyy".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(2) + 1, "MM".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(5), "dd".length());
      paramDate.append('T');
      padInt(paramDate, localGregorianCalendar.get(11), "hh".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(12), "mm".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(13), "ss".length());
      if (paramBoolean)
      {
        paramDate.append('.');
        padInt(paramDate, localGregorianCalendar.get(14), "sss".length());
      }
      i = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
      if (i == 0) {
        break label336;
      }
      j = Math.abs(i / 60000 / 60);
      k = Math.abs(i / 60000 % 60);
      if (i >= 0) {
        break label330;
      }
      c = '-';
      label274:
      paramDate.append(c);
      padInt(paramDate, j, "hh".length());
      paramDate.append(':');
      padInt(paramDate, k, "mm".length());
    }
    for (;;)
    {
      return paramDate.toString();
      i = 0;
      break;
      label320:
      j = "+hh:mm".length();
      break label51;
      label330:
      c = '+';
      break label274;
      label336:
      paramDate.append('Z');
    }
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    paramInt1 = paramInt2 - str.length();
    while (paramInt1 > 0)
    {
      paramStringBuilder.append('0');
      paramInt1 -= 1;
    }
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString, ParsePosition paramParsePosition)
    throws ParseException
  {
    try
    {
      i = paramParsePosition.getIndex();
      j = i + 4;
      i6 = parseInt(paramString, i, j);
      i = j;
      if (checkOffset(paramString, j, '-')) {
        i = j + 1;
      }
      j = i + 2;
      i7 = parseInt(paramString, i, j);
      if (!checkOffset(paramString, j, '-')) {
        break label842;
      }
      i = j + 1;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      int i6;
      int i7;
      int i8;
      boolean bool;
      GregorianCalendar localGregorianCalendar;
      int i5;
      if (paramString != null) {
        break label789;
      }
      paramString = null;
      String str1 = localIndexOutOfBoundsException.getMessage();
      if (str1 == null) {
        break label393;
      }
      Object localObject2 = str1;
      if (!str1.isEmpty()) {
        break label426;
      }
      localObject2 = "(" + localIndexOutOfBoundsException.getClass().getName() + ")";
      paramString = new ParseException("Failed to parse date [" + paramString + "]: " + (String)localObject2, paramParsePosition.getIndex());
      paramString.initCause(localIndexOutOfBoundsException);
      throw paramString;
      c = paramString.charAt(i);
      if (c != 'Z') {
        break label848;
      }
      Object localObject1 = TIMEZONE_Z;
      i += 1;
      String str2;
      do
      {
        do
        {
          for (;;)
          {
            localObject1 = new GregorianCalendar((TimeZone)localObject1);
            ((Calendar)localObject1).setLenient(false);
            ((Calendar)localObject1).set(1, i6);
            ((Calendar)localObject1).set(2, i7 - 1);
            ((Calendar)localObject1).set(5, i8);
            ((Calendar)localObject1).set(11, j);
            ((Calendar)localObject1).set(12, m);
            ((Calendar)localObject1).set(13, n);
            ((Calendar)localObject1).set(14, k);
            paramParsePosition.setIndex(i);
            return ((Calendar)localObject1).getTime();
            localObject1 = paramString.substring(i);
            i1 = i + ((String)localObject1).length();
            if ((!"+0000".equals(localObject1)) && (!"+00:00".equals(localObject1))) {
              break;
            }
            localObject1 = TIMEZONE_Z;
            i = i1;
          }
          str1 = "GMT" + (String)localObject1;
          localObject2 = TimeZone.getTimeZone(str1);
          str2 = ((TimeZone)localObject2).getID();
          i = i1;
          localObject1 = localObject2;
        } while (str2.equals(str1));
        i = i1;
        localObject1 = localObject2;
      } while (str2.replace(":", "").equals(str1));
      throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str1 + " given, resolves to " + ((TimeZone)localObject2).getID());
      throw new IndexOutOfBoundsException("Invalid time zone indicator '" + c + "'");
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        int i2;
        int i3;
        int i1;
        int i4;
        char c;
        continue;
        paramString = '"' + paramString + "'";
        continue;
        int j = i2;
        int k = i1;
        int m = i4;
        int n = i3;
        continue;
        int i = j;
        continue;
        i = j;
        continue;
        if (c != '+') {
          if (c != '-') {}
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      label393:
      label426:
      label789:
      label817:
      label836:
      label842:
      label848:
      for (;;) {}
    }
    i2 = i + 2;
    i8 = parseInt(paramString, i, i2);
    j = 0;
    m = 0;
    i3 = 0;
    i1 = 0;
    bool = checkOffset(paramString, i2, 'T');
    if ((!bool) && (paramString.length() <= i2))
    {
      localGregorianCalendar = new GregorianCalendar(i6, i7 - 1, i8);
      paramParsePosition.setIndex(i2);
      return localGregorianCalendar.getTime();
    }
    k = i1;
    i = i2;
    n = i3;
    if (bool)
    {
      i = i2 + 1;
      j = i + 2;
      i2 = parseInt(paramString, i, j);
      i = j;
      if (checkOffset(paramString, j, ':')) {
        i = j + 1;
      }
      j = i + 2;
      i4 = parseInt(paramString, i, j);
      if (!checkOffset(paramString, j, ':')) {
        break label836;
      }
      i = j + 1;
      if (paramString.length() <= i) {
        break label817;
      }
      j = paramString.charAt(i);
      if ((j == 90) || (j == 43) || (j == 45)) {
        break label817;
      }
      i5 = i + 2;
      i3 = parseInt(paramString, i, i5);
      j = i2;
      k = i1;
      m = i4;
      i = i5;
      n = i3;
      if (checkOffset(paramString, i5, '.'))
      {
        j = i5 + 1;
        i = j + 3;
        k = parseInt(paramString, j, i);
        n = i3;
        m = i4;
        j = i2;
      }
    }
    if (paramString.length() <= i) {
      throw new IllegalArgumentException("No time zone indicator");
    }
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 < 0) || (paramInt2 > paramString.length()) || (paramInt1 > paramInt2)) {
      throw new NumberFormatException(paramString);
    }
    int j = 0;
    int i;
    if (paramInt1 < paramInt2)
    {
      i = paramInt1 + 1;
      j = Character.digit(paramString.charAt(paramInt1), 10);
      if (j < 0) {
        throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
      }
      j = -j;
    }
    for (;;)
    {
      if (i < paramInt2)
      {
        int k = Character.digit(paramString.charAt(i), 10);
        if (k < 0) {
          throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
        }
        j = j * 10 - k;
        i += 1;
      }
      else
      {
        return -j;
        i = paramInt1;
      }
    }
  }
  
  @Deprecated
  public static TimeZone timeZoneGMT()
  {
    return TIMEZONE_GMT;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/util/ISO8601Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */