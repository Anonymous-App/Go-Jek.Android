package io.card.payment;

class MaxLengthValidator
  extends NonEmptyValidator
  implements Validator
{
  private int maxLength;
  
  MaxLengthValidator(int paramInt)
  {
    this.maxLength = paramInt;
  }
  
  public boolean isValid()
  {
    return (super.isValid()) && (getValue().length() <= this.maxLength);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/MaxLengthValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */