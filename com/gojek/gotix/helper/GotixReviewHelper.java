package com.gojek.gotix.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.gojek.gotix.activities.GotixReviewActivity;
import com.gojek.gotix.network.model.Driver;
import com.gojek.gotix.network.model.PendingReview;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GotixReviewHelper
{
  private static final String ORDER_ID_KEY = "orderIdKey";
  private Context originActivity;
  private List<PendingReview> pendingReviewList;
  
  private GotixReviewHelper(Builder paramBuilder)
  {
    this.pendingReviewList = paramBuilder.pendingReviewList;
    this.originActivity = paramBuilder.originActivity;
  }
  
  private void createNewIntent(int paramInt)
  {
    Intent localIntent = new Intent(this.originActivity, GotixReviewActivity.class);
    localIntent.setFlags(134217728);
    localIntent.putExtra("orderIdKey", paramInt);
    this.originActivity.startActivity(localIntent);
  }
  
  private void saveDriverData(Driver paramDriver, int paramInt)
  {
    GotixData.saveDriver(paramDriver, paramInt);
  }
  
  public void show()
  {
    if (!this.pendingReviewList.isEmpty())
    {
      Collections.reverse(this.pendingReviewList);
      Iterator localIterator = this.pendingReviewList.iterator();
      while (localIterator.hasNext())
      {
        PendingReview localPendingReview = (PendingReview)localIterator.next();
        if (!GotixData.hasReview(localPendingReview.getOrderId()))
        {
          saveDriverData(localPendingReview.getDriver(), localPendingReview.getOrderId());
          createNewIntent(localPendingReview.getOrderId());
        }
      }
    }
  }
  
  public static class Builder
  {
    private Context originActivity;
    private List<PendingReview> pendingReviewList;
    
    public Builder(List<PendingReview> paramList)
    {
      this.pendingReviewList = paramList;
    }
    
    public GotixReviewHelper build()
    {
      return new GotixReviewHelper(this, null);
    }
    
    public Builder context(Activity paramActivity)
    {
      this.originActivity = paramActivity;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/helper/GotixReviewHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */