package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.R.id;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class CollapsingToolbarLayout
  extends FrameLayout
{
  private static final int SCRIM_ANIMATION_DURATION = 600;
  private final CollapsingTextHelper mCollapsingTextHelper;
  private boolean mCollapsingTitleEnabled;
  private Drawable mContentScrim;
  private int mCurrentOffset;
  private boolean mDrawCollapsingTitle;
  private View mDummyView;
  private int mExpandedMarginBottom;
  private int mExpandedMarginLeft;
  private int mExpandedMarginRight;
  private int mExpandedMarginTop;
  private WindowInsetsCompat mLastInsets;
  private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener;
  private boolean mRefreshToolbar = true;
  private int mScrimAlpha;
  private ValueAnimatorCompat mScrimAnimator;
  private boolean mScrimsAreShown;
  private Drawable mStatusBarScrim;
  private final Rect mTmpRect = new Rect();
  private Toolbar mToolbar;
  private int mToolbarId;
  
  public CollapsingToolbarLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CollapsingToolbarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CollapsingToolbarLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    this.mCollapsingTextHelper = new CollapsingTextHelper(this);
    this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CollapsingToolbarLayout, paramInt, R.style.Widget_Design_CollapsingToolbar);
    this.mCollapsingTextHelper.setExpandedTextGravity(paramContext.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
    this.mCollapsingTextHelper.setCollapsedTextGravity(paramContext.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
    paramInt = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
    this.mExpandedMarginBottom = paramInt;
    this.mExpandedMarginRight = paramInt;
    this.mExpandedMarginTop = paramInt;
    this.mExpandedMarginLeft = paramInt;
    int i;
    if (ViewCompat.getLayoutDirection(this) == 1)
    {
      paramInt = 1;
      if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart))
      {
        i = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        if (paramInt == 0) {
          break label391;
        }
        this.mExpandedMarginRight = i;
      }
      label163:
      if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd))
      {
        i = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        if (paramInt == 0) {
          break label400;
        }
        this.mExpandedMarginLeft = i;
      }
    }
    for (;;)
    {
      if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
        this.mExpandedMarginTop = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
      }
      if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
        this.mExpandedMarginBottom = paramContext.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
      }
      this.mCollapsingTitleEnabled = paramContext.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, true);
      setTitle(paramContext.getText(R.styleable.CollapsingToolbarLayout_title));
      this.mCollapsingTextHelper.setExpandedTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
      this.mCollapsingTextHelper.setCollapsedTextAppearance(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
      if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
        this.mCollapsingTextHelper.setExpandedTextAppearance(paramContext.getResourceId(R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
      }
      if (paramContext.hasValue(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
        this.mCollapsingTextHelper.setCollapsedTextAppearance(paramContext.getResourceId(R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
      }
      setContentScrim(paramContext.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
      setStatusBarScrim(paramContext.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
      this.mToolbarId = paramContext.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
      paramContext.recycle();
      setWillNotDraw(false);
      ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
      {
        public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
        {
          CollapsingToolbarLayout.access$002(CollapsingToolbarLayout.this, paramAnonymousWindowInsetsCompat);
          CollapsingToolbarLayout.this.requestLayout();
          return paramAnonymousWindowInsetsCompat.consumeSystemWindowInsets();
        }
      });
      return;
      paramInt = 0;
      break;
      label391:
      this.mExpandedMarginLeft = i;
      break label163;
      label400:
      this.mExpandedMarginRight = i;
    }
  }
  
  private void animateScrim(int paramInt)
  {
    ensureToolbar();
    if (this.mScrimAnimator == null)
    {
      this.mScrimAnimator = ViewUtils.createAnimator();
      this.mScrimAnimator.setDuration(600);
      this.mScrimAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
      this.mScrimAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
        {
          CollapsingToolbarLayout.this.setScrimAlpha(paramAnonymousValueAnimatorCompat.getAnimatedIntValue());
        }
      });
    }
    for (;;)
    {
      this.mScrimAnimator.setIntValues(this.mScrimAlpha, paramInt);
      this.mScrimAnimator.start();
      return;
      if (this.mScrimAnimator.isRunning()) {
        this.mScrimAnimator.cancel();
      }
    }
  }
  
  private void ensureToolbar()
  {
    if (!this.mRefreshToolbar) {
      return;
    }
    Object localObject2 = null;
    Object localObject3 = null;
    int i = 0;
    int j = getChildCount();
    Object localObject1 = localObject3;
    View localView;
    if (i < j)
    {
      localView = getChildAt(i);
      localObject1 = localObject2;
      if (!(localView instanceof Toolbar)) {
        break label114;
      }
      if (this.mToolbarId == -1) {
        break label124;
      }
      if (this.mToolbarId != localView.getId()) {
        break label100;
      }
    }
    label100:
    label114:
    label124:
    for (localObject1 = (Toolbar)localView;; localObject1 = (Toolbar)localView)
    {
      localObject3 = localObject1;
      if (localObject1 == null) {
        localObject3 = localObject2;
      }
      this.mToolbar = ((Toolbar)localObject3);
      updateDummyView();
      this.mRefreshToolbar = false;
      return;
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = (Toolbar)localView;
      }
      i += 1;
      localObject2 = localObject1;
      break;
    }
  }
  
  private static ViewOffsetHelper getViewOffsetHelper(View paramView)
  {
    ViewOffsetHelper localViewOffsetHelper2 = (ViewOffsetHelper)paramView.getTag(R.id.view_offset_helper);
    ViewOffsetHelper localViewOffsetHelper1 = localViewOffsetHelper2;
    if (localViewOffsetHelper2 == null)
    {
      localViewOffsetHelper1 = new ViewOffsetHelper(paramView);
      paramView.setTag(R.id.view_offset_helper, localViewOffsetHelper1);
    }
    return localViewOffsetHelper1;
  }
  
  private void setScrimAlpha(int paramInt)
  {
    if (paramInt != this.mScrimAlpha)
    {
      if ((this.mContentScrim != null) && (this.mToolbar != null)) {
        ViewCompat.postInvalidateOnAnimation(this.mToolbar);
      }
      this.mScrimAlpha = paramInt;
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void updateDummyView()
  {
    if ((!this.mCollapsingTitleEnabled) && (this.mDummyView != null))
    {
      ViewParent localViewParent = this.mDummyView.getParent();
      if ((localViewParent instanceof ViewGroup)) {
        ((ViewGroup)localViewParent).removeView(this.mDummyView);
      }
    }
    if ((this.mCollapsingTitleEnabled) && (this.mToolbar != null))
    {
      if (this.mDummyView == null) {
        this.mDummyView = new View(getContext());
      }
      if (this.mDummyView.getParent() == null) {
        this.mToolbar.addView(this.mDummyView, -1, -1);
      }
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    ensureToolbar();
    if ((this.mToolbar == null) && (this.mContentScrim != null) && (this.mScrimAlpha > 0))
    {
      this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
      this.mContentScrim.draw(paramCanvas);
    }
    if ((this.mCollapsingTitleEnabled) && (this.mDrawCollapsingTitle)) {
      this.mCollapsingTextHelper.draw(paramCanvas);
    }
    if ((this.mStatusBarScrim != null) && (this.mScrimAlpha > 0)) {
      if (this.mLastInsets == null) {
        break label153;
      }
    }
    label153:
    for (int i = this.mLastInsets.getSystemWindowInsetTop();; i = 0)
    {
      if (i > 0)
      {
        this.mStatusBarScrim.setBounds(0, -this.mCurrentOffset, getWidth(), i - this.mCurrentOffset);
        this.mStatusBarScrim.mutate().setAlpha(this.mScrimAlpha);
        this.mStatusBarScrim.draw(paramCanvas);
      }
      return;
    }
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    ensureToolbar();
    if ((paramView == this.mToolbar) && (this.mContentScrim != null) && (this.mScrimAlpha > 0))
    {
      this.mContentScrim.mutate().setAlpha(this.mScrimAlpha);
      this.mContentScrim.draw(paramCanvas);
    }
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(super.generateDefaultLayoutParams());
  }
  
  public FrameLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getCollapsedTitleGravity()
  {
    return this.mCollapsingTextHelper.getCollapsedTextGravity();
  }
  
  @NonNull
  public Typeface getCollapsedTitleTypeface()
  {
    return this.mCollapsingTextHelper.getCollapsedTypeface();
  }
  
  public Drawable getContentScrim()
  {
    return this.mContentScrim;
  }
  
  public int getExpandedTitleGravity()
  {
    return this.mCollapsingTextHelper.getExpandedTextGravity();
  }
  
  @NonNull
  public Typeface getExpandedTitleTypeface()
  {
    return this.mCollapsingTextHelper.getExpandedTypeface();
  }
  
  final int getScrimTriggerOffset()
  {
    return ViewCompat.getMinimumHeight(this) * 2;
  }
  
  public Drawable getStatusBarScrim()
  {
    return this.mStatusBarScrim;
  }
  
  @Nullable
  public CharSequence getTitle()
  {
    if (this.mCollapsingTitleEnabled) {
      return this.mCollapsingTextHelper.getText();
    }
    return null;
  }
  
  public boolean isTitleEnabled()
  {
    return this.mCollapsingTitleEnabled;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    ViewParent localViewParent = getParent();
    if ((localViewParent instanceof AppBarLayout))
    {
      if (this.mOnOffsetChangedListener == null) {
        this.mOnOffsetChangedListener = new OffsetUpdateListener(null);
      }
      ((AppBarLayout)localViewParent).addOnOffsetChangedListener(this.mOnOffsetChangedListener);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    ViewParent localViewParent = getParent();
    if ((this.mOnOffsetChangedListener != null) && ((localViewParent instanceof AppBarLayout))) {
      ((AppBarLayout)localViewParent).removeOnOffsetChangedListener(this.mOnOffsetChangedListener);
    }
    super.onDetachedFromWindow();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((this.mCollapsingTitleEnabled) && (this.mDummyView != null))
    {
      this.mDrawCollapsingTitle = this.mDummyView.isShown();
      if (this.mDrawCollapsingTitle)
      {
        ViewGroupUtils.getDescendantRect(this, this.mDummyView, this.mTmpRect);
        this.mCollapsingTextHelper.setCollapsedBounds(this.mTmpRect.left, paramInt4 - this.mTmpRect.height(), this.mTmpRect.right, paramInt4);
        this.mCollapsingTextHelper.setExpandedBounds(this.mExpandedMarginLeft, this.mTmpRect.bottom + this.mExpandedMarginTop, paramInt3 - paramInt1 - this.mExpandedMarginRight, paramInt4 - paramInt2 - this.mExpandedMarginBottom);
        this.mCollapsingTextHelper.recalculate();
      }
    }
    paramInt1 = 0;
    paramInt2 = getChildCount();
    while (paramInt1 < paramInt2)
    {
      View localView = getChildAt(paramInt1);
      if ((this.mLastInsets != null) && (!ViewCompat.getFitsSystemWindows(localView)))
      {
        paramInt3 = this.mLastInsets.getSystemWindowInsetTop();
        if (localView.getTop() < paramInt3) {
          localView.offsetTopAndBottom(paramInt3);
        }
      }
      getViewOffsetHelper(localView).onViewLayout();
      paramInt1 += 1;
    }
    if (this.mToolbar != null)
    {
      if ((this.mCollapsingTitleEnabled) && (TextUtils.isEmpty(this.mCollapsingTextHelper.getText()))) {
        this.mCollapsingTextHelper.setText(this.mToolbar.getTitle());
      }
      setMinimumHeight(this.mToolbar.getHeight());
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    ensureToolbar();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mContentScrim != null) {
      this.mContentScrim.setBounds(0, 0, paramInt1, paramInt2);
    }
  }
  
  public void setCollapsedTitleGravity(int paramInt)
  {
    this.mCollapsingTextHelper.setCollapsedTextGravity(paramInt);
  }
  
  public void setCollapsedTitleTextAppearance(@StyleRes int paramInt)
  {
    this.mCollapsingTextHelper.setCollapsedTextAppearance(paramInt);
  }
  
  public void setCollapsedTitleTextColor(@ColorInt int paramInt)
  {
    this.mCollapsingTextHelper.setCollapsedTextColor(paramInt);
  }
  
  public void setCollapsedTitleTypeface(@Nullable Typeface paramTypeface)
  {
    this.mCollapsingTextHelper.setCollapsedTypeface(paramTypeface);
  }
  
  public void setContentScrim(@Nullable Drawable paramDrawable)
  {
    if (this.mContentScrim != paramDrawable)
    {
      if (this.mContentScrim != null) {
        this.mContentScrim.setCallback(null);
      }
      if (paramDrawable == null) {
        break label67;
      }
      this.mContentScrim = paramDrawable.mutate();
      paramDrawable.setBounds(0, 0, getWidth(), getHeight());
      paramDrawable.setCallback(this);
      paramDrawable.setAlpha(this.mScrimAlpha);
    }
    for (;;)
    {
      ViewCompat.postInvalidateOnAnimation(this);
      return;
      label67:
      this.mContentScrim = null;
    }
  }
  
  public void setContentScrimColor(@ColorInt int paramInt)
  {
    setContentScrim(new ColorDrawable(paramInt));
  }
  
  public void setContentScrimResource(@DrawableRes int paramInt)
  {
    setContentScrim(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setExpandedTitleColor(@ColorInt int paramInt)
  {
    this.mCollapsingTextHelper.setExpandedTextColor(paramInt);
  }
  
  public void setExpandedTitleGravity(int paramInt)
  {
    this.mCollapsingTextHelper.setExpandedTextGravity(paramInt);
  }
  
  public void setExpandedTitleTextAppearance(@StyleRes int paramInt)
  {
    this.mCollapsingTextHelper.setExpandedTextAppearance(paramInt);
  }
  
  public void setExpandedTitleTypeface(@Nullable Typeface paramTypeface)
  {
    this.mCollapsingTextHelper.setExpandedTypeface(paramTypeface);
  }
  
  public void setScrimsShown(boolean paramBoolean)
  {
    if ((ViewCompat.isLaidOut(this)) && (!isInEditMode())) {}
    for (boolean bool = true;; bool = false)
    {
      setScrimsShown(paramBoolean, bool);
      return;
    }
  }
  
  public void setScrimsShown(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 255;
    if (this.mScrimsAreShown != paramBoolean1)
    {
      if (!paramBoolean2) {
        break label36;
      }
      if (!paramBoolean1) {
        break label31;
      }
    }
    for (;;)
    {
      animateScrim(i);
      this.mScrimsAreShown = paramBoolean1;
      return;
      label31:
      i = 0;
    }
    label36:
    if (paramBoolean1) {}
    for (;;)
    {
      setScrimAlpha(i);
      break;
      i = 0;
    }
  }
  
  public void setStatusBarScrim(@Nullable Drawable paramDrawable)
  {
    if (this.mStatusBarScrim != paramDrawable)
    {
      if (this.mStatusBarScrim != null) {
        this.mStatusBarScrim.setCallback(null);
      }
      this.mStatusBarScrim = paramDrawable;
      paramDrawable.setCallback(this);
      paramDrawable.mutate().setAlpha(this.mScrimAlpha);
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void setStatusBarScrimColor(@ColorInt int paramInt)
  {
    setStatusBarScrim(new ColorDrawable(paramInt));
  }
  
  public void setStatusBarScrimResource(@DrawableRes int paramInt)
  {
    setStatusBarScrim(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setTitle(@Nullable CharSequence paramCharSequence)
  {
    this.mCollapsingTextHelper.setText(paramCharSequence);
  }
  
  public void setTitleEnabled(boolean paramBoolean)
  {
    if (paramBoolean != this.mCollapsingTitleEnabled)
    {
      this.mCollapsingTitleEnabled = paramBoolean;
      updateDummyView();
      requestLayout();
    }
  }
  
  public static class LayoutParams
    extends FrameLayout.LayoutParams
  {
    public static final int COLLAPSE_MODE_OFF = 0;
    public static final int COLLAPSE_MODE_PARALLAX = 2;
    public static final int COLLAPSE_MODE_PIN = 1;
    private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5F;
    int mCollapseMode = 0;
    float mParallaxMult = 0.5F;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2, paramInt3);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CollapsingAppBarLayout_LayoutParams);
      this.mCollapseMode = paramContext.getInt(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseMode, 0);
      setParallaxMultiplier(paramContext.getFloat(R.styleable.CollapsingAppBarLayout_LayoutParams_layout_collapseParallaxMultiplier, 0.5F));
      paramContext.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public LayoutParams(FrameLayout.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public int getCollapseMode()
    {
      return this.mCollapseMode;
    }
    
    public float getParallaxMultiplier()
    {
      return this.mParallaxMult;
    }
    
    public void setCollapseMode(int paramInt)
    {
      this.mCollapseMode = paramInt;
    }
    
    public void setParallaxMultiplier(float paramFloat)
    {
      this.mParallaxMult = paramFloat;
    }
  }
  
  private class OffsetUpdateListener
    implements AppBarLayout.OnOffsetChangedListener
  {
    private OffsetUpdateListener() {}
    
    public void onOffsetChanged(AppBarLayout paramAppBarLayout, int paramInt)
    {
      boolean bool = false;
      CollapsingToolbarLayout.access$302(CollapsingToolbarLayout.this, paramInt);
      int i;
      int k;
      label51:
      Object localObject;
      CollapsingToolbarLayout.LayoutParams localLayoutParams;
      ViewOffsetHelper localViewOffsetHelper;
      if (CollapsingToolbarLayout.this.mLastInsets != null)
      {
        i = CollapsingToolbarLayout.this.mLastInsets.getSystemWindowInsetTop();
        k = paramAppBarLayout.getTotalScrollRange();
        j = 0;
        m = CollapsingToolbarLayout.this.getChildCount();
        if (j >= m) {
          break label177;
        }
        localObject = CollapsingToolbarLayout.this.getChildAt(j);
        localLayoutParams = (CollapsingToolbarLayout.LayoutParams)((View)localObject).getLayoutParams();
        localViewOffsetHelper = CollapsingToolbarLayout.getViewOffsetHelper((View)localObject);
        switch (localLayoutParams.mCollapseMode)
        {
        }
      }
      for (;;)
      {
        j += 1;
        break label51;
        i = 0;
        break;
        if (CollapsingToolbarLayout.this.getHeight() - i + paramInt >= ((View)localObject).getHeight())
        {
          localViewOffsetHelper.setTopAndBottomOffset(-paramInt);
          continue;
          localViewOffsetHelper.setTopAndBottomOffset(Math.round(-paramInt * localLayoutParams.mParallaxMult));
        }
      }
      label177:
      if ((CollapsingToolbarLayout.this.mContentScrim != null) || (CollapsingToolbarLayout.this.mStatusBarScrim != null))
      {
        localObject = CollapsingToolbarLayout.this;
        if (CollapsingToolbarLayout.this.getHeight() + paramInt < CollapsingToolbarLayout.this.getScrimTriggerOffset() + i) {
          bool = true;
        }
        ((CollapsingToolbarLayout)localObject).setScrimsShown(bool);
      }
      if ((CollapsingToolbarLayout.this.mStatusBarScrim != null) && (i > 0)) {
        ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
      }
      int j = CollapsingToolbarLayout.this.getHeight();
      int m = ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this);
      CollapsingToolbarLayout.this.mCollapsingTextHelper.setExpansionFraction(Math.abs(paramInt) / (j - m - i));
      if (Math.abs(paramInt) == k)
      {
        ViewCompat.setElevation(paramAppBarLayout, paramAppBarLayout.getTargetElevation());
        return;
      }
      ViewCompat.setElevation(paramAppBarLayout, 0.0F);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/CollapsingToolbarLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */