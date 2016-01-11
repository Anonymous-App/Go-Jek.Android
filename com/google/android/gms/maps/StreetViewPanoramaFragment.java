package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.c;
import com.google.android.gms.maps.internal.t;
import com.google.android.gms.maps.internal.u;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class StreetViewPanoramaFragment
  extends Fragment
  implements TraceFieldInterface
{
  private final b ajg = new b(this);
  private StreetViewPanorama ajh;
  
  public static StreetViewPanoramaFragment newInstance()
  {
    return new StreetViewPanoramaFragment();
  }
  
  public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
  {
    StreetViewPanoramaFragment localStreetViewPanoramaFragment = new StreetViewPanoramaFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("StreetViewPanoramaOptions", paramStreetViewPanoramaOptions);
    localStreetViewPanoramaFragment.setArguments(localBundle);
    return localStreetViewPanoramaFragment;
  }
  
  public final StreetViewPanorama getStreetViewPanorama()
  {
    Object localObject = mD();
    if (localObject == null) {}
    for (;;)
    {
      return null;
      try
      {
        localObject = ((IStreetViewPanoramaFragmentDelegate)localObject).getStreetViewPanorama();
        if (localObject == null) {
          continue;
        }
        if ((this.ajh == null) || (this.ajh.mC().asBinder() != ((IStreetViewPanoramaDelegate)localObject).asBinder())) {
          this.ajh = new StreetViewPanorama((IStreetViewPanoramaDelegate)localObject);
        }
        return this.ajh;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
  }
  
  protected IStreetViewPanoramaFragmentDelegate mD()
  {
    this.ajg.mA();
    if (this.ajg.it() == null) {
      return null;
    }
    return ((a)this.ajg.it()).mD();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    if (paramBundle != null) {
      paramBundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
    }
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    b.a(this.ajg, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("StreetViewPanoramaFragment");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "StreetViewPanoramaFragment#onCreate", null);
      super.onCreate(paramBundle);
      this.ajg.onCreate(paramBundle);
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "StreetViewPanoramaFragment#onCreate", null);
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "StreetViewPanoramaFragment#onCreateView", null);
      paramLayoutInflater = this.ajg.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
      TraceMachine.exitMethod();
      return paramLayoutInflater;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "StreetViewPanoramaFragment#onCreateView", null);
      }
    }
  }
  
  public void onDestroy()
  {
    this.ajg.onDestroy();
    super.onDestroy();
  }
  
  public void onDestroyView()
  {
    this.ajg.onDestroyView();
    super.onDestroyView();
  }
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    super.onInflate(paramActivity, paramAttributeSet, paramBundle);
    b.a(this.ajg, paramActivity);
    paramAttributeSet = new Bundle();
    this.ajg.onInflate(paramActivity, paramAttributeSet, paramBundle);
  }
  
  public void onLowMemory()
  {
    this.ajg.onLowMemory();
    super.onLowMemory();
  }
  
  public void onPause()
  {
    this.ajg.onPause();
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    this.ajg.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null) {
      paramBundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
    }
    super.onSaveInstanceState(paramBundle);
    this.ajg.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
  
  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }
  
  static class a
    implements LifecycleDelegate
  {
    private final Fragment Sj;
    private final IStreetViewPanoramaFragmentDelegate aji;
    
    public a(Fragment paramFragment, IStreetViewPanoramaFragmentDelegate paramIStreetViewPanoramaFragmentDelegate)
    {
      this.aji = ((IStreetViewPanoramaFragmentDelegate)o.i(paramIStreetViewPanoramaFragmentDelegate));
      this.Sj = ((Fragment)o.i(paramFragment));
    }
    
    public IStreetViewPanoramaFragmentDelegate mD()
    {
      return this.aji;
    }
    
    public void onCreate(Bundle paramBundle)
    {
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {}
      try
      {
        localBundle = new Bundle();
        paramBundle = this.Sj.getArguments();
        if ((paramBundle != null) && (paramBundle.containsKey("StreetViewPanoramaOptions"))) {
          t.a(localBundle, "StreetViewPanoramaOptions", paramBundle.getParcelable("StreetViewPanoramaOptions"));
        }
        this.aji.onCreate(localBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      try
      {
        paramLayoutInflater = this.aji.onCreateView(e.k(paramLayoutInflater), e.k(paramViewGroup), paramBundle);
        return (View)e.f(paramLayoutInflater);
      }
      catch (RemoteException paramLayoutInflater)
      {
        throw new RuntimeRemoteException(paramLayoutInflater);
      }
    }
    
    public void onDestroy()
    {
      try
      {
        this.aji.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      try
      {
        this.aji.onDestroyView();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      try
      {
        this.aji.onInflate(e.k(paramActivity), null, paramBundle2);
        return;
      }
      catch (RemoteException paramActivity)
      {
        throw new RuntimeRemoteException(paramActivity);
      }
    }
    
    public void onLowMemory()
    {
      try
      {
        this.aji.onLowMemory();
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
        this.aji.onPause();
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
        this.aji.onResume();
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
        this.aji.onSaveInstanceState(paramBundle);
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
    extends a<StreetViewPanoramaFragment.a>
  {
    private final Fragment Sj;
    protected f<StreetViewPanoramaFragment.a> aiT;
    private Activity nr;
    
    b(Fragment paramFragment)
    {
      this.Sj = paramFragment;
    }
    
    private void setActivity(Activity paramActivity)
    {
      this.nr = paramActivity;
      mA();
    }
    
    protected void a(f<StreetViewPanoramaFragment.a> paramf)
    {
      this.aiT = paramf;
      mA();
    }
    
    public void mA()
    {
      if ((this.nr != null) && (this.aiT != null) && (it() == null)) {}
      try
      {
        MapsInitializer.initialize(this.nr);
        IStreetViewPanoramaFragmentDelegate localIStreetViewPanoramaFragmentDelegate = u.S(this.nr).k(e.k(this.nr));
        this.aiT.a(new StreetViewPanoramaFragment.a(this.Sj, localIStreetViewPanoramaFragmentDelegate));
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


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/maps/StreetViewPanoramaFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */