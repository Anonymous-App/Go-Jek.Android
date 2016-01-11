package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ct
{
  private static final bz<d.a> aqS = new bz(di.pK(), true);
  private final DataLayer aod;
  private final cr.c aqT;
  private final ag aqU;
  private final Map<String, aj> aqV;
  private final Map<String, aj> aqW;
  private final Map<String, aj> aqX;
  private final k<cr.a, bz<d.a>> aqY;
  private final k<String, b> aqZ;
  private final Set<cr.e> ara;
  private final Map<String, c> arb;
  private volatile String arc;
  private int ard;
  
  public ct(Context paramContext, cr.c paramc, DataLayer paramDataLayer, s.a parama1, s.a parama2, ag paramag)
  {
    if (paramc == null) {
      throw new NullPointerException("resource cannot be null");
    }
    this.aqT = paramc;
    this.ara = new HashSet(paramc.oY());
    this.aod = paramDataLayer;
    this.aqU = paramag;
    paramc = new l.a()
    {
      public int a(cr.a paramAnonymousa, bz<d.a> paramAnonymousbz)
      {
        return ((d.a)paramAnonymousbz.getObject()).qH();
      }
    };
    this.aqY = new l().a(1048576, paramc);
    paramc = new l.a()
    {
      public int a(String paramAnonymousString, ct.b paramAnonymousb)
      {
        return paramAnonymousString.length() + paramAnonymousb.getSize();
      }
    };
    this.aqZ = new l().a(1048576, paramc);
    this.aqV = new HashMap();
    b(new i(paramContext));
    b(new s(parama2));
    b(new w(paramDataLayer));
    b(new dj(paramContext, paramDataLayer));
    this.aqW = new HashMap();
    c(new q());
    c(new ad());
    c(new ae());
    c(new al());
    c(new am());
    c(new bd());
    c(new be());
    c(new ci());
    c(new dc());
    this.aqX = new HashMap();
    a(new b(paramContext));
    a(new c(paramContext));
    a(new e(paramContext));
    a(new f(paramContext));
    a(new g(paramContext));
    a(new h(paramContext));
    a(new m());
    a(new p(this.aqT.getVersion()));
    a(new s(parama1));
    a(new u(paramDataLayer));
    a(new z(paramContext));
    a(new aa());
    a(new ac());
    a(new ah(this));
    a(new an());
    a(new ao());
    a(new ax(paramContext));
    a(new az());
    a(new bc());
    a(new bj());
    a(new bl(paramContext));
    a(new ca());
    a(new cc());
    a(new cf());
    a(new ch());
    a(new cj(paramContext));
    a(new cu());
    a(new cv());
    a(new de());
    a(new dk());
    this.arb = new HashMap();
    paramDataLayer = this.ara.iterator();
    while (paramDataLayer.hasNext())
    {
      parama1 = (cr.e)paramDataLayer.next();
      if (paramag.oq())
      {
        a(parama1.pg(), parama1.ph(), "add macro");
        a(parama1.pl(), parama1.pi(), "remove macro");
        a(parama1.pe(), parama1.pj(), "add tag");
        a(parama1.pf(), parama1.pk(), "remove tag");
      }
      int i = 0;
      while (i < parama1.pg().size())
      {
        parama2 = (cr.a)parama1.pg().get(i);
        paramc = "Unknown";
        paramContext = paramc;
        if (paramag.oq())
        {
          paramContext = paramc;
          if (i < parama1.ph().size()) {
            paramContext = (String)parama1.ph().get(i);
          }
        }
        paramc = e(this.arb, h(parama2));
        paramc.b(parama1);
        paramc.a(parama1, parama2);
        paramc.a(parama1, paramContext);
        i += 1;
      }
      i = 0;
      while (i < parama1.pl().size())
      {
        parama2 = (cr.a)parama1.pl().get(i);
        paramc = "Unknown";
        paramContext = paramc;
        if (paramag.oq())
        {
          paramContext = paramc;
          if (i < parama1.pi().size()) {
            paramContext = (String)parama1.pi().get(i);
          }
        }
        paramc = e(this.arb, h(parama2));
        paramc.b(parama1);
        paramc.b(parama1, parama2);
        paramc.b(parama1, paramContext);
        i += 1;
      }
    }
    paramContext = this.aqT.oZ().entrySet().iterator();
    while (paramContext.hasNext())
    {
      paramc = (Map.Entry)paramContext.next();
      paramDataLayer = ((List)paramc.getValue()).iterator();
      while (paramDataLayer.hasNext())
      {
        parama1 = (cr.a)paramDataLayer.next();
        if (!di.n((d.a)parama1.oU().get(com.google.android.gms.internal.b.dG.toString())).booleanValue()) {
          e(this.arb, (String)paramc.getKey()).i(parama1);
        }
      }
    }
  }
  
  private bz<d.a> a(d.a parama, Set<String> paramSet, dl paramdl)
  {
    if (!parama.gF) {
      return new bz(parama, true);
    }
    bz localbz1;
    switch (parama.type)
    {
    case 5: 
    case 6: 
    default: 
      bh.T("Unknown type: " + parama.type);
      return aqS;
    case 2: 
      locala = cr.g(parama);
      locala.gw = new d.a[parama.gw.length];
      i = 0;
      while (i < parama.gw.length)
      {
        localbz1 = a(parama.gw[i], paramSet, paramdl.fi(i));
        if (localbz1 == aqS) {
          return aqS;
        }
        locala.gw[i] = ((d.a)localbz1.getObject());
        i += 1;
      }
      return new bz(locala, false);
    case 3: 
      locala = cr.g(parama);
      if (parama.gx.length != parama.gy.length)
      {
        bh.T("Invalid serving value: " + parama.toString());
        return aqS;
      }
      locala.gx = new d.a[parama.gx.length];
      locala.gy = new d.a[parama.gx.length];
      i = 0;
      while (i < parama.gx.length)
      {
        localbz1 = a(parama.gx[i], paramSet, paramdl.fj(i));
        bz localbz2 = a(parama.gy[i], paramSet, paramdl.fk(i));
        if ((localbz1 == aqS) || (localbz2 == aqS)) {
          return aqS;
        }
        locala.gx[i] = ((d.a)localbz1.getObject());
        locala.gy[i] = ((d.a)localbz2.getObject());
        i += 1;
      }
      return new bz(locala, false);
    case 4: 
      if (paramSet.contains(parama.gz))
      {
        bh.T("Macro cycle detected.  Current macro reference: " + parama.gz + "." + "  Previous macro references: " + paramSet.toString() + ".");
        return aqS;
      }
      paramSet.add(parama.gz);
      paramdl = dm.a(a(parama.gz, paramSet, paramdl.oF()), parama.gE);
      paramSet.remove(parama.gz);
      return paramdl;
    }
    d.a locala = cr.g(parama);
    locala.gD = new d.a[parama.gD.length];
    int i = 0;
    while (i < parama.gD.length)
    {
      localbz1 = a(parama.gD[i], paramSet, paramdl.fl(i));
      if (localbz1 == aqS) {
        return aqS;
      }
      locala.gD[i] = ((d.a)localbz1.getObject());
      i += 1;
    }
    return new bz(locala, false);
  }
  
  private bz<d.a> a(String paramString, Set<String> paramSet, bk parambk)
  {
    this.ard += 1;
    Object localObject = (b)this.aqZ.get(paramString);
    if ((localObject != null) && (!this.aqU.oq()))
    {
      a(((b)localObject).oV(), paramSet);
      this.ard -= 1;
      return ((b)localObject).pp();
    }
    localObject = (c)this.arb.get(paramString);
    if (localObject == null)
    {
      bh.T(po() + "Invalid macro: " + paramString);
      this.ard -= 1;
      return aqS;
    }
    bz localbz = a(paramString, ((c)localObject).pq(), ((c)localObject).pr(), ((c)localObject).ps(), ((c)localObject).pu(), ((c)localObject).pt(), paramSet, parambk.oh());
    if (((Set)localbz.getObject()).isEmpty()) {}
    for (localObject = ((c)localObject).pv(); localObject == null; localObject = (cr.a)((Set)localbz.getObject()).iterator().next())
    {
      this.ard -= 1;
      return aqS;
      if (((Set)localbz.getObject()).size() > 1) {
        bh.W(po() + "Multiple macros active for macroName " + paramString);
      }
    }
    parambk = a(this.aqX, (cr.a)localObject, paramSet, parambk.ow());
    boolean bool;
    if ((localbz.oG()) && (parambk.oG()))
    {
      bool = true;
      if (parambk != aqS) {
        break label392;
      }
    }
    label392:
    for (parambk = aqS;; parambk = new bz(parambk.getObject(), bool))
    {
      localObject = ((cr.a)localObject).oV();
      if (parambk.oG()) {
        this.aqZ.e(paramString, new b(parambk, (d.a)localObject));
      }
      a((d.a)localObject, paramSet);
      this.ard -= 1;
      return parambk;
      bool = false;
      break;
    }
  }
  
  private bz<d.a> a(Map<String, aj> paramMap, cr.a parama, Set<String> paramSet, ck paramck)
  {
    boolean bool = true;
    Object localObject1 = (d.a)parama.oU().get(com.google.android.gms.internal.b.cU.toString());
    if (localObject1 == null)
    {
      bh.T("No function id in properties");
      paramMap = aqS;
    }
    aj localaj;
    do
    {
      return paramMap;
      localObject1 = ((d.a)localObject1).gA;
      localaj = (aj)paramMap.get(localObject1);
      if (localaj == null)
      {
        bh.T((String)localObject1 + " has no backing implementation.");
        return aqS;
      }
      paramMap = (bz)this.aqY.get(parama);
    } while ((paramMap != null) && (!this.aqU.oq()));
    paramMap = new HashMap();
    Iterator localIterator = parama.oU().entrySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject2 = paramck.cH((String)localEntry.getKey());
      localObject2 = a((d.a)localEntry.getValue(), paramSet, ((cm)localObject2).e((d.a)localEntry.getValue()));
      if (localObject2 == aqS) {
        return aqS;
      }
      if (((bz)localObject2).oG()) {
        parama.a((String)localEntry.getKey(), (d.a)((bz)localObject2).getObject());
      }
      for (;;)
      {
        paramMap.put(localEntry.getKey(), ((bz)localObject2).getObject());
        break;
        i = 0;
      }
    }
    if (!localaj.a(paramMap.keySet()))
    {
      bh.T("Incorrect keys for function " + (String)localObject1 + " required " + localaj.os() + " had " + paramMap.keySet());
      return aqS;
    }
    if ((i != 0) && (localaj.nN())) {}
    for (;;)
    {
      paramMap = new bz(localaj.C(paramMap), bool);
      if (bool) {
        this.aqY.e(parama, paramMap);
      }
      paramck.d((d.a)paramMap.getObject());
      return paramMap;
      bool = false;
    }
  }
  
  private bz<Set<cr.a>> a(Set<cr.e> paramSet, Set<String> paramSet1, a parama, cs paramcs)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    paramSet = paramSet.iterator();
    boolean bool = true;
    if (paramSet.hasNext())
    {
      cr.e locale = (cr.e)paramSet.next();
      cn localcn = paramcs.oE();
      bz localbz = a(locale, paramSet1, localcn);
      if (((Boolean)localbz.getObject()).booleanValue()) {
        parama.a(locale, localHashSet1, localHashSet2, localcn);
      }
      if ((bool) && (localbz.oG())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    localHashSet1.removeAll(localHashSet2);
    paramcs.b(localHashSet1);
    return new bz(localHashSet1, bool);
  }
  
  private void a(d.a parama, Set<String> paramSet)
  {
    if (parama == null) {}
    for (;;)
    {
      return;
      parama = a(parama, paramSet, new bx());
      if (parama != aqS)
      {
        parama = di.o((d.a)parama.getObject());
        if ((parama instanceof Map))
        {
          parama = (Map)parama;
          this.aod.push(parama);
          return;
        }
        if (!(parama instanceof List)) {
          break;
        }
        parama = ((List)parama).iterator();
        while (parama.hasNext())
        {
          paramSet = parama.next();
          if ((paramSet instanceof Map))
          {
            paramSet = (Map)paramSet;
            this.aod.push(paramSet);
          }
          else
          {
            bh.W("pushAfterEvaluate: value not a Map");
          }
        }
      }
    }
    bh.W("pushAfterEvaluate: value not a Map or List");
  }
  
  private static void a(List<cr.a> paramList, List<String> paramList1, String paramString)
  {
    if (paramList.size() != paramList1.size()) {
      bh.U("Invalid resource: imbalance of rule names of functions for " + paramString + " operation. Using default rule name instead");
    }
  }
  
  private static void a(Map<String, aj> paramMap, aj paramaj)
  {
    if (paramMap.containsKey(paramaj.or())) {
      throw new IllegalArgumentException("Duplicate function type name: " + paramaj.or());
    }
    paramMap.put(paramaj.or(), paramaj);
  }
  
  private static c e(Map<String, c> paramMap, String paramString)
  {
    c localc2 = (c)paramMap.get(paramString);
    c localc1 = localc2;
    if (localc2 == null)
    {
      localc1 = new c();
      paramMap.put(paramString, localc1);
    }
    return localc1;
  }
  
  private static String h(cr.a parama)
  {
    return di.j((d.a)parama.oU().get(com.google.android.gms.internal.b.df.toString()));
  }
  
  private String po()
  {
    if (this.ard <= 1) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(this.ard));
    int i = 2;
    while (i < this.ard)
    {
      localStringBuilder.append(' ');
      i += 1;
    }
    localStringBuilder.append(": ");
    return localStringBuilder.toString();
  }
  
  bz<Boolean> a(cr.a parama, Set<String> paramSet, ck paramck)
  {
    parama = a(this.aqW, parama, paramSet, paramck);
    paramSet = di.n((d.a)parama.getObject());
    paramck.d(di.u(paramSet));
    return new bz(paramSet, parama.oG());
  }
  
  bz<Boolean> a(cr.e parame, Set<String> paramSet, cn paramcn)
  {
    Object localObject = parame.pd().iterator();
    boolean bool = true;
    if (((Iterator)localObject).hasNext())
    {
      bz localbz = a((cr.a)((Iterator)localObject).next(), paramSet, paramcn.oy());
      if (((Boolean)localbz.getObject()).booleanValue())
      {
        paramcn.f(di.u(Boolean.valueOf(false)));
        return new bz(Boolean.valueOf(false), localbz.oG());
      }
      if ((bool) && (localbz.oG())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    parame = parame.pc().iterator();
    while (parame.hasNext())
    {
      localObject = a((cr.a)parame.next(), paramSet, paramcn.oz());
      if (!((Boolean)((bz)localObject).getObject()).booleanValue())
      {
        paramcn.f(di.u(Boolean.valueOf(false)));
        return new bz(Boolean.valueOf(false), ((bz)localObject).oG());
      }
      if ((bool) && (((bz)localObject).oG())) {
        bool = true;
      } else {
        bool = false;
      }
    }
    paramcn.f(di.u(Boolean.valueOf(true)));
    return new bz(Boolean.valueOf(true), bool);
  }
  
  bz<Set<cr.a>> a(String paramString, Set<cr.e> paramSet, final Map<cr.e, List<cr.a>> paramMap1, final Map<cr.e, List<String>> paramMap2, final Map<cr.e, List<cr.a>> paramMap3, final Map<cr.e, List<String>> paramMap4, Set<String> paramSet1, cs paramcs)
  {
    a(paramSet, paramSet1, new a()
    {
      public void a(cr.e paramAnonymouse, Set<cr.a> paramAnonymousSet1, Set<cr.a> paramAnonymousSet2, cn paramAnonymouscn)
      {
        List localList1 = (List)paramMap1.get(paramAnonymouse);
        List localList2 = (List)paramMap2.get(paramAnonymouse);
        if (localList1 != null)
        {
          paramAnonymousSet1.addAll(localList1);
          paramAnonymouscn.oA().c(localList1, localList2);
        }
        paramAnonymousSet1 = (List)paramMap3.get(paramAnonymouse);
        paramAnonymouse = (List)paramMap4.get(paramAnonymouse);
        if (paramAnonymousSet1 != null)
        {
          paramAnonymousSet2.addAll(paramAnonymousSet1);
          paramAnonymouscn.oB().c(paramAnonymousSet1, paramAnonymouse);
        }
      }
    }, paramcs);
  }
  
  bz<Set<cr.a>> a(Set<cr.e> paramSet, cs paramcs)
  {
    a(paramSet, new HashSet(), new a()
    {
      public void a(cr.e paramAnonymouse, Set<cr.a> paramAnonymousSet1, Set<cr.a> paramAnonymousSet2, cn paramAnonymouscn)
      {
        paramAnonymousSet1.addAll(paramAnonymouse.pe());
        paramAnonymousSet2.addAll(paramAnonymouse.pf());
        paramAnonymouscn.oC().c(paramAnonymouse.pe(), paramAnonymouse.pj());
        paramAnonymouscn.oD().c(paramAnonymouse.pf(), paramAnonymouse.pk());
      }
    }, paramcs);
  }
  
  void a(aj paramaj)
  {
    a(this.aqX, paramaj);
  }
  
  void b(aj paramaj)
  {
    a(this.aqV, paramaj);
  }
  
  void c(aj paramaj)
  {
    a(this.aqW, paramaj);
  }
  
  public bz<d.a> cR(String paramString)
  {
    this.ard = 0;
    af localaf = this.aqU.cA(paramString);
    paramString = a(paramString, new HashSet(), localaf.on());
    localaf.op();
    return paramString;
  }
  
  void cS(String paramString)
  {
    try
    {
      this.arc = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void cp(String paramString)
  {
    try
    {
      cS(paramString);
      paramString = this.aqU.cB(paramString);
      t localt = paramString.oo();
      Iterator localIterator = ((Set)a(this.ara, localt.oh()).getObject()).iterator();
      while (localIterator.hasNext())
      {
        cr.a locala = (cr.a)localIterator.next();
        a(this.aqV, locala, new HashSet(), localt.og());
      }
      paramString.op();
    }
    finally {}
    cS(null);
  }
  
  public void k(List<c.i> paramList)
  {
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (!paramList.hasNext()) {
          break;
        }
        c.i locali = (c.i)paramList.next();
        if ((locali.name == null) || (!locali.name.startsWith("gaExperiment:"))) {
          bh.V("Ignored supplemental: " + locali);
        } else {
          ai.a(this.aod, locali);
        }
      }
      finally {}
    }
  }
  
  String pn()
  {
    try
    {
      String str = this.arc;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  static abstract interface a
  {
    public abstract void a(cr.e parame, Set<cr.a> paramSet1, Set<cr.a> paramSet2, cn paramcn);
  }
  
  private static class b
  {
    private d.a aqE;
    private bz<d.a> arj;
    
    public b(bz<d.a> parambz, d.a parama)
    {
      this.arj = parambz;
      this.aqE = parama;
    }
    
    public int getSize()
    {
      int j = ((d.a)this.arj.getObject()).qH();
      if (this.aqE == null) {}
      for (int i = 0;; i = this.aqE.qH()) {
        return i + j;
      }
    }
    
    public d.a oV()
    {
      return this.aqE;
    }
    
    public bz<d.a> pp()
    {
      return this.arj;
    }
  }
  
  private static class c
  {
    private final Set<cr.e> ara = new HashSet();
    private final Map<cr.e, List<cr.a>> ark = new HashMap();
    private final Map<cr.e, List<cr.a>> arl = new HashMap();
    private final Map<cr.e, List<String>> arm = new HashMap();
    private final Map<cr.e, List<String>> arn = new HashMap();
    private cr.a aro;
    
    public void a(cr.e parame, cr.a parama)
    {
      List localList = (List)this.ark.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.ark.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }
    
    public void a(cr.e parame, String paramString)
    {
      List localList = (List)this.arm.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.arm.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void b(cr.e parame)
    {
      this.ara.add(parame);
    }
    
    public void b(cr.e parame, cr.a parama)
    {
      List localList = (List)this.arl.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.arl.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }
    
    public void b(cr.e parame, String paramString)
    {
      List localList = (List)this.arn.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.arn.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void i(cr.a parama)
    {
      this.aro = parama;
    }
    
    public Set<cr.e> pq()
    {
      return this.ara;
    }
    
    public Map<cr.e, List<cr.a>> pr()
    {
      return this.ark;
    }
    
    public Map<cr.e, List<String>> ps()
    {
      return this.arm;
    }
    
    public Map<cr.e, List<String>> pt()
    {
      return this.arn;
    }
    
    public Map<cr.e, List<cr.a>> pu()
    {
      return this.arl;
    }
    
    public cr.a pv()
    {
      return this.aro;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/tagmanager/ct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */