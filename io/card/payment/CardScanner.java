package io.card.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

class CardScanner
  implements Camera.AutoFocusCallback, Camera.PreviewCallback, SurfaceHolder.Callback
{
  private static final String TAG;
  private static boolean manualFallbackForError;
  private static boolean processingInProgress;
  private long captureStart;
  private Bitmap detectedBitmap;
  private int frameCount = 0;
  private boolean isSurfaceValid = false;
  private long mAutoFocusCompletedAt = 0L;
  private long mAutoFocusStartedAt = 0L;
  private Camera mCamera = null;
  private boolean mFirstPreviewFrame = true;
  private int mFrameOrientation = 1;
  private byte[] mPreviewBuffer;
  final int mPreviewHeight = 480;
  final int mPreviewWidth = 640;
  protected WeakReference<CardIOActivity> mScanActivityRef;
  private boolean mScanExpiry;
  private boolean mSuppressScan = false;
  private int numAutoRefocus;
  private int numFramesSkipped;
  private int numManualRefocus;
  private int numManualTorchChange;
  protected boolean useCamera = true;
  
  static
  {
    boolean bool;
    if (!CardScanner.class.desiredAssertionStatus())
    {
      bool = true;
      $assertionsDisabled = bool;
      TAG = CardScanner.class.getSimpleName();
      manualFallbackForError = false;
      Log.i("card.io", "card.io 5.0.1 06/04/2015 17:39:33 -0500");
    }
    for (;;)
    {
      try
      {
        System.loadLibrary("cardioDecider");
        Log.d("card.io", "Loaded card.io decider library.  nUseNeon():" + nUseNeon() + ",nUseTegra():" + nUseTegra());
        if ((nUseNeon()) || (nUseTegra()))
        {
          System.loadLibrary("opencv_core");
          Log.d("card.io", "Loaded opencv core library");
          System.loadLibrary("opencv_imgproc");
          Log.d("card.io", "Loaded opencv imgproc library");
        }
        if (!nUseNeon()) {
          continue;
        }
        System.loadLibrary("cardioRecognizer");
        Log.i("card.io", "Loaded card.io NEON library");
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        Log.e("card.io", "Failed to load native library: " + localUnsatisfiedLinkError.getMessage());
        manualFallbackForError = true;
        continue;
        Log.w("card.io", "unsupported processor - card.io scanning requires ARMv7 architecture");
        manualFallbackForError = true;
        continue;
      }
      processingInProgress = false;
      return;
      bool = false;
      break;
      if (!nUseTegra()) {
        continue;
      }
      System.loadLibrary("cardioRecognizer_tegra2");
      Log.i("card.io", "Loaded card.io Tegra2 library");
    }
  }
  
  CardScanner(CardIOActivity paramCardIOActivity, int paramInt)
  {
    Intent localIntent = paramCardIOActivity.getIntent();
    if (localIntent != null)
    {
      this.mSuppressScan = localIntent.getBooleanExtra("io.card.payment.suppressScan", false);
      if ((!localIntent.getBooleanExtra("io.card.payment.requireExpiry", false)) || (!localIntent.getBooleanExtra("io.card.payment.scanExpiry", true))) {
        break label143;
      }
    }
    for (;;)
    {
      this.mScanExpiry = bool;
      this.mScanActivityRef = new WeakReference(paramCardIOActivity);
      this.mFrameOrientation = paramInt;
      nSetup(this.mSuppressScan, 6.0F);
      return;
      label143:
      bool = false;
    }
  }
  
  private Camera connectToCamera(int paramInt1, int paramInt2)
  {
    long l = System.currentTimeMillis();
    if (this.useCamera) {}
    try
    {
      Camera localCamera = Camera.open();
      return localCamera;
    }
    catch (RuntimeException localRuntimeException)
    {
      do
      {
        try
        {
          Log.w("card.io", "Wasn't able to connect to camera service. Waiting and trying again...");
          Thread.sleep(paramInt1);
          i = paramInt2;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            Log.e("card.io", "Interrupted while waiting for camera", localInterruptedException);
            i = paramInt2;
          }
        }
        paramInt2 = i;
      } while (System.currentTimeMillis() - l < i);
      Log.w(TAG, "camera connect timeout");
      return null;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("card.io", "Unexpected exception. Please report it to support@card.io", localException);
        int i = 0;
      }
    }
  }
  
  private boolean makePreviewGo(SurfaceHolder paramSurfaceHolder)
  {
    assert (paramSurfaceHolder != null);
    assert (paramSurfaceHolder.getSurface() != null);
    Log.d(TAG, "surfaceFrame: " + String.valueOf(paramSurfaceHolder.getSurfaceFrame()));
    this.mFirstPreviewFrame = true;
    if (this.useCamera) {}
    try
    {
      this.mCamera.setPreviewDisplay(paramSurfaceHolder);
      return false;
    }
    catch (IOException paramSurfaceHolder)
    {
      try
      {
        this.mCamera.startPreview();
        this.mCamera.autoFocus(this);
        Log.d(TAG, "startPreview success");
        return true;
      }
      catch (RuntimeException paramSurfaceHolder)
      {
        Log.e("card.io", "startPreview failed on camera. Error: ", paramSurfaceHolder);
      }
      paramSurfaceHolder = paramSurfaceHolder;
      Log.e("card.io", "can't set preview display", paramSurfaceHolder);
      return false;
    }
  }
  
  private native void nCleanup();
  
  private native void nGetGuideFrame(int paramInt1, int paramInt2, int paramInt3, Rect paramRect);
  
  private native int nGetNumFramesScanned();
  
  private native void nResetAnalytics();
  
  private native void nScanFrame(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, DetectionInfo paramDetectionInfo, Bitmap paramBitmap, boolean paramBoolean);
  
  private native void nSetup(boolean paramBoolean, float paramFloat);
  
  public static native boolean nUseNeon();
  
  public static native boolean nUseTegra();
  
  static boolean processorSupported()
  {
    return (!manualFallbackForError) && ((nUseNeon()) || (nUseTegra()));
  }
  
  public void endScanning()
  {
    if (this.mCamera != null) {
      pauseScanning();
    }
    nCleanup();
    this.mPreviewBuffer = null;
  }
  
  Rect getGuideFrame(int paramInt1, int paramInt2)
  {
    return getGuideFrame(this.mFrameOrientation, paramInt1, paramInt2);
  }
  
  Rect getGuideFrame(int paramInt1, int paramInt2, int paramInt3)
  {
    Rect localRect = null;
    if (processorSupported())
    {
      localRect = new Rect();
      nGetGuideFrame(paramInt1, paramInt2, paramInt3, localRect);
    }
    return localRect;
  }
  
  boolean isAutoFocusing()
  {
    return this.mAutoFocusCompletedAt < this.mAutoFocusStartedAt;
  }
  
  public boolean isFlashOn()
  {
    if (!this.useCamera) {
      return false;
    }
    return this.mCamera.getParameters().getFlashMode().equals("torch");
  }
  
  public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
  {
    this.mAutoFocusCompletedAt = System.currentTimeMillis();
  }
  
  void onEdgeUpdate(DetectionInfo paramDetectionInfo)
  {
    ((CardIOActivity)this.mScanActivityRef.get()).onEdgeUpdate(paramDetectionInfo);
  }
  
  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    int i = 1;
    if (paramArrayOfByte == null) {
      Log.w(TAG, "frame is null! skipping");
    }
    do
    {
      return;
      if (!processingInProgress) {
        break;
      }
      Log.e(TAG, "processing in progress.... dropping frame");
      this.numFramesSkipped += 1;
    } while (paramCamera == null);
    paramCamera.addCallbackBuffer(paramArrayOfByte);
    return;
    processingInProgress = true;
    if (this.mFirstPreviewFrame)
    {
      Log.d(TAG, "mFirstPreviewFrame");
      this.mFirstPreviewFrame = false;
      this.mFrameOrientation = 1;
      ((CardIOActivity)this.mScanActivityRef.get()).onFirstFrame(1);
    }
    DetectionInfo localDetectionInfo = new DetectionInfo();
    nScanFrame(paramArrayOfByte, 640, 480, this.mFrameOrientation, localDetectionInfo, this.detectedBitmap, this.mScanExpiry);
    if (localDetectionInfo.focusScore >= 6.0F)
    {
      if (i != 0) {
        break label171;
      }
      triggerAutoFocus(false);
    }
    for (;;)
    {
      if (paramCamera != null) {
        paramCamera.addCallbackBuffer(paramArrayOfByte);
      }
      processingInProgress = false;
      return;
      i = 0;
      break;
      label171:
      if ((localDetectionInfo.predicted()) || ((this.mSuppressScan) && (localDetectionInfo.detected())))
      {
        Log.d(TAG, "detected card: " + localDetectionInfo.creditCard());
        ((CardIOActivity)this.mScanActivityRef.get()).onCardDetected(this.detectedBitmap, localDetectionInfo);
      }
    }
  }
  
  public void pauseScanning()
  {
    setFlashOn(false);
    if (this.mCamera != null) {}
    try
    {
      this.mCamera.stopPreview();
      this.mCamera.setPreviewDisplay(null);
      this.mCamera.setPreviewCallback(null);
      this.mCamera.release();
      this.mPreviewBuffer = null;
      Log.d(TAG, "- released camera");
      this.mCamera = null;
      Log.i(TAG, "scan paused");
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.w("card.io", "can't stop preview display", localIOException);
      }
    }
  }
  
  void prepareScanner()
  {
    Log.v(TAG, "prepareScanner()");
    this.mFirstPreviewFrame = true;
    this.mAutoFocusStartedAt = 0L;
    this.mAutoFocusCompletedAt = 0L;
    this.numManualRefocus = 0;
    this.numAutoRefocus = 0;
    this.numManualTorchChange = 0;
    this.numFramesSkipped = 0;
    if ((this.useCamera) && (this.mCamera == null))
    {
      this.mCamera = connectToCamera(50, 5000);
      if (this.mCamera == null) {
        Log.e("card.io", "prepare scanner couldn't connect to camera!");
      }
    }
    for (;;)
    {
      return;
      Log.v(TAG, "camera is connected");
      this.mCamera.setDisplayOrientation(90);
      Camera.Parameters localParameters = this.mCamera.getParameters();
      List localList = localParameters.getSupportedPreviewSizes();
      if (localList != null)
      {
        Object localObject2 = null;
        Iterator localIterator = localList.iterator();
        Object localObject1;
        do
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break;
          }
          localObject1 = (Camera.Size)localIterator.next();
        } while ((((Camera.Size)localObject1).width != 640) || (((Camera.Size)localObject1).height != 480));
        if (localObject1 == null)
        {
          Log.w("card.io", "Didn't find a supported 640x480 resolution, so forcing");
          localObject1 = (Camera.Size)localList.get(0);
          ((Camera.Size)localObject1).width = 640;
          ((Camera.Size)localObject1).height = 480;
        }
      }
      Log.d(TAG, "- parameters: " + localParameters);
      localParameters.setPreviewSize(640, 480);
      this.mCamera.setParameters(localParameters);
      while (this.detectedBitmap == null)
      {
        this.detectedBitmap = Bitmap.createBitmap(428, 270, Bitmap.Config.ARGB_8888);
        return;
        if (!this.useCamera) {
          Log.w(TAG, "useCamera is false!");
        } else if (this.mCamera != null) {
          Log.v(TAG, "we already have a camera instance: " + this.mCamera);
        }
      }
    }
  }
  
  boolean resumeScanning(SurfaceHolder paramSurfaceHolder)
  {
    Log.v(TAG, "resumeScanning(" + paramSurfaceHolder + ")");
    if (this.mCamera == null)
    {
      Log.v(TAG, "preparing the scanner...");
      prepareScanner();
      Log.v(TAG, "preparations complete");
    }
    if ((this.useCamera) && (this.mCamera == null))
    {
      Log.i(TAG, "null camera. failure");
      return false;
    }
    assert (paramSurfaceHolder != null);
    if ((this.useCamera) && (this.mPreviewBuffer == null))
    {
      Log.v(TAG, "- mCamera:" + this.mCamera);
      int i = this.mCamera.getParameters().getPreviewFormat();
      Log.v(TAG, "- preview format: " + i);
      i = ImageFormat.getBitsPerPixel(i) / 8;
      Log.v(TAG, "- bytes per pixel: " + i);
      i = 307200 * i * 3;
      Log.v(TAG, "- buffer size: " + i);
      this.mPreviewBuffer = new byte[i];
      this.mCamera.addCallbackBuffer(this.mPreviewBuffer);
    }
    paramSurfaceHolder.addCallback(this);
    paramSurfaceHolder.setType(3);
    if (this.useCamera) {
      this.mCamera.setPreviewCallbackWithBuffer(this);
    }
    if (this.isSurfaceValid) {
      makePreviewGo(paramSurfaceHolder);
    }
    setFlashOn(false);
    this.captureStart = System.currentTimeMillis();
    nResetAnalytics();
    return true;
  }
  
  void setDeviceOrientation(int paramInt)
  {
    this.mFrameOrientation = paramInt;
  }
  
  public boolean setFlashOn(boolean paramBoolean)
  {
    if (this.mCamera != null)
    {
      Log.d(TAG, "setFlashOn: " + paramBoolean);
      try
      {
        Camera.Parameters localParameters = this.mCamera.getParameters();
        if (paramBoolean) {}
        for (String str = "torch";; str = "off")
        {
          localParameters.setFlashMode(str);
          this.mCamera.setParameters(localParameters);
          this.numManualTorchChange += 1;
          return true;
        }
        return false;
      }
      catch (RuntimeException localRuntimeException)
      {
        Log.w(TAG, "Could not set flash mode: " + localRuntimeException);
      }
    }
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    String str = TAG;
    if (paramSurfaceHolder != null) {}
    for (boolean bool = true;; bool = false)
    {
      Log.d(str, String.format("Preview.surfaceChanged(holder?:%b, f:%d, w:%d, h:%d )", new Object[] { Boolean.valueOf(bool), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) }));
      return;
    }
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    Log.d(TAG, "Preview.surfaceCreated()");
    if ((this.mCamera != null) || (!this.useCamera))
    {
      this.isSurfaceValid = true;
      makePreviewGo(paramSurfaceHolder);
      Log.d(TAG, "Preview.surfaceCreated(), surface is valid");
      return;
    }
    Log.wtf("card.io", "CardScanner.surfaceCreated() - camera is null!");
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    Log.d(TAG, "Preview.surfaceDestroyed()");
    if (this.mCamera != null) {}
    try
    {
      this.mCamera.stopPreview();
      this.isSurfaceValid = false;
      return;
    }
    catch (Exception paramSurfaceHolder)
    {
      for (;;)
      {
        Log.e("card.io", "error stopping camera", paramSurfaceHolder);
      }
    }
  }
  
  void triggerAutoFocus(boolean paramBoolean)
  {
    if ((this.useCamera) && (!isAutoFocusing())) {
      try
      {
        this.mAutoFocusStartedAt = System.currentTimeMillis();
        this.mCamera.autoFocus(this);
        if (paramBoolean)
        {
          this.numManualRefocus += 1;
          return;
        }
        this.numAutoRefocus += 1;
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        Log.w(TAG, "could not trigger auto focus: " + localRuntimeException);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/CardScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */