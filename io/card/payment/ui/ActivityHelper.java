package io.card.payment.ui;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.Window;
import android.widget.TextView;

public class ActivityHelper
{
  @TargetApi(11)
  private static boolean actionBarNonNull(Activity paramActivity)
  {
    return paramActivity.getActionBar() != null;
  }
  
  private static boolean actionBarSupported()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  @TargetApi(11)
  public static void addActionBarIfSupported(Activity paramActivity)
  {
    if (actionBarSupported()) {
      paramActivity.requestWindowFeature(8);
    }
  }
  
  public static boolean holoSupported()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  @TargetApi(14)
  private static void setActionBarHomeIcon(ActionBar paramActionBar, Drawable paramDrawable)
  {
    paramActionBar.setIcon(paramDrawable);
  }
  
  @TargetApi(11)
  public static void setActivityTheme(Activity paramActivity, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramActivity.getApplicationInfo().theme != 0))
    {
      paramActivity.setTheme(paramActivity.getApplicationInfo().theme);
      return;
    }
    if (holoSupported())
    {
      paramActivity.setTheme(16973934);
      return;
    }
    paramActivity.setTheme(16973836);
  }
  
  @TargetApi(11)
  public static void setFlagSecure(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      paramActivity.getWindow().addFlags(8192);
    }
  }
  
  @TargetApi(11)
  private static void setupActionBar(Activity paramActivity, String paramString, Drawable paramDrawable)
  {
    ActionBar localActionBar = paramActivity.getActionBar();
    localActionBar.setBackgroundDrawable(Appearance.ACTIONBAR_BACKGROUND);
    localActionBar.setTitle(paramString);
    paramActivity = (TextView)paramActivity.findViewById(Resources.getSystem().getIdentifier("action_bar_title", "id", "android"));
    if (paramActivity != null) {
      paramActivity.setTextColor(-1);
    }
    localActionBar.setDisplayHomeAsUpEnabled(false);
    if ((paramDrawable != null) && (Build.VERSION.SDK_INT >= 14))
    {
      setActionBarHomeIcon(localActionBar, paramDrawable);
      return;
    }
    localActionBar.setDisplayShowHomeEnabled(false);
  }
  
  public static void setupActionBarIfSupported(Activity paramActivity, TextView paramTextView, String paramString1, String paramString2, Drawable paramDrawable)
  {
    String str = paramString2;
    if (paramString2 == null) {
      str = "";
    }
    paramActivity.setTitle(str + paramString1);
    if ((actionBarSupported()) && (actionBarNonNull(paramActivity)))
    {
      setupActionBar(paramActivity, paramString1, paramDrawable);
      if (paramTextView != null) {
        paramTextView.setVisibility(8);
      }
    }
    while (paramTextView == null) {
      return;
    }
    paramTextView.setText(paramString1);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/ui/ActivityHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */