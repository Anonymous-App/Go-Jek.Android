package com.gojek.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gojek.app.model.CustomerPromo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Promo
  extends GojekActivityBase
{
  private static final String TAG = Promo.class.getSimpleName();
  private ImageLoader imageLoader;
  private FrameLayout mFLPromo;
  private ImageView mIVPromo;
  private TextView mTVPromoContent;
  private TextView mTVPromoSubtitle;
  private TextView mTVPromoTitle;
  
  public void onBackPressed()
  {
    startActivity(new Intent(this, Home.class));
    finish();
  }
  
  @TargetApi(17)
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getString(2131165602));
    setContentView(2130968812);
    if (getSupportActionBar() != null)
    {
      getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(17170445)));
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      getSupportActionBar().setDisplayShowHomeEnabled(false);
      getSupportActionBar().setHomeButtonEnabled(false);
    }
    this.mTVPromoTitle = ((TextView)findViewById(2131624946));
    this.mTVPromoContent = ((TextView)findViewById(2131624947));
    this.mTVPromoSubtitle = ((TextView)findViewById(2131624948));
    this.mIVPromo = ((ImageView)findViewById(2131624945));
    this.mFLPromo = ((FrameLayout)findViewById(2131624944));
    paramBundle = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
    int i = paramBundle.heightPixels;
    i = paramBundle.widthPixels;
    this.mFLPromo.getLayoutParams().width = i;
    this.mFLPromo.getLayoutParams().height = (i / 4 * 3);
    if (getIntent().getParcelableExtra("SPLASH_DATA") != null)
    {
      paramBundle = (CustomerPromo)getIntent().getParcelableExtra("SPLASH_DATA");
      if ((paramBundle.getImageUrl() == null) || (paramBundle.getImageUrl().isEmpty())) {
        break label371;
      }
      Glide.with(getApplicationContext()).load(paramBundle.getImageUrl()).placeholder(2131558630).diskCacheStrategy(DiskCacheStrategy.ALL).error(2131558630).into(this.mIVPromo);
      if ((paramBundle.getTitle() == null) || (paramBundle.getTitle().isEmpty())) {
        break label394;
      }
      this.mTVPromoTitle.setText(Html.fromHtml(paramBundle.getTitle()));
      label308:
      if ((paramBundle.getContent() == null) || (paramBundle.getContent().isEmpty())) {
        break label415;
      }
      this.mTVPromoContent.setText(Html.fromHtml(paramBundle.getContent()));
    }
    for (;;)
    {
      if ((paramBundle.getSubtitle() == null) || (paramBundle.getSubtitle().isEmpty())) {
        break label436;
      }
      this.mTVPromoSubtitle.setText(Html.fromHtml(paramBundle.getSubtitle()));
      return;
      label371:
      findViewById(2131624944).setVisibility(8);
      this.mIVPromo.setVisibility(8);
      break;
      label394:
      this.mTVPromoTitle.setText("");
      this.mTVPromoTitle.setVisibility(8);
      break label308;
      label415:
      this.mTVPromoContent.setText("");
      this.mTVPromoContent.setVisibility(8);
    }
    label436:
    this.mTVPromoSubtitle.setText("");
    this.mTVPromoSubtitle.setVisibility(8);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 6)
    {
      startActivity(new Intent(this, Home.class));
      finish();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    paramMenu.add(0, 6, 0, getString(2131165601)).setIcon(2130837863);
    int i = 0;
    while (i < paramMenu.size())
    {
      MenuItemCompat.setShowAsAction(paramMenu.getItem(i), 2);
      i += 1;
    }
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/Promo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */