package com.gojek.gotix.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.gojek.gotix.Event;
import com.gojek.gotix.adapters.EventAdapterDelegate;
import com.gojek.gotix.presenter.GotixEventPresenter;
import java.util.ArrayList;
import java.util.List;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(GotixEventPresenter.class)
public class GotixEventFragment
  extends GotixHomeFragment<GotixEventPresenter>
{
  private static final String EVENT_TYPE = "eventType";
  public static final String HAPPENING = "happening";
  private static final String HAPPENING_TITLE = "WHAT'S HAPPENING";
  private static final String ON_SALE = "sale";
  private static final String ON_SALE_TITLE = "ON SALE";
  private static final String TAG = GotixEventFragment.class.getSimpleName();
  private EventAdapterDelegate eventAdapterDelegate;
  private List<Event> eventData;
  private String eventType;
  private boolean fromRefresh;
  
  public static GotixEventFragment happeningEventInstance()
  {
    return newInstance("happening", "WHAT'S HAPPENING");
  }
  
  private static GotixEventFragment newInstance(String paramString1, String paramString2)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("eventType", paramString1);
    paramString1 = new GotixEventFragment();
    paramString1.setTitle(paramString2);
    paramString1.setArguments(localBundle);
    return paramString1;
  }
  
  public static GotixEventFragment onSaleEventInstance()
  {
    return newInstance("sale", "ON SALE");
  }
  
  protected void fetchData()
  {
    if ("happening".equals(this.eventType)) {
      ((GotixEventPresenter)getPresenter()).getPendingReview();
    }
    ((GotixEventPresenter)getPresenter()).atempSearchEvent(this.eventType);
  }
  
  protected void initAdapter()
  {
    if ((this.eventAdapterDelegate == null) || (this.eventData == null))
    {
      this.eventData = new ArrayList();
      this.eventAdapterDelegate = new EventAdapterDelegate(getActivity(), this.eventData, this.mainNavigationListener);
    }
    this.recyclerView.setAdapter(this.eventAdapterDelegate);
  }
  
  protected void loadMore()
  {
    if (this.seamlessFetchValid) {
      ((GotixEventPresenter)getPresenter()).loadMoreEventList(this.eventType);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.eventType = getArguments().getString("eventType", "sale");
  }
  
  protected void refreshData()
  {
    this.fromRefresh = true;
    ((GotixEventPresenter)getPresenter()).atempSearchEvent(this.eventType);
  }
  
  public void setEventList(List<Event> paramList)
  {
    hideProgressBar();
    if ((!paramList.isEmpty()) && (this.eventData.isEmpty()))
    {
      this.eventData.addAll(paramList);
      this.eventAdapterDelegate.notifyDataSetChanged();
    }
    for (;;)
    {
      swipeRefreshLayoutVisibility(false);
      return;
      if (this.fromRefresh)
      {
        this.fromRefresh = false;
        handleRefreshData(paramList, this.eventData, this.eventAdapterDelegate);
      }
      else if (!paramList.isEmpty())
      {
        handleUpdatedData(paramList, this.eventData, this.eventAdapterDelegate);
      }
      else if (this.eventAdapterDelegate != null)
      {
        this.eventAdapterDelegate.enableFooter(false);
        this.eventAdapterDelegate.notifyDataSetChanged();
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixEventFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */