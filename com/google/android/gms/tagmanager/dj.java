package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class dj
  extends dg
{
  private static final String ID = a.aO.toString();
  private static final String asd = b.bj.toString();
  private static final String ase = b.bs.toString();
  private static final String asf = b.cE.toString();
  private static final String asg = b.cz.toString();
  private static final String ash = b.cy.toString();
  private static final String asi = b.br.toString();
  private static final String asj = b.eN.toString();
  private static final String ask = b.eQ.toString();
  private static final String asl = b.eS.toString();
  private static final List<String> asm = Arrays.asList(new String[] { "detail", "checkout", "checkout_option", "click", "add", "remove", "purchase", "refund" });
  private static Map<String, String> asn;
  private static Map<String, String> aso;
  private final DataLayer aod;
  private final Set<String> asp;
  private final df asq;
  
  public dj(Context paramContext, DataLayer paramDataLayer)
  {
    this(paramContext, paramDataLayer, new df(paramContext));
  }
  
  dj(Context paramContext, DataLayer paramDataLayer, df paramdf)
  {
    super(ID, new String[0]);
    this.aod = paramDataLayer;
    this.asq = paramdf;
    this.asp = new HashSet();
    this.asp.add("");
    this.asp.add("0");
    this.asp.add("false");
  }
  
  private Promotion M(Map<String, String> paramMap)
  {
    Promotion localPromotion = new Promotion();
    String str = (String)paramMap.get("id");
    if (str != null) {
      localPromotion.setId(String.valueOf(str));
    }
    str = (String)paramMap.get("name");
    if (str != null) {
      localPromotion.setName(String.valueOf(str));
    }
    str = (String)paramMap.get("creative");
    if (str != null) {
      localPromotion.setCreative(String.valueOf(str));
    }
    paramMap = (String)paramMap.get("position");
    if (paramMap != null) {
      localPromotion.setPosition(String.valueOf(paramMap));
    }
    return localPromotion;
  }
  
  private Product N(Map<String, Object> paramMap)
  {
    Product localProduct = new Product();
    Object localObject = paramMap.get("id");
    if (localObject != null) {
      localProduct.setId(String.valueOf(localObject));
    }
    localObject = paramMap.get("name");
    if (localObject != null) {
      localProduct.setName(String.valueOf(localObject));
    }
    localObject = paramMap.get("brand");
    if (localObject != null) {
      localProduct.setBrand(String.valueOf(localObject));
    }
    localObject = paramMap.get("category");
    if (localObject != null) {
      localProduct.setCategory(String.valueOf(localObject));
    }
    localObject = paramMap.get("variant");
    if (localObject != null) {
      localProduct.setVariant(String.valueOf(localObject));
    }
    localObject = paramMap.get("coupon");
    if (localObject != null) {
      localProduct.setCouponCode(String.valueOf(localObject));
    }
    localObject = paramMap.get("position");
    if (localObject != null) {
      localProduct.setPosition(z(localObject).intValue());
    }
    localObject = paramMap.get("price");
    if (localObject != null) {
      localProduct.setPrice(y(localObject).doubleValue());
    }
    paramMap = paramMap.get("quantity");
    if (paramMap != null) {
      localProduct.setQuantity(z(paramMap).intValue());
    }
    return localProduct;
  }
  
  private Map<String, String> O(Map<String, d.a> paramMap)
  {
    paramMap = (d.a)paramMap.get(ask);
    if (paramMap != null) {
      return c(paramMap);
    }
    if (asn == null)
    {
      paramMap = new HashMap();
      paramMap.put("transactionId", "&ti");
      paramMap.put("transactionAffiliation", "&ta");
      paramMap.put("transactionTax", "&tt");
      paramMap.put("transactionShipping", "&ts");
      paramMap.put("transactionTotal", "&tr");
      paramMap.put("transactionCurrency", "&cu");
      asn = paramMap;
    }
    return asn;
  }
  
  private Map<String, String> P(Map<String, d.a> paramMap)
  {
    paramMap = (d.a)paramMap.get(asl);
    if (paramMap != null) {
      return c(paramMap);
    }
    if (aso == null)
    {
      paramMap = new HashMap();
      paramMap.put("name", "&in");
      paramMap.put("sku", "&ic");
      paramMap.put("category", "&iv");
      paramMap.put("price", "&ip");
      paramMap.put("quantity", "&iq");
      paramMap.put("currency", "&cu");
      aso = paramMap;
    }
    return aso;
  }
  
  private void a(Tracker paramTracker, Map<String, d.a> paramMap)
  {
    String str = dc("transactionId");
    if (str == null) {
      bh.T("Cannot find transactionId in data layer.");
    }
    for (;;)
    {
      return;
      LinkedList localLinkedList = new LinkedList();
      Object localObject2;
      Object localObject3;
      try
      {
        localObject1 = p((d.a)paramMap.get(asi));
        ((Map)localObject1).put("&t", "transaction");
        localObject2 = O(paramMap).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          b((Map)localObject1, (String)((Map.Entry)localObject3).getValue(), dc((String)((Map.Entry)localObject3).getKey()));
        }
        localLinkedList.add(localObject1);
      }
      catch (IllegalArgumentException paramTracker)
      {
        bh.b("Unable to send transaction", paramTracker);
        return;
      }
      Object localObject1 = dd("transactionProducts");
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map)((Iterator)localObject1).next();
          if (((Map)localObject2).get("name") == null)
          {
            bh.T("Unable to send transaction item hit due to missing 'name' field.");
            return;
          }
          localObject3 = p((d.a)paramMap.get(asi));
          ((Map)localObject3).put("&t", "item");
          ((Map)localObject3).put("&ti", str);
          Iterator localIterator = P(paramMap).entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            b((Map)localObject3, (String)localEntry.getValue(), (String)((Map)localObject2).get(localEntry.getKey()));
          }
          localLinkedList.add(localObject3);
        }
      }
      paramMap = localLinkedList.iterator();
      while (paramMap.hasNext()) {
        paramTracker.send((Map)paramMap.next());
      }
    }
  }
  
  private void b(Tracker paramTracker, Map<String, d.a> paramMap)
  {
    HitBuilders.ScreenViewBuilder localScreenViewBuilder = new HitBuilders.ScreenViewBuilder();
    Object localObject1 = p((d.a)paramMap.get(asi));
    localScreenViewBuilder.setAll((Map)localObject1);
    if (f(paramMap, asg))
    {
      paramMap = this.aod.get("ecommerce");
      if (!(paramMap instanceof Map)) {
        break label722;
      }
      paramMap = (Map)paramMap;
    }
    for (;;)
    {
      if (paramMap != null)
      {
        Object localObject2 = (String)((Map)localObject1).get("&cu");
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = (String)paramMap.get("currencyCode");
        }
        if (localObject1 != null) {
          localScreenViewBuilder.set("&cu", (String)localObject1);
        }
        localObject1 = paramMap.get("impressions");
        if ((localObject1 instanceof List))
        {
          localObject1 = ((List)localObject1).iterator();
          for (;;)
          {
            if (((Iterator)localObject1).hasNext())
            {
              localObject2 = (Map)((Iterator)localObject1).next();
              try
              {
                localScreenViewBuilder.addImpression(N((Map)localObject2), (String)((Map)localObject2).get("list"));
              }
              catch (RuntimeException localRuntimeException1)
              {
                bh.T("Failed to extract a product from DataLayer. " + localRuntimeException1.getMessage());
              }
              continue;
              paramMap = di.o((d.a)paramMap.get(ash));
              if (!(paramMap instanceof Map)) {
                break label717;
              }
              paramMap = (Map)paramMap;
              break;
            }
          }
        }
        if (paramMap.containsKey("promoClick")) {
          localObject1 = (List)((Map)paramMap.get("promoClick")).get("promotions");
        }
      }
      for (;;)
      {
        if (localObject1 != null)
        {
          localObject1 = ((List)localObject1).iterator();
          for (;;)
          {
            if (((Iterator)localObject1).hasNext())
            {
              Map localMap1 = (Map)((Iterator)localObject1).next();
              try
              {
                localScreenViewBuilder.addPromotion(M(localMap1));
              }
              catch (RuntimeException localRuntimeException2)
              {
                bh.T("Failed to extract a promotion from DataLayer. " + localRuntimeException2.getMessage());
              }
              continue;
              if (!paramMap.containsKey("promoView")) {
                break label711;
              }
              localObject1 = (List)((Map)paramMap.get("promoView")).get("promotions");
              break;
            }
          }
          if (paramMap.containsKey("promoClick")) {
            localScreenViewBuilder.set("&promoa", "click");
          }
        }
        for (int i = 0;; i = 1)
        {
          if (i == 0) {
            break label658;
          }
          Iterator localIterator = asm.iterator();
          do
          {
            if (!localIterator.hasNext()) {
              break;
            }
            localObject1 = (String)localIterator.next();
          } while (!paramMap.containsKey(localObject1));
          paramMap = (Map)paramMap.get(localObject1);
          localIterator = ((List)paramMap.get("products")).iterator();
          while (localIterator.hasNext())
          {
            Map localMap2 = (Map)localIterator.next();
            try
            {
              localScreenViewBuilder.addProduct(N(localMap2));
            }
            catch (RuntimeException localRuntimeException3)
            {
              bh.T("Failed to extract a product from DataLayer. " + localRuntimeException3.getMessage());
            }
          }
          localScreenViewBuilder.set("&promoa", "view");
        }
        for (;;)
        {
          try
          {
            if (!paramMap.containsKey("actionField")) {
              continue;
            }
            paramMap = c((String)localObject1, (Map)paramMap.get("actionField"));
            localScreenViewBuilder.setProductAction(paramMap);
          }
          catch (RuntimeException paramMap)
          {
            label658:
            bh.T("Failed to extract a product action from DataLayer. " + paramMap.getMessage());
            continue;
          }
          paramTracker.send(localScreenViewBuilder.build());
          return;
          paramMap = new ProductAction((String)localObject1);
        }
        label711:
        localObject1 = null;
      }
      label717:
      paramMap = null;
      continue;
      label722:
      paramMap = null;
    }
  }
  
  private void b(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  private ProductAction c(String paramString, Map<String, Object> paramMap)
  {
    paramString = new ProductAction(paramString);
    Object localObject = paramMap.get("id");
    if (localObject != null) {
      paramString.setTransactionId(String.valueOf(localObject));
    }
    localObject = paramMap.get("affiliation");
    if (localObject != null) {
      paramString.setTransactionAffiliation(String.valueOf(localObject));
    }
    localObject = paramMap.get("coupon");
    if (localObject != null) {
      paramString.setTransactionCouponCode(String.valueOf(localObject));
    }
    localObject = paramMap.get("list");
    if (localObject != null) {
      paramString.setProductActionList(String.valueOf(localObject));
    }
    localObject = paramMap.get("option");
    if (localObject != null) {
      paramString.setCheckoutOptions(String.valueOf(localObject));
    }
    localObject = paramMap.get("revenue");
    if (localObject != null) {
      paramString.setTransactionRevenue(y(localObject).doubleValue());
    }
    localObject = paramMap.get("tax");
    if (localObject != null) {
      paramString.setTransactionTax(y(localObject).doubleValue());
    }
    localObject = paramMap.get("shipping");
    if (localObject != null) {
      paramString.setTransactionShipping(y(localObject).doubleValue());
    }
    paramMap = paramMap.get("step");
    if (paramMap != null) {
      paramString.setCheckoutStep(z(paramMap).intValue());
    }
    return paramString;
  }
  
  private Map<String, String> c(d.a parama)
  {
    parama = di.o(parama);
    if (!(parama instanceof Map)) {
      return null;
    }
    Object localObject = (Map)parama;
    parama = new LinkedHashMap();
    localObject = ((Map)localObject).entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      parama.put(localEntry.getKey().toString(), localEntry.getValue().toString());
    }
    return parama;
  }
  
  private String dc(String paramString)
  {
    paramString = this.aod.get(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.toString();
  }
  
  private List<Map<String, String>> dd(String paramString)
  {
    paramString = this.aod.get(paramString);
    if (paramString == null) {
      return null;
    }
    if (!(paramString instanceof List)) {
      throw new IllegalArgumentException("transactionProducts should be of type List.");
    }
    Iterator localIterator = ((List)paramString).iterator();
    while (localIterator.hasNext()) {
      if (!(localIterator.next() instanceof Map)) {
        throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
      }
    }
    return (List)paramString;
  }
  
  private boolean f(Map<String, d.a> paramMap, String paramString)
  {
    paramMap = (d.a)paramMap.get(paramString);
    if (paramMap == null) {
      return false;
    }
    return di.n(paramMap).booleanValue();
  }
  
  private Map<String, String> p(d.a parama)
  {
    if (parama == null) {
      return new HashMap();
    }
    parama = c(parama);
    if (parama == null) {
      return new HashMap();
    }
    String str = (String)parama.get("&aip");
    if ((str != null) && (this.asp.contains(str.toLowerCase()))) {
      parama.remove("&aip");
    }
    return parama;
  }
  
  private Double y(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      try
      {
        paramObject = Double.valueOf((String)paramObject);
        return (Double)paramObject;
      }
      catch (NumberFormatException paramObject)
      {
        throw new RuntimeException("Cannot convert the object to Double: " + ((NumberFormatException)paramObject).getMessage());
      }
    }
    if ((paramObject instanceof Integer)) {
      return Double.valueOf(((Integer)paramObject).doubleValue());
    }
    if ((paramObject instanceof Double)) {
      return (Double)paramObject;
    }
    throw new RuntimeException("Cannot convert the object to Double: " + paramObject.toString());
  }
  
  private Integer z(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      try
      {
        paramObject = Integer.valueOf((String)paramObject);
        return (Integer)paramObject;
      }
      catch (NumberFormatException paramObject)
      {
        throw new RuntimeException("Cannot convert the object to Integer: " + ((NumberFormatException)paramObject).getMessage());
      }
    }
    if ((paramObject instanceof Double)) {
      return Integer.valueOf(((Double)paramObject).intValue());
    }
    if ((paramObject instanceof Integer)) {
      return (Integer)paramObject;
    }
    throw new RuntimeException("Cannot convert the object to Integer: " + paramObject.toString());
  }
  
  public void E(Map<String, d.a> paramMap)
  {
    Tracker localTracker = this.asq.cU("_GTM_DEFAULT_TRACKER_");
    if (f(paramMap, asf))
    {
      b(localTracker, paramMap);
      return;
    }
    if (f(paramMap, ase))
    {
      localTracker.send(p((d.a)paramMap.get(asi)));
      return;
    }
    if (f(paramMap, asj))
    {
      a(localTracker, paramMap);
      return;
    }
    bh.W("Ignoring unknown tag.");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/dj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */