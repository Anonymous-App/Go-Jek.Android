package com.gojek.gobox.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.google.android.gms.maps.SupportMapFragment;

public class CustomMapFragment
  extends SupportMapFragment
{
  private OnTouchListener mListener;
  
  static
  {
    if (!CustomMapFragment.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    paramViewGroup = new TouchableWrapper(getActivity());
    paramViewGroup.setBackgroundColor(getResources().getColor(17170445));
    assert ((ViewGroup)paramLayoutInflater != null);
    ((ViewGroup)paramLayoutInflater).addView(paramViewGroup, new ViewGroup.LayoutParams(-1, -1));
    return paramLayoutInflater;
  }
  
  public void setListener(OnTouchListener paramOnTouchListener)
  {
    this.mListener = paramOnTouchListener;
  }
  
  public static abstract interface OnTouchListener
  {
    public abstract void onTouch();
  }
  
  public class TouchableWrapper
    extends FrameLayout
  {
    public TouchableWrapper(Context paramContext)
    {
      super();
    }
    
    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
      switch (paramMotionEvent.getAction())
      {
      }
      for (;;)
      {
        return super.dispatchTouchEvent(paramMotionEvent);
        CustomMapFragment.this.mListener.onTouch();
        continue;
        CustomMapFragment.this.mListener.onTouch();
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/CustomMapFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */