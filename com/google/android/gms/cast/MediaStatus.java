package com.google.android.gms.cast;

import com.google.android.gms.internal.ik;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaStatus
{
  public static final long COMMAND_PAUSE = 1L;
  public static final long COMMAND_SEEK = 2L;
  public static final long COMMAND_SET_VOLUME = 4L;
  public static final long COMMAND_SKIP_BACKWARD = 32L;
  public static final long COMMAND_SKIP_FORWARD = 16L;
  public static final long COMMAND_TOGGLE_MUTE = 8L;
  public static final int IDLE_REASON_CANCELED = 2;
  public static final int IDLE_REASON_ERROR = 4;
  public static final int IDLE_REASON_FINISHED = 1;
  public static final int IDLE_REASON_INTERRUPTED = 3;
  public static final int IDLE_REASON_NONE = 0;
  public static final int PLAYER_STATE_BUFFERING = 4;
  public static final int PLAYER_STATE_IDLE = 1;
  public static final int PLAYER_STATE_PAUSED = 3;
  public static final int PLAYER_STATE_PLAYING = 2;
  public static final int PLAYER_STATE_UNKNOWN = 0;
  private double FA;
  private boolean FB;
  private long[] FC;
  private JSONObject Fl;
  private MediaInfo Fm;
  private long Fu;
  private double Fv;
  private int Fw;
  private int Fx;
  private long Fy;
  private long Fz;
  
  public MediaStatus(JSONObject paramJSONObject)
    throws JSONException
  {
    a(paramJSONObject, 0);
  }
  
  public int a(JSONObject paramJSONObject, int paramInt)
    throws JSONException
  {
    int i2 = 2;
    int i1 = 0;
    int n = 1;
    long l = paramJSONObject.getLong("mediaSessionId");
    if (l != this.Fu) {
      this.Fu = l;
    }
    for (int j = 1;; j = 0)
    {
      int k = j;
      Object localObject;
      int i;
      if (paramJSONObject.has("playerState"))
      {
        localObject = paramJSONObject.getString("playerState");
        if (!((String)localObject).equals("IDLE")) {
          break label464;
        }
        i = 1;
      }
      for (;;)
      {
        int m = j;
        if (i != this.Fw)
        {
          this.Fw = i;
          m = j | 0x2;
        }
        k = m;
        if (i == 1)
        {
          k = m;
          if (paramJSONObject.has("idleReason"))
          {
            localObject = paramJSONObject.getString("idleReason");
            if (!((String)localObject).equals("CANCELLED")) {
              break label512;
            }
            i = i2;
          }
        }
        for (;;)
        {
          label140:
          k = m;
          if (i != this.Fx)
          {
            this.Fx = i;
            k = m | 0x2;
          }
          i = k;
          double d;
          if (paramJSONObject.has("playbackRate"))
          {
            d = paramJSONObject.getDouble("playbackRate");
            i = k;
            if (this.Fv != d)
            {
              this.Fv = d;
              i = k | 0x2;
            }
          }
          k = i;
          if (paramJSONObject.has("currentTime"))
          {
            k = i;
            if ((paramInt & 0x2) == 0)
            {
              l = ik.b(paramJSONObject.getDouble("currentTime"));
              k = i;
              if (l != this.Fy)
              {
                this.Fy = l;
                k = i | 0x2;
              }
            }
          }
          j = k;
          if (paramJSONObject.has("supportedMediaCommands"))
          {
            l = paramJSONObject.getLong("supportedMediaCommands");
            j = k;
            if (l != this.Fz)
            {
              this.Fz = l;
              j = k | 0x2;
            }
          }
          i = j;
          if (paramJSONObject.has("volume"))
          {
            i = j;
            if ((paramInt & 0x1) == 0)
            {
              localObject = paramJSONObject.getJSONObject("volume");
              d = ((JSONObject)localObject).getDouble("level");
              paramInt = j;
              if (d != this.FA)
              {
                this.FA = d;
                paramInt = j | 0x2;
              }
              boolean bool = ((JSONObject)localObject).getBoolean("muted");
              i = paramInt;
              if (bool != this.FB)
              {
                this.FB = bool;
                i = paramInt | 0x2;
              }
            }
          }
          if (paramJSONObject.has("activeTrackIds"))
          {
            JSONArray localJSONArray = paramJSONObject.getJSONArray("activeTrackIds");
            k = localJSONArray.length();
            localObject = new long[k];
            paramInt = 0;
            for (;;)
            {
              if (paramInt < k)
              {
                localObject[paramInt] = localJSONArray.getLong(paramInt);
                paramInt += 1;
                continue;
                label464:
                if (((String)localObject).equals("PLAYING"))
                {
                  i = 2;
                  break;
                }
                if (((String)localObject).equals("PAUSED"))
                {
                  i = 3;
                  break;
                }
                if (!((String)localObject).equals("BUFFERING")) {
                  break label773;
                }
                i = 4;
                break;
                label512:
                if (((String)localObject).equals("INTERRUPTED"))
                {
                  i = 3;
                  break label140;
                }
                if (((String)localObject).equals("FINISHED"))
                {
                  i = 1;
                  break label140;
                }
                if (!((String)localObject).equals("ERROR")) {
                  break label767;
                }
                i = 4;
                break label140;
              }
            }
            if (this.FC == null) {
              paramInt = n;
            }
          }
          for (;;)
          {
            if (paramInt != 0) {
              this.FC = ((long[])localObject);
            }
            j = paramInt;
            for (;;)
            {
              paramInt = i;
              if (j != 0)
              {
                this.FC = ((long[])localObject);
                paramInt = i | 0x2;
              }
              i = paramInt;
              if (paramJSONObject.has("customData"))
              {
                this.Fl = paramJSONObject.getJSONObject("customData");
                i = paramInt | 0x2;
              }
              paramInt = i;
              if (paramJSONObject.has("media"))
              {
                paramJSONObject = paramJSONObject.getJSONObject("media");
                this.Fm = new MediaInfo(paramJSONObject);
                i |= 0x2;
                paramInt = i;
                if (paramJSONObject.has("metadata")) {
                  paramInt = i | 0x4;
                }
              }
              return paramInt;
              paramInt = n;
              if (this.FC.length != k) {
                break;
              }
              j = 0;
              for (;;)
              {
                if (j >= k) {
                  break label762;
                }
                paramInt = n;
                if (this.FC[j] != localObject[j]) {
                  break;
                }
                j += 1;
              }
              if (this.FC != null)
              {
                j = 1;
                localObject = null;
              }
              else
              {
                localObject = null;
                j = i1;
              }
            }
            label762:
            paramInt = 0;
          }
          label767:
          i = 0;
        }
        label773:
        i = 0;
      }
    }
  }
  
  public long fw()
  {
    return this.Fu;
  }
  
  public long[] getActiveTrackIds()
  {
    return this.FC;
  }
  
  public JSONObject getCustomData()
  {
    return this.Fl;
  }
  
  public int getIdleReason()
  {
    return this.Fx;
  }
  
  public MediaInfo getMediaInfo()
  {
    return this.Fm;
  }
  
  public double getPlaybackRate()
  {
    return this.Fv;
  }
  
  public int getPlayerState()
  {
    return this.Fw;
  }
  
  public long getStreamPosition()
  {
    return this.Fy;
  }
  
  public double getStreamVolume()
  {
    return this.FA;
  }
  
  public boolean isMediaCommandSupported(long paramLong)
  {
    return (this.Fz & paramLong) != 0L;
  }
  
  public boolean isMute()
  {
    return this.FB;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/MediaStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */