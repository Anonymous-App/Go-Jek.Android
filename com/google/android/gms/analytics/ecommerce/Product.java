package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.n;
import com.google.android.gms.common.internal.o;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Product
{
  Map<String, String> BK = new HashMap();
  
  public Map<String, String> aq(String paramString)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.BK.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put(paramString + (String)localEntry.getKey(), localEntry.getValue());
    }
    return localHashMap;
  }
  
  void put(String paramString1, String paramString2)
  {
    o.b(paramString1, "Name should be non-null");
    this.BK.put(paramString1, paramString2);
  }
  
  public Product setBrand(String paramString)
  {
    put("br", paramString);
    return this;
  }
  
  public Product setCategory(String paramString)
  {
    put("ca", paramString);
    return this;
  }
  
  public Product setCouponCode(String paramString)
  {
    put("cc", paramString);
    return this;
  }
  
  public Product setCustomDimension(int paramInt, String paramString)
  {
    put(n.D(paramInt), paramString);
    return this;
  }
  
  public Product setCustomMetric(int paramInt1, int paramInt2)
  {
    put(n.E(paramInt1), Integer.toString(paramInt2));
    return this;
  }
  
  public Product setId(String paramString)
  {
    put("id", paramString);
    return this;
  }
  
  public Product setName(String paramString)
  {
    put("nm", paramString);
    return this;
  }
  
  public Product setPosition(int paramInt)
  {
    put("ps", Integer.toString(paramInt));
    return this;
  }
  
  public Product setPrice(double paramDouble)
  {
    put("pr", Double.toString(paramDouble));
    return this;
  }
  
  public Product setQuantity(int paramInt)
  {
    put("qt", Integer.toString(paramInt));
    return this;
  }
  
  public Product setVariant(String paramString)
  {
    put("va", paramString);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/ecommerce/Product.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */