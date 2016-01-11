package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

@ez
public final class ce
  implements by
{
  private static int a(DisplayMetrics paramDisplayMetrics, Map<String, String> paramMap, String paramString, int paramInt)
  {
    paramMap = (String)paramMap.get(paramString);
    int i = paramInt;
    if (paramMap != null) {}
    try
    {
      i = gr.a(paramDisplayMetrics, Integer.parseInt(paramMap));
      return i;
    }
    catch (NumberFormatException paramDisplayMetrics)
    {
      gs.W("Could not parse " + paramString + " in a video GMSG: " + paramMap);
    }
    return paramInt;
  }
  
  public void a(gv paramgv, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("action");
    if (str == null)
    {
      gs.W("Action missing from video GMSG.");
      return;
    }
    Object localObject = paramgv.dt();
    if (localObject == null)
    {
      gs.W("Could not get ad overlay for a video GMSG.");
      return;
    }
    boolean bool1 = "new".equalsIgnoreCase(str);
    boolean bool2 = "position".equalsIgnoreCase(str);
    int i;
    int j;
    if ((bool1) || (bool2))
    {
      paramgv = paramgv.getContext().getResources().getDisplayMetrics();
      i = a(paramgv, paramMap, "x", 0);
      j = a(paramgv, paramMap, "y", 0);
      int k = a(paramgv, paramMap, "w", -1);
      int m = a(paramgv, paramMap, "h", -1);
      if ((bool1) && (((dk)localObject).bV() == null))
      {
        ((dk)localObject).c(i, j, k, m);
        return;
      }
      ((dk)localObject).b(i, j, k, m);
      return;
    }
    localObject = ((dk)localObject).bV();
    if (localObject == null)
    {
      do.a(paramgv, "no_video_view", null);
      return;
    }
    if ("click".equalsIgnoreCase(str))
    {
      paramgv = paramgv.getContext().getResources().getDisplayMetrics();
      i = a(paramgv, paramMap, "x", 0);
      j = a(paramgv, paramMap, "y", 0);
      long l = SystemClock.uptimeMillis();
      paramgv = MotionEvent.obtain(l, l, 0, i, j, 0);
      ((do)localObject).b(paramgv);
      paramgv.recycle();
      return;
    }
    if ("controls".equalsIgnoreCase(str))
    {
      paramgv = (String)paramMap.get("enabled");
      if (paramgv == null)
      {
        gs.W("Enabled parameter missing from controls video GMSG.");
        return;
      }
      ((do)localObject).q(Boolean.parseBoolean(paramgv));
      return;
    }
    if ("currentTime".equalsIgnoreCase(str))
    {
      paramgv = (String)paramMap.get("time");
      if (paramgv == null)
      {
        gs.W("Time parameter missing from currentTime video GMSG.");
        return;
      }
      try
      {
        ((do)localObject).seekTo((int)(Float.parseFloat(paramgv) * 1000.0F));
        return;
      }
      catch (NumberFormatException paramMap)
      {
        gs.W("Could not parse time parameter from currentTime video GMSG: " + paramgv);
        return;
      }
    }
    if ("hide".equalsIgnoreCase(str))
    {
      ((do)localObject).setVisibility(4);
      return;
    }
    if ("load".equalsIgnoreCase(str))
    {
      ((do)localObject).ch();
      return;
    }
    if ("pause".equalsIgnoreCase(str))
    {
      ((do)localObject).pause();
      return;
    }
    if ("play".equalsIgnoreCase(str))
    {
      ((do)localObject).play();
      return;
    }
    if ("show".equalsIgnoreCase(str))
    {
      ((do)localObject).setVisibility(0);
      return;
    }
    if ("src".equalsIgnoreCase(str))
    {
      ((do)localObject).C((String)paramMap.get("src"));
      return;
    }
    gs.W("Unknown video action: " + str);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */