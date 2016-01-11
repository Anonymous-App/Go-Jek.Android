package com.gojek.gobox.driverreview.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.driverreview.presenter.DriverReviewPresenter;
import com.gojek.gobox.networking.ConnectionManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class DriverReviewActivity
  extends BaseActivity
  implements View.OnClickListener, DriverReviewView
{
  private String mAvatar;
  private EditText mCommentField;
  private ImageView mDriverAvatar;
  private String mDriverName;
  private TextView mDriverNameText;
  private DriverReviewPresenter mDriverReviewPresenter;
  private String mOrderId;
  private ProgressBar mProgressBar;
  private RatingBar mRatingBar;
  private ImageView mSubmitButton;
  
  public void hideProgressBar()
  {
    this.mProgressBar.setVisibility(8);
  }
  
  public void hideSubmitButton()
  {
    this.mSubmitButton.setVisibility(8);
  }
  
  public void initOrderData()
  {
    this.mOrderId = getIntent().getStringExtra("order id");
    this.mAvatar = getIntent().getStringExtra("avatar");
    this.mDriverName = getIntent().getStringExtra("driver name");
  }
  
  public void initView()
  {
    this.mDriverAvatar = ((ImageView)findViewById(R.id.driver_avatar));
    this.mDriverNameText = ((TextView)findViewById(R.id.driver_name));
    this.mRatingBar = ((RatingBar)findViewById(R.id.driver_rating_bar));
    this.mCommentField = ((EditText)findViewById(R.id.comment_field));
    this.mSubmitButton = ((ImageView)findViewById(R.id.submit_button));
    this.mProgressBar = ((ProgressBar)findViewById(R.id.progress_bar));
    this.mSubmitButton.setOnClickListener(this);
    if (!TextUtils.isEmpty(this.mAvatar)) {
      Picasso.with(this).load("https://gobox-api.gojek.co.id/" + this.mAvatar).into(this.mDriverAvatar);
    }
    this.mDriverNameText.setText(this.mDriverName);
  }
  
  public void onBackPressed()
  {
    Toast.makeText(this, R.string.give_rating_message, 0).show();
  }
  
  public void onClick(View paramView)
  {
    if (ConnectionManager.isConnected(this))
    {
      if (this.mRatingBar.getRating() > 0.0F)
      {
        this.mDriverReviewPresenter.onSubmitButtonClicked(this.mOrderId, (int)this.mRatingBar.getRating(), this.mCommentField.getText().toString());
        return;
      }
      Toast.makeText(this, R.string.give_rating_message, 0).show();
      return;
    }
    noInternetConnectionHandler();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_driver_review);
    initToolbarView();
    setTitle(getString(R.string.my_booking_title));
    hideBackButton();
    this.mDriverReviewPresenter = PresenterFactory.createDriverReviewPresenter(this, getNetworkManager());
    this.mDriverReviewPresenter.onDriverReviewPresenterCreateView();
  }
  
  public void showProgressBar()
  {
    this.mProgressBar.setVisibility(0);
  }
  
  public void showReviewErrorMessage(Throwable paramThrowable)
  {
    errorConnectionHandler(paramThrowable);
  }
  
  public void showReviewSuccessMessage()
  {
    showSimpleDialog(null, getString(R.string.rating_success_message), getString(R.string.button_OK), false, DriverReviewActivity..Lambda.1.lambdaFactory$(this));
  }
  
  public void showSubmitButton()
  {
    this.mSubmitButton.setVisibility(0);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/driverreview/view/DriverReviewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */