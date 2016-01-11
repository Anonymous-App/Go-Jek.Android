package com.gojek.app.gobusway.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.R.string;
import com.gojek.app.gobusway.model.HalteSchedule;
import com.gojek.app.gobusway.model.KoridorRoute;
import java.util.ArrayList;

public class ScheduleAdapter
  extends RecyclerView.Adapter<ViewHolder>
{
  private static int BUTTON_VIEW_TYPE = 1;
  private static int SCHEDULE_VIEW_TYPE = 0;
  private Context mContext;
  private ArrayList<HalteSchedule> mHalteSchedules;
  private OnButtonRequestClickListener mOnButtonRequestClickListener;
  
  public ScheduleAdapter(ArrayList<HalteSchedule> paramArrayList, Context paramContext, OnButtonRequestClickListener paramOnButtonRequestClickListener)
  {
    this.mHalteSchedules = paramArrayList;
    this.mContext = paramContext;
    this.mOnButtonRequestClickListener = paramOnButtonRequestClickListener;
  }
  
  public int getItemCount()
  {
    return this.mHalteSchedules.size() + 1;
  }
  
  public int getItemViewType(int paramInt)
  {
    if (paramInt < this.mHalteSchedules.size()) {
      return SCHEDULE_VIEW_TYPE;
    }
    return BUTTON_VIEW_TYPE;
  }
  
  public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt)
  {
    if (getItemViewType(paramInt) == SCHEDULE_VIEW_TYPE)
    {
      localObject = (HalteSchedule)this.mHalteSchedules.get(paramInt);
      paramViewHolder.mKoridorName.setText(((HalteSchedule)localObject).getKoridorName().toUpperCase());
      if (((HalteSchedule)localObject).getKoridorRoutes().length >= 0)
      {
        localKoridorRoute = localObject.getKoridorRoutes()[0];
        paramViewHolder.mFirstRouteName.setText(this.mContext.getString(R.string.from_label) + localKoridorRoute.getRouteName());
        paramViewHolder.mFirstRouteSchedule.setAdapter(new TimeAdapter(this.mContext, R.layout.time_item, localKoridorRoute.getEta()));
        paramViewHolder.mRouteName.setText(localKoridorRoute.getRouteName());
      }
      if (((HalteSchedule)localObject).getKoridorRoutes().length >= 1)
      {
        localObject = localObject.getKoridorRoutes()[1];
        paramViewHolder.mSecondRouteName.setText(this.mContext.getString(R.string.from_label) + ((KoridorRoute)localObject).getRouteName());
        paramViewHolder.mSecondRouteSchedule.setAdapter(new TimeAdapter(this.mContext, R.layout.time_item, ((KoridorRoute)localObject).getEta()));
        paramViewHolder.mRouteName.append(" - " + ((KoridorRoute)localObject).getRouteName());
      }
    }
    while (!this.mHalteSchedules.isEmpty())
    {
      Object localObject;
      KoridorRoute localKoridorRoute;
      return;
    }
    paramViewHolder.mNoDataFound.setVisibility(0);
    paramViewHolder.mButtonRequest.setVisibility(8);
  }
  
  public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramInt == SCHEDULE_VIEW_TYPE) {
      return new ViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.schedule_item, paramViewGroup, false), paramInt, this.mOnButtonRequestClickListener);
    }
    return new ViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.request_button_layout, paramViewGroup, false), paramInt, this.mOnButtonRequestClickListener);
  }
  
  public static abstract interface OnButtonRequestClickListener
  {
    public abstract void onRequestClick();
  }
  
  public static class ViewHolder
    extends RecyclerView.ViewHolder
  {
    ImageView mButtonRequest;
    RelativeLayout mFirstRouteContainer;
    TextView mFirstRouteName;
    GridView mFirstRouteSchedule;
    TextView mKoridorName;
    TextView mNoDataFound;
    TextView mRouteName;
    RelativeLayout mSecondLayoutContainer;
    TextView mSecondRouteName;
    GridView mSecondRouteSchedule;
    
    public ViewHolder(View paramView, int paramInt, ScheduleAdapter.OnButtonRequestClickListener paramOnButtonRequestClickListener)
    {
      super();
      if (paramInt == ScheduleAdapter.SCHEDULE_VIEW_TYPE)
      {
        this.mKoridorName = ((TextView)paramView.findViewById(R.id.koridor_name));
        this.mRouteName = ((TextView)paramView.findViewById(R.id.route_name));
        this.mFirstRouteName = ((TextView)paramView.findViewById(R.id.first_route_name));
        this.mSecondRouteName = ((TextView)paramView.findViewById(R.id.second_route_name));
        this.mFirstRouteSchedule = ((GridView)paramView.findViewById(R.id.first_route_schedule));
        this.mSecondRouteSchedule = ((GridView)paramView.findViewById(R.id.second_route_schedule));
        this.mFirstRouteContainer = ((RelativeLayout)paramView.findViewById(R.id.first_route_container));
        this.mSecondLayoutContainer = ((RelativeLayout)paramView.findViewById(R.id.second_route_container));
        return;
      }
      this.mButtonRequest = ((ImageView)paramView.findViewById(R.id.request_button));
      this.mButtonRequest.setOnClickListener(new ScheduleAdapter.ViewHolder.1(this, paramOnButtonRequestClickListener));
      this.mNoDataFound = ((TextView)paramView.findViewById(R.id.no_data_found));
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/adapter/ScheduleAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */