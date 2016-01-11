package com.gojek.app.gobusway.schedule.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.R.string;
import com.gojek.app.gobusway.activity.BusWayMainActivity;
import com.gojek.app.gobusway.activity.FragmentController;
import com.gojek.app.gobusway.adapter.ScheduleAdapter;
import com.gojek.app.gobusway.adapter.ScheduleAdapter.OnButtonRequestClickListener;
import com.gojek.app.gobusway.model.HalteSchedule;
import com.gojek.app.gobusway.networking.ConnectionManager;
import com.gojek.app.gobusway.schedule.presenter.SchedulePresenter;
import com.gojek.app.gobusway.util.PresenterFactory;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;

@Instrumented
public class ScheduleFragment
  extends Fragment
  implements ScheduleAdapter.OnButtonRequestClickListener, ScheduleView, TraceFieldInterface
{
  private static final String HALTE_ID_PARAM_KEY = "halte id";
  private static final String HALTE_LATITUDE_PARAM_KEY = "halte latitude";
  private static final String HALTE_LONGITUDE_PARAM_KEY = "halte longitude";
  private static final String HALTE_NAME_PARAM_KEY = "halte name";
  private FragmentController mFragmentController;
  private String mHalteId;
  private String mHalteName;
  private Double mLatitude;
  private LinearLayoutManager mLayoutManager;
  private Double mLongitude;
  private ProgressBar mProgressBar;
  private ScheduleAdapter mScheduleAdapter;
  private SchedulePresenter mSchedulePresenter;
  private RecyclerView mScheduleRecyclerView;
  private TextView mScheduleTitle;
  
  public static ScheduleFragment newInstance(String paramString1, String paramString2, Double paramDouble1, Double paramDouble2)
  {
    ScheduleFragment localScheduleFragment = new ScheduleFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("halte id", paramString1);
    localBundle.putString("halte name", paramString2);
    localBundle.putDouble("halte latitude", paramDouble1.doubleValue());
    localBundle.putDouble("halte longitude", paramDouble2.doubleValue());
    localScheduleFragment.setArguments(localBundle);
    return localScheduleFragment;
  }
  
  public String getHalteId()
  {
    return this.mHalteId;
  }
  
  public void hideProgress()
  {
    this.mProgressBar.setVisibility(8);
  }
  
  public void initViews()
  {
    this.mLayoutManager = new LinearLayoutManager(getActivity());
    this.mScheduleRecyclerView.setHasFixedSize(true);
    this.mScheduleRecyclerView.setLayoutManager(this.mLayoutManager);
    this.mScheduleTitle.setText(String.format(getString(R.string.halte_name_label), new Object[] { this.mHalteName }).toUpperCase());
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mFragmentController = ((FragmentController)paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("ScheduleFragment");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "ScheduleFragment#onCreate", null);
      super.onCreate(paramBundle);
      if (getArguments() != null)
      {
        this.mHalteId = getArguments().getString("halte id");
        this.mHalteName = getArguments().getString("halte name");
        this.mLatitude = Double.valueOf(getArguments().getDouble("halte latitude"));
        this.mLongitude = Double.valueOf(getArguments().getDouble("halte longitude"));
      }
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "ScheduleFragment#onCreate", null);
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "ScheduleFragment#onCreateView", null);
      paramLayoutInflater = paramLayoutInflater.inflate(R.layout.fragment_schedule, null);
      this.mSchedulePresenter = PresenterFactory.createSchedulePresenter(this, ((BusWayMainActivity)getActivity()).getNetworkManager());
      this.mScheduleRecyclerView = ((RecyclerView)paramLayoutInflater.findViewById(R.id.schedule_list));
      this.mProgressBar = ((ProgressBar)paramLayoutInflater.findViewById(R.id.progress));
      this.mScheduleTitle = ((TextView)paramLayoutInflater.findViewById(R.id.schedule_title));
      initViews();
      if (ConnectionManager.isConnected(getActivity()))
      {
        this.mSchedulePresenter.onScheduleCreateView();
        TraceMachine.exitMethod();
        return paramLayoutInflater;
      }
    }
    catch (NoSuchFieldError paramViewGroup)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "ScheduleFragment#onCreateView", null);
        continue;
        this.mProgressBar.setVisibility(8);
        this.mFragmentController.noInternetConnectionHandler();
      }
    }
  }
  
  public void onRequestClick()
  {
    Intent localIntent = new Intent("com.gojek.app.Services");
    localIntent.putExtra("lat", this.mLatitude);
    localIntent.putExtra("lon", this.mLongitude);
    localIntent.putExtra("destination", String.format(getString(R.string.halte_name_label), new Object[] { this.mHalteName }).toUpperCase());
    startActivity(localIntent);
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
  
  public void showErrorWhileFetchData()
  {
    showSchedule(new ArrayList());
  }
  
  public void showProgress()
  {
    this.mProgressBar.setVisibility(0);
  }
  
  public void showSchedule(ArrayList<HalteSchedule> paramArrayList)
  {
    this.mScheduleRecyclerView.setVisibility(0);
    this.mScheduleAdapter = new ScheduleAdapter(paramArrayList, getActivity(), this);
    this.mScheduleRecyclerView.setAdapter(this.mScheduleAdapter);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/schedule/view/ScheduleFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */