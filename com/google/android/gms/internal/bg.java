package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ez
public final class bg
{
  public static final String DEVICE_ID_EMULATOR = gr.R("emulator");
  private final Date d;
  private final Set<String> f;
  private final Location h;
  private final String ol;
  private final int om;
  private final boolean on;
  private final Bundle oo;
  private final Map<Class<? extends NetworkExtras>, NetworkExtras> op;
  private final String oq;
  private final SearchAdRequest or;
  private final int os;
  private final Set<String> ot;
  
  public bg(a parama)
  {
    this(parama, null);
  }
  
  public bg(a parama, SearchAdRequest paramSearchAdRequest)
  {
    this.d = a.a(parama);
    this.ol = a.b(parama);
    this.om = a.c(parama);
    this.f = Collections.unmodifiableSet(a.d(parama));
    this.h = a.e(parama);
    this.on = a.f(parama);
    this.oo = a.g(parama);
    this.op = Collections.unmodifiableMap(a.h(parama));
    this.oq = a.i(parama);
    this.or = paramSearchAdRequest;
    this.os = a.j(parama);
    this.ot = Collections.unmodifiableSet(a.k(parama));
  }
  
  public SearchAdRequest bd()
  {
    return this.or;
  }
  
  public Map<Class<? extends NetworkExtras>, NetworkExtras> be()
  {
    return this.op;
  }
  
  public Bundle bf()
  {
    return this.oo;
  }
  
  public int bg()
  {
    return this.os;
  }
  
  public Date getBirthday()
  {
    return this.d;
  }
  
  public String getContentUrl()
  {
    return this.ol;
  }
  
  public Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass)
  {
    Bundle localBundle = this.oo.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
    if (localBundle != null) {
      return localBundle.getBundle(paramClass.getClass().getName());
    }
    return null;
  }
  
  public int getGender()
  {
    return this.om;
  }
  
  public Set<String> getKeywords()
  {
    return this.f;
  }
  
  public Location getLocation()
  {
    return this.h;
  }
  
  public boolean getManualImpressionsEnabled()
  {
    return this.on;
  }
  
  @Deprecated
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return (NetworkExtras)this.op.get(paramClass);
  }
  
  public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass)
  {
    return this.oo.getBundle(paramClass.getName());
  }
  
  public String getPublisherProvidedId()
  {
    return this.oq;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.ot.contains(gr.v(paramContext));
  }
  
  public static final class a
  {
    private Date d;
    private Location h;
    private String ol;
    private int om = -1;
    private boolean on = false;
    private final Bundle oo = new Bundle();
    private String oq;
    private int os = -1;
    private final HashSet<String> ou = new HashSet();
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> ov = new HashMap();
    private final HashSet<String> ow = new HashSet();
    
    public void a(Location paramLocation)
    {
      this.h = paramLocation;
    }
    
    @Deprecated
    public void a(NetworkExtras paramNetworkExtras)
    {
      if ((paramNetworkExtras instanceof AdMobExtras))
      {
        a(AdMobAdapter.class, ((AdMobExtras)paramNetworkExtras).getExtras());
        return;
      }
      this.ov.put(paramNetworkExtras.getClass(), paramNetworkExtras);
    }
    
    public void a(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
    {
      this.oo.putBundle(paramClass.getName(), paramBundle);
    }
    
    public void a(Date paramDate)
    {
      this.d = paramDate;
    }
    
    public void b(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
    {
      if (this.oo.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
        this.oo.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
      }
      this.oo.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(paramClass.getName(), paramBundle);
    }
    
    public void g(int paramInt)
    {
      this.om = paramInt;
    }
    
    public void g(boolean paramBoolean)
    {
      this.on = paramBoolean;
    }
    
    public void h(boolean paramBoolean)
    {
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        this.os = i;
        return;
      }
    }
    
    public void r(String paramString)
    {
      this.ou.add(paramString);
    }
    
    public void s(String paramString)
    {
      this.ow.add(paramString);
    }
    
    public void t(String paramString)
    {
      this.ol = paramString;
    }
    
    public void u(String paramString)
    {
      this.oq = paramString;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/bg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */