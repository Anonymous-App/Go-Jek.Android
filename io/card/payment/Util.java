package io.card.payment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

class Util
{
  private static final String TAG = Util.class.getSimpleName();
  private static final boolean TORCH_BLACK_LISTED = Build.MODEL.equals("DROID2");
  private static Boolean sHardwareSupported;
  
  public static boolean deviceSupportsTorch(Context paramContext)
  {
    return (!TORCH_BLACK_LISTED) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.camera.flash"));
  }
  
  public static String getNativeMemoryStats()
  {
    return "(free/alloc'd/total)" + Debug.getNativeHeapFreeSize() + "/" + Debug.getNativeHeapAllocatedSize() + "/" + Debug.getNativeHeapSize();
  }
  
  private static boolean hardwareSupportCheck()
  {
    Log.i("card.io", "Checking hardware support...");
    if (Build.VERSION.SDK_INT < 8)
    {
      Log.w("card.io", "- Android SDK too old. Minimum Android 2.2 / API level 8+ (Froyo) required");
      return false;
    }
    if (!CardScanner.processorSupported())
    {
      Log.w("card.io", "- Processor type is not supported");
      return false;
    }
    try
    {
      Camera localCamera = Camera.open();
      if (localCamera == null)
      {
        Log.w("card.io", "- No camera found");
        return false;
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w("card.io", "- Error opening camera: " + localRuntimeException);
      throw new CameraUnavailableException();
    }
    Object localObject = localRuntimeException.getParameters().getSupportedPreviewSizes();
    localRuntimeException.release();
    int j = 0;
    Iterator localIterator = ((List)localObject).iterator();
    do
    {
      i = j;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (Camera.Size)localIterator.next();
    } while ((((Camera.Size)localObject).width != 640) || (((Camera.Size)localObject).height != 480));
    int i = 1;
    if (i == 0)
    {
      Log.w("card.io", "- Camera resolution is insufficient");
      return false;
    }
    return true;
  }
  
  public static boolean hardwareSupported()
  {
    if (sHardwareSupported == null) {
      sHardwareSupported = Boolean.valueOf(hardwareSupportCheck());
    }
    return sHardwareSupported.booleanValue();
  }
  
  public static boolean hasConfigFlag(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) == paramInt2;
  }
  
  public static void logNativeMemoryStats()
  {
    Log.d("MEMORY", "Native memory stats: " + getNativeMemoryStats());
  }
  
  public static String manifestHasConfigChange(ResolveInfo paramResolveInfo, Class paramClass)
  {
    String str = null;
    if (paramResolveInfo == null) {
      str = String.format("Didn't find %s in the AndroidManifest.xml", new Object[] { paramClass.getName() });
    }
    for (;;)
    {
      if (str != null) {
        Log.e("card.io", str);
      }
      return str;
      if (!hasConfigFlag(paramResolveInfo.activityInfo.configChanges, 128)) {
        str = paramClass.getName() + " requires attribute android:configChanges=\"orientation\"";
      }
    }
  }
  
  public static Rect rectGivenCenter(Point paramPoint, int paramInt1, int paramInt2)
  {
    return new Rect(paramPoint.x - paramInt1 / 2, paramPoint.y - paramInt2 / 2, paramPoint.x + paramInt1 / 2, paramPoint.y + paramInt2 / 2);
  }
  
  public static void setupTextPaintStyle(Paint paramPaint)
  {
    paramPaint.setColor(-1);
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
    paramPaint.setAntiAlias(true);
    paramPaint.setShadowLayer(1.5F, 0.5F, 0.0F, Color.HSVToColor(200, new float[] { 0.0F, 0.0F, 0.0F }));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */