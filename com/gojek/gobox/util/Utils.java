package com.gojek.gobox.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class Utils
{
  private static String convertEnglishContraction(int paramInt)
  {
    if (paramInt == 1) {
      return "st";
    }
    if (paramInt == 2) {
      return "nd";
    }
    if (paramInt == 3) {
      return "rd";
    }
    return "th";
  }
  
  public static String convertIntoFineDateFormat(Date paramDate)
  {
    Calendar.getInstance();
    Object localObject = new SimpleDateFormat("yyyy/mm/dd");
    ((DateFormat)localObject).format(paramDate);
    localObject = ((DateFormat)localObject).getCalendar();
    int i = ((Calendar)localObject).get(1);
    int j = ((Calendar)localObject).get(2);
    int k = ((Calendar)localObject).get(5);
    int m = ((Calendar)localObject).get(7);
    return convertToDay(m - 1) + " " + k + convertEnglishContraction(k) + " " + convertToMonth(j) + " " + i + ", " + getHourMinutes(paramDate);
  }
  
  public static String convertToDay(int paramInt)
  {
    return new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }[paramInt];
  }
  
  private static String convertToMonth(int paramInt)
  {
    return new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }[paramInt];
  }
  
  public static String getHourMinutes(Date paramDate)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("hh:mm a");
    localSimpleDateFormat.format(paramDate);
    return localSimpleDateFormat.format(paramDate);
  }
  
  public static String getJsonStringFromResponse(Response paramResponse)
  {
    try
    {
      paramResponse = new String(((TypedByteArray)paramResponse.getBody()).getBytes(), "utf-8");
      return paramResponse;
    }
    catch (UnsupportedEncodingException paramResponse)
    {
      paramResponse.printStackTrace();
    }
    return "";
  }
  
  public static String getPriceByLocale(double paramDouble)
  {
    NumberFormat localNumberFormat = NumberFormat.getCurrencyInstance(new Locale("ca", "CA"));
    return "Rp " + localNumberFormat.format(paramDouble).replace("CA$", "").replace(",00", "");
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */