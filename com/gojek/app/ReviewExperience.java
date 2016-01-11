package com.gojek.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.facebook.appevents.AppEventsLogger;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.OauthImageLoader;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Driver;
import com.gojek.app.model.Push;
import com.gojek.app.model.PushModel;
import com.gojek.app.model.RestResponse;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.gojek.app.util.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewExperience
  extends GojekActivityBase
  implements View.OnClickListener
{
  private static final String TAG = ReviewExperience.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private BookingRateDao bookingRateDao;
  private double fbTotalSpend;
  private double mActualPrice;
  private BookingStatus mBookingData;
  private CheckBox mCBIsHelmet;
  private CheckBox mCBIsMasker;
  private CustomerSaved mCustomerSaved;
  private Driver mDriver;
  private EditText mETComment;
  private ImageView mIVSubmit;
  private LinearLayout mLLDriverHelmet;
  private LinearLayout mLLDriverMasker;
  private ProgressBar mProgress;
  private Push mPushData;
  private RatingBar mRating;
  private TextView mTVDriverName;
  private OauthImageLoader oauthImageLoader;
  private AppEventsLogger serviceFBLogger;
  private VolleyClient volleyClient;
  
  private void doLoadBooking(int paramInt)
  {
    String str = String.format("https://api.gojek.co.id/gojek/v2/booking/%s", new Object[] { Integer.valueOf(paramInt) });
    this.volleyClient.get(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(ReviewExperience.TAG, "error findBookingById " + paramAnonymousVolleyError);
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          ReviewExperience.this.doSessionAuthenticate();
        }
      }
      
      public void onResponse(BookingStatus paramAnonymousBookingStatus)
      {
        if (paramAnonymousBookingStatus != null)
        {
          ReviewExperience.access$602(ReviewExperience.this, paramAnonymousBookingStatus);
          ReviewExperience.this.setUIProperties();
        }
      }
    }, BookingStatus.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void doLoadDriver()
  {
    if ((!TextUtils.isEmpty(this.mBookingData.driverId)) && (!this.mBookingData.driverId.equals("null")))
    {
      String str = String.format("https://api.gojek.co.id/gojek/v2/drivers/%s", new Object[] { this.mBookingData.driverId });
      this.volleyClient.get(TAG, str, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.i(ReviewExperience.TAG, "error get driverPhoto " + paramAnonymousVolleyError);
        }
        
        public void onResponse(Driver paramAnonymousDriver)
        {
          if (paramAnonymousDriver != null)
          {
            ReviewExperience.access$202(ReviewExperience.this, paramAnonymousDriver);
            if (!TextUtils.isEmpty(ReviewExperience.this.mDriver.photograph))
            {
              paramAnonymousDriver = String.format("https://api.gojek.co.id/gojek/v2/file/img/%s", new Object[] { ReviewExperience.this.mDriver.photograph });
              Log.i(ReviewExperience.TAG, "request for driverImage " + paramAnonymousDriver);
              ImageView localImageView = (ImageView)ReviewExperience.this.findViewById(2131624220);
              ReviewExperience.this.oauthImageLoader.getWithToken(paramAnonymousDriver, ImageLoader.getImageListener(localImageView, 2130837873, 2130837873), ReviewExperience.this.mCustomerSaved.getAccessToken());
            }
          }
        }
      }, Driver.class, this.mCustomerSaved.getAccessToken());
    }
  }
  
  private void doReview()
  {
    final String str = this.mETComment.getText().toString();
    final int i = (int)this.mRating.getRating();
    this.mIVSubmit.setVisibility(4);
    this.mProgress.setVisibility(0);
    localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.putOpt("orderNo", this.mBookingData.orderNo);
        localJSONObject.putOpt("rate", Integer.valueOf(i));
        if (Util.isTextNotNullEmpty(str)) {
          localJSONObject.putOpt("feedback", str);
        }
        if (!this.mCBIsHelmet.isChecked()) {
          continue;
        }
        localJSONObject.putOpt("isHelmetAndJacket", Boolean.valueOf(true));
        if (!this.mCBIsMasker.isChecked()) {
          continue;
        }
        localJSONObject.putOpt("isMaskerAndHairCover", Boolean.valueOf(true));
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        continue;
        localJSONObject.putOpt("isMaskerAndHairCover", Boolean.valueOf(false));
        continue;
      }
      this.volleyClient.post("https://api.gojek.co.id/gojek/v2/booking/rate", localJSONObject, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(ReviewExperience.TAG, "doRating got error " + paramAnonymousVolleyError);
          ReviewExperience.this.mIVSubmit.setVisibility(0);
          ReviewExperience.this.mProgress.setVisibility(4);
          if ((paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = ReviewExperience.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            paramAnonymousVolleyError = "Failed to rate your booking\n" + paramAnonymousVolleyError;
            ReviewExperience.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            return;
          }
          ReviewExperience.this.isUnauthorizedApiAccess();
        }
        
        public void onResponse(RestResponse paramAnonymousRestResponse)
        {
          ReviewExperience.this.mBookingData.rate = i;
          ReviewExperience.this.mBookingData.feedback = str;
          ReviewExperience.this.bookingRateDao.updateBookingRate(ReviewExperience.this.mBookingData.getId(), i, str);
          ReviewExperience.this.bookingHistoryDao.updateBookingRate(ReviewExperience.this.mBookingData.getId(), i, str);
          if (!ReviewExperience.this.getIntent().getBooleanExtra("FORCE_RATING", false)) {
            ReviewExperience.this.gotoCompletePage();
          }
          for (;;)
          {
            ReviewExperience.this.mIVSubmit.setVisibility(0);
            ReviewExperience.this.mProgress.setVisibility(4);
            paramAnonymousRestResponse = new Bundle();
            paramAnonymousRestResponse.putString("fb_currency", "IDR");
            paramAnonymousRestResponse.putString("fb_content_type", "Total Spend");
            paramAnonymousRestResponse.putString("fb_content_id", String.valueOf(ReviewExperience.this.mBookingData.getId()));
            ReviewExperience.this.serviceFBLogger.logEvent(ReviewExperience.this.fbEventService());
            ReviewExperience.this.serviceFBLogger.logEvent(ReviewExperience.this.fbEventTotalSpend(), ReviewExperience.this.fbTotalSpend, paramAnonymousRestResponse);
            return;
            ReviewExperience.this.showSimpleDialog(null, "Thank you for your rating", "OK", false, new ReviewExperience.5.1(this));
          }
        }
      }, RestResponse.class, this.mCustomerSaved.getAccessToken());
      return;
      localJSONObject.putOpt("isHelmetAndJacket", Boolean.valueOf(false));
    }
  }
  
  private void gotoCompletePage()
  {
    Intent localIntent = new Intent(this, BookingComplete.class);
    localIntent.putExtra("BOOKING_DATA", this.mBookingData);
    startActivity(localIntent);
    finish();
  }
  
  private void init()
  {
    this.mCBIsHelmet.setChecked(false);
    this.mCBIsMasker.setChecked(false);
    this.mLLDriverHelmet.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (ReviewExperience.this.mCBIsHelmet.isChecked())
        {
          ReviewExperience.this.mCBIsHelmet.setChecked(false);
          return;
        }
        ReviewExperience.this.mCBIsHelmet.setChecked(true);
      }
    });
    this.mLLDriverMasker.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (ReviewExperience.this.mCBIsMasker.isChecked())
        {
          ReviewExperience.this.mCBIsMasker.setChecked(false);
          return;
        }
        ReviewExperience.this.mCBIsMasker.setChecked(true);
      }
    });
  }
  
  private void renderView()
  {
    setContentView(2130968817);
    this.mTVDriverName = ((TextView)findViewById(2131624221));
    this.mIVSubmit = ((ImageView)findViewById(2131624966));
    this.mETComment = ((EditText)findViewById(2131624961));
    this.mRating = ((RatingBar)findViewById(2131624249));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
    this.mLLDriverHelmet = ((LinearLayout)findViewById(2131624962));
    this.mCBIsHelmet = ((CheckBox)findViewById(2131624963));
    this.mLLDriverMasker = ((LinearLayout)findViewById(2131624964));
    this.mCBIsMasker = ((CheckBox)findViewById(2131624965));
  }
  
  private void setUIProperties()
  {
    try
    {
      setTextView(this.mTVDriverName, this.mBookingData.driverName, new View[0]);
      doLoadDriver();
      if (this.mBookingData.getServiceType() == 5)
      {
        this.mLLDriverMasker.setVisibility(8);
        if (this.mBookingData.getServiceType() == 0) {
          break label82;
        }
        this.mLLDriverHelmet.setVisibility(8);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        continue;
        this.mLLDriverMasker.setVisibility(0);
      }
      label82:
      this.mLLDriverHelmet.setVisibility(0);
    }
  }
  
  public String fbEventService()
  {
    switch (this.mBookingData.getServiceType())
    {
    case 4: 
    default: 
      return "";
    case 2: 
      return getString(2131165545);
    case 1: 
      return getString(2131165552);
    case 5: 
      return getString(2131165543);
    case 6: 
      return getString(2131165547);
    }
    return getString(2131165547);
  }
  
  public String fbEventTotalSpend()
  {
    switch (this.mBookingData.getServiceType())
    {
    case 4: 
    default: 
      return "";
    case 2: 
      this.fbTotalSpend = this.mBookingData.totalPrice;
      return getString(2131165544);
    case 1: 
      this.fbTotalSpend = this.mBookingData.totalPrice;
      return getString(2131165551);
    case 5: 
      this.fbTotalSpend = (this.mActualPrice + this.mBookingData.totalPrice);
      return getString(2131165542);
    case 6: 
      this.fbTotalSpend = (this.mActualPrice + this.mBookingData.totalPrice);
      return getString(2131165546);
    }
    this.fbTotalSpend = (this.mActualPrice + this.mBookingData.totalPrice);
    return getString(2131165546);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 401))
    {
      if (this.mPushData != null) {
        doLoadBooking(this.mPushData.model.id);
      }
      doLoadDriver();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onClick(View paramView)
  {
    doReview();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165333));
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.mPushData = ((Push)getIntent().getParcelableExtra("PUSH_DATA"));
    this.mActualPrice = getIntent().getExtras().getDouble("ACTUAL_PRICE");
    this.bookingRateDao = new BookingRateDao(this);
    this.bookingHistoryDao = new BookingHistoryDao(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.oauthImageLoader = VolleyClient.getInstance(this).getOauthImageLoader();
    this.mCustomerSaved = new CustomerSaved(this);
    this.serviceFBLogger = AppEventsLogger.newLogger(getApplicationContext());
    renderView();
    setUIProperties();
    init();
    if (this.mPushData != null) {
      doLoadBooking(this.mPushData.model.id);
    }
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
  }
  
  protected void onStop()
  {
    this.volleyClient.cancelAllRequest(TAG);
    super.onStop();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/ReviewExperience.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */