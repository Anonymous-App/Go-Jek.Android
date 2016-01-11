package com.gojek.gotix.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.activities.GotixMainActivity;
import com.gojek.gotix.activities.GotixMainNavigationListener;
import com.gojek.gotix.adapters.GotixBaseAdapter;
import com.gojek.gotix.presenter.GotixBasePresenter;
import com.jakewharton.rxbinding.view.RxView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import java.util.List;
import lib.gojek.base.helper.FontFaceHelper;
import rx.Observable;

public abstract class GotixHomeFragment<T extends GotixBasePresenter>
  extends GotixBaseFragment<T>
{
  private GotixViewBinder gotixViewBinder;
  protected GotixMainNavigationListener mainNavigationListener;
  protected GotixMainActivity parentActivity;
  protected ProgressBar progressBar;
  protected RecyclerView recyclerView;
  protected boolean seamlessFetchValid = true;
  private CardView searchBarCard;
  private TextView searchHint;
  protected SwipeRefreshLayout swipeRefreshLayout;
  
  private void addClickListeners()
  {
    RxView.clicks(this.searchBarCard).subscribe(GotixHomeFragment..Lambda.1.lambdaFactory$(this));
    if (this.gotixViewBinder != null) {
      this.gotixViewBinder.addClickListeners();
    }
  }
  
  private void bindViewById(View paramView)
  {
    this.swipeRefreshLayout = ((SwipeRefreshLayout)paramView.findViewById(R.id.swipe_to_refresh));
    this.recyclerView = ((RecyclerView)paramView.findViewById(R.id.event_list));
    this.progressBar = ((ProgressBar)paramView.findViewById(R.id.event_progress_bar));
    this.searchBarCard = ((CardView)paramView.findViewById(R.id.item_search_bar_layout));
    this.searchHint = ((TextView)paramView.findViewById(R.id.search_hint));
    if (this.gotixViewBinder != null) {
      this.gotixViewBinder.bindViewById(paramView);
    }
  }
  
  private void initRecyclerView()
  {
    LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getActivity(), 1, false);
    this.recyclerView.setHasFixedSize(true);
    this.recyclerView.setLayoutManager(localLinearLayoutManager);
    this.recyclerView.addOnScrollListener(new GotixHomeFragment.1(this, localLinearLayoutManager));
  }
  
  private void onRefreshData()
  {
    swipeRefreshLayoutVisibility(true);
    refreshData();
  }
  
  private void setFontFace()
  {
    TypefaceHelper.typeface(this.searchHint, FontFaceHelper.getLatoFont());
    if (this.gotixViewBinder != null) {
      this.gotixViewBinder.setFontFace();
    }
  }
  
  protected <E> void addNewDataToList(List<E> paramList1, List<E> paramList2, int paramInt, GotixBaseAdapter paramGotixBaseAdapter)
  {
    paramList2.addAll(paramList1);
    this.seamlessFetchValid = isSeamlessFetchAllowed(paramList1.size());
    paramGotixBaseAdapter.enableFooter(this.seamlessFetchValid);
    paramGotixBaseAdapter.notifyItemChanged(paramInt);
    paramGotixBaseAdapter.notifyItemRangeInserted(paramInt, paramList1.size());
  }
  
  protected abstract void fetchData();
  
  protected int getLayout()
  {
    return R.layout.fragment_gotix_event;
  }
  
  protected <E> void handleRefreshData(List<E> paramList1, List<E> paramList2, GotixBaseAdapter paramGotixBaseAdapter)
  {
    int i = 0;
    if (i < paramList1.size())
    {
      Object localObject = paramList1.get(i);
      if (paramList2.contains(localObject))
      {
        paramList2.set(paramList2.indexOf(localObject), localObject);
        paramGotixBaseAdapter.notifyItemChanged(paramList2.indexOf(localObject));
      }
      for (;;)
      {
        i += 1;
        break;
        paramList2.add(i, localObject);
        paramGotixBaseAdapter.notifyItemInserted(i);
      }
    }
  }
  
  protected <E> void handleUpdatedData(List<E> paramList1, List<E> paramList2, GotixBaseAdapter paramGotixBaseAdapter)
  {
    int i = paramList2.size();
    if (!paramList2.containsAll(paramList1)) {
      addNewDataToList(paramList1, paramList2, i, paramGotixBaseAdapter);
    }
  }
  
  public void hideProgressBar()
  {
    this.progressBar.setVisibility(8);
  }
  
  protected abstract void initAdapter();
  
  protected void initSwipeRefreshLayout()
  {
    this.swipeRefreshLayout.setColorSchemeColors(new int[] { getResources().getColor(R.color.bg_base_green) });
    this.swipeRefreshLayout.setOnRefreshListener(GotixHomeFragment..Lambda.2.lambdaFactory$(this));
  }
  
  protected boolean isSeamlessFetchAllowed(int paramInt)
  {
    return paramInt == 5;
  }
  
  protected abstract void loadMore();
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    this.parentActivity = ((GotixMainActivity)getActivity());
    try
    {
      this.mainNavigationListener = ((GotixMainNavigationListener)paramContext);
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new ClassCastException(paramContext.getApplicationContext().toString() + "should implement GotixMainNavigationListener");
    }
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    bindViewById(paramView);
    setFontFace();
    addClickListeners();
    initSwipeRefreshLayout();
    initRecyclerView();
    initAdapter();
    showProgressBar();
    fetchData();
  }
  
  protected abstract void refreshData();
  
  public void setViewBinder(GotixViewBinder paramGotixViewBinder)
  {
    this.gotixViewBinder = paramGotixViewBinder;
  }
  
  public void showDialogNetworkProblem()
  {
    this.parentActivity.onNetworkProblem();
  }
  
  public void showDialogWhenNoInternetConnection()
  {
    this.parentActivity.onNoInternetConnection();
  }
  
  public void showProgressBar()
  {
    this.progressBar.setVisibility(0);
  }
  
  protected void swipeRefreshLayoutVisibility(boolean paramBoolean)
  {
    this.swipeRefreshLayout.setRefreshing(paramBoolean);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixHomeFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */