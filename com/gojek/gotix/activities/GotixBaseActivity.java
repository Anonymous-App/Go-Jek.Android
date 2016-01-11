package com.gojek.gotix.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.gojek.gotix.R.anim;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.menu;
import com.gojek.gotix.R.string;
import com.gojek.gotix.helper.GotixEngine;
import com.gojek.gotix.presenter.GotixBasePresenter;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.AbstractBaseActivity;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.util.BaseDialogFragment;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import lib.gojek.base.util.BaseUtils;

public abstract class GotixBaseActivity<T extends GotixBasePresenter>
  extends AbstractBaseActivity<T>
{
  public static final int FLAG_CUSTOM_CONFIRMATION_DIALOG = 2;
  public static String TAG = GotixBaseActivity.class.getSimpleName();
  private MenuItem callCenter;
  private MenuItem refresh;
  private TextView titleBar;
  private Toolbar toolbar;
  private MenuItem wallet;
  
  private BaseDialogFragment getDialog(String paramString1, String paramString2, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return BaseDialogFragment.alertDialogInstance(paramString1, paramString2, getResources().getString(R.string.dialog_ok_button));
    case 2: 
      return BaseDialogFragment.confirmationDialogInstance(paramString1, paramString2, getResources().getString(R.string.dialog_yes_title), getResources().getString(R.string.dialog_no_title));
    }
    return BaseDialogFragment.confirmationDialogInstance(paramString1, paramString2, getResources().getString(R.string.dialog_ok_button), getResources().getString(R.string.dialog_cancel_button));
  }
  
  private void showDialog(String paramString1, String paramString2, int paramInt)
  {
    getDialog(paramString1, paramString2, paramInt).show(getSupportFragmentManager(), getResources().getString(R.string.dialog));
  }
  
  public void closeAllFragment()
  {
    FragmentManager localFragmentManager = getSupportFragmentManager();
    getSupportFragmentManager();
    localFragmentManager.popBackStack(null, 1);
  }
  
  public void closeCurrentFragment()
  {
    getSupportFragmentManager().popBackStack();
  }
  
  protected Fragment getCurrentFragment(int paramInt)
  {
    return getSupportFragmentManager().findFragmentById(paramInt);
  }
  
  protected Fragment getFragment(String paramString)
  {
    return getSupportFragmentManager().findFragmentByTag(paramString);
  }
  
  protected FragmentTransaction getFragmentTransaction(int paramInt, boolean paramBoolean, String paramString)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    if ((paramBoolean) && (isAnyFragmentOnScreen(paramInt))) {
      localFragmentTransaction.addToBackStack(paramString);
    }
    if (!paramBoolean) {
      closeAllFragment();
    }
    return localFragmentTransaction;
  }
  
  public void hideBackNavigation()
  {
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }
  
  protected void hideKeyboard(View paramView)
  {
    setKeyboardVisibility(paramView, false);
  }
  
  public void hideToolbarIcon()
  {
    getSupportActionBar().setIcon(null);
  }
  
  protected boolean isAnyFragmentOnScreen(int paramInt)
  {
    return getCurrentFragment(paramInt) != null;
  }
  
  public void loadFragment(Fragment paramFragment, int paramInt)
  {
    loadFragment(paramFragment, paramInt, null);
  }
  
  public void loadFragment(Fragment paramFragment, int paramInt, String paramString)
  {
    getFragmentTransaction(paramInt, true, paramString).replace(paramInt, paramFragment, paramString).commit();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    GotixEngine.createInstance(getApplicationContext());
    this.toolbar = ((Toolbar)findViewById(R.id.toolbar));
    if (this.toolbar != null)
    {
      TypefaceHelper.typeface(this.toolbar, FontFaceHelper.getBebasNeue());
      this.titleBar = ((TextView)this.toolbar.findViewById(R.id.title));
      setTitleActionBar(getString(R.string.gotix));
      setSupportActionBar(this.toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setHomeAsUpIndicator(R.drawable.gotix_back_button_new);
      getSupportActionBar().setIcon(R.drawable.base_ic_actionbar);
      this.toolbar.setNavigationOnClickListener(GotixBaseActivity..Lambda.1.lambdaFactory$(this));
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getMenuInflater().inflate(R.menu.menu_gotix, paramMenu);
    this.refresh = paramMenu.findItem(R.id.action_refresh);
    this.wallet = paramMenu.findItem(R.id.action_wallet);
    return true;
  }
  
  public void onNetworkProblem()
  {
    runOnUiThread(GotixBaseActivity..Lambda.5.lambdaFactory$(this));
  }
  
  public void onNoInternetConnection()
  {
    runOnUiThread(GotixBaseActivity..Lambda.4.lambdaFactory$(this));
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    if (i == R.id.action_refresh)
    {
      Toast.makeText(getApplicationContext(), getString(R.string.action_refresh).toUpperCase(), 0).show();
      return true;
    }
    if (16908332 == i)
    {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onResume()
  {
    super.onResume();
    setColorStatusBar(this);
  }
  
  public void setColorStatusBar(Activity paramActivity)
  {
    BaseUtils.setColorStatusBar(paramActivity, R.color.toolbar_primaryDark);
  }
  
  public void setFontTitleBar(TypefaceCollection paramTypefaceCollection)
  {
    TypefaceHelper.typeface(this.toolbar, paramTypefaceCollection);
  }
  
  protected void setKeyboardVisibility(View paramView, boolean paramBoolean)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if (paramBoolean)
    {
      localInputMethodManager.showSoftInput(paramView, 2);
      return;
    }
    localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 2);
  }
  
  protected void setRefreshVisible(boolean paramBoolean)
  {
    this.refresh.setVisible(paramBoolean);
  }
  
  public void setTitleActionBar(String paramString)
  {
    if ((this.titleBar != null) && (paramString != null)) {
      this.titleBar.setText(paramString.toUpperCase());
    }
  }
  
  protected void setWalletVisible(boolean paramBoolean)
  {
    this.wallet.setVisible(paramBoolean);
  }
  
  protected void showDialog(String paramString1, String paramString2)
  {
    showDialog(paramString1, paramString2, 0);
  }
  
  protected void showDialog(String paramString1, String paramString2, BaseDialogFragment.AlertDialogListener paramAlertDialogListener)
  {
    showDialog(paramString1, paramString2, paramAlertDialogListener, 0);
  }
  
  protected void showDialog(String paramString1, String paramString2, BaseDialogFragment.AlertDialogListener paramAlertDialogListener, int paramInt)
  {
    paramString1 = getDialog(paramString1, paramString2, paramInt);
    paramString1.setAlertDialogListener(paramAlertDialogListener);
    paramString1.show(getSupportFragmentManager(), getResources().getString(R.string.dialog));
  }
  
  public void showDialogErrorService()
  {
    showDialog(getResources().getString(R.string.dialog_service_error_title), getResources().getString(R.string.dialog_service_error_desc));
  }
  
  public void showDialogNoConnection()
  {
    showDialog(getResources().getString(R.string.dialog_error_network_title), getResources().getString(R.string.dialog_error_network_desc));
  }
  
  public void slideAnimFragment(Fragment paramFragment, int paramInt, int[] paramArrayOfInt, String paramString)
  {
    getFragmentTransaction(paramInt, true, paramString).setCustomAnimations(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]).replace(paramInt, paramFragment, paramString).addToBackStack(paramString).commit();
  }
  
  public void slideLeftFragment(Fragment paramFragment, int paramInt)
  {
    slideLeftFragment(paramFragment, paramInt, null);
  }
  
  public void slideLeftFragment(Fragment paramFragment, int paramInt, String paramString)
  {
    slideAnimFragment(paramFragment, paramInt, new int[] { R.anim.slide_left_support, 0, 0, R.anim.slide_right_support }, paramString);
  }
  
  public void slideUpFragment(Fragment paramFragment, int paramInt)
  {
    slideUpFragment(paramFragment, paramInt, null);
  }
  
  public void slideUpFragment(Fragment paramFragment, int paramInt, String paramString)
  {
    slideAnimFragment(paramFragment, paramInt, new int[] { R.anim.slide_up_support, 0, 0, R.anim.slide_down_support }, paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixBaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */