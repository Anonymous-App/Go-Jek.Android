package com.newrelic.agent.android;

import android.content.Context;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.logging.AndroidAgentLog;
import com.newrelic.agent.android.logging.NullAgentLog;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.tracing.TracingInactiveException;
import com.newrelic.agent.android.util.NetworkFailure;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class NewRelic
{
  private static final String DEFAULT_COLLECTOR_ADDR = "mobile-collector.newrelic.com";
  private static final String UNKNOWN_HTTP_REQUEST_TYPE = "unknown";
  private static final AgentConfiguration agentConfiguration = new AgentConfiguration();
  private static final AgentLog log = ;
  private static boolean started = false;
  private int logLevel = 3;
  private boolean loggingEnabled = true;
  
  private NewRelic(String paramString)
  {
    agentConfiguration.setApplicationToken(paramString);
  }
  
  private static void _noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString3, Map<String, String> paramMap, String paramString4)
  {
    checkEmpty(paramString1, "noticeHttpTransaction: url must not be empty.");
    checkEmpty(paramString2, "noticeHttpTransaction: httpMethod must not be empty.");
    try
    {
      new URL(paramString1);
      double d = paramLong2 - paramLong1;
      if (!checkNegative((int)d, "noticeHttpTransaction: the startTimeMs is later than the endTimeMs, resulting in a negative total time."))
      {
        TaskQueue.queue(new HttpTransactionMeasurement(paramString1, paramString2, paramInt, 0, paramLong1, d / 1000.0D, paramLong3, paramLong4, paramString4));
        if (paramInt >= 400L) {
          Measurements.addHttpError(paramString1, paramString2, paramInt, paramString3, paramMap);
        }
      }
      return;
    }
    catch (MalformedURLException paramString2)
    {
      throw new IllegalArgumentException("noticeHttpTransaction: URL is malformed: " + paramString1);
    }
  }
  
  private static void checkEmpty(String paramString1, String paramString2)
  {
    checkNull(paramString1, paramString2);
    if (paramString1.length() == 0) {
      throw new IllegalArgumentException(paramString2);
    }
  }
  
  private static boolean checkNegative(int paramInt, String paramString)
  {
    if (paramInt < 0)
    {
      log.error(paramString);
      return true;
    }
    return false;
  }
  
  private static void checkNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  public static void crashNow()
  {
    crashNow("This is a demonstration crash courtesy of New Relic");
  }
  
  public static void crashNow(String paramString)
  {
    throw new RuntimeException(paramString);
  }
  
  public static void disableFeature(FeatureFlag paramFeatureFlag)
  {
    FeatureFlag.disableFeature(paramFeatureFlag);
  }
  
  public static void enableFeature(FeatureFlag paramFeatureFlag)
  {
    FeatureFlag.enableFeature(paramFeatureFlag);
  }
  
  public static void endInteraction(String paramString)
  {
    TraceMachine.endTrace(paramString);
  }
  
  private boolean isInstrumented()
  {
    return true;
  }
  
  public static boolean isStarted()
  {
    return started;
  }
  
  @Deprecated
  public static void noticeHttpTransaction(String paramString, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    _noticeHttpTransaction(paramString, "unknown", paramInt, paramLong1, paramLong2, paramLong3, paramLong4, null, null, null);
  }
  
  @Deprecated
  public static void noticeHttpTransaction(String paramString1, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString2)
  {
    _noticeHttpTransaction(paramString1, "unknown", paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString2, null, null);
  }
  
  @Deprecated
  public static void noticeHttpTransaction(String paramString1, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString2, Map<String, String> paramMap)
  {
    _noticeHttpTransaction(paramString1, "unknown", paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString2, paramMap, null);
  }
  
  @Deprecated
  public static void noticeHttpTransaction(String paramString1, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString2, Map<String, String> paramMap, String paramString3)
  {
    _noticeHttpTransaction(paramString1, "unknown", paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString2, paramMap, paramString3);
  }
  
  @Deprecated
  public static void noticeHttpTransaction(String paramString1, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString2, Map<String, String> paramMap, URLConnection paramURLConnection)
  {
    noticeHttpTransaction(paramString1, "unknown", paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString2, paramMap, paramURLConnection);
  }
  
  @Deprecated
  public static void noticeHttpTransaction(String paramString1, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString2, Map<String, String> paramMap, HttpResponse paramHttpResponse)
  {
    noticeHttpTransaction(paramString1, "unknown", paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString2, paramMap, paramHttpResponse);
  }
  
  public static void noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, null, null, null);
  }
  
  public static void noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString3)
  {
    _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, null, null);
  }
  
  public static void noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString3, Map<String, String> paramMap)
  {
    _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, paramMap, null);
  }
  
  public static void noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString3, Map<String, String> paramMap, String paramString4)
  {
    _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, paramMap, paramString4);
  }
  
  public static void noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString3, Map<String, String> paramMap, URLConnection paramURLConnection)
  {
    if (paramURLConnection != null)
    {
      paramURLConnection = paramURLConnection.getHeaderField("X-NewRelic-ID");
      if ((paramURLConnection != null) && (paramURLConnection.length() > 0))
      {
        _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, paramMap, paramURLConnection);
        return;
      }
    }
    _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, paramMap, null);
  }
  
  public static void noticeHttpTransaction(String paramString1, String paramString2, int paramInt, long paramLong1, long paramLong2, long paramLong3, long paramLong4, String paramString3, Map<String, String> paramMap, HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse != null)
    {
      paramHttpResponse = paramHttpResponse.getFirstHeader("X-NewRelic-ID");
      if ((paramHttpResponse != null) && (paramHttpResponse.getValue() != null) && (paramHttpResponse.getValue().length() > 0))
      {
        _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, paramMap, paramHttpResponse.getValue());
        return;
      }
    }
    _noticeHttpTransaction(paramString1, paramString2, paramInt, paramLong1, paramLong2, paramLong3, paramLong4, paramString3, paramMap, null);
  }
  
  @Deprecated
  public static void noticeNetworkFailure(String paramString, long paramLong1, long paramLong2, NetworkFailure paramNetworkFailure)
  {
    noticeNetworkFailure(paramString, "unknown", paramLong1, paramLong2, paramNetworkFailure);
  }
  
  @Deprecated
  public static void noticeNetworkFailure(String paramString, long paramLong1, long paramLong2, Exception paramException)
  {
    noticeNetworkFailure(paramString, "unknown", paramLong1, paramLong2, paramException);
  }
  
  public static void noticeNetworkFailure(String paramString1, String paramString2, long paramLong1, long paramLong2, NetworkFailure paramNetworkFailure)
  {
    log.debug("NewRelic.noticeNetworkFailure invoke - url: " + paramString1 + ", httpMethod: " + paramString2 + ", startTime: " + paramLong1 + ", endTime: " + paramLong2 + ", failure: " + paramNetworkFailure);
    TaskQueue.queue(new HttpTransactionMeasurement(paramString1, paramString2, 0, paramNetworkFailure.getErrorCode(), paramLong1, paramLong2, 0L, 0L, null));
  }
  
  public static void noticeNetworkFailure(String paramString1, String paramString2, long paramLong1, long paramLong2, Exception paramException)
  {
    checkEmpty(paramString1, "noticeHttpException: url must not be empty.");
    noticeNetworkFailure(paramString1, paramString2, paramLong1, paramLong2, NetworkFailure.exceptionToNetworkFailure(paramException));
  }
  
  public static void recordMetric(String paramString1, String paramString2)
  {
    recordMetric(paramString1, paramString2, 1.0D);
  }
  
  public static void recordMetric(String paramString1, String paramString2, double paramDouble)
  {
    recordMetric(paramString1, paramString2, 1, paramDouble, paramDouble, null, null);
  }
  
  public static void recordMetric(String paramString1, String paramString2, int paramInt, double paramDouble1, double paramDouble2)
  {
    recordMetric(paramString1, paramString2, paramInt, paramDouble1, paramDouble2, null, null);
  }
  
  public static void recordMetric(String paramString1, String paramString2, int paramInt, double paramDouble1, double paramDouble2, MetricUnit paramMetricUnit1, MetricUnit paramMetricUnit2)
  {
    checkNull(paramString2, "recordMetric: category must not be null. If no MetricCategory is applicable, use MetricCategory.NONE.");
    checkEmpty(paramString1, "recordMetric: name must not be empty.");
    if (!checkNegative(paramInt, "recordMetric: count must not be negative.")) {
      Measurements.addCustomMetric(paramString1, paramString2, paramInt, paramDouble1, paramDouble2, paramMetricUnit1, paramMetricUnit2);
    }
  }
  
  public static void setInteractionName(String paramString)
  {
    TraceMachine.setRootDisplayName(paramString);
  }
  
  public static void shutdown()
  {
    if (started) {}
    try
    {
      Agent.getImpl().stop();
      return;
    }
    finally
    {
      Agent.setImpl(NullAgentImpl.instance);
      started = false;
    }
  }
  
  @Deprecated
  public static String startInteraction(Context paramContext, String paramString)
  {
    checkNull(paramContext, "startInteraction: context must be an Activity instance.");
    checkNull(paramString, "startInteraction: actionName must be an action/method name.");
    TraceMachine.startTracing(paramContext.getClass().getSimpleName() + "#" + paramString.replace("/", "."));
    try
    {
      paramContext = TraceMachine.getActivityTrace().getId();
      return paramContext;
    }
    catch (TracingInactiveException paramContext) {}
    return null;
  }
  
  @Deprecated
  public static String startInteraction(Context paramContext, String paramString, boolean paramBoolean)
  {
    if ((TraceMachine.isTracingActive()) && (!paramBoolean))
    {
      log.warning("startInteraction: An interaction is already being traced, and invalidateActiveTrace is false. This interaction will not be traced.");
      return null;
    }
    return startInteraction(paramContext, paramString);
  }
  
  public static String startInteraction(String paramString)
  {
    checkNull(paramString, "startInteraction: actionName must be an action/method name.");
    TraceMachine.startTracing(paramString.replace("/", "."), true);
    try
    {
      paramString = TraceMachine.getActivityTrace().getId();
      return paramString;
    }
    catch (TracingInactiveException paramString) {}
    return null;
  }
  
  public static NewRelic withApplicationToken(String paramString)
  {
    return new NewRelic(paramString);
  }
  
  public void start(Context paramContext)
  {
    if (started)
    {
      log.debug("NewRelic is already running.");
      return;
    }
    for (;;)
    {
      try
      {
        if (this.loggingEnabled)
        {
          localObject = new AndroidAgentLog();
          AgentLogManager.setAgentLog((AgentLog)localObject);
          log.setLevel(this.logLevel);
          if (!isInstrumented()) {
            break;
          }
          AndroidAgentImpl.init(paramContext, agentConfiguration);
          started = true;
          return;
        }
      }
      catch (Throwable paramContext)
      {
        log.error("Error occurred while starting the New Relic agent!", paramContext);
        return;
      }
      Object localObject = new NullAgentLog();
    }
    log.error("Failed to detect New Relic instrumentation.  Something likely went wrong during your build process and you should contact support@newrelic.com.");
  }
  
  public NewRelic usingCollectorAddress(String paramString)
  {
    agentConfiguration.setCollectorHost(paramString);
    return this;
  }
  
  public NewRelic usingCrashCollectorAddress(String paramString)
  {
    agentConfiguration.setCrashCollectorHost(paramString);
    return this;
  }
  
  public NewRelic usingSsl(boolean paramBoolean)
  {
    agentConfiguration.setUseSsl(paramBoolean);
    return this;
  }
  
  public NewRelic withApplicationVersion(String paramString)
  {
    if (paramString != null) {
      agentConfiguration.setCustomApplicationVersion(paramString);
    }
    return this;
  }
  
  public NewRelic withCrashReportingEnabled(boolean paramBoolean)
  {
    agentConfiguration.setReportCrashes(paramBoolean);
    if (paramBoolean)
    {
      enableFeature(FeatureFlag.CrashReporting);
      return this;
    }
    disableFeature(FeatureFlag.CrashReporting);
    return this;
  }
  
  public NewRelic withHttpResponseBodyCaptureEnabled(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      enableFeature(FeatureFlag.HttpResponseBodyCapture);
      return this;
    }
    disableFeature(FeatureFlag.HttpResponseBodyCapture);
    return this;
  }
  
  public NewRelic withLocationServiceEnabled(boolean paramBoolean)
  {
    agentConfiguration.setUseLocationService(paramBoolean);
    return this;
  }
  
  public NewRelic withLogLevel(int paramInt)
  {
    this.logLevel = paramInt;
    return this;
  }
  
  public NewRelic withLoggingEnabled(boolean paramBoolean)
  {
    this.loggingEnabled = paramBoolean;
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/newrelic/agent/android/NewRelic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */