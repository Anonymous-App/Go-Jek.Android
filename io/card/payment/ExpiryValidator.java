package io.card.payment;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import java.util.Date;

class ExpiryValidator
  implements Validator
{
  private final String TAG = getClass().getName();
  private boolean fullLength;
  public int month;
  public int year;
  
  public ExpiryValidator() {}
  
  public ExpiryValidator(int paramInt1, int paramInt2)
  {
    this.month = paramInt1;
    this.year = paramInt2;
    if ((this.month > 0) && (this.year > 0)) {}
    for (boolean bool = true;; bool = false)
    {
      this.fullLength = bool;
      if (this.year < 2000) {
        this.year += 2000;
      }
      return;
    }
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    boolean bool;
    if (paramEditable.length() >= 5)
    {
      bool = true;
      this.fullLength = bool;
      paramEditable = paramEditable.toString();
      if (paramEditable != null) {
        break label32;
      }
    }
    label32:
    do
    {
      do
      {
        return;
        bool = false;
        break;
        paramEditable = CreditCardNumber.getDateForString(paramEditable);
      } while (paramEditable == null);
      this.month = (paramEditable.getMonth() + 1);
      this.year = paramEditable.getYear();
    } while (this.year >= 1900);
    this.year += 1900;
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.month = 0;
    this.year = 0;
    this.fullLength = false;
  }
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    paramCharSequence = new SpannableStringBuilder(paramCharSequence);
    int i = paramInt2;
    if (paramInt3 == 0)
    {
      i = paramInt2;
      if (paramCharSequence.length() > 0)
      {
        i = paramInt2;
        if ('1' < paramCharSequence.charAt(0))
        {
          i = paramInt2;
          if (paramCharSequence.charAt(0) <= '9')
          {
            paramCharSequence.insert(0, "0");
            i = paramInt2 + 1;
          }
        }
      }
    }
    int j = paramInt4 - paramInt3;
    paramInt2 = i;
    if (paramInt3 - j <= 2)
    {
      paramInt2 = i;
      if (paramInt3 + i - j >= 2)
      {
        j = 2 - paramInt3;
        if (j != i)
        {
          paramInt2 = i;
          if (j >= 0)
          {
            paramInt2 = i;
            if (j < i)
            {
              paramInt2 = i;
              if (paramCharSequence.charAt(j) == '/') {}
            }
          }
        }
        else
        {
          paramCharSequence.insert(j, "/");
          paramInt2 = i + 1;
        }
      }
    }
    paramSpanned = new SpannableStringBuilder(paramSpanned).replace(paramInt3, paramInt4, paramCharSequence, paramInt1, paramInt2).toString();
    if ((paramSpanned.length() >= 1) && ((paramSpanned.charAt(0) < '0') || ('1' < paramSpanned.charAt(0)))) {
      paramCharSequence = "";
    }
    do
    {
      return paramCharSequence;
      if (paramSpanned.length() >= 2)
      {
        if ((paramSpanned.charAt(0) != '0') && (paramSpanned.charAt(1) > '2')) {
          return "";
        }
        if ((paramSpanned.charAt(0) == '0') && (paramSpanned.charAt(1) == '0')) {
          return "";
        }
      }
    } while (paramSpanned.length() <= 5);
    return "";
  }
  
  public String getValue()
  {
    return String.format("%02d/%02d", new Object[] { Integer.valueOf(this.month), Integer.valueOf(this.year % 100) });
  }
  
  public boolean hasFullLength()
  {
    return this.fullLength;
  }
  
  public boolean isValid()
  {
    if ((this.month < 1) || (12 < this.month)) {}
    Date localDate;
    do
    {
      return false;
      localDate = new Date();
    } while ((this.year > localDate.getYear() + 1900 + 15) || ((this.year <= localDate.getYear() + 1900) && ((this.year != localDate.getYear() + 1900) || (this.month < localDate.getMonth() + 1))));
    return true;
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/ExpiryValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */