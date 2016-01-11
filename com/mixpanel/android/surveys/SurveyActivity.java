package com.mixpanel.android.surveys;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mixpanel.android.R.anim;
import com.mixpanel.android.R.drawable;
import com.mixpanel.android.R.id;
import com.mixpanel.android.R.layout;
import com.mixpanel.android.R.string;
import com.mixpanel.android.mpmetrics.InAppNotification;
import com.mixpanel.android.mpmetrics.MPConfig;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mixpanel.android.mpmetrics.MixpanelAPI.People;
import com.mixpanel.android.mpmetrics.Survey;
import com.mixpanel.android.mpmetrics.Survey.Question;
import com.mixpanel.android.mpmetrics.Survey.QuestionType;
import com.mixpanel.android.mpmetrics.UpdateDisplayState;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.AnswerMap;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.InAppNotificationState;
import com.mixpanel.android.mpmetrics.UpdateDisplayState.DisplayState.SurveyState;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(16)
@Instrumented
public class SurveyActivity
  extends Activity
  implements TraceFieldInterface
{
  private static final String CURRENT_QUESTION_BUNDLE_KEY = "com.mixpanel.android.surveys.SurveyActivity.CURRENT_QUESTION_BUNDLE_KEY";
  private static final int GRAY_30PERCENT = Color.argb(255, 90, 90, 90);
  public static final String INTENT_ID_KEY = "com.mixpanel.android.surveys.SurveyActivity.INTENT_ID_KEY";
  private static final String LOGTAG = "MixpanelAPI.SrvyActvty";
  private static final int SHADOW_SIZE_THRESHOLD_PX = 100;
  private static final String SURVEY_BEGUN_BUNDLE_KEY = "com.mixpanel.android.surveys.SurveyActivity.SURVEY_BEGIN_BUNDLE_KEY";
  private static final String SURVEY_STATE_BUNDLE_KEY = "com.mixpanel.android.surveys.SurveyActivity.SURVEY_STATE_BUNDLE_KEY";
  private CardCarouselLayout mCardHolder;
  private int mCurrentQuestion = 0;
  private AlertDialog mDialog;
  private int mIntentId = -1;
  private MixpanelAPI mMixpanel;
  private View mNextButton;
  private View mPreviousButton;
  private TextView mProgressTextView;
  private boolean mSurveyBegun = false;
  private UpdateDisplayState mUpdateDisplayState;
  
  private void completeSurvey()
  {
    finish();
  }
  
  private UpdateDisplayState.DisplayState.SurveyState getSurveyState()
  {
    return (UpdateDisplayState.DisplayState.SurveyState)this.mUpdateDisplayState.getDisplayState();
  }
  
  private void goToNextQuestion()
  {
    int i = getSurveyState().getSurvey().getQuestions().size();
    if (this.mCurrentQuestion < i - 1)
    {
      showQuestion(this.mCurrentQuestion + 1);
      return;
    }
    completeSurvey();
  }
  
  private void goToPreviousQuestion()
  {
    if (this.mCurrentQuestion > 0)
    {
      showQuestion(this.mCurrentQuestion - 1);
      return;
    }
    completeSurvey();
  }
  
  private boolean isShowingInApp()
  {
    if (this.mUpdateDisplayState == null) {
      return false;
    }
    return "InAppNotificationState".equals(this.mUpdateDisplayState.getDisplayState().getType());
  }
  
  private boolean isShowingSurvey()
  {
    if (this.mUpdateDisplayState == null) {
      return false;
    }
    return "SurveyState".equals(this.mUpdateDisplayState.getDisplayState().getType());
  }
  
  private void onCreateInAppNotification(Bundle paramBundle)
  {
    setContentView(R.layout.com_mixpanel_android_activity_notification_full);
    Object localObject3 = (ImageView)findViewById(R.id.com_mixpanel_android_notification_gradient);
    Object localObject1 = (FadingImageView)findViewById(R.id.com_mixpanel_android_notification_image);
    paramBundle = (TextView)findViewById(R.id.com_mixpanel_android_notification_title);
    TextView localTextView = (TextView)findViewById(R.id.com_mixpanel_android_notification_subtext);
    Button localButton = (Button)findViewById(R.id.com_mixpanel_android_notification_button);
    LinearLayout localLinearLayout = (LinearLayout)findViewById(R.id.com_mixpanel_android_button_exit_wrapper);
    final Object localObject2 = ((UpdateDisplayState.DisplayState.InAppNotificationState)this.mUpdateDisplayState.getDisplayState()).getInAppNotification();
    Object localObject4 = getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    ((Display)localObject4).getSize(localPoint);
    if (getResources().getConfiguration().orientation == 1)
    {
      localObject4 = (RelativeLayout.LayoutParams)localLinearLayout.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject4).setMargins(0, 0, 0, (int)(localPoint.y * 0.06F));
      localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject4);
    }
    localObject4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[] { -446668676, -448247715, -451405793, -451405793 });
    ((GradientDrawable)localObject4).setGradientType(1);
    if (getResources().getConfiguration().orientation == 2)
    {
      ((GradientDrawable)localObject4).setGradientCenter(0.25F, 0.5F);
      ((GradientDrawable)localObject4).setGradientRadius(Math.min(localPoint.x, localPoint.y) * 0.8F);
      setViewBackground((View)localObject3, (Drawable)localObject4);
      paramBundle.setText(((InAppNotification)localObject2).getTitle());
      localTextView.setText(((InAppNotification)localObject2).getBody());
      localObject3 = ((InAppNotification)localObject2).getImage();
      ((FadingImageView)localObject1).setBackgroundResource(R.drawable.com_mixpanel_android_square_dropshadow);
      if ((((Bitmap)localObject3).getWidth() >= 100) && (((Bitmap)localObject3).getHeight() >= 100)) {
        break label550;
      }
      ((FadingImageView)localObject1).setBackgroundResource(R.drawable.com_mixpanel_android_square_nodropshadow);
    }
    for (;;)
    {
      ((FadingImageView)localObject1).setImageBitmap((Bitmap)localObject3);
      localObject3 = ((InAppNotification)localObject2).getCallToActionUrl();
      if ((localObject3 != null) && (((String)localObject3).length() > 0)) {
        localButton.setText(((InAppNotification)localObject2).getCallToAction());
      }
      localButton.setOnClickListener(new View.OnClickListener()
      {
        /* Error */
        public void onClick(View paramAnonymousView)
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 21	com/mixpanel/android/surveys/SurveyActivity$1:val$inApp	Lcom/mixpanel/android/mpmetrics/InAppNotification;
          //   4: invokevirtual 37	com/mixpanel/android/mpmetrics/InAppNotification:getCallToActionUrl	()Ljava/lang/String;
          //   7: astore_1
          //   8: aload_1
          //   9: ifnull +55 -> 64
          //   12: aload_1
          //   13: invokevirtual 43	java/lang/String:length	()I
          //   16: ifle +48 -> 64
          //   19: aload_1
          //   20: invokestatic 49	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
          //   23: astore_1
          //   24: new 51	android/content/Intent
          //   27: dup
          //   28: ldc 53
          //   30: aload_1
          //   31: invokespecial 56	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
          //   34: astore_1
          //   35: aload_0
          //   36: getfield 19	com/mixpanel/android/surveys/SurveyActivity$1:this$0	Lcom/mixpanel/android/surveys/SurveyActivity;
          //   39: aload_1
          //   40: invokevirtual 60	com/mixpanel/android/surveys/SurveyActivity:startActivity	(Landroid/content/Intent;)V
          //   43: aload_0
          //   44: getfield 19	com/mixpanel/android/surveys/SurveyActivity$1:this$0	Lcom/mixpanel/android/surveys/SurveyActivity;
          //   47: invokestatic 64	com/mixpanel/android/surveys/SurveyActivity:access$000	(Lcom/mixpanel/android/surveys/SurveyActivity;)Lcom/mixpanel/android/mpmetrics/MixpanelAPI;
          //   50: invokevirtual 70	com/mixpanel/android/mpmetrics/MixpanelAPI:getPeople	()Lcom/mixpanel/android/mpmetrics/MixpanelAPI$People;
          //   53: ldc 72
          //   55: aload_0
          //   56: getfield 21	com/mixpanel/android/surveys/SurveyActivity$1:val$inApp	Lcom/mixpanel/android/mpmetrics/InAppNotification;
          //   59: invokeinterface 78 3 0
          //   64: aload_0
          //   65: getfield 19	com/mixpanel/android/surveys/SurveyActivity$1:this$0	Lcom/mixpanel/android/surveys/SurveyActivity;
          //   68: invokevirtual 81	com/mixpanel/android/surveys/SurveyActivity:finish	()V
          //   71: aload_0
          //   72: getfield 19	com/mixpanel/android/surveys/SurveyActivity$1:this$0	Lcom/mixpanel/android/surveys/SurveyActivity;
          //   75: invokestatic 85	com/mixpanel/android/surveys/SurveyActivity:access$100	(Lcom/mixpanel/android/surveys/SurveyActivity;)I
          //   78: invokestatic 91	com/mixpanel/android/mpmetrics/UpdateDisplayState:releaseDisplayState	(I)V
          //   81: return
          //   82: astore_1
          //   83: ldc 93
          //   85: ldc 95
          //   87: aload_1
          //   88: invokestatic 101	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
          //   91: pop
          //   92: return
          //   93: astore_1
          //   94: ldc 93
          //   96: ldc 103
          //   98: invokestatic 106	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
          //   101: pop
          //   102: goto -38 -> 64
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	105	0	this	1
          //   0	105	1	paramAnonymousView	View
          // Exception table:
          //   from	to	target	type
          //   19	24	82	java/lang/IllegalArgumentException
          //   24	64	93	android/content/ActivityNotFoundException
        }
      });
      localButton.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            paramAnonymousView.setBackgroundResource(R.drawable.com_mixpanel_android_cta_button_highlight);
          }
          for (;;)
          {
            return false;
            paramAnonymousView.setBackgroundResource(R.drawable.com_mixpanel_android_cta_button);
          }
        }
      });
      localLinearLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SurveyActivity.this.finish();
          UpdateDisplayState.releaseDisplayState(SurveyActivity.this.mIntentId);
        }
      });
      localObject2 = new ScaleAnimation(0.95F, 1.0F, 0.95F, 1.0F, 1, 0.5F, 1, 1.0F);
      ((ScaleAnimation)localObject2).setDuration(200L);
      ((FadingImageView)localObject1).startAnimation((Animation)localObject2);
      localObject1 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.5F, 1, 0.0F);
      ((TranslateAnimation)localObject1).setInterpolator(new DecelerateInterpolator());
      ((TranslateAnimation)localObject1).setDuration(200L);
      paramBundle.startAnimation((Animation)localObject1);
      localTextView.startAnimation((Animation)localObject1);
      localButton.startAnimation((Animation)localObject1);
      localLinearLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.com_mixpanel_android_fade_in));
      return;
      ((GradientDrawable)localObject4).setGradientCenter(0.5F, 0.33F);
      ((GradientDrawable)localObject4).setGradientRadius(Math.min(localPoint.x, localPoint.y) * 0.7F);
      break;
      label550:
      if (Color.alpha(Bitmap.createScaledBitmap((Bitmap)localObject3, 1, 1, false).getPixel(0, 0)) < 255) {
        ((FadingImageView)localObject1).setBackgroundResource(R.drawable.com_mixpanel_android_square_nodropshadow);
      }
    }
  }
  
  private void onCreateSurvey(Bundle paramBundle)
  {
    requestOrientationLock();
    if (paramBundle != null)
    {
      this.mCurrentQuestion = paramBundle.getInt("com.mixpanel.android.surveys.SurveyActivity.CURRENT_QUESTION_BUNDLE_KEY", 0);
      this.mSurveyBegun = paramBundle.getBoolean("com.mixpanel.android.surveys.SurveyActivity.SURVEY_BEGIN_BUNDLE_KEY");
    }
    if (this.mUpdateDisplayState.getDistinctId() == null)
    {
      Log.i("MixpanelAPI.SrvyActvty", "Can't show a survey to a user with no distinct id set");
      finish();
      return;
    }
    setContentView(R.layout.com_mixpanel_android_activity_survey);
    paramBundle = getSurveyState().getBackground();
    if (paramBundle == null) {
      findViewById(R.id.com_mixpanel_android_activity_survey_id).setBackgroundColor(GRAY_30PERCENT);
    }
    for (;;)
    {
      this.mPreviousButton = findViewById(R.id.com_mixpanel_android_button_previous);
      this.mNextButton = findViewById(R.id.com_mixpanel_android_button_next);
      this.mProgressTextView = ((TextView)findViewById(R.id.com_mixpanel_android_progress_text));
      this.mCardHolder = ((CardCarouselLayout)findViewById(R.id.com_mixpanel_android_question_card_holder));
      this.mCardHolder.setOnQuestionAnsweredListener(new CardCarouselLayout.OnQuestionAnsweredListener()
      {
        public void onQuestionAnswered(Survey.Question paramAnonymousQuestion, String paramAnonymousString)
        {
          SurveyActivity.this.saveAnswer(paramAnonymousQuestion, paramAnonymousString);
          SurveyActivity.this.goToNextQuestion();
        }
      });
      return;
      getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(), paramBundle));
    }
  }
  
  @SuppressLint({"SimpleDateFormat"})
  private void onDestroySurvey()
  {
    if (this.mMixpanel != null)
    {
      if (this.mUpdateDisplayState != null)
      {
        Object localObject2 = getSurveyState();
        Survey localSurvey = ((UpdateDisplayState.DisplayState.SurveyState)localObject2).getSurvey();
        Object localObject3 = localSurvey.getQuestions();
        Object localObject1 = this.mUpdateDisplayState.getDistinctId();
        localObject1 = this.mMixpanel.getPeople().withIdentity((String)localObject1);
        ((MixpanelAPI.People)localObject1).append("$responses", Integer.valueOf(localSurvey.getCollectionId()));
        localObject2 = ((UpdateDisplayState.DisplayState.SurveyState)localObject2).getAnswers();
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = (Survey.Question)((Iterator)localObject3).next();
          String str = ((UpdateDisplayState.AnswerMap)localObject2).get(Integer.valueOf(((Survey.Question)localObject4).getId()));
          if (str != null) {
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("$survey_id", localSurvey.getId());
              localJSONObject.put("$collection_id", localSurvey.getCollectionId());
              localJSONObject.put("$question_id", ((Survey.Question)localObject4).getId());
              localJSONObject.put("$question_type", ((Survey.Question)localObject4).getType().toString());
              localObject4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
              ((DateFormat)localObject4).setTimeZone(TimeZone.getTimeZone("UTC"));
              localJSONObject.put("$time", ((DateFormat)localObject4).format(new Date()));
              localJSONObject.put("$value", str);
              ((MixpanelAPI.People)localObject1).append("$answers", localJSONObject);
            }
            catch (JSONException localJSONException)
            {
              Log.e("MixpanelAPI.SrvyActvty", "Couldn't record user's answer.", localJSONException);
            }
          }
        }
      }
      this.mMixpanel.flush();
    }
    UpdateDisplayState.releaseDisplayState(this.mIntentId);
  }
  
  private void onSaveInstanceStateSurvey(Bundle paramBundle)
  {
    paramBundle.putBoolean("com.mixpanel.android.surveys.SurveyActivity.SURVEY_BEGIN_BUNDLE_KEY", this.mSurveyBegun);
    paramBundle.putInt("com.mixpanel.android.surveys.SurveyActivity.CURRENT_QUESTION_BUNDLE_KEY", this.mCurrentQuestion);
    paramBundle.putParcelable("com.mixpanel.android.surveys.SurveyActivity.SURVEY_STATE_BUNDLE_KEY", this.mUpdateDisplayState);
  }
  
  private void onStartSurvey()
  {
    if (this.mSurveyBegun) {
      return;
    }
    if (!MPConfig.getInstance(this).getTestMode()) {
      trackSurveyAttempted();
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.com_mixpanel_android_survey_prompt_dialog_title);
    localBuilder.setMessage(R.string.com_mixpanel_android_survey_prompt_dialog_message);
    localBuilder.setPositiveButton(R.string.com_mixpanel_android_sure, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        SurveyActivity.this.findViewById(R.id.com_mixpanel_android_activity_survey_id).setVisibility(0);
        SurveyActivity.access$402(SurveyActivity.this, true);
        SurveyActivity.this.showQuestion(SurveyActivity.this.mCurrentQuestion);
      }
    });
    localBuilder.setNegativeButton(R.string.com_mixpanel_android_no_thanks, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        SurveyActivity.this.finish();
      }
    });
    localBuilder.setCancelable(false);
    this.mDialog = localBuilder.create();
    this.mDialog.show();
  }
  
  @SuppressLint({"NewApi"})
  private void requestOrientationLock()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      setRequestedOrientation(14);
    }
    int i;
    do
    {
      return;
      i = getResources().getConfiguration().orientation;
      if (i == 2)
      {
        setRequestedOrientation(0);
        return;
      }
    } while (i != 1);
    setRequestedOrientation(1);
  }
  
  private void saveAnswer(Survey.Question paramQuestion, String paramString)
  {
    getSurveyState().getAnswers().put(Integer.valueOf(paramQuestion.getId()), paramString.toString());
  }
  
  @SuppressLint({"NewApi"})
  private void setViewBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 16)
    {
      paramView.setBackgroundDrawable(paramDrawable);
      return;
    }
    paramView.setBackground(paramDrawable);
  }
  
  private void showQuestion(int paramInt)
  {
    Object localObject = getSurveyState();
    List localList = ((UpdateDisplayState.DisplayState.SurveyState)localObject).getSurvey().getQuestions();
    label56:
    int i;
    Survey.Question localQuestion;
    if ((paramInt == 0) || (localList.size() == 0))
    {
      this.mPreviousButton.setEnabled(false);
      if (paramInt < localList.size() - 1) {
        break label181;
      }
      this.mNextButton.setEnabled(false);
      i = this.mCurrentQuestion;
      this.mCurrentQuestion = paramInt;
      localQuestion = (Survey.Question)localList.get(paramInt);
      localObject = ((UpdateDisplayState.DisplayState.SurveyState)localObject).getAnswers().get(Integer.valueOf(localQuestion.getId()));
      if (i >= paramInt) {
        break label192;
      }
    }
    for (;;)
    {
      try
      {
        this.mCardHolder.moveTo(localQuestion, (String)localObject, CardCarouselLayout.Direction.FORWARD);
        if (localList.size() <= 1) {
          break label234;
        }
        this.mProgressTextView.setText("" + (paramInt + 1) + " of " + localList.size());
        return;
      }
      catch (CardCarouselLayout.UnrecognizedAnswerTypeException localUnrecognizedAnswerTypeException)
      {
        label181:
        label192:
        goToNextQuestion();
        return;
      }
      this.mPreviousButton.setEnabled(true);
      break;
      this.mNextButton.setEnabled(true);
      break label56;
      if (i > paramInt) {
        this.mCardHolder.moveTo(localQuestion, (String)localObject, CardCarouselLayout.Direction.BACKWARD);
      } else {
        this.mCardHolder.replaceTo(localQuestion, (String)localObject);
      }
    }
    label234:
    this.mProgressTextView.setText("");
  }
  
  private void trackSurveyAttempted()
  {
    Survey localSurvey = getSurveyState().getSurvey();
    MixpanelAPI.People localPeople = this.mMixpanel.getPeople().withIdentity(this.mUpdateDisplayState.getDistinctId());
    localPeople.append("$surveys", Integer.valueOf(localSurvey.getId()));
    localPeople.append("$collections", Integer.valueOf(localSurvey.getCollectionId()));
  }
  
  public void completeSurvey(View paramView)
  {
    completeSurvey();
  }
  
  public void goToNextQuestion(View paramView)
  {
    goToNextQuestion();
  }
  
  public void goToPreviousQuestion(View paramView)
  {
    goToPreviousQuestion();
  }
  
  public void onBackPressed()
  {
    if ((isShowingSurvey()) && (this.mCurrentQuestion > 0))
    {
      goToPreviousQuestion();
      return;
    }
    if (isShowingInApp()) {
      UpdateDisplayState.releaseDisplayState(this.mIntentId);
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    TraceMachine.startTracing("SurveyActivity");
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "SurveyActivity#onCreate", null);
      super.onCreate(paramBundle);
      this.mIntentId = getIntent().getIntExtra("com.mixpanel.android.surveys.SurveyActivity.INTENT_ID_KEY", Integer.MAX_VALUE);
      this.mUpdateDisplayState = UpdateDisplayState.claimDisplayState(this.mIntentId);
      if (this.mUpdateDisplayState == null)
      {
        Log.e("MixpanelAPI.SrvyActvty", "SurveyActivity intent received, but nothing was found to show.");
        finish();
        TraceMachine.exitMethod();
        return;
      }
    }
    catch (NoSuchFieldError localNoSuchFieldError)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "SurveyActivity#onCreate", null);
      }
      this.mMixpanel = MixpanelAPI.getInstance(this, this.mUpdateDisplayState.getToken());
      if (!isShowingInApp()) {
        break label116;
      }
    }
    onCreateInAppNotification(paramBundle);
    for (;;)
    {
      TraceMachine.exitMethod();
      return;
      label116:
      if (isShowingSurvey()) {
        onCreateSurvey(paramBundle);
      } else {
        finish();
      }
    }
  }
  
  protected void onDestroy()
  {
    if (isShowingSurvey()) {
      onDestroySurvey();
    }
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.mDialog != null)
    {
      this.mDialog.dismiss();
      this.mDialog = null;
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (isShowingSurvey()) {
      onSaveInstanceStateSurvey(paramBundle);
    }
  }
  
  protected void onStart()
  {
    ApplicationStateMonitor.getInstance().activityStarted();
    super.onStart();
    UpdateDisplayState.DisplayState localDisplayState = this.mUpdateDisplayState.getDisplayState();
    if ((localDisplayState != null) && (localDisplayState.getType() == "SurveyState")) {
      onStartSurvey();
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/surveys/SurveyActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */