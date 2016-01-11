package lib.gojek.base.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import lib.gojek.base.activities.BaseFullScreenActivity;

public class BaseFullScreenHelper
  implements View.OnClickListener
{
  private static final String COVER_IMAGE = "cover_image";
  public static final String PATH_IMAGE = "path_image";
  private Activity activity;
  private Context context;
  private String pathImage;
  
  public BaseFullScreenHelper(Context paramContext, Activity paramActivity)
  {
    this.context = paramContext;
    this.activity = paramActivity;
  }
  
  public BaseFullScreenHelper(String paramString, Context paramContext, Activity paramActivity)
  {
    this.pathImage = paramString;
    this.context = paramContext;
    this.activity = paramActivity;
  }
  
  @TargetApi(16)
  private void startFullscreen(View paramView)
  {
    if (!TextUtils.isEmpty(this.pathImage))
    {
      Intent localIntent = new Intent(this.context, BaseFullScreenActivity.class);
      localIntent.putExtra("path_image", this.pathImage);
      paramView = ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, paramView, "cover_image");
      this.context.startActivity(localIntent, paramView.toBundle());
    }
  }
  
  public void onClick(View paramView)
  {
    startFullscreen(paramView);
  }
  
  public void setPathImage(String paramString)
  {
    this.pathImage = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/BaseFullScreenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */