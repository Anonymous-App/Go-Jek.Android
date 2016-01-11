package com.gojek.app.gobusway.activity;

import com.gojek.app.gobusway.model.Halte;
import java.util.ArrayList;

public abstract interface FragmentController
{
  public abstract void errorHandler(String paramString1, String paramString2);
  
  public abstract void noInternetConnectionHandler();
  
  public abstract void serviceUnavailableHandler();
  
  public abstract void setFragmentTitle(String paramString);
  
  public abstract void showHalteMarkerOnMap(Halte paramHalte, ArrayList<Halte> paramArrayList, boolean paramBoolean);
  
  public abstract void showHalteSchedule(String paramString1, String paramString2, Double paramDouble1, Double paramDouble2);
  
  public abstract void showSearchFragment(ArrayList<Halte> paramArrayList);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/activity/FragmentController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */