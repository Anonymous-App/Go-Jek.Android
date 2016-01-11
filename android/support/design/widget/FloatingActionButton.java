package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.R.dimen;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageButton;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class FloatingActionButton
  extends ImageButton
{
  private static final String LOG_TAG = "FloatingActionButton";
  private static final int SIZE_MINI = 1;
  private static final int SIZE_NORMAL = 0;
  private ColorStateList mBackgroundTint;
  private PorterDuff.Mode mBackgroundTintMode;
  private int mBorderWidth;
  private int mContentPadding;
  private final FloatingActionButtonImpl mImpl;
  private int mRippleColor;
  private final Rect mShadowPadding;
  private int mSize;
  
  public FloatingActionButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    this.mShadowPadding = new Rect();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FloatingActionButton, paramInt, R.style.Widget_Design_FloatingActionButton);
    this.mBackgroundTint = paramContext.getColorStateList(R.styleable.FloatingActionButton_backgroundTint);
    this.mBackgroundTintMode = parseTintMode(paramContext.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
    this.mRippleColor = paramContext.getColor(R.styleable.FloatingActionButton_rippleColor, 0);
    this.mSize = paramContext.getInt(R.styleable.FloatingActionButton_fabSize, 0);
    this.mBorderWidth = paramContext.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
    float f1 = paramContext.getDimension(R.styleable.FloatingActionButton_elevation, 0.0F);
    float f2 = paramContext.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
    paramContext.recycle();
    paramContext = new ShadowViewDelegate()
    {
      public float getRadius()
      {
        return FloatingActionButton.this.getSizeDimension() / 2.0F;
      }
      
      public void setBackgroundDrawable(Drawable paramAnonymousDrawable)
      {
        FloatingActionButton.this.setBackgroundDrawable(paramAnonymousDrawable);
      }
      
      public void setShadowPadding(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        FloatingActionButton.this.mShadowPadding.set(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4);
        FloatingActionButton.this.setPadding(FloatingActionButton.this.mContentPadding + paramAnonymousInt1, FloatingActionButton.this.mContentPadding + paramAnonymousInt2, FloatingActionButton.this.mContentPadding + paramAnonymousInt3, FloatingActionButton.this.mContentPadding + paramAnonymousInt4);
      }
    };
    paramInt = Build.VERSION.SDK_INT;
    if (paramInt >= 21) {
      this.mImpl = new FloatingActionButtonLollipop(this, paramContext);
    }
    for (;;)
    {
      paramInt = (int)getResources().getDimension(R.dimen.design_fab_content_size);
      this.mContentPadding = ((getSizeDimension() - paramInt) / 2);
      this.mImpl.setBackgroundDrawable(this.mBackgroundTint, this.mBackgroundTintMode, this.mRippleColor, this.mBorderWidth);
      this.mImpl.setElevation(f1);
      this.mImpl.setPressedTranslationZ(f2);
      return;
      if (paramInt >= 12) {
        this.mImpl = new FloatingActionButtonHoneycombMr1(this, paramContext);
      } else {
        this.mImpl = new FloatingActionButtonEclairMr1(this, paramContext);
      }
    }
  }
  
  static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode)
  {
    switch (paramInt)
    {
    default: 
      return paramMode;
    case 3: 
      return PorterDuff.Mode.SRC_OVER;
    case 5: 
      return PorterDuff.Mode.SRC_IN;
    case 9: 
      return PorterDuff.Mode.SRC_ATOP;
    case 14: 
      return PorterDuff.Mode.MULTIPLY;
    }
    return PorterDuff.Mode.SCREEN;
  }
  
  private static int resolveAdjustedSize(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    switch (i)
    {
    default: 
      return paramInt1;
    case 0: 
      return paramInt1;
    case -2147483648: 
      return Math.min(paramInt1, paramInt2);
    }
    return paramInt2;
  }
  
  @Nullable
  private FloatingActionButtonImpl.InternalVisibilityChangedListener wrapOnVisibilityChangedListener(@Nullable final OnVisibilityChangedListener paramOnVisibilityChangedListener)
  {
    if (paramOnVisibilityChangedListener == null) {
      return null;
    }
    new FloatingActionButtonImpl.InternalVisibilityChangedListener()
    {
      public void onHidden()
      {
        paramOnVisibilityChangedListener.onHidden(FloatingActionButton.this);
      }
      
      public void onShown()
      {
        paramOnVisibilityChangedListener.onShown(FloatingActionButton.this);
      }
    };
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    this.mImpl.onDrawableStateChanged(getDrawableState());
  }
  
  @Nullable
  public ColorStateList getBackgroundTintList()
  {
    return this.mBackgroundTint;
  }
  
  @Nullable
  public PorterDuff.Mode getBackgroundTintMode()
  {
    return this.mBackgroundTintMode;
  }
  
  final int getSizeDimension()
  {
    switch (this.mSize)
    {
    default: 
      return getResources().getDimensionPixelSize(R.dimen.design_fab_size_normal);
    }
    return getResources().getDimensionPixelSize(R.dimen.design_fab_size_mini);
  }
  
  public void hide()
  {
    this.mImpl.hide(null);
  }
  
  public void hide(@Nullable OnVisibilityChangedListener paramOnVisibilityChangedListener)
  {
    this.mImpl.hide(wrapOnVisibilityChangedListener(paramOnVisibilityChangedListener));
  }
  
  @TargetApi(11)
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    this.mImpl.jumpDrawableToCurrentState();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mImpl.onAttachedToWindow();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mImpl.onDetachedFromWindow();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getSizeDimension();
    paramInt1 = Math.min(resolveAdjustedSize(i, paramInt1), resolveAdjustedSize(i, paramInt2));
    setMeasuredDimension(this.mShadowPadding.left + paramInt1 + this.mShadowPadding.right, this.mShadowPadding.top + paramInt1 + this.mShadowPadding.bottom);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundResource(int paramInt)
  {
    Log.i("FloatingActionButton", "Setting a custom background is not supported.");
  }
  
  public void setBackgroundTintList(@Nullable ColorStateList paramColorStateList)
  {
    if (this.mBackgroundTint != paramColorStateList)
    {
      this.mBackgroundTint = paramColorStateList;
      this.mImpl.setBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setBackgroundTintMode(@Nullable PorterDuff.Mode paramMode)
  {
    if (this.mBackgroundTintMode != paramMode)
    {
      this.mBackgroundTintMode = paramMode;
      this.mImpl.setBackgroundTintMode(paramMode);
    }
  }
  
  public void setRippleColor(@ColorInt int paramInt)
  {
    if (this.mRippleColor != paramInt)
    {
      this.mRippleColor = paramInt;
      this.mImpl.setRippleColor(paramInt);
    }
  }
  
  public void show()
  {
    this.mImpl.show(null);
  }
  
  public void show(@Nullable OnVisibilityChangedListener paramOnVisibilityChangedListener)
  {
    this.mImpl.show(wrapOnVisibilityChangedListener(paramOnVisibilityChangedListener));
  }
  
  public static class Behavior
    extends CoordinatorLayout.Behavior<FloatingActionButton>
  {
    private static final boolean SNACKBAR_BEHAVIOR_ENABLED;
    private float mFabTranslationY;
    private ValueAnimatorCompat mFabTranslationYAnimator;
    private Rect mTmpRect;
    
    static
    {
      if (Build.VERSION.SDK_INT >= 11) {}
      for (boolean bool = true;; bool = false)
      {
        SNACKBAR_BEHAVIOR_ENABLED = bool;
        return;
      }
    }
    
    private float getFabTranslationYForSnackbar(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton)
    {
      float f1 = 0.0F;
      List localList = paramCoordinatorLayout.getDependencies(paramFloatingActionButton);
      int i = 0;
      int j = localList.size();
      while (i < j)
      {
        View localView = (View)localList.get(i);
        float f2 = f1;
        if ((localView instanceof Snackbar.SnackbarLayout))
        {
          f2 = f1;
          if (paramCoordinatorLayout.doViewsOverlap(paramFloatingActionButton, localView)) {
            f2 = Math.min(f1, ViewCompat.getTranslationY(localView) - localView.getHeight());
          }
        }
        i += 1;
        f1 = f2;
      }
      return f1;
    }
    
    private void offsetIfNeeded(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton)
    {
      Rect localRect = paramFloatingActionButton.mShadowPadding;
      CoordinatorLayout.LayoutParams localLayoutParams;
      int j;
      int i;
      if ((localRect != null) && (localRect.centerX() > 0) && (localRect.centerY() > 0))
      {
        localLayoutParams = (CoordinatorLayout.LayoutParams)paramFloatingActionButton.getLayoutParams();
        j = 0;
        i = 0;
        if (paramFloatingActionButton.getRight() < paramCoordinatorLayout.getWidth() - localLayoutParams.rightMargin) {
          break label100;
        }
        i = localRect.right;
        if (paramFloatingActionButton.getBottom() < paramCoordinatorLayout.getBottom() - localLayoutParams.bottomMargin) {
          break label122;
        }
        j = localRect.bottom;
      }
      for (;;)
      {
        paramFloatingActionButton.offsetTopAndBottom(j);
        paramFloatingActionButton.offsetLeftAndRight(i);
        return;
        label100:
        if (paramFloatingActionButton.getLeft() > localLayoutParams.leftMargin) {
          break;
        }
        i = -localRect.left;
        break;
        label122:
        if (paramFloatingActionButton.getTop() <= localLayoutParams.topMargin) {
          j = -localRect.top;
        }
      }
    }
    
    private void updateFabTranslationForSnackbar(CoordinatorLayout paramCoordinatorLayout, final FloatingActionButton paramFloatingActionButton, View paramView)
    {
      if (paramFloatingActionButton.getVisibility() != 0) {}
      float f1;
      do
      {
        return;
        f1 = getFabTranslationYForSnackbar(paramCoordinatorLayout, paramFloatingActionButton);
      } while (this.mFabTranslationY == f1);
      float f2 = ViewCompat.getTranslationY(paramFloatingActionButton);
      if ((this.mFabTranslationYAnimator != null) && (this.mFabTranslationYAnimator.isRunning())) {
        this.mFabTranslationYAnimator.cancel();
      }
      if (Math.abs(f2 - f1) > paramFloatingActionButton.getHeight() * 0.667F)
      {
        if (this.mFabTranslationYAnimator == null)
        {
          this.mFabTranslationYAnimator = ViewUtils.createAnimator();
          this.mFabTranslationYAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
          this.mFabTranslationYAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener()
          {
            public void onAnimationUpdate(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
            {
              ViewCompat.setTranslationY(paramFloatingActionButton, paramAnonymousValueAnimatorCompat.getAnimatedFloatValue());
            }
          });
        }
        this.mFabTranslationYAnimator.setFloatValues(f2, f1);
        this.mFabTranslationYAnimator.start();
      }
      for (;;)
      {
        this.mFabTranslationY = f1;
        return;
        ViewCompat.setTranslationY(paramFloatingActionButton, f1);
      }
    }
    
    private boolean updateFabVisibility(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, FloatingActionButton paramFloatingActionButton)
    {
      if (((CoordinatorLayout.LayoutParams)paramFloatingActionButton.getLayoutParams()).getAnchorId() != paramAppBarLayout.getId()) {
        return false;
      }
      if (this.mTmpRect == null) {
        this.mTmpRect = new Rect();
      }
      Rect localRect = this.mTmpRect;
      ViewGroupUtils.getDescendantRect(paramCoordinatorLayout, paramAppBarLayout, localRect);
      if (localRect.bottom <= paramAppBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
        paramFloatingActionButton.hide();
      }
      for (;;)
      {
        return true;
        paramFloatingActionButton.show();
      }
    }
    
    public boolean layoutDependsOn(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, View paramView)
    {
      return (SNACKBAR_BEHAVIOR_ENABLED) && ((paramView instanceof Snackbar.SnackbarLayout));
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, View paramView)
    {
      if ((paramView instanceof Snackbar.SnackbarLayout)) {
        updateFabTranslationForSnackbar(paramCoordinatorLayout, paramFloatingActionButton, paramView);
      }
      for (;;)
      {
        return false;
        if ((paramView instanceof AppBarLayout)) {
          updateFabVisibility(paramCoordinatorLayout, (AppBarLayout)paramView, paramFloatingActionButton);
        }
      }
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, int paramInt)
    {
      List localList = paramCoordinatorLayout.getDependencies(paramFloatingActionButton);
      int i = 0;
      int j = localList.size();
      for (;;)
      {
        if (i < j)
        {
          View localView = (View)localList.get(i);
          if ((!(localView instanceof AppBarLayout)) || (!updateFabVisibility(paramCoordinatorLayout, (AppBarLayout)localView, paramFloatingActionButton))) {}
        }
        else
        {
          paramCoordinatorLayout.onLayoutChild(paramFloatingActionButton, paramInt);
          offsetIfNeeded(paramCoordinatorLayout, paramFloatingActionButton);
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static abstract class OnVisibilityChangedListener
  {
    public void onHidden(FloatingActionButton paramFloatingActionButton) {}
    
    public void onShown(FloatingActionButton paramFloatingActionButton) {}
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/FloatingActionButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */