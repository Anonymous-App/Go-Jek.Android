package com.gojek.app.model;

import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;

public class MerchantSuggestion
{
  public boolean added;
  public String addedTime;
  public int customerId;
  public String doneBy;
  public String doneStatus;
  public BigDecimal latitude;
  public BigDecimal longitude;
  public String merchantAddress;
  public String merchantAddressDetail;
  public String merchantLocationName;
  public String merchantName;
  public String phone;
  public int poiType;
  public String suggestionTime;
  
  public JSONObject toJsonObject()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("customerId", this.customerId);
      localJSONObject.put("merchantName", this.merchantName);
      localJSONObject.put("merchantAddress", this.merchantAddress);
      localJSONObject.put("merchantAddressDetail", this.merchantAddressDetail);
      localJSONObject.put("merchantLocationName", this.merchantLocationName);
      localJSONObject.put("added", this.added);
      localJSONObject.put("poiType", this.poiType);
      localJSONObject.put("doneStatus", this.doneStatus);
      localJSONObject.put("doneBy", this.doneBy);
      localJSONObject.put("phone", this.phone);
      localJSONObject.put("latitude", this.latitude);
      localJSONObject.put("longitude", this.longitude);
      localJSONObject.put("suggestionTime", this.suggestionTime);
      localJSONObject.put("addedTime", this.addedTime);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/MerchantSuggestion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */