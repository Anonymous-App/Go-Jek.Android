package com.gojek.app.gobusway.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;

public class MyGridView
  extends GridView
{
  public MyGridView(Context paramContext)
  {
    super(paramContext);
  }
  
  public MyGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MyGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean isInEditMode()
  {
    return true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (getLayoutParams().height == -2) {
      paramInt2 = View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
    }
    super.onMeasure(paramInt1, paramInt2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/customview/MyGridView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */