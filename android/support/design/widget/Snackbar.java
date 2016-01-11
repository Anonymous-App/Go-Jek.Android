package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.R.anim;
import android.support.design.R.dimen;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.design.R.styleable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Snackbar
{
  private static final int ANIMATION_DURATION = 250;
  private static final int ANIMATION_FADE_DURATION = 180;
  public static final int LENGTH_INDEFINITE = -2;
  public static final int LENGTH_LONG = 0;
  public static final int LENGTH_SHORT = -1;
  private static final int MSG_DISMISS = 1;
  private static final int MSG_SHOW = 0;
  private static final Handler sHandler = new Handler(Looper.getMainLooper(), new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return false;
      case 0: 
        ((Snackbar)paramAnonymousMessage.obj).showView();
        return true;
      }
      ((Snackbar)paramAnonymousMessage.obj).hideView(paramAnonymousMessage.arg1);
      return true;
    }
  });
  private Callback mCallback;
  private final Context mContext;
  private int mDuration;
  private final SnackbarManager.Callback mManagerCallback = new SnackbarManager.Callback()
  {
    public void dismiss(int paramAnonymousInt)
    {
      Snackbar.sHandler.sendMessage(Snackbar.sHandler.obtainMessage(1, paramAnonymousInt, 0, Snackbar.this));
    }
    
    public void show()
    {
      Snackbar.sHandler.sendMessage(Snackbar.sHandler.obtainMessage(0, Snackbar.this));
    }
  };
  private final ViewGroup mTargetParent;
  private final SnackbarLayout mView;
  
  private Snackbar(ViewGroup paramViewGroup)
  {
    this.mTargetParent = paramViewGroup;
    this.mContext = paramViewGroup.getContext();
    ThemeUtils.checkAppCompatTheme(this.mContext);
    this.mView = ((SnackbarLayout)LayoutInflater.from(this.mContext).inflate(R.layout.design_layout_snackbar, this.mTargetParent, false));
  }
  
  private void animateViewIn()
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      ViewCompat.setTranslationY(this.mView, this.mView.getHeight());
      ViewCompat.animate(this.mView).translationY(0.0F).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new ViewPropertyAnimatorListenerAdapter()
      {
        public void onAnimationEnd(View paramAnonymousView)
        {
          if (Snackbar.this.mCallback != null) {
            Snackbar.this.mCallback.onShown(Snackbar.this);
          }
          SnackbarManager.getInstance().onShown(Snackbar.this.mManagerCallback);
        }
        
        public void onAnimationStart(View paramAnonymousView)
        {
          Snackbar.this.mView.animateChildrenIn(70, 180);
        }
      }).start();
      return;
    }
    Animation localAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_snackbar_in);
    localAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    localAnimation.setDuration(250L);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        if (Snackbar.this.mCallback != null) {
          Snackbar.this.mCallback.onShown(Snackbar.this);
        }
        SnackbarManager.getInstance().onShown(Snackbar.this.mManagerCallback);
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    this.mView.startAnimation(localAnimation);
  }
  
  private void animateViewOut(final int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      ViewCompat.animate(this.mView).translationY(this.mView.getHeight()).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setDuration(250L).setListener(new ViewPropertyAnimatorListenerAdapter()
      {
        public void onAnimationEnd(View paramAnonymousView)
        {
          Snackbar.this.onViewHidden(paramInt);
        }
        
        public void onAnimationStart(View paramAnonymousView)
        {
          Snackbar.this.mView.animateChildrenOut(0, 180);
        }
      }).start();
      return;
    }
    Animation localAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R.anim.design_snackbar_out);
    localAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    localAnimation.setDuration(250L);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        Snackbar.this.onViewHidden(paramInt);
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation) {}
    });
    this.mView.startAnimation(localAnimation);
  }
  
  private void dispatchDismiss(int paramInt)
  {
    SnackbarManager.getInstance().dismiss(this.mManagerCallback, paramInt);
  }
  
  private static ViewGroup findSuitableParent(View paramView)
  {
    Object localObject2 = null;
    View localView = paramView;
    if ((localView instanceof CoordinatorLayout)) {
      return (ViewGroup)localView;
    }
    Object localObject1 = localObject2;
    if ((localView instanceof FrameLayout))
    {
      if (localView.getId() == 16908290) {
        return (ViewGroup)localView;
      }
      localObject1 = (ViewGroup)localView;
    }
    paramView = localView;
    if (localView != null)
    {
      paramView = localView.getParent();
      if (!(paramView instanceof View)) {
        break label78;
      }
    }
    label78:
    for (paramView = (View)paramView;; paramView = null)
    {
      localObject2 = localObject1;
      localView = paramView;
      if (paramView != null) {
        break;
      }
      return (ViewGroup)localObject1;
    }
  }
  
  private boolean isBeingDragged()
  {
    boolean bool2 = false;
    Object localObject = this.mView.getLayoutParams();
    boolean bool1 = bool2;
    if ((localObject instanceof CoordinatorLayout.LayoutParams))
    {
      localObject = ((CoordinatorLayout.LayoutParams)localObject).getBehavior();
      bool1 = bool2;
      if ((localObject instanceof SwipeDismissBehavior))
      {
        bool1 = bool2;
        if (((SwipeDismissBehavior)localObject).getDragState() != 0) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  @NonNull
  public static Snackbar make(@NonNull View paramView, @StringRes int paramInt1, int paramInt2)
  {
    return make(paramView, paramView.getResources().getText(paramInt1), paramInt2);
  }
  
  @NonNull
  public static Snackbar make(@NonNull View paramView, @NonNull CharSequence paramCharSequence, int paramInt)
  {
    paramView = new Snackbar(findSuitableParent(paramView));
    paramView.setText(paramCharSequence);
    paramView.setDuration(paramInt);
    return paramView;
  }
  
  private void onViewHidden(int paramInt)
  {
    SnackbarManager.getInstance().onDismissed(this.mManagerCallback);
    if (this.mCallback != null) {
      this.mCallback.onDismissed(this, paramInt);
    }
    ViewParent localViewParent = this.mView.getParent();
    if ((localViewParent instanceof ViewGroup)) {
      ((ViewGroup)localViewParent).removeView(this.mView);
    }
  }
  
  public void dismiss()
  {
    dispatchDismiss(3);
  }
  
  public int getDuration()
  {
    return this.mDuration;
  }
  
  @NonNull
  public View getView()
  {
    return this.mView;
  }
  
  final void hideView(int paramInt)
  {
    if ((this.mView.getVisibility() != 0) || (isBeingDragged()))
    {
      onViewHidden(paramInt);
      return;
    }
    animateViewOut(paramInt);
  }
  
  public boolean isShown()
  {
    return SnackbarManager.getInstance().isCurrent(this.mManagerCallback);
  }
  
  public boolean isShownOrQueued()
  {
    return SnackbarManager.getInstance().isCurrentOrNext(this.mManagerCallback);
  }
  
  @NonNull
  public Snackbar setAction(@StringRes int paramInt, View.OnClickListener paramOnClickListener)
  {
    return setAction(this.mContext.getText(paramInt), paramOnClickListener);
  }
  
  @NonNull
  public Snackbar setAction(CharSequence paramCharSequence, final View.OnClickListener paramOnClickListener)
  {
    Button localButton = this.mView.getActionView();
    if ((TextUtils.isEmpty(paramCharSequence)) || (paramOnClickListener == null))
    {
      localButton.setVisibility(8);
      localButton.setOnClickListener(null);
      return this;
    }
    localButton.setVisibility(0);
    localButton.setText(paramCharSequence);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramOnClickListener.onClick(paramAnonymousView);
        Snackbar.this.dispatchDismiss(1);
      }
    });
    return this;
  }
  
  @NonNull
  public Snackbar setActionTextColor(@ColorInt int paramInt)
  {
    this.mView.getActionView().setTextColor(paramInt);
    return this;
  }
  
  @NonNull
  public Snackbar setActionTextColor(ColorStateList paramColorStateList)
  {
    this.mView.getActionView().setTextColor(paramColorStateList);
    return this;
  }
  
  @NonNull
  public Snackbar setCallback(Callback paramCallback)
  {
    this.mCallback = paramCallback;
    return this;
  }
  
  @NonNull
  public Snackbar setDuration(int paramInt)
  {
    this.mDuration = paramInt;
    return this;
  }
  
  @NonNull
  public Snackbar setText(@StringRes int paramInt)
  {
    return setText(this.mContext.getText(paramInt));
  }
  
  @NonNull
  public Snackbar setText(@NonNull CharSequence paramCharSequence)
  {
    this.mView.getMessageView().setText(paramCharSequence);
    return this;
  }
  
  public void show()
  {
    SnackbarManager.getInstance().show(this.mDuration, this.mManagerCallback);
  }
  
  final void showView()
  {
    if (this.mView.getParent() == null)
    {
      ViewGroup.LayoutParams localLayoutParams = this.mView.getLayoutParams();
      if ((localLayoutParams instanceof CoordinatorLayout.LayoutParams))
      {
        Behavior localBehavior = new Behavior();
        localBehavior.setStartAlphaSwipeDistance(0.1F);
        localBehavior.setEndAlphaSwipeDistance(0.6F);
        localBehavior.setSwipeDirection(0);
        localBehavior.setListener(new SwipeDismissBehavior.OnDismissListener()
        {
          public void onDismiss(View paramAnonymousView)
          {
            Snackbar.this.dispatchDismiss(0);
          }
          
          public void onDragStateChanged(int paramAnonymousInt)
          {
            switch (paramAnonymousInt)
            {
            default: 
              return;
            case 1: 
            case 2: 
              SnackbarManager.getInstance().cancelTimeout(Snackbar.this.mManagerCallback);
              return;
            }
            SnackbarManager.getInstance().restoreTimeout(Snackbar.this.mManagerCallback);
          }
        });
        ((CoordinatorLayout.LayoutParams)localLayoutParams).setBehavior(localBehavior);
      }
      this.mTargetParent.addView(this.mView);
    }
    this.mView.setOnAttachStateChangeListener(new Snackbar.SnackbarLayout.OnAttachStateChangeListener()
    {
      public void onViewAttachedToWindow(View paramAnonymousView) {}
      
      public void onViewDetachedFromWindow(View paramAnonymousView)
      {
        if (Snackbar.this.isShownOrQueued()) {
          Snackbar.sHandler.post(new Runnable()
          {
            public void run()
            {
              Snackbar.this.onViewHidden(3);
            }
          });
        }
      }
    });
    if (ViewCompat.isLaidOut(this.mView))
    {
      animateViewIn();
      return;
    }
    this.mView.setOnLayoutChangeListener(new Snackbar.SnackbarLayout.OnLayoutChangeListener()
    {
      public void onLayoutChange(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
      {
        Snackbar.this.animateViewIn();
        Snackbar.this.mView.setOnLayoutChangeListener(null);
      }
    });
  }
  
  final class Behavior
    extends SwipeDismissBehavior<Snackbar.SnackbarLayout>
  {
    Behavior() {}
    
    public boolean canSwipeDismissView(View paramView)
    {
      return paramView instanceof Snackbar.SnackbarLayout;
    }
    
    public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, Snackbar.SnackbarLayout paramSnackbarLayout, MotionEvent paramMotionEvent)
    {
      if (paramCoordinatorLayout.isPointInChildBounds(paramSnackbarLayout, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) {
        switch (paramMotionEvent.getActionMasked())
        {
        }
      }
      for (;;)
      {
        return super.onInterceptTouchEvent(paramCoordinatorLayout, paramSnackbarLayout, paramMotionEvent);
        SnackbarManager.getInstance().cancelTimeout(Snackbar.this.mManagerCallback);
        continue;
        SnackbarManager.getInstance().restoreTimeout(Snackbar.this.mManagerCallback);
      }
    }
  }
  
  public static abstract class Callback
  {
    public static final int DISMISS_EVENT_ACTION = 1;
    public static final int DISMISS_EVENT_CONSECUTIVE = 4;
    public static final int DISMISS_EVENT_MANUAL = 3;
    public static final int DISMISS_EVENT_SWIPE = 0;
    public static final int DISMISS_EVENT_TIMEOUT = 2;
    
    public void onDismissed(Snackbar paramSnackbar, int paramInt) {}
    
    public void onShown(Snackbar paramSnackbar) {}
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface DismissEvent {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Duration {}
  
  public static class SnackbarLayout
    extends LinearLayout
  {
    private Button mActionView;
    private int mMaxInlineActionWidth;
    private int mMaxWidth;
    private TextView mMessageView;
    private OnAttachStateChangeListener mOnAttachStateChangeListener;
    private OnLayoutChangeListener mOnLayoutChangeListener;
    
    public SnackbarLayout(Context paramContext)
    {
      this(paramContext, null);
    }
    
    public SnackbarLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SnackbarLayout);
      this.mMaxWidth = paramAttributeSet.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
      this.mMaxInlineActionWidth = paramAttributeSet.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
      if (paramAttributeSet.hasValue(R.styleable.SnackbarLayout_elevation)) {
        ViewCompat.setElevation(this, paramAttributeSet.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
      }
      paramAttributeSet.recycle();
      setClickable(true);
      LayoutInflater.from(paramContext).inflate(R.layout.design_layout_snackbar_include, this);
      ViewCompat.setAccessibilityLiveRegion(this, 1);
    }
    
    private static void updateTopBottomPadding(View paramView, int paramInt1, int paramInt2)
    {
      if (ViewCompat.isPaddingRelative(paramView))
      {
        ViewCompat.setPaddingRelative(paramView, ViewCompat.getPaddingStart(paramView), paramInt1, ViewCompat.getPaddingEnd(paramView), paramInt2);
        return;
      }
      paramView.setPadding(paramView.getPaddingLeft(), paramInt1, paramView.getPaddingRight(), paramInt2);
    }
    
    private boolean updateViewsWithinLayout(int paramInt1, int paramInt2, int paramInt3)
    {
      boolean bool = false;
      if (paramInt1 != getOrientation())
      {
        setOrientation(paramInt1);
        bool = true;
      }
      if ((this.mMessageView.getPaddingTop() != paramInt2) || (this.mMessageView.getPaddingBottom() != paramInt3))
      {
        updateTopBottomPadding(this.mMessageView, paramInt2, paramInt3);
        bool = true;
      }
      return bool;
    }
    
    void animateChildrenIn(int paramInt1, int paramInt2)
    {
      ViewCompat.setAlpha(this.mMessageView, 0.0F);
      ViewCompat.animate(this.mMessageView).alpha(1.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
      if (this.mActionView.getVisibility() == 0)
      {
        ViewCompat.setAlpha(this.mActionView, 0.0F);
        ViewCompat.animate(this.mActionView).alpha(1.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
      }
    }
    
    void animateChildrenOut(int paramInt1, int paramInt2)
    {
      ViewCompat.setAlpha(this.mMessageView, 1.0F);
      ViewCompat.animate(this.mMessageView).alpha(0.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
      if (this.mActionView.getVisibility() == 0)
      {
        ViewCompat.setAlpha(this.mActionView, 1.0F);
        ViewCompat.animate(this.mActionView).alpha(0.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
      }
    }
    
    Button getActionView()
    {
      return this.mActionView;
    }
    
    TextView getMessageView()
    {
      return this.mMessageView;
    }
    
    protected void onAttachedToWindow()
    {
      super.onAttachedToWindow();
      if (this.mOnAttachStateChangeListener != null) {
        this.mOnAttachStateChangeListener.onViewAttachedToWindow(this);
      }
    }
    
    protected void onDetachedFromWindow()
    {
      super.onDetachedFromWindow();
      if (this.mOnAttachStateChangeListener != null) {
        this.mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
      }
    }
    
    protected void onFinishInflate()
    {
      super.onFinishInflate();
      this.mMessageView = ((TextView)findViewById(R.id.snackbar_text));
      this.mActionView = ((Button)findViewById(R.id.snackbar_action));
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      if ((paramBoolean) && (this.mOnLayoutChangeListener != null)) {
        this.mOnLayoutChangeListener.onLayoutChange(this, paramInt1, paramInt2, paramInt3, paramInt4);
      }
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, paramInt2);
      int i = paramInt1;
      if (this.mMaxWidth > 0)
      {
        i = paramInt1;
        if (getMeasuredWidth() > this.mMaxWidth)
        {
          i = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
          super.onMeasure(i, paramInt2);
        }
      }
      int j = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
      int k = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
      if (this.mMessageView.getLayout().getLineCount() > 1) {}
      int m;
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        m = 0;
        if ((paramInt1 == 0) || (this.mMaxInlineActionWidth <= 0) || (this.mActionView.getMeasuredWidth() <= this.mMaxInlineActionWidth)) {
          break;
        }
        paramInt1 = m;
        if (updateViewsWithinLayout(1, j, j - k)) {
          paramInt1 = 1;
        }
        if (paramInt1 != 0) {
          super.onMeasure(i, paramInt2);
        }
        return;
      }
      if (paramInt1 != 0) {}
      for (;;)
      {
        paramInt1 = m;
        if (!updateViewsWithinLayout(0, j, j)) {
          break;
        }
        paramInt1 = 1;
        break;
        j = k;
      }
    }
    
    void setOnAttachStateChangeListener(OnAttachStateChangeListener paramOnAttachStateChangeListener)
    {
      this.mOnAttachStateChangeListener = paramOnAttachStateChangeListener;
    }
    
    void setOnLayoutChangeListener(OnLayoutChangeListener paramOnLayoutChangeListener)
    {
      this.mOnLayoutChangeListener = paramOnLayoutChangeListener;
    }
    
    static abstract interface OnAttachStateChangeListener
    {
      public abstract void onViewAttachedToWindow(View paramView);
      
      public abstract void onViewDetachedFromWindow(View paramView);
    }
    
    static abstract interface OnLayoutChangeListener
    {
      public abstract void onLayoutChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/Snackbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */