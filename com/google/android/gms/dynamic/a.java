package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class a<T extends LifecycleDelegate>
{
  private T RX;
  private Bundle RY;
  private LinkedList<a> RZ;
  private final f<T> Sa = new f()
  {
    public void a(T paramAnonymousT)
    {
      a.a(a.this, paramAnonymousT);
      paramAnonymousT = a.a(a.this).iterator();
      while (paramAnonymousT.hasNext()) {
        ((a.a)paramAnonymousT.next()).b(a.b(a.this));
      }
      a.a(a.this).clear();
      a.a(a.this, null);
    }
  };
  
  private void a(Bundle paramBundle, a parama)
  {
    if (this.RX != null)
    {
      parama.b(this.RX);
      return;
    }
    if (this.RZ == null) {
      this.RZ = new LinkedList();
    }
    this.RZ.add(parama);
    if (paramBundle != null)
    {
      if (this.RY != null) {
        break label76;
      }
      this.RY = ((Bundle)paramBundle.clone());
    }
    for (;;)
    {
      a(this.Sa);
      return;
      label76:
      this.RY.putAll(paramBundle);
    }
  }
  
  public static void b(FrameLayout paramFrameLayout)
  {
    Context localContext = paramFrameLayout.getContext();
    final int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(localContext);
    String str2 = GooglePlayServicesUtil.d(localContext, i);
    String str1 = GooglePlayServicesUtil.e(localContext, i);
    LinearLayout localLinearLayout = new LinearLayout(paramFrameLayout.getContext());
    localLinearLayout.setOrientation(1);
    localLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView(localLinearLayout);
    paramFrameLayout = new TextView(paramFrameLayout.getContext());
    paramFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.setText(str2);
    localLinearLayout.addView(paramFrameLayout);
    if (str1 != null)
    {
      paramFrameLayout = new Button(localContext);
      paramFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      paramFrameLayout.setText(str1);
      localLinearLayout.addView(paramFrameLayout);
      paramFrameLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          this.mV.startActivity(GooglePlayServicesUtil.c(this.mV, i));
        }
      });
    }
  }
  
  private void cv(int paramInt)
  {
    while ((!this.RZ.isEmpty()) && (((a)this.RZ.getLast()).getState() >= paramInt)) {
      this.RZ.removeLast();
    }
  }
  
  protected void a(FrameLayout paramFrameLayout)
  {
    b(paramFrameLayout);
  }
  
  protected abstract void a(f<T> paramf);
  
  public T it()
  {
    return this.RX;
  }
  
  public void onCreate(final Bundle paramBundle)
  {
    a(paramBundle, new a()
    {
      public void b(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        a.b(a.this).onCreate(paramBundle);
      }
      
      public int getState()
      {
        return 1;
      }
    });
  }
  
  public View onCreateView(final LayoutInflater paramLayoutInflater, final ViewGroup paramViewGroup, final Bundle paramBundle)
  {
    final FrameLayout localFrameLayout = new FrameLayout(paramLayoutInflater.getContext());
    a(paramBundle, new a()
    {
      public void b(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        localFrameLayout.removeAllViews();
        localFrameLayout.addView(a.b(a.this).onCreateView(paramLayoutInflater, paramViewGroup, paramBundle));
      }
      
      public int getState()
      {
        return 2;
      }
    });
    if (this.RX == null) {
      a(localFrameLayout);
    }
    return localFrameLayout;
  }
  
  public void onDestroy()
  {
    if (this.RX != null)
    {
      this.RX.onDestroy();
      return;
    }
    cv(1);
  }
  
  public void onDestroyView()
  {
    if (this.RX != null)
    {
      this.RX.onDestroyView();
      return;
    }
    cv(2);
  }
  
  public void onInflate(final Activity paramActivity, final Bundle paramBundle1, final Bundle paramBundle2)
  {
    a(paramBundle2, new a()
    {
      public void b(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        a.b(a.this).onInflate(paramActivity, paramBundle1, paramBundle2);
      }
      
      public int getState()
      {
        return 0;
      }
    });
  }
  
  public void onLowMemory()
  {
    if (this.RX != null) {
      this.RX.onLowMemory();
    }
  }
  
  public void onPause()
  {
    if (this.RX != null)
    {
      this.RX.onPause();
      return;
    }
    cv(5);
  }
  
  public void onResume()
  {
    a(null, new a()
    {
      public void b(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        a.b(a.this).onResume();
      }
      
      public int getState()
      {
        return 5;
      }
    });
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.RX != null) {
      this.RX.onSaveInstanceState(paramBundle);
    }
    while (this.RY == null) {
      return;
    }
    paramBundle.putAll(this.RY);
  }
  
  public void onStart()
  {
    a(null, new a()
    {
      public void b(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        a.b(a.this).onStart();
      }
      
      public int getState()
      {
        return 4;
      }
    });
  }
  
  public void onStop()
  {
    if (this.RX != null)
    {
      this.RX.onStop();
      return;
    }
    cv(4);
  }
  
  private static abstract interface a
  {
    public abstract void b(LifecycleDelegate paramLifecycleDelegate);
    
    public abstract int getState();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/dynamic/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */