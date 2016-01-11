package com.gojek.gobox.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.string;
import com.gojek.gobox.model.AuthenticationError;
import com.gojek.gobox.networking.NetworkActivity;
import com.gojek.gobox.util.AlertDialogFragment;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BaseActivity
  extends NetworkActivity
{
  private AlertDialog mAlert;
  private ProgressDialog progressDialog;
  private Toolbar toolbar;
  
  private void authenticationErrorHandler()
  {
    AlertDialogFragment.newInstance(getString(R.string.authentication_failed_title), getString(R.string.authentication_failed_message), getString(R.string.button_OK), null, 18, false, new BaseActivity.2(this)).show(getFragmentManager(), "dialog");
  }
  
  private boolean isAuthenticationError(Throwable paramThrowable)
  {
    return paramThrowable instanceof AuthenticationError;
  }
  
  private boolean isInternalServerError(Throwable paramThrowable)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if ((paramThrowable instanceof RetrofitError))
      {
        paramThrowable = (RetrofitError)paramThrowable;
        if ((paramThrowable.getResponse().getStatus() != 500) && (paramThrowable.getResponse().getStatus() != 503) && (paramThrowable.getResponse().getStatus() != 504) && (paramThrowable.getResponse().getStatus() != 502) && (paramThrowable.getResponse().getStatus() != 501))
        {
          int i = paramThrowable.getResponse().getStatus();
          bool1 = bool2;
          if (i != 400) {}
        }
        else
        {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (NullPointerException paramThrowable) {}
    return false;
  }
  
  private boolean isServiceUnavailable(Throwable paramThrowable)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if ((paramThrowable instanceof RetrofitError))
      {
        int i = ((RetrofitError)paramThrowable).getResponse().getStatus();
        bool1 = bool2;
        if (i == 503) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (NullPointerException paramThrowable) {}
    return false;
  }
  
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
    if ((this.progressDialog != null) && (this.progressDialog.isShowing())) {
      this.progressDialog.dismiss();
    }
  }
  
  public void errorConnectionHandler(Throwable paramThrowable)
  {
    if ((isServiceUnavailable(paramThrowable)) || (isInternalServerError(paramThrowable)))
    {
      serviceUnavailableHandler();
      return;
    }
    if (isAuthenticationError(paramThrowable))
    {
      authenticationErrorHandler();
      return;
    }
    noInternetConnectionHandler();
  }
  
  public void errorHandler(String paramString1, String paramString2)
  {
    AlertDialogFragment.newInstance(paramString1, paramString2, getString(R.string.button_OK), 18).show(getFragmentManager(), "dialog");
  }
  
  public void hideBackButton()
  {
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }
  
  public void initToolbarView()
  {
    this.toolbar = ((Toolbar)findViewById(R.id.toolbar));
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_new);
    getSupportActionBar().setIcon(R.drawable.ic_actionbar);
    this.toolbar.setNavigationOnClickListener(new BaseActivity.1(this));
  }
  
  public void noGpsConnectionHandler()
  {
    AlertDialogFragment.newInstance("GPS Unavailable", "Please enable your GPS", "OK", 18).show(getFragmentManager(), "dialog");
  }
  
  public void noInternetConnectionHandler()
  {
    AlertDialogFragment.newInstance(getString(R.string.ERROR_NO_NETWORK_CONNECTION_TITLE), getString(R.string.ERROR_NO_NETWORK_CONNECTION_DESC), getString(R.string.button_OK), 18).show(getFragmentManager(), "dialog");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public void serviceUnavailableHandler()
  {
    AlertDialogFragment.newInstance(getString(R.string.ERROR_SERVICE_NOT_AVAILABLE_TITLE), getString(R.string.ERROR_SERVICE_NOT_AVAILABLE_DESC), getString(R.string.button_OK), 18).show(getFragmentManager(), "dialog");
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (this.toolbar != null) {
      ((TextView)this.toolbar.findViewById(R.id.action_bar_title)).setText(paramCharSequence);
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
  
  public void showSimpleProgressDialog(String paramString)
  {
    if ((this.progressDialog != null) && (this.progressDialog.isShowing())) {
      this.progressDialog.dismiss();
    }
    String str = paramString;
    if (paramString == null) {
      str = "Loading...";
    }
    if (!isFinishing()) {
      this.progressDialog = ProgressDialog.show(this, null, str, true, false);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/base/BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */