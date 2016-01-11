package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.c;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class StreetViewPanoramaView
  extends FrameLayout
{
  private StreetViewPanorama ajh;
  private final a ajq;
  
  public StreetViewPanoramaView(Context paramContext)
  {
    super(paramContext);
    this.ajq = new a(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.ajq = new a(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.ajq = new a(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
  {
    super(paramContext);
    this.ajq = new a(this, paramContext, paramStreetViewPanoramaOptions);
  }
  
  public final StreetViewPanorama getStreetViewPanorama()
  {
    if (this.ajh != null) {
      return this.ajh;
    }
    this.ajq.mA();
    if (this.ajq.it() == null) {
      return null;
    }
    try
    {
      this.ajh = new StreetViewPanorama(((b)this.ajq.it()).mH().getStreetViewPanorama());
      return this.ajh;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    this.ajq.onCreate(paramBundle);
    if (this.ajq.it() == null)
    {
      paramBundle = this.ajq;
      a.b(this);
    }
  }
  
  public final void onDestroy()
  {
    this.ajq.onDestroy();
  }
  
  public final void onLowMemory()
  {
    this.ajq.onLowMemory();
  }
  
  public final void onPause()
  {
    this.ajq.onPause();
  }
  
  public final void onResume()
  {
    this.ajq.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    this.ajq.onSaveInstanceState(paramBundle);
  }
  
  static class a
    extends a<StreetViewPanoramaView.b>
  {
    protected f<StreetViewPanoramaView.b> aiT;
    private final ViewGroup aiY;
    private final StreetViewPanoramaOptions ajr;
    private final Context mContext;
    
    a(ViewGroup paramViewGroup, Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    {
      this.aiY = paramViewGroup;
      this.mContext = paramContext;
      this.ajr = paramStreetViewPanoramaOptions;
    }
    
    protected void a(f<StreetViewPanoramaView.b> paramf)
    {
      this.aiT = paramf;
      mA();
    }
    
    public void mA()
    {
      if ((this.aiT != null) && (it() == null)) {}
      try
      {
        IStreetViewPanoramaViewDelegate localIStreetViewPanoramaViewDelegate = u.S(this.mContext).a(e.k(this.mContext), this.ajr);
        this.aiT.a(new StreetViewPanoramaView.b(this.aiY, localIStreetViewPanoramaViewDelegate));
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
    }
  }
  
  static class b
    implements LifecycleDelegate
  {
    private final ViewGroup aiV;
    private final IStreetViewPanoramaViewDelegate ajs;
    private View ajt;
    
    public b(ViewGroup paramViewGroup, IStreetViewPanoramaViewDelegate paramIStreetViewPanoramaViewDelegate)
    {
      this.ajs = ((IStreetViewPanoramaViewDelegate)o.i(paramIStreetViewPanoramaViewDelegate));
      this.aiV = ((ViewGroup)o.i(paramViewGroup));
    }
    
    public IStreetViewPanoramaViewDelegate mH()
    {
      return this.ajs;
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        this.ajs.onCreate(paramBundle);
        this.ajt = ((View)e.f(this.ajs.getView()));
        this.aiV.removeAllViews();
        this.aiV.addView(this.ajt);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public void onDestroy()
    {
      try
      {
        this.ajs.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public void onLowMemory()
    {
      try
      {
        this.ajs.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onPause()
    {
      try
      {
        this.ajs.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onResume()
    {
      try
      {
        this.ajs.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        this.ajs.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onStart() {}
    
    public void onStop() {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/StreetViewPanoramaView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */