package com.google.android.gms.internal;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;

@ez
public final class dp
  extends FrameLayout
  implements View.OnClickListener
{
  private final Activity nr;
  private final ImageButton sg;
  
  public dp(Activity paramActivity, int paramInt)
  {
    super(paramActivity);
    this.nr = paramActivity;
    setOnClickListener(this);
    this.sg = new ImageButton(paramActivity);
    this.sg.setImageResource(17301527);
    this.sg.setBackgroundColor(0);
    this.sg.setOnClickListener(this);
    this.sg.setPadding(0, 0, 0, 0);
    this.sg.setContentDescription("Interstitial close button");
    paramInt = gr.a(paramActivity, paramInt);
    addView(this.sg, new FrameLayout.LayoutParams(paramInt, paramInt, 17));
  }
  
  public void o(boolean paramBoolean)
  {
    ImageButton localImageButton = this.sg;
    if (paramBoolean) {}
    for (int i = 4;; i = 0)
    {
      localImageButton.setVisibility(i);
      return;
    }
  }
  
  public void onClick(View paramView)
  {
    this.nr.finish();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/dp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */