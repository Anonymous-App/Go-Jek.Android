package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

abstract class HeaderBehavior<V extends View>
  extends ViewOffsetBehavior<V>
{
  private static final int INVALID_POINTER = -1;
  private int mActivePointerId = -1;
  private Runnable mFlingRunnable;
  private boolean mIsBeingDragged;
  private int mLastMotionY;
  private ScrollerCompat mScroller;
  private int mTouchSlop = -1;
  private VelocityTracker mVelocityTracker;
  
  public HeaderBehavior() {}
  
  public HeaderBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void ensureVelocityTracker()
  {
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  boolean canDragView(V paramV)
  {
    return false;
  }
  
  final boolean fling(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, float paramFloat)
  {
    boolean bool = false;
    if (this.mFlingRunnable != null)
    {
      paramV.removeCallbacks(this.mFlingRunnable);
      this.mFlingRunnable = null;
    }
    if (this.mScroller == null) {
      this.mScroller = ScrollerCompat.create(paramV.getContext());
    }
    this.mScroller.fling(0, getTopAndBottomOffset(), 0, Math.round(paramFloat), 0, 0, paramInt1, paramInt2);
    if (this.mScroller.computeScrollOffset())
    {
      this.mFlingRunnable = new FlingRunnable(paramCoordinatorLayout, paramV);
      ViewCompat.postOnAnimation(paramV, this.mFlingRunnable);
      bool = true;
    }
    return bool;
  }
  
  int getMaxDragOffset(V paramV)
  {
    return -paramV.getHeight();
  }
  
  int getScrollRangeForDragFling(V paramV)
  {
    return paramV.getHeight();
  }
  
  int getTopBottomOffsetForScrollingSibling()
  {
    return getTopAndBottomOffset();
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (this.mTouchSlop < 0) {
      this.mTouchSlop = ViewConfiguration.get(paramCoordinatorLayout.getContext()).getScaledTouchSlop();
    }
    if ((paramMotionEvent.getAction() == 2) && (this.mIsBeingDragged)) {
      return true;
    }
    switch (MotionEventCompat.getActionMasked(paramMotionEvent))
    {
    }
    for (;;)
    {
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.addMovement(paramMotionEvent);
      }
      return this.mIsBeingDragged;
      this.mIsBeingDragged = false;
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if ((canDragView(paramV)) && (paramCoordinatorLayout.isPointInChildBounds(paramV, i, j)))
      {
        this.mLastMotionY = j;
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        ensureVelocityTracker();
        continue;
        i = this.mActivePointerId;
        if (i != -1)
        {
          i = MotionEventCompat.findPointerIndex(paramMotionEvent, i);
          if (i != -1)
          {
            i = (int)MotionEventCompat.getY(paramMotionEvent, i);
            if (Math.abs(i - this.mLastMotionY) > this.mTouchSlop)
            {
              this.mIsBeingDragged = true;
              this.mLastMotionY = i;
              continue;
              this.mIsBeingDragged = false;
              this.mActivePointerId = -1;
              if (this.mVelocityTracker != null)
              {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
              }
            }
          }
        }
      }
    }
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (this.mTouchSlop < 0) {
      this.mTouchSlop = ViewConfiguration.get(paramCoordinatorLayout.getContext()).getScaledTouchSlop();
    }
    switch (MotionEventCompat.getActionMasked(paramMotionEvent))
    {
    }
    for (;;)
    {
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.addMovement(paramMotionEvent);
      }
      return true;
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if ((paramCoordinatorLayout.isPointInChildBounds(paramV, i, j)) && (canDragView(paramV)))
      {
        this.mLastMotionY = j;
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        ensureVelocityTracker();
      }
      else
      {
        return false;
        i = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
        if (i == -1) {
          return false;
        }
        int k = (int)MotionEventCompat.getY(paramMotionEvent, i);
        j = this.mLastMotionY - k;
        i = j;
        if (!this.mIsBeingDragged)
        {
          i = j;
          if (Math.abs(j) > this.mTouchSlop)
          {
            this.mIsBeingDragged = true;
            if (j <= 0) {
              break label244;
            }
          }
        }
        label244:
        for (i = j - this.mTouchSlop; this.mIsBeingDragged; i = j + this.mTouchSlop)
        {
          this.mLastMotionY = k;
          scroll(paramCoordinatorLayout, paramV, i, getMaxDragOffset(paramV), 0);
          break;
        }
        if (this.mVelocityTracker != null)
        {
          this.mVelocityTracker.addMovement(paramMotionEvent);
          this.mVelocityTracker.computeCurrentVelocity(1000);
          float f = VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
          fling(paramCoordinatorLayout, paramV, -getScrollRangeForDragFling(paramV), 0, f);
        }
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
        if (this.mVelocityTracker != null)
        {
          this.mVelocityTracker.recycle();
          this.mVelocityTracker = null;
        }
      }
    }
  }
  
  final int scroll(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, int paramInt3)
  {
    return setHeaderTopBottomOffset(paramCoordinatorLayout, paramV, getTopBottomOffsetForScrollingSibling() - paramInt1, paramInt2, paramInt3);
  }
  
  int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt)
  {
    return setHeaderTopBottomOffset(paramCoordinatorLayout, paramV, paramInt, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, int paramInt3)
  {
    int k = getTopAndBottomOffset();
    int j = 0;
    int i = j;
    if (paramInt2 != 0)
    {
      i = j;
      if (k >= paramInt2)
      {
        i = j;
        if (k <= paramInt3)
        {
          paramInt1 = MathUtils.constrain(paramInt1, paramInt2, paramInt3);
          i = j;
          if (k != paramInt1)
          {
            setTopAndBottomOffset(paramInt1);
            i = k - paramInt1;
          }
        }
      }
    }
    return i;
  }
  
  private class FlingRunnable
    implements Runnable
  {
    private final V mLayout;
    private final CoordinatorLayout mParent;
    
    FlingRunnable(V paramV)
    {
      this.mParent = paramV;
      View localView;
      this.mLayout = localView;
    }
    
    public void run()
    {
      if ((this.mLayout != null) && (HeaderBehavior.this.mScroller != null) && (HeaderBehavior.this.mScroller.computeScrollOffset()))
      {
        HeaderBehavior.this.setHeaderTopBottomOffset(this.mParent, this.mLayout, HeaderBehavior.this.mScroller.getCurrY());
        ViewCompat.postOnAnimation(this.mLayout, this);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/HeaderBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */