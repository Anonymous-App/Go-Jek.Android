package lib.gojek.base.tools;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MapManipulator
{
  public static final float DEFAULT_ZOOM_LEVEL = 15.0F;
  private static final int POLYLINE_COLOR = -16776961;
  private static final int POLYLINE_WIDTH = 5;
  public static final String TAG = MapManipulator.class.getSimpleName();
  private static final int ZOOM_PADDING = 50;
  private GoogleMap googleMap;
  private HashMap<String, Marker> markersMap;
  
  public MapManipulator(GoogleMap paramGoogleMap)
  {
    this.googleMap = paramGoogleMap;
    this.markersMap = new HashMap();
  }
  
  private MarkerOptions createMarkerOptions(LatLng paramLatLng, String paramString1, String paramString2, int paramInt)
  {
    paramLatLng = new MarkerOptions().position(paramLatLng).title(paramString1);
    if ((paramString2 != null) && (paramString2.length() > 0)) {
      paramLatLng.snippet(paramString2);
    }
    if (paramInt != 0) {
      paramLatLng.icon(BitmapDescriptorFactory.fromResource(paramInt));
    }
    return paramLatLng;
  }
  
  private CameraUpdate getAllOrOneZoomCameraUpdate(HashMap<String, Marker> paramHashMap)
  {
    if (paramHashMap.size() > 1) {
      return getZoomBoundFromMarkerCameraUpdate(getMarkersAsList());
    }
    paramHashMap = (Marker)getMarkersAsList().get(0);
    if (paramHashMap != null) {
      return getZoomCameraUpdate(paramHashMap.getPosition(), 15.0F);
    }
    return null;
  }
  
  private List<Marker> getMarkersAsList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.markersMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localArrayList.add((Marker)this.markersMap.get(str));
    }
    return localArrayList;
  }
  
  private CameraUpdate getZoomCameraUpdate(LatLng paramLatLng, float paramFloat)
  {
    return CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(paramLatLng).zoom(paramFloat).build());
  }
  
  public Marker addMarker(LatLng paramLatLng, String paramString, int paramInt)
  {
    return addMarker(paramLatLng, paramString, null, paramInt);
  }
  
  public Marker addMarker(LatLng paramLatLng, String paramString1, String paramString2, int paramInt)
  {
    if (this.markersMap.containsKey(paramString1))
    {
      paramString1 = (Marker)this.markersMap.get(paramString1);
      paramString1.setPosition(paramLatLng);
      return paramString1;
    }
    paramLatLng = this.googleMap.addMarker(createMarkerOptions(paramLatLng, paramString1, paramString2, paramInt));
    this.markersMap.put(paramLatLng.getTitle(), paramLatLng);
    return paramLatLng;
  }
  
  public void animateCamera(CameraUpdate paramCameraUpdate)
  {
    this.googleMap.animateCamera(paramCameraUpdate);
  }
  
  public void drawPolyline(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      Object localObject = PolyUtil.decode(paramString);
      paramString = new PolylineOptions().width(5.0F).color(-16776961).geodesic(true);
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramString.add((LatLng)((Iterator)localObject).next());
      }
      this.googleMap.addPolyline(paramString);
    }
  }
  
  public Marker getMarker(String paramString)
  {
    if (!this.markersMap.containsKey(paramString)) {
      return null;
    }
    return (Marker)this.markersMap.get(paramString);
  }
  
  public CameraUpdate getZoomBoundFromMarkerCameraUpdate(List<Marker> paramList)
  {
    LatLngBounds.Builder localBuilder = new LatLngBounds.Builder();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localBuilder.include(((Marker)paramList.next()).getPosition());
    }
    return CameraUpdateFactory.newLatLngBounds(localBuilder.build(), 50);
  }
  
  public CameraUpdate getZoomBoundFromMarkerCameraUpdate(Marker[] paramArrayOfMarker)
  {
    return getZoomBoundFromMarkerCameraUpdate(new ArrayList(Arrays.asList(paramArrayOfMarker)));
  }
  
  public void moveCamera(CameraUpdate paramCameraUpdate)
  {
    this.googleMap.moveCamera(paramCameraUpdate);
  }
  
  public void moveCameraIncludeAll()
  {
    CameraUpdate localCameraUpdate = getAllOrOneZoomCameraUpdate(this.markersMap);
    if (localCameraUpdate != null) {
      moveCamera(localCameraUpdate);
    }
  }
  
  public void setCamera(LatLng paramLatLng)
  {
    setCamera(paramLatLng, 15.0F);
  }
  
  public void setCamera(LatLng paramLatLng, float paramFloat)
  {
    moveCamera(getZoomCameraUpdate(paramLatLng, paramFloat));
  }
  
  public void zoomCamera(LatLng paramLatLng)
  {
    zoomCamera(paramLatLng, 15.0F);
  }
  
  public void zoomCamera(LatLng paramLatLng, float paramFloat)
  {
    animateCamera(getZoomCameraUpdate(paramLatLng, paramFloat));
  }
  
  public void zoomCameraIncludeAll()
  {
    CameraUpdate localCameraUpdate = getAllOrOneZoomCameraUpdate(this.markersMap);
    if (localCameraUpdate != null) {
      animateCamera(localCameraUpdate);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/tools/MapManipulator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */