package io.card.payment.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtil
{
  private static final Map<String, Integer> DIMENSION_STRING_CONSTANT = ;
  static Pattern DIMENSION_VALUE_PATTERN = Pattern.compile("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$");
  static HashMap<String, Float> pxDimensionLookupTable = new HashMap();
  
  public static Bitmap base64ToBitmap(String paramString, Context paramContext)
  {
    return base64ToBitmap(paramString, paramContext, 240);
  }
  
  public static Bitmap base64ToBitmap(String paramString, Context paramContext, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    if (paramContext != null) {}
    for (localOptions.inTargetDensity = paramContext.getResources().getDisplayMetrics().densityDpi;; localOptions.inTargetDensity = 160)
    {
      localOptions.inDensity = paramInt;
      localOptions.inScaled = false;
      paramString = Base64.decode(paramString, 0);
      return BitmapFactoryInstrumentation.decodeByteArray(paramString, 0, paramString.length, localOptions);
    }
  }
  
  static Map<String, Integer> initDimensionStringConstantMap()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("px", Integer.valueOf(0));
    localHashMap.put("dip", Integer.valueOf(1));
    localHashMap.put("dp", Integer.valueOf(1));
    localHashMap.put("sp", Integer.valueOf(2));
    localHashMap.put("pt", Integer.valueOf(3));
    localHashMap.put("in", Integer.valueOf(4));
    localHashMap.put("mm", Integer.valueOf(5));
    return Collections.unmodifiableMap(localHashMap);
  }
  
  @TargetApi(16)
  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.setBackground(paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }
  
  public static void setDimensions(View paramView, int paramInt1, int paramInt2)
  {
    paramView = paramView.getLayoutParams();
    paramView.width = paramInt1;
    paramView.height = paramInt2;
  }
  
  public static void setMargins(View paramView, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Context localContext = paramView.getContext();
    paramView = paramView.getLayoutParams();
    if ((paramView instanceof ViewGroup.MarginLayoutParams)) {
      ((ViewGroup.MarginLayoutParams)paramView).setMargins(typedDimensionValueToPixelsInt(paramString1, localContext), typedDimensionValueToPixelsInt(paramString2, localContext), typedDimensionValueToPixelsInt(paramString3, localContext), typedDimensionValueToPixelsInt(paramString4, localContext));
    }
  }
  
  public static void setPadding(View paramView, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Context localContext = paramView.getContext();
    paramView.setPadding(typedDimensionValueToPixelsInt(paramString1, localContext), typedDimensionValueToPixelsInt(paramString2, localContext), typedDimensionValueToPixelsInt(paramString3, localContext), typedDimensionValueToPixelsInt(paramString4, localContext));
  }
  
  public static void styleAsButton(View paramView, boolean paramBoolean, Context paramContext)
  {
    setDimensions(paramView, -1, -2);
    setPadding(paramView, "10dip", "0dip", "10dip", "0dip");
    if (paramBoolean) {}
    for (Drawable localDrawable = Appearance.buttonBackgroundPrimary(paramContext);; localDrawable = Appearance.buttonBackgroundSecondary(paramContext))
    {
      setBackground(paramView, localDrawable);
      paramView.setFocusable(true);
      paramView.setMinimumHeight(typedDimensionValueToPixelsInt("54dip", paramContext));
      if ((paramView instanceof TextView)) {
        styleAsButtonText((TextView)paramView);
      }
      if (!(paramView instanceof Button)) {
        paramView.setClickable(true);
      }
      return;
    }
  }
  
  public static void styleAsButtonText(TextView paramTextView)
  {
    paramTextView.setGravity(17);
    paramTextView.setTextColor(-1);
    paramTextView.setTextSize(20.0F);
    paramTextView.setTypeface(Appearance.TYPEFACE_BUTTON);
  }
  
  @SuppressLint({"DefaultLocale"})
  public static float typedDimensionValueToPixels(String paramString, Context paramContext)
  {
    if (paramString == null) {
      return 0.0F;
    }
    String str = paramString.toLowerCase();
    if (pxDimensionLookupTable.containsKey(str)) {
      return ((Float)pxDimensionLookupTable.get(str)).floatValue();
    }
    paramString = DIMENSION_VALUE_PATTERN.matcher(str);
    if (!paramString.matches()) {
      throw new NumberFormatException();
    }
    float f = Float.parseFloat(paramString.group(1));
    paramString = paramString.group(3).toLowerCase();
    Integer localInteger = (Integer)DIMENSION_STRING_CONSTANT.get(paramString);
    paramString = localInteger;
    if (localInteger == null) {
      paramString = Integer.valueOf(1);
    }
    f = TypedValue.applyDimension(paramString.intValue(), f, paramContext.getResources().getDisplayMetrics());
    pxDimensionLookupTable.put(str, Float.valueOf(f));
    return f;
  }
  
  public static int typedDimensionValueToPixelsInt(String paramString, Context paramContext)
  {
    if (paramString == null) {
      return 0;
    }
    return (int)typedDimensionValueToPixels(paramString, paramContext);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/ui/ViewUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */