package lib.gojek.base.helper;

import android.app.Activity;
import android.content.Intent;

public class SignUpHelper
{
  public static final String NEED_SIGN_IN = "NEED_SIGN_IN";
  public static final int SIGN_IN_REQ_CODE = 123;
  public static final String SIGN_UP_INTENT_FILTER = "com.gojek.app.SIGN_UP";
  private Activity activity;
  
  public SignUpHelper(Activity paramActivity)
  {
    this.activity = paramActivity;
  }
  
  public boolean onActivityForResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    return 123 == paramInt1;
  }
  
  public void openSignUpPage()
  {
    Intent localIntent = new Intent("com.gojek.app.SIGN_UP");
    localIntent.putExtra("NEED_SIGN_IN", true);
    this.activity.startActivityForResult(localIntent, 123);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/SignUpHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */