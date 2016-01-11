package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.string;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

public class ActivityChooserView
  extends ViewGroup
  implements ActivityChooserModel.ActivityChooserModelClient
{
  private static final String LOG_TAG = "ActivityChooserView";
  private final LinearLayoutCompat mActivityChooserContent;
  private final Drawable mActivityChooserContentBackground;
  private final ActivityChooserViewAdapter mAdapter;
  private final Callbacks mCallbacks;
  private int mDefaultActionButtonContentDescription;
  private final FrameLayout mDefaultActivityButton;
  private final ImageView mDefaultActivityButtonImage;
  private final FrameLayout mExpandActivityOverflowButton;
  private final ImageView mExpandActivityOverflowButtonImage;
  private int mInitialActivityCount = 4;
  private boolean mIsAttachedToWindow;
  private boolean mIsSelectingDefaultActivity;
  private final int mListPopupMaxWidth;
  private ListPopupWindow mListPopupWindow;
  private final DataSetObserver mModelDataSetOberver = new DataSetObserver()
  {
    public void onChanged()
    {
      super.onChanged();
      ActivityChooserView.this.mAdapter.notifyDataSetChanged();
    }
    
    public void onInvalidated()
    {
      super.onInvalidated();
      ActivityChooserView.this.mAdapter.notifyDataSetInvalidated();
    }
  };
  private PopupWindow.OnDismissListener mOnDismissListener;
  private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener()
  {
    public void onGlobalLayout()
    {
      if (ActivityChooserView.this.isShowingPopup())
      {
        if (ActivityChooserView.this.isShown()) {
          break label31;
        }
        ActivityChooserView.this.getListPopupWindow().dismiss();
      }
      label31:
      do
      {
        return;
        ActivityChooserView.this.getListPopupWindow().show();
      } while (ActivityChooserView.this.mProvider == null);
      ActivityChooserView.this.mProvider.subUiVisibilityChanged(true);
    }
  };
  ActionProvider mProvider;
  
  public ActivityChooserView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActivityChooserView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ActivityChooserView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Object localObject = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActivityChooserView, paramInt, 0);
    this.mInitialActivityCount = ((TypedArray)localObject).getInt(R.styleable.ActivityChooserView_initialActivityCount, 4);
    paramAttributeSet = ((TypedArray)localObject).getDrawable(R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
    ((TypedArray)localObject).recycle();
    LayoutInflater.from(getContext()).inflate(R.layout.abc_activity_chooser_view, this, true);
    this.mCallbacks = new Callbacks(null);
    this.mActivityChooserContent = ((LinearLayoutCompat)findViewById(R.id.activity_chooser_view_content));
    this.mActivityChooserContentBackground = this.mActivityChooserContent.getBackground();
    this.mDefaultActivityButton = ((FrameLayout)findViewById(R.id.default_activity_button));
    this.mDefaultActivityButton.setOnClickListener(this.mCallbacks);
    this.mDefaultActivityButton.setOnLongClickListener(this.mCallbacks);
    this.mDefaultActivityButtonImage = ((ImageView)this.mDefaultActivityButton.findViewById(R.id.image));
    localObject = (FrameLayout)findViewById(R.id.expand_activities_button);
    ((FrameLayout)localObject).setOnClickListener(this.mCallbacks);
    ((FrameLayout)localObject).setOnTouchListener(new ListPopupWindow.ForwardingListener((View)localObject)
    {
      public ListPopupWindow getPopup()
      {
        return ActivityChooserView.this.getListPopupWindow();
      }
      
      protected boolean onForwardingStarted()
      {
        ActivityChooserView.this.showPopup();
        return true;
      }
      
      protected boolean onForwardingStopped()
      {
        ActivityChooserView.this.dismissPopup();
        return true;
      }
    });
    this.mExpandActivityOverflowButton = ((FrameLayout)localObject);
    this.mExpandActivityOverflowButtonImage = ((ImageView)((FrameLayout)localObject).findViewById(R.id.image));
    this.mExpandActivityOverflowButtonImage.setImageDrawable(paramAttributeSet);
    this.mAdapter = new ActivityChooserViewAdapter(null);
    this.mAdapter.registerDataSetObserver(new DataSetObserver()
    {
      public void onChanged()
      {
        super.onChanged();
        ActivityChooserView.this.updateAppearance();
      }
    });
    paramContext = paramContext.getResources();
    this.mListPopupMaxWidth = Math.max(paramContext.getDisplayMetrics().widthPixels / 2, paramContext.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
  }
  
  private ListPopupWindow getListPopupWindow()
  {
    if (this.mListPopupWindow == null)
    {
      this.mListPopupWindow = new ListPopupWindow(getContext());
      this.mListPopupWindow.setAdapter(this.mAdapter);
      this.mListPopupWindow.setAnchorView(this);
      this.mListPopupWindow.setModal(true);
      this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
      this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
    }
    return this.mListPopupWindow;
  }
  
  private void showPopupUnchecked(int paramInt)
  {
    if (this.mAdapter.getDataModel() == null) {
      throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }
    getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
    boolean bool;
    int i;
    label60:
    label92:
    ListPopupWindow localListPopupWindow;
    if (this.mDefaultActivityButton.getVisibility() == 0)
    {
      bool = true;
      int j = this.mAdapter.getActivityCount();
      if (!bool) {
        break label192;
      }
      i = 1;
      if ((paramInt == Integer.MAX_VALUE) || (j <= paramInt + i)) {
        break label197;
      }
      this.mAdapter.setShowFooterView(true);
      this.mAdapter.setMaxActivityCount(paramInt - 1);
      localListPopupWindow = getListPopupWindow();
      if (!localListPopupWindow.isShowing())
      {
        if ((!this.mIsSelectingDefaultActivity) && (bool)) {
          break label216;
        }
        this.mAdapter.setShowDefaultActivity(true, bool);
      }
    }
    for (;;)
    {
      localListPopupWindow.setContentWidth(Math.min(this.mAdapter.measureContentWidth(), this.mListPopupMaxWidth));
      localListPopupWindow.show();
      if (this.mProvider != null) {
        this.mProvider.subUiVisibilityChanged(true);
      }
      localListPopupWindow.getListView().setContentDescription(getContext().getString(R.string.abc_activitychooserview_choose_application));
      return;
      bool = false;
      break;
      label192:
      i = 0;
      break label60;
      label197:
      this.mAdapter.setShowFooterView(false);
      this.mAdapter.setMaxActivityCount(paramInt);
      break label92;
      label216:
      this.mAdapter.setShowDefaultActivity(false, false);
    }
  }
  
  private void updateAppearance()
  {
    if (this.mAdapter.getCount() > 0)
    {
      this.mExpandActivityOverflowButton.setEnabled(true);
      int i = this.mAdapter.getActivityCount();
      int j = this.mAdapter.getHistorySize();
      if ((i != 1) && ((i <= 1) || (j <= 0))) {
        break label161;
      }
      this.mDefaultActivityButton.setVisibility(0);
      Object localObject = this.mAdapter.getDefaultActivity();
      PackageManager localPackageManager = getContext().getPackageManager();
      this.mDefaultActivityButtonImage.setImageDrawable(((ResolveInfo)localObject).loadIcon(localPackageManager));
      if (this.mDefaultActionButtonContentDescription != 0)
      {
        localObject = ((ResolveInfo)localObject).loadLabel(localPackageManager);
        localObject = getContext().getString(this.mDefaultActionButtonContentDescription, new Object[] { localObject });
        this.mDefaultActivityButton.setContentDescription((CharSequence)localObject);
      }
    }
    for (;;)
    {
      if (this.mDefaultActivityButton.getVisibility() != 0) {
        break label173;
      }
      this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
      return;
      this.mExpandActivityOverflowButton.setEnabled(false);
      break;
      label161:
      this.mDefaultActivityButton.setVisibility(8);
    }
    label173:
    this.mActivityChooserContent.setBackgroundDrawable(null);
  }
  
  public boolean dismissPopup()
  {
    if (isShowingPopup())
    {
      getListPopupWindow().dismiss();
      ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
      if (localViewTreeObserver.isAlive()) {
        localViewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
      }
    }
    return true;
  }
  
  public ActivityChooserModel getDataModel()
  {
    return this.mAdapter.getDataModel();
  }
  
  public boolean isShowingPopup()
  {
    return getListPopupWindow().isShowing();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    ActivityChooserModel localActivityChooserModel = this.mAdapter.getDataModel();
    if (localActivityChooserModel != null) {
      localActivityChooserModel.registerObserver(this.mModelDataSetOberver);
    }
    this.mIsAttachedToWindow = true;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Object localObject = this.mAdapter.getDataModel();
    if (localObject != null) {
      ((ActivityChooserModel)localObject).unregisterObserver(this.mModelDataSetOberver);
    }
    localObject = getViewTreeObserver();
    if (((ViewTreeObserver)localObject).isAlive()) {
      ((ViewTreeObserver)localObject).removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
    }
    if (isShowingPopup()) {
      dismissPopup();
    }
    this.mIsAttachedToWindow = false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mActivityChooserContent.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    if (!isShowingPopup()) {
      dismissPopup();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    LinearLayoutCompat localLinearLayoutCompat = this.mActivityChooserContent;
    int i = paramInt2;
    if (this.mDefaultActivityButton.getVisibility() != 0) {
      i = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt2), 1073741824);
    }
    measureChild(localLinearLayoutCompat, paramInt1, i);
    setMeasuredDimension(localLinearLayoutCompat.getMeasuredWidth(), localLinearLayoutCompat.getMeasuredHeight());
  }
  
  public void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel)
  {
    this.mAdapter.setDataModel(paramActivityChooserModel);
    if (isShowingPopup())
    {
      dismissPopup();
      showPopup();
    }
  }
  
  public void setDefaultActionButtonContentDescription(int paramInt)
  {
    this.mDefaultActionButtonContentDescription = paramInt;
  }
  
  public void setExpandActivityOverflowButtonContentDescription(int paramInt)
  {
    String str = getContext().getString(paramInt);
    this.mExpandActivityOverflowButtonImage.setContentDescription(str);
  }
  
  public void setExpandActivityOverflowButtonDrawable(Drawable paramDrawable)
  {
    this.mExpandActivityOverflowButtonImage.setImageDrawable(paramDrawable);
  }
  
  public void setInitialActivityCount(int paramInt)
  {
    this.mInitialActivityCount = paramInt;
  }
  
  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    this.mOnDismissListener = paramOnDismissListener;
  }
  
  public void setProvider(ActionProvider paramActionProvider)
  {
    this.mProvider = paramActionProvider;
  }
  
  public boolean showPopup()
  {
    if ((isShowingPopup()) || (!this.mIsAttachedToWindow)) {
      return false;
    }
    this.mIsSelectingDefaultActivity = false;
    showPopupUnchecked(this.mInitialActivityCount);
    return true;
  }
  
  private class ActivityChooserViewAdapter
    extends BaseAdapter
  {
    private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
    private static final int ITEM_VIEW_TYPE_COUNT = 3;
    private static final int ITEM_VIEW_TYPE_FOOTER = 1;
    public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
    public static final int MAX_ACTIVITY_COUNT_UNLIMITED = Integer.MAX_VALUE;
    private ActivityChooserModel mDataModel;
    private boolean mHighlightDefaultActivity;
    private int mMaxActivityCount = 4;
    private boolean mShowDefaultActivity;
    private boolean mShowFooterView;
    
    private ActivityChooserViewAdapter() {}
    
    public int getActivityCount()
    {
      return this.mDataModel.getActivityCount();
    }
    
    public int getCount()
    {
      int j = this.mDataModel.getActivityCount();
      int i = j;
      if (!this.mShowDefaultActivity)
      {
        i = j;
        if (this.mDataModel.getDefaultActivity() != null) {
          i = j - 1;
        }
      }
      j = Math.min(i, this.mMaxActivityCount);
      i = j;
      if (this.mShowFooterView) {
        i = j + 1;
      }
      return i;
    }
    
    public ActivityChooserModel getDataModel()
    {
      return this.mDataModel;
    }
    
    public ResolveInfo getDefaultActivity()
    {
      return this.mDataModel.getDefaultActivity();
    }
    
    public int getHistorySize()
    {
      return this.mDataModel.getHistorySize();
    }
    
    public Object getItem(int paramInt)
    {
      switch (getItemViewType(paramInt))
      {
      default: 
        throw new IllegalArgumentException();
      case 1: 
        return null;
      }
      int i = paramInt;
      if (!this.mShowDefaultActivity)
      {
        i = paramInt;
        if (this.mDataModel.getDefaultActivity() != null) {
          i = paramInt + 1;
        }
      }
      return this.mDataModel.getActivity(i);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public int getItemViewType(int paramInt)
    {
      if ((this.mShowFooterView) && (paramInt == getCount() - 1)) {
        return 1;
      }
      return 0;
    }
    
    public boolean getShowDefaultActivity()
    {
      return this.mShowDefaultActivity;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView;
      switch (getItemViewType(paramInt))
      {
      default: 
        throw new IllegalArgumentException();
      case 1: 
        if (paramView != null)
        {
          localView = paramView;
          if (paramView.getId() == 1) {}
        }
        else
        {
          localView = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, paramViewGroup, false);
          localView.setId(1);
          ((TextView)localView.findViewById(R.id.title)).setText(ActivityChooserView.this.getContext().getString(R.string.abc_activity_chooser_view_see_all));
        }
        return localView;
      }
      if (paramView != null)
      {
        localView = paramView;
        if (paramView.getId() == R.id.list_item) {}
      }
      else
      {
        localView = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, paramViewGroup, false);
      }
      paramView = ActivityChooserView.this.getContext().getPackageManager();
      paramViewGroup = (ImageView)localView.findViewById(R.id.icon);
      ResolveInfo localResolveInfo = (ResolveInfo)getItem(paramInt);
      paramViewGroup.setImageDrawable(localResolveInfo.loadIcon(paramView));
      ((TextView)localView.findViewById(R.id.title)).setText(localResolveInfo.loadLabel(paramView));
      if ((this.mShowDefaultActivity) && (paramInt == 0) && (this.mHighlightDefaultActivity)) {
        ViewCompat.setActivated(localView, true);
      }
      for (;;)
      {
        return localView;
        ViewCompat.setActivated(localView, false);
      }
    }
    
    public int getViewTypeCount()
    {
      return 3;
    }
    
    public int measureContentWidth()
    {
      int k = this.mMaxActivityCount;
      this.mMaxActivityCount = Integer.MAX_VALUE;
      int j = 0;
      View localView = null;
      int m = View.MeasureSpec.makeMeasureSpec(0, 0);
      int n = View.MeasureSpec.makeMeasureSpec(0, 0);
      int i1 = getCount();
      int i = 0;
      while (i < i1)
      {
        localView = getView(i, localView, null);
        localView.measure(m, n);
        j = Math.max(j, localView.getMeasuredWidth());
        i += 1;
      }
      this.mMaxActivityCount = k;
      return j;
    }
    
    public void setDataModel(ActivityChooserModel paramActivityChooserModel)
    {
      ActivityChooserModel localActivityChooserModel = ActivityChooserView.this.mAdapter.getDataModel();
      if ((localActivityChooserModel != null) && (ActivityChooserView.this.isShown())) {
        localActivityChooserModel.unregisterObserver(ActivityChooserView.this.mModelDataSetOberver);
      }
      this.mDataModel = paramActivityChooserModel;
      if ((paramActivityChooserModel != null) && (ActivityChooserView.this.isShown())) {
        paramActivityChooserModel.registerObserver(ActivityChooserView.this.mModelDataSetOberver);
      }
      notifyDataSetChanged();
    }
    
    public void setMaxActivityCount(int paramInt)
    {
      if (this.mMaxActivityCount != paramInt)
      {
        this.mMaxActivityCount = paramInt;
        notifyDataSetChanged();
      }
    }
    
    public void setShowDefaultActivity(boolean paramBoolean1, boolean paramBoolean2)
    {
      if ((this.mShowDefaultActivity != paramBoolean1) || (this.mHighlightDefaultActivity != paramBoolean2))
      {
        this.mShowDefaultActivity = paramBoolean1;
        this.mHighlightDefaultActivity = paramBoolean2;
        notifyDataSetChanged();
      }
    }
    
    public void setShowFooterView(boolean paramBoolean)
    {
      if (this.mShowFooterView != paramBoolean)
      {
        this.mShowFooterView = paramBoolean;
        notifyDataSetChanged();
      }
    }
  }
  
  private class Callbacks
    implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener
  {
    private Callbacks() {}
    
    private void notifyOnDismissListener()
    {
      if (ActivityChooserView.this.mOnDismissListener != null) {
        ActivityChooserView.this.mOnDismissListener.onDismiss();
      }
    }
    
    public void onClick(View paramView)
    {
      if (paramView == ActivityChooserView.this.mDefaultActivityButton)
      {
        ActivityChooserView.this.dismissPopup();
        paramView = ActivityChooserView.this.mAdapter.getDefaultActivity();
        int i = ActivityChooserView.this.mAdapter.getDataModel().getActivityIndex(paramView);
        paramView = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(i);
        if (paramView != null)
        {
          paramView.addFlags(524288);
          ActivityChooserView.this.getContext().startActivity(paramView);
        }
        return;
      }
      if (paramView == ActivityChooserView.this.mExpandActivityOverflowButton)
      {
        ActivityChooserView.access$602(ActivityChooserView.this, false);
        ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
        return;
      }
      throw new IllegalArgumentException();
    }
    
    public void onDismiss()
    {
      notifyOnDismissListener();
      if (ActivityChooserView.this.mProvider != null) {
        ActivityChooserView.this.mProvider.subUiVisibilityChanged(false);
      }
    }
    
    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      switch (((ActivityChooserView.ActivityChooserViewAdapter)paramAdapterView.getAdapter()).getItemViewType(paramInt))
      {
      default: 
        throw new IllegalArgumentException();
      case 1: 
        ActivityChooserView.this.showPopupUnchecked(Integer.MAX_VALUE);
      }
      do
      {
        return;
        ActivityChooserView.this.dismissPopup();
        if (!ActivityChooserView.this.mIsSelectingDefaultActivity) {
          break;
        }
      } while (paramInt <= 0);
      ActivityChooserView.this.mAdapter.getDataModel().setDefaultActivity(paramInt);
      return;
      if (ActivityChooserView.this.mAdapter.getShowDefaultActivity()) {}
      for (;;)
      {
        paramAdapterView = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(paramInt);
        if (paramAdapterView == null) {
          break;
        }
        paramAdapterView.addFlags(524288);
        ActivityChooserView.this.getContext().startActivity(paramAdapterView);
        return;
        paramInt += 1;
      }
    }
    
    public boolean onLongClick(View paramView)
    {
      if (paramView == ActivityChooserView.this.mDefaultActivityButton)
      {
        if (ActivityChooserView.this.mAdapter.getCount() > 0)
        {
          ActivityChooserView.access$602(ActivityChooserView.this, true);
          ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
        }
        return true;
      }
      throw new IllegalArgumentException();
    }
  }
  
  public static class InnerLayout
    extends LinearLayoutCompat
  {
    private static final int[] TINT_ATTRS = { 16842964 };
    
    public InnerLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, TINT_ATTRS);
      setBackgroundDrawable(paramContext.getDrawable(0));
      paramContext.recycle();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/v7/widget/ActivityChooserView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */