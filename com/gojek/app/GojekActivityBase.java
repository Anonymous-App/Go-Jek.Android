package com.gojek.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.gojek.app.custom.SystemBarTintManager;
import com.gojek.app.mart.MartHistory;
import com.gojek.app.model.CustomerSaved;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

public abstract class GojekActivityBase
  extends AppCompatActivity
{
  public static final int MENU_BASKET = 7;
  public static final int MENU_CLOSE = 6;
  public static final int MENU_REFRESH = 5;
  public static final int MENU_WALLET = 4;
  public static int martIdActived;
  private static ProgressDialog pd;
  protected ActionBar action;
  private CustomerSaved customerSaved;
  private AlertDialog mAlert;
  public MixpanelAPI mixPanel;
  
  @TargetApi(19)
  private void setTranslucentStatus(boolean paramBoolean)
  {
    Window localWindow = getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if (paramBoolean) {}
    for (localLayoutParams.flags |= 0x4000000;; localLayoutParams.flags &= 0xFBFFFFFF)
    {
      localWindow.setAttributes(localLayoutParams);
      return;
    }
  }
  
  public void dismissSimpleDialog()
  {
    if ((this.mAlert != null) && (this.mAlert.isShowing())) {
      this.mAlert.dismiss();
    }
  }
  
  public void dismissSimpleProgressDialog()
  {
    if ((pd != null) && (pd.isShowing())) {
      pd.dismiss();
    }
  }
  
  protected void doSessionAuthenticate()
  {
    showSimpleDialog(null, "Your session has expired, please login to continue", "OK", true, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent(GojekActivityBase.this, SignIn.class);
        paramAnonymousDialogInterface.putExtra("NEED_SIGN_IN", true);
        GojekActivityBase.this.startActivityForResult(paramAnonymousDialogInterface, 401);
      }
    });
  }
  
  public void hideKeyboard()
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
  }
  
  protected void isUnauthorizedApiAccess()
  {
    showSimpleDialog(null, "Your session has expired, please login to continue", "OK", true, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent(GojekActivityBase.this, SignIn.class);
        GojekActivityBase.this.startActivity(paramAnonymousDialogInterface);
      }
    });
  }
  
  protected void isUnauthorizedApiAccess(VolleyError paramVolleyError)
  {
    if ((paramVolleyError instanceof AuthFailureError)) {
      showSimpleDialog(null, "Your session has expired, please login to continue", null, true, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = new Intent(GojekActivityBase.this, SignIn.class);
          GojekActivityBase.this.startActivity(paramAnonymousDialogInterface);
        }
      });
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.action = getSupportActionBar();
    this.customerSaved = new CustomerSaved(this);
    FacebookSdk.sdkInitialize(getApplicationContext());
    this.mixPanel = MixpanelAPI.getInstance(this, getResources().getString(2131165681));
    if (this.action != null)
    {
      paramBundle = LayoutInflater.from(this).inflate(2130968840, null);
      this.action.setDisplayOptions(19);
      this.action.setIcon(2130837844);
      this.action.setDisplayHomeAsUpEnabled(true);
      this.action.setDisplayShowHomeEnabled(true);
      this.action.setHomeButtonEnabled(true);
      this.action.setCustomView(paramBundle);
      if (Build.VERSION.SDK_INT >= 19) {
        setTranslucentStatus(true);
      }
      paramBundle = new SystemBarTintManager(this);
      paramBundle.setStatusBarTintEnabled(true);
      paramBundle.setStatusBarTintResource(2131558489);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 4)
    {
      this.mixPanel.track("PAS: Wallet Selected");
      if (this.customerSaved.customerId == null)
      {
        if (this.customerSaved.email == null) {}
        for (paramMenuItem = new Intent(this, SignUp.class);; paramMenuItem = new Intent(this, SmsVerification.class))
        {
          startActivity(paramMenuItem);
          return true;
        }
      }
      startActivity(new Intent(this, GojekCredit.class));
      return true;
    }
    if (paramMenuItem.getItemId() == 7)
    {
      startActivity(new Intent(this, MartHistory.class));
      return true;
    }
    if (paramMenuItem.getItemId() == 16908332)
    {
      hideKeyboard();
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    AppEventsLogger.deactivateApp(getApplicationContext(), getString(2131165535));
    dismissSimpleDialog();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.add(0, 4, 0, getString(2131165601)).setIcon(2130837954);
    int i = 0;
    while (i < paramMenu.size())
    {
      MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 1);
      i += 1;
    }
    return super.onPrepareOptionsMenu(paramMenu);
  }
  
  protected void onResume()
  {
    this.customerSaved = new CustomerSaved(this);
    AppEventsLogger.activateApp(getApplicationContext(), getString(2131165535));
    super.onResume();
  }
  
  protected void setTextView(TextView paramTextView, String paramString, View... paramVarArgs)
  {
    int i = 0;
    if (paramString == null) {}
    for (String str = "";; str = paramString)
    {
      paramTextView.setText(str);
      if (!TextUtils.isEmpty(paramString)) {
        break label67;
      }
      j = paramVarArgs.length;
      while (i < j)
      {
        paramVarArgs[i].setVisibility(8);
        i += 1;
      }
    }
    paramTextView.setVisibility(8);
    return;
    label67:
    int j = paramVarArgs.length;
    i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(0);
      i += 1;
    }
    paramTextView.setVisibility(0);
    paramTextView.setText(paramString);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (this.action != null) {
      ((TextView)this.action.getCustomView().findViewById(2131625029)).setText(paramCharSequence);
    }
  }
  
  public void showSimpleCallDialog(final String paramString)
  {
    dismissSimpleDialog();
    if (!isFinishing()) {
      this.mAlert = new AlertDialog.Builder(this).setMessage(paramString).setCancelable(true).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          GojekActivityBase.this.dismissSimpleDialog();
        }
      }).setPositiveButton("Call", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if ((paramString != null) && (!paramString.isEmpty())) {}
          try
          {
            paramAnonymousDialogInterface = new Intent("android.intent.action.DIAL");
            paramAnonymousDialogInterface.setData(Uri.parse("tel:" + paramString));
            GojekActivityBase.this.startActivity(paramAnonymousDialogInterface);
            return;
          }
          catch (Exception paramAnonymousDialogInterface) {}
        }
      }).show();
    }
  }
  
  public void showSimpleDialog(String paramString1, String paramString2, String paramString3, boolean paramBoolean, DialogInterface.OnClickListener paramOnClickListener)
  {
    String str = paramString3;
    if (paramString3 == null) {
      str = "Close";
    }
    dismissSimpleDialog();
    if (!isFinishing())
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      paramString3 = paramString2;
      if (paramString2 == null) {
        paramString3 = "";
      }
      this.mAlert = localBuilder.setMessage(paramString3).setCancelable(paramBoolean).setPositiveButton(str, paramOnClickListener).setTitle(paramString1).show();
    }
  }
  
  public void showSimpleProgressDialog(String paramString, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    if ((pd != null) && (pd.isShowing())) {
      pd.dismiss();
    }
    String str = paramString;
    if (paramString == null) {
      str = "Loading...";
    }
    if (!isFinishing()) {
      pd = ProgressDialog.show(this, null, str, true, true, paramOnCancelListener);
    }
  }
  
  protected void showToast(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/GojekActivityBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */