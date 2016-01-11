package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ij;
import com.google.android.gms.internal.iq;
import com.google.android.gms.internal.ir;
import com.google.android.gms.internal.is;
import java.io.IOException;
import org.json.JSONObject;

public class RemoteMediaPlayer
  implements Cast.MessageReceivedCallback
{
  public static final int RESUME_STATE_PAUSE = 2;
  public static final int RESUME_STATE_PLAY = 1;
  public static final int RESUME_STATE_UNCHANGED = 0;
  public static final int STATUS_CANCELED = 2101;
  public static final int STATUS_FAILED = 2100;
  public static final int STATUS_REPLACED = 2103;
  public static final int STATUS_SUCCEEDED = 0;
  public static final int STATUS_TIMED_OUT = 2102;
  private final iq FG = new iq()
  {
    protected void onMetadataUpdated()
    {
      RemoteMediaPlayer.b(RemoteMediaPlayer.this);
    }
    
    protected void onStatusUpdated()
    {
      RemoteMediaPlayer.a(RemoteMediaPlayer.this);
    }
  };
  private final a FH = new a();
  private OnMetadataUpdatedListener FI;
  private OnStatusUpdatedListener FJ;
  private final Object mw = new Object();
  
  public RemoteMediaPlayer()
  {
    this.FG.a(this.FH);
  }
  
  private void onMetadataUpdated()
  {
    if (this.FI != null) {
      this.FI.onMetadataUpdated();
    }
  }
  
  private void onStatusUpdated()
  {
    if (this.FJ != null) {
      this.FJ.onStatusUpdated();
    }
  }
  
  public long getApproximateStreamPosition()
  {
    synchronized (this.mw)
    {
      long l = this.FG.getApproximateStreamPosition();
      return l;
    }
  }
  
  public MediaInfo getMediaInfo()
  {
    synchronized (this.mw)
    {
      MediaInfo localMediaInfo = this.FG.getMediaInfo();
      return localMediaInfo;
    }
  }
  
  public MediaStatus getMediaStatus()
  {
    synchronized (this.mw)
    {
      MediaStatus localMediaStatus = this.FG.getMediaStatus();
      return localMediaStatus;
    }
  }
  
  public String getNamespace()
  {
    return this.FG.getNamespace();
  }
  
  public long getStreamDuration()
  {
    synchronized (this.mw)
    {
      long l = this.FG.getStreamDuration();
      return l;
    }
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo)
  {
    return load(paramGoogleApiClient, paramMediaInfo, true, 0L, null, null);
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, 0L, null, null);
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, paramLong, null, null);
  }
  
  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, JSONObject paramJSONObject)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, paramLong, null, paramJSONObject);
  }
  
  public PendingResult<MediaChannelResult> load(final GoogleApiClient paramGoogleApiClient, final MediaInfo paramMediaInfo, final boolean paramBoolean, final long paramLong, long[] paramArrayOfLong, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramMediaInfo, paramBoolean, paramLong, paramJSONObject, this.FS);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public void onMessageReceived(CastDevice paramCastDevice, String paramString1, String paramString2)
  {
    this.FG.aD(paramString2);
  }
  
  public PendingResult<MediaChannelResult> pause(GoogleApiClient paramGoogleApiClient)
  {
    return pause(paramGoogleApiClient, null);
  }
  
  public PendingResult<MediaChannelResult> pause(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> play(GoogleApiClient paramGoogleApiClient)
  {
    return play(paramGoogleApiClient, null);
  }
  
  public PendingResult<MediaChannelResult> play(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).c(this.Gb, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> requestStatus(final GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong)
  {
    return seek(paramGoogleApiClient, paramLong, 0, null);
  }
  
  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong, int paramInt)
  {
    return seek(paramGoogleApiClient, paramLong, paramInt, null);
  }
  
  public PendingResult<MediaChannelResult> seek(final GoogleApiClient paramGoogleApiClient, final long paramLong, int paramInt, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramLong, paramJSONObject, this.FS);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> setActiveMediaTracks(final GoogleApiClient paramGoogleApiClient, final long[] paramArrayOfLong)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramArrayOfLong);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener paramOnMetadataUpdatedListener)
  {
    this.FI = paramOnMetadataUpdatedListener;
  }
  
  public void setOnStatusUpdatedListener(OnStatusUpdatedListener paramOnStatusUpdatedListener)
  {
    this.FJ = paramOnStatusUpdatedListener;
  }
  
  public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return setStreamMute(paramGoogleApiClient, paramBoolean, null);
  }
  
  public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient paramGoogleApiClient, final boolean paramBoolean, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramBoolean, paramJSONObject);
          }
          catch (IllegalStateException localIllegalStateException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
    throws IllegalArgumentException
  {
    return setStreamVolume(paramGoogleApiClient, paramDouble, null);
  }
  
  public PendingResult<MediaChannelResult> setStreamVolume(final GoogleApiClient paramGoogleApiClient, final double paramDouble, JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramDouble, this.FS);
          }
          catch (IllegalStateException localIllegalStateException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> setTextTrackStyle(final GoogleApiClient paramGoogleApiClient, final TextTrackStyle paramTextTrackStyle)
  {
    if (paramTextTrackStyle == null) {
      throw new IllegalArgumentException("trackStyle cannot be null");
    }
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.Gb, paramTextTrackStyle);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public PendingResult<MediaChannelResult> stop(GoogleApiClient paramGoogleApiClient)
  {
    return stop(paramGoogleApiClient, null);
  }
  
  public PendingResult<MediaChannelResult> stop(final GoogleApiClient paramGoogleApiClient, final JSONObject paramJSONObject)
  {
    paramGoogleApiClient.b(new b()
    {
      protected void a(ij arg1)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(paramGoogleApiClient);
          try
          {
            RemoteMediaPlayer.e(RemoteMediaPlayer.this).b(this.Gb, paramJSONObject);
          }
          catch (IOException localIOException)
          {
            for (;;)
            {
              b(l(new Status(2100)));
              RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
            }
            localObject1 = finally;
            throw ((Throwable)localObject1);
          }
          finally
          {
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          return;
        }
      }
    });
  }
  
  public static abstract interface MediaChannelResult
    extends Result
  {
    public abstract JSONObject getCustomData();
  }
  
  public static abstract interface OnMetadataUpdatedListener
  {
    public abstract void onMetadataUpdated();
  }
  
  public static abstract interface OnStatusUpdatedListener
  {
    public abstract void onStatusUpdated();
  }
  
  private class a
    implements ir
  {
    private GoogleApiClient FX;
    private long FY = 0L;
    
    public a() {}
    
    public void a(String paramString1, String paramString2, long paramLong, String paramString3)
      throws IOException
    {
      if (this.FX == null) {
        throw new IOException("No GoogleApiClient available");
      }
      Cast.CastApi.sendMessage(this.FX, paramString1, paramString2).setResultCallback(new a(paramLong));
    }
    
    public void b(GoogleApiClient paramGoogleApiClient)
    {
      this.FX = paramGoogleApiClient;
    }
    
    public long fx()
    {
      long l = this.FY + 1L;
      this.FY = l;
      return l;
    }
    
    private final class a
      implements ResultCallback<Status>
    {
      private final long FZ;
      
      a(long paramLong)
      {
        this.FZ = paramLong;
      }
      
      public void k(Status paramStatus)
      {
        if (!paramStatus.isSuccess()) {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).b(this.FZ, paramStatus.getStatusCode());
        }
      }
    }
  }
  
  private static abstract class b
    extends Cast.a<RemoteMediaPlayer.MediaChannelResult>
  {
    is Gb = new is()
    {
      public void a(long paramAnonymousLong, int paramAnonymousInt, JSONObject paramAnonymousJSONObject)
      {
        RemoteMediaPlayer.b.this.b(new RemoteMediaPlayer.c(new Status(paramAnonymousInt), paramAnonymousJSONObject));
      }
      
      public void n(long paramAnonymousLong)
      {
        RemoteMediaPlayer.b.this.b(RemoteMediaPlayer.b.this.l(new Status(2103)));
      }
    };
    
    public RemoteMediaPlayer.MediaChannelResult l(final Status paramStatus)
    {
      new RemoteMediaPlayer.MediaChannelResult()
      {
        public JSONObject getCustomData()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static final class c
    implements RemoteMediaPlayer.MediaChannelResult
  {
    private final Status CM;
    private final JSONObject Fl;
    
    c(Status paramStatus, JSONObject paramJSONObject)
    {
      this.CM = paramStatus;
      this.Fl = paramJSONObject;
    }
    
    public JSONObject getCustomData()
    {
      return this.Fl;
    }
    
    public Status getStatus()
    {
      return this.CM;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/RemoteMediaPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */