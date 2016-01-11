package android.support.v7.view.menu;

import android.graphics.drawable.Drawable;

public abstract interface MenuView
{
  public abstract int getWindowAnimations();
  
  public abstract void initialize(MenuBuilder paramMenuBuilder);
  
  public static abstract interface ItemView
  {
    public abstract MenuItemImpl getItemData();
    
    public abstract void initialize(MenuItemImpl paramMenuItemImpl, int paramInt);
    
    public abstract boolean prefersCondensedTitle();
    
    public abstract void setCheckable(boolean paramBoolean);
    
    public abstract void setChecked(boolean paramBoolean);
    
    public abstract void setEnabled(boolean paramBoolean);
    
    public abstract void setIcon(Drawable paramDrawable);
    
    public abstract void setShortcut(boolean paramBoolean, char paramChar);
    
    public abstract void setTitle(CharSequence paramCharSequence);
    
    public abstract boolean showsIcon();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/view/menu/MenuView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */