package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

public abstract interface SessionsApi
{
  public abstract PendingResult<Status> insertSession(GoogleApiClient paramGoogleApiClient, SessionInsertRequest paramSessionInsertRequest);
  
  public abstract PendingResult<SessionReadResult> readSession(GoogleApiClient paramGoogleApiClient, SessionReadRequest paramSessionReadRequest);
  
  public abstract PendingResult<Status> registerForSessions(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  public abstract PendingResult<Status> startSession(GoogleApiClient paramGoogleApiClient, Session paramSession);
  
  public abstract PendingResult<SessionStopResult> stopSession(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<Status> unregisterForSessions(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent);
  
  public static class ViewIntentBuilder
  {
    private String Ss;
    private Session St;
    private boolean Su = false;
    private final Context mContext;
    
    public ViewIntentBuilder(Context paramContext)
    {
      this.mContext = paramContext;
    }
    
    private Intent i(Intent paramIntent)
    {
      if (this.Ss == null) {}
      Intent localIntent;
      ResolveInfo localResolveInfo;
      do
      {
        return paramIntent;
        localIntent = new Intent(paramIntent).setPackage(this.Ss);
        localResolveInfo = this.mContext.getPackageManager().resolveActivity(localIntent, 0);
      } while (localResolveInfo == null);
      paramIntent = localResolveInfo.activityInfo.name;
      localIntent.setComponent(new ComponentName(this.Ss, paramIntent));
      return localIntent;
    }
    
    public Intent build()
    {
      if (this.St != null) {}
      for (boolean bool = true;; bool = false)
      {
        o.a(bool, "Session must be set");
        Intent localIntent = new Intent("vnd.google.fitness.VIEW");
        localIntent.setType(Session.getMimeType(this.St.getActivity()));
        c.a(this.St, localIntent, "vnd.google.fitness.session");
        if (!this.Su) {
          this.Ss = this.St.getAppPackageName();
        }
        return i(localIntent);
      }
    }
    
    public ViewIntentBuilder setPreferredApplication(String paramString)
    {
      this.Ss = paramString;
      this.Su = true;
      return this;
    }
    
    public ViewIntentBuilder setSession(Session paramSession)
    {
      this.St = paramSession;
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/SessionsApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */