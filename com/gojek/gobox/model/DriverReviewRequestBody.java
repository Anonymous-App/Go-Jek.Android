package com.gojek.gobox.model;

public class DriverReviewRequestBody
{
  private String comment;
  private int rate;
  
  public DriverReviewRequestBody(int paramInt, String paramString)
  {
    this.rate = paramInt;
    this.comment = paramString;
  }
  
  public String getComment()
  {
    return this.comment;
  }
  
  public int getRate()
  {
    return this.rate;
  }
  
  public void setComment(String paramString)
  {
    this.comment = paramString;
  }
  
  public void setRate(int paramInt)
  {
    this.rate = paramInt;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/model/DriverReviewRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */