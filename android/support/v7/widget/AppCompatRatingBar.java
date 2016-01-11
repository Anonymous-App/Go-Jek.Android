package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class AppCompatRatingBar
  extends RatingBar
{
  private AppCompatProgressBarHelper mAppCompatProgressBarHelper;
  private TintManager mTintManager;
  
  public AppCompatRatingBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatRatingBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.ratingBarStyle);
  }
  
  public AppCompatRatingBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mTintManager = TintManager.get(paramContext);
    this.mAppCompatProgressBarHelper = new AppCompatProgressBarHelper(this, this.mTintManager);
    this.mAppCompatProgressBarHelper.loadFromAttributes(paramAttributeSet, paramInt);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    try
    {
      super.onMeasure(paramInt1, paramInt2);
      Bitmap localBitmap = this.mAppCompatProgressBarHelper.getSampleTime();
      if (localBitmap != null) {
        setMeasuredDimension(ViewCompat.resolveSizeAndState(localBitmap.getWidth() * getNumStars(), paramInt1, 0), getMeasuredHeight());
      }
      return;
    }
    finally {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/AppCompatRatingBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */