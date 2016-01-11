package com.gojek.app.gobusway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.gojek.app.gobusway.R.id;
import com.gojek.app.gobusway.R.layout;
import com.gojek.app.gobusway.util.StringUtil;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class InfoHalteAdapter
  implements GoogleMap.InfoWindowAdapter
{
  private Context mContext;
  
  public InfoHalteAdapter(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public View getInfoContents(Marker paramMarker)
  {
    return null;
  }
  
  public View getInfoWindow(Marker paramMarker)
  {
    View localView = LayoutInflater.from(this.mContext).inflate(R.layout.marker_info_layout, null);
    ((TextView)localView.findViewById(R.id.halte_name)).setText(StringUtil.upperCaseFirstLatter(paramMarker.getTitle()));
    return localView;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/adapter/InfoHalteAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */