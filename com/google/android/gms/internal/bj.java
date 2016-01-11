package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ez
public final class bj
  implements SafeParcelable
{
  public static final bk CREATOR = new bk();
  public final int backgroundColor;
  public final int oH;
  public final int oI;
  public final int oJ;
  public final int oK;
  public final int oL;
  public final int oM;
  public final int oN;
  public final String oO;
  public final int oP;
  public final String oQ;
  public final int oR;
  public final int oS;
  public final String oT;
  public final int versionCode;
  
  bj(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, String paramString1, int paramInt10, String paramString2, int paramInt11, int paramInt12, String paramString3)
  {
    this.versionCode = paramInt1;
    this.oH = paramInt2;
    this.backgroundColor = paramInt3;
    this.oI = paramInt4;
    this.oJ = paramInt5;
    this.oK = paramInt6;
    this.oL = paramInt7;
    this.oM = paramInt8;
    this.oN = paramInt9;
    this.oO = paramString1;
    this.oP = paramInt10;
    this.oQ = paramString2;
    this.oR = paramInt11;
    this.oS = paramInt12;
    this.oT = paramString3;
  }
  
  public bj(SearchAdRequest paramSearchAdRequest)
  {
    this.versionCode = 1;
    this.oH = paramSearchAdRequest.getAnchorTextColor();
    this.backgroundColor = paramSearchAdRequest.getBackgroundColor();
    this.oI = paramSearchAdRequest.getBackgroundGradientBottom();
    this.oJ = paramSearchAdRequest.getBackgroundGradientTop();
    this.oK = paramSearchAdRequest.getBorderColor();
    this.oL = paramSearchAdRequest.getBorderThickness();
    this.oM = paramSearchAdRequest.getBorderType();
    this.oN = paramSearchAdRequest.getCallButtonColor();
    this.oO = paramSearchAdRequest.getCustomChannels();
    this.oP = paramSearchAdRequest.getDescriptionTextColor();
    this.oQ = paramSearchAdRequest.getFontFace();
    this.oR = paramSearchAdRequest.getHeaderTextColor();
    this.oS = paramSearchAdRequest.getHeaderTextSize();
    this.oT = paramSearchAdRequest.getQuery();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    bk.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/bj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */