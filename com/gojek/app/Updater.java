package com.gojek.app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CustomerPromo;

public class Updater
  extends GojekActivityBase
  implements View.OnClickListener
{
  public static final String KEY_UPDATER = "updater";
  private static final int MENU_CLOSE = 11;
  private static final String TAG = Updater.class.getSimpleName();
  public static final int UPDATE_FORCE = 2;
  public static final int UPDATE_OPTIONAL = 1;
  private ImageView mBtnUpdate;
  private TextView mTVUpdaterMessage;
  private int updaterType;
  
  private void initExtras()
  {
    if (getIntent().hasExtra("updater"))
    {
      this.updaterType = getIntent().getExtras().getInt("updater");
      return;
    }
    startActivity(new Intent(this, Home.class));
    finish();
  }
  
  private void initViews()
  {
    this.mTVUpdaterMessage = ((TextView)findViewById(2131625030));
    this.mBtnUpdate = ((ImageView)findViewById(2131625031));
    this.mBtnUpdate.setOnClickListener(this);
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(17170445)));
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      getSupportActionBar().setDisplayShowHomeEnabled(false);
      getSupportActionBar().setHomeButtonEnabled(false);
    }
  }
  
  private void openPlayStore()
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
    }
  }
  
  private void openPromo()
  {
    VolleyClient.getInstance(this).get(TAG, "http://104.155.199.9/api/v1/gojek/featured.json", new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.i(Updater.TAG, "error got customer promo " + paramAnonymousVolleyError);
        paramAnonymousVolleyError = new Intent(Updater.this, Home.class);
        Updater.this.startActivity(paramAnonymousVolleyError);
        Updater.this.finish();
      }
      
      public void onResponse(CustomerPromo paramAnonymousCustomerPromo)
      {
        Log.i(Updater.TAG, "got customer promo response " + paramAnonymousCustomerPromo);
        Log.i(Updater.TAG, "got customer promo title " + paramAnonymousCustomerPromo.getTitle());
        if ((paramAnonymousCustomerPromo.getTitle() != null) && (paramAnonymousCustomerPromo.getContent() != null))
        {
          Intent localIntent = new Intent(Updater.this, Promo.class);
          localIntent.putExtra("SPLASH_DATA", paramAnonymousCustomerPromo);
          Updater.this.startActivity(localIntent);
          Updater.this.finish();
          return;
        }
        paramAnonymousCustomerPromo = new Intent(Updater.this, Home.class);
        Updater.this.startActivity(paramAnonymousCustomerPromo);
        Updater.this.finish();
      }
    }, CustomerPromo.class);
  }
  
  private void setContent()
  {
    switch (this.updaterType)
    {
    default: 
      return;
    case 1: 
      this.mTVUpdaterMessage.setText(2131165678);
      return;
    }
    this.mTVUpdaterMessage.setText(2131165678);
  }
  
  public void onBackPressed()
  {
    if (this.updaterType == 1)
    {
      openPromo();
      return;
    }
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    openPlayStore();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165603));
    setContentView(2130968842);
    initExtras();
    initViews();
    setContent();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 11) {
      openPromo();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    if (this.updaterType == 1)
    {
      paramMenu.add(0, 11, 0, getString(2131165411)).setIcon(2130837863);
      int i = 0;
      while (i < paramMenu.size())
      {
        MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 2);
        i += 1;
      }
    }
    return true;
  }
  
  protected void onStop()
  {
    VolleyClient.getInstance(this).cancelAllRequest(TAG);
    super.onStop();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/Updater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */