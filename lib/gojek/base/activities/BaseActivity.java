package lib.gojek.base.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.AbstractBaseActivity;
import lib.gojek.base.R.drawable;
import lib.gojek.base.R.id;
import lib.gojek.base.helper.FontFaceHelper;

public abstract class BaseActivity
  extends AbstractBaseActivity
{
  private TextView titleBar;
  private Toolbar toolbar;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.toolbar = ((Toolbar)findViewById(R.id.toolbar));
    if (this.toolbar != null)
    {
      TypefaceHelper.typeface(this.toolbar, FontFaceHelper.getBebasNeue());
      this.titleBar = ((TextView)this.toolbar.findViewById(R.id.title));
      this.toolbar.setLogo(R.drawable.base_ic_actionbar);
      setSupportActionBar(this.toolbar);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
  }
  
  public void setTitleActionBar(String paramString)
  {
    if ((this.titleBar != null) && (paramString != null)) {
      this.titleBar.setText(paramString.toUpperCase());
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/activities/BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */