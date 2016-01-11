package com.gojek.app.food;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;

public class FoodTabListener
  implements ActionBar.TabListener
{
  private FoodFragmentBase fragment;
  
  public FoodTabListener(FoodFragmentBase paramFoodFragmentBase)
  {
    this.fragment = paramFoodFragmentBase;
  }
  
  public void onTabReselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction) {}
  
  public void onTabSelected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction)
  {
    paramFragmentTransaction.replace(2131624460, this.fragment);
  }
  
  public void onTabUnselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction)
  {
    paramFragmentTransaction.remove(this.fragment);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/food/FoodTabListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */