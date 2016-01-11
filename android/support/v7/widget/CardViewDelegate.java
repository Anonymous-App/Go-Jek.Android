package android.support.v7.widget;

import android.graphics.drawable.Drawable;

abstract interface CardViewDelegate
{
  public abstract Drawable getBackground();
  
  public abstract boolean getPreventCornerOverlap();
  
  public abstract float getRadius();
  
  public abstract boolean getUseCompatPadding();
  
  public abstract void setBackgroundDrawable(Drawable paramDrawable);
  
  public abstract void setShadowPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/CardViewDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */