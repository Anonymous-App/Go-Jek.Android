package com.gojek.app;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.MakeBookingResponse;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.gojek.app.persistence.domain.BookingRate;
import com.gojek.app.util.Util;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;

public class BookingTryAgain
  extends GojekActivityBase
{
  private static final String TAG = BookingTryAgain.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private BookingRateDao bookingRateDao;
  private BookingStatus mBookingData;
  private CustomerSaved mCustomerSaved;
  private ImageView mIVTryAgain;
  private ProgressBar mProgressBar;
  private VolleyClient volleyClient;
  
  private void doTryAgainBooking()
  {
    final Object localObject = this.bookingRateDao.findByOrderNo(this.mBookingData.getOrderNo());
    if (localObject == null)
    {
      Log.e(TAG, "can't get bookingData for booking_no " + this.mBookingData.getOrderNo());
      showSimpleDialog(null, "Sorry can't try again your booking, data not found.\nPlease make new booking.", null, true, null);
      return;
    }
    localObject = ((BookingRate)localObject).getBookingJson();
    if ((localObject == null) || (((String)localObject).isEmpty()))
    {
      Log.e(TAG, "can't get booking_json for booking_no " + this.mBookingData.getOrderNo());
      showSimpleDialog(null, "Sorry can't try again your booking, data not found.\nPlease make new booking.", null, true, null);
      return;
    }
    this.mIVTryAgain.setVisibility(8);
    this.mProgressBar.setVisibility(0);
    this.bookingHistoryDao.deleteHistory(this.mBookingData.getId());
    try
    {
      this.volleyClient.post("https://api.gojek.co.id/gojek/v2/booking/v3/makeBooking", JSONObjectInstrumentation.init((String)localObject), new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          Log.e(BookingTryAgain.TAG, "error try_again makeBooking " + paramAnonymousVolleyError);
          BookingTryAgain.this.mIVTryAgain.setVisibility(0);
          BookingTryAgain.this.mProgressBar.setVisibility(8);
          if (!(paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = BookingTryAgain.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            paramAnonymousVolleyError = "Failed to make your booking\n" + paramAnonymousVolleyError;
            BookingTryAgain.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            return;
          }
          BookingTryAgain.this.isUnauthorizedApiAccess();
        }
        
        public void onResponse(MakeBookingResponse paramAnonymousMakeBookingResponse)
        {
          BookingTryAgain.this.mIVTryAgain.setVisibility(0);
          BookingTryAgain.this.mProgressBar.setVisibility(8);
          Log.i(BookingTryAgain.TAG, "success try_again booking " + BookingTryAgain.this.mBookingData.getOrderNo());
          Object localObject1;
          if (paramAnonymousMakeBookingResponse != null) {
            localObject1 = null;
          }
          try
          {
            localObject2 = (BookingStatus)BookingTryAgain.this.mBookingData.clone();
            localObject1 = localObject2;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              Object localObject2;
              boolean bool;
              localException.printStackTrace();
            }
          }
          bool = paramAnonymousMakeBookingResponse.isPrebook();
          localObject2 = paramAnonymousMakeBookingResponse.getPrebookMessage();
          ((BookingStatus)localObject1).id = paramAnonymousMakeBookingResponse.getId();
          ((BookingStatus)localObject1).orderNo = paramAnonymousMakeBookingResponse.getOrderNo();
          paramAnonymousMakeBookingResponse = new Customer();
          paramAnonymousMakeBookingResponse.customerId = BookingTryAgain.this.mCustomerSaved.customerId;
          ((BookingStatus)localObject1).setCustomer(paramAnonymousMakeBookingResponse);
          BookingTryAgain.this.bookingRateDao.addFromBookingStatus((BookingStatus)localObject1, localObject);
          ((BookingStatus)localObject1).setTimeField(Util.formatTimeMiles(System.currentTimeMillis()));
          ((BookingStatus)localObject1).setStatusBooking(0);
          ((BookingStatus)localObject1).setFeedback("");
          BookingTryAgain.this.bookingHistoryDao.addFromBookingStatus((BookingStatus)localObject1, localObject);
          paramAnonymousMakeBookingResponse = new Intent(BookingTryAgain.this, WaitingDriver.class);
          paramAnonymousMakeBookingResponse.putExtra("BOOKING_DATA", (Parcelable)localObject1);
          paramAnonymousMakeBookingResponse.putExtra("IS_PRE_BOOKING", bool);
          paramAnonymousMakeBookingResponse.putExtra("PREBOOK_MESSAGE", (String)localObject2);
          BookingTryAgain.this.startActivity(paramAnonymousMakeBookingResponse);
          BookingTryAgain.this.finish();
        }
      }, MakeBookingResponse.class, this.mCustomerSaved.getAccessToken());
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
  
  private void init()
  {
    this.bookingRateDao = new BookingRateDao(this);
    this.bookingHistoryDao = new BookingHistoryDao(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.mBookingData = ((BookingStatus)getIntent().getParcelableExtra("BOOKING_DATA"));
    this.mCustomerSaved = new CustomerSaved(this);
    clearNoDriverNotification();
    Log.e(TAG, "booking data " + this.mBookingData);
    updateHistoryStatus(this.bookingHistoryDao.getHistoryByOrderNo(this.mBookingData.getOrderNo()).getId(), 5);
    this.mIVTryAgain.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BookingTryAgain.this.doTryAgainBooking();
      }
    });
  }
  
  private void renderView()
  {
    setContentView(2130968639);
    this.mIVTryAgain = ((ImageView)findViewById(2131624314));
    this.mProgressBar = ((ProgressBar)findViewById(2131624286));
  }
  
  private void updateHistoryStatus(int paramInt1, int paramInt2)
  {
    this.bookingHistoryDao.updateBookingStatus(paramInt1, paramInt2);
  }
  
  public void clearNoDriverNotification()
  {
    ((NotificationManager)getSystemService("notification")).cancel(1);
  }
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent(this, Home.class);
    localIntent.putExtra("FEED_BACK", 2);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Log.e(TAG, "BookingTryAgain created");
    renderView();
    setTitle("BOOKING");
    init();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
  
  protected void onResume()
  {
    this.mCustomerSaved = new CustomerSaved(this);
    super.onResume();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/BookingTryAgain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */