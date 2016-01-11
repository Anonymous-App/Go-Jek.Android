package io.card.payment;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.UUID;

public class CreditCard
  implements Parcelable
{
  public static final Parcelable.Creator<CreditCard> CREATOR = new CreditCard.1();
  public static final int EXPIRY_MAX_FUTURE_YEARS = 15;
  private static final String TAG = CreditCard.class.getSimpleName();
  public String cardNumber;
  public String cvv;
  public int expiryMonth = 0;
  public int expiryYear = 0;
  boolean flipped = false;
  public String postalCode;
  String scanId;
  int[] xoff;
  int yoff;
  
  public CreditCard()
  {
    this.xoff = new int[16];
    this.scanId = UUID.randomUUID().toString();
  }
  
  private CreditCard(Parcel paramParcel)
  {
    this.cardNumber = paramParcel.readString();
    this.expiryMonth = paramParcel.readInt();
    this.expiryYear = paramParcel.readInt();
    this.cvv = paramParcel.readString();
    this.postalCode = paramParcel.readString();
    this.scanId = paramParcel.readString();
    this.yoff = paramParcel.readInt();
    this.xoff = paramParcel.createIntArray();
  }
  
  public CreditCard(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3)
  {
    this.cardNumber = paramString1;
    this.expiryMonth = paramInt1;
    this.expiryYear = paramInt2;
    this.cvv = paramString2;
    this.postalCode = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CardType getCardType()
  {
    return CardType.fromCardNumber(this.cardNumber);
  }
  
  public String getFormattedCardNumber()
  {
    return CreditCardNumber.formatString(this.cardNumber);
  }
  
  public String getLastFourDigitsOfCardNumber()
  {
    if (this.cardNumber != null)
    {
      int i = Math.min(4, this.cardNumber.length());
      return this.cardNumber.substring(this.cardNumber.length() - i);
    }
    return "";
  }
  
  public String getRedactedCardNumber()
  {
    if (this.cardNumber != null)
    {
      String str = "";
      if (this.cardNumber.length() > 4) {
        str = "" + String.format(new StringBuilder().append("%").append(this.cardNumber.length() - 4).append("s").toString(), new Object[] { "" }).replace(' ', '•');
      }
      return CreditCardNumber.formatString(str + getLastFourDigitsOfCardNumber(), false, CardType.fromCardNumber(this.cardNumber));
    }
    return "";
  }
  
  public boolean isExpiryValid()
  {
    return CreditCardNumber.isDateValid(this.expiryMonth, this.expiryYear);
  }
  
  public String toString()
  {
    Object localObject2 = "{" + getCardType() + ": " + getRedactedCardNumber();
    if (this.expiryMonth <= 0)
    {
      localObject1 = localObject2;
      if (this.expiryYear <= 0) {}
    }
    else
    {
      localObject1 = (String)localObject2 + "  expiry:" + this.expiryMonth + "/" + this.expiryYear;
    }
    localObject2 = localObject1;
    if (this.postalCode != null) {
      localObject2 = (String)localObject1 + "  postalCode:" + this.postalCode;
    }
    Object localObject1 = localObject2;
    if (this.cvv != null)
    {
      localObject1 = new StringBuilder().append((String)localObject2).append("  cvvLength:");
      if (this.cvv == null) {
        break label196;
      }
    }
    label196:
    for (int i = this.cvv.length();; i = 0)
    {
      localObject1 = i;
      return (String)localObject1 + "}";
    }
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.cardNumber);
    paramParcel.writeInt(this.expiryMonth);
    paramParcel.writeInt(this.expiryYear);
    paramParcel.writeString(this.cvv);
    paramParcel.writeString(this.postalCode);
    paramParcel.writeString(this.scanId);
    paramParcel.writeInt(this.yoff);
    paramParcel.writeIntArray(this.xoff);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/CreditCard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */