package com.google.android.gms.fitness;

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
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.concurrent.TimeUnit;

public abstract interface HistoryApi
{
  public abstract PendingResult<Status> deleteData(GoogleApiClient paramGoogleApiClient, DataDeleteRequest paramDataDeleteRequest);
  
  public abstract PendingResult<Status> insertData(GoogleApiClient paramGoogleApiClient, DataSet paramDataSet);
  
  public abstract PendingResult<DataReadResult> readData(GoogleApiClient paramGoogleApiClient, DataReadRequest paramDataReadRequest);
  
  public static class ViewIntentBuilder
  {
    private long KS;
    private final DataType Sp;
    private DataSource Sq;
    private long Sr;
    private String Ss;
    private final Context mContext;
    
    public ViewIntentBuilder(Context paramContext, DataType paramDataType)
    {
      this.mContext = paramContext;
      this.Sp = paramDataType;
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
      boolean bool2 = true;
      if (this.KS > 0L)
      {
        bool1 = true;
        o.a(bool1, "Start time must be set");
        if (this.Sr <= this.KS) {
          break label107;
        }
      }
      label107:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        o.a(bool1, "End time must be set and after start time");
        Intent localIntent = new Intent("vnd.google.fitness.VIEW");
        localIntent.setType(DataType.getMimeType(this.Sq.getDataType()));
        localIntent.putExtra("vnd.google.fitness.start_time", this.KS);
        localIntent.putExtra("vnd.google.fitness.end_time", this.Sr);
        c.a(this.Sq, localIntent, "vnd.google.fitness.data_source");
        return i(localIntent);
        bool1 = false;
        break;
      }
    }
    
    public ViewIntentBuilder setDataSource(DataSource paramDataSource)
    {
      o.b(paramDataSource.getDataType().equals(this.Sp), "Data source %s is not for the data type %s", new Object[] { paramDataSource, this.Sp });
      this.Sq = paramDataSource;
      return this;
    }
    
    public ViewIntentBuilder setPreferredApplication(String paramString)
    {
      this.Ss = paramString;
      return this;
    }
    
    public ViewIntentBuilder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.KS = paramTimeUnit.toMillis(paramLong1);
      this.Sr = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/HistoryApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */