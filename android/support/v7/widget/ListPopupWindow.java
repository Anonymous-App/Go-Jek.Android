package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;

public class ListPopupWindow
{
  private static final boolean DEBUG = false;
  private static final int EXPAND_LIST_TIMEOUT = 250;
  public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
  public static final int INPUT_METHOD_NEEDED = 1;
  public static final int INPUT_METHOD_NOT_NEEDED = 2;
  public static final int MATCH_PARENT = -1;
  public static final int POSITION_PROMPT_ABOVE = 0;
  public static final int POSITION_PROMPT_BELOW = 1;
  private static final String TAG = "ListPopupWindow";
  public static final int WRAP_CONTENT = -2;
  private static Method sClipToWindowEnabledMethod;
  private static Method sGetMaxAvailableHeightMethod;
  private ListAdapter mAdapter;
  private Context mContext;
  private boolean mDropDownAlwaysVisible = false;
  private View mDropDownAnchorView;
  private int mDropDownGravity = 0;
  private int mDropDownHeight = -2;
  private int mDropDownHorizontalOffset;
  private DropDownListView mDropDownList;
  private Drawable mDropDownListHighlight;
  private int mDropDownVerticalOffset;
  private boolean mDropDownVerticalOffsetSet;
  private int mDropDownWidth = -2;
  private int mDropDownWindowLayoutType = 1002;
  private boolean mForceIgnoreOutsideTouch = false;
  private final Handler mHandler;
  private final ListSelectorHider mHideSelector = new ListSelectorHider(null);
  private AdapterView.OnItemClickListener mItemClickListener;
  private AdapterView.OnItemSelectedListener mItemSelectedListener;
  private int mLayoutDirection;
  int mListItemExpandMaximum = Integer.MAX_VALUE;
  private boolean mModal;
  private DataSetObserver mObserver;
  private PopupWindow mPopup;
  private int mPromptPosition = 0;
  private View mPromptView;
  private final ResizePopupRunnable mResizePopupRunnable = new ResizePopupRunnable(null);
  private final PopupScrollListener mScrollListener = new PopupScrollListener(null);
  private Runnable mShowDropDownRunnable;
  private Rect mTempRect = new Rect();
  private final PopupTouchInterceptor mTouchInterceptor = new PopupTouchInterceptor(null);
  
  static
  {
    try
    {
      sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[] { Boolean.TYPE });
    }
    catch (NoSuchMethodException localNoSuchMethodException1)
    {
      for (;;)
      {
        try
        {
          sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[] { View.class, Integer.TYPE, Boolean.TYPE });
          return;
        }
        catch (NoSuchMethodException localNoSuchMethodException2)
        {
          Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        localNoSuchMethodException1 = localNoSuchMethodException1;
        Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
      }
    }
  }
  
  public ListPopupWindow(Context paramContext)
  {
    this(paramContext, null, R.attr.listPopupWindowStyle);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.listPopupWindowStyle);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public ListPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this.mContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper());
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPopupWindow, paramInt1, paramInt2);
    this.mDropDownHorizontalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
    this.mDropDownVerticalOffset = localTypedArray.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
    if (this.mDropDownVerticalOffset != 0) {
      this.mDropDownVerticalOffsetSet = true;
    }
    localTypedArray.recycle();
    this.mPopup = new AppCompatPopupWindow(paramContext, paramAttributeSet, paramInt1);
    this.mPopup.setInputMethodMode(1);
    this.mLayoutDirection = TextUtilsCompat.getLayoutDirectionFromLocale(this.mContext.getResources().getConfiguration().locale);
  }
  
  private int buildDropDown()
  {
    int j = 0;
    int i = 0;
    Object localObject3;
    Object localObject2;
    View localView;
    Object localObject1;
    label261:
    label277:
    label325:
    int k;
    if (this.mDropDownList == null)
    {
      localObject3 = this.mContext;
      this.mShowDropDownRunnable = new Runnable()
      {
        public void run()
        {
          View localView = ListPopupWindow.this.getAnchorView();
          if ((localView != null) && (localView.getWindowToken() != null)) {
            ListPopupWindow.this.show();
          }
        }
      };
      if (!this.mModal)
      {
        bool = true;
        this.mDropDownList = new DropDownListView((Context)localObject3, bool);
        if (this.mDropDownListHighlight != null) {
          this.mDropDownList.setSelector(this.mDropDownListHighlight);
        }
        this.mDropDownList.setAdapter(this.mAdapter);
        this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
        this.mDropDownList.setFocusable(true);
        this.mDropDownList.setFocusableInTouchMode(true);
        this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (paramAnonymousInt != -1)
            {
              paramAnonymousAdapterView = ListPopupWindow.this.mDropDownList;
              if (paramAnonymousAdapterView != null) {
                ListPopupWindow.DropDownListView.access$502(paramAnonymousAdapterView, false);
              }
            }
          }
          
          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
        });
        this.mDropDownList.setOnScrollListener(this.mScrollListener);
        if (this.mItemSelectedListener != null) {
          this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
        }
        localObject2 = this.mDropDownList;
        localView = this.mPromptView;
        localObject1 = localObject2;
        if (localView != null)
        {
          localObject1 = new LinearLayout((Context)localObject3);
          ((LinearLayout)localObject1).setOrientation(1);
          localObject3 = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        }
        switch (this.mPromptPosition)
        {
        default: 
          Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
          if (this.mDropDownWidth >= 0)
          {
            i = Integer.MIN_VALUE;
            j = this.mDropDownWidth;
            localView.measure(View.MeasureSpec.makeMeasureSpec(j, i), 0);
            localObject2 = (LinearLayout.LayoutParams)localView.getLayoutParams();
            i = localView.getMeasuredHeight() + ((LinearLayout.LayoutParams)localObject2).topMargin + ((LinearLayout.LayoutParams)localObject2).bottomMargin;
            this.mPopup.setContentView((View)localObject1);
            k = 0;
            localObject1 = this.mPopup.getBackground();
            if (localObject1 == null) {
              break label547;
            }
            ((Drawable)localObject1).getPadding(this.mTempRect);
            j = this.mTempRect.top + this.mTempRect.bottom;
            k = j;
            if (!this.mDropDownVerticalOffsetSet)
            {
              this.mDropDownVerticalOffset = (-this.mTempRect.top);
              k = j;
            }
            label390:
            if (this.mPopup.getInputMethodMode() != 2) {
              break label557;
            }
          }
          break;
        }
      }
    }
    int m;
    label547:
    label557:
    for (boolean bool = true;; bool = false)
    {
      m = getMaxAvailableHeight(getAnchorView(), this.mDropDownVerticalOffset, bool);
      if ((!this.mDropDownAlwaysVisible) && (this.mDropDownHeight != -1)) {
        break label563;
      }
      return m + k;
      bool = false;
      break;
      ((LinearLayout)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
      ((LinearLayout)localObject1).addView(localView);
      break label261;
      ((LinearLayout)localObject1).addView(localView);
      ((LinearLayout)localObject1).addView((View)localObject2, (ViewGroup.LayoutParams)localObject3);
      break label261;
      i = 0;
      j = 0;
      break label277;
      localObject1 = (ViewGroup)this.mPopup.getContentView();
      localObject1 = this.mPromptView;
      i = j;
      if (localObject1 == null) {
        break label325;
      }
      localObject2 = (LinearLayout.LayoutParams)((View)localObject1).getLayoutParams();
      i = ((View)localObject1).getMeasuredHeight() + ((LinearLayout.LayoutParams)localObject2).topMargin + ((LinearLayout.LayoutParams)localObject2).bottomMargin;
      break label325;
      this.mTempRect.setEmpty();
      break label390;
    }
    label563:
    switch (this.mDropDownWidth)
    {
    default: 
      j = View.MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
    }
    for (;;)
    {
      m = this.mDropDownList.measureHeightOfChildrenCompat(j, 0, -1, m - i, -1);
      j = i;
      if (m > 0) {
        j = i + k;
      }
      return m + j;
      j = View.MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), Integer.MIN_VALUE);
      continue;
      j = View.MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 1073741824);
    }
  }
  
  private int getMaxAvailableHeight(View paramView, int paramInt, boolean paramBoolean)
  {
    if (sGetMaxAvailableHeightMethod != null) {
      try
      {
        int i = ((Integer)sGetMaxAvailableHeightMethod.invoke(this.mPopup, new Object[] { paramView, Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean) })).intValue();
        return i;
      }
      catch (Exception localException)
      {
        Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
      }
    }
    return this.mPopup.getMaxAvailableHeight(paramView, paramInt);
  }
  
  private static boolean isConfirmKey(int paramInt)
  {
    return (paramInt == 66) || (paramInt == 23);
  }
  
  private void removePromptView()
  {
    if (this.mPromptView != null)
    {
      ViewParent localViewParent = this.mPromptView.getParent();
      if ((localViewParent instanceof ViewGroup)) {
        ((ViewGroup)localViewParent).removeView(this.mPromptView);
      }
    }
  }
  
  private void setPopupClipToScreenEnabled(boolean paramBoolean)
  {
    if (sClipToWindowEnabledMethod != null) {}
    try
    {
      sClipToWindowEnabledMethod.invoke(this.mPopup, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException)
    {
      Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
    }
  }
  
  public void clearListSelection()
  {
    DropDownListView localDropDownListView = this.mDropDownList;
    if (localDropDownListView != null)
    {
      DropDownListView.access$502(localDropDownListView, true);
      localDropDownListView.requestLayout();
    }
  }
  
  public View.OnTouchListener createDragToOpenListener(View paramView)
  {
    new ForwardingListener(paramView)
    {
      public ListPopupWindow getPopup()
      {
        return ListPopupWindow.this;
      }
    };
  }
  
  public void dismiss()
  {
    this.mPopup.dismiss();
    removePromptView();
    this.mPopup.setContentView(null);
    this.mDropDownList = null;
    this.mHandler.removeCallbacks(this.mResizePopupRunnable);
  }
  
  public View getAnchorView()
  {
    return this.mDropDownAnchorView;
  }
  
  public int getAnimationStyle()
  {
    return this.mPopup.getAnimationStyle();
  }
  
  public Drawable getBackground()
  {
    return this.mPopup.getBackground();
  }
  
  public int getHeight()
  {
    return this.mDropDownHeight;
  }
  
  public int getHorizontalOffset()
  {
    return this.mDropDownHorizontalOffset;
  }
  
  public int getInputMethodMode()
  {
    return this.mPopup.getInputMethodMode();
  }
  
  public ListView getListView()
  {
    return this.mDropDownList;
  }
  
  public int getPromptPosition()
  {
    return this.mPromptPosition;
  }
  
  public Object getSelectedItem()
  {
    if (!isShowing()) {
      return null;
    }
    return this.mDropDownList.getSelectedItem();
  }
  
  public long getSelectedItemId()
  {
    if (!isShowing()) {
      return Long.MIN_VALUE;
    }
    return this.mDropDownList.getSelectedItemId();
  }
  
  public int getSelectedItemPosition()
  {
    if (!isShowing()) {
      return -1;
    }
    return this.mDropDownList.getSelectedItemPosition();
  }
  
  public View getSelectedView()
  {
    if (!isShowing()) {
      return null;
    }
    return this.mDropDownList.getSelectedView();
  }
  
  public int getSoftInputMode()
  {
    return this.mPopup.getSoftInputMode();
  }
  
  public int getVerticalOffset()
  {
    if (!this.mDropDownVerticalOffsetSet) {
      return 0;
    }
    return this.mDropDownVerticalOffset;
  }
  
  public int getWidth()
  {
    return this.mDropDownWidth;
  }
  
  public boolean isDropDownAlwaysVisible()
  {
    return this.mDropDownAlwaysVisible;
  }
  
  public boolean isInputMethodNotNeeded()
  {
    return this.mPopup.getInputMethodMode() == 2;
  }
  
  public boolean isModal()
  {
    return this.mModal;
  }
  
  public boolean isShowing()
  {
    return this.mPopup.isShowing();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int m;
    int k;
    int i;
    int j;
    if ((isShowing()) && (paramInt != 62) && ((this.mDropDownList.getSelectedItemPosition() >= 0) || (!isConfirmKey(paramInt))))
    {
      m = this.mDropDownList.getSelectedItemPosition();
      ListAdapter localListAdapter;
      if (!this.mPopup.isAboveAnchor())
      {
        k = 1;
        localListAdapter = this.mAdapter;
        i = Integer.MAX_VALUE;
        j = Integer.MIN_VALUE;
        if (localListAdapter != null)
        {
          boolean bool = localListAdapter.areAllItemsEnabled();
          if (!bool) {
            break label162;
          }
          i = 0;
          label87:
          if (!bool) {
            break label175;
          }
        }
      }
      label162:
      label175:
      for (j = localListAdapter.getCount() - 1;; j = this.mDropDownList.lookForSelectablePosition(localListAdapter.getCount() - 1, false))
      {
        if (((k == 0) || (paramInt != 19) || (m > i)) && ((k != 0) || (paramInt != 20) || (m < j))) {
          break label197;
        }
        clearListSelection();
        this.mPopup.setInputMethodMode(1);
        show();
        return true;
        k = 0;
        break;
        i = this.mDropDownList.lookForSelectablePosition(0, true);
        break label87;
      }
      label197:
      DropDownListView.access$502(this.mDropDownList, false);
      if (!this.mDropDownList.onKeyDown(paramInt, paramKeyEvent)) {
        break label282;
      }
      this.mPopup.setInputMethodMode(2);
      this.mDropDownList.requestFocusFromTouch();
      show();
      switch (paramInt)
      {
      }
    }
    label282:
    do
    {
      do
      {
        return false;
        if ((k == 0) || (paramInt != 20)) {
          break;
        }
      } while (m != j);
      return true;
    } while ((k != 0) || (paramInt != 19) || (m != i));
    return true;
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (isShowing()))
    {
      Object localObject = this.mDropDownAnchorView;
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
      {
        localObject = ((View)localObject).getKeyDispatcherState();
        if (localObject != null) {
          ((KeyEvent.DispatcherState)localObject).startTracking(paramKeyEvent, this);
        }
        return true;
      }
      if (paramKeyEvent.getAction() == 1)
      {
        localObject = ((View)localObject).getKeyDispatcherState();
        if (localObject != null) {
          ((KeyEvent.DispatcherState)localObject).handleUpEvent(paramKeyEvent);
        }
        if ((paramKeyEvent.isTracking()) && (!paramKeyEvent.isCanceled()))
        {
          dismiss();
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((isShowing()) && (this.mDropDownList.getSelectedItemPosition() >= 0))
    {
      boolean bool = this.mDropDownList.onKeyUp(paramInt, paramKeyEvent);
      if ((bool) && (isConfirmKey(paramInt))) {
        dismiss();
      }
      return bool;
    }
    return false;
  }
  
  public boolean performItemClick(int paramInt)
  {
    if (isShowing())
    {
      if (this.mItemClickListener != null)
      {
        DropDownListView localDropDownListView = this.mDropDownList;
        View localView = localDropDownListView.getChildAt(paramInt - localDropDownListView.getFirstVisiblePosition());
        ListAdapter localListAdapter = localDropDownListView.getAdapter();
        this.mItemClickListener.onItemClick(localDropDownListView, localView, paramInt, localListAdapter.getItemId(paramInt));
      }
      return true;
    }
    return false;
  }
  
  public void postShow()
  {
    this.mHandler.post(this.mShowDropDownRunnable);
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (this.mObserver == null) {
      this.mObserver = new PopupDataSetObserver(null);
    }
    for (;;)
    {
      this.mAdapter = paramListAdapter;
      if (this.mAdapter != null) {
        paramListAdapter.registerDataSetObserver(this.mObserver);
      }
      if (this.mDropDownList != null) {
        this.mDropDownList.setAdapter(this.mAdapter);
      }
      return;
      if (this.mAdapter != null) {
        this.mAdapter.unregisterDataSetObserver(this.mObserver);
      }
    }
  }
  
  public void setAnchorView(View paramView)
  {
    this.mDropDownAnchorView = paramView;
  }
  
  public void setAnimationStyle(int paramInt)
  {
    this.mPopup.setAnimationStyle(paramInt);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    this.mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setContentWidth(int paramInt)
  {
    Drawable localDrawable = this.mPopup.getBackground();
    if (localDrawable != null)
    {
      localDrawable.getPadding(this.mTempRect);
      this.mDropDownWidth = (this.mTempRect.left + this.mTempRect.right + paramInt);
      return;
    }
    setWidth(paramInt);
  }
  
  public void setDropDownAlwaysVisible(boolean paramBoolean)
  {
    this.mDropDownAlwaysVisible = paramBoolean;
  }
  
  public void setDropDownGravity(int paramInt)
  {
    this.mDropDownGravity = paramInt;
  }
  
  public void setForceIgnoreOutsideTouch(boolean paramBoolean)
  {
    this.mForceIgnoreOutsideTouch = paramBoolean;
  }
  
  public void setHeight(int paramInt)
  {
    this.mDropDownHeight = paramInt;
  }
  
  public void setHorizontalOffset(int paramInt)
  {
    this.mDropDownHorizontalOffset = paramInt;
  }
  
  public void setInputMethodMode(int paramInt)
  {
    this.mPopup.setInputMethodMode(paramInt);
  }
  
  void setListItemExpandMax(int paramInt)
  {
    this.mListItemExpandMaximum = paramInt;
  }
  
  public void setListSelector(Drawable paramDrawable)
  {
    this.mDropDownListHighlight = paramDrawable;
  }
  
  public void setModal(boolean paramBoolean)
  {
    this.mModal = paramBoolean;
    this.mPopup.setFocusable(paramBoolean);
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    this.mPopup.setOnDismissListener(paramOnDismissListener);
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.mItemClickListener = paramOnItemClickListener;
  }
  
  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    this.mItemSelectedListener = paramOnItemSelectedListener;
  }
  
  public void setPromptPosition(int paramInt)
  {
    this.mPromptPosition = paramInt;
  }
  
  public void setPromptView(View paramView)
  {
    boolean bool = isShowing();
    if (bool) {
      removePromptView();
    }
    this.mPromptView = paramView;
    if (bool) {
      show();
    }
  }
  
  public void setSelection(int paramInt)
  {
    DropDownListView localDropDownListView = this.mDropDownList;
    if ((isShowing()) && (localDropDownListView != null))
    {
      DropDownListView.access$502(localDropDownListView, false);
      localDropDownListView.setSelection(paramInt);
      if ((Build.VERSION.SDK_INT >= 11) && (localDropDownListView.getChoiceMode() != 0)) {
        localDropDownListView.setItemChecked(paramInt, true);
      }
    }
  }
  
  public void setSoftInputMode(int paramInt)
  {
    this.mPopup.setSoftInputMode(paramInt);
  }
  
  public void setVerticalOffset(int paramInt)
  {
    this.mDropDownVerticalOffset = paramInt;
    this.mDropDownVerticalOffsetSet = true;
  }
  
  public void setWidth(int paramInt)
  {
    this.mDropDownWidth = paramInt;
  }
  
  public void setWindowLayoutType(int paramInt)
  {
    this.mDropDownWindowLayoutType = paramInt;
  }
  
  public void show()
  {
    boolean bool1 = true;
    boolean bool2 = false;
    int m = -1;
    int j = buildDropDown();
    boolean bool3 = isInputMethodNotNeeded();
    PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
    int i;
    label64:
    PopupWindow localPopupWindow;
    if (this.mPopup.isShowing())
    {
      int k;
      label85:
      label99:
      View localView;
      int n;
      if (this.mDropDownWidth == -1)
      {
        i = -1;
        if (this.mDropDownHeight != -1) {
          break label262;
        }
        if (!bool3) {
          break label214;
        }
        if (!bool3) {
          break label224;
        }
        localPopupWindow = this.mPopup;
        if (this.mDropDownWidth != -1) {
          break label219;
        }
        k = -1;
        localPopupWindow.setWidth(k);
        this.mPopup.setHeight(0);
        localPopupWindow = this.mPopup;
        bool1 = bool2;
        if (!this.mForceIgnoreOutsideTouch)
        {
          bool1 = bool2;
          if (!this.mDropDownAlwaysVisible) {
            bool1 = true;
          }
        }
        localPopupWindow.setOutsideTouchable(bool1);
        localPopupWindow = this.mPopup;
        localView = getAnchorView();
        k = this.mDropDownHorizontalOffset;
        n = this.mDropDownVerticalOffset;
        if (i >= 0) {
          break label282;
        }
        i = -1;
        label166:
        if (j >= 0) {
          break label285;
        }
        j = m;
      }
      label214:
      label219:
      label224:
      label262:
      label282:
      label285:
      for (;;)
      {
        localPopupWindow.update(localView, k, n, i, j);
        return;
        if (this.mDropDownWidth == -2)
        {
          i = getAnchorView().getWidth();
          break;
        }
        i = this.mDropDownWidth;
        break;
        j = -1;
        break label64;
        k = 0;
        break label85;
        localPopupWindow = this.mPopup;
        if (this.mDropDownWidth == -1) {}
        for (k = -1;; k = 0)
        {
          localPopupWindow.setWidth(k);
          this.mPopup.setHeight(-1);
          break;
        }
        if (this.mDropDownHeight == -2) {
          break label99;
        }
        j = this.mDropDownHeight;
        break label99;
        break label166;
      }
    }
    if (this.mDropDownWidth == -1)
    {
      i = -1;
      label298:
      if (this.mDropDownHeight != -1) {
        break label467;
      }
      j = -1;
      label308:
      this.mPopup.setWidth(i);
      this.mPopup.setHeight(j);
      setPopupClipToScreenEnabled(true);
      localPopupWindow = this.mPopup;
      if ((this.mForceIgnoreOutsideTouch) || (this.mDropDownAlwaysVisible)) {
        break label487;
      }
    }
    for (;;)
    {
      localPopupWindow.setOutsideTouchable(bool1);
      this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
      PopupWindowCompat.showAsDropDown(this.mPopup, getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
      this.mDropDownList.setSelection(-1);
      if ((!this.mModal) || (this.mDropDownList.isInTouchMode())) {
        clearListSelection();
      }
      if (this.mModal) {
        break;
      }
      this.mHandler.post(this.mHideSelector);
      return;
      if (this.mDropDownWidth == -2)
      {
        i = getAnchorView().getWidth();
        break label298;
      }
      i = this.mDropDownWidth;
      break label298;
      label467:
      if (this.mDropDownHeight == -2) {
        break label308;
      }
      j = this.mDropDownHeight;
      break label308;
      label487:
      bool1 = false;
    }
  }
  
  private static class DropDownListView
    extends ListViewCompat
  {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ListViewAutoScrollHelper mScrollHelper;
    
    public DropDownListView(Context paramContext, boolean paramBoolean)
    {
      super(null, R.attr.dropDownListViewStyle);
      this.mHijackFocus = paramBoolean;
      setCacheColorHint(0);
    }
    
    private void clearPressedItem()
    {
      this.mDrawsInPressedState = false;
      setPressed(false);
      drawableStateChanged();
      View localView = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
      if (localView != null) {
        localView.setPressed(false);
      }
      if (this.mClickAnimation != null)
      {
        this.mClickAnimation.cancel();
        this.mClickAnimation = null;
      }
    }
    
    private void clickPressedItem(View paramView, int paramInt)
    {
      performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
    }
    
    private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2)
    {
      this.mDrawsInPressedState = true;
      if (Build.VERSION.SDK_INT >= 21) {
        drawableHotspotChanged(paramFloat1, paramFloat2);
      }
      if (!isPressed()) {
        setPressed(true);
      }
      layoutChildren();
      if (this.mMotionPosition != -1)
      {
        View localView = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
        if ((localView != null) && (localView != paramView) && (localView.isPressed())) {
          localView.setPressed(false);
        }
      }
      this.mMotionPosition = paramInt;
      float f1 = paramView.getLeft();
      float f2 = paramView.getTop();
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.drawableHotspotChanged(paramFloat1 - f1, paramFloat2 - f2);
      }
      if (!paramView.isPressed()) {
        paramView.setPressed(true);
      }
      setSelection(paramInt);
      positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
      setSelectorEnabled(false);
      refreshDrawableState();
    }
    
    public boolean hasFocus()
    {
      return (this.mHijackFocus) || (super.hasFocus());
    }
    
    public boolean hasWindowFocus()
    {
      return (this.mHijackFocus) || (super.hasWindowFocus());
    }
    
    public boolean isFocused()
    {
      return (this.mHijackFocus) || (super.isFocused());
    }
    
    public boolean isInTouchMode()
    {
      return ((this.mHijackFocus) && (this.mListSelectionHidden)) || (super.isInTouchMode());
    }
    
    public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt)
    {
      boolean bool1 = true;
      boolean bool2 = true;
      int i = 0;
      int j = MotionEventCompat.getActionMasked(paramMotionEvent);
      switch (j)
      {
      default: 
        bool1 = bool2;
        paramInt = i;
        if ((!bool1) || (paramInt != 0)) {
          clearPressedItem();
        }
        if (bool1)
        {
          if (this.mScrollHelper == null) {
            this.mScrollHelper = new ListViewAutoScrollHelper(this);
          }
          this.mScrollHelper.setEnabled(true);
          this.mScrollHelper.onTouch(this, paramMotionEvent);
        }
        break;
      }
      while (this.mScrollHelper == null)
      {
        return bool1;
        bool1 = false;
        paramInt = i;
        break;
        bool1 = false;
        int k = paramMotionEvent.findPointerIndex(paramInt);
        if (k < 0)
        {
          bool1 = false;
          paramInt = i;
          break;
        }
        paramInt = (int)paramMotionEvent.getX(k);
        int m = (int)paramMotionEvent.getY(k);
        k = pointToPosition(paramInt, m);
        if (k == -1)
        {
          paramInt = 1;
          break;
        }
        View localView = getChildAt(k - getFirstVisiblePosition());
        setPressedItem(localView, k, paramInt, m);
        bool2 = true;
        paramInt = i;
        bool1 = bool2;
        if (j != 1) {
          break;
        }
        clickPressedItem(localView, k);
        paramInt = i;
        bool1 = bool2;
        break;
      }
      this.mScrollHelper.setEnabled(false);
      return bool1;
    }
    
    protected boolean touchModeDrawsInPressedStateCompat()
    {
      return (this.mDrawsInPressedState) || (super.touchModeDrawsInPressedStateCompat());
    }
  }
  
  public static abstract class ForwardingListener
    implements View.OnTouchListener
  {
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    private final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation = new int[2];
    private Runnable mTriggerLongPress;
    private boolean mWasLongPress;
    
    public ForwardingListener(View paramView)
    {
      this.mSrc = paramView;
      this.mScaledTouchSlop = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
      this.mTapTimeout = ViewConfiguration.getTapTimeout();
      this.mLongPressTimeout = ((this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2);
    }
    
    private void clearCallbacks()
    {
      if (this.mTriggerLongPress != null) {
        this.mSrc.removeCallbacks(this.mTriggerLongPress);
      }
      if (this.mDisallowIntercept != null) {
        this.mSrc.removeCallbacks(this.mDisallowIntercept);
      }
    }
    
    private void onLongPress()
    {
      clearCallbacks();
      View localView = this.mSrc;
      if ((!localView.isEnabled()) || (localView.isLongClickable())) {}
      while (!onForwardingStarted()) {
        return;
      }
      localView.getParent().requestDisallowInterceptTouchEvent(true);
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
      localView.onTouchEvent(localMotionEvent);
      localMotionEvent.recycle();
      this.mForwarding = true;
      this.mWasLongPress = true;
    }
    
    private boolean onTouchForwarded(MotionEvent paramMotionEvent)
    {
      boolean bool1 = true;
      View localView = this.mSrc;
      Object localObject = getPopup();
      if ((localObject == null) || (!((ListPopupWindow)localObject).isShowing())) {}
      do
      {
        return false;
        localObject = ((ListPopupWindow)localObject).mDropDownList;
      } while ((localObject == null) || (!((ListPopupWindow.DropDownListView)localObject).isShown()));
      MotionEvent localMotionEvent = MotionEvent.obtainNoHistory(paramMotionEvent);
      toGlobalMotionEvent(localView, localMotionEvent);
      toLocalMotionEvent((View)localObject, localMotionEvent);
      boolean bool2 = ((ListPopupWindow.DropDownListView)localObject).onForwardedEvent(localMotionEvent, this.mActivePointerId);
      localMotionEvent.recycle();
      int i = MotionEventCompat.getActionMasked(paramMotionEvent);
      if ((i != 1) && (i != 3))
      {
        i = 1;
        if ((!bool2) || (i == 0)) {
          break label124;
        }
      }
      for (;;)
      {
        return bool1;
        i = 0;
        break;
        label124:
        bool1 = false;
      }
    }
    
    private boolean onTouchObserved(MotionEvent paramMotionEvent)
    {
      View localView = this.mSrc;
      if (!localView.isEnabled()) {}
      int i;
      do
      {
        return false;
        switch (MotionEventCompat.getActionMasked(paramMotionEvent))
        {
        default: 
          return false;
        case 0: 
          this.mActivePointerId = paramMotionEvent.getPointerId(0);
          this.mWasLongPress = false;
          if (this.mDisallowIntercept == null) {
            this.mDisallowIntercept = new DisallowIntercept(null);
          }
          localView.postDelayed(this.mDisallowIntercept, this.mTapTimeout);
          if (this.mTriggerLongPress == null) {
            this.mTriggerLongPress = new TriggerLongPress(null);
          }
          localView.postDelayed(this.mTriggerLongPress, this.mLongPressTimeout);
          return false;
        case 2: 
          i = paramMotionEvent.findPointerIndex(this.mActivePointerId);
        }
      } while ((i < 0) || (pointInView(localView, paramMotionEvent.getX(i), paramMotionEvent.getY(i), this.mScaledTouchSlop)));
      clearCallbacks();
      localView.getParent().requestDisallowInterceptTouchEvent(true);
      return true;
      clearCallbacks();
      return false;
    }
    
    private static boolean pointInView(View paramView, float paramFloat1, float paramFloat2, float paramFloat3)
    {
      return (paramFloat1 >= -paramFloat3) && (paramFloat2 >= -paramFloat3) && (paramFloat1 < paramView.getRight() - paramView.getLeft() + paramFloat3) && (paramFloat2 < paramView.getBottom() - paramView.getTop() + paramFloat3);
    }
    
    private boolean toGlobalMotionEvent(View paramView, MotionEvent paramMotionEvent)
    {
      int[] arrayOfInt = this.mTmpLocation;
      paramView.getLocationOnScreen(arrayOfInt);
      paramMotionEvent.offsetLocation(arrayOfInt[0], arrayOfInt[1]);
      return true;
    }
    
    private boolean toLocalMotionEvent(View paramView, MotionEvent paramMotionEvent)
    {
      int[] arrayOfInt = this.mTmpLocation;
      paramView.getLocationOnScreen(arrayOfInt);
      paramMotionEvent.offsetLocation(-arrayOfInt[0], -arrayOfInt[1]);
      return true;
    }
    
    public abstract ListPopupWindow getPopup();
    
    protected boolean onForwardingStarted()
    {
      ListPopupWindow localListPopupWindow = getPopup();
      if ((localListPopupWindow != null) && (!localListPopupWindow.isShowing())) {
        localListPopupWindow.show();
      }
      return true;
    }
    
    protected boolean onForwardingStopped()
    {
      ListPopupWindow localListPopupWindow = getPopup();
      if ((localListPopupWindow != null) && (localListPopupWindow.isShowing())) {
        localListPopupWindow.dismiss();
      }
      return true;
    }
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      boolean bool3 = false;
      boolean bool4 = this.mForwarding;
      boolean bool1;
      if (bool4)
      {
        if (this.mWasLongPress)
        {
          bool1 = onTouchForwarded(paramMotionEvent);
          this.mForwarding = bool1;
          if (!bool1)
          {
            bool1 = bool3;
            if (!bool4) {}
          }
          else
          {
            bool1 = true;
          }
          return bool1;
        }
        if ((onTouchForwarded(paramMotionEvent)) || (!onForwardingStopped())) {}
        for (bool1 = true;; bool1 = false) {
          break;
        }
      }
      if ((onTouchObserved(paramMotionEvent)) && (onForwardingStarted())) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool1 = bool2;
        if (!bool2) {
          break;
        }
        long l = SystemClock.uptimeMillis();
        paramView = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        this.mSrc.onTouchEvent(paramView);
        paramView.recycle();
        bool1 = bool2;
        break;
      }
    }
    
    private class DisallowIntercept
      implements Runnable
    {
      private DisallowIntercept() {}
      
      public void run()
      {
        ListPopupWindow.ForwardingListener.this.mSrc.getParent().requestDisallowInterceptTouchEvent(true);
      }
    }
    
    private class TriggerLongPress
      implements Runnable
    {
      private TriggerLongPress() {}
      
      public void run()
      {
        ListPopupWindow.ForwardingListener.this.onLongPress();
      }
    }
  }
  
  private class ListSelectorHider
    implements Runnable
  {
    private ListSelectorHider() {}
    
    public void run()
    {
      ListPopupWindow.this.clearListSelection();
    }
  }
  
  private class PopupDataSetObserver
    extends DataSetObserver
  {
    private PopupDataSetObserver() {}
    
    public void onChanged()
    {
      if (ListPopupWindow.this.isShowing()) {
        ListPopupWindow.this.show();
      }
    }
    
    public void onInvalidated()
    {
      ListPopupWindow.this.dismiss();
    }
  }
  
  private class PopupScrollListener
    implements AbsListView.OnScrollListener
  {
    private PopupScrollListener() {}
    
    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if ((paramInt == 1) && (!ListPopupWindow.this.isInputMethodNotNeeded()) && (ListPopupWindow.this.mPopup.getContentView() != null))
      {
        ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
        ListPopupWindow.this.mResizePopupRunnable.run();
      }
    }
  }
  
  private class PopupTouchInterceptor
    implements View.OnTouchListener
  {
    private PopupTouchInterceptor() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      if ((i == 0) && (ListPopupWindow.this.mPopup != null) && (ListPopupWindow.this.mPopup.isShowing()) && (j >= 0) && (j < ListPopupWindow.this.mPopup.getWidth()) && (k >= 0) && (k < ListPopupWindow.this.mPopup.getHeight())) {
        ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 250L);
      }
      for (;;)
      {
        return false;
        if (i == 1) {
          ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
        }
      }
    }
  }
  
  private class ResizePopupRunnable
    implements Runnable
  {
    private ResizePopupRunnable() {}
    
    public void run()
    {
      if ((ListPopupWindow.this.mDropDownList != null) && (ViewCompat.isAttachedToWindow(ListPopupWindow.this.mDropDownList)) && (ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) && (ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum))
      {
        ListPopupWindow.this.mPopup.setInputMethodMode(2);
        ListPopupWindow.this.show();
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/ListPopupWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */