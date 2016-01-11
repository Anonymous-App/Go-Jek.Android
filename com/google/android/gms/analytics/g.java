package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

class g
  implements l
{
  private static g xP;
  private static Object xz = new Object();
  protected String xL;
  protected String xM;
  protected String xN;
  protected String xO;
  
  protected g() {}
  
  private g(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    this.xN = paramContext.getPackageName();
    this.xO = localPackageManager.getInstallerPackageName(this.xN);
    String str = this.xN;
    localObject2 = null;
    localObject1 = str;
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
      paramContext = (Context)localObject2;
      localObject1 = str;
      if (localPackageInfo != null)
      {
        localObject1 = str;
        paramContext = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
        localObject1 = paramContext;
        str = localPackageInfo.versionName;
        localObject1 = paramContext;
        paramContext = str;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        z.T("Error retrieving package info: appName set to " + (String)localObject1);
        paramContext = (Context)localObject2;
      }
    }
    this.xL = ((String)localObject1);
    this.xM = paramContext;
  }
  
  public static g dP()
  {
    return xP;
  }
  
  public static void y(Context paramContext)
  {
    synchronized (xz)
    {
      if (xP == null) {
        xP = new g(paramContext);
      }
      return;
    }
  }
  
  public boolean ac(String paramString)
  {
    return ("&an".equals(paramString)) || ("&av".equals(paramString)) || ("&aid".equals(paramString)) || ("&aiid".equals(paramString));
  }
  
  public String getValue(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return null;
      if (paramString.equals("&an")) {
        return this.xL;
      }
      if (paramString.equals("&av")) {
        return this.xM;
      }
      if (paramString.equals("&aid")) {
        return this.xN;
      }
    } while (!paramString.equals("&aiid"));
    return this.xO;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/analytics/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */