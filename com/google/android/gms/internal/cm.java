package com.google.android.gms.internal;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public final class cm
{
  public final List<cl> qd;
  public final long qe;
  public final List<String> qf;
  public final List<String> qg;
  public final List<String> qh;
  public final String qi;
  public final long qj;
  public int qk;
  public int ql;
  
  public cm(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    Object localObject;
    if (gs.u(2))
    {
      localObject = new StringBuilder().append("Mediation Response JSON: ");
      if ((localJSONObject instanceof JSONObject)) {
        break label151;
      }
    }
    int j;
    label151:
    for (paramString = localJSONObject.toString(2);; paramString = JSONObjectInstrumentation.toString((JSONObject)localJSONObject, 2))
    {
      gs.V(paramString);
      paramString = localJSONObject.getJSONArray("ad_networks");
      localObject = new ArrayList(paramString.length());
      j = -1;
      int i = 0;
      while (i < paramString.length())
      {
        cl localcl = new cl(paramString.getJSONObject(i));
        ((List)localObject).add(localcl);
        int k = j;
        if (j < 0)
        {
          k = j;
          if (a(localcl)) {
            k = i;
          }
        }
        i += 1;
        j = k;
      }
    }
    this.qk = j;
    this.ql = paramString.length();
    this.qd = Collections.unmodifiableList((List)localObject);
    this.qi = localJSONObject.getString("qdata");
    paramString = localJSONObject.optJSONObject("settings");
    if (paramString != null)
    {
      this.qe = paramString.optLong("ad_network_timeout_millis", -1L);
      this.qf = cr.a(paramString, "click_urls");
      this.qg = cr.a(paramString, "imp_urls");
      this.qh = cr.a(paramString, "nofill_urls");
      long l = paramString.optLong("refresh", -1L);
      if (l > 0L) {}
      for (l *= 1000L;; l = -1L)
      {
        this.qj = l;
        return;
      }
    }
    this.qe = -1L;
    this.qf = null;
    this.qg = null;
    this.qh = null;
    this.qj = -1L;
  }
  
  private boolean a(cl paramcl)
  {
    paramcl = paramcl.pY.iterator();
    while (paramcl.hasNext()) {
      if (((String)paramcl.next()).equals("com.google.ads.mediation.admob.AdMobAdapter")) {
        return true;
      }
    }
    return false;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/cm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */