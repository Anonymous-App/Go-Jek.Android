package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.internal.pc;
import com.google.android.gms.internal.pc.a;
import com.google.android.gms.internal.pn;
import java.util.List;

public class PutDataMapRequest
{
  private final DataMap auX;
  private final PutDataRequest auY;
  
  private PutDataMapRequest(PutDataRequest paramPutDataRequest, DataMap paramDataMap)
  {
    this.auY = paramPutDataRequest;
    this.auX = new DataMap();
    if (paramDataMap != null) {
      this.auX.putAll(paramDataMap);
    }
  }
  
  public static PutDataMapRequest create(String paramString)
  {
    return new PutDataMapRequest(PutDataRequest.create(paramString), null);
  }
  
  public static PutDataMapRequest createFromDataMapItem(DataMapItem paramDataMapItem)
  {
    return new PutDataMapRequest(PutDataRequest.k(paramDataMapItem.getUri()), paramDataMapItem.getDataMap());
  }
  
  public static PutDataMapRequest createWithAutoAppendedId(String paramString)
  {
    return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(paramString), null);
  }
  
  public PutDataRequest asPutDataRequest()
  {
    pc.a locala = pc.a(this.auX);
    this.auY.setData(pn.f(locala.awb));
    int j = locala.awc.size();
    int i = 0;
    while (i < j)
    {
      String str = Integer.toString(i);
      Asset localAsset = (Asset)locala.awc.get(i);
      if (str == null) {
        throw new IllegalStateException("asset key cannot be null: " + localAsset);
      }
      if (localAsset == null) {
        throw new IllegalStateException("asset cannot be null: key=" + str);
      }
      if (Log.isLoggable("DataMap", 3)) {
        Log.d("DataMap", "asPutDataRequest: adding asset: " + str + " " + localAsset);
      }
      this.auY.putAsset(str, localAsset);
      i += 1;
    }
    return this.auY;
  }
  
  public DataMap getDataMap()
  {
    return this.auX;
  }
  
  public Uri getUri()
  {
    return this.auY.getUri();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/wearable/PutDataMapRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */