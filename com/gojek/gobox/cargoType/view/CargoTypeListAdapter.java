package com.gojek.gobox.cargoType.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gojek.gobox.R.drawable;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.model.CargoType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;

public class CargoTypeListAdapter
  extends RecyclerView.Adapter<CargoTypeHolder>
{
  private List<CargoType> mCargoTypes;
  private OnCargoTypeClickListener mClickListener;
  private Context mContext;
  
  public CargoTypeListAdapter(Context paramContext, List<CargoType> paramList, OnCargoTypeClickListener paramOnCargoTypeClickListener)
  {
    this.mContext = paramContext;
    this.mCargoTypes = paramList;
    this.mClickListener = paramOnCargoTypeClickListener;
  }
  
  public int getItemCount()
  {
    return this.mCargoTypes.size();
  }
  
  public void onBindViewHolder(CargoTypeHolder paramCargoTypeHolder, int paramInt)
  {
    CargoType localCargoType = (CargoType)this.mCargoTypes.get(paramInt);
    if (!TextUtils.isEmpty(localCargoType.getImage())) {
      Picasso.with(this.mContext).load("https://gobox-api.gojek.co.id/" + localCargoType.getImage()).placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder).into(paramCargoTypeHolder.mCargoImage);
    }
    paramCargoTypeHolder.mCargoDimension.setText(String.format(this.mContext.getString(R.string.dimension_size), new Object[] { Long.valueOf(localCargoType.getLength()), Long.valueOf(localCargoType.getWidth()), Long.valueOf(localCargoType.getHeight()) }));
    paramCargoTypeHolder.mCargoType.setText(localCargoType.getName().toUpperCase());
    paramCargoTypeHolder.mCargoFare.setText(String.format(this.mContext.getString(R.string.cargo_fare), new Object[] { Long.valueOf(localCargoType.getPricePerKm()) }));
    paramCargoTypeHolder.mCargoMaxWeight.setText(String.format(this.mContext.getString(R.string.cargo_max_weight), new Object[] { Integer.valueOf(localCargoType.getMaxWeight()) }));
    paramCargoTypeHolder.mRootView.setOnClickListener(new CargoTypeListAdapter.1(this, paramInt));
  }
  
  public CargoTypeHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    return new CargoTypeHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.cargo_type_item, paramViewGroup, false), this.mCargoTypes);
  }
  
  public static class CargoTypeHolder
    extends RecyclerView.ViewHolder
  {
    private TextView mCargoDimension;
    private TextView mCargoFare;
    private ImageView mCargoImage;
    private TextView mCargoMaxWeight;
    private TextView mCargoType;
    private List<CargoType> mCargoTypes;
    private View mRootView;
    
    public CargoTypeHolder(View paramView, List<CargoType> paramList)
    {
      super();
      this.mCargoImage = ((ImageView)paramView.findViewById(R.id.cargo_type_image));
      this.mCargoType = ((TextView)paramView.findViewById(R.id.cargo_type));
      this.mCargoDimension = ((TextView)paramView.findViewById(R.id.cargo_type_dimension));
      this.mCargoFare = ((TextView)paramView.findViewById(R.id.cargo_type_fare));
      this.mCargoMaxWeight = ((TextView)paramView.findViewById(R.id.cargo_max_weight));
      this.mCargoTypes = paramList;
      this.mRootView = paramView.findViewById(R.id.cargo_type_item_root);
    }
  }
  
  public static abstract interface OnCargoTypeClickListener
  {
    public abstract void OnCargoClick(CargoType paramCargoType);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/cargoType/view/CargoTypeListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */