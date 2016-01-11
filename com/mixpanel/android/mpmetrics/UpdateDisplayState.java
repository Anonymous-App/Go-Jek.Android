package com.mixpanel.android.mpmetrics;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@TargetApi(16)
public class UpdateDisplayState
  implements Parcelable
{
  public static final Parcelable.Creator<UpdateDisplayState> CREATOR = new UpdateDisplayState.1();
  private static final String DISPLAYSTATE_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.DISPLAYSTATE_BUNDLE_KEY";
  private static final String DISTINCT_ID_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.DISTINCT_ID_BUNDLE_KEY";
  private static final String LOGTAG = "MixpanelAPI.UpDisplSt";
  private static final long MAX_LOCK_TIME_MILLIS = 43200000L;
  private static final String TOKEN_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.TOKEN_BUNDLE_KEY";
  private static int sNextIntentId = 0;
  private static int sShowingIntentId = -1;
  private static final ReentrantLock sUpdateDisplayLock = new ReentrantLock();
  private static long sUpdateDisplayLockMillis = -1L;
  private static UpdateDisplayState sUpdateDisplayState = null;
  private final DisplayState mDisplayState;
  private final String mDistinctId;
  private final String mToken;
  
  private UpdateDisplayState(Bundle paramBundle)
  {
    this.mDistinctId = paramBundle.getString("com.mixpanel.android.mpmetrics.UpdateDisplayState.DISTINCT_ID_BUNDLE_KEY");
    this.mToken = paramBundle.getString("com.mixpanel.android.mpmetrics.UpdateDisplayState.TOKEN_BUNDLE_KEY");
    this.mDisplayState = ((DisplayState)paramBundle.getParcelable("com.mixpanel.android.mpmetrics.UpdateDisplayState.DISPLAYSTATE_BUNDLE_KEY"));
  }
  
  UpdateDisplayState(DisplayState paramDisplayState, String paramString1, String paramString2)
  {
    this.mDistinctId = paramString1;
    this.mToken = paramString2;
    this.mDisplayState = paramDisplayState;
  }
  
  public static UpdateDisplayState claimDisplayState(int paramInt)
  {
    sUpdateDisplayLock.lock();
    try
    {
      if (sShowingIntentId > 0)
      {
        int i = sShowingIntentId;
        if (i != paramInt) {
          return null;
        }
      }
      UpdateDisplayState localUpdateDisplayState = sUpdateDisplayState;
      if (localUpdateDisplayState == null) {
        return null;
      }
      sUpdateDisplayLockMillis = System.currentTimeMillis();
      sShowingIntentId = paramInt;
      localUpdateDisplayState = sUpdateDisplayState;
      return localUpdateDisplayState;
    }
    finally
    {
      sUpdateDisplayLock.unlock();
    }
  }
  
  static ReentrantLock getLockObject()
  {
    return sUpdateDisplayLock;
  }
  
  static boolean hasCurrentProposal()
  {
    if (!sUpdateDisplayLock.isHeldByCurrentThread()) {
      throw new AssertionError();
    }
    long l1 = System.currentTimeMillis();
    long l2 = sUpdateDisplayLockMillis;
    if ((sNextIntentId > 0) && (l1 - l2 > 43200000L))
    {
      Log.i("MixpanelAPI.UpDisplSt", "UpdateDisplayState set long, long ago, without showing. Update state will be cleared.");
      sUpdateDisplayState = null;
    }
    return sUpdateDisplayState != null;
  }
  
  static int proposeDisplay(DisplayState paramDisplayState, String paramString1, String paramString2)
  {
    int i = -1;
    if (!sUpdateDisplayLock.isHeldByCurrentThread()) {
      throw new AssertionError();
    }
    if (!hasCurrentProposal())
    {
      sUpdateDisplayLockMillis = System.currentTimeMillis();
      sUpdateDisplayState = new UpdateDisplayState(paramDisplayState, paramString1, paramString2);
      sNextIntentId += 1;
      i = sNextIntentId;
    }
    while (!MPConfig.DEBUG) {
      return i;
    }
    Log.v("MixpanelAPI.UpDisplSt", "Already showing (or cooking) a Mixpanel update, declining to show another.");
    return -1;
  }
  
  public static void releaseDisplayState(int paramInt)
  {
    sUpdateDisplayLock.lock();
    try
    {
      if (paramInt == sShowingIntentId)
      {
        sShowingIntentId = -1;
        sUpdateDisplayState = null;
      }
      return;
    }
    finally
    {
      sUpdateDisplayLock.unlock();
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DisplayState getDisplayState()
  {
    return this.mDisplayState;
  }
  
  public String getDistinctId()
  {
    return this.mDistinctId;
  }
  
  public String getToken()
  {
    return this.mToken;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("com.mixpanel.android.mpmetrics.UpdateDisplayState.DISTINCT_ID_BUNDLE_KEY", this.mDistinctId);
    localBundle.putString("com.mixpanel.android.mpmetrics.UpdateDisplayState.TOKEN_BUNDLE_KEY", this.mToken);
    localBundle.putParcelable("com.mixpanel.android.mpmetrics.UpdateDisplayState.DISPLAYSTATE_BUNDLE_KEY", this.mDisplayState);
    paramParcel.writeBundle(localBundle);
  }
  
  public static class AnswerMap
    implements Parcelable
  {
    public static final Parcelable.Creator<AnswerMap> CREATOR = new UpdateDisplayState.AnswerMap.1();
    private final HashMap<Integer, String> mMap = new HashMap();
    
    public boolean contentEquals(AnswerMap paramAnswerMap)
    {
      return this.mMap.equals(paramAnswerMap.mMap);
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String get(Integer paramInteger)
    {
      return (String)this.mMap.get(paramInteger);
    }
    
    public void put(Integer paramInteger, String paramString)
    {
      this.mMap.put(paramInteger, paramString);
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      Bundle localBundle = new Bundle();
      Iterator localIterator = this.mMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localBundle.putString(Integer.toString(((Integer)localEntry.getKey()).intValue()), (String)localEntry.getValue());
      }
      paramParcel.writeBundle(localBundle);
    }
  }
  
  public static abstract class DisplayState
    implements Parcelable
  {
    public static final Parcelable.Creator<DisplayState> CREATOR = new UpdateDisplayState.DisplayState.1();
    public static final String STATE_IMPL_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.STATE_IMPL_KEY";
    public static final String STATE_TYPE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.STATE_TYPE_KEY";
    
    public abstract String getType();
    
    public static final class InAppNotificationState
      extends UpdateDisplayState.DisplayState
    {
      public static final Parcelable.Creator<InAppNotificationState> CREATOR = new UpdateDisplayState.DisplayState.InAppNotificationState.1();
      private static String HIGHLIGHT_KEY = "com.com.mixpanel.android.mpmetrics.UpdateDisplayState.InAppNotificationState.HIGHLIGHT_KEY";
      private static String INAPP_KEY = "com.com.mixpanel.android.mpmetrics.UpdateDisplayState.InAppNotificationState.INAPP_KEY";
      public static final String TYPE = "InAppNotificationState";
      private final int mHighlightColor;
      private final InAppNotification mInAppNotification;
      
      private InAppNotificationState(Bundle paramBundle)
      {
        super();
        this.mInAppNotification = ((InAppNotification)paramBundle.getParcelable(INAPP_KEY));
        this.mHighlightColor = paramBundle.getInt(HIGHLIGHT_KEY);
      }
      
      public InAppNotificationState(InAppNotification paramInAppNotification, int paramInt)
      {
        super();
        this.mInAppNotification = paramInAppNotification;
        this.mHighlightColor = paramInt;
      }
      
      public int describeContents()
      {
        return 0;
      }
      
      public int getHighlightColor()
      {
        return this.mHighlightColor;
      }
      
      public InAppNotification getInAppNotification()
      {
        return this.mInAppNotification;
      }
      
      public String getType()
      {
        return "InAppNotificationState";
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        Bundle localBundle = new Bundle();
        localBundle.putParcelable(INAPP_KEY, this.mInAppNotification);
        localBundle.putInt(HIGHLIGHT_KEY, this.mHighlightColor);
        paramParcel.writeBundle(localBundle);
      }
    }
    
    public static final class SurveyState
      extends UpdateDisplayState.DisplayState
    {
      private static final String ANSWERS_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.ANSWERS_BUNDLE_KEY";
      private static final String BACKGROUND_COMPRESSED_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.BACKGROUND_COMPRESSED_BUNDLE_KEY";
      public static final Parcelable.Creator<SurveyState> CREATOR = new UpdateDisplayState.DisplayState.SurveyState.1();
      private static final String HIGHLIGHT_COLOR_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.HIGHLIGHT_COLOR_BUNDLE_KEY";
      private static final String SURVEY_BUNDLE_KEY = "com.mixpanel.android.mpmetrics.UpdateDisplayState.SURVEY_BUNDLE_KEY";
      public static final String TYPE = "SurveyState";
      private final UpdateDisplayState.AnswerMap mAnswers;
      private Bitmap mBackground;
      private int mHighlightColor;
      private final Survey mSurvey;
      
      private SurveyState(Bundle paramBundle)
      {
        super();
        this.mHighlightColor = paramBundle.getInt("com.mixpanel.android.mpmetrics.UpdateDisplayState.HIGHLIGHT_COLOR_BUNDLE_KEY");
        this.mAnswers = ((UpdateDisplayState.AnswerMap)paramBundle.getParcelable("com.mixpanel.android.mpmetrics.UpdateDisplayState.ANSWERS_BUNDLE_KEY"));
        byte[] arrayOfByte = paramBundle.getByteArray("com.mixpanel.android.mpmetrics.UpdateDisplayState.BACKGROUND_COMPRESSED_BUNDLE_KEY");
        if (arrayOfByte != null) {}
        for (this.mBackground = BitmapFactoryInstrumentation.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);; this.mBackground = null)
        {
          this.mSurvey = ((Survey)paramBundle.getParcelable("com.mixpanel.android.mpmetrics.UpdateDisplayState.SURVEY_BUNDLE_KEY"));
          return;
        }
      }
      
      public SurveyState(Survey paramSurvey)
      {
        super();
        this.mSurvey = paramSurvey;
        this.mAnswers = new UpdateDisplayState.AnswerMap();
        this.mHighlightColor = -16777216;
        this.mBackground = null;
      }
      
      public int describeContents()
      {
        return 0;
      }
      
      public UpdateDisplayState.AnswerMap getAnswers()
      {
        return this.mAnswers;
      }
      
      public Bitmap getBackground()
      {
        return this.mBackground;
      }
      
      public int getHighlightColor()
      {
        return this.mHighlightColor;
      }
      
      public Survey getSurvey()
      {
        return this.mSurvey;
      }
      
      public String getType()
      {
        return "SurveyState";
      }
      
      public void setBackground(Bitmap paramBitmap)
      {
        this.mBackground = paramBitmap;
      }
      
      public void setHighlightColor(int paramInt)
      {
        this.mHighlightColor = paramInt;
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("com.mixpanel.android.mpmetrics.UpdateDisplayState.HIGHLIGHT_COLOR_BUNDLE_KEY", this.mHighlightColor);
        localBundle.putParcelable("com.mixpanel.android.mpmetrics.UpdateDisplayState.ANSWERS_BUNDLE_KEY", this.mAnswers);
        Object localObject = null;
        if (this.mBackground != null)
        {
          localObject = new ByteArrayOutputStream();
          this.mBackground.compress(Bitmap.CompressFormat.PNG, 20, (OutputStream)localObject);
          localObject = ((ByteArrayOutputStream)localObject).toByteArray();
        }
        localBundle.putByteArray("com.mixpanel.android.mpmetrics.UpdateDisplayState.BACKGROUND_COMPRESSED_BUNDLE_KEY", (byte[])localObject);
        localBundle.putParcelable("com.mixpanel.android.mpmetrics.UpdateDisplayState.SURVEY_BUNDLE_KEY", this.mSurvey);
        paramParcel.writeBundle(localBundle);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/UpdateDisplayState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */