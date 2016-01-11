package lib.gojek.base.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PickLocationHelper
{
  public static final String CACHE_LOCATION_HISTORY = "CACHE_LOCATION_HISTORY";
  public static final String LOCATION_ADDRESS = "LOCATION_ADDRESS";
  public static final String LOCATION_DESC = "LOCATION_DESC";
  public static final String LOCATION_ID = "LOCATION_ID";
  public static final String LOCATION_LAT = "LOCATION_LAT";
  public static final String LOCATION_LNG = "LOCATION_LNG";
  public static final String LOCATION_NAME = "LOCATION_NAME";
  public static final String PICK_LOCATION_INTENT_FILTER = "com.gojek.app.PICK_LOCATION";
  public static final int PICK_LOCATION_REQUEST_CODE = 2;
  private Activity activity;
  private String address;
  private Bundle bundle;
  private double lat;
  private int locationId;
  private double lon;
  private String name;
  
  public PickLocationHelper(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  private void bindDataLocationFromBundle(Bundle paramBundle)
  {
    this.locationId = paramBundle.getInt("LOCATION_ID", 0);
    this.name = paramBundle.getString("LOCATION_NAME", "");
    this.address = paramBundle.getString("LOCATION_ADDRESS", "");
    this.lat = paramBundle.getDouble("LOCATION_LAT", 0.0D);
    this.lon = paramBundle.getDouble("LOCATION_LNG", 0.0D);
  }
  
  public Activity getActivity()
  {
    return this.activity;
  }
  
  public String getAddress()
  {
    return this.address;
  }
  
  public Bundle getBundle()
  {
    return this.bundle;
  }
  
  public double getLat()
  {
    return this.lat;
  }
  
  public int getLocationId()
  {
    return this.locationId;
  }
  
  public double getLon()
  {
    return this.lon;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean onActivityForResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((2 == paramInt1) && (-1 == paramInt2) && (paramIntent != null))
    {
      this.bundle = paramIntent.getExtras();
      bindDataLocationFromBundle(this.bundle);
      return true;
    }
    return false;
  }
  
  public void openPickLocationPage()
  {
    Intent localIntent = new Intent("com.gojek.app.PICK_LOCATION");
    localIntent.putExtra("CACHE_LOCATION_HISTORY", true);
    this.activity.startActivityForResult(localIntent, 2);
  }
  
  public void setActivity(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  public void setAddress(String paramString)
  {
    this.address = paramString;
  }
  
  public void setBundle(Bundle paramBundle)
  {
    this.bundle = paramBundle;
  }
  
  public void setLat(double paramDouble)
  {
    this.lat = paramDouble;
  }
  
  public void setLocationId(int paramInt)
  {
    this.locationId = paramInt;
  }
  
  public void setLon(double paramDouble)
  {
    this.lon = paramDouble;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/PickLocationHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */