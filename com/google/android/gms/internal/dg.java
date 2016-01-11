package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class dg
{
  private final Context mContext;
  private final WindowManager mG;
  private final gv md;
  private final bl rg;
  DisplayMetrics rh;
  private float ri;
  int rj = -1;
  int rk = -1;
  private int rl;
  private int rm = -1;
  private int rn = -1;
  private int[] ro = new int[2];
  
  public dg(gv paramgv, Context paramContext, bl parambl)
  {
    this.md = paramgv;
    this.mContext = paramContext;
    this.rg = parambl;
    this.mG = ((WindowManager)paramContext.getSystemService("window"));
    bM();
    bN();
    bO();
  }
  
  private void bM()
  {
    this.rh = new DisplayMetrics();
    Display localDisplay = this.mG.getDefaultDisplay();
    localDisplay.getMetrics(this.rh);
    this.ri = this.rh.density;
    this.rl = localDisplay.getRotation();
  }
  
  private void bO()
  {
    this.md.getLocationOnScreen(this.ro);
    this.md.measure(0, 0);
    float f = 160.0F / this.rh.densityDpi;
    this.rm = Math.round(this.md.getMeasuredWidth() * f);
    this.rn = Math.round(f * this.md.getMeasuredHeight());
  }
  
  private df bU()
  {
    return new df.a().j(this.rg.bj()).i(this.rg.bk()).k(this.rg.bo()).l(this.rg.bl()).m(this.rg.bm()).bL();
  }
  
  void bN()
  {
    int i = gj.s(this.mContext);
    float f = 160.0F / this.rh.densityDpi;
    this.rj = Math.round(this.rh.widthPixels * f);
    this.rk = Math.round((this.rh.heightPixels - i) * f);
  }
  
  public void bP()
  {
    bS();
    bT();
    bR();
    bQ();
  }
  
  public void bQ()
  {
    if (gs.u(2)) {
      gs.U("Dispatching Ready Event.");
    }
    this.md.b("onReadyEventReceived", new JSONObject());
  }
  
  public void bR()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("x", this.ro[0]).put("y", this.ro[1]).put("width", this.rm).put("height", this.rn);
      this.md.b("onDefaultPositionReceived", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      gs.b("Error occured while dispatching default position.", localJSONException);
    }
  }
  
  public void bS()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("width", this.rj).put("height", this.rk).put("density", this.ri).put("rotation", this.rl);
      this.md.b("onScreenInfoChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      gs.b("Error occured while obtaining screen information.", localJSONException);
    }
  }
  
  public void bT()
  {
    df localdf = bU();
    this.md.b("onDeviceFeaturesReceived", localdf.bK());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */