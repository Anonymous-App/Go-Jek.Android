package com.gojek.app.model;

import android.content.Context;
import com.gojek.app.parcelable.BookingStatus;
import com.gojek.app.parcelable.RouteItem;
import com.gojek.app.util.GojekPreference;
import com.google.gson.Gson;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

public class BookingSaved
{
  public static final String booking = "booking_";
  public static final String other = "otheritem_";
  Gson gson = new Gson();
  private String jsonBooking;
  private String jsonOtherItem;
  private BookingStatus mBookingStatus;
  private ArrayList<RouteItem> mOtherRouteItems;
  private GojekPreference mPref;
  
  public BookingSaved(Context paramContext)
  {
    this.mPref = GojekPreference.getInstance(paramContext);
    this.jsonBooking = this.mPref.getString("booking_", null);
    this.jsonOtherItem = this.mPref.getString("otheritem_", null);
  }
  
  public void clearData()
  {
    this.mPref.setString("booking_", null);
    this.mPref.setString("otheritem_", null);
  }
  
  public BookingStatus getBookingStatus()
  {
    Object localObject = null;
    this.jsonBooking = this.mPref.getString("booking_", "");
    String str;
    if (!this.jsonBooking.isEmpty())
    {
      new BookingStatus();
      localObject = this.gson;
      str = this.jsonBooking;
      if ((localObject instanceof Gson)) {
        break label67;
      }
    }
    label67:
    for (localObject = ((Gson)localObject).fromJson(str, BookingStatus.class);; localObject = GsonInstrumentation.fromJson((Gson)localObject, str, BookingStatus.class))
    {
      localObject = (BookingStatus)localObject;
      return (BookingStatus)localObject;
    }
  }
  
  public ArrayList<RouteItem> getOtherRouteItems()
  {
    Object localObject1 = null;
    this.jsonOtherItem = this.mPref.getString("otheritem_", "");
    if (!this.jsonOtherItem.isEmpty())
    {
      ArrayList localArrayList = new ArrayList();
      try
      {
        JSONArray localJSONArray = JSONArrayInstrumentation.init(this.jsonOtherItem);
        int i = 0;
        localObject1 = localArrayList;
        if (i < localJSONArray.length())
        {
          localObject1 = this.gson;
          String str = localJSONArray.getString(i);
          if (!(localObject1 instanceof Gson)) {}
          for (localObject1 = ((Gson)localObject1).fromJson(str, RouteItem.class);; localObject1 = GsonInstrumentation.fromJson((Gson)localObject1, str, RouteItem.class))
          {
            localArrayList.add(localObject1);
            i += 1;
            break;
          }
        }
        Object localObject2;
        return (ArrayList<RouteItem>)localObject2;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        localObject2 = localArrayList;
      }
    }
  }
  
  public void saveData()
  {
    Object localObject1 = this.gson;
    Object localObject2 = this.mBookingStatus;
    if (!(localObject1 instanceof Gson))
    {
      localObject1 = ((Gson)localObject1).toJson(localObject2);
      this.jsonBooking = ((String)localObject1);
      localObject1 = this.gson;
      localObject2 = this.mOtherRouteItems;
      if ((localObject1 instanceof Gson)) {
        break label97;
      }
    }
    label97:
    for (localObject1 = ((Gson)localObject1).toJson(localObject2);; localObject1 = GsonInstrumentation.toJson((Gson)localObject1, localObject2))
    {
      this.jsonOtherItem = ((String)localObject1);
      this.mPref.setString("booking_", this.jsonBooking);
      this.mPref.setString("otheritem_", this.jsonOtherItem);
      return;
      localObject1 = GsonInstrumentation.toJson((Gson)localObject1, localObject2);
      break;
    }
  }
  
  public void saveData(BookingStatus paramBookingStatus, ArrayList<RouteItem> paramArrayList)
  {
    this.mBookingStatus = paramBookingStatus;
    this.mOtherRouteItems = paramArrayList;
    saveData();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/BookingSaved.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */