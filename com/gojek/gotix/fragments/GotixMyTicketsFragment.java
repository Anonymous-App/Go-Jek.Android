package com.gojek.gotix.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gotix.Order;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.string;
import com.gojek.gotix.adapters.MyTicketsAdapterDelegate;
import com.gojek.gotix.presenter.GotixOrderPresenter;
import com.jakewharton.rxbinding.view.RxView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import java.util.ArrayList;
import java.util.List;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.FontFaceHelper;
import nucleus.factory.RequiresPresenter;
import rx.Observable;

@RequiresPresenter(GotixOrderPresenter.class)
public class GotixMyTicketsFragment
  extends GotixHomeFragment<GotixOrderPresenter>
  implements GotixViewBinder
{
  public static final String MY_TICKETS_TITLE = "MY TICKETS";
  private static final String TAG = GotixMyTicketsFragment.class.getSimpleName();
  protected ImageView emptyAction;
  protected TextView emptyMessage;
  protected LinearLayout emptyView;
  private boolean fromRefresh;
  private MyTicketsAdapterDelegate myTicketsAdapterDelegate;
  private List<Order> myTicketsData;
  
  public static GotixMyTicketsFragment newInstance()
  {
    Bundle localBundle = new Bundle();
    GotixMyTicketsFragment localGotixMyTicketsFragment = new GotixMyTicketsFragment();
    localGotixMyTicketsFragment.setTitle("MY TICKETS");
    localGotixMyTicketsFragment.setArguments(localBundle);
    return localGotixMyTicketsFragment;
  }
  
  public void addClickListeners()
  {
    RxView.clicks(this.emptyAction).subscribe(GotixMyTicketsFragment..Lambda.1.lambdaFactory$(this));
  }
  
  public void bindViewById(View paramView)
  {
    this.emptyView = ((LinearLayout)paramView.findViewById(R.id.empty_view));
    this.emptyMessage = ((TextView)paramView.findViewById(R.id.empty_message));
    this.emptyAction = ((ImageView)paramView.findViewById(R.id.empty_action));
  }
  
  public void emptyOrderMyTicket()
  {
    hideProgressBar();
    this.emptyMessage.setText(getString(R.string.my_tickets_empty_message));
    this.emptyAction.setImageResource(R.drawable.gotix_button_buy_now);
    this.recyclerView.setVisibility(8);
    this.emptyView.setVisibility(0);
  }
  
  protected void fetchData()
  {
    if (BasePreferences.isLoggedIn())
    {
      showProgressBar();
      ((GotixOrderPresenter)getPresenter()).registerToken(BasePreferences.getCustomerId());
      ((GotixOrderPresenter)getPresenter()).attemptMyEventList(BasePreferences.getCustomerId());
      return;
    }
    emptyOrderMyTicket();
  }
  
  protected void initAdapter()
  {
    if ((this.myTicketsAdapterDelegate == null) || (this.myTicketsData == null))
    {
      this.myTicketsData = new ArrayList();
      this.myTicketsAdapterDelegate = new MyTicketsAdapterDelegate(getActivity(), this.myTicketsData, this.mainNavigationListener);
    }
    this.recyclerView.setAdapter(this.myTicketsAdapterDelegate);
  }
  
  protected void loadMore()
  {
    if (this.seamlessFetchValid) {
      ((GotixOrderPresenter)getPresenter()).loadMoreMyEventList(BasePreferences.getCustomerId());
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setViewBinder(this);
  }
  
  public void onResume()
  {
    super.onResume();
    fetchData();
  }
  
  protected void refreshData()
  {
    this.fromRefresh = true;
    fetchData();
  }
  
  public void setFontFace()
  {
    TypefaceHelper.typeface(this.emptyMessage, FontFaceHelper.getLatoFont());
  }
  
  public void setOrderList(List<Order> paramList)
  {
    hideProgressBar();
    if ((paramList.isEmpty()) && (this.myTicketsData.isEmpty())) {
      emptyOrderMyTicket();
    }
    for (;;)
    {
      swipeRefreshLayoutVisibility(false);
      return;
      if ((!paramList.isEmpty()) && (this.myTicketsData.isEmpty()))
      {
        showOrderMyTicket();
        this.myTicketsData.addAll(paramList);
        this.myTicketsAdapterDelegate.notifyDataSetChanged();
      }
      else if (this.fromRefresh)
      {
        this.fromRefresh = false;
        handleRefreshData(paramList, this.myTicketsData, this.myTicketsAdapterDelegate);
      }
      else if (!paramList.isEmpty())
      {
        handleUpdatedData(paramList, this.myTicketsData, this.myTicketsAdapterDelegate);
      }
      else if (this.myTicketsAdapterDelegate != null)
      {
        this.myTicketsAdapterDelegate.enableFooter(false);
        this.myTicketsAdapterDelegate.notifyDataSetChanged();
      }
    }
  }
  
  public void showOrderMyTicket()
  {
    this.recyclerView.setVisibility(0);
    this.emptyView.setVisibility(8);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixMyTicketsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */