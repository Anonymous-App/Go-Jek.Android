package com.gojek.app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.OauthImageLoader;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Driver;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.util.Util;
import java.util.Iterator;
import java.util.List;

public class BookingComplete
  extends GojekActivityBase
{
  private static final String TAG = BookingComplete.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private boolean isCanceled;
  private BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private Driver mDriver;
  private ImageView mIVItem;
  private LinearLayout mLLItem;
  private RatingBar mRating;
  private TextView mTVComment;
  private TextView mTVContactNameFrom;
  private TextView mTVContactNameTo;
  private TextView mTVContactNumberFrom;
  private TextView mTVContactNumberTo;
  private TextView mTVDriverName;
  private TextView mTVDriverPhone;
  private TextView mTVItem;
  private TextView mTVLocationDetailFrom;
  private TextView mTVLocationDetailTo;
  private TextView mTVLocationFrom;
  private TextView mTVLocationTo;
  private TextView mTVOrderNo;
  private TextView mTVReceivedBy;
  private TextView mTVStatusMessage;
  private TextView mTVTime;
  private TextView mTVTotal;
  private OauthImageLoader oauthImageLoader;
  
  private void doLoadDriver()
  {
    if ((!TextUtils.isEmpty(this.mBookingData.driverId)) && (!this.mBookingData.driverId.equals("null")))
    {
      String str = String.format("https://api.gojek.co.id/gojek/v2/drivers/%s", new Object[] { this.mBookingData.driverId });
      VolleyClient.getInstance(this).get(str, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(BookingComplete.TAG, "error get driver photo " + (paramAnonymousVolleyError instanceof AuthFailureError));
          if ((paramAnonymousVolleyError instanceof AuthFailureError))
          {
            Log.e(BookingComplete.TAG, "error authenticate ");
            BookingComplete.this.doSessionAuthenticate();
          }
        }
        
        public void onResponse(Driver paramAnonymousDriver)
        {
          BookingComplete.access$002(BookingComplete.this, paramAnonymousDriver);
          if ((paramAnonymousDriver != null) && (!TextUtils.isEmpty(BookingComplete.this.mDriver.photograph)))
          {
            paramAnonymousDriver = String.format("https://api.gojek.co.id/gojek/v2/file/img/%s", new Object[] { BookingComplete.this.mDriver.photograph });
            Log.d(BookingComplete.TAG, "get driver photo " + paramAnonymousDriver);
            ImageView localImageView = (ImageView)BookingComplete.this.findViewById(2131624220);
            BookingComplete.this.oauthImageLoader.getWithToken(paramAnonymousDriver, ImageLoader.getImageListener(localImageView, 2130837873, 2130837873), BookingComplete.this.mCustomerSaved.getAccessToken());
          }
        }
      }, Driver.class, this.mCustomerSaved.getAccessToken());
    }
  }
  
  private void renderView()
  {
    setContentView(2130968632);
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
    this.mRating = ((RatingBar)findViewById(2131624249));
    this.mTVComment = ((TextView)findViewById(2131624250));
    this.mTVStatusMessage = ((TextView)findViewById(2131624246));
    this.mTVReceivedBy = ((TextView)findViewById(2131624255));
    this.mLLItem = ((LinearLayout)findViewById(2131624253));
    this.mIVItem = ((ImageView)findViewById(2131624227));
    this.mTVOrderNo = ((TextView)findViewById(2131624251));
    this.mTVTotal = ((TextView)findViewById(2131624239));
    this.mTVDriverPhone = ((TextView)findViewById(2131624248));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 401))
    {
      Log.i(TAG, "result activity result from sign-in");
      if ((!TextUtils.isEmpty(this.mBookingData.driverId)) && (!this.mBookingData.driverId.equals("null"))) {
        doLoadDriver();
      }
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent(this, Home.class);
    localIntent.putExtra("FEED_BACK", 2);
    localIntent.putExtra("HISTORY_TAB", 1);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165333));
    this.oauthImageLoader = VolleyClient.getInstance(this).getOauthImageLoader();
    this.mCustomerSaved = new CustomerSaved(this);
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.bookingHistoryDao = new BookingHistoryDao(this);
    renderView();
    if ((this.mBookingData.getOrderNo() != null) && (!this.mBookingData.getOrderNo().isEmpty())) {
      this.mTVOrderNo.setText("ORDER NO " + this.mBookingData.getOrderNo());
    }
    this.mTVLocationFrom.setText(((Addresses)this.mBookingData.addresses.get(0)).originName);
    this.mTVLocationTo.setText(((Addresses)this.mBookingData.addresses.get(0)).destinationName);
    if (this.mBookingData.serviceType == 5)
    {
      setTextView(this.mTVLocationDetailFrom, ((Addresses)this.mBookingData.addresses.get(0)).originAddress, new View[0]);
      this.mTVLocationDetailTo.setText(((Addresses)this.mBookingData.addresses.get(0)).destinationNote);
      setTextView(this.mTVContactNameFrom, ((Addresses)this.mBookingData.addresses.get(0)).originContactName, new View[] { findViewById(2131624231) });
      setTextView(this.mTVContactNumberFrom, ((Addresses)this.mBookingData.addresses.get(0)).originContactPhone, new View[] { findViewById(2131624231) });
      setTextView(this.mTVContactNameTo, ((Addresses)this.mBookingData.addresses.get(0)).destinationContactName, new View[] { findViewById(2131624236) });
      setTextView(this.mTVContactNumberTo, ((Addresses)this.mBookingData.addresses.get(0)).destinationContactPhone, new View[] { findViewById(2131624236) });
      setTextView(this.mTVTime, Util.formatDateFromAPI(this.mBookingData.timeField, null), new View[] { findViewById(2131624225) });
      setTextView(this.mTVDriverName, this.mBookingData.driverName, new View[0]);
      setTextView(this.mTVTotal, Util.getRupiahFormat(String.valueOf(this.mBookingData.totalPrice)), new View[0]);
      if (this.mBookingData.serviceType == 1) {
        this.mTVLocationDetailTo.setVisibility(8);
      }
      if (this.mBookingData.serviceType != 2) {
        break label987;
      }
      findViewById(2131624254).setVisibility(0);
      this.mTVReceivedBy.setText(((Addresses)this.mBookingData.addresses.get(0)).receivedBy);
      label556:
      if ((TextUtils.isEmpty(this.mBookingData.driverId)) || (this.mBookingData.driverId.equals("null"))) {
        break label1002;
      }
      doLoadDriver();
    }
    for (;;)
    {
      if (this.mBookingData.cancelReasonId == 4)
      {
        this.mTVStatusMessage.setBackgroundColor(getResources().getColor(2131558500));
        this.mTVStatusMessage.setText("THIS BOOKING HAS BEEN CANCELED");
        this.isCanceled = true;
      }
      if (this.mBookingData.statusBooking == 5)
      {
        this.mTVStatusMessage.setBackgroundColor(getResources().getColor(2131558500));
        this.mTVStatusMessage.setText("SORRY CANNOT FIND DRIVER");
        this.isCanceled = true;
      }
      if (this.mBookingData.serviceType != 5) {
        break label1137;
      }
      Object localObject1 = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
      if ((localObject1 == null) || (((List)localObject1).size() <= 0)) {
        break label1041;
      }
      paramBundle = new StringBuffer();
      paramBundle.append(getString(2131165576));
      if (((List)localObject1).size() > 0) {
        paramBundle.append("<br />");
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (RouteItem)((Iterator)localObject1).next();
        paramBundle.append("- ");
        if (((RouteItem)localObject2).quantity > 0) {
          paramBundle.append("[" + ((RouteItem)localObject2).quantity + "] ");
        }
        paramBundle.append(((RouteItem)localObject2).itemName + "<br />");
        if ((((RouteItem)localObject2).notes != null) && (((RouteItem)localObject2).notes.trim().length() > 0))
        {
          localObject2 = "<i>" + ((RouteItem)localObject2).notes + "</i>";
          paramBundle.append("&nbsp;&nbsp;" + (String)localObject2);
        }
        if (((Iterator)localObject1).hasNext()) {
          paramBundle.append("<br />");
        }
      }
      setTextView(this.mTVLocationDetailFrom, ((Addresses)this.mBookingData.addresses.get(0)).originNote, new View[0]);
      break;
      label987:
      findViewById(2131624254).setVisibility(8);
      break label556;
      label1002:
      findViewById(2131624247).setVisibility(8);
    }
    this.mTVItem.setText(Html.fromHtml(paramBundle.toString()));
    findViewById(2131624227).setVisibility(0);
    label1041:
    this.bookingHistoryDao.updateBookingStatus(this.mBookingData.getId(), this.mBookingData.getStatusBooking());
    if (this.mBookingData.rate > 0)
    {
      this.mRating.setVisibility(0);
      this.mRating.setRating(this.mBookingData.rate);
      label1095:
      if (!TextUtils.isEmpty(this.mBookingData.feedback)) {
        break label1291;
      }
      this.mTVComment.setVisibility(8);
    }
    for (;;)
    {
      setTextView(this.mTVDriverPhone, this.mBookingData.getDriverPhone(), new View[0]);
      return;
      label1137:
      if (Util.isTextNotNullEmpty(((Addresses)this.mBookingData.addresses.get(0)).item))
      {
        setTextView(this.mTVItem, ((Addresses)this.mBookingData.addresses.get(0)).item, new View[] { findViewById(2131624227) });
        break;
      }
      this.mTVItem.setVisibility(8);
      this.mIVItem.setVisibility(8);
      break;
      if ((this.mBookingData.rate == 0) && (!this.isCanceled))
      {
        paramBundle = new Intent(this, ReviewExperience.class);
        paramBundle.putExtra("BOOKING_DATA", this.mBookingData);
        startActivity(paramBundle);
        finish();
        break label1095;
      }
      this.mRating.setVisibility(8);
      break label1095;
      label1291:
      this.mTVComment.setVisibility(0);
      this.mTVComment.setText(this.mBookingData.feedback);
    }
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/BookingComplete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */