package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout
  extends ViewGroup
  implements NestedScrollingParent
{
  static final Class<?>[] CONSTRUCTOR_PARAMS;
  static final CoordinatorLayoutInsetsHelper INSETS_HELPER;
  static final String TAG = "CoordinatorLayout";
  static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
  private static final int TYPE_ON_INTERCEPT = 0;
  private static final int TYPE_ON_TOUCH = 1;
  static final String WIDGET_PACKAGE_NAME;
  static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
  private View mBehaviorTouchView;
  private final List<View> mDependencySortedChildren = new ArrayList();
  private boolean mDrawStatusBarBackground;
  private boolean mIsAttachedToWindow;
  private int[] mKeylines;
  private WindowInsetsCompat mLastInsets;
  final Comparator<View> mLayoutDependencyComparator = new Comparator()
  {
    public int compare(View paramAnonymousView1, View paramAnonymousView2)
    {
      if (paramAnonymousView1 == paramAnonymousView2) {
        return 0;
      }
      if (((CoordinatorLayout.LayoutParams)paramAnonymousView1.getLayoutParams()).dependsOn(CoordinatorLayout.this, paramAnonymousView1, paramAnonymousView2)) {
        return 1;
      }
      if (((CoordinatorLayout.LayoutParams)paramAnonymousView2.getLayoutParams()).dependsOn(CoordinatorLayout.this, paramAnonymousView2, paramAnonymousView1)) {
        return -1;
      }
      return 0;
    }
  };
  private boolean mNeedsPreDrawListener;
  private View mNestedScrollingDirectChild;
  private final NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
  private View mNestedScrollingTarget;
  private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
  private OnPreDrawListener mOnPreDrawListener;
  private Paint mScrimPaint;
  private Drawable mStatusBarBackground;
  private final List<View> mTempDependenciesList = new ArrayList();
  private final int[] mTempIntPair = new int[2];
  private final List<View> mTempList1 = new ArrayList();
  private final Rect mTempRect1 = new Rect();
  private final Rect mTempRect2 = new Rect();
  private final Rect mTempRect3 = new Rect();
  
  static
  {
    Object localObject = CoordinatorLayout.class.getPackage();
    if (localObject != null)
    {
      localObject = ((Package)localObject).getName();
      WIDGET_PACKAGE_NAME = (String)localObject;
      if (Build.VERSION.SDK_INT < 21) {
        break label80;
      }
      TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
    }
    for (INSETS_HELPER = new CoordinatorLayoutInsetsHelperLollipop();; INSETS_HELPER = null)
    {
      CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
      sConstructors = new ThreadLocal();
      return;
      localObject = null;
      break;
      label80:
      TOP_SORTED_CHILDREN_COMPARATOR = null;
    }
  }
  
  public CoordinatorLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CoordinatorLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CoordinatorLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CoordinatorLayout, paramInt, R.style.Widget_Design_CoordinatorLayout);
    paramInt = paramAttributeSet.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
    if (paramInt != 0)
    {
      paramContext = paramContext.getResources();
      this.mKeylines = paramContext.getIntArray(paramInt);
      float f = paramContext.getDisplayMetrics().density;
      int i = this.mKeylines.length;
      paramInt = 0;
      while (paramInt < i)
      {
        paramContext = this.mKeylines;
        paramContext[paramInt] = ((int)(paramContext[paramInt] * f));
        paramInt += 1;
      }
    }
    this.mStatusBarBackground = paramAttributeSet.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
    paramAttributeSet.recycle();
    if (INSETS_HELPER != null) {
      INSETS_HELPER.setupForWindowInsets(this, new ApplyInsetsListener());
    }
    super.setOnHierarchyChangeListener(new HierarchyChangeListener());
  }
  
  private void dispatchChildApplyWindowInsets(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (paramWindowInsetsCompat.isConsumed()) {}
    label103:
    for (;;)
    {
      return;
      int i = 0;
      int j = getChildCount();
      for (;;)
      {
        if (i >= j) {
          break label103;
        }
        View localView = getChildAt(i);
        WindowInsetsCompat localWindowInsetsCompat = paramWindowInsetsCompat;
        if (ViewCompat.getFitsSystemWindows(localView))
        {
          Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
          localWindowInsetsCompat = paramWindowInsetsCompat;
          if (localBehavior != null)
          {
            localWindowInsetsCompat = localBehavior.onApplyWindowInsets(this, localView, paramWindowInsetsCompat);
            if (localWindowInsetsCompat.isConsumed()) {
              break;
            }
          }
          localWindowInsetsCompat = ViewCompat.dispatchApplyWindowInsets(localView, localWindowInsetsCompat);
          if (localWindowInsetsCompat.isConsumed()) {
            break;
          }
        }
        i += 1;
        paramWindowInsetsCompat = localWindowInsetsCompat;
      }
    }
  }
  
  private int getKeyline(int paramInt)
  {
    if (this.mKeylines == null)
    {
      Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + paramInt);
      return 0;
    }
    if ((paramInt < 0) || (paramInt >= this.mKeylines.length))
    {
      Log.e("CoordinatorLayout", "Keyline index " + paramInt + " out of range for " + this);
      return 0;
    }
    return this.mKeylines[paramInt];
  }
  
  private void getTopSortedChildren(List<View> paramList)
  {
    paramList.clear();
    boolean bool = isChildrenDrawingOrderEnabled();
    int k = getChildCount();
    int i = k - 1;
    if (i >= 0)
    {
      if (bool) {}
      for (int j = getChildDrawingOrder(k, i);; j = i)
      {
        paramList.add(getChildAt(j));
        i -= 1;
        break;
      }
    }
    if (TOP_SORTED_CHILDREN_COMPARATOR != null) {
      Collections.sort(paramList, TOP_SORTED_CHILDREN_COMPARATOR);
    }
  }
  
  private void layoutChild(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect localRect1 = this.mTempRect1;
    localRect1.set(getPaddingLeft() + localLayoutParams.leftMargin, getPaddingTop() + localLayoutParams.topMargin, getWidth() - getPaddingRight() - localLayoutParams.rightMargin, getHeight() - getPaddingBottom() - localLayoutParams.bottomMargin);
    if ((this.mLastInsets != null) && (ViewCompat.getFitsSystemWindows(this)) && (!ViewCompat.getFitsSystemWindows(paramView)))
    {
      localRect1.left += this.mLastInsets.getSystemWindowInsetLeft();
      localRect1.top += this.mLastInsets.getSystemWindowInsetTop();
      localRect1.right -= this.mLastInsets.getSystemWindowInsetRight();
      localRect1.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
    }
    Rect localRect2 = this.mTempRect2;
    GravityCompat.apply(resolveGravity(localLayoutParams.gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), localRect1, localRect2, paramInt);
    paramView.layout(localRect2.left, localRect2.top, localRect2.right, localRect2.bottom);
  }
  
  private void layoutChildWithAnchor(View paramView1, View paramView2, int paramInt)
  {
    Object localObject = (LayoutParams)paramView1.getLayoutParams();
    localObject = this.mTempRect1;
    Rect localRect = this.mTempRect2;
    getDescendantRect(paramView2, (Rect)localObject);
    getDesiredAnchoredChildRect(paramView1, paramInt, (Rect)localObject, localRect);
    paramView1.layout(localRect.left, localRect.top, localRect.right, localRect.bottom);
  }
  
  private void layoutChildWithKeyline(View paramView, int paramInt1, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i1 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(localLayoutParams.gravity), paramInt2);
    int n = getWidth();
    int m = getHeight();
    int j = paramView.getMeasuredWidth();
    int k = paramView.getMeasuredHeight();
    int i = paramInt1;
    if (paramInt2 == 1) {
      i = n - paramInt1;
    }
    paramInt1 = getKeyline(i) - j;
    paramInt2 = 0;
    switch (i1 & 0x7)
    {
    default: 
      switch (i1 & 0x70)
      {
      }
      break;
    }
    for (;;)
    {
      paramInt1 = Math.max(getPaddingLeft() + localLayoutParams.leftMargin, Math.min(paramInt1, n - getPaddingRight() - j - localLayoutParams.rightMargin));
      paramInt2 = Math.max(getPaddingTop() + localLayoutParams.topMargin, Math.min(paramInt2, m - getPaddingBottom() - k - localLayoutParams.bottomMargin));
      paramView.layout(paramInt1, paramInt2, paramInt1 + j, paramInt2 + k);
      return;
      paramInt1 += j;
      break;
      paramInt1 += j / 2;
      break;
      paramInt2 = 0 + k;
      continue;
      paramInt2 = 0 + k / 2;
    }
  }
  
  static Behavior parseBehavior(Context paramContext, AttributeSet paramAttributeSet, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (paramString.startsWith(".")) {
      paramString = paramContext.getPackageName() + paramString;
    }
    do
    {
      try
      {
        Object localObject2 = (Map)sConstructors.get();
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new HashMap();
          sConstructors.set(localObject1);
        }
        Constructor localConstructor = (Constructor)((Map)localObject1).get(paramString);
        localObject2 = localConstructor;
        if (localConstructor == null)
        {
          localObject2 = Class.forName(paramString, true, paramContext.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);
          ((Constructor)localObject2).setAccessible(true);
          ((Map)localObject1).put(paramString, localObject2);
        }
        paramContext = (Behavior)((Constructor)localObject2).newInstance(new Object[] { paramContext, paramAttributeSet });
        return paramContext;
      }
      catch (Exception paramContext)
      {
        throw new RuntimeException("Could not inflate Behavior subclass " + paramString, paramContext);
      }
    } while (paramString.indexOf('.') >= 0);
    if (!TextUtils.isEmpty(WIDGET_PACKAGE_NAME)) {
      paramString = WIDGET_PACKAGE_NAME + '.' + paramString;
    }
    for (;;)
    {
      break;
    }
  }
  
  private boolean performIntercept(MotionEvent paramMotionEvent, int paramInt)
  {
    boolean bool1 = false;
    int i = 0;
    Object localObject1 = null;
    int m = MotionEventCompat.getActionMasked(paramMotionEvent);
    List localList = this.mTempList1;
    getTopSortedChildren(localList);
    int n = localList.size();
    int j = 0;
    boolean bool2 = bool1;
    View localView;
    Object localObject2;
    Behavior localBehavior;
    boolean bool3;
    int k;
    if (j < n)
    {
      localView = (View)localList.get(j);
      localObject2 = (LayoutParams)localView.getLayoutParams();
      localBehavior = ((LayoutParams)localObject2).getBehavior();
      if (((bool1) || (i != 0)) && (m != 0))
      {
        localObject2 = localObject1;
        bool3 = bool1;
        k = i;
        if (localBehavior != null)
        {
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            long l = SystemClock.uptimeMillis();
            localObject2 = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
          }
          switch (paramInt)
          {
          default: 
            k = i;
            bool3 = bool1;
          }
        }
        for (;;)
        {
          j += 1;
          localObject1 = localObject2;
          bool1 = bool3;
          i = k;
          break;
          localBehavior.onInterceptTouchEvent(this, localView, (MotionEvent)localObject2);
          bool3 = bool1;
          k = i;
          continue;
          localBehavior.onTouchEvent(this, localView, (MotionEvent)localObject2);
          bool3 = bool1;
          k = i;
        }
      }
      bool2 = bool1;
      if (!bool1)
      {
        bool2 = bool1;
        if (localBehavior == null) {}
      }
      switch (paramInt)
      {
      default: 
        label272:
        bool2 = bool1;
        if (bool1)
        {
          this.mBehaviorTouchView = localView;
          bool2 = bool1;
        }
        bool3 = ((LayoutParams)localObject2).didBlockInteraction();
        bool1 = ((LayoutParams)localObject2).isBlockingInteractionBelow(this, localView);
        if ((!bool1) || (bool3)) {
          break;
        }
      }
    }
    for (i = 1;; i = 0)
    {
      localObject2 = localObject1;
      bool3 = bool2;
      k = i;
      if (!bool1) {
        break;
      }
      localObject2 = localObject1;
      bool3 = bool2;
      k = i;
      if (i != 0) {
        break;
      }
      localList.clear();
      return bool2;
      bool1 = localBehavior.onInterceptTouchEvent(this, localView, paramMotionEvent);
      break label272;
      bool1 = localBehavior.onTouchEvent(this, localView, paramMotionEvent);
      break label272;
    }
  }
  
  private void prepareChildren()
  {
    this.mDependencySortedChildren.clear();
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      View localView = getChildAt(i);
      getResolvedLayoutParams(localView).findAnchorView(this, localView);
      this.mDependencySortedChildren.add(localView);
      i += 1;
    }
    selectionSort(this.mDependencySortedChildren, this.mLayoutDependencyComparator);
  }
  
  private void resetTouchBehaviors()
  {
    if (this.mBehaviorTouchView != null)
    {
      Behavior localBehavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
      if (localBehavior != null)
      {
        long l = SystemClock.uptimeMillis();
        MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        localBehavior.onTouchEvent(this, this.mBehaviorTouchView, localMotionEvent);
        localMotionEvent.recycle();
      }
      this.mBehaviorTouchView = null;
    }
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      ((LayoutParams)getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking();
      i += 1;
    }
  }
  
  private static int resolveAnchoredChildGravity(int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0) {
      i = 17;
    }
    return i;
  }
  
  private static int resolveGravity(int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0) {
      i = 8388659;
    }
    return i;
  }
  
  private static int resolveKeylineGravity(int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0) {
      i = 8388661;
    }
    return i;
  }
  
  private static void selectionSort(List<View> paramList, Comparator<View> paramComparator)
  {
    if ((paramList == null) || (paramList.size() < 2)) {}
    for (;;)
    {
      return;
      View[] arrayOfView = new View[paramList.size()];
      paramList.toArray(arrayOfView);
      int n = arrayOfView.length;
      int i = 0;
      while (i < n)
      {
        int k = i;
        int j = i + 1;
        while (j < n)
        {
          int m = k;
          if (paramComparator.compare(arrayOfView[j], arrayOfView[k]) < 0) {
            m = j;
          }
          j += 1;
          k = m;
        }
        if (i != k)
        {
          View localView = arrayOfView[k];
          arrayOfView[k] = arrayOfView[i];
          arrayOfView[i] = localView;
        }
        i += 1;
      }
      paramList.clear();
      i = 0;
      while (i < n)
      {
        paramList.add(arrayOfView[i]);
        i += 1;
      }
    }
  }
  
  private void setWindowInsets(WindowInsetsCompat paramWindowInsetsCompat)
  {
    boolean bool2 = true;
    if (this.mLastInsets != paramWindowInsetsCompat)
    {
      this.mLastInsets = paramWindowInsetsCompat;
      if ((paramWindowInsetsCompat == null) || (paramWindowInsetsCompat.getSystemWindowInsetTop() <= 0)) {
        break label64;
      }
      bool1 = true;
      this.mDrawStatusBarBackground = bool1;
      if ((this.mDrawStatusBarBackground) || (getBackground() != null)) {
        break label69;
      }
    }
    label64:
    label69:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      setWillNotDraw(bool1);
      dispatchChildApplyWindowInsets(paramWindowInsetsCompat);
      requestLayout();
      return;
      bool1 = false;
      break;
    }
  }
  
  void addPreDrawListener()
  {
    if (this.mIsAttachedToWindow)
    {
      if (this.mOnPreDrawListener == null) {
        this.mOnPreDrawListener = new OnPreDrawListener();
      }
      getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    }
    this.mNeedsPreDrawListener = true;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  void dispatchDependentViewRemoved(View paramView)
  {
    int m = this.mDependencySortedChildren.size();
    int j = 0;
    int i = 0;
    if (i < m)
    {
      View localView = (View)this.mDependencySortedChildren.get(i);
      int k;
      if (localView == paramView) {
        k = 1;
      }
      for (;;)
      {
        i += 1;
        j = k;
        break;
        k = j;
        if (j != 0)
        {
          LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
          Behavior localBehavior = localLayoutParams.getBehavior();
          k = j;
          if (localBehavior != null)
          {
            k = j;
            if (localLayoutParams.dependsOn(this, localView, paramView))
            {
              localBehavior.onDependentViewRemoved(this, localView, paramView);
              k = j;
            }
          }
        }
      }
    }
  }
  
  public void dispatchDependentViewsChanged(View paramView)
  {
    int m = this.mDependencySortedChildren.size();
    int j = 0;
    int i = 0;
    if (i < m)
    {
      View localView = (View)this.mDependencySortedChildren.get(i);
      int k;
      if (localView == paramView) {
        k = 1;
      }
      for (;;)
      {
        i += 1;
        j = k;
        break;
        k = j;
        if (j != 0)
        {
          LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
          Behavior localBehavior = localLayoutParams.getBehavior();
          k = j;
          if (localBehavior != null)
          {
            k = j;
            if (localLayoutParams.dependsOn(this, localView, paramView))
            {
              localBehavior.onDependentViewChanged(this, localView, paramView);
              k = j;
            }
          }
        }
      }
    }
  }
  
  void dispatchOnDependentViewChanged(boolean paramBoolean)
  {
    int k = ViewCompat.getLayoutDirection(this);
    int m = this.mDependencySortedChildren.size();
    int i = 0;
    while (i < m)
    {
      View localView = (View)this.mDependencySortedChildren.get(i);
      Object localObject1 = (LayoutParams)localView.getLayoutParams();
      int j = 0;
      while (j < i)
      {
        localObject2 = (View)this.mDependencySortedChildren.get(j);
        if (((LayoutParams)localObject1).mAnchorDirectChild == localObject2) {
          offsetChildToAnchor(localView, k);
        }
        j += 1;
      }
      localObject1 = this.mTempRect1;
      Object localObject2 = this.mTempRect2;
      getLastChildRect(localView, (Rect)localObject1);
      getChildRect(localView, true, (Rect)localObject2);
      if (((Rect)localObject1).equals(localObject2))
      {
        i += 1;
      }
      else
      {
        recordLastChildRect(localView, (Rect)localObject2);
        j = i + 1;
        label155:
        Behavior localBehavior;
        if (j < m)
        {
          localObject1 = (View)this.mDependencySortedChildren.get(j);
          localObject2 = (LayoutParams)((View)localObject1).getLayoutParams();
          localBehavior = ((LayoutParams)localObject2).getBehavior();
          if ((localBehavior != null) && (localBehavior.layoutDependsOn(this, (View)localObject1, localView)))
          {
            if ((paramBoolean) || (!((LayoutParams)localObject2).getChangedAfterNestedScroll())) {
              break label235;
            }
            ((LayoutParams)localObject2).resetChangedAfterNestedScroll();
          }
        }
        for (;;)
        {
          j += 1;
          break label155;
          break;
          label235:
          boolean bool = localBehavior.onDependentViewChanged(this, (View)localObject1, localView);
          if (paramBoolean) {
            ((LayoutParams)localObject2).setChangedAfterNestedScroll(bool);
          }
        }
      }
    }
  }
  
  public boolean doViewsOverlap(View paramView1, View paramView2)
  {
    if ((paramView1.getVisibility() == 0) && (paramView2.getVisibility() == 0))
    {
      Rect localRect = this.mTempRect1;
      if (paramView1.getParent() != this)
      {
        bool = true;
        getChildRect(paramView1, bool, localRect);
        paramView1 = this.mTempRect2;
        if (paramView2.getParent() == this) {
          break label115;
        }
      }
      label115:
      for (boolean bool = true;; bool = false)
      {
        getChildRect(paramView2, bool, paramView1);
        if ((localRect.left > paramView1.right) || (localRect.top > paramView1.bottom) || (localRect.right < paramView1.left) || (localRect.bottom < paramView1.top)) {
          break label120;
        }
        return true;
        bool = false;
        break;
      }
      label120:
      return false;
    }
    return false;
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((localLayoutParams.mBehavior != null) && (localLayoutParams.mBehavior.getScrimOpacity(this, paramView) > 0.0F))
    {
      if (this.mScrimPaint == null) {
        this.mScrimPaint = new Paint();
      }
      this.mScrimPaint.setColor(localLayoutParams.mBehavior.getScrimColor(this, paramView));
      paramCanvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
    }
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  void ensurePreDrawListener()
  {
    int m = 0;
    int j = getChildCount();
    int i = 0;
    for (;;)
    {
      int k = m;
      if (i < j)
      {
        if (hasDependencies(getChildAt(i))) {
          k = 1;
        }
      }
      else
      {
        if (k != this.mNeedsPreDrawListener)
        {
          if (k == 0) {
            break;
          }
          addPreDrawListener();
        }
        return;
      }
      i += 1;
    }
    removePreDrawListener();
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams)) {
      return new LayoutParams((LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  void getChildRect(View paramView, boolean paramBoolean, Rect paramRect)
  {
    if ((paramView.isLayoutRequested()) || (paramView.getVisibility() == 8))
    {
      paramRect.set(0, 0, 0, 0);
      return;
    }
    if (paramBoolean)
    {
      getDescendantRect(paramView, paramRect);
      return;
    }
    paramRect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
  }
  
  public List<View> getDependencies(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    List localList = this.mTempDependenciesList;
    localList.clear();
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      if (localView == paramView) {}
      for (;;)
      {
        i += 1;
        break;
        if (localLayoutParams.dependsOn(this, paramView, localView)) {
          localList.add(localView);
        }
      }
    }
    return localList;
  }
  
  void getDescendantRect(View paramView, Rect paramRect)
  {
    ViewGroupUtils.getDescendantRect(this, paramView, paramRect);
  }
  
  void getDesiredAnchoredChildRect(View paramView, int paramInt, Rect paramRect1, Rect paramRect2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int n = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(localLayoutParams.gravity), paramInt);
    int i = GravityCompat.getAbsoluteGravity(resolveGravity(localLayoutParams.anchorGravity), paramInt);
    int k = paramView.getMeasuredWidth();
    int m = paramView.getMeasuredHeight();
    label122:
    int j;
    switch (i & 0x7)
    {
    default: 
      paramInt = paramRect1.left;
      switch (i & 0x70)
      {
      default: 
        i = paramRect1.top;
        j = paramInt;
        switch (n & 0x7)
        {
        default: 
          j = paramInt - k;
        case 5: 
          label162:
          paramInt = i;
          switch (n & 0x70)
          {
          }
          break;
        }
        break;
      }
      break;
    }
    for (paramInt = i - m;; paramInt = i - m / 2)
    {
      n = getWidth();
      i = getHeight();
      j = Math.max(getPaddingLeft() + localLayoutParams.leftMargin, Math.min(j, n - getPaddingRight() - k - localLayoutParams.rightMargin));
      paramInt = Math.max(getPaddingTop() + localLayoutParams.topMargin, Math.min(paramInt, i - getPaddingBottom() - m - localLayoutParams.bottomMargin));
      paramRect2.set(j, paramInt, j + k, paramInt + m);
      return;
      paramInt = paramRect1.right;
      break;
      paramInt = paramRect1.left + paramRect1.width() / 2;
      break;
      i = paramRect1.bottom;
      break label122;
      i = paramRect1.top + paramRect1.height() / 2;
      break label122;
      j = paramInt - k / 2;
      break label162;
    }
  }
  
  void getLastChildRect(View paramView, Rect paramRect)
  {
    paramRect.set(((LayoutParams)paramView.getLayoutParams()).getLastChildRect());
  }
  
  public int getNestedScrollAxes()
  {
    return this.mNestedScrollingParentHelper.getNestedScrollAxes();
  }
  
  LayoutParams getResolvedLayoutParams(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    View localView;
    if (!localLayoutParams.mBehaviorResolved)
    {
      Class localClass = paramView.getClass();
      paramView = null;
      for (;;)
      {
        localView = paramView;
        if (localClass == null) {
          break;
        }
        paramView = (DefaultBehavior)localClass.getAnnotation(DefaultBehavior.class);
        localView = paramView;
        if (paramView != null) {
          break;
        }
        localClass = localClass.getSuperclass();
      }
      if (localView == null) {}
    }
    try
    {
      localLayoutParams.setBehavior((Behavior)localView.value().newInstance());
      localLayoutParams.mBehaviorResolved = true;
      return localLayoutParams;
    }
    catch (Exception paramView)
    {
      for (;;)
      {
        Log.e("CoordinatorLayout", "Default behavior class " + localView.value().getName() + " could not be instantiated. Did you forget a default constructor?", paramView);
      }
    }
  }
  
  public Drawable getStatusBarBackground()
  {
    return this.mStatusBarBackground;
  }
  
  protected int getSuggestedMinimumHeight()
  {
    return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
  }
  
  protected int getSuggestedMinimumWidth()
  {
    return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
  }
  
  boolean hasDependencies(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.mAnchorView != null) {
      return true;
    }
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      if (localView == paramView) {}
      while (!localLayoutParams.dependsOn(this, paramView, localView))
      {
        i += 1;
        break;
      }
      return true;
    }
    return false;
  }
  
  public boolean isPointInChildBounds(View paramView, int paramInt1, int paramInt2)
  {
    Rect localRect = this.mTempRect1;
    getDescendantRect(paramView, localRect);
    return localRect.contains(paramInt1, paramInt2);
  }
  
  void offsetChildToAnchor(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.mAnchorView != null)
    {
      Object localObject = this.mTempRect1;
      Rect localRect1 = this.mTempRect2;
      Rect localRect2 = this.mTempRect3;
      getDescendantRect(localLayoutParams.mAnchorView, (Rect)localObject);
      getChildRect(paramView, false, localRect1);
      getDesiredAnchoredChildRect(paramView, paramInt, (Rect)localObject, localRect2);
      paramInt = localRect2.left - localRect1.left;
      int i = localRect2.top - localRect1.top;
      if (paramInt != 0) {
        paramView.offsetLeftAndRight(paramInt);
      }
      if (i != 0) {
        paramView.offsetTopAndBottom(i);
      }
      if ((paramInt != 0) || (i != 0))
      {
        localObject = localLayoutParams.getBehavior();
        if (localObject != null) {
          ((Behavior)localObject).onDependentViewChanged(this, paramView, localLayoutParams.mAnchorView);
        }
      }
    }
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    resetTouchBehaviors();
    if (this.mNeedsPreDrawListener)
    {
      if (this.mOnPreDrawListener == null) {
        this.mOnPreDrawListener = new OnPreDrawListener();
      }
      getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    }
    if ((this.mLastInsets == null) && (ViewCompat.getFitsSystemWindows(this))) {
      ViewCompat.requestApplyInsets(this);
    }
    this.mIsAttachedToWindow = true;
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    resetTouchBehaviors();
    if ((this.mNeedsPreDrawListener) && (this.mOnPreDrawListener != null)) {
      getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
    }
    if (this.mNestedScrollingTarget != null) {
      onStopNestedScroll(this.mNestedScrollingTarget);
    }
    this.mIsAttachedToWindow = false;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.mDrawStatusBarBackground) && (this.mStatusBarBackground != null)) {
      if (this.mLastInsets == null) {
        break label61;
      }
    }
    label61:
    for (int i = this.mLastInsets.getSystemWindowInsetTop();; i = 0)
    {
      if (i > 0)
      {
        this.mStatusBarBackground.setBounds(0, 0, getWidth(), i);
        this.mStatusBarBackground.draw(paramCanvas);
      }
      return;
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 0) {
      resetTouchBehaviors();
    }
    boolean bool = performIntercept(paramMotionEvent, 0);
    if (0 != 0) {
      throw new NullPointerException();
    }
    if ((i == 1) || (i == 3)) {
      resetTouchBehaviors();
    }
    return bool;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = ViewCompat.getLayoutDirection(this);
    paramInt3 = this.mDependencySortedChildren.size();
    paramInt1 = 0;
    while (paramInt1 < paramInt3)
    {
      View localView = (View)this.mDependencySortedChildren.get(paramInt1);
      Behavior localBehavior = ((LayoutParams)localView.getLayoutParams()).getBehavior();
      if ((localBehavior == null) || (!localBehavior.onLayoutChild(this, localView, paramInt2))) {
        onLayoutChild(localView, paramInt2);
      }
      paramInt1 += 1;
    }
  }
  
  public void onLayoutChild(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.checkAnchorChanged()) {
      throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
    }
    if (localLayoutParams.mAnchorView != null)
    {
      layoutChildWithAnchor(paramView, localLayoutParams.mAnchorView, paramInt);
      return;
    }
    if (localLayoutParams.keyline >= 0)
    {
      layoutChildWithKeyline(paramView, localLayoutParams.keyline, paramInt);
      return;
    }
    layoutChild(paramView, paramInt);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    prepareChildren();
    ensurePreDrawListener();
    int i7 = getPaddingLeft();
    int i8 = getPaddingTop();
    int i9 = getPaddingRight();
    int i10 = getPaddingBottom();
    int i11 = ViewCompat.getLayoutDirection(this);
    int j;
    int i12;
    int i13;
    int i14;
    int i15;
    int i2;
    int i1;
    int n;
    int k;
    label103:
    int m;
    label117:
    View localView;
    LayoutParams localLayoutParams;
    int i3;
    int i;
    int i4;
    int i5;
    if (i11 == 1)
    {
      j = 1;
      i12 = View.MeasureSpec.getMode(paramInt1);
      i13 = View.MeasureSpec.getSize(paramInt1);
      i14 = View.MeasureSpec.getMode(paramInt2);
      i15 = View.MeasureSpec.getSize(paramInt2);
      i2 = getSuggestedMinimumWidth();
      i1 = getSuggestedMinimumHeight();
      n = 0;
      if ((this.mLastInsets == null) || (!ViewCompat.getFitsSystemWindows(this))) {
        break label465;
      }
      k = 1;
      int i16 = this.mDependencySortedChildren.size();
      m = 0;
      if (m >= i16) {
        break label512;
      }
      localView = (View)this.mDependencySortedChildren.get(m);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      i3 = 0;
      i = i3;
      if (localLayoutParams.keyline >= 0)
      {
        i = i3;
        if (i12 != 0)
        {
          i4 = getKeyline(localLayoutParams.keyline);
          i5 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(localLayoutParams.gravity), i11) & 0x7;
          if (((i5 != 3) || (j != 0)) && ((i5 != 5) || (j == 0))) {
            break label471;
          }
          i = Math.max(0, i13 - i9 - i4);
        }
      }
    }
    for (;;)
    {
      i4 = paramInt1;
      i5 = paramInt2;
      int i6 = i4;
      i3 = i5;
      if (k != 0)
      {
        i6 = i4;
        i3 = i5;
        if (!ViewCompat.getFitsSystemWindows(localView))
        {
          i5 = this.mLastInsets.getSystemWindowInsetLeft();
          i6 = this.mLastInsets.getSystemWindowInsetRight();
          i3 = this.mLastInsets.getSystemWindowInsetTop();
          i4 = this.mLastInsets.getSystemWindowInsetBottom();
          i6 = View.MeasureSpec.makeMeasureSpec(i13 - (i5 + i6), i12);
          i3 = View.MeasureSpec.makeMeasureSpec(i15 - (i3 + i4), i14);
        }
      }
      Behavior localBehavior = localLayoutParams.getBehavior();
      if ((localBehavior == null) || (!localBehavior.onMeasureChild(this, localView, i6, i, i3, 0))) {
        onMeasureChild(localView, i6, i, i3, 0);
      }
      i2 = Math.max(i2, localView.getMeasuredWidth() + (i7 + i9) + localLayoutParams.leftMargin + localLayoutParams.rightMargin);
      i1 = Math.max(i1, localView.getMeasuredHeight() + (i8 + i10) + localLayoutParams.topMargin + localLayoutParams.bottomMargin);
      n = ViewCompat.combineMeasuredStates(n, ViewCompat.getMeasuredState(localView));
      m += 1;
      break label117;
      j = 0;
      break;
      label465:
      k = 0;
      break label103;
      label471:
      if ((i5 != 5) || (j != 0))
      {
        i = i3;
        if (i5 == 3)
        {
          i = i3;
          if (j == 0) {}
        }
      }
      else
      {
        i = Math.max(0, i4 - i7);
      }
    }
    label512:
    setMeasuredDimension(ViewCompat.resolveSizeAndState(i2, paramInt1, 0xFF000000 & n), ViewCompat.resolveSizeAndState(i1, paramInt2, n << 16));
  }
  
  public void onMeasureChild(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    measureChildWithMargins(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    boolean bool1 = false;
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      Object localObject = (LayoutParams)localView.getLayoutParams();
      boolean bool2;
      if (!((LayoutParams)localObject).isNestedScrollAccepted()) {
        bool2 = bool1;
      }
      for (;;)
      {
        i += 1;
        bool1 = bool2;
        break;
        localObject = ((LayoutParams)localObject).getBehavior();
        bool2 = bool1;
        if (localObject != null) {
          bool2 = bool1 | ((Behavior)localObject).onNestedFling(this, localView, paramView, paramFloat1, paramFloat2, paramBoolean);
        }
      }
    }
    if (bool1) {
      dispatchOnDependentViewChanged(true);
    }
    return bool1;
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    boolean bool1 = false;
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      Object localObject = (LayoutParams)localView.getLayoutParams();
      boolean bool2;
      if (!((LayoutParams)localObject).isNestedScrollAccepted()) {
        bool2 = bool1;
      }
      for (;;)
      {
        i += 1;
        bool1 = bool2;
        break;
        localObject = ((LayoutParams)localObject).getBehavior();
        bool2 = bool1;
        if (localObject != null) {
          bool2 = bool1 | ((Behavior)localObject).onNestedPreFling(this, localView, paramView, paramFloat1, paramFloat2);
        }
      }
    }
    return bool1;
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    int i = 0;
    int j = 0;
    int m = 0;
    int i2 = getChildCount();
    int k = 0;
    if (k < i2)
    {
      View localView = getChildAt(k);
      Object localObject = (LayoutParams)localView.getLayoutParams();
      int i1;
      int n;
      if (!((LayoutParams)localObject).isNestedScrollAccepted())
      {
        i1 = j;
        n = i;
      }
      do
      {
        k += 1;
        i = n;
        j = i1;
        break;
        localObject = ((LayoutParams)localObject).getBehavior();
        n = i;
        i1 = j;
      } while (localObject == null);
      int[] arrayOfInt = this.mTempIntPair;
      this.mTempIntPair[1] = 0;
      arrayOfInt[0] = 0;
      ((Behavior)localObject).onNestedPreScroll(this, localView, paramView, paramInt1, paramInt2, this.mTempIntPair);
      if (paramInt1 > 0)
      {
        i = Math.max(i, this.mTempIntPair[0]);
        label146:
        if (paramInt2 <= 0) {
          break label193;
        }
      }
      label193:
      for (j = Math.max(j, this.mTempIntPair[1]);; j = Math.min(j, this.mTempIntPair[1]))
      {
        m = 1;
        n = i;
        i1 = j;
        break;
        i = Math.min(i, this.mTempIntPair[0]);
        break label146;
      }
    }
    paramArrayOfInt[0] = i;
    paramArrayOfInt[1] = j;
    if (m != 0) {
      dispatchOnDependentViewChanged(true);
    }
  }
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int k = getChildCount();
    int j = 0;
    int i = 0;
    if (i < k)
    {
      View localView = getChildAt(i);
      Object localObject = (LayoutParams)localView.getLayoutParams();
      if (!((LayoutParams)localObject).isNestedScrollAccepted()) {}
      for (;;)
      {
        i += 1;
        break;
        localObject = ((LayoutParams)localObject).getBehavior();
        if (localObject != null)
        {
          ((Behavior)localObject).onNestedScroll(this, localView, paramView, paramInt1, paramInt2, paramInt3, paramInt4);
          j = 1;
        }
      }
    }
    if (j != 0) {
      dispatchOnDependentViewChanged(true);
    }
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    this.mNestedScrollingParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt);
    this.mNestedScrollingDirectChild = paramView1;
    this.mNestedScrollingTarget = paramView2;
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      Object localObject = (LayoutParams)localView.getLayoutParams();
      if (!((LayoutParams)localObject).isNestedScrollAccepted()) {}
      for (;;)
      {
        i += 1;
        break;
        localObject = ((LayoutParams)localObject).getBehavior();
        if (localObject != null) {
          ((Behavior)localObject).onNestedScrollAccepted(this, localView, paramView1, paramView2, paramInt);
        }
      }
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    paramParcelable = paramParcelable.behaviorStates;
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      View localView = getChildAt(i);
      int k = localView.getId();
      Behavior localBehavior = getResolvedLayoutParams(localView).getBehavior();
      if ((k != -1) && (localBehavior != null))
      {
        Parcelable localParcelable = (Parcelable)paramParcelable.get(k);
        if (localParcelable != null) {
          localBehavior.onRestoreInstanceState(this, localView, localParcelable);
        }
      }
      i += 1;
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    SparseArray localSparseArray = new SparseArray();
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      Object localObject = getChildAt(i);
      int k = ((View)localObject).getId();
      Behavior localBehavior = ((LayoutParams)((View)localObject).getLayoutParams()).getBehavior();
      if ((k != -1) && (localBehavior != null))
      {
        localObject = localBehavior.onSaveInstanceState(this, (View)localObject);
        if (localObject != null) {
          localSparseArray.append(k, localObject);
        }
      }
      i += 1;
    }
    localSavedState.behaviorStates = localSparseArray;
    return localSavedState;
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    boolean bool1 = false;
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      Behavior localBehavior = localLayoutParams.getBehavior();
      if (localBehavior != null)
      {
        boolean bool2 = localBehavior.onStartNestedScroll(this, localView, paramView1, paramView2, paramInt);
        bool1 |= bool2;
        localLayoutParams.acceptNestedScroll(bool2);
      }
      for (;;)
      {
        i += 1;
        break;
        localLayoutParams.acceptNestedScroll(false);
      }
    }
    return bool1;
  }
  
  public void onStopNestedScroll(View paramView)
  {
    this.mNestedScrollingParentHelper.onStopNestedScroll(paramView);
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (!localLayoutParams.isNestedScrollAccepted()) {}
      for (;;)
      {
        i += 1;
        break;
        Behavior localBehavior = localLayoutParams.getBehavior();
        if (localBehavior != null) {
          localBehavior.onStopNestedScroll(this, localView, paramView);
        }
        localLayoutParams.resetNestedScroll();
        localLayoutParams.resetChangedAfterNestedScroll();
      }
    }
    this.mNestedScrollingDirectChild = null;
    this.mNestedScrollingTarget = null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool4 = false;
    boolean bool2 = false;
    Object localObject1 = null;
    Object localObject2 = null;
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    boolean bool3;
    boolean bool1;
    if (this.mBehaviorTouchView == null)
    {
      bool2 = performIntercept(paramMotionEvent, 1);
      bool3 = bool2;
      bool1 = bool4;
      if (!bool2) {}
    }
    else
    {
      Behavior localBehavior = ((LayoutParams)this.mBehaviorTouchView.getLayoutParams()).getBehavior();
      bool3 = bool2;
      bool1 = bool4;
      if (localBehavior != null)
      {
        bool1 = localBehavior.onTouchEvent(this, this.mBehaviorTouchView, paramMotionEvent);
        bool3 = bool2;
      }
    }
    if (this.mBehaviorTouchView == null)
    {
      bool2 = bool1 | super.onTouchEvent(paramMotionEvent);
      paramMotionEvent = (MotionEvent)localObject2;
    }
    for (;;)
    {
      if (((bool2) || (i != 0)) || (paramMotionEvent != null)) {
        paramMotionEvent.recycle();
      }
      if ((i == 1) || (i == 3)) {
        resetTouchBehaviors();
      }
      return bool2;
      paramMotionEvent = (MotionEvent)localObject2;
      bool2 = bool1;
      if (bool3)
      {
        paramMotionEvent = (MotionEvent)localObject1;
        if (0 == 0)
        {
          long l = SystemClock.uptimeMillis();
          paramMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        }
        super.onTouchEvent(paramMotionEvent);
        bool2 = bool1;
      }
    }
  }
  
  void recordLastChildRect(View paramView, Rect paramRect)
  {
    ((LayoutParams)paramView.getLayoutParams()).setLastChildRect(paramRect);
  }
  
  void removePreDrawListener()
  {
    if ((this.mIsAttachedToWindow) && (this.mOnPreDrawListener != null)) {
      getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
    }
    this.mNeedsPreDrawListener = false;
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    if (paramBoolean) {
      resetTouchBehaviors();
    }
  }
  
  public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener paramOnHierarchyChangeListener)
  {
    this.mOnHierarchyChangeListener = paramOnHierarchyChangeListener;
  }
  
  public void setStatusBarBackground(Drawable paramDrawable)
  {
    this.mStatusBarBackground = paramDrawable;
    invalidate();
  }
  
  public void setStatusBarBackgroundColor(int paramInt)
  {
    setStatusBarBackground(new ColorDrawable(paramInt));
  }
  
  public void setStatusBarBackgroundResource(int paramInt)
  {
    if (paramInt != 0) {}
    for (Drawable localDrawable = ContextCompat.getDrawable(getContext(), paramInt);; localDrawable = null)
    {
      setStatusBarBackground(localDrawable);
      return;
    }
  }
  
  final class ApplyInsetsListener
    implements OnApplyWindowInsetsListener
  {
    ApplyInsetsListener() {}
    
    public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
    {
      CoordinatorLayout.this.setWindowInsets(paramWindowInsetsCompat);
      return paramWindowInsetsCompat.consumeSystemWindowInsets();
    }
  }
  
  public static abstract class Behavior<V extends View>
  {
    public Behavior() {}
    
    public Behavior(Context paramContext, AttributeSet paramAttributeSet) {}
    
    public static Object getTag(View paramView)
    {
      return ((CoordinatorLayout.LayoutParams)paramView.getLayoutParams()).mBehaviorTag;
    }
    
    public static void setTag(View paramView, Object paramObject)
    {
      ((CoordinatorLayout.LayoutParams)paramView.getLayoutParams()).mBehaviorTag = paramObject;
    }
    
    public boolean blocksInteractionBelow(CoordinatorLayout paramCoordinatorLayout, V paramV)
    {
      return getScrimOpacity(paramCoordinatorLayout, paramV) > 0.0F;
    }
    
    public final int getScrimColor(CoordinatorLayout paramCoordinatorLayout, V paramV)
    {
      return -16777216;
    }
    
    public final float getScrimOpacity(CoordinatorLayout paramCoordinatorLayout, V paramV)
    {
      return 0.0F;
    }
    
    public boolean isDirty(CoordinatorLayout paramCoordinatorLayout, V paramV)
    {
      return false;
    }
    
    public boolean layoutDependsOn(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView)
    {
      return false;
    }
    
    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout paramCoordinatorLayout, V paramV, WindowInsetsCompat paramWindowInsetsCompat)
    {
      return paramWindowInsetsCompat;
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView)
    {
      return false;
    }
    
    public void onDependentViewRemoved(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView) {}
    
    public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
    {
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt)
    {
      return false;
    }
    
    public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return false;
    }
    
    public boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
    {
      return false;
    }
    
    public boolean onNestedPreFling(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, float paramFloat1, float paramFloat2)
    {
      return false;
    }
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
    
    public void onNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
    
    public void onNestedScrollAccepted(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView1, View paramView2, int paramInt) {}
    
    public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV, Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV)
    {
      return View.BaseSavedState.EMPTY_STATE;
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView1, View paramView2, int paramInt)
    {
      return false;
    }
    
    public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView) {}
    
    public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
    {
      return false;
    }
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface DefaultBehavior
  {
    Class<? extends CoordinatorLayout.Behavior> value();
  }
  
  final class HierarchyChangeListener
    implements ViewGroup.OnHierarchyChangeListener
  {
    HierarchyChangeListener() {}
    
    public void onChildViewAdded(View paramView1, View paramView2)
    {
      if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
        CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(paramView1, paramView2);
      }
    }
    
    public void onChildViewRemoved(View paramView1, View paramView2)
    {
      CoordinatorLayout.this.dispatchDependentViewRemoved(paramView2);
      if (CoordinatorLayout.this.mOnHierarchyChangeListener != null) {
        CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(paramView1, paramView2);
      }
    }
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public int anchorGravity = 0;
    public int gravity = 0;
    public int keyline = -1;
    View mAnchorDirectChild;
    int mAnchorId = -1;
    View mAnchorView;
    CoordinatorLayout.Behavior mBehavior;
    boolean mBehaviorResolved = false;
    Object mBehaviorTag;
    private boolean mDidAcceptNestedScroll;
    private boolean mDidBlockInteraction;
    private boolean mDidChangeAfterNestedScroll;
    final Rect mLastChildRect = new Rect();
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CoordinatorLayout_LayoutParams);
      this.gravity = localTypedArray.getInteger(R.styleable.CoordinatorLayout_LayoutParams_android_layout_gravity, 0);
      this.mAnchorId = localTypedArray.getResourceId(R.styleable.CoordinatorLayout_LayoutParams_layout_anchor, -1);
      this.anchorGravity = localTypedArray.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_anchorGravity, 0);
      this.keyline = localTypedArray.getInteger(R.styleable.CoordinatorLayout_LayoutParams_layout_keyline, -1);
      this.mBehaviorResolved = localTypedArray.hasValue(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior);
      if (this.mBehaviorResolved) {
        this.mBehavior = CoordinatorLayout.parseBehavior(paramContext, paramAttributeSet, localTypedArray.getString(R.styleable.CoordinatorLayout_LayoutParams_layout_behavior));
      }
      localTypedArray.recycle();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    private void resolveAnchorView(View paramView, CoordinatorLayout paramCoordinatorLayout)
    {
      this.mAnchorView = paramCoordinatorLayout.findViewById(this.mAnchorId);
      if (this.mAnchorView != null)
      {
        View localView = this.mAnchorView;
        for (ViewParent localViewParent = this.mAnchorView.getParent(); (localViewParent != paramCoordinatorLayout) && (localViewParent != null); localViewParent = localViewParent.getParent())
        {
          if (localViewParent == paramView)
          {
            if (paramCoordinatorLayout.isInEditMode())
            {
              this.mAnchorDirectChild = null;
              this.mAnchorView = null;
              return;
            }
            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
          }
          if ((localViewParent instanceof View)) {
            localView = (View)localViewParent;
          }
        }
        this.mAnchorDirectChild = localView;
        return;
      }
      if (paramCoordinatorLayout.isInEditMode())
      {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
        return;
      }
      throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + paramCoordinatorLayout.getResources().getResourceName(this.mAnchorId) + " to anchor view " + paramView);
    }
    
    private boolean verifyAnchorView(View paramView, CoordinatorLayout paramCoordinatorLayout)
    {
      if (this.mAnchorView.getId() != this.mAnchorId) {
        return false;
      }
      View localView = this.mAnchorView;
      for (ViewParent localViewParent = this.mAnchorView.getParent(); localViewParent != paramCoordinatorLayout; localViewParent = localViewParent.getParent())
      {
        if ((localViewParent == null) || (localViewParent == paramView))
        {
          this.mAnchorDirectChild = null;
          this.mAnchorView = null;
          return false;
        }
        if ((localViewParent instanceof View)) {
          localView = (View)localViewParent;
        }
      }
      this.mAnchorDirectChild = localView;
      return true;
    }
    
    void acceptNestedScroll(boolean paramBoolean)
    {
      this.mDidAcceptNestedScroll = paramBoolean;
    }
    
    boolean checkAnchorChanged()
    {
      return (this.mAnchorView == null) && (this.mAnchorId != -1);
    }
    
    boolean dependsOn(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      return (paramView2 == this.mAnchorDirectChild) || ((this.mBehavior != null) && (this.mBehavior.layoutDependsOn(paramCoordinatorLayout, paramView1, paramView2)));
    }
    
    boolean didBlockInteraction()
    {
      if (this.mBehavior == null) {
        this.mDidBlockInteraction = false;
      }
      return this.mDidBlockInteraction;
    }
    
    View findAnchorView(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      if (this.mAnchorId == -1)
      {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
        return null;
      }
      if ((this.mAnchorView == null) || (!verifyAnchorView(paramView, paramCoordinatorLayout))) {
        resolveAnchorView(paramView, paramCoordinatorLayout);
      }
      return this.mAnchorView;
    }
    
    public int getAnchorId()
    {
      return this.mAnchorId;
    }
    
    public CoordinatorLayout.Behavior getBehavior()
    {
      return this.mBehavior;
    }
    
    boolean getChangedAfterNestedScroll()
    {
      return this.mDidChangeAfterNestedScroll;
    }
    
    Rect getLastChildRect()
    {
      return this.mLastChildRect;
    }
    
    void invalidateAnchor()
    {
      this.mAnchorDirectChild = null;
      this.mAnchorView = null;
    }
    
    boolean isBlockingInteractionBelow(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      if (this.mDidBlockInteraction) {
        return true;
      }
      boolean bool2 = this.mDidBlockInteraction;
      if (this.mBehavior != null) {}
      for (boolean bool1 = this.mBehavior.blocksInteractionBelow(paramCoordinatorLayout, paramView);; bool1 = false)
      {
        bool1 |= bool2;
        this.mDidBlockInteraction = bool1;
        return bool1;
      }
    }
    
    boolean isDirty(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      return (this.mBehavior != null) && (this.mBehavior.isDirty(paramCoordinatorLayout, paramView));
    }
    
    boolean isNestedScrollAccepted()
    {
      return this.mDidAcceptNestedScroll;
    }
    
    void resetChangedAfterNestedScroll()
    {
      this.mDidChangeAfterNestedScroll = false;
    }
    
    void resetNestedScroll()
    {
      this.mDidAcceptNestedScroll = false;
    }
    
    void resetTouchBehaviorTracking()
    {
      this.mDidBlockInteraction = false;
    }
    
    public void setAnchorId(int paramInt)
    {
      invalidateAnchor();
      this.mAnchorId = paramInt;
    }
    
    public void setBehavior(CoordinatorLayout.Behavior paramBehavior)
    {
      if (this.mBehavior != paramBehavior)
      {
        this.mBehavior = paramBehavior;
        this.mBehaviorTag = null;
        this.mBehaviorResolved = true;
      }
    }
    
    void setChangedAfterNestedScroll(boolean paramBoolean)
    {
      this.mDidChangeAfterNestedScroll = paramBoolean;
    }
    
    void setLastChildRect(Rect paramRect)
    {
      this.mLastChildRect.set(paramRect);
    }
  }
  
  class OnPreDrawListener
    implements ViewTreeObserver.OnPreDrawListener
  {
    OnPreDrawListener() {}
    
    public boolean onPreDraw()
    {
      CoordinatorLayout.this.dispatchOnDependentViewChanged(false);
      return true;
    }
  }
  
  protected static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
    {
      public CoordinatorLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new CoordinatorLayout.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public CoordinatorLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new CoordinatorLayout.SavedState[paramAnonymousInt];
      }
    });
    SparseArray<Parcelable> behaviorStates;
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super();
      int j = paramParcel.readInt();
      int[] arrayOfInt = new int[j];
      paramParcel.readIntArray(arrayOfInt);
      paramParcel = paramParcel.readParcelableArray(paramClassLoader);
      this.behaviorStates = new SparseArray(j);
      int i = 0;
      while (i < j)
      {
        this.behaviorStates.append(arrayOfInt[i], paramParcel[i]);
        i += 1;
      }
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      if (this.behaviorStates != null) {}
      int[] arrayOfInt;
      Parcelable[] arrayOfParcelable;
      for (int i = this.behaviorStates.size();; i = 0)
      {
        paramParcel.writeInt(i);
        arrayOfInt = new int[i];
        arrayOfParcelable = new Parcelable[i];
        int j = 0;
        while (j < i)
        {
          arrayOfInt[j] = this.behaviorStates.keyAt(j);
          arrayOfParcelable[j] = ((Parcelable)this.behaviorStates.valueAt(j));
          j += 1;
        }
      }
      paramParcel.writeIntArray(arrayOfInt);
      paramParcel.writeParcelableArray(arrayOfParcelable, paramInt);
    }
  }
  
  static class ViewElevationComparator
    implements Comparator<View>
  {
    public int compare(View paramView1, View paramView2)
    {
      float f1 = ViewCompat.getZ(paramView1);
      float f2 = ViewCompat.getZ(paramView2);
      if (f1 > f2) {
        return -1;
      }
      if (f1 < f2) {
        return 1;
      }
      return 0;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/CoordinatorLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */