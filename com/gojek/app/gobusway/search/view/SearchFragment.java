package com.gojek.app.gobusway.search.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.activity.BusWayMainActivity;
import com.gojek.app.gobusway.activity.FragmentController;
import com.gojek.app.gobusway.adapter.SearchHalteAdapter;
import com.gojek.app.gobusway.model.Halte;
import com.gojek.app.gobusway.networking.NetworkServiceProvider;
import com.gojek.app.gobusway.search.presenter.SearchPresenter;
import com.gojek.app.gobusway.util.PresenterFactory;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import org.parceler.Parcels;

@Instrumented
public class SearchFragment
  extends Fragment
  implements TextWatcher, AdapterView.OnItemClickListener, SearchView, TraceFieldInterface
{
  private static final String HALTE_LIST_PARAM_KEY = "halte list";
  private FragmentController mFragmentController;
  private ArrayList<Halte> mHalteList;
  private View mRootView;
  private SearchHalteAdapter mSearchAdapter;
  private EditText mSearchField;
  private ListView mSearchListView;
  private SearchPresenter mSearchPresenter;
  private boolean needSetHalteListToMap;
  
  public static SearchFragment newInstance(ArrayList<Halte> paramArrayList)
  {
    SearchFragment localSearchFragment = new SearchFragment();
    Bundle localBundle = new Bundle();
    if (paramArrayList != null) {
      localBundle.putParcelable("halte list", Parcels.wrap(paramArrayList));
    }
    localSearchFragment.setArguments(localBundle);
    return localSearchFragment;
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    this.mSearchAdapter.getFilter().filter(paramEditable.toString());
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void initListView()
  {
    if (this.mHalteList != null) {}
    for (this.mSearchAdapter = new SearchHalteAdapter(getActivity(), R.layout.search_halte_item, this.mHalteList);; this.mSearchAdapter = new SearchHalteAdapter(getActivity(), R.layout.search_halte_item, new ArrayList()))
    {
      this.mSearchListView.setOnItemClickListener(this);
      this.mSearchListView.setAdapter(this.mSearchAdapter);
      return;
      this.needSetHalteListToMap = true;
      this.mSearchPresenter.onHalteListEmpty();
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mFragmentController = ((FragmentController)paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("SearchFragment");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "SearchFragment#onCreate", null);
      super.onCreate(paramBundle);
      if (getArguments() != null) {
        this.mHalteList = ((ArrayList)Parcels.unwrap(getArguments().getParcelable("halte list")));
      }
      TraceMachine.exitMethod();
      return;
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "SearchFragment#onCreate", null);
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "SearchFragment#onCreateView", null);
      if (this.mRootView == null)
      {
        this.mRootView = paramLayoutInflater.inflate(R.layout.fragment_search, paramViewGroup, false);
        this.mSearchListView = ((ListView)this.mRootView.findViewById(R.id.search_result_list));
        this.mSearchField = ((EditText)this.mRootView.findViewById(R.id.search_field));
        this.mSearchField.addTextChangedListener(this);
        this.mSearchPresenter = PresenterFactory.createSearchPresenter(this, ((BusWayMainActivity)getActivity()).getNetworkServiceProvider().getNetworkManager());
        this.mSearchPresenter.onSearchCreateView();
      }
      paramLayoutInflater = this.mRootView;
      TraceMachine.exitMethod();
      return paramLayoutInflater;
    }
    catch (NoSuchFieldError paramBundle)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "SearchFragment#onCreateView", null);
      }
    }
  }
  
  public void onDestroyView()
  {
    ((InputMethodManager)getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mSearchField.getWindowToken(), 0);
    super.onDestroyView();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.mFragmentController.showHalteMarkerOnMap((Halte)paramAdapterView.getAdapter().getItem(paramInt), this.mHalteList, this.needSetHalteListToMap);
  }
  
  public void onResume()
  {
    super.onResume();
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
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void showAllHalte(ArrayList<Halte> paramArrayList)
  {
    this.mHalteList = paramArrayList;
    this.mSearchAdapter.addAllData(paramArrayList);
    this.mSearchAdapter.getFilter().filter(this.mSearchField.getText());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/search/view/SearchFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */