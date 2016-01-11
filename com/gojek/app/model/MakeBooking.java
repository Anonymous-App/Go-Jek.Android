package com.gojek.app.model;

import java.util.ArrayList;
import java.util.List;

public class MakeBooking
{
  public int activitySource = 2;
  public MakeBookingBuyer buyer = new MakeBookingBuyer();
  public MakeBookingCorporateFee corporateFee = new MakeBookingCorporateFee();
  public String corporatePin = null;
  public int customerId = 0;
  public String deviceToken = "";
  public String gcmKey = "";
  public int optionReturn = 0;
  public int paymentType = 0;
  public List<MakeBookingRoutes> routes = new ArrayList();
  public String timeField = "";
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/app/model/MakeBooking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */