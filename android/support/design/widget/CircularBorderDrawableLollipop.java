package android.support.design.widget;

import android.graphics.Outline;

class CircularBorderDrawableLollipop
  extends CircularBorderDrawable
{
  public void getOutline(Outline paramOutline)
  {
    copyBounds(this.mRect);
    paramOutline.setOval(this.mRect);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/CircularBorderDrawableLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */