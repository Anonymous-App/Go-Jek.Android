package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import io.fabric.sdk.android.services.events.EventTransform;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class SessionEventTransform
  implements EventTransform<SessionEvent>
{
  static final String ADVERTISING_ID_KEY = "advertisingId";
  static final String ANDROID_ID_KEY = "androidId";
  static final String APP_BUNDLE_ID_KEY = "appBundleId";
  static final String APP_VERSION_CODE_KEY = "appVersionCode";
  static final String APP_VERSION_NAME_KEY = "appVersionName";
  static final String BETA_DEVICE_TOKEN_KEY = "betaDeviceToken";
  static final String BUILD_ID_KEY = "buildId";
  static final String CUSTOM_ATTRIBUTES = "customAttributes";
  static final String CUSTOM_TYPE = "customType";
  static final String DETAILS_KEY = "details";
  static final String DEVICE_MODEL_KEY = "deviceModel";
  static final String EXECUTION_ID_KEY = "executionId";
  static final String INSTALLATION_ID_KEY = "installationId";
  static final String LIMIT_AD_TRACKING_ENABLED_KEY = "limitAdTrackingEnabled";
  static final String OS_VERSION_KEY = "osVersion";
  static final String PREDEFINED_ATTRIBUTES = "predefinedAttributes";
  static final String PREDEFINED_TYPE = "predefinedType";
  static final String TIMESTAMP_KEY = "timestamp";
  static final String TYPE_KEY = "type";
  
  @TargetApi(9)
  public JSONObject buildJsonForEvent(SessionEvent paramSessionEvent)
    throws IOException
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      SessionEventMetadata localSessionEventMetadata = paramSessionEvent.sessionEventMetadata;
      localJSONObject.put("appBundleId", localSessionEventMetadata.appBundleId);
      localJSONObject.put("executionId", localSessionEventMetadata.executionId);
      localJSONObject.put("installationId", localSessionEventMetadata.installationId);
      localJSONObject.put("androidId", localSessionEventMetadata.androidId);
      localJSONObject.put("advertisingId", localSessionEventMetadata.advertisingId);
      localJSONObject.put("limitAdTrackingEnabled", localSessionEventMetadata.limitAdTrackingEnabled);
      localJSONObject.put("betaDeviceToken", localSessionEventMetadata.betaDeviceToken);
      localJSONObject.put("buildId", localSessionEventMetadata.buildId);
      localJSONObject.put("osVersion", localSessionEventMetadata.osVersion);
      localJSONObject.put("deviceModel", localSessionEventMetadata.deviceModel);
      localJSONObject.put("appVersionCode", localSessionEventMetadata.appVersionCode);
      localJSONObject.put("appVersionName", localSessionEventMetadata.appVersionName);
      localJSONObject.put("timestamp", paramSessionEvent.timestamp);
      localJSONObject.put("type", paramSessionEvent.type.toString());
      localJSONObject.put("details", new JSONObject(paramSessionEvent.details));
      localJSONObject.put("customType", paramSessionEvent.customType);
      localJSONObject.put("customAttributes", new JSONObject(paramSessionEvent.customAttributes));
      localJSONObject.put("predefinedType", paramSessionEvent.predefinedType);
      localJSONObject.put("predefinedAttributes", new JSONObject(paramSessionEvent.predefinedAttributes));
      return localJSONObject;
    }
    catch (JSONException paramSessionEvent)
    {
      if (Build.VERSION.SDK_INT >= 9) {
        throw new IOException(paramSessionEvent.getMessage(), paramSessionEvent);
      }
      throw new IOException(paramSessionEvent.getMessage());
    }
  }
  
  public byte[] toBytes(SessionEvent paramSessionEvent)
    throws IOException
  {
    paramSessionEvent = buildJsonForEvent(paramSessionEvent);
    if (!(paramSessionEvent instanceof JSONObject)) {}
    for (paramSessionEvent = paramSessionEvent.toString();; paramSessionEvent = JSONObjectInstrumentation.toString((JSONObject)paramSessionEvent)) {
      return paramSessionEvent.getBytes("UTF-8");
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/SessionEventTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */