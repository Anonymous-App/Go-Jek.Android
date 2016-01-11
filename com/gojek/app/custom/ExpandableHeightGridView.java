package com.gojek.app.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class ExpandableHeightGridView
  extends GridView
{
  private static final String TAG = ExpandableHeightGridView.class.getSimpleName();
  boolean expanded = false;
  
  public ExpandableHeightGridView(Context paramContext)
  {
    super(paramContext);
  }
  
  public ExpandableHeightGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public ExpandableHeightGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean isExpanded()
  {
    return this.expanded;
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    if (isExpanded())
    {
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
      getLayoutParams().height = getMeasuredHeight();
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setExpanded(boolean paramBoolean)
  {
    this.expanded = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/custom/ExpandableHeightGridView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */