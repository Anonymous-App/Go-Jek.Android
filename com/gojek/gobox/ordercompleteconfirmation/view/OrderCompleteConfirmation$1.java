package com.gojek.gobox.ordercompleteconfirmation.view;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.gojek.gobox.ordercompleteconfirmation.presenter.OrderCompleteConfirmationPresenter;
import java.util.concurrent.TimeUnit;

class OrderCompleteConfirmation$1
  extends CountDownTimer
{
  OrderCompleteConfirmation$1(OrderCompleteConfirmation paramOrderCompleteConfirmation, long paramLong1, long paramLong2)
  {
    super(paramLong1, paramLong2);
  }
  
  public void onFinish()
  {
    OrderCompleteConfirmation.access$200(this.this$0).onYesButtonClicked(OrderCompleteConfirmation.access$100(this.this$0));
  }
  
  public void onTick(long paramLong)
  {
    String str = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toHours(paramLong)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(paramLong) % TimeUnit.HOURS.toMinutes(1L)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(paramLong) % TimeUnit.MINUTES.toSeconds(1L)) });
    OrderCompleteConfirmation.access$000(this.this$0).setText(str);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/ordercompleteconfirmation/view/OrderCompleteConfirmation$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */