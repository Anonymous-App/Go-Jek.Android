package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaInfo
{
  public static final int STREAM_TYPE_BUFFERED = 1;
  public static final int STREAM_TYPE_INVALID = -1;
  public static final int STREAM_TYPE_LIVE = 2;
  public static final int STREAM_TYPE_NONE = 0;
  private final String Fe;
  private int Ff;
  private String Fg;
  private MediaMetadata Fh;
  private long Fi;
  private List<MediaTrack> Fj;
  private TextTrackStyle Fk;
  private JSONObject Fl;
  
  MediaInfo(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    this.Fe = paramString;
    this.Ff = -1;
  }
  
  MediaInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.Fe = paramJSONObject.getString("contentId");
    Object localObject1 = paramJSONObject.getString("streamType");
    if ("NONE".equals(localObject1)) {
      this.Ff = 0;
    }
    Object localObject2;
    for (;;)
    {
      this.Fg = paramJSONObject.getString("contentType");
      if (paramJSONObject.has("metadata"))
      {
        localObject1 = paramJSONObject.getJSONObject("metadata");
        this.Fh = new MediaMetadata(((JSONObject)localObject1).getInt("metadataType"));
        this.Fh.c((JSONObject)localObject1);
      }
      this.Fi = ik.b(paramJSONObject.optDouble("duration", 0.0D));
      if (!paramJSONObject.has("tracks")) {
        break;
      }
      this.Fj = new ArrayList();
      localObject1 = paramJSONObject.getJSONArray("tracks");
      while (i < ((JSONArray)localObject1).length())
      {
        localObject2 = new MediaTrack(((JSONArray)localObject1).getJSONObject(i));
        this.Fj.add(localObject2);
        i += 1;
      }
      if ("BUFFERED".equals(localObject1)) {
        this.Ff = 1;
      } else if ("LIVE".equals(localObject1)) {
        this.Ff = 2;
      } else {
        this.Ff = -1;
      }
    }
    this.Fj = null;
    if (paramJSONObject.has("textTrackStyle"))
    {
      localObject1 = paramJSONObject.getJSONObject("textTrackStyle");
      localObject2 = new TextTrackStyle();
      ((TextTrackStyle)localObject2).c((JSONObject)localObject1);
    }
    for (this.Fk = ((TextTrackStyle)localObject2);; this.Fk = null)
    {
      this.Fl = paramJSONObject.optJSONObject("customData");
      return;
    }
  }
  
  void a(MediaMetadata paramMediaMetadata)
  {
    this.Fh = paramMediaMetadata;
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("contentId", this.Fe);
        switch (this.Ff)
        {
        default: 
          localJSONObject.put("streamType", localObject);
          if (this.Fg != null) {
            localJSONObject.put("contentType", this.Fg);
          }
          if (this.Fh != null) {
            localJSONObject.put("metadata", this.Fh.bK());
          }
          localJSONObject.put("duration", ik.o(this.Fi));
          if (this.Fj != null)
          {
            localObject = new JSONArray();
            Iterator localIterator = this.Fj.iterator();
            if (localIterator.hasNext())
            {
              ((JSONArray)localObject).put(((MediaTrack)localIterator.next()).bK());
              continue;
            }
            localJSONObject.put("tracks", localObject);
          }
          if (this.Fk != null) {
            localJSONObject.put("textTrackStyle", this.Fk.bK());
          }
          if (this.Fl == null) {
            break label215;
          }
          localJSONObject.put("customData", this.Fl);
          return localJSONObject;
        }
      }
      catch (JSONException localJSONException) {}
      Object localObject = "NONE";
      continue;
      label215:
      return localJSONObject;
      String str = "BUFFERED";
      continue;
      str = "LIVE";
    }
  }
  
  void c(List<MediaTrack> paramList)
  {
    this.Fj = paramList;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    if (this == paramObject) {
      bool1 = true;
    }
    int i;
    int j;
    label51:
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool3;
        } while (!(paramObject instanceof MediaInfo));
        paramObject = (MediaInfo)paramObject;
        if (this.Fl != null) {
          break;
        }
        i = 1;
        if (((MediaInfo)paramObject).Fl != null) {
          break label169;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.Fl == null) || (((MediaInfo)paramObject).Fl == null)) {
        break;
      }
      bool1 = bool3;
    } while (!jz.d(this.Fl, ((MediaInfo)paramObject).Fl));
    if ((ik.a(this.Fe, ((MediaInfo)paramObject).Fe)) && (this.Ff == ((MediaInfo)paramObject).Ff) && (ik.a(this.Fg, ((MediaInfo)paramObject).Fg)) && (ik.a(this.Fh, ((MediaInfo)paramObject).Fh)) && (this.Fi == ((MediaInfo)paramObject).Fi)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label169:
      j = 0;
      break label51;
    }
  }
  
  void fv()
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(this.Fe)) {
      throw new IllegalArgumentException("content ID cannot be null or empty");
    }
    if (TextUtils.isEmpty(this.Fg)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    if (this.Ff == -1) {
      throw new IllegalArgumentException("a valid stream type must be specified");
    }
  }
  
  public String getContentId()
  {
    return this.Fe;
  }
  
  public String getContentType()
  {
    return this.Fg;
  }
  
  public JSONObject getCustomData()
  {
    return this.Fl;
  }
  
  public List<MediaTrack> getMediaTracks()
  {
    return this.Fj;
  }
  
  public MediaMetadata getMetadata()
  {
    return this.Fh;
  }
  
  public long getStreamDuration()
  {
    return this.Fi;
  }
  
  public int getStreamType()
  {
    return this.Ff;
  }
  
  public TextTrackStyle getTextTrackStyle()
  {
    return this.Fk;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { this.Fe, Integer.valueOf(this.Ff), this.Fg, this.Fh, Long.valueOf(this.Fi), String.valueOf(this.Fl) });
  }
  
  void m(long paramLong)
    throws IllegalArgumentException
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("Stream duration cannot be negative");
    }
    this.Fi = paramLong;
  }
  
  void setContentType(String paramString)
    throws IllegalArgumentException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("content type cannot be null or empty");
    }
    this.Fg = paramString;
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.Fl = paramJSONObject;
  }
  
  void setStreamType(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt < -1) || (paramInt > 2)) {
      throw new IllegalArgumentException("invalid stream type");
    }
    this.Ff = paramInt;
  }
  
  public void setTextTrackStyle(TextTrackStyle paramTextTrackStyle)
  {
    this.Fk = paramTextTrackStyle;
  }
  
  public static class Builder
  {
    private final MediaInfo Fm;
    
    public Builder(String paramString)
      throws IllegalArgumentException
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("Content ID cannot be empty");
      }
      this.Fm = new MediaInfo(paramString);
    }
    
    public MediaInfo build()
      throws IllegalArgumentException
    {
      this.Fm.fv();
      return this.Fm;
    }
    
    public Builder setContentType(String paramString)
      throws IllegalArgumentException
    {
      this.Fm.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.Fm.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setMediaTracks(List<MediaTrack> paramList)
    {
      this.Fm.c(paramList);
      return this;
    }
    
    public Builder setMetadata(MediaMetadata paramMediaMetadata)
    {
      this.Fm.a(paramMediaMetadata);
      return this;
    }
    
    public Builder setStreamDuration(long paramLong)
      throws IllegalArgumentException
    {
      this.Fm.m(paramLong);
      return this;
    }
    
    public Builder setStreamType(int paramInt)
      throws IllegalArgumentException
    {
      this.Fm.setStreamType(paramInt);
      return this;
    }
    
    public Builder setTextTrackStyle(TextTrackStyle paramTextTrackStyle)
    {
      this.Fm.setTextTrackStyle(paramTextTrackStyle);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/MediaInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */