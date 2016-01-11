package com.gojek.gotix.tools;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import com.gojek.gotix.R.string;
import com.gojek.gotix.activities.GotixMainActivity;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class GotixUtils
{
  private static final long DENSITY_CONSTANT = 4L;
  private static final String EMPTY_STRING = "";
  private static final String FORMAT_TAKE_ME_THERE = "dd-MMM-yyyy";
  private static final String FORMAT_TIME = "dd MMM yyyy HH:mm";
  private static final long KILOMETER_VALUE = 1000L;
  private static final char SINGLE_SPACE = ' ';
  private static final String SPACE = " ";
  private static String TAG = GotixUtils.class.getSimpleName();
  
  public static SpannableString formatWordsWithBoldBefore(String paramString1, String paramString2)
  {
    int i = paramString1.substring(0, paramString1.indexOf(paramString2)).length();
    paramString1 = new SpannableString(paramString1);
    paramString1.setSpan(new StyleSpan(1), 0, i, 33);
    return paramString1;
  }
  
  public static String getDateFromTimestamp(long paramLong)
  {
    return getDateFromTimestamp(paramLong, "dd MMM yyyy HH:mm");
  }
  
  public static String getDateFromTimestamp(long paramLong, String paramString)
  {
    return new SimpleDateFormat(paramString).format(new Date(TimeUnit.SECONDS.toMillis(paramLong)));
  }
  
  public static boolean getEnabledBtnTakeMeThere(long paramLong1, long paramLong2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    try
    {
      Date localDate1 = localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date(TimeUnit.SECONDS.toMillis(paramLong1))));
      Date localDate2 = localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date(TimeUnit.SECONDS.toMillis(paramLong2))));
      boolean bool = statusTakeMeThere(localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date(System.currentTimeMillis()))), localDate1, localDate2);
      return bool;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return false;
  }
  
  public static int[] getIndexPositionFrom(String paramString, char paramChar1, char paramChar2)
  {
    paramChar1 = paramString.indexOf(paramChar1) + 1;
    int j;
    for (int i = 0;; i = j)
    {
      j = i;
      if (paramString.charAt(paramChar1) != ' ')
      {
        j = i;
        if (i == 0) {
          j = paramChar1;
        }
      }
      if (paramString.charAt(paramChar1) == paramChar2) {
        return new int[] { j, paramString.indexOf(paramString.charAt(paramChar1)) };
      }
      paramChar1 += '\001';
    }
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
  
  public static String getKilometers(long paramLong)
  {
    return String.valueOf(paramLong / 1000L);
  }
  
  public static String getPercentageFormat(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString + "%";
    }
    return "";
  }
  
  public static int getRadiusByDensity(Context paramContext)
  {
    return (int)(4.0F * paramContext.getResources().getDisplayMetrics().density);
  }
  
  public static String getRupiahFormat(long paramLong)
  {
    return getRupiahFormat(String.valueOf(paramLong));
  }
  
  public static String getRupiahFormat(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      double d = Double.valueOf(paramString).doubleValue();
      paramString = NumberFormat.getCurrencyInstance(new Locale("ca", "CA"));
      return "Rp " + paramString.format(d).replace("CA$", "").replace(",00", "");
    }
    return "";
  }
  
  public static String loadJSONFromAsset(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open("json/" + paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString, "UTF-8");
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void openMainActivityWithFlag(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, GotixMainActivity.class);
    localIntent.addFlags(67108864);
    paramContext.startActivity(localIntent);
  }
  
  public static String populateTag(String[] paramArrayOfString, Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfString.length);
    if (paramArrayOfString != null)
    {
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = paramArrayOfString[i];
        localStringBuilder.append(String.format(paramContext.getString(R.string.tag_format), new Object[] { str }));
        i += 1;
      }
      return localStringBuilder.toString().toUpperCase();
    }
    return "";
  }
  
  public static String[] splitName(String paramString)
  {
    String[] arrayOfString = paramString.split(" ");
    if (arrayOfString.length > 1)
    {
      arrayOfString[0] = arrayOfString[0];
      arrayOfString[1] = arrayOfString[(arrayOfString.length - 1)];
      return arrayOfString;
    }
    return new String[] { paramString, paramString };
  }
  
  private static boolean statusTakeMeThere(Date paramDate1, Date paramDate2, Date paramDate3)
  {
    return ((paramDate1.after(paramDate2)) && (paramDate1.before(paramDate3))) || (paramDate1.compareTo(paramDate2) == 0) || (paramDate1.compareTo(paramDate3) == 0);
  }
  
  public static void terminateSwipeRefresh(SwipeRefreshLayout paramSwipeRefreshLayout)
  {
    if (paramSwipeRefreshLayout != null)
    {
      paramSwipeRefreshLayout.setRefreshing(false);
      paramSwipeRefreshLayout.destroyDrawingCache();
      paramSwipeRefreshLayout.clearAnimation();
    }
  }
  
  public static String wrapText(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    return str;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/tools/GotixUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */