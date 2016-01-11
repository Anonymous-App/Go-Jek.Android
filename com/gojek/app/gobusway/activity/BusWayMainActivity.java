package com.gojek.app.gobusway.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.gojek.app.gobusway.R.anim;
import com.gojek.app.gobusway.R.drawable;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.R.string;
import com.gojek.app.gobusway.map.view.BusWayMapFragment;
import com.gojek.app.gobusway.model.Halte;
import com.gojek.app.gobusway.networking.NetworkActivity;
import com.gojek.app.gobusway.schedule.view.ScheduleFragment;
import com.gojek.app.gobusway.search.view.SearchFragment;
import com.gojek.app.gobusway.util.AlertDialogFragment;
import java.util.ArrayList;

public class BusWayMainActivity
  extends NetworkActivity
  implements FragmentController
{
  private Toolbar toolbar;
  
  public void errorHandler(String paramString1, String paramString2)
  {
    AlertDialogFragment.newInstance(paramString1, paramString2, getString(R.string.button_OK), 18).show(getFragmentManager(), "dialog");
  }
  
  public void initToolbarView()
  {
    this.toolbar = ((Toolbar)findViewById(R.id.toolbar));
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_new);
    this.toolbar.setNavigationOnClickListener(new BusWayMainActivity.1(this));
  }
  
  public void noInternetConnectionHandler()
  {
    AlertDialogFragment.newInstance(getString(R.string.ERROR_NO_NETWORK_CONNECTION_TITLE), getString(R.string.ERROR_NO_NETWORK_CONNECTION_DESC), getString(R.string.button_OK), 18).show(getFragmentManager(), "dialog");
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_main);
    initToolbarView();
    replaceFragment(new BusWayMapFragment(), false);
  }
  
  public void replaceFragment(Fragment paramFragment, boolean paramBoolean)
  {
    replaceFragment(paramFragment, paramBoolean, null);
  }
  
  public void replaceFragment(Fragment paramFragment, boolean paramBoolean, String paramString)
  {
    replaceFragment(paramFragment, paramBoolean, paramString, new int[] { R.anim.slide_left_enter, R.anim.slide_left_exit, R.anim.slide_right_enter, R.anim.slide_right_exit });
  }
  
  public void replaceFragment(Fragment paramFragment, boolean paramBoolean, String paramString, int[] paramArrayOfInt)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    if (paramBoolean) {
      localFragmentTransaction.addToBackStack(null);
    }
    localFragmentTransaction.setCustomAnimations(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
    localFragmentTransaction.replace(R.id.main_content, paramFragment, paramString);
    localFragmentTransaction.commitAllowingStateLoss();
    getSupportFragmentManager().executePendingTransactions();
  }
  
  public void serviceUnavailableHandler()
  {
    AlertDialogFragment.newInstance(getString(R.string.ERROR_SERVICE_NOT_AVAILABLE_TITLE), getString(R.string.ERROR_SERVICE_NOT_AVAILABLE_DESC), getString(R.string.button_OK), 18).show(getFragmentManager(), "dialog");
  }
  
  public void setFragmentTitle(String paramString)
  {
    setTitle(paramString);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (this.toolbar != null) {
      ((ImageView)this.toolbar.findViewById(R.id.action_bar_image)).setBackgroundResource(R.drawable.ic_action_trans_logo);
    }
  }
  
  public void showHalteMarkerOnMap(Halte paramHalte, ArrayList<Halte> paramArrayList, boolean paramBoolean)
  {
    onBackPressed();
    BusWayMapFragment localBusWayMapFragment = (BusWayMapFragment)getSupportFragmentManager().findFragmentById(R.id.main_content);
    if (paramBoolean) {
      localBusWayMapFragment.showHalteList(paramArrayList);
    }
    localBusWayMapFragment.showHalteInfoWindow(paramHalte);
  }
  
  public void showHalteSchedule(String paramString1, String paramString2, Double paramDouble1, Double paramDouble2)
  {
    replaceFragment(ScheduleFragment.newInstance(paramString1, paramString2, paramDouble1, paramDouble2), true);
  }
  
  public void showSearchFragment(ArrayList<Halte> paramArrayList)
  {
    replaceFragment(SearchFragment.newInstance(paramArrayList), true);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/activity/BusWayMainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */