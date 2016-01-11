package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.common.internal.p;
import com.google.android.gms.common.internal.q;
import com.google.android.gms.dynamic.g.a;

public final class SignInButton
  extends FrameLayout
  implements View.OnClickListener
{
  public static final int COLOR_DARK = 0;
  public static final int COLOR_LIGHT = 1;
  public static final int SIZE_ICON_ONLY = 2;
  public static final int SIZE_STANDARD = 0;
  public static final int SIZE_WIDE = 1;
  private View Im;
  private View.OnClickListener In = null;
  private int mColor;
  private int mSize;
  
  public SignInButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setStyle(0, 0);
  }
  
  private void G(Context paramContext)
  {
    if (this.Im != null) {
      removeView(this.Im);
    }
    try
    {
      this.Im = p.b(paramContext, this.mSize, this.mColor);
      addView(this.Im);
      this.Im.setEnabled(isEnabled());
      this.Im.setOnClickListener(this);
      return;
    }
    catch (g.a locala)
    {
      for (;;)
      {
        Log.w("SignInButton", "Sign in button not found, using placeholder instead");
        this.Im = a(paramContext, this.mSize, this.mColor);
      }
    }
  }
  
  private static Button a(Context paramContext, int paramInt1, int paramInt2)
  {
    q localq = new q(paramContext);
    localq.a(paramContext.getResources(), paramInt1, paramInt2);
    return localq;
  }
  
  public void onClick(View paramView)
  {
    if ((this.In != null) && (paramView == this.Im)) {
      this.In.onClick(this);
    }
  }
  
  public void setColorScheme(int paramInt)
  {
    setStyle(this.mSize, paramInt);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.Im.setEnabled(paramBoolean);
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.In = paramOnClickListener;
    if (this.Im != null) {
      this.Im.setOnClickListener(this);
    }
  }
  
  public void setSize(int paramInt)
  {
    setStyle(paramInt, this.mColor);
  }
  
  public void setStyle(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 < 3))
    {
      bool = true;
      o.a(bool, "Unknown button size %d", new Object[] { Integer.valueOf(paramInt1) });
      if ((paramInt2 < 0) || (paramInt2 >= 2)) {
        break label80;
      }
    }
    label80:
    for (boolean bool = true;; bool = false)
    {
      o.a(bool, "Unknown color scheme %s", new Object[] { Integer.valueOf(paramInt2) });
      this.mSize = paramInt1;
      this.mColor = paramInt2;
      G(getContext());
      return;
      bool = false;
      break;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/common/SignInButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */