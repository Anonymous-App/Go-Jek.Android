package com.gojek.app.gobusway.model;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class KoridorRoute
{
  @SerializedName("koridor_route_eta")
  private long[] eta;
  @SerializedName("koridor_route")
  private String routeName;
  
  public String[] getEta()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    String[] arrayOfString = new String[10];
    int i = 0;
    if (i < 10)
    {
      if (i < this.eta.length) {
        arrayOfString[i] = localSimpleDateFormat.format(new Date(this.eta[i] * 1000L));
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfString[i] = "";
      }
    }
    return arrayOfString;
  }
  
  public String getRouteName()
  {
    return this.routeName;
  }
  
  public void setEta(long[] paramArrayOfLong)
  {
    this.eta = paramArrayOfLong;
  }
  
  public void setRouteName(String paramString)
  {
    this.routeName = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/gobusway/model/KoridorRoute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */