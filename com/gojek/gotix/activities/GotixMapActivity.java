package com.gojek.gotix.activities;

import android.content.Intent;
import android.os.Bundle;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.network.model.LocationMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GotixMapActivity
  extends GotixBaseMapActivity
{
  public static final String TAG = GotixMapActivity.class.getSimpleName();
  private List<LocationMap> locationMaps;
  
  private LatLng convertLatLng(LocationMap paramLocationMap)
  {
    return new LatLng(Double.parseDouble(paramLocationMap.getLatitude()), Double.parseDouble(paramLocationMap.getLongitude()));
  }
  
  private void setLocationMaps(List<LocationMap> paramList)
  {
    Observable.from(paramList).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).map(GotixMapActivity..Lambda.2.lambdaFactory$(this)).toList().subscribe(GotixMapActivity..Lambda.3.lambdaFactory$(this), GotixMapActivity..Lambda.4.lambdaFactory$());
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_maps_detail;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.locationMaps = getIntent().getParcelableArrayListExtra("locations");
  }
  
  public void onFragmentReady()
  {
    super.onFragmentReady();
    getGoogleMap().setOnMapLoadedCallback(GotixMapActivity..Lambda.1.lambdaFactory$(this));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixMapActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */