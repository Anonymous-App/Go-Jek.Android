package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

class AppCompatViewInflater
{
  private static final String LOG_TAG = "AppCompatViewInflater";
  private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();
  private static final Class<?>[] sConstructorSignature = { Context.class, AttributeSet.class };
  private static final int[] sOnClickAttrs = { 16843375 };
  private final Object[] mConstructorArgs = new Object[2];
  
  private void checkOnClickListener(View paramView, AttributeSet paramAttributeSet)
  {
    Object localObject = paramView.getContext();
    if ((!ViewCompat.hasOnClickListeners(paramView)) || (!(localObject instanceof ContextWrapper))) {
      return;
    }
    paramAttributeSet = ((Context)localObject).obtainStyledAttributes(paramAttributeSet, sOnClickAttrs);
    localObject = paramAttributeSet.getString(0);
    if (localObject != null) {
      paramView.setOnClickListener(new DeclaredOnClickListener(paramView, (String)localObject));
    }
    paramAttributeSet.recycle();
  }
  
  private View createView(Context paramContext, String paramString1, String paramString2)
    throws ClassNotFoundException, InflateException
  {
    Constructor localConstructor = (Constructor)sConstructorMap.get(paramString1);
    Object localObject = localConstructor;
    if (localConstructor == null) {}
    try
    {
      localObject = paramContext.getClassLoader();
      if (paramString2 != null) {}
      for (paramContext = paramString2 + paramString1;; paramContext = paramString1)
      {
        localObject = ((ClassLoader)localObject).loadClass(paramContext).asSubclass(View.class).getConstructor(sConstructorSignature);
        sConstructorMap.put(paramString1, localObject);
        ((Constructor)localObject).setAccessible(true);
        paramContext = (View)((Constructor)localObject).newInstance(this.mConstructorArgs);
        return paramContext;
      }
      return null;
    }
    catch (Exception paramContext) {}
  }
  
  /* Error */
  private View createViewFromTag(Context paramContext, String paramString, AttributeSet paramAttributeSet)
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 4
    //   3: aload_2
    //   4: ldc -113
    //   6: invokevirtual 149	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +14 -> 23
    //   12: aload_3
    //   13: aconst_null
    //   14: ldc -105
    //   16: invokeinterface 155 3 0
    //   21: astore 4
    //   23: aload_0
    //   24: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   27: iconst_0
    //   28: aload_1
    //   29: aastore
    //   30: aload_0
    //   31: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   34: iconst_1
    //   35: aload_3
    //   36: aastore
    //   37: iconst_m1
    //   38: aload 4
    //   40: bipush 46
    //   42: invokevirtual 159	java/lang/String:indexOf	(I)I
    //   45: if_icmpne +29 -> 74
    //   48: aload_0
    //   49: aload_1
    //   50: aload 4
    //   52: ldc -95
    //   54: invokespecial 163	android/support/v7/app/AppCompatViewInflater:createView	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;
    //   57: astore_1
    //   58: aload_0
    //   59: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   62: iconst_0
    //   63: aconst_null
    //   64: aastore
    //   65: aload_0
    //   66: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   69: iconst_1
    //   70: aconst_null
    //   71: aastore
    //   72: aload_1
    //   73: areturn
    //   74: aload_0
    //   75: aload_1
    //   76: aload 4
    //   78: aconst_null
    //   79: invokespecial 163	android/support/v7/app/AppCompatViewInflater:createView	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;
    //   82: astore_1
    //   83: aload_0
    //   84: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   87: iconst_0
    //   88: aconst_null
    //   89: aastore
    //   90: aload_0
    //   91: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   94: iconst_1
    //   95: aconst_null
    //   96: aastore
    //   97: aload_1
    //   98: areturn
    //   99: astore_1
    //   100: aload_0
    //   101: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   104: iconst_0
    //   105: aconst_null
    //   106: aastore
    //   107: aload_0
    //   108: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   111: iconst_1
    //   112: aconst_null
    //   113: aastore
    //   114: aconst_null
    //   115: areturn
    //   116: astore_1
    //   117: aload_0
    //   118: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   121: iconst_0
    //   122: aconst_null
    //   123: aastore
    //   124: aload_0
    //   125: getfield 45	android/support/v7/app/AppCompatViewInflater:mConstructorArgs	[Ljava/lang/Object;
    //   128: iconst_1
    //   129: aconst_null
    //   130: aastore
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	this	AppCompatViewInflater
    //   0	133	1	paramContext	Context
    //   0	133	2	paramString	String
    //   0	133	3	paramAttributeSet	AttributeSet
    //   1	76	4	str	String
    // Exception table:
    //   from	to	target	type
    //   23	58	99	java/lang/Exception
    //   74	83	99	java/lang/Exception
    //   23	58	116	finally
    //   74	83	116	finally
  }
  
  private static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    int i = 0;
    if (paramBoolean1) {
      i = paramAttributeSet.getResourceId(R.styleable.View_android_theme, 0);
    }
    int j = i;
    if (paramBoolean2)
    {
      j = i;
      if (i == 0)
      {
        i = paramAttributeSet.getResourceId(R.styleable.View_theme, 0);
        j = i;
        if (i != 0)
        {
          Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
          j = i;
        }
      }
    }
    paramAttributeSet.recycle();
    paramAttributeSet = paramContext;
    if (j != 0) {
      if ((paramContext instanceof ContextThemeWrapper))
      {
        paramAttributeSet = paramContext;
        if (((ContextThemeWrapper)paramContext).getThemeResId() == j) {}
      }
      else
      {
        paramAttributeSet = new ContextThemeWrapper(paramContext, j);
      }
    }
    return paramAttributeSet;
  }
  
  public final View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Object localObject = paramContext;
    if (paramBoolean1)
    {
      localObject = paramContext;
      if (paramView != null) {
        localObject = paramView.getContext();
      }
    }
    paramView = (View)localObject;
    if (!paramBoolean2)
    {
      localObject = paramView;
      if (!paramBoolean3) {}
    }
    else
    {
      localObject = themifyContext(paramView, paramAttributeSet, paramBoolean2, paramBoolean3);
    }
    paramView = null;
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    for (;;)
    {
      View localView = paramView;
      if (paramView == null)
      {
        localView = paramView;
        if (paramContext != localObject) {
          localView = createViewFromTag((Context)localObject, paramString, paramAttributeSet);
        }
      }
      if (localView != null) {
        checkOnClickListener(localView, paramAttributeSet);
      }
      return localView;
      if (!paramString.equals("TextView")) {
        break;
      }
      i = 0;
      break;
      if (!paramString.equals("ImageView")) {
        break;
      }
      i = 1;
      break;
      if (!paramString.equals("Button")) {
        break;
      }
      i = 2;
      break;
      if (!paramString.equals("EditText")) {
        break;
      }
      i = 3;
      break;
      if (!paramString.equals("Spinner")) {
        break;
      }
      i = 4;
      break;
      if (!paramString.equals("ImageButton")) {
        break;
      }
      i = 5;
      break;
      if (!paramString.equals("CheckBox")) {
        break;
      }
      i = 6;
      break;
      if (!paramString.equals("RadioButton")) {
        break;
      }
      i = 7;
      break;
      if (!paramString.equals("CheckedTextView")) {
        break;
      }
      i = 8;
      break;
      if (!paramString.equals("AutoCompleteTextView")) {
        break;
      }
      i = 9;
      break;
      if (!paramString.equals("MultiAutoCompleteTextView")) {
        break;
      }
      i = 10;
      break;
      if (!paramString.equals("RatingBar")) {
        break;
      }
      i = 11;
      break;
      if (!paramString.equals("SeekBar")) {
        break;
      }
      i = 12;
      break;
      paramView = new AppCompatTextView((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatImageView((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatButton((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatEditText((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatSpinner((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatImageButton((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatCheckBox((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatRadioButton((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatCheckedTextView((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatAutoCompleteTextView((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatMultiAutoCompleteTextView((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatRatingBar((Context)localObject, paramAttributeSet);
      continue;
      paramView = new AppCompatSeekBar((Context)localObject, paramAttributeSet);
    }
  }
  
  private static class DeclaredOnClickListener
    implements View.OnClickListener
  {
    private final View mHostView;
    private final String mMethodName;
    private Context mResolvedContext;
    private Method mResolvedMethod;
    
    public DeclaredOnClickListener(@NonNull View paramView, @NonNull String paramString)
    {
      this.mHostView = paramView;
      this.mMethodName = paramString;
    }
    
    @NonNull
    private void resolveMethod(@Nullable Context paramContext, @NonNull String paramString)
    {
      while (paramContext != null) {
        try
        {
          if (!paramContext.isRestricted())
          {
            paramString = paramContext.getClass().getMethod(this.mMethodName, new Class[] { View.class });
            if (paramString != null)
            {
              this.mResolvedMethod = paramString;
              this.mResolvedContext = paramContext;
              return;
            }
          }
        }
        catch (NoSuchMethodException paramString)
        {
          if ((paramContext instanceof ContextWrapper)) {
            paramContext = ((ContextWrapper)paramContext).getBaseContext();
          } else {
            paramContext = null;
          }
        }
      }
      int i = this.mHostView.getId();
      if (i == -1) {}
      for (paramContext = "";; paramContext = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(i) + "'") {
        throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + paramContext);
      }
    }
    
    public void onClick(@NonNull View paramView)
    {
      if (this.mResolvedMethod == null) {
        resolveMethod(this.mHostView.getContext(), this.mMethodName);
      }
      try
      {
        this.mResolvedMethod.invoke(this.mResolvedContext, new Object[] { paramView });
        return;
      }
      catch (IllegalAccessException paramView)
      {
        throw new IllegalStateException("Could not execute non-public method for android:onClick", paramView);
      }
      catch (InvocationTargetException paramView)
      {
        throw new IllegalStateException("Could not execute method for android:onClick", paramView);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/app/AppCompatViewInflater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */