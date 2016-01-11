package com.mixpanel.android.mpmetrics;

import android.util.Log;
import com.mixpanel.android.viewcrawler.UpdatesFromMixpanel;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;

class DecideMessages
{
  private static final String LOGTAG = "MixpanelAPI.DecideUpdts";
  private static final Set<Integer> mLoadedVariants = new HashSet();
  private String mDistinctId;
  private final OnNewResultsListener mListener;
  private final Set<Integer> mNotificationIds;
  private final Set<Integer> mSurveyIds;
  private final String mToken;
  private final List<InAppNotification> mUnseenNotifications;
  private final List<Survey> mUnseenSurveys;
  private final UpdatesFromMixpanel mUpdatesFromMixpanel;
  private JSONArray mVariants;
  
  public DecideMessages(String paramString, OnNewResultsListener paramOnNewResultsListener, UpdatesFromMixpanel paramUpdatesFromMixpanel)
  {
    this.mToken = paramString;
    this.mListener = paramOnNewResultsListener;
    this.mUpdatesFromMixpanel = paramUpdatesFromMixpanel;
    this.mDistinctId = null;
    this.mUnseenSurveys = new LinkedList();
    this.mUnseenNotifications = new LinkedList();
    this.mSurveyIds = new HashSet();
    this.mNotificationIds = new HashSet();
  }
  
  public String getDistinctId()
  {
    try
    {
      String str = this.mDistinctId;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public InAppNotification getNotification(int paramInt, boolean paramBoolean)
  {
    InAppNotification localInAppNotification2 = null;
    int i = 0;
    for (;;)
    {
      InAppNotification localInAppNotification1 = localInAppNotification2;
      try
      {
        if (i < this.mUnseenNotifications.size())
        {
          if (((InAppNotification)this.mUnseenNotifications.get(i)).getId() != paramInt) {
            break label87;
          }
          localInAppNotification2 = (InAppNotification)this.mUnseenNotifications.get(i);
          localInAppNotification1 = localInAppNotification2;
          if (!paramBoolean)
          {
            this.mUnseenNotifications.remove(i);
            localInAppNotification1 = localInAppNotification2;
          }
        }
        return localInAppNotification1;
        label87:
        i += 1;
      }
      finally {}
    }
  }
  
  public InAppNotification getNotification(boolean paramBoolean)
  {
    for (;;)
    {
      InAppNotification localInAppNotification;
      try
      {
        Object localObject1;
        if (this.mUnseenNotifications.isEmpty())
        {
          if (MPConfig.DEBUG) {
            Log.v("MixpanelAPI.DecideUpdts", "No unseen notifications exist, none will be returned.");
          }
          localObject1 = null;
          return (InAppNotification)localObject1;
        }
        localInAppNotification = (InAppNotification)this.mUnseenNotifications.remove(0);
        if (paramBoolean)
        {
          this.mUnseenNotifications.add(this.mUnseenNotifications.size(), localInAppNotification);
          localObject1 = localInAppNotification;
          continue;
        }
        localObject3 = localInAppNotification;
      }
      finally {}
      Object localObject3;
      if (MPConfig.DEBUG)
      {
        Log.v("MixpanelAPI.DecideUpdts", "Recording notification " + localInAppNotification + " as seen.");
        localObject3 = localInAppNotification;
      }
    }
  }
  
  public Survey getSurvey(int paramInt, boolean paramBoolean)
  {
    Survey localSurvey2 = null;
    int i = 0;
    for (;;)
    {
      Survey localSurvey1 = localSurvey2;
      try
      {
        if (i < this.mUnseenSurveys.size())
        {
          if (((Survey)this.mUnseenSurveys.get(i)).getId() != paramInt) {
            break label87;
          }
          localSurvey2 = (Survey)this.mUnseenSurveys.get(i);
          localSurvey1 = localSurvey2;
          if (!paramBoolean)
          {
            this.mUnseenSurveys.remove(i);
            localSurvey1 = localSurvey2;
          }
        }
        return localSurvey1;
        label87:
        i += 1;
      }
      finally {}
    }
  }
  
  /* Error */
  public Survey getSurvey(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 54	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenSurveys	Ljava/util/List;
    //   6: invokeinterface 87 1 0
    //   11: istore_2
    //   12: iload_2
    //   13: ifeq +9 -> 22
    //   16: aconst_null
    //   17: astore_3
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_3
    //   21: areturn
    //   22: aload_0
    //   23: getfield 54	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenSurveys	Ljava/util/List;
    //   26: iconst_0
    //   27: invokeinterface 82 2 0
    //   32: checkcast 126	com/mixpanel/android/mpmetrics/Survey
    //   35: astore 4
    //   37: aload 4
    //   39: astore_3
    //   40: iload_1
    //   41: ifeq -23 -> 18
    //   44: aload_0
    //   45: getfield 54	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenSurveys	Ljava/util/List;
    //   48: aload_0
    //   49: getfield 54	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenSurveys	Ljava/util/List;
    //   52: invokeinterface 70 1 0
    //   57: aload 4
    //   59: invokeinterface 105 3 0
    //   64: aload 4
    //   66: astore_3
    //   67: goto -49 -> 18
    //   70: astore_3
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_3
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	DecideMessages
    //   0	75	1	paramBoolean	boolean
    //   11	2	2	bool	boolean
    //   17	50	3	localObject1	Object
    //   70	4	3	localObject2	Object
    //   35	30	4	localSurvey	Survey
    // Exception table:
    //   from	to	target	type
    //   2	12	70	finally
    //   22	37	70	finally
    //   44	64	70	finally
  }
  
  public String getToken()
  {
    return this.mToken;
  }
  
  public JSONArray getVariants()
  {
    try
    {
      JSONArray localJSONArray = this.mVariants;
      return localJSONArray;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean hasUpdatesAvailable()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenNotifications	Ljava/util/List;
    //   6: invokeinterface 87 1 0
    //   11: ifeq +24 -> 35
    //   14: aload_0
    //   15: getfield 54	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenSurveys	Ljava/util/List;
    //   18: invokeinterface 87 1 0
    //   23: ifeq +12 -> 35
    //   26: aload_0
    //   27: getfield 133	com/mixpanel/android/mpmetrics/DecideMessages:mVariants	Lorg/json/JSONArray;
    //   30: astore_2
    //   31: aload_2
    //   32: ifnull +9 -> 41
    //   35: iconst_1
    //   36: istore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: iload_1
    //   40: ireturn
    //   41: iconst_0
    //   42: istore_1
    //   43: goto -6 -> 37
    //   46: astore_2
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_2
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	DecideMessages
    //   36	7	1	bool	boolean
    //   30	2	2	localJSONArray	JSONArray
    //   46	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	31	46	finally
  }
  
  /* Error */
  public void reportResults(List<Survey> paramList, List<InAppNotification> paramList1, JSONArray paramJSONArray1, JSONArray paramJSONArray2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore 5
    //   5: aload_0
    //   6: getfield 47	com/mixpanel/android/mpmetrics/DecideMessages:mUpdatesFromMixpanel	Lcom/mixpanel/android/viewcrawler/UpdatesFromMixpanel;
    //   9: aload_3
    //   10: invokeinterface 144 2 0
    //   15: aload_1
    //   16: invokeinterface 148 1 0
    //   21: astore_3
    //   22: aload_3
    //   23: invokeinterface 153 1 0
    //   28: ifeq +71 -> 99
    //   31: aload_3
    //   32: invokeinterface 157 1 0
    //   37: checkcast 126	com/mixpanel/android/mpmetrics/Survey
    //   40: astore 11
    //   42: aload 11
    //   44: invokevirtual 127	com/mixpanel/android/mpmetrics/Survey:getId	()I
    //   47: istore 6
    //   49: aload_0
    //   50: getfield 58	com/mixpanel/android/mpmetrics/DecideMessages:mSurveyIds	Ljava/util/Set;
    //   53: iload 6
    //   55: invokestatic 163	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   58: invokeinterface 169 2 0
    //   63: ifne -41 -> 22
    //   66: aload_0
    //   67: getfield 58	com/mixpanel/android/mpmetrics/DecideMessages:mSurveyIds	Ljava/util/Set;
    //   70: iload 6
    //   72: invokestatic 163	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   75: invokeinterface 171 2 0
    //   80: pop
    //   81: aload_0
    //   82: getfield 54	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenSurveys	Ljava/util/List;
    //   85: aload 11
    //   87: invokeinterface 172 2 0
    //   92: pop
    //   93: iconst_1
    //   94: istore 5
    //   96: goto -74 -> 22
    //   99: aload_2
    //   100: invokeinterface 148 1 0
    //   105: astore_3
    //   106: aload_3
    //   107: invokeinterface 153 1 0
    //   112: ifeq +71 -> 183
    //   115: aload_3
    //   116: invokeinterface 157 1 0
    //   121: checkcast 76	com/mixpanel/android/mpmetrics/InAppNotification
    //   124: astore 11
    //   126: aload 11
    //   128: invokevirtual 79	com/mixpanel/android/mpmetrics/InAppNotification:getId	()I
    //   131: istore 6
    //   133: aload_0
    //   134: getfield 60	com/mixpanel/android/mpmetrics/DecideMessages:mNotificationIds	Ljava/util/Set;
    //   137: iload 6
    //   139: invokestatic 163	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   142: invokeinterface 169 2 0
    //   147: ifne -41 -> 106
    //   150: aload_0
    //   151: getfield 60	com/mixpanel/android/mpmetrics/DecideMessages:mNotificationIds	Ljava/util/Set;
    //   154: iload 6
    //   156: invokestatic 163	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   159: invokeinterface 171 2 0
    //   164: pop
    //   165: aload_0
    //   166: getfield 56	com/mixpanel/android/mpmetrics/DecideMessages:mUnseenNotifications	Ljava/util/List;
    //   169: aload 11
    //   171: invokeinterface 172 2 0
    //   176: pop
    //   177: iconst_1
    //   178: istore 5
    //   180: goto -74 -> 106
    //   183: aload 4
    //   185: invokevirtual 177	org/json/JSONArray:length	()I
    //   188: istore 10
    //   190: iconst_0
    //   191: istore 9
    //   193: iconst_0
    //   194: istore 7
    //   196: iload 9
    //   198: istore 8
    //   200: iload 5
    //   202: istore 6
    //   204: iload 7
    //   206: iload 10
    //   208: if_icmpge +43 -> 251
    //   211: aload 4
    //   213: iload 7
    //   215: invokevirtual 181	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   218: astore_3
    //   219: getstatic 38	com/mixpanel/android/mpmetrics/DecideMessages:mLoadedVariants	Ljava/util/Set;
    //   222: aload_3
    //   223: ldc -73
    //   225: invokevirtual 189	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   228: invokestatic 163	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   231: invokeinterface 169 2 0
    //   236: ifne +251 -> 487
    //   239: aload_0
    //   240: aload 4
    //   242: putfield 133	com/mixpanel/android/mpmetrics/DecideMessages:mVariants	Lorg/json/JSONArray;
    //   245: iconst_1
    //   246: istore 6
    //   248: iconst_1
    //   249: istore 8
    //   251: iload 8
    //   253: ifeq +135 -> 388
    //   256: getstatic 38	com/mixpanel/android/mpmetrics/DecideMessages:mLoadedVariants	Ljava/util/Set;
    //   259: invokeinterface 192 1 0
    //   264: iconst_0
    //   265: istore 5
    //   267: iload 5
    //   269: iload 10
    //   271: if_icmpge +117 -> 388
    //   274: aload_0
    //   275: getfield 133	com/mixpanel/android/mpmetrics/DecideMessages:mVariants	Lorg/json/JSONArray;
    //   278: iload 5
    //   280: invokevirtual 181	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   283: astore_3
    //   284: getstatic 38	com/mixpanel/android/mpmetrics/DecideMessages:mLoadedVariants	Ljava/util/Set;
    //   287: aload_3
    //   288: ldc -73
    //   290: invokevirtual 189	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   293: invokestatic 163	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   296: invokeinterface 171 2 0
    //   301: pop
    //   302: iload 5
    //   304: iconst_1
    //   305: iadd
    //   306: istore 5
    //   308: goto -41 -> 267
    //   311: astore_3
    //   312: ldc 11
    //   314: new 107	java/lang/StringBuilder
    //   317: dup
    //   318: invokespecial 108	java/lang/StringBuilder:<init>	()V
    //   321: ldc -62
    //   323: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: iload 7
    //   328: invokevirtual 197	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   331: ldc -57
    //   333: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   339: aload_3
    //   340: invokestatic 203	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   343: pop
    //   344: goto +143 -> 487
    //   347: astore_3
    //   348: ldc 11
    //   350: new 107	java/lang/StringBuilder
    //   353: dup
    //   354: invokespecial 108	java/lang/StringBuilder:<init>	()V
    //   357: ldc -62
    //   359: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: iload 5
    //   364: invokevirtual 197	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   367: ldc -51
    //   369: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: aload_3
    //   376: invokestatic 203	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   379: pop
    //   380: goto -78 -> 302
    //   383: astore_1
    //   384: aload_0
    //   385: monitorexit
    //   386: aload_1
    //   387: athrow
    //   388: getstatic 93	com/mixpanel/android/mpmetrics/MPConfig:DEBUG	Z
    //   391: ifeq +65 -> 456
    //   394: ldc 11
    //   396: new 107	java/lang/StringBuilder
    //   399: dup
    //   400: invokespecial 108	java/lang/StringBuilder:<init>	()V
    //   403: ldc -49
    //   405: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: aload_1
    //   409: invokeinterface 70 1 0
    //   414: invokevirtual 197	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   417: ldc -47
    //   419: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   422: aload_2
    //   423: invokeinterface 70 1 0
    //   428: invokevirtual 197	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   431: ldc -45
    //   433: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: aload 4
    //   438: invokevirtual 177	org/json/JSONArray:length	()I
    //   441: invokevirtual 197	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   444: ldc -43
    //   446: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   452: invokestatic 101	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   455: pop
    //   456: iload 6
    //   458: ifeq +26 -> 484
    //   461: aload_0
    //   462: invokevirtual 215	com/mixpanel/android/mpmetrics/DecideMessages:hasUpdatesAvailable	()Z
    //   465: ifeq +19 -> 484
    //   468: aload_0
    //   469: getfield 45	com/mixpanel/android/mpmetrics/DecideMessages:mListener	Lcom/mixpanel/android/mpmetrics/DecideMessages$OnNewResultsListener;
    //   472: ifnull +12 -> 484
    //   475: aload_0
    //   476: getfield 45	com/mixpanel/android/mpmetrics/DecideMessages:mListener	Lcom/mixpanel/android/mpmetrics/DecideMessages$OnNewResultsListener;
    //   479: invokeinterface 218 1 0
    //   484: aload_0
    //   485: monitorexit
    //   486: return
    //   487: iload 7
    //   489: iconst_1
    //   490: iadd
    //   491: istore 7
    //   493: goto -297 -> 196
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	496	0	this	DecideMessages
    //   0	496	1	paramList	List<Survey>
    //   0	496	2	paramList1	List<InAppNotification>
    //   0	496	3	paramJSONArray1	JSONArray
    //   0	496	4	paramJSONArray2	JSONArray
    //   3	360	5	i	int
    //   47	410	6	j	int
    //   194	298	7	k	int
    //   198	54	8	m	int
    //   191	6	9	n	int
    //   188	84	10	i1	int
    //   40	130	11	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   211	245	311	org/json/JSONException
    //   274	302	347	org/json/JSONException
    //   5	22	383	finally
    //   22	93	383	finally
    //   99	106	383	finally
    //   106	177	383	finally
    //   183	190	383	finally
    //   211	245	383	finally
    //   256	264	383	finally
    //   274	302	383	finally
    //   312	344	383	finally
    //   348	380	383	finally
    //   388	456	383	finally
    //   461	484	383	finally
  }
  
  public void setDistinctId(String paramString)
  {
    try
    {
      this.mUnseenSurveys.clear();
      this.mUnseenNotifications.clear();
      this.mDistinctId = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static abstract interface OnNewResultsListener
  {
    public abstract void onNewResults();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/mpmetrics/DecideMessages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */