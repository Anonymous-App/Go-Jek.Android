package com.crashlytics.android.answers;

import java.math.BigDecimal;
import java.util.Currency;

public class StartCheckoutEvent
  extends PredefinedEvent<StartCheckoutEvent>
{
  static final String CURRENCY_ATTRIBUTE = "currency";
  static final String ITEM_COUNT_ATTRIBUTE = "itemCount";
  static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000L);
  static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
  static final String TYPE = "startCheckout";
  
  String getPredefinedType()
  {
    return "startCheckout";
  }
  
  long priceToMicros(BigDecimal paramBigDecimal)
  {
    return MICRO_CONSTANT.multiply(paramBigDecimal).longValue();
  }
  
  public StartCheckoutEvent putCurrency(Currency paramCurrency)
  {
    if (!this.validator.isNull(paramCurrency, "currency")) {
      this.predefinedAttributes.put("currency", paramCurrency.getCurrencyCode());
    }
    return this;
  }
  
  public StartCheckoutEvent putItemCount(int paramInt)
  {
    this.predefinedAttributes.put("itemCount", Integer.valueOf(paramInt));
    return this;
  }
  
  public StartCheckoutEvent putTotalPrice(BigDecimal paramBigDecimal)
  {
    if (!this.validator.isNull(paramBigDecimal, "totalPrice")) {
      this.predefinedAttributes.put("totalPrice", Long.valueOf(priceToMicros(paramBigDecimal)));
    }
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/StartCheckoutEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */