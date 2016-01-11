package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.R.attr;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class AppCompatSeekBar
  extends SeekBar
{
  private AppCompatSeekBarHelper mAppCompatSeekBarHelper;
  private TintManager mTintManager;
  
  public AppCompatSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.seekBarStyle);
  }
  
  public AppCompatSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mTintManager = TintManager.get(paramContext);
    this.mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(this, this.mTintManager);
    this.mAppCompatSeekBarHelper.loadFromAttributes(paramAttributeSet, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/AppCompatSeekBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */