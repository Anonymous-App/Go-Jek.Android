package io.card.payment;

import android.text.InputFilter;
import android.text.TextWatcher;

abstract interface Validator
  extends InputFilter, TextWatcher
{
  public abstract String getValue();
  
  public abstract boolean hasFullLength();
  
  public abstract boolean isValid();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/Validator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */