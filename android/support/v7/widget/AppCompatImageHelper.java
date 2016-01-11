package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

class AppCompatImageHelper
{
  private static final int[] VIEW_ATTRS = { 16843033 };
  private final TintManager mTintManager;
  private final ImageView mView;
  
  AppCompatImageHelper(ImageView paramImageView, TintManager paramTintManager)
  {
    this.mView = paramImageView;
    this.mTintManager = paramTintManager;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), paramAttributeSet, VIEW_ATTRS, paramInt, 0);
    try
    {
      if (paramAttributeSet.hasValue(0)) {
        this.mView.setImageDrawable(paramAttributeSet.getDrawable(0));
      }
      return;
    }
    finally
    {
      paramAttributeSet.recycle();
    }
  }
  
  void setImageResource(int paramInt)
  {
    if (paramInt != 0)
    {
      ImageView localImageView = this.mView;
      if (this.mTintManager != null) {}
      for (Drawable localDrawable = this.mTintManager.getDrawable(paramInt);; localDrawable = ContextCompat.getDrawable(this.mView.getContext(), paramInt))
      {
        localImageView.setImageDrawable(localDrawable);
        return;
      }
    }
    this.mView.setImageDrawable(null);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/AppCompatImageHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */