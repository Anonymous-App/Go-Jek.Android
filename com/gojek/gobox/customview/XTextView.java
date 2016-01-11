package com.gojek.gobox.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.TextView;
import com.gojek.gobox.R.styleable;

public class XTextView
  extends TextView
{
  private AttributeSet attrs;
  
  public XTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.attrs = paramAttributeSet;
    init();
  }
  
  private void init()
  {
    Object localObject = getContext().obtainStyledAttributes(this.attrs, R.styleable.XTextView);
    int i = ((TypedArray)localObject).getInt(R.styleable.XTextView_fontFamily, 1);
    int j = ((TypedArray)localObject).getInt(R.styleable.XTextView_fontStyle, 1);
    localObject = null;
    switch (i)
    {
    }
    for (;;)
    {
      localObject = Typeface.createFromAsset(getContext().getAssets(), (String)localObject);
      if ((Build.VERSION.SDK_INT < 11) || (Build.VERSION.SDK_INT > 13)) {
        setTypeface((Typeface)localObject);
      }
      return;
      switch (j)
      {
      default: 
        break;
      case 0: 
        localObject = "fonts/BebasNeue Bold.ttf";
        break;
      case 1: 
        localObject = "fonts/BebasNeue Regular.ttf";
        continue;
        switch (j)
        {
        default: 
          break;
        case 0: 
          localObject = "fonts/lato-bold.ttf";
          break;
        case 1: 
          localObject = "fonts/lato-regular.ttf";
          continue;
          localObject = "fonts/us101.TTF";
        }
        break;
      }
    }
  }
  
  public boolean isInEditMode()
  {
    return true;
  }
  
  public void setText(int paramInt1, int paramInt2)
  {
    Object localObject = null;
    switch (paramInt1)
    {
    }
    for (;;)
    {
      localObject = Typeface.createFromAsset(getContext().getAssets(), (String)localObject);
      if ((Build.VERSION.SDK_INT < 11) || (Build.VERSION.SDK_INT > 13)) {
        setTypeface((Typeface)localObject);
      }
      return;
      switch (paramInt2)
      {
      default: 
        break;
      case 0: 
        localObject = "fonts/BebasNeue Bold.ttf";
        break;
      case 1: 
        localObject = "fonts/BebasNeue Regular.ttf";
        continue;
        switch (paramInt2)
        {
        default: 
          break;
        case 0: 
          localObject = "fonts/lato-bold.ttf";
          break;
        case 1: 
          localObject = "fonts/lato-regular.ttf";
          continue;
          localObject = "fonts/us101.TTF";
        }
        break;
      }
    }
  }
  
  public static class XTextViewAttr
  {
    public static final int bebas = 2;
    public static final int bold = 0;
    public static final int lato = 1;
    public static final int regular = 1;
    public static final int us = 3;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/customview/XTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */