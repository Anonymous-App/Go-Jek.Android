package lib.gojek.base.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.StringRes;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceCollection.Builder;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.R.string;

public class FontFaceHelper
{
  private static TypefaceCollection bebasNeue;
  private static TypefaceCollection latoFont;
  
  public static TypefaceCollection getBebasNeue()
  {
    return bebasNeue;
  }
  
  public static TypefaceCollection getLatoFont()
  {
    return latoFont;
  }
  
  private static Typeface getTypeFace(Context paramContext, @StringRes int paramInt)
  {
    return Typeface.createFromAsset(paramContext.getAssets(), paramContext.getString(paramInt));
  }
  
  public static void initFace(Context paramContext)
  {
    bebasNeue = new TypefaceCollection.Builder().set(0, getTypeFace(paramContext, R.string.BebasNeueReguler)).create();
    latoFont = new TypefaceCollection.Builder().set(0, getTypeFace(paramContext, R.string.LatoReguler)).set(1, getTypeFace(paramContext, R.string.LatoBold)).set(3, getTypeFace(paramContext, R.string.LatoBoldItalic)).create();
    TypefaceHelper.init(bebasNeue);
    TypefaceHelper.init(latoFont);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/helper/FontFaceHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */