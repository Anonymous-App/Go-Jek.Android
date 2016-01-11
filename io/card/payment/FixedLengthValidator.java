package io.card.payment;

import android.text.Editable;
import android.text.Spanned;

class FixedLengthValidator
  implements Validator
{
  public int requiredLength;
  private String value;
  
  public FixedLengthValidator(int paramInt)
  {
    this.requiredLength = paramInt;
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    this.value = paramEditable.toString();
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    if ((paramInt2 > 0) && (paramSpanned.length() + paramInt4 - paramInt3 + paramInt2 > this.requiredLength)) {
      return "";
    }
    return null;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public boolean hasFullLength()
  {
    return isValid();
  }
  
  public boolean isValid()
  {
    return (this.value != null) && (this.value.length() == this.requiredLength);
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/FixedLengthValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */