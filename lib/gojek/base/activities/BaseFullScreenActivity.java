package lib.gojek.base.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import lib.gojek.base.R.drawable;
import lib.gojek.base.R.id;
import lib.gojek.base.R.layout;
import uk.co.senab.photoview.PhotoViewAttacher;

public class BaseFullScreenActivity
  extends BaseActivity
{
  private RelativeLayout closeImage;
  private ImageView imgEvent;
  private String pathImage;
  private PhotoViewAttacher photoViewAttacher;
  
  private void bindIntent()
  {
    this.pathImage = getIntent().getExtras().getString("path_image");
  }
  
  private void bindViewById()
  {
    this.imgEvent = ((ImageView)findViewById(R.id.img_event));
    this.closeImage = ((RelativeLayout)findViewById(R.id.close_image));
    this.closeImage.setOnClickListener(BaseFullScreenActivity..Lambda.1.lambdaFactory$(this));
  }
  
  private void setImage()
  {
    if (!TextUtils.isEmpty(this.pathImage))
    {
      Glide.with(this).load(this.pathImage).asBitmap().placeholder(R.drawable.gotix_placeholder).error(R.drawable.gotix_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(new SimpleTarget()
      {
        public void onResourceReady(Bitmap paramAnonymousBitmap, GlideAnimation<? super Bitmap> paramAnonymousGlideAnimation)
        {
          BaseFullScreenActivity.this.imgEvent.setImageBitmap(paramAnonymousBitmap);
          BaseFullScreenActivity.access$102(BaseFullScreenActivity.this, new PhotoViewAttacher(BaseFullScreenActivity.this.imgEvent));
        }
      });
      return;
    }
    this.imgEvent.setImageResource(R.drawable.gotix_placeholder);
    this.photoViewAttacher = new PhotoViewAttacher(this.imgEvent);
  }
  
  protected int getLayout()
  {
    return R.layout.base_activity_gotix_fullscreen;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    bindViewById();
    bindIntent();
    setImage();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.photoViewAttacher != null) {
      this.photoViewAttacher.cleanup();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/activities/BaseFullScreenActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */