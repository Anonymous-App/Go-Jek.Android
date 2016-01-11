package com.gojek.app;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.facebook.appevents.AppEventsLogger;
import com.flurry.android.FlurryAgent;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.OauthImageLoader;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Driver;
import com.gojek.app.model.Push;
import com.gojek.app.model.PushModel;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.gojek.app.util.TrackingNotificationUtils;
import com.gojek.app.util.Util;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BookingStatus
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = BookingStatus.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private BookingRateDao bookingRateDao;
  private AppEventsLogger cancelFBLogger;
  private Handler handler = new Handler();
  private ImageLoader imageLoader;
  private boolean isBookingLoad = false;
  private double mActualPrice;
  private com.gojek.app.parcelable.BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private int mDoubleTapCounter;
  private Driver mDriver;
  private GoogleMap mGoogleMap;
  private ImageView mIVDriver;
  private MapView mMVLocation;
  private Push mPushData;
  private BroadcastReceiver mPushReceiver;
  private TextView mTVAllianz;
  private TextView mTVContactNameFrom;
  private TextView mTVContactNameTo;
  private TextView mTVContactNumberFrom;
  private TextView mTVContactNumberTo;
  private TextView mTVDriverETA;
  private TextView mTVDriverName;
  private TextView mTVDriverPhone;
  private TextView mTVDriverStatus;
  private TextView mTVItem;
  private TextView mTVLocationDetailFrom;
  private TextView mTVLocationDetailTo;
  private TextView mTVLocationFrom;
  private TextView mTVLocationTo;
  private TextView mTVOrderNo;
  private TextView mTVPleaseWait;
  private TextView mTVTime;
  private TextView mTVTotal;
  private View mVmap;
  private OauthImageLoader oauthImageLoader;
  private Menu optionsMenu;
  private VolleyClient volleyClient;
  
  private void bindReceiver()
  {
    this.mPushReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        BookingStatus.access$1302(BookingStatus.this, (Push)paramAnonymousIntent.getParcelableExtra("PUSH_DATA"));
        BookingStatus.this.actualPrice(BookingStatus.this.mPushData);
        if ((BookingStatus.this.mPushData != null) && (BookingStatus.this.mPushData.type.equals("BOOKING_COMPLETE")))
        {
          TrackingNotificationUtils.getInstance().trackTimeAndRate(BookingStatus.this.mBookingData.getServiceType(), 3, BookingStatus.this.mixPanel);
          paramAnonymousContext = new Intent(BookingStatus.this, ReviewExperience.class);
          paramAnonymousContext.putExtra("PUSH_DATA", BookingStatus.this.mPushData);
          paramAnonymousContext.putExtra("BOOKING_DATA", BookingStatus.this.mBookingData);
          BookingStatus.this.startActivity(paramAnonymousContext);
          BookingStatus.this.finish();
        }
        do
        {
          return;
          BookingStatus.this.doLoadBooking(BookingStatus.this.mBookingData.id);
        } while (BookingStatus.this.mBookingData.statusBooking != 2);
        TrackingNotificationUtils.getInstance().trackTimeAndRate(BookingStatus.this.mBookingData.getServiceType(), 2, BookingStatus.this.mixPanel);
        TrackingNotificationUtils.getInstance().setStartDate(new Date());
      }
    };
    registerReceiver(this.mPushReceiver, new IntentFilter("com.gojek.app.PUSH_RECEIVER"));
  }
  
  public static MarkerOptions createMarker(LatLng paramLatLng, String paramString, int paramInt)
  {
    paramLatLng = new MarkerOptions().position(paramLatLng).title(paramString);
    paramLatLng.icon(BitmapDescriptorFactory.fromResource(paramInt));
    return paramLatLng;
  }
  
  private void doCancel()
  {
    showSimpleProgressDialog("Canceling...", new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        BookingStatus.this.volleyClient.cancelAllRequest();
      }
    });
    FlurryAgent.logEvent("Cancel_Clicked");
    FlurryAgent.endTimedEvent("Order_Clicked");
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("orderNo", this.mBookingData.orderNo);
      localJSONObject.put("bookingId", "");
      localJSONObject.put("cancelReasonId", 4);
      localJSONObject.put("cancelDescription", getString(2131165437));
      localJSONObject.put("activitySource", 2);
      this.volleyClient.putAndGetJSON("https://api.gojek.co.id/gojek/v2/booking/cancelBooking", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(BookingStatus.TAG, "error cancel booking " + paramAnonymousVolleyError);
          BookingStatus.this.dismissSimpleProgressDialog();
          if (!(paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = BookingStatus.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            paramAnonymousVolleyError = "Failed cancel your booking\n" + paramAnonymousVolleyError;
            BookingStatus.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            return;
          }
          BookingStatus.this.isUnauthorizedApiAccess();
        }
        
        public void onResponse(JSONObject paramAnonymousJSONObject)
        {
          BookingStatus.this.dismissSimpleProgressDialog();
          if (paramAnonymousJSONObject != null)
          {
            BookingStatus.this.bookingHistoryDao.updateBookingToCancel(BookingStatus.this.mBookingData.getId());
            BookingStatus.this.mixPanel.track("TR: Booking cancelled");
            BookingStatus.this.showSimpleDialog(null, "Booking canceled", null, false, new BookingStatus.6.1(this));
          }
        }
      }, this.mCustomerSaved.getAccessToken());
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  private void doLoadBooking(int paramInt)
  {
    setRefreshActionButtonState(true);
    String str = String.format("https://api.gojek.co.id/gojek/v2/booking/%s", new Object[] { Integer.valueOf(paramInt) });
    this.volleyClient.get(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(BookingStatus.TAG, "error loadBooking by id " + paramAnonymousVolleyError);
        BookingStatus.this.setRefreshActionButtonState(false);
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          BookingStatus.this.doSessionAuthenticate();
        }
      }
      
      public void onResponse(com.gojek.app.parcelable.BookingStatus paramAnonymousBookingStatus)
      {
        BookingStatus.this.setRefreshActionButtonState(false);
        if (paramAnonymousBookingStatus != null)
        {
          BookingStatus.access$1402(BookingStatus.this, true);
          BookingStatus.access$002(BookingStatus.this, paramAnonymousBookingStatus);
          BookingStatus.this.setUIProperties();
          if (BookingStatus.this.mBookingData.statusBooking == 4)
          {
            BookingStatus.this.bookingRateDao.updateBookingStatus(BookingStatus.this.mBookingData.getId(), 4);
            BookingStatus.this.bookingHistoryDao.updateBookingStatus(BookingStatus.this.mBookingData.getId(), 4);
            paramAnonymousBookingStatus = new Intent(BookingStatus.this, ReviewExperience.class);
            paramAnonymousBookingStatus.putExtra("PUSH_DATA", BookingStatus.this.mPushData);
            paramAnonymousBookingStatus.putExtra("BOOKING_DATA", BookingStatus.this.mBookingData);
            BookingStatus.this.startActivity(paramAnonymousBookingStatus);
            BookingStatus.this.finish();
          }
        }
      }
    }, com.gojek.app.parcelable.BookingStatus.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void doLoadBooking(String paramString)
  {
    setRefreshActionButtonState(true);
    String str = String.format("https://api.gojek.co.id/gojek/v2/booking/findByOrderNo/%s", new Object[] { paramString });
    Log.i(TAG, "load booking orderNo " + paramString);
    this.volleyClient.get(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(BookingStatus.TAG, "error loadBooking by orderNo " + paramAnonymousVolleyError);
        BookingStatus.this.setRefreshActionButtonState(false);
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          BookingStatus.this.doSessionAuthenticate();
        }
      }
      
      public void onResponse(com.gojek.app.parcelable.BookingStatus paramAnonymousBookingStatus)
      {
        BookingStatus.this.setRefreshActionButtonState(false);
        if (paramAnonymousBookingStatus != null)
        {
          BookingStatus.access$1402(BookingStatus.this, true);
          BookingStatus.access$002(BookingStatus.this, paramAnonymousBookingStatus);
          Log.i(BookingStatus.TAG, "load booking orderNo response : : " + ((Addresses)BookingStatus.this.mBookingData.addresses.get(0)).routePolyline);
          BookingStatus.this.setUIProperties();
          if (BookingStatus.this.mBookingData.statusBooking == 4)
          {
            BookingStatus.this.bookingRateDao.updateBookingStatus(BookingStatus.this.mBookingData.getId(), 4);
            BookingStatus.this.bookingHistoryDao.updateBookingStatus(BookingStatus.this.mBookingData.getId(), 4);
            paramAnonymousBookingStatus = new Intent(BookingStatus.this, ReviewExperience.class);
            paramAnonymousBookingStatus.putExtra("PUSH_DATA", BookingStatus.this.mPushData);
            paramAnonymousBookingStatus.putExtra("BOOKING_DATA", BookingStatus.this.mBookingData);
            BookingStatus.this.startActivity(paramAnonymousBookingStatus);
            BookingStatus.this.finish();
          }
        }
      }
    }, com.gojek.app.parcelable.BookingStatus.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void doLoadDriver()
  {
    if ((!TextUtils.isEmpty(this.mBookingData.driverId)) && (!this.mBookingData.driverId.equals("null")))
    {
      String str = String.format("https://api.gojek.co.id/gojek/v2/drivers/%s", new Object[] { this.mBookingData.driverId });
      Log.i(TAG, "load driverId " + this.mBookingData.getDriverId());
      this.volleyClient.get(str, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(BookingStatus.TAG, "error get driver photo " + paramAnonymousVolleyError);
        }
        
        public void onResponse(Driver paramAnonymousDriver)
        {
          BookingStatus.access$302(BookingStatus.this, paramAnonymousDriver);
          if ((paramAnonymousDriver != null) && (!TextUtils.isEmpty(BookingStatus.this.mDriver.photograph)))
          {
            paramAnonymousDriver = String.format("https://api.gojek.co.id/gojek/v2/file/img/%s", new Object[] { BookingStatus.this.mDriver.photograph });
            BookingStatus.this.oauthImageLoader.getWithToken(paramAnonymousDriver, ImageLoader.getImageListener(BookingStatus.this.mIVDriver, 2130837873, 2130837873), BookingStatus.this.mCustomerSaved.getAccessToken());
          }
        }
      }, Driver.class, this.mCustomerSaved.getAccessToken());
    }
  }
  
  public static LatLng getLatLngFromStr(String paramString)
  {
    paramString = paramString.split(",");
    return new LatLng(Double.parseDouble(paramString[0]), Double.parseDouble(paramString[1]));
  }
  
  public static int getZoomLevel(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    paramDouble1 = Util.getDistance(paramDouble1, paramDouble2, paramDouble3, paramDouble4, 'K');
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
  
  private void init()
  {
    setTitle(getString(2131165333));
    this.mBookingData = ((com.gojek.app.parcelable.BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.mPushData = ((Push)getIntent().getParcelableExtra("PUSH_DATA"));
    this.mCustomerSaved = new CustomerSaved(this);
    this.bookingRateDao = new BookingRateDao(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.oauthImageLoader = VolleyClient.getInstance(this).getOauthImageLoader();
    this.imageLoader = VolleyClient.getInstance(this).getImageLoader();
    this.bookingHistoryDao = new BookingHistoryDao(this);
    initializeMap();
    bindReceiver();
    setUIProperties();
    if (this.mBookingData.getId() != 0) {
      doLoadBooking(this.mBookingData.id);
    }
    for (;;)
    {
      periodicRefresh();
      return;
      if ((this.mBookingData.getOrderNo() != null) && (!this.mBookingData.getOrderNo().isEmpty())) {
        doLoadBooking(this.mBookingData.getOrderNo());
      }
    }
  }
  
  private void initializeMap()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0)
    {
      this.mGoogleMap = this.mMVLocation.getMap();
      this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
      this.mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
      this.mGoogleMap.getUiSettings().setZoomGesturesEnabled(false);
      MapsInitializer.initialize(this);
      return;
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
  }
  
  private void periodicRefresh()
  {
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (BookingStatus.this.mBookingData.getId() != 0) {
          BookingStatus.this.doLoadBooking(BookingStatus.this.mBookingData.id);
        }
        while ((BookingStatus.this.mBookingData.getOrderNo() == null) || (BookingStatus.this.mBookingData.getOrderNo().isEmpty())) {
          return;
        }
        BookingStatus.this.doLoadBooking(BookingStatus.this.mBookingData.getOrderNo());
      }
    }, 8500L);
  }
  
  private void renderView()
  {
    setContentView(2130968635);
    this.mMVLocation = ((MapView)findViewById(2131624296));
    this.mTVLocationFrom = ((TextView)findViewById(2131624229));
    this.mTVLocationTo = ((TextView)findViewById(2131624234));
    this.mTVLocationDetailFrom = ((TextView)findViewById(2131624230));
    this.mTVLocationDetailTo = ((TextView)findViewById(2131624235));
    this.mTVContactNameFrom = ((TextView)findViewById(2131624232));
    this.mTVContactNumberFrom = ((TextView)findViewById(2131624233));
    this.mTVContactNameTo = ((TextView)findViewById(2131624237));
    this.mTVContactNumberTo = ((TextView)findViewById(2131624238));
    this.mTVItem = ((TextView)findViewById(2131624228));
    this.mTVTime = ((TextView)findViewById(2131624226));
    this.mTVDriverName = ((TextView)findViewById(2131624221));
    this.mTVDriverStatus = ((TextView)findViewById(2131624294));
    this.mTVDriverETA = ((TextView)findViewById(2131624295));
    this.mTVTotal = ((TextView)findViewById(2131624239));
    this.mTVOrderNo = ((TextView)findViewById(2131624251));
    this.mTVPleaseWait = ((TextView)findViewById(2131624298));
    this.mVmap = findViewById(2131624297);
    this.mIVDriver = ((ImageView)findViewById(2131624220));
    this.mTVOrderNo = ((TextView)findViewById(2131624251));
    this.mTVDriverPhone = ((TextView)findViewById(2131624248));
    this.mTVAllianz = ((TextView)findViewById(2131624299));
  }
  
  private void setUIProperties()
  {
    for (;;)
    {
      try
      {
        if ((this.mBookingData.getOrderNo() != null) && (!this.mBookingData.getOrderNo().isEmpty())) {
          this.mTVOrderNo.setText("ORDER NO " + this.mBookingData.getOrderNo());
        }
        setTextView(this.mTVLocationFrom, ((Addresses)this.mBookingData.addresses.get(0)).originName, new View[0]);
        setTextView(this.mTVLocationTo, ((Addresses)this.mBookingData.addresses.get(0)).destinationName, new View[0]);
        if ((this.mBookingData.serviceType == 5) || (this.mBookingData.serviceType == 6))
        {
          setTextView(this.mTVLocationDetailFrom, ((Addresses)this.mBookingData.addresses.get(0)).originAddress, new View[0]);
          setTextView(this.mTVLocationDetailTo, ((Addresses)this.mBookingData.addresses.get(0)).destinationNote, new View[0]);
          setTextView(this.mTVContactNameFrom, ((Addresses)this.mBookingData.addresses.get(0)).originContactName, new View[] { findViewById(2131624231) });
          setTextView(this.mTVContactNumberFrom, ((Addresses)this.mBookingData.addresses.get(0)).originContactPhone, new View[] { findViewById(2131624231) });
          setTextView(this.mTVContactNameTo, ((Addresses)this.mBookingData.addresses.get(0)).destinationContactName, new View[] { findViewById(2131624236) });
          setTextView(this.mTVContactNumberTo, ((Addresses)this.mBookingData.addresses.get(0)).destinationContactPhone, new View[] { findViewById(2131624236) });
          setTextView(this.mTVItem, ((Addresses)this.mBookingData.addresses.get(0)).item, new View[] { findViewById(2131624227) });
          setTextView(this.mTVTime, Util.formatDateFromAPI(this.mBookingData.timeField, null), new View[] { findViewById(2131624225) });
          setTextView(this.mTVDriverName, this.mBookingData.driverName, new View[0]);
          setTextView(this.mTVTotal, Util.getRupiahFormat(String.valueOf(this.mBookingData.totalPrice)), new View[0]);
          this.mTVAllianz.setVisibility(8);
          if (this.mBookingData.statusBooking != 2) {
            break label830;
          }
          setTextView(this.mTVDriverStatus, ((Addresses)this.mBookingData.addresses.get(0)).originName, new View[0]);
          i = 10;
        }
      }
      catch (Exception localException1)
      {
        int i;
        int j;
        Object localObject;
        String str2;
        String str1;
        localException1.printStackTrace();
        return;
      }
      try
      {
        j = Integer.parseInt(this.mBookingData.driverETA);
        i = j;
        j /= 60;
        i = j;
      }
      catch (Exception localException2)
      {
        continue;
      }
      this.mTVDriverETA.setText("ARRIVING IN " + i + " MINS");
      if ((this.mBookingData.serviceType != 5) && (this.mBookingData.serviceType != 6)) {
        break label888;
      }
      localObject = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
      if ((localObject == null) || (((List)localObject).size() <= 0)) {
        break label888;
      }
      str2 = getString(2131165576);
      str1 = str2;
      if (((List)localObject).size() > 0) {
        str1 = str2 + "\n";
      }
      localObject = ((List)localObject).iterator();
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      str2 = str1 + "- " + ((RouteItem)((Iterator)localObject).next()).itemName;
      str1 = str2;
      if (((Iterator)localObject).hasNext())
      {
        str1 = str2 + "\n";
        continue;
        setTextView(this.mTVLocationDetailFrom, ((Addresses)this.mBookingData.addresses.get(0)).originNote, new View[0]);
        continue;
        label830:
        setTextView(this.mTVDriverStatus, ((Addresses)this.mBookingData.addresses.get(0)).destinationName, new View[0]);
      }
    }
    setTextView(this.mTVItem, localException1, new View[0]);
    findViewById(2131624227).setVisibility(0);
    label888:
    setTextView(this.mTVDriverPhone, this.mBookingData.getDriverPhone(), new View[0]);
    if (this.mBookingData.serviceType != 6) {
      this.mTVPleaseWait.setVisibility(8);
    }
    if (this.mBookingData.statusBooking == 7)
    {
      findViewById(2131624301).setVisibility(8);
      this.mTVPleaseWait.setVisibility(8);
    }
    zoomRoute();
    doLoadDriver();
  }
  
  private void zoomRoute()
  {
    this.mGoogleMap.clear();
    Object localObject1 = getLatLngFromStr(((Addresses)this.mBookingData.addresses.get(0)).latLongOrigin);
    Object localObject2 = getLatLngFromStr(((Addresses)this.mBookingData.addresses.get(0)).latLongDestination);
    for (;;)
    {
      try
      {
        LatLng localLatLng1 = new LatLng(this.mBookingData.driverLatitude, this.mBookingData.driverLongitude);
        try
        {
          this.mGoogleMap.addMarker(createMarker(localLatLng1, "Driver", 2130837724));
          if (this.mBookingData.statusBooking == 2)
          {
            localLatLng2 = Util.getMapMidPoint(((LatLng)localObject1).latitude, ((LatLng)localObject1).longitude, localLatLng1.latitude, localLatLng1.longitude);
            i = getZoomLevel(((LatLng)localObject1).latitude, ((LatLng)localObject1).longitude, localLatLng1.latitude, localLatLng1.longitude);
            this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng2, i));
            this.mGoogleMap.addMarker(createMarker((LatLng)localObject1, "Origin", 2130837910));
            this.mGoogleMap.addMarker(createMarker((LatLng)localObject2, "Destination", 2130837871));
            localObject2 = PolyUtil.decode(((Addresses)this.mBookingData.addresses.get(0)).routePolyline);
            localObject1 = new PolylineOptions().width(5.0F).color(-16776961).geodesic(true);
            localObject2 = ((List)localObject2).iterator();
            if (!((Iterator)localObject2).hasNext()) {
              continue;
            }
            ((PolylineOptions)localObject1).add((LatLng)((Iterator)localObject2).next());
            continue;
          }
          LatLng localLatLng2 = Util.getMapMidPoint(((LatLng)localObject2).latitude, ((LatLng)localObject2).longitude, localLatLng1.latitude, localLatLng1.longitude);
          int i = getZoomLevel(((LatLng)localObject2).latitude, ((LatLng)localObject2).longitude, localLatLng1.latitude, localLatLng1.longitude);
          this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localLatLng2, i));
          this.mGoogleMap.addMarker(createMarker((LatLng)localObject1, "Origin", 2130837952));
          this.mGoogleMap.addMarker(createMarker((LatLng)localObject2, "Destination", 2130837910));
          continue;
          if (this.mBookingData.statusBooking != 2) {
            continue;
          }
        }
        catch (Exception localException1) {}
      }
      catch (Exception localException2)
      {
        continue;
      }
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject1, 12));
      this.mGoogleMap.addMarker(createMarker((LatLng)localObject1, "Origin", 2130837910));
      this.mGoogleMap.addMarker(createMarker((LatLng)localObject2, "Destination", 2130837871));
      continue;
      this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((LatLng)localObject2, 12));
      this.mGoogleMap.addMarker(createMarker((LatLng)localObject1, "Origin", 2130837952));
      this.mGoogleMap.addMarker(createMarker((LatLng)localObject2, "Destination", 2130837910));
    }
    this.mGoogleMap.addPolyline((PolylineOptions)localObject1);
  }
  
  public void actualPrice(Push paramPush)
  {
    if ((paramPush.type.equals("SHOPPING_ACTUAL_PRICE")) && (paramPush.model.message != null)) {
      this.mActualPrice = Double.parseDouble(paramPush.model.message.replaceAll("\\D+", ""));
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 401))
    {
      if (this.mBookingData.getId() == 0) {
        break label45;
      }
      doLoadBooking(this.mBookingData.id);
    }
    for (;;)
    {
      doLoadDriver();
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      label45:
      if ((this.mBookingData.getOrderNo() != null) && (!this.mBookingData.getOrderNo().isEmpty())) {
        doLoadBooking(this.mBookingData.getOrderNo());
      }
    }
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent(this, Home.class);
    localIntent.putExtra("FEED_BACK", 2);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        do
        {
          return;
        } while (this.mDriver == null);
        try
        {
          startActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + this.mDriver.phone)));
          return;
        }
        catch (Exception paramView)
        {
          return;
        }
      } while (this.mDriver == null);
      try
      {
        paramView = new Intent("android.intent.action.DIAL");
        paramView.setData(Uri.parse("tel:" + this.mDriver.phone));
        startActivity(paramView);
        return;
      }
      catch (Exception paramView)
      {
        return;
      }
      showCancelDialog();
      return;
      this.mDoubleTapCounter += 1;
      paramView = new Handler();
      Runnable local3 = new Runnable()
      {
        public void run()
        {
          BookingStatus.access$802(BookingStatus.this, 0);
        }
      };
      if (this.mDoubleTapCounter == 1)
      {
        paramView.postDelayed(local3, 250L);
        return;
      }
    } while (this.mDoubleTapCounter != 2);
    this.mDoubleTapCounter = 0;
    paramView = new Intent(this, BookingStatusMap.class);
    paramView.putExtra("BOOKING_DATA", this.mBookingData);
    startActivity(paramView);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    this.mMVLocation.onCreate(paramBundle);
    init();
    this.cancelFBLogger = AppEventsLogger.newLogger(getApplicationContext());
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.optionsMenu = paramMenu;
    getMenuInflater().inflate(2131755018, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.mMVLocation.onDestroy();
    try
    {
      unregisterReceiver(this.mPushReceiver);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    this.mMVLocation.onLowMemory();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    doLoadBooking(this.mBookingData.id);
    return true;
  }
  
  public void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    if ((this.mBookingData.getId() != 0) && (!this.isBookingLoad)) {
      doLoadBooking(this.mBookingData.id);
    }
    for (;;)
    {
      if (this.mDriver == null) {
        doLoadDriver();
      }
      super.onResume();
      this.mMVLocation.onResume();
      return;
      if ((this.mBookingData.getOrderNo() != null) && (!this.mBookingData.getOrderNo().isEmpty()) && (!this.isBookingLoad)) {
        doLoadBooking(this.mBookingData.getOrderNo());
      }
    }
  }
  
  public void setRefreshActionButtonState(boolean paramBoolean)
  {
    MenuItem localMenuItem;
    if (this.optionsMenu != null)
    {
      localMenuItem = this.optionsMenu.findItem(2131625037);
      if (localMenuItem != null)
      {
        if (!paramBoolean) {
          break label39;
        }
        localMenuItem.setActionView(2130968814);
      }
    }
    return;
    label39:
    localMenuItem.setActionView(null);
  }
  
  public void showCancelDialog()
  {
    new AlertDialog.Builder(this).setTitle(getString(2131165424)).setMessage(getString(2131165353)).setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        BookingStatus.this.doCancel();
        BookingStatus.this.cancelFBLogger.logEvent(BookingStatus.this.getString(2131165549));
      }
    }).setNegativeButton("No", null).show();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/BookingStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */