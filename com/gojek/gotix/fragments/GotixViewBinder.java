package com.gojek.gotix.fragments;

import android.view.View;

public abstract interface GotixViewBinder
{
  public abstract void addClickListeners();
  
  public abstract void bindViewById(View paramView);
  
  public abstract void setFontFace();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixViewBinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */