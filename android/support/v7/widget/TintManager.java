package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.LruCache;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.drawable;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class TintManager
{
  private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
  private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
  private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
  private static final ColorFilterLruCache COLOR_FILTER_CACHE;
  private static final boolean DEBUG = false;
  private static final PorterDuff.Mode DEFAULT_MODE;
  private static final WeakHashMap<Context, TintManager> INSTANCE_CACHE;
  public static final boolean SHOULD_BE_USED;
  private static final String TAG = "TintManager";
  private static final int[] TINT_CHECKABLE_BUTTON_LIST;
  private static final int[] TINT_COLOR_CONTROL_NORMAL;
  private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
  private final WeakReference<Context> mContextRef;
  private ColorStateList mDefaultColorStateList;
  private SparseArray<ColorStateList> mTintLists;
  
  static
  {
    if (Build.VERSION.SDK_INT < 21) {}
    for (boolean bool = true;; bool = false)
    {
      SHOULD_BE_USED = bool;
      DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
      INSTANCE_CACHE = new WeakHashMap();
      COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
      COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
      TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_ab_back_mtrl_am_alpha, R.drawable.abc_ic_go_search_api_mtrl_alpha, R.drawable.abc_ic_search_api_mtrl_alpha, R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_ic_clear_mtrl_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R.drawable.abc_ic_voice_search_api_mtrl_alpha };
      COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material };
      COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
      TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_edit_text_material, R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material, R.drawable.abc_spinner_mtrl_am_alpha, R.drawable.abc_spinner_textfield_background_material, R.drawable.abc_ratingbar_full_material, R.drawable.abc_switch_track_mtrl_alpha, R.drawable.abc_switch_thumb_material, R.drawable.abc_btn_default_mtrl_shape, R.drawable.abc_btn_borderless_material };
      TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
      return;
    }
  }
  
  private TintManager(Context paramContext)
  {
    this.mContextRef = new WeakReference(paramContext);
  }
  
  private static boolean arrayContains(int[] paramArrayOfInt, int paramInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private ColorStateList createButtonColorStateList(Context paramContext, int paramInt)
  {
    int[][] arrayOfInt = new int[4][];
    int[] arrayOfInt1 = new int[4];
    paramInt = ThemeUtils.getThemeAttrColor(paramContext, paramInt);
    int i = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlHighlight);
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    int j = 0 + 1;
    arrayOfInt[j] = ThemeUtils.PRESSED_STATE_SET;
    arrayOfInt1[j] = ColorUtils.compositeColors(i, paramInt);
    j += 1;
    arrayOfInt[j] = ThemeUtils.FOCUSED_STATE_SET;
    arrayOfInt1[j] = ColorUtils.compositeColors(i, paramInt);
    i = j + 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = paramInt;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private ColorStateList createCheckableButtonColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int i = 0 + 1;
    arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    i += 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private ColorStateList createColoredButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, R.attr.colorAccent);
  }
  
  private ColorStateList createDefaultButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, R.attr.colorButtonNormal);
  }
  
  private ColorStateList createEditTextColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int i = 0 + 1;
    arrayOfInt[i] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
    i += 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private ColorStateList createSeekbarThumbColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[2][];
    int[] arrayOfInt1 = new int[2];
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlActivated);
    int i = 0 + 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private ColorStateList createSpinnerColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int i = 0 + 1;
    arrayOfInt[i] = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
    i += 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private ColorStateList createSwitchThumbColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    ColorStateList localColorStateList = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorSwitchThumbNormal);
    int i;
    if ((localColorStateList != null) && (localColorStateList.isStateful()))
    {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = localColorStateList.getColorForState(arrayOfInt[0], 0);
      i = 0 + 1;
      arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      i += 1;
      arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[i] = localColorStateList.getDefaultColor();
    }
    for (;;)
    {
      return new ColorStateList(arrayOfInt, arrayOfInt1);
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
      i = 0 + 1;
      arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      i += 1;
      arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    }
  }
  
  private ColorStateList createSwitchTrackColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getThemeAttrColor(paramContext, 16842800, 0.1F);
    int i = 0 + 1;
    arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated, 0.3F);
    i += 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, 16842800, 0.3F);
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static PorterDuffColorFilter createTintFilter(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int[] paramArrayOfInt)
  {
    if ((paramColorStateList == null) || (paramMode == null)) {
      return null;
    }
    return getPorterDuffColorFilter(paramColorStateList.getColorForState(paramArrayOfInt, 0), paramMode);
  }
  
  public static TintManager get(Context paramContext)
  {
    TintManager localTintManager2 = (TintManager)INSTANCE_CACHE.get(paramContext);
    TintManager localTintManager1 = localTintManager2;
    if (localTintManager2 == null)
    {
      localTintManager1 = new TintManager(paramContext);
      INSTANCE_CACHE.put(paramContext, localTintManager1);
    }
    return localTintManager1;
  }
  
  private ColorStateList getDefaultColorStateList(Context paramContext)
  {
    if (this.mDefaultColorStateList == null)
    {
      int i = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
      int j = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      int[][] arrayOfInt = new int[7][];
      int[] arrayOfInt1 = new int[7];
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
      int k = 0 + 1;
      arrayOfInt[k] = ThemeUtils.FOCUSED_STATE_SET;
      arrayOfInt1[k] = j;
      k += 1;
      arrayOfInt[k] = ThemeUtils.ACTIVATED_STATE_SET;
      arrayOfInt1[k] = j;
      k += 1;
      arrayOfInt[k] = ThemeUtils.PRESSED_STATE_SET;
      arrayOfInt1[k] = j;
      k += 1;
      arrayOfInt[k] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[k] = j;
      k += 1;
      arrayOfInt[k] = ThemeUtils.SELECTED_STATE_SET;
      arrayOfInt1[k] = j;
      j = k + 1;
      arrayOfInt[j] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[j] = i;
      this.mDefaultColorStateList = new ColorStateList(arrayOfInt, arrayOfInt1);
    }
    return this.mDefaultColorStateList;
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    if (isInTintList(paramInt)) {
      return get(paramContext).getDrawable(paramInt);
    }
    return ContextCompat.getDrawable(paramContext, paramInt);
  }
  
  private static PorterDuffColorFilter getPorterDuffColorFilter(int paramInt, PorterDuff.Mode paramMode)
  {
    PorterDuffColorFilter localPorterDuffColorFilter2 = COLOR_FILTER_CACHE.get(paramInt, paramMode);
    PorterDuffColorFilter localPorterDuffColorFilter1 = localPorterDuffColorFilter2;
    if (localPorterDuffColorFilter2 == null)
    {
      localPorterDuffColorFilter1 = new PorterDuffColorFilter(paramInt, paramMode);
      COLOR_FILTER_CACHE.put(paramInt, paramMode, localPorterDuffColorFilter1);
    }
    return localPorterDuffColorFilter1;
  }
  
  private static boolean isInTintList(int paramInt)
  {
    return (arrayContains(TINT_COLOR_CONTROL_NORMAL, paramInt)) || (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt)) || (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt)) || (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, paramInt)) || (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt)) || (arrayContains(TINT_CHECKABLE_BUTTON_LIST, paramInt)) || (paramInt == R.drawable.abc_cab_background_top_material);
  }
  
  private static void setPorterDuffColorFilter(Drawable paramDrawable, int paramInt, PorterDuff.Mode paramMode)
  {
    PorterDuff.Mode localMode = paramMode;
    if (paramMode == null) {
      localMode = DEFAULT_MODE;
    }
    paramDrawable.setColorFilter(getPorterDuffColorFilter(paramInt, localMode));
  }
  
  private static boolean shouldMutateBackground(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16) {}
    for (;;)
    {
      return true;
      if ((paramDrawable instanceof LayerDrawable))
      {
        if (Build.VERSION.SDK_INT < 16) {
          return false;
        }
      }
      else if ((paramDrawable instanceof InsetDrawable))
      {
        if (Build.VERSION.SDK_INT < 14) {
          return false;
        }
      }
      else if ((paramDrawable instanceof DrawableContainer))
      {
        paramDrawable = paramDrawable.getConstantState();
        if ((paramDrawable instanceof DrawableContainer.DrawableContainerState))
        {
          paramDrawable = ((DrawableContainer.DrawableContainerState)paramDrawable).getChildren();
          int j = paramDrawable.length;
          int i = 0;
          while (i < j)
          {
            if (!shouldMutateBackground(paramDrawable[i])) {
              return false;
            }
            i += 1;
          }
        }
      }
    }
  }
  
  public static void tintDrawable(Drawable paramDrawable, TintInfo paramTintInfo, int[] paramArrayOfInt)
  {
    if ((shouldMutateBackground(paramDrawable)) && (paramDrawable.mutate() != paramDrawable)) {
      Log.d("TintManager", "Mutated drawable is not the same instance as the input.");
    }
    label63:
    label91:
    label103:
    for (;;)
    {
      return;
      ColorStateList localColorStateList;
      if ((paramTintInfo.mHasTintList) || (paramTintInfo.mHasTintMode)) {
        if (paramTintInfo.mHasTintList)
        {
          localColorStateList = paramTintInfo.mTintList;
          if (!paramTintInfo.mHasTintMode) {
            break label91;
          }
          paramTintInfo = paramTintInfo.mTintMode;
          paramDrawable.setColorFilter(createTintFilter(localColorStateList, paramTintInfo, paramArrayOfInt));
        }
      }
      for (;;)
      {
        if (Build.VERSION.SDK_INT > 10) {
          break label103;
        }
        paramDrawable.invalidateSelf();
        return;
        localColorStateList = null;
        break;
        paramTintInfo = DEFAULT_MODE;
        break label63;
        paramDrawable.clearColorFilter();
      }
    }
  }
  
  public Drawable getDrawable(int paramInt)
  {
    return getDrawable(paramInt, false);
  }
  
  public Drawable getDrawable(int paramInt, boolean paramBoolean)
  {
    Context localContext = (Context)this.mContextRef.get();
    Object localObject1;
    if (localContext == null) {
      localObject1 = null;
    }
    do
    {
      Object localObject2;
      do
      {
        Object localObject3;
        do
        {
          do
          {
            return (Drawable)localObject1;
            localObject3 = ContextCompat.getDrawable(localContext, paramInt);
            localObject1 = localObject3;
          } while (localObject3 == null);
          localObject2 = localObject3;
          if (Build.VERSION.SDK_INT >= 8) {
            localObject2 = ((Drawable)localObject3).mutate();
          }
          localObject1 = getTintList(paramInt);
          if (localObject1 == null) {
            break;
          }
          localObject2 = DrawableCompat.wrap((Drawable)localObject2);
          DrawableCompat.setTintList((Drawable)localObject2, (ColorStateList)localObject1);
          localObject3 = getTintMode(paramInt);
          localObject1 = localObject2;
        } while (localObject3 == null);
        DrawableCompat.setTintMode((Drawable)localObject2, (PorterDuff.Mode)localObject3);
        return (Drawable)localObject2;
        if (paramInt == R.drawable.abc_cab_background_top_material) {
          return new LayerDrawable(new Drawable[] { getDrawable(R.drawable.abc_cab_background_internal_bg), getDrawable(R.drawable.abc_cab_background_top_mtrl_alpha) });
        }
        if (paramInt == R.drawable.abc_seekbar_track_material)
        {
          localObject1 = (LayerDrawable)localObject2;
          setPorterDuffColorFilter(((LayerDrawable)localObject1).findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(localContext, R.attr.colorControlNormal), DEFAULT_MODE);
          setPorterDuffColorFilter(((LayerDrawable)localObject1).findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(localContext, R.attr.colorControlNormal), DEFAULT_MODE);
          setPorterDuffColorFilter(((LayerDrawable)localObject1).findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(localContext, R.attr.colorControlActivated), DEFAULT_MODE);
          return (Drawable)localObject2;
        }
        localObject1 = localObject2;
      } while (tintDrawableUsingColorFilter(paramInt, (Drawable)localObject2));
      localObject1 = localObject2;
    } while (!paramBoolean);
    return null;
  }
  
  public final ColorStateList getTintList(int paramInt)
  {
    ColorStateList localColorStateList1 = null;
    ColorStateList localColorStateList2 = null;
    Context localContext = (Context)this.mContextRef.get();
    if (localContext == null) {}
    do
    {
      return localColorStateList2;
      if (this.mTintLists != null) {
        localColorStateList1 = (ColorStateList)this.mTintLists.get(paramInt);
      }
      localColorStateList2 = localColorStateList1;
    } while (localColorStateList1 != null);
    if (paramInt == R.drawable.abc_edit_text_material) {
      localColorStateList1 = createEditTextColorStateList(localContext);
    }
    for (;;)
    {
      localColorStateList2 = localColorStateList1;
      if (localColorStateList1 == null) {
        break;
      }
      if (this.mTintLists == null) {
        this.mTintLists = new SparseArray();
      }
      this.mTintLists.append(paramInt, localColorStateList1);
      return localColorStateList1;
      if (paramInt == R.drawable.abc_switch_track_mtrl_alpha) {
        localColorStateList1 = createSwitchTrackColorStateList(localContext);
      } else if (paramInt == R.drawable.abc_switch_thumb_material) {
        localColorStateList1 = createSwitchThumbColorStateList(localContext);
      } else if ((paramInt == R.drawable.abc_btn_default_mtrl_shape) || (paramInt == R.drawable.abc_btn_borderless_material)) {
        localColorStateList1 = createDefaultButtonColorStateList(localContext);
      } else if (paramInt == R.drawable.abc_btn_colored_material) {
        localColorStateList1 = createColoredButtonColorStateList(localContext);
      } else if ((paramInt == R.drawable.abc_spinner_mtrl_am_alpha) || (paramInt == R.drawable.abc_spinner_textfield_background_material)) {
        localColorStateList1 = createSpinnerColorStateList(localContext);
      } else if (arrayContains(TINT_COLOR_CONTROL_NORMAL, paramInt)) {
        localColorStateList1 = ThemeUtils.getThemeAttrColorStateList(localContext, R.attr.colorControlNormal);
      } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, paramInt)) {
        localColorStateList1 = getDefaultColorStateList(localContext);
      } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, paramInt)) {
        localColorStateList1 = createCheckableButtonColorStateList(localContext);
      } else if (paramInt == R.drawable.abc_seekbar_thumb_material) {
        localColorStateList1 = createSeekbarThumbColorStateList(localContext);
      }
    }
  }
  
  final PorterDuff.Mode getTintMode(int paramInt)
  {
    PorterDuff.Mode localMode = null;
    if (paramInt == R.drawable.abc_switch_thumb_material) {
      localMode = PorterDuff.Mode.MULTIPLY;
    }
    return localMode;
  }
  
  public final boolean tintDrawableUsingColorFilter(int paramInt, Drawable paramDrawable)
  {
    Context localContext = (Context)this.mContextRef.get();
    if (localContext == null) {}
    for (;;)
    {
      return false;
      PorterDuff.Mode localMode2 = DEFAULT_MODE;
      int j = 0;
      int i = 0;
      int k = -1;
      PorterDuff.Mode localMode1;
      if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt))
      {
        i = R.attr.colorControlNormal;
        j = 1;
        localMode1 = localMode2;
      }
      while (j != 0)
      {
        paramDrawable.setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(localContext, i), localMode1));
        if (k != -1) {
          paramDrawable.setAlpha(k);
        }
        return true;
        if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt))
        {
          i = R.attr.colorControlActivated;
          j = 1;
          localMode1 = localMode2;
        }
        else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt))
        {
          i = 16842801;
          j = 1;
          localMode1 = PorterDuff.Mode.MULTIPLY;
        }
        else
        {
          localMode1 = localMode2;
          if (paramInt == R.drawable.abc_list_divider_mtrl_alpha)
          {
            i = 16842800;
            j = 1;
            k = Math.round(40.8F);
            localMode1 = localMode2;
          }
        }
      }
    }
  }
  
  private static class ColorFilterLruCache
    extends LruCache<Integer, PorterDuffColorFilter>
  {
    public ColorFilterLruCache(int paramInt)
    {
      super();
    }
    
    private static int generateCacheKey(int paramInt, PorterDuff.Mode paramMode)
    {
      return (paramInt + 31) * 31 + paramMode.hashCode();
    }
    
    PorterDuffColorFilter get(int paramInt, PorterDuff.Mode paramMode)
    {
      return (PorterDuffColorFilter)get(Integer.valueOf(generateCacheKey(paramInt, paramMode)));
    }
    
    PorterDuffColorFilter put(int paramInt, PorterDuff.Mode paramMode, PorterDuffColorFilter paramPorterDuffColorFilter)
    {
      return (PorterDuffColorFilter)put(Integer.valueOf(generateCacheKey(paramInt, paramMode)), paramPorterDuffColorFilter);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/TintManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */