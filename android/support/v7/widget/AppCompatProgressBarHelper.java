package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class AppCompatProgressBarHelper
{
  private static final int[] TINT_ATTRS = { 16843067, 16843068 };
  private Bitmap mSampleTile;
  final TintManager mTintManager;
  private final ProgressBar mView;
  
  AppCompatProgressBarHelper(ProgressBar paramProgressBar, TintManager paramTintManager)
  {
    this.mView = paramProgressBar;
    this.mTintManager = paramTintManager;
  }
  
  private Shape getDrawableShape()
  {
    return new RoundRectShape(new float[] { 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F }, null, null);
  }
  
  private Drawable tileify(Drawable paramDrawable, boolean paramBoolean)
  {
    if ((paramDrawable instanceof DrawableWrapper))
    {
      localObject = ((DrawableWrapper)paramDrawable).getWrappedDrawable();
      if (localObject != null)
      {
        localObject = tileify((Drawable)localObject, paramBoolean);
        ((DrawableWrapper)paramDrawable).setWrappedDrawable((Drawable)localObject);
      }
    }
    do
    {
      return paramDrawable;
      if ((paramDrawable instanceof LayerDrawable))
      {
        LayerDrawable localLayerDrawable = (LayerDrawable)paramDrawable;
        int j = localLayerDrawable.getNumberOfLayers();
        paramDrawable = new Drawable[j];
        int i = 0;
        if (i < j)
        {
          int k = localLayerDrawable.getId(i);
          localObject = localLayerDrawable.getDrawable(i);
          if ((k == 16908301) || (k == 16908303)) {}
          for (paramBoolean = true;; paramBoolean = false)
          {
            paramDrawable[i] = tileify((Drawable)localObject, paramBoolean);
            i += 1;
            break;
          }
        }
        localObject = new LayerDrawable(paramDrawable);
        i = 0;
        for (;;)
        {
          paramDrawable = (Drawable)localObject;
          if (i >= j) {
            break;
          }
          ((LayerDrawable)localObject).setId(i, localLayerDrawable.getId(i));
          i += 1;
        }
      }
    } while (!(paramDrawable instanceof BitmapDrawable));
    paramDrawable = ((BitmapDrawable)paramDrawable).getBitmap();
    if (this.mSampleTile == null) {
      this.mSampleTile = paramDrawable;
    }
    Object localObject = new ShapeDrawable(getDrawableShape());
    paramDrawable = new BitmapShader(paramDrawable, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
    ((ShapeDrawable)localObject).getPaint().setShader(paramDrawable);
    paramDrawable = (Drawable)localObject;
    if (paramBoolean) {
      paramDrawable = new ClipDrawable((Drawable)localObject, 3, 1);
    }
    return paramDrawable;
  }
  
  private Drawable tileifyIndeterminate(Drawable paramDrawable)
  {
    Object localObject = paramDrawable;
    if ((paramDrawable instanceof AnimationDrawable))
    {
      paramDrawable = (AnimationDrawable)paramDrawable;
      int j = paramDrawable.getNumberOfFrames();
      localObject = new AnimationDrawable();
      ((AnimationDrawable)localObject).setOneShot(paramDrawable.isOneShot());
      int i = 0;
      while (i < j)
      {
        Drawable localDrawable = tileify(paramDrawable.getFrame(i), true);
        localDrawable.setLevel(10000);
        ((AnimationDrawable)localObject).addFrame(localDrawable, paramDrawable.getDuration(i));
        i += 1;
      }
      ((AnimationDrawable)localObject).setLevel(10000);
    }
    return (Drawable)localObject;
  }
  
  Bitmap getSampleTime()
  {
    return this.mSampleTile;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
    Drawable localDrawable = paramAttributeSet.getDrawableIfKnown(0);
    if (localDrawable != null) {
      this.mView.setIndeterminateDrawable(tileifyIndeterminate(localDrawable));
    }
    localDrawable = paramAttributeSet.getDrawableIfKnown(1);
    if (localDrawable != null) {
      this.mView.setProgressDrawable(tileify(localDrawable, false));
    }
    paramAttributeSet.recycle();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/AppCompatProgressBarHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */