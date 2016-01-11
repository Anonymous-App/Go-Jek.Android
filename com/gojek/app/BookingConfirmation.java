package com.gojek.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.flurry.android.FlurryAgent;
import com.gojek.app.gcm.GcmUtil;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CorporatePIN;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.MakeBooking;
import com.gojek.app.model.MakeBookingResponse;
import com.gojek.app.model.MakeBookingRoutes;
import com.gojek.app.model.PaymentMethod;
import com.gojek.app.parcelable.Addresses;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.parcelable.MartMerchant;
import com.gojek.app.parcelable.Merchant;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.gojek.app.persistence.dao.BookingRateDao;
import com.gojek.app.util.DelayTask;
import com.gojek.app.util.DelayTask.CallBack;
import com.gojek.app.util.TrackingNotificationUtils;
import com.gojek.app.util.Util;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BookingConfirmation
  extends GojekActivityBase
  implements View.OnClickListener, AdapterView.OnItemSelectedListener, TextWatcher
{
  public static final int ASSIGN_DRIVER_REQ = 99;
  public static final String TAG = BookingConfirmation.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private BookingRateDao bookingRateDao;
  private Long customerCredit = Long.valueOf(0L);
  private Intent intent;
  private boolean isFreeDelivery;
  private BookingStatus mBookingData;
  private CorporatePIN mCorporatePIN;
  private Customer mCustomer;
  private CustomerSaved mCustomerSaved;
  private DelayTask mDelayTask;
  private EditText mETCorporatePIN;
  private int mFlag;
  private ImageView mIVItem;
  private ImageView mIVOrder;
  private LinearLayout mLLBookingCostDetail;
  private LinearLayout mLLCorporatePIN;
  private LinearLayout mLLFoodShoppingFee;
  private LinearLayout mLLGojekCredit;
  private ProgressBar mProgress;
  private ProgressBar mProgressGojekCredit;
  private ProgressBar mProgressTotal;
  private Spinner mSPNPayWith;
  private TextView mTVContactNameFrom;
  private TextView mTVContactNameTo;
  private TextView mTVContactNumberFrom;
  private TextView mTVContactNumberTo;
  private TextView mTVEstimatedCost;
  private TextView mTVFoodShoopingFee;
  private TextView mTVFoodShoopingFeeFree;
  private TextView mTVFoodShoopingFeeLabel;
  private TextView mTVItem;
  private TextView mTVLocationDetailFrom;
  private TextView mTVLocationDetailTo;
  private TextView mTVLocationFrom;
  private TextView mTVLocationTo;
  private TextView mTVOrder;
  private TextView mTVPrice;
  private TextView mTVPriceDeliveryFree;
  private TextView mTVPriceLabel;
  private TextView mTVTime;
  private TextView mTVTotal;
  private TextView mTVTotalLabel;
  private TextView mTVVoucher;
  private TextView mTVVoucherLabel;
  private View mVFoodShoopingFeeFreeLine;
  private View mVPriceDeliveryFreeLine;
  private int selectedPaymentValue = -1;
  private VolleyClient volleyClient;
  
  private long calculateTotalPrice()
  {
    long l1 = ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice;
    long l2 = this.intent.getLongExtra("PRICE", 0L);
    long l3 = this.intent.getLongExtra("VOUCHER", 0L);
    if ((this.mBookingData.serviceType == 3) || (this.mBookingData.serviceType == 5) || (this.mBookingData.serviceType == 6)) {
      l1 = 0L;
    }
    l2 = l1 + l2 + l3;
    if (this.intent.getBooleanExtra("FREE_DELIVERY", false)) {
      l2 = l1;
    }
    Log.i(TAG, "calculatePrice " + l2);
    return l2;
  }
  
  private void doBooking()
  {
    if (this.mCustomer == null) {
      return;
    }
    this.mIVOrder.setVisibility(8);
    this.mProgress.setVisibility(0);
    for (;;)
    {
      Gson localGson;
      try
      {
        final Object localObject = getMakeBooking(this, this.mBookingData);
        localGson = new Gson();
        if (!(localGson instanceof Gson))
        {
          localObject = localGson.toJson(localObject);
          this.volleyClient.post("https://api.gojek.co.id/gojek/v2/booking/v3/makeBooking", JSONObjectInstrumentation.init((String)localObject), new JsonCallback()
          {
            public void onError(VolleyError paramAnonymousVolleyError)
            {
              Log.e(BookingConfirmation.TAG, "error makeBooking " + paramAnonymousVolleyError);
              BookingConfirmation.this.mIVOrder.setVisibility(0);
              BookingConfirmation.this.mProgress.setVisibility(8);
              if (!(paramAnonymousVolleyError instanceof AuthFailureError))
              {
                paramAnonymousVolleyError = BookingConfirmation.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
                paramAnonymousVolleyError = "Failed to make your booking\n" + paramAnonymousVolleyError;
                BookingConfirmation.this.saveLocalHistory(BookingConfirmation.this.mBookingData, null);
                BookingConfirmation.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
                return;
              }
              BookingConfirmation.this.isUnauthorizedApiAccess();
            }
            
            public void onResponse(MakeBookingResponse paramAnonymousMakeBookingResponse)
            {
              Object localObject1;
              if (paramAnonymousMakeBookingResponse != null)
              {
                localObject1 = null;
                try
                {
                  localObject2 = (BookingStatus)BookingConfirmation.this.mBookingData.clone();
                  localObject1 = localObject2;
                }
                catch (Exception localException)
                {
                  boolean bool;
                  ArrayList localArrayList;
                  for (;;)
                  {
                    Object localObject2;
                    int i;
                    localException.printStackTrace();
                  }
                  new ArrayList();
                  Object localObject3 = new Date();
                  BookingConfirmation.this.mBookingData.timeField = Util.formatTimeMiles(((Date)localObject3).getTime());
                  localArrayList.add(BookingConfirmation.this.mBookingData);
                  BookingConfirmation.this.mCustomerSaved.saveHistoryBooking(localArrayList);
                  ((BookingStatus)localObject1).setCustomer(paramAnonymousMakeBookingResponse);
                  BookingConfirmation.this.bookingRateDao.addFromBookingStatus((BookingStatus)localObject1, localObject);
                  BookingConfirmation.this.saveLocalHistory((BookingStatus)localObject1, localObject);
                  paramAnonymousMakeBookingResponse = new Intent(BookingConfirmation.this, WaitingDriver.class);
                  paramAnonymousMakeBookingResponse.putExtra("BOOKING_DATA", (Parcelable)localObject1);
                  paramAnonymousMakeBookingResponse.putExtra("IS_PRE_BOOKING", bool);
                  paramAnonymousMakeBookingResponse.putExtra("PREBOOK_MESSAGE", localException);
                  BookingConfirmation.this.startActivity(paramAnonymousMakeBookingResponse);
                  BookingConfirmation.this.finish();
                }
                bool = paramAnonymousMakeBookingResponse.isPrebook();
                localObject2 = paramAnonymousMakeBookingResponse.getPrebookMessage();
                ((BookingStatus)localObject1).id = paramAnonymousMakeBookingResponse.getId();
                ((BookingStatus)localObject1).orderNo = paramAnonymousMakeBookingResponse.getOrderNo();
                paramAnonymousMakeBookingResponse = new Customer();
                paramAnonymousMakeBookingResponse.customerId = BookingConfirmation.this.mCustomerSaved.customerId;
                if (BookingConfirmation.this.mBookingData.serviceType == 6)
                {
                  localObject3 = BookingConfirmation.this.mCustomerSaved.getHistoryBooking();
                  localArrayList = new ArrayList();
                  if ((localObject3 != null) && (!((List)localObject3).isEmpty()))
                  {
                    i = 0;
                    while (i < ((List)localObject3).size())
                    {
                      localArrayList.add(((List)localObject3).get(i));
                      i += 1;
                    }
                  }
                }
              }
            }
          }, MakeBookingResponse.class, this.mCustomerSaved.getAccessToken());
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      String str = GsonInstrumentation.toJson((Gson)localGson, localException);
    }
  }
  
  private void doLoadProfile()
  {
    String str = String.format("https://api.gojek.co.id/gojek/v2/customer/%s", new Object[] { this.mCustomerSaved.customerId });
    if (!Util.isTextNotNullEmpty(this.mCustomerSaved.customerId))
    {
      Log.i(TAG, "can't doLoadProfile() > no customerId");
      return;
    }
    this.mProgressTotal.setVisibility(0);
    this.mLLGojekCredit.setVisibility(8);
    this.mProgressGojekCredit.setVisibility(0);
    findViewById(2131624239).setVisibility(8);
    this.volleyClient.get(str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(BookingConfirmation.TAG, "error doLoadProfile " + paramAnonymousVolleyError);
        BookingConfirmation.access$1702(BookingConfirmation.this, 0);
        BookingConfirmation.this.mSPNPayWith.setSelection(0);
        BookingConfirmation.this.mProgressGojekCredit.setVisibility(8);
        BookingConfirmation.this.mProgressTotal.setVisibility(8);
        long l = BookingConfirmation.this.calculateTotalPrice();
        BookingConfirmation.this.findViewById(2131624239).setVisibility(0);
        BookingConfirmation.this.mTVTotal.setText(Util.getRupiahFormat(String.valueOf(l)));
        if ((paramAnonymousVolleyError instanceof AuthFailureError)) {
          BookingConfirmation.this.isUnauthorizedApiAccess();
        }
      }
      
      public void onResponse(Customer paramAnonymousCustomer)
      {
        if (paramAnonymousCustomer != null)
        {
          BookingConfirmation.access$1302(BookingConfirmation.this, paramAnonymousCustomer);
          BookingConfirmation.access$1402(BookingConfirmation.this, Long.valueOf(BookingConfirmation.this.mCustomer.creditBalance));
          long l2 = BookingConfirmation.this.intent.getLongExtra("PRICE", 0L);
          long l1 = l2;
          if (l2 > BookingConfirmation.this.mCustomer.creditBalance) {
            l1 = BookingConfirmation.this.mCustomer.creditBalance;
          }
          long l3 = BookingConfirmation.this.calculateTotalPrice();
          Log.i(BookingConfirmation.TAG, "doLoadProfile() -> price " + l2);
          Log.i(BookingConfirmation.TAG, "doLoadProfile -> customerCredit " + BookingConfirmation.this.customerCredit);
          Log.i(BookingConfirmation.TAG, "doLoadProfile -> total discount " + l1);
          Log.i(BookingConfirmation.TAG, "doLoadProfile -> total price " + l3);
          Log.i(BookingConfirmation.TAG, "initial selected payment value " + BookingConfirmation.this.selectedPaymentValue);
          if ((BookingConfirmation.this.selectedPaymentValue != -1) && (BookingConfirmation.this.selectedPaymentValue != 1)) {
            break label448;
          }
          BookingConfirmation.this.mTVTotal.setText(Util.getRupiahFormat(String.valueOf(l3)));
          if ((BookingConfirmation.this.customerCredit.longValue() < l3) || (l3 <= 0L)) {
            break label425;
          }
          BookingConfirmation.this.mLLGojekCredit.setVisibility(0);
          BookingConfirmation.this.mProgressGojekCredit.setVisibility(0);
          BookingConfirmation.access$1702(BookingConfirmation.this, 1);
          BookingConfirmation.this.mSPNPayWith.setSelection(1);
          BookingConfirmation.this.mTVTotal.setText(Util.getRupiahFormat(String.valueOf(l3 - l1)));
        }
        for (;;)
        {
          BookingConfirmation.this.mProgressGojekCredit.setVisibility(8);
          BookingConfirmation.this.mProgressTotal.setVisibility(8);
          BookingConfirmation.this.findViewById(2131624239).setVisibility(0);
          return;
          BookingConfirmation.access$1302(BookingConfirmation.this, new Customer());
          BookingConfirmation.access$1402(BookingConfirmation.this, Long.valueOf(0L));
          break;
          label425:
          BookingConfirmation.access$1702(BookingConfirmation.this, 0);
          BookingConfirmation.this.mSPNPayWith.setSelection(0);
          continue;
          label448:
          BookingConfirmation.access$1702(BookingConfirmation.this, 0);
          BookingConfirmation.this.mSPNPayWith.setSelection(0);
        }
      }
    }, Customer.class, this.mCustomerSaved.getAccessToken());
  }
  
  private void doValidatePIN(String paramString)
  {
    findViewById(2131624285).setVisibility(0);
    findViewById(2131624284).setVisibility(8);
    paramString = String.format("https://api.gojek.co.id/gojek/v2/corporate/findByPin?pin=%s", new Object[] { paramString });
    this.volleyClient.get(paramString, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        BookingConfirmation.this.findViewById(2131624285).setVisibility(8);
        BookingConfirmation.this.findViewById(2131624284).setVisibility(0);
        if (!(paramAnonymousVolleyError instanceof AuthFailureError))
        {
          ImageView localImageView = (ImageView)BookingConfirmation.this.findViewById(2131624284);
          Log.e(BookingConfirmation.TAG, "error get corporate pin " + paramAnonymousVolleyError);
          localImageView.setImageResource(2130837856);
          BookingConfirmation.access$2402(BookingConfirmation.this, null);
          return;
        }
        BookingConfirmation.this.isUnauthorizedApiAccess();
      }
      
      public void onResponse(CorporatePIN paramAnonymousCorporatePIN)
      {
        BookingConfirmation.this.findViewById(2131624285).setVisibility(8);
        BookingConfirmation.this.findViewById(2131624284).setVisibility(0);
        ImageView localImageView = (ImageView)BookingConfirmation.this.findViewById(2131624284);
        if (paramAnonymousCorporatePIN != null)
        {
          if (paramAnonymousCorporatePIN.status.equals("OK"))
          {
            BookingConfirmation.access$2402(BookingConfirmation.this, paramAnonymousCorporatePIN);
            localImageView.setImageResource(2130837857);
            return;
          }
          localImageView.setImageResource(2130837856);
          BookingConfirmation.access$2402(BookingConfirmation.this, null);
          return;
        }
        BookingConfirmation.access$2402(BookingConfirmation.this, null);
        localImageView.setImageResource(2130837856);
      }
    }, CorporatePIN.class, this.mCustomerSaved.getAccessToken());
  }
  
  public static MakeBooking getMakeBooking(Context paramContext, BookingStatus paramBookingStatus)
  {
    CustomerSaved localCustomerSaved = new CustomerSaved(paramContext);
    MakeBooking localMakeBooking = new MakeBooking();
    localMakeBooking.timeField = null;
    localMakeBooking.customerId = Integer.parseInt(localCustomerSaved.customerId);
    localMakeBooking.gcmKey = GcmUtil.getRegistrationId(paramContext);
    paramContext = new MakeBookingRoutes();
    paramContext.serviceType = paramBookingStatus.serviceType;
    paramContext.originNote = ((Addresses)paramBookingStatus.addresses.get(0)).originNote;
    paramContext.destinationNote = ((Addresses)paramBookingStatus.addresses.get(0)).destinationNote;
    paramContext.originName = ((Addresses)paramBookingStatus.addresses.get(0)).originName;
    paramContext.destinationName = ((Addresses)paramBookingStatus.addresses.get(0)).destinationName;
    paramContext.originAddress = ((Addresses)paramBookingStatus.addresses.get(0)).originAddress;
    paramContext.destinationAddress = ((Addresses)paramBookingStatus.addresses.get(0)).destinationAddress;
    paramContext.originContactName = ((Addresses)paramBookingStatus.addresses.get(0)).originContactName;
    paramContext.originContactPhone = ((Addresses)paramBookingStatus.addresses.get(0)).originContactPhone;
    paramContext.destinationContactName = ((Addresses)paramBookingStatus.addresses.get(0)).destinationContactName;
    paramContext.destinationContactPhone = ((Addresses)paramBookingStatus.addresses.get(0)).destinationContactPhone;
    paramContext.originLatLong = ((Addresses)paramBookingStatus.addresses.get(0)).latLongOrigin;
    paramContext.detailsAddress = ((Addresses)paramBookingStatus.addresses.get(0)).detailAddress;
    paramContext.destinationLatLong = ((Addresses)paramBookingStatus.addresses.get(0)).latLongDestination;
    paramContext.item = ((Addresses)paramBookingStatus.addresses.get(0)).item;
    paramContext.routeItems = ((Addresses)paramBookingStatus.addresses.get(0)).routeItems;
    paramContext.estimatedPrice = String.valueOf(((Addresses)paramBookingStatus.addresses.get(0)).estimatedPrice);
    if (paramBookingStatus.serviceType == 6) {}
    for (int i = ((Addresses)paramBookingStatus.addresses.get(0)).martMerchant.martId;; i = ((Addresses)paramBookingStatus.addresses.get(0)).merchant.id)
    {
      paramContext.merchantId = i;
      localMakeBooking.routes.add(paramContext);
      localMakeBooking.corporatePin = paramBookingStatus.corporatePin;
      localMakeBooking.paymentType = paramBookingStatus.paymentType;
      return localMakeBooking;
    }
  }
  
  @Deprecated
  private BookingStatus getUpdateBooking()
  {
    return this.mBookingData;
  }
  
  private void init()
  {
    this.intent = getIntent();
    this.mCustomerSaved = new CustomerSaved(this);
    this.bookingRateDao = new BookingRateDao(this);
    this.bookingHistoryDao = new BookingHistoryDao(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.mBookingData = ((BookingStatus)this.intent.getParcelableExtra("BOOKING_DATA"));
    this.mFlag = this.intent.getIntExtra("FLAG", 25);
    this.isFreeDelivery = this.intent.getBooleanExtra("FREE_DELIVERY", false);
    setTextView(((Addresses)this.mBookingData.addresses.get(0)).originName, this.mTVLocationFrom, new View[0]);
    if ((this.mBookingData.serviceType == 5) || (this.mBookingData.serviceType == 6))
    {
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).originAddress, this.mTVLocationDetailFrom, new View[0]);
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).originContactName, this.mTVContactNameFrom, new View[] { findViewById(2131624258) });
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).originContactPhone, this.mTVContactNumberFrom, new View[] { findViewById(2131624258) });
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).destinationName, this.mTVLocationTo, new View[0]);
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).destinationNote, this.mTVLocationDetailTo, new View[0]);
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).destinationContactName, this.mTVContactNameTo, new View[] { findViewById(2131624259) });
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).destinationContactPhone, this.mTVContactNumberTo, new View[] { findViewById(2131624259) });
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).item, this.mTVItem, new View[] { this.mTVItem, findViewById(2131624227) });
      setTextView(Util.getRupiahFormat(String.valueOf(((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice)), this.mTVEstimatedCost, new View[0]);
      setTextView(Util.getRupiahFormat(String.valueOf(this.intent.getLongExtra("PRICE", 0L))), this.mTVPrice, new View[0]);
      setTextView(Util.getRupiahFormat(String.valueOf(this.intent.getLongExtra("VOUCHER", 0L))), this.mTVVoucher, new View[0]);
      setTextView(Util.formatDateFromAPI(this.mBookingData.timeField, null), this.mTVTime, new View[] { this.mTVTime, findViewById(2131624225) });
      if (this.mFlag == 24) {
        this.mTVOrder.setText("EDIT BOOKING");
      }
      if ((this.mTVTime.getVisibility() != 0) && (this.mTVItem.getVisibility() != 0))
      {
        findViewById(2131624256).setVisibility(8);
        findViewById(2131624257).setVisibility(8);
      }
      this.mSPNPayWith.setOnItemSelectedListener(this);
      this.mIVOrder.setOnClickListener(this);
      this.mETCorporatePIN.addTextChangedListener(this);
      switch (this.mBookingData.serviceType)
      {
      }
    }
    for (;;)
    {
      if (this.isFreeDelivery) {
        this.mLLBookingCostDetail.post(new Runnable()
        {
          public void run()
          {
            BookingConfirmation.this.mVPriceDeliveryFreeLine.setVisibility(0);
            int i = BookingConfirmation.this.mTVPrice.getMeasuredWidth();
            ViewGroup.LayoutParams localLayoutParams = BookingConfirmation.this.mVPriceDeliveryFreeLine.getLayoutParams();
            localLayoutParams.width = i;
            BookingConfirmation.this.mVPriceDeliveryFreeLine.setLayoutParams(localLayoutParams);
            BookingConfirmation.this.mTVPriceDeliveryFree.setVisibility(0);
            if ((BookingConfirmation.this.mBookingData.serviceType == 5) || (BookingConfirmation.this.mBookingData.serviceType == 3) || (BookingConfirmation.this.mBookingData.serviceType == 6))
            {
              BookingConfirmation.this.mVFoodShoopingFeeFreeLine.setVisibility(0);
              i = BookingConfirmation.this.mTVFoodShoopingFee.getMeasuredWidth();
              localLayoutParams = BookingConfirmation.this.mVFoodShoopingFeeFreeLine.getLayoutParams();
              localLayoutParams.width = i;
              BookingConfirmation.this.mVFoodShoopingFeeFreeLine.setLayoutParams(localLayoutParams);
              BookingConfirmation.this.mTVFoodShoopingFeeFree.setVisibility(0);
            }
          }
        });
      }
      return;
      setTextView(((Addresses)this.mBookingData.addresses.get(0)).originNote, this.mTVLocationDetailFrom, new View[0]);
      break;
      setTitle(getString(2131165881));
      findViewById(2131624261).setVisibility(8);
      findViewById(2131624235).setVisibility(8);
      continue;
      setTitle(getString(2131165431));
      findViewById(2131624261).setVisibility(8);
      this.mIVItem.setImageResource(2130837891);
      continue;
      setTitle(getString(2131165798));
      findViewById(2131624261).setVisibility(0);
      this.mIVItem.setImageResource(2130837933);
      this.mTVPriceLabel.setText(getString(2131165451));
      this.mTVVoucherLabel.setText(getString(2131165797));
      this.mTVTotalLabel.setText(getString(2131165520));
      long l = ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice;
      this.intent.getLongExtra("PRICE", 0L);
      l = this.intent.getLongExtra("VOUCHER", 0L);
      this.mLLFoodShoppingFee.setVisibility(8);
      this.mTVFoodShoopingFee.setText(Util.getRupiahFormat(String.valueOf(l)));
      continue;
      setTitle(getString(2131165585));
      findViewById(2131624261).setVisibility(0);
      this.mTVPriceLabel.setText(getString(2131165451));
      this.mTVVoucherLabel.setText(getString(2131165797));
      this.mTVTotalLabel.setText(getString(2131165877));
      this.mIVItem.setImageResource(2130837933);
      ((TextView)findViewById(2131624262)).setText(2131165516);
      Object localObject1;
      Object localObject2;
      Object localObject3;
      if (((Addresses)this.mBookingData.addresses.get(0)).routeItems != null)
      {
        findViewById(2131624256).setVisibility(0);
        localObject1 = new StringBuffer();
        ((StringBuffer)localObject1).append(getString(2131165576));
        localObject2 = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
        if (((List)localObject2).size() > 0) {
          ((StringBuffer)localObject1).append("<br />");
        }
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (RouteItem)((Iterator)localObject2).next();
          ((StringBuffer)localObject1).append("- ");
          if (((RouteItem)localObject3).quantity > 0) {
            ((StringBuffer)localObject1).append("[" + ((RouteItem)localObject3).quantity + "] ");
          }
          ((StringBuffer)localObject1).append(((RouteItem)localObject3).itemName + "<br />");
          if ((((RouteItem)localObject3).notes != null) && (((RouteItem)localObject3).notes.trim().length() > 0))
          {
            localObject3 = "<i>" + ((RouteItem)localObject3).notes + "</i>";
            ((StringBuffer)localObject1).append("&nbsp;&nbsp;" + (String)localObject3);
          }
          if (((Iterator)localObject2).hasNext()) {
            ((StringBuffer)localObject1).append("<br />");
          }
        }
        this.mIVItem.setVisibility(0);
        this.mTVItem.setVisibility(0);
        this.mTVItem.setText(Html.fromHtml(((StringBuffer)localObject1).toString()));
      }
      l = ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice;
      this.intent.getLongExtra("PRICE", 0L);
      l = this.intent.getLongExtra("VOUCHER", 0L);
      this.mLLFoodShoppingFee.setVisibility(8);
      this.mTVFoodShoopingFee.setText(Util.getRupiahFormat(String.valueOf(l)));
      continue;
      setTitle(getString(2131165648));
      findViewById(2131624261).setVisibility(0);
      this.mTVPriceLabel.setText(getString(2131165451));
      this.mTVVoucherLabel.setText(getString(2131165797));
      this.mTVTotalLabel.setText(getString(2131165877));
      this.mIVItem.setImageResource(2130837933);
      ((TextView)findViewById(2131624262)).setText(2131165519);
      if (((Addresses)this.mBookingData.addresses.get(0)).routeItems != null)
      {
        findViewById(2131624256).setVisibility(0);
        localObject2 = getString(2131165576);
        localObject3 = ((Addresses)this.mBookingData.addresses.get(0)).routeItems;
        localObject1 = localObject2;
        if (((List)localObject3).size() > 0) {
          localObject1 = (String)localObject2 + "\n";
        }
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject2 = (String)localObject1 + "- " + ((RouteItem)((Iterator)localObject3).next()).itemName;
          localObject1 = localObject2;
          if (((Iterator)localObject3).hasNext()) {
            localObject1 = (String)localObject2 + "\n";
          }
        }
        this.mIVItem.setVisibility(0);
        this.mTVItem.setVisibility(0);
        setTextView((String)localObject1, this.mTVItem, new View[0]);
      }
      l = ((Addresses)this.mBookingData.addresses.get(0)).estimatedPrice;
      this.intent.getLongExtra("PRICE", 0L);
      l = this.intent.getLongExtra("VOUCHER", 0L);
      this.mLLFoodShoppingFee.setVisibility(8);
      this.mTVFoodShoopingFee.setText(Util.getRupiahFormat(String.valueOf(l)));
    }
  }
  
  private void renderView()
  {
    setContentView(2130968633);
    this.mTVLocationFrom = ((TextView)findViewById(2131624229));
    this.mTVLocationDetailFrom = ((TextView)findViewById(2131624230));
    this.mTVContactNameFrom = ((TextView)findViewById(2131624232));
    this.mTVContactNumberFrom = ((TextView)findViewById(2131624233));
    this.mTVLocationTo = ((TextView)findViewById(2131624234));
    this.mTVLocationDetailTo = ((TextView)findViewById(2131624235));
    this.mTVContactNameTo = ((TextView)findViewById(2131624237));
    this.mTVContactNumberTo = ((TextView)findViewById(2131624238));
    this.mTVItem = ((TextView)findViewById(2131624228));
    this.mTVTime = ((TextView)findViewById(2131624226));
    this.mTVPrice = ((TextView)findViewById(2131624267));
    this.mTVPriceLabel = ((TextView)findViewById(2131624265));
    this.mTVVoucher = ((TextView)findViewById(2131624276));
    this.mTVVoucherLabel = ((TextView)findViewById(2131624275));
    this.mIVOrder = ((ImageView)findViewById(2131624287));
    this.mProgress = ((ProgressBar)findViewById(2131624286));
    this.mTVTotal = ((TextView)findViewById(2131624239));
    this.mTVTotalLabel = ((TextView)findViewById(2131624279));
    this.mTVEstimatedCost = ((TextView)findViewById(2131624263));
    this.mETCorporatePIN = ((EditText)findViewById(2131624283));
    this.mSPNPayWith = ((Spinner)findViewById(2131624281));
    this.mIVItem = ((ImageView)findViewById(2131624227));
    this.mTVOrder = ((TextView)findViewById(2131624288));
    this.mLLCorporatePIN = ((LinearLayout)findViewById(2131624282));
    this.mProgressGojekCredit = ((ProgressBar)findViewById(2131624277));
    this.mLLBookingCostDetail = ((LinearLayout)findViewById(2131624260));
    this.mTVPriceDeliveryFree = ((TextView)findViewById(2131624266));
    this.mVPriceDeliveryFreeLine = findViewById(2131624268);
    this.mLLGojekCredit = ((LinearLayout)findViewById(2131624274));
    this.mLLFoodShoppingFee = ((LinearLayout)findViewById(2131624269));
    this.mTVFoodShoopingFeeLabel = ((TextView)findViewById(2131624270));
    this.mTVFoodShoopingFeeFree = ((TextView)findViewById(2131624271));
    this.mTVFoodShoopingFee = ((TextView)findViewById(2131624272));
    this.mVFoodShoopingFeeFreeLine = findViewById(2131624273);
    this.mProgressTotal = ((ProgressBar)findViewById(2131624280));
  }
  
  private void saveLocalHistory(BookingStatus paramBookingStatus, String paramString)
  {
    paramBookingStatus.setTimeField(Util.formatTimeMiles(System.currentTimeMillis()));
    paramBookingStatus.setStatusBooking(6);
    Log.e(TAG, "got booking status= " + paramBookingStatus);
    this.bookingHistoryDao.addFromBookingStatus(paramBookingStatus, paramString);
  }
  
  private void setTextView(String paramString, TextView paramTextView, View... paramVarArgs)
  {
    if (TextUtils.isEmpty(paramString))
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        paramVarArgs[i].setVisibility(8);
        i += 1;
      }
    }
    paramTextView.setText(paramString);
  }
  
  private void validateGojekCreditOption()
  {
    long l = calculateTotalPrice();
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.customerCredit.longValue() < l) {
      localStringBuilder.append(String.format(getString(2131165600), new Object[] { Util.getRupiahFormat(String.valueOf(this.customerCredit)) }));
    }
    if (!localStringBuilder.toString().equals("")) {
      showSimpleDialog(getString(2131165719), localStringBuilder.toString(), null, false, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (BookingConfirmation.this.selectedPaymentValue != -1)
          {
            BookingConfirmation.this.mSPNPayWith.setSelection(BookingConfirmation.this.selectedPaymentValue);
            return;
          }
          BookingConfirmation.this.mSPNPayWith.setSelection(0);
        }
      });
    }
  }
  
  public void afterTextChanged(final Editable paramEditable)
  {
    if (this.mDelayTask != null)
    {
      this.mDelayTask.cancel(true);
      this.mDelayTask = null;
    }
    this.mDelayTask = new DelayTask(600, new DelayTask.CallBack()
    {
      public void onFinish()
      {
        if (paramEditable.toString().length() > 0) {
          BookingConfirmation.this.doValidatePIN(paramEditable.toString());
        }
      }
    });
    paramEditable = this.mDelayTask;
    Void[] arrayOfVoid = new Void[0];
    if (!(paramEditable instanceof AsyncTask))
    {
      paramEditable.execute(arrayOfVoid);
      return;
    }
    AsyncTaskInstrumentation.execute((AsyncTask)paramEditable, arrayOfVoid);
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 == -1) && (paramInt1 == 99)) {
      finish();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    if (this.mBookingData != null) {
      switch (this.mBookingData.serviceType)
      {
      }
    }
    for (;;)
    {
      super.onBackPressed();
      return;
      Services.locDetailsFromConfirmation = this.mTVLocationDetailFrom.getText().toString();
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131624287: 
      if (this.mCustomerSaved.customerId == null)
      {
        if (this.mCustomerSaved.email == null) {}
        for (paramView = new Intent(this, SignUp.class);; paramView = new Intent(this, SmsVerification.class))
        {
          startActivity(paramView);
          return;
        }
      }
      if (this.mFlag == 24) {}
      for (;;)
      {
        TrackingNotificationUtils.getInstance().setStartDate(new Date());
        doBooking();
        return;
        FlurryAgent.logEvent("Order_Clicked", true);
      }
    }
    this.mSPNPayWith.performClick();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    renderView();
    init();
  }
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    findViewById(2131624284).setVisibility(8);
    findViewById(2131624282).setVisibility(8);
    if (this.selectedPaymentValue == -1) {
      return;
    }
    long l = calculateTotalPrice();
    paramLong = 0L;
    if (paramInt == 0)
    {
      this.selectedPaymentValue = 0;
      findViewById(2131624282).setVisibility(8);
      this.mETCorporatePIN.setText("");
      hideKeyboard();
      this.mLLGojekCredit.setVisibility(8);
      this.mBookingData.paymentType = PaymentMethod.CASH.ordinal();
    }
    for (;;)
    {
      Log.i(TAG, "onItemSelected() get total " + l);
      this.mTVTotal.setText(Util.getRupiahFormat(String.valueOf(l - paramLong)));
      this.mBookingData.setTotalPrice(l);
      this.mBookingData.setTotalCustomerPrice(l - paramLong);
      return;
      findViewById(2131624282).setVisibility(8);
      this.mETCorporatePIN.setText("");
      hideKeyboard();
      paramLong = l;
      if (paramLong <= this.customerCredit.longValue())
      {
        this.selectedPaymentValue = 1;
        this.mTVVoucher.setText("-" + Util.getRupiahFormat(String.valueOf(paramLong)));
        this.mLLGojekCredit.setVisibility(0);
        this.mTVVoucherLabel.setText(2131165598);
        this.mBookingData.paymentType = PaymentMethod.CUSTOMER_CREDIT.ordinal();
      }
      else
      {
        validateGojekCreditOption();
      }
    }
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
  
  protected void onResume()
  {
    super.onResume();
    this.mCustomerSaved = new CustomerSaved(this);
    if (this.mCustomerSaved.customerId != null) {
      doLoadProfile();
    }
    while ((this.mCustomerSaved.customerId != null) || (this.mBookingData.serviceType == 5)) {
      return;
    }
    this.mTVVoucher.setText("0");
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/BookingConfirmation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */