package com.gojek.app.gobusway.map.view;

import com.gojek.app.gobusway.activity.FragmentController;
import com.gojek.app.gobusway.model.Halte;
import com.google.android.gms.maps.model.Marker;
import java.util.HashMap;

class BusWayMapFragment$1
  implements Runnable
{
  BusWayMapFragment$1(BusWayMapFragment paramBusWayMapFragment, Marker paramMarker) {}
  
  public void run()
  {
    BusWayMapFragment.access$100(this.this$0).showHalteSchedule(((Halte)BusWayMapFragment.access$000(this.this$0).get(this.val$marker)).getId(), ((Halte)BusWayMapFragment.access$000(this.this$0).get(this.val$marker)).getName(), Double.valueOf(((Halte)BusWayMapFragment.access$000(this.this$0).get(this.val$marker)).getLatitude()), Double.valueOf(((Halte)BusWayMapFragment.access$000(this.this$0).get(this.val$marker)).getLongitude()));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/map/view/BusWayMapFragment$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */