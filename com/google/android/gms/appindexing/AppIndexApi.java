package com.google.android.gms.appindexing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

public abstract interface AppIndexApi
{
  public abstract PendingResult<Status> view(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Intent paramIntent, String paramString, Uri paramUri, List<AppIndexingLink> paramList);
  
  public abstract PendingResult<Status> view(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Uri paramUri1, String paramString, Uri paramUri2, List<AppIndexingLink> paramList);
  
  public abstract PendingResult<Status> viewEnd(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Intent paramIntent);
  
  public abstract PendingResult<Status> viewEnd(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Uri paramUri);
  
  public static final class AppIndexingLink
  {
    public final Uri appIndexingUrl;
    public final int viewId;
    public final Uri webUrl;
    
    public AppIndexingLink(Uri paramUri1, Uri paramUri2, View paramView)
    {
      this.appIndexingUrl = paramUri1;
      this.webUrl = paramUri2;
      this.viewId = paramView.getId();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/appindexing/AppIndexApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */