package com.gojek.app.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.maps.model.LatLng;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Util
{
  public static void confirmDialog(Context paramContext, Handler paramHandler1, Handler paramHandler2, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(paramString1);
    paramContext.setTitle(paramString2);
    paramContext.setPositiveButton("Yes", new Util.1(paramHandler1));
    paramContext.setNegativeButton("No", new Util.2(paramHandler2));
    paramContext.create().show();
  }
  
  public static void confirmDialog(Context paramContext, Handler paramHandler1, Handler paramHandler2, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(paramString1);
    if (paramString2 != null) {
      paramContext.setTitle(paramString2);
    }
    paramContext.setPositiveButton(paramString3, new Util.4(paramHandler1));
    paramContext.setNegativeButton(paramString4, new Util.5(paramHandler2));
    paramContext.create().show();
  }
  
  public static void confirmDialog(Context paramContext, Handler paramHandler, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setMessage(paramString1);
    if (paramString2 != null) {
      paramContext.setTitle(paramString2);
    }
    paramContext.setPositiveButton("OK", new Util.3(paramHandler));
    paramContext.create().show();
  }
  
  private static double deg2rad(double paramDouble)
  {
    return 3.141592653589793D * paramDouble / 180.0D;
  }
  
  public static String formatDateFromAPI(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return "";
    }
    String str = paramString2;
    if (paramString2 == null) {
      str = "d MMM, hh:mm a";
    }
    try
    {
      paramString2 = formatDateFromAPI(paramString1);
      paramString2 = new SimpleDateFormat(str).format(paramString2);
      return paramString2;
    }
    catch (Exception paramString2)
    {
      paramString2.printStackTrace();
    }
    return paramString1;
  }
  
  public static Date formatDateFromAPI(String paramString)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString);
    localStringBuilder.deleteCharAt(paramString.length() - 3);
    paramString = localStringBuilder.toString();
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(paramString);
  }
  
  public static Date formatDateFromText(String paramString1, String paramString2)
    throws Exception
  {
    if (paramString2 != null) {}
    for (;;)
    {
      return new SimpleDateFormat(paramString2).parse(paramString1);
      paramString2 = "yyyy-MM-dd'T'HH:mm:ssZ";
    }
  }
  
  public static String formatTimeMiles(long paramLong)
  {
    if (paramLong == 0L) {
      return "";
    }
    Object localObject = new Date();
    ((Date)localObject).setTime(paramLong);
    localObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format((Date)localObject);
    int i = ((String)localObject).length();
    return ((String)localObject).substring(0, i - 2) + ":" + ((String)localObject).substring(i - 2, i);
  }
  
  public static int getAppVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new RuntimeException("Could not get package name: " + paramContext);
    }
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static Bitmap getCircleBitmap(Bitmap paramBitmap)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-65536);
    localCanvas.drawOval(localRectF, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    paramBitmap.recycle();
    return localBitmap;
  }
  
  public static String getDay(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "SUNDAY";
    case 2: 
      return "MONDAY";
    case 3: 
      return "TUESDAY";
    case 4: 
      return "WEDNESDAY";
    case 5: 
      return "THURSDAY";
    case 6: 
      return "FRIDAY";
    }
    return "SATURDAY";
  }
  
  public static String getDeviceIMEI(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static DisplayImageOptions getDisplayImageConfig()
  {
    return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).showImageForEmptyUri(2130837841).showImageOnFail(2130837841).showImageOnLoading(2130837841).build();
  }
  
  public static DisplayImageOptions getDisplayImageConfig(int paramInt)
  {
    return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(false).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).showImageForEmptyUri(paramInt).showImageOnFail(paramInt).showImageOnLoading(paramInt).build();
  }
  
  public static DisplayImageOptions getDisplayImageConfig(RoundedBitmapDisplayer paramRoundedBitmapDisplayer)
  {
    return new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).showImageForEmptyUri(2130837841).showImageOnFail(2130837841).displayer(paramRoundedBitmapDisplayer).showImageOnLoading(2130837841).build();
  }
  
  public static double getDistance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, char paramChar)
  {
    paramDouble2 = 60.0D * rad2deg(Math.acos(Math.sin(deg2rad(paramDouble1)) * Math.sin(deg2rad(paramDouble3)) + Math.cos(deg2rad(paramDouble1)) * Math.cos(deg2rad(paramDouble3)) * Math.cos(deg2rad(paramDouble2 - paramDouble4)))) * 1.1515D;
    if (paramChar == 'K') {
      paramDouble1 = paramDouble2 * 1.609344D;
    }
    do
    {
      return paramDouble1;
      paramDouble1 = paramDouble2;
    } while (paramChar != 'N');
    return paramDouble2 * 0.8684D;
  }
  
  public static String getEditTextNotNull(EditText paramEditText)
  {
    if (paramEditText.getText() != null) {
      return paramEditText.getText().toString();
    }
    return null;
  }
  
  public static String[] getLatLong(String paramString)
  {
    return paramString.split(",");
  }
  
  public static LatLng getMapMidPoint(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d2 = Math.abs(Math.abs(paramDouble1) - Math.abs(paramDouble3)) / 2.0D;
    double d1 = Math.abs(Math.abs(paramDouble2) - Math.abs(paramDouble4)) / 2.0D;
    if (paramDouble1 < paramDouble3)
    {
      paramDouble1 += d2;
      if (paramDouble2 >= paramDouble4) {
        break label115;
      }
    }
    label115:
    for (paramDouble2 += d1;; paramDouble2 = paramDouble4 + d1)
    {
      LatLng localLatLng = new LatLng(paramDouble1, paramDouble2);
      System.out.println(paramDouble1 + "," + paramDouble2);
      return localLatLng;
      paramDouble1 = paramDouble3 + d2;
      break;
    }
  }
  
  public static String getRupiahFormat(String paramString)
  {
    Object localObject2 = "";
    if ((paramString == null) || (paramString.length() == 0) || (paramString.equals("null")))
    {
      localObject2 = "Rp. 0";
      return (String)localObject2;
    }
    if (paramString.length() > 3)
    {
      int i = paramString.length();
      Object localObject1 = paramString;
      paramString = (String)localObject2;
      for (;;)
      {
        localObject2 = paramString;
        if (i <= 0) {
          break;
        }
        localObject2 = localObject1;
        if (i > 3)
        {
          paramString = ((String)localObject1).substring(0, i - 3);
          localObject1 = ((String)localObject1).substring(i - 3);
          paramString = paramString + ".";
          paramString = paramString + (String)localObject1;
          localObject2 = paramString;
          paramString = "Rp. " + paramString;
        }
        i -= 3;
        localObject1 = localObject2;
      }
    }
    return "Rp. " + paramString;
  }
  
  public static String getThousandFormat(String paramString)
  {
    Object localObject2 = "";
    if ((paramString == null) || (paramString.length() == 0) || (paramString.equals("null")))
    {
      localObject2 = "";
      return (String)localObject2;
    }
    if (paramString.length() > 3)
    {
      int i = paramString.length();
      Object localObject1 = paramString;
      paramString = (String)localObject2;
      for (;;)
      {
        localObject2 = paramString;
        if (i <= 0) {
          break;
        }
        localObject2 = localObject1;
        if (i > 3)
        {
          paramString = ((String)localObject1).substring(0, i - 3);
          localObject1 = ((String)localObject1).substring(i - 3);
          paramString = paramString + ".";
          paramString = paramString + (String)localObject1;
          localObject2 = paramString;
          paramString = "Rp. " + paramString;
        }
        i -= 3;
        localObject1 = localObject2;
      }
    }
    return paramString;
  }
  
  public static String getUniqueId(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static void hideSoftKeyboard(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static boolean isActivityRuning(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext()) {
      if (((ActivityManager.RunningTaskInfo)paramContext.next()).baseActivity.getClassName().equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isBetweenHour(String paramString1, String paramString2)
  {
    if ((!paramString1.contains(".")) || (!paramString2.contains(".")) || (!paramString1.contains(":")) || (!paramString2.contains(":")))
    {
      if ((paramString1.equals("closed")) || (paramString2.equals("closed"))) {
        return false;
      }
      if ((paramString1.equals("")) || (paramString2.equals(""))) {
        return true;
      }
    }
    Log.i("open time and clo", paramString1 + " " + paramString2);
    Object localObject2 = paramString1;
    for (;;)
    {
      try
      {
        if (!paramString1.contains(" WIB")) {
          continue;
        }
        localObject2 = paramString1;
        localObject1 = paramString1.replace(" WIB", "");
        localObject2 = localObject1;
        if (!paramString2.contains(" WIB")) {
          continue;
        }
        localObject2 = localObject1;
        paramString1 = paramString2.replace(" WIB", "");
        localObject3 = localObject1;
      }
      catch (Exception paramString1)
      {
        Log.e("error in util get time ", paramString1.toString());
        Object localObject3 = localObject2;
        paramString1 = paramString2;
        continue;
        paramString2 = ((String)localObject3).split("\\:");
        continue;
        paramString1 = paramString1.split("\\:");
        continue;
        if (paramString1.length != 0) {
          continue;
        }
        return true;
        int i = Integer.parseInt(paramString2[0]);
        int j = Integer.parseInt(paramString2[1]);
        int k = Integer.parseInt(paramString1[0]);
        int m = Integer.parseInt(paramString1[1]);
        paramString1 = new Date();
        paramString2 = Calendar.getInstance();
        paramString2.setTime(paramString1);
        paramString2.set(11, i);
        paramString2.set(12, j);
        Object localObject1 = Calendar.getInstance();
        ((Calendar)localObject1).setTime(paramString1);
        ((Calendar)localObject1).set(11, k);
        ((Calendar)localObject1).set(12, m);
        Log.i("time now", paramString1.getTime() + "");
        Log.d("now hour", "now: " + paramString1.getTime() + "/ calendarStart: " + paramString2.getTime().getTime() + "/ calendarEnd: " + ((Calendar)localObject1).getTime().getTime());
        if (paramString1.getTime() >= paramString2.getTime().getTime()) {
          continue;
        }
        if (((Calendar)localObject1).getTime().getTime() >= paramString2.getTime().getTime()) {
          continue;
        }
        if (paramString1.getTime() >= ((Calendar)localObject1).getTime().getTime()) {
          continue;
        }
        return true;
        if (paramString1.getTime() != ((Calendar)localObject1).getTime().getTime()) {
          continue;
        }
        return false;
        if (paramString1.getTime() <= ((Calendar)localObject1).getTime().getTime()) {
          break label816;
        }
        return false;
        return false;
        if (paramString1.getTime() != paramString2.getTime().getTime()) {
          continue;
        }
        return true;
        if (paramString1.getTime() <= paramString2.getTime().getTime()) {
          break label816;
        }
        if (paramString1.getTime() != ((Calendar)localObject1).getTime().getTime()) {
          continue;
        }
        return false;
        if (paramString1.getTime() >= ((Calendar)localObject1).getTime().getTime()) {
          continue;
        }
        return true;
        if (paramString1.getTime() <= ((Calendar)localObject1).getTime().getTime()) {
          break label816;
        }
        return ((Calendar)localObject1).getTime().getTime() < paramString2.getTime().getTime();
      }
      Log.i("open time and clo", (String)localObject3 + " " + paramString1);
      if (!((String)localObject3).contains(".")) {
        continue;
      }
      paramString2 = ((String)localObject3).split("\\.");
      if (!paramString1.contains(".")) {
        continue;
      }
      paramString1 = paramString1.split("\\.");
      if (paramString2.length != 0) {
        continue;
      }
      return true;
      localObject2 = paramString1;
      if (paramString1.contains(" WITA"))
      {
        localObject2 = paramString1;
        localObject1 = paramString1.replace(" WITA", "");
      }
      else
      {
        localObject1 = paramString1;
        localObject2 = paramString1;
        if (paramString1.contains(" WIT"))
        {
          localObject2 = paramString1;
          localObject1 = paramString1.replace(" WIT", "");
          continue;
          localObject2 = localObject1;
          if (paramString2.contains(" WITA"))
          {
            localObject2 = localObject1;
            paramString1 = paramString2.replace(" WITA", "");
            localObject3 = localObject1;
          }
          else
          {
            localObject3 = localObject1;
            paramString1 = paramString2;
            localObject2 = localObject1;
            if (paramString2.contains(" WIT"))
            {
              localObject2 = localObject1;
              paramString1 = paramString2.replace(" WIT", "");
              localObject3 = localObject1;
            }
          }
        }
      }
    }
    label816:
    return false;
  }
  
  public static boolean isTextNotNullEmpty(String paramString)
  {
    return (paramString != null) && (!TextUtils.isEmpty(paramString));
  }
  
  private static double rad2deg(double paramDouble)
  {
    return 180.0D * paramDouble / 3.141592653589793D;
  }
  
  public static String replaceTextNullEmpty(String paramString1, String paramString2)
  {
    if (!isTextNotNullEmpty(paramString1)) {
      return paramString1;
    }
    return paramString2;
  }
  
  public static void resetTextInput(EditText... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setText("");
      i += 1;
    }
  }
  
  public static void resizeGridView(GridView paramGridView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramGridView.getLayoutParams();
    localLayoutParams.height = (paramGridView.getHeight() * (paramInt1 / paramInt2));
    paramGridView.setLayoutParams(localLayoutParams);
  }
  
  public static Double roundDecimal(Double paramDouble)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#.##");
    Log.d("doubled ", "" + localDecimalFormat.format(paramDouble));
    localDecimalFormat.format(paramDouble).replace(",", ".");
    return Double.valueOf(localDecimalFormat.format(paramDouble).replace(",", "."));
  }
  
  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = paramListView.getPaddingTop() + paramListView.getPaddingBottom();
    int m = View.MeasureSpec.makeMeasureSpec(paramListView.getWidth(), Integer.MIN_VALUE);
    int i = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      int k = j;
      if (localObject != null)
      {
        ((View)localObject).setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        ((View)localObject).measure(m, 0);
        k = j + ((View)localObject).getMeasuredHeight();
      }
      i += 1;
      j = k;
    }
    Object localObject = paramListView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    paramListView.requestLayout();
  }
  
  public static String textDecimalFormat(double paramDouble)
  {
    return new DecimalFormat("0.00").format(paramDouble);
  }
  
  public static String textRemoveSpace(String paramString)
  {
    return paramString.replace(" ", "");
  }
  
  public static String urlEncode(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/util/Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */