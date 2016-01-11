package com.gojek.app.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomScrollView
  extends ScrollView
{
  List<View> mInterceptScrollViews = new ArrayList();
  
  public CustomScrollView(Context paramContext)
  {
    super(paramContext);
  }
  
  public CustomScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CustomScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void addInterceptScrollView(View paramView)
  {
    this.mInterceptScrollViews.add(paramView);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mInterceptScrollViews.size() > 0)
    {
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      Rect localRect = new Rect();
      Iterator localIterator = this.mInterceptScrollViews.iterator();
      while (localIterator.hasNext())
      {
        ((View)localIterator.next()).getHitRect(localRect);
        if (localRect.contains(i, j)) {
          return false;
        }
      }
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public void removeInterceptScrollView(View paramView)
  {
    this.mInterceptScrollViews.remove(paramView);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/custom/CustomScrollView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */