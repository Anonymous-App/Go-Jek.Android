package com.gojek.gotix.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixDeliveryActivity;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.model.DeliveryBooking;
import com.gojek.gotix.network.model.MapPoint;
import com.gojek.gotix.network.model.RegistrationGCM;
import com.gojek.gotix.presenter.GotixDeliveryFromPresenter;
import com.google.android.gms.maps.model.LatLng;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import nucleus.factory.RequiresPresenter;
import rx.Observable;

@RequiresPresenter(GotixDeliveryFromPresenter.class)
public class GotixDeliveryFormFragment
  extends GotixBaseFragment<GotixDeliveryFromPresenter>
{
  private static final String EMPTY_STRING = "";
  private static final int END_BOOKING_TITLE = 18;
  private static final String NEWLINE_DELIMETER = "\n";
  private static final int START_BOOKING_CODE = 19;
  private static final int START_BOOKING_TITLE = 0;
  private RelativeLayout addNewContact;
  private String bookingCode;
  private ImageView btnNext;
  private CircleProgressBar circleProgressBar;
  private EditText contactNameView;
  private EditText contactNumberView;
  private TextView deliveryBookingCode;
  private TextView deliveryBookingTitle;
  private TextView deliveryContactDesc;
  private TextView deliveryContactPerson;
  private TextView deliveryDeliverTo;
  private TextView deliveryLocation;
  private TextView deliveryLocationDesc;
  private EditText deliveryLocationDetails;
  private TextView deliveryTicket;
  private TextView deliveryTitleEvent;
  private TextView distance;
  private RelativeLayout distanceGroup;
  private GotixDeliveryActivity gotixDeliveryActivity;
  private LinearLayout holderContact;
  private LatLng latLng;
  private String locationAddress;
  private int orderId;
  private LinearLayout pickContact;
  private LinearLayout pickLocation;
  private String ticket;
  private String titleEvent;
  
  private BaseDialogFragment.AlertDialogListener alertDialogListener()
  {
    return new GotixDeliveryFormFragment.1(this);
  }
  
  private void bindViewById(View paramView)
  {
    this.deliveryTitleEvent = ((TextView)paramView.findViewById(R.id.delivery_title_event));
    this.deliveryTicket = ((TextView)paramView.findViewById(R.id.delivery_ticket));
    this.deliveryBookingTitle = ((TextView)paramView.findViewById(R.id.delivery_booking_title));
    this.deliveryDeliverTo = ((TextView)paramView.findViewById(R.id.text_deliver_to));
    this.deliveryContactPerson = ((TextView)paramView.findViewById(R.id.delivery_edit_contact));
    this.deliveryLocation = ((TextView)paramView.findViewById(R.id.delivery_edit_location));
    this.deliveryLocationDetails = ((EditText)paramView.findViewById(R.id.delivery_edit_details));
    this.deliveryLocationDesc = ((TextView)paramView.findViewById(R.id.delivery_location_desc));
    this.deliveryContactDesc = ((TextView)paramView.findViewById(R.id.delivery_contact_desc));
    this.pickLocation = ((LinearLayout)paramView.findViewById(R.id.pick_location));
    this.pickContact = ((LinearLayout)paramView.findViewById(R.id.pick_contact));
    this.contactNameView = ((EditText)paramView.findViewById(R.id.contact_name));
    this.contactNumberView = ((EditText)paramView.findViewById(R.id.contact_number));
    this.holderContact = ((LinearLayout)paramView.findViewById(R.id.holder_contact));
    this.addNewContact = ((RelativeLayout)paramView.findViewById(R.id.add_contact));
    this.btnNext = ((ImageView)paramView.findViewById(R.id.delivery_next));
    this.distanceGroup = ((RelativeLayout)paramView.findViewById(R.id.dinstance_group));
    this.distance = ((TextView)paramView.findViewById(R.id.distance));
    this.circleProgressBar = ((CircleProgressBar)paramView.findViewById(R.id.delivery_progress));
    this.btnNext.setEnabled(false);
  }
  
  private void configCircleProgresbar()
  {
    this.circleProgressBar.setColorSchemeResources(new int[] { R.color.bg_base_green });
  }
  
  private String getContactName()
  {
    if (this.contactNameView.getText() == null) {
      return "";
    }
    return this.contactNameView.getText().toString();
  }
  
  private String getContactPhone()
  {
    if (this.contactNumberView.getText() == null) {
      return "";
    }
    return this.contactNumberView.getText().toString();
  }
  
  private void getDataFromBundle()
  {
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      this.bookingCode = localBundle.getString("booking_code");
      this.titleEvent = localBundle.getString("title_event");
      this.ticket = localBundle.getString("ticket");
      this.orderId = localBundle.getInt("orderIdKey");
    }
  }
  
  private DeliveryBooking getDeliveryBookingData()
  {
    return new DeliveryBooking(BasePreferences.getCustomerId(), getDeviceToken(), this.bookingCode, getDeliveryItem(), getDestinationPoint());
  }
  
  private String getDeliveryItem()
  {
    return String.format(getString(R.string.format_item_booking), new Object[] { this.deliveryBookingTitle.getText().toString(), this.deliveryTitleEvent.getText().toString(), this.deliveryTicket.getText().toString() });
  }
  
  private MapPoint getDestinationPoint()
  {
    return new MapPoint(this.deliveryLocation.getText().toString(), this.deliveryLocationDetails.getText().toString(), this.locationAddress, getContactName(), getContactPhone(), Double.valueOf(this.latLng.latitude), Double.valueOf(this.latLng.longitude));
  }
  
  private String getDeviceToken()
  {
    return GotixData.getRegistrationGCM().getDeviceToken();
  }
  
  private void hideHolderContact()
  {
    this.holderContact.setVisibility(8);
  }
  
  public static GotixDeliveryFormFragment newInstance(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("orderIdKey", paramInt);
    localBundle.putString("booking_code", paramString1);
    localBundle.putString("title_event", paramString2);
    localBundle.putString("ticket", paramString3);
    paramString1 = new GotixDeliveryFormFragment();
    paramString1.setArguments(localBundle);
    return paramString1;
  }
  
  private void prepareListener()
  {
    RxView.clicks(this.pickLocation).subscribe(GotixDeliveryFormFragment..Lambda.1.lambdaFactory$(this));
    RxView.clicks(this.pickContact).subscribe(GotixDeliveryFormFragment..Lambda.2.lambdaFactory$(this));
    RxView.clicks(this.addNewContact).subscribe(GotixDeliveryFormFragment..Lambda.3.lambdaFactory$(this));
    RxView.clicks(this.btnNext).subscribe(GotixDeliveryFormFragment..Lambda.4.lambdaFactory$(this));
  }
  
  private void setBookingCode()
  {
    setBookingCodeTextView(this.bookingCode);
    this.deliveryTitleEvent.setText(this.titleEvent);
    this.deliveryTicket.setText(this.ticket);
  }
  
  private void setBookingCodeTextView(String paramString)
  {
    paramString = String.format(getString(R.string.delivery_booking_ref), new Object[] { paramString });
    this.deliveryBookingTitle.setText(styleBookingCode(paramString));
  }
  
  private void setContactView(String paramString1, String paramString2)
  {
    this.contactNameView.setText(paramString1);
    this.contactNumberView.setText(paramString2);
    this.holderContact.setVisibility(0);
  }
  
  private void setFontTextview(View paramView)
  {
    TypefaceHelper.typeface(paramView, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.deliveryDeliverTo, FontFaceHelper.getBebasNeue());
  }
  
  private void showHolderContact()
  {
    this.holderContact.setVisibility(0);
  }
  
  private Spannable styleBookingCode(String paramString)
  {
    int i = this.bookingCode.length() + 19;
    paramString = new SpannableString(paramString);
    paramString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.bg_base_text)), 0, 18, 33);
    paramString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.gotix_green)), 19, i, 33);
    paramString.setSpan(new StyleSpan(1), 19, i, 33);
    return paramString;
  }
  
  public Observable<CharSequence> deliveryLocationDetailsWidgetObserver()
  {
    return RxTextView.textChanges(this.deliveryLocationDetails);
  }
  
  public Observable<CharSequence> deliveryLocationWidgetObserver()
  {
    return RxTextView.textChanges(this.deliveryLocation);
  }
  
  public void enableDisableButtonNext(boolean paramBoolean)
  {
    this.btnNext.setEnabled(paramBoolean);
  }
  
  protected int getLayout()
  {
    return R.layout.fragment_delivery_form;
  }
  
  public void hideCircleBar()
  {
    this.circleProgressBar.setVisibility(8);
  }
  
  public void hideDistanceView()
  {
    this.gotixDeliveryActivity.setShadowVisibility(true);
    this.distanceGroup.setVisibility(8);
  }
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    this.gotixDeliveryActivity = ((GotixDeliveryActivity)getActivity());
  }
  
  public void onContactPicked(String paramString1, String paramString2)
  {
    ((GotixDeliveryFromPresenter)getPresenter()).onContactPicked(paramString1, paramString2);
    setContactView(paramString1, paramString2);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getDataFromBundle();
  }
  
  public void onLocationPickedFromPickLocation(int paramInt, String paramString1, String paramString2, double paramDouble1, double paramDouble2)
  {
    this.latLng = new LatLng(paramDouble1, paramDouble2);
    this.locationAddress = paramString2;
    showCircleBar();
    setLocationName(paramString1);
    ((GotixDeliveryFromPresenter)getPresenter()).calculateEventDistanceFromLocation(paramInt, Double.valueOf(paramDouble1), Double.valueOf(paramDouble2));
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    bindViewById(paramView);
    configCircleProgresbar();
    setFontTextview(paramView);
    setBookingCode();
    prepareListener();
  }
  
  public void openWaitingDriverPage()
  {
    this.gotixDeliveryActivity.openWaitingDriverPage();
  }
  
  public void setDistanceView(String paramString)
  {
    showDistanceView();
    this.distance.setText(String.format(getString(R.string.distance_format), new Object[] { paramString }));
  }
  
  public void setLocationName(String paramString)
  {
    this.deliveryLocation.setText(paramString);
  }
  
  public void showCircleBar()
  {
    this.circleProgressBar.setVisibility(0);
  }
  
  public void showDialogNetworkProblem()
  {
    this.gotixDeliveryActivity.onNetworkProblem();
    setLocationName("");
  }
  
  public void showDialogWhenLocationOutsideBoundary()
  {
    this.gotixDeliveryActivity.runOnUiThread(GotixDeliveryFormFragment..Lambda.5.lambdaFactory$(this));
  }
  
  public void showDialogWhenNoInternetConnection()
  {
    this.gotixDeliveryActivity.onNoInternetConnection();
    setLocationName("");
  }
  
  public void showDistanceView()
  {
    this.gotixDeliveryActivity.setShadowVisibility(false);
    this.distanceGroup.setVisibility(0);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixDeliveryFormFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */