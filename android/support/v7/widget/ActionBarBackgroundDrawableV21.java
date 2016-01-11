package android.support.v7.widget;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

class ActionBarBackgroundDrawableV21
  extends ActionBarBackgroundDrawable
{
  public ActionBarBackgroundDrawableV21(ActionBarContainer paramActionBarContainer)
  {
    super(paramActionBarContainer);
  }
  
  public void getOutline(@NonNull Outline paramOutline)
  {
    if (this.mContainer.mIsSplit) {
      if (this.mContainer.mSplitBackground != null) {
        this.mContainer.mSplitBackground.getOutline(paramOutline);
      }
    }
    while (this.mContainer.mBackground == null) {
      return;
    }
    this.mContainer.mBackground.getOutline(paramOutline);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/ActionBarBackgroundDrawableV21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */