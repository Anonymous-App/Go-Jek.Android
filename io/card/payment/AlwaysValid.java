package io.card.payment;

import android.text.Editable;
import android.text.Spanned;

class AlwaysValid
  implements Validator
{
  private String placeholder;
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    return null;
  }
  
  public String getValue()
  {
    return this.placeholder;
  }
  
  public boolean hasFullLength()
  {
    return true;
  }
  
  public boolean isValid()
  {
    return true;
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/AlwaysValid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */