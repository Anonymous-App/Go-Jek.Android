package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar
  extends ViewGroup
{
  private static final String TAG = "Toolbar";
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  private int mButtonGravity;
  private ImageButton mCollapseButtonView;
  private CharSequence mCollapseDescription;
  private Drawable mCollapseIcon;
  private boolean mCollapsible;
  private final RtlSpacingHelper mContentInsets = new RtlSpacingHelper();
  private boolean mEatingHover;
  private boolean mEatingTouch;
  View mExpandedActionView;
  private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
  private int mGravity = 8388627;
  private final ArrayList<View> mHiddenViews = new ArrayList();
  private ImageView mLogoView;
  private int mMaxButtonHeight;
  private MenuBuilder.Callback mMenuBuilderCallback;
  private ActionMenuView mMenuView;
  private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener = new ActionMenuView.OnMenuItemClickListener()
  {
    public boolean onMenuItemClick(MenuItem paramAnonymousMenuItem)
    {
      if (Toolbar.this.mOnMenuItemClickListener != null) {
        return Toolbar.this.mOnMenuItemClickListener.onMenuItemClick(paramAnonymousMenuItem);
      }
      return false;
    }
  };
  private ImageButton mNavButtonView;
  private OnMenuItemClickListener mOnMenuItemClickListener;
  private ActionMenuPresenter mOuterActionMenuPresenter;
  private Context mPopupContext;
  private int mPopupTheme;
  private final Runnable mShowOverflowMenuRunnable = new Runnable()
  {
    public void run()
    {
      Toolbar.this.showOverflowMenu();
    }
  };
  private CharSequence mSubtitleText;
  private int mSubtitleTextAppearance;
  private int mSubtitleTextColor;
  private TextView mSubtitleTextView;
  private final int[] mTempMargins = new int[2];
  private final ArrayList<View> mTempViews = new ArrayList();
  private final TintManager mTintManager;
  private int mTitleMarginBottom;
  private int mTitleMarginEnd;
  private int mTitleMarginStart;
  private int mTitleMarginTop;
  private CharSequence mTitleText;
  private int mTitleTextAppearance;
  private int mTitleTextColor;
  private TextView mTitleTextView;
  private ToolbarWidgetWrapper mWrapper;
  
  public Toolbar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Toolbar(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.toolbarStyle);
  }
  
  public Toolbar(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, R.styleable.Toolbar, paramInt, 0);
    this.mTitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
    this.mSubtitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
    this.mGravity = paramContext.getInteger(R.styleable.Toolbar_android_gravity, this.mGravity);
    this.mButtonGravity = 48;
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, 0);
    this.mTitleMarginBottom = paramInt;
    this.mTitleMarginTop = paramInt;
    this.mTitleMarginEnd = paramInt;
    this.mTitleMarginStart = paramInt;
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
    if (paramInt >= 0) {
      this.mTitleMarginStart = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1);
    if (paramInt >= 0) {
      this.mTitleMarginEnd = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1);
    if (paramInt >= 0) {
      this.mTitleMarginTop = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1);
    if (paramInt >= 0) {
      this.mTitleMarginBottom = paramInt;
    }
    this.mMaxButtonHeight = paramContext.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
    int i = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
    int j = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
    int k = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
    this.mContentInsets.setAbsolute(j, k);
    if ((paramInt != Integer.MIN_VALUE) || (i != Integer.MIN_VALUE)) {
      this.mContentInsets.setRelative(paramInt, i);
    }
    this.mCollapseIcon = paramContext.getDrawable(R.styleable.Toolbar_collapseIcon);
    this.mCollapseDescription = paramContext.getText(R.styleable.Toolbar_collapseContentDescription);
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_title);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setTitle(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_subtitle);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setSubtitle(paramAttributeSet);
    }
    this.mPopupContext = getContext();
    setPopupTheme(paramContext.getResourceId(R.styleable.Toolbar_popupTheme, 0));
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_navigationIcon);
    if (paramAttributeSet != null) {
      setNavigationIcon(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_navigationContentDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setNavigationContentDescription(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_logo);
    if (paramAttributeSet != null) {
      setLogo(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_logoDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setLogoDescription(paramAttributeSet);
    }
    if (paramContext.hasValue(R.styleable.Toolbar_titleTextColor)) {
      setTitleTextColor(paramContext.getColor(R.styleable.Toolbar_titleTextColor, -1));
    }
    if (paramContext.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
      setSubtitleTextColor(paramContext.getColor(R.styleable.Toolbar_subtitleTextColor, -1));
    }
    paramContext.recycle();
    this.mTintManager = paramContext.getTintManager();
  }
  
  private void addCustomViewsWithGravity(List<View> paramList, int paramInt)
  {
    int i = 1;
    if (ViewCompat.getLayoutDirection(this) == 1) {}
    int k;
    int j;
    View localView;
    LayoutParams localLayoutParams;
    for (;;)
    {
      k = getChildCount();
      j = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
      paramList.clear();
      if (i == 0) {
        break;
      }
      paramInt = k - 1;
      while (paramInt >= 0)
      {
        localView = getChildAt(paramInt);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if ((localLayoutParams.mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(localLayoutParams.gravity) == j)) {
          paramList.add(localView);
        }
        paramInt -= 1;
      }
      i = 0;
    }
    paramInt = 0;
    while (paramInt < k)
    {
      localView = getChildAt(paramInt);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if ((localLayoutParams.mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(localLayoutParams.gravity) == j)) {
        paramList.add(localView);
      }
      paramInt += 1;
    }
  }
  
  private void addSystemView(View paramView, boolean paramBoolean)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null) {
      localObject = generateDefaultLayoutParams();
    }
    for (;;)
    {
      ((LayoutParams)localObject).mViewType = 1;
      if ((!paramBoolean) || (this.mExpandedActionView == null)) {
        break;
      }
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.mHiddenViews.add(paramView);
      return;
      if (!checkLayoutParams((ViewGroup.LayoutParams)localObject)) {
        localObject = generateLayoutParams((ViewGroup.LayoutParams)localObject);
      } else {
        localObject = (LayoutParams)localObject;
      }
    }
    addView(paramView, (ViewGroup.LayoutParams)localObject);
  }
  
  private void ensureCollapseButtonView()
  {
    if (this.mCollapseButtonView == null)
    {
      this.mCollapseButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
      this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      localLayoutParams.gravity = (0x800003 | this.mButtonGravity & 0x70);
      localLayoutParams.mViewType = 2;
      this.mCollapseButtonView.setLayoutParams(localLayoutParams);
      this.mCollapseButtonView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Toolbar.this.collapseActionView();
        }
      });
    }
  }
  
  private void ensureLogoView()
  {
    if (this.mLogoView == null) {
      this.mLogoView = new ImageView(getContext());
    }
  }
  
  private void ensureMenu()
  {
    ensureMenuView();
    if (this.mMenuView.peekMenu() == null)
    {
      MenuBuilder localMenuBuilder = (MenuBuilder)this.mMenuView.getMenu();
      if (this.mExpandedMenuPresenter == null) {
        this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
      }
      this.mMenuView.setExpandedActionViewsExclusive(true);
      localMenuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
    }
  }
  
  private void ensureMenuView()
  {
    if (this.mMenuView == null)
    {
      this.mMenuView = new ActionMenuView(getContext());
      this.mMenuView.setPopupTheme(this.mPopupTheme);
      this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
      this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      localLayoutParams.gravity = (0x800005 | this.mButtonGravity & 0x70);
      this.mMenuView.setLayoutParams(localLayoutParams);
      addSystemView(this.mMenuView, false);
    }
  }
  
  private void ensureNavButtonView()
  {
    if (this.mNavButtonView == null)
    {
      this.mNavButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      LayoutParams localLayoutParams = generateDefaultLayoutParams();
      localLayoutParams.gravity = (0x800003 | this.mButtonGravity & 0x70);
      this.mNavButtonView.setLayoutParams(localLayoutParams);
    }
  }
  
  private int getChildHorizontalGravity(int paramInt)
  {
    int j = ViewCompat.getLayoutDirection(this);
    int i = GravityCompat.getAbsoluteGravity(paramInt, j) & 0x7;
    paramInt = i;
    switch (i)
    {
    case 2: 
    case 4: 
    default: 
      if (j != 1) {
        break;
      }
    }
    for (paramInt = 5;; paramInt = 3) {
      return paramInt;
    }
  }
  
  private int getChildTop(View paramView, int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int j = paramView.getMeasuredHeight();
    int k;
    int m;
    int i;
    if (paramInt > 0)
    {
      paramInt = (j - paramInt) / 2;
      switch (getChildVerticalGravity(localLayoutParams.gravity))
      {
      default: 
        k = getPaddingTop();
        paramInt = getPaddingBottom();
        m = getHeight();
        i = (m - k - paramInt - j) / 2;
        if (i < localLayoutParams.topMargin) {
          paramInt = localLayoutParams.topMargin;
        }
        break;
      }
    }
    for (;;)
    {
      return k + paramInt;
      paramInt = 0;
      break;
      return getPaddingTop() - paramInt;
      return getHeight() - getPaddingBottom() - j - localLayoutParams.bottomMargin - paramInt;
      j = m - paramInt - j - i - k;
      paramInt = i;
      if (j < localLayoutParams.bottomMargin) {
        paramInt = Math.max(0, i - (localLayoutParams.bottomMargin - j));
      }
    }
  }
  
  private int getChildVerticalGravity(int paramInt)
  {
    int i = paramInt & 0x70;
    paramInt = i;
    switch (i)
    {
    default: 
      paramInt = this.mGravity & 0x70;
    }
    return paramInt;
  }
  
  private int getHorizontalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    return MarginLayoutParamsCompat.getMarginStart(paramView) + MarginLayoutParamsCompat.getMarginEnd(paramView);
  }
  
  private MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(getContext());
  }
  
  private int getVerticalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    return paramView.topMargin + paramView.bottomMargin;
  }
  
  private int getViewListMeasuredWidth(List<View> paramList, int[] paramArrayOfInt)
  {
    int m = paramArrayOfInt[0];
    int k = paramArrayOfInt[1];
    int j = 0;
    int n = paramList.size();
    int i = 0;
    while (i < n)
    {
      paramArrayOfInt = (View)paramList.get(i);
      LayoutParams localLayoutParams = (LayoutParams)paramArrayOfInt.getLayoutParams();
      m = localLayoutParams.leftMargin - m;
      k = localLayoutParams.rightMargin - k;
      int i1 = Math.max(0, m);
      int i2 = Math.max(0, k);
      m = Math.max(0, -m);
      k = Math.max(0, -k);
      j += paramArrayOfInt.getMeasuredWidth() + i1 + i2;
      i += 1;
    }
    return j;
  }
  
  private boolean isChildOrHidden(View paramView)
  {
    return (paramView.getParent() == this) || (this.mHiddenViews.contains(paramView));
  }
  
  private static boolean isCustomView(View paramView)
  {
    return ((LayoutParams)paramView.getLayoutParams()).mViewType == 0;
  }
  
  private int layoutChildLeft(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = localLayoutParams.leftMargin - paramArrayOfInt[0];
    paramInt1 += Math.max(0, i);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1, paramInt2, paramInt1 + i, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 + (localLayoutParams.rightMargin + i);
  }
  
  private int layoutChildRight(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = localLayoutParams.rightMargin - paramArrayOfInt[1];
    paramInt1 -= Math.max(0, i);
    paramArrayOfInt[1] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1 - i, paramInt2, paramInt1, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 - (localLayoutParams.leftMargin + i);
  }
  
  private int measureChildCollapseMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = localMarginLayoutParams.leftMargin - paramArrayOfInt[0];
    int j = localMarginLayoutParams.rightMargin - paramArrayOfInt[1];
    int k = Math.max(0, i) + Math.max(0, j);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramArrayOfInt[1] = Math.max(0, -j);
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + k + paramInt2, localMarginLayoutParams.width), getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin + paramInt4, localMarginLayoutParams.height));
    return paramView.getMeasuredWidth() + k;
  }
  
  private void measureChildConstrained(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + localMarginLayoutParams.leftMargin + localMarginLayoutParams.rightMargin + paramInt2, localMarginLayoutParams.width);
    paramInt2 = getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin + paramInt4, localMarginLayoutParams.height);
    paramInt3 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = paramInt2;
    if (paramInt3 != 1073741824)
    {
      paramInt1 = paramInt2;
      if (paramInt5 >= 0) {
        if (paramInt3 == 0) {
          break label132;
        }
      }
    }
    label132:
    for (paramInt1 = Math.min(View.MeasureSpec.getSize(paramInt2), paramInt5);; paramInt1 = paramInt5)
    {
      paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      paramView.measure(i, paramInt1);
      return;
    }
  }
  
  private void postShowOverflowMenu()
  {
    removeCallbacks(this.mShowOverflowMenuRunnable);
    post(this.mShowOverflowMenuRunnable);
  }
  
  private boolean shouldCollapse()
  {
    if (!this.mCollapsible) {
      return false;
    }
    int j = getChildCount();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label56;
      }
      View localView = getChildAt(i);
      if ((shouldLayout(localView)) && (localView.getMeasuredWidth() > 0) && (localView.getMeasuredHeight() > 0)) {
        break;
      }
      i += 1;
    }
    label56:
    return true;
  }
  
  private boolean shouldLayout(View paramView)
  {
    return (paramView != null) && (paramView.getParent() == this) && (paramView.getVisibility() != 8);
  }
  
  void addChildrenForExpandedActionView()
  {
    int i = this.mHiddenViews.size() - 1;
    while (i >= 0)
    {
      addView((View)this.mHiddenViews.get(i));
      i -= 1;
    }
    this.mHiddenViews.clear();
  }
  
  public boolean canShowOverflowMenu()
  {
    return (getVisibility() == 0) && (this.mMenuView != null) && (this.mMenuView.isOverflowReserved());
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (super.checkLayoutParams(paramLayoutParams)) && ((paramLayoutParams instanceof LayoutParams));
  }
  
  public void collapseActionView()
  {
    if (this.mExpandedMenuPresenter == null) {}
    for (MenuItemImpl localMenuItemImpl = null;; localMenuItemImpl = this.mExpandedMenuPresenter.mCurrentExpandedItem)
    {
      if (localMenuItemImpl != null) {
        localMenuItemImpl.collapseActionView();
      }
      return;
    }
  }
  
  public void dismissPopupMenus()
  {
    if (this.mMenuView != null) {
      this.mMenuView.dismissPopupMenus();
    }
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
    if ((paramLayoutParams instanceof ActionBar.LayoutParams)) {
      return new LayoutParams((ActionBar.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getContentInsetEnd()
  {
    return this.mContentInsets.getEnd();
  }
  
  public int getContentInsetLeft()
  {
    return this.mContentInsets.getLeft();
  }
  
  public int getContentInsetRight()
  {
    return this.mContentInsets.getRight();
  }
  
  public int getContentInsetStart()
  {
    return this.mContentInsets.getStart();
  }
  
  public Drawable getLogo()
  {
    if (this.mLogoView != null) {
      return this.mLogoView.getDrawable();
    }
    return null;
  }
  
  public CharSequence getLogoDescription()
  {
    if (this.mLogoView != null) {
      return this.mLogoView.getContentDescription();
    }
    return null;
  }
  
  public Menu getMenu()
  {
    ensureMenu();
    return this.mMenuView.getMenu();
  }
  
  @Nullable
  public CharSequence getNavigationContentDescription()
  {
    if (this.mNavButtonView != null) {
      return this.mNavButtonView.getContentDescription();
    }
    return null;
  }
  
  @Nullable
  public Drawable getNavigationIcon()
  {
    if (this.mNavButtonView != null) {
      return this.mNavButtonView.getDrawable();
    }
    return null;
  }
  
  @Nullable
  public Drawable getOverflowIcon()
  {
    ensureMenu();
    return this.mMenuView.getOverflowIcon();
  }
  
  public int getPopupTheme()
  {
    return this.mPopupTheme;
  }
  
  public CharSequence getSubtitle()
  {
    return this.mSubtitleText;
  }
  
  public CharSequence getTitle()
  {
    return this.mTitleText;
  }
  
  public DecorToolbar getWrapper()
  {
    if (this.mWrapper == null) {
      this.mWrapper = new ToolbarWidgetWrapper(this, true);
    }
    return this.mWrapper;
  }
  
  public boolean hasExpandedActionView()
  {
    return (this.mExpandedMenuPresenter != null) && (this.mExpandedMenuPresenter.mCurrentExpandedItem != null);
  }
  
  public boolean hideOverflowMenu()
  {
    return (this.mMenuView != null) && (this.mMenuView.hideOverflowMenu());
  }
  
  public void inflateMenu(@MenuRes int paramInt)
  {
    getMenuInflater().inflate(paramInt, getMenu());
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (this.mMenuView != null) && (this.mMenuView.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (this.mMenuView != null) && (this.mMenuView.isOverflowMenuShowing());
  }
  
  public boolean isTitleTruncated()
  {
    if (this.mTitleTextView == null) {}
    for (;;)
    {
      return false;
      Layout localLayout = this.mTitleTextView.getLayout();
      if (localLayout != null)
      {
        int j = localLayout.getLineCount();
        int i = 0;
        while (i < j)
        {
          if (localLayout.getEllipsisCount(i) > 0) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(this.mShowOverflowMenuRunnable);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 9) {
      this.mEatingHover = false;
    }
    if (!this.mEatingHover)
    {
      boolean bool = super.onHoverEvent(paramMotionEvent);
      if ((i == 9) && (!bool)) {
        this.mEatingHover = true;
      }
    }
    if ((i == 10) || (i == 3)) {
      this.mEatingHover = false;
    }
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j;
    int i2;
    int i4;
    int n;
    int i3;
    int m;
    int i5;
    int[] arrayOfInt;
    int i1;
    label120:
    label160:
    label200:
    label300:
    label340:
    boolean bool;
    Object localObject1;
    int k;
    label467:
    Object localObject2;
    if (ViewCompat.getLayoutDirection(this) == 1)
    {
      j = 1;
      i2 = getWidth();
      i4 = getHeight();
      n = getPaddingLeft();
      i3 = getPaddingRight();
      m = getPaddingTop();
      i5 = getPaddingBottom();
      paramInt3 = n;
      paramInt4 = i2 - i3;
      arrayOfInt = this.mTempMargins;
      arrayOfInt[1] = 0;
      arrayOfInt[0] = 0;
      i1 = ViewCompat.getMinimumHeight(this);
      paramInt1 = paramInt3;
      paramInt2 = paramInt4;
      if (shouldLayout(this.mNavButtonView))
      {
        if (j == 0) {
          break label901;
        }
        paramInt2 = layoutChildRight(this.mNavButtonView, paramInt4, arrayOfInt, i1);
        paramInt1 = paramInt3;
      }
      paramInt3 = paramInt1;
      paramInt4 = paramInt2;
      if (shouldLayout(this.mCollapseButtonView))
      {
        if (j == 0) {
          break label922;
        }
        paramInt4 = layoutChildRight(this.mCollapseButtonView, paramInt2, arrayOfInt, i1);
        paramInt3 = paramInt1;
      }
      paramInt2 = paramInt3;
      paramInt1 = paramInt4;
      if (shouldLayout(this.mMenuView))
      {
        if (j == 0) {
          break label943;
        }
        paramInt2 = layoutChildLeft(this.mMenuView, paramInt3, arrayOfInt, i1);
        paramInt1 = paramInt4;
      }
      arrayOfInt[0] = Math.max(0, getContentInsetLeft() - paramInt2);
      arrayOfInt[1] = Math.max(0, getContentInsetRight() - (i2 - i3 - paramInt1));
      paramInt3 = Math.max(paramInt2, getContentInsetLeft());
      paramInt4 = Math.min(paramInt1, i2 - i3 - getContentInsetRight());
      paramInt1 = paramInt3;
      paramInt2 = paramInt4;
      if (shouldLayout(this.mExpandedActionView))
      {
        if (j == 0) {
          break label964;
        }
        paramInt2 = layoutChildRight(this.mExpandedActionView, paramInt4, arrayOfInt, i1);
        paramInt1 = paramInt3;
      }
      paramInt3 = paramInt1;
      paramInt4 = paramInt2;
      if (shouldLayout(this.mLogoView))
      {
        if (j == 0) {
          break label985;
        }
        paramInt4 = layoutChildRight(this.mLogoView, paramInt2, arrayOfInt, i1);
        paramInt3 = paramInt1;
      }
      paramBoolean = shouldLayout(this.mTitleTextView);
      bool = shouldLayout(this.mSubtitleTextView);
      paramInt1 = 0;
      if (paramBoolean)
      {
        localObject1 = (LayoutParams)this.mTitleTextView.getLayoutParams();
        paramInt1 = 0 + (((LayoutParams)localObject1).topMargin + this.mTitleTextView.getMeasuredHeight() + ((LayoutParams)localObject1).bottomMargin);
      }
      k = paramInt1;
      if (bool)
      {
        localObject1 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
        k = paramInt1 + (((LayoutParams)localObject1).topMargin + this.mSubtitleTextView.getMeasuredHeight() + ((LayoutParams)localObject1).bottomMargin);
      }
      if (!paramBoolean)
      {
        paramInt2 = paramInt3;
        paramInt1 = paramInt4;
        if (!bool) {}
      }
      else
      {
        if (!paramBoolean) {
          break label1006;
        }
        localObject1 = this.mTitleTextView;
        if (!bool) {
          break label1015;
        }
        localObject2 = this.mSubtitleTextView;
        label478:
        localObject1 = (LayoutParams)((View)localObject1).getLayoutParams();
        localObject2 = (LayoutParams)((View)localObject2).getLayoutParams();
        if (((!paramBoolean) || (this.mTitleTextView.getMeasuredWidth() <= 0)) && ((!bool) || (this.mSubtitleTextView.getMeasuredWidth() <= 0))) {
          break label1024;
        }
        i = 1;
        label530:
        switch (this.mGravity & 0x70)
        {
        default: 
          paramInt2 = (i4 - m - i5 - k) / 2;
          if (paramInt2 < ((LayoutParams)localObject1).topMargin + this.mTitleMarginTop)
          {
            paramInt1 = ((LayoutParams)localObject1).topMargin + this.mTitleMarginTop;
            label603:
            paramInt1 = m + paramInt1;
            label608:
            if (j == 0) {
              break label1132;
            }
            if (i == 0) {
              break label1127;
            }
          }
          break;
        }
      }
    }
    label901:
    label922:
    label943:
    label964:
    label985:
    label1006:
    label1015:
    label1024:
    label1127:
    for (paramInt2 = this.mTitleMarginStart;; paramInt2 = 0)
    {
      paramInt2 -= arrayOfInt[1];
      paramInt4 -= Math.max(0, paramInt2);
      arrayOfInt[1] = Math.max(0, -paramInt2);
      k = paramInt4;
      paramInt2 = paramInt4;
      j = k;
      m = paramInt1;
      if (paramBoolean)
      {
        localObject1 = (LayoutParams)this.mTitleTextView.getLayoutParams();
        j = k - this.mTitleTextView.getMeasuredWidth();
        m = paramInt1 + this.mTitleTextView.getMeasuredHeight();
        this.mTitleTextView.layout(j, paramInt1, k, m);
        j -= this.mTitleMarginEnd;
        m += ((LayoutParams)localObject1).bottomMargin;
      }
      k = paramInt2;
      if (bool)
      {
        localObject1 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
        paramInt1 = m + ((LayoutParams)localObject1).topMargin;
        k = this.mSubtitleTextView.getMeasuredWidth();
        m = paramInt1 + this.mSubtitleTextView.getMeasuredHeight();
        this.mSubtitleTextView.layout(paramInt2 - k, paramInt1, paramInt2, m);
        k = paramInt2 - this.mTitleMarginEnd;
        paramInt1 = ((LayoutParams)localObject1).bottomMargin;
      }
      paramInt2 = paramInt3;
      paramInt1 = paramInt4;
      if (i != 0)
      {
        paramInt1 = Math.min(j, k);
        paramInt2 = paramInt3;
      }
      addCustomViewsWithGravity(this.mTempViews, 3);
      paramInt4 = this.mTempViews.size();
      paramInt3 = 0;
      while (paramInt3 < paramInt4)
      {
        paramInt2 = layoutChildLeft((View)this.mTempViews.get(paramInt3), paramInt2, arrayOfInt, i1);
        paramInt3 += 1;
      }
      j = 0;
      break;
      paramInt1 = layoutChildLeft(this.mNavButtonView, paramInt3, arrayOfInt, i1);
      paramInt2 = paramInt4;
      break label120;
      paramInt3 = layoutChildLeft(this.mCollapseButtonView, paramInt1, arrayOfInt, i1);
      paramInt4 = paramInt2;
      break label160;
      paramInt1 = layoutChildRight(this.mMenuView, paramInt4, arrayOfInt, i1);
      paramInt2 = paramInt3;
      break label200;
      paramInt1 = layoutChildLeft(this.mExpandedActionView, paramInt3, arrayOfInt, i1);
      paramInt2 = paramInt4;
      break label300;
      paramInt3 = layoutChildLeft(this.mLogoView, paramInt1, arrayOfInt, i1);
      paramInt4 = paramInt2;
      break label340;
      localObject1 = this.mSubtitleTextView;
      break label467;
      localObject2 = this.mTitleTextView;
      break label478;
      i = 0;
      break label530;
      paramInt1 = getPaddingTop() + ((LayoutParams)localObject1).topMargin + this.mTitleMarginTop;
      break label608;
      k = i4 - i5 - k - paramInt2 - m;
      paramInt1 = paramInt2;
      if (k >= ((LayoutParams)localObject1).bottomMargin + this.mTitleMarginBottom) {
        break label603;
      }
      paramInt1 = Math.max(0, paramInt2 - (((LayoutParams)localObject2).bottomMargin + this.mTitleMarginBottom - k));
      break label603;
      paramInt1 = i4 - i5 - ((LayoutParams)localObject2).bottomMargin - this.mTitleMarginBottom - k;
      break label608;
    }
    label1132:
    if (i != 0) {}
    for (paramInt2 = this.mTitleMarginStart;; paramInt2 = 0)
    {
      j = paramInt2 - arrayOfInt[0];
      paramInt2 = paramInt3 + Math.max(0, j);
      arrayOfInt[0] = Math.max(0, -j);
      k = paramInt2;
      paramInt3 = paramInt2;
      j = k;
      m = paramInt1;
      if (paramBoolean)
      {
        localObject1 = (LayoutParams)this.mTitleTextView.getLayoutParams();
        j = k + this.mTitleTextView.getMeasuredWidth();
        m = paramInt1 + this.mTitleTextView.getMeasuredHeight();
        this.mTitleTextView.layout(k, paramInt1, j, m);
        j += this.mTitleMarginEnd;
        m += ((LayoutParams)localObject1).bottomMargin;
      }
      k = paramInt3;
      if (bool)
      {
        localObject1 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
        paramInt1 = m + ((LayoutParams)localObject1).topMargin;
        k = paramInt3 + this.mSubtitleTextView.getMeasuredWidth();
        m = paramInt1 + this.mSubtitleTextView.getMeasuredHeight();
        this.mSubtitleTextView.layout(paramInt3, paramInt1, k, m);
        k += this.mTitleMarginEnd;
        paramInt1 = ((LayoutParams)localObject1).bottomMargin;
      }
      paramInt1 = paramInt4;
      if (i == 0) {
        break;
      }
      paramInt2 = Math.max(j, k);
      paramInt1 = paramInt4;
      break;
    }
    addCustomViewsWithGravity(this.mTempViews, 5);
    int i = this.mTempViews.size();
    paramInt4 = 0;
    paramInt3 = paramInt1;
    paramInt1 = paramInt4;
    while (paramInt1 < i)
    {
      paramInt3 = layoutChildRight((View)this.mTempViews.get(paramInt1), paramInt3, arrayOfInt, i1);
      paramInt1 += 1;
    }
    addCustomViewsWithGravity(this.mTempViews, 1);
    paramInt1 = getViewListMeasuredWidth(this.mTempViews, arrayOfInt);
    paramInt4 = n + (i2 - n - i3) / 2 - paramInt1 / 2;
    i = paramInt4 + paramInt1;
    if (paramInt4 < paramInt2) {
      paramInt1 = paramInt2;
    }
    for (;;)
    {
      paramInt3 = this.mTempViews.size();
      paramInt2 = 0;
      while (paramInt2 < paramInt3)
      {
        paramInt1 = layoutChildLeft((View)this.mTempViews.get(paramInt2), paramInt1, arrayOfInt, i1);
        paramInt2 += 1;
      }
      paramInt1 = paramInt4;
      if (i > paramInt3) {
        paramInt1 = paramInt4 - (i - paramInt3);
      }
    }
    this.mTempViews.clear();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int m = 0;
    int k = 0;
    int[] arrayOfInt = this.mTempMargins;
    int n;
    label524:
    View localView;
    if (ViewUtils.isLayoutRtl(this))
    {
      i2 = 1;
      i1 = 0;
      n = 0;
      if (shouldLayout(this.mNavButtonView))
      {
        measureChildConstrained(this.mNavButtonView, paramInt1, 0, paramInt2, 0, this.mMaxButtonHeight);
        n = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
        m = Math.max(0, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
        k = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState(this.mNavButtonView));
      }
      i = k;
      j = m;
      if (shouldLayout(this.mCollapseButtonView))
      {
        measureChildConstrained(this.mCollapseButtonView, paramInt1, 0, paramInt2, 0, this.mMaxButtonHeight);
        n = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
        j = Math.max(m, this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView));
        i = ViewUtils.combineMeasuredStates(k, ViewCompat.getMeasuredState(this.mCollapseButtonView));
      }
      k = getContentInsetStart();
      i3 = 0 + Math.max(k, n);
      arrayOfInt[i2] = Math.max(0, k - n);
      n = 0;
      k = i;
      m = j;
      if (shouldLayout(this.mMenuView))
      {
        measureChildConstrained(this.mMenuView, paramInt1, i3, paramInt2, 0, this.mMaxButtonHeight);
        n = this.mMenuView.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
        m = Math.max(j, this.mMenuView.getMeasuredHeight() + getVerticalMargins(this.mMenuView));
        k = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(this.mMenuView));
      }
      i = getContentInsetEnd();
      i2 = i3 + Math.max(i, n);
      arrayOfInt[i1] = Math.max(0, i - n);
      i1 = i2;
      i = k;
      j = m;
      if (shouldLayout(this.mExpandedActionView))
      {
        i1 = i2 + measureChildCollapseMargins(this.mExpandedActionView, paramInt1, i2, paramInt2, 0, arrayOfInt);
        j = Math.max(m, this.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView));
        i = ViewUtils.combineMeasuredStates(k, ViewCompat.getMeasuredState(this.mExpandedActionView));
      }
      k = i1;
      m = i;
      n = j;
      if (shouldLayout(this.mLogoView))
      {
        k = i1 + measureChildCollapseMargins(this.mLogoView, paramInt1, i1, paramInt2, 0, arrayOfInt);
        n = Math.max(j, this.mLogoView.getMeasuredHeight() + getVerticalMargins(this.mLogoView));
        m = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(this.mLogoView));
      }
      i3 = getChildCount();
      j = 0;
      i1 = n;
      i = m;
      n = k;
      if (j >= i3) {
        break label664;
      }
      localView = getChildAt(j);
      k = n;
      m = i;
      i2 = i1;
      if (((LayoutParams)localView.getLayoutParams()).mViewType == 0)
      {
        if (shouldLayout(localView)) {
          break label613;
        }
        i2 = i1;
        m = i;
        k = n;
      }
    }
    for (;;)
    {
      j += 1;
      n = k;
      i = m;
      i1 = i2;
      break label524;
      i2 = 0;
      i1 = 1;
      break;
      label613:
      k = n + measureChildCollapseMargins(localView, paramInt1, n, paramInt2, 0, arrayOfInt);
      i2 = Math.max(i1, localView.getMeasuredHeight() + getVerticalMargins(localView));
      m = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(localView));
    }
    label664:
    m = 0;
    k = 0;
    int i4 = this.mTitleMarginTop + this.mTitleMarginBottom;
    int i5 = this.mTitleMarginStart + this.mTitleMarginEnd;
    int j = i;
    if (shouldLayout(this.mTitleTextView))
    {
      measureChildCollapseMargins(this.mTitleTextView, paramInt1, n + i5, paramInt2, i4, arrayOfInt);
      m = this.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
      k = this.mTitleTextView.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
      j = ViewUtils.combineMeasuredStates(i, ViewCompat.getMeasuredState(this.mTitleTextView));
    }
    int i2 = j;
    int i3 = k;
    int i = m;
    if (shouldLayout(this.mSubtitleTextView))
    {
      i = Math.max(m, measureChildCollapseMargins(this.mSubtitleTextView, paramInt1, n + i5, paramInt2, k + i4, arrayOfInt));
      i3 = k + (this.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView));
      i2 = ViewUtils.combineMeasuredStates(j, ViewCompat.getMeasuredState(this.mSubtitleTextView));
    }
    j = Math.max(i1, i3);
    int i1 = getPaddingLeft();
    i3 = getPaddingRight();
    k = getPaddingTop();
    m = getPaddingBottom();
    i = ViewCompat.resolveSizeAndState(Math.max(n + i + (i1 + i3), getSuggestedMinimumWidth()), paramInt1, 0xFF000000 & i2);
    paramInt1 = ViewCompat.resolveSizeAndState(Math.max(j + (k + m), getSuggestedMinimumHeight()), paramInt2, i2 << 16);
    if (shouldCollapse()) {
      paramInt1 = 0;
    }
    setMeasuredDimension(i, paramInt1);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    if (this.mMenuView != null) {}
    for (paramParcelable = this.mMenuView.peekMenu();; paramParcelable = null)
    {
      if ((localSavedState.expandedMenuItemId != 0) && (this.mExpandedMenuPresenter != null) && (paramParcelable != null))
      {
        paramParcelable = paramParcelable.findItem(localSavedState.expandedMenuItemId);
        if (paramParcelable != null) {
          MenuItemCompat.expandActionView(paramParcelable);
        }
      }
      if (localSavedState.isOverflowOpen) {
        postShowOverflowMenu();
      }
      return;
    }
  }
  
  public void onRtlPropertiesChanged(int paramInt)
  {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 17) {
      super.onRtlPropertiesChanged(paramInt);
    }
    RtlSpacingHelper localRtlSpacingHelper = this.mContentInsets;
    if (paramInt == 1) {}
    for (;;)
    {
      localRtlSpacingHelper.setDirection(bool);
      return;
      bool = false;
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if ((this.mExpandedMenuPresenter != null) && (this.mExpandedMenuPresenter.mCurrentExpandedItem != null)) {
      localSavedState.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
    }
    localSavedState.isOverflowOpen = isOverflowMenuShowing();
    return localSavedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 0) {
      this.mEatingTouch = false;
    }
    if (!this.mEatingTouch)
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if ((i == 0) && (!bool)) {
        this.mEatingTouch = true;
      }
    }
    if ((i == 1) || (i == 3)) {
      this.mEatingTouch = false;
    }
    return true;
  }
  
  void removeChildrenForExpandedActionView()
  {
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      View localView = getChildAt(i);
      if ((((LayoutParams)localView.getLayoutParams()).mViewType != 2) && (localView != this.mMenuView))
      {
        removeViewAt(i);
        this.mHiddenViews.add(localView);
      }
      i -= 1;
    }
  }
  
  public void setCollapsible(boolean paramBoolean)
  {
    this.mCollapsible = paramBoolean;
    requestLayout();
  }
  
  public void setContentInsetsAbsolute(int paramInt1, int paramInt2)
  {
    this.mContentInsets.setAbsolute(paramInt1, paramInt2);
  }
  
  public void setContentInsetsRelative(int paramInt1, int paramInt2)
  {
    this.mContentInsets.setRelative(paramInt1, paramInt2);
  }
  
  public void setLogo(@DrawableRes int paramInt)
  {
    setLogo(this.mTintManager.getDrawable(paramInt));
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureLogoView();
      if (!isChildOrHidden(this.mLogoView)) {
        addSystemView(this.mLogoView, true);
      }
    }
    for (;;)
    {
      if (this.mLogoView != null) {
        this.mLogoView.setImageDrawable(paramDrawable);
      }
      return;
      if ((this.mLogoView != null) && (isChildOrHidden(this.mLogoView)))
      {
        removeView(this.mLogoView);
        this.mHiddenViews.remove(this.mLogoView);
      }
    }
  }
  
  public void setLogoDescription(@StringRes int paramInt)
  {
    setLogoDescription(getContext().getText(paramInt));
  }
  
  public void setLogoDescription(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureLogoView();
    }
    if (this.mLogoView != null) {
      this.mLogoView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setMenu(MenuBuilder paramMenuBuilder, ActionMenuPresenter paramActionMenuPresenter)
  {
    if ((paramMenuBuilder == null) && (this.mMenuView == null)) {}
    MenuBuilder localMenuBuilder;
    do
    {
      return;
      ensureMenuView();
      localMenuBuilder = this.mMenuView.peekMenu();
    } while (localMenuBuilder == paramMenuBuilder);
    if (localMenuBuilder != null)
    {
      localMenuBuilder.removeMenuPresenter(this.mOuterActionMenuPresenter);
      localMenuBuilder.removeMenuPresenter(this.mExpandedMenuPresenter);
    }
    if (this.mExpandedMenuPresenter == null) {
      this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(null);
    }
    paramActionMenuPresenter.setExpandedActionViewsExclusive(true);
    if (paramMenuBuilder != null)
    {
      paramMenuBuilder.addMenuPresenter(paramActionMenuPresenter, this.mPopupContext);
      paramMenuBuilder.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
    }
    for (;;)
    {
      this.mMenuView.setPopupTheme(this.mPopupTheme);
      this.mMenuView.setPresenter(paramActionMenuPresenter);
      this.mOuterActionMenuPresenter = paramActionMenuPresenter;
      return;
      paramActionMenuPresenter.initForMenu(this.mPopupContext, null);
      this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
      paramActionMenuPresenter.updateMenuView(true);
      this.mExpandedMenuPresenter.updateMenuView(true);
    }
  }
  
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1)
  {
    this.mActionMenuPresenterCallback = paramCallback;
    this.mMenuBuilderCallback = paramCallback1;
  }
  
  public void setNavigationContentDescription(@StringRes int paramInt)
  {
    if (paramInt != 0) {}
    for (CharSequence localCharSequence = getContext().getText(paramInt);; localCharSequence = null)
    {
      setNavigationContentDescription(localCharSequence);
      return;
    }
  }
  
  public void setNavigationContentDescription(@Nullable CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureNavButtonView();
    }
    if (this.mNavButtonView != null) {
      this.mNavButtonView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setNavigationIcon(@DrawableRes int paramInt)
  {
    setNavigationIcon(this.mTintManager.getDrawable(paramInt));
  }
  
  public void setNavigationIcon(@Nullable Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureNavButtonView();
      if (!isChildOrHidden(this.mNavButtonView)) {
        addSystemView(this.mNavButtonView, true);
      }
    }
    for (;;)
    {
      if (this.mNavButtonView != null) {
        this.mNavButtonView.setImageDrawable(paramDrawable);
      }
      return;
      if ((this.mNavButtonView != null) && (isChildOrHidden(this.mNavButtonView)))
      {
        removeView(this.mNavButtonView);
        this.mHiddenViews.remove(this.mNavButtonView);
      }
    }
  }
  
  public void setNavigationOnClickListener(View.OnClickListener paramOnClickListener)
  {
    ensureNavButtonView();
    this.mNavButtonView.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    this.mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowIcon(@Nullable Drawable paramDrawable)
  {
    ensureMenu();
    this.mMenuView.setOverflowIcon(paramDrawable);
  }
  
  public void setPopupTheme(@StyleRes int paramInt)
  {
    if (this.mPopupTheme != paramInt)
    {
      this.mPopupTheme = paramInt;
      if (paramInt == 0) {
        this.mPopupContext = getContext();
      }
    }
    else
    {
      return;
    }
    this.mPopupContext = new ContextThemeWrapper(getContext(), paramInt);
  }
  
  public void setSubtitle(@StringRes int paramInt)
  {
    setSubtitle(getContext().getText(paramInt));
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (this.mSubtitleTextView == null)
      {
        Context localContext = getContext();
        this.mSubtitleTextView = new TextView(localContext);
        this.mSubtitleTextView.setSingleLine();
        this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (this.mSubtitleTextAppearance != 0) {
          this.mSubtitleTextView.setTextAppearance(localContext, this.mSubtitleTextAppearance);
        }
        if (this.mSubtitleTextColor != 0) {
          this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
        }
      }
      if (!isChildOrHidden(this.mSubtitleTextView)) {
        addSystemView(this.mSubtitleTextView, true);
      }
    }
    for (;;)
    {
      if (this.mSubtitleTextView != null) {
        this.mSubtitleTextView.setText(paramCharSequence);
      }
      this.mSubtitleText = paramCharSequence;
      return;
      if ((this.mSubtitleTextView != null) && (isChildOrHidden(this.mSubtitleTextView)))
      {
        removeView(this.mSubtitleTextView);
        this.mHiddenViews.remove(this.mSubtitleTextView);
      }
    }
  }
  
  public void setSubtitleTextAppearance(Context paramContext, @StyleRes int paramInt)
  {
    this.mSubtitleTextAppearance = paramInt;
    if (this.mSubtitleTextView != null) {
      this.mSubtitleTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setSubtitleTextColor(@ColorInt int paramInt)
  {
    this.mSubtitleTextColor = paramInt;
    if (this.mSubtitleTextView != null) {
      this.mSubtitleTextView.setTextColor(paramInt);
    }
  }
  
  public void setTitle(@StringRes int paramInt)
  {
    setTitle(getContext().getText(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (this.mTitleTextView == null)
      {
        Context localContext = getContext();
        this.mTitleTextView = new TextView(localContext);
        this.mTitleTextView.setSingleLine();
        this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        if (this.mTitleTextAppearance != 0) {
          this.mTitleTextView.setTextAppearance(localContext, this.mTitleTextAppearance);
        }
        if (this.mTitleTextColor != 0) {
          this.mTitleTextView.setTextColor(this.mTitleTextColor);
        }
      }
      if (!isChildOrHidden(this.mTitleTextView)) {
        addSystemView(this.mTitleTextView, true);
      }
    }
    for (;;)
    {
      if (this.mTitleTextView != null) {
        this.mTitleTextView.setText(paramCharSequence);
      }
      this.mTitleText = paramCharSequence;
      return;
      if ((this.mTitleTextView != null) && (isChildOrHidden(this.mTitleTextView)))
      {
        removeView(this.mTitleTextView);
        this.mHiddenViews.remove(this.mTitleTextView);
      }
    }
  }
  
  public void setTitleTextAppearance(Context paramContext, @StyleRes int paramInt)
  {
    this.mTitleTextAppearance = paramInt;
    if (this.mTitleTextView != null) {
      this.mTitleTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setTitleTextColor(@ColorInt int paramInt)
  {
    this.mTitleTextColor = paramInt;
    if (this.mTitleTextView != null) {
      this.mTitleTextView.setTextColor(paramInt);
    }
  }
  
  public boolean showOverflowMenu()
  {
    return (this.mMenuView != null) && (this.mMenuView.showOverflowMenu());
  }
  
  private class ExpandedActionViewMenuPresenter
    implements MenuPresenter
  {
    MenuItemImpl mCurrentExpandedItem;
    MenuBuilder mMenu;
    
    private ExpandedActionViewMenuPresenter() {}
    
    public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      if ((Toolbar.this.mExpandedActionView instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewCollapsed();
      }
      Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
      Toolbar.this.removeView(Toolbar.this.mCollapseButtonView);
      Toolbar.this.mExpandedActionView = null;
      Toolbar.this.addChildrenForExpandedActionView();
      this.mCurrentExpandedItem = null;
      Toolbar.this.requestLayout();
      paramMenuItemImpl.setActionViewExpanded(false);
      return true;
    }
    
    public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
    {
      Toolbar.this.ensureCollapseButtonView();
      if (Toolbar.this.mCollapseButtonView.getParent() != Toolbar.this) {
        Toolbar.this.addView(Toolbar.this.mCollapseButtonView);
      }
      Toolbar.this.mExpandedActionView = paramMenuItemImpl.getActionView();
      this.mCurrentExpandedItem = paramMenuItemImpl;
      if (Toolbar.this.mExpandedActionView.getParent() != Toolbar.this)
      {
        paramMenuBuilder = Toolbar.this.generateDefaultLayoutParams();
        paramMenuBuilder.gravity = (0x800003 | Toolbar.this.mButtonGravity & 0x70);
        paramMenuBuilder.mViewType = 2;
        Toolbar.this.mExpandedActionView.setLayoutParams(paramMenuBuilder);
        Toolbar.this.addView(Toolbar.this.mExpandedActionView);
      }
      Toolbar.this.removeChildrenForExpandedActionView();
      Toolbar.this.requestLayout();
      paramMenuItemImpl.setActionViewExpanded(true);
      if ((Toolbar.this.mExpandedActionView instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewExpanded();
      }
      return true;
    }
    
    public boolean flagActionItems()
    {
      return false;
    }
    
    public int getId()
    {
      return 0;
    }
    
    public MenuView getMenuView(ViewGroup paramViewGroup)
    {
      return null;
    }
    
    public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
    {
      if ((this.mMenu != null) && (this.mCurrentExpandedItem != null)) {
        this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
      }
      this.mMenu = paramMenuBuilder;
    }
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public void onRestoreInstanceState(Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState()
    {
      return null;
    }
    
    public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
    {
      return false;
    }
    
    public void setCallback(MenuPresenter.Callback paramCallback) {}
    
    public void updateMenuView(boolean paramBoolean)
    {
      int k;
      int j;
      int m;
      int i;
      if (this.mCurrentExpandedItem != null)
      {
        k = 0;
        j = k;
        if (this.mMenu != null)
        {
          m = this.mMenu.size();
          i = 0;
        }
      }
      for (;;)
      {
        j = k;
        if (i < m)
        {
          if (this.mMenu.getItem(i) == this.mCurrentExpandedItem) {
            j = 1;
          }
        }
        else
        {
          if (j == 0) {
            collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
          }
          return;
        }
        i += 1;
      }
    }
  }
  
  public static class LayoutParams
    extends ActionBar.LayoutParams
  {
    static final int CUSTOM = 0;
    static final int EXPANDED = 2;
    static final int SYSTEM = 1;
    int mViewType = 0;
    
    public LayoutParams(int paramInt)
    {
      this(-2, -1, paramInt);
    }
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      this.gravity = 8388627;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2);
      this.gravity = paramInt3;
    }
    
    public LayoutParams(@NonNull Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(ActionBar.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      this.mViewType = paramLayoutParams.mViewType;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
      copyMarginsFromCompat(paramMarginLayoutParams);
    }
    
    void copyMarginsFromCompat(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      this.leftMargin = paramMarginLayoutParams.leftMargin;
      this.topMargin = paramMarginLayoutParams.topMargin;
      this.rightMargin = paramMarginLayoutParams.rightMargin;
      this.bottomMargin = paramMarginLayoutParams.bottomMargin;
    }
  }
  
  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public Toolbar.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new Toolbar.SavedState(paramAnonymousParcel);
      }
      
      public Toolbar.SavedState[] newArray(int paramAnonymousInt)
      {
        return new Toolbar.SavedState[paramAnonymousInt];
      }
    };
    int expandedMenuItemId;
    boolean isOverflowOpen;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      this.expandedMenuItemId = paramParcel.readInt();
      if (paramParcel.readInt() != 0) {}
      for (boolean bool = true;; bool = false)
      {
        this.isOverflowOpen = bool;
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
      paramParcel.writeInt(this.expandedMenuItemId);
      if (this.isOverflowOpen) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/Toolbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */