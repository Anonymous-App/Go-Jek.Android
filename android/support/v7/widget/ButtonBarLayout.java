package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class ButtonBarLayout
  extends LinearLayout
{
  private boolean mAllowStacking;
  private int mLastWidthSize = -1;
  
  public ButtonBarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ButtonBarLayout);
    this.mAllowStacking = paramContext.getBoolean(R.styleable.ButtonBarLayout_allowStacking, false);
    paramContext.recycle();
  }
  
  private boolean isStacked()
  {
    return getOrientation() == 1;
  }
  
  private void setStacked(boolean paramBoolean)
  {
    label17:
    View localView;
    if (paramBoolean)
    {
      i = 1;
      setOrientation(i);
      if (!paramBoolean) {
        break label78;
      }
      i = 5;
      setGravity(i);
      localView = findViewById(R.id.spacer);
      if (localView != null) {
        if (!paramBoolean) {
          break label84;
        }
      }
    }
    label78:
    label84:
    for (int i = 8;; i = 4)
    {
      localView.setVisibility(i);
      i = getChildCount() - 2;
      while (i >= 0)
      {
        bringChildToFront(getChildAt(i));
        i -= 1;
      }
      i = 0;
      break;
      i = 80;
      break label17;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.getSize(paramInt1);
    if (this.mAllowStacking)
    {
      if ((j > this.mLastWidthSize) && (isStacked())) {
        setStacked(false);
      }
      this.mLastWidthSize = j;
    }
    int i = 0;
    if ((!isStacked()) && (View.MeasureSpec.getMode(paramInt1) == 1073741824))
    {
      j = View.MeasureSpec.makeMeasureSpec(j, Integer.MIN_VALUE);
      i = 1;
    }
    for (;;)
    {
      super.onMeasure(j, paramInt2);
      j = i;
      if (this.mAllowStacking)
      {
        j = i;
        if (!isStacked())
        {
          j = i;
          if ((getMeasuredWidthAndState() & 0xFF000000) == 16777216)
          {
            setStacked(true);
            j = 1;
          }
        }
      }
      if (j != 0) {
        super.onMeasure(paramInt1, paramInt2);
      }
      return;
      j = paramInt1;
    }
  }
  
  public void setAllowStacking(boolean paramBoolean)
  {
    if (this.mAllowStacking != paramBoolean)
    {
      this.mAllowStacking = paramBoolean;
      if ((!this.mAllowStacking) && (getOrientation() == 1)) {
        setStacked(false);
      }
      requestLayout();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/ButtonBarLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */