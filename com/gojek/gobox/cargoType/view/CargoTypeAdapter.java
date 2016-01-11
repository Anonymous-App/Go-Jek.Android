package com.gojek.gobox.cargoType.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.gojek.gobox.model.CargoType;
import java.util.List;

public class CargoTypeAdapter
  extends FragmentStatePagerAdapter
{
  private List<CargoType> cargoTypes;
  
  public CargoTypeAdapter(FragmentManager paramFragmentManager, List<CargoType> paramList)
  {
    super(paramFragmentManager);
    this.cargoTypes = paramList;
  }
  
  public int getCount()
  {
    return this.cargoTypes.size();
  }
  
  public Fragment getItem(int paramInt)
  {
    return CargoTypeFragment.create(paramInt, (CargoType)this.cargoTypes.get(paramInt));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */