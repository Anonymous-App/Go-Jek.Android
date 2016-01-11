package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.R.drawable;
import android.support.design.R.string;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.Space;
import android.support.v7.widget.TintManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.List;

public class TextInputLayout
  extends LinearLayout
{
  private static final int ANIMATION_DURATION = 200;
  private static final int INVALID_MAX_LENGTH = -1;
  private ValueAnimatorCompat mAnimator;
  private final CollapsingTextHelper mCollapsingTextHelper = new CollapsingTextHelper(this);
  private boolean mCounterEnabled;
  private int mCounterMaxLength;
  private int mCounterOverflowTextAppearance;
  private boolean mCounterOverflowed;
  private int mCounterTextAppearance;
  private TextView mCounterView;
  private ColorStateList mDefaultTextColor;
  private EditText mEditText;
  private boolean mErrorEnabled;
  private boolean mErrorShown;
  private int mErrorTextAppearance;
  private TextView mErrorView;
  private ColorStateList mFocusedTextColor;
  private CharSequence mHint;
  private boolean mHintAnimationEnabled;
  private LinearLayout mIndicatorArea;
  private Paint mTmpPaint;
  
  public TextInputLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    ThemeUtils.checkAppCompatTheme(paramContext);
    setOrientation(1);
    setWillNotDraw(false);
    setAddStatesFromChildren(true);
    this.mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    this.mCollapsingTextHelper.setPositionInterpolator(new AccelerateInterpolator());
    this.mCollapsingTextHelper.setCollapsedTextGravity(8388659);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.TextInputLayout, paramInt, R.style.Widget_Design_TextInputLayout);
    setHint(paramContext.getText(R.styleable.TextInputLayout_android_hint));
    this.mHintAnimationEnabled = paramContext.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
    if (paramContext.hasValue(R.styleable.TextInputLayout_android_textColorHint))
    {
      paramAttributeSet = paramContext.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
      this.mFocusedTextColor = paramAttributeSet;
      this.mDefaultTextColor = paramAttributeSet;
    }
    if (paramContext.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
      setHintTextAppearance(paramContext.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
    }
    this.mErrorTextAppearance = paramContext.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
    boolean bool1 = paramContext.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
    boolean bool2 = paramContext.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
    setCounterMaxLength(paramContext.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
    this.mCounterTextAppearance = paramContext.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
    this.mCounterOverflowTextAppearance = paramContext.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
    paramContext.recycle();
    setErrorEnabled(bool1);
    setCounterEnabled(bool2);
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.setImportantForAccessibility(this, 1);
    }
    ViewCompat.setAccessibilityDelegate(this, new TextInputAccessibilityDelegate(null));
  }
  
  private void addIndicator(TextView paramTextView, int paramInt)
  {
    if (this.mIndicatorArea == null)
    {
      this.mIndicatorArea = new LinearLayout(getContext());
      this.mIndicatorArea.setOrientation(0);
      addView(this.mIndicatorArea, -1, -2);
      Space localSpace = new Space(getContext());
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, 0, 1.0F);
      this.mIndicatorArea.addView(localSpace, localLayoutParams);
      if (this.mEditText != null) {
        adjustIndicatorPadding();
      }
    }
    this.mIndicatorArea.setVisibility(0);
    this.mIndicatorArea.addView(paramTextView, paramInt);
  }
  
  private void adjustIndicatorPadding()
  {
    ViewCompat.setPaddingRelative(this.mIndicatorArea, ViewCompat.getPaddingStart(this.mEditText), 0, ViewCompat.getPaddingEnd(this.mEditText), this.mEditText.getPaddingBottom());
  }
  
  private void animateToExpansionFraction(float paramFloat)
  {
    if (this.mCollapsingTextHelper.getExpansionFraction() == paramFloat) {
      return;
    }
    if (this.mAnimator == null)
    {
      this.mAnimator = ViewUtils.createAnimator();
      this.mAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
      this.mAnimator.setDuration(200);
      this.mAnimator.setUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
        {
          TextInputLayout.this.mCollapsingTextHelper.setExpansionFraction(paramAnonymousValueAnimatorCompat.getAnimatedFloatValue());
        }
      });
    }
    this.mAnimator.setFloatValues(this.mCollapsingTextHelper.getExpansionFraction(), paramFloat);
    this.mAnimator.start();
  }
  
  private static boolean arrayContains(int[] paramArrayOfInt, int paramInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void collapseHint(boolean paramBoolean)
  {
    if ((this.mAnimator != null) && (this.mAnimator.isRunning())) {
      this.mAnimator.cancel();
    }
    if ((paramBoolean) && (this.mHintAnimationEnabled))
    {
      animateToExpansionFraction(1.0F);
      return;
    }
    this.mCollapsingTextHelper.setExpansionFraction(1.0F);
  }
  
  private void expandHint(boolean paramBoolean)
  {
    if ((this.mAnimator != null) && (this.mAnimator.isRunning())) {
      this.mAnimator.cancel();
    }
    if ((paramBoolean) && (this.mHintAnimationEnabled))
    {
      animateToExpansionFraction(0.0F);
      return;
    }
    this.mCollapsingTextHelper.setExpansionFraction(0.0F);
  }
  
  private int getThemeAttrColor(int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (getContext().getTheme().resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.data;
    }
    return -65281;
  }
  
  private void removeIndicator(TextView paramTextView)
  {
    if (this.mIndicatorArea != null)
    {
      this.mIndicatorArea.removeView(paramTextView);
      if (this.mIndicatorArea.getChildCount() == 0) {
        this.mIndicatorArea.setVisibility(8);
      }
    }
  }
  
  private void setEditText(EditText paramEditText)
  {
    if (this.mEditText != null) {
      throw new IllegalArgumentException("We already have an EditText, can only have one");
    }
    this.mEditText = paramEditText;
    this.mCollapsingTextHelper.setTypefaces(this.mEditText.getTypeface());
    this.mCollapsingTextHelper.setExpandedTextSize(this.mEditText.getTextSize());
    this.mCollapsingTextHelper.setExpandedTextGravity(this.mEditText.getGravity());
    this.mEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        TextInputLayout.this.updateLabelVisibility(true);
        if (TextInputLayout.this.mCounterEnabled) {
          TextInputLayout.this.updateCounter(paramAnonymousEditable.length());
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    if (this.mDefaultTextColor == null) {
      this.mDefaultTextColor = this.mEditText.getHintTextColors();
    }
    if (TextUtils.isEmpty(this.mHint))
    {
      setHint(this.mEditText.getHint());
      this.mEditText.setHint(null);
    }
    if (this.mCounterView != null) {
      updateCounter(this.mEditText.getText().length());
    }
    if (this.mIndicatorArea != null) {
      adjustIndicatorPadding();
    }
    updateLabelVisibility(false);
  }
  
  private void updateCounter(int paramInt)
  {
    boolean bool2 = this.mCounterOverflowed;
    if (this.mCounterMaxLength == -1)
    {
      this.mCounterView.setText(String.valueOf(paramInt));
      this.mCounterOverflowed = false;
      if ((this.mEditText != null) && (bool2 != this.mCounterOverflowed))
      {
        updateLabelVisibility(false);
        updateEditTextBackground();
      }
      return;
    }
    boolean bool1;
    label66:
    TextView localTextView;
    Context localContext;
    if (paramInt > this.mCounterMaxLength)
    {
      bool1 = true;
      this.mCounterOverflowed = bool1;
      if (bool2 != this.mCounterOverflowed)
      {
        localTextView = this.mCounterView;
        localContext = getContext();
        if (!this.mCounterOverflowed) {
          break label158;
        }
      }
    }
    label158:
    for (int i = this.mCounterOverflowTextAppearance;; i = this.mCounterTextAppearance)
    {
      localTextView.setTextAppearance(localContext, i);
      this.mCounterView.setText(getContext().getString(R.string.character_counter_pattern, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(this.mCounterMaxLength) }));
      break;
      bool1 = false;
      break label66;
    }
  }
  
  private void updateEditTextBackground()
  {
    if ((this.mErrorShown) && (this.mErrorView != null))
    {
      ViewCompat.setBackgroundTintList(this.mEditText, ColorStateList.valueOf(this.mErrorView.getCurrentTextColor()));
      return;
    }
    if ((this.mCounterOverflowed) && (this.mCounterView != null))
    {
      ViewCompat.setBackgroundTintList(this.mEditText, ColorStateList.valueOf(this.mCounterView.getCurrentTextColor()));
      return;
    }
    TintManager localTintManager = TintManager.get(getContext());
    ViewCompat.setBackgroundTintList(this.mEditText, localTintManager.getTintList(R.drawable.abc_edit_text_material));
  }
  
  private LinearLayout.LayoutParams updateEditTextMargin(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LinearLayout.LayoutParams)) {}
    for (paramLayoutParams = (LinearLayout.LayoutParams)paramLayoutParams;; paramLayoutParams = new LinearLayout.LayoutParams(paramLayoutParams))
    {
      if (this.mTmpPaint == null) {
        this.mTmpPaint = new Paint();
      }
      this.mTmpPaint.setTypeface(this.mCollapsingTextHelper.getCollapsedTypeface());
      this.mTmpPaint.setTextSize(this.mCollapsingTextHelper.getCollapsedTextSize());
      paramLayoutParams.topMargin = ((int)-this.mTmpPaint.ascent());
      return paramLayoutParams;
    }
  }
  
  private void updateLabelVisibility(boolean paramBoolean)
  {
    int i;
    boolean bool;
    int j;
    if ((this.mEditText != null) && (!TextUtils.isEmpty(this.mEditText.getText())))
    {
      i = 1;
      bool = arrayContains(getDrawableState(), 16842908);
      if (TextUtils.isEmpty(getError())) {
        break label119;
      }
      j = 1;
      label46:
      if (this.mDefaultTextColor != null) {
        this.mCollapsingTextHelper.setExpandedTextColor(this.mDefaultTextColor.getDefaultColor());
      }
      if ((!this.mCounterOverflowed) || (this.mCounterView == null)) {
        break label124;
      }
      this.mCollapsingTextHelper.setCollapsedTextColor(this.mCounterView.getCurrentTextColor());
    }
    for (;;)
    {
      if ((i == 0) && (!bool) && (j == 0)) {
        break label205;
      }
      collapseHint(paramBoolean);
      return;
      i = 0;
      break;
      label119:
      j = 0;
      break label46;
      label124:
      if ((j != 0) && (this.mErrorView != null)) {
        this.mCollapsingTextHelper.setCollapsedTextColor(this.mErrorView.getCurrentTextColor());
      } else if ((bool) && (this.mFocusedTextColor != null)) {
        this.mCollapsingTextHelper.setCollapsedTextColor(this.mFocusedTextColor.getDefaultColor());
      } else if (this.mDefaultTextColor != null) {
        this.mCollapsingTextHelper.setCollapsedTextColor(this.mDefaultTextColor.getDefaultColor());
      }
    }
    label205:
    expandHint(paramBoolean);
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramView instanceof EditText))
    {
      setEditText((EditText)paramView);
      super.addView(paramView, 0, updateEditTextMargin(paramLayoutParams));
      return;
    }
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    this.mCollapsingTextHelper.draw(paramCanvas);
  }
  
  public int getCounterMaxLength()
  {
    return this.mCounterMaxLength;
  }
  
  @Nullable
  public EditText getEditText()
  {
    return this.mEditText;
  }
  
  @Nullable
  public CharSequence getError()
  {
    if ((this.mErrorEnabled) && (this.mErrorView != null) && (this.mErrorView.getVisibility() == 0)) {
      return this.mErrorView.getText();
    }
    return null;
  }
  
  @Nullable
  public CharSequence getHint()
  {
    return this.mHint;
  }
  
  @NonNull
  public Typeface getTypeface()
  {
    return this.mCollapsingTextHelper.getCollapsedTypeface();
  }
  
  public boolean isErrorEnabled()
  {
    return this.mErrorEnabled;
  }
  
  public boolean isHintAnimationEnabled()
  {
    return this.mHintAnimationEnabled;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mEditText != null)
    {
      paramInt1 = this.mEditText.getLeft() + this.mEditText.getCompoundPaddingLeft();
      paramInt3 = this.mEditText.getRight() - this.mEditText.getCompoundPaddingRight();
      this.mCollapsingTextHelper.setExpandedBounds(paramInt1, this.mEditText.getTop() + this.mEditText.getCompoundPaddingTop(), paramInt3, this.mEditText.getBottom() - this.mEditText.getCompoundPaddingBottom());
      this.mCollapsingTextHelper.setCollapsedBounds(paramInt1, getPaddingTop(), paramInt3, paramInt4 - paramInt2 - getPaddingBottom());
      this.mCollapsingTextHelper.recalculate();
    }
  }
  
  public void refreshDrawableState()
  {
    super.refreshDrawableState();
    updateLabelVisibility(ViewCompat.isLaidOut(this));
  }
  
  public void setCounterEnabled(boolean paramBoolean)
  {
    if (this.mCounterEnabled != paramBoolean)
    {
      if (!paramBoolean) {
        break label104;
      }
      this.mCounterView = new TextView(getContext());
      this.mCounterView.setMaxLines(1);
      this.mCounterView.setTextAppearance(getContext(), this.mCounterTextAppearance);
      ViewCompat.setAccessibilityLiveRegion(this.mCounterView, 1);
      addIndicator(this.mCounterView, -1);
      if (this.mEditText != null) {
        break label85;
      }
      updateCounter(0);
    }
    for (;;)
    {
      this.mCounterEnabled = paramBoolean;
      return;
      label85:
      updateCounter(this.mEditText.getText().length());
      continue;
      label104:
      removeIndicator(this.mCounterView);
      this.mCounterView = null;
    }
  }
  
  public void setCounterMaxLength(int paramInt)
  {
    if (this.mCounterMaxLength != paramInt)
    {
      if (paramInt <= 0) {
        break label39;
      }
      this.mCounterMaxLength = paramInt;
      if (this.mCounterEnabled) {
        if (this.mEditText != null) {
          break label47;
        }
      }
    }
    label39:
    label47:
    for (paramInt = 0;; paramInt = this.mEditText.getText().length())
    {
      updateCounter(paramInt);
      return;
      this.mCounterMaxLength = -1;
      break;
    }
  }
  
  public void setError(@Nullable CharSequence paramCharSequence)
  {
    if (!this.mErrorEnabled) {
      if (!TextUtils.isEmpty(paramCharSequence)) {}
    }
    do
    {
      return;
      setErrorEnabled(true);
      if (!TextUtils.isEmpty(paramCharSequence))
      {
        ViewCompat.setAlpha(this.mErrorView, 0.0F);
        this.mErrorView.setText(paramCharSequence);
        ViewCompat.animate(this.mErrorView).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter()
        {
          public void onAnimationStart(View paramAnonymousView)
          {
            paramAnonymousView.setVisibility(0);
          }
        }).start();
        this.mErrorShown = true;
        updateEditTextBackground();
        updateLabelVisibility(true);
        return;
      }
    } while (this.mErrorView.getVisibility() != 0);
    ViewCompat.animate(this.mErrorView).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener(new ViewPropertyAnimatorListenerAdapter()
    {
      public void onAnimationEnd(View paramAnonymousView)
      {
        paramAnonymousView.setVisibility(4);
        TextInputLayout.this.updateLabelVisibility(true);
      }
    }).start();
    this.mErrorShown = false;
    updateEditTextBackground();
  }
  
  public void setErrorEnabled(boolean paramBoolean)
  {
    if (this.mErrorEnabled != paramBoolean)
    {
      if (this.mErrorView != null) {
        ViewCompat.animate(this.mErrorView).cancel();
      }
      if (!paramBoolean) {
        break label90;
      }
      this.mErrorView = new TextView(getContext());
      this.mErrorView.setTextAppearance(getContext(), this.mErrorTextAppearance);
      this.mErrorView.setVisibility(4);
      ViewCompat.setAccessibilityLiveRegion(this.mErrorView, 1);
      addIndicator(this.mErrorView, 0);
    }
    for (;;)
    {
      this.mErrorEnabled = paramBoolean;
      return;
      label90:
      this.mErrorShown = false;
      updateEditTextBackground();
      removeIndicator(this.mErrorView);
      this.mErrorView = null;
    }
  }
  
  public void setHint(@Nullable CharSequence paramCharSequence)
  {
    this.mHint = paramCharSequence;
    this.mCollapsingTextHelper.setText(paramCharSequence);
    sendAccessibilityEvent(2048);
  }
  
  public void setHintAnimationEnabled(boolean paramBoolean)
  {
    this.mHintAnimationEnabled = paramBoolean;
  }
  
  public void setHintTextAppearance(@StyleRes int paramInt)
  {
    this.mCollapsingTextHelper.setCollapsedTextAppearance(paramInt);
    this.mFocusedTextColor = ColorStateList.valueOf(this.mCollapsingTextHelper.getCollapsedTextColor());
    if (this.mEditText != null)
    {
      updateLabelVisibility(false);
      LinearLayout.LayoutParams localLayoutParams = updateEditTextMargin(this.mEditText.getLayoutParams());
      this.mEditText.setLayoutParams(localLayoutParams);
      this.mEditText.requestLayout();
    }
  }
  
  public void setTypeface(@Nullable Typeface paramTypeface)
  {
    this.mCollapsingTextHelper.setTypefaces(paramTypeface);
  }
  
  private class TextInputAccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    private TextInputAccessibilityDelegate() {}
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(TextInputLayout.class.getSimpleName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
      paramView = TextInputLayout.this.mCollapsingTextHelper.getText();
      if (!TextUtils.isEmpty(paramView)) {
        paramAccessibilityNodeInfoCompat.setText(paramView);
      }
      if (TextInputLayout.this.mEditText != null) {
        paramAccessibilityNodeInfoCompat.setLabelFor(TextInputLayout.this.mEditText);
      }
      if (TextInputLayout.this.mErrorView != null) {}
      for (paramView = TextInputLayout.this.mErrorView.getText();; paramView = null)
      {
        if (!TextUtils.isEmpty(paramView))
        {
          paramAccessibilityNodeInfoCompat.setContentInvalid(true);
          paramAccessibilityNodeInfoCompat.setError(paramView);
        }
        return;
      }
    }
    
    public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramView = TextInputLayout.this.mCollapsingTextHelper.getText();
      if (!TextUtils.isEmpty(paramView)) {
        paramAccessibilityEvent.getText().add(paramView);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/TextInputLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */