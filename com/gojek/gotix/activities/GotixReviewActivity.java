package com.gojek.gotix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.string;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.model.Driver;
import com.gojek.gotix.network.model.Review;
import com.gojek.gotix.presenter.GotixReviewPresenter;
import com.jakewharton.rxbinding.view.RxView;
import com.norbsoft.typefacehelper.TypefaceHelper;
import lib.gojek.base.helper.FontFaceHelper;
import nucleus.factory.RequiresPresenter;
import rx.Observable;

@RequiresPresenter(GotixReviewPresenter.class)
public class GotixReviewActivity
  extends GotixBaseActivity<GotixReviewPresenter>
{
  private static final float MIN_RATING = 1.0F;
  private static final float ZERO_RATING = 0.0F;
  public static boolean isRunning;
  private EditText appCommentField;
  private View appLayoutArea;
  private RatingBar appRatingBar;
  private ImageView driverAvatarImage;
  private EditText driverCommentField;
  private View driverLayoutArea;
  private TextView driverNameText;
  private RatingBar driverRatingBar;
  private CheckBox driverReviewInformation;
  private RatingBar.OnRatingBarChangeListener onRatingBarChangeListener = GotixReviewActivity..Lambda.1.lambdaFactory$(this);
  private int orderId;
  private TextView reviewAppName;
  private TextView reviewTitle;
  private ImageView submitReviewBtn;
  
  private void addClickListeners()
  {
    setEnableButtonSubmit(false);
    RxView.clicks(this.submitReviewBtn).subscribe(GotixReviewActivity..Lambda.2.lambdaFactory$(this));
  }
  
  private void addRatingBarChangeListener()
  {
    this.appRatingBar.setOnRatingBarChangeListener(this.onRatingBarChangeListener);
    this.driverRatingBar.setOnRatingBarChangeListener(this.onRatingBarChangeListener);
  }
  
  private void bindViewById()
  {
    this.reviewTitle = ((TextView)findViewById(R.id.review_title));
    this.reviewAppName = ((TextView)findViewById(R.id.review_app_name));
    this.appLayoutArea = findViewById(R.id.app_layout_area);
    this.appRatingBar = ((RatingBar)this.appLayoutArea.findViewById(R.id.review_rating_bar));
    this.appCommentField = ((EditText)this.appLayoutArea.findViewById(R.id.review_message));
    this.driverLayoutArea = findViewById(R.id.driver_layout_area);
    this.driverRatingBar = ((RatingBar)this.driverLayoutArea.findViewById(R.id.review_rating_bar));
    this.driverCommentField = ((EditText)this.driverLayoutArea.findViewById(R.id.review_message));
    this.driverReviewInformation = ((CheckBox)findViewById(R.id.review_information_checkbox));
    this.driverAvatarImage = ((ImageView)findViewById(R.id.review_driver_avatar));
    this.driverNameText = ((TextView)findViewById(R.id.review_driver_name));
    this.submitReviewBtn = ((ImageView)findViewById(R.id.submit_review_button));
  }
  
  private void checkValidRatingReview()
  {
    if (isRatingReviewValid()) {
      setEnableButtonSubmit(true);
    }
  }
  
  private boolean isRatingReviewValid()
  {
    return (this.appRatingBar.getRating() != 0.0F) && (this.driverRatingBar.getRating() != 0.0F);
  }
  
  private void setDriverData()
  {
    Object localObject = GotixData.getDriver(this.orderId);
    String str = ((Driver)localObject).getPhoto();
    localObject = ((Driver)localObject).getName();
    if (!TextUtils.isEmpty(str)) {
      Glide.with(getApplicationContext()).load(str).placeholder(R.drawable.gotix_driver_avatar_placeholder).dontAnimate().into(this.driverAvatarImage);
    }
    this.driverNameText.setText((CharSequence)localObject);
  }
  
  private void setEnableButtonSubmit(boolean paramBoolean)
  {
    runOnUiThread(GotixReviewActivity..Lambda.3.lambdaFactory$(this, paramBoolean));
  }
  
  private void setFontFace()
  {
    TypefaceHelper.typeface(this, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.reviewTitle, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.reviewAppName, FontFaceHelper.getBebasNeue());
    setFontTitleBar(FontFaceHelper.getBebasNeue());
  }
  
  private void setOrderId()
  {
    this.orderId = getIntent().getIntExtra("orderIdKey", 0);
  }
  
  private void submitReview()
  {
    GotixData.saveReview(this.orderId);
    ((GotixReviewPresenter)getPresenter()).submitReview(this.orderId, new Review(this.appRatingBar.getRating(), this.appCommentField.getText().toString(), this.driverRatingBar.getRating(), this.driverCommentField.getText().toString()));
  }
  
  protected int getLayout()
  {
    return R.layout.activity_gotix_review;
  }
  
  public void onBackPressed()
  {
    openMainActivity();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    bindViewById();
    addRatingBarChangeListener();
    addClickListeners();
    setFontFace();
    setOrderId();
    setDriverData();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    setWalletVisible(true);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.action_wallet)
    {
      Toast.makeText(getApplicationContext(), getString(R.string.action_wallet), 0).show();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    isRunning = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    isRunning = true;
  }
  
  public void openMainActivity()
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("lastActiveTab", 2);
    setResult(-1, localIntent);
    finish();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/activities/GotixReviewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */