package com.gojek.gobox.util;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolyUtil
{
  private static final double DEFAULT_TOLERANCE = 0.1D;
  
  public static boolean containsLocation(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean)
  {
    int i = paramList.size();
    if (i == 0) {
      return false;
    }
    double d5 = Math.toRadians(paramLatLng.latitude);
    double d6 = Math.toRadians(paramLatLng.longitude);
    paramLatLng = (LatLng)paramList.get(i - 1);
    double d2 = Math.toRadians(paramLatLng.latitude);
    double d1 = Math.toRadians(paramLatLng.longitude);
    paramLatLng = paramList.iterator();
    i = 0;
    double d3;
    double d4;
    if (paramLatLng.hasNext())
    {
      paramList = (LatLng)paramLatLng.next();
      double d7 = MathUtil.wrap(d6 - d1, -3.141592653589793D, 3.141592653589793D);
      if ((d5 == d2) && (d7 == 0.0D)) {
        return true;
      }
      d3 = Math.toRadians(paramList.latitude);
      d4 = Math.toRadians(paramList.longitude);
      if (!intersects(d2, d3, MathUtil.wrap(d4 - d1, -3.141592653589793D, 3.141592653589793D), d5, d7, paramBoolean)) {
        break label198;
      }
      i += 1;
    }
    label198:
    for (;;)
    {
      d1 = d4;
      d2 = d3;
      break;
      return (i & 0x1) != 0;
    }
  }
  
  public static List<LatLng> decode(String paramString)
  {
    int i2 = paramString.length();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = 0;
    int k = 0;
    int n;
    int m;
    if (i < i2)
    {
      n = 1;
      m = 0;
    }
    for (int i1 = i;; i1 = i)
    {
      i = i1 + 1;
      i1 = paramString.charAt(i1) - '?' - 1;
      n += (i1 << m);
      m += 5;
      if (i1 < 31)
      {
        if ((n & 0x1) != 0)
        {
          m = n >> 1 ^ 0xFFFFFFFF;
          label92:
          i1 = k + m;
          m = 1;
          k = 0;
        }
        for (n = i;; n = i)
        {
          i = n + 1;
          n = paramString.charAt(n) - '?' - 1;
          m += (n << k);
          k += 5;
          if (n < 31)
          {
            if ((m & 0x1) != 0) {}
            for (k = m >> 1 ^ 0xFFFFFFFF;; k = m >> 1)
            {
              j = k + j;
              localArrayList.add(new LatLng(i1 * 1.0E-5D, j * 1.0E-5D));
              k = i1;
              break;
              m = n >> 1;
              break label92;
            }
            return localArrayList;
          }
        }
      }
    }
  }
  
  public static String encode(List<LatLng> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramList = paramList.iterator();
    long l1 = 0L;
    long l3;
    for (long l2 = 0L; paramList.hasNext(); l2 = l3)
    {
      LatLng localLatLng = (LatLng)paramList.next();
      l3 = Math.round(localLatLng.latitude * 100000.0D);
      long l4 = Math.round(localLatLng.longitude * 100000.0D);
      encode(l3 - l2, localStringBuffer);
      encode(l4 - l1, localStringBuffer);
      l1 = l4;
    }
    return localStringBuffer.toString();
  }
  
  private static void encode(long paramLong, StringBuffer paramStringBuffer)
  {
    if (paramLong < 0L) {
      paramLong = paramLong << 1 ^ 0xFFFFFFFFFFFFFFFF;
    }
    while (paramLong >= 32L)
    {
      paramStringBuffer.append(Character.toChars((int)((0x1F & paramLong | 0x20) + 63L)));
      paramLong >>= 5;
      continue;
      paramLong <<= 1;
    }
    paramStringBuffer.append(Character.toChars((int)(paramLong + 63L)));
  }
  
  private static boolean intersects(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, boolean paramBoolean)
  {
    if (((paramDouble5 >= 0.0D) && (paramDouble5 >= paramDouble3)) || ((paramDouble5 < 0.0D) && (paramDouble5 < paramDouble3))) {
      return false;
    }
    if (paramDouble4 <= -1.5707963267948966D) {
      return false;
    }
    if ((paramDouble1 <= -1.5707963267948966D) || (paramDouble2 <= -1.5707963267948966D) || (paramDouble1 >= 1.5707963267948966D) || (paramDouble2 >= 1.5707963267948966D)) {
      return false;
    }
    if (paramDouble3 <= -3.141592653589793D) {
      return false;
    }
    double d = ((paramDouble3 - paramDouble5) * paramDouble1 + paramDouble2 * paramDouble5) / paramDouble3;
    if ((paramDouble1 >= 0.0D) && (paramDouble2 >= 0.0D) && (paramDouble4 < d)) {
      return false;
    }
    if ((paramDouble1 <= 0.0D) && (paramDouble2 <= 0.0D) && (paramDouble4 >= d)) {
      return true;
    }
    if (paramDouble4 >= 1.5707963267948966D) {
      return true;
    }
    if (paramBoolean) {
      return Math.tan(paramDouble4) >= tanLatGC(paramDouble1, paramDouble2, paramDouble3, paramDouble5);
    }
    return MathUtil.mercator(paramDouble4) >= mercatorLatRhumb(paramDouble1, paramDouble2, paramDouble3, paramDouble5);
  }
  
  public static boolean isLocationOnEdge(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean)
  {
    return isLocationOnEdge(paramLatLng, paramList, paramBoolean, 0.1D);
  }
  
  public static boolean isLocationOnEdge(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean, double paramDouble)
  {
    return isLocationOnEdgeOrPath(paramLatLng, paramList, true, paramBoolean, paramDouble);
  }
  
  private static boolean isLocationOnEdgeOrPath(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean1, boolean paramBoolean2, double paramDouble)
  {
    int i = paramList.size();
    if (i == 0) {
      return false;
    }
    double d9 = paramDouble / 6371009.0D;
    double d6 = MathUtil.hav(d9);
    double d7 = Math.toRadians(paramLatLng.latitude);
    double d8 = Math.toRadians(paramLatLng.longitude);
    double d2;
    if (paramBoolean1)
    {
      i -= 1;
      paramLatLng = (LatLng)paramList.get(i);
      d2 = Math.toRadians(paramLatLng.latitude);
      paramDouble = Math.toRadians(paramLatLng.longitude);
      if (paramBoolean2)
      {
        paramLatLng = paramList.iterator();
        d1 = paramDouble;
      }
    }
    else
    {
      for (paramDouble = d2;; paramDouble = d2)
      {
        if (!paramLatLng.hasNext()) {
          break label483;
        }
        paramList = (LatLng)paramLatLng.next();
        d2 = Math.toRadians(paramList.latitude);
        d3 = Math.toRadians(paramList.longitude);
        if (isOnSegmentGC(paramDouble, d1, d2, d3, d7, d8, d6))
        {
          return true;
          i = 0;
          break;
        }
        d1 = d3;
      }
    }
    double d3 = MathUtil.mercator(d2);
    double d10 = MathUtil.mercator(d7);
    paramLatLng = new double[3];
    paramList = paramList.iterator();
    double d1 = paramDouble;
    paramDouble = d3;
    while (paramList.hasNext())
    {
      LatLng localLatLng = (LatLng)paramList.next();
      d3 = Math.toRadians(localLatLng.latitude);
      double d5 = MathUtil.mercator(d3);
      double d4 = Math.toRadians(localLatLng.longitude);
      if ((Math.max(d2, d3) >= d7 - d9) && (Math.min(d2, d3) <= d7 + d9))
      {
        d2 = MathUtil.wrap(d4 - d1, -3.141592653589793D, 3.141592653589793D);
        d1 = MathUtil.wrap(d8 - d1, -3.141592653589793D, 3.141592653589793D);
        paramLatLng[0] = d1;
        paramLatLng[1] = (6.283185307179586D + d1);
        paramLatLng[2] = (d1 - 6.283185307179586D);
        int j = paramLatLng.length;
        i = 0;
        while (i < j)
        {
          double d11 = paramLatLng[i];
          double d12 = d5 - paramDouble;
          d1 = d2 * d2 + d12 * d12;
          if (d1 <= 0.0D) {}
          for (d1 = 0.0D; MathUtil.havDistance(d7, MathUtil.inverseMercator(d1 * d12 + paramDouble), d11 - d1 * d2) < d6; d1 = MathUtil.clamp((d11 * d2 + (d10 - paramDouble) * d12) / d1, 0.0D, 1.0D)) {
            return true;
          }
          i += 1;
        }
      }
      paramDouble = d5;
      d1 = d4;
      d2 = d3;
    }
    label483:
    return false;
  }
  
  public static boolean isLocationOnPath(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean)
  {
    return isLocationOnPath(paramLatLng, paramList, paramBoolean, 0.1D);
  }
  
  public static boolean isLocationOnPath(LatLng paramLatLng, List<LatLng> paramList, boolean paramBoolean, double paramDouble)
  {
    return isLocationOnEdgeOrPath(paramLatLng, paramList, false, paramBoolean, paramDouble);
  }
  
  private static boolean isOnSegmentGC(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7)
  {
    double d1 = MathUtil.havDistance(paramDouble1, paramDouble5, paramDouble2 - paramDouble6);
    if (d1 <= paramDouble7) {
      return true;
    }
    double d2 = MathUtil.havDistance(paramDouble3, paramDouble5, paramDouble4 - paramDouble6);
    if (d2 <= paramDouble7) {
      return true;
    }
    paramDouble5 = MathUtil.havFromSin(sinDeltaBearing(paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramDouble5, paramDouble6) * MathUtil.sinFromHav(d1));
    if (paramDouble5 > paramDouble7) {
      return false;
    }
    paramDouble1 = MathUtil.havDistance(paramDouble1, paramDouble3, paramDouble2 - paramDouble4);
    paramDouble2 = (1.0D - 2.0D * paramDouble1) * paramDouble5 + paramDouble1;
    if ((d1 > paramDouble2) || (d2 > paramDouble2)) {
      return false;
    }
    if (paramDouble1 < 0.74D) {
      return true;
    }
    paramDouble1 = 1.0D - 2.0D * paramDouble5;
    return MathUtil.sinSumFromHav((d1 - paramDouble5) / paramDouble1, (d2 - paramDouble5) / paramDouble1) > 0.0D;
  }
  
  private static double mercatorLatRhumb(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return (MathUtil.mercator(paramDouble1) * (paramDouble3 - paramDouble4) + MathUtil.mercator(paramDouble2) * paramDouble4) / paramDouble3;
  }
  
  private static double sinDeltaBearing(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    double d1 = Math.sin(paramDouble1);
    double d2 = Math.cos(paramDouble3);
    double d3 = Math.cos(paramDouble5);
    paramDouble6 -= paramDouble2;
    double d4 = paramDouble4 - paramDouble2;
    paramDouble2 = Math.sin(paramDouble6) * d3;
    paramDouble4 = Math.sin(d4) * d2;
    paramDouble5 = Math.sin(paramDouble5 - paramDouble1);
    paramDouble5 = d3 * (2.0D * d1) * MathUtil.hav(paramDouble6) + paramDouble5;
    paramDouble1 = Math.sin(paramDouble3 - paramDouble1);
    paramDouble1 = d1 * 2.0D * d2 * MathUtil.hav(d4) + paramDouble1;
    paramDouble3 = (paramDouble2 * paramDouble2 + paramDouble5 * paramDouble5) * (paramDouble4 * paramDouble4 + paramDouble1 * paramDouble1);
    if (paramDouble3 <= 0.0D) {
      return 1.0D;
    }
    return (paramDouble1 * paramDouble2 - paramDouble5 * paramDouble4) / Math.sqrt(paramDouble3);
  }
  
  private static double tanLatGC(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return (Math.tan(paramDouble1) * Math.sin(paramDouble3 - paramDouble4) + Math.tan(paramDouble2) * Math.sin(paramDouble4)) / Math.sin(paramDouble3);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/PolyUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */