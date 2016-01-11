package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c.c;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ai
{
  private static void a(DataLayer paramDataLayer, c.d paramd)
  {
    paramd = paramd.fB;
    int j = paramd.length;
    int i = 0;
    while (i < j)
    {
      paramDataLayer.cv(di.j(paramd[i]));
      i += 1;
    }
  }
  
  public static void a(DataLayer paramDataLayer, c.i parami)
  {
    if (parami.gq == null)
    {
      bh.W("supplemental missing experimentSupplemental");
      return;
    }
    a(paramDataLayer, parami.gq);
    b(paramDataLayer, parami.gq);
    c(paramDataLayer, parami.gq);
  }
  
  private static void b(DataLayer paramDataLayer, c.d paramd)
  {
    paramd = paramd.fA;
    int j = paramd.length;
    int i = 0;
    while (i < j)
    {
      Map localMap = c(paramd[i]);
      if (localMap != null) {
        paramDataLayer.push(localMap);
      }
      i += 1;
    }
  }
  
  private static Map<String, Object> c(d.a parama)
  {
    parama = di.o(parama);
    if (!(parama instanceof Map))
    {
      bh.W("value: " + parama + " is not a map value, ignored.");
      return null;
    }
    return (Map)parama;
  }
  
  private static void c(DataLayer paramDataLayer, c.d paramd)
  {
    c.c[] arrayOfc = paramd.fC;
    int j = arrayOfc.length;
    int i = 0;
    while (i < j)
    {
      c.c localc = arrayOfc[i];
      if (localc.fv == null)
      {
        bh.W("GaExperimentRandom: No key");
        i += 1;
      }
      else
      {
        Object localObject = paramDataLayer.get(localc.fv);
        if (!(localObject instanceof Number))
        {
          paramd = null;
          label64:
          long l1 = localc.fw;
          long l2 = localc.fx;
          if ((!localc.fy) || (paramd == null) || (paramd.longValue() < l1) || (paramd.longValue() > l2))
          {
            if (l1 > l2) {
              break label237;
            }
            localObject = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
          }
          paramDataLayer.cv(localc.fv);
          paramd = paramDataLayer.c(localc.fv, localObject);
          if (localc.fz > 0L)
          {
            if (paramd.containsKey("gtm")) {
              break label245;
            }
            paramd.put("gtm", DataLayer.mapOf(new Object[] { "lifetime", Long.valueOf(localc.fz) }));
          }
        }
        for (;;)
        {
          paramDataLayer.push(paramd);
          break;
          paramd = Long.valueOf(((Number)localObject).longValue());
          break label64;
          label237:
          bh.W("GaExperimentRandom: random range invalid");
          break;
          label245:
          localObject = paramd.get("gtm");
          if ((localObject instanceof Map)) {
            ((Map)localObject).put("lifetime", Long.valueOf(localc.fz));
          } else {
            bh.W("GaExperimentRandom: gtm not a map");
          }
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */