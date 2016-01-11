package com.gojek.gobox.ordercompleteconfirmation.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gojek.gobox.R.id;
import com.gojek.gobox.R.layout;
import com.gojek.gobox.R.string;
import com.gojek.gobox.base.BaseActivity;
import com.gojek.gobox.base.PresenterFactory;
import com.gojek.gobox.networking.ConnectionManager;
import com.gojek.gobox.ordercompleteconfirmation.presenter.OrderCompleteConfirmationPresenter;

public class OrderCompleteConfirmation
  extends BaseActivity
  implements View.OnClickListener, OrderCompleteConfirmationView
{
  private CountDownTimer mCountDownTimer;
  private LinearLayout mNoButton;
  private OrderCompleteConfirmationPresenter mOrderCompleteConfirmationPresenter;
  private String mOrderId;
  private TextView mRemainingTimerText;
  private LinearLayout mYesButton;
  
  public void hideProgressBar()
  {
    dismissSimpleProgressDialog();
  }
  
  public void initView()
  {
    this.mRemainingTimerText = ((TextView)findViewById(R.id.confirmation_remaining_time));
    this.mNoButton = ((LinearLayout)findViewById(R.id.no_confirm_button));
    this.mYesButton = ((LinearLayout)findViewById(R.id.yes_confirm_button));
    this.mNoButton.setOnClickListener(this);
    this.mYesButton.setOnClickListener(this);
  }
  
  public void onBackPressed()
  {
    setResult(0);
    finish();
  }
  
  public void onClick(View paramView)
  {
    if (ConnectionManager.isConnected(this))
    {
      if (paramView.getId() == R.id.yes_confirm_button) {
        this.mOrderCompleteConfirmationPresenter.onYesButtonClicked(this.mOrderId);
      }
      while (paramView.getId() != R.id.no_confirm_button) {
        return;
      }
      this.mOrderCompleteConfirmationPresenter.onNoButtonClicked(this.mOrderId);
      return;
    }
    noInternetConnectionHandler();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_order_complete_confirmation);
    initToolbarView();
    setTitle(getString(R.string.gobox_title));
    this.mOrderCompleteConfirmationPresenter = PresenterFactory.createOrderCompleteConfirmationPresenter(this, getNetworkManager());
    this.mOrderId = getIntent().getStringExtra("order id");
    this.mOrderCompleteConfirmationPresenter.onCreateView(this.mOrderId);
  }
  
  protected void onStop()
  {
    if (this.mCountDownTimer != null) {
      this.mCountDownTimer.cancel();
    }
    super.onStop();
  }
  
  public void showDriverStatusScreen(boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("confirmation result", paramBoolean);
    setResult(-1, localIntent);
    finish();
  }
  
  public void showErrorMessage(Throwable paramThrowable)
  {
    dismissSimpleProgressDialog();
    errorConnectionHandler(paramThrowable);
  }
  
  public void showProgressBar(String paramString)
  {
    showSimpleProgressDialog(paramString);
  }
  
  public void startRemainingTime(long paramLong)
  {
    this.mCountDownTimer = new OrderCompleteConfirmation.1(this, paramLong, 1000L);
    this.mRemainingTimerText.setVisibility(0);
    this.mCountDownTimer.start();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/view/OrderCompleteConfirmation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */