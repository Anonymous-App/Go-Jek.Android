package com.gojek.gotix.activities;

import android.os.Bundle;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.presenter.GotixBasePresenter;
import com.google.android.gms.maps.GoogleMap;
import lib.gojek.base.fragments.MapsFragment;
import lib.gojek.base.fragments.MapsFragment.MapsFragmentListener;
import lib.gojek.base.tools.MapManipulator;

public abstract class GotixBaseMapActivity<T extends GotixBasePresenter>
  extends GotixBaseActivity<T>
  implements MapsFragment.MapsFragmentListener
{
  public static final String TAG = GotixBaseMapActivity.class.getSimpleName();
  private MapManipulator mapManipulator;
  private MapsFragment mapsFragment;
  
  public GoogleMap getGoogleMap()
  {
    return this.mapsFragment.getGoogleMap();
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_maps_detail;
  }
  
  public MapManipulator getMapManipulator()
  {
    return this.mapManipulator;
  }
  
  public MapsFragment getMapsFragment()
  {
    return this.mapsFragment;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mapsFragment = MapsFragment.newInstance();
    this.mapsFragment.setListener(this);
    loadFragment(this.mapsFragment, R.id.mapsLayout, TAG);
  }
  
  public void onFragmentDestroy() {}
  
  public void onFragmentReady()
  {
    this.mapManipulator = new MapManipulator(getGoogleMap());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixBaseMapActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */