package com.gojek.gobox.orderForm.view;

import android.animation.LayoutTransition;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import com.gojek.gobox.ConfirmPage.view.ConfirmActivity;
import com.gojek.gobox.R.color;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.cargoType.view.CargoTypeActivity;
import com.gojek.gobox.contactPersonDetailForm.view.ContactPicker;
import com.gojek.gobox.model.BookingItem;
import com.gojek.gobox.model.BookingRequestBody;
import com.gojek.gobox.model.CargoType;
import com.gojek.gobox.model.CargoTypeResponse;
import com.gojek.gobox.model.EstimatedPrice;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.Order;
import com.gojek.gobox.model.VehicleType;
import com.gojek.gobox.model.VehicleTypeResponse;
import com.gojek.gobox.networking.ConnectionManager;
import com.gojek.gobox.orderForm.presenter.OrderFormPresenter;
import com.gojek.gobox.util.AlertDialogFragment.AlertDialogListener;
import com.gojek.gobox.util.CustomMapFragment;
import com.gojek.gobox.util.GoBoxPreferences;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class OrderFormActivity
  extends BaseActivity
  implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, OrderFormView, VehicleAdapter.OnSelectedVehicleChangedListener, AlertDialogFragment.AlertDialogListener, GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnCameraChangeListener
{
  private static final String TAG = OrderFormActivity.class.getName();
  public static CargoType mSelectedCargoType;
  private ArrayList<CargoType> cargoTypes;
  private StringBuilder formValidationError;
  private int intCargoMaxWeight;
  private boolean isContactDestinationVisible = false;
  private boolean isContactOriginDestinationVisible = false;
  private boolean isContactOriginVisible = false;
  private boolean isItemToSend = false;
  private Button mAddShipperButton;
  private int mAdditionalShipperCounter = 1;
  private ImageView mButtonAddNextDestination;
  private RelativeLayout mButtonNewAddFromContactOrigin;
  private boolean mCalculatingEstimation = false;
  private TextView mCargoMaxWeight;
  private long mDateSelected;
  private int mDestinationOrderTarget;
  private LinkedHashMap<ImageView, View> mDestinationViewMap;
  private TextView mEditedContactPerson;
  private TextView mEditedContactPersonName;
  private LinearLayout mEstimationContainer;
  private TextView mEstimationPrice;
  private ProgressBar mEstimationProgressBar;
  private CheckBox mFragileCheckBox;
  private View mFragileOptionContainer;
  private GoBoxPreferences mGoBoxPreferences;
  private GoogleMap mGoogleMap;
  private ImageView mImageTermAndCondition;
  private EditText mItemToDeliver;
  private ImageView mItemToSendTerms;
  private android.location.Location mLastLocation;
  private float mLastZoomLevel = -1.0F;
  private Button mLessShipperButton;
  private LinearLayout mLinearAddNextDestination;
  private LinearLayout mLinearContactDestination;
  private LinearLayout mLinearContactHolderDestination;
  private LinearLayout mLinearContactHolderOrigin;
  private LinearLayout mLinearContactOrigin;
  private LocationClient mLocationClient;
  private EditText mLocationDetailsTargettextView;
  private LocationRequest mLocationRequest;
  private TextView mLocationTargetTextView;
  private TextView mMaxCargoDimension;
  private OrderFormPresenter mOrderFormPresenter;
  private ImageView mOrderNextBUtton;
  private EditText mOriginLocationDetail;
  private EditText mOriginSpecialInstruction;
  private TextView mOriginStreetLocation;
  private LinearLayout mPickTime;
  private TextView mPickTimeText;
  private long mPickUpTime = Calendar.getInstance().getTime().getTime();
  private RadioGroup mPickup;
  private RadioButton mPickupLater;
  private RadioButton mPickupNow;
  private ProgressBar mProgressBar;
  private CheckBox mShipperCheckBox;
  private TextView mShipperCounter;
  private TextView mShipperPrice;
  private CheckBox mTermAndConditionCheck;
  private EditText mTextNameOrigin;
  private EditText mTextPhoneNumberOrigin;
  private GridView mVehicleGridView;
  private VehicleRequestTimer mVehicleRequestTime;
  private WebView mWebViewTermAndCondition;
  private int maxAdditionalShipper;
  private int maxDestinationSize;
  
  private BookingItem createBookingItem()
  {
    BookingItem localBookingItem = new BookingItem();
    localBookingItem.setDescription(this.mItemToDeliver.getText().toString());
    localBookingItem.setAirConditionerSupport(this.mFragileCheckBox.isChecked());
    localBookingItem.setFragileSupport(this.mFragileCheckBox.isChecked());
    return localBookingItem;
  }
  
  private BookingRequestBody createBookingRequestBody(com.gojek.gobox.model.Location paramLocation, ArrayList<com.gojek.gobox.model.Location> paramArrayList, EstimationResponse paramEstimationResponse)
  {
    BookingRequestBody localBookingRequestBody = new BookingRequestBody();
    paramLocation.setInfo(this.mOriginLocationDetail.getText().toString());
    paramLocation.setInstruction(this.mOriginSpecialInstruction.getText().toString());
    paramLocation.setContactPerson(this.mTextPhoneNumberOrigin.getText().toString());
    paramLocation.setContactName(this.mTextNameOrigin.getText().toString());
    localBookingRequestBody.setOrigin(paramLocation);
    fillDestinationOrderDetails(paramArrayList);
    localBookingRequestBody.setDestinations(paramArrayList);
    localBookingRequestBody.setItem(createBookingItem());
    localBookingRequestBody.setOrder(createOrder(paramEstimationResponse.getEstimatedPrice().getTotal()));
    localBookingRequestBody.setConsumerName(this.mGoBoxPreferences.getUserName());
    localBookingRequestBody.setConsumerPhone(this.mGoBoxPreferences.getUserPhone());
    return localBookingRequestBody;
  }
  
  private void createLocationRequest()
  {
    this.mLocationRequest = LocationRequest.create();
    this.mLocationRequest.setInterval(5000L);
    this.mLocationRequest.setPriority(104);
    this.mLocationRequest.setFastestInterval(1000L);
    this.mLocationClient.requestLocationUpdates(this.mLocationRequest, this);
  }
  
  private Order createOrder(double paramDouble)
  {
    Order localOrder = new Order();
    localOrder.setPickUpLater(this.mPickupLater.isChecked());
    localOrder.setPickUpNow(this.mPickupNow.isChecked());
    if (this.mShipperCheckBox.isChecked()) {
      localOrder.setNumberOfShipper(this.mAdditionalShipperCounter);
    }
    for (;;)
    {
      localOrder.setTotalPrice(paramDouble);
      localOrder.setTime(this.mPickUpTime);
      return localOrder;
      localOrder.setNumberOfShipper(0);
    }
  }
  
  private void fillDestinationOrderDetails(ArrayList<com.gojek.gobox.model.Location> paramArrayList)
  {
    int k = this.mLinearAddNextDestination.getChildCount();
    int j = 0;
    int i = 0;
    if (j < k - 1)
    {
      Object localObject2 = this.mLinearAddNextDestination.getChildAt(j);
      Object localObject1 = (TextView)((View)localObject2).findViewById(R.id.location_target_textview);
      EditText localEditText1 = (EditText)((View)localObject2).findViewById(R.id.destination_location_details);
      EditText localEditText2 = (EditText)((View)localObject2).findViewById(R.id.destination_instruction);
      EditText localEditText3 = (EditText)((View)localObject2).findViewById(R.id.text_phone_number);
      localObject2 = (EditText)((View)localObject2).findViewById(R.id.text_name);
      if (TextUtils.isEmpty(((TextView)localObject1).getText())) {
        break label181;
      }
      localObject1 = (com.gojek.gobox.model.Location)paramArrayList.get(i);
      ((com.gojek.gobox.model.Location)localObject1).setInfo(localEditText1.getText().toString());
      ((com.gojek.gobox.model.Location)localObject1).setInstruction(localEditText2.getText().toString());
      ((com.gojek.gobox.model.Location)localObject1).setContactPerson(localEditText3.getText().toString());
      ((com.gojek.gobox.model.Location)localObject1).setContactName(((EditText)localObject2).getText().toString());
      i += 1;
    }
    label181:
    for (;;)
    {
      j += 1;
      break;
      return;
    }
  }
  
  private CargoType getSelectedVehicleFromExtras()
  {
    Object localObject = getIntent().getExtras();
    if (localObject != null)
    {
      long l = ((Bundle)localObject).getLong("cargo_type_object");
      localObject = this.cargoTypes.iterator();
      while (((Iterator)localObject).hasNext())
      {
        CargoType localCargoType = (CargoType)((Iterator)localObject).next();
        if (localCargoType.getId() == l)
        {
          this.intCargoMaxWeight = localCargoType.getMaxWeight();
          return localCargoType;
        }
      }
    }
    return (CargoType)this.cargoTypes.get(0);
  }
  
  private Calendar getTodayDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.clear(10);
    localCalendar.clear(12);
    localCalendar.clear(13);
    return localCalendar;
  }
  
  private Calendar getTomorrowDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(6, 1);
    return localCalendar;
  }
  
  private void initAdditionalShipperView()
  {
    this.mAddShipperButton.setEnabled(false);
    this.mLessShipperButton.setEnabled(false);
    this.mShipperCounter.setText(String.format(getString(R.string.additional_shipper_counter_label), new Object[] { Integer.valueOf(this.mAdditionalShipperCounter) }));
  }
  
  private void initBookingTimeForm()
  {
    this.mPickup = ((RadioGroup)findViewById(R.id.pick_up_radio_group));
    this.mPickupNow = ((RadioButton)findViewById(R.id.pick_up_now_radio_button));
    this.mPickupLater = ((RadioButton)findViewById(R.id.pick_up_later_radio_button));
    this.mPickTime = ((LinearLayout)findViewById(R.id.pick_up_text_container));
    this.mPickTimeText = ((TextView)findViewById(R.id.pick_up_text));
    this.mTermAndConditionCheck = ((CheckBox)findViewById(R.id.checkbox_term_and_condition));
    this.mImageTermAndCondition = ((ImageView)findViewById(R.id.image_show_termandcondition));
  }
  
  private void initDestinationForm()
  {
    this.mButtonAddNextDestination = ((ImageView)findViewById(R.id.image_add_next_destination));
    this.mLinearAddNextDestination = ((LinearLayout)findViewById(R.id.linear_for_next_destination));
    this.mLinearContactDestination = ((LinearLayout)findViewById(R.id.mLLNextDestinations));
    this.mLinearContactHolderDestination = ((LinearLayout)findViewById(R.id.linear_for_contact_holder_destination));
    this.mLinearContactDestination.setOnClickListener(this);
    this.mDestinationViewMap = new LinkedHashMap();
    LayoutTransition localLayoutTransition = new LayoutTransition();
    localLayoutTransition.disableTransitionType(3);
    this.mLinearAddNextDestination.setLayoutTransition(localLayoutTransition);
  }
  
  private void initEstimationLayout()
  {
    this.mEstimationContainer = ((LinearLayout)findViewById(R.id.estimation_container));
    this.mEstimationProgressBar = ((ProgressBar)findViewById(R.id.estimation_progress));
    this.mEstimationPrice = ((TextView)findViewById(R.id.estimation_price));
  }
  
  private void initExtraFeatureForm()
  {
    this.mShipperCheckBox = ((CheckBox)findViewById(R.id.additional_shipper_checkbox));
    this.mAddShipperButton = ((Button)findViewById(R.id.add_shipper));
    this.mLessShipperButton = ((Button)findViewById(R.id.less_shipper));
    this.mShipperCounter = ((TextView)findViewById(R.id.additional_counter));
    this.mShipperPrice = ((TextView)findViewById(R.id.additional_shipper_price));
  }
  
  private void initItemToDeliverForm()
  {
    this.mItemToDeliver = ((EditText)findViewById(R.id.item_to_deliver));
    this.mFragileCheckBox = ((CheckBox)findViewById(R.id.fragile_checkbox));
    this.mMaxCargoDimension = ((TextView)findViewById(R.id.cargo_dimension_label));
    this.mFragileOptionContainer = findViewById(R.id.fragile_checkbox_container);
    this.mCargoMaxWeight = ((TextView)findViewById(R.id.cargo_max_weight));
    this.mItemToSendTerms = ((ImageView)findViewById(R.id.image_item_to_send));
    this.mItemToSendTerms.setOnClickListener(this);
  }
  
  private void initOriginForm()
  {
    View localView = findViewById(R.id.origin_form);
    this.mOriginStreetLocation = ((TextView)findViewById(R.id.location_target_textview));
    this.mOriginLocationDetail = ((EditText)findViewById(R.id.origin_location_detail));
    this.mOriginSpecialInstruction = ((EditText)findViewById(R.id.origin_instruction));
    this.mLinearContactOrigin = ((LinearLayout)findViewById(R.id.mLLcontactPerson));
    this.mTextNameOrigin = ((EditText)findViewById(R.id.text_name));
    this.mTextPhoneNumberOrigin = ((EditText)findViewById(R.id.text_phone_number));
    this.mLinearContactHolderOrigin = ((LinearLayout)findViewById(R.id.linear_for_contact_holder_origin));
    this.mButtonNewAddFromContactOrigin = ((RelativeLayout)findViewById(R.id.button_new_add_from_contact_origin));
    this.mButtonNewAddFromContactOrigin.setTag(localView);
    this.mLinearContactOrigin.setOnClickListener(this);
  }
  
  private void initPickupTimeView()
  {
    this.mPickup.setOnCheckedChangeListener(OrderFormActivity..Lambda.3.lambdaFactory$(this));
    this.mPickupNow.setChecked(true);
    this.mPickTime.setVisibility(8);
    this.mPickTime.setOnClickListener(OrderFormActivity..Lambda.4.lambdaFactory$(this));
  }
  
  private void initTimePicker(View paramView)
  {
    ToggleButton localToggleButton1 = (ToggleButton)paramView.findViewById(R.id.today_button);
    ToggleButton localToggleButton2 = (ToggleButton)paramView.findViewById(R.id.tomorrow_button);
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.date_text);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.month_text);
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Bold.ttf");
    localToggleButton1.setTypeface(localTypeface);
    localToggleButton2.setTypeface(localTypeface);
    localToggleButton1.setTag(getTodayDate());
    localToggleButton2.setTag(getTomorrowDate());
    paramView = OrderFormActivity..Lambda.7.lambdaFactory$(this, paramView, localToggleButton1, localToggleButton2, localTextView1, localTextView2);
    localToggleButton1.setOnCheckedChangeListener(paramView);
    localToggleButton2.setOnCheckedChangeListener(paramView);
    setToggleButtonState(localToggleButton1, localToggleButton2, localTextView1, localTextView2);
  }
  
  private void initViewListener()
  {
    this.mButtonAddNextDestination.setOnClickListener(this);
    this.mImageTermAndCondition.setOnClickListener(this);
    this.mAddShipperButton.setOnClickListener(this);
    this.mLessShipperButton.setOnClickListener(this);
    this.mShipperCheckBox.setOnCheckedChangeListener(this);
    this.mImageTermAndCondition.setOnClickListener(this);
    this.mOrderNextBUtton.setOnClickListener(this);
    this.mEstimationContainer.setOnClickListener(this);
    this.mTermAndConditionCheck.setOnCheckedChangeListener(OrderFormActivity..Lambda.1.lambdaFactory$(this));
  }
  
  private boolean isAllFormValid()
  {
    boolean bool1 = true;
    this.formValidationError = new StringBuilder();
    if (TextUtils.isEmpty(this.mOriginStreetLocation.getText()))
    {
      this.formValidationError.append(getString(R.string.origin_validation_message));
      bool1 = false;
    }
    if (TextUtils.isEmpty(this.mOriginLocationDetail.getText()))
    {
      this.formValidationError.append(getString(R.string.origin_details_validation_message));
      bool1 = false;
    }
    int j = this.mLinearAddNextDestination.getChildCount();
    int i = 0;
    boolean bool2 = bool1;
    Object localObject;
    if (i < j - 1)
    {
      localObject = this.mLinearAddNextDestination.getChildAt(i);
      TextView localTextView = (TextView)((View)localObject).findViewById(R.id.location_target_textview);
      localObject = (EditText)((View)localObject).findViewById(R.id.destination_location_details);
      if (!TextUtils.isEmpty(localTextView.getText())) {
        break label235;
      }
      this.formValidationError.append(getString(R.string.destination_validation_message));
      bool1 = false;
    }
    label235:
    for (;;)
    {
      if (TextUtils.isEmpty(((EditText)localObject).getText()))
      {
        this.formValidationError.append(getString(R.string.destination_details_validation_message));
        bool1 = false;
      }
      for (;;)
      {
        if (!bool1)
        {
          bool2 = bool1;
          if (TextUtils.isEmpty(this.mItemToDeliver.getText()))
          {
            this.formValidationError.append(getString(R.string.item_to_deliver_validation_message));
            return false;
          }
        }
        else
        {
          i += 1;
          break;
        }
        return bool2;
      }
    }
  }
  
  private boolean isFutureDate(Date paramDate)
  {
    Date localDate = new Date();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    localCalendar.clear(13);
    localCalendar.clear(14);
    localDate = localCalendar.getTime();
    return (paramDate.compareTo(localDate) == 0) || (paramDate.after(localDate));
  }
  
  private Animation outToRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  private void pickMultipleContact(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this, ContactPicker.class);
    localIntent.putExtra("contact id", paramString1);
    localIntent.putExtra("contact name", paramString2);
    startActivityForResult(localIntent, 1);
  }
  
  private void populateContact(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { paramString1 }, null);
    while (localCursor.moveToNext()) {
      localArrayList.add(localCursor.getString(localCursor.getColumnIndex("data1")));
    }
    localCursor.close();
    if (localArrayList.size() == 0)
    {
      this.mEditedContactPerson.setText("");
      this.mEditedContactPerson.requestFocus();
      return;
    }
    if (localArrayList.size() == 1)
    {
      this.mEditedContactPerson.setText((CharSequence)localArrayList.get(0));
      return;
    }
    pickMultipleContact(paramString1, paramString2);
  }
  
  private void reorderDestinationTag()
  {
    int j = this.mLinearAddNextDestination.getChildCount();
    int i = 0;
    while (i < j - 1)
    {
      Object localObject = this.mLinearAddNextDestination.getChildAt(i);
      ImageView localImageView = (ImageView)((View)localObject).findViewById(R.id.button_remove_destination);
      localObject = (LinearLayout)((View)localObject).findViewById(R.id.destination_picker);
      localImageView.setTag(Integer.valueOf(i));
      ((LinearLayout)localObject).setTag(Integer.valueOf(i));
      i += 1;
    }
  }
  
  private void resetDestinationRemoveButton()
  {
    View localView = (View)new ArrayList(this.mDestinationViewMap.keySet()).get(0);
    if (this.mDestinationViewMap.size() == 1)
    {
      localView.setVisibility(8);
      return;
    }
    localView.setVisibility(0);
  }
  
  private void setButtonOrderEnable(boolean paramBoolean)
  {
    this.mOrderNextBUtton.setEnabled(paramBoolean);
    if (paramBoolean)
    {
      this.mOrderNextBUtton.setImageAlpha(200);
      return;
    }
    this.mOrderNextBUtton.setImageAlpha(75);
  }
  
  private void setFragileOption(CargoType paramCargoType)
  {
    if (paramCargoType.isFragileSupport())
    {
      this.mFragileOptionContainer.setVisibility(0);
      return;
    }
    this.mFragileOptionContainer.setVisibility(8);
  }
  
  private void setToggleButtonState(ToggleButton paramToggleButton1, ToggleButton paramToggleButton2, TextView paramTextView1, TextView paramTextView2)
  {
    paramToggleButton2.setChecked(false);
    paramToggleButton1.setChecked(true);
    paramToggleButton2.setEnabled(true);
    paramToggleButton1.setEnabled(false);
    paramToggleButton1.setTextColor(getResources().getColor(R.color.white));
    paramToggleButton2.setTextColor(getResources().getColor(R.color.gobox_green));
    paramToggleButton1 = (Calendar)paramToggleButton1.getTag();
    int i = paramToggleButton1.get(5);
    paramToggleButton1 = paramToggleButton1.getDisplayName(2, 2, Locale.US);
    paramTextView1.setText(i + "");
    paramTextView2.setText(paramToggleButton1);
  }
  
  private void showValidationDialog(String paramString)
  {
    new AlertDialog.Builder(this).setMessage(paramString).setCancelable(true).setPositiveButton("OK", OrderFormActivity..Lambda.9.lambdaFactory$()).setTitle(getString(R.string.order_validation_error_message)).show();
  }
  
  public void addNextDestination()
  {
    if (this.mDestinationViewMap.size() <= this.maxDestinationSize)
    {
      int i = this.mOrderFormPresenter.onDestinationAdded();
      View localView = LayoutInflater.from(this).inflate(R.layout.content_next_destination, null);
      ImageView localImageView = (ImageView)localView.findViewById(R.id.button_remove_destination);
      LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(R.id.destination_picker);
      RelativeLayout localRelativeLayout = (RelativeLayout)localView.findViewById(R.id.button_new_add_from_contact_destination);
      ((LinearLayout)localView.findViewById(R.id.mLLDestinationContactPerson)).setOnClickListener(new OrderFormActivity.1(this, localView));
      localImageView.setTag(Integer.valueOf(i));
      localRelativeLayout.setTag(localView);
      localLinearLayout.setTag(Integer.valueOf(i));
      localImageView.setOnClickListener(this);
      this.mDestinationViewMap.put(localImageView, localView);
      this.mLinearAddNextDestination.addView(localView, this.mLinearAddNextDestination.getChildCount() - 1);
      resetDestinationRemoveButton();
      if (this.mDestinationViewMap.size() == this.maxDestinationSize) {
        this.mButtonAddNextDestination.setVisibility(8);
      }
    }
  }
  
  public long getEscrowCeiling()
  {
    return this.mGoBoxPreferences.getEscrowCeiling();
  }
  
  public LatLng getLastLocation()
  {
    android.location.Location localLocation = this.mLocationClient.getLastLocation();
    this.mLastLocation = localLocation;
    double d1;
    if (localLocation == null)
    {
      d1 = -6.2305195D;
      if (localLocation != null) {
        break label52;
      }
    }
    label52:
    for (double d2 = 106.8074253D;; d2 = localLocation.getLongitude())
    {
      return new LatLng(d1, d2);
      d1 = localLocation.getLatitude();
      break;
    }
  }
  
  public void hideEstimationView()
  {
    this.mEstimationContainer.setVisibility(8);
  }
  
  public void hideProgressBar()
  {
    this.mProgressBar.setVisibility(8);
    this.mOrderNextBUtton.setVisibility(0);
  }
  
  public void initMapView()
  {
    CustomMapFragment localCustomMapFragment = (CustomMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
    ScrollView localScrollView = (ScrollView)findViewById(R.id.scrollView);
    this.mGoogleMap = localCustomMapFragment.getMap();
    this.mGoogleMap.setMapType(1);
    this.mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
    this.mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
    this.mGoogleMap.setMyLocationEnabled(true);
    this.mGoogleMap.setOnCameraChangeListener(this);
    localCustomMapFragment.setListener(OrderFormActivity..Lambda.2.lambdaFactory$(localScrollView));
    this.mLocationClient = new LocationClient(this, this, this);
    this.mLocationClient.connect();
  }
  
  public void initOrderForm()
  {
    this.mGoBoxPreferences = new GoBoxPreferences(this);
    this.maxDestinationSize = this.mGoBoxPreferences.getMaxDestination();
    this.maxAdditionalShipper = this.mGoBoxPreferences.getMaxAdditionalShipper();
    this.mOrderNextBUtton = ((ImageView)findViewById(R.id.order_next_button));
    this.mProgressBar = ((ProgressBar)findViewById(R.id.order_progress));
    setButtonOrderEnable(false);
    initBookingTimeForm();
    initOriginForm();
    initDestinationForm();
    initItemToDeliverForm();
    initExtraFeatureForm();
    initEstimationLayout();
    addNextDestination();
    initViewListener();
    initAdditionalShipperView();
    initPickupTimeView();
  }
  
  public void initVehicleOption()
  {
    Object localObject = new GoBoxPreferences(this);
    this.mVehicleGridView = ((GridView)findViewById(R.id.vehicle_grid));
    this.cargoTypes = ((GoBoxPreferences)localObject).getCargoTypeData().getmCargoTypes();
    this.mVehicleGridView.setNumColumns(this.cargoTypes.size());
    mSelectedCargoType = getSelectedVehicleFromExtras();
    this.mOrderFormPresenter.onCargoTypeChanged(mSelectedCargoType);
    this.mOrderFormPresenter.onAdditionalShipperChanged(0);
    localObject = new VehicleAdapter(this, R.layout.vehicle_option_item, this.cargoTypes, mSelectedCargoType.getId());
    ((VehicleAdapter)localObject).setOnSelectedVehicleChangedListener(this);
    this.mMaxCargoDimension.setText(String.format(getString(R.string.items_to_deliver_desc), new Object[] { Long.valueOf(mSelectedCargoType.getLength()), Long.valueOf(mSelectedCargoType.getWidth()), Long.valueOf(mSelectedCargoType.getHeight()) }));
    this.mCargoMaxWeight.setText(String.format(getString(R.string.cargo_max_weight), new Object[] { Integer.valueOf(this.intCargoMaxWeight) }));
    this.mVehicleGridView.setAdapter((ListAdapter)localObject);
    setFragileOption(mSelectedCargoType);
    this.mShipperPrice.setText(String.format(getString(R.string.additional_shipper_price), new Object[] { Long.valueOf(mSelectedCargoType.getPricePerShipper()) }));
  }
  
  public void negativeButtonClicked(int paramInt) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      switch (paramInt1)
      {
      }
    }
    label159:
    label196:
    label203:
    label298:
    label335:
    label342:
    do
    {
      return;
      this.mEditedContactPerson.setText(paramIntent.getStringExtra("phone number"));
      return;
      localObject1 = paramIntent.getStringExtra("LOCATION_ADDRESS");
      Object localObject2 = paramIntent.getStringExtra("LOCATION_NAME");
      String str = paramIntent.getStringExtra("LOCATION_DESC");
      double d1 = paramIntent.getDoubleExtra("LOCATION_LNG", 0.0D);
      double d2 = paramIntent.getDoubleExtra("LOCATION_LAT", 0.0D);
      OrderFormPresenter localOrderFormPresenter = this.mOrderFormPresenter;
      paramInt1 = this.mDestinationOrderTarget;
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        paramIntent = (Intent)localObject2;
        localOrderFormPresenter.onDestinationLocationPicked(paramInt1, d2, d1, paramIntent);
        paramIntent = this.mLocationTargetTextView;
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label196;
        }
        paramIntent.setText((CharSequence)localObject2);
        localObject1 = this.mLocationDetailsTargettextView;
        if (!TextUtils.isEmpty(str)) {
          break label203;
        }
      }
      for (paramIntent = "";; paramIntent = str)
      {
        ((EditText)localObject1).setText(paramIntent);
        return;
        paramIntent = (Intent)localObject1;
        break;
        localObject2 = localObject1;
        break label159;
      }
      localObject1 = paramIntent.getStringExtra("LOCATION_ADDRESS");
      localObject2 = paramIntent.getStringExtra("LOCATION_NAME");
      str = paramIntent.getStringExtra("LOCATION_DESC");
      d1 = paramIntent.getDoubleExtra("LOCATION_LNG", 0.0D);
      d2 = paramIntent.getDoubleExtra("LOCATION_LAT", 0.0D);
      localOrderFormPresenter = this.mOrderFormPresenter;
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        paramIntent = (Intent)localObject2;
        localOrderFormPresenter.onOriginLocationPicked(d2, d1, paramIntent);
        paramIntent = this.mLocationTargetTextView;
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label335;
        }
        paramIntent.setText((CharSequence)localObject2);
        localObject1 = this.mLocationDetailsTargettextView;
        if (!TextUtils.isEmpty(str)) {
          break label342;
        }
      }
      for (paramIntent = "";; paramIntent = str)
      {
        ((EditText)localObject1).setText(paramIntent);
        return;
        paramIntent = (Intent)localObject1;
        break;
        localObject2 = localObject1;
        break label298;
      }
      paramIntent = paramIntent.getData();
      localObject1 = getContentResolver().query(paramIntent, null, null, null, null);
    } while (!((Cursor)localObject1).moveToFirst());
    paramIntent = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("_id"));
    Object localObject1 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("display_name"));
    this.mEditedContactPersonName.setText((CharSequence)localObject1);
    populateContact(paramIntent, (String)localObject1);
  }
  
  public void onBackPressed()
  {
    startActivity(new Intent(this, CargoTypeActivity.class));
    finish();
  }
  
  public void onCalculatingEstimation()
  {
    this.mEstimationContainer.setEnabled(false);
    this.mEstimationContainer.setVisibility(0);
    this.mEstimationProgressBar.setVisibility(0);
    this.mEstimationPrice.setVisibility(8);
    this.mCalculatingEstimation = true;
    setButtonOrderEnable(false);
  }
  
  public void onCalculationError(Throwable paramThrowable)
  {
    this.mEstimationProgressBar.setVisibility(8);
    this.mEstimationPrice.setVisibility(0);
    this.mEstimationPrice.setText(getString(R.string.failed_to_calculate_price_message));
    this.mEstimationContainer.setEnabled(true);
    this.mCalculatingEstimation = true;
    setButtonOrderEnable(false);
    errorConnectionHandler(paramThrowable);
  }
  
  public void onCalculationSuccess(double paramDouble1, double paramDouble2)
  {
    this.mEstimationContainer.setEnabled(false);
    DecimalFormat localDecimalFormat = new DecimalFormat("0.00");
    NumberFormat localNumberFormat = NumberFormat.getCurrencyInstance(new Locale("ca", "CA"));
    this.mEstimationProgressBar.setVisibility(8);
    this.mCalculatingEstimation = false;
    setButtonOrderEnable(this.mTermAndConditionCheck.isChecked());
    this.mEstimationPrice.setVisibility(0);
    this.mEstimationPrice.setText("(" + localDecimalFormat.format(paramDouble1) + "KM) Rp" + localNumberFormat.format(paramDouble2).replace("CA$", "").replace(",00", ""));
  }
  
  public void onCameraChange(CameraPosition paramCameraPosition)
  {
    this.mLastZoomLevel = paramCameraPosition.zoom;
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      this.mAdditionalShipperCounter = 1;
      this.mShipperCounter.setText(String.format(getString(R.string.additional_shipper_counter_label), new Object[] { Integer.valueOf(this.mAdditionalShipperCounter) }));
      this.mAddShipperButton.setEnabled(false);
      this.mLessShipperButton.setEnabled(false);
      this.mOrderFormPresenter.onAdditionalShipperChanged(0);
      return;
    }
    if (this.maxAdditionalShipper > 0) {
      this.mAddShipperButton.setEnabled(true);
    }
    this.mOrderFormPresenter.onAdditionalShipperChanged(this.mAdditionalShipperCounter);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.image_add_next_destination) {
      addNextDestination();
    }
    do
    {
      do
      {
        do
        {
          return;
          if (paramView.getId() == R.id.button_remove_destination)
          {
            removeNextDestination(paramView);
            return;
          }
          if (paramView.getId() == R.id.image_show_termandcondition)
          {
            showTermAndCondition();
            return;
          }
          if (paramView.getId() != R.id.add_shipper) {
            break;
          }
          this.mAdditionalShipperCounter += 1;
          this.mShipperCounter.setText(String.format(getString(R.string.additional_shipper_counter_label), new Object[] { Integer.valueOf(this.mAdditionalShipperCounter) }));
          this.mLessShipperButton.setEnabled(true);
          this.mOrderFormPresenter.onAdditionalShipperChanged(this.mAdditionalShipperCounter);
        } while (this.mAdditionalShipperCounter != this.maxAdditionalShipper);
        this.mAddShipperButton.setEnabled(false);
        return;
        if (paramView.getId() != R.id.less_shipper) {
          break;
        }
        this.mAdditionalShipperCounter -= 1;
        this.mShipperCounter.setText(String.format(getString(R.string.additional_shipper_counter_label), new Object[] { Integer.valueOf(this.mAdditionalShipperCounter) }));
        this.mAddShipperButton.setEnabled(true);
        this.mOrderFormPresenter.onAdditionalShipperChanged(this.mAdditionalShipperCounter);
      } while (this.mAdditionalShipperCounter != 1);
      this.mLessShipperButton.setEnabled(false);
      return;
      if (paramView.getId() == R.id.order_next_button)
      {
        if (this.mGoBoxPreferences.isLoggedIn())
        {
          if (ConnectionManager.isConnected(this))
          {
            this.mOrderFormPresenter.onNextButtonClicked();
            return;
          }
          noInternetConnectionHandler();
          return;
        }
        startActivity(new Intent("com.gojek.app.SIGN_UP"));
        return;
      }
      if (paramView.getId() == R.id.mLLcontactPerson)
      {
        if (this.mLinearContactHolderOrigin.getVisibility() == 8)
        {
          this.mLinearContactHolderOrigin.setVisibility(0);
          return;
        }
        this.mLinearContactHolderOrigin.setVisibility(8);
        return;
      }
      if (paramView.getId() == R.id.image_item_to_send)
      {
        showItemToSendInfo();
        return;
      }
    } while (paramView.getId() != R.id.estimation_container);
    this.mOrderFormPresenter.onEstimationContainerClicked();
  }
  
  public void onConnected(Bundle paramBundle)
  {
    paramBundle = getLastLocation();
    if (ConnectionManager.isConnected(this)) {
      this.mOrderFormPresenter.onWaitingListVehicle(mSelectedCargoType.getId(), paramBundle.latitude, paramBundle.longitude);
    }
    for (;;)
    {
      this.mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paramBundle, 14.0F));
      createLocationRequest();
      return;
      noInternetConnectionHandler();
    }
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    noInternetConnectionHandler();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.order_form);
    initToolbarView();
    setTitle(getString(R.string.gobox_title));
    this.mOrderFormPresenter = PresenterFactory.createOrderFormPresenterFactory(this, getNetworkManager());
    this.mOrderFormPresenter.onOrderViewCreate();
  }
  
  public void onDisconnected()
  {
    noInternetConnectionHandler();
  }
  
  public void onLocationChanged(android.location.Location paramLocation)
  {
    this.mLastLocation = paramLocation;
    GoogleMap localGoogleMap = this.mGoogleMap;
    paramLocation = new LatLng(paramLocation.getLatitude(), paramLocation.getLongitude());
    if (this.mLastZoomLevel == -1.0F) {}
    for (float f = 14.0F;; f = this.mLastZoomLevel)
    {
      localGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paramLocation, f));
      return;
    }
  }
  
  public void onSelectedCargoChanged(CargoType paramCargoType)
  {
    setFragileOption(paramCargoType);
    mSelectedCargoType = paramCargoType;
    if (ConnectionManager.isConnected(this)) {
      if (ConnectionManager.isGpsEnable(this))
      {
        LatLng localLatLng = getLastLocation();
        this.mOrderFormPresenter.onWaitingListVehicle(paramCargoType.getId(), localLatLng.latitude, localLatLng.longitude);
      }
    }
    for (;;)
    {
      this.mShipperPrice.setText(String.format(getString(R.string.additional_shipper_price), new Object[] { Long.valueOf(paramCargoType.getPricePerShipper()) }));
      this.mMaxCargoDimension.setText(String.format(getString(R.string.items_to_deliver_desc), new Object[] { Long.valueOf(paramCargoType.getLength()), Long.valueOf(paramCargoType.getWidth()), Long.valueOf(paramCargoType.getHeight()) }));
      this.mCargoMaxWeight.setText(String.format(getString(R.string.cargo_max_weight), new Object[] { Integer.valueOf(paramCargoType.getMaxWeight()) }));
      this.mOrderFormPresenter.onCargoTypeChanged(paramCargoType);
      return;
      noGpsConnectionHandler();
      continue;
      noInternetConnectionHandler();
    }
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void openContactPickerNative(View paramView)
  {
    paramView = (View)paramView.getTag();
    this.mEditedContactPerson = ((EditText)paramView.findViewById(R.id.text_phone_number));
    this.mEditedContactPersonName = ((EditText)paramView.findViewById(R.id.text_name));
    startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 21);
  }
  
  public void openLocationPicker(View paramView)
  {
    Intent localIntent = new Intent("com.gojek.app.PICK_LOCATION");
    localIntent.putExtra("CACHE_LOCATION_HISTORY", true);
    this.mLocationTargetTextView = ((TextView)paramView.findViewById(R.id.location_target_textview));
    paramView.getRootView().findViewById(R.id.origin_location_detail);
    if (paramView.getId() == R.id.origin_picker)
    {
      this.mLocationDetailsTargettextView = ((EditText)paramView.getRootView().findViewById(R.id.origin_location_detail));
      startActivityForResult(localIntent, 2);
      return;
    }
    this.mDestinationOrderTarget = ((Integer)paramView.getTag()).intValue();
    this.mLocationDetailsTargettextView = ((EditText)((ViewGroup)paramView.getParent()).findViewById(R.id.destination_location_details));
    startActivityForResult(localIntent, 3);
  }
  
  public void openTimePicker()
  {
    View localView = View.inflate(this, R.layout.gobox_date_time_picker, null);
    TimePicker localTimePicker = (TimePicker)localView.findViewById(R.id.time_picker);
    initTimePicker(localView);
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    ((Button)localView.findViewById(R.id.date_time_set)).setOnClickListener(OrderFormActivity..Lambda.5.lambdaFactory$(this, localView, localTimePicker, localAlertDialog));
    ((Button)localView.findViewById(R.id.cancel)).setOnClickListener(OrderFormActivity..Lambda.6.lambdaFactory$(localAlertDialog));
    localAlertDialog.setView(localView);
    localAlertDialog.show();
  }
  
  public void populateVehicleType(VehicleTypeResponse paramVehicleTypeResponse)
  {
    int j = paramVehicleTypeResponse.getArrayVehicle().size();
    this.mGoogleMap.clear();
    int i = 0;
    while (i < j)
    {
      Object localObject = (VehicleType)paramVehicleTypeResponse.getArrayVehicle().get(i);
      localObject = new LatLng(((VehicleType)localObject).getLat(), ((VehicleType)localObject).getLongi());
      this.mGoogleMap.addMarker(new MarkerOptions().position((LatLng)localObject).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_location_gobox)));
      i += 1;
    }
  }
  
  public void positiveButtonClicked(int paramInt)
  {
    if (paramInt == 18) {}
  }
  
  public void removeNextDestination(View paramView)
  {
    ViewGroup localViewGroup = (ViewGroup)this.mDestinationViewMap.get(paramView);
    localViewGroup.startAnimation(outToRightAnimation());
    new Handler().postDelayed(OrderFormActivity..Lambda.8.lambdaFactory$(this, paramView, localViewGroup), 400L);
  }
  
  public void showConfirmationScreen(com.gojek.gobox.model.Location paramLocation, ArrayList<com.gojek.gobox.model.Location> paramArrayList, EstimationResponse paramEstimationResponse, long paramLong1, long paramLong2)
  {
    if (isAllFormValid())
    {
      paramLocation = createBookingRequestBody(paramLocation, paramArrayList, paramEstimationResponse);
      paramArrayList = new Intent(this, ConfirmActivity.class);
      Gson localGson = new Gson();
      if (!(localGson instanceof Gson))
      {
        paramLocation = localGson.toJson(paramLocation);
        paramArrayList.putExtra("booking body", paramLocation);
        paramArrayList.putExtra("cargo_type_id", paramLong1);
        paramArrayList.putExtra("credit balance", paramLong2);
        paramLocation = new Gson();
        if ((paramLocation instanceof Gson)) {
          break label129;
        }
      }
      label129:
      for (paramLocation = paramLocation.toJson(paramEstimationResponse);; paramLocation = GsonInstrumentation.toJson((Gson)paramLocation, paramEstimationResponse))
      {
        paramArrayList.putExtra("loading_shipper_price", paramLocation);
        startActivity(paramArrayList);
        return;
        paramLocation = GsonInstrumentation.toJson((Gson)localGson, paramLocation);
        break;
      }
    }
    showValidationDialog(this.formValidationError.toString());
  }
  
  public void showErrorConfirmOrder(Throwable paramThrowable)
  {
    errorConnectionHandler(paramThrowable);
  }
  
  public void showItemToSendInfo()
  {
    Intent localIntent = new Intent(this, StaticWebInfoActivity.class);
    localIntent.putExtra("info url", "uploads/items-to-send-inline.html");
    localIntent.putExtra("title url", getString(R.string.item_to_send_title));
    startActivity(localIntent);
  }
  
  public void showProgressBar()
  {
    this.mOrderNextBUtton.setVisibility(8);
    this.mProgressBar.setVisibility(0);
  }
  
  public void showTermAndCondition()
  {
    Intent localIntent = new Intent(this, StaticWebInfoActivity.class);
    localIntent.putExtra("info url", "uploads/terms-condition-inline.html");
    localIntent.putExtra("title url", getString(R.string.terms_and_conditions));
    startActivity(localIntent);
  }
  
  private static class VehicleRequestTimer
    extends CountDownTimer
  {
    private CargoType mCargoType;
    private OrderFormPresenter mOrderFormPresenter;
    private android.location.Location mTimerLocation;
    
    public VehicleRequestTimer(long paramLong1, long paramLong2, OrderFormPresenter paramOrderFormPresenter, android.location.Location paramLocation, CargoType paramCargoType)
    {
      super(paramLong2);
      this.mOrderFormPresenter = paramOrderFormPresenter;
      this.mTimerLocation = paramLocation;
      this.mCargoType = OrderFormActivity.mSelectedCargoType;
    }
    
    public void onFinish()
    {
      this.mOrderFormPresenter.onWaitingListVehicle(this.mCargoType.getId(), this.mTimerLocation.getLatitude(), this.mTimerLocation.getLongitude());
    }
    
    public void onTick(long paramLong) {}
    
    public void setCargoType(CargoType paramCargoType)
    {
      this.mCargoType = paramCargoType;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/view/OrderFormActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */