package com.gojek.app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.appsflyer.AppsFlyerLib;
import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.gojek.app.gcm.GcmUtil;
import com.gojek.app.gcm.GcmUtil.OnGcmRegisteredListener;
import com.gojek.app.json.JsonCallback;
import com.gojek.app.json.VolleyClient;
import com.gojek.app.model.CheckUpdateResponse;
import com.gojek.app.model.CustomerPromo;
import com.gojek.app.model.CustomerSaved;
import com.gojek.app.util.AdvertisingIdClient;
import com.gojek.app.util.AdvertisingIdClient.AdInfo;
import com.gojek.app.util.Util;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class Splash
  extends GojekActivityBase
{
  private static final String TAG = Splash.class.getSimpleName();
  private static final String UPDATER_TOKEN = "InD0AmN/O6C26dzriIatE78RSJ0a24SAs4tgKIpa5Pg=";
  private final String GOBOX_SENDER_ID = "56154713749";
  private final int REQUEST_CODE_ASK_PERMISSIONS = 123;
  private final String SENDER_ID = "323832313547";
  private int UPDATER_TIMEOUT = 2000;
  private String advertisingId = "";
  private GoogleCloudMessaging gcm;
  private String goboxRegId;
  private ImageView mIVLogo;
  private String regId;
  
  private void checkPromo()
  {
    VolleyClient.getInstance(this).get(TAG, "http://104.155.199.9/api/v1/gojek/featured.json", new JsonCallback()
    {
      public void onError(VolleyError paramAnonymousVolleyError)
      {
        Log.i(Splash.TAG, "error got customer promo " + paramAnonymousVolleyError);
        Splash.this.openActivity();
      }
      
      public void onResponse(CustomerPromo paramAnonymousCustomerPromo)
      {
        Log.i(Splash.TAG, "got customer promo response " + paramAnonymousCustomerPromo);
        Log.i(Splash.TAG, "got customer promo title " + paramAnonymousCustomerPromo.getTitle());
        if ((paramAnonymousCustomerPromo.getTitle() != null) && (paramAnonymousCustomerPromo.getContent() != null))
        {
          Splash.this.openActivity(Promo.class, paramAnonymousCustomerPromo);
          return;
        }
        Splash.this.openActivity();
      }
    }, CustomerPromo.class);
  }
  
  private void checkUpdate()
  {
    Object localObject1 = new CustomerSaved(this);
    Object localObject2 = Build.VERSION.RELEASE;
    String str3 = Build.MANUFACTURER + " " + Build.MODEL;
    String str4 = Util.getAppVersionName(this);
    String str5 = Util.getUniqueId(this);
    String str6 = this.advertisingId;
    String str7 = Util.getDeviceIMEI(this);
    String str1;
    if (((CustomerSaved)localObject1).getCustomerId() == null) {
      str1 = "";
    }
    for (;;)
    {
      JSONObject localJSONObject;
      if (((CustomerSaved)localObject1).getEmail() == null)
      {
        localObject1 = "";
        localJSONObject = new JSONObject();
      }
      try
      {
        localJSONObject.putOpt("os", "android");
        localJSONObject.putOpt("os_version", localObject2);
        localJSONObject.putOpt("device", str3);
        localJSONObject.putOpt("app_version", str4);
        localJSONObject.putOpt("unique_id", str5);
        localJSONObject.putOpt("advertisement_id", str6);
        localJSONObject.putOpt("imei", str7);
        localJSONObject.putOpt("customer_id", str1);
        localJSONObject.putOpt("customer_email", localObject1);
        localObject1 = TAG;
        localObject2 = new StringBuilder().append("Check update body= ");
        if (!(localJSONObject instanceof JSONObject))
        {
          str1 = localJSONObject.toString();
          Log.e((String)localObject1, str1);
          VolleyClient.getInstance(this, this.UPDATER_TIMEOUT).post("https://api.gojek.co.id/gojek/app-version", localJSONObject, new JsonCallback()
          {
            public void onError(VolleyError paramAnonymousVolleyError)
            {
              Log.e(Splash.TAG, "check update error, " + paramAnonymousVolleyError.getMessage());
              Splash.this.checkPromo();
            }
            
            public void onResponse(CheckUpdateResponse paramAnonymousCheckUpdateResponse)
            {
              Log.e(Splash.TAG, "check update success, get data= " + paramAnonymousCheckUpdateResponse.toString());
              switch (paramAnonymousCheckUpdateResponse.getCode())
              {
              default: 
                Splash.this.checkPromo();
                return;
              case 0: 
                Log.d(Splash.TAG, "GO-JEK is up to date.");
                Splash.this.checkPromo();
                return;
              case 1: 
                Log.d(Splash.TAG, "GO-JEK update available.");
                Splash.this.openUpdater(1);
                return;
              }
              Log.d(Splash.TAG, "Should update GO-JEK app!");
              Splash.this.openUpdater(2);
            }
          }, CheckUpdateResponse.class, "InD0AmN/O6C26dzriIatE78RSJ0a24SAs4tgKIpa5Pg=");
          return;
          str1 = ((CustomerSaved)localObject1).getCustomerId();
          continue;
          localObject1 = ((CustomerSaved)localObject1).getEmail();
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localJSONException.printStackTrace();
          continue;
          String str2 = JSONObjectInstrumentation.toString((JSONObject)localJSONObject);
        }
      }
    }
  }
  
  private void initAdvertisingId()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          AdvertisingIdClient.AdInfo localAdInfo = AdvertisingIdClient.getAdvertisingIdInfo(Splash.this);
          Splash.access$702(Splash.this, localAdInfo.getId());
          Splash.this.checkUpdate();
          return;
        }
        catch (Exception localException)
        {
          Splash.this.checkUpdate();
        }
      }
    }).start();
  }
  
  private boolean isServiceAvailable()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i == 0) {
      return true;
    }
    GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
    return false;
  }
  
  private void openActivity()
  {
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        Intent localIntent = new Intent(Splash.this, Home.class);
        Splash.this.startActivity(localIntent);
        Splash.this.finish();
      }
    }, 2000L);
  }
  
  private void openActivity(final Class paramClass, final Parcelable paramParcelable)
  {
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        Intent localIntent = new Intent(Splash.this, paramClass);
        if (paramParcelable != null) {
          localIntent.putExtra("SPLASH_DATA", paramParcelable);
        }
        Splash.this.startActivity(localIntent);
        Splash.this.finish();
      }
    }, 2000L);
  }
  
  private void openUpdater(final int paramInt)
  {
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        Intent localIntent = new Intent(Splash.this, Updater.class);
        localIntent.putExtra("updater", paramInt);
        Splash.this.startActivity(localIntent);
        Splash.this.finish();
      }
    }, 2000L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    FacebookSdk.sdkInitialize(getApplicationContext());
    AppEventsLogger.activateApp(getApplicationContext(), getString(2131165535));
    Fabric.with(this, new Kit[] { new Crashlytics() });
    AppsFlyerLib.setAppsFlyerKey("nn3gEyFppeARRaPRuyKx8R");
    AppsFlyerLib.sendTracking(getApplicationContext());
    setContentView(2130968834);
    this.mIVLogo = ((ImageView)findViewById(2131625026));
    this.regId = GcmUtil.getRegistrationId(this);
    this.goboxRegId = GcmUtil.getGoboxRegistrationId(this);
    if ((TextUtils.isEmpty(this.regId)) && (TextUtils.isEmpty(this.goboxRegId)))
    {
      if (isServiceAvailable())
      {
        this.gcm = GoogleCloudMessaging.getInstance(this);
        GcmUtil.registerGcm(this.gcm, "323832313547", new GcmUtil.OnGcmRegisteredListener()
        {
          public void onRegister(String paramAnonymousString)
          {
            Splash.access$002(Splash.this, paramAnonymousString);
            GcmUtil.storeRegistrationId(Splash.this, Splash.this.regId);
            Log.i(Splash.TAG, "GCM REG ID : " + Splash.this.regId);
            if (TextUtils.isEmpty(Splash.this.goboxRegId)) {
              GcmUtil.registerGoboxGcm(Splash.this.gcm, "56154713749", new Splash.1.1(this));
            }
          }
        });
      }
      return;
    }
    Log.i(TAG, "GCM REG ID STORED: " + this.regId);
    Log.i(TAG, "GOBOX GCM REG ID STORED: " + this.goboxRegId);
    initAdvertisingId();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/Splash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */