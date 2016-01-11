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
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.c;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapView
  extends FrameLayout
{
  private GoogleMap aiR;
  private final b aiU;
  
  public MapView(Context paramContext)
  {
    super(paramContext);
    this.aiU = new b(this, paramContext, null);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.aiU = new b(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.aiU = new b(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
  }
  
  public MapView(Context paramContext, GoogleMapOptions paramGoogleMapOptions)
  {
    super(paramContext);
    this.aiU = new b(this, paramContext, paramGoogleMapOptions);
  }
  
  public final GoogleMap getMap()
  {
    if (this.aiR != null) {
      return this.aiR;
    }
    this.aiU.mA();
    if (this.aiU.it() == null) {
      return null;
    }
    try
    {
      this.aiR = new GoogleMap(((a)this.aiU.it()).mB().getMap());
      return this.aiR;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    this.aiU.onCreate(paramBundle);
    if (this.aiU.it() == null)
    {
      paramBundle = this.aiU;
      b.b(this);
    }
  }
  
  public final void onDestroy()
  {
    this.aiU.onDestroy();
  }
  
  public final void onLowMemory()
  {
    this.aiU.onLowMemory();
  }
  
  public final void onPause()
  {
    this.aiU.onPause();
  }
  
  public final void onResume()
  {
    this.aiU.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    this.aiU.onSaveInstanceState(paramBundle);
  }
  
  static class a
    implements LifecycleDelegate
  {
    private final ViewGroup aiV;
    private final IMapViewDelegate aiW;
    private View aiX;
    
    public a(ViewGroup paramViewGroup, IMapViewDelegate paramIMapViewDelegate)
    {
      this.aiW = ((IMapViewDelegate)o.i(paramIMapViewDelegate));
      this.aiV = ((ViewGroup)o.i(paramViewGroup));
    }
    
    public IMapViewDelegate mB()
    {
      return this.aiW;
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        this.aiW.onCreate(paramBundle);
        this.aiX = ((View)e.f(this.aiW.getView()));
        this.aiV.removeAllViews();
        this.aiV.addView(this.aiX);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    public void onDestroy()
    {
      try
      {
        this.aiW.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    public void onLowMemory()
    {
      try
      {
        this.aiW.onLowMemory();
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
        this.aiW.onPause();
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
        this.aiW.onResume();
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
        this.aiW.onSaveInstanceState(paramBundle);
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
  
  static class b
    extends a<MapView.a>
  {
    protected f<MapView.a> aiT;
    private final ViewGroup aiY;
    private final GoogleMapOptions aiZ;
    private final Context mContext;
    
    b(ViewGroup paramViewGroup, Context paramContext, GoogleMapOptions paramGoogleMapOptions)
    {
      this.aiY = paramViewGroup;
      this.mContext = paramContext;
      this.aiZ = paramGoogleMapOptions;
    }
    
    protected void a(f<MapView.a> paramf)
    {
      this.aiT = paramf;
      mA();
    }
    
    public void mA()
    {
      if ((this.aiT != null) && (it() == null)) {}
      try
      {
        IMapViewDelegate localIMapViewDelegate = u.S(this.mContext).a(e.k(this.mContext), this.aiZ);
        this.aiT.a(new MapView.a(this.aiY, localIMapViewDelegate));
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/MapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */