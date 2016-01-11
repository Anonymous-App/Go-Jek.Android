package com.gojek.app.gobusway.schedule.view;

import com.gojek.app.gobusway.model.HalteSchedule;
import java.util.ArrayList;

public abstract interface ScheduleView
{
  public abstract String getHalteId();
  
  public abstract void hideProgress();
  
  public abstract void initViews();
  
  public abstract void showErrorWhileFetchData();
  
  public abstract void showProgress();
  
  public abstract void showSchedule(ArrayList<HalteSchedule> paramArrayList);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/schedule/view/ScheduleView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */