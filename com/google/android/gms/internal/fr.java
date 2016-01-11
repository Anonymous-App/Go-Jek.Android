package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@ez
public final class fr
  extends fm.a
{
  private static final Object uf = new Object();
  private static fr ug;
  private final Context mContext;
  private final fx uh;
  private final ci ui;
  private final bm uj;
  
  fr(Context paramContext, bm parambm, ci paramci, fx paramfx)
  {
    this.mContext = paramContext;
    this.uh = paramfx;
    this.ui = paramci;
    this.uj = parambm;
  }
  
  private static gw.a I(String paramString)
  {
    new gw.a()
    {
      public void a(gv paramAnonymousgv)
      {
        String str = String.format("javascript:%s(%s);", new Object[] { "AFMA_buildAdURL", this.uo });
        gs.V("About to execute: " + str);
        paramAnonymousgv.loadUrl(str);
      }
    };
  }
  
  private static fk a(Context paramContext, final bm parambm, final ci paramci, fx paramfx, final fi paramfi)
  {
    gs.S("Starting ad request from service.");
    paramci.init();
    fw localfw = new fw(paramContext);
    if (localfw.vd == -1)
    {
      gs.S("Device is offline.");
      return new fk(2);
    }
    final ft localft = new ft(paramfi.applicationInfo.packageName);
    if (paramfi.tx.extras != null)
    {
      localObject = paramfi.tx.extras.getString("_ad");
      if (localObject != null) {
        return fs.a(paramContext, paramfi, (String)localObject);
      }
    }
    Object localObject = paramci.a(250L);
    paramci = parambm.bp();
    parambm = fs.a(paramfi, localfw, (Location)localObject, parambm.bq(), parambm.br());
    if (parambm == null) {
      return new fk(0);
    }
    parambm = I(parambm);
    gr.wC.post(new Runnable()
    {
      public void run()
      {
        gv localgv = gv.a(this.mV, new ay(), false, false, null, paramfi.lD);
        localgv.setWillNotDraw(true);
        localft.b(localgv);
        gw localgw = localgv.du();
        localgw.a("/invalidRequest", localft.us);
        localgw.a("/loadAdURL", localft.ut);
        localgw.a("/log", bx.pG);
        localgw.a(parambm);
        gs.S("Loading the JS library.");
        localgv.loadUrl(paramci);
      }
    });
    try
    {
      paramci = (fv)localft.cK().get(10L, TimeUnit.SECONDS);
      if (paramci == null) {
        return new fk(0);
      }
    }
    catch (Exception paramContext)
    {
      return new fk(0);
    }
    if (paramci.getErrorCode() != -2) {
      return new fk(paramci.getErrorCode());
    }
    parambm = null;
    if (paramci.cN()) {
      parambm = paramfx.K(paramfi.ty.packageName);
    }
    return a(paramContext, paramfi.lD.wD, paramci.getUrl(), parambm, paramci);
  }
  
  public static fk a(Context paramContext, String paramString1, String paramString2, String paramString3, fv paramfv)
  {
    try
    {
      localfu = new fu();
      gs.S("AdRequestServiceImpl: Sending request: " + paramString2);
      paramString2 = new URL(paramString2);
      l = SystemClock.elapsedRealtime();
      i = 0;
    }
    catch (IOException paramContext)
    {
      try
      {
        for (;;)
        {
          fu localfu;
          long l;
          int i;
          gj.a(paramContext, paramString1, false, localHttpURLConnection);
          if (!TextUtils.isEmpty(paramString3)) {
            localHttpURLConnection.addRequestProperty("x-afma-drt-cookie", paramString3);
          }
          if ((paramfv != null) && (!TextUtils.isEmpty(paramfv.cM())))
          {
            localHttpURLConnection.setDoOutput(true);
            localObject = paramfv.cM().getBytes();
            localHttpURLConnection.setFixedLengthStreamingMode(localObject.length);
            BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localHttpURLConnection.getOutputStream());
            localBufferedOutputStream.write((byte[])localObject);
            localBufferedOutputStream.close();
          }
          int j = localHttpURLConnection.getResponseCode();
          Object localObject = localHttpURLConnection.getHeaderFields();
          if ((j >= 200) && (j < 300))
          {
            paramContext = paramString2.toString();
            paramString1 = gj.a(new InputStreamReader(localHttpURLConnection.getInputStream()));
            a(paramContext, (Map)localObject, paramString1, j);
            localfu.a(paramContext, (Map)localObject, paramString1);
            paramContext = localfu.i(l);
            return paramContext;
          }
          a(paramString2.toString(), (Map)localObject, null, j);
          if ((j >= 300) && (j < 400))
          {
            paramString2 = localHttpURLConnection.getHeaderField("Location");
            if (TextUtils.isEmpty(paramString2))
            {
              gs.W("No location header to follow redirect.");
              paramContext = new fk(0);
              return paramContext;
            }
            paramString2 = new URL(paramString2);
            i += 1;
            if (i > 5)
            {
              gs.W("Too many redirects.");
              paramContext = new fk(0);
              return paramContext;
            }
          }
          else
          {
            gs.W("Received error HTTP response code: " + j);
            paramContext = new fk(0);
            return paramContext;
          }
          localfu.e((Map)localObject);
          localHttpURLConnection.disconnect();
        }
      }
      finally
      {
        HttpURLConnection localHttpURLConnection;
        localHttpURLConnection.disconnect();
      }
      paramContext = paramContext;
      gs.W("Error while connecting to ad server: " + paramContext.getMessage());
      return new fk(2);
    }
    localHttpURLConnection = (HttpURLConnection)HttpInstrumentation.openConnection(paramString2.openConnection());
  }
  
  public static fr a(Context paramContext, bm parambm, ci paramci, fx paramfx)
  {
    synchronized (uf)
    {
      if (ug == null) {
        ug = new fr(paramContext.getApplicationContext(), parambm, paramci, paramfx);
      }
      paramContext = ug;
      return paramContext;
    }
  }
  
  private static void a(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (gs.u(2))
    {
      gs.V("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        paramString1 = paramMap.keySet().iterator();
        while (paramString1.hasNext())
        {
          Object localObject = (String)paramString1.next();
          gs.V("    " + (String)localObject + ":");
          localObject = ((List)paramMap.get(localObject)).iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str = (String)((Iterator)localObject).next();
            gs.V("      " + str);
          }
        }
      }
      gs.V("  Body:");
      if (paramString2 != null)
      {
        int i = 0;
        while (i < Math.min(paramString2.length(), 100000))
        {
          gs.V(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
          i += 1000;
        }
      }
      gs.V("    null");
      gs.V("  Response Code:\n    " + paramInt + "\n}");
    }
  }
  
  public fk b(fi paramfi)
  {
    return a(this.mContext, this.uj, this.ui, this.uh, paramfi);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/fr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */