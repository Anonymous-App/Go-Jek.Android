package com.gojek.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.model.Referral;
import com.gojek.app.model.ReferralTemplate;
import com.gojek.app.model.VoucherCodeResponse;
import com.gojek.app.parcelable.Customer;
import com.gojek.app.util.Util;
import java.math.BigInteger;
import org.json.JSONException;
import org.json.JSONObject;

public class GojekCredit
  extends GojekActivityBase
{
  private static final String TAG = GojekCredit.class.getSimpleName();
  private Activity activity;
  private Customer mCustomer;
  private CustomerSaved mCustomerSaved;
  private EditText mETVoucher;
  private LinearLayout mLLVoucher;
  private ReferralTemplate mReferralTemplate;
  private boolean mShowCancel;
  private TextView mTVCredit;
  private TextView mTVRefferalBonusMsg;
  private ProgressDialog progressDialog;
  private VolleyClient volleyClient;
  
  private void doLoadProfile()
  {
    this.mTVCredit.setText("Loading...");
    String str = String.format("https://api.gojek.co.id/gojek/v2/customer/%s", new Object[] { this.mCustomerSaved.customerId });
    this.volleyClient.get(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(GojekCredit.TAG, "error get customer profile " + paramAnonymousVolleyError);
        GojekCredit.this.mTVCredit.setText(paramAnonymousVolleyError.getClass().getSimpleName());
        if (!(paramAnonymousVolleyError instanceof AuthFailureError))
        {
          GojekCredit.this.progressDialogDismiss();
          GojekCredit.this.showSimpleDialog(null, GojekCredit.this.getString(2131165884), "OK", true, null);
          return;
        }
        GojekCredit.this.progressDialogDismiss();
        GojekCredit.this.isUnauthorizedApiAccess();
      }
      
      public void onResponse(Customer paramAnonymousCustomer)
      {
        if (paramAnonymousCustomer != null)
        {
          GojekCredit.access$102(GojekCredit.this, paramAnonymousCustomer);
          GojekCredit.this.mTVCredit.setText(Util.getRupiahFormat(String.valueOf(GojekCredit.this.mCustomer.creditBalance)));
        }
      }
    }, Customer.class, this.mCustomerSaved.getAccessToken());
    str = String.format("https://api.gojek.co.id/gojek/v2/customer/first-ride-bonus-amount/%s", new Object[] { this.mCustomerSaved.customerId });
    this.volleyClient.get(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(GojekCredit.TAG, "error get referral message template " + paramAnonymousVolleyError);
        if (!(paramAnonymousVolleyError instanceof AuthFailureError))
        {
          GojekCredit.this.progressDialogDismiss();
          return;
        }
        GojekCredit.this.progressDialogDismiss();
        GojekCredit.this.isUnauthorizedApiAccess();
      }
      
      public void onResponse(ReferralTemplate paramAnonymousReferralTemplate)
      {
        if (paramAnonymousReferralTemplate != null) {
          GojekCredit.access$402(GojekCredit.this, paramAnonymousReferralTemplate);
        }
      }
    }, ReferralTemplate.class, this.mCustomerSaved.getAccessToken());
    str = String.format("https://api.gojek.co.id/gojek/v2/customer/referral-share-info/%s", new Object[] { this.mCustomerSaved.customerId });
    this.volleyClient.getPlainText(TAG, str, new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.e(GojekCredit.TAG, "error get message bonus " + paramAnonymousVolleyError);
        if (!(paramAnonymousVolleyError instanceof AuthFailureError)) {
          GojekCredit.this.progressDialogDismiss();
        }
        for (;;)
        {
          GojekCredit.this.hideReferralTemplate();
          return;
          GojekCredit.this.progressDialogDismiss();
          GojekCredit.this.isUnauthorizedApiAccess();
        }
      }
      
      public void onResponse(String paramAnonymousString)
      {
        if (paramAnonymousString != null)
        {
          GojekCredit.this.mTVRefferalBonusMsg.setText(Html.fromHtml(paramAnonymousString));
          GojekCredit.this.progressDialogDismiss();
          return;
        }
        GojekCredit.this.hideReferralTemplate();
      }
    }, this.mCustomerSaved.getAccessToken());
  }
  
  private void doRedeemVoucher()
  {
    JSONObject localJSONObject;
    String str;
    if (validation())
    {
      localJSONObject = new JSONObject();
      findViewById(2131624286).setVisibility(0);
      findViewById(2131624689).setVisibility(8);
      findViewById(2131624688).setVisibility(8);
      str = this.mETVoucher.getText().toString();
      if ((!str.matches("[0-9]+")) || (str.length() <= 2)) {}
    }
    else
    {
      try
      {
        localJSONObject.put("referralId", new BigInteger(this.mETVoucher.getText().toString()));
        localJSONObject.put("referrerId", new BigInteger(this.mCustomerSaved.customerId));
        this.volleyClient.post("https://api.gojek.co.id/gojek/v2/customer/referral", localJSONObject, new JsonCallback()
        {
          public void onError(VolleyError paramAnonymousVolleyError)
          {
            GojekCredit.this.findViewById(2131624286).setVisibility(8);
            GojekCredit.this.findViewById(2131624689).setVisibility(0);
            GojekCredit.this.findViewById(2131624688).setVisibility(0);
            Log.e(GojekCredit.TAG, "error post referral code " + paramAnonymousVolleyError);
            if (!(paramAnonymousVolleyError instanceof AuthFailureError))
            {
              GojekCredit.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
              GojekCredit.this.showSimpleDialog(null, "Sorry the customer referral process has been disabled", null, true, null);
              return;
            }
            GojekCredit.this.isUnauthorizedApiAccess();
          }
          
          public void onResponse(Referral paramAnonymousReferral)
          {
            GojekCredit.this.findViewById(2131624286).setVisibility(8);
            GojekCredit.this.findViewById(2131624689).setVisibility(0);
            GojekCredit.this.findViewById(2131624688).setVisibility(0);
            if (paramAnonymousReferral != null) {
              GojekCredit.this.showSimpleDialog(null, paramAnonymousReferral.getMessage(), null, true, null);
            }
          }
        }, Referral.class, this.mCustomerSaved.getAccessToken());
        return;
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
        return;
      }
    }
    try
    {
      localJSONException1.put("redeemTo", this.mCustomerSaved.email);
      localJSONException1.put("redeemBy", this.mCustomerSaved.email);
      localJSONException1.put("voucherCode", str);
      this.volleyClient.post("https://api.gojek.co.id/gojek/v2/voucher/redeem/", localJSONException1, new JsonCallback()
      {
        public void onError(VolleyError paramAnonymousVolleyError)
        {
          GojekCredit.this.findViewById(2131624286).setVisibility(8);
          GojekCredit.this.findViewById(2131624689).setVisibility(0);
          GojekCredit.this.findViewById(2131624688).setVisibility(0);
          Log.e(GojekCredit.TAG, "error redeem voucher " + paramAnonymousVolleyError);
          if (!(paramAnonymousVolleyError instanceof AuthFailureError))
          {
            paramAnonymousVolleyError = GojekCredit.this.volleyClient.getErrorResponse(paramAnonymousVolleyError);
            GojekCredit.this.showSimpleDialog(null, paramAnonymousVolleyError, null, true, null);
            return;
          }
          GojekCredit.this.isUnauthorizedApiAccess();
        }
        
        public void onResponse(VoucherCodeResponse paramAnonymousVoucherCodeResponse)
        {
          GojekCredit.this.findViewById(2131624286).setVisibility(8);
          GojekCredit.this.findViewById(2131624689).setVisibility(0);
          GojekCredit.this.findViewById(2131624688).setVisibility(0);
          if (paramAnonymousVoucherCodeResponse != null)
          {
            GojekCredit.this.showSimpleDialog(null, paramAnonymousVoucherCodeResponse.getMessage(), null, true, null);
            GojekCredit.this.mTVCredit.setText(Util.getRupiahFormat(String.valueOf(paramAnonymousVoucherCodeResponse.getCreditBalance())));
          }
        }
      }, VoucherCodeResponse.class, this.mCustomerSaved.getAccessToken());
      return;
    }
    catch (JSONException localJSONException2)
    {
      localJSONException2.printStackTrace();
    }
  }
  
  private void hideReferralTemplate()
  {
    this.mTVRefferalBonusMsg.setText("");
    findViewById(2131624681).setVisibility(8);
    findViewById(2131624684).setVisibility(8);
  }
  
  private void renderView()
  {
    setContentView(2130968719);
    this.mTVCredit = ((TextView)findViewById(2131624677));
    this.mLLVoucher = ((LinearLayout)findViewById(2131624685));
    this.mETVoucher = ((EditText)findViewById(2131624686));
    this.mTVRefferalBonusMsg = ((TextView)findViewById(2131624680));
  }
  
  private boolean validation()
  {
    boolean bool = true;
    StringBuilder localStringBuilder = new StringBuilder();
    if (TextUtils.isEmpty(this.mETVoucher.getText().toString())) {
      localStringBuilder.append("- Voucher Code").append("\n");
    }
    if (!localStringBuilder.toString().equals(""))
    {
      showSimpleDialog(null, getString(2131165732) + "\n" + localStringBuilder.toString(), null, true, null);
      bool = false;
    }
    return bool;
  }
  
  public void onClick(View paramView)
  {
    if (this.mCustomerSaved.customerId == null) {
      startActivity(new Intent(this, SignUp.class));
    }
    do
    {
      return;
      switch (paramView.getId())
      {
      default: 
        return;
      case 2131624678: 
        startActivity(new Intent(this, TopUp.class));
        return;
      }
    } while ((this.mReferralTemplate == null) || (this.mReferralTemplate.message == null));
    paramView = this.mReferralTemplate.message;
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", paramView);
    localIntent.setType("text/plain");
    startActivity(Intent.createChooser(localIntent, "Share via"));
    return;
    doRedeemVoucher();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165601));
    renderView();
    this.mCustomerSaved = new CustomerSaved(this);
    this.volleyClient = VolleyClient.getInstance(this);
    this.activity = this;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 0)
    {
      this.mLLVoucher.setVisibility(8);
      this.mShowCancel = false;
      supportInvalidateOptionsMenu();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    this.volleyClient.cancelAllRequest(TAG);
    super.onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.mShowCancel)
    {
      paramMenu.clear();
      paramMenu.add(0, 0, 0, "Cancel");
      MenuItemCompat.setShowAsAction(paramMenu.getItem(0), 2);
    }
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mCustomerSaved = new CustomerSaved(this);
    if (this.mCustomerSaved.customerId != null)
    {
      findViewById(2131624678).setVisibility(0);
      findViewById(2131624681).setVisibility(0);
      findViewById(2131624684).setVisibility(0);
      this.progressDialog = ProgressDialog.show(this, "", "Please Wait...", true, true);
      this.progressDialog.setIndeterminate(true);
      this.progressDialog.setCanceledOnTouchOutside(false);
      this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          GojekCredit.this.activity.finish();
        }
      });
      doLoadProfile();
      return;
    }
    findViewById(2131624678).setVisibility(8);
    hideReferralTemplate();
  }
  
  protected void onStop()
  {
    this.volleyClient.cancelAllRequest(TAG);
    super.onStop();
  }
  
  public void progressDialogDismiss()
  {
    if (!isFinishing()) {
      this.progressDialog.dismiss();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/GojekCredit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */