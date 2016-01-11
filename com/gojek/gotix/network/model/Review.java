package com.gojek.gotix.network.model;

import com.google.gson.annotations.SerializedName;

public class Review
{
  @SerializedName("driver_comment")
  public String driverComment;
  @SerializedName("driver_rating")
  public float driverRating;
  @SerializedName("gotix_comment")
  public String gotixComment;
  @SerializedName("gotix_rating")
  public float gotixRating;
  
  public Review(float paramFloat1, String paramString1, float paramFloat2, String paramString2)
  {
    this.gotixRating = paramFloat1;
    this.gotixComment = paramString1;
    this.driverRating = paramFloat2;
    this.driverComment = paramString2;
  }
  
  public String getDriverComment()
  {
    return this.driverComment;
  }
  
  public float getDriverRating()
  {
    return this.driverRating;
  }
  
  public String getGotixComment()
  {
    return this.gotixComment;
  }
  
  public float getGotixRating()
  {
    return this.gotixRating;
  }
  
  public void setDriverComment(String paramString)
  {
    this.driverComment = paramString;
  }
  
  public void setDriverRating(float paramFloat)
  {
    this.driverRating = paramFloat;
  }
  
  public void setGotixComment(String paramString)
  {
    this.gotixComment = paramString;
  }
  
  public void setGotixRating(float paramFloat)
  {
    this.gotixRating = paramFloat;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/Review.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */