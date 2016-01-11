package com.gojek.gotix.views;

import android.view.View;
import android.widget.TextView;
import com.gojek.gotix.R.id;
import com.gojek.gotix.network.model.PaymentComponent;
import com.gojek.gotix.tools.GotixUtils;

public class PaymentComponentHolder
{
  private static final String TYPE_AMOUNT = "amount";
  private static final String TYPE_PERCENTAGE = "percentage";
  private TextView paymentTitle;
  private TextView paymentValue;
  private View view;
  
  public PaymentComponentHolder(View paramView, PaymentComponent paramPaymentComponent)
  {
    this.view = paramView;
    bindViewById(paramView);
    this.paymentTitle.setText(paramPaymentComponent.getPayment_title());
    paramView = "";
    if (paramPaymentComponent.getPayment_type().equals("amount")) {
      paramView = formatToStringRupiah(paramPaymentComponent.getPayment_description());
    }
    for (;;)
    {
      this.paymentValue.setText(paramView);
      return;
      if (paramPaymentComponent.getPayment_type().equals("percentage")) {
        paramView = formatToStringPercentage(paramPaymentComponent.getPayment_description());
      }
    }
  }
  
  private void bindViewById(View paramView)
  {
    this.paymentTitle = ((TextView)paramView.findViewById(R.id.payment_component_title));
    this.paymentValue = ((TextView)paramView.findViewById(R.id.payment_component_value));
  }
  
  private String formatToStringPercentage(String paramString)
  {
    return GotixUtils.getPercentageFormat(paramString);
  }
  
  private String formatToStringRupiah(String paramString)
  {
    return GotixUtils.getRupiahFormat(paramString);
  }
  
  public View getView()
  {
    return this.view;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/views/PaymentComponentHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */