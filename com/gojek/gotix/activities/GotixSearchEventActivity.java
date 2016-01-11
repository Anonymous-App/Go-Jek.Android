package com.gojek.gotix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.gojek.gotix.Event;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.adapters.SearchEventAdapter;
import com.gojek.gotix.presenter.GotixSearchEventPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lib.gojek.base.components.BaseEndlessScrollListener;
import lib.gojek.base.helper.FontFaceHelper;
import nucleus.factory.RequiresPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

@RequiresPresenter(GotixSearchEventPresenter.class)
public class GotixSearchEventActivity
  extends GotixBaseActivity<GotixSearchEventPresenter>
  implements GotixSearchEventNavigationListener
{
  private static final int MIN_SEARCH_CHARACTER = 3;
  private static final int SEARCH_THROTTLE = 300;
  private CompositeSubscription compositeSubscription;
  private CardView noResultView;
  private RecyclerView recyclerView;
  private boolean seamlessFetchValid;
  private SearchEventAdapter searchEventAdapter;
  private EditText searchField;
  private ProgressBar searchLoadingBar;
  private String searchQuery;
  private List<Event> searchResults;
  
  private void bindViewById()
  {
    this.searchField = ((EditText)findViewById(R.id.search_field));
    this.searchLoadingBar = ((ProgressBar)findViewById(R.id.search_loading_bar));
    this.recyclerView = ((RecyclerView)findViewById(R.id.search_result_list));
    this.noResultView = ((CardView)findViewById(R.id.search_event_no_result));
  }
  
  private void clearAdapterData()
  {
    if (!this.searchResults.isEmpty())
    {
      int j = this.searchResults.size();
      this.searchResults.clear();
      this.searchEventAdapter.notifyItemChanged(j);
      SearchEventAdapter localSearchEventAdapter = this.searchEventAdapter;
      int i = j;
      if (this.seamlessFetchValid) {
        i = j + 1;
      }
      localSearchEventAdapter.notifyItemRangeRemoved(0, i);
    }
  }
  
  private LinearLayoutManager getLayoutManager()
  {
    return new LinearLayoutManager(this, 1, false);
  }
  
  private void initAdapter()
  {
    if ((this.searchEventAdapter == null) || (this.searchResults == null))
    {
      this.searchResults = new ArrayList();
      this.searchEventAdapter = new SearchEventAdapter(this, this.searchResults, this);
    }
    this.recyclerView.setAdapter(this.searchEventAdapter);
  }
  
  private void initCompositeSubscription()
  {
    this.compositeSubscription = new CompositeSubscription();
  }
  
  private void initRecyclerView()
  {
    LinearLayoutManager localLinearLayoutManager = getLayoutManager();
    this.recyclerView.setLayoutManager(localLinearLayoutManager);
    this.recyclerView.addOnScrollListener(new BaseEndlessScrollListener(localLinearLayoutManager)
    {
      public void onLoadMore()
      {
        if (GotixSearchEventActivity.this.seamlessFetchValid) {
          ((GotixSearchEventPresenter)GotixSearchEventActivity.this.getPresenter()).loadMoreSearchEvent(GotixSearchEventActivity.this.searchQuery);
        }
      }
    });
  }
  
  private void initSearchField()
  {
    this.searchField.setOnEditorActionListener(GotixSearchEventActivity..Lambda.1.lambdaFactory$(this));
  }
  
  private boolean isQueryValid()
  {
    return (!this.searchQuery.isEmpty()) && (isSearchCharacterLengthValid());
  }
  
  private boolean isSearchCharacterLengthValid()
  {
    return this.searchQuery.length() >= 3;
  }
  
  private void performSearch()
  {
    setSearchLoadingVisibility(true);
    ((GotixSearchEventPresenter)getPresenter()).attemptSearchEvent(this.searchQuery);
  }
  
  private void populateResults(List<Event> paramList)
  {
    int i = this.searchResults.size();
    this.searchResults.addAll(paramList);
    this.seamlessFetchValid = isSeamlessFetchAllowed(paramList.size());
    this.searchEventAdapter.enableFooter(this.seamlessFetchValid);
    this.searchEventAdapter.notifyItemChanged(i);
    this.searchEventAdapter.notifyItemRangeInserted(i, paramList.size());
  }
  
  private void resetSearchState()
  {
    clearAdapterData();
    setNoResultViewVisibility(false);
  }
  
  private void searchNoResult()
  {
    clearAdapterData();
    setNoResultViewVisibility(true);
  }
  
  private void setFontFace()
  {
    TypefaceHelper.typeface(this.searchField, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.noResultView, FontFaceHelper.getLatoFont());
  }
  
  private void setNoResultViewVisibility(boolean paramBoolean)
  {
    CardView localCardView = this.noResultView;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      localCardView.setVisibility(i);
      return;
    }
  }
  
  private void validateSearchField()
  {
    this.compositeSubscription.add(RxTextView.textChanges(this.searchField).debounce(300L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixSearchEventActivity..Lambda.2.lambdaFactory$(this), GotixSearchEventActivity..Lambda.3.lambdaFactory$(this)));
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_search_event;
  }
  
  protected boolean isSeamlessFetchAllowed(int paramInt)
  {
    return paramInt == 20;
  }
  
  public void navigateToEventDetail(Event paramEvent)
  {
    Intent localIntent = new Intent(this, GotixEventDetailActivity.class);
    if (paramEvent != null)
    {
      localIntent.putExtra("type_id", paramEvent.getEvent_id());
      startActivity(localIntent);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    bindViewById();
    setFontFace();
    initSearchField();
    initCompositeSubscription();
    initRecyclerView();
    initAdapter();
    validateSearchField();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    hideToolbarIcon();
    setTitleActionBar(getString(R.string.search_title));
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.compositeSubscription.unsubscribe();
  }
  
  public void setSearchLoadingVisibility(boolean paramBoolean)
  {
    ProgressBar localProgressBar = this.searchLoadingBar;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      localProgressBar.setVisibility(i);
      return;
    }
  }
  
  public void setSearchResult(List<Event> paramList)
  {
    if ((!this.searchResults.isEmpty()) || (!paramList.isEmpty())) {
      populateResults(paramList);
    }
    for (;;)
    {
      setSearchLoadingVisibility(false);
      return;
      searchNoResult();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixSearchEventActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */