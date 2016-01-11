package com.crashlytics.android.answers;

import java.math.BigDecimal;
import java.util.Currency;

public class AddToCartEvent
  extends PredefinedEvent<AddToCartEvent>
{
  static final String CURRENCY_ATTRIBUTE = "currency";
  static final String ITEM_ID_ATTRIBUTE = "itemId";
  static final String ITEM_NAME_ATTRIBUTE = "itemName";
  static final String ITEM_PRICE_ATTRIBUTE = "itemPrice";
  static final String ITEM_TYPE_ATTRIBUTE = "itemType";
  static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000L);
  static final String TYPE = "addToCart";
  
  String getPredefinedType()
  {
    return "addToCart";
  }
  
  long priceToMicros(BigDecimal paramBigDecimal)
  {
    return MICRO_CONSTANT.multiply(paramBigDecimal).longValue();
  }
  
  public AddToCartEvent putCurrency(Currency paramCurrency)
  {
    if (!this.validator.isNull(paramCurrency, "currency")) {
      this.predefinedAttributes.put("currency", paramCurrency.getCurrencyCode());
    }
    return this;
  }
  
  public AddToCartEvent putItemId(String paramString)
  {
    this.predefinedAttributes.put("itemId", paramString);
    return this;
  }
  
  public AddToCartEvent putItemName(String paramString)
  {
    this.predefinedAttributes.put("itemName", paramString);
    return this;
  }
  
  public AddToCartEvent putItemPrice(BigDecimal paramBigDecimal)
  {
    if (!this.validator.isNull(paramBigDecimal, "itemPrice")) {
      this.predefinedAttributes.put("itemPrice", Long.valueOf(priceToMicros(paramBigDecimal)));
    }
    return this;
  }
  
  public AddToCartEvent putItemType(String paramString)
  {
    this.predefinedAttributes.put("itemType", paramString);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/answers/AddToCartEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */