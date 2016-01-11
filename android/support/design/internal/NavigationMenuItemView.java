package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.StateListDrawable;
import android.support.design.R.attr;
import android.support.design.R.dimen;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView.ItemView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;

public class NavigationMenuItemView
  extends ForegroundLinearLayout
  implements MenuView.ItemView
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private FrameLayout mActionArea;
  private final int mIconSize;
  private ColorStateList mIconTintList;
  private MenuItemImpl mItemData;
  private final CheckedTextView mTextView;
  
  public NavigationMenuItemView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NavigationMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NavigationMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setOrientation(0);
    LayoutInflater.from(paramContext).inflate(R.layout.design_navigation_menu_item, this, true);
    this.mIconSize = paramContext.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size);
    this.mTextView = ((CheckedTextView)findViewById(R.id.design_menu_item_text));
    this.mTextView.setDuplicateParentStateEnabled(true);
  }
  
  private StateListDrawable createDefaultBackground()
  {
    TypedValue localTypedValue = new TypedValue();
    if (getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, localTypedValue, true))
    {
      StateListDrawable localStateListDrawable = new StateListDrawable();
      localStateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(localTypedValue.data));
      localStateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
      return localStateListDrawable;
    }
    return null;
  }
  
  private void setActionView(View paramView)
  {
    if (this.mActionArea == null) {
      this.mActionArea = ((FrameLayout)((ViewStub)findViewById(R.id.design_menu_item_action_area_stub)).inflate());
    }
    this.mActionArea.removeAllViews();
    if (paramView != null) {
      this.mActionArea.addView(paramView);
    }
  }
  
  public MenuItemImpl getItemData()
  {
    return this.mItemData;
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt)
  {
    this.mItemData = paramMenuItemImpl;
    if (paramMenuItemImpl.isVisible()) {}
    for (paramInt = 0;; paramInt = 8)
    {
      setVisibility(paramInt);
      if (getBackground() == null) {
        setBackgroundDrawable(createDefaultBackground());
      }
      setCheckable(paramMenuItemImpl.isCheckable());
      setChecked(paramMenuItemImpl.isChecked());
      setEnabled(paramMenuItemImpl.isEnabled());
      setTitle(paramMenuItemImpl.getTitle());
      setIcon(paramMenuItemImpl.getIcon());
      setActionView(paramMenuItemImpl.getActionView());
      return;
    }
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if ((this.mItemData != null) && (this.mItemData.isCheckable()) && (this.mItemData.isChecked())) {
      mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    }
    return arrayOfInt;
  }
  
  public boolean prefersCondensedTitle()
  {
    return false;
  }
  
  public void recycle()
  {
    if (this.mActionArea != null) {
      this.mActionArea.removeAllViews();
    }
    this.mTextView.setCompoundDrawables(null, null, null, null);
  }
  
  public void setCheckable(boolean paramBoolean)
  {
    refreshDrawableState();
  }
  
  public void setChecked(boolean paramBoolean)
  {
    refreshDrawableState();
    this.mTextView.setChecked(paramBoolean);
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    Object localObject = paramDrawable;
    if (paramDrawable != null)
    {
      localObject = paramDrawable.getConstantState();
      if (localObject != null) {
        break label57;
      }
    }
    for (;;)
    {
      localObject = DrawableCompat.wrap(paramDrawable).mutate();
      ((Drawable)localObject).setBounds(0, 0, this.mIconSize, this.mIconSize);
      DrawableCompat.setTintList((Drawable)localObject, this.mIconTintList);
      TextViewCompat.setCompoundDrawablesRelative(this.mTextView, (Drawable)localObject, null, null, null);
      return;
      label57:
      paramDrawable = ((Drawable.ConstantState)localObject).newDrawable();
    }
  }
  
  void setIconTintList(ColorStateList paramColorStateList)
  {
    this.mIconTintList = paramColorStateList;
    if (this.mItemData != null) {
      setIcon(this.mItemData.getIcon());
    }
  }
  
  public void setShortcut(boolean paramBoolean, char paramChar) {}
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    this.mTextView.setTextAppearance(paramContext, paramInt);
  }
  
  public void setTextColor(ColorStateList paramColorStateList)
  {
    this.mTextView.setTextColor(paramColorStateList);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    this.mTextView.setText(paramCharSequence);
  }
  
  public boolean showsIcon()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/internal/NavigationMenuItemView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */