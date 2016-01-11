package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SwipeDismissBehavior<V extends View>
  extends CoordinatorLayout.Behavior<V>
{
  private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5F;
  private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0F;
  private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5F;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_IDLE = 0;
  public static final int STATE_SETTLING = 2;
  public static final int SWIPE_DIRECTION_ANY = 2;
  public static final int SWIPE_DIRECTION_END_TO_START = 1;
  public static final int SWIPE_DIRECTION_START_TO_END = 0;
  private float mAlphaEndSwipeDistance = 0.5F;
  private float mAlphaStartSwipeDistance = 0.0F;
  private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback()
  {
    private int mOriginalCapturedViewLeft;
    
    private boolean shouldDismiss(View paramAnonymousView, float paramAnonymousFloat)
    {
      int i;
      if (paramAnonymousFloat != 0.0F) {
        if (ViewCompat.getLayoutDirection(paramAnonymousView) == 1)
        {
          i = 1;
          if (SwipeDismissBehavior.this.mSwipeDirection != 2) {
            break label34;
          }
        }
      }
      label34:
      label56:
      label64:
      label87:
      int j;
      int k;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return true;
                i = 0;
                break;
                if (SwipeDismissBehavior.this.mSwipeDirection != 0) {
                  break label64;
                }
                if (i == 0) {
                  break label56;
                }
              } while (paramAnonymousFloat < 0.0F);
              return false;
            } while (paramAnonymousFloat > 0.0F);
            return false;
            if (SwipeDismissBehavior.this.mSwipeDirection != 1) {
              break label138;
            }
            if (i == 0) {
              break label87;
            }
          } while (paramAnonymousFloat > 0.0F);
          return false;
        } while (paramAnonymousFloat < 0.0F);
        return false;
        i = paramAnonymousView.getLeft();
        j = this.mOriginalCapturedViewLeft;
        k = Math.round(paramAnonymousView.getWidth() * SwipeDismissBehavior.this.mDragDismissThreshold);
      } while (Math.abs(i - j) >= k);
      return false;
      label138:
      return false;
    }
    
    public int clampViewPositionHorizontal(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      int i;
      if (ViewCompat.getLayoutDirection(paramAnonymousView) == 1)
      {
        paramAnonymousInt2 = 1;
        if (SwipeDismissBehavior.this.mSwipeDirection != 0) {
          break label72;
        }
        if (paramAnonymousInt2 == 0) {
          break label53;
        }
        i = this.mOriginalCapturedViewLeft - paramAnonymousView.getWidth();
        paramAnonymousInt2 = this.mOriginalCapturedViewLeft;
      }
      for (;;)
      {
        return SwipeDismissBehavior.clamp(i, paramAnonymousInt1, paramAnonymousInt2);
        paramAnonymousInt2 = 0;
        break;
        label53:
        i = this.mOriginalCapturedViewLeft;
        paramAnonymousInt2 = this.mOriginalCapturedViewLeft + paramAnonymousView.getWidth();
        continue;
        label72:
        if (SwipeDismissBehavior.this.mSwipeDirection == 1)
        {
          if (paramAnonymousInt2 != 0)
          {
            i = this.mOriginalCapturedViewLeft;
            paramAnonymousInt2 = this.mOriginalCapturedViewLeft + paramAnonymousView.getWidth();
          }
          else
          {
            i = this.mOriginalCapturedViewLeft - paramAnonymousView.getWidth();
            paramAnonymousInt2 = this.mOriginalCapturedViewLeft;
          }
        }
        else
        {
          i = this.mOriginalCapturedViewLeft - paramAnonymousView.getWidth();
          paramAnonymousInt2 = this.mOriginalCapturedViewLeft + paramAnonymousView.getWidth();
        }
      }
    }
    
    public int clampViewPositionVertical(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return paramAnonymousView.getTop();
    }
    
    public int getViewHorizontalDragRange(View paramAnonymousView)
    {
      return paramAnonymousView.getWidth();
    }
    
    public void onViewCaptured(View paramAnonymousView, int paramAnonymousInt)
    {
      this.mOriginalCapturedViewLeft = paramAnonymousView.getLeft();
    }
    
    public void onViewDragStateChanged(int paramAnonymousInt)
    {
      if (SwipeDismissBehavior.this.mListener != null) {
        SwipeDismissBehavior.this.mListener.onDragStateChanged(paramAnonymousInt);
      }
    }
    
    public void onViewPositionChanged(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      float f1 = this.mOriginalCapturedViewLeft + paramAnonymousView.getWidth() * SwipeDismissBehavior.this.mAlphaStartSwipeDistance;
      float f2 = this.mOriginalCapturedViewLeft + paramAnonymousView.getWidth() * SwipeDismissBehavior.this.mAlphaEndSwipeDistance;
      if (paramAnonymousInt1 <= f1)
      {
        ViewCompat.setAlpha(paramAnonymousView, 1.0F);
        return;
      }
      if (paramAnonymousInt1 >= f2)
      {
        ViewCompat.setAlpha(paramAnonymousView, 0.0F);
        return;
      }
      ViewCompat.setAlpha(paramAnonymousView, SwipeDismissBehavior.clamp(0.0F, 1.0F - SwipeDismissBehavior.fraction(f1, f2, paramAnonymousInt1), 1.0F));
    }
    
    public void onViewReleased(View paramAnonymousView, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      int i = paramAnonymousView.getWidth();
      boolean bool = false;
      if (shouldDismiss(paramAnonymousView, paramAnonymousFloat1)) {
        if (paramAnonymousView.getLeft() < this.mOriginalCapturedViewLeft)
        {
          i = this.mOriginalCapturedViewLeft - i;
          bool = true;
          label41:
          if (!SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(i, paramAnonymousView.getTop())) {
            break label100;
          }
          ViewCompat.postOnAnimation(paramAnonymousView, new SwipeDismissBehavior.SettleRunnable(SwipeDismissBehavior.this, paramAnonymousView, bool));
        }
      }
      label100:
      while ((!bool) || (SwipeDismissBehavior.this.mListener == null))
      {
        return;
        i = this.mOriginalCapturedViewLeft + i;
        break;
        i = this.mOriginalCapturedViewLeft;
        break label41;
      }
      SwipeDismissBehavior.this.mListener.onDismiss(paramAnonymousView);
    }
    
    public boolean tryCaptureView(View paramAnonymousView, int paramAnonymousInt)
    {
      return SwipeDismissBehavior.this.canSwipeDismissView(paramAnonymousView);
    }
  };
  private float mDragDismissThreshold = 0.5F;
  private boolean mIgnoreEvents;
  private OnDismissListener mListener;
  private float mSensitivity = 0.0F;
  private boolean mSensitivitySet;
  private int mSwipeDirection = 2;
  private ViewDragHelper mViewDragHelper;
  
  private static float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.min(Math.max(paramFloat1, paramFloat2), paramFloat3);
  }
  
  private static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    return Math.min(Math.max(paramInt1, paramInt2), paramInt3);
  }
  
  private void ensureViewDragHelper(ViewGroup paramViewGroup)
  {
    if (this.mViewDragHelper == null) {
      if (!this.mSensitivitySet) {
        break label33;
      }
    }
    label33:
    for (paramViewGroup = ViewDragHelper.create(paramViewGroup, this.mSensitivity, this.mDragCallback);; paramViewGroup = ViewDragHelper.create(paramViewGroup, this.mDragCallback))
    {
      this.mViewDragHelper = paramViewGroup;
      return;
    }
  }
  
  static float fraction(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat3 - paramFloat1) / (paramFloat2 - paramFloat1);
  }
  
  public boolean canSwipeDismissView(@NonNull View paramView)
  {
    return true;
  }
  
  public int getDragState()
  {
    if (this.mViewDragHelper != null) {
      return this.mViewDragHelper.getViewDragState();
    }
    return 0;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    switch (MotionEventCompat.getActionMasked(paramMotionEvent))
    {
    case 2: 
    default: 
      if (paramCoordinatorLayout.isPointInChildBounds(paramV, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) {
        break;
      }
    }
    for (boolean bool = true;; bool = false)
    {
      this.mIgnoreEvents = bool;
      do
      {
        if (!this.mIgnoreEvents) {
          break;
        }
        return false;
      } while (!this.mIgnoreEvents);
      this.mIgnoreEvents = false;
      return false;
    }
    ensureViewDragHelper(paramCoordinatorLayout);
    return this.mViewDragHelper.shouldInterceptTouchEvent(paramMotionEvent);
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    if (this.mViewDragHelper != null)
    {
      this.mViewDragHelper.processTouchEvent(paramMotionEvent);
      return true;
    }
    return false;
  }
  
  public void setDragDismissDistance(float paramFloat)
  {
    this.mDragDismissThreshold = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setEndAlphaSwipeDistance(float paramFloat)
  {
    this.mAlphaEndSwipeDistance = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setListener(OnDismissListener paramOnDismissListener)
  {
    this.mListener = paramOnDismissListener;
  }
  
  public void setSensitivity(float paramFloat)
  {
    this.mSensitivity = paramFloat;
    this.mSensitivitySet = true;
  }
  
  public void setStartAlphaSwipeDistance(float paramFloat)
  {
    this.mAlphaStartSwipeDistance = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setSwipeDirection(int paramInt)
  {
    this.mSwipeDirection = paramInt;
  }
  
  public static abstract interface OnDismissListener
  {
    public abstract void onDismiss(View paramView);
    
    public abstract void onDragStateChanged(int paramInt);
  }
  
  private class SettleRunnable
    implements Runnable
  {
    private final boolean mDismiss;
    private final View mView;
    
    SettleRunnable(View paramView, boolean paramBoolean)
    {
      this.mView = paramView;
      this.mDismiss = paramBoolean;
    }
    
    public void run()
    {
      if ((SwipeDismissBehavior.this.mViewDragHelper != null) && (SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true))) {
        ViewCompat.postOnAnimation(this.mView, this);
      }
      while ((!this.mDismiss) || (SwipeDismissBehavior.this.mListener == null)) {
        return;
      }
      SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/SwipeDismissBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */