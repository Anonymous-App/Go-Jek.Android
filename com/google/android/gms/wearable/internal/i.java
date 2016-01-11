package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

public class i
  implements DataItemAsset
{
  private final String BL;
  private final String JO;
  
  public i(DataItemAsset paramDataItemAsset)
  {
    this.BL = paramDataItemAsset.getId();
    this.JO = paramDataItemAsset.getDataItemKey();
  }
  
  public String getDataItemKey()
  {
    return this.JO;
  }
  
  public String getId()
  {
    return this.BL;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public DataItemAsset pX()
  {
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DataItemAssetEntity[");
    localStringBuilder.append("@");
    localStringBuilder.append(Integer.toHexString(hashCode()));
    if (this.BL == null) {
      localStringBuilder.append(",noid");
    }
    for (;;)
    {
      localStringBuilder.append(", key=");
      localStringBuilder.append(this.JO);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
      localStringBuilder.append(",");
      localStringBuilder.append(this.BL);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */