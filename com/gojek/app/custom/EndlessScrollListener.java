package com.gojek.app.custom;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public abstract class EndlessScrollListener
  implements AbsListView.OnScrollListener
{
  private static final String TAG = "endless_scroll";
  private int currentPage = 0;
  private boolean loading = true;
  private int previousTotalItemCount = 0;
  private int startingPageIndex = 0;
  private int visibleThreshold = 1;
  
  public EndlessScrollListener() {}
  
  public EndlessScrollListener(int paramInt)
  {
    this.visibleThreshold = paramInt;
  }
  
  protected abstract void onLoadMore(int paramInt);
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt3 == 1) || (paramInt3 == 2)) {}
    do
    {
      return;
      if (paramInt3 < this.previousTotalItemCount)
      {
        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = paramInt3;
        if (paramInt3 == 0) {
          this.loading = true;
        }
      }
      if ((this.loading) && (paramInt3 > this.previousTotalItemCount))
      {
        this.loading = false;
        this.previousTotalItemCount = paramInt3;
        this.currentPage += 1;
      }
    } while ((this.loading) || (paramInt3 - paramInt2 > this.visibleThreshold + paramInt1));
    onLoadMore(this.currentPage);
    this.loading = true;
  }
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/custom/EndlessScrollListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */