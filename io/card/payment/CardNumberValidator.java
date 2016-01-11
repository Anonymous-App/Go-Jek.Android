package io.card.payment;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;

class CardNumberValidator
  implements Validator
{
  static final int[] AMEX_SPACER = { 4, 11 };
  static final int[] NORMAL_SPACER = { 4, 9, 14 };
  private String numberString;
  private int spacerToDelete = 0;
  
  public CardNumberValidator() {}
  
  public CardNumberValidator(String paramString)
  {
    this.numberString = paramString;
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    this.numberString = StringHelper.getDigitsOnlyString(paramEditable.toString());
    CardType localCardType = CardType.fromCardNumber(this.numberString);
    int j;
    if (this.spacerToDelete > 1)
    {
      i = this.spacerToDelete;
      j = this.spacerToDelete - 1;
      this.spacerToDelete = 0;
      if (i > j) {
        paramEditable.delete(j, i);
      }
    }
    int i = 0;
    if (i < paramEditable.length())
    {
      int k = paramEditable.charAt(i);
      if (((localCardType.numberLength() == 15) && ((i == 4) || (i == 11))) || (((localCardType.numberLength() == 16) || (localCardType.numberLength() == 14)) && ((i == 4) || (i == 9) || (i == 14))))
      {
        j = i;
        if (k != 32)
        {
          paramEditable.insert(i, " ");
          j = i;
        }
      }
      for (;;)
      {
        i = j + 1;
        break;
        j = i;
        if (k == 32)
        {
          paramEditable.delete(i, i + 1);
          j = i - 1;
        }
      }
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    Object localObject = StringHelper.getDigitsOnlyString(new SpannableStringBuilder(paramSpanned).replace(paramInt3, paramInt4, paramCharSequence, paramInt1, paramInt2).toString());
    paramInt1 = CardType.fromCardNumber((String)localObject).numberLength();
    if (((String)localObject).length() > paramInt1)
    {
      localObject = "";
      return (CharSequence)localObject;
    }
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
    if (paramInt1 == 15) {}
    for (int[] arrayOfInt = AMEX_SPACER;; arrayOfInt = NORMAL_SPACER)
    {
      int i = paramInt4 - paramInt3;
      paramInt1 = 0;
      for (;;)
      {
        localObject = localSpannableStringBuilder;
        if (paramInt1 >= arrayOfInt.length) {
          break;
        }
        if ((paramCharSequence.length() == 0) && (paramInt3 == arrayOfInt[paramInt1]) && (paramSpanned.charAt(paramInt3) == ' ')) {
          this.spacerToDelete = arrayOfInt[paramInt1];
        }
        paramInt4 = paramInt2;
        if (paramInt3 - i <= arrayOfInt[paramInt1])
        {
          paramInt4 = paramInt2;
          if (paramInt3 + paramInt2 - i >= arrayOfInt[paramInt1])
          {
            int j = arrayOfInt[paramInt1] - paramInt3;
            if (j != paramInt2)
            {
              paramInt4 = paramInt2;
              if (j >= 0)
              {
                paramInt4 = paramInt2;
                if (j < paramInt2)
                {
                  paramInt4 = paramInt2;
                  if (localSpannableStringBuilder.charAt(j) == ' ') {}
                }
              }
            }
            else
            {
              localSpannableStringBuilder.insert(j, " ");
              paramInt4 = paramInt2 + 1;
            }
          }
        }
        paramInt1 += 1;
        paramInt2 = paramInt4;
      }
    }
  }
  
  public String getValue()
  {
    return this.numberString;
  }
  
  public boolean hasFullLength()
  {
    if (TextUtils.isEmpty(this.numberString)) {}
    CardType localCardType;
    do
    {
      return false;
      localCardType = CardType.fromCardNumber(this.numberString);
    } while (this.numberString.length() != localCardType.numberLength());
    return true;
  }
  
  public boolean isValid()
  {
    if (!hasFullLength()) {}
    while (!CreditCardNumber.passesLuhnChecksum(this.numberString)) {
      return false;
    }
    return true;
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/CardNumberValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */