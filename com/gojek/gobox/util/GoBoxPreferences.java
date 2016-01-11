package com.gojek.gobox.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.gojek.gobox.model.CargoTypeResponse;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;

public class GoBoxPreferences
{
  private static final String ACCESS_TOKEN_KEY = "accessToken";
  private static final String BEARER = "Bearer ";
  public static final String CALL_CENTER_KEY = "call center";
  private static final String CARGO_TYPE_KEY = "cargo type";
  public static final String EMAIL = "email";
  private static final String ESCROW_CEILING = "escrow ceiling";
  public static final String GO_BOX_GCM_REG_ID = "PROPERTY_GOBOX_REG_ID";
  private static final String IS_NEED_UPDATE_CARGOT_TYPE = "need update";
  private static final String MAX_ADDITIONAL_SHIPPER = "max additional shipper";
  private static final String MAX_DESTINATION_KEY = "max_destination";
  public static final String NAME = "name";
  public static final String PHONE = "phone";
  public static final String PROFILE_ID = "id";
  private static final String REFRESH_TOKEN_KEY = "refreshToken";
  private static final String USER_UUID_KEY = "user uuid";
  private Context mContext;
  
  public GoBoxPreferences(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public void clearContextReference()
  {
    this.mContext = null;
  }
  
  public String getBearerAccessToken()
  {
    return "Bearer " + PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("accessToken", "");
  }
  
  public String getCallCenterNumber()
  {
    return PreferencesHelper.getString(this.mContext, "call center", "");
  }
  
  public CargoTypeResponse getCargoTypeData()
  {
    Object localObject = new Gson();
    String str = PreferencesHelper.getString(this.mContext, "cargo type", "");
    if (!(localObject instanceof Gson)) {}
    for (localObject = ((Gson)localObject).fromJson(str, CargoTypeResponse.class);; localObject = GsonInstrumentation.fromJson((Gson)localObject, str, CargoTypeResponse.class)) {
      return (CargoTypeResponse)localObject;
    }
  }
  
  public String getEmail()
  {
    return PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("email", "");
  }
  
  public long getEscrowCeiling()
  {
    return PreferencesHelper.getLong(this.mContext, "escrow ceiling", 0L);
  }
  
  public String getGcmRegistrationId()
  {
    return PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("PROPERTY_GOBOX_REG_ID", "");
  }
  
  public int getMaxAdditionalShipper()
  {
    return PreferencesHelper.getInt(this.mContext, "max additional shipper", 1);
  }
  
  public int getMaxDestination()
  {
    return PreferencesHelper.getInt(this.mContext, "max_destination", 1);
  }
  
  public String getRefreshToken()
  {
    return PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("refreshToken", "");
  }
  
  public String getUserName()
  {
    return PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("name", "");
  }
  
  public String getUserPhone()
  {
    return PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("phone", "");
  }
  
  public String getUserUUID()
  {
    return PreferencesHelper.getPreferences(this.mContext, "GojekPref").getString("id", "");
  }
  
  public boolean isLoggedIn()
  {
    return !TextUtils.isEmpty(getUserUUID());
  }
  
  public boolean isNeedRefreshCargoTypeData()
  {
    return (TextUtils.isEmpty(PreferencesHelper.getString(this.mContext, "cargo type", ""))) && (PreferencesHelper.getBoolean(this.mContext, "need update", true));
  }
  
  public void saveAccessToken(String paramString)
  {
    PreferencesHelper.getEditor(this.mContext, "GojekPref").putString("accessToken", paramString).commit();
  }
  
  public void saveCallCenterNumber(String paramString)
  {
    PreferencesHelper.putString(this.mContext, "call center", paramString);
  }
  
  public void saveCargoTypeData(String paramString)
  {
    PreferencesHelper.putString(this.mContext, "cargo type", paramString);
    PreferencesHelper.putBoolean(this.mContext, "need update", false);
  }
  
  public void saveEscrowCeiling(long paramLong)
  {
    PreferencesHelper.putLong(this.mContext, "escrow ceiling", paramLong);
  }
  
  public void saveMaxAdditionalShipper(int paramInt)
  {
    PreferencesHelper.putInt(this.mContext, "max additional shipper", paramInt);
  }
  
  public void saveMaxDestination(int paramInt)
  {
    PreferencesHelper.putInt(this.mContext, "max_destination", paramInt);
  }
  
  public void saveRefreshToken(String paramString)
  {
    PreferencesHelper.getEditor(this.mContext, "GojekPref").putString("refreshToken", paramString).commit();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/GoBoxPreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */