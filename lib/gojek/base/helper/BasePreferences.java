package lib.gojek.base.helper;

import android.text.TextUtils;

public class BasePreferences
{
  private static final String ACCESS_TOKEN = "accessToken";
  private static final String BEARER = "Bearer ";
  private static final String EXPIRES_IN = "expiresIn";
  private static final String REFRESH_TOKEN = "refreshToken";
  private static final String USER_EMAIL = "email";
  private static final String USER_ID = "id";
  private static final String USER_NAME = "name";
  private static final String USER_PHONE = "phone";
  
  public static String getAccessToken()
  {
    return GojekDataHelper.getString("accessToken");
  }
  
  public static String getBearerAccessToken()
  {
    return "Bearer " + GojekDataHelper.getString("accessToken");
  }
  
  public static String getCustomerEmail()
  {
    return GojekDataHelper.getString("email");
  }
  
  public static int getCustomerId()
  {
    if (TextUtils.isEmpty(GojekDataHelper.getString("id"))) {
      return 0;
    }
    return Integer.valueOf(GojekDataHelper.getString("id")).intValue();
  }
  
  public static String getCustomerName()
  {
    return GojekDataHelper.getString("name");
  }
  
  public static String getCustomerPhone()
  {
    return GojekDataHelper.getString("phone");
  }
  
  public static long getExpiresIn()
  {
    return GojekDataHelper.getLong("expiresIn");
  }
  
  public static String getRefreshToken()
  {
    return GojekDataHelper.getString("refreshToken");
  }
  
  public static boolean isLoggedIn()
  {
    return getCustomerId() > 0;
  }
  
  public static void saveAccessToken(String paramString)
  {
    GojekDataHelper.saveData("accessToken", paramString);
  }
  
  public static void saveExpiresIn(long paramLong)
  {
    GojekDataHelper.saveData("expiresIn", paramLong);
  }
  
  public static void saveRefreshToken(String paramString)
  {
    GojekDataHelper.saveData("refreshToken", paramString);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/BasePreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */