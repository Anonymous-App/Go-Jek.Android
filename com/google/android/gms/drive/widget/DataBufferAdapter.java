package com.google.android.gms.drive.widget;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.drive.internal.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataBufferAdapter<T>
  extends BaseAdapter
{
  private final int RR;
  private int RS;
  private final int RT;
  private final List<DataBuffer<T>> RU;
  private final LayoutInflater RV;
  private boolean RW = true;
  private final Context mContext;
  
  public DataBufferAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, 0, new ArrayList());
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this(paramContext, paramInt1, paramInt2, new ArrayList());
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2, List<DataBuffer<T>> paramList)
  {
    this.mContext = paramContext;
    this.RS = paramInt1;
    this.RR = paramInt1;
    this.RT = paramInt2;
    this.RU = paramList;
    this.RV = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt1, int paramInt2, DataBuffer<T>... paramVarArgs)
  {
    this(paramContext, paramInt1, paramInt2, Arrays.asList(paramVarArgs));
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt, List<DataBuffer<T>> paramList)
  {
    this(paramContext, paramInt, 0, paramList);
  }
  
  public DataBufferAdapter(Context paramContext, int paramInt, DataBuffer<T>... paramVarArgs)
  {
    this(paramContext, paramInt, 0, Arrays.asList(paramVarArgs));
  }
  
  private View a(int paramInt1, View paramView, ViewGroup paramViewGroup, int paramInt2)
  {
    if (paramView == null) {
      paramView = this.RV.inflate(paramInt2, paramViewGroup, false);
    }
    try
    {
      if (this.RT == 0) {}
      Object localObject;
      for (paramViewGroup = (TextView)paramView;; paramViewGroup = (TextView)paramView.findViewById(this.RT))
      {
        localObject = getItem(paramInt1);
        if (!(localObject instanceof CharSequence)) {
          break label92;
        }
        paramViewGroup.setText((CharSequence)localObject);
        return paramView;
        break;
      }
      paramViewGroup.setText(localObject.toString());
    }
    catch (ClassCastException paramView)
    {
      v.a("DataBufferAdapter", paramView, "You must supply a resource ID for a TextView");
      throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", paramView);
    }
    label92:
    return paramView;
  }
  
  public void append(DataBuffer<T> paramDataBuffer)
  {
    this.RU.add(paramDataBuffer);
    if (this.RW) {
      notifyDataSetChanged();
    }
  }
  
  public void clear()
  {
    Iterator localIterator = this.RU.iterator();
    while (localIterator.hasNext()) {
      ((DataBuffer)localIterator.next()).release();
    }
    this.RU.clear();
    if (this.RW) {
      notifyDataSetChanged();
    }
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public int getCount()
  {
    Iterator localIterator = this.RU.iterator();
    for (int i = 0; localIterator.hasNext(); i = ((DataBuffer)localIterator.next()).getCount() + i) {}
    return i;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.RS);
  }
  
  public T getItem(int paramInt)
    throws CursorIndexOutOfBoundsException
  {
    Object localObject = this.RU.iterator();
    int i = paramInt;
    while (((Iterator)localObject).hasNext())
    {
      DataBuffer localDataBuffer = (DataBuffer)((Iterator)localObject).next();
      int j = localDataBuffer.getCount();
      if (j <= i) {
        i -= j;
      } else {
        try
        {
          localObject = localDataBuffer.get(i);
          return (T)localObject;
        }
        catch (CursorIndexOutOfBoundsException localCursorIndexOutOfBoundsException)
        {
          throw new CursorIndexOutOfBoundsException(paramInt, getCount());
        }
      }
    }
    throw new CursorIndexOutOfBoundsException(paramInt, getCount());
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.RR);
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    this.RW = true;
  }
  
  public void setDropDownViewResource(int paramInt)
  {
    this.RS = paramInt;
  }
  
  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.RW = paramBoolean;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/widget/DataBufferAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */