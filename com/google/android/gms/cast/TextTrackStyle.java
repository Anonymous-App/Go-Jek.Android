package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.jz;
import com.google.android.gms.internal.kc;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle
{
  public static final int COLOR_UNSPECIFIED = 0;
  public static final float DEFAULT_FONT_SCALE = 1.0F;
  public static final int EDGE_TYPE_DEPRESSED = 4;
  public static final int EDGE_TYPE_DROP_SHADOW = 2;
  public static final int EDGE_TYPE_NONE = 0;
  public static final int EDGE_TYPE_OUTLINE = 1;
  public static final int EDGE_TYPE_RAISED = 3;
  public static final int EDGE_TYPE_UNSPECIFIED = -1;
  public static final int FONT_FAMILY_CASUAL = 4;
  public static final int FONT_FAMILY_CURSIVE = 5;
  public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
  public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
  public static final int FONT_FAMILY_SANS_SERIF = 0;
  public static final int FONT_FAMILY_SERIF = 2;
  public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
  public static final int FONT_FAMILY_UNSPECIFIED = -1;
  public static final int FONT_STYLE_BOLD = 1;
  public static final int FONT_STYLE_BOLD_ITALIC = 3;
  public static final int FONT_STYLE_ITALIC = 2;
  public static final int FONT_STYLE_NORMAL = 0;
  public static final int FONT_STYLE_UNSPECIFIED = -1;
  public static final int WINDOW_TYPE_NONE = 0;
  public static final int WINDOW_TYPE_NORMAL = 1;
  public static final int WINDOW_TYPE_ROUNDED = 2;
  public static final int WINDOW_TYPE_UNSPECIFIED = -1;
  private JSONObject Fl;
  private float Gd;
  private int Ge;
  private int Gf;
  private int Gg;
  private int Gh;
  private int Gi;
  private int Gj;
  private String Gk;
  private int Gl;
  private int Gm;
  private int xm;
  
  public TextTrackStyle()
  {
    clear();
  }
  
  private int aC(String paramString)
  {
    int j = 0;
    int i = j;
    if (paramString != null)
    {
      i = j;
      if (paramString.length() == 9)
      {
        i = j;
        if (paramString.charAt(0) != '#') {}
      }
    }
    try
    {
      i = Integer.parseInt(paramString.substring(1, 3), 16);
      j = Integer.parseInt(paramString.substring(3, 5), 16);
      int k = Integer.parseInt(paramString.substring(5, 7), 16);
      i = Color.argb(Integer.parseInt(paramString.substring(7, 9), 16), i, j, k);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return 0;
  }
  
  private void clear()
  {
    this.Gd = 1.0F;
    this.Ge = 0;
    this.xm = 0;
    this.Gf = -1;
    this.Gg = 0;
    this.Gh = -1;
    this.Gi = 0;
    this.Gj = 0;
    this.Gk = null;
    this.Gl = -1;
    this.Gm = -1;
    this.Fl = null;
  }
  
  public static TextTrackStyle fromSystemSettings(Context paramContext)
  {
    TextTrackStyle localTextTrackStyle = new TextTrackStyle();
    if (!kc.hH()) {
      return localTextTrackStyle;
    }
    paramContext = (CaptioningManager)paramContext.getSystemService("captioning");
    localTextTrackStyle.setFontScale(paramContext.getFontScale());
    paramContext = paramContext.getUserStyle();
    localTextTrackStyle.setBackgroundColor(paramContext.backgroundColor);
    localTextTrackStyle.setForegroundColor(paramContext.foregroundColor);
    label117:
    boolean bool1;
    boolean bool2;
    switch (paramContext.edgeType)
    {
    default: 
      localTextTrackStyle.setEdgeType(0);
      localTextTrackStyle.setEdgeColor(paramContext.edgeColor);
      paramContext = paramContext.getTypeface();
      if (paramContext != null)
      {
        if (!Typeface.MONOSPACE.equals(paramContext)) {
          break label158;
        }
        localTextTrackStyle.setFontGenericFamily(1);
        bool1 = paramContext.isBold();
        bool2 = paramContext.isItalic();
        if ((!bool1) || (!bool2)) {
          break label202;
        }
        localTextTrackStyle.setFontStyle(3);
      }
      break;
    }
    for (;;)
    {
      return localTextTrackStyle;
      localTextTrackStyle.setEdgeType(1);
      break;
      localTextTrackStyle.setEdgeType(2);
      break;
      label158:
      if (Typeface.SANS_SERIF.equals(paramContext))
      {
        localTextTrackStyle.setFontGenericFamily(0);
        break label117;
      }
      if (Typeface.SERIF.equals(paramContext))
      {
        localTextTrackStyle.setFontGenericFamily(2);
        break label117;
      }
      localTextTrackStyle.setFontGenericFamily(0);
      break label117;
      label202:
      if (bool1) {
        localTextTrackStyle.setFontStyle(1);
      } else if (bool2) {
        localTextTrackStyle.setFontStyle(2);
      } else {
        localTextTrackStyle.setFontStyle(0);
      }
    }
  }
  
  private String t(int paramInt)
  {
    return String.format("#%02X%02X%02X%02X", new Object[] { Integer.valueOf(Color.red(paramInt)), Integer.valueOf(Color.green(paramInt)), Integer.valueOf(Color.blue(paramInt)), Integer.valueOf(Color.alpha(paramInt)) });
  }
  
  public JSONObject bK()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("fontScale", this.Gd);
      if (this.Ge != 0) {
        localJSONObject.put("foregroundColor", t(this.Ge));
      }
      if (this.xm != 0) {
        localJSONObject.put("backgroundColor", t(this.xm));
      }
      switch (this.Gf)
      {
      case 0: 
        if (this.Gg != 0) {
          localJSONObject.put("edgeColor", t(this.Gg));
        }
        switch (this.Gh)
        {
        case 0: 
          label156:
          if (this.Gi != 0) {
            localJSONObject.put("windowColor", t(this.Gi));
          }
          if (this.Gh == 2) {
            localJSONObject.put("windowRoundedCornerRadius", this.Gj);
          }
          if (this.Gk != null) {
            localJSONObject.put("fontFamily", this.Gk);
          }
          switch (this.Gl)
          {
          case 0: 
            label264:
            switch (this.Gm)
            {
            }
            break;
          }
          break;
        }
        break;
      }
      for (;;)
      {
        if (this.Fl == null) {
          break label599;
        }
        localJSONObject.put("customData", this.Fl);
        return localJSONObject;
        localJSONObject.put("edgeType", "NONE");
        break;
        localJSONObject.put("edgeType", "OUTLINE");
        break;
        localJSONObject.put("edgeType", "DROP_SHADOW");
        break;
        localJSONObject.put("edgeType", "RAISED");
        break;
        localJSONObject.put("edgeType", "DEPRESSED");
        break;
        localJSONObject.put("windowType", "NONE");
        break label156;
        localJSONObject.put("windowType", "NORMAL");
        break label156;
        localJSONObject.put("windowType", "ROUNDED_CORNERS");
        break label156;
        localJSONObject.put("fontGenericFamily", "SANS_SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "MONOSPACED_SERIF");
        break label264;
        localJSONObject.put("fontGenericFamily", "CASUAL");
        break label264;
        localJSONObject.put("fontGenericFamily", "CURSIVE");
        break label264;
        localJSONObject.put("fontGenericFamily", "SMALL_CAPITALS");
        break label264;
        localJSONObject.put("fontStyle", "NORMAL");
        continue;
        localJSONObject.put("fontStyle", "BOLD");
        continue;
        localJSONObject.put("fontStyle", "ITALIC");
        continue;
        localJSONObject.put("fontStyle", "BOLD_ITALIC");
        continue;
        break;
        break label156;
        break label264;
      }
      label599:
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public void c(JSONObject paramJSONObject)
    throws JSONException
  {
    clear();
    this.Gd = ((float)paramJSONObject.optDouble("fontScale", 1.0D));
    this.Ge = aC(paramJSONObject.optString("foregroundColor"));
    this.xm = aC(paramJSONObject.optString("backgroundColor"));
    String str;
    if (paramJSONObject.has("edgeType"))
    {
      str = paramJSONObject.getString("edgeType");
      if ("NONE".equals(str)) {
        this.Gf = 0;
      }
    }
    else
    {
      this.Gg = aC(paramJSONObject.optString("edgeColor"));
      if (paramJSONObject.has("windowType"))
      {
        str = paramJSONObject.getString("windowType");
        if (!"NONE".equals(str)) {
          break label320;
        }
        this.Gh = 0;
      }
      label124:
      this.Gi = aC(paramJSONObject.optString("windowColor"));
      if (this.Gh == 2) {
        this.Gj = paramJSONObject.optInt("windowRoundedCornerRadius", 0);
      }
      this.Gk = paramJSONObject.optString("fontFamily", null);
      if (paramJSONObject.has("fontGenericFamily"))
      {
        str = paramJSONObject.getString("fontGenericFamily");
        if (!"SANS_SERIF".equals(str)) {
          break label356;
        }
        this.Gl = 0;
      }
      label203:
      if (paramJSONObject.has("fontStyle"))
      {
        str = paramJSONObject.getString("fontStyle");
        if (!"NORMAL".equals(str)) {
          break label465;
        }
        this.Gm = 0;
      }
    }
    for (;;)
    {
      this.Fl = paramJSONObject.optJSONObject("customData");
      return;
      if ("OUTLINE".equals(str))
      {
        this.Gf = 1;
        break;
      }
      if ("DROP_SHADOW".equals(str))
      {
        this.Gf = 2;
        break;
      }
      if ("RAISED".equals(str))
      {
        this.Gf = 3;
        break;
      }
      if (!"DEPRESSED".equals(str)) {
        break;
      }
      this.Gf = 4;
      break;
      label320:
      if ("NORMAL".equals(str))
      {
        this.Gh = 1;
        break label124;
      }
      if (!"ROUNDED_CORNERS".equals(str)) {
        break label124;
      }
      this.Gh = 2;
      break label124;
      label356:
      if ("MONOSPACED_SANS_SERIF".equals(str))
      {
        this.Gl = 1;
        break label203;
      }
      if ("SERIF".equals(str))
      {
        this.Gl = 2;
        break label203;
      }
      if ("MONOSPACED_SERIF".equals(str))
      {
        this.Gl = 3;
        break label203;
      }
      if ("CASUAL".equals(str))
      {
        this.Gl = 4;
        break label203;
      }
      if ("CURSIVE".equals(str))
      {
        this.Gl = 5;
        break label203;
      }
      if (!"SMALL_CAPITALS".equals(str)) {
        break label203;
      }
      this.Gl = 6;
      break label203;
      label465:
      if ("BOLD".equals(str)) {
        this.Gm = 1;
      } else if ("ITALIC".equals(str)) {
        this.Gm = 2;
      } else if ("BOLD_ITALIC".equals(str)) {
        this.Gm = 3;
      }
    }
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
        } while (!(paramObject instanceof TextTrackStyle));
        paramObject = (TextTrackStyle)paramObject;
        if (this.Fl != null) {
          break;
        }
        i = 1;
        if (((TextTrackStyle)paramObject).Fl != null) {
          break label218;
        }
        j = 1;
        bool1 = bool3;
      } while (i != j);
      if ((this.Fl == null) || (((TextTrackStyle)paramObject).Fl == null)) {
        break;
      }
      bool1 = bool3;
    } while (!jz.d(this.Fl, ((TextTrackStyle)paramObject).Fl));
    if ((this.Gd == ((TextTrackStyle)paramObject).Gd) && (this.Ge == ((TextTrackStyle)paramObject).Ge) && (this.xm == ((TextTrackStyle)paramObject).xm) && (this.Gf == ((TextTrackStyle)paramObject).Gf) && (this.Gg == ((TextTrackStyle)paramObject).Gg) && (this.Gh == ((TextTrackStyle)paramObject).Gh) && (this.Gj == ((TextTrackStyle)paramObject).Gj) && (ik.a(this.Gk, ((TextTrackStyle)paramObject).Gk)) && (this.Gl == ((TextTrackStyle)paramObject).Gl) && (this.Gm == ((TextTrackStyle)paramObject).Gm)) {}
    for (boolean bool1 = bool2;; bool1 = false)
    {
      return bool1;
      i = 0;
      break;
      label218:
      j = 0;
      break label51;
    }
  }
  
  public int getBackgroundColor()
  {
    return this.xm;
  }
  
  public JSONObject getCustomData()
  {
    return this.Fl;
  }
  
  public int getEdgeColor()
  {
    return this.Gg;
  }
  
  public int getEdgeType()
  {
    return this.Gf;
  }
  
  public String getFontFamily()
  {
    return this.Gk;
  }
  
  public int getFontGenericFamily()
  {
    return this.Gl;
  }
  
  public float getFontScale()
  {
    return this.Gd;
  }
  
  public int getFontStyle()
  {
    return this.Gm;
  }
  
  public int getForegroundColor()
  {
    return this.Ge;
  }
  
  public int getWindowColor()
  {
    return this.Gi;
  }
  
  public int getWindowCornerRadius()
  {
    return this.Gj;
  }
  
  public int getWindowType()
  {
    return this.Gh;
  }
  
  public int hashCode()
  {
    return n.hashCode(new Object[] { Float.valueOf(this.Gd), Integer.valueOf(this.Ge), Integer.valueOf(this.xm), Integer.valueOf(this.Gf), Integer.valueOf(this.Gg), Integer.valueOf(this.Gh), Integer.valueOf(this.Gi), Integer.valueOf(this.Gj), this.Gk, Integer.valueOf(this.Gl), Integer.valueOf(this.Gm), this.Fl });
  }
  
  public void setBackgroundColor(int paramInt)
  {
    this.xm = paramInt;
  }
  
  public void setCustomData(JSONObject paramJSONObject)
  {
    this.Fl = paramJSONObject;
  }
  
  public void setEdgeColor(int paramInt)
  {
    this.Gg = paramInt;
  }
  
  public void setEdgeType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 4)) {
      throw new IllegalArgumentException("invalid edgeType");
    }
    this.Gf = paramInt;
  }
  
  public void setFontFamily(String paramString)
  {
    this.Gk = paramString;
  }
  
  public void setFontGenericFamily(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 6)) {
      throw new IllegalArgumentException("invalid fontGenericFamily");
    }
    this.Gl = paramInt;
  }
  
  public void setFontScale(float paramFloat)
  {
    this.Gd = paramFloat;
  }
  
  public void setFontStyle(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 3)) {
      throw new IllegalArgumentException("invalid fontStyle");
    }
    this.Gm = paramInt;
  }
  
  public void setForegroundColor(int paramInt)
  {
    this.Ge = paramInt;
  }
  
  public void setWindowColor(int paramInt)
  {
    this.Gi = paramInt;
  }
  
  public void setWindowCornerRadius(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("invalid windowCornerRadius");
    }
    this.Gj = paramInt;
  }
  
  public void setWindowType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 2)) {
      throw new IllegalArgumentException("invalid windowType");
    }
    this.Gh = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/cast/TextTrackStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */