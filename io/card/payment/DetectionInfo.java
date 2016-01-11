package io.card.payment;

class DetectionInfo
{
  public boolean bottomEdge;
  public boolean complete = false;
  public CreditCard detectedCard;
  public int expiry_month;
  public int expiry_year;
  public float focusScore;
  public boolean leftEdge;
  public int[] prediction = new int[16];
  public boolean rightEdge;
  public boolean topEdge;
  
  public DetectionInfo()
  {
    this.prediction[0] = -1;
    this.prediction[15] = -1;
    this.detectedCard = new CreditCard();
  }
  
  CreditCard creditCard()
  {
    String str = new String();
    int i = 0;
    while ((i < 16) && (this.prediction[i] >= 0) && (this.prediction[i] < 10))
    {
      str = str + String.valueOf(this.prediction[i]);
      i += 1;
    }
    this.detectedCard.cardNumber = str;
    this.detectedCard.expiryMonth = this.expiry_month;
    this.detectedCard.expiryYear = this.expiry_year;
    return this.detectedCard;
  }
  
  boolean detected()
  {
    return (this.topEdge) && (this.bottomEdge) && (this.rightEdge) && (this.leftEdge);
  }
  
  int numVisibleEdges()
  {
    int m = 1;
    int i;
    int j;
    label21:
    int k;
    if (this.topEdge)
    {
      i = 1;
      if (!this.bottomEdge) {
        break label51;
      }
      j = 1;
      if (!this.leftEdge) {
        break label56;
      }
      k = 1;
      label30:
      if (!this.rightEdge) {
        break label61;
      }
    }
    for (;;)
    {
      return k + (j + i) + m;
      i = 0;
      break;
      label51:
      j = 0;
      break label21;
      label56:
      k = 0;
      break label30;
      label61:
      m = 0;
    }
  }
  
  boolean predicted()
  {
    return this.complete;
  }
  
  boolean sameEdgesAs(DetectionInfo paramDetectionInfo)
  {
    return (paramDetectionInfo.topEdge == this.topEdge) && (paramDetectionInfo.bottomEdge == this.bottomEdge) && (paramDetectionInfo.leftEdge == this.leftEdge) && (paramDetectionInfo.rightEdge == this.rightEdge);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/card/payment/DetectionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */