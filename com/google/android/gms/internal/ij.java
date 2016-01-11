package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.e;
import com.google.android.gms.common.internal.e.e;
import com.google.android.gms.common.internal.l;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class ij
  extends e<in>
{
  private static final Object GL = new Object();
  private static final Object GM = new Object();
  private static final ip Gr = new ip("CastClientImpl");
  private final Cast.Listener EO;
  private double FA;
  private boolean FB;
  private boolean GA;
  private int GB;
  private int GC;
  private final AtomicLong GD;
  private String GE;
  private String GF;
  private Bundle GG;
  private Map<Long, BaseImplementation.b<Status>> GH;
  private b GI;
  private BaseImplementation.b<Cast.ApplicationConnectionResult> GJ;
  private BaseImplementation.b<Status> GK;
  private ApplicationMetadata Gs;
  private final CastDevice Gt;
  private final Map<String, Cast.MessageReceivedCallback> Gu;
  private final long Gv;
  private c Gw;
  private String Gx;
  private boolean Gy;
  private boolean Gz;
  private final Handler mHandler;
  
  public ij(Context paramContext, Looper paramLooper, CastDevice paramCastDevice, long paramLong, Cast.Listener paramListener, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, (String[])null);
    this.Gt = paramCastDevice;
    this.EO = paramListener;
    this.Gv = paramLong;
    this.mHandler = new Handler(paramLooper);
    this.Gu = new HashMap();
    this.GD = new AtomicLong(0L);
    this.GH = new HashMap();
    fB();
    this.GI = new b(null);
    registerConnectionFailedListener(this.GI);
  }
  
  private void a(ig paramig)
  {
    paramig = paramig.fy();
    if (!ik.a(paramig, this.Gx)) {
      this.Gx = paramig;
    }
    for (boolean bool = true;; bool = false)
    {
      Gr.b("hasChanged=%b, mFirstApplicationStatusUpdate=%b", new Object[] { Boolean.valueOf(bool), Boolean.valueOf(this.Gy) });
      if ((this.EO != null) && ((bool) || (this.Gy))) {
        this.EO.onApplicationStatusChanged();
      }
      this.Gy = false;
      return;
    }
  }
  
  private void a(il paramil)
  {
    this.Gs = paramil.getApplicationMetadata();
    double d = paramil.fE();
    if ((d != NaN.0D) && (d != this.FA)) {
      this.FA = d;
    }
    for (boolean bool1 = true;; bool1 = false)
    {
      boolean bool2 = paramil.fM();
      if (bool2 != this.FB)
      {
        this.FB = bool2;
        bool1 = true;
      }
      Gr.b("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[] { Boolean.valueOf(bool1), Boolean.valueOf(this.Gz) });
      if ((this.EO != null) && ((bool1) || (this.Gz))) {
        this.EO.onVolumeChanged();
      }
      int i = paramil.fN();
      if (i != this.GB) {
        this.GB = i;
      }
      for (bool1 = true;; bool1 = false)
      {
        Gr.b("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[] { Boolean.valueOf(bool1), Boolean.valueOf(this.Gz) });
        if ((this.EO != null) && ((bool1) || (this.Gz))) {
          this.EO.W(this.GB);
        }
        i = paramil.fO();
        if (i != this.GC) {
          this.GC = i;
        }
        for (bool1 = true;; bool1 = false)
        {
          Gr.b("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[] { Boolean.valueOf(bool1), Boolean.valueOf(this.Gz) });
          if ((this.EO != null) && ((bool1) || (this.Gz))) {
            this.EO.X(this.GC);
          }
          this.Gz = false;
          return;
        }
      }
    }
  }
  
  private void c(BaseImplementation.b<Cast.ApplicationConnectionResult> paramb)
  {
    synchronized (GL)
    {
      if (this.GJ != null) {
        this.GJ.b(new a(new Status(2002)));
      }
      this.GJ = paramb;
      return;
    }
  }
  
  private void e(BaseImplementation.b<Status> paramb)
  {
    synchronized (GM)
    {
      if (this.GK != null)
      {
        paramb.b(new Status(2001));
        return;
      }
      this.GK = paramb;
      return;
    }
  }
  
  private void fB()
  {
    this.GA = false;
    this.GB = -1;
    this.GC = -1;
    this.Gs = null;
    this.Gx = null;
    this.FA = 0.0D;
    this.FB = false;
  }
  
  private void fF()
  {
    Gr.b("removing all MessageReceivedCallbacks", new Object[0]);
    synchronized (this.Gu)
    {
      this.Gu.clear();
      return;
    }
  }
  
  private void fG()
    throws IllegalStateException
  {
    if ((!this.GA) || (this.Gw == null) || (this.Gw.fL())) {
      throw new IllegalStateException("Not connected to a device");
    }
  }
  
  public void G(boolean paramBoolean)
    throws IllegalStateException, RemoteException
  {
    ((in)gS()).a(paramBoolean, this.FA, this.FB);
  }
  
  protected in L(IBinder paramIBinder)
  {
    return in.a.M(paramIBinder);
  }
  
  public void a(double paramDouble)
    throws IllegalArgumentException, IllegalStateException, RemoteException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    ((in)gS()).a(paramDouble, this.FA, this.FB);
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    Gr.b("in onPostInitHandler; statusCode=%d", new Object[] { Integer.valueOf(paramInt) });
    if ((paramInt == 0) || (paramInt == 1001))
    {
      this.GA = true;
      this.Gy = true;
      this.Gz = true;
    }
    for (;;)
    {
      int i = paramInt;
      if (paramInt == 1001)
      {
        this.GG = new Bundle();
        this.GG.putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
        i = 0;
      }
      super.a(i, paramIBinder, paramBundle);
      return;
      this.GA = false;
    }
  }
  
  protected void a(l paraml, e.e parame)
    throws RemoteException
  {
    Bundle localBundle = new Bundle();
    Gr.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", new Object[] { this.GE, this.GF });
    this.Gt.putInBundle(localBundle);
    localBundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.Gv);
    if (this.GE != null)
    {
      localBundle.putString("last_application_id", this.GE);
      if (this.GF != null) {
        localBundle.putString("last_session_id", this.GF);
      }
    }
    this.Gw = new c(null);
    paraml.a(parame, 6171000, getContext().getPackageName(), this.Gw.asBinder(), localBundle);
  }
  
  public void a(String paramString, Cast.MessageReceivedCallback paramMessageReceivedCallback)
    throws IllegalArgumentException, IllegalStateException, RemoteException
  {
    ik.aF(paramString);
    aE(paramString);
    if (paramMessageReceivedCallback != null) {}
    synchronized (this.Gu)
    {
      this.Gu.put(paramString, paramMessageReceivedCallback);
      ((in)gS()).aI(paramString);
      return;
    }
  }
  
  public void a(String paramString, LaunchOptions paramLaunchOptions, BaseImplementation.b<Cast.ApplicationConnectionResult> paramb)
    throws IllegalStateException, RemoteException
  {
    c(paramb);
    ((in)gS()).a(paramString, paramLaunchOptions);
  }
  
  public void a(String paramString, BaseImplementation.b<Status> paramb)
    throws IllegalStateException, RemoteException
  {
    e(paramb);
    ((in)gS()).aH(paramString);
  }
  
  public void a(String paramString1, String paramString2, BaseImplementation.b<Status> paramb)
    throws IllegalArgumentException, IllegalStateException, RemoteException
  {
    if (TextUtils.isEmpty(paramString2)) {
      throw new IllegalArgumentException("The message payload cannot be null or empty");
    }
    if (paramString2.length() > 65536) {
      throw new IllegalArgumentException("Message exceeds maximum size");
    }
    ik.aF(paramString1);
    fG();
    long l = this.GD.incrementAndGet();
    try
    {
      this.GH.put(Long.valueOf(l), paramb);
      ((in)gS()).a(paramString1, paramString2, l);
      return;
    }
    catch (Throwable paramString1)
    {
      this.GH.remove(Long.valueOf(l));
      throw paramString1;
    }
  }
  
  public void a(String paramString, boolean paramBoolean, BaseImplementation.b<Cast.ApplicationConnectionResult> paramb)
    throws IllegalStateException, RemoteException
  {
    c(paramb);
    ((in)gS()).f(paramString, paramBoolean);
  }
  
  public void aE(String paramString)
    throws IllegalArgumentException, RemoteException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Channel namespace cannot be null or empty");
    }
    synchronized (this.Gu)
    {
      Cast.MessageReceivedCallback localMessageReceivedCallback = (Cast.MessageReceivedCallback)this.Gu.remove(paramString);
      if (localMessageReceivedCallback == null) {}
    }
  }
  
  public void b(String paramString1, String paramString2, BaseImplementation.b<Cast.ApplicationConnectionResult> paramb)
    throws IllegalStateException, RemoteException
  {
    c(paramb);
    ((in)gS()).l(paramString1, paramString2);
  }
  
  public void d(BaseImplementation.b<Status> paramb)
    throws IllegalStateException, RemoteException
  {
    e(paramb);
    ((in)gS()).fP();
  }
  
  public void disconnect()
  {
    Gr.b("disconnect(); ServiceListener=%s, isConnected=%b", new Object[] { this.Gw, Boolean.valueOf(isConnected()) });
    c localc = this.Gw;
    this.Gw = null;
    if ((localc == null) || (!localc.fK()))
    {
      Gr.b("already disposed, so short-circuiting", new Object[0]);
      return;
    }
    fF();
    try
    {
      if ((isConnected()) || (isConnecting())) {
        ((in)gS()).disconnect();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Gr.a(localRemoteException, "Error while disconnecting the controller interface: %s", new Object[] { localRemoteException.getMessage() });
      return;
    }
    finally
    {
      super.disconnect();
    }
  }
  
  public Bundle fC()
  {
    if (this.GG != null)
    {
      Bundle localBundle = this.GG;
      this.GG = null;
      return localBundle;
    }
    return super.fC();
  }
  
  public void fD()
    throws IllegalStateException, RemoteException
  {
    ((in)gS()).fD();
  }
  
  public double fE()
    throws IllegalStateException
  {
    fG();
    return this.FA;
  }
  
  public ApplicationMetadata getApplicationMetadata()
    throws IllegalStateException
  {
    fG();
    return this.Gs;
  }
  
  public String getApplicationStatus()
    throws IllegalStateException
  {
    fG();
    return this.Gx;
  }
  
  protected String getServiceDescriptor()
  {
    return "com.google.android.gms.cast.internal.ICastDeviceController";
  }
  
  protected String getStartServiceAction()
  {
    return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
  }
  
  public boolean isMute()
    throws IllegalStateException
  {
    fG();
    return this.FB;
  }
  
  private static final class a
    implements Cast.ApplicationConnectionResult
  {
    private final Status CM;
    private final ApplicationMetadata GN;
    private final String GO;
    private final boolean GP;
    private final String vL;
    
    public a(Status paramStatus)
    {
      this(paramStatus, null, null, null, false);
    }
    
    public a(Status paramStatus, ApplicationMetadata paramApplicationMetadata, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.CM = paramStatus;
      this.GN = paramApplicationMetadata;
      this.GO = paramString1;
      this.vL = paramString2;
      this.GP = paramBoolean;
    }
    
    public ApplicationMetadata getApplicationMetadata()
    {
      return this.GN;
    }
    
    public String getApplicationStatus()
    {
      return this.GO;
    }
    
    public String getSessionId()
    {
      return this.vL;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
    
    public boolean getWasLaunched()
    {
      return this.GP;
    }
  }
  
  private class b
    implements GoogleApiClient.OnConnectionFailedListener
  {
    private b() {}
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      ij.b(ij.this);
    }
  }
  
  private class c
    extends io.a
  {
    private AtomicBoolean GR = new AtomicBoolean(false);
    
    private c() {}
    
    private boolean ag(int paramInt)
    {
      synchronized ()
      {
        if (ij.j(ij.this) != null)
        {
          ij.j(ij.this).b(new Status(paramInt));
          ij.b(ij.this, null);
          return true;
        }
        return false;
      }
    }
    
    private void c(long paramLong, int paramInt)
    {
      synchronized (ij.i(ij.this))
      {
        BaseImplementation.b localb = (BaseImplementation.b)ij.i(ij.this).remove(Long.valueOf(paramLong));
        if (localb != null) {
          localb.b(new Status(paramInt));
        }
        return;
      }
    }
    
    public void a(ApplicationMetadata paramApplicationMetadata, String paramString1, String paramString2, boolean paramBoolean)
    {
      if (this.GR.get()) {
        return;
      }
      ij.a(ij.this, paramApplicationMetadata);
      ij.a(ij.this, paramApplicationMetadata.getApplicationId());
      ij.b(ij.this, paramString2);
      synchronized (ij.fI())
      {
        if (ij.d(ij.this) != null)
        {
          ij.d(ij.this).b(new ij.a(new Status(0), paramApplicationMetadata, paramString1, paramString2, paramBoolean));
          ij.a(ij.this, null);
        }
        return;
      }
    }
    
    public void a(String paramString, double paramDouble, boolean paramBoolean)
    {
      ij.fH().b("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }
    
    public void a(String paramString, long paramLong)
    {
      if (this.GR.get()) {
        return;
      }
      c(paramLong, 0);
    }
    
    public void a(String paramString, long paramLong, int paramInt)
    {
      if (this.GR.get()) {
        return;
      }
      c(paramLong, paramInt);
    }
    
    public void ac(int paramInt)
    {
      if (!fK()) {}
      do
      {
        return;
        ij.fH().b("ICastDeviceControllerListener.onDisconnected: %d", new Object[] { Integer.valueOf(paramInt) });
      } while (paramInt == 0);
      ij.this.aA(2);
    }
    
    public void ad(int paramInt)
    {
      if (this.GR.get()) {
        return;
      }
      synchronized (ij.fI())
      {
        if (ij.d(ij.this) != null)
        {
          ij.d(ij.this).b(new ij.a(new Status(paramInt)));
          ij.a(ij.this, null);
        }
        return;
      }
    }
    
    public void ae(int paramInt)
    {
      if (this.GR.get()) {
        return;
      }
      ag(paramInt);
    }
    
    public void af(int paramInt)
    {
      if (this.GR.get()) {
        return;
      }
      ag(paramInt);
    }
    
    public void b(final ig paramig)
    {
      if (this.GR.get()) {
        return;
      }
      ij.fH().b("onApplicationStatusChanged", new Object[0]);
      ij.f(ij.this).post(new Runnable()
      {
        public void run()
        {
          ij.a(ij.this, paramig);
        }
      });
    }
    
    public void b(final il paramil)
    {
      if (this.GR.get()) {
        return;
      }
      ij.fH().b("onDeviceStatusChanged", new Object[0]);
      ij.f(ij.this).post(new Runnable()
      {
        public void run()
        {
          ij.a(ij.this, paramil);
        }
      });
    }
    
    public void b(String paramString, byte[] paramArrayOfByte)
    {
      if (this.GR.get()) {
        return;
      }
      ij.fH().b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", new Object[] { paramString, Integer.valueOf(paramArrayOfByte.length) });
    }
    
    public boolean fK()
    {
      if (this.GR.getAndSet(true)) {
        return false;
      }
      ij.c(ij.this);
      return true;
    }
    
    public boolean fL()
    {
      return this.GR.get();
    }
    
    public void k(final String paramString1, final String paramString2)
    {
      if (this.GR.get()) {
        return;
      }
      ij.fH().b("Receive (type=text, ns=%s) %s", new Object[] { paramString1, paramString2 });
      ij.f(ij.this).post(new Runnable()
      {
        public void run()
        {
          synchronized (ij.g(ij.this))
          {
            Cast.MessageReceivedCallback localMessageReceivedCallback = (Cast.MessageReceivedCallback)ij.g(ij.this).get(paramString1);
            if (localMessageReceivedCallback != null)
            {
              localMessageReceivedCallback.onMessageReceived(ij.h(ij.this), paramString1, paramString2);
              return;
            }
          }
          ij.fH().b("Discarded message for unknown namespace '%s'", new Object[] { paramString1 });
        }
      });
    }
    
    public void onApplicationDisconnected(final int paramInt)
    {
      if (this.GR.get()) {}
      do
      {
        return;
        ij.a(ij.this, null);
        ij.b(ij.this, null);
        ag(paramInt);
      } while (ij.e(ij.this) == null);
      ij.f(ij.this).post(new Runnable()
      {
        public void run()
        {
          if (ij.e(ij.this) != null) {
            ij.e(ij.this).onApplicationDisconnected(paramInt);
          }
        }
      });
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/ij.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */