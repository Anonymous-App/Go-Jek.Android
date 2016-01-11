package com.google.android.gms.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

class a
  implements l
{
  private static a xA;
  private static Object xz = new Object();
  private Context mContext;
  private AdvertisingIdClient.Info xB;
  private long xC;
  private String xD;
  private boolean xE = false;
  private Object xF = new Object();
  
  a(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }
  
  private boolean a(AdvertisingIdClient.Info paramInfo1, AdvertisingIdClient.Info paramInfo2)
  {
    Object localObject1 = null;
    if (paramInfo2 == null) {}
    for (paramInfo2 = null; TextUtils.isEmpty(paramInfo2); paramInfo2 = paramInfo2.getId()) {
      return true;
    }
    h.y(this.mContext);
    h localh = h.dQ();
    String str = localh.getValue("&cid");
    boolean bool;
    for (;;)
    {
      synchronized (this.xF)
      {
        if (!this.xE)
        {
          this.xD = x(this.mContext);
          this.xE = true;
          paramInfo1 = aa(paramInfo2 + str);
          if (!TextUtils.isEmpty(paramInfo1)) {
            break;
          }
          return false;
        }
      }
      if (TextUtils.isEmpty(this.xD))
      {
        if (paramInfo1 == null) {}
        for (paramInfo1 = (AdvertisingIdClient.Info)localObject1; paramInfo1 == null; paramInfo1 = paramInfo1.getId())
        {
          bool = ab(paramInfo2 + str);
          return bool;
        }
        this.xD = aa(paramInfo1 + str);
      }
    }
    if (paramInfo1.equals(this.xD)) {
      return true;
    }
    if (!TextUtils.isEmpty(this.xD))
    {
      z.V("Resetting the client id because Advertising Id changed.");
      paramInfo1 = localh.dR();
      z.V("New client Id: " + paramInfo1);
    }
    for (;;)
    {
      bool = ab(paramInfo2 + paramInfo1);
      return bool;
      paramInfo1 = str;
    }
  }
  
  static String aa(String paramString)
  {
    MessageDigest localMessageDigest = aj.ap("MD5");
    if (localMessageDigest == null) {
      return null;
    }
    return String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, localMessageDigest.digest(paramString.getBytes())) });
  }
  
  private boolean ab(String paramString)
  {
    try
    {
      paramString = aa(paramString);
      z.V("Storing hashed adid.");
      FileOutputStream localFileOutputStream = this.mContext.openFileOutput("gaClientIdData", 0);
      localFileOutputStream.write(paramString.getBytes());
      localFileOutputStream.close();
      this.xD = paramString;
      return true;
    }
    catch (FileNotFoundException paramString)
    {
      z.T("Error creating hash file.");
      return false;
    }
    catch (IOException paramString)
    {
      z.T("Error writing to hash file.");
    }
    return false;
  }
  
  public static l w(Context paramContext)
  {
    if (xA == null) {}
    synchronized (xz)
    {
      if (xA == null) {
        xA = new a(paramContext);
      }
      return xA;
    }
  }
  
  static String x(Context paramContext)
  {
    String str = null;
    try
    {
      FileInputStream localFileInputStream = paramContext.openFileInput("gaClientIdData");
      Object localObject2 = new byte[''];
      int i = localFileInputStream.read((byte[])localObject2, 0, 128);
      if (localFileInputStream.available() > 0)
      {
        z.W("Hash file seems corrupted, deleting it.");
        localFileInputStream.close();
        paramContext.deleteFile("gaClientIdData");
        return null;
      }
      if (i <= 0)
      {
        z.U("Hash file is empty.");
        localFileInputStream.close();
        return null;
      }
      localObject2 = new String((byte[])localObject2, 0, i);
      Object localObject1;
      return null;
    }
    catch (IOException localIOException2)
    {
      try
      {
        localFileInputStream.close();
        return (String)localObject2;
      }
      catch (IOException localIOException1)
      {
        for (;;)
        {
          localObject1 = localIOException2;
        }
      }
      catch (FileNotFoundException paramContext)
      {
        return localIOException2;
      }
      localIOException2 = localIOException2;
      z.W("Error reading Hash file, deleting it.");
      paramContext.deleteFile("gaClientIdData");
      return str;
    }
    catch (FileNotFoundException paramContext) {}
  }
  
  AdvertisingIdClient.Info dG()
  {
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
      return localInfo;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      z.W("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
      return null;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      z.W("GooglePlayServicesRepairableException getting Ad Id Info");
      return null;
    }
    catch (IOException localIOException)
    {
      z.W("IOException getting Ad Id Info");
      return null;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      z.W("GooglePlayServicesNotAvailableException getting Ad Id Info");
      return null;
    }
    catch (Throwable localThrowable)
    {
      z.W("Unknown exception. Could not get the ad Id.");
    }
    return null;
  }
  
  public String getValue(String paramString)
  {
    long l = System.currentTimeMillis();
    AdvertisingIdClient.Info localInfo;
    if (l - this.xC > 1000L)
    {
      localInfo = dG();
      if (!a(this.xB, localInfo)) {
        break label72;
      }
    }
    label72:
    for (this.xB = localInfo;; this.xB = new AdvertisingIdClient.Info("", false))
    {
      this.xC = l;
      if (this.xB == null) {
        break label118;
      }
      if (!"&adid".equals(paramString)) {
        break;
      }
      return this.xB.getId();
    }
    if ("&ate".equals(paramString))
    {
      if (this.xB.isLimitAdTrackingEnabled()) {
        return "0";
      }
      return "1";
    }
    label118:
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */