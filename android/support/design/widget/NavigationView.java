package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.R.attr;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;

public class NavigationView
  extends ScrimInsetsFrameLayout
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int[] DISABLED_STATE_SET = { -16842910 };
  private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
  private OnNavigationItemSelectedListener mListener;
  private int mMaxWidth;
  private final NavigationMenu mMenu;
  private MenuInflater mMenuInflater;
  private final NavigationMenuPresenter mPresenter = new NavigationMenuPresenter();
  
  public NavigationView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NavigationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NavigationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    this.mMenu = new NavigationMenu(paramContext);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.NavigationView, paramInt, R.style.Widget_Design_NavigationView);
    setBackgroundDrawable(localTypedArray.getDrawable(R.styleable.NavigationView_android_background));
    if (localTypedArray.hasValue(R.styleable.NavigationView_elevation)) {
      ViewCompat.setElevation(this, localTypedArray.getDimensionPixelSize(R.styleable.NavigationView_elevation, 0));
    }
    ViewCompat.setFitsSystemWindows(this, localTypedArray.getBoolean(R.styleable.NavigationView_android_fitsSystemWindows, false));
    this.mMaxWidth = localTypedArray.getDimensionPixelSize(R.styleable.NavigationView_android_maxWidth, 0);
    if (localTypedArray.hasValue(R.styleable.NavigationView_itemIconTint)) {}
    for (ColorStateList localColorStateList = localTypedArray.getColorStateList(R.styleable.NavigationView_itemIconTint);; localColorStateList = createDefaultColorStateList(16842808))
    {
      int i = 0;
      paramInt = 0;
      if (localTypedArray.hasValue(R.styleable.NavigationView_itemTextAppearance))
      {
        paramInt = localTypedArray.getResourceId(R.styleable.NavigationView_itemTextAppearance, 0);
        i = 1;
      }
      paramAttributeSet = null;
      if (localTypedArray.hasValue(R.styleable.NavigationView_itemTextColor)) {
        paramAttributeSet = localTypedArray.getColorStateList(R.styleable.NavigationView_itemTextColor);
      }
      Object localObject = paramAttributeSet;
      if (i == 0)
      {
        localObject = paramAttributeSet;
        if (paramAttributeSet == null) {
          localObject = createDefaultColorStateList(16842806);
        }
      }
      paramAttributeSet = localTypedArray.getDrawable(R.styleable.NavigationView_itemBackground);
      this.mMenu.setCallback(new MenuBuilder.Callback()
      {
        public boolean onMenuItemSelected(MenuBuilder paramAnonymousMenuBuilder, MenuItem paramAnonymousMenuItem)
        {
          return (NavigationView.this.mListener != null) && (NavigationView.this.mListener.onNavigationItemSelected(paramAnonymousMenuItem));
        }
        
        public void onMenuModeChange(MenuBuilder paramAnonymousMenuBuilder) {}
      });
      this.mPresenter.setId(1);
      this.mPresenter.initForMenu(paramContext, this.mMenu);
      this.mPresenter.setItemIconTintList(localColorStateList);
      if (i != 0) {
        this.mPresenter.setItemTextAppearance(paramInt);
      }
      this.mPresenter.setItemTextColor((ColorStateList)localObject);
      this.mPresenter.setItemBackground(paramAttributeSet);
      this.mMenu.addMenuPresenter(this.mPresenter);
      addView((View)this.mPresenter.getMenuView(this));
      if (localTypedArray.hasValue(R.styleable.NavigationView_menu)) {
        inflateMenu(localTypedArray.getResourceId(R.styleable.NavigationView_menu, 0));
      }
      if (localTypedArray.hasValue(R.styleable.NavigationView_headerLayout)) {
        inflateHeaderView(localTypedArray.getResourceId(R.styleable.NavigationView_headerLayout, 0));
      }
      localTypedArray.recycle();
      return;
    }
  }
  
  private ColorStateList createDefaultColorStateList(int paramInt)
  {
    Object localObject = new TypedValue();
    if (!getContext().getTheme().resolveAttribute(paramInt, (TypedValue)localObject, true)) {}
    ColorStateList localColorStateList;
    do
    {
      return null;
      localColorStateList = getResources().getColorStateList(((TypedValue)localObject).resourceId);
    } while (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, (TypedValue)localObject, true));
    paramInt = ((TypedValue)localObject).data;
    int i = localColorStateList.getDefaultColor();
    localObject = DISABLED_STATE_SET;
    int[] arrayOfInt1 = CHECKED_STATE_SET;
    int[] arrayOfInt2 = EMPTY_STATE_SET;
    int j = localColorStateList.getColorForState(DISABLED_STATE_SET, i);
    return new ColorStateList(new int[][] { localObject, arrayOfInt1, arrayOfInt2 }, new int[] { j, paramInt, i });
  }
  
  private MenuInflater getMenuInflater()
  {
    if (this.mMenuInflater == null) {
      this.mMenuInflater = new SupportMenuInflater(getContext());
    }
    return this.mMenuInflater;
  }
  
  public void addHeaderView(@NonNull View paramView)
  {
    this.mPresenter.addHeaderView(paramView);
  }
  
  public int getHeaderCount()
  {
    return this.mPresenter.getHeaderCount();
  }
  
  public View getHeaderView(int paramInt)
  {
    return this.mPresenter.getHeaderView(paramInt);
  }
  
  public Drawable getItemBackground()
  {
    return this.mPresenter.getItemBackground();
  }
  
  @Nullable
  public ColorStateList getItemIconTintList()
  {
    return this.mPresenter.getItemTintList();
  }
  
  @Nullable
  public ColorStateList getItemTextColor()
  {
    return this.mPresenter.getItemTextColor();
  }
  
  public Menu getMenu()
  {
    return this.mMenu;
  }
  
  public View inflateHeaderView(@LayoutRes int paramInt)
  {
    return this.mPresenter.inflateHeaderView(paramInt);
  }
  
  public void inflateMenu(int paramInt)
  {
    this.mPresenter.setUpdateSuspended(true);
    getMenuInflater().inflate(paramInt, this.mMenu);
    this.mPresenter.setUpdateSuspended(false);
    this.mPresenter.updateMenuView(false);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    switch (View.MeasureSpec.getMode(paramInt1))
    {
    default: 
      i = paramInt1;
    }
    for (;;)
    {
      super.onMeasure(i, paramInt2);
      return;
      i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(paramInt1), this.mMaxWidth), 1073741824);
      continue;
      i = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.mMenu.restorePresenterStates(paramParcelable.menuState);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.menuState = new Bundle();
    this.mMenu.savePresenterStates(localSavedState.menuState);
    return localSavedState;
  }
  
  public void removeHeaderView(@NonNull View paramView)
  {
    this.mPresenter.removeHeaderView(paramView);
  }
  
  public void setCheckedItem(@IdRes int paramInt)
  {
    MenuItem localMenuItem = this.mMenu.findItem(paramInt);
    if (localMenuItem != null) {
      this.mPresenter.setCheckedItem((MenuItemImpl)localMenuItem);
    }
  }
  
  public void setItemBackground(Drawable paramDrawable)
  {
    this.mPresenter.setItemBackground(paramDrawable);
  }
  
  public void setItemBackgroundResource(@DrawableRes int paramInt)
  {
    setItemBackground(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList)
  {
    this.mPresenter.setItemIconTintList(paramColorStateList);
  }
  
  public void setItemTextAppearance(@StyleRes int paramInt)
  {
    this.mPresenter.setItemTextAppearance(paramInt);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList)
  {
    this.mPresenter.setItemTextColor(paramColorStateList);
  }
  
  public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener paramOnNavigationItemSelectedListener)
  {
    this.mListener = paramOnNavigationItemSelectedListener;
  }
  
  public static abstract interface OnNavigationItemSelectedListener
  {
    public abstract boolean onNavigationItemSelected(MenuItem paramMenuItem);
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
    {
      public NavigationView.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new NavigationView.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public NavigationView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new NavigationView.SavedState[paramAnonymousInt];
      }
    });
    public Bundle menuState;
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super();
      this.menuState = paramParcel.readBundle(paramClassLoader);
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(@NonNull Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeBundle(this.menuState);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/NavigationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */