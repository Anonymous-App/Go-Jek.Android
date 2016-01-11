package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.integer;
import android.support.v7.appcompat.R.layout;
import android.support.v7.transition.ActionBarTransition;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import java.util.ArrayList;

class ActionMenuPresenter
  extends BaseMenuPresenter
  implements ActionProvider.SubUiVisibilityListener
{
  private static final String TAG = "ActionMenuPresenter";
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  private ActionButtonSubmenu mActionButtonPopup;
  private int mActionItemWidthLimit;
  private boolean mExpandedActionViewsExclusive;
  private int mMaxItems;
  private boolean mMaxItemsSet;
  private int mMinCellSize;
  int mOpenSubMenuId;
  private OverflowMenuButton mOverflowButton;
  private OverflowPopup mOverflowPopup;
  private Drawable mPendingOverflowIcon;
  private boolean mPendingOverflowIconSet;
  private ActionMenuPopupCallback mPopupCallback;
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback(null);
  private OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)this.mMenuView;
    Object localObject;
    if (localViewGroup == null)
    {
      localObject = null;
      return (View)localObject;
    }
    int j = localViewGroup.getChildCount();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label74;
      }
      View localView = localViewGroup.getChildAt(i);
      if ((localView instanceof MenuView.ItemView))
      {
        localObject = localView;
        if (((MenuView.ItemView)localView).getItemData() == paramMenuItem) {
          break;
        }
      }
      i += 1;
    }
    label74:
    return null;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    paramItemView.initialize(paramMenuItemImpl, 0);
    paramMenuItemImpl = (ActionMenuView)this.mMenuView;
    paramItemView = (ActionMenuItemView)paramItemView;
    paramItemView.setItemInvoker(paramMenuItemImpl);
    if (this.mPopupCallback == null) {
      this.mPopupCallback = new ActionMenuPopupCallback(null);
    }
    paramItemView.setPopupCallback(this.mPopupCallback);
  }
  
  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == this.mOverflowButton) {
      return false;
    }
    return super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems()
  {
    ArrayList localArrayList = this.mMenu.getVisibleItems();
    int i5 = localArrayList.size();
    int i = this.mMaxItems;
    int i4 = this.mActionItemWidthLimit;
    int i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)this.mMenuView;
    int j = 0;
    int m = 0;
    int i2 = 0;
    int n = 0;
    int k = 0;
    int i1;
    if (k < i5)
    {
      localObject1 = (MenuItemImpl)localArrayList.get(k);
      if (((MenuItemImpl)localObject1).requiresActionButton()) {
        j += 1;
      }
      for (;;)
      {
        i1 = i;
        if (this.mExpandedActionViewsExclusive)
        {
          i1 = i;
          if (((MenuItemImpl)localObject1).isActionViewExpanded()) {
            i1 = 0;
          }
        }
        k += 1;
        i = i1;
        break;
        if (((MenuItemImpl)localObject1).requestsActionButton()) {
          m += 1;
        } else {
          n = 1;
        }
      }
    }
    k = i;
    if (this.mReserveOverflow) {
      if (n == 0)
      {
        k = i;
        if (j + m <= i) {}
      }
      else
      {
        k = i - 1;
      }
    }
    k -= j;
    Object localObject1 = this.mActionButtonGroups;
    ((SparseBooleanArray)localObject1).clear();
    int i3 = 0;
    j = 0;
    if (this.mStrictWidthLimit)
    {
      j = i4 / this.mMinCellSize;
      i = this.mMinCellSize;
      i3 = this.mMinCellSize + i4 % i / j;
    }
    i = 0;
    n = i4;
    i4 = i;
    i = i2;
    if (i4 < i5)
    {
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)localArrayList.get(i4);
      Object localObject2;
      if (localMenuItemImpl.requiresActionButton())
      {
        localObject2 = getItemView(localMenuItemImpl, this.mScrapActionButtonView, localViewGroup);
        if (this.mScrapActionButtonView == null) {
          this.mScrapActionButtonView = ((View)localObject2);
        }
        if (this.mStrictWidthLimit)
        {
          j -= ActionMenuView.measureChildForCells((View)localObject2, i3, j, i6, 0);
          label310:
          i1 = ((View)localObject2).getMeasuredWidth();
          m = n - i1;
          n = i;
          if (i == 0) {
            n = i1;
          }
          i = localMenuItemImpl.getGroupId();
          if (i != 0) {
            ((SparseBooleanArray)localObject1).put(i, true);
          }
          localMenuItemImpl.setIsActionButton(true);
          i = n;
        }
      }
      for (;;)
      {
        i4 += 1;
        n = m;
        break;
        ((View)localObject2).measure(i6, i6);
        break label310;
        if (localMenuItemImpl.requestsActionButton())
        {
          int i7 = localMenuItemImpl.getGroupId();
          boolean bool = ((SparseBooleanArray)localObject1).get(i7);
          int i8;
          label438:
          int i9;
          if (((k > 0) || (bool)) && (n > 0) && ((!this.mStrictWidthLimit) || (j > 0)))
          {
            i8 = 1;
            i2 = j;
            i1 = i;
            i9 = i8;
            m = n;
            if (i8 != 0)
            {
              localObject2 = getItemView(localMenuItemImpl, this.mScrapActionButtonView, localViewGroup);
              if (this.mScrapActionButtonView == null) {
                this.mScrapActionButtonView = ((View)localObject2);
              }
              if (!this.mStrictWidthLimit) {
                break label625;
              }
              i1 = ActionMenuView.measureChildForCells((View)localObject2, i3, j, i6, 0);
              m = j - i1;
              j = m;
              if (i1 == 0)
              {
                i8 = 0;
                j = m;
              }
              label524:
              i2 = ((View)localObject2).getMeasuredWidth();
              m = n - i2;
              i1 = i;
              if (i == 0) {
                i1 = i2;
              }
              if (!this.mStrictWidthLimit) {
                break label642;
              }
              if (m < 0) {
                break label637;
              }
              i = 1;
              label563:
              i9 = i8 & i;
              i2 = j;
            }
            if ((i9 == 0) || (i7 == 0)) {
              break label669;
            }
            ((SparseBooleanArray)localObject1).put(i7, true);
            i = k;
          }
          label625:
          label637:
          label642:
          label669:
          do
          {
            k = i;
            if (i9 != 0) {
              k = i - 1;
            }
            localMenuItemImpl.setIsActionButton(i9);
            j = i2;
            i = i1;
            break;
            i8 = 0;
            break label438;
            ((View)localObject2).measure(i6, i6);
            break label524;
            i = 0;
            break label563;
            if (m + i1 > 0) {}
            for (i = 1;; i = 0)
            {
              i9 = i8 & i;
              i2 = j;
              break;
            }
            i = k;
          } while (!bool);
          ((SparseBooleanArray)localObject1).put(i7, false);
          j = 0;
          for (;;)
          {
            i = k;
            if (j >= i4) {
              break;
            }
            localObject2 = (MenuItemImpl)localArrayList.get(j);
            i = k;
            if (((MenuItemImpl)localObject2).getGroupId() == i7)
            {
              i = k;
              if (((MenuItemImpl)localObject2).isActionButton()) {
                i = k + 1;
              }
              ((MenuItemImpl)localObject2).setIsActionButton(false);
            }
            j += 1;
            k = i;
          }
        }
        localMenuItemImpl.setIsActionButton(false);
        m = n;
      }
    }
    return true;
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramMenuItemImpl.getActionView();
    if ((localView == null) || (paramMenuItemImpl.hasCollapsibleActionView())) {
      localView = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup);
    }
    if (paramMenuItemImpl.isActionViewExpanded()) {}
    for (int i = 8;; i = 0)
    {
      localView.setVisibility(i);
      paramMenuItemImpl = (ActionMenuView)paramViewGroup;
      paramView = localView.getLayoutParams();
      if (!paramMenuItemImpl.checkLayoutParams(paramView)) {
        localView.setLayoutParams(paramMenuItemImpl.generateLayoutParams(paramView));
      }
      return localView;
    }
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.getMenuView(paramViewGroup);
    ((ActionMenuView)paramViewGroup).setPresenter(this);
    return paramViewGroup;
  }
  
  public Drawable getOverflowIcon()
  {
    if (this.mOverflowButton != null) {
      return this.mOverflowButton.getDrawable();
    }
    if (this.mPendingOverflowIconSet) {
      return this.mPendingOverflowIcon;
    }
    return null;
  }
  
  public boolean hideOverflowMenu()
  {
    if ((this.mPostedOpenRunnable != null) && (this.mMenuView != null))
    {
      ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
      this.mPostedOpenRunnable = null;
      return true;
    }
    OverflowPopup localOverflowPopup = this.mOverflowPopup;
    if (localOverflowPopup != null)
    {
      localOverflowPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public boolean hideSubMenus()
  {
    if (this.mActionButtonPopup != null)
    {
      this.mActionButtonPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    super.initForMenu(paramContext, paramMenuBuilder);
    paramMenuBuilder = paramContext.getResources();
    paramContext = ActionBarPolicy.get(paramContext);
    if (!this.mReserveOverflowSet) {
      this.mReserveOverflow = paramContext.showsOverflowMenuButton();
    }
    if (!this.mWidthLimitSet) {
      this.mWidthLimit = paramContext.getEmbeddedMenuWidthLimit();
    }
    if (!this.mMaxItemsSet) {
      this.mMaxItems = paramContext.getMaxActionButtons();
    }
    int i = this.mWidthLimit;
    if (this.mReserveOverflow)
    {
      if (this.mOverflowButton == null)
      {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
        if (this.mPendingOverflowIconSet)
        {
          this.mOverflowButton.setImageDrawable(this.mPendingOverflowIcon);
          this.mPendingOverflowIcon = null;
          this.mPendingOverflowIconSet = false;
        }
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mOverflowButton.measure(j, j);
      }
      i -= this.mOverflowButton.getMeasuredWidth();
    }
    for (;;)
    {
      this.mActionItemWidthLimit = i;
      this.mMinCellSize = ((int)(56.0F * paramMenuBuilder.getDisplayMetrics().density));
      this.mScrapActionButtonView = null;
      return;
      this.mOverflowButton = null;
    }
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (this.mPostedOpenRunnable != null) || (isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (this.mOverflowPopup != null) && (this.mOverflowPopup.isShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return this.mReserveOverflow;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!this.mMaxItemsSet) {
      this.mMaxItems = this.mContext.getResources().getInteger(R.integer.abc_max_action_buttons);
    }
    if (this.mMenu != null) {
      this.mMenu.onItemsChanged(true);
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    if (paramParcelable.openSubMenuId > 0)
    {
      paramParcelable = this.mMenu.findItem(paramParcelable.openSubMenuId);
      if (paramParcelable != null) {
        onSubMenuSelected((SubMenuBuilder)paramParcelable.getSubMenu());
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState();
    localSavedState.openSubMenuId = this.mOpenSubMenuId;
    return localSavedState;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    if (!paramSubMenuBuilder.hasVisibleItems()) {}
    do
    {
      return false;
      for (localObject = paramSubMenuBuilder; ((SubMenuBuilder)localObject).getParentMenu() != this.mMenu; localObject = (SubMenuBuilder)((SubMenuBuilder)localObject).getParentMenu()) {}
      View localView = findViewForItem(((SubMenuBuilder)localObject).getItem());
      localObject = localView;
      if (localView != null) {
        break;
      }
    } while (this.mOverflowButton == null);
    Object localObject = this.mOverflowButton;
    this.mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
    this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, paramSubMenuBuilder);
    this.mActionButtonPopup.setAnchorView((View)localObject);
    this.mActionButtonPopup.show();
    super.onSubMenuSelected(paramSubMenuBuilder);
    return true;
  }
  
  public void onSubUiVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      super.onSubMenuSelected(null);
      return;
    }
    this.mMenu.close(false);
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    this.mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setItemLimit(int paramInt)
  {
    this.mMaxItems = paramInt;
    this.mMaxItemsSet = true;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView)
  {
    this.mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(this.mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    if (this.mOverflowButton != null)
    {
      this.mOverflowButton.setImageDrawable(paramDrawable);
      return;
    }
    this.mPendingOverflowIconSet = true;
    this.mPendingOverflowIcon = paramDrawable;
  }
  
  public void setReserveOverflow(boolean paramBoolean)
  {
    this.mReserveOverflow = paramBoolean;
    this.mReserveOverflowSet = true;
  }
  
  public void setWidthLimit(int paramInt, boolean paramBoolean)
  {
    this.mWidthLimit = paramInt;
    this.mStrictWidthLimit = paramBoolean;
    this.mWidthLimitSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu()
  {
    if ((this.mReserveOverflow) && (!isOverflowMenuShowing()) && (this.mMenu != null) && (this.mMenuView != null) && (this.mPostedOpenRunnable == null) && (!this.mMenu.getNonActionItems().isEmpty()))
    {
      this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
      ((View)this.mMenuView).post(this.mPostedOpenRunnable);
      super.onSubMenuSelected(null);
      return true;
    }
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    Object localObject = (ViewGroup)((View)this.mMenuView).getParent();
    if (localObject != null) {
      ActionBarTransition.beginDelayedTransition((ViewGroup)localObject);
    }
    super.updateMenuView(paramBoolean);
    ((View)this.mMenuView).requestLayout();
    int j;
    int i;
    if (this.mMenu != null)
    {
      localObject = this.mMenu.getActionItems();
      j = ((ArrayList)localObject).size();
      i = 0;
      while (i < j)
      {
        ActionProvider localActionProvider = ((MenuItemImpl)((ArrayList)localObject).get(i)).getSupportActionProvider();
        if (localActionProvider != null) {
          localActionProvider.setSubUiVisibilityListener(this);
        }
        i += 1;
      }
    }
    if (this.mMenu != null)
    {
      localObject = this.mMenu.getNonActionItems();
      j = 0;
      i = j;
      if (this.mReserveOverflow)
      {
        i = j;
        if (localObject != null)
        {
          i = ((ArrayList)localObject).size();
          if (i != 1) {
            break label274;
          }
          if (((MenuItemImpl)((ArrayList)localObject).get(0)).isActionViewExpanded()) {
            break label269;
          }
          i = 1;
        }
      }
      label163:
      if (i == 0) {
        break label288;
      }
      if (this.mOverflowButton == null) {
        this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
      }
      localObject = (ViewGroup)this.mOverflowButton.getParent();
      if (localObject != this.mMenuView)
      {
        if (localObject != null) {
          ((ViewGroup)localObject).removeView(this.mOverflowButton);
        }
        localObject = (ActionMenuView)this.mMenuView;
        ((ActionMenuView)localObject).addView(this.mOverflowButton, ((ActionMenuView)localObject).generateOverflowButtonLayoutParams());
      }
    }
    for (;;)
    {
      ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
      return;
      localObject = null;
      break;
      label269:
      i = 0;
      break label163;
      label274:
      if (i > 0) {}
      for (i = 1;; i = 0) {
        break;
      }
      label288:
      if ((this.mOverflowButton != null) && (this.mOverflowButton.getParent() == this.mMenuView)) {
        ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
      }
    }
  }
  
  private class ActionButtonSubmenu
    extends MenuPopupHelper
  {
    private SubMenuBuilder mSubMenu;
    
    public ActionButtonSubmenu(Context paramContext, SubMenuBuilder paramSubMenuBuilder)
    {
      super(paramSubMenuBuilder, null, false, R.attr.actionOverflowMenuStyle);
      this.mSubMenu = paramSubMenuBuilder;
      boolean bool2;
      int j;
      int i;
      if (!((MenuItemImpl)paramSubMenuBuilder.getItem()).isActionButton())
      {
        if (ActionMenuPresenter.this.mOverflowButton == null)
        {
          paramContext = (View)ActionMenuPresenter.this.mMenuView;
          setAnchorView(paramContext);
        }
      }
      else
      {
        setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        bool2 = false;
        j = paramSubMenuBuilder.size();
        i = 0;
      }
      for (;;)
      {
        boolean bool1 = bool2;
        if (i < j)
        {
          this$1 = paramSubMenuBuilder.getItem(i);
          if ((ActionMenuPresenter.this.isVisible()) && (ActionMenuPresenter.this.getIcon() != null)) {
            bool1 = true;
          }
        }
        else
        {
          setForceShowIcon(bool1);
          return;
          paramContext = ActionMenuPresenter.this.mOverflowButton;
          break;
        }
        i += 1;
      }
    }
    
    public void onDismiss()
    {
      super.onDismiss();
      ActionMenuPresenter.access$802(ActionMenuPresenter.this, null);
      ActionMenuPresenter.this.mOpenSubMenuId = 0;
    }
  }
  
  private class ActionMenuPopupCallback
    extends ActionMenuItemView.PopupCallback
  {
    private ActionMenuPopupCallback() {}
    
    public ListPopupWindow getPopup()
    {
      if (ActionMenuPresenter.this.mActionButtonPopup != null) {
        return ActionMenuPresenter.this.mActionButtonPopup.getPopup();
      }
      return null;
    }
  }
  
  private class OpenOverflowRunnable
    implements Runnable
  {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup paramOverflowPopup)
    {
      this.mPopup = paramOverflowPopup;
    }
    
    public void run()
    {
      ActionMenuPresenter.this.mMenu.changeMenuMode();
      View localView = (View)ActionMenuPresenter.this.mMenuView;
      if ((localView != null) && (localView.getWindowToken() != null) && (this.mPopup.tryShow())) {
        ActionMenuPresenter.access$202(ActionMenuPresenter.this, this.mPopup);
      }
      ActionMenuPresenter.access$302(ActionMenuPresenter.this, null);
    }
  }
  
  private class OverflowMenuButton
    extends AppCompatImageView
    implements ActionMenuView.ActionMenuChildView
  {
    private final float[] mTempPts = new float[2];
    
    public OverflowMenuButton(Context paramContext)
    {
      super(null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      setOnTouchListener(new ListPopupWindow.ForwardingListener(this)
      {
        public ListPopupWindow getPopup()
        {
          if (ActionMenuPresenter.this.mOverflowPopup == null) {
            return null;
          }
          return ActionMenuPresenter.this.mOverflowPopup.getPopup();
        }
        
        public boolean onForwardingStarted()
        {
          ActionMenuPresenter.this.showOverflowMenu();
          return true;
        }
        
        public boolean onForwardingStopped()
        {
          if (ActionMenuPresenter.this.mPostedOpenRunnable != null) {
            return false;
          }
          ActionMenuPresenter.this.hideOverflowMenu();
          return true;
        }
      });
    }
    
    public boolean needsDividerAfter()
    {
      return false;
    }
    
    public boolean needsDividerBefore()
    {
      return false;
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      ActionMenuPresenter.this.showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
      Drawable localDrawable1 = getDrawable();
      Drawable localDrawable2 = getBackground();
      if ((localDrawable1 != null) && (localDrawable2 != null))
      {
        int i = getWidth();
        paramInt2 = getHeight();
        paramInt1 = Math.max(i, paramInt2) / 2;
        int j = getPaddingLeft();
        int k = getPaddingRight();
        paramInt3 = getPaddingTop();
        paramInt4 = getPaddingBottom();
        i = (i + (j - k)) / 2;
        paramInt2 = (paramInt2 + (paramInt3 - paramInt4)) / 2;
        DrawableCompat.setHotspotBounds(localDrawable2, i - paramInt1, paramInt2 - paramInt1, i + paramInt1, paramInt2 + paramInt1);
      }
      return bool;
    }
  }
  
  private class OverflowPopup
    extends MenuPopupHelper
  {
    public OverflowPopup(Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean)
    {
      super(paramMenuBuilder, paramView, paramBoolean, R.attr.actionOverflowMenuStyle);
      setGravity(8388613);
      setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
    }
    
    public void onDismiss()
    {
      super.onDismiss();
      if (ActionMenuPresenter.this.mMenu != null) {
        ActionMenuPresenter.this.mMenu.close();
      }
      ActionMenuPresenter.access$202(ActionMenuPresenter.this, null);
    }
  }
  
  private class PopupPresenterCallback
    implements MenuPresenter.Callback
  {
    private PopupPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      if ((paramMenuBuilder instanceof SubMenuBuilder)) {
        ((SubMenuBuilder)paramMenuBuilder).getRootMenu().close(false);
      }
      MenuPresenter.Callback localCallback = ActionMenuPresenter.this.getCallback();
      if (localCallback != null) {
        localCallback.onCloseMenu(paramMenuBuilder, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null) {
        return false;
      }
      ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)paramMenuBuilder).getItem().getItemId();
      MenuPresenter.Callback localCallback = ActionMenuPresenter.this.getCallback();
      if (localCallback != null) {}
      for (boolean bool = localCallback.onOpenSubMenu(paramMenuBuilder);; bool = false) {
        return bool;
      }
    }
  }
  
  private static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public ActionMenuPresenter.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ActionMenuPresenter.SavedState(paramAnonymousParcel);
      }
      
      public ActionMenuPresenter.SavedState[] newArray(int paramAnonymousInt)
      {
        return new ActionMenuPresenter.SavedState[paramAnonymousInt];
      }
    };
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState(Parcel paramParcel)
    {
      this.openSubMenuId = paramParcel.readInt();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.openSubMenuId);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/ActionMenuPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */