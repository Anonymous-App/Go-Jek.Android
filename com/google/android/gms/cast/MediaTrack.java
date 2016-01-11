package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack
{
  public static final int SUBTYPE_CAPTIONS = 2;
  public static final int SUBTYPE_CHAPTERS = 4;
  public static final int SUBTYPE_DESCRIPTIONS = 3;
  public static final int SUBTYPE_METADATA = 5;
  public static final int SUBTYPE_NONE = 0;
  public static final int SUBTYPE_SUBTITLES = 1;
  public static final int SUBTYPE_UNKNOWN = -1;
  public static final int TYPE_AUDIO = 2;
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_UNKNOWN = 0;
  public static final int TYPE_VIDEO = 3;
  private long Dj;
  private int FD;
  private int FE;
  private String Fc;
  private String Fe;
  private String Fg;
  private JSONObject Fl;
  private String mName;
  
  MediaTrack(long paramLong, int paramInt)
    throws IllegalArgumentException
  {
    clear();
    this.Dj = paramLong;
    if ((paramInt <= 0) || (paramInt > 3)) {
      throw new IllegalArgumentException("invalid type " + paramInt);
    }
    this.FD = paramInt;
  }
  
  MediaTrack(JSONObject paramJSONObject)
    throws JSONException
  {
    c(paramJSONObject);
  }
  
  private void c(JSONObject paramJSONObject)
    throws JSONException
  {
    clear();
    this.Dj = paramJSONObject.getLong("trackId");
    String str = paramJSONObject.getString("type");
    if ("TEXT".equals(str))
    {
      this.FD = 1;
      this.Fe = paramJSONObject.optString("trackContentId", null);
      this.Fg = paramJSONObject.optString("trackContentType", null);
      this.mName = paramJSONObject.optString("name", null);
      this.Fc = paramJSONObject.optString("language", null);
      if (!paramJSONObject.has("subtype")) {
        break label276;
      }
      str = paramJSONObject.getString("subtype");
      if (!"SUBTITLES".equals(str)) {
        break label181;
      }
      this.FE = 1;
    }
    for (;;)
    {
      this.Fl = paramJSONObject.optJSONObject("customData");
      return;
      if ("AUDIO".equals(str))
      {
        this.FD = 2;
        break;
      }
      if ("VIDEO".equals(str))
      {
        this.FD = 3;
        break;
      }
      throw new JSONException("invalid type: " + str);
      label181:
      if ("CAPTIONS".equals(str))
      {
        this.FE = 2;
      }
      else if ("DESCRIPTIONS".equals(str))
      {
        this.FE = 3;
      }
      else if ("CHAPTERS".equals(str))
      {
        this.FE = 4;
      }
      else if ("METADATA".equals(str))
      {
        this.FE = 5;
      }
      else
      {
        throw new JSONException("invalid subtype: " + str);
        label276:
        this.FE = 0;
      }
    }
  }
  
  private void clear()
  {
    this.Dj = 0L;
    this.FD = 0;
    this.Fe = null;
    this.mName = null;
    this.Fc = null;
    this.FE = -1;
    this.Fl = null;
  }
  
  void aa(int paramInt)
    throws IllegalArgumentException
  {
    if ((paramInt <= -1) || (paramInt > 5)) {
      throw new IllegalArgumentException("invalid subtype " + paramInt);
    }
    if ((paramInt != 0) && (this.FD != 1)) {
      throw new IllegalArgumentException("subtypes are only valid for text tracks");
    }
    this.FE = paramInt;
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("trackId", this.Dj);
      switch (this.FD)
      {
      case 1: 
        if (this.Fe != null) {
          localJSONObject.put("trackContentId", this.Fe);
        }
        if (this.Fg != null) {
          localJSONObject.put("trackContentType", this.Fg);
        }
        if (this.mName != null) {
          localJSONObject.put("name", this.mName);
        }
        if (!TextUtils.isEmpty(this.Fc)) {
          localJSONObject.put("language", this.Fc);
        }
        switch (this.FE)
        {
        }
        break;
      }
      for (;;)
      {
        if (this.Fl == null) {
          break label282;
        }
        localJSONObject.put("customData", this.Fl);
        return localJSONObject;
        localJSONObject.put("type", "TEXT");
        break;
        localJSONObject.put("type", "AUDIO");
        break;
        localJSONObject.put("type", "VIDEO");
        break;
        localJSONObject.put("subtype", "SUBTITLES");
        continue;
        localJSONObject.put("subtype", "CAPTIONS");
        continue;
        localJSONObject.put("subtype", "DESCRIPTIONS");
        continue;
        localJSONObject.put("subtype", "CHAPTERS");
        continue;
        localJSONObject.put("subtype", "METADATA");
        continue;
        break;
      }
      label282:
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
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
        } while (!(paramObject instanceof MediaTrack));
        paramObject = (MediaTrack)paramObject;
        if (this.Fl != null) {
          break;
        }
        i = 1;
        if (((MediaTrack)paramObject).Fl != null) {
          break label194;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.Fl == null) || (((MediaTrack)paramObject).Fl == null)) {
        break;
      }
      bool1 = bool3;
    } while (!jz.d(this.Fl, ((MediaTrack)paramObject).Fl));
    if ((this.Dj == ((MediaTrack)paramObject).Dj) && (this.FD == ((MediaTrack)paramObject).FD) && (ik.a(this.Fe, ((MediaTrack)paramObject).Fe)) && (ik.a(this.Fg, ((MediaTrack)paramObject).Fg)) && (ik.a(this.mName, ((MediaTrack)paramObject).mName)) && (ik.a(this.Fc, ((MediaTrack)paramObject).Fc)) && (this.FE == ((MediaTrack)paramObject).FE)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label194:
      j = 0;
      break label51;
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
  
  public long getId()
  {
    return this.Dj;
  }
  
  public String getLanguage()
  {
    return this.Fc;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public int getSubtype()
  {
    return this.FE;
  }
  
  public int getType()
  {
    return this.FD;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Long.valueOf(this.Dj), Integer.valueOf(this.FD), this.Fe, this.Fg, this.mName, this.Fc, Integer.valueOf(this.FE), this.Fl });
  }
  
  public void setContentId(String paramString)
  {
    this.Fe = paramString;
  }
  
  public void setContentType(String paramString)
  {
    this.Fg = paramString;
  }
  
  void setCustomData(JSONObject paramJSONObject)
  {
    this.Fl = paramJSONObject;
  }
  
  void setLanguage(String paramString)
  {
    this.Fc = paramString;
  }
  
  void setName(String paramString)
  {
    this.mName = paramString;
  }
  
  public static class Builder
  {
    private final MediaTrack FF;
    
    public Builder(long paramLong, int paramInt)
      throws IllegalArgumentException
    {
      this.FF = new MediaTrack(paramLong, paramInt);
    }
    
    public MediaTrack build()
    {
      return this.FF;
    }
    
    public Builder setContentId(String paramString)
    {
      this.FF.setContentId(paramString);
      return this;
    }
    
    public Builder setContentType(String paramString)
    {
      this.FF.setContentType(paramString);
      return this;
    }
    
    public Builder setCustomData(JSONObject paramJSONObject)
    {
      this.FF.setCustomData(paramJSONObject);
      return this;
    }
    
    public Builder setLanguage(String paramString)
    {
      this.FF.setLanguage(paramString);
      return this;
    }
    
    public Builder setLanguage(Locale paramLocale)
    {
      this.FF.setLanguage(ik.b(paramLocale));
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.FF.setName(paramString);
      return this;
    }
    
    public Builder setSubtype(int paramInt)
      throws IllegalArgumentException
    {
      this.FF.aa(paramInt);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/MediaTrack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */