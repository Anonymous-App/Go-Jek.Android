package com.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.facebook.appevents.AppEventsLogger;

public abstract class FacebookButtonBase
  extends Button
{
  private String analyticsButtonCreatedEventName;
  private View.OnClickListener externalOnClickListener;
  private View.OnClickListener internalOnClickListener;
  private boolean overrideCompoundPadding;
  private int overrideCompoundPaddingLeft;
  private int overrideCompoundPaddingRight;
  private Fragment parentFragment;
  private int requestCode;
  
  protected FacebookButtonBase(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    super(paramContext, paramAttributeSet, 0);
    int i = paramInt2;
    if (paramInt2 == 0) {
      i = getDefaultStyleResource();
    }
    paramInt2 = i;
    if (i == 0) {
      paramInt2 = R.style.com_facebook_button;
    }
    configureButton(paramContext, paramAttributeSet, paramInt1, paramInt2);
    this.analyticsButtonCreatedEventName = paramString;
    this.requestCode = paramInt3;
  }
  
  private void logButtonCreated(Context paramContext)
  {
    AppEventsLogger.newLogger(paramContext).logSdkEvent(this.analyticsButtonCreatedEventName, null, null);
  }
  
  private void parseBackgroundAttributes(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, new int[] { 16842964 }, paramInt1, paramInt2);
    for (;;)
    {
      try
      {
        if (paramContext.hasValue(0))
        {
          paramInt1 = paramContext.getResourceId(0, 0);
          if (paramInt1 != 0)
          {
            setBackgroundResource(paramInt1);
            return;
          }
          setBackgroundColor(paramContext.getColor(0, 0));
          continue;
        }
        setBackgroundColor(paramContext.getColor(0, R.color.com_facebook_blue));
      }
      finally
      {
        paramContext.recycle();
      }
    }
  }
  
  private void parseCompoundDrawableAttributes(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, new int[] { 16843119, 16843117, 16843120, 16843118, 16843121 }, paramInt1, paramInt2);
    try
    {
      setCompoundDrawablesWithIntrinsicBounds(paramContext.getResourceId(0, 0), paramContext.getResourceId(1, 0), paramContext.getResourceId(2, 0), paramContext.getResourceId(3, 0));
      setCompoundDrawablePadding(paramContext.getDimensionPixelSize(4, 0));
      return;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  private void parseContentAttributes(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, new int[] { 16842966, 16842967, 16842968, 16842969 }, paramInt1, paramInt2);
    try
    {
      setPadding(paramContext.getDimensionPixelSize(0, 0), paramContext.getDimensionPixelSize(1, 0), paramContext.getDimensionPixelSize(2, 0), paramContext.getDimensionPixelSize(3, 0));
      return;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  /* Error */
  private void parseTextAttributes(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 66	android/content/Context:getTheme	()Landroid/content/res/Resources$Theme;
    //   4: aload_2
    //   5: iconst_1
    //   6: newarray <illegal type>
    //   8: dup
    //   9: iconst_0
    //   10: ldc -128
    //   12: iastore
    //   13: iload_3
    //   14: iload 4
    //   16: invokevirtual 73	android/content/res/Resources$Theme:obtainStyledAttributes	(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   19: astore 5
    //   21: aload_0
    //   22: aload 5
    //   24: iconst_0
    //   25: iconst_m1
    //   26: invokevirtual 94	android/content/res/TypedArray:getColor	(II)I
    //   29: invokevirtual 131	com/facebook/FacebookButtonBase:setTextColor	(I)V
    //   32: aload 5
    //   34: invokevirtual 91	android/content/res/TypedArray:recycle	()V
    //   37: aload_1
    //   38: invokevirtual 66	android/content/Context:getTheme	()Landroid/content/res/Resources$Theme;
    //   41: aload_2
    //   42: iconst_1
    //   43: newarray <illegal type>
    //   45: dup
    //   46: iconst_0
    //   47: ldc -124
    //   49: iastore
    //   50: iload_3
    //   51: iload 4
    //   53: invokevirtual 73	android/content/res/Resources$Theme:obtainStyledAttributes	(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   56: astore 5
    //   58: aload_0
    //   59: aload 5
    //   61: iconst_0
    //   62: bipush 17
    //   64: invokevirtual 135	android/content/res/TypedArray:getInt	(II)I
    //   67: invokevirtual 138	com/facebook/FacebookButtonBase:setGravity	(I)V
    //   70: aload 5
    //   72: invokevirtual 91	android/content/res/TypedArray:recycle	()V
    //   75: aload_1
    //   76: invokevirtual 66	android/content/Context:getTheme	()Landroid/content/res/Resources$Theme;
    //   79: aload_2
    //   80: iconst_3
    //   81: newarray <illegal type>
    //   83: dup
    //   84: iconst_0
    //   85: ldc -117
    //   87: iastore
    //   88: dup
    //   89: iconst_1
    //   90: ldc -116
    //   92: iastore
    //   93: dup
    //   94: iconst_2
    //   95: ldc -115
    //   97: iastore
    //   98: iload_3
    //   99: iload 4
    //   101: invokevirtual 73	android/content/res/Resources$Theme:obtainStyledAttributes	(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   104: astore_1
    //   105: aload_0
    //   106: iconst_0
    //   107: aload_1
    //   108: iconst_0
    //   109: iconst_0
    //   110: invokevirtual 115	android/content/res/TypedArray:getDimensionPixelSize	(II)I
    //   113: i2f
    //   114: invokevirtual 145	com/facebook/FacebookButtonBase:setTextSize	(IF)V
    //   117: aload_0
    //   118: aload_1
    //   119: iconst_1
    //   120: iconst_1
    //   121: invokevirtual 135	android/content/res/TypedArray:getInt	(II)I
    //   124: invokestatic 151	android/graphics/Typeface:defaultFromStyle	(I)Landroid/graphics/Typeface;
    //   127: invokevirtual 155	com/facebook/FacebookButtonBase:setTypeface	(Landroid/graphics/Typeface;)V
    //   130: aload_0
    //   131: aload_1
    //   132: iconst_2
    //   133: invokevirtual 159	android/content/res/TypedArray:getString	(I)Ljava/lang/String;
    //   136: invokevirtual 163	com/facebook/FacebookButtonBase:setText	(Ljava/lang/CharSequence;)V
    //   139: aload_1
    //   140: invokevirtual 91	android/content/res/TypedArray:recycle	()V
    //   143: return
    //   144: astore_1
    //   145: aload 5
    //   147: invokevirtual 91	android/content/res/TypedArray:recycle	()V
    //   150: aload_1
    //   151: athrow
    //   152: astore_1
    //   153: aload 5
    //   155: invokevirtual 91	android/content/res/TypedArray:recycle	()V
    //   158: aload_1
    //   159: athrow
    //   160: astore_2
    //   161: aload_1
    //   162: invokevirtual 91	android/content/res/TypedArray:recycle	()V
    //   165: aload_2
    //   166: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	167	0	this	FacebookButtonBase
    //   0	167	1	paramContext	Context
    //   0	167	2	paramAttributeSet	AttributeSet
    //   0	167	3	paramInt1	int
    //   0	167	4	paramInt2	int
    //   19	135	5	localTypedArray	TypedArray
    // Exception table:
    //   from	to	target	type
    //   21	32	144	finally
    //   58	70	152	finally
    //   105	139	160	finally
  }
  
  private void setupOnClickListener()
  {
    super.setOnClickListener(new FacebookButtonBase.1(this));
  }
  
  protected void callExternalOnClickListener(View paramView)
  {
    if (this.externalOnClickListener != null) {
      this.externalOnClickListener.onClick(paramView);
    }
  }
  
  protected void configureButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    parseBackgroundAttributes(paramContext, paramAttributeSet, paramInt1, paramInt2);
    parseCompoundDrawableAttributes(paramContext, paramAttributeSet, paramInt1, paramInt2);
    parseContentAttributes(paramContext, paramAttributeSet, paramInt1, paramInt2);
    parseTextAttributes(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setupOnClickListener();
  }
  
  protected Activity getActivity()
  {
    Context localContext = getContext();
    if ((localContext instanceof Activity)) {
      return (Activity)localContext;
    }
    if ((localContext instanceof ContextWrapper))
    {
      localContext = ((ContextWrapper)localContext).getBaseContext();
      if ((localContext instanceof Activity)) {
        return (Activity)localContext;
      }
    }
    throw new FacebookException("Unable to get Activity.");
  }
  
  public int getCompoundPaddingLeft()
  {
    if (this.overrideCompoundPadding) {
      return this.overrideCompoundPaddingLeft;
    }
    return super.getCompoundPaddingLeft();
  }
  
  public int getCompoundPaddingRight()
  {
    if (this.overrideCompoundPadding) {
      return this.overrideCompoundPaddingRight;
    }
    return super.getCompoundPaddingRight();
  }
  
  protected int getDefaultStyleResource()
  {
    return 0;
  }
  
  public Fragment getFragment()
  {
    return this.parentFragment;
  }
  
  public int getRequestCode()
  {
    return this.requestCode;
  }
  
  protected int measureTextWidth(String paramString)
  {
    return (int)Math.ceil(getPaint().measureText(paramString));
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    logButtonCreated(getContext());
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if ((getGravity() & 0x1) != 0) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        i = getCompoundPaddingLeft();
        int j = getCompoundPaddingRight();
        int k = getCompoundDrawablePadding();
        k = Math.min((getWidth() - (i + k) - j - measureTextWidth(getText().toString())) / 2, (i - getPaddingLeft()) / 2);
        this.overrideCompoundPaddingLeft = (i - k);
        this.overrideCompoundPaddingRight = (j + k);
        this.overrideCompoundPadding = true;
      }
      super.onDraw(paramCanvas);
      this.overrideCompoundPadding = false;
      return;
    }
  }
  
  public void setFragment(Fragment paramFragment)
  {
    this.parentFragment = paramFragment;
  }
  
  protected void setInternalOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.internalOnClickListener = paramOnClickListener;
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.externalOnClickListener = paramOnClickListener;
  }
  
  protected void setRequestCode(int paramInt)
  {
    if (FacebookSdk.isFacebookRequestCode(paramInt)) {
      throw new IllegalArgumentException("Request code " + paramInt + " cannot be within the range reserved by the Facebook SDK.");
    }
    this.requestCode = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/facebook/FacebookButtonBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */