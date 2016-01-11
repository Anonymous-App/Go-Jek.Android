package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class AppBarLayout
  extends LinearLayout
{
  private static final int INVALID_SCROLL_RANGE = -1;
  private static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
  private static final int PENDING_ACTION_COLLAPSED = 2;
  private static final int PENDING_ACTION_EXPANDED = 1;
  private static final int PENDING_ACTION_NONE = 0;
  private int mDownPreScrollRange = -1;
  private int mDownScrollRange = -1;
  boolean mHaveChildWithInterpolator;
  private WindowInsetsCompat mLastInsets;
  private final List<OnOffsetChangedListener> mListeners;
  private int mPendingAction = 0;
  private float mTargetElevation;
  private int mTotalScrollRange = -1;
  
  public AppBarLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppBarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOrientation(1);
    ThemeUtils.checkAppCompatTheme(paramContext);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout);
    this.mTargetElevation = paramContext.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0);
    setBackgroundDrawable(paramContext.getDrawable(R.styleable.AppBarLayout_android_background));
    if (paramContext.hasValue(R.styleable.AppBarLayout_expanded)) {
      setExpanded(paramContext.getBoolean(R.styleable.AppBarLayout_expanded, false));
    }
    paramContext.recycle();
    ViewUtils.setBoundsViewOutlineProvider(this);
    this.mListeners = new ArrayList();
    ViewCompat.setElevation(this, this.mTargetElevation);
    ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
    {
      public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
      {
        AppBarLayout.this.setWindowInsets(paramAnonymousWindowInsetsCompat);
        return paramAnonymousWindowInsetsCompat.consumeSystemWindowInsets();
      }
    });
  }
  
  private int getDownNestedPreScrollRange()
  {
    if (this.mDownPreScrollRange != -1) {
      return this.mDownPreScrollRange;
    }
    int k = 0;
    int j = getChildCount() - 1;
    if (j >= 0)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      int i = localView.getMeasuredHeight();
      int m = localLayoutParams.mScrollFlags;
      if ((m & 0x5) == 5)
      {
        k += localLayoutParams.topMargin + localLayoutParams.bottomMargin;
        if ((m & 0x8) != 0) {
          i = k + ViewCompat.getMinimumHeight(localView);
        }
      }
      do
      {
        for (;;)
        {
          j -= 1;
          k = i;
          break;
          i = k + i;
        }
        i = k;
      } while (k <= 0);
    }
    this.mDownPreScrollRange = k;
    return k;
  }
  
  private int getDownNestedScrollRange()
  {
    if (this.mDownScrollRange != -1) {
      return this.mDownScrollRange;
    }
    int i = 0;
    int j = 0;
    int m = getChildCount();
    for (;;)
    {
      int k = i;
      if (j < m)
      {
        View localView = getChildAt(j);
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        int i1 = localView.getMeasuredHeight();
        int i2 = localLayoutParams.topMargin;
        int i3 = localLayoutParams.bottomMargin;
        int n = localLayoutParams.mScrollFlags;
        k = i;
        if ((n & 0x1) != 0)
        {
          i += i1 + (i2 + i3);
          if ((n & 0x2) == 0) {
            break label129;
          }
          k = i - (ViewCompat.getMinimumHeight(localView) + getTopInset());
        }
      }
      i = Math.max(0, k);
      this.mDownScrollRange = i;
      return i;
      label129:
      j += 1;
    }
  }
  
  private int getPendingAction()
  {
    return this.mPendingAction;
  }
  
  private int getTopInset()
  {
    if (this.mLastInsets != null) {
      return this.mLastInsets.getSystemWindowInsetTop();
    }
    return 0;
  }
  
  private int getUpNestedPreScrollRange()
  {
    return getTotalScrollRange();
  }
  
  private boolean hasChildWithInterpolator()
  {
    return this.mHaveChildWithInterpolator;
  }
  
  private boolean hasScrollableChildren()
  {
    return getTotalScrollRange() != 0;
  }
  
  private void invalidateScrollRanges()
  {
    this.mTotalScrollRange = -1;
    this.mDownPreScrollRange = -1;
    this.mDownScrollRange = -1;
  }
  
  private void resetPendingAction()
  {
    this.mPendingAction = 0;
  }
  
  private void setWindowInsets(WindowInsetsCompat paramWindowInsetsCompat)
  {
    this.mTotalScrollRange = -1;
    this.mLastInsets = paramWindowInsetsCompat;
    int i = 0;
    int j = getChildCount();
    for (;;)
    {
      if (i < j)
      {
        paramWindowInsetsCompat = ViewCompat.dispatchApplyWindowInsets(getChildAt(i), paramWindowInsetsCompat);
        if (!paramWindowInsetsCompat.isConsumed()) {}
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  public void addOnOffsetChangedListener(OnOffsetChangedListener paramOnOffsetChangedListener)
  {
    if ((paramOnOffsetChangedListener != null) && (!this.mListeners.contains(paramOnOffsetChangedListener))) {
      this.mListeners.add(paramOnOffsetChangedListener);
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-1, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LinearLayout.LayoutParams)) {
      return new LayoutParams((LinearLayout.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  final int getMinimumHeightForVisibleOverlappingContent()
  {
    int j = 0;
    int i;
    int k;
    if (this.mLastInsets != null)
    {
      i = this.mLastInsets.getSystemWindowInsetTop();
      k = ViewCompat.getMinimumHeight(this);
      if (k == 0) {
        break label39;
      }
      j = k * 2 + i;
    }
    label39:
    do
    {
      return j;
      i = 0;
      break;
      k = getChildCount();
    } while (k < 1);
    return ViewCompat.getMinimumHeight(getChildAt(k - 1)) * 2 + i;
  }
  
  public float getTargetElevation()
  {
    return this.mTargetElevation;
  }
  
  public final int getTotalScrollRange()
  {
    if (this.mTotalScrollRange != -1) {
      return this.mTotalScrollRange;
    }
    int i = 0;
    int j = 0;
    int m = getChildCount();
    for (;;)
    {
      int k = i;
      if (j < m)
      {
        View localView = getChildAt(j);
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        int i1 = localView.getMeasuredHeight();
        int n = localLayoutParams.mScrollFlags;
        k = i;
        if ((n & 0x1) != 0)
        {
          i += localLayoutParams.topMargin + i1 + localLayoutParams.bottomMargin;
          if ((n & 0x2) == 0) {
            break label121;
          }
          k = i - ViewCompat.getMinimumHeight(localView);
        }
      }
      i = Math.max(0, k - getTopInset());
      this.mTotalScrollRange = i;
      return i;
      label121:
      j += 1;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    invalidateScrollRanges();
    this.mHaveChildWithInterpolator = false;
    paramInt1 = 0;
    paramInt2 = getChildCount();
    for (;;)
    {
      if (paramInt1 < paramInt2)
      {
        if (((LayoutParams)getChildAt(paramInt1).getLayoutParams()).getScrollInterpolator() != null) {
          this.mHaveChildWithInterpolator = true;
        }
      }
      else {
        return;
      }
      paramInt1 += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    invalidateScrollRanges();
  }
  
  public void removeOnOffsetChangedListener(OnOffsetChangedListener paramOnOffsetChangedListener)
  {
    if (paramOnOffsetChangedListener != null) {
      this.mListeners.remove(paramOnOffsetChangedListener);
    }
  }
  
  public void setExpanded(boolean paramBoolean)
  {
    setExpanded(paramBoolean, ViewCompat.isLaidOut(this));
  }
  
  public void setExpanded(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i;
    if (paramBoolean1)
    {
      i = 1;
      if (!paramBoolean2) {
        break label31;
      }
    }
    label31:
    for (int j = 4;; j = 0)
    {
      this.mPendingAction = (j | i);
      requestLayout();
      return;
      i = 2;
      break;
    }
  }
  
  public void setOrientation(int paramInt)
  {
    if (paramInt != 1) {
      throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }
    super.setOrientation(paramInt);
  }
  
  public void setTargetElevation(float paramFloat)
  {
    this.mTargetElevation = paramFloat;
  }
  
  public static class Behavior
    extends HeaderBehavior<AppBarLayout>
  {
    private static final int ANIMATE_OFFSET_DIPS_PER_SECOND = 300;
    private static final int INVALID_POSITION = -1;
    private ValueAnimatorCompat mAnimator;
    private WeakReference<View> mLastNestedScrollingChildRef;
    private int mOffsetDelta;
    private int mOffsetToChildIndexOnLayout = -1;
    private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
    private float mOffsetToChildIndexOnLayoutPerc;
    private DragCallback mOnDragCallback;
    private boolean mSkipNestedPreScroll;
    private boolean mWasFlung;
    
    public Behavior() {}
    
    public Behavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    private void animateOffsetTo(final CoordinatorLayout paramCoordinatorLayout, final AppBarLayout paramAppBarLayout, int paramInt)
    {
      int i = getTopBottomOffsetForScrollingSibling();
      if (i == paramInt)
      {
        if ((this.mAnimator != null) && (this.mAnimator.isRunning())) {
          this.mAnimator.cancel();
        }
        return;
      }
      if (this.mAnimator == null)
      {
        this.mAnimator = ViewUtils.createAnimator();
        this.mAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        this.mAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener()
        {
          public void onAnimationUpdate(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
          {
            AppBarLayout.Behavior.this.setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, paramAnonymousValueAnimatorCompat.getAnimatedIntValue());
          }
        });
      }
      for (;;)
      {
        float f = Math.abs(i - paramInt) / paramCoordinatorLayout.getResources().getDisplayMetrics().density;
        this.mAnimator.setDuration(Math.round(1000.0F * f / 300.0F));
        this.mAnimator.setIntValues(i, paramInt);
        this.mAnimator.start();
        return;
        this.mAnimator.cancel();
      }
    }
    
    private void dispatchOffsetUpdates(AppBarLayout paramAppBarLayout)
    {
      List localList = paramAppBarLayout.mListeners;
      int i = 0;
      int j = localList.size();
      while (i < j)
      {
        AppBarLayout.OnOffsetChangedListener localOnOffsetChangedListener = (AppBarLayout.OnOffsetChangedListener)localList.get(i);
        if (localOnOffsetChangedListener != null) {
          localOnOffsetChangedListener.onOffsetChanged(paramAppBarLayout, getTopAndBottomOffset());
        }
        i += 1;
      }
    }
    
    private View getChildOnOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int i = 0;
      int j = paramAppBarLayout.getChildCount();
      while (i < j)
      {
        View localView = paramAppBarLayout.getChildAt(i);
        if ((localView.getTop() <= -paramInt) && (localView.getBottom() >= -paramInt)) {
          return localView;
        }
        i += 1;
      }
      return null;
    }
    
    private int interpolateOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int k = Math.abs(paramInt);
      int j = 0;
      int m = paramAppBarLayout.getChildCount();
      for (;;)
      {
        int i = paramInt;
        if (j < m)
        {
          View localView = paramAppBarLayout.getChildAt(j);
          AppBarLayout.LayoutParams localLayoutParams = (AppBarLayout.LayoutParams)localView.getLayoutParams();
          Interpolator localInterpolator = localLayoutParams.getScrollInterpolator();
          if ((k < localView.getTop()) || (k > localView.getBottom())) {
            break label203;
          }
          i = paramInt;
          if (localInterpolator != null)
          {
            i = 0;
            m = localLayoutParams.getScrollFlags();
            if ((m & 0x1) != 0)
            {
              j = 0 + (localView.getHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin);
              i = j;
              if ((m & 0x2) != 0) {
                i = j - ViewCompat.getMinimumHeight(localView);
              }
            }
            j = i;
            if (ViewCompat.getFitsSystemWindows(localView)) {
              j = i - paramAppBarLayout.getTopInset();
            }
            i = paramInt;
            if (j > 0)
            {
              i = localView.getTop();
              i = Math.round(j * localInterpolator.getInterpolation((k - i) / j));
              i = Integer.signum(paramInt) * (localView.getTop() + i);
            }
          }
        }
        return i;
        label203:
        j += 1;
      }
    }
    
    private void snapToChildIfNeeded(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      int m = getTopBottomOffsetForScrollingSibling();
      View localView = getChildOnOffset(paramAppBarLayout, m);
      int j;
      int i;
      if (localView != null)
      {
        AppBarLayout.LayoutParams localLayoutParams = (AppBarLayout.LayoutParams)localView.getLayoutParams();
        if ((localLayoutParams.getScrollFlags() & 0x11) == 17)
        {
          j = -localView.getTop();
          int k = -localView.getBottom();
          i = k;
          if ((localLayoutParams.getScrollFlags() & 0x2) == 2) {
            i = k + ViewCompat.getMinimumHeight(localView);
          }
          if (m >= (i + j) / 2) {
            break label110;
          }
        }
      }
      for (;;)
      {
        animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, MathUtils.constrain(i, -paramAppBarLayout.getTotalScrollRange(), 0));
        return;
        label110:
        i = j;
      }
    }
    
    boolean canDragView(AppBarLayout paramAppBarLayout)
    {
      boolean bool2 = true;
      boolean bool1;
      if (this.mOnDragCallback != null) {
        bool1 = this.mOnDragCallback.canDrag(paramAppBarLayout);
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (this.mLastNestedScrollingChildRef == null);
        paramAppBarLayout = (View)this.mLastNestedScrollingChildRef.get();
        if ((paramAppBarLayout == null) || (!paramAppBarLayout.isShown())) {
          break;
        }
        bool1 = bool2;
      } while (!ViewCompat.canScrollVertically(paramAppBarLayout, -1));
      return false;
    }
    
    int getMaxDragOffset(AppBarLayout paramAppBarLayout)
    {
      return -paramAppBarLayout.getDownNestedScrollRange();
    }
    
    int getScrollRangeForDragFling(AppBarLayout paramAppBarLayout)
    {
      return paramAppBarLayout.getTotalScrollRange();
    }
    
    int getTopBottomOffsetForScrollingSibling()
    {
      return getTopAndBottomOffset() + this.mOffsetDelta;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt)
    {
      boolean bool = super.onLayoutChild(paramCoordinatorLayout, paramAppBarLayout, paramInt);
      int i = paramAppBarLayout.getPendingAction();
      if (i != 0) {
        if ((i & 0x4) != 0)
        {
          paramInt = 1;
          if ((i & 0x2) == 0) {
            break label89;
          }
          i = -paramAppBarLayout.getUpNestedPreScrollRange();
          if (paramInt == 0) {
            break label77;
          }
          animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, i);
        }
      }
      label77:
      label89:
      while (this.mOffsetToChildIndexOnLayout < 0) {
        for (;;)
        {
          paramAppBarLayout.resetPendingAction();
          this.mOffsetToChildIndexOnLayout = -1;
          dispatchOffsetUpdates(paramAppBarLayout);
          return bool;
          paramInt = 0;
          continue;
          setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, i);
          continue;
          if ((i & 0x1) != 0) {
            if (paramInt != 0) {
              animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, 0);
            } else {
              setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, 0);
            }
          }
        }
      }
      paramCoordinatorLayout = paramAppBarLayout.getChildAt(this.mOffsetToChildIndexOnLayout);
      paramInt = -paramCoordinatorLayout.getBottom();
      if (this.mOffsetToChildIndexOnLayoutIsMinHeight) {
        paramInt += ViewCompat.getMinimumHeight(paramCoordinatorLayout);
      }
      for (;;)
      {
        setTopAndBottomOffset(paramInt);
        break;
        paramInt += Math.round(paramCoordinatorLayout.getHeight() * this.mOffsetToChildIndexOnLayoutPerc);
      }
    }
    
    public boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
    {
      boolean bool = false;
      if (!paramBoolean) {
        paramBoolean = fling(paramCoordinatorLayout, paramAppBarLayout, -paramAppBarLayout.getTotalScrollRange(), 0, -paramFloat2);
      }
      for (;;)
      {
        this.mWasFlung = paramBoolean;
        return paramBoolean;
        int i;
        if (paramFloat2 < 0.0F)
        {
          i = -paramAppBarLayout.getTotalScrollRange() + paramAppBarLayout.getDownNestedPreScrollRange();
          paramBoolean = bool;
          if (getTopBottomOffsetForScrollingSibling() < i)
          {
            animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, i);
            paramBoolean = true;
          }
        }
        else
        {
          i = -paramAppBarLayout.getUpNestedPreScrollRange();
          paramBoolean = bool;
          if (getTopBottomOffsetForScrollingSibling() > i)
          {
            animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, i);
            paramBoolean = true;
          }
        }
      }
    }
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
    {
      if ((paramInt2 != 0) && (!this.mSkipNestedPreScroll))
      {
        if (paramInt2 >= 0) {
          break label50;
        }
        paramInt1 = -paramAppBarLayout.getTotalScrollRange();
      }
      for (int i = paramInt1 + paramAppBarLayout.getDownNestedPreScrollRange();; i = 0)
      {
        paramArrayOfInt[1] = scroll(paramCoordinatorLayout, paramAppBarLayout, paramInt2, paramInt1, i);
        return;
        label50:
        paramInt1 = -paramAppBarLayout.getUpNestedPreScrollRange();
      }
    }
    
    public void onNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (paramInt4 < 0)
      {
        scroll(paramCoordinatorLayout, paramAppBarLayout, paramInt4, -paramAppBarLayout.getDownNestedScrollRange(), 0);
        this.mSkipNestedPreScroll = true;
        return;
      }
      this.mSkipNestedPreScroll = false;
    }
    
    public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, Parcelable paramParcelable)
    {
      if ((paramParcelable instanceof SavedState))
      {
        paramParcelable = (SavedState)paramParcelable;
        super.onRestoreInstanceState(paramCoordinatorLayout, paramAppBarLayout, paramParcelable.getSuperState());
        this.mOffsetToChildIndexOnLayout = paramParcelable.firstVisibleChildIndex;
        this.mOffsetToChildIndexOnLayoutPerc = paramParcelable.firstVisibileChildPercentageShown;
        this.mOffsetToChildIndexOnLayoutIsMinHeight = paramParcelable.firstVisibileChildAtMinimumHeight;
        return;
      }
      super.onRestoreInstanceState(paramCoordinatorLayout, paramAppBarLayout, paramParcelable);
      this.mOffsetToChildIndexOnLayout = -1;
    }
    
    public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      Parcelable localParcelable = super.onSaveInstanceState(paramCoordinatorLayout, paramAppBarLayout);
      int j = getTopAndBottomOffset();
      int i = 0;
      int k = paramAppBarLayout.getChildCount();
      while (i < k)
      {
        paramCoordinatorLayout = paramAppBarLayout.getChildAt(i);
        int m = paramCoordinatorLayout.getBottom() + j;
        if ((paramCoordinatorLayout.getTop() + j <= 0) && (m >= 0))
        {
          paramAppBarLayout = new SavedState(localParcelable);
          paramAppBarLayout.firstVisibleChildIndex = i;
          if (m == ViewCompat.getMinimumHeight(paramCoordinatorLayout)) {}
          for (boolean bool = true;; bool = false)
          {
            paramAppBarLayout.firstVisibileChildAtMinimumHeight = bool;
            paramAppBarLayout.firstVisibileChildPercentageShown = (m / paramCoordinatorLayout.getHeight());
            return paramAppBarLayout;
          }
        }
        i += 1;
      }
      return localParcelable;
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView1, View paramView2, int paramInt)
    {
      if (((paramInt & 0x2) != 0) && (paramAppBarLayout.hasScrollableChildren()) && (paramCoordinatorLayout.getHeight() - paramView1.getHeight() <= paramAppBarLayout.getHeight())) {}
      for (boolean bool = true;; bool = false)
      {
        if ((bool) && (this.mAnimator != null)) {
          this.mAnimator.cancel();
        }
        this.mLastNestedScrollingChildRef = null;
        return bool;
      }
    }
    
    public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView)
    {
      if (!this.mWasFlung) {
        snapToChildIfNeeded(paramCoordinatorLayout, paramAppBarLayout);
      }
      this.mSkipNestedPreScroll = false;
      this.mWasFlung = false;
      this.mLastNestedScrollingChildRef = new WeakReference(paramView);
    }
    
    public void setDragCallback(@Nullable DragCallback paramDragCallback)
    {
      this.mOnDragCallback = paramDragCallback;
    }
    
    int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, int paramInt3)
    {
      int k = getTopBottomOffsetForScrollingSibling();
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
            paramInt2 = MathUtils.constrain(paramInt1, paramInt2, paramInt3);
            i = j;
            if (k != paramInt2) {
              if (!paramAppBarLayout.hasChildWithInterpolator()) {
                break label123;
              }
            }
          }
        }
      }
      label123:
      for (paramInt1 = interpolateOffset(paramAppBarLayout, paramInt2);; paramInt1 = paramInt2)
      {
        boolean bool = setTopAndBottomOffset(paramInt1);
        i = k - paramInt2;
        this.mOffsetDelta = (paramInt2 - paramInt1);
        if ((!bool) && (paramAppBarLayout.hasChildWithInterpolator())) {
          paramCoordinatorLayout.dispatchDependentViewsChanged(paramAppBarLayout);
        }
        dispatchOffsetUpdates(paramAppBarLayout);
        return i;
      }
    }
    
    public static abstract class DragCallback
    {
      public abstract boolean canDrag(@NonNull AppBarLayout paramAppBarLayout);
    }
    
    protected static class SavedState
      extends View.BaseSavedState
    {
      public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
      {
        public AppBarLayout.Behavior.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
        {
          return new AppBarLayout.Behavior.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
        }
        
        public AppBarLayout.Behavior.SavedState[] newArray(int paramAnonymousInt)
        {
          return new AppBarLayout.Behavior.SavedState[paramAnonymousInt];
        }
      });
      boolean firstVisibileChildAtMinimumHeight;
      float firstVisibileChildPercentageShown;
      int firstVisibleChildIndex;
      
      public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        super();
        this.firstVisibleChildIndex = paramParcel.readInt();
        this.firstVisibileChildPercentageShown = paramParcel.readFloat();
        if (paramParcel.readByte() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          this.firstVisibileChildAtMinimumHeight = bool;
          return;
        }
      }
      
      public SavedState(Parcelable paramParcelable)
      {
        super();
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        super.writeToParcel(paramParcel, paramInt);
        paramParcel.writeInt(this.firstVisibleChildIndex);
        paramParcel.writeFloat(this.firstVisibileChildPercentageShown);
        if (this.firstVisibileChildAtMinimumHeight) {}
        for (paramInt = 1;; paramInt = 0)
        {
          paramParcel.writeByte((byte)paramInt);
          return;
        }
      }
    }
  }
  
  public static class LayoutParams
    extends LinearLayout.LayoutParams
  {
    static final int FLAG_QUICK_RETURN = 5;
    static final int FLAG_SNAP = 17;
    public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
    public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
    public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
    public static final int SCROLL_FLAG_SCROLL = 1;
    public static final int SCROLL_FLAG_SNAP = 16;
    int mScrollFlags = 1;
    Interpolator mScrollInterpolator;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(int paramInt1, int paramInt2, float paramFloat)
    {
      super(paramInt2, paramFloat);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppBarLayout_LayoutParams);
      this.mScrollFlags = paramAttributeSet.getInt(R.styleable.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
      if (paramAttributeSet.hasValue(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
        this.mScrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(paramContext, paramAttributeSet.getResourceId(R.styleable.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
      }
      paramAttributeSet.recycle();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      this.mScrollFlags = paramLayoutParams.mScrollFlags;
      this.mScrollInterpolator = paramLayoutParams.mScrollInterpolator;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public LayoutParams(LinearLayout.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public int getScrollFlags()
    {
      return this.mScrollFlags;
    }
    
    public Interpolator getScrollInterpolator()
    {
      return this.mScrollInterpolator;
    }
    
    public void setScrollFlags(int paramInt)
    {
      this.mScrollFlags = paramInt;
    }
    
    public void setScrollInterpolator(Interpolator paramInterpolator)
    {
      this.mScrollInterpolator = paramInterpolator;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface ScrollFlags {}
  }
  
  public static abstract interface OnOffsetChangedListener
  {
    public abstract void onOffsetChanged(AppBarLayout paramAppBarLayout, int paramInt);
  }
  
  public static class ScrollingViewBehavior
    extends HeaderScrollingViewBehavior
  {
    private int mOverlayTop;
    
    public ScrollingViewBehavior() {}
    
    public ScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ScrollingViewBehavior_Params);
      this.mOverlayTop = paramContext.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Params_behavior_overlapTop, 0);
      paramContext.recycle();
    }
    
    private int getOverlapForOffset(View paramView, int paramInt)
    {
      if ((this.mOverlayTop != 0) && ((paramView instanceof AppBarLayout)))
      {
        paramView = (AppBarLayout)paramView;
        int i = paramView.getTotalScrollRange();
        int j = paramView.getDownNestedPreScrollRange();
        if ((j != 0) && (i + paramInt <= j)) {
          return 0;
        }
        i -= j;
        if (i != 0) {
          return MathUtils.constrain(Math.round((1.0F + paramInt / i) * this.mOverlayTop), 0, this.mOverlayTop);
        }
      }
      return this.mOverlayTop;
    }
    
    private boolean updateOffset(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      paramCoordinatorLayout = ((CoordinatorLayout.LayoutParams)paramView2.getLayoutParams()).getBehavior();
      if ((paramCoordinatorLayout instanceof AppBarLayout.Behavior))
      {
        int i = ((AppBarLayout.Behavior)paramCoordinatorLayout).getTopBottomOffsetForScrollingSibling();
        setTopAndBottomOffset(paramView2.getHeight() + i - getOverlapForOffset(paramView2, i));
        return true;
      }
      return false;
    }
    
    View findFirstDependency(List<View> paramList)
    {
      int i = 0;
      int j = paramList.size();
      while (i < j)
      {
        View localView = (View)paramList.get(i);
        if ((localView instanceof AppBarLayout)) {
          return localView;
        }
        i += 1;
      }
      return null;
    }
    
    public int getOverlayTop()
    {
      return this.mOverlayTop;
    }
    
    int getScrollRange(View paramView)
    {
      if ((paramView instanceof AppBarLayout)) {
        return ((AppBarLayout)paramView).getTotalScrollRange();
      }
      return super.getScrollRange(paramView);
    }
    
    public boolean layoutDependsOn(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      return paramView2 instanceof AppBarLayout;
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      updateOffset(paramCoordinatorLayout, paramView1, paramView2);
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
    {
      super.onLayoutChild(paramCoordinatorLayout, paramView, paramInt);
      List localList = paramCoordinatorLayout.getDependencies(paramView);
      paramInt = 0;
      int i = localList.size();
      for (;;)
      {
        if ((paramInt >= i) || (updateOffset(paramCoordinatorLayout, paramView, (View)localList.get(paramInt)))) {
          return true;
        }
        paramInt += 1;
      }
    }
    
    public void setOverlayTop(int paramInt)
    {
      this.mOverlayTop = paramInt;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/AppBarLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */