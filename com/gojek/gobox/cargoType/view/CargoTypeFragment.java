package com.gojek.gobox.cargoType.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.model.CargoType;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

@SuppressLint({"ValidFragment"})
@Instrumented
public class CargoTypeFragment
  extends Fragment
  implements TraceFieldInterface
{
  private static final String ARG_PAGE_NUMBER = "pageNumber";
  private CargoType data;
  private LinearLayout layout;
  private TextView mCargoDimension;
  private TextView mCargoFare;
  private ImageView mCargoImage;
  private TextView mCargoMaxWeight;
  private TextView mCargoType;
  
  public CargoTypeFragment() {}
  
  public CargoTypeFragment(CargoType paramCargoType)
  {
    this.data = paramCargoType;
  }
  
  public static CargoTypeFragment create(int paramInt, CargoType paramCargoType)
  {
    paramCargoType = new CargoTypeFragment(paramCargoType);
    Bundle localBundle = new Bundle();
    localBundle.putInt("pageNumber", paramInt);
    paramCargoType.setArguments(localBundle);
    return paramCargoType;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "CargoTypeFragment#onCreateView", null);
      paramLayoutInflater = paramLayoutInflater.inflate(R.layout.cargotype_fragment, paramViewGroup, false);
      this.layout = ((LinearLayout)paramLayoutInflater.findViewById(R.id.cargo_layout_container));
      this.mCargoImage = ((ImageView)paramLayoutInflater.findViewById(R.id.cargo_type_image));
      this.mCargoType = ((TextView)paramLayoutInflater.findViewById(R.id.cargo_type));
      this.mCargoDimension = ((TextView)paramLayoutInflater.findViewById(R.id.cargo_type_dimension));
      this.mCargoFare = ((TextView)paramLayoutInflater.findViewById(R.id.cargo_type_fare));
      this.mCargoMaxWeight = ((TextView)paramLayoutInflater.findViewById(R.id.cargo_max_weight));
      if (!TextUtils.isEmpty(this.data.getImage())) {
        Picasso.with(getActivity()).load(this.data.getImage()).placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder).into(this.mCargoImage);
      }
      this.mCargoDimension.setText(String.format(getActivity().getString(R.string.dimension_size), new Object[] { Long.valueOf(this.data.getLength()), Long.valueOf(this.data.getWidth()), Long.valueOf(this.data.getHeight()) }));
      this.mCargoType.setText(this.data.getName().toUpperCase());
      this.mCargoFare.setText(String.format(getActivity().getString(R.string.cargo_fare), new Object[] { Long.valueOf(this.data.getPricePerKm()) }));
      this.mCargoMaxWeight.setText(String.format(getActivity().getString(R.string.cargo_max_weight), new Object[] { Integer.valueOf(this.data.getMaxWeight()) }));
      this.layout.setOnClickListener(new CargoTypeFragment.1(this));
      TraceMachine.exitMethod();
      return paramLayoutInflater;
    }
    catch (NoSuchFieldError paramBundle)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "CargoTypeFragment#onCreateView", null);
      }
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */