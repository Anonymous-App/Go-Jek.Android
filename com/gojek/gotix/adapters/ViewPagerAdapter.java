package com.gojek.gotix.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.gojek.gotix.fragments.GotixBaseFragment;
import java.util.ArrayList;

public class ViewPagerAdapter
  extends FragmentPagerAdapter
{
  private ArrayList<GotixBaseFragment> fragments;
  
  public ViewPagerAdapter(FragmentManager paramFragmentManager)
  {
    this(paramFragmentManager, new ArrayList());
  }
  
  private ViewPagerAdapter(FragmentManager paramFragmentManager, ArrayList<GotixBaseFragment> paramArrayList)
  {
    super(paramFragmentManager);
    this.fragments = paramArrayList;
  }
  
  public void addPage(GotixBaseFragment paramGotixBaseFragment)
  {
    this.fragments.add(paramGotixBaseFragment);
  }
  
  public int getCount()
  {
    return this.fragments.size();
  }
  
  public GotixBaseFragment getItem(int paramInt)
  {
    return (GotixBaseFragment)this.fragments.get(paramInt);
  }
  
  public CharSequence getPageTitle(int paramInt)
  {
    return getItem(paramInt).getTitle();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/adapters/ViewPagerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */