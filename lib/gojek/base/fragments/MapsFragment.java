package lib.gojek.base.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import lib.gojek.base.AbstractBaseFragment;
import lib.gojek.base.R.id;
import lib.gojek.base.R.layout;

public class MapsFragment
  extends AbstractBaseFragment
{
  private GoogleMap googleMap;
  private MapsFragmentListener listener;
  private View mapView;
  
  private void bindViewById(View paramView)
  {
    this.googleMap = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.baseMap)).getMap();
    this.mapView = paramView.findViewById(R.id.mapView);
  }
  
  public static MapsFragment newInstance()
  {
    return new MapsFragment();
  }
  
  public GoogleMap getGoogleMap()
  {
    return this.googleMap;
  }
  
  protected int getLayout()
  {
    return R.layout.base_maps;
  }
  
  public View getMapView()
  {
    return this.mapView;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    if (this.listener != null) {
      this.listener.onFragmentDestroy();
    }
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    bindViewById(paramView);
    if (this.listener != null) {
      this.listener.onFragmentReady();
    }
  }
  
  public void setListener(MapsFragmentListener paramMapsFragmentListener)
  {
    this.listener = paramMapsFragmentListener;
  }
  
  public void setMyLocationButtonEnabled(boolean paramBoolean)
  {
    this.googleMap.getUiSettings().setMyLocationButtonEnabled(paramBoolean);
  }
  
  public void setZoomControlsEnabled(boolean paramBoolean)
  {
    this.googleMap.getUiSettings().setZoomControlsEnabled(paramBoolean);
  }
  
  public void setZoomGesturesEnabled(boolean paramBoolean)
  {
    this.googleMap.getUiSettings().setZoomGesturesEnabled(paramBoolean);
  }
  
  public static abstract interface MapsFragmentListener
  {
    public abstract void onFragmentDestroy();
    
    public abstract void onFragmentReady();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/fragments/MapsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */