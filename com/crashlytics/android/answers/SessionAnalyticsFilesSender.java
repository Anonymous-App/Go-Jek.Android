package com.crashlytics.android.answers;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.File;
import java.util.Iterator;
import java.util.List;

class SessionAnalyticsFilesSender
  extends AbstractSpiCall
  implements FilesSender
{
  static final String FILE_CONTENT_TYPE = "application/vnd.crashlytics.android.events";
  static final String FILE_PARAM_NAME = "session_analytics_file_";
  private final String apiKey;
  
  public SessionAnalyticsFilesSender(Kit paramKit, String paramString1, String paramString2, HttpRequestFactory paramHttpRequestFactory, String paramString3)
  {
    super(paramKit, paramString1, paramString2, paramHttpRequestFactory, HttpMethod.POST);
    this.apiKey = paramString3;
  }
  
  public boolean send(List<File> paramList)
  {
    HttpRequest localHttpRequest = getHttpRequest().header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", this.kit.getVersion()).header("X-CRASHLYTICS-API-KEY", this.apiKey);
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      File localFile = (File)localIterator.next();
      localHttpRequest.part("session_analytics_file_" + i, localFile.getName(), "application/vnd.crashlytics.android.events", localFile);
      i += 1;
    }
    Fabric.getLogger().d("Answers", "Sending " + paramList.size() + " analytics files to " + getUrl());
    i = localHttpRequest.code();
    Fabric.getLogger().d("Answers", "Response code for analytics file send is " + i);
    return ResponseParser.parse(i) == 0;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionAnalyticsFilesSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */