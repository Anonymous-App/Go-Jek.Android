package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class di
{
  private static final Object arU = null;
  private static Long arV = new Long(0L);
  private static Double arW = new Double(0.0D);
  private static dh arX = dh.z(0L);
  private static String arY = new String("");
  private static Boolean arZ = new Boolean(false);
  private static List<Object> asa = new ArrayList(0);
  private static Map<Object, Object> asb = new HashMap();
  private static d.a asc = u(arY);
  
  public static d.a cX(String paramString)
  {
    d.a locala = new d.a();
    locala.type = 5;
    locala.gA = paramString;
    return locala;
  }
  
  private static dh cY(String paramString)
  {
    try
    {
      dh localdh = dh.cW(paramString);
      return localdh;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      bh.T("Failed to convert '" + paramString + "' to a number.");
    }
    return arX;
  }
  
  private static Long cZ(String paramString)
  {
    paramString = cY(paramString);
    if (paramString == arX) {
      return arV;
    }
    return Long.valueOf(paramString.longValue());
  }
  
  private static Double da(String paramString)
  {
    paramString = cY(paramString);
    if (paramString == arX) {
      return arW;
    }
    return Double.valueOf(paramString.doubleValue());
  }
  
  private static Boolean db(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return Boolean.FALSE;
    }
    return arZ;
  }
  
  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).doubleValue();
    }
    bh.T("getDouble received non-Number");
    return 0.0D;
  }
  
  public static String j(d.a parama)
  {
    return p(o(parama));
  }
  
  public static dh k(d.a parama)
  {
    return q(o(parama));
  }
  
  public static Long l(d.a parama)
  {
    return r(o(parama));
  }
  
  public static Double m(d.a parama)
  {
    return s(o(parama));
  }
  
  public static Boolean n(d.a parama)
  {
    return t(o(parama));
  }
  
  public static Object o(d.a parama)
  {
    int k = 0;
    int j = 0;
    int i = 0;
    if (parama == null) {
      return arU;
    }
    Object localObject1;
    Object localObject2;
    switch (parama.type)
    {
    default: 
      bh.T("Failed to convert a value of type: " + parama.type);
      return arU;
    case 1: 
      return parama.gv;
    case 2: 
      localObject1 = new ArrayList(parama.gw.length);
      parama = parama.gw;
      j = parama.length;
      while (i < j)
      {
        localObject2 = o(parama[i]);
        if (localObject2 == arU) {
          return arU;
        }
        ((ArrayList)localObject1).add(localObject2);
        i += 1;
      }
      return localObject1;
    case 3: 
      if (parama.gx.length != parama.gy.length)
      {
        bh.T("Converting an invalid value to object: " + parama.toString());
        return arU;
      }
      localObject1 = new HashMap(parama.gy.length);
      i = k;
      while (i < parama.gx.length)
      {
        localObject2 = o(parama.gx[i]);
        Object localObject3 = o(parama.gy[i]);
        if ((localObject2 == arU) || (localObject3 == arU)) {
          return arU;
        }
        ((Map)localObject1).put(localObject2, localObject3);
        i += 1;
      }
      return localObject1;
    case 4: 
      bh.T("Trying to convert a macro reference to object");
      return arU;
    case 5: 
      bh.T("Trying to convert a function id to object");
      return arU;
    case 6: 
      return Long.valueOf(parama.gB);
    case 7: 
      localObject1 = new StringBuffer();
      parama = parama.gD;
      k = parama.length;
      i = j;
      while (i < k)
      {
        localObject2 = j(parama[i]);
        if (localObject2 == arY) {
          return arU;
        }
        ((StringBuffer)localObject1).append((String)localObject2);
        i += 1;
      }
      return ((StringBuffer)localObject1).toString();
    }
    return Boolean.valueOf(parama.gC);
  }
  
  public static String p(Object paramObject)
  {
    if (paramObject == null) {
      return arY;
    }
    return paramObject.toString();
  }
  
  public static Object pE()
  {
    return arU;
  }
  
  public static Long pF()
  {
    return arV;
  }
  
  public static Double pG()
  {
    return arW;
  }
  
  public static Boolean pH()
  {
    return arZ;
  }
  
  public static dh pI()
  {
    return arX;
  }
  
  public static String pJ()
  {
    return arY;
  }
  
  public static d.a pK()
  {
    return asc;
  }
  
  public static dh q(Object paramObject)
  {
    if ((paramObject instanceof dh)) {
      return (dh)paramObject;
    }
    if (w(paramObject)) {
      return dh.z(x(paramObject));
    }
    if (v(paramObject)) {
      return dh.a(Double.valueOf(getDouble(paramObject)));
    }
    return cY(p(paramObject));
  }
  
  public static Long r(Object paramObject)
  {
    if (w(paramObject)) {
      return Long.valueOf(x(paramObject));
    }
    return cZ(p(paramObject));
  }
  
  public static Double s(Object paramObject)
  {
    if (v(paramObject)) {
      return Double.valueOf(getDouble(paramObject));
    }
    return da(p(paramObject));
  }
  
  public static Boolean t(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return (Boolean)paramObject;
    }
    return db(p(paramObject));
  }
  
  public static d.a u(Object paramObject)
  {
    boolean bool = false;
    Object localObject1 = new d.a();
    if ((paramObject instanceof d.a)) {
      return (d.a)paramObject;
    }
    if ((paramObject instanceof String))
    {
      ((d.a)localObject1).type = 1;
      ((d.a)localObject1).gv = ((String)paramObject);
    }
    for (;;)
    {
      ((d.a)localObject1).gF = bool;
      return (d.a)localObject1;
      Object localObject2;
      Object localObject3;
      if ((paramObject instanceof List))
      {
        ((d.a)localObject1).type = 2;
        localObject2 = (List)paramObject;
        paramObject = new ArrayList(((List)localObject2).size());
        localObject2 = ((List)localObject2).iterator();
        bool = false;
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = u(((Iterator)localObject2).next());
          if (localObject3 == asc) {
            return asc;
          }
          if ((bool) || (((d.a)localObject3).gF)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localObject3);
            break;
          }
        }
        ((d.a)localObject1).gw = ((d.a[])((List)paramObject).toArray(new d.a[0]));
      }
      else if ((paramObject instanceof Map))
      {
        ((d.a)localObject1).type = 3;
        localObject3 = ((Map)paramObject).entrySet();
        paramObject = new ArrayList(((Set)localObject3).size());
        localObject2 = new ArrayList(((Set)localObject3).size());
        localObject3 = ((Set)localObject3).iterator();
        bool = false;
        if (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = (Map.Entry)((Iterator)localObject3).next();
          d.a locala = u(((Map.Entry)localObject4).getKey());
          localObject4 = u(((Map.Entry)localObject4).getValue());
          if ((locala == asc) || (localObject4 == asc)) {
            return asc;
          }
          if ((bool) || (locala.gF) || (((d.a)localObject4).gF)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(locala);
            ((List)localObject2).add(localObject4);
            break;
          }
        }
        ((d.a)localObject1).gx = ((d.a[])((List)paramObject).toArray(new d.a[0]));
        ((d.a)localObject1).gy = ((d.a[])((List)localObject2).toArray(new d.a[0]));
      }
      else if (v(paramObject))
      {
        ((d.a)localObject1).type = 1;
        ((d.a)localObject1).gv = paramObject.toString();
      }
      else if (w(paramObject))
      {
        ((d.a)localObject1).type = 6;
        ((d.a)localObject1).gB = x(paramObject);
      }
      else
      {
        if (!(paramObject instanceof Boolean)) {
          break;
        }
        ((d.a)localObject1).type = 8;
        ((d.a)localObject1).gC = ((Boolean)paramObject).booleanValue();
      }
    }
    localObject1 = new StringBuilder().append("Converting to Value from unknown object type: ");
    if (paramObject == null) {}
    for (paramObject = "null";; paramObject = paramObject.getClass().toString())
    {
      bh.T((String)paramObject);
      return asc;
    }
  }
  
  private static boolean v(Object paramObject)
  {
    return ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof dh)) && (((dh)paramObject).pz()));
  }
  
  private static boolean w(Object paramObject)
  {
    return ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof dh)) && (((dh)paramObject).pA()));
  }
  
  private static long x(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).longValue();
    }
    bh.T("getInt64 received non-Number");
    return 0L;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */