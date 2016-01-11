package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public final class h
  extends c.a
{
  private Fragment Lt;
  
  private h(Fragment paramFragment)
  {
    this.Lt = paramFragment;
  }
  
  public static h a(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new h(paramFragment);
    }
    return null;
  }
  
  public void d(d paramd)
  {
    paramd = (View)e.f(paramd);
    this.Lt.registerForContextMenu(paramd);
  }
  
  public void e(d paramd)
  {
    paramd = (View)e.f(paramd);
    this.Lt.unregisterForContextMenu(paramd);
  }
  
  public Bundle getArguments()
  {
    return this.Lt.getArguments();
  }
  
  public int getId()
  {
    return this.Lt.getId();
  }
  
  public boolean getRetainInstance()
  {
    return this.Lt.getRetainInstance();
  }
  
  public String getTag()
  {
    return this.Lt.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return this.Lt.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return this.Lt.getUserVisibleHint();
  }
  
  public d getView()
  {
    return e.k(this.Lt.getView());
  }
  
  public boolean isAdded()
  {
    return this.Lt.isAdded();
  }
  
  public boolean isDetached()
  {
    return this.Lt.isDetached();
  }
  
  public boolean isHidden()
  {
    return this.Lt.isHidden();
  }
  
  public boolean isInLayout()
  {
    return this.Lt.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return this.Lt.isRemoving();
  }
  
  public boolean isResumed()
  {
    return this.Lt.isResumed();
  }
  
  public boolean isVisible()
  {
    return this.Lt.isVisible();
  }
  
  public d iu()
  {
    return e.k(this.Lt.getActivity());
  }
  
  public c iv()
  {
    return a(this.Lt.getParentFragment());
  }
  
  public d iw()
  {
    return e.k(this.Lt.getResources());
  }
  
  public c ix()
  {
    return a(this.Lt.getTargetFragment());
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    this.Lt.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    this.Lt.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    this.Lt.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    this.Lt.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    this.Lt.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.Lt.startActivityForResult(paramIntent, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/dynamic/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */