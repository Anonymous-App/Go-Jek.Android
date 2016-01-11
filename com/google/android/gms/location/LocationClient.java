package com.google.android.gms.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.lz;
import com.google.android.gms.internal.ma;
import com.google.android.gms.internal.mc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class LocationClient
  implements GooglePlayServicesClient
{
  public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
  public static final String KEY_MOCK_LOCATION = "mockLocation";
  private final lz aea;
  
  public LocationClient(Context paramContext, GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.aea = new lz(paramContext, paramConnectionCallbacks, paramOnConnectionFailedListener, "location");
  }
  
  public static int getErrorCode(Intent paramIntent)
  {
    return paramIntent.getIntExtra("gms_error_code", -1);
  }
  
  public static int getGeofenceTransition(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
    if (i == -1) {}
    while ((i != 1) && (i != 2) && (i != 4)) {
      return -1;
    }
    return i;
  }
  
  public static List<Geofence> getTriggeringGeofences(Intent paramIntent)
  {
    Object localObject = (ArrayList)paramIntent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
    if (localObject == null) {
      return null;
    }
    paramIntent = new ArrayList(((ArrayList)localObject).size());
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramIntent.add(mc.h((byte[])((Iterator)localObject).next()));
    }
    return paramIntent;
  }
  
  public static Location getTriggeringLocation(Intent paramIntent)
  {
    return (Location)paramIntent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location");
  }
  
  public static boolean hasError(Intent paramIntent)
  {
    return paramIntent.hasExtra("gms_error_code");
  }
  
  public void addGeofences(List<Geofence> paramList, PendingIntent paramPendingIntent, OnAddGeofencesResultListener paramOnAddGeofencesResultListener)
  {
    ArrayList localArrayList = null;
    if (paramList != null)
    {
      localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Geofence localGeofence = (Geofence)paramList.next();
        o.b(localGeofence instanceof mc, "Geofence must be created using Geofence.Builder.");
        localArrayList.add((mc)localGeofence);
      }
    }
    try
    {
      this.aea.addGeofences(localArrayList, paramPendingIntent, paramOnAddGeofencesResultListener);
      return;
    }
    catch (RemoteException paramList)
    {
      throw new IllegalStateException(paramList);
    }
  }
  
  public void connect()
  {
    this.aea.connect();
  }
  
  public void disconnect()
  {
    this.aea.disconnect();
  }
  
  public Location getLastLocation()
  {
    return this.aea.getLastLocation();
  }
  
  public boolean isConnected()
  {
    return this.aea.isConnected();
  }
  
  public boolean isConnecting()
  {
    return this.aea.isConnecting();
  }
  
  public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.aea.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.aea.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.aea.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.aea.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void removeGeofences(PendingIntent paramPendingIntent, OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
  {
    try
    {
      this.aea.removeGeofences(paramPendingIntent, paramOnRemoveGeofencesResultListener);
      return;
    }
    catch (RemoteException paramPendingIntent)
    {
      throw new IllegalStateException(paramPendingIntent);
    }
  }
  
  public void removeGeofences(List<String> paramList, OnRemoveGeofencesResultListener paramOnRemoveGeofencesResultListener)
  {
    try
    {
      this.aea.removeGeofences(paramList, paramOnRemoveGeofencesResultListener);
      return;
    }
    catch (RemoteException paramList)
    {
      throw new IllegalStateException(paramList);
    }
  }
  
  public void removeLocationUpdates(PendingIntent paramPendingIntent)
  {
    try
    {
      this.aea.removeLocationUpdates(paramPendingIntent);
      return;
    }
    catch (RemoteException paramPendingIntent)
    {
      throw new IllegalStateException(paramPendingIntent);
    }
  }
  
  public void removeLocationUpdates(LocationListener paramLocationListener)
  {
    try
    {
      this.aea.removeLocationUpdates(paramLocationListener);
      return;
    }
    catch (RemoteException paramLocationListener)
    {
      throw new IllegalStateException(paramLocationListener);
    }
  }
  
  public void requestLocationUpdates(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
  {
    try
    {
      this.aea.b(ma.b(paramLocationRequest), paramPendingIntent);
      return;
    }
    catch (RemoteException paramLocationRequest)
    {
      throw new IllegalStateException(paramLocationRequest);
    }
  }
  
  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener)
  {
    try
    {
      this.aea.a(ma.b(paramLocationRequest), paramLocationListener);
      return;
    }
    catch (RemoteException paramLocationRequest)
    {
      throw new IllegalStateException(paramLocationRequest);
    }
  }
  
  public void requestLocationUpdates(LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper)
  {
    try
    {
      this.aea.a(ma.b(paramLocationRequest), paramLocationListener, paramLooper);
      return;
    }
    catch (RemoteException paramLocationRequest)
    {
      throw new IllegalStateException(paramLocationRequest);
    }
  }
  
  public void setMockLocation(Location paramLocation)
  {
    try
    {
      this.aea.setMockLocation(paramLocation);
      return;
    }
    catch (RemoteException paramLocation)
    {
      throw new IllegalStateException(paramLocation);
    }
  }
  
  public void setMockMode(boolean paramBoolean)
  {
    try
    {
      this.aea.setMockMode(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.aea.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.aea.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public static abstract interface OnAddGeofencesResultListener
  {
    public abstract void onAddGeofencesResult(int paramInt, String[] paramArrayOfString);
  }
  
  public static abstract interface OnRemoveGeofencesResultListener
  {
    public abstract void onRemoveGeofencesByPendingIntentResult(int paramInt, PendingIntent paramPendingIntent);
    
    public abstract void onRemoveGeofencesByRequestIdsResult(int paramInt, String[] paramArrayOfString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/location/LocationClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */