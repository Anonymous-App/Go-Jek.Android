package lib.gojek.base.util;

import android.text.TextUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import java.util.Iterator;
import java.util.List;

public class MapsUtils
{
  private static final int BOOKING_STATUS_DRIVER = 2;
  private static final String DESTINATION = "Destination";
  private static final String DRIVER = "Driver";
  private static final String ORIGIN = "Origin";
  
  public static MarkerOptions createMarker(LatLng paramLatLng, String paramString, int paramInt)
  {
    paramLatLng = new MarkerOptions().position(paramLatLng).title(paramString);
    paramLatLng.icon(BitmapDescriptorFactory.fromResource(paramInt));
    return paramLatLng;
  }
  
  private static double deg2rad(double paramDouble)
  {
    return 3.141592653589793D * paramDouble / 180.0D;
  }
  
  public static void drawRoute(GoogleMap paramGoogleMap, int paramInt1, String paramString1, String paramString2, String paramString3, double paramDouble1, double paramDouble2, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramGoogleMap.clear();
    paramString2 = getLatLngFromStr(paramString2);
    paramString3 = getLatLngFromStr(paramString3);
    for (;;)
    {
      try
      {
        localLatLng = new LatLng(paramDouble1, paramDouble2);
        paramGoogleMap.addMarker(createMarker(localLatLng, "Driver", paramInt2));
        if (paramInt1 != 2) {
          continue;
        }
        paramGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getMapMidPoint(paramString2.latitude, paramString2.longitude, localLatLng.latitude, localLatLng.longitude), getZoomLevel(paramString2.latitude, paramString2.longitude, localLatLng.latitude, localLatLng.longitude)));
        paramGoogleMap.addMarker(createMarker(paramString2, "Origin", paramInt3));
        paramGoogleMap.addMarker(createMarker(paramString3, "Destination", paramInt5));
      }
      catch (Exception localException)
      {
        LatLng localLatLng;
        if (paramInt1 != 2) {
          continue;
        }
        paramGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramString2, 12));
        paramGoogleMap.addMarker(createMarker(paramString2, "Origin", paramInt3));
        paramGoogleMap.addMarker(createMarker(paramString3, "Origin", paramInt5));
        continue;
        paramGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paramString3, 12));
        paramGoogleMap.addMarker(createMarker(paramString2, "Origin", paramInt4));
        paramGoogleMap.addMarker(createMarker(paramString3, "Destination", paramInt3));
        continue;
        paramGoogleMap.addPolyline(paramString1);
      }
      if ((paramString1 == null) || (TextUtils.isEmpty(paramString1))) {
        return;
      }
      paramString2 = PolyUtil.decode(paramString1);
      paramString1 = new PolylineOptions().width(5.0F).color(-16776961).geodesic(true);
      paramString2 = paramString2.iterator();
      if (!paramString2.hasNext()) {
        continue;
      }
      paramString1.add((LatLng)paramString2.next());
      continue;
      paramGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getMapMidPoint(paramString3.latitude, paramString3.longitude, localLatLng.latitude, localLatLng.longitude), getZoomLevel(paramString3.latitude, paramString3.longitude, localLatLng.latitude, localLatLng.longitude)));
      paramGoogleMap.addMarker(createMarker(paramString2, "Origin", paramInt4));
      paramGoogleMap.addMarker(createMarker(paramString3, "Destination", paramInt3));
    }
  }
  
  public static double getDistance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, char paramChar)
  {
    paramDouble2 = 60.0D * rad2deg(Math.acos(Math.sin(deg2rad(paramDouble1)) * Math.sin(deg2rad(paramDouble3)) + Math.cos(deg2rad(paramDouble1)) * Math.cos(deg2rad(paramDouble3)) * Math.cos(deg2rad(paramDouble2 - paramDouble4)))) * 1.1515D;
    if (paramChar == 'K') {
      paramDouble1 = paramDouble2 * 1.609344D;
    }
    do
    {
      return paramDouble1;
      paramDouble1 = paramDouble2;
    } while (paramChar != 'N');
    return paramDouble2 * 0.8684D;
  }
  
  public static LatLng getLatLngFromStr(String paramString)
  {
    paramString = paramString.split(",");
    return new LatLng(Double.parseDouble(paramString[0]), Double.parseDouble(paramString[1]));
  }
  
  public static LatLng getMapMidPoint(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d2 = Math.abs(Math.abs(paramDouble1) - Math.abs(paramDouble3)) / 2.0D;
    double d1 = Math.abs(Math.abs(paramDouble2) - Math.abs(paramDouble4)) / 2.0D;
    if (paramDouble1 < paramDouble3)
    {
      paramDouble1 += d2;
      if (paramDouble2 >= paramDouble4) {
        break label81;
      }
    }
    label81:
    for (paramDouble2 += d1;; paramDouble2 = paramDouble4 + d1)
    {
      return new LatLng(paramDouble1, paramDouble2);
      paramDouble1 = paramDouble3 + d2;
      break;
    }
  }
  
  public static int getZoomLevel(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    paramDouble1 = getDistance(paramDouble1, paramDouble2, paramDouble3, paramDouble4, 'K');
    if (paramDouble1 > 15.0D) {
      return 10;
    }
    if (paramDouble1 > 10.0D) {
      return 11;
    }
    if (paramDouble1 > 5.0D) {
      return 12;
    }
    if (paramDouble1 > 2.7D) {
      return 13;
    }
    if (paramDouble1 > 1.0D) {
      return 14;
    }
    return 15;
  }
  
  private static double rad2deg(double paramDouble)
  {
    return 180.0D * paramDouble / 3.141592653589793D;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/util/MapsUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */