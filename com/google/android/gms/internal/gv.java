package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class gv
  extends WebView
  implements DownloadListener
{
  private final WindowManager mG;
  private final Object mw = new Object();
  private ay qr;
  private final gt qs;
  private final k sX;
  private final gw wH;
  private final a wI;
  private dk wJ;
  private boolean wK;
  private boolean wL;
  private boolean wM;
  private boolean wN;
  
  protected gv(a parama, ay paramay, boolean paramBoolean1, boolean paramBoolean2, k paramk, gt paramgt)
  {
    super(parama);
    this.wI = parama;
    this.qr = paramay;
    this.wK = paramBoolean1;
    this.sX = paramk;
    this.qs = paramgt;
    this.mG = ((WindowManager)getContext().getSystemService("window"));
    setBackgroundColor(0);
    paramay = getSettings();
    paramay.setJavaScriptEnabled(true);
    paramay.setSavePassword(false);
    paramay.setSupportMultipleWindows(true);
    paramay.setJavaScriptCanOpenWindowsAutomatically(true);
    gj.a(parama, paramgt.wD, paramay);
    if (Build.VERSION.SDK_INT >= 17)
    {
      gp.a(getContext(), paramay);
      setDownloadListener(this);
      if (Build.VERSION.SDK_INT < 11) {
        break label194;
      }
      this.wH = new gy(this, paramBoolean2);
      label142:
      setWebViewClient(this.wH);
      if (Build.VERSION.SDK_INT < 14) {
        break label211;
      }
      setWebChromeClient(new gz(this));
    }
    for (;;)
    {
      dA();
      return;
      if (Build.VERSION.SDK_INT < 11) {
        break;
      }
      gn.a(getContext(), paramay);
      break;
      label194:
      this.wH = new gw(this, paramBoolean2);
      break label142;
      label211:
      if (Build.VERSION.SDK_INT >= 11) {
        setWebChromeClient(new gx(this));
      }
    }
  }
  
  public static gv a(Context paramContext, ay paramay, boolean paramBoolean1, boolean paramBoolean2, k paramk, gt paramgt)
  {
    return new gv(new a(paramContext), paramay, paramBoolean1, paramBoolean2, paramk, paramgt);
  }
  
  private void dA()
  {
    for (;;)
    {
      synchronized (this.mw)
      {
        if ((this.wK) || (this.qr.og))
        {
          if (Build.VERSION.SDK_INT < 14)
          {
            gs.S("Disabling hardware acceleration on an overlay.");
            dB();
            return;
          }
          gs.S("Enabling hardware acceleration on an overlay.");
          dC();
        }
      }
      if (Build.VERSION.SDK_INT < 18)
      {
        gs.S("Disabling hardware acceleration on an AdView.");
        dB();
      }
      else
      {
        gs.S("Enabling hardware acceleration on an AdView.");
        dC();
      }
    }
  }
  
  private void dB()
  {
    synchronized (this.mw)
    {
      if ((!this.wL) && (Build.VERSION.SDK_INT >= 11)) {
        gn.i(this);
      }
      this.wL = true;
      return;
    }
  }
  
  private void dC()
  {
    synchronized (this.mw)
    {
      if ((this.wL) && (Build.VERSION.SDK_INT >= 11)) {
        gn.j(this);
      }
      this.wL = false;
      return;
    }
  }
  
  protected void X(String paramString)
  {
    synchronized (this.mw)
    {
      if (!isDestroyed())
      {
        loadUrl(paramString);
        return;
      }
      gs.W("The webview is destroyed. Ignoring action.");
    }
  }
  
  public ay Y()
  {
    synchronized (this.mw)
    {
      ay localay = this.qr;
      return localay;
    }
  }
  
  public void a(Context paramContext, ay paramay)
  {
    synchronized (this.mw)
    {
      this.wI.setBaseContext(paramContext);
      this.wJ = null;
      this.qr = paramay;
      this.wK = false;
      this.wN = false;
      gj.b(this);
      loadUrl("about:blank");
      this.wH.reset();
      setOnTouchListener(null);
      setOnClickListener(null);
      return;
    }
  }
  
  public void a(ay paramay)
  {
    synchronized (this.mw)
    {
      this.qr = paramay;
      requestLayout();
      return;
    }
  }
  
  public void a(dk paramdk)
  {
    synchronized (this.mw)
    {
      this.wJ = paramdk;
      return;
    }
  }
  
  public void a(String paramString, Map<String, ?> paramMap)
  {
    try
    {
      paramMap = gj.t(paramMap);
      b(paramString, paramMap);
      return;
    }
    catch (JSONException paramString)
    {
      gs.W("Could not convert parameters to JSON.");
    }
  }
  
  public void a(String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      paramJSONObject = new JSONObject();
    }
    for (;;)
    {
      if (!(paramJSONObject instanceof JSONObject)) {}
      for (paramJSONObject = paramJSONObject.toString();; paramJSONObject = JSONObjectInstrumentation.toString((JSONObject)paramJSONObject))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("javascript:" + paramString + "(");
        localStringBuilder.append(paramJSONObject);
        localStringBuilder.append(");");
        X(localStringBuilder.toString());
        return;
      }
    }
  }
  
  public void b(String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      paramJSONObject = new JSONObject();
    }
    for (;;)
    {
      if (!(paramJSONObject instanceof JSONObject)) {}
      for (paramJSONObject = paramJSONObject.toString();; paramJSONObject = JSONObjectInstrumentation.toString((JSONObject)paramJSONObject))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("javascript:AFMA_ReceiveMessage('");
        localStringBuilder.append(paramString);
        localStringBuilder.append("'");
        localStringBuilder.append(",");
        localStringBuilder.append(paramJSONObject);
        localStringBuilder.append(");");
        gs.V("Dispatching AFMA event: " + localStringBuilder);
        X(localStringBuilder.toString());
        return;
      }
    }
  }
  
  public void bS()
  {
    if (!du().dE()) {
      return;
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    Display localDisplay = this.mG.getDefaultDisplay();
    localDisplay.getMetrics(localDisplayMetrics);
    int j = gj.s(getContext());
    float f = 160.0F / localDisplayMetrics.densityDpi;
    int i = Math.round(localDisplayMetrics.widthPixels * f);
    j = Math.round((localDisplayMetrics.heightPixels - j) * f);
    try
    {
      b("onScreenInfoChanged", new JSONObject().put("width", i).put("height", j).put("density", localDisplayMetrics.density).put("rotation", localDisplay.getRotation()));
      return;
    }
    catch (JSONException localJSONException)
    {
      gs.b("Error occured while obtaining screen information.", localJSONException);
    }
  }
  
  public void bZ()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.qs.wD);
    a("onshow", localHashMap);
  }
  
  public void ca()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.qs.wD);
    a("onhide", localHashMap);
  }
  
  public void destroy()
  {
    synchronized (this.mw)
    {
      super.destroy();
      this.wM = true;
      return;
    }
  }
  
  public dk dt()
  {
    synchronized (this.mw)
    {
      dk localdk = this.wJ;
      return localdk;
    }
  }
  
  public gw du()
  {
    return this.wH;
  }
  
  public boolean dv()
  {
    return this.wN;
  }
  
  public k dw()
  {
    return this.sX;
  }
  
  public gt dx()
  {
    return this.qs;
  }
  
  public boolean dy()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wK;
      return bool;
    }
  }
  
  public Context dz()
  {
    return this.wI.dz();
  }
  
  public void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback)
  {
    synchronized (this.mw)
    {
      if (isDestroyed())
      {
        gs.W("The webview is destroyed. Ignoring action.");
        if (paramValueCallback != null) {
          paramValueCallback.onReceiveValue(null);
        }
        return;
      }
      super.evaluateJavascript(paramString, paramValueCallback);
      return;
    }
  }
  
  public boolean isDestroyed()
  {
    synchronized (this.mw)
    {
      boolean bool = this.wM;
      return bool;
    }
  }
  
  public void o(boolean paramBoolean)
  {
    synchronized (this.mw)
    {
      if (this.wJ != null)
      {
        this.wJ.o(paramBoolean);
        return;
      }
      this.wN = paramBoolean;
    }
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    try
    {
      paramString2 = new Intent("android.intent.action.VIEW");
      paramString2.setDataAndType(Uri.parse(paramString1), paramString4);
      getContext().startActivity(paramString2);
      return;
    }
    catch (ActivityNotFoundException paramString2)
    {
      gs.S("Couldn't find an Activity to view url/mimetype: " + paramString1 + " / " + paramString4);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = Integer.MAX_VALUE;
    for (;;)
    {
      int i;
      int m;
      int k;
      synchronized (this.mw)
      {
        if ((isInEditMode()) || (this.wK))
        {
          super.onMeasure(paramInt1, paramInt2);
          return;
        }
        int n = View.MeasureSpec.getMode(paramInt1);
        i = View.MeasureSpec.getSize(paramInt1);
        m = View.MeasureSpec.getMode(paramInt2);
        k = View.MeasureSpec.getSize(paramInt2);
        if (n == Integer.MIN_VALUE) {
          break label280;
        }
        if (n != 1073741824) {
          break label273;
        }
        break label280;
        if ((this.qr.widthPixels > paramInt1) || (this.qr.heightPixels > paramInt2))
        {
          float f = this.wI.getResources().getDisplayMetrics().density;
          gs.W("Not enough space to show ad. Needs " + (int)(this.qr.widthPixels / f) + "x" + (int)(this.qr.heightPixels / f) + " dp, but only has " + (int)(i / f) + "x" + (int)(k / f) + " dp.");
          if (getVisibility() != 8) {
            setVisibility(4);
          }
          setMeasuredDimension(0, 0);
          return;
        }
      }
      if (getVisibility() != 8) {
        setVisibility(0);
      }
      setMeasuredDimension(this.qr.widthPixels, this.qr.heightPixels);
      continue;
      label273:
      paramInt1 = Integer.MAX_VALUE;
      break label283;
      label280:
      paramInt1 = i;
      label283:
      if (m != Integer.MIN_VALUE)
      {
        paramInt2 = j;
        if (m != 1073741824) {}
      }
      else
      {
        paramInt2 = k;
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.sX != null) {
      this.sX.a(paramMotionEvent);
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setContext(Context paramContext)
  {
    this.wI.setBaseContext(paramContext);
  }
  
  public void x(boolean paramBoolean)
  {
    synchronized (this.mw)
    {
      this.wK = paramBoolean;
      dA();
      return;
    }
  }
  
  @ez
  protected static class a
    extends MutableContextWrapper
  {
    private Context mD;
    private Activity wO;
    
    public a(Context paramContext)
    {
      super();
      setBaseContext(paramContext);
    }
    
    public Context dz()
    {
      return this.wO;
    }
    
    public void setBaseContext(Context paramContext)
    {
      this.mD = paramContext.getApplicationContext();
      if ((paramContext instanceof Activity)) {}
      for (paramContext = (Activity)paramContext;; paramContext = null)
      {
        this.wO = paramContext;
        super.setBaseContext(this.mD);
        return;
      }
    }
    
    public void startActivity(Intent paramIntent)
    {
      if (this.wO != null)
      {
        this.wO.startActivity(paramIntent);
        return;
      }
      paramIntent.setFlags(268435456);
      this.mD.startActivity(paramIntent);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/gv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */