package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.color;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.style;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ContentFrameLayout.OnAttachListener;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import android.support.v7.widget.TintManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

class AppCompatDelegateImplV7
  extends AppCompatDelegateImplBase
  implements MenuBuilder.Callback, LayoutInflaterFactory
{
  private ActionMenuPresenterCallback mActionMenuPresenterCallback;
  ActionMode mActionMode;
  PopupWindow mActionModePopup;
  ActionBarContextView mActionModeView;
  private AppCompatViewInflater mAppCompatViewInflater;
  private boolean mClosingActionMenu;
  private DecorContentParent mDecorContentParent;
  private boolean mEnableDefaultActionBarUp;
  ViewPropertyAnimatorCompat mFadeAnim = null;
  private boolean mFeatureIndeterminateProgress;
  private boolean mFeatureProgress;
  private int mInvalidatePanelMenuFeatures;
  private boolean mInvalidatePanelMenuPosted;
  private final Runnable mInvalidatePanelMenuRunnable = new Runnable()
  {
    public void run()
    {
      if ((AppCompatDelegateImplV7.this.mInvalidatePanelMenuFeatures & 0x1) != 0) {
        AppCompatDelegateImplV7.this.doInvalidatePanelMenu(0);
      }
      if ((AppCompatDelegateImplV7.this.mInvalidatePanelMenuFeatures & 0x1000) != 0) {
        AppCompatDelegateImplV7.this.doInvalidatePanelMenu(108);
      }
      AppCompatDelegateImplV7.access$202(AppCompatDelegateImplV7.this, false);
      AppCompatDelegateImplV7.access$002(AppCompatDelegateImplV7.this, 0);
    }
  };
  private boolean mLongPressBackDown;
  private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
  private PanelFeatureState[] mPanels;
  private PanelFeatureState mPreparedPanel;
  Runnable mShowActionModePopup;
  private View mStatusGuard;
  private ViewGroup mSubDecor;
  private boolean mSubDecorInstalled;
  private Rect mTempRect1;
  private Rect mTempRect2;
  private TextView mTitleView;
  private ViewGroup mWindowDecor;
  
  AppCompatDelegateImplV7(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    super(paramContext, paramWindow, paramAppCompatCallback);
  }
  
  private void applyFixedSizeWindow()
  {
    ContentFrameLayout localContentFrameLayout = (ContentFrameLayout)this.mSubDecor.findViewById(16908290);
    localContentFrameLayout.setDecorPadding(this.mWindowDecor.getPaddingLeft(), this.mWindowDecor.getPaddingTop(), this.mWindowDecor.getPaddingRight(), this.mWindowDecor.getPaddingBottom());
    TypedArray localTypedArray = this.mContext.obtainStyledAttributes(R.styleable.Theme);
    localTypedArray.getValue(R.styleable.Theme_windowMinWidthMajor, localContentFrameLayout.getMinWidthMajor());
    localTypedArray.getValue(R.styleable.Theme_windowMinWidthMinor, localContentFrameLayout.getMinWidthMinor());
    if (localTypedArray.hasValue(R.styleable.Theme_windowFixedWidthMajor)) {
      localTypedArray.getValue(R.styleable.Theme_windowFixedWidthMajor, localContentFrameLayout.getFixedWidthMajor());
    }
    if (localTypedArray.hasValue(R.styleable.Theme_windowFixedWidthMinor)) {
      localTypedArray.getValue(R.styleable.Theme_windowFixedWidthMinor, localContentFrameLayout.getFixedWidthMinor());
    }
    if (localTypedArray.hasValue(R.styleable.Theme_windowFixedHeightMajor)) {
      localTypedArray.getValue(R.styleable.Theme_windowFixedHeightMajor, localContentFrameLayout.getFixedHeightMajor());
    }
    if (localTypedArray.hasValue(R.styleable.Theme_windowFixedHeightMinor)) {
      localTypedArray.getValue(R.styleable.Theme_windowFixedHeightMinor, localContentFrameLayout.getFixedHeightMinor());
    }
    localTypedArray.recycle();
    localContentFrameLayout.requestLayout();
  }
  
  private void callOnPanelClosed(int paramInt, PanelFeatureState paramPanelFeatureState, Menu paramMenu)
  {
    Object localObject1 = paramPanelFeatureState;
    Object localObject2 = paramMenu;
    if (paramMenu == null)
    {
      PanelFeatureState localPanelFeatureState = paramPanelFeatureState;
      if (paramPanelFeatureState == null)
      {
        localPanelFeatureState = paramPanelFeatureState;
        if (paramInt >= 0)
        {
          localPanelFeatureState = paramPanelFeatureState;
          if (paramInt < this.mPanels.length) {
            localPanelFeatureState = this.mPanels[paramInt];
          }
        }
      }
      localObject1 = localPanelFeatureState;
      localObject2 = paramMenu;
      if (localPanelFeatureState != null)
      {
        localObject2 = localPanelFeatureState.menu;
        localObject1 = localPanelFeatureState;
      }
    }
    if ((localObject1 != null) && (!((PanelFeatureState)localObject1).isOpen)) {}
    while (isDestroyed()) {
      return;
    }
    this.mOriginalWindowCallback.onPanelClosed(paramInt, (Menu)localObject2);
  }
  
  private void checkCloseActionMenu(MenuBuilder paramMenuBuilder)
  {
    if (this.mClosingActionMenu) {
      return;
    }
    this.mClosingActionMenu = true;
    this.mDecorContentParent.dismissPopups();
    Window.Callback localCallback = getWindowCallback();
    if ((localCallback != null) && (!isDestroyed())) {
      localCallback.onPanelClosed(108, paramMenuBuilder);
    }
    this.mClosingActionMenu = false;
  }
  
  private void closePanel(int paramInt)
  {
    closePanel(getPanelState(paramInt, true), true);
  }
  
  private void closePanel(PanelFeatureState paramPanelFeatureState, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramPanelFeatureState.featureId == 0) && (this.mDecorContentParent != null) && (this.mDecorContentParent.isOverflowMenuShowing())) {
      checkCloseActionMenu(paramPanelFeatureState.menu);
    }
    do
    {
      return;
      WindowManager localWindowManager = (WindowManager)this.mContext.getSystemService("window");
      if ((localWindowManager != null) && (paramPanelFeatureState.isOpen) && (paramPanelFeatureState.decorView != null))
      {
        localWindowManager.removeView(paramPanelFeatureState.decorView);
        if (paramBoolean) {
          callOnPanelClosed(paramPanelFeatureState.featureId, paramPanelFeatureState, null);
        }
      }
      paramPanelFeatureState.isPrepared = false;
      paramPanelFeatureState.isHandled = false;
      paramPanelFeatureState.isOpen = false;
      paramPanelFeatureState.shownPanelView = null;
      paramPanelFeatureState.refreshDecorView = true;
    } while (this.mPreparedPanel != paramPanelFeatureState);
    this.mPreparedPanel = null;
  }
  
  private ViewGroup createSubDecor()
  {
    Object localObject1 = this.mContext.obtainStyledAttributes(R.styleable.Theme);
    if (!((TypedArray)localObject1).hasValue(R.styleable.Theme_windowActionBar))
    {
      ((TypedArray)localObject1).recycle();
      throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }
    if (((TypedArray)localObject1).getBoolean(R.styleable.Theme_windowNoTitle, false))
    {
      requestWindowFeature(1);
      if (((TypedArray)localObject1).getBoolean(R.styleable.Theme_windowActionBarOverlay, false)) {
        requestWindowFeature(109);
      }
      if (((TypedArray)localObject1).getBoolean(R.styleable.Theme_windowActionModeOverlay, false)) {
        requestWindowFeature(10);
      }
      this.mIsFloating = ((TypedArray)localObject1).getBoolean(R.styleable.Theme_android_windowIsFloating, false);
      ((TypedArray)localObject1).recycle();
      localObject2 = LayoutInflater.from(this.mContext);
      localObject1 = null;
      if (this.mWindowNoTitle) {
        break label428;
      }
      if (!this.mIsFloating) {
        break label265;
      }
      localObject1 = (ViewGroup)((LayoutInflater)localObject2).inflate(R.layout.abc_dialog_title_material, null);
      this.mOverlayActionBar = false;
      this.mHasActionBar = false;
    }
    for (;;)
    {
      if (localObject1 != null) {
        break label505;
      }
      throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
      if (!((TypedArray)localObject1).getBoolean(R.styleable.Theme_windowActionBar, false)) {
        break;
      }
      requestWindowFeature(108);
      break;
      label265:
      if (this.mHasActionBar)
      {
        localObject1 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.actionBarTheme, (TypedValue)localObject1, true);
        if (((TypedValue)localObject1).resourceId != 0) {}
        for (localObject1 = new ContextThemeWrapper(this.mContext, ((TypedValue)localObject1).resourceId);; localObject1 = this.mContext)
        {
          localObject2 = (ViewGroup)LayoutInflater.from((Context)localObject1).inflate(R.layout.abc_screen_toolbar, null);
          this.mDecorContentParent = ((DecorContentParent)((ViewGroup)localObject2).findViewById(R.id.decor_content_parent));
          this.mDecorContentParent.setWindowCallback(getWindowCallback());
          if (this.mOverlayActionBar) {
            this.mDecorContentParent.initFeature(109);
          }
          if (this.mFeatureProgress) {
            this.mDecorContentParent.initFeature(2);
          }
          localObject1 = localObject2;
          if (!this.mFeatureIndeterminateProgress) {
            break;
          }
          this.mDecorContentParent.initFeature(5);
          localObject1 = localObject2;
          break;
        }
        label428:
        if (this.mOverlayActionMode) {}
        for (localObject1 = (ViewGroup)((LayoutInflater)localObject2).inflate(R.layout.abc_screen_simple_overlay_action_mode, null);; localObject1 = (ViewGroup)((LayoutInflater)localObject2).inflate(R.layout.abc_screen_simple, null))
        {
          if (Build.VERSION.SDK_INT < 21) {
            break label485;
          }
          ViewCompat.setOnApplyWindowInsetsListener((View)localObject1, new OnApplyWindowInsetsListener()
          {
            public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
            {
              int i = paramAnonymousWindowInsetsCompat.getSystemWindowInsetTop();
              int j = AppCompatDelegateImplV7.this.updateStatusGuard(i);
              WindowInsetsCompat localWindowInsetsCompat = paramAnonymousWindowInsetsCompat;
              if (i != j) {
                localWindowInsetsCompat = paramAnonymousWindowInsetsCompat.replaceSystemWindowInsets(paramAnonymousWindowInsetsCompat.getSystemWindowInsetLeft(), j, paramAnonymousWindowInsetsCompat.getSystemWindowInsetRight(), paramAnonymousWindowInsetsCompat.getSystemWindowInsetBottom());
              }
              return ViewCompat.onApplyWindowInsets(paramAnonymousView, localWindowInsetsCompat);
            }
          });
          break;
        }
        label485:
        ((FitWindowsViewGroup)localObject1).setOnFitSystemWindowsListener(new FitWindowsViewGroup.OnFitSystemWindowsListener()
        {
          public void onFitSystemWindows(Rect paramAnonymousRect)
          {
            paramAnonymousRect.top = AppCompatDelegateImplV7.this.updateStatusGuard(paramAnonymousRect.top);
          }
        });
      }
    }
    label505:
    if (this.mDecorContentParent == null) {
      this.mTitleView = ((TextView)((ViewGroup)localObject1).findViewById(R.id.title));
    }
    ViewUtils.makeOptionalFitsSystemWindows((View)localObject1);
    Object localObject2 = (ViewGroup)this.mWindow.findViewById(16908290);
    ContentFrameLayout localContentFrameLayout = (ContentFrameLayout)((ViewGroup)localObject1).findViewById(R.id.action_bar_activity_content);
    while (((ViewGroup)localObject2).getChildCount() > 0)
    {
      View localView = ((ViewGroup)localObject2).getChildAt(0);
      ((ViewGroup)localObject2).removeViewAt(0);
      localContentFrameLayout.addView(localView);
    }
    this.mWindow.setContentView((View)localObject1);
    ((ViewGroup)localObject2).setId(-1);
    localContentFrameLayout.setId(16908290);
    if ((localObject2 instanceof FrameLayout)) {
      ((FrameLayout)localObject2).setForeground(null);
    }
    localContentFrameLayout.setAttachListener(new ContentFrameLayout.OnAttachListener()
    {
      public void onAttachedFromWindow() {}
      
      public void onDetachedFromWindow()
      {
        AppCompatDelegateImplV7.this.dismissPopups();
      }
    });
    return (ViewGroup)localObject1;
  }
  
  private void dismissPopups()
  {
    if (this.mDecorContentParent != null) {
      this.mDecorContentParent.dismissPopups();
    }
    if (this.mActionModePopup != null)
    {
      this.mWindowDecor.removeCallbacks(this.mShowActionModePopup);
      if (!this.mActionModePopup.isShowing()) {}
    }
    try
    {
      this.mActionModePopup.dismiss();
      this.mActionModePopup = null;
      endOnGoingFadeAnimation();
      PanelFeatureState localPanelFeatureState = getPanelState(0, false);
      if ((localPanelFeatureState != null) && (localPanelFeatureState.menu != null)) {
        localPanelFeatureState.menu.close();
      }
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  private void doInvalidatePanelMenu(int paramInt)
  {
    PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
    if (localPanelFeatureState.menu != null)
    {
      Bundle localBundle = new Bundle();
      localPanelFeatureState.menu.saveActionViewStates(localBundle);
      if (localBundle.size() > 0) {
        localPanelFeatureState.frozenActionViewState = localBundle;
      }
      localPanelFeatureState.menu.stopDispatchingItemsChanged();
      localPanelFeatureState.menu.clear();
    }
    localPanelFeatureState.refreshMenuContent = true;
    localPanelFeatureState.refreshDecorView = true;
    if (((paramInt == 108) || (paramInt == 0)) && (this.mDecorContentParent != null))
    {
      localPanelFeatureState = getPanelState(0, false);
      if (localPanelFeatureState != null)
      {
        localPanelFeatureState.isPrepared = false;
        preparePanel(localPanelFeatureState, null);
      }
    }
  }
  
  private void endOnGoingFadeAnimation()
  {
    if (this.mFadeAnim != null) {
      this.mFadeAnim.cancel();
    }
  }
  
  private void ensureSubDecor()
  {
    if (!this.mSubDecorInstalled)
    {
      this.mSubDecor = createSubDecor();
      Object localObject = getTitle();
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        onTitleChanged((CharSequence)localObject);
      }
      applyFixedSizeWindow();
      onSubDecorInstalled(this.mSubDecor);
      this.mSubDecorInstalled = true;
      localObject = getPanelState(0, false);
      if ((!isDestroyed()) && ((localObject == null) || (((PanelFeatureState)localObject).menu == null))) {
        invalidatePanelMenu(108);
      }
    }
  }
  
  private PanelFeatureState findMenuPanel(Menu paramMenu)
  {
    PanelFeatureState[] arrayOfPanelFeatureState = this.mPanels;
    int i;
    int j;
    if (arrayOfPanelFeatureState != null)
    {
      i = arrayOfPanelFeatureState.length;
      j = 0;
    }
    for (;;)
    {
      if (j >= i) {
        break label57;
      }
      PanelFeatureState localPanelFeatureState = arrayOfPanelFeatureState[j];
      if ((localPanelFeatureState != null) && (localPanelFeatureState.menu == paramMenu))
      {
        return localPanelFeatureState;
        i = 0;
        break;
      }
      j += 1;
    }
    label57:
    return null;
  }
  
  private PanelFeatureState getPanelState(int paramInt, boolean paramBoolean)
  {
    Object localObject2 = this.mPanels;
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (localObject2.length > paramInt) {}
    }
    else
    {
      arrayOfPanelFeatureState = new PanelFeatureState[paramInt + 1];
      if (localObject2 != null) {
        System.arraycopy(localObject2, 0, arrayOfPanelFeatureState, 0, localObject2.length);
      }
      localObject1 = arrayOfPanelFeatureState;
      this.mPanels = arrayOfPanelFeatureState;
    }
    PanelFeatureState[] arrayOfPanelFeatureState = localObject1[paramInt];
    localObject2 = arrayOfPanelFeatureState;
    if (arrayOfPanelFeatureState == null)
    {
      localObject2 = new PanelFeatureState(paramInt);
      localObject1[paramInt] = localObject2;
    }
    return (PanelFeatureState)localObject2;
  }
  
  private boolean initializePanelContent(PanelFeatureState paramPanelFeatureState)
  {
    if (paramPanelFeatureState.createdPanelView != null) {
      paramPanelFeatureState.shownPanelView = paramPanelFeatureState.createdPanelView;
    }
    do
    {
      return true;
      if (paramPanelFeatureState.menu == null) {
        return false;
      }
      if (this.mPanelMenuPresenterCallback == null) {
        this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(null);
      }
      paramPanelFeatureState.shownPanelView = ((View)paramPanelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback));
    } while (paramPanelFeatureState.shownPanelView != null);
    return false;
  }
  
  private boolean initializePanelDecor(PanelFeatureState paramPanelFeatureState)
  {
    paramPanelFeatureState.setStyle(getActionBarThemedContext());
    paramPanelFeatureState.decorView = new ListMenuDecorView(paramPanelFeatureState.listPresenterContext);
    paramPanelFeatureState.gravity = 81;
    return true;
  }
  
  private boolean initializePanelMenu(PanelFeatureState paramPanelFeatureState)
  {
    Context localContext = this.mContext;
    Object localObject1;
    TypedValue localTypedValue;
    Resources.Theme localTheme;
    if (paramPanelFeatureState.featureId != 0)
    {
      localObject1 = localContext;
      if (paramPanelFeatureState.featureId != 108) {}
    }
    else
    {
      localObject1 = localContext;
      if (this.mDecorContentParent != null)
      {
        localTypedValue = new TypedValue();
        localTheme = localContext.getTheme();
        localTheme.resolveAttribute(R.attr.actionBarTheme, localTypedValue, true);
        localObject1 = null;
        if (localTypedValue.resourceId == 0) {
          break label197;
        }
        localObject1 = localContext.getResources().newTheme();
        ((Resources.Theme)localObject1).setTo(localTheme);
        ((Resources.Theme)localObject1).applyStyle(localTypedValue.resourceId, true);
        ((Resources.Theme)localObject1).resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
      }
    }
    for (;;)
    {
      Object localObject2 = localObject1;
      if (localTypedValue.resourceId != 0)
      {
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = localContext.getResources().newTheme();
          ((Resources.Theme)localObject2).setTo(localTheme);
        }
        ((Resources.Theme)localObject2).applyStyle(localTypedValue.resourceId, true);
      }
      localObject1 = localContext;
      if (localObject2 != null)
      {
        localObject1 = new ContextThemeWrapper(localContext, 0);
        ((Context)localObject1).getTheme().setTo((Resources.Theme)localObject2);
      }
      localObject1 = new MenuBuilder((Context)localObject1);
      ((MenuBuilder)localObject1).setCallback(this);
      paramPanelFeatureState.setMenu((MenuBuilder)localObject1);
      return true;
      label197:
      localTheme.resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
    }
  }
  
  private void invalidatePanelMenu(int paramInt)
  {
    this.mInvalidatePanelMenuFeatures |= 1 << paramInt;
    if ((!this.mInvalidatePanelMenuPosted) && (this.mWindowDecor != null))
    {
      ViewCompat.postOnAnimation(this.mWindowDecor, this.mInvalidatePanelMenuRunnable);
      this.mInvalidatePanelMenuPosted = true;
    }
  }
  
  private boolean onKeyDownPanel(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getRepeatCount() == 0)
    {
      PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
      if (!localPanelFeatureState.isOpen) {
        return preparePanel(localPanelFeatureState, paramKeyEvent);
      }
    }
    return false;
  }
  
  private boolean onKeyUpPanel(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool2;
    if (this.mActionMode != null)
    {
      bool2 = false;
      return bool2;
    }
    boolean bool3 = false;
    PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
    boolean bool1;
    if ((paramInt == 0) && (this.mDecorContentParent != null) && (this.mDecorContentParent.canShowOverflowMenu()) && (!ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext)))) {
      if (!this.mDecorContentParent.isOverflowMenuShowing())
      {
        bool1 = bool3;
        if (!isDestroyed())
        {
          bool1 = bool3;
          if (preparePanel(localPanelFeatureState, paramKeyEvent)) {
            bool1 = this.mDecorContentParent.showOverflowMenu();
          }
        }
      }
    }
    for (;;)
    {
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      paramKeyEvent = (AudioManager)this.mContext.getSystemService("audio");
      if (paramKeyEvent == null) {
        break label239;
      }
      paramKeyEvent.playSoundEffect(0);
      return bool1;
      bool1 = this.mDecorContentParent.hideOverflowMenu();
      continue;
      if ((localPanelFeatureState.isOpen) || (localPanelFeatureState.isHandled))
      {
        bool1 = localPanelFeatureState.isOpen;
        closePanel(localPanelFeatureState, true);
      }
      else
      {
        bool1 = bool3;
        if (localPanelFeatureState.isPrepared)
        {
          bool2 = true;
          if (localPanelFeatureState.refreshMenuContent)
          {
            localPanelFeatureState.isPrepared = false;
            bool2 = preparePanel(localPanelFeatureState, paramKeyEvent);
          }
          bool1 = bool3;
          if (bool2)
          {
            openPanel(localPanelFeatureState, paramKeyEvent);
            bool1 = true;
          }
        }
      }
    }
    label239:
    Log.w("AppCompatDelegate", "Couldn't get audio manager");
    return bool1;
  }
  
  private void openPanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent)
  {
    if ((paramPanelFeatureState.isOpen) || (isDestroyed())) {}
    Object localObject;
    int i;
    int j;
    label109:
    label114:
    label118:
    label120:
    WindowManager localWindowManager;
    do
    {
      do
      {
        for (;;)
        {
          return;
          if (paramPanelFeatureState.featureId == 0)
          {
            localObject = this.mContext;
            if ((((Context)localObject).getResources().getConfiguration().screenLayout & 0xF) != 4) {
              break label109;
            }
            i = 1;
            if (((Context)localObject).getApplicationInfo().targetSdkVersion < 11) {
              break label114;
            }
          }
          for (j = 1;; j = 0)
          {
            if ((i != 0) && (j != 0)) {
              break label118;
            }
            localObject = getWindowCallback();
            if ((localObject == null) || (((Window.Callback)localObject).onMenuOpened(paramPanelFeatureState.featureId, paramPanelFeatureState.menu))) {
              break label120;
            }
            closePanel(paramPanelFeatureState, true);
            return;
            i = 0;
            break;
          }
        }
        localWindowManager = (WindowManager)this.mContext.getSystemService("window");
      } while ((localWindowManager == null) || (!preparePanel(paramPanelFeatureState, paramKeyEvent)));
      j = -2;
      if ((paramPanelFeatureState.decorView != null) && (!paramPanelFeatureState.refreshDecorView)) {
        break label409;
      }
      if (paramPanelFeatureState.decorView != null) {
        break;
      }
    } while ((!initializePanelDecor(paramPanelFeatureState)) || (paramPanelFeatureState.decorView == null));
    label189:
    if ((initializePanelContent(paramPanelFeatureState)) && (paramPanelFeatureState.hasPanelItems()))
    {
      localObject = paramPanelFeatureState.shownPanelView.getLayoutParams();
      paramKeyEvent = (KeyEvent)localObject;
      if (localObject == null) {
        paramKeyEvent = new ViewGroup.LayoutParams(-2, -2);
      }
      i = paramPanelFeatureState.background;
      paramPanelFeatureState.decorView.setBackgroundResource(i);
      localObject = paramPanelFeatureState.shownPanelView.getParent();
      if ((localObject != null) && ((localObject instanceof ViewGroup))) {
        ((ViewGroup)localObject).removeView(paramPanelFeatureState.shownPanelView);
      }
      paramPanelFeatureState.decorView.addView(paramPanelFeatureState.shownPanelView, paramKeyEvent);
      i = j;
      if (!paramPanelFeatureState.shownPanelView.hasFocus())
      {
        paramPanelFeatureState.shownPanelView.requestFocus();
        i = j;
      }
    }
    for (;;)
    {
      paramPanelFeatureState.isHandled = false;
      paramKeyEvent = new WindowManager.LayoutParams(i, -2, paramPanelFeatureState.x, paramPanelFeatureState.y, 1002, 8519680, -3);
      paramKeyEvent.gravity = paramPanelFeatureState.gravity;
      paramKeyEvent.windowAnimations = paramPanelFeatureState.windowAnimations;
      localWindowManager.addView(paramPanelFeatureState.decorView, paramKeyEvent);
      paramPanelFeatureState.isOpen = true;
      return;
      if ((!paramPanelFeatureState.refreshDecorView) || (paramPanelFeatureState.decorView.getChildCount() <= 0)) {
        break label189;
      }
      paramPanelFeatureState.decorView.removeAllViews();
      break label189;
      break;
      label409:
      i = j;
      if (paramPanelFeatureState.createdPanelView != null)
      {
        paramKeyEvent = paramPanelFeatureState.createdPanelView.getLayoutParams();
        i = j;
        if (paramKeyEvent != null)
        {
          i = j;
          if (paramKeyEvent.width == -1) {
            i = -1;
          }
        }
      }
    }
  }
  
  private boolean performPanelShortcut(PanelFeatureState paramPanelFeatureState, int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    boolean bool2;
    if (paramKeyEvent.isSystem()) {
      bool2 = false;
    }
    boolean bool1;
    do
    {
      do
      {
        do
        {
          return bool2;
          bool2 = false;
          if (!paramPanelFeatureState.isPrepared)
          {
            bool1 = bool2;
            if (!preparePanel(paramPanelFeatureState, paramKeyEvent)) {}
          }
          else
          {
            bool1 = bool2;
            if (paramPanelFeatureState.menu != null) {
              bool1 = paramPanelFeatureState.menu.performShortcut(paramInt1, paramKeyEvent, paramInt2);
            }
          }
          bool2 = bool1;
        } while (!bool1);
        bool2 = bool1;
      } while ((paramInt2 & 0x1) != 0);
      bool2 = bool1;
    } while (this.mDecorContentParent != null);
    closePanel(paramPanelFeatureState, true);
    return bool1;
  }
  
  private boolean preparePanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent)
  {
    if (isDestroyed()) {
      return false;
    }
    if (paramPanelFeatureState.isPrepared) {
      return true;
    }
    if ((this.mPreparedPanel != null) && (this.mPreparedPanel != paramPanelFeatureState)) {
      closePanel(this.mPreparedPanel, false);
    }
    Window.Callback localCallback = getWindowCallback();
    if (localCallback != null) {
      paramPanelFeatureState.createdPanelView = localCallback.onCreatePanelView(paramPanelFeatureState.featureId);
    }
    if ((paramPanelFeatureState.featureId == 0) || (paramPanelFeatureState.featureId == 108)) {}
    for (int i = 1;; i = 0)
    {
      if ((i != 0) && (this.mDecorContentParent != null)) {
        this.mDecorContentParent.setMenuPrepared();
      }
      if ((paramPanelFeatureState.createdPanelView != null) || ((i != 0) && ((peekSupportActionBar() instanceof ToolbarActionBar)))) {
        break label408;
      }
      if ((paramPanelFeatureState.menu != null) && (!paramPanelFeatureState.refreshMenuContent)) {
        break label278;
      }
      if ((paramPanelFeatureState.menu == null) && ((!initializePanelMenu(paramPanelFeatureState)) || (paramPanelFeatureState.menu == null))) {
        break;
      }
      if ((i != 0) && (this.mDecorContentParent != null))
      {
        if (this.mActionMenuPresenterCallback == null) {
          this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(null);
        }
        this.mDecorContentParent.setMenu(paramPanelFeatureState.menu, this.mActionMenuPresenterCallback);
      }
      paramPanelFeatureState.menu.stopDispatchingItemsChanged();
      if (localCallback.onCreatePanelMenu(paramPanelFeatureState.featureId, paramPanelFeatureState.menu)) {
        break label273;
      }
      paramPanelFeatureState.setMenu(null);
      if ((i == 0) || (this.mDecorContentParent == null)) {
        break;
      }
      this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
      return false;
    }
    label273:
    paramPanelFeatureState.refreshMenuContent = false;
    label278:
    paramPanelFeatureState.menu.stopDispatchingItemsChanged();
    if (paramPanelFeatureState.frozenActionViewState != null)
    {
      paramPanelFeatureState.menu.restoreActionViewStates(paramPanelFeatureState.frozenActionViewState);
      paramPanelFeatureState.frozenActionViewState = null;
    }
    if (!localCallback.onPreparePanel(0, paramPanelFeatureState.createdPanelView, paramPanelFeatureState.menu))
    {
      if ((i != 0) && (this.mDecorContentParent != null)) {
        this.mDecorContentParent.setMenu(null, this.mActionMenuPresenterCallback);
      }
      paramPanelFeatureState.menu.startDispatchingItemsChanged();
      return false;
    }
    if (paramKeyEvent != null)
    {
      i = paramKeyEvent.getDeviceId();
      if (KeyCharacterMap.load(i).getKeyboardType() == 1) {
        break label430;
      }
    }
    label408:
    label430:
    for (boolean bool = true;; bool = false)
    {
      paramPanelFeatureState.qwertyMode = bool;
      paramPanelFeatureState.menu.setQwertyMode(paramPanelFeatureState.qwertyMode);
      paramPanelFeatureState.menu.startDispatchingItemsChanged();
      paramPanelFeatureState.isPrepared = true;
      paramPanelFeatureState.isHandled = false;
      this.mPreparedPanel = paramPanelFeatureState;
      return true;
      i = -1;
      break;
    }
  }
  
  private void reopenMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if ((this.mDecorContentParent != null) && (this.mDecorContentParent.canShowOverflowMenu()) && ((!ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(this.mContext))) || (this.mDecorContentParent.isOverflowMenuShowPending())))
    {
      paramMenuBuilder = getWindowCallback();
      if ((!this.mDecorContentParent.isOverflowMenuShowing()) || (!paramBoolean)) {
        if ((paramMenuBuilder != null) && (!isDestroyed()))
        {
          if ((this.mInvalidatePanelMenuPosted) && ((this.mInvalidatePanelMenuFeatures & 0x1) != 0))
          {
            this.mWindowDecor.removeCallbacks(this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuRunnable.run();
          }
          PanelFeatureState localPanelFeatureState = getPanelState(0, true);
          if ((localPanelFeatureState.menu != null) && (!localPanelFeatureState.refreshMenuContent) && (paramMenuBuilder.onPreparePanel(0, localPanelFeatureState.createdPanelView, localPanelFeatureState.menu)))
          {
            paramMenuBuilder.onMenuOpened(108, localPanelFeatureState.menu);
            this.mDecorContentParent.showOverflowMenu();
          }
        }
      }
      do
      {
        return;
        this.mDecorContentParent.hideOverflowMenu();
      } while (isDestroyed());
      paramMenuBuilder.onPanelClosed(108, getPanelState(0, true).menu);
      return;
    }
    paramMenuBuilder = getPanelState(0, true);
    paramMenuBuilder.refreshDecorView = true;
    closePanel(paramMenuBuilder, false);
    openPanel(paramMenuBuilder, null);
  }
  
  private int sanitizeWindowFeatureId(int paramInt)
  {
    int i;
    if (paramInt == 8)
    {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
      i = 108;
    }
    do
    {
      return i;
      i = paramInt;
    } while (paramInt != 9);
    Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
    return 109;
  }
  
  private boolean shouldInheritContext(ViewParent paramViewParent)
  {
    ViewParent localViewParent = paramViewParent;
    if (paramViewParent == null) {
      return false;
    }
    do
    {
      localViewParent = localViewParent.getParent();
      if (localViewParent == null) {
        return true;
      }
    } while ((localViewParent != this.mWindowDecor) && ((localViewParent instanceof View)) && (!ViewCompat.isAttachedToWindow((View)localViewParent)));
    return false;
  }
  
  private void throwFeatureRequestIfSubDecorInstalled()
  {
    if (this.mSubDecorInstalled) {
      throw new AndroidRuntimeException("Window feature must be requested before adding content");
    }
  }
  
  private int updateStatusGuard(int paramInt)
  {
    int i1 = 0;
    int j = 0;
    int i2 = 0;
    int n = j;
    int i = paramInt;
    Object localObject1;
    Object localObject2;
    label217:
    label227:
    int k;
    int m;
    if (this.mActionModeView != null)
    {
      n = j;
      i = paramInt;
      if ((this.mActionModeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
      {
        localObject1 = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
        j = 0;
        i = 0;
        if (!this.mActionModeView.isShown()) {
          break label373;
        }
        if (this.mTempRect1 == null)
        {
          this.mTempRect1 = new Rect();
          this.mTempRect2 = new Rect();
        }
        localObject2 = this.mTempRect1;
        Rect localRect = this.mTempRect2;
        ((Rect)localObject2).set(0, paramInt, 0, 0);
        ViewUtils.computeFitSystemWindows(this.mSubDecor, (Rect)localObject2, localRect);
        if (localRect.top != 0) {
          break label322;
        }
        j = paramInt;
        if (((ViewGroup.MarginLayoutParams)localObject1).topMargin != j)
        {
          j = 1;
          ((ViewGroup.MarginLayoutParams)localObject1).topMargin = paramInt;
          if (this.mStatusGuard != null) {
            break label327;
          }
          this.mStatusGuard = new View(this.mContext);
          this.mStatusGuard.setBackgroundColor(this.mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
          this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, paramInt));
          i = j;
        }
        if (this.mStatusGuard == null) {
          break label367;
        }
        n = 1;
        j = i;
        k = n;
        m = paramInt;
        if (!this.mOverlayActionMode)
        {
          j = i;
          k = n;
          m = paramInt;
          if (n != 0)
          {
            m = 0;
            k = n;
            j = i;
          }
        }
        label266:
        n = k;
        i = m;
        if (j != 0)
        {
          this.mActionModeView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
          i = m;
          n = k;
        }
      }
    }
    if (this.mStatusGuard != null)
    {
      localObject1 = this.mStatusGuard;
      if (n == 0) {
        break label406;
      }
    }
    label322:
    label327:
    label367:
    label373:
    label406:
    for (paramInt = i1;; paramInt = 8)
    {
      ((View)localObject1).setVisibility(paramInt);
      return i;
      j = 0;
      break;
      localObject2 = this.mStatusGuard.getLayoutParams();
      i = j;
      if (((ViewGroup.LayoutParams)localObject2).height == paramInt) {
        break label217;
      }
      ((ViewGroup.LayoutParams)localObject2).height = paramInt;
      this.mStatusGuard.setLayoutParams((ViewGroup.LayoutParams)localObject2);
      i = j;
      break label217;
      n = 0;
      break label227;
      k = i2;
      m = paramInt;
      if (((ViewGroup.MarginLayoutParams)localObject1).topMargin == 0) {
        break label266;
      }
      j = 1;
      ((ViewGroup.MarginLayoutParams)localObject1).topMargin = 0;
      k = i2;
      m = paramInt;
      break label266;
    }
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    ensureSubDecor();
    ((ViewGroup)this.mSubDecor.findViewById(16908290)).addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  View callActivityOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    if ((this.mOriginalWindowCallback instanceof LayoutInflater.Factory))
    {
      paramView = ((LayoutInflater.Factory)this.mOriginalWindowCallback).onCreateView(paramString, paramContext, paramAttributeSet);
      if (paramView != null) {
        return paramView;
      }
    }
    return null;
  }
  
  public View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet)
  {
    boolean bool1;
    if (Build.VERSION.SDK_INT < 21)
    {
      bool1 = true;
      if (this.mAppCompatViewInflater == null) {
        this.mAppCompatViewInflater = new AppCompatViewInflater();
      }
      if ((!bool1) || (!this.mSubDecorInstalled) || (!shouldInheritContext((ViewParent)paramView))) {
        break label79;
      }
    }
    label79:
    for (boolean bool2 = true;; bool2 = false)
    {
      return this.mAppCompatViewInflater.createView(paramView, paramString, paramContext, paramAttributeSet, bool2, bool1, true);
      bool1 = false;
      break;
    }
  }
  
  boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyCode() == 82) && (this.mOriginalWindowCallback.dispatchKeyEvent(paramKeyEvent))) {
      return true;
    }
    int j = paramKeyEvent.getKeyCode();
    if (paramKeyEvent.getAction() == 0) {}
    for (int i = 1; i != 0; i = 0) {
      return onKeyDown(j, paramKeyEvent);
    }
    return onKeyUp(j, paramKeyEvent);
  }
  
  ViewGroup getSubDecor()
  {
    return this.mSubDecor;
  }
  
  public boolean hasWindowFeature(int paramInt)
  {
    paramInt = sanitizeWindowFeatureId(paramInt);
    switch (paramInt)
    {
    default: 
      return this.mWindow.hasFeature(paramInt);
    case 108: 
      return this.mHasActionBar;
    case 109: 
      return this.mOverlayActionBar;
    case 10: 
      return this.mOverlayActionMode;
    case 2: 
      return this.mFeatureProgress;
    case 5: 
      return this.mFeatureIndeterminateProgress;
    }
    return this.mWindowNoTitle;
  }
  
  public void initWindowDecorActionBar()
  {
    ensureSubDecor();
    if ((!this.mHasActionBar) || (this.mActionBar != null)) {}
    for (;;)
    {
      return;
      if ((this.mOriginalWindowCallback instanceof Activity)) {
        this.mActionBar = new WindowDecorActionBar((Activity)this.mOriginalWindowCallback, this.mOverlayActionBar);
      }
      while (this.mActionBar != null)
      {
        this.mActionBar.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
        return;
        if ((this.mOriginalWindowCallback instanceof Dialog)) {
          this.mActionBar = new WindowDecorActionBar((Dialog)this.mOriginalWindowCallback);
        }
      }
    }
  }
  
  public void installViewFactory()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.mContext);
    if (localLayoutInflater.getFactory() == null)
    {
      LayoutInflaterCompat.setFactory(localLayoutInflater, this);
      return;
    }
    Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
  }
  
  public void invalidateOptionsMenu()
  {
    ActionBar localActionBar = getSupportActionBar();
    if ((localActionBar != null) && (localActionBar.invalidateOptionsMenu())) {
      return;
    }
    invalidatePanelMenu(0);
  }
  
  boolean onBackPressed()
  {
    if (this.mActionMode != null) {
      this.mActionMode.finish();
    }
    ActionBar localActionBar;
    do
    {
      return true;
      localActionBar = getSupportActionBar();
    } while ((localActionBar != null) && (localActionBar.collapseActionView()));
    return false;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if ((this.mHasActionBar) && (this.mSubDecorInstalled))
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null) {
        localActionBar.onConfigurationChanged(paramConfiguration);
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    this.mWindowDecor = ((ViewGroup)this.mWindow.getDecorView());
    if (((this.mOriginalWindowCallback instanceof Activity)) && (NavUtils.getParentActivityName((Activity)this.mOriginalWindowCallback) != null))
    {
      paramBundle = peekSupportActionBar();
      if (paramBundle == null) {
        this.mEnableDefaultActionBarUp = true;
      }
    }
    else
    {
      return;
    }
    paramBundle.setDefaultDisplayHomeAsUpEnabled(true);
  }
  
  public final View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView = callActivityOnCreateView(paramView, paramString, paramContext, paramAttributeSet);
    if (localView != null) {
      return localView;
    }
    return createView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    switch (paramInt)
    {
    default: 
      if (Build.VERSION.SDK_INT < 11) {
        onKeyShortcut(paramInt, paramKeyEvent);
      }
      return false;
    case 82: 
      onKeyDownPanel(0, paramKeyEvent);
      return true;
    }
    if ((paramKeyEvent.getFlags() & 0x80) != 0) {}
    for (;;)
    {
      this.mLongPressBackDown = bool;
      break;
      bool = false;
    }
  }
  
  boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject = getSupportActionBar();
    if ((localObject != null) && (((ActionBar)localObject).onKeyShortcut(paramInt, paramKeyEvent))) {}
    boolean bool;
    do
    {
      do
      {
        return true;
        if ((this.mPreparedPanel == null) || (!performPanelShortcut(this.mPreparedPanel, paramKeyEvent.getKeyCode(), paramKeyEvent, 1))) {
          break;
        }
      } while (this.mPreparedPanel == null);
      this.mPreparedPanel.isHandled = true;
      return true;
      if (this.mPreparedPanel != null) {
        break;
      }
      localObject = getPanelState(0, true);
      preparePanel((PanelFeatureState)localObject, paramKeyEvent);
      bool = performPanelShortcut((PanelFeatureState)localObject, paramKeyEvent.getKeyCode(), paramKeyEvent, 1);
      ((PanelFeatureState)localObject).isPrepared = false;
    } while (bool);
    return false;
  }
  
  boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool1 = true;
    switch (paramInt)
    {
    }
    do
    {
      bool1 = false;
      boolean bool2;
      do
      {
        return bool1;
        onKeyUpPanel(0, paramKeyEvent);
        return true;
        bool2 = this.mLongPressBackDown;
        this.mLongPressBackDown = false;
        paramKeyEvent = getPanelState(0, false);
        if ((paramKeyEvent == null) || (!paramKeyEvent.isOpen)) {
          break;
        }
      } while (bool2);
      closePanel(paramKeyEvent, true);
      return true;
    } while (!onBackPressed());
    return true;
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    Window.Callback localCallback = getWindowCallback();
    if ((localCallback != null) && (!isDestroyed()))
    {
      paramMenuBuilder = findMenuPanel(paramMenuBuilder.getRootMenu());
      if (paramMenuBuilder != null) {
        return localCallback.onMenuItemSelected(paramMenuBuilder.featureId, paramMenuItem);
      }
    }
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder)
  {
    reopenMenu(paramMenuBuilder, true);
  }
  
  boolean onMenuOpened(int paramInt, Menu paramMenu)
  {
    if (paramInt == 108)
    {
      paramMenu = getSupportActionBar();
      if (paramMenu != null) {
        paramMenu.dispatchMenuVisibilityChanged(true);
      }
      return true;
    }
    return false;
  }
  
  void onPanelClosed(int paramInt, Menu paramMenu)
  {
    if (paramInt == 108)
    {
      paramMenu = getSupportActionBar();
      if (paramMenu != null) {
        paramMenu.dispatchMenuVisibilityChanged(false);
      }
    }
    do
    {
      do
      {
        return;
      } while (paramInt != 0);
      paramMenu = getPanelState(paramInt, true);
    } while (!paramMenu.isOpen);
    closePanel(paramMenu, false);
  }
  
  public void onPostCreate(Bundle paramBundle)
  {
    ensureSubDecor();
  }
  
  public void onPostResume()
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null) {
      localActionBar.setShowHideAnimationEnabled(true);
    }
  }
  
  public void onStop()
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null) {
      localActionBar.setShowHideAnimationEnabled(false);
    }
  }
  
  void onSubDecorInstalled(ViewGroup paramViewGroup) {}
  
  void onTitleChanged(CharSequence paramCharSequence)
  {
    if (this.mDecorContentParent != null) {
      this.mDecorContentParent.setWindowTitle(paramCharSequence);
    }
    do
    {
      return;
      if (peekSupportActionBar() != null)
      {
        peekSupportActionBar().setWindowTitle(paramCharSequence);
        return;
      }
    } while (this.mTitleView == null);
    this.mTitleView.setText(paramCharSequence);
  }
  
  public boolean requestWindowFeature(int paramInt)
  {
    paramInt = sanitizeWindowFeatureId(paramInt);
    if ((this.mWindowNoTitle) && (paramInt == 108)) {
      return false;
    }
    if ((this.mHasActionBar) && (paramInt == 1)) {
      this.mHasActionBar = false;
    }
    switch (paramInt)
    {
    default: 
      return this.mWindow.requestFeature(paramInt);
    case 108: 
      throwFeatureRequestIfSubDecorInstalled();
      this.mHasActionBar = true;
      return true;
    case 109: 
      throwFeatureRequestIfSubDecorInstalled();
      this.mOverlayActionBar = true;
      return true;
    case 10: 
      throwFeatureRequestIfSubDecorInstalled();
      this.mOverlayActionMode = true;
      return true;
    case 2: 
      throwFeatureRequestIfSubDecorInstalled();
      this.mFeatureProgress = true;
      return true;
    case 5: 
      throwFeatureRequestIfSubDecorInstalled();
      this.mFeatureIndeterminateProgress = true;
      return true;
    }
    throwFeatureRequestIfSubDecorInstalled();
    this.mWindowNoTitle = true;
    return true;
  }
  
  public void setContentView(int paramInt)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    LayoutInflater.from(this.mContext).inflate(paramInt, localViewGroup);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    localViewGroup.addView(paramView);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)this.mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    localViewGroup.addView(paramView, paramLayoutParams);
    this.mOriginalWindowCallback.onContentChanged();
  }
  
  public void setSupportActionBar(Toolbar paramToolbar)
  {
    if (!(this.mOriginalWindowCallback instanceof Activity)) {
      return;
    }
    if ((getSupportActionBar() instanceof WindowDecorActionBar)) {
      throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
    }
    this.mMenuInflater = null;
    paramToolbar = new ToolbarActionBar(paramToolbar, ((Activity)this.mContext).getTitle(), this.mAppCompatWindowCallback);
    this.mActionBar = paramToolbar;
    this.mWindow.setCallback(paramToolbar.getWrappedWindowCallback());
    paramToolbar.invalidateOptionsMenu();
  }
  
  public ActionMode startSupportActionMode(ActionMode.Callback paramCallback)
  {
    if (paramCallback == null) {
      throw new IllegalArgumentException("ActionMode callback can not be null.");
    }
    if (this.mActionMode != null) {
      this.mActionMode.finish();
    }
    paramCallback = new ActionModeCallbackWrapperV7(paramCallback);
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      this.mActionMode = localActionBar.startActionMode(paramCallback);
      if ((this.mActionMode != null) && (this.mAppCompatCallback != null)) {
        this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
      }
    }
    if (this.mActionMode == null) {
      this.mActionMode = startSupportActionModeFromWindow(paramCallback);
    }
    return this.mActionMode;
  }
  
  ActionMode startSupportActionModeFromWindow(ActionMode.Callback paramCallback)
  {
    endOnGoingFadeAnimation();
    if (this.mActionMode != null) {
      this.mActionMode.finish();
    }
    ActionModeCallbackWrapperV7 localActionModeCallbackWrapperV7 = new ActionModeCallbackWrapperV7(paramCallback);
    Object localObject3 = null;
    Object localObject1 = localObject3;
    if (this.mAppCompatCallback != null)
    {
      localObject1 = localObject3;
      if (isDestroyed()) {}
    }
    try
    {
      localObject1 = this.mAppCompatCallback.onWindowStartingSupportActionMode(localActionModeCallbackWrapperV7);
      if (localObject1 != null) {
        this.mActionMode = ((ActionMode)localObject1);
      }
      for (;;)
      {
        if ((this.mActionMode != null) && (this.mAppCompatCallback != null)) {
          this.mAppCompatCallback.onSupportActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
        if (this.mActionModeView == null)
        {
          if (!this.mIsFloating) {
            break label501;
          }
          localObject3 = new TypedValue();
          localObject1 = this.mContext.getTheme();
          ((Resources.Theme)localObject1).resolveAttribute(R.attr.actionBarTheme, (TypedValue)localObject3, true);
          if (((TypedValue)localObject3).resourceId != 0)
          {
            Resources.Theme localTheme = this.mContext.getResources().newTheme();
            localTheme.setTo((Resources.Theme)localObject1);
            localTheme.applyStyle(((TypedValue)localObject3).resourceId, true);
            localObject1 = new ContextThemeWrapper(this.mContext, 0);
            ((Context)localObject1).getTheme().setTo(localTheme);
            label216:
            this.mActionModeView = new ActionBarContextView((Context)localObject1);
            this.mActionModePopup = new PopupWindow((Context)localObject1, null, R.attr.actionModePopupWindowStyle);
            PopupWindowCompat.setWindowLayoutType(this.mActionModePopup, 2);
            this.mActionModePopup.setContentView(this.mActionModeView);
            this.mActionModePopup.setWidth(-1);
            ((Context)localObject1).getTheme().resolveAttribute(R.attr.actionBarSize, (TypedValue)localObject3, true);
            int i = TypedValue.complexToDimensionPixelSize(((TypedValue)localObject3).data, ((Context)localObject1).getResources().getDisplayMetrics());
            this.mActionModeView.setContentHeight(i);
            this.mActionModePopup.setHeight(-2);
            this.mShowActionModePopup = new Runnable()
            {
              public void run()
              {
                AppCompatDelegateImplV7.this.mActionModePopup.showAtLocation(AppCompatDelegateImplV7.this.mActionModeView, 55, 0, 0);
                AppCompatDelegateImplV7.this.endOnGoingFadeAnimation();
                ViewCompat.setAlpha(AppCompatDelegateImplV7.this.mActionModeView, 0.0F);
                AppCompatDelegateImplV7.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImplV7.this.mActionModeView).alpha(1.0F);
                AppCompatDelegateImplV7.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter()
                {
                  public void onAnimationEnd(View paramAnonymous2View)
                  {
                    ViewCompat.setAlpha(AppCompatDelegateImplV7.this.mActionModeView, 1.0F);
                    AppCompatDelegateImplV7.this.mFadeAnim.setListener(null);
                    AppCompatDelegateImplV7.this.mFadeAnim = null;
                  }
                  
                  public void onAnimationStart(View paramAnonymous2View)
                  {
                    AppCompatDelegateImplV7.this.mActionModeView.setVisibility(0);
                  }
                });
              }
            };
          }
        }
        else
        {
          label334:
          if (this.mActionModeView == null) {
            break label546;
          }
          endOnGoingFadeAnimation();
          this.mActionModeView.killMode();
          localObject1 = this.mActionModeView.getContext();
          localObject3 = this.mActionModeView;
          if (this.mActionModePopup != null) {
            break label548;
          }
        }
        label501:
        label546:
        label548:
        for (boolean bool = true;; bool = false)
        {
          localObject1 = new StandaloneActionMode((Context)localObject1, (ActionBarContextView)localObject3, localActionModeCallbackWrapperV7, bool);
          if (!paramCallback.onCreateActionMode((ActionMode)localObject1, ((ActionMode)localObject1).getMenu())) {
            break label553;
          }
          ((ActionMode)localObject1).invalidate();
          this.mActionModeView.initForMode((ActionMode)localObject1);
          this.mActionMode = ((ActionMode)localObject1);
          ViewCompat.setAlpha(this.mActionModeView, 0.0F);
          this.mFadeAnim = ViewCompat.animate(this.mActionModeView).alpha(1.0F);
          this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter()
          {
            public void onAnimationEnd(View paramAnonymousView)
            {
              ViewCompat.setAlpha(AppCompatDelegateImplV7.this.mActionModeView, 1.0F);
              AppCompatDelegateImplV7.this.mFadeAnim.setListener(null);
              AppCompatDelegateImplV7.this.mFadeAnim = null;
            }
            
            public void onAnimationStart(View paramAnonymousView)
            {
              AppCompatDelegateImplV7.this.mActionModeView.setVisibility(0);
              AppCompatDelegateImplV7.this.mActionModeView.sendAccessibilityEvent(32);
              if (AppCompatDelegateImplV7.this.mActionModeView.getParent() != null) {
                ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV7.this.mActionModeView.getParent());
              }
            }
          });
          if (this.mActionModePopup == null) {
            break;
          }
          this.mWindow.getDecorView().post(this.mShowActionModePopup);
          break;
          localObject1 = this.mContext;
          break label216;
          localObject1 = (ViewStubCompat)this.mSubDecor.findViewById(R.id.action_mode_bar_stub);
          if (localObject1 == null) {
            break label334;
          }
          ((ViewStubCompat)localObject1).setLayoutInflater(LayoutInflater.from(getActionBarThemedContext()));
          this.mActionModeView = ((ActionBarContextView)((ViewStubCompat)localObject1).inflate());
          break label334;
          break;
        }
        label553:
        this.mActionMode = null;
      }
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Object localObject2 = localObject3;
      }
    }
  }
  
  private final class ActionMenuPresenterCallback
    implements MenuPresenter.Callback
  {
    private ActionMenuPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      AppCompatDelegateImplV7.this.checkCloseActionMenu(paramMenuBuilder);
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      Window.Callback localCallback = AppCompatDelegateImplV7.this.getWindowCallback();
      if (localCallback != null) {
        localCallback.onMenuOpened(108, paramMenuBuilder);
      }
      return true;
    }
  }
  
  class ActionModeCallbackWrapperV7
    implements ActionMode.Callback
  {
    private ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapperV7(ActionMode.Callback paramCallback)
    {
      this.mWrapped = paramCallback;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return this.mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return this.mWrapped.onCreateActionMode(paramActionMode, paramMenu);
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      this.mWrapped.onDestroyActionMode(paramActionMode);
      if (AppCompatDelegateImplV7.this.mActionModePopup != null) {
        AppCompatDelegateImplV7.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV7.this.mShowActionModePopup);
      }
      if (AppCompatDelegateImplV7.this.mActionModeView != null)
      {
        AppCompatDelegateImplV7.this.endOnGoingFadeAnimation();
        AppCompatDelegateImplV7.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImplV7.this.mActionModeView).alpha(0.0F);
        AppCompatDelegateImplV7.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter()
        {
          public void onAnimationEnd(View paramAnonymousView)
          {
            AppCompatDelegateImplV7.this.mActionModeView.setVisibility(8);
            if (AppCompatDelegateImplV7.this.mActionModePopup != null) {
              AppCompatDelegateImplV7.this.mActionModePopup.dismiss();
            }
            for (;;)
            {
              AppCompatDelegateImplV7.this.mActionModeView.removeAllViews();
              AppCompatDelegateImplV7.this.mFadeAnim.setListener(null);
              AppCompatDelegateImplV7.this.mFadeAnim = null;
              return;
              if ((AppCompatDelegateImplV7.this.mActionModeView.getParent() instanceof View)) {
                ViewCompat.requestApplyInsets((View)AppCompatDelegateImplV7.this.mActionModeView.getParent());
              }
            }
          }
        });
      }
      if (AppCompatDelegateImplV7.this.mAppCompatCallback != null) {
        AppCompatDelegateImplV7.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImplV7.this.mActionMode);
      }
      AppCompatDelegateImplV7.this.mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return this.mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
  
  private class ListMenuDecorView
    extends ContentFrameLayout
  {
    public ListMenuDecorView(Context paramContext)
    {
      super();
    }
    
    private boolean isOutOfBounds(int paramInt1, int paramInt2)
    {
      return (paramInt1 < -5) || (paramInt2 < -5) || (paramInt1 > getWidth() + 5) || (paramInt2 > getHeight() + 5);
    }
    
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
    {
      return (AppCompatDelegateImplV7.this.dispatchKeyEvent(paramKeyEvent)) || (super.dispatchKeyEvent(paramKeyEvent));
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      if ((paramMotionEvent.getAction() == 0) && (isOutOfBounds((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())))
      {
        AppCompatDelegateImplV7.this.closePanel(0);
        return true;
      }
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    
    public void setBackgroundResource(int paramInt)
    {
      setBackgroundDrawable(TintManager.getDrawable(getContext(), paramInt));
    }
  }
  
  private static final class PanelFeatureState
  {
    int background;
    View createdPanelView;
    ViewGroup decorView;
    int featureId;
    Bundle frozenActionViewState;
    Bundle frozenMenuState;
    int gravity;
    boolean isHandled;
    boolean isOpen;
    boolean isPrepared;
    ListMenuPresenter listMenuPresenter;
    Context listPresenterContext;
    MenuBuilder menu;
    public boolean qwertyMode;
    boolean refreshDecorView;
    boolean refreshMenuContent;
    View shownPanelView;
    boolean wasLastOpen;
    int windowAnimations;
    int x;
    int y;
    
    PanelFeatureState(int paramInt)
    {
      this.featureId = paramInt;
      this.refreshDecorView = false;
    }
    
    void applyFrozenState()
    {
      if ((this.menu != null) && (this.frozenMenuState != null))
      {
        this.menu.restorePresenterStates(this.frozenMenuState);
        this.frozenMenuState = null;
      }
    }
    
    public void clearMenuPresenters()
    {
      if (this.menu != null) {
        this.menu.removeMenuPresenter(this.listMenuPresenter);
      }
      this.listMenuPresenter = null;
    }
    
    MenuView getListMenuView(MenuPresenter.Callback paramCallback)
    {
      if (this.menu == null) {
        return null;
      }
      if (this.listMenuPresenter == null)
      {
        this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R.layout.abc_list_menu_item_layout);
        this.listMenuPresenter.setCallback(paramCallback);
        this.menu.addMenuPresenter(this.listMenuPresenter);
      }
      return this.listMenuPresenter.getMenuView(this.decorView);
    }
    
    public boolean hasPanelItems()
    {
      boolean bool2 = true;
      boolean bool1;
      if (this.shownPanelView == null) {
        bool1 = false;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (this.createdPanelView != null);
        bool1 = bool2;
      } while (this.listMenuPresenter.getAdapter().getCount() > 0);
      return false;
    }
    
    void onRestoreInstanceState(Parcelable paramParcelable)
    {
      paramParcelable = (SavedState)paramParcelable;
      this.featureId = paramParcelable.featureId;
      this.wasLastOpen = paramParcelable.isOpen;
      this.frozenMenuState = paramParcelable.menuState;
      this.shownPanelView = null;
      this.decorView = null;
    }
    
    Parcelable onSaveInstanceState()
    {
      SavedState localSavedState = new SavedState(null);
      localSavedState.featureId = this.featureId;
      localSavedState.isOpen = this.isOpen;
      if (this.menu != null)
      {
        localSavedState.menuState = new Bundle();
        this.menu.savePresenterStates(localSavedState.menuState);
      }
      return localSavedState;
    }
    
    void setMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == this.menu) {}
      do
      {
        return;
        if (this.menu != null) {
          this.menu.removeMenuPresenter(this.listMenuPresenter);
        }
        this.menu = paramMenuBuilder;
      } while ((paramMenuBuilder == null) || (this.listMenuPresenter == null));
      paramMenuBuilder.addMenuPresenter(this.listMenuPresenter);
    }
    
    void setStyle(Context paramContext)
    {
      TypedValue localTypedValue = new TypedValue();
      Resources.Theme localTheme = paramContext.getResources().newTheme();
      localTheme.setTo(paramContext.getTheme());
      localTheme.resolveAttribute(R.attr.actionBarPopupTheme, localTypedValue, true);
      if (localTypedValue.resourceId != 0) {
        localTheme.applyStyle(localTypedValue.resourceId, true);
      }
      localTheme.resolveAttribute(R.attr.panelMenuListTheme, localTypedValue, true);
      if (localTypedValue.resourceId != 0) {
        localTheme.applyStyle(localTypedValue.resourceId, true);
      }
      for (;;)
      {
        paramContext = new ContextThemeWrapper(paramContext, 0);
        paramContext.getTheme().setTo(localTheme);
        this.listPresenterContext = paramContext;
        paramContext = paramContext.obtainStyledAttributes(R.styleable.Theme);
        this.background = paramContext.getResourceId(R.styleable.Theme_panelBackground, 0);
        this.windowAnimations = paramContext.getResourceId(R.styleable.Theme_android_windowAnimationStyle, 0);
        paramContext.recycle();
        return;
        localTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
      }
    }
    
    private static class SavedState
      implements Parcelable
    {
      public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
      {
        public AppCompatDelegateImplV7.PanelFeatureState.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
        {
          return AppCompatDelegateImplV7.PanelFeatureState.SavedState.readFromParcel(paramAnonymousParcel, paramAnonymousClassLoader);
        }
        
        public AppCompatDelegateImplV7.PanelFeatureState.SavedState[] newArray(int paramAnonymousInt)
        {
          return new AppCompatDelegateImplV7.PanelFeatureState.SavedState[paramAnonymousInt];
        }
      });
      int featureId;
      boolean isOpen;
      Bundle menuState;
      
      private static SavedState readFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        boolean bool = true;
        SavedState localSavedState = new SavedState();
        localSavedState.featureId = paramParcel.readInt();
        if (paramParcel.readInt() == 1) {}
        for (;;)
        {
          localSavedState.isOpen = bool;
          if (localSavedState.isOpen) {
            localSavedState.menuState = paramParcel.readBundle(paramClassLoader);
          }
          return localSavedState;
          bool = false;
        }
      }
      
      public int describeContents()
      {
        return 0;
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        paramParcel.writeInt(this.featureId);
        if (this.isOpen) {}
        for (paramInt = 1;; paramInt = 0)
        {
          paramParcel.writeInt(paramInt);
          if (this.isOpen) {
            paramParcel.writeBundle(this.menuState);
          }
          return;
        }
      }
    }
  }
  
  private final class PanelMenuPresenterCallback
    implements MenuPresenter.Callback
  {
    private PanelMenuPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      MenuBuilder localMenuBuilder = paramMenuBuilder.getRootMenu();
      if (localMenuBuilder != paramMenuBuilder) {}
      for (int i = 1;; i = 0)
      {
        AppCompatDelegateImplV7 localAppCompatDelegateImplV7 = AppCompatDelegateImplV7.this;
        if (i != 0) {
          paramMenuBuilder = localMenuBuilder;
        }
        paramMenuBuilder = localAppCompatDelegateImplV7.findMenuPanel(paramMenuBuilder);
        if (paramMenuBuilder != null)
        {
          if (i == 0) {
            break;
          }
          AppCompatDelegateImplV7.this.callOnPanelClosed(paramMenuBuilder.featureId, paramMenuBuilder, localMenuBuilder);
          AppCompatDelegateImplV7.this.closePanel(paramMenuBuilder, true);
        }
        return;
      }
      AppCompatDelegateImplV7.this.closePanel(paramMenuBuilder, paramBoolean);
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if ((paramMenuBuilder == null) && (AppCompatDelegateImplV7.this.mHasActionBar))
      {
        Window.Callback localCallback = AppCompatDelegateImplV7.this.getWindowCallback();
        if ((localCallback != null) && (!AppCompatDelegateImplV7.this.isDestroyed())) {
          localCallback.onMenuOpened(108, paramMenuBuilder);
        }
      }
      return true;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/app/AppCompatDelegateImplV7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */