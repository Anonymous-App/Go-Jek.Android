package com.crashlytics.android.beta;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class BuildProperties
{
  private static final String BUILD_ID = "build_id";
  private static final String PACKAGE_NAME = "package_name";
  private static final String VERSION_CODE = "version_code";
  private static final String VERSION_NAME = "version_name";
  public final String buildId;
  public final String packageName;
  public final String versionCode;
  public final String versionName;
  
  BuildProperties(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.versionCode = paramString1;
    this.versionName = paramString2;
    this.buildId = paramString3;
    this.packageName = paramString4;
  }
  
  public static BuildProperties fromProperties(Properties paramProperties)
  {
    return new BuildProperties(paramProperties.getProperty("version_code"), paramProperties.getProperty("version_name"), paramProperties.getProperty("build_id"), paramProperties.getProperty("package_name"));
  }
  
  public static BuildProperties fromPropertiesStream(InputStream paramInputStream)
    throws IOException
  {
    Properties localProperties = new Properties();
    localProperties.load(paramInputStream);
    return fromProperties(localProperties);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/beta/BuildProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */