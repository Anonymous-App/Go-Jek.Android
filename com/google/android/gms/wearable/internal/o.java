package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import java.util.HashMap;
import java.util.Map;

public final class o
  extends d
  implements DataItem
{
  private final int aaK;
  
  public o(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.aaK = paramInt2;
  }
  
  public Map<String, DataItemAsset> getAssets()
  {
    HashMap localHashMap = new HashMap(this.aaK);
    int i = 0;
    if (i < this.aaK)
    {
      k localk = new k(this.II, this.JX + i);
      if (localk.getDataItemKey() == null) {}
      for (;;)
      {
        i += 1;
        break;
        localHashMap.put(localk.getDataItemKey(), localk);
      }
    }
    return localHashMap;
  }
  
  public byte[] getData()
  {
    return getByteArray("data");
  }
  
  public Uri getUri()
  {
    return Uri.parse(getString("path"));
  }
  
  public DataItem pY()
  {
    return new l(this);
  }
  
  public DataItem setData(byte[] paramArrayOfByte)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/internal/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */