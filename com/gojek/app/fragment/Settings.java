package com.gojek.app.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.login.LoginManager;
import com.gojek.app.ChangePassword;
import com.gojek.app.EditProfile;
import com.gojek.app.GojekActivityBase;
import com.gojek.app.GojekCredit;
import com.gojek.app.Home;
import com.gojek.app.SignIn;
import com.gojek.app.SignUp;
import com.gojek.app.SmsVerification;
import com.gojek.app.TermOfService;
import com.gojek.app.gcm.GcmUtil;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.persistence.dao.BookingHistoryDao;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

public class Settings
  extends GojekFragmentBase
  implements View.OnClickListener
{
  private static final String TAG = Settings.class.getSimpleName();
  private BookingHistoryDao bookingHistoryDao;
  private LinearLayout llCallUs;
  private LinearLayout llChangePassword;
  private LinearLayout llEditProfile;
  private LinearLayout llLogout;
  private LinearLayout llRateApp;
  private Home mActivity;
  private CustomerSaved mCustomerSaved;
  private LinearLayout mLLCredit;
  private LinearLayout mLLTerm;
  private TextView mTVLogout;
  private VolleyClient volleyClient;
  
  private void doLogout()
  {
    showSimpleProgressDialog("Logging out...", new Settings.1(this));
    LoginManager.getInstance().logOut();
    CustomerSaved localCustomerSaved = new CustomerSaved(getActivity());
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.putOpt("deviceToken", GcmUtil.getRegistrationId(getActivity()));
      localJSONObject.putOpt("activitySource", Integer.valueOf(2));
      localJSONObject.putOpt("customerId", localCustomerSaved.customerId);
      this.volleyClient.postAndGetObject("https://api.gojek.co.id/gojek/customer/logout", localJSONObject, new Settings.2(this), String.class);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  public static Settings newInstance()
  {
    return new Settings();
  }
  
  public void completeLogout()
  {
    this.mCustomerSaved.clearData();
    this.bookingHistoryDao.deleteAllHistory();
    ((GojekActivityBase)getActivity()).mixPanel.reset();
    Intent localIntent = new Intent(getActivity(), SignIn.class);
    localIntent.putExtra("TO_HOME", true);
    startActivity(localIntent);
    getActivity().finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131625011: 
      if (this.mCustomerSaved.customerId == null)
      {
        startActivityForResult(new Intent(this.mActivity, SignUp.class), 100);
        return;
      }
      doLogout();
      return;
    case 2131625010: 
      paramView = new Intent("android.intent.action.DIAL");
      paramView.setData(Uri.parse("tel:" + getString(2131165346)));
      startActivity(paramView);
      return;
    case 2131625005: 
      startActivity(new Intent(getActivity(), EditProfile.class));
      return;
    case 2131625006: 
      startActivity(new Intent(getActivity(), ChangePassword.class));
      return;
    case 2131625009: 
      paramView = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.gojek.app"));
      try
      {
        startActivity(paramView);
        return;
      }
      catch (ActivityNotFoundException paramView)
      {
        Toast.makeText(getActivity(), " unable to find market app", 1).show();
        return;
      }
    case 2131625008: 
      startActivity(new Intent(getActivity(), TermOfService.class));
      return;
    }
    if (this.mCustomerSaved.customerId == null)
    {
      if (this.mCustomerSaved.email == null) {}
      for (paramView = new Intent(getActivity(), SignUp.class);; paramView = new Intent(getActivity(), SmsVerification.class))
      {
        startActivity(paramView);
        return;
      }
    }
    startActivity(new Intent(getActivity(), GojekCredit.class));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mActivity = ((Home)getActivity());
    this.mCustomerSaved = new CustomerSaved(this.mActivity.getApplicationContext());
    this.volleyClient = VolleyClient.getInstance(getActivity());
    this.bookingHistoryDao = new BookingHistoryDao(getContext());
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968827, null);
  }
  
  public void onResume()
  {
    super.onResume();
    this.mCustomerSaved = new CustomerSaved(this.mActivity.getApplicationContext());
    if (this.mCustomerSaved.customerId == null)
    {
      this.llEditProfile.setVisibility(8);
      this.llChangePassword.setVisibility(8);
      this.mTVLogout.setText(2131165801);
      return;
    }
    this.llEditProfile.setVisibility(0);
    this.llChangePassword.setVisibility(0);
    this.mTVLogout.setText(2131165638);
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.llLogout = ((LinearLayout)paramView.findViewById(2131625011));
    this.llCallUs = ((LinearLayout)paramView.findViewById(2131625010));
    this.llEditProfile = ((LinearLayout)paramView.findViewById(2131625005));
    this.llChangePassword = ((LinearLayout)paramView.findViewById(2131625006));
    this.llRateApp = ((LinearLayout)paramView.findViewById(2131625009));
    this.mLLTerm = ((LinearLayout)paramView.findViewById(2131625008));
    this.mLLCredit = ((LinearLayout)paramView.findViewById(2131625007));
    this.mTVLogout = ((TextView)paramView.findViewById(2131625012));
    this.llLogout.setOnClickListener(this);
    this.llCallUs.setOnClickListener(this);
    this.llEditProfile.setOnClickListener(this);
    this.llChangePassword.setOnClickListener(this);
    this.llRateApp.setOnClickListener(this);
    this.mLLTerm.setOnClickListener(this);
    this.mLLCredit.setOnClickListener(this);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/fragment/Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */