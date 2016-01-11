package io.card.payment.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.DisplayMetrics;

public class Appearance
{
  public static final Drawable ACTIONBAR_BACKGROUND;
  public static final int BUTTON_PRIMARY_DISABLED_COLOR;
  public static final int BUTTON_PRIMARY_FOCUS_COLOR;
  public static final int BUTTON_PRIMARY_NORMAL_COLOR;
  public static final int BUTTON_PRIMARY_PRESSED_COLOR;
  public static final int BUTTON_SECONDARY_DISABLED_COLOR;
  public static final int BUTTON_SECONDARY_FOCUS_COLOR;
  public static final int BUTTON_SECONDARY_NORMAL_COLOR;
  public static final int BUTTON_SECONDARY_PRESSED_COLOR;
  public static final int[] BUTTON_STATE_DISABLED;
  public static final int[] BUTTON_STATE_FOCUSED;
  public static final int[] BUTTON_STATE_NORMAL;
  public static final int[] BUTTON_STATE_PRESSED = { 16842919, 16842910 };
  public static final int DEFAULT_BACKGROUND_COLOR;
  public static final int PAL_BLUE_COLOR;
  public static final int PAL_BLUE_COLOR_OPACITY_66;
  public static final int PAY_BLUE_COLOR;
  public static final int TEXT_COLOR_ERROR = Color.parseColor("#b32317");
  public static final int TEXT_COLOR_LABEL = TEXT_COLOR_LIGHT;
  public static final int TEXT_COLOR_LIGHT;
  public static final Typeface TYPEFACE_BUTTON = typefaceLight();
  
  static
  {
    BUTTON_STATE_NORMAL = new int[] { 16842910 };
    BUTTON_STATE_DISABLED = new int[] { -16842910 };
    BUTTON_STATE_FOCUSED = new int[] { 16842908 };
    PAY_BLUE_COLOR = Color.parseColor("#003087");
    PAL_BLUE_COLOR = Color.parseColor("#009CDE");
    PAL_BLUE_COLOR_OPACITY_66 = Color.parseColor("#aa009CDE");
    ACTIONBAR_BACKGROUND = new ColorDrawable(Color.parseColor("#717074"));
    DEFAULT_BACKGROUND_COLOR = Color.parseColor("#f5f5f5");
    BUTTON_PRIMARY_NORMAL_COLOR = PAL_BLUE_COLOR;
    BUTTON_PRIMARY_FOCUS_COLOR = PAL_BLUE_COLOR_OPACITY_66;
    BUTTON_PRIMARY_PRESSED_COLOR = PAY_BLUE_COLOR;
    BUTTON_PRIMARY_DISABLED_COLOR = Color.parseColor("#c5ddeb");
    BUTTON_SECONDARY_NORMAL_COLOR = Color.parseColor("#717074");
    BUTTON_SECONDARY_FOCUS_COLOR = Color.parseColor("#aa717074");
    BUTTON_SECONDARY_PRESSED_COLOR = Color.parseColor("#5a5a5d");
    BUTTON_SECONDARY_DISABLED_COLOR = Color.parseColor("#f5f5f5");
    TEXT_COLOR_LIGHT = Color.parseColor("#515151");
  }
  
  public static Drawable buttonBackgroundPrimary(Context paramContext)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(BUTTON_STATE_PRESSED, new ColorDrawable(BUTTON_PRIMARY_PRESSED_COLOR));
    localStateListDrawable.addState(BUTTON_STATE_DISABLED, new ColorDrawable(BUTTON_PRIMARY_DISABLED_COLOR));
    localStateListDrawable.addState(BUTTON_STATE_FOCUSED, buttonBackgroundPrimaryFocused(paramContext));
    localStateListDrawable.addState(BUTTON_STATE_NORMAL, buttonBackgroundPrimaryNormal(paramContext));
    return localStateListDrawable;
  }
  
  private static Drawable buttonBackgroundPrimaryFocused(Context paramContext)
  {
    return buttonFocused(BUTTON_PRIMARY_NORMAL_COLOR, BUTTON_PRIMARY_FOCUS_COLOR, getFocusBorderWidthPixels(paramContext));
  }
  
  private static Drawable buttonBackgroundPrimaryNormal(Context paramContext)
  {
    return buttonNormal(BUTTON_PRIMARY_NORMAL_COLOR, getFocusBorderWidthPixels(paramContext));
  }
  
  public static Drawable buttonBackgroundSecondary(Context paramContext)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(BUTTON_STATE_PRESSED, new ColorDrawable(BUTTON_SECONDARY_PRESSED_COLOR));
    localStateListDrawable.addState(BUTTON_STATE_DISABLED, new ColorDrawable(BUTTON_SECONDARY_DISABLED_COLOR));
    localStateListDrawable.addState(BUTTON_STATE_FOCUSED, buttonBackgroundSecondaryFocused(paramContext));
    localStateListDrawable.addState(BUTTON_STATE_NORMAL, buttonBackgroundSecondaryNormal(paramContext));
    return localStateListDrawable;
  }
  
  private static Drawable buttonBackgroundSecondaryFocused(Context paramContext)
  {
    return buttonFocused(BUTTON_SECONDARY_NORMAL_COLOR, BUTTON_SECONDARY_FOCUS_COLOR, getFocusBorderWidthPixels(paramContext));
  }
  
  private static Drawable buttonBackgroundSecondaryNormal(Context paramContext)
  {
    return buttonNormal(BUTTON_SECONDARY_NORMAL_COLOR, getFocusBorderWidthPixels(paramContext));
  }
  
  private static Drawable buttonFocused(int paramInt1, int paramInt2, float paramFloat)
  {
    ColorDrawable localColorDrawable = new ColorDrawable(paramInt1);
    ShapeDrawable localShapeDrawable1 = new ShapeDrawable(new RectShape());
    localShapeDrawable1.getPaint().setStrokeWidth(2.0F * paramFloat);
    localShapeDrawable1.getPaint().setStyle(Paint.Style.STROKE);
    localShapeDrawable1.getPaint().setColor(DEFAULT_BACKGROUND_COLOR);
    ShapeDrawable localShapeDrawable2 = new ShapeDrawable(new RectShape());
    localShapeDrawable2.getPaint().setStrokeWidth(paramFloat);
    localShapeDrawable2.getPaint().setStyle(Paint.Style.STROKE);
    localShapeDrawable2.getPaint().setColor(paramInt2);
    return new LayerDrawable(new Drawable[] { localColorDrawable, localShapeDrawable1, localShapeDrawable2 });
  }
  
  private static Drawable buttonNormal(int paramInt, float paramFloat)
  {
    ColorDrawable localColorDrawable = new ColorDrawable(paramInt);
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RectShape());
    localShapeDrawable.getPaint().setStrokeWidth(2.0F * paramFloat);
    localShapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
    localShapeDrawable.getPaint().setColor(DEFAULT_BACKGROUND_COLOR);
    return new LayerDrawable(new Drawable[] { localColorDrawable, localShapeDrawable });
  }
  
  private static float getFocusBorderWidthPixels(Context paramContext)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return ViewUtil.typedDimensionValueToPixels("4dip", paramContext) / 2.0F * f;
  }
  
  private static Typeface typefaceLight()
  {
    return Typeface.create("sans-serif-light", 0);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/ui/Appearance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */