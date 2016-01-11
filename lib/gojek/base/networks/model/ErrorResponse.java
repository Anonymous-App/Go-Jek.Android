package lib.gojek.base.networks.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse
{
  private String error;
  @SerializedName("error_description")
  private String errorDescription;
  
  public ErrorResponse(String paramString)
  {
    this.error = paramString;
  }
  
  public String getError()
  {
    return this.error;
  }
  
  public String getErrorDescription()
  {
    return this.errorDescription;
  }
  
  public void setError(String paramString)
  {
    this.error = paramString;
  }
  
  public void setErrorDescription(String paramString)
  {
    this.errorDescription = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/model/ErrorResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */