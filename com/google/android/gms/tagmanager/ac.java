package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ac
  extends aj
{
  private static final String ID = a.aa.toString();
  private static final String apf = b.bw.toString();
  private static final String apg = b.dH.toString();
  private static final String aph = b.de.toString();
  private static final String api = b.dP.toString();
  
  public ac()
  {
    super(ID, new String[] { apf });
  }
  
  public d.a C(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(apf);
    if ((localObject == null) || (localObject == di.pK())) {
      return di.pK();
    }
    String str2 = di.j((d.a)localObject);
    localObject = (d.a)paramMap.get(aph);
    String str1;
    if (localObject == null)
    {
      str1 = "text";
      localObject = (d.a)paramMap.get(api);
      if (localObject != null) {
        break label148;
      }
      localObject = "base16";
      label75:
      paramMap = (d.a)paramMap.get(apg);
      if ((paramMap == null) || (!di.n(paramMap).booleanValue())) {
        break label322;
      }
    }
    label148:
    label257:
    label296:
    label322:
    for (int i = 3;; i = 2)
    {
      for (;;)
      {
        try
        {
          if ("text".equals(str1))
          {
            paramMap = str2.getBytes();
            if (!"base16".equals(localObject)) {
              break label257;
            }
            paramMap = j.d(paramMap);
            return di.u(paramMap);
            str1 = di.j((d.a)localObject);
            break;
            localObject = di.j((d.a)localObject);
            break label75;
          }
          if ("base16".equals(str1))
          {
            paramMap = j.cm(str2);
            continue;
          }
          if ("base64".equals(str1))
          {
            paramMap = Base64.decode(str2, i);
            continue;
          }
          if ("base64url".equals(str1))
          {
            paramMap = Base64.decode(str2, i | 0x8);
            continue;
          }
          bh.T("Encode: unknown input format: " + str1);
          paramMap = di.pK();
          return paramMap;
        }
        catch (IllegalArgumentException paramMap)
        {
          bh.T("Encode: invalid input:");
          return di.pK();
        }
        if ("base64".equals(localObject))
        {
          paramMap = Base64.encodeToString(paramMap, i);
        }
        else
        {
          if (!"base64url".equals(localObject)) {
            break label296;
          }
          paramMap = Base64.encodeToString(paramMap, i | 0x8);
        }
      }
      bh.T("Encode: unknown output format: " + (String)localObject);
      return di.pK();
    }
  }
  
  public boolean nN()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */