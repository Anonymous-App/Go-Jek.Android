package lib.gojek.base.components;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

public abstract class BaseEndlessScrollListener
  extends RecyclerView.OnScrollListener
{
  private static final int VISIBLE_THRESHOLD = 3;
  int firstVisibleItem;
  private final LinearLayoutManager linearLayoutManager;
  private boolean loadMoreAllowed;
  private boolean loading;
  private int previousTotal;
  int totalItemCount;
  int visibleItemCount;
  
  public BaseEndlessScrollListener(LinearLayoutManager paramLinearLayoutManager)
  {
    this.linearLayoutManager = paramLinearLayoutManager;
    initValue();
  }
  
  private void initValue()
  {
    this.loading = true;
    this.loadMoreAllowed = true;
  }
  
  public abstract void onLoadMore();
  
  public void onScrolled(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    super.onScrolled(paramRecyclerView, paramInt1, paramInt2);
    this.visibleItemCount = paramRecyclerView.getChildCount();
    this.totalItemCount = this.linearLayoutManager.getItemCount();
    this.firstVisibleItem = this.linearLayoutManager.findFirstVisibleItemPosition();
    if (this.totalItemCount < this.previousTotal)
    {
      this.previousTotal = 0;
      if (this.totalItemCount == 0) {
        this.loading = true;
      }
    }
    if ((this.loading) && (this.totalItemCount > this.previousTotal))
    {
      this.loading = false;
      this.previousTotal = this.totalItemCount;
    }
    if ((this.loadMoreAllowed) && (!this.loading) && (this.totalItemCount - this.visibleItemCount <= this.firstVisibleItem + 3))
    {
      onLoadMore();
      this.loading = true;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/components/BaseEndlessScrollListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */