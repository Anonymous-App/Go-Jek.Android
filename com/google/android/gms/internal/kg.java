package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.j;
import java.util.Arrays;

public class kg
  extends j<DriveId>
{
  public static final kg Qy = new kg();
  
  private kg()
  {
    super("driveId", Arrays.asList(new String[] { "sqlId", "resourceId" }), Arrays.asList(new String[] { "dbInstanceId" }), 4100000);
  }
  
  protected DriveId m(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    long l = paramDataHolder.gy().getLong("dbInstanceId");
    String str2 = paramDataHolder.c("resourceId", paramInt1, paramInt2);
    String str1 = str2;
    if (str2 != null)
    {
      str1 = str2;
      if (str2.startsWith("generated-android-")) {
        str1 = null;
      }
    }
    return new DriveId(str1, Long.valueOf(paramDataHolder.a("sqlId", paramInt1, paramInt2)).longValue(), l);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */