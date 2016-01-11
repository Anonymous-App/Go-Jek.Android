package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class iq
  extends ii
{
  private static final long Hb = TimeUnit.HOURS.toMillis(24L);
  private static final long Hc = TimeUnit.HOURS.toMillis(24L);
  private static final long Hd = TimeUnit.HOURS.toMillis(24L);
  private static final long He = TimeUnit.SECONDS.toMillis(1L);
  private static final String NAMESPACE = ik.aG("com.google.cast.media");
  private long Hf;
  private MediaStatus Hg;
  private final it Hh = new it(Hc);
  private final it Hi;
  private final it Hj;
  private final it Hk;
  private final it Hl;
  private final it Hm;
  private final it Hn;
  private final it Ho;
  private final it Hp;
  private final it Hq;
  private final List<it> Hr = new ArrayList();
  private final Runnable Hs = new a(null);
  private boolean Ht;
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  
  public iq()
  {
    this(null);
  }
  
  public iq(String paramString)
  {
    super(NAMESPACE, "MediaControlChannel", paramString);
    this.Hr.add(this.Hh);
    this.Hi = new it(Hb);
    this.Hr.add(this.Hi);
    this.Hj = new it(Hb);
    this.Hr.add(this.Hj);
    this.Hk = new it(Hb);
    this.Hr.add(this.Hk);
    this.Hl = new it(Hd);
    this.Hr.add(this.Hl);
    this.Hm = new it(Hb);
    this.Hr.add(this.Hm);
    this.Hn = new it(Hb);
    this.Hr.add(this.Hn);
    this.Ho = new it(Hb);
    this.Hr.add(this.Ho);
    this.Hp = new it(Hb);
    this.Hr.add(this.Hp);
    this.Hq = new it(Hb);
    this.Hr.add(this.Hq);
    fT();
  }
  
  private void H(boolean paramBoolean)
  {
    if (this.Ht != paramBoolean)
    {
      this.Ht = paramBoolean;
      if (paramBoolean) {
        this.mHandler.postDelayed(this.Hs, He);
      }
    }
    else
    {
      return;
    }
    this.mHandler.removeCallbacks(this.Hs);
  }
  
  private void a(long paramLong, JSONObject paramJSONObject)
    throws JSONException
  {
    int k = 1;
    boolean bool = this.Hh.p(paramLong);
    int j;
    if ((this.Hl.fV()) && (!this.Hl.p(paramLong)))
    {
      i = 1;
      if (this.Hm.fV())
      {
        j = k;
        if (!this.Hm.p(paramLong)) {}
      }
      else
      {
        if ((!this.Hn.fV()) || (this.Hn.p(paramLong))) {
          break label235;
        }
        j = k;
      }
      label87:
      if (i == 0) {
        break label257;
      }
    }
    label235:
    label257:
    for (int i = 2;; i = 0)
    {
      k = i;
      if (j != 0) {
        k = i | 0x1;
      }
      if ((bool) || (this.Hg == null))
      {
        this.Hg = new MediaStatus(paramJSONObject);
        this.Hf = SystemClock.elapsedRealtime();
      }
      for (i = 7;; i = this.Hg.a(paramJSONObject, k))
      {
        if ((i & 0x1) != 0)
        {
          this.Hf = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((i & 0x2) != 0)
        {
          this.Hf = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((i & 0x4) != 0) {
          onMetadataUpdated();
        }
        paramJSONObject = this.Hr.iterator();
        while (paramJSONObject.hasNext()) {
          ((it)paramJSONObject.next()).d(paramLong, 0);
        }
        i = 0;
        break;
        j = 0;
        break label87;
      }
      return;
    }
  }
  
  private void fT()
  {
    H(false);
    this.Hf = 0L;
    this.Hg = null;
    this.Hh.clear();
    this.Hl.clear();
    this.Hm.clear();
  }
  
  public long a(is paramis)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Ho.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "GET_STATUS");
      if (this.Hg != null) {
        localJSONObject.put("mediaSessionId", this.Hg.fw());
      }
      if (!(localJSONObject instanceof JSONObject)) {}
      for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
      {
        a(paramis, l, null);
        return l;
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
  }
  
  public long a(is paramis, double paramDouble, JSONObject paramJSONObject)
    throws IOException, IllegalStateException, IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hm.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SET_VOLUME");
      localJSONObject.put("mediaSessionId", fw());
      paramis = new JSONObject();
      paramis.put("level", paramDouble);
      localJSONObject.put("volume", paramis);
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public long a(is paramis, long paramLong, int paramInt, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    localJSONObject = new JSONObject();
    long l = fz();
    this.Hl.a(l, paramis);
    H(true);
    for (;;)
    {
      try
      {
        localJSONObject.put("requestId", l);
        localJSONObject.put("type", "SEEK");
        localJSONObject.put("mediaSessionId", fw());
        localJSONObject.put("currentTime", ik.o(paramLong));
        if (paramInt != 1) {
          continue;
        }
        localJSONObject.put("resumeState", "PLAYBACK_START");
        if (paramJSONObject != null) {
          localJSONObject.put("customData", paramJSONObject);
        }
      }
      catch (JSONException paramis)
      {
        continue;
        paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
        continue;
      }
      if ((localJSONObject instanceof JSONObject)) {
        continue;
      }
      paramis = localJSONObject.toString();
      a(paramis, l, null);
      return l;
      if (paramInt == 2) {
        localJSONObject.put("resumeState", "PLAYBACK_PAUSE");
      }
    }
  }
  
  public long a(is paramis, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, long[] paramArrayOfLong, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hh.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "LOAD");
      localJSONObject.put("media", paramMediaInfo.bK());
      localJSONObject.put("autoplay", paramBoolean);
      localJSONObject.put("currentTime", ik.o(paramLong));
      if ((paramArrayOfLong != null) && (paramArrayOfLong.length > 0))
      {
        paramis = new JSONArray();
        int i = 0;
        while (i < paramArrayOfLong.length)
        {
          paramis.put(i, paramArrayOfLong[i]);
          i += 1;
        }
        localJSONObject.put("activeTrackIds", paramis);
      }
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public long a(is paramis, TextTrackStyle paramTextTrackStyle)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hq.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "EDIT_TRACKS_INFO");
      if (paramTextTrackStyle != null) {
        localJSONObject.put("textTrackStyle", paramTextTrackStyle.bK());
      }
      localJSONObject.put("mediaSessionId", fw());
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public long a(is paramis, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hi.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PAUSE");
      localJSONObject.put("mediaSessionId", fw());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public long a(is paramis, boolean paramBoolean, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hn.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SET_VOLUME");
      localJSONObject.put("mediaSessionId", fw());
      paramis = new JSONObject();
      paramis.put("muted", paramBoolean);
      localJSONObject.put("volume", paramis);
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public long a(is paramis, long[] paramArrayOfLong)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hp.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "EDIT_TRACKS_INFO");
      localJSONObject.put("mediaSessionId", fw());
      paramis = new JSONArray();
      int i = 0;
      while (i < paramArrayOfLong.length)
      {
        paramis.put(i, paramArrayOfLong[i]);
        i += 1;
      }
      localJSONObject.put("activeTrackIds", paramis);
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public final void aD(String paramString)
  {
    this.Go.b("message received: %s", new Object[] { paramString });
    Object localObject2;
    long l;
    try
    {
      Object localObject1 = JSONObjectInstrumentation.init(paramString);
      localObject2 = ((JSONObject)localObject1).getString("type");
      l = ((JSONObject)localObject1).optLong("requestId", -1L);
      if (((String)localObject2).equals("MEDIA_STATUS"))
      {
        localObject1 = ((JSONObject)localObject1).getJSONArray("status");
        if (((JSONArray)localObject1).length() > 0)
        {
          a(l, ((JSONArray)localObject1).getJSONObject(0));
          return;
        }
        this.Hg = null;
        onStatusUpdated();
        onMetadataUpdated();
        this.Ho.d(l, 0);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      this.Go.d("Message is malformed (%s); ignoring: %s", new Object[] { localJSONException.getMessage(), paramString });
      return;
    }
    JSONObject localJSONObject;
    if (((String)localObject2).equals("INVALID_PLAYER_STATE"))
    {
      this.Go.d("received unexpected error: Invalid Player State.", new Object[0]);
      localJSONObject = localJSONException.optJSONObject("customData");
      localObject2 = this.Hr.iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((it)((Iterator)localObject2).next()).b(l, 2100, localJSONObject);
      }
    }
    if (((String)localObject2).equals("LOAD_FAILED"))
    {
      localJSONObject = localJSONObject.optJSONObject("customData");
      this.Hh.b(l, 2100, localJSONObject);
      return;
    }
    if (((String)localObject2).equals("LOAD_CANCELLED"))
    {
      localJSONObject = localJSONObject.optJSONObject("customData");
      this.Hh.b(l, 2101, localJSONObject);
      return;
    }
    if (((String)localObject2).equals("INVALID_REQUEST"))
    {
      this.Go.d("received unexpected error: Invalid Request.", new Object[0]);
      localJSONObject = localJSONObject.optJSONObject("customData");
      localObject2 = this.Hr.iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((it)((Iterator)localObject2).next()).b(l, 2100, localJSONObject);
      }
    }
  }
  
  public long b(is paramis, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hk.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "STOP");
      localJSONObject.put("mediaSessionId", fw());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public void b(long paramLong, int paramInt)
  {
    Iterator localIterator = this.Hr.iterator();
    while (localIterator.hasNext()) {
      ((it)localIterator.next()).d(paramLong, paramInt);
    }
  }
  
  public long c(is paramis, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = fz();
    this.Hj.a(l, paramis);
    H(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PLAY");
      localJSONObject.put("mediaSessionId", fw());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramis)
    {
      for (;;) {}
    }
    if (!(localJSONObject instanceof JSONObject)) {}
    for (paramis = localJSONObject.toString();; paramis = JSONObjectInstrumentation.toString((JSONObject)localJSONObject))
    {
      a(paramis, l, null);
      return l;
    }
  }
  
  public void fA()
  {
    fT();
  }
  
  public long fw()
    throws IllegalStateException
  {
    if (this.Hg == null) {
      throw new IllegalStateException("No current media session");
    }
    return this.Hg.fw();
  }
  
  public long getApproximateStreamPosition()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo == null) {}
    while (this.Hf == 0L) {
      return 0L;
    }
    double d = this.Hg.getPlaybackRate();
    long l3 = this.Hg.getStreamPosition();
    int i = this.Hg.getPlayerState();
    if ((d == 0.0D) || (i != 2)) {
      return l3;
    }
    long l1 = SystemClock.elapsedRealtime() - this.Hf;
    if (l1 < 0L) {
      l1 = 0L;
    }
    for (;;)
    {
      if (l1 == 0L) {
        return l3;
      }
      long l2 = localMediaInfo.getStreamDuration();
      l1 = l3 + (l1 * d);
      if ((l2 > 0L) && (l1 > l2)) {
        l1 = l2;
      }
      for (;;)
      {
        return l1;
        if (l1 < 0L) {
          l1 = 0L;
        }
      }
    }
  }
  
  public MediaInfo getMediaInfo()
  {
    if (this.Hg == null) {
      return null;
    }
    return this.Hg.getMediaInfo();
  }
  
  public MediaStatus getMediaStatus()
  {
    return this.Hg;
  }
  
  public long getStreamDuration()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo != null) {
      return localMediaInfo.getStreamDuration();
    }
    return 0L;
  }
  
  protected void onMetadataUpdated() {}
  
  protected void onStatusUpdated() {}
  
  private class a
    implements Runnable
  {
    private a() {}
    
    public void run()
    {
      boolean bool = false;
      iq.a(iq.this, false);
      long l = SystemClock.elapsedRealtime();
      ??? = iq.a(iq.this).iterator();
      while (((Iterator)???).hasNext()) {
        ((it)((Iterator)???).next()).e(l, 2102);
      }
      for (;;)
      {
        synchronized (it.Hz)
        {
          Iterator localIterator = iq.a(iq.this).iterator();
          if (localIterator.hasNext())
          {
            if (((it)localIterator.next()).fV()) {
              bool = true;
            }
          }
          else
          {
            iq.b(iq.this, bool);
            return;
          }
        }
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/iq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */