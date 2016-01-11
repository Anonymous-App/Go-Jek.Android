package com.gojek.gobox.cargoType.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.ProgressBar;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.cargoType.presenter.CargoTypePresenter;
import com.gojek.gobox.model.CargoType;
import com.gojek.gobox.model.CargoTypeResponse;
import com.gojek.gobox.networking.ConnectionManager;
import com.gojek.gobox.orderForm.view.OrderFormActivity;
import com.gojek.gobox.util.AlertDialogFragment.AlertDialogListener;
import com.gojek.gobox.util.GoBoxPreferences;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;

public class CargoTypeActivity
  extends BaseActivity
  implements CargoTypeListAdapter.OnCargoTypeClickListener, CargoTypeView, AlertDialogFragment.AlertDialogListener
{
  private static final String TAG = CargoTypeActivity.class.getName();
  private RecyclerView.Adapter mAdapter;
  private CargoTypePresenter mCargoTypePresenter;
  private RecyclerView.LayoutManager mLayoutManager;
  private ProgressBar mProgressBar;
  private RecyclerView recyclerView;
  
  public void OnCargoClick(CargoType paramCargoType)
  {
    Intent localIntent = new Intent(this, OrderFormActivity.class);
    localIntent.putExtra("cargo_type_object", paramCargoType.getId());
    startActivity(localIntent);
  }
  
  public void hideProgressBar()
  {
    this.mProgressBar.setVisibility(8);
  }
  
  public void initRecyclerView()
  {
    this.recyclerView.setHasFixedSize(true);
    this.mLayoutManager = new LinearLayoutManager(this);
    this.recyclerView.setLayoutManager(this.mLayoutManager);
  }
  
  public void negativeButtonClicked(int paramInt) {}
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent("com.gojek.app.HOME");
    localIntent.addFlags(268468224);
    startActivity(localIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.cargo_type_screen);
    initToolbarView();
    setTitle(getString(R.string.gobox_title));
    this.recyclerView = ((RecyclerView)findViewById(R.id.my_cargo_type_recycler_view));
    this.mProgressBar = ((ProgressBar)findViewById(R.id.progress_bar));
    this.mCargoTypePresenter = PresenterFactory.createCargoTypePresenter(this, getNetworkManager());
    if (ConnectionManager.isConnected(this))
    {
      this.mCargoTypePresenter.onViewCreate();
      return;
    }
    noInternetConnectionHandler();
  }
  
  public void populateCargoTypeList(CargoTypeResponse paramCargoTypeResponse)
  {
    if (ConnectionManager.isConnected(this))
    {
      GoBoxPreferences localGoBoxPreferences = new GoBoxPreferences(this);
      Object localObject = new Gson();
      if (!(localObject instanceof Gson)) {}
      for (localObject = ((Gson)localObject).toJson(paramCargoTypeResponse);; localObject = GsonInstrumentation.toJson((Gson)localObject, paramCargoTypeResponse))
      {
        localGoBoxPreferences.saveCargoTypeData((String)localObject);
        this.recyclerView.setVisibility(0);
        this.mAdapter = new CargoTypeListAdapter(this, paramCargoTypeResponse.getmCargoTypes(), this);
        this.recyclerView.setAdapter(this.mAdapter);
        return;
      }
    }
    noInternetConnectionHandler();
  }
  
  public void positiveButtonClicked(int paramInt)
  {
    if (paramInt == 18) {
      hideProgressBar();
    }
  }
  
  public void showErrorLoadingCargoType(Throwable paramThrowable)
  {
    errorConnectionHandler(paramThrowable);
  }
  
  public void showProgressBar()
  {
    this.mProgressBar.setVisibility(0);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */